/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.controller;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashAttributeSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import megatherium.ui.EventPanel;
import megatherium.ui.MainDialog;
import megatherium.ui.MainFrame;
import megatherium.ui.HeaderPanel;
import megatherium.event.EventHandler;
import megatherium.event.EventManager;
import megatherium.event.IEventHandler;
import megatherium.event.IUniversalListener;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public abstract class Controller implements IUniversalListener {
	private Map<String, EventPanel> panelList = new HashMap<String, EventPanel>();
	protected JFrame frame;
	protected HeaderPanel statusPanel;
	private JPanel currentPanel;
	protected MainDialog dialog;
	
	/**
	 * Initializes the panel list.
	 * This means, adds all panels to this controller that shall be managed this controller.
	 */
	protected abstract void initializePanels();
	
	/**
	 * Initializes the main frame.
	 */
	protected void initializeFrame() {
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		this.frame = new JFrame();
		this.statusPanel = new HeaderPanel();
		this.frame.add(this.statusPanel, BorderLayout.NORTH);
		this.frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//		this.frame = new MainFrame();
//		this.frame.setLayout(new BorderLayout());
//		this.frame.pack();
	}
	
	/**
	 * Initializes the event listeners.
	 */
	protected void initializeEvents() {
		// initialize references
		for (String[] reference : this.getReferences()) {
			if (reference.length != 2) continue;
			
			// fetch data
			String eventName = reference[0];
			String methodName = reference[1];
			
			// set listener
			EventManager.getInstance().addListener(eventName, this, methodName);
		}
	}
	
	/**
	 * Initializes the main dialog.
	 */
	protected void initializeDialog() {
		this.dialog = new MainDialog(this.frame, true);
		this.dialog.setLayout(new BorderLayout());
		this.dialog.pack();
	}
	
	/**
	 * Returns a two dimensional array with event references.
	 * The inner array must contain 2 items. The first item contains the name of the event, the second contains the name of the method that will be invoked after the event was fired. If the inner array contains more or less than 2 items, it will be ignored.
	 * The outer array may contain as many items as you want.
	 * 
	 * @return the event - method references
	 */
	protected String[][] getReferences() {return null;}
	
	/**
	 * This method has to return the name of the panel that will be displayed after startup.
	 * That panel has to be registered before by the addPanel() method.
	 * 
	 * @return the start/home/login/whatever panel name
	 */
	public abstract String getStartupPanel();
	
	/**
	 * Adds a new panel into the panel list.
	 * This panel must include the megatherium.client.ui.EventPanel class as it has to manage events that will then be managed by this controller.
	 * 
	 * @param name the name of the panel; the panel will be identified by this name
	 * @param panel the panel that will be added
	 */
	public void addPanel(String name, EventPanel panel) {
		this.panelList.put(name, panel);
	}
	
	/**
	 * Boots this controller, starts the application, calls initializing methods and so on.
	 */
	public void startup() {
		// initialize panels
		this.initializePanels();
		
		// initialize frame
		this.initializeFrame();
		System.out.println(this.frame);
		
		// initialize event listening
		this.initializeEvents();
		
		// display first panel
		this.display(this.getStartupPanel());
		
		// display application
		this.frame.setVisible(true);
	}

	@Override
	public void execute(String event, String position, Object[] parameters) {
		// fires the event as a controller event for some purposes
	}
	
	/**
	 * Returns a panel by it's name that has been registered via the addPanel() method.
	 * 
	 * @param name the name of the panel
	 * @return the panel or null if no such panel was found
	 */
	public EventPanel getPanel(String name) {
		return this.panelList.get(name);
	}
	
	/**
	 * Displays a panel that has been registered via "addPanel()" before.
	 * 
	 * @param name the name of the panel
	 */
	public void display(String name) {
		EventPanel panel = this.getPanel(name);
		if (this.currentPanel != null) this.frame.remove(this.currentPanel);
		this.currentPanel = panel;
		this.frame.add(panel, BorderLayout.WEST);
		this.frame.repaint();
		this.frame.pack();
//		this.frame.setPanel(this.getPanel(name));
	}
	
	/**
	 * Displays a panel that has been registered via "addPanel()" within a dialog.
	 * 
	 * @param name the name of the panel
	 */
	public void displayDialog(String name) {
		if (this.dialog == null) this.initializeDialog();
		this.dialog.setPanel(this.getPanel(name));
		this.dialog.setVisible(true);
	}
	
	/**
	 * Closes the dialog.
	 */
	public void closeDialog() {
		if (this.dialog == null) return;
		this.dialog.setVisible(false);
		this.dialog = null;
	}
	
	/**
	 * Displays a notification.
	 * 
	 * @param message the message that will be displayed
	 */
	public void showNotification(String message) {
		JOptionPane.showMessageDialog(this.frame, message);
	}
	
	/**
	 * Sets the current status message.
	 * 
	 * @param status the status message
	 */
	public void setStatus(String status) {
		this.statusPanel.setStatus(status);
	//	this.frame.setStatus(status);
	}
	
}
