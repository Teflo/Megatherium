/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.communicator.data;

import megatherium.language.LanguageServer;

/**
 *
 * @author marti_000
 */
public class Language {
	private String code;
	private String name;
	public String getCode() {return this.code;}
	public String getName() {return this.name;}
	public String toString() {return LanguageServer.getInstance().get(this.getName());}
	
}
