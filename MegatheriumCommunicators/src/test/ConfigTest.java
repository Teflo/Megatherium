/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import megatherium.config.Config;

/**
 *
 * @author marti_000
 */
public class ConfigTest {
	
	public static void main(String[] args) {
		System.out.println(Config.get("userAgent", String.class));
	}
	
}
