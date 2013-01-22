/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia;

import encyclopedia.communicator.IEncyclopediaCommunicator;
import encyclopedia.communicator.MediawikiCommunicator;
import encyclopedia.communicator.mediawiki.data.Article;
import encyclopedia.data.PlatformData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import megatherium.communicator.data.Platform;
import megatherium.data.store.PlatformStore;
import megatherium.data.store.Stores;

/**
 *
 * @author marti_000
 */
public class Encyclopedia {
	private static IEncyclopediaCommunicator[] communicators;
	private static Platform[] platforms;
	
	/**
	 * Searches for articles.
	 * 
	 * @param title the title
	 * @return the list of articles
	 */
	public static Article[] search(String title) {
		List<Article> articleList = new ArrayList<Article>();
		for (IEncyclopediaCommunicator communicator : getCommunicators()) {
			for (Article article : communicator.search(title)) articleList.add(article.setPlatform(communicator.getPlatform()));
		}
		return articleList.toArray(new Article[]{});
	}
	
	/**
	 * Sets a list of encyclopedias that will be used.
	 * 
	 * @param names the names of the encyclopedias
	 */
	public static void setEncyclopedias(String[] names) {
		List<Platform> platformList = new ArrayList<Platform>();
		Platform[] platforms2 = Stores.get("platform", PlatformStore.class).getByType("encyclopedia");
		for (String name : names) {
			for (Platform platform : platforms2) {
				if (platform.getName().equals(name)) {
					platformList.add(platform);
					break;
				}
			}
		}
		platforms = platformList.toArray(new Platform[]{});
		communicators = null;
	}
	
	/**
	 * Returns a list of all encyclopedian communicators.
	 * 
	 * @return the communicators
	 */
	public static IEncyclopediaCommunicator[] getCommunicators() {
		if (communicators != null) return communicators;
		
		// call list
		List<IEncyclopediaCommunicator> communicatorList = new ArrayList<IEncyclopediaCommunicator>();
		
		// fetch platforms
		if (platforms == null) platforms = Stores.get("platform", PlatformStore.class).getByType("encyclopedia");
		for (Platform platform : platforms) {
			if (platform.getType().startsWith("encyclopedia.mediawiki")) {
				String url = platform.getData(PlatformData.class).getURL();
				communicatorList.add(MediawikiCommunicator.getInstance(platform));
			}
		}
		
		// return communicators
		communicators = communicatorList.toArray(new IEncyclopediaCommunicator[]{});
		return communicators;
	}
	
	
}
