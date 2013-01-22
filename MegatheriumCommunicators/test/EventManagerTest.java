/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import megatherium.event.EventManager;

/**
 *
 * @author marti_000
 */
public class EventManagerTest {
	
	public static void main(String[] args) {
		EventManager.getInstance().addListener("test.eventManager.doSomething", new EventManagerTest(), "helloWorld");
		EventManager.getInstance().addAfterListener("test.eventManager.doSomethingElse", new EventManagerTest(), "notEnoughParams");
		EventManager.getInstance().addListener("test.eventManager", new EventManagerTest(), "allsideListener");
		
		// fire events!
		EventManager.getInstance().fireEvent("test.eventManager.doSomething");
		EventManager.getInstance().fireAfterEvent("test.eventManager.doSomethingElse", new Object[] {"rofl", "lol", "Karl", "Peter"});
	}
	
	public void allsideListener(String event) {
		System.out.println("The following event was thrown: "+event);
	}
	
	public void helloWorld() {
		System.out.println("Hello World! :)");
	}
	
	public void notEnoughParams(String do1, String do2, String notMyName) {
		System.out.println("Hello. I do "+do1+" and I do "+do2+", but my name is not "+notMyName+".");
	}
	
}
