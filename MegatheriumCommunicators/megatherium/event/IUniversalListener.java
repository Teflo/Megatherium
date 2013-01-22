/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.event;

/**
 *
 * @author SargTeX
 */
public interface IUniversalListener {
	
	/**
	 * This method is called after an event was executed.
	 * 
	 * @param event the name of the executed event
	 * @param position either "before" or "after"; see "IEventHandler" class for more information
	 * @param parameters some parameters or null if no parameters where given
	 */
	public void execute(String event, String position, Object[] parameters);
	
}
