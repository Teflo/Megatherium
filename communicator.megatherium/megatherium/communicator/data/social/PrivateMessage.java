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
public class PrivateMessage implements IPrivateMessage {
	private String text;
	private IUser recipient;
	private IUser sender;
	private Date creationDate;
	private long creationDateTimestamp;

	/**
	 * Initializes this private message.
	 * 
	 * @param text the textual content of the message
	 * @param sender the user object of the sender
	 * @param recipient the user object of the recipient
	 * @param creationDate the date of creation
	 */
	public PrivateMessage(String text, IUser sender, IUser recipient, Date creationDate) {
		this.text = text;
		this.recipient = recipient;
		this.sender = sender;
		this.creationDate = creationDate;
		this.creationDateTimestamp = creationDate.getTime();
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public IUser getRecipient() {
		return this.recipient;
	}

	@Override
	public IUser getSender() {
		return this.sender;
	}

	@Override
	public Date getCreationDate() {
		return this.creationDate;
	}

	@Override
	public long getCreationDateTimestamp() {
		return this.creationDateTimestamp;
	}
	
	
}
