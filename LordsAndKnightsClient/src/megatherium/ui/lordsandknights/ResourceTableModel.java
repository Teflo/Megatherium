/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui.lordsandknights;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import megatherium.communicator.data.lordsandknights.Attack;
import megatherium.communicator.data.lordsandknights.megatherium.HabitatResource;
import megatherium.communicator.data.lordsandknights.megatherium.HabitatUnit;
import megatherium.communicator.data.lordsandknights.megatherium.Resource;
import megatherium.data.store.Stores;
import megatherium.data.store.lordsandknights.ResourceStore;

/**
 *
 * @author marti_000
 */
public class ResourceTableModel extends javax.swing.table.DefaultTableModel {
	protected String[] columns = new String[] {"Name", "Verfügbar (ca.)", "Anzahl"};
	protected List<HabitatResource> items2 = new ArrayList<>();
	protected Map<String, String> amounts = new HashMap<String, String>();
	
	public ResourceTableModel(HabitatResource[] items) {
		for (HabitatResource item : items) {
			this.items2.add(item);
		}
	}
	
	/**
	 * Adds the items to the table model.
	 * 
	 * @param items the items
	 */
	@Deprecated
	public void setItems(HabitatResource[] items) {
		this.items2 = new ArrayList<HabitatResource>();
		for (HabitatResource item : items) {
			this.items2.add(item);
			System.out.println("Added ID \""+item.getID()+"\" as number \""+this.items2.size()+"\"");
		}
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
	public HabitatResource getItem(int rowNumber) {
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
		// get resource
		HabitatResource resource = this.getItem(rowNumber);
		
		// return value
		switch (columnNumber) {
			case 0:
				Resource theresource = ((ResourceStore) Stores.getInstance().getStore("resourceStore")).get(resource.getResourceID());
				return (theresource == null) ? "Unbekannt" : theresource.getLabel();
			case 1:
				return resource.getAmount();
			case 2:
				return (this.amounts.containsKey(resource.getResourceID()+"")) ? this.amounts.get(resource.getResourceID()+"") : 0;
		}
		
		return null;
	}

	@Override
	public void setValueAt(Object o, int i, int i1) {
		HabitatResource resource = this.items2.get(i);
		this.amounts.put(resource.getResourceID()+"", ((int) o)+"");
	}
	
	/**
	 * Returns the amounts.
	 * The key is equal to the resource id and the value contains the amounts.
	 * 
	 * @return the amounts
	 */
	public Map<String, String> getAmounts() {
		return this.amounts;
	}
	
	
	
	
	
}
