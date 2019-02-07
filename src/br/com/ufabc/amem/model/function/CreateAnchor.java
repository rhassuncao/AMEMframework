package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.controller.AnchorController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.util.Strings;

public class CreateAnchor extends Function{
	
	public CreateAnchor(){
		
		this.name           = "createAnchor";
		this.description    = "Create an Anchor anchor modeling object";
		this.parameters     = new ArrayList<String>();
		this.parameters.add("descriptor");
		this.parameters.add("mnemonic");
		this.parameters.add("capsule");
		this.parameters.add("dataRange");
		this.parameters.add("generator");
		this.parameters.add("description");
	}

	@Override
	public String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		validateParameters(params);

		new AnchorController().createAnchor(params[0], params[1], params[2], params[3], params[4], params[5]);
		return Strings.getString("objectCreated");
	}

	@Override
	public String getImpact() {
		// TODO Auto-generated method stub
		return null;
	}
}