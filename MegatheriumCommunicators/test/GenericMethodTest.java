/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import megatherium.util.ClassUtil;

/**
 *
 * @author marti_000
 */
public class GenericMethodTest {

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		GenericMethodTest object = new GenericMethodTest();
		String methodName = "myMethod";
		Object[] values = new Object[] {"Addition", 7, 4};
		callGeneric(object, methodName, values);
	}
	
	public static void callGeneric(Object object, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?>[] types = ClassUtil.getClassTypes(parameters);
		object.getClass().getMethod(methodName, types).invoke(object, parameters);
	}

	public void myMethod(String bla, Integer a, Integer b) {
		System.out.println("bla = "+bla+"\na="+a+"\nb="+b);
		System.out.println(bla + ": " + (a + b));
	}
}
