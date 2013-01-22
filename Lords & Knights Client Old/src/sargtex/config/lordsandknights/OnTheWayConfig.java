/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.config.lordsandknights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import sargtex.crse.data.lordsandknights.Game;
import sargtex.crse.data.lordsandknights.Habitat;

/**
 *
 * @author Pathos
 */
public class OnTheWayConfig {
	private static OnTheWayConfig instance;
	private Map<String, Habitat> attackedTargetList = new HashMap<String, Habitat>();
	private ArrayList<Habitat> sourceHabitatList = new ArrayList<Habitat>();
	
	private OnTheWayConfig() {
		this.generateSourceHabitatList();
	}
	
	/**
	 * Returns the instance of the on the way config.
	 * 
	 * @return the current instance
	 */
	public static OnTheWayConfig getInstance() {
		if (instance == null) instance = new OnTheWayConfig();
		
		return instance;
	}
	
	public ArrayList<Habitat> getSourceHabitatList() {return this.sourceHabitatList;}
	
	/**
	 * Generates a list of source habitats. In this session, the source habitats will attack the targets in this order.
	 */
	private void generateSourceHabitatList() {
		// fetch player's habitats
		Habitat[] habitats = Game.getInstance().getCommunicator().getSession().getPlayer().getHabitatList();
		
		// get start habitat number
		int startAt = (int) (Math.random()*(habitats.length));
		
		// add them in the correct list
		for (int i = startAt; i < habitats.length; ++i) sourceHabitatList.add(habitats[i]);
		for (int i = 0; i < startAt; ++i) sourceHabitatList.add(habitats[i]);
	}
	
	/**
	 * Checks whether the habitat was already attacked.
	 * 
	 * @param target the target habitat
	 * @return boolean
	 */
	public boolean wasAttacked(Habitat target) {
		return (attackedTargetList.containsKey(""+target.getID()));
	}
	
	/**
	 * Checks whether the habitat was attacked since the timestamp.
	 * 
	 * @param target the target habitat
	 * @param timestamp the timestamp
	 * @return return (target.lastAttackTime >= timestamp) 
	 */
	public boolean wasAttackedSince(Habitat target, long timestamp) {
		if (!attackedTargetList.containsKey(""+target.getID())) attackedTargetList.put(""+target.getID(), target);
		
		return (attackedTargetList.get(""+target.getID()).lastAttackTime >= timestamp);
	}

	public void updateAttackTime(Habitat target) {
		if (!attackedTargetList.containsKey(""+target.getID())) attackedTargetList.put(""+target.getID(), target);
		
		target.lastAttackTime = (int) (System.currentTimeMillis()/1000);
		attackedTargetList.put(""+target.getID(), target);
	}
	
}
