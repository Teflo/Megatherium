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
public class ShippingInformation {
	private Amount cost;
	private Map<String, String> platformIDMap = new HashMap<String, String>();
	public Amount getCost() {return this.cost;}
	public String getID(Platform platform) {return platformIDMap.get(platform.getName());}
	
}
