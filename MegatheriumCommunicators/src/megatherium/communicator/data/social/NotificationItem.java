/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data.social;

import java.awt.Color;
import java.util.Date;

/**
 *
 * @author Pathos
 */
public class NotificationItem implements INotificationItem {
	private int type;
	private String text;
	private Date creationDate;
	private long creationDateTimestamp;
	private IUser user;
	private int platform;
	
	/**
	 * Initializes the notification item.
	 * 
	 * @param platform the platform of the item, e.g. Twitter or Facebook, see Platform
	 * @param type the type of the item, e.g. a direct message, see NotificationType
	 * @param text the text of the item, e.g. the content of the direct message
	 * @param creationDate the date of creation
	 * @param user the user that launched this notification
	 */
	public NotificationItem(int platform, int type, String text, Date creationDate, IUser user) {
		this.platform = platform;
		this.type = type;
		this.text = text;
		this.creationDate = creationDate;
		this.creationDateTimestamp = creationDate.getTime();
		this.user = user;
	}
	
	@Override
	public int getPlatform() {
		return this.platform;
	}

	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	@Override
	public IUser getUser() {
		return this.user;
	}
	
}
