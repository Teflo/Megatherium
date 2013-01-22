/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.communicator;

import cloudstorage.communicator.convert.GoogleDriveConverter;
import cloudstorage.communicator.data.GoogleDriveLoginInformation;
import cloudstorage.communicator.data.GoogleDrivePlatformData;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.communicator.data.Account;
import megatherium.data.store.PlatformStore;
import megatherium.data.store.Stores;
import megatherium.event.EventManager;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class GoogleDriveCommunicator extends CloudStorageCommunicator {

	private Drive drive;
	private static Map<String, GoogleDriveCommunicator> instances = new HashMap<String, GoogleDriveCommunicator>();
	private Account account;
	private HttpTransport httpTransport;
	private JsonFactory jsonFactory;
	private GoogleDrivePlatformData data;
	private GoogleAuthorizationCodeFlow flow;

	private GoogleDriveCommunicator() {
		this.initialize();
	}

	private GoogleDriveCommunicator(Account account) {
		this.account = account;
		this.initialize();
	}

	@Override
	protected void initializePlatform() {
		this.platform = Stores.get("platformStore", PlatformStore.class).get("googledrive");
	}

	/**
	 * Returns an unauthorized instance from the communicator.
	 *
	 * @return the communicator
	 */
	public static GoogleDriveCommunicator getInstance() {
		if (!instances.containsKey("0")) {
			instances.put("0", new GoogleDriveCommunicator());
		}

		return instances.get("0");
	}

	/**
	 * Returns the current instance from this communicator.
	 *
	 * @param account the account from the user
	 * @return the communicator
	 */
	public static GoogleDriveCommunicator getInstance(Account account) {
		if (!instances.containsKey(account.getID() + "")) {
			instances.put(account.getID() + "", new GoogleDriveCommunicator(account));
		}

		return instances.get(account.getID() + "");
	}
	
	/**
	 * Returns the list of files in the cloud storage.
	 * 
	 * @return the file list
	 */
	public List<cloudstorage.data.File> getFileList() {
		// call service
		try {
			return GoogleDriveConverter.convert(drive.files().list().execute().getItems());
		}catch(IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
		return new ArrayList<cloudstorage.data.File>();
	}
	
	/**
	 * Creates a new directory inside drive.
	 * 
	 * @param name the name from the directory
	 */
	public void createDirectory(String name) {
		this.initialize();
		
		// the file model
		File file = new File();
		file.setTitle(name);
		file.setMimeType("application/vnd.google-apps.folder");
		try {
			File result = drive.files().insert(file).execute();
			System.out.println("ID: "+result.getId());
			System.out.println("Link: "+result.getAlternateLink());
			System.out.println("Kind: "+result.getKind());
		}catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
	}

	/**
	 * Uploads a file to the gdrive service.
	 *
	 * @param name the name of the file
	 * @param content the file content
	 */
	public void upload(String name, String description, String mimeType, String path) throws IOException {
		ParentReference reference = new ParentReference();
		reference.setFactory(jsonFactory);
		reference.setId("0BwUGoNHvIXyBLWdvM1RlbnJQc1E");
		reference.setKind("drive#file");
		List<ParentReference> parentList = new ArrayList<ParentReference>();
		parentList.add(reference);

		// the file model
		File body = new File();
		body.setTitle(name);
		body.setDescription(description);
		body.setMimeType(mimeType);
		body.setParents(parentList);
		body.getParents().add(reference);
		drive.files().insert(body, new FileContent(mimeType, new java.io.File(path))).execute();
	}

	/**
	 * Returns the authentication url.
	 *
	 * @return the url
	 */
	public String getAuthorizationURL() {
		return flow.newAuthorizationUrl().setRedirectUri(data.getRedirectURL()).build();
	}

	/**
	 * Returns the login information (access and refresh token) by the
	 * authorization code.
	 *
	 * @param code the authorization code
	 * @return the login information
	 */
	public GoogleDriveLoginInformation getLoginInformation(String code) {
		try {
			GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(data.getRedirectURL()).execute();
			GoogleCredential credential = new GoogleCredential.Builder().setJsonFactory(jsonFactory).setTransport(httpTransport).setClientSecrets(data.getClientID(), data.getClientSecret()).build().setFromTokenResponse(response);
			return new GoogleDriveLoginInformation(null, credential.getAccessToken(), credential.getRefreshToken());
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
		return null;
	}

	/**
	 * Initializes the gdrive service.
	 */
	protected void initialize() {
		if (drive != null) {
			return;
		}

		// initialize classes
		httpTransport = new NetHttpTransport();
		jsonFactory = new JacksonFactory();

		// fetch data
		data = this.getPlatform().getData(GoogleDrivePlatformData.class);

		// generate authorization code flow
		flow = new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, jsonFactory, data.getClientID(), data.getClientSecret(), Arrays.asList(DriveScopes.DRIVE))
				.setAccessType("offline")
				.setApprovalPrompt("auto").build();

		// fetch url for getting an authentication code
		String url = flow.newAuthorizationUrl().setRedirectUri(data.getRedirectURL()).build();

		// break if no account was set
		if (account == null) {
			return;
		}

		// receive authentication code from account
		String accessToken = account.getLoginInformation(GoogleDriveLoginInformation.class).getAccessToken();
		String refreshToken = account.getLoginInformation(GoogleDriveLoginInformation.class).getRefreshToken();

		// initialize code
		if (accessToken != null && refreshToken != null) {
			this.setTokens(accessToken, refreshToken);
		} else {
			EventManager.getInstance().fireEvent("storage.drive.authorization.fail", url);
		}
	}

	/**
	 * Initializes the credentials by using the tokens.
	 *
	 * @param accessToken the access token
	 * @param refreshToken the refresh token
	 */
	protected void setTokens(String accessToken, String refreshToken) {
		GoogleCredential credential = new GoogleCredential.Builder().setJsonFactory(jsonFactory).setTransport(httpTransport).setClientSecrets(data.getClientID(), data.getClientSecret()).build();
		credential.setAccessToken(accessToken).setRefreshToken(refreshToken);
		drive = new Drive.Builder(httpTransport, jsonFactory, credential).build();
	}
	
}
