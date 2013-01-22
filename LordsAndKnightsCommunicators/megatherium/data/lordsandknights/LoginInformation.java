/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.data.lordsandknights;

import megatherium.communicator.data.ILoginInformation;

/**
 *
 * @author marti_000
 */
public class LoginInformation extends ILoginInformation {
	private String login;
	private String password;
	private World world;
	public String getLogin() {return this.login;}
	public String getPassword() {return this.password;}
	public World getWorld() {return this.world;}
	public LoginInformation(String login, String password, World world) {this.login = login; this.password = password; this.world = world;}
	
}
