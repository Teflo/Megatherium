/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.communicator.data;

import megatherium.util.JsonUtil;

/**
 *
 * @author marti_000
 */
public class Platform {
	private String name;
	private String label;
	private String type;
	private String data;
	public String getName() {return this.name;}
	public String getLabel() {return this.label;}
	public String getType() {return this.type;}
	public String getData() {return this.data;}
	public <T> T getData(Class<? extends T> classType) {return JsonUtil.getGson().fromJson(this.getData(), classType);}
	public String toString() {return this.getLabel();}
	
}
