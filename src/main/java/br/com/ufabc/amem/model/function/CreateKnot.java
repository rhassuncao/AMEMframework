package br.com.ufabc.amem.model.function;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.controller.KnotController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.LogManager;
import br.com.ufabc.amem.util.Strings;

public class CreateKnot extends Function{
	
	/**
	 * 
	 */
	public CreateKnot(){
		
		this.name           = "createKnot";
		this.description    = "Create a Knot anchor modeling object";
		this.parameters     = new ArrayList<String>();
		this.parameters.add("descriptor");
		this.parameters.add("mnemonic");
		this.parameters.add("capsule");
		this.parameters.add("dataRange");
		this.parameters.add("identity");
		this.parameters.add("generator");
		this.parameters.add("description");
	}
	
	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.function.Function#execute(java.lang.String[])
	 */
	@Override
	public String execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated, IOException {
		
		validateParameters(params);
		new KnotController().createKnot(params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
		
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
		
		return new KnotController().createKnotImpact(params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
	}
}