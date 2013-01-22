/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui.lordsandknights;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti_000
 */
public class DefaultTableModel<T> extends javax.swing.table.DefaultTableModel {
	protected List<T> items2 = new ArrayList<>();
	protected String[] columns = new String[] {};
	
	public DefaultTableModel(T[] items) {
		this.items2 = new ArrayList<>();
		for (T item : items) this.items2.add(item);
	}
	
	/**
	 * Adds the items to the table model.
	 * 
	 * @param items the items
	 */
	public void setItems(List<T> items) {
		System.out.println("set items");
		this.items2 = items;
		this.fireTableDataChanged();
	}
	
	/**
	 * Adds the items to the table model.
	 * 
	 * @param items the items
	 */
	public void setItems(T[] items) {
		System.out.println("set items");
		this.items2 = new ArrayList<T>();
		for (T item : items) this.items2.add(item);
		this.fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		if (this.items2 == null) {
			System.out.println("isnull");
			return 0;
		}
		return this.items2.size();
	}

	@Override
	public int getColumnCount() {
		return this.columns.length;
	}

	@Override
	public String getColumnName(int i) {
		return this.columns[i];
	}

	@Override
	public Class<?> getColumnClass(int i) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowNumber, int columnNumber) {
		return false;
	}
	
	/**
	 * Returns the item for the specified row.
	 * 
	 * @param rowNumber the number of the row (alias the index of the object)
	 * @return the object/item
	 */
	public T getItem(int rowNumber) {
		return this.items2.get(rowNumber);
	}
	
}
