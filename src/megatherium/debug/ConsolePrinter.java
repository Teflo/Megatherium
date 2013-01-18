/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.debug;

/**
 *
 * @author Pathos
 */
public class ConsolePrinter extends Printer {

	@Override
	public void print(String message) {
		System.out.print(message);
	}
	
}
