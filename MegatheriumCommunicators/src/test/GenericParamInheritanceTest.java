/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.lang.reflect.InvocationTargetException;
import megatherium.util.ClassUtil;

/**
 *
 * @author SargTeX
 */
public class GenericParamInheritanceTest extends GenericInheritanceTest{
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
		Object obj = new GenericParamInheritanceTest();
		Object[] objects = new Object[] {new GenericParamInheritanceTest()};
		obj.getClass().getMethod("myMeth", GenericInheritanceTest.class).invoke(obj, objects);
	}
	
	public void myMeth(GenericInheritanceTest test) {
		System.out.println("Hello World");
	}
	
}
