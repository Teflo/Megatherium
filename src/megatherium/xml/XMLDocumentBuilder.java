/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml;

/**
 *
 * @author SargTeX
 */
public class XMLDocumentBuilder {
	private XMLDocument document;
	
	/**
	 * Initializes the document builder with a XML document.
	 * 
	 * @param document the xml document
	 */
	public XMLDocumentBuilder(XMLDocument document) {
		this.document = document;
	}
	
	/**
	 * Builds the xml document.
	 * 
	 * @return the xml document as a string
	 */
	public String build() {
		StringBuilder builder = new StringBuilder();
		
		// append document
		builder.append(this.toString(document));
		
		// apend root node
		builder.append(this.toString(document.getRootNode()));
		
		// return string
		return builder.toString();
	}
	
	/**
	 * Parses the document to a string
	 * 
	 * @param document the document
	 * @return the document as a string
	 */
	protected String toString(XMLDocument document) {
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml");
		builder.append(toString(document.getAttributes()));
		builder.append("?>");
		return builder.toString();
	}
	
	/**
	 * Parses the attributes to a string.
	 * 
	 * @param attributes the attributes
	 * @return the attributes as a string
	 */
	protected String toString(Attribute[] attributes) {
		StringBuilder builder = new StringBuilder();
		for (Attribute attribute : attributes) builder.append(" ").append(attribute.getName()).append("=\"").append(attribute.getValue()).append("\"");
		return builder.toString();
	}
	
	/**
	 * Parses the node to a string.
	 * 
	 * @param node the xml node
	 * @return the node as a string
	 */
	protected String toString(XMLNode node) {
		StringBuilder builder = new StringBuilder();
		builder.append("<").append(node.getTagName());
		builder.append(this.toString(node.getAttributes()));
		builder.append(">");
		
		// append sub nodes
		for (XMLNode subNode : node.getSubNodes()) builder.append(this.toString(subNode));
		
		// append closing tag
		builder.append("</").append(node.getTagName()).append(">");
		
		// return as string
		return builder.toString();
	}
	
}
