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
public class HabitatUpdateServlet extends DefaultServlet {
	private int habitatID;
	private String name;
	
	@Override
	public void readParameters() {
		if (hasParam("habitatID")) this.habitatID = Integer.parseInt(getParam("habitatID"));
		if (hasParam("name")) this.name = getParam("name");
	}
	
	@Override
	public String validate() {
		if (this.habitatID == 0) return "nohabitat";
		if (this.name == null || this.name.isEmpty()) return "noname";
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		new Habitat(this.habitatID).setName(this.name).update();
	}
	
}
