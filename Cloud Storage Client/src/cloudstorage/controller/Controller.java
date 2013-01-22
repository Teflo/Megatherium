/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.controller;

import cloudstorage.communicator.GoogleDriveCommunicator;
import cloudstorage.communicator.data.GoogleDriveLoginInformation;
import cloudstorage.config.Config;
import cloudstorage.ui.FileAddPanel;
import cloudstorage.ui.GoogleDriveLoginInformationPanel;
import cloudstorage.ui.HomePanel;
import cloudstorage.ui.SettingsPanel;
import java.io.File;
import megatherium.application.Application;
import megatherium.communicator.data.ILoginInformation;
import megatherium.communicator.data.Platform;
import megatherium.controller.MegatheriumController;
import megatherium.util.ArrayUtil;

/**
 *
 * @author SargTeX
 */
public class Controller extends MegatheriumController {

	@Override
	protected String[][] getReferences() {
		return ArrayUtil.merge2(super.getReferences(), new String[][] {
			{"megatherium.ui.account.login.information.show", "showLoginInformation"},
			{"storage.ui.settings.cancel", "closeDialog"},
			{"storage.ui.settings.save", "saveSettings"},
			{"storage.ui.settings.show", "showSettings"}
		});
	}

	@Override
	public void initializePanels() {
		super.initializePanels();
		
		this.addPanel("fileAdd", new FileAddPanel());
		this.addPanel("googleDriveLoginInformation", new GoogleDriveLoginInformationPanel());
		this.addPanel("homePanel", new HomePanel());
		this.addPanel("settingsPanel", new SettingsPanel());
	}
	
	@Override
	public void setChosenFiles(File[] files) {
		super.setChosenFiles(files);
		((FileAddPanel) this.getPanel("fileAdd")).setFiles(files);
		this.displayDialog("fileAdd");
	}
	
	/** References **/
	public void saveAccountLoginInformation(GoogleDriveLoginInformation information) {super.saveAccountLoginInformation(information);}
//	public void saveSettings(String uploadDirectory) {Application.getConfig(Config.class).setUploadDirectory(new cloudstorage.data.File())}
	public void showLoginInformation(Platform platform) {
		if (platform.getName().equals("googledrive")) {
			((GoogleDriveLoginInformationPanel) this.getPanel("googleDriveLoginInformation")).setAuthenticationURL(GoogleDriveCommunicator.getInstance().getAuthorizationURL());
			displayDialog("googleDriveLoginInformation");
		}
	}
	public void showSettings() {this.displayDialog("settingsPanel");}
	
}
