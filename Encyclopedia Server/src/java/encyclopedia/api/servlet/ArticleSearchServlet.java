/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.api.servlet;

import encyclopedia.api.data.Article;
import java.sql.SQLException;
import megatherium.api.servlet.DefaultServlet;

/**
 *
 * @author marti_000
 */
public class ArticleSearchServlet extends DefaultServlet {
	private String title;
	
	@Override
	public void readParameters() {
		super.readParameters();
		
		if (hasParam("title")) this.title = getParam("title");
	}
	
	@Override
	public String validate() throws SQLException {
		if (title == null || title.isEmpty()) return "title.empty";
		return super.validate();
	}
	
	@Override
	public void save() throws SQLException {
		respond(true, "articleList", Article.search(title));
	}
	
}
