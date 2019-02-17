package br.com.ufabc.amem.model.function;

public class FunctionAndParams {
	
	private Function          function;
	private String[] params;
	
	public FunctionAndParams(Function function, String[] params) {

		this.function = function;
		this.params   = params;
	}

	public Function getFunction() {
		
		return function;
	}

	public void setFunction(Function function) {
		
		this.function = function;
	}

	public String[] getParams() {
		
		return params;
	}

	public void setParams(String[] params) {
		
		this.params = params;
	}
}