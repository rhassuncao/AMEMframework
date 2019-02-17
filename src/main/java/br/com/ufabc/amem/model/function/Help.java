package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Strings;

public class Help extends Function{
	
	public Help(){
		
		this.name        = "Help";
		this.description = "Show information about all working functions";
		this.parameters  = new ArrayList<String>();
	}

	@Override
	public String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		String returnString = "";

		returnString += "===================================================================================================\n";
		returnString += Strings.getString("help") + "\n";
		returnString += "===================================================================================================\n";
		returnString += Strings.getString("operatorParameters") + "\n";

		Map<String, Function> functions = Functions.getAllFunctions();

		for (Map.Entry<String, Function> function : functions.entrySet()) {
				
			returnString += Strings.completeWithSpace(function.getValue().getName(), 20) + ": ";
			
			for(int i = 0; i < function.getValue().getParameters().size(); i++) {
				
				returnString += function.getValue().getParameters().get(i);
				
				if(i+1 < function.getValue().getParameters().size()) {
					
					returnString += ", ";
				}
			}
			returnString += "\n";
		}

		returnString += "===================================================================================================\n";
	
		return returnString;
	}

	@Override
	public ImpactList getImpact(String[] params) {

		return null;
	}
}