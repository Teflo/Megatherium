/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data.lordsandknights.megatherium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marti_000
 */
public class Attack {
	private int id;
	private int accountID;
	private int startHabitatID;
	private int targetHabitatID;
	private long time;
	private boolean executed;
	private HabitatResource[] resources;
	private HabitatUnit[] units;
	public int getID() {return this.id;}
	public int getAccountID() {return this.accountID;}
	public int getStartHabitatID() {return this.startHabitatID;}
	public int getTargetHabitatID() {return this.targetHabitatID;}
	public long getTime() {return this.time;}
	public boolean isExecuted() {return this.executed;}
	public HabitatResource[] getResources() {return this.resources;}
	public HabitatUnit[] getUnits() {return this.units;}
	public Map<String, String> getResourcesAsMap() {
		Map<String, String> resources = new HashMap<String, String>();
		for (HabitatResource resource : this.resources) resources.put(resource.getResourceID()+"", resource.getAmount()+"");
		return resources;
	}
	public Map<String, String> getUnitsAsMap() {
		Map<String, String> units = new HashMap<String, String>();
		for (HabitatUnit unit : this.units) units.put(unit.getUnitID()+"", unit.getAmount()+"");
		return units;
	}
	
}
