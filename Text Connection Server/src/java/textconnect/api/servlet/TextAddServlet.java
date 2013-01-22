/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package textconnect.api.servlet;

import java.sql.SQLException;
import megatherium.api.servlet.DefaultServlet;
import megatherium.util.ReportUtil;
import textconnect.api.data.Text;

/**
 *
 * @author marti_000
 */
public class TextAddServlet extends DefaultServlet {
	private String title = "";
	private String comment = "";
	private String text = "";
	
	@Override
	public void readParameters() {
		super.readParameters();
		
		if (hasParam("title")) this.title = getParam("title");
		if (hasParam("comment")) this.comment = getParam("comment");
		if (hasParam("text")) this.text = getParam("text");
	}
	
	@Override
	public String validate() throws SQLException {
		if (this.title.isEmpty()) return "title.empty";
		if (this.text.isEmpty()) return "text.empty";
		return super.validate();
	}
	
	@Override
	public void save() throws SQLException {
		ReportUtil.getInstance().add("Title: "+title);
		new Text().setTitle(title).setText(text).setComment(comment).create();
	}
	
}
