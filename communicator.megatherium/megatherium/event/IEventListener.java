/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.event;

/**
 *
 * @author SargTeX
 */
public interface IEventListener {
	
	/**
	 * Does the event action.
	 * 
	 * @param parameters some parameters (may be null if no parameters have been sent)
	 */
	public void execute(Object[] parameters);
	
}
