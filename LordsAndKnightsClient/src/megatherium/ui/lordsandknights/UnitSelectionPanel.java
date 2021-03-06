/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui.lordsandknights;

import java.util.HashMap;
import java.util.Map;
import megatherium.ui.EventPanel;
import megatherium.communicator.data.lordsandknights.megatherium.Habitat;
import megatherium.event.EventManager;

/**
 *
 * @author marti_000
 */
public class UnitSelectionPanel extends EventPanel {
	private Map<String, String> amounts = new HashMap<String, String>();

	/**
	 * Creates new form UnitSelectionPanel
	 */
	public UnitSelectionPanel() {
		initComponents();
	}
	
	/**
	 * Sets the current habitat, loads the units.
	 * 
	 * @param habitat the habitat
	 */
	public void setHabitat(Habitat habitat) {
		UnitTableModel model = new UnitTableModel(habitat.getUnits());
		this.jTable1.setModel(model);
		this.repaint();
	}
	
	/**
	 * Returns the amounts.
	 * 
	 * @return the amounts
	 */
	public Map<String, String> getAmounts() {
		return this.amounts;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        save = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTable1);

        save.setText("Okay");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        cancel.setText("Abbrechen");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(save)
                        .addGap(18, 18, 18)
                        .addComponent(cancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save)
                    .addComponent(cancel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        this.amounts = ((UnitTableModel) this.jTable1.getModel()).getAmounts();
		EventManager.getInstance().fireEvent("lordsandknights.ui.unit.selection.save", this.amounts);
    }//GEN-LAST:event_saveActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        EventManager.getInstance().fireEvent("lordsandknights.ui.unit.selection.cancel");
    }//GEN-LAST:event_cancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables

}
