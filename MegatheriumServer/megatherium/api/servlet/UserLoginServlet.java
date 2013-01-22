/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import megatherium.api.data.Session;
import megatherium.api.data.User;
import megatherium.api.servlet.DefaultServlet;

/**
 *
 * @author SargTeX
 */
public class UserLoginServlet extends DefaultServlet {
	private String login;
	private String password;

	@Override
	public void readParameters() {
		if (hasParam("login")) this.login = getParam("login");
		if (hasParam("password")) this.password = getParam("password");
	}
	
	@Override
	public String validate() {
		if (this.login == null || this.login.isEmpty()) return "nologin";
		if (this.password == null || this.password.isEmpty()) return "nopassword";
		return null;
	}
	
	@Override
	public void save() {
		if (!User.login(login, password)) respond(false, "loginfail");
		else respond(true, "session", Session.getInstance());
	}
	
}