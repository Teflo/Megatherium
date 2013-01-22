/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.request;

import com.google.gson.internal.Primitives;
import java.util.HashMap;
import java.util.Map;
import megatherium.config.Config;
import megatherium.event.EventHandler;
import megatherium.event.EventManager;
import megatherium.util.ClassUtil;
import megatherium.util.HttpUtil;
import megatherium.util.JsonUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class HttpRequest {
	private Map<String, String> parameters = new HashMap<String, String>();
	private String url;
	private String method = "get";
	private String eventName = "megatherium.request.execute";
	private String body = "";
	public String getEventName() {return this.eventName;}
	
	/**
	 * Initializes the request with the default event name "execute" (resulting event name: "megatherium.request.execute")
	 * 
	 * @param url the url that will be called
	 */
	public HttpRequest(String url) {
		this.url = url;
	}
	
	/**
	 * Initializes the request with an event name.
	 * 
	 * @param eventName the name of the event that will be fired when the request will be executed
	 * @param url the url that will be called
	 */
	public HttpRequest(String eventName, String url) {
		this.eventName = eventName;
		this.url = url;
	}
	
	/**
	 * Adds a new parameter that will be sent.
	 * 
	 * @param name the name of the parameter
	 * @param value the parameter's value
	 * @return the class instance itself for faster programming
	 */
	public HttpRequest set(String name, String value) {
		parameters.put(name, value);
		
		return this;
	}
	
	/**
	 * Adds a new parameter that will be parsed to json and then send.
	 * 
	 * @param name the name of the parameter
	 * @param object the object that will be parsed and be sent as the value
	 * @return the class instance itself for faster programming
	 */
	public HttpRequest set(String name, Object object) {
		parameters.put(name, JsonUtil.toJson(object));
		return this;
	}
	
	/**
	 * Sets the request body.
	 * 
	 * @param body the body content
	 * @return the class instance itself for faster programming
	 */
	public HttpRequest setBody(String body) {
		this.body = body;
		return this;
	}
	
	/**
	 * Sets the request method (either "get" or "post", all other ones will be ignored). Default: "get"
	 * 
	 * @param method the method name
	 * @return the class instance itself for faster programming
	 */
	public HttpRequest setMethod(String method) {
		if (method.equals("get") || method.equals("post")) this.method = method;
		
		return this;
	}
	
	/**
	 * Executes the request and returns the answer.
	 * 
	 * @return the answer
	 */
	public String execute() {
		EventManager.getInstance().fireBeforeEvent(this.eventName, new Object[] {this});
		String response = HttpUtil.execute(url, parameters, method);
		EventManager.getInstance().fireAfterEvent(this.eventName, new Object[] {this});
		if (Config.get("debug", boolean.class)) ReportUtil.getInstance().add("Response: "+response);
		return response;
	}
	
	/**
	 * Use this method for Json responses. The method parses the response into the given class type and returns its object.
	 * 
	 * @param responseType the type of the response
	 * @return the parsed/casted response
	 */
	public <T> T execute(Class<T> responseType) {
		String response = this.execute();
		int objectBeginIndex = response.indexOf("{");
		int objectEndIndex = response.lastIndexOf("}");
		if (objectBeginIndex > 0) response = response.substring(objectBeginIndex, objectEndIndex+1);
		return ClassUtil.cast(JsonUtil.getGson().fromJson(response, responseType), responseType); // TODO do I really need to cast this?!
	}
	
}
