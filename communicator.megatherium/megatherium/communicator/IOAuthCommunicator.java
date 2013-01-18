/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import megatherium.event.IEventHandler;

/**
 *
 * @author Pathos
 */
public interface IOAuthCommunicator extends IEventHandler {
	
	/**
	 * Returns the url the user has to access to get the pin for the oauth access.
	 * 
	 * @return the url for getting the authorization key
	 * @deprecated 
	 */
	public String getOAuthorizationURL();
	
	/**
	 * Returns the url the user has to access to get the pin for the oauth access with the specified callback url (e.g. required for mobile applications).
	 * 
	 * @param callbackURL the url that will be called from the OAuth server after the access token has been generated
	 * @return the url for getting the authorization key
	 */
	public String getOAuthorizationURL(String callbackURL);
	
	/**
	 * Fetches a new access token for the current account and returns it.
	 * 
	 * @param code the authorization code
	 * @param callbackURL the url that will be opened and that will receive the access token
	 * @return the new access token
	 */
	public String getOAuthAccessTokenURL(String code, String callbackURL);
	
	/**
	 * Returns the currently used access token or null if currently no access token is used.
	 * 
	 * @return the access token or null
	 */
	public String getAccessToken();
	
}
