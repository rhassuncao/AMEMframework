package br.com.ufabc.amem.model.function;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.controller.AttributeController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.LogManager;
import br.com.ufabc.amem.util.Strings;

public class CreateAttribute extends Function{
	
	/**
	 * 
	 */
	public CreateAttribute() {
		
		this.name           = "createAttribute";
		this.description    = "Create an Attribute anchor modeling object";
		this.parameters     = new ArrayList<String>();
		this.parameters.add("anchorCapcule");
		this.parameters.add("anchor");
		this.parameters.add("descriptor");
		this.parameters.add("mnemonic");
		this.parameters.add("attributeCapsule");
		this.parameters.add("dataRange");
		this.parameters.add("description");
		this.parameters.add("timeRange");
		this.parameters.add("knotCapsule");
		this.parameters.add("knot");
	}

	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.function.Function#execute(java.lang.String[])
	 */
	@Override
	public String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber, IOException {
		
		validateParameters(params);
		
		new AttributeController().createAttribute(params[0], params[1], params[2], params[3], params[4], params[5],
				params[6], params[7], params[8], params[9]);
		
		LogManager logManager = new LogManager();
		logManager.writeLog(new FunctionAndParams(this, params));
		
		return Strings.getString("objectCreated");
	}

	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.function.Function#getImpact(java.lang.String[])
	 */
	@Override
	public ImpactList getImpact(String[] params) throws InvalidParameterNumber, InvalidObject, SQLException {
		
		validateParameters(params);
		
		return new AttributeController().createAttributeImpact(params[0], params[1], params[2], params[3], params[4], params[5],
				params[6], params[7], params[8], params[9]);
	}
}