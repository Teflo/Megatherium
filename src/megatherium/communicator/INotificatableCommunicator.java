/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator;

import java.util.Date;
import java.util.List;
import megatherium.communicator.data.social.INotificationItem;
import megatherium.communicator.data.social.NotificationItem;

/**
 *
 * @author Pathos
 */
public interface INotificatableCommunicator {
	
	/**
	 * Returns a list with all notifications.
	 * 
	 * @param since the list shall only notifications that where created after this timestamp
	 * @return the list with the notifications
	 */
	public List<INotificationItem> getNotifications(Date since);
	
}
