/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui;

import com.sun.corba.se.pept.transport.EventHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import megatherium.event.IEventHandler;
import megatherium.event.IEventListener;
import megatherium.event.IUniversalListener;

/**
 *
 * @author marti_000
 */
public abstract class LoginInformationDialog extends JDialog implements IEventHandler {
	private Map<String, Map<String, List<IEventListener>>> listeners = new HashMap<String, Map<String, List<IEventListener>>>();
	private List<IUniversalListener> universalListenerList = new ArrayList<IUniversalListener>();
	
	public LoginInformationDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
	}
	
	/**
	 * Returns the login information as a string.
	 * 
	 * @return the login information
	 */
	public abstract String getLoginInformation();

	@Override
	public void addListener(String event, IEventListener listener, String position) {
		if (!listeners.containsKey(event)) {
			// create position list
			Map<String, List<IEventListener>> list = new HashMap<String, List<IEventListener>>();
			list.put("before", new ArrayList<IEventListener>());
			list.put("after", new ArrayList<IEventListener>());
			
			// add list
			listeners.put(event, list);
		}
		
		// add event listener
		listeners.get(event).get(position).add(listener);
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
		System.out.println("Adding universal listener to a panel");
		this.universalListenerList.add(listener);
	}

	@Override
	public void fireEvent(String event, String position, Object[] parameters) {
		System.out.println("Firing event: "+event+"."+position+"("+this.universalListenerList.size()+")");
		
		// call all universal listeners
		for (int i = 0; i < this.universalListenerList.size(); ++i) {
			this.universalListenerList.get(i).execute(event, position, parameters);
		}
		
		// skip if there are no custom events for this one
		if (!this.listeners.containsKey(event)) return;
		
		// loop through all listeners
		for (int i = 0; i < this.listeners.get(event).get(position).size(); ++i) {
			IEventListener listener = this.listeners.get(event).get(position).get(i);
			
			// call
			listener.execute(parameters);
		}
	}
	
}
