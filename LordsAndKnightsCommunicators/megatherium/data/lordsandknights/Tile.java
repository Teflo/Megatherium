/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.lordsandknights;

/**
 *
 * @author SargTeX
 */
public class Tile {
	private Frame frame;
	private String map;
	private java.util.Map<String, Habitat> habitatDictionary;
	public Frame getFrame() {return this.frame;}
	public String getMap() {return this.map;}
	public java.util.Map<String, Habitat> getHabitatDictionary() {return this.habitatDictionary;}
	
}
