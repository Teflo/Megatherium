/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import java.util.Map;
import megatherium.debug.exception.MissingCredentialException;

/**
 *
 * @author Pathos
 */
public interface IAuthenticationCommunicator {
	
	/**
	 * This method logs the user into the system by using the given credentials.
	 * 
	 * 
	 * @param login some login data, e.g. the username or the email adress
	 * @param password the already encoded password
	 * @return returns true if the user was successfully logged in or false if he wasn't
	 */
	public boolean login(String login, String password);
	
}
