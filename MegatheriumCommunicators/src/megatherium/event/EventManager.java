/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.event;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.util.MapBuilder;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class EventManager {
	private static EventManager instance;
	private Map<String, EventCategory> eventCategories = new HashMap<String, EventCategory>();

	/**
	 * Initializes the event manager.
	 */
	private EventManager() {
	}

	/**
	 * Returns the current instance from the event manager.
	 *
	 * @return the current instance
	 */
	public static EventManager getInstance() {
		if (instance == null) {
			instance = new EventManager();
		}
		return instance;
	}

	/**
	 * Registers a new event.
	 *
	 * @param event the event's name
	 * @return the instance of the event manager itself for faster programming.
	 */
	protected EventManager registerEvent(String event) {
		if (this.eventCategories.containsKey(event)) return this;
		
		// split at last point for category
		String[] parts = event.split("\\.");
		String eventName = parts[parts.length-1];
		String categories = (event.equals(eventName)) ? "" : event.substring(0, event.length()-(eventName.length()+1));
		
		// create parental items
		if (categories.length() > 0) this.registerEvent(categories);
		
		// register the event
		if (categories.length() == 0) {
			this.eventCategories.put(eventName, new EventCategory());
		} else {
			EventCategory category = null;
			for (String categoryName : categories.split("\\.")) {
				if (category == null) category = this.eventCategories.get(categoryName);
				else category = category.getCategory(categoryName);
			}
			
			category.addCategory(eventName);
		}
		
		// return current instance
		return this;
	}
	
	/**
	 * Fires a normal event. This should be used if you can't determine a
	 * "before" and "after" state of the event.
	 *
	 * @param event the name of the event that is fired
	 * @param parameters an array of parameters that will be submitted to the event listener
	 */
	public void fireEvent(String event, Object... parameters) {
		this.fireEvent(event, "normal", parameters);
	}

	/**
	 * Fires an after event. That should be done after the event has been
	 * executed.
	 * 
	 * @param event the name of the event that is fired
	 * @param parameters an array of parameters that will be submitted to the event listener
	 */
	public void fireAfterEvent(String event, Object... parameters) {
		this.fireEvent(event, "after", parameters);
	}

	/**
	 * Fires a before event. That should be done before the event is beeing
	 * executed.
	 * 
	 * @param event the name of the event that is fired
	 * @param parameters an array of parameters that will be submitted to the event listener
	 */
	public void fireBeforeEvent(String event, Object... parameters) {
		this.fireEvent(event, "before", parameters);
	}

	/**
	 * Fires an event.
	 *
	 * @param event the event's name
	 * @param position the position (normal|before|after)
	 * @param parameters the parameters
	 */
	protected void fireEvent(String event, String position, Object... parameters) {
		// register event
		this.registerEvent(event);
		
		// extract information
		String[] categories = event.split("\\.");
		String eventName = categories[categories.length - 1];

		// call all normal listeners
		EventCategory category = null;
		for (String categoryName : categories) {
			if (category == null) category = this.eventCategories.get(categoryName);
			else category = category.getCategory(categoryName);
			
			// call listeners
			if (!categoryName.equals(eventName)) category.callListeners("normal", new Object[] {event, position, parameters});
			else category.callListeners(position, parameters);
		}
	}

	/**
	 * Adds a normal event listener.
	 * 
	 * @param event the name of the event the listener is listening to
	 * @param listener the event listener
	 * @return the object itself for faster programming
	 */
	public EventManager addListener(String event, IEventListener listener) {
		return this.addListener(event, "normal", listener, null);
	}

	/**
	 * Adds a normal after listener. The listener will be called after the event
	 * has been executed.
	 */
	public EventManager addAfterListener(String event, IEventListener listener) {
		return this.addListener(event, "after", listener, null);
	}

	/**
	 * Adds a normal before listener. The listener will be called before the
	 * event has been executed.
	 */
	public EventManager addBeforeListener(String event, IEventListener listener) {
		return this.addListener(event, "before", listener, null);
	}

	/**
	 * Adds a generic event listener. The listener method gets called
	 * generically, that's why it MUST exist to prevent errors.
	 *
	 * @param event the name of the event the listener is listening to
	 * @param object just some class object
	 * @param methodName the name of the method that will be called after the
	 * event was fired. The method must exist within the object
	 * @return the class instance itself for faster programming
	 */
	public EventManager addListener(String event, Object object, String methodName) {
		return this.addListener(event, "normal", object, methodName);
	}

	/**
	 * Adds a generic after listener. The listener will be called after the
	 * event has been executed.
	 *
	 * @param event
	 * @param object
	 * @param methodName
	 * @return the class instance
	 */
	public EventManager addAfterListener(String event, Object object, String methodName) {
		return this.addListener(event, "after", object, methodName);
	}

	/**
	 * Adds a generic before listener. The listener will be called before the
	 * event has been executed.
	 *
	 * @param event
	 * @param object
	 * @param methodName
	 * @return the class instance
	 */
	public EventManager addBeforeListener(String event, Object object, String methodName) {
		return this.addListener(event, "before", object, methodName);
	}

	/**
	 * Adds a new event listener.
	 *
	 * @param event the name of the event the listener is listening to
	 * @param position either normal (no position said), "after" or "before"
	 * @param object the event listener object
	 * @param methodName either the name of the method listening to the event or
	 * null if this is a normal listener
	 * @return the class instance itself for faster programming
	 */
	protected EventManager addListener(String event, String position, Object object, String methodName) {
		this.registerEvent(event);

		// add listener
		EventCategory category = null;
		for (String categoryName : event.split("\\.")) {
			if (category == null) category = this.eventCategories.get(categoryName);
			else category = category.getCategory(categoryName);
		}
		
		// add listener
		category.addListener(position, object, methodName);

		// return current object
		return this;
	}
	
}
