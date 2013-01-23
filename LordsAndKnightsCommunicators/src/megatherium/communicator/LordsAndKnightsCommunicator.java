/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import megatherium.communicator.data.Account;
import megatherium.data.lordsandknights.Map;
import megatherium.data.lordsandknights.Session;
import megatherium.data.lordsandknights.World;
import org.apache.commons.codec.binary.Hex;
import megatherium.event.EventHandler;
import megatherium.communicator.data.lordsandknights.Position;
import megatherium.communicator.data.lordsandknights.Size;
import megatherium.config.Config;
import megatherium.data.lordsandknights.Habitat;
import megatherium.data.lordsandknights.LoginInformation;
import megatherium.data.lordsandknights.Tile;
import megatherium.event.IEventListener;
import megatherium.event.IUniversalListener;
import megatherium.request.HttpRequest;
import megatherium.util.JsonUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class LordsAndKnightsCommunicator implements IGameCommunicator {
	private static java.util.Map<String, LordsAndKnightsCommunicator> instances = new HashMap<String, LordsAndKnightsCommunicator>();
	public Session getSession() {return this.session;}
	
	private LordsAndKnightsCommunicator( Account account ) {
		this.account = account;
		LoginInformation info = account.getLoginInformation(LoginInformation.class);
		this.login = info.getLogin();
		this.password = info.getPassword();
		this.world = info.getWorld();
	}
	
	/**
	 * Contains the login, means the email adress of the user.
	 */
	private String login;
	/**
	 * Contains the SHA-256-encrypted password.
	 */
	private String password;
	/**
	 * Contains the current session object.
	 */
	private Session session;
	/**
	 * Contains the world the user is currently in or null if the user did not select a world.
	 */
	private World world;
	/**
	 * Contains the size of the map. (Default is 32)
	 */
	private final int MAP_SIZE = 32;
	
	private Account account;
	
	private static Account lastUserAccount;
	
	/**
	 * Returns an undefined instance of the communicator.
	 * This uses the accountID "0".
	 * 
	 * @return the instance
	 */
	public static LordsAndKnightsCommunicator getInstance() {
		return getInstance(lastUserAccount);
	}
	
	/**
	 * Returns the current instance of the communicator or creates one.
	 * 
	 * @param accountID the id of the account or some other id identifying this instance
	 * @return the instance
	 */
	public static LordsAndKnightsCommunicator getInstance(Account account) {
		lastUserAccount = account;
		int accountID = (account != null) ? account.getID() : 0;
		if (!instances.containsKey(accountID+"")) {
			instances.put(accountID+"", new LordsAndKnightsCommunicator(account));
		}
		
		return instances.get(account.getID()+"");
	}

	/**
	 * Logs in the user.
	 * 
	 * @param login the email adress of the user
	 * @param password the password of the user (will be encrypted; UTF-8 only)
	 * @return either true if the login was successfully or false if it wasn't
	 */
	public boolean login(String login, String password) {
		try {
			// encrypt password
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes("UTF-8"));
			password = Hex.encodeHexString(messageDigest.digest());
			
			// save data
			this.login = login;
			this.password = password;
			
			// set config
			HttpRequest request = new HttpRequest("lordsandknights.request.user.login", "http://ios-hh.lordsandknights.com/XYRALITY/WebObjects/BKLoginServer.woa/wa/LoginAction/checkValidLogin");
			JsonObject response = request.set("callback", "").set("login", this.login).set("password", this.password).set("isDoubleOptIn", "true")
					.execute(JsonObject.class);
			
			// return
			if (response.has("error")) return false;
			return true;
		} catch (UnsupportedEncodingException ex) {
			ReportUtil.getInstance().add(ex);
		} catch (NoSuchAlgorithmException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		// return false
		return false;
	}
	
	/**
	 * Updates the session at the server. The official browser client of L&K does this every 5 minutes.
	 * 
	 * @return returns the session object with the updated data received by the server
	 */
	// HttpRequest must implement automatic event handling
	public Session updateSession() {
		// do request
		HttpRequest request = new HttpRequest("lordsandknights.request.session.update", world.getURL()+"/wa/SessionAction/update");
		this.session = request.set("callback", "").set(session.getPlayer().getID()+"", session.getPlayer().getSessionID()).set("PropertyListVersion", "3").execute(Session.class);
		
		// return session
		return this.session;
	}
	
	/**
	 * Calls the server to receive the list of political ties.
	 * Only experimental atm, because we don't know what the numbers do stand for. Therefore, this method does not return anything.
	 */
	public void callPoliticalTieList() {
		HttpRequest request = new HttpRequest("lordsandknights.request.politicalTile.list", world.getMapURL()+"/tiles.jsonp");
		request.set("callback", "").execute();
	}
	
	/**
	 * Returns the list of worlds that are available. This list contains all worlds, not only the connected.
	 * 
	 * @return the world list
	 */
	public World[] getWorldList() {
		HttpRequest request = new HttpRequest("lordsandknights.request.world.list", "http://ios-hh.lordsandknights.com/XYRALITY/WebObjects/BKLoginServer.woa/wa/worlds");
		JsonObject object = request.set("callback", "").set("login", this.login).set("password", this.password)
				.set("deviceId", this.login).set("deviceType", "Email").set("PropertyListVersion", "3").execute(JsonElement.class)
				.getAsJsonObject();
		
		// return world list
		return JsonUtil.getGson().fromJson(object.get("allAvailableWorlds"), World[].class);
	}
	
	/**
	 * Switches to another world.
	 * 
	 * @param world the world the user wants to visit
	 * @return the generated and current session
	 */
	public Session changeWorld(World world) {
		this.world = world;
		
		// do request
		HttpRequest request = new HttpRequest("lordsandknights.request.world.change", world.getURL()+"/wa/LoginAction/connectBrowser");
		this.session = request.set("callback", "").set("login", this.login).set("password", this.password).set("worldId", world.getID()+"")
				.set("logoutUrl", "http://lordsandknights.com").set("PropertyListVersion", "3").execute(Session.class);
		
		// return session
		return this.session;
	}
	
	public Session changeWorld() {
		return this.changeWorld(this.world);
	}
	
	/**
	 * Returns the map content at a specific place.
	 * 
	 * @param position the position around that the map will be loaded
	 * @param size the size of the map
	 * @return the map
	 */
	public Map getMapContent(Position position, Size size) {
		HttpRequest request = new HttpRequest("lordsandknights.request.map", this.world.getURL()+"/wa/MapAction/map");
		
		// do request
		JsonObject response = request.set(session.getPlayer().getID()+"", session.getPlayer().getSessionID()).set("mapX", position.getX()+"").set("mapY", position.getY()+"").set("callback", "").set("mapWidth", MAP_SIZE+"").set("mapHeight", MAP_SIZE+"").set("PropertyListVersion", "3").execute(JsonElement.class).getAsJsonObject();
		
		// fetch map content
		return JsonUtil.getGson().fromJson(response.get("map"), Map.class);
	}
	
	/**
	 * Sends some units and resources to another habitat.
	 * 
	 * @param startHabitatID the id of the start habitat
	 * @param targetHabitatID the id of the target habitat
	 * @param units the units that will be send to the habitat
	 * @param resources the resources that will be sent to the habitat
	 * @return the server response
	 */
	public String sendUnits(int startHabitatID, int targetHabitatID, java.util.Map<String, String> units, java.util.Map<String, String> resources) {
		String unitDictionary = this.getAsDictionary(units);
		String resourceDictionary = this.getAsDictionary(this.stripToSilver(resources));
		HttpRequest request = new HttpRequest("lordsandknights.request.unit.send", this.world.getURL()+"/wa/TransitAction/startTransit");
		request.set("callback", "").set("sourceHabitatID", startHabitatID).set("destinationHabitatID", targetHabitatID);
		request.set("unitDictionary", unitDictionary).set("resourceDictionary", resourceDictionary).set("PropertyListVersion", "3");
		
		return request.set(this.getSession().getPlayer().getID()+"", this.getSession().getPlayer().getSessionID()).execute();
	}
	
	public static void main(String[] args) {
		Config.set("debug", false, boolean.class);
		if (!LordsAndKnightsCommunicator.getInstance().login("martin.christian.bories@gmail.com", "thebospeler")) System.exit(-1);
		World[] worlds = LordsAndKnightsCommunicator.getInstance().getWorldList();
		World world = null;
		for (World world2 : worlds) if (world2.getID() == 4) world = world2;
		LordsAndKnightsCommunicator.getInstance().changeWorld(world);
		Session session = LordsAndKnightsCommunicator.getInstance().getSession();
		session = LordsAndKnightsCommunicator.getInstance().updateSession();
		
		Map map = LordsAndKnightsCommunicator.getInstance().getMapContent(session.getPlayer().getHabitatList()[0].getPosition(), null);
		for (Tile tile : map.getTiles()) {
			for (Habitat habitat : tile.getHabitatDictionary().values()) {
				System.out.println("Found habitat: "+habitat.getName());
			}
		}
		
		System.out.println(session.getPlayer().getID()+" => "+session.getPlayer().getSessionID());
		
//		java.util.Map<String, String> units = new HashMap<String, String>();
//		units.put("1", "10");
//		units.put("10001", "1");
//		LordsAndKnightsCommunicator.getInstance().sendUnits(344404, 344403, units, new HashMap<String, String>());
		
	}
	
	/**
	 * Removes all resources except for silver.
	 * 
	 * @param map the map
	 * @return the map only containing silver
	 */
	public java.util.Map<String, String> stripToSilver(java.util.Map<String, String> map) {
		java.util.Map<String, String> map2 = new HashMap<String, String>();
		map2.put("6", (map.containsKey("6")) ? map.get("6") : "0");
		return map2;
	}
	
	/**
	 * Parses the map into a dictionary.
	 * 
	 * @param map the map
	 * @return the dictionary
	 */
	public String getAsDictionary(java.util.Map<String, String> map) {
		String dictionary = "{";
		for (String key : map.keySet().toArray(new String[]{})) {
			dictionary += key+"="+map.get(key)+";";
		}
		return dictionary+"}";
	}
	
}
