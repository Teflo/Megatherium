/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.lordsandknights;

import java.util.Date;

/**
 *
 * @author marti_000
 */
public class HabitatResource {
	private int amount;
	private int storageAmount;
	private int generateAmount;
	private Date lastUpdate;
	public int getAmount() {return this.amount;}
	public int getStorableAmount() {return this.storageAmount;}
	public int getGenerateAmount() {return this.generateAmount;}
	public Date getLastUpdate() {return this.lastUpdate;}
	
}
