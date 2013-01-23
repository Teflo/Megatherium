/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import cloudstorage.communicator.GoogleDriveCommunicator;
import cloudstorage.communicator.data.GoogleDriveLoginInformation;
import cloudstorage.data.File;
import java.io.IOException;
import java.util.List;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.communicator.data.Account;
import megatherium.config.Config;
import megatherium.util.FileUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class FileDownloadTest {
	
	public static void main(String[] args) throws IOException {
		Config.set("debug", false, boolean.class);
		
		// select account
		MegatheriumCommunicator.getInstance().login("SargTeX", "thebospeler");
		Account[] accounts = MegatheriumCommunicator.getInstance().getAccountList();
		Account gdriveAccount = null;
		for (Account account : accounts) if (account.getPlatformName().equals("googledrive")) gdriveAccount = account;
		
		// fetch login information
		GoogleDriveLoginInformation loginInformation = gdriveAccount.getLoginInformation(GoogleDriveLoginInformation.class);
		
		// download a file
		List<File> fileList = GoogleDriveCommunicator.getInstance(gdriveAccount).getFileList(100);
		for (File file : fileList) {
			if (file.isFolder()) continue;
			
			String path = null;
			try {
				String content = GoogleDriveCommunicator.getInstance(gdriveAccount).download(file);
				if (content != null) {
					File parent = file.getParent();
					String seperator = System.getProperty("file.separator");
					while (parent != null) {
						path = parent.getName()+((path != null) ? seperator+path : seperator);
						parent = parent.getParent();
					}
					
					System.out.println("Downloading file to: "+loginInformation.getLocalDirectory()+path+file.getName());
					FileUtil.write(loginInformation.getLocalDirectory()+path, file.getName(), content, true);
				}
			}catch (IOException ex) {
				ReportUtil.getInstance().add(ex);
//				System.err.println("Datei konnte nicht erstellt werden: "+loginInformation.getLocalDirectory()+path+file.getName()+"\nÜberprüfen Sie ggf. den Dateinamen.");
			}
		}
	}
	
}
