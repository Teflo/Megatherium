/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import sargtex.crse.communicator.lordsandknights.LordsAndKnightsCommunicator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sargtex.crse.data.lordsandknights.Habitat;
import sargtex.crse.data.lordsandknights.MapPosition;
import sargtex.crse.data.lordsandknights.World;

/**
 *
 * @author Pathos
 */
public class LoginTest {
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		LordsAndKnightsCommunicator communicator = new LordsAndKnightsCommunicator();
		
		// create credentials
		Map<String, String> credentials = new HashMap<String, String>();
		credentials.put("username", "itunes@lktk.de");
		credentials.put("password", "3b5fa6b03f6d57642f30697a989cedc6ce253cbfbab306effe2e13db0e31a673");
		credentials.put("worldID", "21");
		
		if (communicator.login(credentials)) {
			System.out.println("Anmeldung erfolgreich!");
			communicator.setWorld(21);
			
			// get world list
			for (int i = 0; i < communicator.getSession().getPlayer().getHabitatList().length; ++i) {
				System.out.println(communicator.getSession().getPlayer().getHabitatList()[i].getPosition());
			}
			
			// get something
			for (Habitat habitat : communicator.getHabitats(communicator.getSession().getPlayer().getHabitatList()[0].getPosition())) {
				System.out.println("Found habitat: "+habitat.getName());
			}
		} else {
			System.out.println("Anmeldung fehlgeschlagen.");
		}
	}
	
}
