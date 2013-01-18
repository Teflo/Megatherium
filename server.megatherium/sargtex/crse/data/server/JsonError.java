/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.data.server;

/**
 *
 * @author Pathos
 */
public class JsonError {
	private String errorMessage;
	private String additionalInformation;
	
	/**
	 * Initializes the error.
	 * 
	 * @param errorMessage the error message
	 * @param additionalInformation some additional information for the developer, e.g. the stack trace
	 */
	public JsonError(String errorMessage, String additionalInformation) {
		this.errorMessage = errorMessage;
		this.additionalInformation = additionalInformation;
	}
	
}
