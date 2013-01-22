/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.gui.lordsandknights;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import sargtex.config.lordsandknights.Config;
import sargtex.crse.data.lordsandknights.Attack;
import sargtex.crse.data.lordsandknights.Habitat;
import sargtex.crse.data.lordsandknights.HabitatToTarget;

/**
 *
 * @author Pathos
 */
public class HabitatTableModel implements TableModel {
	protected String[] columnNames = {"Name"};
	protected Habitat[] habitats;
	
	/**
	 * Initializes the table model.
	 * 
	 * @param habitatList	a list with the habitats the user has
	 */
	public HabitatTableModel(Habitat[] habitats) {
		this.habitats = habitats;
	}

	@Override
	public int getRowCount() {
		return habitats.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return habitats[rowIndex].getName();
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
//		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
//		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
