/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textconnect.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;

/**
 *
 * @author marti_000
 */
public class Connection extends DatabaseObject {
    
	public Connection() {super();}
	public Connection(int id) throws SQLException {super(id);}
	public Connection(Map<String, String> data) {super(data);}
	public Connection(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getLabel() {return this.get("label");}
	public int getTextID1() {return Integer.parseInt(this.get("textID1"));}
	public int getStartIndex1() {return Integer.parseInt(this.get("startIndex1"));}
	public int getEndIndex1() {return Integer.parseInt(this.get("endIndex1"));}
	public int getTextID2() {return Integer.parseInt(this.get("textID2"));}
	public int getStartIndex2() {return Integer.parseInt(this.get("startIndex2"));}
	public int getEndIndex2() {return Integer.parseInt(this.get("endIndex2"));}
	public String getComment() {return this.get("comment");}
	public Connection setLabel(String label) {this.set("label", label); return this;}
	public Connection setTextID1(int textID1) {this.set("textID1", textID1+""); return this;}
	public Connection setStartIndex1(int startIndex1) {this.set("startIndex1", startIndex1+""); return this;}
	public Connection setEndIndex1(int endIndex1) {this.set("endIndex1", endIndex1+""); return this;}
	public Connection setTextID2(int textID2) {this.set("textID2", textID2+""); return this;}
	public Connection setStartIndex2(int startIndex2) {this.set("startIndex2", startIndex2+""); return this;}
	public Connection setEndIndex2(int endIndex2) {this.set("endIndex2", endIndex2+""); return this;}
	public Connection setComment(String comment) {this.set("comment", comment); return this;}
	
	@Override
	protected String[] getFields() {
		return new String[] {"id", "label", "textID1", "startIndex1", "endIndex1", "textID2", "startIndex2", "endIndex2", "comment"};
	}

	@Override
	protected String getTableName() {
		return "tc_connection";
	}

}