/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.communicator.data.Account;
import megatherium.communicator.data.Platform;

/**
 *
 * @author marti_000
 */
public class PlatformStore extends Store<Platform> {
	private Map<String, Platform> platforms;
	
	@Override
	public String getName() {
		return "platformStore";
	}
	
	public void add(Platform[] platforms) {
		for (Platform platform : platforms) this.add(platform);
	}
	
	public void add(Platform platform) {
		super.add(platform);
		
		this.platforms.put(platform.getName(), platform);
	}
	
	/**
	 * Returns the platform by it's name.
	 * 
	 * @param name the name of the platform
	 * @return either the platform or null if not found
	 */
	public Platform get(String name) {
		if (this.platforms == null) this.load();
		return this.platforms.get(name);
	}
	
	/**
	 * Returns all platforms by it's type.
	 * The list includes subtypes.
	 * 
	 * @param type the type of the platform
	 * @return a list with matching platforms
	 */
	public Platform[] getByType(String type) {
		// create list
		List<Platform> platformList = new ArrayList<Platform>();
		
		// loop through platforms
		for (Platform platform : this.getItems()) {
			if (platform.getType().equals(type) || platform.getType().startsWith(type+".")) platformList.add(platform);
		}
		
		return platformList.toArray(new Platform[]{});
	}

	@Override
	public void load() {
		this.platforms = new HashMap<String, Platform>();
		this.add(MegatheriumCommunicator.getInstance().getPlatformList());
	}
	
}
