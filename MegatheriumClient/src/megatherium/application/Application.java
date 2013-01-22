/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.config.Config;
import megatherium.controller.Controller;
import megatherium.data.store.AccountStore;
import megatherium.data.store.PlatformStore;
import megatherium.data.store.Store;
import megatherium.data.store.Stores;
import megatherium.language.LanguageServer;
import megatherium.util.ClassUtil;
import megatherium.util.Clock;
import megatherium.util.FileUtil;
import megatherium.util.JsonUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public abstract class Application {
	protected static Controller controller;
	private static Map<Class<?>, Object> configObjects = new HashMap<Class<?>, Object>();
	
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
	 * Returns the config object.
	 * 
	 * @param classType the class type
	 * @return the config
	 */
	public static <T> T getConfig(Class<? extends T> classType) {
		if (configObjects.containsKey(classType)) return ClassUtil.cast(configObjects.get(classType), classType);
		
		try {
			String content = FileUtil.getResourceContent("data.config");
			configObjects.put(classType, JsonUtil.getGson().fromJson(content, classType));
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		return ClassUtil.cast(configObjects.get(classType), classType);
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
