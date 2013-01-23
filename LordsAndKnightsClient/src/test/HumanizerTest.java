/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import megatherium.communicator.LordsAndKnightsCommunicator;
import megatherium.config.Config;
import megatherium.data.lordsandknights.Session;
import megatherium.data.lordsandknights.World;
import megatherium.humanizer.LordsAndKnightsHumanizer;
import megatherium.util.JsonUtil;

/**
 *
 * @author SargTeX
 */
public class HumanizerTest {
	
	public static void main(String[] args) {
		Config.set("debug", false, boolean.class);
		LordsAndKnightsHumanizer.initialize();
		
		// do stuff
		LordsAndKnightsCommunicator.getInstance().login("martin.christian.bories@gmail.com", "thebospeler");
		World[] worldList = LordsAndKnightsCommunicator.getInstance().getWorldList();
		World world = null;
		for (int i = 0; i < worldList.length; ++i) if (worldList[i].getID() == 4) world = worldList[i];
		Session session = LordsAndKnightsCommunicator.getInstance().changeWorld(world);
		System.out.println(JsonUtil.toJson(session));
	}
	
}
