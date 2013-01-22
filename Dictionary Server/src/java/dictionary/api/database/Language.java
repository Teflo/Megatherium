/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary.api.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class Language extends DatabaseObject {
    
	public Language() {super();}
	public Language(String code) throws SQLException {super(code);}
	public Language(Map<String, String> data) {super(data);}
	public Language(ResultSet rs) throws SQLException {super(rs);}
	public String getCode() {return this.get("code");}
	public String getName() {return this.get("name");}
	public Language setCode(String code) {this.set("code", code); return this;}
	public Language setName(String name) {this.set("name", name); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"code", "name"};
	}

	@Override
	protected String getTableName() {
		return "dictionary_language";
	}
	
	@Override
	public String getIDProperty() {
		return "code";
	}
	
	/**
	 * Returns a list of languages.
	 * 
	 * @return all languages
	 */
	public static Language[] getAll() throws SQLException {
		List<Language> languageList = new ArrayList<Language>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM dictionary_language");
		while (rs.next()) languageList.add(new Language(rs));
		return languageList.toArray(new Language[]{});
	}
	

}