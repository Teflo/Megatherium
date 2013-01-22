/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import java.sql.SQLException;
import megatherium.api.data.User;
import megatherium.api.servlet.DefaultServlet;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class UserCreateServlet extends DefaultServlet {
	private String name;
	private String email;
	private String password;
	
	@Override
	public void readParameters() {
		if (hasParam("name")) this.name = getParam("name");
		if (hasParam("email")) this.email = getParam("email");
		if (hasParam("password")) this.password = getParam("password");
	}
	
	@Override
	public String validate() {
		// check for required parameters
		if (this.name == null || this.name.isEmpty()) return "noname";
		if (this.email == null || this.email.isEmpty()) return "noemail";
		if (this.password == null || this.password.isEmpty()) return "nopassword";
		
		// check for duplicates
		String duplicateField = User.checkDuplicate(this.name, this.email);
		if (duplicateField != null) return "duplicate"+duplicateField;
		
		// everything okay!
		return null;
	}
	
	@Override
	public void save() {
		try {
			// create new user
			new User().setEmail(this.email).setName(this.name).setPassword(this.password).create();
			respond(true, "usercreate");
		} catch (SQLException ex) {
			ReportUtil.getInstance().add(ex);
		}
	}
	
}
