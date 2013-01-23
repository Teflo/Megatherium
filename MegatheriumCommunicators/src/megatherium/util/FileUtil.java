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
	
	/**
	 * Returns the file size of the given files in bytes.
	 * 
	 * @param files a list of files
	 * @return the file size
	 */
	public static long getFileSize(File[] files) {
		long size = 0;
		for (File file : files) size += file.length();
		return size;
	}
	
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
	
	/**
	 * Writes the content into a file within the data directory. If the file doesn't exist, creates it.
	 * 
	 * @param name the name of the file (including the paths aso)
	 * @param content the content of the file
	 * @throws IOException
	 */
	public static void write(String name, String content) throws IOException {
		write(getDataPath(), name, content, true);
	}

	/**
	 * Writes the content into a file.
	 * 
	 * @param path the path where the file is located
	 * @param name the name of the file
	 * @param content the content of the file
	 * @param autoCreate if true, if the file doesn't exist it will be created
	 * @throws IOException 
	 */
	public static void write(String path, String name, String content, boolean autoCreate) throws IOException {
		// manipulate file name
		name = name.replaceAll("%20", " ");
		
		// get file
		File file = new File(path, name);
		if (!file.getParentFile().exists() && autoCreate && !file.getParentFile().mkdirs()) throw new IOException("Could not create folders");
		if (!file.exists() && autoCreate) file.createNewFile();
		
		// write file
		FileWriter fstream = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);
		
		// close output stream
		out.close();
	}
}
