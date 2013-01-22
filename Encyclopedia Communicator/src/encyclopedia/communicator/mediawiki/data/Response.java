/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.communicator.mediawiki.data;

/**
 *
 * @author marti_000
 */
public class Response {
	private Query query;
	private Article parse;
	public Query getQuery() {return this.query;}
	public Article getArticle() {return this.parse;}
	
}
