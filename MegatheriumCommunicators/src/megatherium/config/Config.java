/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import megatherium.util.ClassUtil;
import megatherium.util.FileUtil;
import megatherium.util.JsonUtil;
import megatherium.util.ReportUtil;

/**
 *
 * @author Pathos
 */
public class Config {
	private static Map<String, Object> data;
	
	private static void init() {
		if (data == null) load();
	}
	
	/**
	 * Returns the data from the config file.
	 * If the data does not have an entry in the config file, an empty entry will be created.
	 * 
	 * @param name the name from the config item
	 * @param classType the class type from the item
	 * @return the item value
	 */
	public static <T> T get(String name, Class<? extends T> classType) {
		init();
		if (!data.containsKey(name)) data.put(name, null);
		return ClassUtil.cast(data.get(name), classType);
	}
	
	/**
	 * Sets a new item.
	 * The config file will be rewritten.
	 * 
	 * @param name the name of the item
	 * @param value the valu of the item
	 */
	public static void set(String name, Object value, Class<?> classType) {
		init();
		data.put(name, ClassUtil.cast(value, classType));
		save();
	}
	
	/**
	 * Saves the config to the config.data file.
	 */
	public static void save() {
//		try {
//			FileUtil.write("config.data", JsonUtil.toJson(data));
//		} catch (IOException ex) {
//			ReportUtil.getInstance().add(ex);
//		}
	}
	
	/**
	 * Loads the config.
	 */
	private static void load() {
		try {
			String content = FileUtil.getResourceContent("config.data");
			System.out.println(content);
			data = JsonUtil.getGson().fromJson(content, new HashMap<String, Object>().getClass());
		} catch(IOException ex) {
			ReportUtil.getInstance().add(ex);
		}
	}
	
}
