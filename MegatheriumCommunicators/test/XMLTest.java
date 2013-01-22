/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.StringReader;
import javax.sound.midi.SysexMessage;
import megatherium.request.HttpRequest;
import megatherium.util.HttpUtil;
import megatherium.util.XMLUtil;
import megatherium.xml.XMLDocument;
import megatherium.xml.XMLDocumentBuilderPretty;
import megatherium.xml.XMLNode;
import megatherium.xml.XMLValueNode;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author SargTeX
 */
public class XMLTest {
	
	public static void main(String[] args) throws SAXException, IOException {
		// create document
		XMLDocument document = new XMLDocument();
		document.set("version", "1.0");
		document.set("encoding", "ISO-8859-1");
		
		// create root node
		XMLNode rootNode = new XMLNode("SOAP-ENV:Envelope");
		rootNode.set("SOAP-ENV:encodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
		rootNode.set("xmlns:SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
		rootNode.set("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
		rootNode.set("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		rootNode.set("xmlns:SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding");
		rootNode.set("xmlns:tns", "urn:server");
		
		// create node for login information
		XMLNode loginInformation = new XMLNode("LoginInformation").set("xsi:type", "tns:LoginInformation");
		loginInformation.append(new XMLValueNode("AuctionUsername", "test-SargTeX1")).append(new XMLValueNode("AuctionPassword", "myEncodedPassword"));
		loginInformation.append(new XMLValueNode("ServiceProvider", "SargTeX")).append(new XMLValueNode("ServicePassword", "myEncodedServicePassword"));

		// create request node
		XMLNode requestNode = new XMLNode("CategoriesRequest").set("xsi:type", "tns:CategoriesRequestType");
		requestNode.append(new XMLValueNode("ParentCategory", "0", "xsd:int")).append(new XMLValueNode("LimitLevel", "1", "xsd:int"));
		requestNode.append(new XMLValueNode("ShowAllNodes", "true", "xsd:boolean"));
		
		// append body node
		XMLNode bodyNode = new XMLNode("SOAP-ENV:body");
		rootNode.append(bodyNode.append(new XMLNode("tns:getCategories").set("xmlns:tnx", "urn:server").append(loginInformation).append(requestNode)));
		
		// set root node
		document.setRootNode(rootNode);
		
		// send document to remote server
		HttpRequest request = new HttpRequest("http://www.auvito.de/server.php?wsdl");
		request.setMethod("post");
		request.setBody(document.toString());
		String response = request.execute();
		
		// print server response
		System.out.println(response);
	}
	
}
