/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.request;

import com.google.gson.internal.Primitives;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import megatherium.communicator.MegatheriumCommunicator;
import megatherium.communicator.data.Response;
import megatherium.communicator.data.ResponseContainer;
import megatherium.event.EventManager;
import megatherium.event.IEventHandler;
import megatherium.event.IEventListener;
import megatherium.event.IUniversalListener;
import megatherium.util.JsonUtil;
import megatherium.util.StringUtil;

/**
 *
 * @author marti_000
 */
public class MegatheriumRequest extends HttpRequest {
	private static Map<String, Map<String, List<IEventListener>>> listenerList = new HashMap<String, Map<String, List<IEventListener>>>();
	private String object;
	private String action;
	private static String serverURL = MegatheriumCommunicator.SERVER_URL;
	
	/**
	 * Initializes the request manager with the server url.
	 * 
	 * @param serverURL the new server url
	 */
	public static void initialize(String serverURL) {
		MegatheriumRequest.serverURL = serverURL;
	}
	
	/**
	 * Initializes the request.
	 * 
	 * @param object the name of the object, e.g. "account" or "platform" or sth
	 * @param action the action, e.g. "list", "update", "create", "delete" or sth like that
	 */
	protected MegatheriumRequest(String object, String action) {
		super("megatherium.request."+object+"."+action, serverURL+StringUtil.ucfirst(object)+StringUtil.ucfirst(action)+"Servlet");
		
		this.object = object;
		this.action = action;
	}
	
	/**
	 * Creates a new instance and automatically adds all the universal event listeners to it that have been registered at the request manager.
	 * 
	 * @param object the nameof the object, e.g. "account" or "platform"
	 * @param action the action, e.g. "list", "update", "create", "delete" or sth like that
	 */
	public static MegatheriumRequest create(String object, String action) {
		return create(object, action, "get");
	}
	
	public static MegatheriumRequest create(String object, String action, String method) {
		MegatheriumRequest request = (MegatheriumRequest) new MegatheriumRequest(object, action).setMethod(method);
		
		return request;
	}
	
	/**
	 * Executes the request, parses the response to a response and returns it.
	 * 
	 * @return the response
	 */
	public Response exec() {
		ResponseContainer container = super.execute(ResponseContainer.class);
		return container.getResponse();
	}
	
	/**
	 * Parses the data from the request into the given class type and returns the object.
	 * 
	 * @param classType the type of the class into that the data from the response will be parsed.
	 * @return the data
	 */
	public <T> T exec(Class<T> classType) {
		Response response = this.exec();
		System.err.println("exec, BITCH!");
		return response.getData(classType);
	}
	
	@Override
	public MegatheriumRequest set(String name, Object value) {
		super.set(name, value);
		return this;
	}
	
	@Override
	public MegatheriumRequest set(String name, String value) {
		super.set(name, value);
		return this;
	}
	
}
