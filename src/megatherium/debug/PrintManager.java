/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.debug;

/**
 *
 * @author Pathos
 */
public class PrintManager {
	private static PrintManager instance;
	private Printer printer;
	
	private PrintManager() {}
	
	/**
	 * Returns the current instance of the print manager.
	 */
	public static PrintManager getInstance() {
		if (instance == null) instance = new PrintManager();
		
		return instance;
	}
	
	/**
	 * Sets the printer.
	 * 
	 * @param printer
	 */
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	/**
	 * Prints a message.
	 */
	public void printLine(String line) {
		printer.print(line+"\n");
	}
	
	/**
	 * Prints a single line message.
	 */
	public void print(String line) {
		printer.print(line);
	}
	
	/**
	 * Prints an exception.
	 */
	public void print(Exception ex) {
		this.printLine(ex.toString());
		StackTraceElement[] elements = ex.getStackTrace();
		for (int i = 0; i < elements.length; ++i) {
			printLine("\t"+elements[i].toString());
		}
	}
	
}
