/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.debug;

/**
 *
 * @author SargTeX
 */
public class JsonError {
	private String errorMessage;
	private String[] additionalInformation;
	public String getErrorMessage() {return this.errorMessage;}
	public String[] getAdditionalInformation() {return this.additionalInformation;}

	/**
	 * Initializes the error.
	 *
	 * @param errorMessage the error message
	 * @param additionalInformation some additional information for the
	 * developer, e.g. the stack trace
	 */
	public JsonError(String errorMessage, String[] additionalInformation) {
		this.errorMessage = errorMessage;
		this.additionalInformation = additionalInformation;
	}
}
