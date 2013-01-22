/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.api.data.DatabaseObject;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class Session extends DatabaseObject {
	private static Session instance;
	private User user;
	
	private Session(String id) throws SQLException {super(id); ReportUtil.getInstance().add("Initializing ID: "+id);}
	private Session(ResultSet rs) throws SQLException {super(rs);}
	private Session(Map<String, String> data) {super(data);}
	public String getID() {return this.get("id");}
	public int getUserID() {if (this.get("userID") == null) return 0; return Integer.parseInt(this.get("userID"));}
	public Session setUserID(int userID) {this.set("userID", userID+""); return this;}
	public Session setUser(User user) {this.setUserID(user.getID()); this.user = user; return this;}
	
	/**
	 * Returns the object of the session's user or null if an error occurs or the userID is 0.
	 * 
	 * @return the user object or null
	 */
	public User getUser() {
		try {
			if (this.user == null && this.getUserID() > 0) this.user = new User(this.getUserID());
		} catch (SQLException ex) {
			ReportUtil.getInstance().add(ex);
		}
		return this.user;
	}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "userID"};
	}

	@Override
	protected String getTableName() {
		return "session";
	}
	
	/**
	 * Returns the current session instance or null if there is currently no created session.
	 * (This method shouldn't return null as the class megatherium.api.servlet.DefaultServlet should initialize this class with a sessionID).
	 * 
	 * @return the current instance or null
	 */
	public static Session getInstance() {
		return instance;
	}
	
	/**
	 * Initializes the session.
	 * As there is only one session running for any user, this class is designed as a singleton-pattern.
	 * This method creates a new session instance in the database if the session id does not match an entry.
	 * 
	 * @param id the id of the session
	 */
	public static void create(String id) {
		try {
			instance = new Session(id);
			if (instance.get("userID") == null) instance.create();
		} catch (SQLException ex) {
			ReportUtil.getInstance().add(ex);
		}
	}
	
}
