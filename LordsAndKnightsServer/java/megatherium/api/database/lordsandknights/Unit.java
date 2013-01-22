/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.database.lordsandknights;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import megatherium.api.data.DatabaseObject;
import sargtex.sql.Database;

/**
 *
 * @author SargTeX
 */
public class Unit extends DatabaseObject {
	
	public Unit(int id) throws SQLException {super(id);}
	public Unit(Map<String, String> data) {super(data);}
	public Unit(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getLabel() {return this.get("label");}
	public double getSpeed() {return Double.parseDouble(this.get("speed"));}
	public Unit setLabel(String label) {this.set("label", label); return this;}
	public Unit setSpeed(double speed) {this.set("speed", speed+""); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "label", "speed"};
	}

	@Override
	protected String getTableName() {
		return "lak_unit";
	}
	
	/**
	 * Returns all units.
	 * 
	 * @return all units
	 */
	public static Unit[] getAll() throws SQLException {
		List<Unit> unitList = new ArrayList<Unit>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM lak_unit");
		while (rs.next()) unitList.add(new Unit(rs));
		return unitList.toArray(new Unit[]{});
	}
	
	
}
