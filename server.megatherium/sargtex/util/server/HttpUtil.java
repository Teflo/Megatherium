/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.util.server;

import megatherium.util.ReportUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pathos
 */
public class HttpUtil {
	private static HttpUtil instance;
	
	private HttpUtil() {}
	public static HttpUtil getInstance() {
		if (instance == null) instance = new HttpUtil();
		return instance;
	}
	
	public String execute(String url) {
		try {
			URL url2 = new URL(url);
			URLConnection urlConnection = url2.openConnection();
			ReportUtil.getInstance().add(urlConnection.getHeaderFields());
			InputStream result = urlConnection.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(result));
			String line = null;
			String response = "";
			while ((line = reader.readLine()) != null) {
				response += line+"\n";
			}
			reader.close();
			return response;
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
		return "";
	}
	
}
