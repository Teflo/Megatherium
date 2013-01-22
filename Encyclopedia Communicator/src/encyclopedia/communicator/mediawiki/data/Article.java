/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.communicator.mediawiki.data;

import megatherium.communicator.data.Platform;

/**
 *
 * @author marti_000
 */
public class Article {
	private int pageid;
	private String title;
	private Text text;
	private Link[] links;
	private Platform platform;
	public int getID() {return this.pageid;}
	public String getTitle() {return this.title;}
	public String getText() {return this.text.toString();}
	public Link[] getLinks() {return this.links;}
	public Platform getPlatform() {return this.platform;}
	public Article setPlatform(Platform platform) {this.platform = platform; return this;}
	public String toString() {
		return (this.platform == null) ? this.getTitle() : this.getTitle()+" - "+this.getPlatform().getLabel();
	}
	
}
