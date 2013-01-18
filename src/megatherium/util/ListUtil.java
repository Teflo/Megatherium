/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pathos
 */
public class ListUtil {
	
	/**
	 * Parses the map into a list.
	 * 
	 * @param map	the map
	 * @return the list with the same entries
	 */
	public static Collection<?> toList(Map<?, ?> map) {
		return map.values();
	}
	
}
