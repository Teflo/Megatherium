/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.data.lordsandknights;

/**
 *
 * @author Pathos
 */
public class World {
	private String name;
	private String url;
	private int id;
	
	/**
	 * @return the id of the world
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * @return the name of this world
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the url of this world
	 */
	public String getURL() {
		return this.url;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
