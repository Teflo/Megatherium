/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import java.util.Date;
import java.util.List;
import megatherium.communicator.data.social.IMessage;
import megatherium.communicator.data.social.IPrivateMessage;

/**
 *
 * @author Pathos
 */
public interface IPrivateMessageCommunicator {
	
	/**
	 * Returns all sent private messages since the timestamp.
	 * The list has to be sorted by the creation date in ASC.
	 * 
	 * @param since the timestamp
	 * @return list of the private messages sent by the user since the timestamp
	 */
	public List<IMessage> getSentPrivateMessages(Date since);
	
	/**
	 * Returns all received private messages since the timestamp.
	 * The list has to be sorted by the creation date in ASC.
	 * 
	 * @param since the timestamp
	 * @return list of the private messages received by the user since the timestamp
	 */
	public List<IMessage> getReceivedPrivateMessages(Date since);
	
	/**
	 * Returns a list of all received private messages since the timestamp.
	 * The list has to be sorted by the creation date in ASC.
	 * 
	 * @param since the timestamp
	 * @return list of the private messages since the timestamp
	 */
	public List<IMessage> getPrivateMessages(Date since);
	
}
