/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import com.google.gson.internal.Primitives;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpParamsNames;
import org.apache.http.util.EntityUtils;
import megatherium.config.Config;
import megatherium.debug.PrintManager;
import megatherium.event.EventManager;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;

/**
 *
 * @author Pathos
 */
public class HttpUtil {

	private static HttpClient client;
	private static boolean executing = false;
	private static ClientConnectionManager connectionManager;
	private static SchemeRegistry schemeRegistry;

	/**
	 * Returns the single instance of the http client.
	 *
	 * @return HttpClient	the only instance
	 */
	public static HttpClient getClient() {
		if (client == null) {
			try {
				// ssl
				SSLContext sslContext = SSLContext.getInstance("SSL");
				X509TrustManager trustManager = new X509TrustManager() {
					@Override
					public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
					}

					@Override
					public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
				};
				sslContext.init(null, new TrustManager[]{trustManager}, null);
				SSLSocketFactory sslSocketFactory = new SSLSocketFactory(sslContext);

				schemeRegistry = new SchemeRegistry();
				schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
				schemeRegistry.register(new Scheme("https", 443, sslSocketFactory));

				connectionManager = new PoolingClientConnectionManager(schemeRegistry);
				client = new DefaultHttpClient(connectionManager);
			} catch (KeyManagementException ex) {
				PrintManager.getInstance().print(ex);
			} catch (NoSuchAlgorithmException ex) {
				PrintManager.getInstance().print(ex);
			}
		}

		return client;
	}

	/**
	 * Returns the response of the request as a string.
	 *
	 * @param HttpResponse	the response
	 * @return String	the response content
	 */
	public static String getResponseContent(HttpResponse response) {
		String content = "";
		BufferedReader rd;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				content += line + "\n";
			}
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
		return content;
	}

	/**
	 * Executes a http post call.
	 *
	 * @param url	the url that will be called
	 * @return the response
	 * @deprecated
	 */
	public static String execute(String uri, String method) {
		return execute(uri, new HashMap<String, String>(), method);
	}
	
	/**
	 * Executes a GET http call.
	 * 
	 * @param url the url that will be called
	 * @param params the parameters
	 * @return the response
	 */
	public static String execute(String url, Map<String, String> params) {
		return execute(url, params, "get");
	}
	
	/**
	 * Executes a http call.
	 * 
	 * @param url	the url that will be called
	 * @param params	the parameters
	 * @param method either "get" or "post"
	 * @return the response
	 */
	public static String execute(String url, Map<String, String> params, String method) {
		return execute(url, params, method, null);
	}

	/**
	 * Executes a http call.
	 *
	 * @param url	the url that will be called
	 * @param params	the parameters
	 * @param method either "get" or "post"
	 * @param body the request body
	 * @return the response
	 */
	public static String execute(String uri, Map<String, String> params, String method, String body) {
		Object[] keys = (params != null) ? params.keySet().toArray() : new Object[0];

		// post requests
		if (method.equals("post")) {
			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			for (int i = 0; i < keys.length; ++i) {
				paramList.add(new BasicNameValuePair((String) keys[i], (String) params.get(keys[i])));
			}

			HttpPost request = new HttpPost(uri);
			try {
				HttpEntity entity;
				HttpEntity paramEntity = new UrlEncodedFormEntity(paramList, "utf-8");
				if (body != null && !body.isEmpty()) {
					entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
					((MultipartEntity)entity).addPart("xml", new StringBody(body));
				} else entity = paramEntity;
				request.setEntity(entity);
				System.out.println("Request body: "+EntityUtils.toString(entity));
			} catch (Exception ex) {
				ReportUtil.getInstance().add(ex);
			}
			return execute(new HttpPost(uri));
		}

		// get requests
		for (int i = 0; i < params.size(); ++i) {
			if (i == 0) {
				uri += "?";
			} else {
				uri += "&";
			}

			try {
				uri += ((String) keys[i]) + "=" + URLEncoder.encode(params.get(keys[i]), "utf-8");
			} catch (UnsupportedEncodingException ex) {
				ReportUtil.getInstance().add(ex);
			}
		}
		return execute(new HttpGet(uri));
	}

	/**
	 * Executes a http get and returns the response as a string.
	 *
	 * @param HttpGet	the get object
	 * @param List<NameValuePair>	the parameters
	 * @return String	the response content or null if an error occurs
	 */
	public static String execute(HttpRequestBase request) {
		try {
			// fake user agent
//			request.setEntity
			if (Config.get("userAgent", String.class).length() > 0) request.setHeader("User-Agent", Config.get("userAgent", String.class));

			// execute call
			System.out.println("Request-URL: "+request.getURI().toString());
			System.out.println("Request-Method: "+request.getMethod());
		//	request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			System.out.println("Request headers -- ");
			for (int i = 0; i < request.getAllHeaders().length; ++i) {
				Header header = request.getAllHeaders()[i];
				System.out.println("\t"+header.getName()+" = "+header.getValue());
			}
			HttpResponse response = getClient().execute(request);
			System.out.println("Response headers -- ");
			for (int i = 0; i < response.getAllHeaders().length; ++i) {
				Header header = response.getAllHeaders()[i];
				System.out.println("\t"+header.getName()+" = "+header.getValue());
			}
			String content = HttpUtil.getResponseContent(response);
	//		System.out.println("Response: "+content);
			
			

			// return content
			return content;
		} catch (UnknownHostException ex) {
			EventManager.getInstance().fireEvent("megatherium.error.request.unknownHost", request.getURI().getHost());
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}

		return null;
	}

//	/**
//	 * Executes a http post call and returns the response as a string.
//	 * 
//	 * @param HttpPost the post object
//	 * @param Map<String, String> the parameters
//	 * @return String the response content or null if an error occurs
//	 */
//	public static String execute(HttpGet request) {
//		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
//		
//		// translate parameters
//		Iterator<String> iterator = params.keySet().iterator();
//		while (iterator.hasNext()) {
//			String name = iterator.next();
//			paramList.add(new BasicNameValuePair(name, params.get(name)));
//		}
//		
//		// execute it and return response
//		return execute(request, paramList);
//	}
	/**
	 * Parses the content to create a map scheme out of it. Content must be
	 * schemed like URL-parameters.
	 *
	 * @param content the content
	 * @return the map
	 */
	public static Map<String, String> getAsMap(String content) {
		Map<String, String> map = new HashMap<String, String>();

		// parse
		String[] parts = content.split("&");
		for (int i = 0; i < parts.length; ++i) {
			String[] pair = content.split("=");
			if (pair.length != 2) {
				continue;
			}
			map.put(pair[0], pair[1]);
		}

		return map;
	}

	/**
	 * Formats the url. That means, appends all the parameters to the url and
	 * encode the parameter values by "utf-8".
	 *
	 * @param url the url
	 * @param parameters the parameters
	 * @return the formatted url
	 */
	public static String formURL(String url, Map<String, String> parameters) {
		// abort if no parameters where given
		if (parameters.size() == 0) {
			return url;
		}

		// fetch keys and add first param
		Object[] keys = parameters.keySet().toArray();
		try {
			url += "?" + keys[0] + "=" + URLEncoder.encode(parameters.get(keys[0]), "utf-8");

			// build url
			for (int i = 1; i < keys.length; ++i) {
				url += "&"+keys[i]+"="+URLEncoder.encode(parameters.get(keys[i]), "utf-8");
			}
		} catch (UnsupportedEncodingException ex) {
			ReportUtil.getInstance().add(ex);
		}
		
		// return url with formatted and encoded parameters
		return url;
	}
}
