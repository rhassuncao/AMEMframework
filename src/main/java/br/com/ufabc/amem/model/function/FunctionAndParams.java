package br.com.ufabc.amem.model.function;

public class FunctionAndParams {
	
	/**
	 * 
	 */
	private Function          function;
	/**
	 * 
	 */
	private String[] params;
	
	/**
	 * @param function
	 * @param params
	 */
	public FunctionAndParams(Function function, String[] params) {

		this.function = function;
		this.params   = params;
	}

	/**
	 * @return
	 */
	public Function getFunction() {
		
		return function;
	}

	/**
	 * @param function
	 */
	public void setFunction(Function function) {
		
		this.function = function;
	}

	/**
	 * @return
	 */
	public String[] getParams() {
		
		return params;
	}

	/**
	 * @param params
	 */
	public void setParams(String[] params) {
		
		this.params = params;
	}
}