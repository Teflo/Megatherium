/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.data.store;

import encyclopedia.communicator.mediawiki.data.Article;
import megatherium.data.store.Store;

/**
 *
 * @author marti_000
 */
public class ArticleStore extends Store<Article> {

	@Override
	public void load() {
		
	}

	@Override
	public String getName() {
		return "articleStore";
	}
	
	/**
	 * Returns the article by it's title.
	 * If the article can't be found, it will be loaded.
	 */
	
}
