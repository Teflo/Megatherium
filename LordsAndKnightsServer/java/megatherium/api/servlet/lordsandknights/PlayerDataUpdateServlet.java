/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet.lordsandknights;

import java.sql.SQLException;
import megatherium.api.data.Session;
import megatherium.api.database.lordsandknights.Habitat;
import megatherium.api.database.lordsandknights.HabitatResource;
import megatherium.api.database.lordsandknights.HabitatUnit;
import megatherium.api.servlet.DefaultServlet;
import megatherium.data.lordsandknights.Player;
import megatherium.util.JsonUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class PlayerDataUpdateServlet extends DefaultServlet {
	private Player player;
	private int accountID;
	
	@Override
	public void readParameters() {
		if (hasParam("accountID")) this.accountID = Integer.parseInt(getParam("accountID"));
		if (hasParam("player")) this.player = JsonUtil.getGson().fromJson(getParam("player"), Player.class);
	}
	
	@Override
	public String validate() throws SQLException {
		if (Session.getInstance().getUser() == null || !Session.getInstance().getUser().hasAccount(this.accountID)) return "noaccess";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		// loop through habitats
		for (megatherium.data.lordsandknights.Habitat playerHabitat : this.player.getHabitatList()) {
			// update habitat
			Habitat habitat = new Habitat(playerHabitat.getID());
			habitat.setAccountID(this.accountID).setName(playerHabitat.getName());
			if (!habitat.exists()) habitat.create();
			else habitat.update();
			
			// update resources
			for (String id : playerHabitat.getHabitatResources().keySet()) {
				megatherium.data.lordsandknights.HabitatResource playerResource = playerHabitat.getHabitatResources().get(id);
				HabitatResource resource = HabitatResource.get(habitat.getID(), Integer.parseInt(id));
				resource.setAmount(playerResource.getAmount()).update();
			}
			
			// update units
			for (megatherium.data.lordsandknights.Unit playerUnit : playerHabitat.getHabitatUnits()) {
				for (String unitID : playerUnit.getDictionary().keySet()) {
					HabitatUnit unit = HabitatUnit.get(habitat.getID(), Integer.parseInt(unitID));
					unit.setAmount(Integer.parseInt(playerUnit.getDictionary().get(unitID))).update();
				}
			}
		}
		
		respond(true, "save");
	}
	
}
