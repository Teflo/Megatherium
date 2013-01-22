/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textconnect.api.servlet;

import java.sql.SQLException;
import megatherium.api.servlet.DefaultServlet;
import textconnect.api.data.Connection;
import textconnect.api.data.Text;

/**
 *
 * @author marti_000
 */
public class ConnectionAddServlet extends DefaultServlet {
	private String label = "";
	private int textID1 = 0;
	private int startIndex1 = 0;
	private int endIndex1 = 0;
	private int textID2 = 0;
	private int startIndex2 = 0;
	private int endIndex2 = 0;
	private String comment = "";
	
	@Override
	public void readParameters() {
		super.readParameters();
		
		if (hasParam("label")) this.label = getParam("label");
		if (hasParam("textID1")) this.textID1 = Integer.parseInt(getParam("textID1"));
		if (hasParam("startIndex1")) this.startIndex1 = Integer.parseInt(getParam("startIndex1"));
		if (hasParam("endIndex1")) this.endIndex1 = Integer.parseInt(getParam("endIndex1"));
		if (hasParam("textID2")) this.textID2 = Integer.parseInt(getParam("textID2"));
		if (hasParam("startIndex2")) this.startIndex2 = Integer.parseInt(getParam("startIndex2"));
		if (hasParam("endIndex2")) this.endIndex2 = Integer.parseInt(getParam("endIndex2"));
		if (hasParam("comment")) this.comment = getParam("comment");
	}
	
	@Override
	public String validate() throws SQLException {
		if (!new Text(this.textID1).exists()) return "text1.notFound";
		if (!new Text(this.textID2).exists()) return "text2.notFound";
		if (this.endIndex1 < 0 || this.endIndex2 < 0 || this.startIndex1 < 0 || this.startIndex2 < 0) return "index.unvalid";
		return super.validate();
	}
	
	@Override
	public void save() throws SQLException {
		new Connection().setLabel(label).setTextID1(textID1).setTextID2(textID2).setStartIndex1(startIndex1).setStartIndex2(startIndex2).setEndIndex1(endIndex1).setEndIndex2(endIndex2).setComment(comment).create();
	}
	
}
