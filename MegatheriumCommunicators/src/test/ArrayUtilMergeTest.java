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
public class ArrayUtilMergeTest {
	
	public static void main(String[] args) {
		String[][] array1 = new String[][] {
			{"Lea", "Schmidt"},
			{"Marvin", "Juchems-Fellinger"},
			{"Florian Valentin", "Valerius Ben Abdeslam Ben Modeslam Ben Omar Mohamed Abdeslam"}
		};
		
		String[][] array2 = new String[][] {
			{"David", "Wade"},
			{"Martin", "Bories"}
		};
		
		String[][] array = ArrayUtil.merge2(array1, array2);
		
		for (String[] arr : array) {
			System.out.println(arr[0]+" "+arr[1]);
		}
	}
	
}
