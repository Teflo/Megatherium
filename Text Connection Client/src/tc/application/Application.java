/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tc.application;

import megatherium.config.Config;
import tc.controller.Controller;
import tc.data.store.TextStore;

/**
 *
 * @author marti_000
 */
public class Application extends megatherium.application.Application {

	public static void main(String[] args) {
		Config.set("debug", false, boolean.class);
		new Application();
	}
	
	@Override
	protected void initializeController() {
		controller = new Controller();
	}

	@Override
	protected void initializeStores() {
		super.initializeStores();
		
		this.addStore("textStore", new TextStore());
	}
	
	
	
}
