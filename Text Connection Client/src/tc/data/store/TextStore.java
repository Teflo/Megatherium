/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tc.data.store;

import megatherium.data.store.Store;
import textconnect.communicator.TextConnectCommunicator;
import textconnect.communicator.data.Text;

/**
 *
 * @author marti_000
 */
public class TextStore extends Store<Text> {

	@Override
	public void load() {
		this.add(TextConnectCommunicator.getInstance().getTextList());
	}

	@Override
	public String getName() {
		return "textStore";
	}
	
}
