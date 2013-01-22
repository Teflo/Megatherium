/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.util.ClassUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class Stores {
	private Map<String, Store> storeMap = new HashMap<String, Store>();
	private static Stores instance;
	
	private Stores() {}
	
	/**
	 * Returns the instance of the store manager.
	 * 
	 * @return the instance
	 */
	public static Stores getInstance() {
		if (instance == null) instance = new Stores();
		
		return instance;
	}
	
	/**
	 * Returns the instance of the store.
	 * 
	 * @param name the name of the store
	 * @return the store or null if no such store was found
	 */
	public Store getStore(String name) {
		return this.storeMap.get(name);
	}
	
	/**
	 * Returns the instance of the store casted. If the store wasn't inserted, it will be created.
	 * 
	 * @param name the name of the store
	 * @param classType the classtype
	 * @return the store or null if no such store was found
	 */
	public <T> T getStore(String name, Class<? extends T> classType) {
		if (!this.storeMap.containsKey(name)) this.createStore(name, classType);
		return ClassUtil.cast(this.storeMap.get(name), classType);
	}
	
	/**
	 * Creates a new store instance.
	 * 
	 * @param name
	 * @param classType
	 */
	protected void createStore(String name, Class<?> classType) {
		Store store = null;
		try {
			String param = name.substring(name.split("-")[0].length());
			store = (Store) classType.getConstructor(String.class).newInstance(param);
		} catch (NoSuchMethodException ex) {
			try {
				store = (Store) classType.getConstructor().newInstance();
			} catch (InstantiationException ex1) {
				ReportUtil.getInstance().add(ex1);
			} catch (IllegalAccessException ex1) {
				ReportUtil.getInstance().add(ex1);
			} catch (IllegalArgumentException ex1) {
				ReportUtil.getInstance().add(ex1);
			} catch (InvocationTargetException ex1) {
				ReportUtil.getInstance().add(ex1);
			} catch (NoSuchMethodException ex1) {
				ReportUtil.getInstance().add(ex1);
			} catch (SecurityException ex1) {
				ReportUtil.getInstance().add(ex1);
			}
		} catch (SecurityException ex) {
			ReportUtil.getInstance().add(ex);
		} catch (InstantiationException ex) {
			ReportUtil.getInstance().add(ex);
		} catch (IllegalAccessException ex) {
			ReportUtil.getInstance().add(ex);
		} catch (IllegalArgumentException ex) {
			ReportUtil.getInstance().add(ex);
		} catch (InvocationTargetException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		this.storeMap.put(name, store);
	}
	
	/**
	 * Adds a new Store to the current store manager.
	 * 
	 * @param name the name of the store
	 * @param store the store's instance
	 */
	public void addStore(String name, Store store) {
		this.storeMap.put(name, store);
	}
	
	/** Redirections for faster programming **/
	public static Store get(String name) {return Stores.getInstance().getStore(name);}
	public static <T> T get(String name, Class<? extends T> classType) {return Stores.getInstance().getStore(name, classType);}
	
	
}
