/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import java.util.List;
import megatherium.communicator.data.social.IUser;
import megatherium.communicator.data.social.User;

/**
 *
 * @author Pathos
 */
public interface ISocialCommunicator extends IWebsiteCommunicator, IOAuthCommunicator {
	
	/**
	 * Posts a status message.
	 * 
	 * @param message the status message
	 */
	public void postStatusMessage(String message);
	
	/**
	 * Sends a message to another user.
	 * 
	 * @param userID the id of the user
	 * @param message the private message
	 */
	public void sendPrivateMessage(String userID, String message);
	
	/**
	 * Returns the URL of the avatar of the user.
	 * 
	 * @param userID the id of the user
	 * @return the avatar url
	 */
	public String getAvatarURL(String userID);
	
	/**
	 * Returns the contacts list.
	 * 
	 * @return the contacts list
	 */
	public List<User> getContactList();
	
	/**
	 * Returns the user object of the current logged-in user.
	 * 
	 * @return the user object
	 */
	public IUser getUser();
	
	/**
	 * Returns the user object by some user.
	 * 
	 * @param userID the id of the user
	 * @return the user object
	 */
	public IUser getUser(String id);
	
	/**
	 * Returns an instance of the communicator with that access token.
	 * 
	 * @param accessToken the access token as string (e.g. serialized)
	 * @return the communicator with that access token
	 */
	public ISocialCommunicator getAuthorizedInstance(String token);
	
}
