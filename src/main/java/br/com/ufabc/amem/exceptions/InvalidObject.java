package br.com.ufabc.amem.exceptions;

@SuppressWarnings("serial")
public class InvalidObject extends Exception {
	
	/**
	 * @param object
	 */
	public InvalidObject(Object object) {
		
		super("Object " + object.toString() + " from type " + object.getClass() + " is invalid! Operation Canceled.");
	}
}
