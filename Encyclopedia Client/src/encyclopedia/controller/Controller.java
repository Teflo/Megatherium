/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.controller;

import encyclopedia.Encyclopedia;
import encyclopedia.communicator.MediawikiCommunicator;
import encyclopedia.ui.ArticlePanel;
import encyclopedia.ui.HomePanel;
import encyclopedia.ui.SettingsPanel;
import java.util.ArrayList;
import java.util.List;
import megatherium.config.Config;
import megatherium.event.EventManager;
import megatherium.util.ArrayUtil;

/**
 *
 * @author marti_000
 */
public class Controller extends megatherium.controller.MegatheriumController {

	@Override
	protected String[][] getReferences() {
		return ArrayUtil.merge2(super.getReferences(), new String[][] {
			{"encyclopedia.ui.article.show", "showArticle"},
			{"encyclopedia.ui.settings.cancel", "closeDialog"},
			{"encyclopedia.ui.settings.close", "closeDialog"},
			{"encyclopedia.ui.settings.show", "showSettings"},
			{"encyclopedia.ui.search", "searchArticles"}
		});
	}

	@Override
	public void initializePanels() {
		super.initializePanels();
		
		this.addPanel("article", new ArticlePanel());
		this.addPanel("homePanel", new HomePanel());
		this.addPanel("settings", new SettingsPanel());
	}

	@Override
	public String getStartupPanel() {
		return "homePanel";
	}

	@Override
	public void initializeEvents() {
		super.initializeEvents();
		
		List<String> platforms = Config.get("encyclopedia.activePlatforms", new ArrayList<String>().getClass());
		String[] names = new String[platforms.size()];
		for (int i = 0; i < platforms.size(); ++i) names[i] = platforms.get(i);
		Encyclopedia.setEncyclopedias(names);
	}
	
	/** References **/
	public void searchArticles(String query) {
		((HomePanel) this.getPanel("homePanel")).setArticles(Encyclopedia.search(query));
	}
	public void showArticle(String title) {
		((ArticlePanel) getPanel("article")).setArticle(MediawikiCommunicator.getInstance().getArticle(title));
		this.displayDialog("article");
	}
	public void showSettings() {this.displayDialog("settings");}
	
}
