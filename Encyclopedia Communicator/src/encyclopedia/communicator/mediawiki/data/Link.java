/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.communicator.mediawiki.data;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author marti_000
 */
public class Link {
	@SerializedName("*")
	private String title;
	public String getTitle() {return this.title;}
	public String toString() {return this.getTitle();}
	
}
