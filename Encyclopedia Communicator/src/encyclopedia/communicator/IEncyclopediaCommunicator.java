/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.communicator;

import encyclopedia.communicator.mediawiki.data.Article;
import megatherium.communicator.data.Platform;

/**
 *
 * @author marti_000
 */
public interface IEncyclopediaCommunicator {
	
	/**
	 * Searches for articles.
	 * 
	 * @param title the title of the articles
	 * @return the article list
	 */
	public Article[] search(String title);
	
	/**
	 * Returns the platform.
	 * 
	 * @return the platform
	 */
	public Platform getPlatform();
	
	/**
	 * Sets the platform.
	 * 
	 * @param platform the platform
	 * @return the communicator itself for faster programming
	 */
	public IEncyclopediaCommunicator setPlatform(Platform platform);
	
}
