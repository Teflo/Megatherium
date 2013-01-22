/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encyclopedia.ui;

import encyclopedia.communicator.MediawikiCommunicator;
import encyclopedia.communicator.mediawiki.data.Article;
import encyclopedia.communicator.mediawiki.data.Link;
import megatherium.ui.EventPanel;

/**
 *
 * @author marti_000
 */
public class ArticlePanel extends EventPanel {

	/**
	 * Creates new form ArticlePanel
	 */
	public ArticlePanel() {
		initComponents();
	}
	
	public void setArticle(Article article) {
		this.title.setText(article.getTitle());
		this.text.setText(article.getText());
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
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        text = new javax.swing.JTextPane();

        jLabel1.setText("Titel:");

        title.setText("jLabel2");

        text.setEditable(false);
        text.setBackground(new java.awt.Color(240, 240, 240));
        text.setBorder(javax.swing.BorderFactory.createTitledBorder("Artikel"));
        text.setContentType("text/html"); // NOI18N
        text.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(text);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(title)
                        .addGap(0, 304, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textCaretUpdate
        
    }//GEN-LAST:event_textCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane text;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
