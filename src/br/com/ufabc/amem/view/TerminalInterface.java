package br.com.ufabc.amem.view;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.Functions;
import br.com.ufabc.amem.util.Strings;

public class TerminalInterface {

	public static void main(String[] args) {

		System.out.println(Strings.getString("welcome"));
		
		Scanner scanner     = new Scanner(System.in);
		String functionName = null;

		do {

			String[] params = null;
			String line     = scanner.nextLine();

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
			
			try {

				System.out.println(Functions.getFunction(functionName).execute(params));

			} catch (InvalidParameterNumber | InvalidObject | SQLException | ObjectAlreadyCreated exception) {

				System.out.println(exception.getMessage());
			}

		} while (!functionName.equalsIgnoreCase("EXIT"));

		scanner.close();
	}
}