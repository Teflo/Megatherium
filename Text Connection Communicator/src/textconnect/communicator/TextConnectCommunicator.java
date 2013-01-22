/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textconnect.communicator;

import megatherium.communicator.data.Response;
import megatherium.request.MegatheriumRequest;
import textconnect.communicator.data.Connection;
import textconnect.communicator.data.Selection;
import textconnect.communicator.data.Text;
import textconnect.communicator.data.TextPart;

/**
 *
 * @author marti_000
 */
public class TextConnectCommunicator {
	private static TextConnectCommunicator instance;
	private static final String SERVER_URL = "http://localhost:8084/Text_Connection_Server/";
	
	/**
	 * Returns the current instance.
	 * 
	 * @return the instance
	 */
	public static TextConnectCommunicator getInstance() {
		if (instance == null) {
			instance = new TextConnectCommunicator();
			MegatheriumRequest.initialize(TextConnectCommunicator.SERVER_URL);
		}
		
		return instance;
	}
	
	/**
	 * Adds a new text.
	 * 
	 * @param title the title of the text
	 * @param text the text
	 * @param comment some comment of the text
	 * @return the response
	 */
	public Response addText(String title, String text, String comment) {
		MegatheriumRequest request = MegatheriumRequest.create("text", "add");
		return request.set("title", title).set("text", text).set("comment", comment).exec();
	}
	
	/**
	 * Adds a new connection.
	 * 
	 * @param label the label of the connection
	 * @param selection1 the first text selection
	 * @param selection2 the second text selection
	 * @param comment some additional comment
	 * @return the response
	 */
	public Response addConnection(String label, Selection selection1, Selection selection2, String comment) {
		MegatheriumRequest request = MegatheriumRequest.create("connection", "add").set("label", label).set("comment", comment);
		request.set("textID1", selection1.getText().getID()).set("startIndex1", selection1.getStartIndex()).set("endIndex1", selection1.getEndIndex());
		return request.set("textID2", selection2.getText().getID()).set("startIndex2", selection2.getStartIndex()).set("endIndex2", selection2.getEndIndex()).exec();
	}
	
	/**
	 * Returns a list of texts.
	 * 
	 * @return the text list
	 */
	public Text[] getTextList() {
		return MegatheriumRequest.create("text", "list").exec(new Text[]{}.getClass());
	}
	
	/**
	 * Returns all connections between the two texts.
	 * 
	 * @param textID1 the id of the first text
	 * @param textID2 the id of the second text
	 * @return the connection list
	 */
	public Connection[] getConnections(int textID1, int textID2) {
		return MegatheriumRequest.create("connection", "list").set("textID1", textID1).set("textID2", textID2).exec(new Connection[]{}.getClass());
	}
	
}
