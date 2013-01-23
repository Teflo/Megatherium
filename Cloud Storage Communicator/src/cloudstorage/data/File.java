/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.data;

/**
 *
 * @author SargTeX
 */
public class File {
	private String name;
	private String id;
	private File parent;
	private String downloadURL;
	private long fileSize;
	private boolean folder = false;
	public String getName() {return this.name;}
	public String getID() {return this.id;}
	public File getParent() {return this.parent;}
	public String getDownloadURL() {return this.downloadURL;}
	public long getFileSize() {return this.fileSize;}
	public boolean isFolder() {return this.folder;}
	public File setFileSize(long fileSize) {this.fileSize = fileSize; return this;}
	public File setFolder(boolean isFolder) {this.folder = isFolder; return this;}
	public File setParent(File parent) {this.parent = parent; return this;}
	public File(String id, String name, String downloadURL) {this.id = id; this.name = name; this.downloadURL = downloadURL;}
	
}
