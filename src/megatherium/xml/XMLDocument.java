/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author SargTeX
 */
public class XMLDocument extends Attributable {
	private XMLNode rootNode;
	
	/**
	 * Initializes an empty xml document.
	 */
	public XMLDocument() {}
	
	/**
	 * Initializes the xml document by a string containing a xml document.
	 * 
	 * @param document the document as a string
	 */
	public XMLDocument(String document) throws SAXException, IOException {
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new DefaultHandler() {

			@Override
			public void startDocument() throws SAXException {
				System.out.println("startDocument");
			}

			@Override
			public void endDocument() throws SAXException {
				System.out.println("endDocument");
			}

			@Override
			public void startElement(String string, String string1, String string2, Attributes atrbts) throws SAXException {
				System.out.println("startElement: "+string+","+string1+","+string2+","+atrbts.getLength()+" attributes");
			}

			@Override
			public void endElement(String string, String string1, String string2) throws SAXException {
				System.out.println("endElement: "+string+","+string1+","+string2);
			}
		});
		reader.parse(new InputSource(new StringReader(document)));
	}
	
	/**
	 * Sets the root node.
	 * 
	 * @param rootNode the root node for this xml document
	 * @return the class instance itself for faster programming
	 */
	public XMLDocument setRootNode(XMLNode rootNode) {
		this.rootNode = rootNode;
		return this;
	}
	
	/**
	 * Returns the root node of the document.
	 * 
	 * @return the xml root node
	 */
	public XMLNode getRootNode() {
		return this.rootNode;
	}
	
	/**
	 * Parses the xml document to a string and returns that.
	 * 
	 * @return the document as a string
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml").append(this.buildAttributes()).append("?>\n");
		builder.append(rootNode.toString());
		return builder.toString();
	}
	
}
