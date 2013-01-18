/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.util.ArrayUtil;
import megatherium.util.ClassUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class EventCategory {
	public List<Listener> eventListeners = new ArrayList<Listener>();
	private Map<String, EventCategory> eventCategories = new HashMap<String, EventCategory>();
	
	/**
	 * Returns the category or null if this category doesn't have such a child category.
	 * 
	 * @param name the name of the sub category
	 * @return the category or null if the category doesn't exist
	 */
	public EventCategory getCategory(String name) {
		return this.eventCategories.get(name);
	}
	
	/**
	 * Adds a new sub category.
	 * 
	 * @param name the name of the sub category
	 * @return this category for faster programming
	 */
	public EventCategory addCategory(String name) {
		if (!this.eventCategories.containsKey(name)) this.eventCategories.put(name, new EventCategory());
		return this;
	}
	
	/**
	 * Calls the listeners of this category that an event was fired.
	 * 
	 * @param position the position (normal|before|after)
	 * @param parameters some additional parameters being passed to the listener
	 */
	protected void callListeners(String position, Object[] parameters) {
		for (Listener listener : this.eventListeners) {
			if (!listener.hasPosition(position)) continue;
			
			// call listener
			listener.call(parameters);
		}
	}
	
	/**
	 * Adds a new listener to this event.
	 * 
	 * @param position the position the listener is listening to
	 * @param listener the listener object, either an instance of any class or of IEventListener
	 * @param methodName either null (if the listener implements IEventListener) or the name of the method that will be called if the event was fired
	 */
	protected void addListener(String position, Object listener, String methodName) {
		this.eventListeners.add(new Listener(position, listener, methodName));
	}
	
	/**
	 * This is a class that only helps to pack data into a container.
	 */
	private class Listener {
		private String position;
		private Object listener;
		private String methodName;
		
		/**
		 * Initializes the listener.
		 * 
		 * @param position the position the listener is listening to
		 * @param listener the listener object, either an instance of any class or of IEventListener
		 * @param methodName either null (if the listener implements IEventListener) or the name of the method that will be called if the event was fired
		 */
		public Listener(String position, Object listener, String methodName) {
			this.position = position;
			this.listener = listener;
			this.methodName = methodName;
		}
		
		/** Getters **/
		public String getPosition() {return this.position;}
		public Object getListener() {return this.listener;}
		public String getMethodName() {return this.methodName;}
		public boolean hasPosition(String position) {return this.position.equals(position);}
		public boolean isGeneric() {return (this.methodName != null);}
		
		/**
		 * Calls the listener.
		 * 
		 * @param parameters some additional parameters passed to the listener
		 */
		public void call(Object[] parameters) {
			this.call(parameters, this.listener.getClass());
		}
		
		protected void call(Object[] parameters, Class<?> classType) {
			if (!this.isGeneric()) {
				((IEventListener) this.getListener()).execute(parameters);
				return;
			}
			
			// call generic listener
			try {
				// get method
				Method method = classType.getMethod(this.methodName, ClassUtil.getClassTypes(parameters));
				
				try {
					// call method
					method.invoke(this.listener, parameters);
				} catch (InvocationTargetException ex) {
					throw new Exception("Error calling listener on "+this.listener.getClass().getCanonicalName()+"."+this.methodName+" ("+parameters.length+" parameters)", ex.getCause());
				} catch (IllegalAccessException ex) {
					ReportUtil.getInstance().add(ex);
				} catch (IllegalArgumentException ex) {
					if (parameters != null && parameters.length > 0) this.call(ArrayUtil.removeLast(parameters), classType);
					else ReportUtil.getInstance().add(ex);
				}
			} catch (NoSuchMethodException ex) {
				if (parameters != null && parameters.length > 0) this.call(ArrayUtil.removeLast(parameters), classType);
				else ReportUtil.getInstance().add(ex);
			} catch (Exception ex) {
				ReportUtil.getInstance().add(ex);
			}
		}
		
	}
	
}
