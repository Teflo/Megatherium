/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui.lordsandknights;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import megatherium.communicator.data.lordsandknights.megatherium.Attack;

/**
 *
 * @author marti_000
 */
public class AttackTableModel extends javax.swing.table.DefaultTableModel {
	protected String[] columns = new String[] {"Konto", "Start", "Ziel", "Uhrzeit"};
	protected List<Attack> items2 = new ArrayList<Attack>();
	
	public AttackTableModel(Attack[] items) {
		this.items2 = new ArrayList<Attack>();
		for (Attack item : items) this.items2.add(item);
	}
	
	/**
	 * Adds the items to the table model.
	 * 
	 * @param items the items
	 */
	@Deprecated
	public void setItems(List<Attack> items) {
		System.out.println("set items");
		this.items2 = items;
		this.fireTableDataChanged();
	}
	
	/**
	 * Adds the items to the table model.
	 * 
	 * @param items the items
	 */
	@Deprecated
	public void setItems(Attack[] items) {
		System.out.println("set items");
		this.items2 = new ArrayList<Attack>();
		for (Attack item : items) this.items2.add(item);
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
	public Attack getItem(int rowNumber) {
		return this.items2.get(rowNumber);
	}

	@Override
	public Object getValueAt(int rowNumber, int columnNumber) {
		if (rowNumber > (this.getRowCount() - 1)) return null;
		
		// get attack
		Attack attack = this.getItem(rowNumber);
		
		// return value
		switch (columnNumber) {
			case 0:
				return attack.getAccountID();
			case 1:
				return attack.getStartHabitatID();
			case 2:
				return attack.getTargetHabitatID();
			case 3:
				return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(attack.getTime());
		}
		return null;
	}
	
}
