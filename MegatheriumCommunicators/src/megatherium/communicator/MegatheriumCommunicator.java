/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import megatherium.communicator.data.Account;
import megatherium.communicator.data.ILoginInformation;
import megatherium.communicator.data.Platform;
import megatherium.communicator.data.Response;
import megatherium.communicator.data.ResponseContainer;
import megatherium.communicator.data.Session;
import megatherium.config.Config;
import megatherium.event.EventHandler;
import megatherium.request.HttpRequest;
import megatherium.request.MegatheriumRequest;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class MegatheriumCommunicator implements IAuthenticationCommunicator {
	private static MegatheriumCommunicator instance;
	public static final String SERVER_URL = "http://spinshare.de:8080/Lords__Knights_Server/";
	private static final int APPLICATION_ID = 1;
	private Session session = new Session();
	
	/**
	 * Returns the current instance. If there is no instance from this class, creates one and returns it.
	 * 
	 * @return the instance
	 */
	public static MegatheriumCommunicator getInstance() {
		if (instance == null) instance = new MegatheriumCommunicator();
		
		return instance;
	}
	
	/**
	 * Returns a list of all existing platforms.
	 * 
	 * @return the list of platforms
	 */
	public Platform[] getPlatformList() {
		MegatheriumRequest request = MegatheriumRequest.create("platform", "list");
		return request.exec(new Platform[] {}.getClass());
	}

	@Override
	public boolean login(String login, String password) {
		MegatheriumRequest request = MegatheriumRequest.create("user", "login");
		this.session = request.set("login", login).set("password", password).exec(Session.class);
		return (this.session.getUserID() > 0);
	}
	
	/**
	 * Creates a new feedback.
	 * 
	 * @param text the feedback text
	 * @return either null (no error) or a string containing error identifier
	 */
	public String createFeedback(String text) {
		MegatheriumRequest request = MegatheriumRequest.create("feedback", "create");
		Response response = request.set("text", text).set("applicationID", APPLICATION_ID).set("userID", this.session.getUserID()).exec();
		return (response.getStatus()) ? null : response.getType();
	}
	
	/**
	 * Creates a new account or fails.
	 * If the account creation fails, a string will be returned that is not null and that contains information about the error.
	 * Visit this page for information about the errors: http://megatherium.spinshare.de/wiki/index.php/API.Servlet#UserCreateServlet
	 * 
	 * @param name the username
	 * @param email the email adress
	 * @param password the encoded password
	 * @return either null (success) or a string determining the error
	 */
	public String createUser(String name, String email, String password) {
		MegatheriumRequest request = MegatheriumRequest.create("user", "create");
		Response response = request.set("name", name).set("email", email).set("password", password).exec();
		return (response.getStatus()) ? null : response.getType();
	}
	
	/**
	 * Returns the list of accounts that are linked to the current logged-in user.
	 * 
	 * @return the account list
	 */
	public Account[] getAccountList() {
		MegatheriumRequest request = MegatheriumRequest.create("account", "list");
		return request.exec().getData(new Account[]{}.getClass());
	}
	
	/**
	 * Adds a new account in the megatherium project.
	 * 
	 * @param platformName the name of the platform
	 * @param alias the alias for that account
	 * @param loginInformation some object containing login information for that platform
	 * @return a status error if such thing happened
	 */
	public String createAccount(String platformName, String alias, ILoginInformation loginInformation) {
		MegatheriumRequest request = MegatheriumRequest.create("account", "create");
		Response response = request.set("platformName", platformName).set("alias", alias).set("loginInformation", loginInformation).exec();
		return (response.getStatus()) ? null : response.getType();
	}
	
	/**
	 * Updates the account information.
	 * 
	 * @param accountID the id of the account
	 * @param platformName the new name of the platform
	 * @param alias the new account alias
	 * @param loginInformation the new login information for the account
	 * @return a status error if such thing happened
	 */
	public String updateAccount(int accountID, String platformName, String alias, String loginInformation) {
		Response response = MegatheriumRequest.create("account", "update").exec();
		return (response.getStatus()) ? null : response.getType();
	}
	
	/**
	 * Deletes the account completely.
	 * 
	 * @param accountID the id of the account
	 * @return a status error if such thing happened
	 */
	public String deleteAccount(int accountID) {
		MegatheriumRequest request = MegatheriumRequest.create("account", "delete");
		Response response = request.set("accountID", accountID).exec();
		return (response.getStatus()) ? null : response.getType();
	}
	
}
