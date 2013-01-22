/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data.social;

import java.util.Date;

/**
 *
 * @author Pathos
 */
public interface IMessage {
	
	/**
	 * Returns the text of the private message.
	 * 
	 * @return the text
	 */
	public String getText();
	
	/**
	 * Returns the user object of the recipient.
	 * 
	 * @return the recipient
	 */
	public IUser getRecipient();
	
	/**
	 * Returns the user object of the sender of this message.
	 * 
	 * @return the sender
	 */
	public IUser getSender();
	
	/**
	 * Returns the date of creation of this message.
	 * 
	 * @return the date of creation
	 */
	public Date getCreationDate();
	
	/**
	 * Returns the UNIX timestamp of the creation date.
	 * 
	 * @return the date of creation as UNIX timestamp
	 */
	public long getCreationDateTimestamp();
	
}
