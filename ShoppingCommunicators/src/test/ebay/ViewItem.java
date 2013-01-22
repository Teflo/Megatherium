/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.ebay;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetItemCall;
import com.ebay.sdk.helper.ConsoleUtil;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.ListingDetailsType;
import com.ebay.soap.eBLBaseComponents.SellingStatusType;
import com.ebay.soap.eBLBaseComponents.UserType;

/**
 *
 * @author SargTeX
 */
public class ViewItem {
	private static final String SERVER = "https://api.sandbox.ebay.com/wsapi";
	private static final String AUTH_TOKEN = "AgAAAA**AQAAAA**aAAAAA**W9D3UA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCZiGpgqdj6x9nY+seQ**fwwCAA**AAMAAA**nZCZcwomRFnrt9Hgg/ss+ax6HuUyh29rqonbQr8qq9s7ysLAjLOMR2SwobKoT+LDHbK/lgjOVxUqHJ+DKZTQxi2Eo+L9elYmXs9Su2q2qtypu+BI7xWDp5b6c/oUWJXnvngoFHaT5v2uX4Voblh8xmfxo1UP0vAOqyF2Okyxe+EwnskMum1rVhBOUJ+/GDKDd/5PCsnJ9g24CodbLDAiGVGQwCe2ieh7IuuFSUHs052gWTwKFtS65kMAHtpK3MzXzHRSjsNlhTxpCZx6qtDYY1V3yT88CJAigIS6tnOlj7aD10W68e9X2xmoehFJo8Udx/psgdvl/Rvu/5btGs04odwf+BOtU74wx6eyhD1Bs3yIaKHfgZkHWExZQpCa5zVujgINACiO6tNs6vGj2Jxkjp/dPhTMGO8EGivnHOsg5TBdj4hYSdJW+qBHov6CbYWzn3oLW+Cal9WKrE0U9Slz9oXI2dYIi01Kw03JbDDZLn424YmUvI2gouc/qjm6RpTXpJvD9c+p1fMSfJd+4hv6sX714PAguIDPlRr58gItNhU8fXA5c0ZTOaB39mNEPjQXyqr60OELKFp8XH4bXd4W19WgOGSwyyiGRlLWCMklYJGSG4seBglD08agHB8GafrifQ7cABZzzVReenKwxssfmRJmtOxVRZMSsdfkbpboFMF8+w+VnfIIl3Gx+5U8j8V2vd6nmJKbqLH1Ll1YPZ93+Ebk9HKW/XyG+ZeUlYNo/FxQ9sbCtEs0DaGdPiOhEZ1S";

	  //Main method
  public static void main(String[] args) {

    String input;

    try {

      System.out.print("\n");
      System.out.print("+++++++++++++++++++++++++++++++++++++++\n");
      System.out.print("+ Welcome to eBay SDK for Java Sample +\n");
      System.out.print("+  - ConsoleViewItem                  +\n");
      System.out.print("+++++++++++++++++++++++++++++++++++++++\n");
      System.out.print("\n");

      // [1] Create ApiContext object.
      System.out.println("===== [1] Collect Account Information ====");

      ApiContext apiContext = new ApiContext();
      ApiCredential cred = apiContext.getApiCredential();
      cred.seteBayToken(AUTH_TOKEN);
      apiContext.setApiServerUrl(SERVER);

      // [2] Ask for itemID.
      System.out.println("===== [2] Call GetItemCall ====");
      String itemIDStr = ConsoleUtil.readString("Enter ID of the item that you want to get: ");

      GetItemCall gc = new GetItemCall(apiContext);
      DetailLevelCodeType[] detailLevels = new DetailLevelCodeType[] {
          DetailLevelCodeType.RETURN_ALL,
          DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES,
          DetailLevelCodeType.ITEM_RETURN_DESCRIPTION
      };
      gc.setDetailLevel(detailLevels);

      ItemType item = gc.getItem(itemIDStr);

      // [3] Display result.
      System.out.println("===== [3] Display Returned Item Information ====");

      print("ItemType", item.getListingType().toString());
      print("Title: ",  item.getTitle());
      print("StartPrice", item.getStartPrice().getValue());
      print("Quantity", item.getQuantity().toString());
      print("PrimaryCategory", item.getPrimaryCategory().getCategoryID());
      SellingStatusType sst = item.getSellingStatus();
      print("CurrentPrice", sst.getCurrentPrice().getValue());
      print("BidCount", sst.getBidCount().toString());
      print("QuantitySold", sst.getQuantitySold() == null ? "" : sst.getQuantitySold().toString());

      ListingDetailsType ldt = item.getListingDetails();
      UserType hb = sst.getHighBidder();
      if( hb != null )
        print("HighBidder", hb.getUserID());

      print("StartTime", eBayUtil.toAPITimeString(ldt.getStartTime().getTime()));
      print("EndTime", eBayUtil.toAPITimeString(ldt.getEndTime().getTime()));
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  static void print(String name, String value)
  {
    System.out.println(name + ": " + value);
  }

  static void print(String name, double value)
  {
    System.out.println(name + ": " + new Double(value).toString());
  }

  static void print(String name, int value)
  {
    System.out.println(name + ": " + new Integer(value).toString());
  }
	
}
