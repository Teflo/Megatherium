/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textconnect.communicator.data;

import textconnect.communicator.data.Text;

/**
 *
 * @author marti_000
 */
public class Selection {
	private Text text;
	private int startIndex;
	private int endIndex;
	public Selection(Text text, int startIndex, int endIndex) {
		this.text = text;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	public Text getText() {return this.text;}
	public int getStartIndex() {return this.startIndex;}
	public int getEndIndex() {return this.endIndex;}
	public String toString() {return text.getText().substring(startIndex, endIndex);}
	
}
