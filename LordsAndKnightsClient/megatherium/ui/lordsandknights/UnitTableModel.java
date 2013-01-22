/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui.lordsandknights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import megatherium.communicator.data.lordsandknights.megatherium.HabitatResource;
import megatherium.communicator.data.lordsandknights.megatherium.HabitatUnit;
import megatherium.communicator.data.lordsandknights.megatherium.Resource;
import megatherium.communicator.data.lordsandknights.megatherium.Unit;
import megatherium.data.store.Stores;
import megatherium.data.store.lordsandknights.ResourceStore;
import megatherium.data.store.lordsandknights.UnitStore;

/**
 *
 * @author marti_000
 */
public class UnitTableModel extends javax.swing.table.DefaultTableModel {
	protected String[] columns = new String[] {"Name", "Verfügbar (ca.)", "Anzahl"};
	protected List<HabitatUnit> items2 = new ArrayList<>();
	protected Map<String, String> amounts = new HashMap<String, String>();
	
	public UnitTableModel(HabitatUnit[] items) {
		for (HabitatUnit item : items) this.items2.add(item);
	}
	
	/**
	 * Adds the items to the table model.
	 * 
	 * @param items the items
	 */
	@Deprecated
	public void setItems(List<HabitatUnit> items) {
		this.items2 = items;
		this.fireTableDataChanged();
	}
	
	/**
	 * Adds the items to the table model.
	 * 
	 * @param items the items
	 */
	@Deprecated
	public void setItems(HabitatUnit[] items) {
		this.items2 = new ArrayList<HabitatUnit>();
		for (HabitatUnit item : items) this.items2.add(item);
		this.fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		if (this.items2 == null) {
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
	
	/**
	 * Returns the item for the specified row.
	 * 
	 * @param rowNumber the number of the row (alias the index of the object)
	 * @return the object/item
	 */
	public HabitatUnit getItem(int rowNumber) {
		return this.items2.get(rowNumber);
	}
	
	@Override
	public Class<?> getColumnClass(int i) {
		return (i == 2) ? Integer.class : String.class;
	}
	
	@Override
	public boolean isCellEditable(int rowNumber, int columnNumber) {
		return (columnNumber == 2);
	}
	
	@Override
	public Object getValueAt(int rowNumber, int columnNumber) {
		if (rowNumber > (this.getRowCount() - 1)) return null;
		
		// get resource
		HabitatUnit unit = this.getItem(rowNumber);
		
		// return value
		switch (columnNumber) {
			case 0:
				Unit theunit = ((UnitStore) Stores.getInstance().getStore("unitStore")).get(unit.getUnitID());
				return (theunit == null) ? "Unbekannt" : theunit.getLabel();
			case 1:
				return unit.getAmount();
			case 2:
				return (this.amounts.containsKey(unit.getUnitID()+"")) ? this.amounts.get(unit.getUnitID()+"") : 0;
		}
		
		return null;
	}

	@Override
	public void setValueAt(Object o, int i, int i1) {
		HabitatUnit unit = this.items2.get(i);
		this.amounts.put(unit.getUnitID()+"", ((int) o)+"");
	}
	
	/**
	 * Returns the amounts.
	 * The key is equal to the unit id and the value contains the amounts.
	 * 
	 * @return the amounts
	 */
	public Map<String, String> getAmounts() {
		return this.amounts;
	}
	
}
