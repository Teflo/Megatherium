/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml;

/**
 *
 * @author SargTeX
 */
public class XMLValueNode extends XMLNode {
	
	/**
	 * Initializes the value node with content type "xsd:string".
	 * 
	 * @param name the name of the tag
	 * @param value the content of the tag
	 */
	public XMLValueNode(String name, String value) {
		super(name);
		this.set("xsi:type", "xsd:string");
		this.setContent(value);
	}
	
	/**
	 * Initializes the value node.
	 * 
	 * @param name the name of the tag
	 * @param value the content of the tag
	 * @param type the content type, default: "xsd:string"
	 */
	public XMLValueNode(String name, String value, String type) {
		super(name);
		this.set("xsi:type", type);
		this.setContent(value);
	}
	
}
