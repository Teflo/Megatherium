/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data.social;

/**
 *
 * @author Pathos
 */
public class User implements IUser {
	private String displayName;
	private String id;
	private String avatarURL;
	
	/**
	 * Initializes the user.
	 * 
	 * @param id the user's id
	 * @param displayName the display name of the user
	 * @param avatarURL the url of the avatar of the user
	 */
	public User(String id, String displayName, String avatarURL) {
		this.id = id;
		this.displayName = displayName;
		this.avatarURL = avatarURL;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public String getAvatarURL() {
		return this.avatarURL;
	}
	
	
	
}
