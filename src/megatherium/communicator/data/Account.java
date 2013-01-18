/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data;

import com.google.gson.internal.Primitives;
import megatherium.util.JsonUtil;

/**
 *
 * @author marti_000
 */
public class Account {
	private int id;
	private int userID;
	private String platformName;
	private String alias;
	private String loginInformation;
	public int getID() {return this.id;}
	public int getUserID() {return this.userID;}
	public String getPlatformName() {return this.platformName;}
	public String getAlias() {return this.alias;}
	public String getLoginInformation() {return this.loginInformation;}
	public String toString() {return this.getAlias();}
	
	/**
	 * Returns the login information parsed from JSON to the specified class.
	 * 
	 * @param classType the class type
	 * @return the object
	 */
	public <T> T getLoginInformation(Class<T> classType) {
		return Primitives.wrap(classType).cast(JsonUtil.getGson().fromJson(this.getLoginInformation(), classType));
	}
	
}
