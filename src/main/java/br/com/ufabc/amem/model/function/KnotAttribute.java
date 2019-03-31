package br.com.ufabc.amem.model.function;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.controller.AttributeController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.LogManager;
import br.com.ufabc.amem.util.Strings;

public class KnotAttribute extends Function{

	public KnotAttribute() {
		
		this.name           = "historizeAttribute";
		this.description    = "Historize an existing non historized anchor attribute";
		this.parameters     = new ArrayList<String>();
		this.parameters.add("capsule");
		this.parameters.add("attribute");
		this.parameters.add("anchorCapsule");
		this.parameters.add("descriptor");
		this.parameters.add("mnemonic");
		this.parameters.add("capsule");
		this.parameters.add("dataRange");
		this.parameters.add("identity");
		this.parameters.add("generator");
		this.parameters.add("description");
	}
	
	@Override
	public String execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated, IOException {
		
		validateParameters(params);

		new AttributeController().knotAttribute(params[0], params[1], params[2], params[3], params[4], params[5], 
				params[6], params[7], params[8], params[9]);
		
		LogManager logManager = new LogManager();
		logManager.writeLog(new FunctionAndParams(this, params));
		
		return Strings.getString("objectCreated");
	}

	@Override
	public ImpactList getImpact(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {

		validateParameters(params);
		
		return new AttributeController().knotAttributeImpacts(params[0], params[1], params[2], params[3], params[4], params[5], 
				params[6], params[7], params[8], params[9]);
	}
}