/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import com.google.gson.JsonObject;
import com.google.gson.internal.Primitives;
import java.util.List;
import megatherium.util.JsonUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author Pathos
 */
public class Response {
	private String status;
	private String type;
	private Object data;
	public boolean getStatus() {return this.status.equals("success");}
	public String getType() {return this.type;}
	public Object getData() {return this.data;}
	
	/**
	 * Initializes the response.
	 * 
	 * @param status the status, e.g. 'success' or 'error'
	 * @param type the type as a string, helps recognizing the type of object (e.g. 'session' or 'user' for user information, ...)
	 * @param data an object with data, that will be serialized using Gson to be displayed to the website
	 */
	public Response(String status, String type, Object data) {
		this.status = status;
		this.type = type;
		this.data = data;
	}
	
	/**
	 * This method parses the data into the given class type and returns it.
	 * 
	 * @param classType the class type of the data
	 * @return the object or null if it wasn't possible or whatever
	 */
	public <T> T getData(Class<T> classType) {
		System.err.println("BITCH");
		System.err.println(JsonUtil.getGson().fromJson((String) this.getData(), JsonObject.class).get("data").getAsString());
		return Primitives.wrap(classType).cast(JsonUtil.getGson().fromJson((String) this.getData(), classType));
	}
	
	@Override
	public String toString() {
		return JsonUtil.toJson(new ResponseContainer(this));
	}
	
}
