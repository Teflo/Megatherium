/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.lordsandknights;

/**
 *
 * @author Pathos
 */
public class Session {
	private Player player;
	private int sessionTimeout;
	
	/**
	 * Returns the player of this session.
	 * 
	 * @return Player the player
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Returns the session time out.
	 * 
	 * @return int session time out
	 */
	public int getSessionTimeout() {
		return this.sessionTimeout;
	}
	
}
