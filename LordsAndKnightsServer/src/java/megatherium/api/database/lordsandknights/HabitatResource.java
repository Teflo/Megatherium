/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package megatherium.api.database.lordsandknights;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class HabitatResource extends DatabaseObject {
    
	public HabitatResource() {super();}
	public HabitatResource(int id) throws SQLException {super(id);}
	public HabitatResource(Map<String, String> data) {super(data);}
	public HabitatResource(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return (this.get("id") == null) ? 0 : Integer.parseInt(this.get("id"));}
	public int getHabitatID() {return Integer.parseInt(this.get("habitatID"));}
	public int getResourceID() {return Integer.parseInt(this.get("resourceID"));}
	public int getAmount() {return Integer.parseInt(this.get("amount"));}
	public HabitatResource setHabitatID(int habitatID) {this.set("habitatID", habitatID+""); return this;}
	public HabitatResource setResourceID(int resourceID) {this.set("resourceID", resourceID+""); return this;}
	public HabitatResource setAmount(int amount) {this.set("amount", amount+""); return this;}
	
	@Override
	protected String[] getFields() {
		return new String[] {"id", "habitatID", "resourceID", "amount"};
	}

	@Override
	protected String getTableName() {
		return "lak_habitat_resource";
	}
	
	/**
	 * Returns the instance of the habitat resource. If such a resource wasn't inserted into the database, the resource will be inserted before.
	 * 
	 * @param habitatID the id of the habitat
	 * @param resourceID the id of the resource
	 * @return the habitat resource
	 */
	public static HabitatResource get(int habitatID, int resourceID) throws SQLException {
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM lak_habitat_resource WHERE habitatID = ? AND resourceID = ?", new String[] {habitatID+"", resourceID+""});
		if (rs.first()) return new HabitatResource(rs);
		
		// create new unit
		HabitatResource resource = new HabitatResource();
		resource.setHabitatID(habitatID).setResourceID(resourceID).create();
		return resource;
	}
	


}