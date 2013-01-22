/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import sargtex.util.HttpUtil;

/**
 *
 * @author Pathos
 */
public class MultiThreadTest {
	private static HttpClient client;

	public static void main(String[] args) throws InterruptedException {
		MultiThreadTest test = new MultiThreadTest();
	}
	
	public static HttpClient getClient() {
		return client;
	}

	public MultiThreadTest() throws InterruptedException {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

		ClientConnectionManager connectionManager = new PoolingClientConnectionManager(schemeRegistry);
		client = new DefaultHttpClient(connectionManager);

		String[] uris = {"http://www.google.de", "http://www.lordsandknights.com", "http://spinshare.de", "http://spinshare.de/index.php?page=Board&boardID=1"};

		GetThread[] threads = new GetThread[uris.length];
		for (int i = 0; i < threads.length; ++i) {
			threads[i] = new GetThread(client, new HttpGet(uris[i]));
		}
		// start threads
		for (int i = 0; i < threads.length; ++i) {
			threads[i].start();
		}

		// join threads
		for (int i = 0; i < threads.length; ++i) {
	//		threads[i].join();
		}
	}

	class GetThread extends Thread {
		private HttpClient client;
		private HttpGet request;

		public GetThread(HttpClient client, HttpGet request) {
			this.client = HttpUtil.getClient();
			this.request = request;
		}

		@Override
		public void run() {
			try {
				HttpResponse response = client.execute(request);
		//		HttpEntity entity = response.getEntity();
				System.out.println("Fetched entity from " + request.getURI());
				//		EntityUtils.consume(entity);
			} catch (Exception e) {
			//	request.abort();
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
