/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import megatherium.xml.XMLDocument;
import megatherium.xml.XMLParser;
import org.xml.sax.SAXException;

/**
 *
 * @author SargTeX
 */
public class XMLUtil {
	
	/**
	 * Parses the document given as string to a xml document.
	 * 
	 * @param content the document as a string
	 * @return the xml document
	 */
	public static XMLDocument parse(String content) throws SAXException {
		return new XMLParser(content).parse();
	}
	
}
