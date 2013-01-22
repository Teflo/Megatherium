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
public class ProductCategory {
	private Map<String, String> platformIDMap = new HashMap<String, String>();
	private Map<String, String> platformLabelMap = new HashMap<String, String>();
	private String label;
	public String getLabel() {return this.label;}
	public String getLabel(Platform platform) {return this.platformLabelMap.get(platform.getName());}
	public String getID(Platform platform) {return this.platformIDMap.get(platform.getName());}
	
}
