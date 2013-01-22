/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.communicator.convert;

import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.BuyerPaymentMethodCodeType;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.CurrencyCodeType;
import com.ebay.soap.eBLBaseComponents.ReturnPolicyType;
import com.ebay.soap.eBLBaseComponents.ReturnsAcceptedOptionsCodeType;
import com.ebay.soap.eBLBaseComponents.ShippingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import megatherium.communicator.data.Platform;
import shopping.communicator.EBayCommunicator;
import shopping.communicator.data.Amount;
import shopping.communicator.data.PaymentMethod;
import shopping.communicator.data.ProductCategory;
import shopping.communicator.data.ReturnPolicy;
import shopping.communicator.data.ShippingInformation;

/**
 *
 * @author SargTeX
 */
public class EBayConverter {

	/**
	 * Converts the amount to an ebay amount object.
	 */
	public static AmountType convert(Amount amount) {
		AmountType type = new AmountType();
		type.setCurrencyID(CurrencyCodeType.fromValue(amount.getCurrency().getID(getPlatform())));
		type.setValue(amount.getPrice());
		return type;
	}

	/**
	 * Converts a product category into a category type object from ebay.
	 *
	 * @param category the product category object
	 * @return the category type object or null if such a category id doesn't exist for ebay
	 */
	public static CategoryType convert(ProductCategory category) {
		String id = category.getID(getPlatform());
		if (id == null || id.isEmpty()) return null;

		// add category to ebay item
		CategoryType cat = new CategoryType();
		cat.setCategoryID(id);
		return cat;
	}
	
	/**
	 * Converts a shipping information to the shipping details object by ebay.
	 * 
	 * @param shippingInformation the shipping information
	 * @return the ebay shipping details
	 */
	public static ShippingServiceOptionsType convert(ShippingInformation shippingInformation) {
		ShippingServiceOptionsType type = new ShippingServiceOptionsType();
		type.setShippingService(shippingInformation.getID(getPlatform()));
		type.setShippingServiceCost(EBayConverter.convert(shippingInformation.getCost()));
//		st1.setShippingServiceAdditionalCost(shipAmt1Addnl);	// TODO why should I add additional costs?!
		return type;
	}
	
	/**
	 * Converts the return policy to an ebay return policy type object.
	 * 
	 * @param returnPolicy the return policy
	 * @return the ebay return policy object
	 */
	public static ReturnPolicyType convert(ReturnPolicy returnPolicy) {
		ReturnPolicyType policy = new ReturnPolicyType();
		policy.setDescription(returnPolicy.getDescription());
		policy.setReturnsAcceptedOption((returnPolicy.isReturnAllowed()) ? ReturnsAcceptedOptionsCodeType.RETURNS_ACCEPTED.value() : ReturnsAcceptedOptionsCodeType.RETURNS_NOT_ACCEPTED.value());
		return policy;
	}
	
	/**
	 * Converts the payment method.
	 * 
	 * @param paymentMethod the payment method
	 * @return the ebay payment method type
	 */
	public static BuyerPaymentMethodCodeType convert(PaymentMethod paymentMethod) {
		switch(paymentMethod.getName()) {
			case "paypal":
				return BuyerPaymentMethodCodeType.PAY_PAL;
		}
		
		// return null - payment method not found
		return null;
	}

	/**
	 * Returns the platform.
	 *
	 * @return the platform
	 */
	protected static Platform getPlatform() {
		return EBayCommunicator.getInstance().getPlatform();
	}
}
