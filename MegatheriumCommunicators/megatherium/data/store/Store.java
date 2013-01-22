/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import megatherium.event.EventHandler;
import megatherium.event.EventManager;

/**
 *
 * @author marti_000
 */
public abstract class Store<T> {
	private T classType;
	private List<T> itemList = new ArrayList<T>();
	
	/**
	 * If set to true, the data from the store will be automatically loaded after initializing the store.
	 */
	protected boolean autoLoad = true;
	
	/**
	 * Initializes the store.
	 */
	public Store() {
	//	if (this.autoLoad) this.load();
	}
	
	/**
	 * Loads the data from remote or from a file and adds it into the store.
	 */
	public abstract void load();
	
	/**
	 * Adds an object to the store.
	 * 
	 * @param item the item
	 */
	public void add(T item) {
		if (item == null) return;
		EventManager.getInstance().fireBeforeEvent("megatherium.data.store.Store", "addItem", new Object[] {item});
		this.itemList.add(item);
		EventManager.getInstance().fireAfterEvent("megatherium.data.store.Store", "addItem", new Object[] {item});
	}
	
	/**
	 * Adds several objects to the store.
	 * 
	 * @param itemList another list of items
	 */
	public void add(List<? extends T> itemList) {
		if (itemList == null) return;
		for (T item : itemList) {
			this.add(item);
		}
	}
	
	/**
	 * Adds several objects to the store.
	 * 
	 * @param items an array of items
	 */
	public void add(T[] items) {
		for (T item : items) {
			this.add(item);
		}
	}
	
	/**
	 * Returns the item at the specific position.
	 * 
	 * @param position the position of the item
	 * @return the item or null if the position is beyond the list
	 */
	public T getItem(int position) {
		T object = (position < this.itemList.size()) ? this.itemList.get(position) : null;
		return object;
	}
	
	/**
	 * Returns all items.
	 * 
	 * @return the item list
	 */
	public List<T> getItems() {
		if (this.autoLoad && this.itemList.size() == 0) this.load();
		return this.itemList;
	}
	
	/**
	 * Returns the identifyable name of this store.
	 * 
	 * @return the store name
	 */
	public abstract String getName();
	
}
