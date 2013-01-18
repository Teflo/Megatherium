/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import megatherium.config.Config;
import megatherium.xml.soap.SOAPClient;
import org.xml.sax.SAXException;

/**
 *
 * @author SargTeX
 */
public class SOAPTest {
	
	public static void main(String[] args) throws SAXException, IOException {
		Config.set("debug", false, boolean.class);
		SOAPClient client = new SOAPClient("http://www.auvito.de/server.php?wsdl", "soap-env");
		client.loadWSDLDocument();
	}
	
}
