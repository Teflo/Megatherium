/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.communicator.data;

import megatherium.communicator.data.ILoginInformation;

/**
 *
 * @author SargTeX
 */
public class CloudStorageLoginInformation extends ILoginInformation {
	private String localDirectory;
	public String getLocalDirectory() {return this.localDirectory;}
	public CloudStorageLoginInformation(String localDirectory) {this.localDirectory = localDirectory;}
	public CloudStorageLoginInformation setLocalDirectory(String localDirectory) {this.localDirectory = localDirectory; return this;}
	
}
