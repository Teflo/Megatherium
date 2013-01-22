/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.data.store;

import dictionary.communicator.DictionaryCommunicator;
import dictionary.communicator.data.Word;
import java.util.Collections;
import java.util.Comparator;
import megatherium.data.store.Store;

/**
 *
 * @author marti_000
 */
public class WordStore extends Store<Word> {
	private String languageCode;
	
	/**
	 * Initializes the store.
	 * 
	 * @param languageCode the language code for this store
	 */
	public WordStore(String languageCode) {
		this.languageCode = languageCode;
	}
	
	@Override
	public void load() {
		this.add(DictionaryCommunicator.getInstance().getWordList(languageCode));
	}

	@Override
	public String getName() {
		return "wordStore-"+this.languageCode;
	}
	
	/**
	 * Checks whether such a word exists in this store.
	 * 
	 * @param word the word
	 * @return either true or false
	 */
	public boolean exists(Word word) {
		return (Collections.binarySearch(this.getItems(), word) > 0);
	}
	
}
