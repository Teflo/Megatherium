/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store.lordsandknights;

import megatherium.communicator.LordsAndKnightsCommunicator;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.data.lordsandknights.World;
import megatherium.data.store.Store;

/**
 *
 * @author marti_000
 */
public class WorldStore extends Store<World> {

	@Override
	public void load() {
		this.add(LordsAndKnightsCommunicator.getInstance().getWorldList());
	}

	@Override
	public String getName() {
		return "worldStore";
	}
	
}
