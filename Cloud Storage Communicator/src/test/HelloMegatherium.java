/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import cloudstorage.communicator.GoogleDriveCommunicator;
import cloudstorage.data.File;
import java.io.IOException;
import java.util.List;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.communicator.data.Account;
import megatherium.config.Config;
import megatherium.util.FileUtil;

/**
 *
 * @author SargTeX
 */
public class HelloMegatherium {
	
	public static void main(String[] args) throws IOException {
		Config.set("debug", false, boolean.class);
		
		// select account
		MegatheriumCommunicator.getInstance().login("SargTeX", "thebospeler");
		Account[] accounts = MegatheriumCommunicator.getInstance().getAccountList();
		Account gdriveAccount = null;
		for (Account account : accounts) if (account.getPlatformName().equals("googledrive")) gdriveAccount = account;
		
		// create some folder
		//GoogleDriveCommunicator.getInstance(gdriveAccount).createDirectory("Hello Directory");
		
		// upload file
//		GoogleDriveCommunicator.getInstance(gdriveAccount).upload("Hallo Megatherium", null, "text/plain", FileUtil.getDataPath()+"de.lang");
		long systemTime1 = System.currentTimeMillis();
		List<File> files = GoogleDriveCommunicator.getInstance(gdriveAccount).getFileList();
		long systemTime2 = System.currentTimeMillis();
		long difference = systemTime2 - systemTime1;
		System.out.println("Total: Found "+files.size()+" files");
		System.out.println("Took "+(difference/1000/60)+":"+((difference/1000)%60)+"min");
//		for (File file : files) {
//			System.out.println(file.getID()+": "+file.getName());
//		}
	}
	
}
