/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class Feedback extends DatabaseObject {
	
	public Feedback() {super();}
	public Feedback(int id) throws SQLException {super(id);}
	public Feedback(ResultSet rs) throws SQLException {super(rs);}
	public Feedback(Map<String, String> data) {super(data);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public int getApplicationID() {return Integer.parseInt(this.get("applicationID"));}
	public int getUserID() {return Integer.parseInt(this.get("userID"));}
	public String getText() {return this.get("text");}
	public Feedback setApplicationID(int applicationID) {this.set("applicationID", applicationID+""); return this;}
	public Feedback setUserID(int userID) {this.set("userID", userID+""); return this;}
	public Feedback setText(String text) {this.set("text", text); return this;}
	
	@Override
	protected void initializeLinkings() {
		super.initializeLinkings();
		
		this.addLinking("application", new Application());
	}
	
	@Override
	protected String[] getFields() {
		return new String[] {"id", "applicationID", "userID", "text"};
	}

	@Override
	protected String getTableName() {
		return "feedback";
	}
	
	/**
	 * Returns a list containing all feedbacks.
	 * 
	 * @return the feedback list
	 */
	public static Feedback[] getAll() throws SQLException {
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM feedback");
		while (rs.next()) feedbackList.add(new Feedback(rs));
		return feedbackList.toArray(new Feedback[]{});
	}
	
	
}
