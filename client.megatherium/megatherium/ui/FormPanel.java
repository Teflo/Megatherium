/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SargTeX
 */
public abstract class FormPanel extends EventPanel {
	
	/**
	 * Returns the values of this form.
	 * 
	 * @return the values
	 */
	public abstract Map<String, String> getValues();
	
}
