/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti_000
 */
public class ArrayUtil {
	
	/**
	 * Returns a copy from the array, excluding the last item.
	 * 
	 * @param array the array containing some stuff
	 * @return the array without the last item
	 */
	public static Object[] removeLast(Object[] array) {
		Object[] array2 = new Object[array.length - 1];
		for (int i = 0; i < array2.length; ++i) array2[i] = array[i];
		return array2;
	}
	
	/**
	 * Merges several string arrays with each other.
	 * 
	 * @param arrays the arrays
	 * @return the merged array
	 */
	public static String[][] merge2(String[][]... arrays) {
		List<String[]> arrayList = new ArrayList<String[]>();
		for (String[][] array : arrays) {
			for (String[] item : array) arrayList.add(item);
		}
		return arrayList.toArray(new String[][]{});
	}
	
}
