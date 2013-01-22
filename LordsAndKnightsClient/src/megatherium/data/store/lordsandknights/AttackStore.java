/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store.lordsandknights;

import megatherium.communicator.LordsAndKnightsMegatheriumCommunicator;
import megatherium.communicator.data.lordsandknights.megatherium.Attack;
import megatherium.data.store.Store;
import megatherium.data.store.Stores;

/**
 *
 * @author marti_000
 */
public class AttackStore extends Store<Attack> {

	@Override
	public void load() {
		this.add(LordsAndKnightsMegatheriumCommunicator.getInstance().getAttackList());
	}

	@Override
	public String getName() {
		return "attackStore";
	}
	
}
