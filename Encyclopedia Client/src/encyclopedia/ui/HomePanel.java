/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.ui;

import encyclopedia.communicator.mediawiki.data.Article;
import encyclopedia.communicator.mediawiki.data.Link;
import javax.swing.DefaultListModel;
import megatherium.data.store.Stores;
import megatherium.event.EventManager;
import megatherium.ui.EventPanel;
import megatherium.ui.LabeledTextField;

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
	
	public void setArticles(Article[] articles) {
		this.articleList.setListData(articles);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new LabeledTextField("Suche nach Artikeln");
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        articleList = new javax.swing.JList();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("Suchen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        articleList.setModel(new DefaultListModel<Article>());
        articleList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                articleListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(articleList);

        jButton2.setText("Einstellungen");
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EventManager.getInstance().fireEvent("encyclopedia.ui.search", this.jTextField1.getText());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void articleListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_articleListValueChanged
//        Article article = (Article) articleList.getSelectedValue();
//		EventManager.getInstance().fireEvent("encyclopedia.ui.article.show", article.getTitle());
    }//GEN-LAST:event_articleListValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EventManager.getInstance().fireEvent("encyclopedia.ui.settings.show");
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList articleList;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
