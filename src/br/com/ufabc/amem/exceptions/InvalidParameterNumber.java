package br.com.ufabc.amem.exceptions;

import br.com.ufabc.amem.model.function.Function;

@SuppressWarnings("serial")
public class InvalidParameterNumber extends Exception {

	public InvalidParameterNumber(Function function) {
		
		super("Invalid Parameter Number for function " + function.getName());
	}
}
