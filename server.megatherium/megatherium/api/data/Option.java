/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package megatherium.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;

/**
 *
 * @author marti_000
 */
public class Option extends DatabaseObject {
    
	public Option() {super();}
	public Option(int id) throws SQLException {super(id);}
	public Option(Map<String, String> data) {super(data);}
	public Option(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public int getOptionCategoryID() {return Integer.parseInt(this.get("id"));}
	public String getName() {return this.get("name");}
	public String getType() {return this.get("type");}
	public String getDefault() {return this.get("default");}
	public Option setOptionCategoryID(int optionCategoryID) {this.set("optionCategoryID", optionCategoryID+""); return this;}
	public Option setName(String name) {this.set("name", name); return this;}
	public Option setType(String type) {this.set("type", type); return this;}
	public Option setDefault(String defaultValue) {this.set("default", defaultValue); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "optionCategoryID", "name", "type", "default"};
	}

	@Override
	protected String getTableName() {
		return "option";
	}
	
	@Override
	protected void initializeLinkings() {
		super.initializeLinkings();
		
		this.addLinking("category", new OptionCategory());
	}
	


}