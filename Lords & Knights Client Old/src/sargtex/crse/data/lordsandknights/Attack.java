/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.data.lordsandknights;

import sargtex.crse.data.lordsandknights.Habitat;

/**
 *
 * @author Pathos
 */
public class Attack {

	private int startHabitatID;
	private int destinationHabitatID;
	private long timestamp;

	/**
	 * Initializes the attack data.
	 *
	 * @param startHabitatID the id of the habitat that attacks
	 * @param destinationHabitatID the id of the habitat that was attacked
	 * @param timestamp the time in seconds of that attack
	 */
	public Attack(int startHabitatID, int destinationHabitatID, long timestamp) {
		this.startHabitatID = startHabitatID;
		this.destinationHabitatID = destinationHabitatID;
		this.timestamp = timestamp;
	}

	/**
	 * Returns the id of the start habitat.
	 *
	 * @return int the id of the start habitat
	 */
	public int getStartHabitatID() {
		return this.startHabitatID;
	}

	/**
	 * Returns the id of the destination habitat.
	 *
	 * @return int the id of the destination habitat
	 */
	public int getDestinationHabitatID() {
		return this.destinationHabitatID;
	}

	/**
	 * Returns the timestamp of the attacking.
	 *
	 * @return long the timestamp
	 */
	public long getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Updates the timestamp.
	 *
	 * @param long the timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Returns the start habitat.
	 * 
	 * @return the start habitat
	 */
	public Habitat getStartHabitat() {
		return Game.getInstance().getCommunicator().getSession().getPlayer().getHabitats().get("" + startHabitatID);
	}
	
}
