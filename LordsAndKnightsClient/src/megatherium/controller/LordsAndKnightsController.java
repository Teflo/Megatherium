/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.controller;

import java.util.Map;
import megatherium.ui.lordsandknights.AttackCreatePanel;
import megatherium.communicator.LordsAndKnightsMegatheriumCommunicator;
import megatherium.communicator.data.Account;
import megatherium.data.store.Stores;
import megatherium.ui.lordsandknights.AttackShedulePanel;
import megatherium.ui.lordsandknights.HomePanel;
import megatherium.ui.lordsandknights.LoginInformationDialog;
import megatherium.ui.lordsandknights.ResourceSelectionPanel;
import megatherium.ui.lordsandknights.UnitSelectionPanel;
import megatherium.communicator.LordsAndKnightsCommunicator;
import megatherium.communicator.data.Platform;
import megatherium.communicator.data.lordsandknights.megatherium.Attack;
import megatherium.communicator.data.lordsandknights.megatherium.Habitat;
import megatherium.config.Config;
import megatherium.data.lordsandknights.LoginInformation;
import megatherium.data.lordsandknights.Session;
import megatherium.data.store.AccountStore;
import megatherium.event.EventManager;
import megatherium.humanizer.LordsAndKnightsHumanizer;
import megatherium.task.lordsandknights.AttackSheduler;
import megatherium.request.MegatheriumRequest;
import megatherium.util.ArrayUtil;
import megatherium.util.JsonUtil;

/**
 *
 * @author marti_000
 */
public class LordsAndKnightsController extends MegatheriumController {
	
	private boolean loadedUserData = false;
	
	@Override
	public void initializePanels() {
		super.initializePanels();
		
		// add panels
		this.addPanel("attackCreate", new AttackCreatePanel());
		this.addPanel("attackShedule", new AttackShedulePanel());
		this.addPanel("homePanel", new HomePanel());
		this.addPanel("resourceSelection", new ResourceSelectionPanel());
		this.addPanel("unitSelection", new UnitSelectionPanel());
	}
	
	@Override
	public void initializeEvents() {
		super.initializeEvents();
		
		// initialize attack sheduler
		new AttackSheduler();
		
		// initialize humanizer
		new LordsAndKnightsHumanizer();
	}
	
	@Override
	public String[][] getReferences() {
		return ArrayUtil.merge2(super.getReferences(), new String[][] {
			{"lordsandknights.ui.attack.create.show", "showAttackCreate"},
			{"lordsandknights.ui.attack.shedule.show", "showAttackShedule"},
			{"lordsandknights.ui.resource.selection.cancel", "cancelResourceSelection"},
			{"lordsandknights.ui.resource.selection.save", "saveResourceSelection"},
			{"lordsandknights.ui.resource.selection.show", "showResourceSelection"},
			{"lordsandknights.ui.unit.selection.cancel", "cancelUnitSelection"},
			{"lordsandknights.ui.unit.selection.save", "saveUnitSelection"},
			{"lordsandknights.ui.unit.selection.show", "showUnitSelection"},
			{"megatherium.ui.account.login.information.show", "showLoginInformation"}
		});
	}
	
	@Override
	public void execute(String event, String position, Object[] parameters) {
		switch(event) {
			case "createAttack":
				this.createAttack((int) parameters[0], (int) parameters[1], (int) parameters[2], (int) parameters[3], (Map<String, String>) parameters[4], (Map<String, String>) parameters[5]);
				break;
		}
	}
	
	/**
	 * Stops the selection of resources.
	 */
	public void cancelResourceSelection() {
		this.closeDialog();
	}
	
	/**
	 * Stops the selection of units.
	 */
	public void cancelUnitSelection() {
		this.closeDialog();
	}
	
	/**
	 * Creates a new scheduled attack.
	 * 
	 * @param account the account
	 * @param time the timestamp
	 * @param habitat the habitat
	 * @param resources a map with the amount of resources
	 * @param units a map with the amount of units
	 */
	public void createAttack(int accountID, int startHabitatID, int targetHabitatID, int time, Map<String, String> resources, Map<String, String> units) {
		LordsAndKnightsMegatheriumCommunicator.getInstance().createAttack(accountID, startHabitatID, targetHabitatID, time, resources, units);
	}
	
	/**
	 * Performs the attack.
	 * 
	 * @param attack the attack
	 */
	public void performAttack(Attack attack) {
		LordsAndKnightsCommunicator.getInstance().sendUnits(attack.getStartHabitatID(), attack.getTargetHabitatID(), attack.getResourcesAsMap(), attack.getUnitsAsMap());
	}
	
	/**
	 * Closes the dialog and saves the resource selection.
	 * 
	 * @param amounts the amount map
	 */
	public void saveResourceSelection(Map<String, String> amounts) {
		this.closeDialog();
	}
	
	/**
	 * Closes the dialog and saves the unit selection.
	 * 
	 * @param amounts the amount map
	 */
	public void saveUnitSelection(Map<String, String> amounts) {
		this.closeDialog();
	}
	
	/**
	 * Displays the attack create panel.
	 */
	public void showAttackCreate() {
		this.display("attackCreate");
	}
	
	/**
	 * Displays the attack shedule panel.
	 */
	public void showAttackShedule() {
		this.display("attackShedule");
	}
	
	/**
	 * Opens a dialog forcing the user to input his account login information.
	 * 
	 * @param platform the platform that's login information is required
	 */
	public void showLoginInformation(Platform platform) {
		// check that this is a lords and knights account
		if (!platform.getName().equals("lordsandknights")) return;
		
		// display dialog
		LoginInformationDialog dialog = new LoginInformationDialog(null, true);
		dialog.addUniversalListener(this);
		dialog.setVisible(true);
	}
	
	@Override
	public void showHomePanel() {
		super.showHomePanel();
		
		this.loadUserData();
	}
	
	/**
	 * Displays the resource selection panel.
	 * 
	 * @param habitat the habitat
	 */
	public void showResourceSelection(Habitat habitat) {
		((ResourceSelectionPanel) this.getPanel("resourceSelection")).setHabitat(habitat);
		this.displayDialog("resourceSelection");
	}
	
	/**
	 * Displays the unit selection panel.
	 * 
	 * @param habitat the habitat
	 */
	public void showUnitSelection(Habitat habitat) {
		((UnitSelectionPanel) this.getPanel("unitSelection")).setHabitat(habitat);
		this.displayDialog("unitSelection");
	}
	
	/**
	 * Loads the user data from the browsergame.
	 */
	public void loadUserData() {
		if( loadedUserData ) return;
		// loop through account list, load session data and upload it to the server
		for (Account account : Stores.get("accountStore", AccountStore.class).getItems("lordsandknights")) {
			LoginInformation loginInformation = account.getLoginInformation(LoginInformation.class);
			
			// try to login
			if (LordsAndKnightsCommunicator.getInstance(account).login(loginInformation.getLogin(), loginInformation.getPassword())) {
				System.out.println(JsonUtil.toJson(loginInformation));
				Session session = LordsAndKnightsCommunicator.getInstance(account).changeWorld();
				LordsAndKnightsMegatheriumCommunicator.getInstance().updatePlayerData(account.getID(), session.getPlayer());
				loadedUserData = true;
			}
		}
	}
	
	/**
	 * Forces to reload the user data from the browsergame
	 */
	public void forceReloadUserData(){
		loadedUserData = false;
		loadUserData();
	}
	
}
