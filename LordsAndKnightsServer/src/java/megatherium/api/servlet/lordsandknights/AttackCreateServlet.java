/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet.lordsandknights;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import megatherium.api.data.DatabaseObject;
import megatherium.api.data.Session;
import megatherium.api.database.lordsandknights.Attack;
import megatherium.api.database.lordsandknights.AttackResource;
import megatherium.api.database.lordsandknights.AttackUnit;
import megatherium.api.database.lordsandknights.Habitat;
import megatherium.api.servlet.DefaultServlet;
import megatherium.util.JsonUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class AttackCreateServlet extends DefaultServlet {
	private int accountID;
	private int startHabitatID;
	private int targetHabitatID;
	private int time;
	private Map<String, String> units = new HashMap<String, String>();
	private Map<String, String> resources = new HashMap<String, String>();
	
	@Override
	public void readParameters() {
		if (hasParam("accountID")) this.accountID = Integer.parseInt(getParam("accountID"));
		if (hasParam("startHabitatID")) this.startHabitatID = Integer.parseInt(getParam("startHabitatID"));
		if (hasParam("targetHabitatID")) this.targetHabitatID = Integer.parseInt(getParam("targetHabitatID"));
		if (hasParam("time")) this.time = Integer.parseInt(getParam("time"));
		if (hasParam("units")) this.units = JsonUtil.getGson().fromJson(getParam("units"), this.units.getClass());
		if (hasParam("resources")) this.resources = JsonUtil.getGson().fromJson(getParam("resources"), this.resources.getClass());
	}
	
	@Override
	public String validate() throws SQLException {
		if (Session.getInstance().getUser() == null || !Session.getInstance().getUser().hasAccount(this.accountID)) return "noaccess";
		if (!new Habitat(this.startHabitatID).exists()) return "nostarthabitat";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		// set attack data
		Attack attack = new Attack();
		attack.setAccountID(this.accountID).setStartHabitatID(this.startHabitatID).setTargetHabitatID(this.targetHabitatID).setTime(this.time);
		
		// add resources
		List<AttackResource> resourceList = new ArrayList<AttackResource>();
		Set<String> keySet = this.resources.keySet();
		for (String key : keySet) resourceList.add(new AttackResource().setResourceID(Integer.parseInt(key)).setAmount(Integer.parseInt(this.resources.get(key))));
		
		// add units
		List<AttackUnit> unitList = new ArrayList<AttackUnit>();
		keySet = this.units.keySet();
		for (String key : keySet) unitList.add(new AttackUnit().setUnitID(Integer.parseInt(key)).setAmount(Integer.parseInt(this.units.get(key))));
		
		// create attack and linked resources
		attack.setLinkedResource("resources", resourceList.toArray(new DatabaseObject[]{})).setLinkedResource("units", unitList.toArray(new DatabaseObject[]{})).create();

		// respond
		respond(true, "attackcreate");
	}
	
}
