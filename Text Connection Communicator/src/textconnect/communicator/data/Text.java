/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textconnect.communicator.data;

/**
 *
 * @author marti_000
 */
public class Text {
	private int id;
	private String title;
	private String comment;
	private String text;
	public int getID() {return this.id;}
	public String getTitle() {return this.title;}
	public String getComment() {return this.comment;}
	public String getText() {return this.text;}
	public String toString() {return this.getTitle();}
	
}
