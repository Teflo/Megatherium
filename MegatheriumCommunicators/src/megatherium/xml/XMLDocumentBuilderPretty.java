/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml;

/**
 *
 * @author SargTeX
 */
public class XMLDocumentBuilderPretty extends XMLDocumentBuilder {
	
	/**
	 * Initializes the document builder with a XML document.
	 * 
	 * @param document the xml document
	 */
	public XMLDocumentBuilderPretty(XMLDocument document) {
		super(document);
	}

	@Override
	protected String toString(XMLDocument document) {
		return super.toString(document)+"\n";
	}

	@Override
	protected String toString(XMLNode node) {
		return super.toString(node).replace(">", ">\n\t");
	}
	
}
