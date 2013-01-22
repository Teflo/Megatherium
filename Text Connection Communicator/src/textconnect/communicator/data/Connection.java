/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textconnect.communicator.data;

/**
 *
 * @author marti_000
 */
public class Connection {
	private int id;
	private String label;
	private int textID1;
	private int textID2;
	private int startIndex1;
	private int startIndex2;
	private int endIndex1;
	private int endIndex2;
	
	public String toString() {return this.getLabel();}

	public String getLabel() {
		return label;
	}

	public int getTextID1() {
		return textID1;
	}

	public int getTextID2() {
		return textID2;
	}

	public int getStartIndex1() {
		return startIndex1;
	}

	public int getStartIndex2() {
		return startIndex2;
	}

	public int getEndIndex1() {
		return endIndex1;
	}

	public int getEndIndex2() {
		return endIndex2;
	}
	private String comment;

	public int getID() {
		return id;
	}

	public String getComment() {
		return comment;
	}
	
	
}
