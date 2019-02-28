package br.com.ufabc.amem.exceptions;

@SuppressWarnings("serial")
public class ObjectAlreadyCreated extends Exception {

	/**
	 * @param objectName
	 * @param objectType
	 * @param function
	 */
	public ObjectAlreadyCreated(String objectName, String objectType, String function) {
		
		super("Objetct " + objectName + " from type " + objectType + " has already been created!");
	}
}
