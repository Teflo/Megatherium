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
public class WordAddServlet extends DefaultServlet {
	private String languageCode;
	private String word;
	
	@Override
	public void readParameters() {
		super.readParameters();
		
		if (hasParam("languageCode")) this.languageCode = getParam("languageCode");
		if (hasParam("word")) this.word = getParam("word");
	}
	
	@Override
	public String validate() throws SQLException {
		if (this.languageCode == null || this.languageCode.isEmpty()) return "languageCode.empty";
		if (this.word == null || this.word.isEmpty()) return "word.empty";
		if (Word.exists(languageCode, word)) return "word.exists";
		return super.validate();
	}
	
	@Override
	public void save() throws SQLException {
		new Word().setLanguageCode(languageCode).setWord(word).create();
	}
	
}
