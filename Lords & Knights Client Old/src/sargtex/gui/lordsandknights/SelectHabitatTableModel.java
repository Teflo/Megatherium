/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.gui.lordsandknights;

import sargtex.crse.data.lordsandknights.Habitat;

/**
 *
 * @author Pathos
 */
public class SelectHabitatTableModel extends HabitatTableModel {

	protected String[] columnNames = {"Name", "Position", "Angreifen"};
	protected boolean[] selected;

	/**
	 * Initializes the table model.
	 *
	 * @param habitatList	a list with the habitats the user has
	 */
	public SelectHabitatTableModel(Habitat[] habitats) {
		super(habitats);

		// set selection default value to true
		selected = new boolean[habitats.length];
		for (int i = 0; i < selected.length; ++i) {
			selected[i] = true;
		}
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
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex == 2);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case 2:
				return Boolean.class;
		}

		return String.class;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (columnIndex != 2) return;
		
		selected[rowIndex] = ((Boolean) value).booleanValue();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 1:
				return habitats[rowIndex].getPosition();
			case 2:
				return selected[rowIndex];
		}

		return habitats[rowIndex].getName();
	}
}
