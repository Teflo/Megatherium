/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.Primitives;
import megatherium.util.JsonUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class Response {
	private String status;
	private String type;
	private JsonElement data;
	public boolean getStatus() {return this.status.equals("success");}
	public String getType() {return this.type;}
	public String getData() {
		return this.data.toString();
	}
	
	/**
	 * This method parses the data into the given class type and returns it.
	 * 
	 * @param classType the class type of the data
	 * @return the object or null if it wasn't possible or whatever
	 */
	public <T> T getData(Class<T> classType) {
		String data = this.getData();
		try {
			data = JsonUtil.getGson().fromJson(this.getData(), JsonElement.class).getAsJsonObject().get("data").toString();
		} catch (IllegalStateException ex) {}
		return Primitives.wrap(classType).cast(JsonUtil.getGson().fromJson(data, classType));
	}
	
}
