/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.database.lordsandknights;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.Account;
import megatherium.api.data.DatabaseObject;
import megatherium.api.data.Session;
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class Attack extends DatabaseObject {
    
    public Attack() {super();}
    public Attack(int id) throws SQLException {super(id);}
    public Attack(Map<String, String> data) {super(data);}
    public Attack(ResultSet rs) throws SQLException {super(rs);}
    public int getID() {return Integer.parseInt(this.get("id"));}
    public int getAccountID() {return Integer.parseInt(this.get("accountID"));}
    public int getStartHabitatID() {return Integer.parseInt(this.get("startHabitatID"));}
    public int getTargetHabitatID() {return Integer.parseInt(this.get("targetHabitatID"));}
	public int getTime() {return Integer.parseInt(this.get("time"));}
	public boolean hasExecuted() {return (this.get("executed").equals("1"));}
	public Attack setAccountID(int accountID) {this.set("accountID", accountID+""); return this;}
	public Attack setStartHabitatID(int startHabitatID) {this.set("startHabitatID", startHabitatID+""); return this;}
	public Attack setTargetHabitatID(int targetHabitatID) {this.set("targetHabitatID", targetHabitatID+""); return this;}
	public Attack setTime(int time) {this.set("time", time+""); return this;}
	public Attack setExecuted(boolean executed) {this.set("executed", (executed) ? "1" : "0"); return this;}
	public Attack setUnits(AttackUnit[] units) {this.setLinkedResource("units", units); return this;}
	public Attack setResources(AttackResource[] resources) {this.setLinkedResource("resources", Arrays.copyOf(resources, resources.length, DatabaseObject[].class)); return this;}

    @Override
    protected String[] getFields() {
		return new String[] {"id", "accountID", "attackHabitatID", "targetHabitatID", "time", "executed"};
    }

	@Override
	protected String getTableName() {
		return "lak_attack";
	}
	
	@Override
	protected void initializeLinkings() {
		this.addLinking("resources", new AttackResource());
		this.addLinking("units", new AttackUnit());
	}
	
	@Override
	protected String getForeignKeyName() {
		return "attackID";
	}
    
    /**
	 * Fetches all attacks from the account id.
	 * 
	 * @param accountID the id of the account.
	 * @return the attacks
	 */
	public static Attack[] getByAccountID(int accountID) throws SQLException {
		List<Attack> attackList = new ArrayList<Attack>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM lak_attack WHERE accountID = ?", new String[] {accountID+""});
		while (rs.next()) attackList.add(new Attack(rs));
		return attackList.toArray(new Attack[]{});
	}
	
	/**
	 * Returns all attacks that derive from the user.
	 * 
	 * @return the attacks
	 */
	public static Attack[] getAll() throws SQLException {
		List<Attack> attackList = new ArrayList<Attack>();
		Account[] accounts = Session.getInstance().getUser().getAccounts();
		for (Account account : accounts) {
			for (Attack attack : getByAccountID(account.getID())) {
				attack.loadLinkings();
				attackList.add(attack);
			}
		}
		
		// return attack list
		return attackList.toArray(new Attack[]{});
	}
    
}
