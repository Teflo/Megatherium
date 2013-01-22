/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.controller;

import java.io.File;
import java.util.Map;
import megatherium.ui.AccountCreatePanel;
import megatherium.ui.AccountListPanel;
import megatherium.ui.EventPanel;
import megatherium.ui.HomePanel;
import megatherium.ui.UserCreatePanel;
import megatherium.ui.UserLoginPanel;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.communicator.data.Account;
import megatherium.communicator.data.ILoginInformation;
import megatherium.communicator.data.Platform;
import megatherium.data.store.Stores;
import megatherium.event.EventManager;
import megatherium.language.LanguageServer;
import megatherium.request.MegatheriumRequest;
import megatherium.ui.FeedbackCreatePanel;
import megatherium.ui.FileChoosePanel;

/**
 *
 * @author SargTeX
 */
public class MegatheriumController extends Controller {
	protected String loginInformation;
	private File[] chosenFiles;
	public void setChosenFiles(File[] files) {this.chosenFiles = files; this.closeDialog();}
	public File[] getChosenFiles() {return this.chosenFiles;}
	
	@Override
	public void initializeEvents() {
		super.initializeEvents();
	}
	
	@Override
	protected String[][] getReferences() {
		return new String[][] {
			{"megatherium.data.account.create", "performAccountCreate"},
			{"megatherium.data.feedback.create", "performFeedbackCreate"},
			{"megatherium.data.user.create", "performUserCreate"},
			{"megatherium.data.user.login", "performUserLogin"},
			{"megatherium.error.request.unknownHost", "reportUnknownHost"},
			{"megatherium.ui.account.create.show", "showAccountCreate"},
			{"megatherium.ui.account.list.show", "showAccountList"},
			{"megatherium.ui.account.login.information.cancel", "closeDialog"},
			{"megatherium.ui.account.login.information.save", "saveAccountLoginInformation"},
			{"megatherium.ui.feedback.create.cancel", "closeDialog"},
			{"megatherium.ui.feedback.create.show", "showFeedbackCreate"},
			{"megatherium.ui.file.choose.cancel", "closeDialog"},
			{"megatherium.ui.file.choose.save", "setChosenFiles"},
			{"megatherium.ui.file.choose.show", "showFileChooser"},
			{"megatherium.ui.user.create.show", "showUserCreate"},
			{"megatherium.ui.user.login.show", "showUserLogin"}
		};
	}
	
	@Override
	public void initializePanels() {
		this.addPanel("accountCreate", new AccountCreatePanel());
		this.addPanel("accountList", new AccountListPanel());
		this.addPanel("feedbackCreate", new FeedbackCreatePanel());
		this.addPanel("fileChoose", new FileChoosePanel());
		this.addPanel("homePanel", new HomePanel());
		this.addPanel("userCreate", new UserCreatePanel());
		this.addPanel("userLogin", new UserLoginPanel());
	}
	
	@Override
	public String getStartupPanel() {
		return "userLogin";
	}

	@Override
	public void execute(String event, String position, Object[] parameters) {
		super.execute(event, position, parameters);
		
		switch(event) {
			case "saveLoginInformation":
				((AccountCreatePanel) this.getPanel("accountCreate")).setLoginInformation((ILoginInformation) parameters[0]);
				break;
		}
	}
	
	/**
	 * Reports to the GUI that some host was not found.
	 * 
	 * @param host the host
	 */
	public void reportUnknownHost(String host) {
		this.showNotification(LanguageServer.getInstance().get("megatherium.error.request.unknownHost")+": "+host);
	}
	
	/**
	 * Loads the list of accounts for the current user.
	 */
	public void loadAccountList() {
		Stores.getInstance().getStore("accountStore").load();
	}
	
	/**
	 * Creates the new account.
	 * 
	 * @param platformName the name of the platform
	 * @param alias the alias of the account
	 * @param loginInformation some platform-specific login information
	 */
	public void performAccountCreate(String platformName, String alias, ILoginInformation loginInformation) {
		MegatheriumCommunicator.getInstance().createAccount(platformName, alias, loginInformation);
	}
	
	/**
	 * Creates a new feedback.
	 * 
	 * @param text the feedback text
	 */
	public void performFeedbackCreate(String text) {
		MegatheriumCommunicator.getInstance().createFeedback(text);
	}
	
	/**
	 * Performs the user login.
	 */
	public void performUserLogin(String login, String password) {
		// try to login
		if (MegatheriumCommunicator.getInstance().login(login, password)) {
			this.showLoginInformation(true);
		} else {
			this.showLoginInformation(false);
		}
		
		// display home panel
		this.showHomePanel();
	}
	
	/**
	 * Creates a user.
	 */
	public void performUserCreate(String name, String email, String password) {
		// try to create the user
		String response = MegatheriumCommunicator.getInstance().createUser(name, email, password);
		if (response == null) {
			this.showNotification("Der Benutzer wurde erfolgreich erstellt.");
			this.showUserLogin();
		} else {
			switch(response) {
				case "noname":
					this.showNotification("Bitte geben Sie einen Benutzernamen ein.");
					break;
				case "noemail":
					this.showNotification("Bitte geben Sie eine gültige E-Mail Adresse ein.");
					break;
				case "nopassword":
					this.showNotification("Bitte wählen Sie ein Passwort.");
					break;
				case "duplicatename":
					this.showNotification("Der Benutzername ist bereits vergeben.");
					break;
				case "duplicateemail":
					this.showNotification("Die E-Mail Adresse ist bereits vergeben.");
					break;
				default:
					this.showNotification("Some error occured, please notify us: \"UserCreate->"+response+"\"");
			}
		}
	}
	
	/** These methods simply redirect to several panels within the display() method **/
	public void saveAccountLoginInformation(ILoginInformation loginInformation) {((AccountCreatePanel) this.getPanel("accountCreate")).setLoginInformation(loginInformation); this.closeDialog();}
	public void showAccountCreate() {this.display("accountCreate");}
	public void showAccountList() {this.display("accountList");}
	public void showFeedbackCreate() {this.displayDialog("feedbackCreate");}
	public void showFileChooser() {this.displayDialog("fileChoose");}
	public void showHomePanel() {this.display("homePanel");}
	public void showUserLogin() {this.display("userLogin");}
	public void showUserCreate() {this.display("userCreate");}
	
	/**
	 * Displays a notification whether the user was successfully logged in or not.
	 * 
	 * @param login either true or false
	 */
	public void showLoginInformation(boolean login) {
		String message = "Sie wurden erfolgreich angemeldet.";
		if (!login) message = "Die Anmeldung ist fehlgeschlagen.";
		this.showNotification(message);
	}
	
}
