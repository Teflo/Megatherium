/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package encyclopedia.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import megatherium.api.data.DatabaseObject;
import megatherium.event.EventManager;
import sargtex.sql.Database;

/**
 *
 * @author marti_000
 */
public class Article extends DatabaseObject {
    
	public Article() {super();}
	public Article(int id) throws SQLException {super(id);}
	public Article(Map<String, String> data) {super(data);}
	public Article(ResultSet rs) throws SQLException {super(rs);}
	public int getID() {return Integer.parseInt(this.get("id"));}
	public String getPlatform() {return this.get("platform");}
	public String getTitle() {return this.get("title");}
	public String getText() {return this.get("text");}
	public Article setPlatform(String platform) {this.set("platform", platform); return this;}
	public Article setTitle(String title) {this.set("title", title); return this;}
	public Article setText(String text) {this.set("text", text); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"id", "platform", "title", "text"};
	}

	@Override
	protected String getTableName() {
		return "encyclopedia_article";
	}
	
	/**
	 * Searches for articles with that or similar titles in the database.
	 * 
	 * @param title the title
	 * @return the list of articles
	 */
	public static Article[] search(String title) throws SQLException {
		// do event
		EventManager.getInstance().fireEvent("encyclopedia.data.article.search", title);
		
		List<Article> articleList = new ArrayList<Article>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM encyclopedia_article WHERE title LIKE '%?%'", new String[] {title});
		while (rs.next()) articleList.add(new Article(rs));
		return articleList.toArray(new Article[]{});
	}

}