/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary.api.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class Word extends DatabaseObject {
	
	public Word() {super();}
	public Word(int id) throws SQLException {super(id);}
	public Word(Map<String, String> data) {super(data);}
	public Word(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getLanguageCode() {return this.get("languageCode");}
	public String getWord() {return this.get("word");}
	public Word setLanguageCode(String languageCode) {this.set("languageCode", languageCode); return this;}
	public Word setWord(String word) {this.set("word", word); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "languageCode", "word"};
	}

	@Override
	protected String getTableName() {
		return "dictionary_word";
	}
	
	/**
	 * Returns all words from a specified language.
	 * 
	 * @param languageCode the language code
	 * @return all words from the language
	 */
	public static Word[] getWords(String languageCode) throws SQLException {
		List<Word> wordList = new ArrayList<Word>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM dictionary_word WHERE languageCode = ?", new String[] {languageCode});
		while (rs.next()) wordList.add(new Word(rs));
		return wordList.toArray(new Word[]{});
	}
	
	/**
	 * Checks whether the word exists or not.
	 * 
	 * @param languageCode the code of the language
	 * @param word the word
	 * @return either true (exists) or false (doesnt exist)
	 */
	public static boolean exists(String languageCode, String word) throws SQLException {
		ResultSet rs = Database.getInstance().fetch("SELECT id FROM dictionary_word WHERE languageCode = ? AND word = ?", new String[] {languageCode, word});
		return rs.first();
	}

}