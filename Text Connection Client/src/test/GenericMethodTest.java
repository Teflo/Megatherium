/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.lang.reflect.InvocationTargetException;
import javax.sound.midi.ControllerEventListener;
import megatherium.util.ClassUtil;
import tc.controller.Controller;
import textconnect.communicator.data.Text;

/**
 *
 * @author marti_000
 */
public class GenericMethodTest {
	
	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Controller object = new Controller();
		String methodName = "showTextCreate";
		callGeneric(object, methodName, new Text[]{});
	}
	
	public static void callGeneric(Object object, String methodName, Object... parameters) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?>[] types = ClassUtil.getClassTypes(parameters);
		object.getClass().getMethod(methodName, types).invoke(object, parameters);
	}
	
}
