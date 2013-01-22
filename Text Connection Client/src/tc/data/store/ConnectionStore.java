/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tc.data.store;

import megatherium.data.store.Store;
import textconnect.communicator.TextConnectCommunicator;
import textconnect.communicator.data.Connection;

/**
 *
 * @author marti_000
 */
public class ConnectionStore extends Store<Connection> {
	private int textID1;
	private int textID2;
	
	/**
	 * Initializes the connection store for 2 texts.
	 * 
	 * @param textID1 the id of the first text
	 * @param textID2 the id of the second text
	 */
	public ConnectionStore(int textID1, int textID2) {
		this.textID1 = textID1;
		this.textID2 = textID2;
	}

	@Override
	public void load() {
		this.add(TextConnectCommunicator.getInstance().getConnections(textID1, textID2));
	}

	@Override
	public String getName() {
		return "textConnectionStore-"+this.textID1+"-"+this.textID2;
	}
	
}
