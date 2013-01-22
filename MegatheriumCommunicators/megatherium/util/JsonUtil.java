/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Primitives;
import java.util.ArrayList;
import java.util.List;
import megatherium.debug.JsonError;


/**
 *
 * @author Pathos
 */
public class JsonUtil {
	private static Gson gson;
	
	/**
	 * Returns the current gson instance.
	 * 
	 * @return gson instnace
	 */
	public static Gson getGson() {
		if (gson == null) {
			gson = new GsonBuilder().setPrettyPrinting().create();
		}
		
		return gson;
	}
	
	/**
	 * Prints the response pretty.
	 * Works also with method-calls like mymeth({obje: val})
	 * 
	 * @param text the response
	 * @return the pretty stuff
	 */
	public static String getPretty(String text) {
		int objectBeginIndex = text.indexOf("{");
		int objectEndIndex = text.lastIndexOf("}");
		if (objectBeginIndex > 0) text = text.substring(objectBeginIndex, objectEndIndex+1);
		return JsonUtil.getGson().toJson(JsonUtil.getGson().fromJson(text, JsonElement.class));
	}
	
	/**
	 * Parses the object as json and returns the json string.
	 * 
	 * @param object the object that will be jsonized
	 * @return the json string
	 */
	public static String toJson(Object object) {
		return getGson().toJson(object);
	}

	/**
	 * Parses an exception to an json error object and returns it.
	 *
	 * @param exception the thrown exception
	 * @return the json error object
	 */
	public static JsonError toError(Throwable ex) {
		// create string stacktrace
		List<String> additionalInformation = new ArrayList<String>();

		// fill stacktrace information
		for (int i = 0; i < ex.getStackTrace().length; ++i) {
			additionalInformation.add("    "+ex.getStackTrace()[i].toString());
		}
		
		// add "caused by" information
		if (ex.getCause() != null) {
			JsonError error = toError(ex.getCause());
			additionalInformation.add("Caused by: "+error.getErrorMessage());
			for (String line : error.getAdditionalInformation()) additionalInformation.add(line);
		}

		// return new error
		JsonError error = new JsonError(ex.getClass().getCanonicalName()+": "+ex.getLocalizedMessage(), additionalInformation.toArray(new String[]{}));
		return error;
	}

	/**
	 * Parses an exception to a json error object and returns that as a json
	 * string.
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
