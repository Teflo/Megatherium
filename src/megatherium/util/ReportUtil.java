/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pathos
 */
public class ReportUtil {
	private static ReportUtil instance;
	private Writer writer;
	public boolean exceptionThrown;
	public List<String> messageList = new ArrayList<String>();
	public List<String> getMessageList() {return this.messageList;}
	
	/**
	 * Private constructor to enable singleton pattern.
	 */
	private ReportUtil() {}
	
	/**
	 * Returns the instance of the report util.
	 * 
	 * @return the report util
	 */
	public static ReportUtil getInstance() {
		if (instance == null) instance = new ReportUtil();
		
		return instance;
	}
	
	/**
	 * Sets the new writer.
	 * 
	 * @param writer the writer
	 */
	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	
	/**
	 * Adds a message to the message list.
	 * 
	 * @param message the message
	 */
	public void add(String message) {
		this.messageList.add(message);
		System.err.println("MESSAGE: "+message);
	}
	
	/**
	 * Adds an object to the message list.
	 * 
	 * @param object the object
	 */
	public void add(Object object) {
		if (object instanceof Map) {
			Object[] keys = ((Map) object).keySet().toArray();
			if (keys[0] instanceof String) for (int i = 0; i < ((Map)object).size(); ++i) add(keys[i]+" = "+((Map)object).get(keys[i]));
			return;
		}
		
		try {
			this.add(Json.getInstance().toJson(object));
		} catch (Exception ex) {
			this.add(ex);
		}
	}
	
	/**
	 * Adds an exception's error message to the message list.
	 * 
	 * @param exception the exception
	 */
	public void add(Exception exception) {
		exceptionThrown = true;
		
		try {
			System.err.println(exception.getLocalizedMessage());
			this.add(JsonUtil.toJsonError(exception));
		}catch(Exception ex) {
			this.add(exception.getMessage());
		}
	}
	
	/**
	 * Reports a message.
	 * 
	 * @param message the message
	 */
	public void report(String message) {
		try {
			if (writer != null) {
				writer.write(message);
			}
			else messageList.add(message);
		} catch (IOException ex) {
			Logger.getLogger(ReportUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	/**
	 * Reports an exception.
	 * 
	 * @param exception the exception
	 */
	public void report(Exception exception) {
		exceptionThrown = true;
		
		try {
			report(JsonUtil.toJsonError(exception));
		} catch (Exception ex) {
			report(ex.getMessage());
			report(exception.getMessage());
		}
	}
	
	/**
	 * Reports an object.
	 * 
	 * @param object the object
	 */
	public void report(Object object) {
		try {
			writer.write(Json.getInstance().toJson(object));
		}catch(Exception ex) {
			ReportUtil.getInstance().add(ex);
		}
	}
	
	/**
	 * Closes the writer and resets the instance.
	 */
	public void close() {
		try {
			writer.close();
		} catch (IOException ex) {
			Logger.getLogger(ReportUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		// reset instance
		instance = null;
	}
	
}
