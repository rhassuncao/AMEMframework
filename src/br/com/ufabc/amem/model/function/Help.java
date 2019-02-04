package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.Map;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.util.Instructions;
import br.com.ufabc.amem.util.Strings;

public class Help implements Executable{
	
	private String command;

	@Override
	public void execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		if (params == null) {

			System.out.println(
					"==================================================================================================="); //$NON-NLS-1$
			System.out.println(
					Strings.getString("help"));
			System.out.println(
					"==================================================================================================="); //$NON-NLS-1$
			System.out.println(Strings.getString("operatorParameters"));

			Map<String, String> instructions = new Instructions().getAllInstructions();

			for (Map.Entry<String, String> instruction : instructions.entrySet()) {
				System.out.println(completeWithSpace(instruction.getKey(), 20) + ": " + instruction.getValue());
			}

			System.out.println(
					"==================================================================================================="); //$NON-NLS-1$
		
		} else if (params != null && params.length == 1) {
			
			String instruction = new Instructions().getInstruction(params[0].toUpperCase());
			
			if(instruction == null) {
				
				throw new InvalidObject(params[0]);
				
			} else {
				
				System.out.println(instruction);
			}
			
		} else {

			throw new InvalidParameterNumber(command);
		}
	}
	
	private static String completeWithSpace(String text, int size) {
		
		for (int i = text.length(); i < size; i++) {
			
			text += " ";
		}
		
		return text;
	}
}
