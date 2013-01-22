/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textconnect.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;
import megatherium.util.ReportUtil;
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class Text extends DatabaseObject {
    
	public Text() {super();}
	public Text(int id) throws SQLException {super(id);}
	public Text(Map<String, String> data) {super(data);}
	public Text(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getTitle() {return this.get("title");}
	public String getComment() {return this.get("comment");}
	public String getText() {return this.get("text");}
	public Text setTitle(String title) {this.set("title", title); return this;}
	public Text setComment(String comment) {this.set("comment", comment); return this;}
	public Text setText(String text) {this.set("text", text); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "title", "comment", "text"};
	}

	@Override
	protected String getTableName() {
		return "tc_text";
	}
	
	@Override
	public String getForeignKeyName() {
		return "textID";
	}
	
	/**
	 * Returns a list containing all text objects.
	 * 
	 * @return the texts
	 */
	public static Text[] getAll() throws SQLException {
		List<Text> textList = new ArrayList<Text>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM tc_text");
		while (rs.next()) textList.add(new Text(rs));
		return textList.toArray(new Text[]{});
	}
	
	/**
	 * Returns a list containing all connections between the two texts.
	 * 
	 * @param textID1 the id of the first text
	 * @param textID2 the id of the second text
	 * @return the connections
	 */
	public static Connection[] getConnections(int textID1, int textID2) throws SQLException {
		List<Connection> connectionList = new ArrayList<Connection>();
		
		// generate query
		String query = "SELECT * FROM tc_connection WHERE (textID1 = ? AND textID2 = ?)"+((textID1 == textID2) ? " OR (textID1 = ? AND textID2 = ?)" : "");
		String[] parameters = (textID1 == textID2) ? new String[] {textID1+"", textID2+"", textID2+"", textID1+""} : new String[] {textID1+"", textID2+""};
		
		// execute query, return connections
		ResultSet rs = Database.getInstance().fetch(query, parameters);
		while (rs.next()) connectionList.add(new Connection(rs));
		return connectionList.toArray(new Connection[]{});
	}

}