/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SargTeX
 */
public class MapBuilder<K, V> {
	private Map<K, V> map = new HashMap<K, V>();
	
	/**
	 * Adds a new value to the map.
	 * 
	 * @param name the name
	 * @param value the value
	 * @return the object itself for faster programming
	 */
	public MapBuilder set(K name, V value) {
		this.map.put(name, value);
		return this;
	}
	
	/**
	 * Returns the map.
	 * 
	 * @return the map
	 */
	public Map<K, V> create() {
		return this.map;
	}
	
}
