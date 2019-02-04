package br.com.ufabc.amem.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.CreateAttribute;
import br.com.ufabc.amem.model.function.Function;
import br.com.ufabc.amem.util.Strings;

public class TerminalInterface {
	
	private static String[] params;
	private static String   functionName;
	static Scanner          scanner;
	private static HashMap<String, Function> functions;

	public static void main(String[] args) {

		System.out.println(Strings.getString("welcome"));
		scanner = new Scanner(System.in);
		//TODO how will i see the functions?

		do {

			readInput();
			
			try {

				Function function2 = functions.get(functionName);
				function2.execute(params);

			} catch (InvalidParameterNumber | InvalidObject | SQLException | ObjectAlreadyCreated exception) {

				System.out.println(exception.getMessage());
			}

		} while (!functionName.equals("EXIT")); //(false);

		scanner.close();
	}
	
	private static void readInput() {
		
		String line  = scanner.nextLine();
		functionName = null;
		params       = null;
		
		if(line.contains("(")) {
			
			functionName = line.substring(0, line.indexOf("(")).trim().toUpperCase();
			params       = line.substring(line.indexOf("(") + 1, line.length()).trim().split(",");
			
			for(int i = 0; i < params.length; i++) {
				
				params[i] = params[i].trim();
			}
			
			params[params.length-1] = params[params.length-1].substring(0, params[params.length-1].length()-1);
			
		} else {
			
			functionName = line.trim().toUpperCase();
		}
	}
}