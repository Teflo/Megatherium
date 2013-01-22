/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package megatherium.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;

/**
 *
 * @author marti_000
 */
public class Usergroup extends DatabaseObject {
    
	public Usergroup() {super();}
	public Usergroup(int id) throws SQLException {super(id);}
	public Usergroup(Map<String, String> data) {super(data);}
	public Usergroup(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getName() {return this.get("name");}
	public Usergroup setName(String name) {this.set("name", name); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "name"};
	}

	@Override
	protected String getTableName() {
		return "usergroup";
	}

	@Override
	protected String getForeignKeyName() {
		return "usergroupID";
	}
	
	/**
	 * Returns the value of the option for this group.
	 * If the option isn't set for this group, returns null.
	 * 
	 * @param name the name of the option
	 * TODO do this method bitch
	 */
	public void getOption(String name) {
		
	}
	


}