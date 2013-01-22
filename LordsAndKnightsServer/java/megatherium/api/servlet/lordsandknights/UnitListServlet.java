/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet.lordsandknights;

import java.sql.SQLException;
import megatherium.api.database.lordsandknights.Unit;
import megatherium.api.servlet.DefaultServlet;

/**
 *
 * @author marti_000
 */
public class UnitListServlet extends DefaultServlet {
	
	@Override
	public void save() throws SQLException {
		respond(true, "unitList", Unit.getAll());
	}
	
}
