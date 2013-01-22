/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.amazonaws.mws.MarketplaceWebServiceClient;

/**
 *
 * @author marti_000
 */
public class Test {
	
	public static void main(String[] args) {
		String accessKeyID = "";
		String secretAccessKey = "";
		String applicationName = "";
		String applicationVersion = "";
		MarketplaceWebServiceClient client = new MarketplaceWebServiceClient(accessKeyID, secretAccessKey, applicationName, applicationVersion);
			
	}
	
}
