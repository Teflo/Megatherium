/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package megatherium.humanizer;

/**
 *
 * @author SargTeX
 */
public class MethodCall {
	private String methodName;
	private Object[] parameters;
	private Class<?>[] parameterTypes;
	public String getMethodName() {return this.methodName;}
	public Object[] getParameters() {return this.parameters;}
	public Class<?>[] getParameterTypes() {return this.parameterTypes;}
	public MethodCall(String methodName, Object[] parameters, Class<?>[] parameterTypes) {this.methodName = methodName; this.parameters = parameters; this.parameterTypes = parameterTypes;}
	
}
