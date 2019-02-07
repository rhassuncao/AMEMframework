package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.controller.AttributeController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.util.Strings;

public class CreateAttribute extends Function{
	
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

	@Override
	public String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		validateParameters(params);
		new AttributeController().createAttribute(params[0], params[1], params[2], params[3], params[4], params[5],
				params[6], params[7], params[8], params[9]);
		return Strings.getString("objectCreated");
	}

	@Override
	public String getImpact() {
		// TODO Auto-generated method stub
		return null;
	}
}