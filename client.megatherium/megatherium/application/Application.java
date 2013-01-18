/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.application;

import megatherium.controller.Controller;
import megatherium.data.store.AccountStore;
import megatherium.data.store.PlatformStore;
import megatherium.data.store.Store;
import megatherium.data.store.Stores;
import megatherium.language.LanguageServer;
import megatherium.util.Clock;

/**
 *
 * @author marti_000
 */
public abstract class Application {
	protected static Controller controller;
	
	/**
	 * Initializes the application.
	 */
	protected Application() {
		this.initializeController();
		this.initializeStores();
		this.initializeLanguageServer();
		this.initializeClock();
		getController().startup();
	}
	
	/**
	 * Initializes the controller.
	 */
	protected abstract void initializeController();
	
	/**
	 * Initializes the language server with the default language.
	 */
	protected void initializeLanguageServer() {
		LanguageServer.getInstance();
	}
	
	/**
	 * Initializes the system clock.
	 */
	protected void initializeClock() {
		Clock.getInstance();
	}
	
	/**
	 * Initializes the stores.
	 */
	protected void initializeStores() {
		this.addStore("platformStore", new PlatformStore());
		this.addStore("accountStore", new AccountStore());
	}
	
	/**
	 * Returns the controller used by this application.
	 * 
	 * @return the used controller
	 */
	public static Controller getController() {
		return controller;
	}
	
	/**
	 * Adds a new Store to the current store manager.
	 * 
	 * @param name the name of the store
	 * @param store the store's instance
	 */
	protected void addStore(String name, Store store) {
		Stores.getInstance().addStore(name, store);
	}
	
}
