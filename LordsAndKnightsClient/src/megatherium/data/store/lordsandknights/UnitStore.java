/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store.lordsandknights;

import megatherium.communicator.LordsAndKnightsMegatheriumCommunicator;
import megatherium.communicator.data.lordsandknights.megatherium.Resource;
import megatherium.communicator.data.lordsandknights.megatherium.Unit;
import megatherium.data.store.Store;

/**
 *
 * @author marti_000
 */
public class UnitStore extends Store<Unit> {

	@Override
	public void load() {
		this.add(LordsAndKnightsMegatheriumCommunicator.getInstance().getUnitList());
	}

	@Override
	public String getName() {
		return "unitStore";
	}
	
	/**
	 * Returns the unit by it's id or null if such a unit doesn't exist.
	 * 
	 * @param id the id of the unit
	 * @return the unit
	 */
	public Unit get(int id) {
		for (Unit unit : this.getItems()) if (unit.getID() == id) return unit;
		return null;
	}
	
}
