/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import megatherium.util.FileUtil;

/**
 *
 * @author SargTeX
 */
public class HelloWorld {

	private static String CLIENT_ID = "237596419011-9dfc6kf60his7pjce8vnrfq58k666h5h.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "ECaqLbmZxNO_2UHSG_-QAu-L";
	private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	private static String accessToken = "ya29.AHES6ZQy1F0jZ_RGy1T2Zv1qHy9Zq7iF_gemjMH_PAPDPtPAXUp_UQ";
	private static String refreshToken = "1/fnuQBWb0ehSOGq1NhksEK1jFgyWZyzL4QTTA9WpdBaw";

	public static void main(String[] args) throws IOException {
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
				.setAccessType("offline")
				.setApprovalPrompt("force").build();

		GoogleCredential credential = null;
		if (accessToken == null || refreshToken == null) {
			String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
			System.out.println("Please open the following URL in your browser then type the authorization code:");
			System.out.println("  " + url);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String code = br.readLine();
			GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
			credential = new GoogleCredential.Builder().setJsonFactory(jsonFactory).setTransport(httpTransport).setClientSecrets(CLIENT_ID, CLIENT_SECRET).build().setFromTokenResponse(response);
		} else {
			credential = new GoogleCredential.Builder().setJsonFactory(jsonFactory).setTransport(httpTransport).setClientSecrets(CLIENT_ID, CLIENT_SECRET).build();
			credential.setAccessToken(accessToken).setRefreshToken(refreshToken);
		}
//		String code = "ya29.AHES6ZQsbrR0jh8VhO0B8yAHDG3PPxatmaSiiyuieuexa5U";

		System.out.println("Access token: " + credential.getAccessToken());
		System.out.println("Refresh token: " + credential.getRefreshToken());
//		System.out.println("Expires in: " + (credential.getExpiresInSeconds() / 60) + ":" + (credential.getExpiresInSeconds() % 60) + " Minutes");

		//Create a new authorized API client

		Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();

		//Insert a file  
		File body = new File();
		body.setTitle("My document");
		body.setDescription("A test document");
		body.setMimeType("text/plain");

		java.io.File fileContent = new java.io.File(FileUtil.getDataPath() + "config.data");
		FileContent mediaContent = new FileContent("text/plain", fileContent);

		File file = service.files().insert(body, mediaContent).execute();
		System.out.println("File ID: " + file.getId());
	}
}
