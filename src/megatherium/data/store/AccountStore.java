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
	
	@Override
	public void load() {
		this.add(MegatheriumCommunicator.getInstance().getAccountList());
	}
	
}
