/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tc.controller;

import megatherium.communicator.data.Response;
import megatherium.data.store.Stores;
import megatherium.util.ArrayUtil;
import tc.ui.HomePanel;
import tc.ui.TextConnectPanel;
import tc.ui.TextConnectionPanel;
import tc.ui.TextCreatePanel;
import textconnect.communicator.TextConnectCommunicator;
import textconnect.communicator.data.Selection;
import textconnect.communicator.data.Text;
import textconnect.communicator.data.Text;

/**
 *
 * @author marti_000
 */
public class Controller extends megatherium.controller.MegatheriumController {

	@Override
	public void initializeEvents() {
		super.initializeEvents();
	}

	@Override
	protected String[][] getReferences() {
		return ArrayUtil.merge2(super.getReferences(), new String[][] {
			{"textconnect.data.connection.create", "createConnection"},
			{"textconnect.data.text.create", "createText"},
			{"textconnect.ui.text.connect.cancel", "closeDialog"},
			{"textconnect.ui.text.connect.show", "showTextConnect"},
			{"textconnect.ui.text.connection.close", "closeDialog"},
			{"textconnect.ui.text.connection.show", "showTextConnection"},
			{"textconnect.ui.text.create.cancel", "closeDialog"},
			{"textconnect.ui.text.create.show", "showTextCreate"}
		});
	}

	@Override
	public void initializePanels() {
		super.initializePanels();
		
		this.addPanel("homePanel", new HomePanel());
		this.addPanel("textConnect", new TextConnectPanel());
		this.addPanel("textConnection", new TextConnectionPanel());
		this.addPanel("textCreate", new TextCreatePanel());
	}
	
	@Override
	public String getStartupPanel() {
		return "homePanel";
	}
	
	/** References **/
	public void createConnection(String label, Selection selection1, Selection selection2, String comment) {
		if (TextConnectCommunicator.getInstance().addConnection(label, selection1, selection2, comment).getStatus()) {
			this.showNotification("Verbindung wurde erstellt");
		}else this.showNotification("Es ist ein Fehler aufgetreten - Verbindung konnte nicht erstellt werden.");
	}
	public void createText(String title, String text, String comment) {
		if (TextConnectCommunicator.getInstance().addText(title, text, comment).getStatus()) {
			this.showNotification("Text wurde erstellt.");
			this.closeDialog();
			Stores.getInstance().getStore("textStore").load();
		}else this.showNotification("Es ist ein Fehler aufgetreten - Text konnte nicht erstellt werden.");
	}
	public void showTextConnect(Text[] texts) {((TextConnectPanel) this.getPanel("textConnect")).setTexts(texts); this.displayDialog("textConnect");}
	public void showTextConnection(Text[] texts) {((TextConnectionPanel) this.getPanel("textConnection")).setTexts(texts); this.displayDialog("textConnection");}
	public void showTextCreate() {this.displayDialog("textCreate");}
	
}
