/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.request.surveillance;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti_000
 */
public class RequestSurveillance {
	private static RequestSurveillance instance;
	private List<String> serverSurveillanceList = new ArrayList<String>();
	
	/**
	 * Initializes the request surveillance.
	 */
	private RequestSurveillance() {
		this.initializeEventListing();
	}
	
	/**
	 * Returns the current instance from the request surveillance.
	 * 
	 * @return the current instance
	 */
	public static RequestSurveillance getInstance() {
		if (instance == null) instance = new RequestSurveillance();
		
		return instance;
	}
	
	/**
	 * Initializes the event listing for the request surveillance.
	 */
	protected void initializeEventListing() {
		
	}
	
	/**
	 * Adds a server to the request surveillance.
	 * 
	 * @param server the server's host
	 */
	public void addServer(String server) {
		this.serverSurveillanceList.add(server);
	}
	
	
	
}
