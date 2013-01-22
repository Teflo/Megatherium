/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.communicator.data;

import java.util.HashMap;
import java.util.Map;
import megatherium.communicator.data.Platform;

/**
 *
 * @author SargTeX
 */
public class Currency {
	private Map<String, String> platformIDMap = new HashMap<String, String>();
	
	/**
	 * Returns the currency value for the given platform or null if the currency wasn't found.
	 * 
	 * @param platform the platform
	 * @return the value
	 */
	public String getID(Platform platform) {
		return this.getID(platform.getName());
	}
	
	/**
	 * Returns the currency value for the given platform name.
	 * 
	 * @param platformName the name of the platform (ID)
	 * @return the value
	 */
	protected String getID(String platformName) {
		return this.platformIDMap.get(platformName);
	}
	
}
