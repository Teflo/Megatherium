/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SargTeX
 */
public class EventHandler implements IEventHandler {
	private Map<String, Map<String, List<IEventListener>>> listenerList = new HashMap<String, Map<String, List<IEventListener>>>();
	private List<IUniversalListener> universalListenerList = new ArrayList<IUniversalListener>();

	@Override
	public void addListener(String event, IEventListener listener, String position) {
		if (!listenerList.containsKey(event)) {
			// create position list
			Map<String, List<IEventListener>> list = new HashMap<String, List<IEventListener>>();
			list.put("before", new ArrayList<IEventListener>());
			list.put("after", new ArrayList<IEventListener>());
			
			// add list
			listenerList.put(event, list);
		}
		
		// add event listener
		listenerList.get(event).get(position).add(listener);
		System.out.println("Added listener to "+event+" on "+position+"!");
	}

	@Override
	public void addAfterListener(String event, IEventListener listener) {
		this.addListener(event, listener, "after");
	}

	@Override
	public void addBeforeListener(String event, IEventListener listener) {
		this.addListener(event, listener, "before");
	}
	
	@Override
	public void fireEvent(String event, String position) {
		this.fireEvent(event, position, null);
	}
	
	@Override
	public void addUniversalListener(IUniversalListener listener) {
		this.universalListenerList.add(listener);
	}

	@Override
	public void fireEvent(String event, String position, Object[] parameters) {
		// call all universal listeners
		for (int i = 0; i < this.universalListenerList.size(); ++i) {
			this.universalListenerList.get(i).execute(event, position, parameters);
		}
		
		// check whether or not there are specific event listeners listening to this event
		if (!this.listenerList.containsKey(event)) return;
		System.out.println("Event: "+event+" ("+position+", "+this.listenerList.get(event).get(position).size()+" listeners)");
		
		// loop through all listeners
		for (int i = 0; i < this.listenerList.get(event).get(position).size(); ++i) {
			IEventListener listener = this.listenerList.get(event).get(position).get(i);
			
			// call
			listener.execute(parameters);
		}
	}
	
}
