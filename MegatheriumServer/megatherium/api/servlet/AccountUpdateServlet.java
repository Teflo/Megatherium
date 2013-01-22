/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import java.sql.SQLException;
import megatherium.api.data.Account;
import megatherium.api.data.Platform;
import megatherium.api.data.Session;

/**
 *
 * @author marti_000
 */
public class AccountUpdateServlet extends DefaultServlet {
	private int accountID;
	private String alias;
	private String loginInformation;
	private String platformName;
	
	@Override
	public void readParameters() {
		if (hasParam("accountID")) this.accountID = Integer.parseInt(getParam("accountID"));
		if (hasParam("alias")) this.alias = getParam("alias");
		if (hasParam("loginInformation")) this.loginInformation = getParam("loginInformation");
		if (hasParam("platformName")) this.platformName = getParam("platformName");
	}
	
	@Override
	public String validate() throws SQLException {
		if (Session.getInstance().getUser() == null || !Session.getInstance().getUser().hasAccount(this.accountID)) return "noaccess";
		if (alias == null || alias.isEmpty()) return "noalias";
		if (this.loginInformation == null || this.loginInformation.isEmpty()) return "nologininformation";
		if (this.platformName == null || this.platformName.isEmpty() || !new Platform(this.platformName).exists()) return "noplatformname";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		new Account(this.accountID).setAlias(this.alias).setLoginInformation(this.loginInformation).setPlatformName(this.platformName).update();
		respond(true, "accountupdate");
	}
	
}
