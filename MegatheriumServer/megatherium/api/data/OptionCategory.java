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
public class OptionCategory extends DatabaseObject {
    
	public OptionCategory() {super();}
	public OptionCategory(int id) throws SQLException {super(id);}
	public OptionCategory(Map<String, String> data) {super(data);}
	public OptionCategory(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getName() {return this.get("name");}
	public OptionCategory setName(String name) {this.set("name", name); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "name"};
	}

	@Override
	protected String getTableName() {
		return "option_category";
	}
	
	@Override
	public String getForeignKeyName() {
		return "optionCategoryID";
	}

}