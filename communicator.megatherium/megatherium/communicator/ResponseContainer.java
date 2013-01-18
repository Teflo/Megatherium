/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import java.util.List;
import megatherium.util.ReportUtil;

/**
 *
 * @author SargTeX
 */
public class ResponseContainer {
	private List<String> messages;
	private Response response;
	public List<String> getMessages() {return this.messages;}
	public Response getResponse() {return this.response;}

	public ResponseContainer(Response response) {
		this.messages = ReportUtil.getInstance().getMessageList();
		this.response = response;
	}
}
