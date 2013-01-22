/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.communicator;

import megatherium.communicator.data.Platform;

/**
 *
 * @author marti_000
 */
public abstract class EncyclopediaCommunicator implements IEncyclopediaCommunicator {
	private Platform platform;
	
	@Override
	public Platform getPlatform() {return this.platform;}
	
	@Override
	public IEncyclopediaCommunicator setPlatform(Platform platform) {this.platform = platform; return this;}
	
}
