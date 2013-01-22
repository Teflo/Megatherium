/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.data;

/**
 *
 * @author SargTeX
 */
public class File {
	private String name;
	private String id;
	private File parent;
	public String getName() {return this.name;}
	public String getID() {return this.id;}
	public File getParent() {return this.parent;}
	public File(String id, String name) {this.id = id; this.name = name;}
	
}
