/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet.lordsandknights;

import java.sql.SQLException;
import java.util.List;
import megatherium.api.data.Session;
import megatherium.api.database.lordsandknights.Habitat;
import megatherium.api.servlet.DefaultServlet;
import megatherium.communicator.LordsAndKnightsMegatheriumCommunicator;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class HabitatListServlet extends DefaultServlet {
	private int accountID;
	
	@Override
	public void readParameters() {
		if (hasParam("accountID")) this.accountID = Integer.parseInt(getParam("accountID"));
	}
	
	@Override
	public String validate() throws SQLException {
		if (Session.getInstance().getUser() == null || !Session.getInstance().getUser().hasAccount(this.accountID)) return "noaccess";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		respond(true, "habitatlist", Habitat.getAll(this.accountID));
	}
	
}
