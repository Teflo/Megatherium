/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.xml.soap;

import java.io.IOException;
import megatherium.request.HttpRequest;
import megatherium.util.HttpUtil;
import megatherium.util.XMLUtil;
import megatherium.xml.XMLDocument;
import org.xml.sax.SAXException;

/**
 *
 * @author SargTeX
 */
public class SOAPClient {
	private String wsdlURL = "";
	private String namespace;
	private XMLDocument document;
	
	/**
	 * Initializes the soap client.
	 * 
	 * @param wsdlURL the url of the WSDL (Web Services Description Language) document
	 * @param namespace the default namespace for the SOAP document
	 */
	public SOAPClient(String wsdlURL, String namespace) {
		this.wsdlURL = wsdlURL;
		this.namespace = namespace;
	}
	
	/**
	 * Loads the WSDL document.
	 */
	public void loadWSDLDocument() throws SAXException {
		HttpRequest request = new HttpRequest(wsdlURL);
		String doc = request.execute();
		document = XMLUtil.parse(doc);
	}
	
}
