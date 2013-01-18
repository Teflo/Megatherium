/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.ebay;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.GeteBayOfficialTimeCall;
import com.ebay.sdk.helper.ConsoleUtil;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author SargTeX
 */
public class HelloWorld {
	private static final String SERVER = "https://api.sandbox.ebay.com/wsapi";
	private static final String AUTH_TOKEN = "AgAAAA**AQAAAA**aAAAAA**W9D3UA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCZiGpgqdj6x9nY+seQ**fwwCAA**AAMAAA**nZCZcwomRFnrt9Hgg/ss+ax6HuUyh29rqonbQr8qq9s7ysLAjLOMR2SwobKoT+LDHbK/lgjOVxUqHJ+DKZTQxi2Eo+L9elYmXs9Su2q2qtypu+BI7xWDp5b6c/oUWJXnvngoFHaT5v2uX4Voblh8xmfxo1UP0vAOqyF2Okyxe+EwnskMum1rVhBOUJ+/GDKDd/5PCsnJ9g24CodbLDAiGVGQwCe2ieh7IuuFSUHs052gWTwKFtS65kMAHtpK3MzXzHRSjsNlhTxpCZx6qtDYY1V3yT88CJAigIS6tnOlj7aD10W68e9X2xmoehFJo8Udx/psgdvl/Rvu/5btGs04odwf+BOtU74wx6eyhD1Bs3yIaKHfgZkHWExZQpCa5zVujgINACiO6tNs6vGj2Jxkjp/dPhTMGO8EGivnHOsg5TBdj4hYSdJW+qBHov6CbYWzn3oLW+Cal9WKrE0U9Slz9oXI2dYIi01Kw03JbDDZLn424YmUvI2gouc/qjm6RpTXpJvD9c+p1fMSfJd+4hv6sX714PAguIDPlRr58gItNhU8fXA5c0ZTOaB39mNEPjQXyqr60OELKFp8XH4bXd4W19WgOGSwyyiGRlLWCMklYJGSG4seBglD08agHB8GafrifQ7cABZzzVReenKwxssfmRJmtOxVRZMSsdfkbpboFMF8+w+VnfIIl3Gx+5U8j8V2vd6nmJKbqLH1Ll1YPZ93+Ebk9HKW/XyG+ZeUlYNo/FxQ9sbCtEs0DaGdPiOhEZ1S";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, ApiException, SdkException, Exception {
		System.out.print("\n");
		System.out.print("+++++++++++++++++++++++++++++++++++++++\n");
		System.out.print("+ Welcome to eBay SDK for Java Sample +\n");
		System.out.print("+  - HelloWorld                   +\n");
		System.out.print("+++++++++++++++++++++++++++++++++++++++\n");
		System.out.print("\n");

		// [Step 1] Initialize eBay ApiContext object
		System.out.println("===== [1] Account Information ====");
		ApiContext apiContext = getApiContext();

		// [Step 2] Create call object and execute the call
		GeteBayOfficialTimeCall apiCall = new GeteBayOfficialTimeCall(apiContext);
		System.out.println("Begin to call eBay API, please wait ... ");
		Calendar cal = apiCall.geteBayOfficialTime();
		System.out.println("End to call eBay API, show call result ...");

		// [Setp 3] Handle the result returned
		System.out.println("Official eBay Time : " + cal.getTime().toString());

	}

	/**
	 * Populate eBay SDK ApiContext object with data input from user
	 *
	 * @return ApiContext object
	 */
	private static ApiContext getApiContext() throws IOException {

		String input;
		ApiContext apiContext = new ApiContext();

		//set Api Token to access eBay Api Server
		ApiCredential cred = apiContext.getApiCredential();
		cred.seteBayToken(AUTH_TOKEN);

		//set Api Server Url
		apiContext.setApiServerUrl(SERVER);

		return apiContext;
	}
}
