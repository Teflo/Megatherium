/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.gui.lordsandknights;

import java.util.Iterator;
import java.util.Map;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import sargtex.crse.data.lordsandknights.Habitat;

/**
 *
 * @author Pathos
 */
public class HabitatComboBoxModel implements ComboBoxModel {
	private Habitat[] habitats;
	private Habitat selectedItem;
	
	/**
	 * Initializes the class.
	 * 
	 * @param Map<String, Habitat> the habitats
	 */
	public HabitatComboBoxModel(Map<String, Habitat> habitats) {
		// add habitats to array
		this.habitats = new Habitat[habitats.size()];
		Iterator<String> iterator = habitats.keySet().iterator();
		int i = 0;
		while(iterator.hasNext()) {
			String key = iterator.next();
			this.habitats[i] = habitats.get(key);
			++i;
		}
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selectedItem = (Habitat) anItem;
	}

	@Override
	public Habitat getSelectedItem() {
		return this.selectedItem;
	}

	@Override
	public int getSize() {
		return habitats.length;
	}

	@Override
	public Object getElementAt(int index) {
		return habitats[index];
	}

	@Override
	public void addListDataListener(ListDataListener l) {
//		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
//		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
