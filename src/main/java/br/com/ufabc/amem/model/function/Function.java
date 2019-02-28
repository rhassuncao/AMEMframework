package br.com.ufabc.amem.model.function;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.impact.ImpactList;

public abstract class Function implements Executable {
	
	/**
	 * 
	 */
	protected String            name;
	/**
	 * 
	 */
	protected ArrayList<String> parameters;
	/**
	 * 
	 */
	protected String            description;

	/**
	 * @return
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getParameters() {
		
		return parameters;
	}

	/**
	 * @param parameters
	 */
	public void setParameters(ArrayList<String> parameters) {
		
		this.parameters = parameters;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.function.Executable#execute(java.lang.String[])
	 */
	public abstract String execute(String[] params) 
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated, IOException;
	
	/**
	 * @param params
	 * @throws InvalidParameterNumber
	 */
	protected void validateParameters(String[] params) throws InvalidParameterNumber {
		
		if (params.length != parameters.size()) {

			throw new InvalidParameterNumber(this);
		}
	}
	
	/**
	 * @param params
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 * @throws InvalidParameterNumber
	 */
	public abstract ImpactList getImpact(String[] params) 
			throws InvalidObject, SQLException, InvalidParameterNumber;
}