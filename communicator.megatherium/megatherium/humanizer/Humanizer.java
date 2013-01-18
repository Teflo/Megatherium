/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.humanizer;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.event.EventManager;
import megatherium.util.MathUtil;

/**
 *
 * @author SargTeX
 */
public abstract class Humanizer {

	/**
	 * Private access.
	 */
	protected Humanizer() {
		registerEventListeners();
	}
	
	/**
	 * Returns a list of events. The humanizer will implement a delay after each of these events.
	 */
	protected String[] getDelayList() {return null;}

	/**
	 * Waits a random amount of time.
	 */
	protected void delay() {
		synchronized (this) {
			try {
				double time = MathUtil.getRandom(500, 1500);
				double exp = MathUtil.getRandom(1, 1.2);
				time = Math.pow(time, exp);
				System.out.println("Waiting for "+(time/1000)+" seconds\n");
				wait((long) time);
			} catch (InterruptedException ex) {
				System.err.println(ex.getLocalizedMessage());
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Registers the event listeners.
	 */
	protected void registerEventListeners() {
		for (String eventName : this.getDelayList()) EventManager.getInstance().addAfterListener(eventName, this, "delay");
	}
	
}
