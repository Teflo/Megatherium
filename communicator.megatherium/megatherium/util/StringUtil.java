/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

/**
 *
 * @author marti_000
 */
public class StringUtil {
	
	/**
	 * Upper-cases the first letter of the string.
	 * 
	 * @param text the string that will be uppercased at the first letter
	 * @return the uppercased text
	 */
	public static String ucfirst(String text) {
		return Character.toUpperCase(text.charAt(0))+text.substring(1);
	}
	
}
