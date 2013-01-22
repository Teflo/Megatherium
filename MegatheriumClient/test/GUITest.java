/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import megatherium.ui.HeaderPanel;

/**
 *
 * @author marti_000
 */
public class GUITest {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel panel1 = new HeaderPanel();
		JPanel panel2 = new JPanel();
//		panel1.add(new JLabel("Hallo Welt"));
		panel2.add(new JLabel("Hallo Welt"));
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.WEST);
		frame.pack();
		frame.setVisible(true);
	}
	
}
