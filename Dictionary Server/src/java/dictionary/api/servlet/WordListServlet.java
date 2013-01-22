/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.api.servlet;

import dictionary.api.database.Word;
import java.sql.SQLException;
import megatherium.api.servlet.DefaultServlet;

/**
 *
 * @author marti_000
 */
public class WordListServlet extends DefaultServlet {
	private String languageCode;
	
	@Override
	public void readParameters() {
		super.readParameters();
		
		if (hasParam("languageCode")) this.languageCode = getParam("languageCode");
	}
	
	@Override
	public String validate() throws SQLException {
		if (this.languageCode == null || this.languageCode.isEmpty()) return "languageCode.missing";
		return super.validate();
	}
	
	@Override
	public void save() throws SQLException {
		respond(true, "wordList", Word.getWords(this.languageCode));
	}
	
}
