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

public class HistorizeAttribute extends Function{
	
	/**
	 * 
	 */
	public HistorizeAttribute(){
		
		this.name           = "historizeAttribute";
		this.description    = "Create an Attribute anchor modeling object";
		this.parameters     = new ArrayList<String>();
		this.parameters.add("capsule");
		this.parameters.add("attribute");
		this.parameters.add("anchorCapsule");
		this.parameters.add("timeRange");
		this.parameters.add("defaultTime");
		this.parameters.add("defaultTimeFormat");
	}

	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.function.Function#execute(java.lang.String[])
	 */
	@Override
	public String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated, IOException {
		
		validateParameters(params);

		new AttributeController().historizeAttribute(params[0], params[1], params[2], params[3], params[4], params[5]);
		
		LogManager logManager = new LogManager();
		logManager.writeLog(new FunctionAndParams(this, params));
		
		return Strings.getString("objectCreated");
	}

	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.function.Function#getImpact(java.lang.String[])
	 */
	@Override
	public ImpactList getImpact(String[] params) {
		
		return null;
	}
}