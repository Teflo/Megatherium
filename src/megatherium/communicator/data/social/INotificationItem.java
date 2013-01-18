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
public interface INotificationItem {
	
	/**
	 * Returns the platform on that this notification was created.
	 * 
	 * @return the code for the platform, see class Platform
	 */
	public int getPlatform();
	
	/**
	 * Returns information about another account that launched this notification.
	 * This could either be another user (e.g. if this notification is a PM) or a bot.
	 * 
	 * @return the account information
	 */
	public IUser getUser();
	
	/**
	 * Returns the type of this notification item. That could be, for example, a direct message, or a timeline item, or ...
	 * 
	 * @return the number of the notification item type. see NotificationType to get to know the exact type
	 */
	public int getType();
	
	/**
	 * Returns the text of the item, e.g. the text of the direct message.
	 * 
	 * @return the text
	 */
	public String getText();
	
	/**
	 * Returns the date of creation of the notification. E.g. on a direct message, this is the creation date of the message.
	 * 
	 * @return the date
	 */
	public Date getCreationDate();
	
}
