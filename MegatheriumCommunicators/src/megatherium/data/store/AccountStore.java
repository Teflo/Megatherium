/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.store;

import java.util.ArrayList;
import java.util.List;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.communicator.data.Account;

/**
 *
 * @author marti_000
 */
public class AccountStore extends Store<Account> {
	
	@Override
	public String getName() {
		return "accountStore";
	}
	
	/**
	 * Returns all accounts from the specified platform.
	 * 
	 * @param platformName the name of the platform
	 * @return the account list
	 */
	public Account[] getItems(String platformName) {
		List<Account> accountList = new ArrayList<Account>();
		for (Account account : this.getItems()) {
			if (account.getPlatformName().equals(platformName)) accountList.add(account);
		}
		return accountList.toArray(new Account[]{});
	}
	
	@Override
	public void load() {
		this.add(MegatheriumCommunicator.getInstance().getAccountList());
	}
	
}
