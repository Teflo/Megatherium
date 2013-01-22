/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import megatherium.api.data.DatabaseObject;
import megatherium.api.data.Session;
import megatherium.communicator.Response;
import megatherium.debug.PrintManager;
import sargtex.debug.ServletPrinter;
import megatherium.util.ReportUtil;

/**
 *
 * @author Pathos
 */
@WebServlet(name = "DefaultServle#t", urlPatterns = {"/DefaultServlet"})
public abstract class DefaultServlet extends HttpServlet {

	protected HttpServletRequest request;
	private HttpServletResponse response;
	protected String activeTabMenuItem = "";
	private boolean responded = false;

	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and
	 * <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// save request
		this.request = request;
		this.response = response;
		this.responded = false;

		// enable printer
		PrintManager.getInstance().setPrinter(new ServletPrinter(response.getWriter()));
		ServletInputStream str = this.request.getInputStream();
		byte[] bytes = new byte[1];
		String content = "";
		while (str.read(bytes) > 0) content += new String(bytes);
		ReportUtil.getInstance().add("Title: \""+this.request.getAttribute("title")+"\"\n\t\""+this.request.getParameter("title")+"\"");
		ReportUtil.getInstance().add("InputStream: \""+content+"\"");

		// initialize session
		Session.create(this.getSessionID());

		// call methods
		try {
			this.readParameters();
			this.readData();
			String validation = this.validate();
			if (validation == null) this.save();
			else respond(false, validation);
			
			if (!responded) respond(true, "success");
		} catch (SQLException ex) {
			ReportUtil.getInstance().add(ex);
			respond(false, "error", ex);
		}
	}

	/**
	 * Reads the parameters.
	 */
	public void readParameters() {}

	/**
	 * Reads the data.
	 */
	public void readData() throws SQLException {}
	
	/**
	 * Returns a list of permissions that are required to call this servlet.
	 * The permissions will be tested within the validate() method. It is only allowed to use "boolean"-permissions, all the other permissions must be checked by the servlet itself.
	 * 
	 * @return a list of required permissions
	 */
	public String[] getRequiredPermissions() {return new String[]{};}

	/**
	 * Validates the data.
	 *
	 * @return null or (if an error occured) some message
	 */
	public String validate() throws SQLException {
		return null;
	}

	/**
	 * Saves the data.
	 */
	public void save() throws SQLException {};

	/**
	 * Checks whether that param was given or not.
	 *
	 * @param name the name of the parameter
	 * @return either true (exists) or false (doesnt)
	 */
	public boolean hasParam(String name) {
		return (request.getParameter(name) != null);
	}

	/**
	 * Returns the value of the parameter.
	 *
	 * @param name the name of the parameter
	 * @return the value
	 */
	public String getParam(String name) {
		return request.getParameter(name);
	}

	/**
	 * Redirects the servlet to another URL.
	 *
	 * @param url the target url
	 */
	public void sendRedirect(String url) {
		// donot redirect if an error was thrown (for debugging clause)
		if (ReportUtil.getInstance().exceptionThrown) {
			respond(false, "error");
			return;
		}
		
		// send redirect
		try {
			response.sendRedirect(url);
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
	}

	/**
	 * Responds to the server with a null data-object.
	 *
	 * @param success false if an error occured
	 * @param message the message
	 */
	protected void respond(boolean success, String message) {
		respond(success, message, null);
	}

	/**
	 * Responds to the server.
	 *
	 * @param success false if an error occured
	 * @param message the message
	 * @param data an object with data that will be included to the respond
	 */
	protected void respond(boolean success, String message, Object data) {
		if (responded) {
			return;
		}
		responded = true;
		
		// form data for case if database object
		// TODO find a nicer solution
		data = this.bundle(data);
		
		try {
			response.setContentType("script;charset=UTF-8");
			ReportUtil.getInstance().setWriter(response.getWriter());

			// check for thrown exceptions
//			if (sargtex.util.ReportUtil.getReportList().size() > 0) {
//				for (int i = 0; i < sargtex.util.ReportUtil.getReportList().size(); ++i) {
//					Object report = sargtex.util.ReportUtil.getReportList().get(i);
//					if (report instanceof String) {
//						ReportUtil.getInstance().add((String) report);
//					} else {
//						ReportUtil.getInstance().add(report);
//					}
//				}
//			}

			// don't go on if any exceptions where thrown
			//	if (ReportUtil.getInstance().exceptionThrown) return;

			// set active tab
			// TODO update this?!?!?!
	//		GUI.getInstance().setActiveTabMenuItem(activeTabMenuItem);

			// respond
			Response response = new Response((success) ? "success" : "error", message, data);
			if (response != null) {
				message = request.getParameter("callback") + "(" + response.toString() + ")";
			}
			ReportUtil.getInstance().report(message);
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}

		ReportUtil.getInstance().close();
	}
	
	/**
	 * Bundles the data (that will be used in the response) to manage database objects aso.
	 * 
	 * @param data the data
	 * @return the bundled data
	 */
	protected Object bundle(Object data) {
		if (!(data instanceof DatabaseObject[])) {
			return data;
		}
		
		// bundle data
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		DatabaseObject[] objects = (DatabaseObject[]) data;
		
		// loop through objects
		for (int i = 0; i < objects.length; ++i) {
			// fetch new data object and keys
			Map<String, Object> data2 = objects[i].getData();
			Set<String> keySet = data2.keySet();
			
			// loop through sub objects
			for (String key : keySet) {
				Object object = data2.get(key);
				data2.put(key, this.bundle(object));
			}
			
			// add data to list
			dataList.add(data2);
		}
		
		return dataList;
	}
	
	/**
	 * Returns the session id generated by the tomcat server.
	 * 
	 * @return the session id
	 */
	public String getSessionID() {
		return this.request.getSession().getId();
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP
	 * <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP
	 * <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
