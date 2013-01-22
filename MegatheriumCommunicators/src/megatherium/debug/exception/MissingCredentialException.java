/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.debug.exception;

/**
 *
 * @author Pathos
 */
public class MissingCredentialException extends Exception {
	
	public MissingCredentialException(String parameter, String message) {
		super(message);
	}
	
}
