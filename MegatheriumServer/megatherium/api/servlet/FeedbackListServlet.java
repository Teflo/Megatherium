/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import java.sql.SQLException;
import megatherium.api.data.Feedback;
import megatherium.api.data.Session;

/**
 *
 * @author marti_000
 */
public class FeedbackListServlet extends DefaultServlet {
	
	@Override
	public String validate() throws SQLException {
		if (Session.getInstance().getUser() == null) return "noaccess";
		return super.validate();
	}
	
	@Override
	public void save() throws SQLException {
		respond(true, "feedbacklist", Feedback.getAll());
	}
	
}
