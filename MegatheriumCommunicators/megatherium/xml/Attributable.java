/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SargTeX
 */
public class Attributable {
	private Map<String, String> attributes = new HashMap<String, String>();
	
	/**
	 * Returns the attributes parsed to a string.
	 * 
	 * @return the attributes as a string
	 */
	protected String buildAttributes() {
		StringBuilder builder = new StringBuilder();
		for (String key : attributes.keySet()) builder.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
		return builder.toString();
	}
	
	/**
	 * Sets the value of a specific attribute.
	 * 
	 * @param name the name of the attribute
	 * @param value the value of the attribute
	 * @return the object itself for faster programming
	 */
	public Attributable set(String name, String value) {
		this.attributes.put(name, value);
		return this;
	}
	
	/**
	 * Returns the set of attributes.
	 * 
	 * @return the attribute set
	 */
	public Attribute[] getAttributes() {
		List<Attribute> attributeList = new ArrayList<Attribute>();
		for (String key : attributes.keySet()) attributeList.add(new Attribute(key, attributes.get(key)));
		return attributeList.toArray(new Attribute[]{});
	}
	
}
