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
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
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
import megatherium.io.StringOutputStream;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class GoogleDriveCommunicator extends CloudStorageCommunicator {

	private static Account lastUsedAccount;
	private Drive drive;
	private static Map<String, GoogleDriveCommunicator> instances = new HashMap<String, GoogleDriveCommunicator>();
	private Account account;
	private HttpTransport httpTransport;
	private JsonFactory jsonFactory;
	private GoogleDrivePlatformData data;
	private GoogleAuthorizationCodeFlow flow;
	private Map<String, cloudstorage.data.File> fileList = new HashMap<String, cloudstorage.data.File>();

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
		return getInstance(lastUsedAccount);
	}

	/**
	 * Returns the current instance from this communicator.
	 *
	 * @param account the account from the user
	 * @return the communicator
	 */
	public static GoogleDriveCommunicator getInstance(Account account) {
		lastUsedAccount = account;
		if (!instances.containsKey(account.getID() + "")) {
			instances.put(account.getID() + "", new GoogleDriveCommunicator(account));
		}

		return instances.get(account.getID() + "");
	}
	
	/**
	 * Returns the file with that file id.
	 * This method uses the cache what means that if the file's metadata has been received before, it will not be redownloaded.
	 * 
	 * @param id the id of the file
	 * @return the file
	 */
	public cloudstorage.data.File getFile(String id) {
		return getFile(id, true);
	}
	
	/**
	 * Returns the file with that file id.
	 * 
	 * @param id the id of the file
	 * @param cache if true the method uses the memory cache
	 * @return the file
	 */
	public cloudstorage.data.File getFile(String id, boolean cache) {
		// look in cache
		if (cache && fileList.containsKey(id)) return fileList.get(id);
		
		// fetch file
		try {
			cloudstorage.data.File file = GoogleDriveConverter.convert(drive.files().get(id).execute());
			fileList.put(id, file);
		
			// return file
			return file;
		}catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		// return null
		return null;
	}
	
	/**
	 * Returns the list of all files in the cloud storage.
	 * 
	 * @return the file list
	 */
	public List<cloudstorage.data.File> getFileList() {
		return getFileList(-1);
	}
	
	/**
	 * Returns the list of files in the cloud storage.
	 * 
	 * @param maximum the maximum list size or -1 if a complete file list should be received
	 * @return the file list
	 */
	public List<cloudstorage.data.File> getFileList(int maximum) {
		// call service
		try {
			FileList fileList = (maximum != -1) ? drive.files().list().setMaxResults(maximum).execute() : drive.files().list().execute();
			List<cloudstorage.data.File> files = GoogleDriveConverter.convert(fileList.getItems());
			if (maximum != -1) maximum -= files.size();
			
			// get files from other pages
			while (fileList.getNextPageToken() != null) {
				// tell event listener how much files where found
				EventManager.getInstance().fireEvent("storage.data.file.list", account, files);
				if (maximum <= 0 && maximum != -1) break;
				
				// fetch files
				fileList = (maximum != -1) ? drive.files().list().setPageToken(fileList.getNextPageToken()).execute() : drive.files().list().setPageToken(fileList.getNextPageToken()).setMaxResults(maximum).execute();
				
				// put files into cache and list
				for (File file : fileList.getItems()) {
					cloudstorage.data.File file2 = GoogleDriveConverter.convert(file);
					this.fileList.put(file2.getID(), file2);
					files.add(file2);
				}
			}
			
			// return file list
			return files;
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
	 * Downloads a file and returns it's content.
	 * 
	 * @param file the file
	 * @return the file's content
	 */
	public String download(cloudstorage.data.File file) {
		try {
			if (file.getDownloadURL() == null) {
				System.err.println("Datei konnte nicht heruntergeladen werden, da download-URL fehlt: "+file.getID()+" - "+file.getName());
				return null;
			}
			HttpResponse resp = drive.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadURL())).execute();
			StringOutputStream stream = new StringOutputStream();
			resp.download(stream);
			return stream.toString();
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
		return null;
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
