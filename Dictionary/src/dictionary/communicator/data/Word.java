/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.communicator.data;

/**
 *
 * @author marti_000
 */
public class Word implements Comparable<Word> {
	private int id;
	private String languageCode;
	private String word;
	public Word(String languageCode, String word) {this.languageCode = languageCode; this.word = word;}
	public int getID() {return this.id;}
	public String getLanguageCode() {return this.languageCode;}
	public String getWord() {return this.word;}

	@Override
	public int compareTo(Word word) {
		return this.getWord().compareTo(word.getWord());
	}
	
}
