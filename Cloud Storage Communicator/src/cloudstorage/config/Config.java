/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.config;

import cloudstorage.data.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SargTeX
 */
public class Config extends megatherium.config.Config {
	private Map<String, File> fileList = new HashMap<String, File>();
	private File uploadDirectory;
	public File getUploadDirectory() {return this.uploadDirectory;}
	public Collection<File> getFileList() {return fileList.values();}
	public File getFile(String name) {return this.fileList.get(name);}
	public Config setUploadDirectory(File file) {this.uploadDirectory = uploadDirectory; return this;}
	
	/**
	 * Adds a new file.
	 * 
	 * @param file the file name
	 * @return the config object
	 */
	public Config addFile(File file) {
		// generate file name
		String name = file.getName();
		File parent = file.getParent();
		while (parent != null) {
			parent = file.getParent();
			name = parent.getName()+"/"+name;
		}
		
		// add file
		this.fileList.put(name, file);
		
		// return config
		return this;
	}
	
}
