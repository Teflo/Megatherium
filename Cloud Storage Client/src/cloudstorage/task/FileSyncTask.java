/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.task;

import cloudstorage.communicator.GoogleDriveCommunicator;
import cloudstorage.data.File;
import java.util.List;
import megatherium.communicator.data.Account;
import megatherium.data.store.AccountStore;
import megatherium.data.store.Stores;

/**
 *
 * @author SargTeX
 */
public class FileSyncTask extends Thread {

	@Override
	public void run() {
		super.run();
		
		// loop through accounts
		for (Account account : Stores.get("accountStore", AccountStore.class).getItems()) {
			// load file list
			List<File> fileList = GoogleDriveCommunicator.getInstance(account).getFileList();
			
		}
	}
	
	
	
}
