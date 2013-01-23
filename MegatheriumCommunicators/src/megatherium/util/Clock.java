/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.event.EventHandler;
import megatherium.event.IEventListener;
import megatherium.event.IUniversalListener;

/**
 *
 * @author marti_000
 */
public class Clock extends Thread {
	private Map<String, List<IEventListener>> listenerList = new HashMap<String, List<IEventListener>>();
	private static Clock instance;
	private long time;
	private Clock() {}
	
	/**
	 * Returns the current clock instance.
	 * 
	 * @return the instance
	 */
	public static Clock getInstance() {
		if (instance == null) {
			instance = new Clock();
			instance.start();
		}
		
		return instance;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				this.setTime( (System.currentTimeMillis()));
				try {
					wait(500);
				} catch (InterruptedException ex) {
					ReportUtil.getInstance().add(ex);
				}
			}
		}
	}
	
	/**
	 * Returns the current time stamp.
	 * 
	 * @return the time stamp
	 */
	public long getTime() {
		return this.time;
	}
	
	/**
	 * Sets the current time in seconds.
	 * This must be unix timestamp.
	 * 
	 * @param time the current time
	 */
	public void setTime(long time) {
		if (time == this.time) return;
		this.time = time;
		this.callListeners();
	}
	
	/**
	 * Calls the event listeners.
	 * This method is usually called after the time changed.
	 */
	private void callListeners() {
		if (!this.listenerList.containsKey(this.time+"")) return;
		List<IEventListener> listeners = this.listenerList.get(this.time+"");
		for (IEventListener listener : listeners) {
			listener.execute(new Object[] {this.time});
		}
	}
	
	/**
	 * This method registers an event listener. The listener will be called after the time was reached.
	 * 
	 * @param time an UNIX-timestamp. after this time was reached by the system time, the listener will be called
	 * @param listener the listener
	 */
	public void addListener(long time, IEventListener listener) {
		if (!this.listenerList.containsKey(time+"")) this.listenerList.put(time+"", new ArrayList<IEventListener>());
		this.listenerList.get(time+"").add(listener);
	}
	
}
