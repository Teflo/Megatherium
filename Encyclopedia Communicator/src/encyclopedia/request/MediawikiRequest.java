/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.request;

import megatherium.request.HttpRequest;
import megatherium.request.MegatheriumRequest;

/**
 *
 * @author marti_000
 */
public class MediawikiRequest extends HttpRequest {
	private String format = "json";
	private String action = "query";

	public MediawikiRequest(String url) {
		super(url);
	}

	public MediawikiRequest(String eventName, String url) {
		super(eventName, url);
	}
	
	/**
	 * Sets the request action.
	 * 
	 * @param action the action
	 * @return the class instance itself for faster programming
	 */
	public MediawikiRequest setAction(String action) {
		this.action = action;
		return this;
	}

	@Override
	public MediawikiRequest set(String name, String value) {
		super.set(name, value);
		return this;
	}

	@Override
	public MediawikiRequest set(String name, Object object) {
		super.set(name, object);
		return this;
	}

	@Override
	public MediawikiRequest setMethod(String method) {
		super.setMethod(method);
		return this;
	}

	@Override
	public String execute() {
		this.set("action", this.action);
		this.set("format", this.format);
		return super.execute();
	}
	
	
	
}
