/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.lordsandknights;

import java.util.Map;

/**
 *
 * @author Pathos
 */
public class Unit {
	private Map<String, String> habitatUnitDictionary;
	private int battleType;
	
	/**
	 * Returns the battle type.
	 * 
	 * @return the battle type
	 */
	public int getBattleType() {
		return this.battleType;
	}
	
	/**
	 * Returns the habitat unit dictionary.
	 */
	public Map<String, String> getDictionary() {
		return this.habitatUnitDictionary;
	}
	
}
