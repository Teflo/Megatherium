/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import sargtex.sql.Database;
import megatherium.util.ReportUtil;

/**
 * This class is designed for objects deriving from database entries.
 * You can see a good example at the class "megatherium.api.data.User".
 * Use such classes for all database-related operations according to those tables and use one class for each table.
 * 
 * I don't know how we should handle multi-table-requests, but let's talk about that later.
 * 
 * @author SargTeX
 */
public abstract class DatabaseObject {
	private static Map<String, Map<String, Map<String, String>>> objectsDataMap = new HashMap<String, Map<String, Map<String, String>>>();
	private Map<String, String> updateMap = new HashMap<String, String>();
	private Map<String, String> data = new HashMap<String, String>();
	private Map<String, DatabaseObject> linkings = new HashMap<String, DatabaseObject>();
	private Map<String, DatabaseObject[]> linkedResources = new HashMap<String, DatabaseObject[]>();
	private boolean exists = false;
	
	/**
	 * Initializes an empty object. This object can be used to create a new one.
	 */
	public DatabaseObject() {this.initializeLinkings();}

	/**
	 * Initializes the object by it's id.
	 *
	 * @param id the id of this database object
	 */
	public DatabaseObject(int id) throws SQLException {
		this.initialize(id+"");
		this.initializeLinkings();
	}
	
	/**
	 * Initializes the object by it's id.
	 * Use this constructor if the object's id property isn't an integer.
	 * 
	 * @param id the id of this database object
	 */
	public DatabaseObject(String id) throws SQLException {
		this.initialize(id);
		this.initializeLinkings();
	}

	/**
	 * Initializes the object with a result set that points to this object.
	 *
	 * @param resultSet the result set
	 */
	public DatabaseObject(ResultSet resultSet) throws SQLException {
		this.initialize(resultSet);
		this.initializeLinkings();
	}

	/**
	 * Initializes the object by it's data.
	 *
	 * @param data the data
	 */
	public DatabaseObject(Map<String, String> data) {
		// set local data
		this.data = data;
		
		// cache data
		DatabaseObject.cache(this.getTableName(), data.get(this.getIDProperty()), data);
		this.initializeLinkings();
	}
	
	/**
	 * Initializes the object by using it's id.
	 * 
	 * @param id the id of this object => the value of the field named this.getIDProperty()
	 */
	protected void initialize(String id) throws SQLException {
		// look into cache
		if (DatabaseObject.hasCached(this.getTableName(), id)) {
			this.data = DatabaseObject.getCached(this.getTableName(), id);
			this.exists = true;
			return;
		}
		
		// fetch object
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM " + this.getTableName() + " WHERE " + this.getIDProperty() + " = ?", new String[] {id});
		if (rs.first()) {
			this.initialize(rs);
			this.exists = true;
		} else {
			this.set(this.getIDProperty(), id);
		}
	}
	
	/**
	 * Adds linkings to this class.
	 */
	protected void initializeLinkings(){}
	
	/**
	 * Adds a new linking to another database object to this class description.
	 * 
	 * @param name the name of the resulted field, shouldn't be contained in the field name list
	 * @param databaseObject an (empty) instance of the linked object type
	 * @return the current object for faster programming
	 */
	protected DatabaseObject addLinking(String name, DatabaseObject databaseObject) {
		this.linkings.put(name, databaseObject);
		return this;
	}
	
	/**
	 * Loads the linked tables.
	 * 
	 * @return the current object for faster programming
	 */
	public DatabaseObject loadLinkings() throws SQLException {
		// loop through linkings
		Set<String> names = this.linkings.keySet();
		for (String name : names) {
			DatabaseObject object = this.linkings.get(name);
			List<DatabaseObject> objectList = new ArrayList<DatabaseObject>();
			
			// load object
			ResultSet rs = Database.getInstance().fetch("SELECT * FROM "+object.getTableName()+" WHERE "+this.getForeignKeyName()+" = ?", new String[] {this.get(this.getIDProperty())});
			while (rs.next()) try {
				objectList.add(object.getClass().getDeclaredConstructor(ResultSet.class).newInstance(rs));
			} catch (Exception ex) {
				ReportUtil.getInstance().add(ex);
			}
			
			// save linking
			this.linkedResources.put(name, objectList.toArray(new DatabaseObject[]{}));
		}
		
		// return object
		return this;
	}
	
	/**
	 * Sets the data of a linked resource.
	 * 
	 * @param name the name of the resource link
	 * @param objects the objects
	 * @return the class instance itself for faster programming
	 */
	public DatabaseObject setLinkedResource(String name, DatabaseObject[] objects) {
		this.linkedResources.put(name, objects);
		return this;
	}
	
	/**
	 * Returns true if this object exists in the database.
	 * This method only works currently for datasets initialized by id or result set.
	 * 
	 * @return true or false
	 */
	public boolean exists() {
		return this.exists;
	}

	/**
	 * Sets the new value for the field.
	 *
	 * @param name the name of the field
	 * @param value the new value
	 * @return returns the current instance
	 */
	public DatabaseObject set(String name, String value) {
		updateMap.put(name, value);
		ReportUtil.getInstance().add("Set \""+name+"\" to \""+value+"\"");

		return this;
	}
	
	/**
	 * Initializes the object by the result set that points to this object.
	 * 
	 * @param resultSet the result set
	 */
	private void initialize(ResultSet rs) throws SQLException {
		this.exists = true;
		
		// fetch data
		for (int i = 0; i < getFields().length; ++i) {
			boolean found = false;
			for (int j = 1; j <= rs.getMetaData().getColumnCount(); ++j)
				if (rs.getMetaData().getColumnName(j).equals(getFields()[i])) found = true;
			if (found) data.put(getFields()[i], rs.getString(getFields()[i]));
		}
		
		// cache this
		DatabaseObject.cache(this.getTableName(), data.get(this.getIDProperty()), data);
	}
	
	/**
	 * Caches an object to avoid multi-instanciating the same data.
	 * 
	 * @param tableName the name of the table of the object
	 * @param id some id value of the object
	 * @param data the data of the object
	 */
	protected static void cache(String tableName, String id, Map<String, String> data) {
		if (!objectsDataMap.containsKey(tableName)) objectsDataMap.put(tableName, new HashMap<String, Map<String, String>>());
		
		// cache object
		objectsDataMap.get(tableName).put(id, data);
	}
	
	/**
	 * Checks whether the object has been cached or not.
	 * 
	 * @param tableName the name of the table
	 * @param id the id of the object
	 */
	protected static boolean hasCached(String tableName, String id) {
		return (objectsDataMap.containsKey(tableName) && objectsDataMap.get(tableName).containsKey(id));
	}
	
	/**
	 * Returns the cached data if it exists or null.
	 * 
	 * @param tableName the name of the table
	 * @param id the id of the object
	 */
	protected static Map<String, String> getCached(String tableName, String id) {
		return (hasCached(tableName, id)) ? objectsDataMap.get(tableName).get(id) : null;
	}
	
	/**
	 * Creates the object in the database.
	 * This method also automatically creates all the linked resources.
	 * 
	 * @return the object itself
	 */
	public DatabaseObject create() throws SQLException {
		String[] params = new String[this.updateMap.size()];
		Object[] fieldNames = this.updateMap.keySet().toArray();
		String query = "INSERT INTO "+getTableName()+" (";
		
		// add things to query
		for (int i = 0; i < fieldNames.length; ++i) {
			if (i > 0) query += ", ";
			query += "`"+(String) fieldNames[i]+"`";
		}
		
		// add value questionmarks and parameters
		query += ") VALUES (";
		for (int i = 0; i < fieldNames.length; ++i) {
			if (i > 0) query += ", ";
			query += "?";
			params[i] = updateMap.get(fieldNames[i]);
		}
		query += ")";
		
		// execute query
		int id = Database.getInstance().execute(query, params);
		
		// update data
		for (Object name : fieldNames) {
			this.data.put((String) name, updateMap.get(name));
			updateMap.remove(name);
		}
		if (id > 0) this.data.put(getIDProperty(), id+"");
		
		// mark as existing
		this.exists = true;
		
		// create resources
		Set<String> keySet = this.linkedResources.keySet();
		ReportUtil.getInstance().add("Linked resources: "+keySet.size());
		for (String key : keySet) {
			DatabaseObject[] objects = this.linkedResources.get(key);
			ReportUtil.getInstance().add("Linked objects of \""+key+"\": "+objects.length);
			
			// loop through objects and create each of them
			for (DatabaseObject object : objects) {
				object.set(this.getForeignKeyName(), id+"");
				object.create();
			}
		}
		
		// return the object
		return this;
	}

	/**
	 * Updates the object.
	 * 
	 * @return the object itself
	 */
	public DatabaseObject update() throws SQLException {
		// abort update if no new data is available
		if (this.updateMap.size() == 0) {
			return this;
		}

		// generate query
		String query = "UPDATE " + getTableName() + " SET ";
		String[] params = new String[updateMap.size()+1];

		// generate update query
		Object[] keys = updateMap.keySet().toArray();
		for (int i = 0; i < updateMap.size(); ++i) {
			if (i > 0) query += ", ";
			this.data.put((String) keys[i], this.updateMap.get(keys[i]));
			query += keys[i] + " = ? ";
			params[i] = this.get((String) keys[i]);
		}

		// add where clause
		query += "WHERE " + getIDProperty() + " = ?";
		params[updateMap.size()] = this.get(getIDProperty());

		// run query
		Database.getInstance().execute(query, params);
		
		// reset update map
		updateMap = new HashMap<String, String>();
		
		// return object instance
		return this;
	}
	
	/**
	 * Deletes the entry from the database.
	 * 
	 * @return the object itself
	 */
	public DatabaseObject delete() throws SQLException {
		Database.getInstance().execute("DELETE FROM "+getTableName()+" WHERE "+getIDProperty()+" = ?", new String[] {this.get(getIDProperty())});
		
		// this record doesn't exist any more
		this.exists = false;
		
		return this;
	}

	/**
	 * Returns the current value of the field (including updateMap).
	 *
	 * @param name the name of the field
	 * @return the value
	 */
	public String get(String name) {
		return (String) data.get(name);
	}
	
	/**
	 * Returns an array with names of the fields of this class.
	 * 
	 * @return an array with the field names
	 */
	protected abstract String[] getFields();
	
	/**
	 * Returns the name of the table.
	 * 
	 * @return the table name
	 */
	protected abstract String getTableName();
	
	/**
	 * Returns the name of the foreign key.
	 * E.g. for the table "lak_habitat", this would be "habitatID".
	 * 
	 * @return the name of the foreign key or null if this object isn't permitted to own a foreign key
	 */
	protected String getForeignKeyName() {
		return null;
	}
	
	/**
	 * Returns the name of the id field.
	 * 
	 * @return the name of the id field (default: "id")
	 */
	protected String getIDProperty() {return "id";}
	
	/**
	 * Returns a map with the current data of this object.
	 * This should be used carefully and is mainly designed for APIs.
	 * 
	 * @return the data map
	 */
	public Map<String, Object> getData() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.putAll(this.data);
		data.putAll(this.linkedResources);
		return data;
	}
	
}
