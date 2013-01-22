/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet.lordsandknights;

import java.sql.SQLException;
import megatherium.api.database.lordsandknights.Habitat;
import megatherium.api.servlet.DefaultServlet;

/**
 *
 * @author marti_000
 */
public class HabitatCreateServlet extends DefaultServlet {
	private int id;
	private String name;
	
	@Override
	public void readParameters() {
		if (hasParam("id")) this.id = Integer.parseInt(getParam("id"));
		if (hasParam("name")) this.name = getParam("name");
	}
	
	@Override
	public String validate() throws SQLException {
		if (this.id < 1 || new Habitat(this.id).exists()) return "noid";
		if (this.name == null || this.name.isEmpty()) return "noname";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		new Habitat(this.id).setName(this.name).create();
	}
	
}
