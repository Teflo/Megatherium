/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.application;

import megatherium.config.Config;
import megatherium.controller.LordsAndKnightsController;
import megatherium.data.store.lordsandknights.AttackStore;
import megatherium.data.store.lordsandknights.HabitatStore;
import megatherium.data.store.lordsandknights.ResourceStore;
import megatherium.data.store.lordsandknights.UnitStore;
import megatherium.data.store.lordsandknights.WorldStore;

/**		
 *
 * @author marti_000
 */
public class LordsAndKnightsClientApplication extends Application {
	
	public static void main(String[] args) {
		Config.set("debug", false, boolean.class);
		LordsAndKnightsClientApplication app = new LordsAndKnightsClientApplication();
	}
	
	@Override
	protected void initializeStores() {
		super.initializeStores();
		
		this.addStore("attackStore", new AttackStore());
		this.addStore("habitatStore", new HabitatStore());
		this.addStore("resourceStore", new ResourceStore());
		this.addStore("unitStore", new UnitStore());
		this.addStore("worldStore", new WorldStore());
	}
	
	@Override
	protected void initializeController() {
		controller = new LordsAndKnightsController();
	}
	
}
