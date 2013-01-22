/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store.lordsandknights;

import megatherium.communicator.LordsAndKnightsMegatheriumCommunicator;
import megatherium.communicator.data.lordsandknights.megatherium.Resource;
import megatherium.data.store.Store;

/**
 *
 * @author marti_000
 */
public class ResourceStore extends Store<Resource> {

	@Override
	public void load() {
		this.add(LordsAndKnightsMegatheriumCommunicator.getInstance().getResourceList());
	}

	@Override
	public String getName() {
		return "resourceStore";
	}
	
	/**
	 * Returns the resource by it's id or null if such a ressource doesn't exist.
	 * 
	 * @param id the id of the resource
	 * @return the resource
	 */
	public Resource get(int id) {
		for (Resource resource : this.getItems()) if (resource.getID() == id) return resource;
		return null;
	}
	
}
