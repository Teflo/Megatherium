/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.crse.data.lordsandknights;

import sargtex.crse.data.lordsandknights.Attack;
import sargtex.crse.communicator.lordsandknights.LordsAndKnightsCommunicator;
import com.google.gson.Gson;
import sargtex.gui.lordsandknights.bot.GameFrame;
import sargtex.gui.lordsandknights.bot.LoginFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.client.methods.HttpPost;
import sargtex.crse.exception.MissingCredentialException;
import sargtex.gui.lordsandknights.bot.WorldSelectionFrame;
import sargtex.util.HttpUtil;

/**
 *
 * @author Pathos
 */
public class Game {
	private static final String VERSION = "1.1.1beta1";
	private LordsAndKnightsCommunicator communicator;
	private LoginFrame loginFrame;
	private GameFrame gameFrame;
	private AutoAttack2 attackThread;
	private Gson gson = new Gson();
	private static Game instance;
	private Map<String, String> credentials = new HashMap<String, String>();
	
	private Game() {}
	
	/**
	 * Initializes the game and returns its object.
	 * 
	 * @return Game
	 */
	public static Game getInstance() {
		if (instance == null) instance = new Game();
		
		return instance;
	}
	
	/**
	 * Starts the game.
	 */
	public void init() {
		// validate the game license
		validateLicense();
		
		// fetch Communicator
		communicator = new LordsAndKnightsCommunicator();
			
		// first, the user has to be logged in
		login();
	}
	
	/**
	 * Checks whether the game runs on a valid license.
	 */
	private void validateLicense() {
		HttpPost request = new HttpPost("http://auerochse.spinshare.de/validateLicense.php");
		Map<String, String> params = new HashMap<String, String>();
		params.put("version", VERSION);
		String response = HttpUtil.execute(request, params);
		if (response.startsWith("0")) {
			JOptionPane.showMessageDialog(null, "Validierungsversuch fehlgeschlagen. Bitte benutzen Sie die aktuellste Version oder kontaktieren Sie den Entwickler.");
			System.exit(0);
		}
	}
	
	/**
	 * Returns the communicator.
	 * 
	 * @return LordsAndKnightsCommunicator the communicator
	 */
	public LordsAndKnightsCommunicator getCommunicator() {
		return this.communicator;
	}
	
	/**
	 * Returns a map with the login credentials for the user, fetched from the GUI.
	 * 
	 * @return Map<String, String> the credentials
	 */
	public void login() {
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		loginFrame.addLoginListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					credentials.put("username", loginFrame.getUsername());
					credentials.put("password", loginFrame.getPassword());
					
					// check whether login was successfull or not
					if (!communicator.login(credentials)) {
						JOptionPane.showMessageDialog(loginFrame, "Die Anmeldung ist fehlgeschlagen.");
					} else {
						loginFrame.setEnabled(false);
						loginFrame.setVisible(false);
						
						// ask user to select a world
						selectWorld();
					}
				} catch (MissingCredentialException ex) {
					System.err.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Asks the user which world he wants to use.
	 */
	public void selectWorld() {
		// open frame for world selection
		final WorldSelectionFrame frame = new WorldSelectionFrame();
		frame.setVisible(true);
		frame.addSubmitListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				communicator.setWorld(frame.getWorld());
						
				// user is now logged in, follow to main screen
				startup();
			}
			
		});
	}
	
	/**
	 * Starts the main game frame.
	 */
	public void startup() {
		// open game frame
		gameFrame = new GameFrame();
		gameFrame.setEnabled(true);
		gameFrame.setVisible(true);
		
		// start attacking thread
		attackThread = new AutoAttack2();
		attackThread.start();
	}
	
	/**
	 * Returns the attack thread.
	 */
	public AutoAttack2 getAttackThread() {return this.attackThread;}
	
	/**
	 * Returns the game frame.
	 * 
	 * @return GameFrame
	 */
	public GameFrame getGameFrame() {
		return this.gameFrame;
	}
	
	/**
	 * Adds a general notification.
	 * 
	 * @param String the message
	 */
	public void addNotification(String message) {
		getGameFrame().addNotificationMessage(new SimpleDateFormat("H:m:s").format(new Date(System.currentTimeMillis()))+" "+message);
	}

	/**
	 * Notifies the user that another auto attack was done.
	 */
	public void notifyAutoAttack(Attack attack) {
		Game.getInstance().addNotification("Angriff auf #"+attack.getDestinationHabitatID()+" durchgeführt");
	}
	
	/**
	 * Notifies the user that another auto attack was done.
	 * 
	 * @param attack	the object with the information about the attack
	 * @param skipped	if true, the notification implies that this attack was skipped
	 */
	public void notifyAutoAttack(Attack attack, boolean skipped) {
		if (!skipped) notifyAutoAttack(attack);
		else Game.getInstance().addNotification("Angriff auf #"+attack.getDestinationHabitatID()+" übersprungen");
	}

	/**
	 * Notifies about an attack
	 */
	void notifyAttack(Habitat source, Habitat target) {
		Game.getInstance().addNotification("Angriff von "+source.getName()+" auf "+target.getName()+" durchgeführt");
	}
	
	/**
	 * Notifies about an attack
	 */
	void notifyAttack(Habitat source, Habitat target, boolean skipped) {
		if (!skipped) notifyAttack(source, target);
		else Game.getInstance().addNotification("Angriff von "+source.getName()+" auf "+target.getName()+" übersprungen");
	}
	
}
