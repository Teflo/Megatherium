/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.gui.lordsandknights;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import sargtex.crse.data.lordsandknights.World;

/**
 *
 * @author Pathos
 */
public class WorldComboBoxModel implements ComboBoxModel {
	private List<World> worldList;
	private World selectedItem;
	
	/**
	 * Initializes the class.
	 * 
	 * @param Map<String, Habitat> the habitats
	 */
	public WorldComboBoxModel(List<World> worldList) {
		this.worldList = worldList;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selectedItem = (World) anItem;
	}

	@Override
	public World getSelectedItem() {
		return this.selectedItem;
	}

	@Override
	public int getSize() {
		return worldList.size();
	}

	@Override
	public World getElementAt(int index) {
		return worldList.get(index);
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
