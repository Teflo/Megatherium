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
public abstract class DatabaseUserObject extends DatabaseObject {
	
	public DatabaseUserObject() {
		super();
		this.setUserID(Session.getInstance().getUserID());
	}
	public DatabaseUserObject(int id) throws SQLException {super(id);}
	public DatabaseUserObject(ResultSet rs) throws SQLException {super(rs);}
	public DatabaseUserObject(Map<String, String> data) {super(data);}
	
	public int getUserID() {return Integer.parseInt(this.get("userID"));}
	public DatabaseUserObject setUserID(int userID) {this.set("userID", userID+""); return this;}
	
}
