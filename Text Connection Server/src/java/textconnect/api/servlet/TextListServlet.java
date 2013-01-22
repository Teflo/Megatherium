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
public class TextListServlet extends DefaultServlet {
	
	@Override
	public void save() throws SQLException {
		respond(true, "textList", Text.getAll());
	}
	
}
