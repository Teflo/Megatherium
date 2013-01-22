/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data.social;

/**
 *
 * @author Pathos
 */
public interface IUser {
	
	/**
	 * Returns the display name of the user.
	 * 
	 * @return the display name
	 */
	public String getDisplayName();
	
	/**
	 * Returns the id of the user.
	 * Remember, that some websites do NOT use numbers as IDs. That's why this interfaces uses "String" for the user IDs.
	 * 
	 * @return the id
	 */
	public String getID();
	
	/**
	 * Returns the url of the avatar of the user.
	 * 
	 * @return the avatar url
	 */
	public String getAvatarURL();
	
}
