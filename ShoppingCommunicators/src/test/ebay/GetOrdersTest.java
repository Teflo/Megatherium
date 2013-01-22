/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.ebay;

import shopping.communicator.EBayCommunicator;

/**
 *
 * @author SargTeX
 */
public class GetOrdersTest {
	
	public static void main(String[] args) {
		EBayCommunicator.getInstance().getOrderList();
	}
	
}
