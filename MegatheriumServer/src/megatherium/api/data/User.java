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
import megatherium.api.data.DatabaseObject;
import sargtex.sql.Database;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class User extends DatabaseObject {
	private Account[] accounts;
	
	public User() {}
	public User(int id) throws SQLException {super(id);}
	public User(ResultSet rs) throws SQLException {super(rs);}
	public User(Map<String, String> data) {super(data);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getName() {return this.get("name");}
	public String getEmail() {return this.get("email");}
	public User setName(String name) {this.set("name", name); return this;}
	public User setEmail(String email) {this.set("email", email); return this;}
	public User setPassword(String password) {this.set("password", password); return this;}
	
	/**
	 * Checks whether the user has the account or not.
	 * 
	 * @param accountID the id of the account
	 * @return true or false
	 */
	public boolean hasAccount(int accountID) throws SQLException {
		// check in account list
		Account[] accounts = this.getAccounts();
		for (int i = 0; i < accounts.length; ++i) {
			if (accounts[i].getID() == accountID) return true;
		}
		
		// return false - doesn't seem to be owned by this user or this is a caching issue
		// if there is instability in cause of caching, uncomment the following line instead
		// return (new Account(accountID).getUserID() == this.getID());
		return false;
	}
	
	/**
	 * Returns all the accounts that belong to this user.
	 * 
	 * @return the accounts
	 */
	public Account[] getAccounts() throws SQLException {
		if (this.accounts != null) return this.accounts;
		
		// load accounts
		List<Account> accountList = new ArrayList<Account>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM account WHERE userID = ?", new String[] {this.getID()+""});
		while (rs.next()) {
			accountList.add(new Account(rs));
		}
		ReportUtil.getInstance().add("UserID: "+this.getID());
		this.accounts = new Account[]{};
		
		// return account list
		return accountList.toArray(this.accounts);
	}
	
	@Override
	protected String[] getFields() {
		return new String[] {"id", "usergroupID", "name", "email", "password"};
	}

	@Override
	protected String getTableName() {
		return "user";
	}

	@Override
	protected void initializeLinkings() {
		super.initializeLinkings();
		
		this.addLinking("usergroup", new Usergroup());
	}
	
	/**
	 * Tries to login the user.
	 * 
	 * @param login the login data (either the user's name or the email adress)
	 * @param password the password (encoded)
	 * @return true if successfull or false if the user couldn't be logged-in
	 */
	public static boolean login(String login, String password) {
		try {
			ResultSet rs = Database.getInstance().fetch("SELECT * FROM user WHERE (email = ? OR name = ?) AND password = ? LIMIT 1", new String[] {login, login, password});
			
			// return user
			if (rs.first()) {
				// create user object
				User user = new User(rs);
				
				// update session data
				ReportUtil.getInstance().add(Session.getInstance());
				Session.getInstance().setUserID(user.getID()).update();
				
				// successfully logged-in
				return true;
			}
		} catch (SQLException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		// no user found or error occured - return null
		return false;
	}
	
	/**
	 * Checks whether the user's name or the email adress is already given to another user in the database.
	 * If so, returns the name of the field (either "name" or "email") that already exists. If it doesn't, returns null.
	 * 
	 * @param name the name of the user
	 * @param email the email adress
	 * @return null if no duplicate was found, otherwise "name" if the name is already given or "email"
	 */
	public static String checkDuplicate(String name, String email) {
		try {
			ResultSet rs = Database.getInstance().fetch("SELECT name, email FROM user WHERE name = ? OR email = ? LIMIT 1", new String[] {name, email});
			
			// return null if no duplicate was found
			if (!rs.first()) return null;
			
			// return either "email" or "name"
			if (rs.getString("name").equals(name)) return "name";
			return "email";
		} catch (SQLException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		// error occured - return error because it shouldn't create a user now
		return "error";
	}
	
}
