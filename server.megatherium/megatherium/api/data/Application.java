/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author marti_000
 */
public class Application extends DatabaseObject {

	public Application() {super();}
	public Application(int id) throws SQLException {super(id);}
	public Application(ResultSet rs) throws SQLException {super(rs);}
	public Application(Map<String, String> data) {super(data);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getName() {return this.get("name");}
	public String getLabel() {return this.get("label");}
	public String getOwner() {return this.get("owner");}
	public Application setName(String name) {this.set("name", name); return this;}
	public Application setLabel(String label) {this.set("label", label); return this;}
	public Application setOwner(String owner) {this.set("owner", owner); return this;}
	
	@Override
	protected String getForeignKeyName() {
		return "applicationID";
	}
	
	@Override
	protected String[] getFields() {
		return new String[] {"id", "name", "label", "owner"};
	}

	@Override
	protected String getTableName() {
		return "application";
	}
	
}
