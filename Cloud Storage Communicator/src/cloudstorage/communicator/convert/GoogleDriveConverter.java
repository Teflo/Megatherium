/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.communicator.convert;

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
		cloudstorage.data.File file = new cloudstorage.data.File(original.getId(), original.getTitle());
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
