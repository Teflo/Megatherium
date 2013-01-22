/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author SargTeX
 */
public class Account extends DatabaseUserObject {

	public Account() {super();}
	public Account(int id) throws SQLException {super(id);}
	public Account(ResultSet rs) throws SQLException {super(rs);}
	public Account(Map<String, String> data) {super(data);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getPlatformName() {return this.get("platformName");}
	public String getLoginInformation() {return this.get("loginInformation");}
	public String getAlias() {return this.get("alias");}
	public Account setUserID(int userID) {super.setUserID(userID); return this;}
	public Account setPlatformName(String platformName) {this.set("platformName", platformName); return this;}
	public Account setLoginInformation(String loginInformation) {this.set("loginInformation", loginInformation); return this;}
	public Account setAlias(String alias) {this.set("alias", alias); return this;}
	
	@Override
	protected String[] getFields() {
		return new String[] {"id", "userID", "platformName", "loginInformation", "alias"};
	}

	@Override
	protected String getTableName() {
		return "account";
	}
	
	
	
}
