/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import com.google.gson.internal.Primitives;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti_000
 */
public class ClassUtil {
	
	/**
	 * Returns an array with the class types of the objects.
	 * 
	 * @param objects an array with some objects
	 * @return an array containing the class types of the objects
	 */
	public static Class<?>[] getClassTypes(Object[] objects) {
		List<Class<?>> typeList = new ArrayList<Class<?>>();
		if (objects != null) {
			for (Object obj : objects) {
				if (obj != null) typeList.add(obj.getClass());
				else typeList.add(Object.class);
			}
		}
		return typeList.toArray(new Class<?>[]{});
	}
	
	/**
	 * Casts the object into the class type.
	 * 
	 * @param object the object that will be casted
	 * @param classType the class type
	 * @return the casted object
	 */
	public static <T> T cast(Object object, Class<? extends T> classType) {
		return Primitives.wrap(classType).cast(object);
	}
	
}
