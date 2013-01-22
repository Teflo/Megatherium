/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.event;

/**
 *
 * @author SargTeX
 */
public interface IEventHandler {
	
	/**
	 * Adds a listener for an event.
	 * 
	 * @param event the name of the event
	 * @param listener the listener
	 * @param position the position (before|after)
	 */
	public void addListener(String event, IEventListener listener, String position);
	
	/**
	 * Adds an "after" listerner.
	 * 
	 * @param event the name of the event
	 * @param listener the listener
	 */
	public void addAfterListener(String event, IEventListener listener);
	
	/**
	 * Adds an "before" listener.
	 * 
	 * @param event the name of the event
	 * @param listener the listener
	 */
	public void addBeforeListener(String event, IEventListener listener);
	
	/**
	 * Adds an universal listener.
	 * This listener will be called on each event firing - on "before" and "after" events.
	 * 
	 * @param listener the universal listener
	 */
	public void addUniversalListener(IUniversalListener listener);
	
	/**
	 * Fires an event.
	 * This will call all listeners for that event.
	 * 
	 * @param event the name of the event
	 * @param position before|after
	 */
	public void fireEvent(String event, String position);
	
	/**
	 * Fires an event and gives the listener some parameters.
	 * This will call all listeners for that event.
	 * 
	 * @param event the name of the event
	 * @param position before|after
	 * @param parameters an array with objects determining the parameters
	 */
	public void fireEvent(String event, String position, Object[] parameters);
	
}
