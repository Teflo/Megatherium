/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author marti_000
 */
public class StringSplitTest {
	
	public static void main(String[] args) {
		String text = "megatherium.request.user.create";
		String[] parts = text.split("\\.");
		String event = parts[parts.length-1];
		String categories = text.substring(0, text.length()-(event.length()+1));
		System.out.println("Last item: "+event+"\nCategories: "+categories);
	}
	
}
