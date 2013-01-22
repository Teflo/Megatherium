/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui;

import java.text.SimpleDateFormat;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import megatherium.communicator.data.Account;
import megatherium.communicator.data.lordsandknights.Attack;
import megatherium.data.store.AccountStore;
import megatherium.data.store.PlatformStore;
import megatherium.data.store.Stores;

/**
 *
 * @author marti_000
 */
public class AccountTableModel extends DefaultTableModel {
	private Account[] accounts;
	private final String[] columns = new String[] {"Plattform", "Alias"};
	
	/**
	 * Sets the account list.
	 * 
	 * @param accounts the accounts
	 */
	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}

	@Override
	public int getRowCount() {
		if (this.accounts == null) return 0;
		return this.accounts.length;
	}

	@Override
	public int getColumnCount() {
		return this.columns.length;
	}

	@Override
	public String getColumnName(int i) {
		return this.columns[i];
	}

	@Override
	public Class<?> getColumnClass(int i) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowNumber, int columnNumber) {
		switch(columnNumber) {
			// alias can be edited
			case 1:
				return true;
		}
		return false;
	}

	@Override
	public Object getValueAt(int rowNumber, int columnNumber) {
		if (rowNumber > (this.getRowCount() - 1)) return null;
		
		// get account
		Account account = this.accounts[rowNumber];
		
		// return value
		switch(columnNumber) {
			case 0:
				PlatformStore platformStore = (PlatformStore) Stores.getInstance().getStore("platformStore");
				return platformStore.get(account.getPlatformName()).getLabel();
			case 1:
				return account.getAlias();
		}
		return null;
	}
	
}
