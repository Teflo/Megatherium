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
 * @author marti_000
 */
public class Habitat extends DatabaseObject {
	
	public Habitat() {super();}
    public Habitat(int id) throws SQLException {super(id);}
    public Habitat(Map<String, String> data) {super(data);}
    public Habitat(ResultSet rs) throws SQLException  {super(rs);}
    public int getID() {return Integer.parseInt(this.get("id"));}
    public int getAccountID() {if (this.get("accountID") == null) return 0; return Integer.parseInt(this.get("accountID"));}
    public String getName() {return this.get("name");}
    public Habitat setAccountID(int accountID) {this.set("accountID", accountID+""); return this;}
    public Habitat setName(String name) {this.set("name", name); return this;}
    
    @Override
    protected String[] getFields() {
		return new String[] {"id", "accountID", "name"};
    }

    @Override
    protected String getTableName() {
		return "lak_habitat";
    }
	
	@Override
	protected String getForeignKeyName() {
		return "habitatID";
	}
	
	@Override
	protected void initializeLinkings() {
		this.addLinking("units", new HabitatUnit());
		this.addLinking("resources", new HabitatResource());
	}
    
	/**
	 * Returns all the habitats matching the account id.
	 * 
	 * @param accountID the account id
	 * @return the habitat list
	 */
	public static Habitat[] getAll(int accountID) throws SQLException {
		List<Habitat> habitatList = new ArrayList<Habitat>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM lak_habitat WHERE accountID = ?", new String[] {accountID+""});
		while (rs.next()) {
			Habitat habitat = new Habitat(rs);
			habitatList.add((Habitat) habitat.loadLinkings());
		}
		return habitatList.toArray(new Habitat[]{});
	}
    
    
}
