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
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class HabitatUnit extends DatabaseObject {
    
	public HabitatUnit() {super();}
	public HabitatUnit(int id) throws SQLException {super(id);}
	public HabitatUnit(Map<String, String> data) {super(data);}
	public HabitatUnit(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public int getHabitatID() {return Integer.parseInt(this.get("habitatID"));}
	public int getUnitID() {return Integer.parseInt(this.get("unitID"));}
	public int getAmount() {return Integer.parseInt(this.get("amount"));}
	public HabitatUnit setHabitatID(int habitatID) {this.set("habitatID", habitatID+""); return this;}
	public HabitatUnit setUnitID(int unitID) {this.set("unitID", unitID+""); return this;}
	public HabitatUnit setAmount(int amount) {this.set("amount", amount+""); return this;}

    @Override
	protected String[] getFields() {
		return new String[] {"id", "habitatID", "unitID", "amount"};
	}

	@Override
	protected String getTableName() {
		return "lak_habitat_unit";
	}
    
    /**
	 * Returns the instance of the habitat unit. If such an unit wasn't inserted into the database, the unit will be inserted before.
	 * 
	 * @param habitatID the id of the habitat
	 * @param unitID the id of the unit
	 * @return the habitat unit
	 */
	public static HabitatUnit get(int habitatID, int unitID) throws SQLException {
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM lak_habitat_unit WHERE habitatID = ? AND unitID = ?", new String[] {habitatID+"", unitID+""});
		if (rs.first()) return new HabitatUnit(rs);
		
		// create new unit
		HabitatUnit unit = new HabitatUnit();
		unit.setHabitatID(habitatID).setUnitID(unitID).create();
		return unit;
	}
    
}