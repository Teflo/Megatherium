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
public class AccountDeleteServlet extends DefaultServlet {
	private int accountID;
	
	@Override
	public void readParameters() {
		if (hasParam("accountID")) this.accountID = Integer.parseInt(getParam("accountID"));
	}
	
	@Override
	public String validate() throws SQLException {
		if (this.accountID < 1 || !new Account(this.accountID).exists()) return "noaccount";
		if (Session.getInstance().getUser() == null || !Session.getInstance().getUser().hasAccount(this.accountID)) return "noaccess";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		new Account(this.accountID).delete();
		respond(true, "accountdelete");
	}
	
}
