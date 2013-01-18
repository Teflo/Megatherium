/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.util.server;

import sargtex.crse.data.server.JsonError;

/**
 *
 * @author Pathos
 */
public class JsonUtil {
	
	/**
	 * Parses an exception to an json error object and returns it.
	 * 
	 * @param exception the thrown exception
	 * @return the json error object
	 */
	public static JsonError toError(Exception ex) {
		// create string stacktrace
		String stacktrace = "";
		
		// fill stacktrace information
		for (int i = 0; i < ex.getStackTrace().length; ++i) {
			stacktrace += ex.getStackTrace()[i].toString()+"\n";
		}
		
		// return new error
		return new JsonError(ex.getLocalizedMessage(), stacktrace);
	}
	
	/**
	 * Parses an exception to a json error object and returns that as a json string.
	 * 
	 * @param exception the thrown exception
	 * @return the json error string
	 */
	public static String toJsonError(Exception ex) {
		JsonError error = toError(ex);
		
		// return error
		return Json.getInstance().toJson(error);
	}
	
}
