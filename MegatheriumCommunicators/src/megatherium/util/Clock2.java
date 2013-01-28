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
import megatherium.event.IEventListener;

/**
 *
 * @author Teflo
 */
public class Clock2 extends Thread {
	
	private class TimeEvent{
		long time;
		List<IEventListener> listeners;
		boolean handled = false;
		
		public TimeEvent(long time){
			this.time = time;
			this.listeners = new ArrayList<IEventListener>();
		}
		
		public void addListener(IEventListener event) {
			this.listeners.add(event);
		}
		
		public boolean past(long time) {
			return this.time <= time;
		}
		
		public boolean handle(long time) {
			if(handled || !past(time)) return false;
			for(IEventListener listener : listeners) {
				listener.execute(new Object[] {time});
			}
			handled = true;
			return true;
		}
		
	}
	
	private Map<Long, TimeEvent> events;
	private static Clock2 instance;
	private boolean abort;
	private int timeStep = 100;
	private long time = 0;
	
	private Clock2() {
		events = new HashMap<Long, TimeEvent>();
	}
	
	public static Clock2 getInstance() {
		if(instance == null) {
			instance = new Clock2();
			instance.start();
		}
		return instance;
	}
	
	private void setTime(long time) {
		this.time = time;
		for(TimeEvent event : events.values())
			event.handle(time);
	}
	
	@Override
	public void run() {
		while(!abort) {
			this.setTime(System.currentTimeMillis());
			synchronized(this) {
				try {
					wait(timeStep);
				} catch (InterruptedException ex) {
					ReportUtil.getInstance().add(ex);
				}
			}
			
		}
	}
	
	public void abort() {
		this.abort = true;
	}
	
	public void addListener(long time, IEventListener listener) {
		if(!events.containsKey(time)) events.put(time, new TimeEvent(time));
		events.get(time).addListener(listener);
	}
	
}
