package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.controller.AttributeController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.util.Strings;

public class Function {
	
	protected static String            name;
	protected static ArrayList<String> parameters;
	protected static String            description;
	
	public Function() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {
		
	}
	
	protected void validateParameters(String[] params) throws InvalidParameterNumber {
		
		if (params.length != parameters.size()) {

			throw new InvalidParameterNumber(name);
		}
	}
}
