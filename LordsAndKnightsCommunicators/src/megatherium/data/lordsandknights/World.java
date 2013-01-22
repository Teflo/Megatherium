/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.lordsandknights;

/**
 *
 * @author SargTeX
 */
public class World {
	private String name;
	private String mapURL;
	private WorldStatus worldStatus;
	private String country;
	private String language;
	private int id;
	private String url;
	public String getName() {return this.name;}
	public String getMapURL() {return this.mapURL;}
	public WorldStatus getWorldStatus() {return this.worldStatus;}
	public String getCountry() {return this.country;}
	public String getLanguage() {return this.language;}
	public int getID() {return this.id;}
	public String getURL() {return this.url;}
	public String toString() {return this.getName();}

	public World(String name, String mapURL, WorldStatus worldStatus, String country, String language, int id, String url) {
		this.name = name;
		this.mapURL = mapURL;
		this.worldStatus = worldStatus;
		this.country = country;
		this.language = language;
		this.id = id;
		this.url = url;
	}
	
	private class WorldStatus {
		private int id;
		private String description;
		public int getID() {return this.id;}
		public String getDescription() {return this.description;}

		public WorldStatus(int id, String description) {
			this.id = id;
			this.description = description;
		}
	}
	
}
