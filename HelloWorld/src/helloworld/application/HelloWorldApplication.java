/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld.application;

import helloworld.controller.HelloWorldController;
import megatherium.application.Application;
import megatherium.data.store.Stores;

/**
 *
 * @author marti_000
 */
public class HelloWorldApplication extends Application {
	
	public static void main(String[] args) {
		new HelloWorldApplication();
	}

	@Override
	protected void initializeController() {
		controller = new HelloWorldController();
	}

	@Override
	protected void initializeStores() {
		super.initializeStores();
		
		this.addStore("textStore", null);
	}

	
	
}
