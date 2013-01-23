/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * copied from: http://www.reisinger.ws/2009/08/02/stringinputstream-stringoutputstream/
 * TODO: ask for permission
 * @author SargTeX
 */
public class StringOutputStream extends OutputStream {
    StringWriter stringWriter;
 
    public StringOutputStream() {
        this.stringWriter = new StringWriter();
    }
 
    @Override
    public String toString() {
        return stringWriter.toString();
    }
 
    public StringBuffer toStringBuffer() {
        return stringWriter.getBuffer();
    }
 
    @Override
    public void write(int b) throws IOException {
        this.stringWriter.write(b);
    }
	
}
