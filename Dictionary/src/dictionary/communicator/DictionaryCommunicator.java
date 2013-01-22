/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.communicator;

import dictionary.communicator.data.Language;
import dictionary.communicator.data.Word;
import megatherium.request.HttpRequest;
import megatherium.request.MegatheriumRequest;

/**
 *
 * @author marti_000
 */
public class DictionaryCommunicator {
	private static DictionaryCommunicator instance;
	
	private DictionaryCommunicator() {}
	
	/**
	 * Returns the current instance.
	 * 
	 * @return the current instance
	 */
	public static DictionaryCommunicator getInstance() {
		if (instance == null) instance = new DictionaryCommunicator();
		
		return instance;
	}
	
	/**
	 * Returns a list of languages from the remote server.
	 * 
	 * @return the language list
	 */
	public Language[] getLanguages() {
		MegatheriumRequest request = MegatheriumRequest.create("language", "list");
		return request.exec(new Language[]{}.getClass());
	}
	
	/**
	 * Returns all words registered for that language.
	 * 
	 * @param languageCode the language code
	 * @return the word list
	 */
	public Word[] getWordList(String languageCode) {
		return MegatheriumRequest.create("word", "list").set("languageCode", languageCode).exec(new Word[]{}.getClass());
	}
	
	/**
	 * Tries to add a new word.
	 * 
	 * @param languageCode the language code
	 * @param word the word
	 * @return true if the word was created successfully, false if it already exists
	 */
	public boolean addWord(String languageCode, String word) {
		return MegatheriumRequest.create("word", "add").set("languageCode", languageCode).set("word", word).exec().getStatus();
	}
	
	
}
