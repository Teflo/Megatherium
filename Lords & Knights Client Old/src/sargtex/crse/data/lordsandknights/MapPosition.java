/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.data.lordsandknights;

/**
 *
 * @author Pathos
 */
public class MapPosition {
	private int x;
	private int y;
	
	/**
	 * Initializes the map position.
	 */
	public MapPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x coordinate of the map.
	 * 
	 * @return x coordinate
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Returns the y coordinate of the map.
	 * 
	 * @return y coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	@Override
	public String toString() {
		return this.x+"/"+this.y;
	}
	
}
