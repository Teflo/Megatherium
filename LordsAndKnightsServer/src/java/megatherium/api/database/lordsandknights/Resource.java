/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package megatherium.api.database.lordsandknights;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class Resource extends DatabaseObject {
    
	public Resource() {super();}
	public Resource(int id) throws SQLException {super(id);}
	public Resource(Map<String, String> data) {super(data);}
	public Resource(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getLabel() {return this.get("label");}
	public Resource setID(int id) {this.set("id", id+""); return this;}
	public Resource setLabel(String label) {this.set("label", label); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "label"};
	}

	@Override
	protected String getTableName() {
		return "lak_resource";
	}
	
	/**
	 * Returns all resources.
	 * 
	 * @return the resources
	 */
	public static Resource[] getAll() throws SQLException {
		List<Resource> resourceList = new ArrayList<Resource>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM lak_resource");
		while (rs.next()) resourceList.add(new Resource(rs));
		return resourceList.toArray(new Resource[]{});
	}

}