/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.communicator.data.Response;
import megatherium.communicator.data.ResponseContainer;
import megatherium.communicator.data.lordsandknights.megatherium.Attack;
import megatherium.communicator.data.lordsandknights.megatherium.Habitat;
import megatherium.communicator.data.lordsandknights.megatherium.Resource;
import megatherium.communicator.data.lordsandknights.megatherium.Unit;
import megatherium.data.lordsandknights.Player;
import megatherium.request.HttpRequest;
import megatherium.request.MegatheriumRequest;

/**
 *
 * @author marti_000
 */
public class LordsAndKnightsMegatheriumCommunicator extends MegatheriumCommunicator {
	private static LordsAndKnightsMegatheriumCommunicator instance;
	
	/**
	 * Returns the current instance. If there is no instance from this class, creates one and returns it.
	 * 
	 * @return the instance
	 */
	public static LordsAndKnightsMegatheriumCommunicator getInstance() {
		if (instance == null) instance = new LordsAndKnightsMegatheriumCommunicator();
		
		return instance;
	}
	
	/**
	 * Fetches the attack list from the remote server and returns it.
	 * 
	 * @return the attack list
	 */
	public Attack[] getAttackList() {
		MegatheriumRequest request = MegatheriumRequest.create("attack", "list");
		return request.exec(new Attack[]{}.getClass());
	}
	
	/**
	 * Uploads the data from the player to the server to update it over there.
	 * 
	 * @param accountID the id of the account
	 * @param player the player's object
	 * @return the response container
	 */
	public Response updatePlayerData(int accountID, Player player) {
		MegatheriumRequest request = MegatheriumRequest.create("playerData", "update");
		return request.set("accountID", accountID).set("player", player).exec();
	}
	
	/**
	 * Loads the complete habitat list from remote.
	 * 
	 * @param accountID the id of the account
	 * @return the habitat list
	 */
	public Habitat[] getHabitatList(int accountID) {
		MegatheriumRequest request = MegatheriumRequest.create("habitat", "list");
		return request.set("accountID", accountID).exec(new Habitat[]{}.getClass());
	}
	
	/**
	 * Returns the list of units.
	 * 
	 * @return the list of units
	 */
	public Unit[] getUnitList() {
		MegatheriumRequest request = MegatheriumRequest.create("unit", "list");
		return request.exec(new Unit[]{}.getClass());
	}
	
	/**
	 * Returns the list of resources.
	 * 
	 * @return the list of resources
	 */
	public Resource[] getResourceList() {
		MegatheriumRequest request = MegatheriumRequest.create("resource", "list");
		return request.exec(new Resource[]{}.getClass());
	}
	
	/**
	 * Creates an attack.
	 * 
	 * @param accountID the id of the account
	 * @param startHabitatID the id of the attacking habitat
	 * @param targetHabitatID the id of the attacked habitat
	 * @param time the timestamp of the attack execution
	 * @param resources a map with the resource amounts
	 * @param units a map with the unit amounts
	 * @return the server response
	 */
	public Response createAttack(int accountID, int startHabitatID, int targetHabitatID, long time, HashMap<String, String> resources, HashMap<String, String> units) {
		MegatheriumRequest request = MegatheriumRequest.create("attack", "create");
		return request.set("accountID", accountID).set("startHabitatID", startHabitatID).set("targetHabitatID", targetHabitatID).set("time", time).set("resources", resources).set("units", units).exec();
	}
	
}
