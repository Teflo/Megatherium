/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml.soap;

import java.util.Map;
import megatherium.xml.XMLDocument;

/**
 *
 * @author SargTeX
 */
public class WSDLDocument extends SOAPDocument {
	
	/**
	 * Initializes the WSDL document by a xml document.
	 * The information from the xml document will be extracted to this document.
	 * 
	 * @param document the XML document
	 */
	public WSDLDocument(XMLDocument document) {
		super(document);
	}
	
}
