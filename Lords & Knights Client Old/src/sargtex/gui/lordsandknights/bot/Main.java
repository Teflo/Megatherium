/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sargtex.gui.lordsandknights.bot;

import sargtex.crse.data.lordsandknights.Game;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Pathos
 */
public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
		} catch (UnsupportedLookAndFeelException ex) {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		
		Game.getInstance().init();
	}
	
}
