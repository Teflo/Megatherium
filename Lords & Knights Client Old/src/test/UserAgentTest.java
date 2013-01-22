/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import sargtex.util.HttpUtil;

/**
 *
 * @author Pathos
 */
public class UserAgentTest {
	
	public static void main(String[] args) {
		System.out.println(HttpUtil.execute("http://auerochse.spinshare.de/userAgentCheck.php"));
	}
	
}
