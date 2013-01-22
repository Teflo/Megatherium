/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.debug;

import megatherium.debug.Printer;
import java.io.IOException;
import java.io.Writer;
import megatherium.util.ReportUtil;

/**
 *
 * @author Pathos
 */
public class ServletPrinter extends Printer {
	private Writer writer;
	
	/**
	 * Initializes the servlet printer.
	 */
	public ServletPrinter(Writer writer) {
		this.writer = writer;
	}

	@Override
	public void print(String message) {
		// format message
		message = message.replaceAll("\n", "<br>");
		message = message.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		
		try {
			writer.write(message);
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
	}
	
	
	
}
