/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import sargtex.util.FileUtil;

/**
 *
 * @author Pathos
 */
public class FileTest {
	
	public static void main(String[] args) {
		String path = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"LordsAndKnights.jar";
		if (path.endsWith(".jar")) {
			String[] parts = path.split("/");
			path.replaceAll(parts[parts.length - 1], "");
		}
		
		// check
		if (!path.endsWith("/")) path += "/";
		path += "data/";
		
		System.out.println(path);
	}
	
}
