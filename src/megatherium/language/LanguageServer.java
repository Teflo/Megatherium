/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.language;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.util.FileUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class LanguageServer {
	private static LanguageServer instance;
	private Map<String, String> items = new HashMap<String, String>();
	
	/**
	 * Private for singleton pattern.
	 */
	private LanguageServer() {
		this.load("de.lang");
	}
	
	/**
	 * Returns the current instance of this class.
	 * 
	 * @return the current instance
	 */
	public static LanguageServer getInstance() {
		if (instance == null) instance = new LanguageServer();
		
		return instance;
	}
	
	/**
	 * Loads the translation information by the given filename.
	 * 
	 * @param filename the name of the file
	 */
	protected void load(String filename) {
		filename = FileUtil.getDataPath()+filename;
		
		// do THAT SHIT!!
		try {
			String text = "";
			
			// read file content
			Path path = Paths.get(filename);
			for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
				String[] parts = line.split("=", 2);
				if (parts.length == 2) {
					System.out.println("Added: "+parts[0]+" => "+parts[1]);
					this.items.put(parts[0], parts[1]);
				}
				else ReportUtil.getInstance().add("ERROR: Invalid translation line @ "+filename+" (line content: \""+line+"\")");
			}
		} catch (IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
	}
	
	/**
	 * Returns the current translation for the item.
	 * 
	 * @param itemName the name of the item
	 * @return the current translation
	 */
	public String get(String itemName) {
		if (!this.items.containsKey(itemName)) {
			for (String key : this.items.keySet()) System.out.println("Exists: \""+key+"\"");
			System.out.println("Doesnt exist: \""+itemName+"\"");
			return itemName;
		}
		return this.items.get(itemName);
	}
	
}
