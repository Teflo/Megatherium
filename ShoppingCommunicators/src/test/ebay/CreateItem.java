/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.ebay;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.AddFixedPriceItemCall;
import com.ebay.sdk.helper.ConsoleUtil;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.CountryCodeType;
import com.ebay.soap.eBLBaseComponents.CurrencyCodeType;
import com.ebay.soap.eBLBaseComponents.FeesType;
import com.ebay.soap.eBLBaseComponents.ItemType;
import com.ebay.soap.eBLBaseComponents.ListingDurationCodeType;
import com.ebay.soap.eBLBaseComponents.ListingTypeCodeType;
import com.ebay.soap.eBLBaseComponents.NameValueListArrayType;
import com.ebay.soap.eBLBaseComponents.NameValueListType;
import com.ebay.soap.eBLBaseComponents.SellerPaymentProfileType;
import com.ebay.soap.eBLBaseComponents.SellerProfilesType;
import com.ebay.soap.eBLBaseComponents.SellerReturnProfileType;
import com.ebay.soap.eBLBaseComponents.SellerShippingProfileType;
import com.ebay.soap.eBLBaseComponents.VariationType;
import com.ebay.soap.eBLBaseComponents.VariationsType;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import shopping.communicator.EBayCommunicator;
import shopping.communicator.data.Amount;
import shopping.communicator.data.Product;

/**
 *
 * @author SargTeX
 */
public class CreateItem {
	
	public static void main(String[] args) {
		EBayCommunicator.createItem("Penis!", "This is only a test", "Oberursel", 27432, 10.0);
	}

	public static void main2(String[] args) {
//		Product product = new Product("Test", "Dies ist kein echter Artikel. BITCH!", new Amount(10.0, 1));
		
	}
	
}
