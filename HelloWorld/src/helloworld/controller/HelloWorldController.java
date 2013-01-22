/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld.controller;

import helloworld.ui.HomePanel;
import megatherium.controller.Controller;
import megatherium.controller.MegatheriumController;
import megatherium.util.ArrayUtil;

/**
 *
 * @author marti_000
 */
public class HelloWorldController extends MegatheriumController {

	@Override
	public void initializePanels() {
		super.initializePanels();
		
		this.addPanel("homePanel", new HomePanel());
	}

	@Override
	protected String[][] getReferences() {
		return ArrayUtil.merge2(super.getReferences(), new String[][] {
			{"helloworld.ui.dialog.open", "displayDialog"}
		});
	}

	@Override
	public String getStartupPanel() {
		return "homePanel";
	}
	
	
	
}
