/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudstorage.ui;

import cloudstorage.communicator.GoogleDriveCommunicator;
import cloudstorage.communicator.data.GoogleDriveLoginInformation;
import megatherium.application.Application;
import megatherium.communicator.data.ILoginInformation;
import megatherium.event.EventManager;
import megatherium.ui.EventPanel;

/**
 *
 * @author SargTeX
 */
public class GoogleDriveLoginInformationPanel extends EventPanel {

	/**
	 * Creates new form GoogleDriveLoginInformationPanel
	 */
	public GoogleDriveLoginInformationPanel() {
		initComponents();
	}
	
	/**
	 * Sets the authentication url.
	 * 
	 * @param url the authentication url
	 */
	public void setAuthenticationURL(String url) {
		this.authenticationURL.setText(url);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        authenticationURL = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        authenticationCode = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Bitte öffnen Sie den folgenden Link im Browser, um eine sichere Authentifizierung durch zu führen:");

        jLabel2.setText("Bestätigungs-Schlüssel:");

        jButton1.setText("Okay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Abbrechen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(authenticationURL)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(authenticationCode))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(authenticationURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(authenticationCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EventManager.getInstance().fireEvent("megatherium.ui.account.login.information.cancel");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (this.authenticationCode.getText().isEmpty()) {
			Application.getController().showNotification("Sie müssen zunächst einen Authentifizierungs-Code eingeben. Rufen Sie dazu die URL im Browser auf und erlauben Sie unseren Zugriff auf Google Drive.");
			return;
		}
		
		// fetch credentials
		ILoginInformation information = GoogleDriveCommunicator.getInstance().getLoginInformation(this.authenticationCode.getText());
		EventManager.getInstance().fireEvent("megatherium.ui.account.login.information.save", information);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authenticationCode;
    private javax.swing.JTextField authenticationURL;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}