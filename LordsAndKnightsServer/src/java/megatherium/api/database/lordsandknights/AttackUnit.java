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
public class AttackUnit extends DatabaseObject {
    
	public AttackUnit() {super();}
	public AttackUnit(int id) throws SQLException {super(id);}
	public AttackUnit(Map<String, String> data) {super(data);}
	public AttackUnit(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public int getAttackID() {return Integer.parseInt(this.get("attackID"));}
	public int getUnitID() {return Integer.parseInt(this.get("unitID"));}
	public int getAmount() {return Integer.parseInt(this.get("amount"));}
	public AttackUnit setAttackID(int attackID) {this.set("attackID", attackID+""); return this;}
	public AttackUnit setUnitID(int unitID) {this.set("unitID", unitID+""); return this;}
	public AttackUnit setAmount(int amount) {this.set("amount", amount+""); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "attackID", "unitID", "amount"};
	}

	@Override
	protected String getTableName() {
		return "lak_attack_unit";
	}
	


}