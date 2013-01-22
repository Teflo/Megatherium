/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import megatherium.util.ReportUtil;

/**
 *
 * @author marti_000
 */
public class TimeInputField extends JFormattedTextField {
	
	public TimeInputField() {
		try {
			this.setFormatter(new MaskFormatter("##.##.#### ##:##:##"));
		} catch (ParseException ex) {
			ReportUtil.getInstance().add(ex);
		}
		this.setInputVerifier(new InputVerifier() {
            private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            public boolean verify(JComponent input) {
                boolean res = true;
                JTextComponent tc = (JTextComponent)input;
                String newContent = tc.getText();
                if (newContent.length() > 0) {
                    try {
                        Date d = sdf.parse(newContent);
                        
                        if (!sdf.format(d).equals(newContent)) {
                            tc.selectAll();
                            res = false;
                        }
                    }
                    catch (ParseException ex) {
                        tc.selectAll();
                        res = false;
                    }            
                }
                return res;
            }
        });
	}
	
	/**
	 * Returns the date.
	 * 
	 * @return the date
	 */
	public Date getDate() {
		Date date = new Date();
		date.setTime(this.getTimestamp());
		return date;
	}
	
	/**
	 * Returns the UNIX timestamp.
	 * 
	 * @return the timestamp
	 */
	public long getTimestamp() {
		try {
			return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(this.getText()).getTime();
		} catch (ParseException ex) {
			ReportUtil.getInstance().add(ex);
		}
		return 0;
	}
	
}
