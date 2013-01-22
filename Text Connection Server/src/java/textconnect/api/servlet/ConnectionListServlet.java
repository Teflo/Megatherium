/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textconnect.api.servlet;

import java.sql.SQLException;
import megatherium.api.servlet.DefaultServlet;
import textconnect.api.data.Text;

/**
 *
 * @author marti_000
 */
public class ConnectionListServlet extends DefaultServlet {
	private int textID1;
	private int textID2;
	
	@Override
	public void readParameters() {
		super.readParameters();
		
		if (hasParam("textID1")) this.textID1 = Integer.parseInt(getParam("textID1"));
		if (hasParam("textID2")) this.textID2 = Integer.parseInt(getParam("textID2"));
	}
	
	@Override
	public String validate() throws SQLException {
		if (!new Text(this.textID1).exists()) return "text1.unknown";
		if (!new Text(this.textID2).exists()) return "text2.unknown";
		return super.validate();
	}
	
	@Override
	public void save() throws SQLException {
		respond(true, "connectionList", Text.getConnections(this.textID1, this.textID2));
	}
	
}
