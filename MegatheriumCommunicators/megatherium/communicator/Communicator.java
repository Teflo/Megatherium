/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

/**
 *
 * @author SargTeX
 */
public abstract class Communicator {
	protected megatherium.communicator.data.Platform platform;
	
	/**
	 * Initializes the communicator.
	 */
	public Communicator() {
		this.initializePlatform();
	}
	
	/**
	 * Returns the platform from this communicator.
	 * 
	 * @return the platform
	 */
	public megatherium.communicator.data.Platform getPlatform() {
		return this.platform;
	}
	
	/**
	 * Initializes the platform.
	 */
	protected abstract void initializePlatform();
	
}
