/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.util;

import java.io.*;

/**
 *
 * @author Pathos
 */
public class FileUtil {
	
	public static String getDataPath() {
		String path = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (path.endsWith(".jar")) {
			String[] parts = path.split("/");
			path = path.replaceAll(parts[parts.length - 1], "");
		}
		
		// check
		if (!path.endsWith("/")) path += "/";
		if (path.startsWith("/")) path = path.substring(1);
		path += "data/";
		
		return path;
	}

	/**
	 * @param filename	the name of the file inside of the resource directory
	 * @return the content of the resource file
	 */
	public static String getResourceContent(String filename) throws FileNotFoundException, IOException {
		filename = getDataPath()+filename;
		filename = filename.replaceAll("%20", " ");
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = "";
		String file = "";
		while ((line = reader.readLine()) != null) {
			file += line + "\r\n";
		}
		return file;
	}

	public static void writeResource(String filename, String content) throws IOException {
		// manipulate file name
		filename = getDataPath()+filename;
		filename = filename.replaceAll("%20", " ");
		
		// write file
		FileWriter fstream = new FileWriter(filename);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		
		// close output stream
		out.close();
	}
}
