/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import sargtex.crse.communicator.lordsandknights.LordsAndKnightsCommunicator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sargtex.crse.data.lordsandknights.Habitat;
import sargtex.crse.data.lordsandknights.MapPosition;
import sargtex.crse.exception.MissingCredentialException;

/**
 *
 * @author Pathos
 */
public class NotEnoughUnitsTest {
	
	public static void main(String[] args) throws MissingCredentialException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		LordsAndKnightsCommunicator communicator = new LordsAndKnightsCommunicator();
		
		// create credentials
		Map<String, String> credentials = new HashMap<String, String>();
		credentials.put("username", "itunes@lktk.de");
		credentials.put("password", "3b5fa6b03f6d57642f30697a989cedc6ce253cbfbab306effe2e13db0e31a673");
		credentials.put("worldID", "35");
		
		if (communicator.login(credentials)) {
			System.out.println("Anmeldung erfolgreich!");
			
			List<Habitat> habitatList = communicator.getFreeHabitats(new MapPosition(16008, 16608));
			System.out.println("Found "+habitatList.size()+" free habitats");
			
			// list free castles
			communicator.startTransit(144106, 145425);
		} else {
			System.out.println("Anmeldung fehlgeschlagen.");
		}
	}
	
}
