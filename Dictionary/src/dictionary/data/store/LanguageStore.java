/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.data.store;

import com.sun.org.apache.bcel.internal.generic.StoreInstruction;
import dictionary.communicator.DictionaryCommunicator;
import dictionary.communicator.data.Language;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.data.store.Store;
import megatherium.event.EventManager;

/**
 *
 * @author marti_000
 */
public class LanguageStore extends Store<Language> {
	
	@Override
	public String getName() {
		return "accountStore";
	}
	
	@Override
	public void load() {
		this.add(DictionaryCommunicator.getInstance().getLanguages());
	}
	
}
