/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package megatherium.api.database.lordsandknights;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;

/**
 *
 * @author marti_000
 */
public class AttackResource extends DatabaseObject {
    
	public AttackResource() {super();}
	public AttackResource(int id) throws SQLException {super(id);}
	public AttackResource(Map<String, String> data) {super(data);}
	public AttackResource(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public int getAttackID() {return Integer.parseInt(this.get("attackID"));}
	public int getResourceID() {return Integer.parseInt(this.get("resourceID"));}
	public int getAmount() {return Integer.parseInt(this.get("amount"));}
	public AttackResource setAttackID(int attackID) {this.set("attackID", attackID+""); return this;}
	public AttackResource setResourceID(int resourceID) {this.set("resourceID", resourceID+""); return this;}
	public AttackResource setAmount(int amount) {this.set("amount", amount+""); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "attackID", "resourceID", "amount"};
	}

	@Override
	protected String getTableName() {
		return "lak_attack_resource";
	}
	


}