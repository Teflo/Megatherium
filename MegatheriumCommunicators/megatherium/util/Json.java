/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Pathos
 */
public class Json {
	private static Gson instance;
	
	/**
	 * Make constructor private to enable singleton pattern.
	 */
	private Json() {}
	
	/**
	 * Returns the existing instance of this class or creates a new one.
	 * 
	 * @return the instance
	 */
	public static Gson getInstance() {
		if (instance == null) {
			instance = new GsonBuilder().setPrettyPrinting().create();
		}
		
		return instance;
	}
	
}
