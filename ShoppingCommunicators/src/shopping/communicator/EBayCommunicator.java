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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.communicator.Communicator;
import megatherium.util.ReportUtil;
import shopping.communicator.convert.EBayConverter;
import shopping.communicator.data.Product;
import shopping.communicator.data.ProductCategory;
import shopping.communicator.data.ShippingInformation;

/**
 *
 * @author SargTeX
 */
public class EBayCommunicator extends Communicator implements IShoppingCommunicator {
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
		call.setCreateTimeTo(Calendar.getInstance());
		try {
			OrderType[] orders = call.getOrders();
			for (OrderType order : orders) {
				System.out.println("Bought by: "+order.getBuyerUserID());
				System.out.println("Price: "+order.getAmountPaid().getValue()+" "+order.getAmountPaid().getCurrencyID().value());
				System.out.println("Order ID: "+order.getOrderID());
				System.out.println("Order line item: "+order.getTransactionArray().getTransaction(0).getOrderLineItemID());
			}
		} catch (Exception ex) {
			ReportUtil.getInstance().add(ex);
		}
		
	}

	public void createItem(Product product) {
		try {
			AddFixedPriceItemCall call = new AddFixedPriceItemCall(getAPIContext());
			call.setSite(SiteCodeType.GERMANY);

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
			
			ItemType item = new ItemType();
			item.setReturnPolicy(EBayConverter.convert(product.getReturnPolicy()));
			item.setTitle(product.getTitle());
			item.setDescription(product.getDescription());
			item.setListingType(ListingTypeCodeType.FIXED_PRICE_ITEM);
			
			// add categories
			int count = 0;
			for (ProductCategory category : product.getCategories()) {
				if (count > 1) break;
				
				// fetch category
				CategoryType cat = EBayConverter.convert(category);
				if (cat == null) continue;
				
				// set category
				if (count == 0) item.setPrimaryCategory(cat);
				else item.setSecondaryCategory(cat);
				++count;
			}
			
			// add shipping information
			ShippingDetailsType sd = new ShippingDetailsType();
			List<ShippingServiceOptionsType> shippingOptionsList = new ArrayList<ShippingServiceOptionsType>();
			
			// add shipping information
			for (ShippingInformation shipping : product.getShippingInformation()) shippingOptionsList.add(EBayConverter.convert(shipping));
			sd.setShippingServiceOptions(shippingOptionsList.toArray(new ShippingServiceOptionsType[]{}));
			item.setShippingDetails(sd);
			
			// set price
			item.setStartPrice(EBayConverter.convert(product.getPrice()));
			
			// set listing duration
			item.setListingDuration(product.getListingDuration(this.getPlatform()));
			
			// set location
			item.setLocation(product.getLocation());
			
			// set some other things
			item.setPaymentMethods(new BuyerPaymentMethodCodeType[]{BuyerPaymentMethodCodeType.PAY_PAL});
			item.setPayPalEmailAddress("martin.christian.bories@gmail.com");
			item.setCurrency(CurrencyCodeType.EUR); // TODO why? I already set a price and shipping currency! BITCH!
			item.setCountry(CountryCodeType.DE);
			item.setDispatchTimeMax(3);
			item.setConditionID(1000);
			
			// set item and execute call
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

	@Override
	//TODO find a correct implementation for this. Maybe something like Platform.get("myPlatformName")?
	protected void initializePlatform() {
		this.platform = new megatherium.communicator.data.Platform();
	}
}
