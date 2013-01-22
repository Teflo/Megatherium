/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tc.ui;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import megatherium.ui.EventPanel;
import tc.data.store.ConnectionStore;
import textconnect.communicator.data.Connection;
import textconnect.communicator.data.Text;

/**
 *
 * @author marti_000
 */
public class TextConnectionPanel extends EventPanel {
	private Text[] texts;
	private ConnectionStore connectionStore;
	private Connection connection;
	
	/**
	 * Creates new form TextConnectionPanel
	 */
	public TextConnectionPanel() {
		initComponents();
	}
	
	/**
	 * Sets the texts.
	 * 
	 * @param texts the texts that will be edited
	 */
	public void setTexts(Text[] texts) {
		if (texts.length == 1) texts = new Text[] {texts[0], texts[0]};
		this.texts = texts;
		
		// set text values
		this.text1.setText(texts[0].getText());
		this.text1.setBorder(BorderFactory.createTitledBorder(texts[0].getTitle()));
		this.textComment1.setText(this.texts[0].getComment());
		this.text2.setText(texts[1].getText());
		this.text2.setBorder(BorderFactory.createTitledBorder(texts[1].getTitle()));
		this.textComment2.setText(texts[1].getComment());
		
		// load selections
		connectionStore = new ConnectionStore(texts[0].getID(), texts[1].getID());
		((DefaultListModel<Connection>) jList1.getModel()).removeAllElements();
		for (Connection connection : connectionStore.getItems()) {
			((DefaultListModel<Connection>) jList1.getModel()).addElement(connection);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        textScroll1 = new javax.swing.JScrollPane();
        text1 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        textComment1 = new javax.swing.JTextPane();
        jSplitPane4 = new javax.swing.JSplitPane();
        textScroll2 = new javax.swing.JScrollPane();
        text2 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        textComment2 = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setOneTouchExpandable(true);

        jSplitPane2.setDividerLocation(290);

        jSplitPane3.setDividerLocation(400);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setOneTouchExpandable(true);

        text1.setBackground(new java.awt.Color(240, 240, 240));
        text1.setBorder(javax.swing.BorderFactory.createTitledBorder("Text 1"));
        text1.setContentType("text/html"); // NOI18N
        textScroll1.setViewportView(text1);

        jSplitPane3.setTopComponent(textScroll1);

        textComment1.setBackground(new java.awt.Color(240, 240, 240));
        textComment1.setBorder(javax.swing.BorderFactory.createTitledBorder("Kommentar"));
        jScrollPane6.setViewportView(textComment1);

        jSplitPane3.setRightComponent(jScrollPane6);

        jSplitPane2.setLeftComponent(jSplitPane3);

        jSplitPane4.setDividerLocation(400);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane4.setOneTouchExpandable(true);

        text2.setBackground(new java.awt.Color(240, 240, 240));
        text2.setBorder(javax.swing.BorderFactory.createTitledBorder("Text 2"));
        text2.setContentType("text/html"); // NOI18N
        textScroll2.setViewportView(text2);

        jSplitPane4.setTopComponent(textScroll2);

        textComment2.setBackground(new java.awt.Color(240, 240, 240));
        textComment2.setBorder(javax.swing.BorderFactory.createTitledBorder("Kommentar"));
        jScrollPane5.setViewportView(textComment2);

        jSplitPane4.setRightComponent(jScrollPane5);

        jSplitPane2.setRightComponent(jSplitPane4);

        jSplitPane1.setRightComponent(jSplitPane2);

        jList1.setModel(new DefaultListModel<Connection>());
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        if (evt.getValueIsAdjusting()) return;
		
		// set connection
		this.connection = (Connection) jList1.getSelectedValue();
		if (this.connection == null) return;
		
		// scroll
		this.text1.setCaretPosition(this.connection.getStartIndex1());
		this.text2.setCaretPosition(this.connection.getStartIndex2());
		
		// mark text
		String text = this.text1.getText();
		String part1 = text.substring(0, this.connection.getStartIndex1());
		String part2 = text.substring(this.connection.getStartIndex1(), this.connection.getEndIndex1());
		String part3 = text.substring(this.connection.getEndIndex1());
		this.text1.setText(part1+"<div style='background-color: yellow'>"+part2+"</div>"+part3);
		
		// mark text
		String text2 = this.text2.getText();
		part1 = text2.substring(0, this.connection.getStartIndex2());
		part2 = text2.substring(this.connection.getStartIndex2(), this.connection.getEndIndex2());
		part3 = text2.substring(this.connection.getEndIndex2());
		this.text2.setText(part1+"<div style='background-color: yellow'>"+part2+"</div>"+part3);
    }//GEN-LAST:event_jList1ValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JTextPane text1;
    private javax.swing.JTextPane text2;
    private javax.swing.JTextPane textComment1;
    private javax.swing.JTextPane textComment2;
    private javax.swing.JScrollPane textScroll1;
    private javax.swing.JScrollPane textScroll2;
    // End of variables declaration//GEN-END:variables
}
