package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;

public abstract class Function implements Executable {
	
	protected String            name;
	protected ArrayList<String> parameters;
	protected String            description;

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
	
	public abstract String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated;
	
	protected void validateParameters(String[] params) throws InvalidParameterNumber {
		
		if (params.length != parameters.size()) {

			throw new InvalidParameterNumber(this);
		}
	}
}