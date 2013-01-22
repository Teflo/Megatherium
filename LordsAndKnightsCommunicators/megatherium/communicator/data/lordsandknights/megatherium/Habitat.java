/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data.lordsandknights.megatherium;

/**
 *
 * @author marti_000
 */
public class Habitat {
	private int id;
	private int accountID;
	private String name;
	private HabitatResource[] resources;
	private HabitatUnit[] units;
	public int getID() {return this.id;}
	public int getAccountID() {return this.accountID;}
	public String getName() {return this.name;}
	public HabitatResource[] getResources() {return this.resources;}
	public HabitatUnit[] getUnits() {return this.units;}
	public String toString() {return this.getName();}
	
}
