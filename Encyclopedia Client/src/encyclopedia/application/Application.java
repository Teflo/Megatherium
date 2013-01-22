package encyclopedia.application;

import encyclopedia.controller.Controller;
import megatherium.config.Config;

public class Application extends megatherium.application.Application {
	
	public static void main(String[] args) {
		Config.set("debug", true, boolean.class);
//		Config.set("encyclopedia.resultsPerPlatform", 1, int.class);
		new Application();
	}

	@Override
	protected void initializeController() {
		controller = new Controller();
	}
	
}
