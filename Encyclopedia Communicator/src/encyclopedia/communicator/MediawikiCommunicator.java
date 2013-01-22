/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.communicator;

import encyclopedia.communicator.mediawiki.data.Article;
import encyclopedia.communicator.mediawiki.data.Query;
import encyclopedia.communicator.mediawiki.data.Response;
import encyclopedia.data.PlatformData;
import encyclopedia.request.MediawikiRequest;
import java.util.HashMap;
import java.util.Map;
import megatherium.communicator.data.Platform;
import megatherium.config.Config;
import megatherium.util.JsonUtil;

/**
 *
 * @author marti_000
 */
public class MediawikiCommunicator extends EncyclopediaCommunicator implements IEncyclopediaCommunicator {
	private String url;
	private static Map<String, MediawikiCommunicator> instances = new HashMap<String, MediawikiCommunicator>();
	private static Platform lastPlatform;
	
	/**
	 * Initializes the communicator with th mediawiki's URL.
	 * 
	 * @param url the url
	 */
	private MediawikiCommunicator(Platform platform) {
		this.setPlatform(platform);
		this.url = platform.getData(PlatformData.class).getURL();
	}
	
	/**
	 * Returns the instance.
	 * 
	 * @param platform the platform on that the communicator gets executed
	 * @return the instance
	 */
	public static MediawikiCommunicator getInstance(Platform platform) {
		if (!instances.containsKey(platform.getName())) instances.put(platform.getName(), new MediawikiCommunicator(platform));
		
		return instances.get(platform.getName());
	}
	
	/**
	 * Returns the last used communicator.
	 * You have to call getInstance(String url) first.
	 * 
	 * @return the last used communicator
	 */
	public static MediawikiCommunicator getInstance() {
		return getInstance(lastPlatform);
	}
	
	/**
	 * Searches for the string and returns possibilities.
	 * 
	 * @param query the search query
	 */
	public Article[] search(String query) {
		return search(new String[] {query});
	}
	
	/**
	 * Searches for several article titles and returns all possibilities.
	 * 
	 * @param titles the titles
	 * @return the possibilities
	 */
	public Article[] search(String[] titles) {
		// generate search query
		String query = "";
		for (String title : titles) {
			if (query.length() > 0) query += "|";
			query += title;
		}
		
		// search
		return new MediawikiRequest(this.url+"api.php").set("list", "allpages").set("apfrom", query).set("aplimit", 3).execute(Response.class).getQuery().getArticles();
	}
	
	/**
	 * Returns an article.
	 * 
	 * @param title the title of the article/page
	 * @return the article
	 */
	public Article getArticle(String title) {
		return new MediawikiRequest(this.url+"api.php").setAction("parse").set("redirects", "").set("page", title).execute(Response.class).getArticle();
	}
	
	/**
	 * Returns an article by it's id.
	 * 
	 * @param articleID the id of the article
	 * @return the article's content
	 */
	public Article getArticle(int articleID) {
		return new MediawikiRequest(this.url+"api.php").setAction("parse").set("pageid", articleID).execute(Response.class).getArticle();
	}
	
}
