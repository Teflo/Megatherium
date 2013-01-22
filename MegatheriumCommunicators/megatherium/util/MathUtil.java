/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

/**
 *
 * @author Pathos
 */
public class MathUtil {
	
	/**
	 * Returns a random double value >= low and <= high.
	 * 
	 * @param low
	 * @param high
	 * @return the random value
	 */
	public static double getRandom(double low, double high) {
		return (Math.random() * (high-low) + low);
	}
	
	/**
	 * Returns the difference between 2 coordinates/points.
	 * 
	 * @param coordinate1
	 * @param coordinate2
	 * @return coordinate1 - coordinate2
	 */
	public static int getDifference(int coordinate1, int coordinate2) {
		int diff = coordinate1-coordinate2;
		return (diff > 0) ? diff : -diff;
	}
	
}
