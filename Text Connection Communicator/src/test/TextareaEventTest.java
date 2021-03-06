/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author marti_000
 */
public class TextareaEventTest extends javax.swing.JFrame {

	/**
	 * Creates new form TextareaEventTest
	 */
	public TextareaEventTest() {
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
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("# LanguageServer: Dateiverwaltung verbessern\n - Applikationsabhängige Sprachdateien, d.h. eigene Sprachdatei für LAK-Client und eigene für Megatherium-Client\nBeispiele:\n\nLanguageServer.getInstance().add(\"de\", \"megatherium.de.lang\");\nLanguageServer.getInstance().add(\"en\", \"megatherium.en.lang\");\nLanguageServer.getInstance().add(\"de\", \"lordsandknights.de.lang\");\nLanguageServer.getInstance().add(\"en\", \"lordsandknights.en.lang\");\n\n\n\n\n# EventManager: Namespaces entfernen\n - Namespaces sollen durch punktgetrennte Namespaces im Namen ersetzt werden\n - Man muss auf unter-Namespaces hören können, bspw: Listener hört auf \"megatherium.request.*\" und wird über alle Anfragen vom Megatheriumprojekt benachrichtigt\n - Beispiele für Namespaces\n  - lordsandknights.* -> Alle (unter-) Namespaces des LordsAndKnights-Projektes\n   - lordsandknights.request.*\n   - lordsandknights.ui.*\n  - megatherium.* -> Alle (unter-)Namespaces des Megatherium-Kernprojektes\n   - megatherium.request.*\n\t- megatherium.request.user.create\n\t- megatherium.request.user.login\n   - megatherium.ui.*\n\n{\n\t\"megatherium.request.*\": [#obj, #obj, ...],\n\t\"megatherium.request.\n}\n\n{\n\teventListeners: [],\n\tcategories: {\n\t\t\"megatherium\": {\n\t\t\teventListeners: [],\n\t\t\tcategories: {\n\t\t\t\t\"request\": {\n\t\t\t\t\teventListeners: [],\n\t\t\t\t\tcategories: {\n\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t}\n}\n\n$categories[\"megatherium\"] = {\n\t\"eventListeners\": {\n\n\t},\n\tcategories:\n};\n\n\n# Controller: References\nReferences einbauen: Automatische Verlinkung von Events zu Methoden (statt einzelner Initialisierung über EventManager)\n\n\"showAttackCreate\" => \"showAttackCreate\"\nnew MapBuilder<String, String>().set(\"showAttackCreate\", \"showAttackCreate\").set(\"mymeme\", \"mymeme\");\nreturn new String[][] {\n\t{\"showAttackCreate\", \"showAttackCreate\"},\n\t{\"mymeme\", \"mymeme\"},\n\t{\"myCustomEventMthrfckr\", \"myCustomOtherMethodMthrfckr\"}\n};");
        jTextArea1.setAutoscrolls(false);
        jTextArea1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextArea1CaretUpdate(evt);
            }
        });
        jTextArea1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTextArea1MouseDragged(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseDragged
        
    }//GEN-LAST:event_jTextArea1MouseDragged

    private void jTextArea1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextArea1CaretUpdate
        System.out.println("Mark: "+evt.getMark()+"\nDot: "+evt.getDot());
    }//GEN-LAST:event_jTextArea1CaretUpdate

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TextareaEventTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TextareaEventTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TextareaEventTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TextareaEventTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TextareaEventTest().setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
