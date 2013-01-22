/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.application;

import cloudstorage.controller.Controller;
import megatherium.config.Config;

/**
 *
 * @author SargTeX
 */
public class Application extends megatherium.application.Application {
	
	public static void main(String[] args) {
		Config.set("debug", false, boolean.class);
		new Application();
	}

	@Override
	protected void initializeController() {
		this.controller = new Controller();
	}
	
}
