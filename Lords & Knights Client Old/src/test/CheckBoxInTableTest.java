/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.*;
import javax.swing.table.*;

public class CheckBoxInTableTest {
  public static void main(String [] args) throws Exception {
    DefaultTableModel model = new DefaultTableModel(null, new String [] {"CheckMe", "Value"}) {
                                public Class getColumnClass(int c) {
                                  switch (c) {
                                    case 0: return Boolean.class;
                                    default: return String.class;
                                  }   
                                } };
    JTable table = new JTable(model);
    JFrame frame = new JFrame("CheckBox Test");
    frame.add(table);
    model.addRow(new Object [] {true, "This is true"});
    model.addRow(new Object [] {false, "This is false"});
    frame.pack(); frame.validate();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}