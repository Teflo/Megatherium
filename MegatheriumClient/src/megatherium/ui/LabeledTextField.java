/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 * This class extends from the JTextField-class and behaves like it, except you are able to set a default value that will be written in light grey letters.
 * 
 * @author SargTeX
 */
public class LabeledTextField extends JTextField {
	private String label;
	private Color color;
	private Color labelColor = Color.LIGHT_GRAY;
	
	/**
	 * Initializes the text field.
	 * 
	 * @param label the default text field value/label
	 */
	public LabeledTextField(String label) {
		this.label = label;
		
		// initialize
		this.color = this.getForeground();
		super.setForeground(this.labelColor);
		addLabel();
		
		// register events
		this.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				removeLabel();
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().isEmpty()) addLabel();
			}
		});
	}
	
	/**
	 * Removes the label from the text field.
	 */
	public void removeLabel() {
		this.setText("");
		this.setForeground(this.color);
	}
	
	/**
	 * Writes the label into the text field.
	 */
	public void addLabel() {
		this.setText(this.label);
		super.setForeground(this.labelColor);
	}
	
	@Override
	public void setForeground(Color color) {
		this.color = color;
		super.setForeground(color);
	}
	
	/**
	 * Sets the label color.
	 * 
	 * @param color the color of the label
	 */
	public void setLabelColor(Color color) {
		this.labelColor = color;
	}
	
	@Override
	public String getText() {
		if (super.getText().equals(this.label)) return "";
		return super.getText();
	}
	
}
