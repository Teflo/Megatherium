/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.communicator;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.AddFixedPriceItemCall;
import com.ebay.sdk.call.AddItemCall;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.BuyerPaymentMethodCodeType;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.CountryCodeType;
import com.ebay.soap.eBLBaseComponents.CurrencyCodeType;
import com.ebay.soap.eBLBaseComponents.FeeType;
import com.ebay.soap.eBLBaseComponents.FeesType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.ListingDurationCodeType;
import com.ebay.soap.eBLBaseComponents.ListingTypeCodeType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.ReturnPolicyType;
import com.ebay.soap.eBLBaseComponents.ReturnsAcceptedOptionsCodeType;
import com.ebay.soap.eBLBaseComponents.ShippingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShippingRateTypeCodeType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceCodeType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.ShippingTypeCodeType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class EBayCommunicator implements IShoppingCommunicator {
	private static final String SERVER = "https://api.sandbox.ebay.com/wsapi";
	private static final String AUTH_TOKEN = "AgAAAA**AQAAAA**aAAAAA**W9D3UA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCZiGpgqdj6x9nY+seQ**fwwCAA**AAMAAA**nZCZcwomRFnrt9Hgg/ss+ax6HuUyh29rqonbQr8qq9s7ysLAjLOMR2SwobKoT+LDHbK/lgjOVxUqHJ+DKZTQxi2Eo+L9elYmXs9Su2q2qtypu+BI7xWDp5b6c/oUWJXnvngoFHaT5v2uX4Voblh8xmfxo1UP0vAOqyF2Okyxe+EwnskMum1rVhBOUJ+/GDKDd/5PCsnJ9g24CodbLDAiGVGQwCe2ieh7IuuFSUHs052gWTwKFtS65kMAHtpK3MzXzHRSjsNlhTxpCZx6qtDYY1V3yT88CJAigIS6tnOlj7aD10W68e9X2xmoehFJo8Udx/psgdvl/Rvu/5btGs04odwf+BOtU74wx6eyhD1Bs3yIaKHfgZkHWExZQpCa5zVujgINACiO6tNs6vGj2Jxkjp/dPhTMGO8EGivnHOsg5TBdj4hYSdJW+qBHov6CbYWzn3oLW+Cal9WKrE0U9Slz9oXI2dYIi01Kw03JbDDZLn424YmUvI2gouc/qjm6RpTXpJvD9c+p1fMSfJd+4hv6sX714PAguIDPlRr58gItNhU8fXA5c0ZTOaB39mNEPjQXyqr60OELKFp8XH4bXd4W19WgOGSwyyiGRlLWCMklYJGSG4seBglD08agHB8GafrifQ7cABZzzVReenKwxssfmRJmtOxVRZMSsdfkbpboFMF8+w+VnfIIl3Gx+5U8j8V2vd6nmJKbqLH1Ll1YPZ93+Ebk9HKW/XyG+ZeUlYNo/FxQ9sbCtEs0DaGdPiOhEZ1S";
	private static EBayCommunicator instance;
	
	/**
	 * Returns the instance of the ebay communicator.
	 * 
	 * @return the current instance
	 */
	public static EBayCommunicator getInstance() {
		if (instance == null) instance = new EBayCommunicator();
		return instance;
	}
	
	@Override
	public void getOrderList() {
		GetOrdersCall call = new GetOrdersCall(getAPIContext());
		call.setSite(SiteCodeType.GERMANY);
		call.setCreateTimeFrom(new GregorianCalendar(2012, 12, 12));
		try {
			OrderType[] orders = call.getOrders();
			for (OrderType order : orders) System.out.println("Bought by: "+order.getBuyerUserID());
		} catch (Exception ex) {
			ReportUtil.getInstance().add(ex);
		}
		
	}

	public static void createItem(String title, String description, String location, int categoryID, double price) {
		try {
			AddFixedPriceItemCall call = new AddFixedPriceItemCall(getAPIContext());
			call.setSite(SiteCodeType.GERMANY);

			ShippingDetailsType sd = new ShippingDetailsType();

			ShippingServiceOptionsType st1 = new ShippingServiceOptionsType();
			System.out.println(ShippingServiceCodeType.DE_DHL_PACKCHEN.value());
			st1.setShippingService(ShippingServiceCodeType.DE_DHL_PACKCHEN.value());
	//		st1.setShippingService("UK_SellersStandardRate");

			AmountType shipAmt1 = new AmountType();
			shipAmt1.setValue(4.0);

			st1.setShippingServiceCost(shipAmt1);

			AmountType shipAmt1Addnl = new AmountType();
			shipAmt1Addnl.setValue(2.0);

			st1.setShippingServiceAdditionalCost(shipAmt1Addnl);

//			ShippingServiceOptionsType st2 = new ShippingServiceOptionsType();
//			st2.setShippingService("UK_RoyalMailFirstClassStandard");
//
//			AmountType shipAmt2 = new AmountType();
//			shipAmt2.setValue(2.0);
//
//			st2.setShippingServiceCost(shipAmt2);
//
//			AmountType shipAmt2Addnl = new AmountType();
//			shipAmt2Addnl.setValue(2.0);
//
//			st2.setShippingServiceAdditionalCost(shipAmt2Addnl);
			sd.setShippingServiceOptions(new ShippingServiceOptionsType[]{st1});

			ReturnPolicyType returnPolicy = new ReturnPolicyType();
			returnPolicy.setDescription("");
			returnPolicy.setReturnsAcceptedOption(ReturnsAcceptedOptionsCodeType.RETURNS_NOT_ACCEPTED.value());
			
			ItemType item = new ItemType();
			item.setPaymentMethods(new BuyerPaymentMethodCodeType[]{BuyerPaymentMethodCodeType.PAY_PAL});
			item.setPayPalEmailAddress("martin.christian.bories@gmail.com");
			item.setShippingDetails(sd);
			item.setReturnPolicy(returnPolicy);
			item.setTitle(title);
			item.setDescription(description);
			item.setListingType(ListingTypeCodeType.FIXED_PRICE_ITEM);
			item.setCurrency(CurrencyCodeType.EUR);
			item.setListingDuration(ListingDurationCodeType.DAYS_3.value());
			item.setLocation(location);
			item.setCountry(CountryCodeType.DE);
			item.setDispatchTimeMax(3);
			CategoryType category = new CategoryType();
			category.setCategoryID(categoryID + "");
			item.setPrimaryCategory(category);
			item.setConditionID(1000);
			AmountType priceObj = new AmountType();
			priceObj.setCurrencyID(CurrencyCodeType.EUR);
			priceObj.setValue(price);
			item.setStartPrice(priceObj);
			call.setItem(item);
			FeesType fees = call.addFixedPriceItem();
			FeeType fee = eBayUtil.findFeeByName(fees.getFee(), "ListingFee");
			System.out.println("Item ID: " + item.getItemID());
		} catch (Exception ex) {
			ReportUtil.getInstance().add(ex);
		}
	}

	private static ApiContext getAPIContext() {
		ApiContext context = new ApiContext();
		ApiCredential credentials = new ApiCredential();
		credentials.seteBayToken(AUTH_TOKEN);
		context.setApiCredential(credentials);
		context.setApiServerUrl(SERVER);
		return context;
	}
}
