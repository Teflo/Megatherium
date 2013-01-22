/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import megatherium.util.ArrayUtil;

/**
 *
 * @author marti_000
 */
public class ArrayUtilRemoveLastTest {
	
	public static void main(String[] args) {
		Object[] objects = new Object[] {null, null, null, null};
		objects = ArrayUtil.removeLast(objects);
		System.out.println("Length: "+objects.length);
	}
	
}
