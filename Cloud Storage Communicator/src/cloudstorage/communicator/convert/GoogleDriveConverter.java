/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.communicator.convert;

import cloudstorage.communicator.GoogleDriveCommunicator;
import com.google.api.services.drive.model.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author SargTeX
 */
public class GoogleDriveConverter {
	
	/**
	 * @param original the original object
	 * @return the converted object
	 */
	public static cloudstorage.data.File convert(File original) {
		cloudstorage.data.File file = new cloudstorage.data.File(original.getId(), original.getTitle(), original.getDownloadUrl());
		
		// set additional file information
		if (original.getFileSize() != null) file.setFileSize(original.getFileSize());
		if (original.getMimeType() != null && original.getMimeType().equals("application/vnd.google-apps.folder")) file.setFolder(true);
		if (original.getParents().size() > 0) {
			cloudstorage.data.File parent = GoogleDriveCommunicator.getInstance().getFile(original.getParents().get(0).getId());
			file.setParent(parent);
		}
		
		// return file
		return file;
	}
	
	/**
	 * @param originals the original objects
	 * @return the converted objects
	 */
	public static List<cloudstorage.data.File> convert(Collection<File> originals) {
		List<cloudstorage.data.File> files = new ArrayList<cloudstorage.data.File>();
		for (File file : originals) files.add(convert(file));
		return files;
	}
	
}
