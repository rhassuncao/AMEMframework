package br.com.ufabc.amem.model.function;

import java.util.Map;
import java.util.TreeMap;

public class Functions {
	
	private static Map<String, Function> functions;

	static {

		functions = new TreeMap<String, Function>(String.CASE_INSENSITIVE_ORDER);
		registerFunction(new CreateAttribute());
		registerFunction(new ConnectionInfo());
		registerFunction(new ConnectionTest());
		registerFunction(new CreateAnchor());
		registerFunction(new CreateKnot());
		registerFunction(new Exit());
		registerFunction(new Help());
		registerFunction(new HistorizeAttribute());
	}

	public static Map<String, Function> getAllFunctions(){
		
		return functions;
	}
	
	public static void registerFunction(Function function) {
		
		functions.put(function.getName().toUpperCase(), function);
	}
	
	public static Function getFunction(String functionName) {
		
		return functions.get(functionName);
	}
}