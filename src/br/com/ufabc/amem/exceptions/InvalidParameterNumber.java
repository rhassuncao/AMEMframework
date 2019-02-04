package br.com.ufabc.amem.exceptions;

import br.com.ufabc.amem.util.Instructions;

@SuppressWarnings("serial")
public class InvalidParameterNumber extends Exception {

	public InvalidParameterNumber(String function) {
		
		super("Invalid Parameter Number for function " + function + "!\n"
				+ "Parameters: " + new Instructions().getInstruction(function));
	}
}
