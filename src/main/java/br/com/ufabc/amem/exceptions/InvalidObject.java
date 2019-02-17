package br.com.ufabc.amem.exceptions;

@SuppressWarnings("serial")
public class InvalidObject extends Exception {
	
	public InvalidObject(Object object) {
		
		super("Object " + object.toString() + " from type " + object.getClass() + " is invalid! Operation Canceled.");
	}
}
