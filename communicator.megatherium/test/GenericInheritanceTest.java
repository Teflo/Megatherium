/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.lang.reflect.InvocationTargetException;
import megatherium.request.MegatheriumRequest;

/**
 *
 * @author marti_000
 */
public class GenericInheritanceTest {
	
	public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		
		
		MegatheriumRequest request = MegatheriumRequest.create("user", "login");
		
		request.getClass().getMethod("foo").invoke(request);
		System.out.println((request.getClass().getSuperclass().getSuperclass() == Object.class));
		System.out.println(request.getClass().getSuperclass().getCanonicalName());
		Class<?>[] classes = request.getClass().getDeclaredClasses();
		for (Class<?> clas : classes) System.out.println(clas.getCanonicalName());
	}
	
}
