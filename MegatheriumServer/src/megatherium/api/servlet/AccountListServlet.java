/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import java.sql.SQLException;
import megatherium.api.data.Account;
import megatherium.api.data.Session;

/**
 *
 * @author marti_000
 */
public class AccountListServlet extends DefaultServlet {
	
	@Override
	public String validate() {
		if (Session.getInstance().getUser() == null) return "noaccess";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		respond(true, "accountList", Session.getInstance().getUser().getAccounts());
	}
	
}
