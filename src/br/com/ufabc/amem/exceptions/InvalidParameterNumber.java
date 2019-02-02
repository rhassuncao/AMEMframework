package br.com.ufabc.amem.exceptions;

import br.com.ufabc.amem.util.Instructions;

@SuppressWarnings("serial")
public class InvalidParameterNumber extends Exception {

	public InvalidParameterNumber(String function) {
		
		super("Invalid Parameter Number for function " + function + "!\n"
				+ "Correct parameter number: " + (((new Instructions().getInstruction(function)).split(" ")).length - 1) + "\n"
				+ "Parameters: " + new Instructions().getInstruction(function));
	}
}
