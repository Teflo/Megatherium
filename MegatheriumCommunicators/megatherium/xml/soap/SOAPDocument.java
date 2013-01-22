/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml.soap;

import megatherium.xml.Attribute;
import megatherium.xml.XMLDocument;

/**
 *
 * @author SargTeX
 */
public class SOAPDocument extends XMLDocument {
	
	/**
	 * Initializes the soap document with a xml document.
	 * The information from the xml document will be passed to the parent so that this xml/soap document is an exact copy.
	 * 
	 * @param document the xml document
	 */
	public SOAPDocument(XMLDocument document) {
		this.setRootNode(document.getRootNode());
		for (Attribute attribute : document.getAttributes()) this.set(attribute.getName(), attribute.getValue());
	}
	
}
