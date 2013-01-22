/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tc.ui;

import java.util.List;
import megatherium.data.store.Stores;
import megatherium.event.EventManager;
import megatherium.ui.EventPanel;
import tc.data.store.TextStore;
import textconnect.communicator.data.Text;

/**
 *
 * @author marti_000
 */
public class HomePanel extends EventPanel {

	/**
	 * Creates new form HomePanel
	 */
	public HomePanel() {
		initComponents();
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
        textList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();

        textList.setModel(new javax.swing.AbstractListModel() {
            private Text[] texts = ((TextStore) Stores.getInstance().getStore("textStore")).getItems().toArray(new Text[]{});
            public int getSize() { return texts.length; }
            public Object getElementAt(int i) { return texts[i]; }
        });
        textList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textListMouseClicked(evt);
            }
        });
        textList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                textListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(textList);

        jButton1.setText("Weiteren Text hinzufügen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        connectButton.setText("Verbinden (0)");
        connectButton.setEnabled(false);
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        viewButton.setText("Ansehen (0)");
        viewButton.setEnabled(false);
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(viewButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(connectButton)
                    .addComponent(viewButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EventManager.getInstance().fireEvent("textconnect.ui.text.create.show");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_textListValueChanged
        if (evt.getValueIsAdjusting()) return;
		
		// get values
		Text[] texts = ((List<Text>) textList.getSelectedValuesList()).toArray(new Text[]{});
		connectButton.setText("Verbinden ("+texts.length+")");
		connectButton.setEnabled((texts.length > 0));
		
		// set view button
		viewButton.setText("Ansehen ("+texts.length+")");
		viewButton.setEnabled((texts.length == 1 || texts.length == 2));
    }//GEN-LAST:event_textListValueChanged

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        Text[] texts = ((List<Text>) textList.getSelectedValuesList()).toArray(new Text[]{});
		EventManager.getInstance().fireEvent("textconnect.ui.text.connect.show", texts, null);
		EventManager.getInstance().addListener("textconnect.ui.text.connect.show", this, "dosth");
    }//GEN-LAST:event_connectButtonActionPerformed

	public void dosth(Text[] texts) {
		
	}
	
    private void textListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textListMouseClicked
        
    }//GEN-LAST:event_textListMouseClicked

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        Text[] texts = ((List<Text>) textList.getSelectedValuesList()).toArray(new Text[]{});
		EventManager.getInstance().fireEvent("textconnect.ui.text.connection.show", texts, null);
    }//GEN-LAST:event_viewButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList textList;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}