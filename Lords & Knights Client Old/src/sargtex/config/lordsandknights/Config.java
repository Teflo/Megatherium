/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.config.lordsandknights;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;
import sargtex.crse.data.lordsandknights.Attack;
import sargtex.crse.data.lordsandknights.Habitat;
import sargtex.crse.data.lordsandknights.HabitatToTarget;
import sargtex.util.FileUtil;

/**
 *
 * @author Pathos
 */
public class Config extends sargtex.config.Config {
	private Map<String, ArrayList<Habitat>> habitatToTargetList = new HashMap<String, ArrayList<Habitat>>();
	private int attackFrequency = 21600;
	private int sessionUpdateFrequency = 300;
	private int autoAttackCheckFrequency = 5;
	private static Config instance;
	
	/**
	 * Adds an auto attack target.
	 * 
	 * @param habitat the own habitat from that you want to attack your opponent
	 * @param target the opponent habitat
	 */
	public void addHabitatAttack(Habitat habitat, Habitat target) {
		if (!habitatToTargetList.containsKey(habitat.getID()+"")) habitatToTargetList.put(habitat.getID()+"", new ArrayList<Habitat>());
		
		// add attack
		habitatToTargetList.get(habitat.getID()+"").add(target);
	}
	
	/**
	 * Returns the current config instance.
	 * 
	 * @return Config
	 */
	public static Config getInstance() {
		if (instance == null) {
			Gson gson = new Gson();
			try {
				instance = gson.fromJson(FileUtil.getResourceContent("config.data"), Config.class);
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		return instance;
	}
	
	/**
	 * Returns the targets for that specific source habitat.
	 * 
	 * @param source the start (source|attacking) habitat
	 * @return the habitat attack list for this source habitat
	 */
	public ArrayList<Habitat> getTargets(Habitat source) {
		// return empty list if the habitat isn't inserted in the target list
		if (!habitatToTargetList.containsKey(source.getID()+"")) return new ArrayList<Habitat>();
		
		// return target list
		return habitatToTargetList.get(""+source.getID());
	}
	
	/**
	 * Returns the next attack targets from a specific habitat.
	 * 
	 * @param habitat the start (attacking) habitat
	 * @return the habitat list
	 */
	public ArrayList<Habitat> getNextTargets(Habitat habitat) {
		// return empty list if the habitat isn't inserted in the target list
		if (!habitatToTargetList.containsKey(habitat.getID()+"")) return new ArrayList<Habitat>();
		
		// fetch possible targets
		ArrayList<Habitat> habitatList = habitatToTargetList.get(habitat.getID()+"");
		
		// create empty target list
		ArrayList<Habitat> targetList = new ArrayList<Habitat>();
		
		// loop through possible targets
		for (int i = 0; i < habitatList.size(); ++i) {
			if (System.currentTimeMillis()/1000 - attackFrequency > habitatList.get(i).lastAttackTime) targetList.add(habitatList.get(i));
		}
		
		// return next targets
		return targetList;
	}
	
	/**
	 * Returns the session update frequency in seconds.
	 * 
	 * @return int
	 */
	public int getSessionUpdateFrequency() {
		return this.sessionUpdateFrequency;
	}
	
	/**
	 * Returns the attack frequency in seconds.
	 * 
	 * @return int
	 */
	public int getAttackFrequency() {
		return this.attackFrequency;
	}
	
	/**
	 * Returns the attack check frequency in seconds.
	 * Each x seconds the thread will check whether he is able to auto-attack some habitats or not.
	 * 
	 * @return int
	 */
	public int getAutoAttackCheckFrequency() {
		return this.autoAttackCheckFrequency;
	}

	public void setAttackFrequency(int attackFrequency) {
		this.attackFrequency = attackFrequency;
		
		// update config file
		save();
	}

	public void setAutoAttackCheckFrequency(int autoAttackCheckFrequency) {
		this.autoAttackCheckFrequency = autoAttackCheckFrequency;
		
		// update config file
		save();
	}

	public void setSessionUpdateFrequency(int sessionUpdateFrequency) {
		this.sessionUpdateFrequency = sessionUpdateFrequency;
		
		// update config file
		save();
	}
	
}
