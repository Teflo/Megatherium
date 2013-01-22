/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.data.lordsandknights;

import java.util.Map;

/**
 *
 * @author Pathos
 */
public class Habitat {
	private String creationDate;
	private int id;
	private int mapX;
	private int mapY;
	private int points;
	private Player player;
	public int lastAttackTime = 0;
	
	// new fields
	private String name;
	private int habitatType;
	private int[] habitatKnowledgeArray;
	private Unit[] habitatUnitArray;
	
	/**
	 * Returns the date of creation of this habitat.
	 * 
	 * @return String	the creation date
	 */
	public String getCreationDate() {
		return this.creationDate;
	}
	
	/**
	 * Returns the id of this habitat.
	 * 
	 * @return int	the id
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Returns the x coordinate in the map of this habitat.
	 * 
	 * @return int	the x coordinate
	 */
	public int getX() {
		return this.mapX;
	}
	
	/**
	 * Returns the y coordinate in the map of this habitat.
	 * 
	 * @return int	the y coordinate
	 */
	public int getY() {
		return this.mapY;
	}
	
	/**
	 * Returns the map position of this habitat.
	 * 
	 * @return MapPosition	the position of this habitat in the map
	 */
	public MapPosition getPosition() {
		return new MapPosition(getX(), getY());
	}
	
	/**
	 * Returns the amount of points within this habitat.
	 * 
	 * @return int	the amount of points
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * Returns whether this is a free habitat (so it isn't owned by a user) or not.
	 * 
	 * @return boolean	true or false
	 */
	public boolean isFree() {
		return (this.player == null);
	}
	
	/**
	 * Returns the player, if it is set, or null.
	 * 
	 * @return Player	the player object
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Returns the name of this habitat.
	 * 
	 * @return the name
	 */
	public String getName() {
		if (this.name == null) return "Freie Burg #"+this.getID();
		
		return this.name;
	}
	
	/**
	 * Returns the habitat unit array.
	 * 
	 * @return the habitat unit array
	 */
	public Unit[] getHabitatUnits() {
		return this.habitatUnitArray;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Checks whether this habitat has enough attacking units or not.
	 * 
	 * @return true if it has enough, otherwise false
	 */
	public boolean hasAttackUnits() {
		boolean enoughAttackUnits = false;
		boolean enoughTransportUnits = false;
		
		// loop through habitat units
		for (int i = 0; i < getHabitatUnits().length; ++i) {
			Unit unit = getHabitatUnits()[i];
			Map<String, String> units = unit.getDictionary();
			
			// check whether there are enough or not
			if (units.containsKey("2") && Integer.parseInt(units.get("2")) >= 1) enoughAttackUnits = true;
			if (units.containsKey("10001") && Integer.parseInt(units.get("10001")) >= 1) enoughTransportUnits = true;
		}
		
		// return true or false
		return (enoughTransportUnits && enoughAttackUnits);
	}

	/**
	 * Substracts the attacking units (NOT TESTED!)
	 * 
	 * @todo TEST THIS!
	 */
	public void substractAttackUnits() {
		boolean substractedAttackUnits = false;
		boolean substractedTransportUnits = false;
		
		// loop through habitat units
		for (int i = 0; i < getHabitatUnits().length; ++i) {
			Unit unit = getHabitatUnits()[i];
			Map<String, String> units = unit.getDictionary();
			
			// check whether there are enough or not
			if (!substractedAttackUnits && units.containsKey("2") && Integer.parseInt(units.get("2")) >= 1) {
				units.put("2", (Integer.parseInt(units.get("2")) - 1) +"");
				substractedAttackUnits = true;
			}
			if (!substractedTransportUnits && units.containsKey("10001") && Integer.parseInt(units.get("10001")) >= 1) {
				units.put("10001", (Integer.parseInt(units.get("10001")) - 1) +"");
				substractedTransportUnits = true;
			}
		}
	}
	
}
