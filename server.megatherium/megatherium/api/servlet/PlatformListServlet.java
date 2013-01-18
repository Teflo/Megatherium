/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import java.sql.SQLException;
import megatherium.api.data.Platform;

/**
 *
 * @author marti_000
 */
public class PlatformListServlet extends DefaultServlet {
	
	@Override
	public void save() throws SQLException {
		respond(true, "platformList", Platform.getAll());
	}
	
}
