/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data.social;

import java.util.Comparator;

/**
 *
 * @author Pathos
 */
public class MessageComparator implements Comparator<IMessage> {

	@Override
	public int compare(IMessage o1, IMessage o2) {
		return (o1.getCreationDate().compareTo(o2.getCreationDate()));
	}
	
}
