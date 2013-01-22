/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import sargtex.util.MathUtil;

/**
 *
 * @author Pathos
 */
public class RandomTest {
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; ++i) {
			double time = MathUtil.getRandom(500, 1000);
			double exp = MathUtil.getRandom(1, 1.2);
			time = Math.pow(time, exp);
			System.out.println((time/1000)+" seconds");
		}
	}
	
}
