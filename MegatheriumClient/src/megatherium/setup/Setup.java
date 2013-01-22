/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.setup;

import megatherium.application.Application;
import megatherium.ui.EventPanel;
import megatherium.ui.FormPanel;

/**
 *
 * @author SargTeX
 */
public abstract class Setup {
	private int position;
	
	/**
	 * Returns the list of panels that will be displayed in the setup.
	 * The first item in the list will be displayed on begin. When the setup continues, the second item will be displayed and so on.
	 * 
	 * @return a list of panel names
	 */
	public abstract String[] getPanelStack();
	
	/**
	 * Starts the setup.
	 */
	public void start() {
		position = 0;
		
		// display next panel
		displayNextPanel();
	}
	
	/**
	 * Displays the next panel.
	 */
	public void displayNextPanel() {
		displayPanel(position+1);
	}
	
	/**
	 * Displays the previous panel.
	 */
	public void displayPreviousPanel() {
		displayPanel(position-1);
	}
	
	/**
	 * Displays a panel.
	 * 
	 * @param position the position of the panel in the stack
	 */
	public void displayPanel(int position) {
		FormPanel panel = (FormPanel) Application.getController().getPanel(getPanelStack()[position]);
		
		Application.getController().display(getPanelStack()[position]);
	}
	
}
