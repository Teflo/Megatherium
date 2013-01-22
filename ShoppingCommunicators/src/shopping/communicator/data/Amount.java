/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.communicator.data;

/**
 *
 * @author SargTeX
 */
public class Amount {
	private double price;
	private Currency currency;
	public double getPrice() {return this.price;}
	public Currency getCurrency() {return this.currency;}
	
	public Amount(double price, Currency currency) {
		this.price = price;
		this.currency = currency;
	}
	
}
