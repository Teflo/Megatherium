/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data.lordsandknights;

/**
 *
 * @author marti_000
 */
public class Attack {
	private int id;
	private int accountID;
	private int startHabitatID;
	private int targetHabitatID;
	private int time;
	private boolean executed;
	public int getID() {return this.id;}
	public int getAccountID() {return this.accountID;}
	public int getStartHabitatID() {return this.startHabitatID;}
	public int getTargetHabitatID() {return this.targetHabitatID;}
	public int getTime() {return this.time;}
	public boolean isExecuted() {return this.executed;}
	
}
