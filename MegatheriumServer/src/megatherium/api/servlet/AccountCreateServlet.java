/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import java.sql.SQLException;
import megatherium.api.data.Account;
import megatherium.api.data.Platform;

/**
 *
 * @author marti_000
 */
public class AccountCreateServlet extends DefaultServlet {
	private String platformName;
	private String loginInformation;
	private String alias;
	
	@Override
	public void readParameters() {
		if (hasParam("platformName")) this.platformName = getParam("platformName");
		if (hasParam("alias")) this.alias = getParam("alias");
		if (hasParam("loginInformation")) this.loginInformation = getParam("loginInformation");
	}
	
	@Override
	public String validate() throws SQLException {
		if (!new Platform(this.platformName).exists()) return "noplatform";
		if (this.alias == null || this.alias.isEmpty()) return "noalias";
		if (this.loginInformation == null || this.loginInformation.isEmpty()) return "nologininformation";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		new Account().setPlatformName(this.platformName).setLoginInformation(this.loginInformation).setAlias(this.alias).create();
		respond(true, "accountcreate");
	}
	
}
