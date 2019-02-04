package br.com.ufabc.amem.model.function;

import java.sql.SQLException;

import br.com.ufabc.amem.controller.AttributeController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.util.Strings;

public class CreateAttribute extends Function{
	
	private static CreateAttribute instance;
	
	private CreateAttribute() {
	}

	static {

		instance       = new CreateAttribute();
		name           = "createAttribute";
		description    = "Create an Attribute anchor modeling object";
		parameters.add("descriptor");
		parameters.add("mnemonic");
		parameters.add("capsule");
		parameters.add("dataRange");
		parameters.add("generator");
		parameters.add("description");
	}

	@Override
	public void execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		validateParameters(params);

		AttributeController attributeControler = new AttributeController();
		attributeControler.createAttribute(params[0], params[1], params[2], params[3], params[4], params[5],
				params[6], params[7], params[8], params[9]);
		System.out.println(Strings.getString("objectCreated"));
	}
}
