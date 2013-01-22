/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.communicator.lordsandknights;

import com.google.gson.*;
import java.util.*;
import megatherium.util.HttpUtil;
import megatherium.util.JsonUtil;
import megatherium.util.MapBuilder;
import sargtex.crse.data.lordsandknights.Habitat;
import sargtex.crse.data.lordsandknights.MapPosition;
import sargtex.crse.data.lordsandknights.Session;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import sargtex.config.lordsandknights.Config;
import sargtex.crse.data.lordsandknights.World;

/**
 *
 * @author Pathos
 */
public class LordsAndKnightsCommunicator implements ILordsAndKnightsCommunicator {
	private Map<String, String> credentials;
	private Gson gson;
	private Session session;
	private Thread sessionAwakeThread;
	private World world;
	private int mapWidth = 32;
	private int mapHeight = 32;
	
	/**
	 * Returns the configurated map width.
	 */
	public int getMapWidth() {return this.mapWidth;}
	
	/**
	 * Returns the configurated map height.
	 */
	public int getMapHeight() {return this.mapHeight;}
	
	/**
	 * Sets the user selected world.
	 * 
	 * @param world the world
	 */
	public void setWorld(World world) {
		this.world = world;
		
		// set world id for credentials
		credentials.put("worldID", world.getID()+"");
		
		// connect to the world
		connect();
	}
	
	/**
	 * Sets the world by the id.
	 * 
	 * @param id the world id
	 */
	public void setWorld(int id) {
		List<World> worldList = getWorldList();
		for (int i = 0; i < worldList.size(); ++i) {
			if (worldList.get(i).getID() == id) {
				setWorld(worldList.get(i));
				break;
			}
		}
	}

	/**
	 * Returns the gson object.
	 *
	 * @return Gson	the only gson object
	 */
	public Gson getGson() {
		if (gson == null) {
			// TODO use unpretty builder for stable releases (might be faster)
			gson = new GsonBuilder().setPrettyPrinting().create();
		}

		return gson;
	}

	/**
	 * Returns the session of the current logged-in player or null if no player
	 * was logged in.
	 *
	 * @return Session	the session
	 */
	public Session getSession() {
		return this.session;
	}
	
	/**
	 * Returns a list of available worlds
	 * 
	 * @return the world list
	 */
	public List<World> getWorldList() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("callback", "");
		params.put("login", credentials.get("username"));
		params.put("password", credentials.get("password"));
		params.put("deviceId", credentials.get("username"));
		params.put("deviceType", "Email");
		
		// execute request
		String content = HttpUtil.execute("http://ios-hh.lordsandknights.com/XYRALITY/WebObjects/BKLoginServer.woa/wa/worlds", params);
		
		// remove brackets
		content = content.substring(1, content.length() - 2);
		
		// fill world list
		List<World> worldList = new ArrayList<World>();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(content).getAsJsonObject().get("allAvailableWorlds").getAsJsonArray();
		for (int i = 0; i < array.size(); ++i) {
			worldList.add(JsonUtil.getGson().fromJson(array.get(i), World.class));
		}
		
		// sort list
		Collections.sort(worldList, new Comparator<World>() {
			@Override
			public int compare(World o1, World o2) {
				if (o1.getName() == null && o2.getName() == null) return 0;
				if (o1.getName() == null) return -1;
				if (o2.getName() == null) return 1;
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		// return world list
		return worldList;
	}

	public boolean login(Map<String, String> credentials) {
		this.credentials = credentials;
		
		// this implementation requires a username and a password
//		if (!credentials.containsKey("username")) {
//			throw new MissingCredentialException("username", "Parameter \"username\" missing for login @ LordsAndKnights");
//		}
//		if (!credentials.containsKey("password")) {
//			throw new MissingCredentialException("password", "Parameter \"password\" missing for login @ LordsAndKnights");
//		}

		// create call parameters
		Map<String, String> params = new MapBuilder<String, String>().set("login", credentials.get("username")).set("password", credentials.get("password")).create();

		// call website
		String content = HttpUtil.execute("http://ios-hh.lordsandknights.com/XYRALITY/WebObjects/BKLoginServer.woa/wa/LoginAction/checkValidLogin", params, "get");

		// check whether login was successfull
		return (content.contains("loginId"));
	}

	/**
	 * Connects the browser. This is part of the login routine
	 */
	protected void connect() {
		// create call parameters
		Map<String, String> params = new HashMap<String, String>();
		params.put("callback", "");
		params.put("login", credentials.get("username"));
		params.put("password", credentials.get("password"));
		params.put("worldId", credentials.get("worldID"));
		params.put("logoutUrl", "http://lordsandknights.com");
		params.put("PropertyListVersion", "3");
//		for (String key : params.keySet()) System.out.println(key+" = "+params.get(key));

		// call website
		String content = HttpUtil.execute(world.getURL()+"/wa/LoginAction/connectBrowser", params, "get");

		// remove method call
		content = content.substring(1, content.length() - 2);

		// fetch player info
		this.session = getGson().fromJson(content, Session.class);
		
		// create session awake thread
		this.sessionAwakeThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					synchronized (this) {
						try {
							wait(500*1000);
						} catch (InterruptedException ex) {
							System.err.println(ex.getMessage());
							ex.printStackTrace();
						}
					}
					
					// update session
					updateSession();
				}
			}
		});

		// start thread to ensure that the session will stay awake
		this.sessionAwakeThread.start();
	}

	/**
	 * Updates the session to ensure the user won't be logged out by the server.
	 */
	public void updateSession() {
		// set params
		Map<String, String> params = new HashMap<String, String>();
		params.put("callback", "");
		params.put(getSession().getPlayer().getID() + "", getSession().getPlayer().getSessionID());
		params.put("PropertyListVersion", "3");

		// update session
		HttpUtil.execute(world.getURL()+"/wa/SessionAction/update", params, "get");
	}
	
	/**
	 * Returns a list with all found habitats near that position and within a range of x fields into all horizontal directions.
	 * 
	 * @param MapPosition a MapPosition object determining the starting coordinates
	 * @param range a range of fields (e.g. 100) within that the habitats will be searched
	 * @return the habitat list
	 */
	public List<Habitat> getHabitats(MapPosition position, int range) {
		List<Habitat> habitatList = getHabitats(position);
		int maxX = 0;
		int minX = 0;
		
		// get maximum x position
		for (maxX = 0; maxX < range; maxX += mapWidth);
		minX = position.getY() - maxX;
		maxX += position.getX();
		
		// first, go into the direction right (same y position, bigger x position)
		for (int i = mapWidth; i < range; i += mapWidth) {
			habitatList.addAll(getHabitats(new MapPosition(position.getX()+i, position.getY())));
		}
		
		// secondly, loop through the rows down of the default position
		for (int i = 1; i < (range/mapWidth)+1; ++i) {
			if (i % 2 == 1) {
				// zahl ungerade - gehe die reihe nach links
				for (int j = maxX; j > minX; j -= mapWidth) {
					habitatList.addAll(getHabitats(new MapPosition(j, position.getY() - i * mapWidth)));
				}
			}else {
				// zahl gerade - gehe die reihe nach rechts
				for (int j = minX; j < maxX; j += mapWidth) {
					habitatList.addAll(getHabitats(new MapPosition(j, position.getY() - i * mapWidth)));
				}
			}
		}
		
		// thirdly, loop through the rows above the default position
		for (int i = 1; i < (range/mapWidth)+1; ++i) {
			if (i % 2 == 1) {
				// zahl ungerade - gehe die reihe nach links
				for (int j = maxX; j > minX; j -= mapWidth) {
					habitatList.addAll(getHabitats(new MapPosition(j, position.getY() + i * mapWidth)));
				}
			}else {
				// zahl gerade - gehe die reihe nach rechts
				for (int j = minX; j < maxX; j += mapWidth) {
					habitatList.addAll(getHabitats(new MapPosition(j, position.getY() + i * mapWidth)));
				}
			}
		}
		
		// return habitat list
		return habitatList;
	}

	/**
	 * Returns a list with all found habitats near that position.
	 *
	 * @param MapPosition	a MapPosition object determining the x and y
	 * coordinates
	 * @return List<Habitat>	contains a list of habitats
	 */
	public List<Habitat> getHabitats(MapPosition position) {
		List<Habitat> habitatList = new ArrayList<Habitat>();

		// create call parameters
		MapBuilder<String, String> builder = new MapBuilder<String, String>();
		builder.set("callback", "").set(getSession().getPlayer().getID()+"", getSession().getPlayer().getSessionID());
		builder.set("mapX", position.getX()+"").set("mapY", position.getY()+"").set("mapWidth", mapWidth+"").set("mapHeight", mapHeight+"");
		builder.set("PropertyListVersion", "3");

		// call website
		String content = HttpUtil.execute(world.getURL()+"/wa/MapAction/map", builder.create(), "get");

		// remove method call
		content = content.substring(1, content.length() - 2);

		// interpret by json
		JsonParser parser = new JsonParser();
		JsonObject habitats = parser.parse(content).getAsJsonObject().get("map").getAsJsonObject().get("tileArray").getAsJsonArray().get(0).getAsJsonObject().get("habitatDictionary").getAsJsonObject();

		Set<Map.Entry<String, JsonElement>> habitatSet = habitats.entrySet();
		Iterator habitatIterator = habitatSet.iterator();

		// add all habitats to the list
		while (habitatIterator.hasNext()) {
			Map.Entry<String, JsonElement> entry = (Map.Entry<String, JsonElement>) habitatIterator.next();
			JsonObject habitat = entry.getValue().getAsJsonObject();

			habitatList.add(getGson().fromJson(habitat, Habitat.class));
		}

		return habitatList;
	}
	
	/**
	 * Returns a list of all free habitats near that habitat.
	 * 
	 * @param habitat the start habitat
	 * @param range the range
	 * @return the list of free habitat near that position
	 */
	public List<Habitat> getFreeHabitats(Habitat habitat, int range) {
		return getFreeHabitats(habitat.getPosition(), range);
	}
	
	/**
	 * Returns a list of all castles near the position.
	 *
	 * @param MapPosition	the position of the map that will be scanned
	 * @param range the range
	 * @return List<Habitat>	the list with free habitats near that position
	 */
	public List<Habitat> getFreeHabitats(MapPosition position, int range) {
		return getFreeHabitats(getHabitats(position, range));
	}
	
	/**
	 * Returns a list of all free habitats near that habitat.
	 * 
	 * @param habitat the start habitat
	 * @return the list of free habitat near that position
	 */
	public List<Habitat> getFreeHabitats(Habitat habitat) {
		return getFreeHabitats(habitat.getPosition());
	}

	/**
	 * Returns a list of all castles near the position.
	 *
	 * @param MapPosition	the position of the map that will be scanned
	 * @return List<Habitat>	the list with free habitats near that position
	 */
	public List<Habitat> getFreeHabitats(MapPosition position) {
		return getFreeHabitats(getHabitats(position));
	}
	
	/**
	 * Returns a list of all free castles from that list.
	 *
	 * @param List<Habitat> a list of habitats
	 * @return the free habitats from that list
	 */
	public List<Habitat> getFreeHabitats(List<Habitat> habitatList) {
		List<Habitat> freeHabitats = new ArrayList<Habitat>();

		// loop through all habitats and find free ones
		for (int i = 0; i < habitatList.size(); ++i) {
			Habitat habitat = habitatList.get(i);

			// check whether this habitat is free and add it to the list if it is
			if (habitat.isFree()) {
				freeHabitats.add(habitat);
			}
		}

		return freeHabitats;
	}

	/**
	 * Starts a transit with units from one habitat to another.
	 */
	public void startTransit(int sourceHabitatID, int destinationHabitatID) {
		// create call parameters
		Map<String, String> params = new HashMap<String, String>();
		params.put("callback", "");
		params.put(getSession().getPlayer().getID() + "", getSession().getPlayer().getSessionID());
		params.put("sourceHabitatID", sourceHabitatID + "");
		params.put("destinationHabitatID", destinationHabitatID + "");
		params.put("transitType", "2");
		params.put("unitDictionary", "{2=1; 10001=1;}");
		params.put("resourceDictionary", "{6=0;}");
		params.put("PropertyListVersion", "3");

		// call website and get response
		String content = HttpUtil.execute(world.getURL()+"/wa/TransitAction/startTransit", params, "get");

		// remove method call
		content = content.substring(1, content.length() - 2);
	}
	
}
