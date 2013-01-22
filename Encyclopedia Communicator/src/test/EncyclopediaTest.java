/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import encyclopedia.Encyclopedia;
import encyclopedia.communicator.mediawiki.data.Article;
import megatherium.communicator.data.Platform;
import megatherium.config.Config;

/**
 *
 * @author marti_000
 */
public class EncyclopediaTest {
	
	public static void main(String[] args) {
		Config.set("debug", false, boolean.class);
		
		Article[] articles = Encyclopedia.search("Einstein");
		
		Platform platform = null;
		for (Article article : articles) {
			if (platform == null || !platform.getName().equals(article.getPlatform().getName())) {
				platform = article.getPlatform();
				System.out.println("\nErgebnisse von "+platform.getLabel());
			}
			System.out.println(article.getID()+": "+article.getTitle());
		}
	}
	
}
