/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.task.lordsandknights;

import megatherium.application.Application;
import megatherium.communicator.data.lordsandknights.megatherium.Attack;
import megatherium.controller.LordsAndKnightsController;
import megatherium.data.store.Stores;
import megatherium.data.store.lordsandknights.AttackStore;
import megatherium.event.EventHandler;
import megatherium.event.EventManager;
import megatherium.event.IEventListener;
import megatherium.event.IUniversalListener;
import megatherium.util.Clock;

/**
 *
 * @author marti_000
 */
public class AttackSheduler {
	
	public AttackSheduler() {
		EventManager.getInstance().addAfterListener("megatherium.request.user.login", this, "initializeEvents");
	}
	
	/**
	 * Hangs into the clock for sheduled attacks.
	 */
	public void initializeEvents() {
		for (final Attack attack : ((AttackStore) Stores.getInstance().getStore("attackStore")).getItems()) {
			Clock.getInstance().addListener(attack.getTime(), new IEventListener() {
				@Override
				public void execute(Object[] parameters) {
					((LordsAndKnightsController) Application.getController()).performAttack(attack);
				}
			});
		}
	}
	
}
