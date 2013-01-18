/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml;

/**
 *
 * @author SargTeX
 */
public class Attribute {
	private String name;
	private String value;
	public String getName() {return this.name;}
	public String getValue() {return this.value;}
	public Attribute(String name, String value) {this.name = name; this.value = value;}
	
}
