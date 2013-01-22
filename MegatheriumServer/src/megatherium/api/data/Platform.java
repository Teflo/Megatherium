/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.api.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import megatherium.util.ClassUtil;
import megatherium.util.JsonUtil;
import sargtex.sql.Database;

/**
 *
 * @author SargTeX
 */
public class Platform extends DatabaseObject {
	
	public Platform(String name) throws SQLException {super(name);}
	public Platform(ResultSet rs) throws SQLException {super(rs);}
	public Platform(Map<String, String> data) {super(data);}
	public String getName() {return this.get("name");}
	public String getLabel() {return this.get("label");}
	public String getType() {return this.get("type");}
	public <T> T getData(Class<T> classType) {return JsonUtil.getGson().fromJson(this.get("data"), classType);}
	public Platform setLabel(String label) {this.set("label", label); return this;}
	public Platform setType(String type) {this.set("type", type); return this;}
	public Platform setData(String data) {this.set("data", data); return this;}

	@Override
	protected String[] getFields() {
		return new String[] {"name", "label", "type", "data"};
	}

	@Override
	protected String getTableName() {
		return "platform";
	}
	
	@Override
	protected String getIDProperty() {
		return "name";
	}
	
	/**
	 * Returns all existing platforms.
	 * 
	 * @return the platform list
	 */
	public static Platform[] getAll() throws SQLException {
		List<Platform> platformList = new ArrayList<Platform>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM platform");
		while (rs.next()) platformList.add(new Platform(rs));
		return platformList.toArray(new Platform[]{});
	}
	
	/**
	 * Returns all platforms containing that type or a subtype.
	 * 
	 * @param type the type
	 * @return the platforms
	 */
	public static Platform[] getPlatforms(String type) throws SQLException {
		List<Platform> platformList = new ArrayList<Platform>();
		ResultSet rs = Database.getInstance().fetch("SELECT * FROM platform WHERE type LIKE '?%'", new String[] {type});
		while (rs.next()) platformList.add(new Platform(rs));
		return platformList.toArray(new Platform[]{});
	}
	
}
