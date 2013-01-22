/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.api.servlet;

import dictionary.api.database.Language;
import java.sql.SQLException;
import megatherium.api.servlet.DefaultServlet;

/**
 *
 * @author marti_000
 */
public class LanguageListServlet extends DefaultServlet {
	
	@Override
	public void save() throws SQLException {
		respond(true, "languageList", Language.getAll());
	}
	
}
