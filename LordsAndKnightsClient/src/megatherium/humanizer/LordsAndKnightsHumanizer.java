/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.humanizer;

import megatherium.event.IEventListener;
import megatherium.communicator.LordsAndKnightsCommunicator;
import megatherium.event.EventManager;
import megatherium.humanizer.Humanizer;

/**
 *
 * @author SargTeX
 */
public class LordsAndKnightsHumanizer extends Humanizer {
	private static LordsAndKnightsHumanizer instance;
	
	/**
	 * Initializes the humanizer.
	 * As the humanizer works for itself by implementing event listeners into communicators aso, there is no need to get his instance orto call the constructor manually.
	 */
	public static void initialize() {
		if (instance == null) {
			instance = new LordsAndKnightsHumanizer();
		}
	}
	
	@Override
	protected String[] getDelayList() {
		return new String[] {
			"lordsandknights.request.user.login",
			"lordsandknights.request.world.change",
			"lordsandknights.request.world.list"
		};
	}
	
	/**
	 * Registers the event listeners.
	 */
	@Override
	protected void registerEventListeners() {
		super.registerEventListeners();
		
		EventManager.getInstance().addAfterListener("lordsandknights.request.world.change", LordsAndKnightsCommunicator.getInstance(), "callPoliticalTileList");
	}
	
}
