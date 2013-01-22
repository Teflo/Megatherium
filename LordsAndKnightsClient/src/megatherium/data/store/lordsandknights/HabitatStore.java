/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store.lordsandknights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import megatherium.communicator.LordsAndKnightsMegatheriumCommunicator;
import megatherium.communicator.data.Account;
import megatherium.communicator.data.lordsandknights.megatherium.Habitat;
import megatherium.data.store.AccountStore;
import megatherium.data.store.Store;
import megatherium.data.store.Stores;

/**
 *
 * @author marti_000
 */
public class HabitatStore extends Store<Habitat> {
	protected Map<String, List<Habitat>> habitats = new HashMap<String, List<Habitat>>();

	public void add(int accountID, Habitat habitat) {
		super.add(habitat);
		
		if (!this.habitats.containsKey(accountID+"")) this.habitats.put(accountID+"", new ArrayList<Habitat>());
		this.habitats.get(accountID+"").add(habitat);
	}
	
	@Override
	public void load() {
		for (Account account : ((AccountStore) Stores.getInstance().getStore("accountStore")).getItems())
			this.load(account.getID());
	}
	
	public void add(int accountID, Habitat[] habitats) {
		for (Habitat habitat : habitats) this.add(accountID, habitat);
	}
	
	@Override
	public void add(List<? extends Habitat> habitats) {
		for (Habitat habitat : habitats) this.add(habitat);
	}
	
	/**
	 * Loads the entries from the account.
	 * 
	 * @param accountID the id of the account
	 */
	public void load(int accountID) {
		this.add(accountID, LordsAndKnightsMegatheriumCommunicator.getInstance().getHabitatList(accountID));
	}
	
	/**
	 * Returns the habitats from that account.
	 * 
	 * @param accountID the id of the account
	 * @return the habitat list
	 */
	public Habitat[] getItems(int accountID) {
		if (!this.habitats.containsKey(accountID+"")) this.load(accountID);
		return this.habitats.get(accountID+"").toArray(new Habitat[]{});
	}

	@Override
	public String getName() {
		return "habitatStore";
	}
	
}
