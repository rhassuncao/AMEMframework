package br.com.ufabc.amem.exceptions;

@SuppressWarnings("serial")
public class ObjectAlreadyCreated extends Exception {

	public ObjectAlreadyCreated(String objectName, String objectType, String function) {
		
		super("Objetct " + objectName + " from type " + objectType + " has already been created!");
	}
}
