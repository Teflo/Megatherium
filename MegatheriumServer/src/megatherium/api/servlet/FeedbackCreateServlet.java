/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import java.sql.SQLException;
import megatherium.api.data.Application;
import megatherium.api.data.Feedback;
import megatherium.api.data.Session;

/**
 *
 * @author marti_000
 */
public class FeedbackCreateServlet extends DefaultServlet {
	private int applicationID;
	private String text;
	
	@Override
	public void readParameters() {
		super.readParameters();
		
		if (hasParam("applicationID")) this.applicationID = Integer.parseInt(getParam("applicationID"));
		if (hasParam("text")) this.text = getParam("text");
	}
	
	@Override
	public String validate() throws SQLException {
		if (!new Application(this.applicationID).exists()) return "noapplication";
	//	if (Session.getInstance().getUser() == null) return "noaccess";
		if (this.text == null || this.text.isEmpty()) return "notext";
		return super.validate();
	}
	
	@Override
	public void save() throws SQLException {
		new Feedback().setApplicationID(this.applicationID).setUserID(Session.getInstance().getUserID()).setText(this.text).create();
		respond(true, "feedbackcreate");
	}
	
}
