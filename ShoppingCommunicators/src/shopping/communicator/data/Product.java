/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping.communicator.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import megatherium.communicator.data.Platform;

/**
 *
 * @author SargTeX
 */
public class Product {
	private String title;
	private String description;
	private Amount price;
	private List<ProductCategory> categoryList = new ArrayList<ProductCategory>();
	private List<ShippingInformation> shippingInformationList = new ArrayList<ShippingInformation>();
	public Map<String, String> listingDurationMap = new HashMap<String, String>();
	private String location;
	private ReturnPolicy returnPolicy;
	public String getTitle() {return this.title;}
	public String getDescription() {return this.description;}
	public Amount getPrice() {return this.price;}
	public String getListingDuration(Platform platform) {return this.listingDurationMap.get(platform.getName());}
	public ReturnPolicy getReturnPolicy() {return this.returnPolicy;}
	public String getLocation() {return this.location;}
	
	public Product(String title, String description, Amount price) {
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Adds a new product category to this product.
	 * 
	 * @param category the product category
	 * @return the class instance itself for faster programming
	 */
	public Product addCategory(ProductCategory category) {
		this.categoryList.add(category);
		return this;
	}
	
	/**
	 * Returns all product categories.
	 * 
	 * @return the product categories
	 */
	public ProductCategory[] getCategories() {
		return this.categoryList.toArray(new ProductCategory[]{});
	}
	
	/**
	 * Returns all shipping information.
	 * 
	 * @return the shipping information
	 */
	public ShippingInformation[] getShippingInformation() {
		return this.shippingInformationList.toArray(new ShippingInformation[]{});
	}
	
}
