/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet.lordsandknights;

import java.sql.SQLException;
import megatherium.api.data.Session;
import megatherium.api.servlet.DefaultServlet;
import megatherium.api.database.lordsandknights.Attack;

/**
 *
 * @author marti_000
 */
public class AttackListServlet extends DefaultServlet {
	private int accountID = 0;
	private Attack[] attacks;
	
	@Override
	public void readParameters() {
		if (hasParam("accountID")) this.accountID = Integer.parseInt(getParam("accountID"));
	}
	
	@Override
	public String validate() throws SQLException {
		if (Session.getInstance().getUser() == null) return "noaccess";
		if (this.accountID > 0 && !Session.getInstance().getUser().hasAccount(this.accountID)) return "noaccess";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		if (this.accountID > 0) this.attacks = Attack.getByAccountID(this.accountID);
		else this.attacks = Attack.getAll();
		
		// respond
		respond(true, "attackList", this.attacks);
	}
	
}
