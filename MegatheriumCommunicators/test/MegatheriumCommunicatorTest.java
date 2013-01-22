/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import megatherium.communicator.MegatheriumCommunicator;

/**
 *
 * @author SargTeX
 */
public class MegatheriumCommunicatorTest {
	
	public static void main(String[] args) {
		MegatheriumCommunicator.getInstance().createUser("SargTeX", "martin.christian.bories@gmail.com", "thebospeler");
		MegatheriumCommunicator.getInstance().login("SargTeX", "thebospeler");
	}
	
}
