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
public class GoogleDriveLoginInformation extends CloudStorageLoginInformation {
	private String accessToken;
	private String refreshToken;
	public String getAccessToken() {return this.accessToken;}
	public String getRefreshToken() {return this.refreshToken;}
	public GoogleDriveLoginInformation(String localDirectory, String accessToken, String refreshToken) {super(localDirectory); this.accessToken = accessToken; this.refreshToken = refreshToken;}
}
