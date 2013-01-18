/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.util.ReportUtil;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author SargTeX
 */
public class XMLParser {

	private String content;

	/**
	 * Initializes the parser with a xml document.
	 *
	 * @param content the content of the xml document
	 */
	public XMLParser(String content) {
		this.content = content;
	}

	/**
	 * Parses the content, forms it to a xml document and returns the document.
	 *
	 * @return the document
	 */
	public XMLDocument parse() throws SAXException {
		XMLContentHandler handler = new XMLContentHandler();
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(handler);
		try {
			reader.parse(new InputSource(new StringReader(content)));
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}

		return handler.getDocument();
	}

	/**
	 * This class handles the content from a xml source.
	 */
	public class XMLContentHandler extends DefaultHandler {
		private XMLDocument document;
		private List<XMLNode> nodeList = new ArrayList<XMLNode>();
		private XMLNode currentNode;
		
		/**
		 * Returns the document.
		 * 
		 * @return the document
		 */
		public XMLDocument getDocument() {
			return this.document;
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			
			this.document = new XMLDocument();
		}

		@Override
		public void endDocument() throws SAXException {
			super.endDocument();
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			
			XMLNode node = new XMLNode(qName);
			for (int i = 0; i < attributes.getLength(); ++i) node.set(attributes.getQName(i), attributes.getValue(i));
			nodeList.add(node);
			if (currentNode != null) currentNode.append(node);
			else document.setRootNode(node);
			currentNode = node;
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri, localName, qName);
			
			for (int i = (nodeList.size() - 1); i >= 0; --i) {
				if (nodeList.get(i).getTagName().equals(qName)) {
					if (i > 0) currentNode = nodeList.get(i-1);
					else currentNode = null;
					nodeList.remove(i);
					break;
				}
			}
		}
	}
}
