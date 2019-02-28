package br.com.ufabc.amem.model.function;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.controller.TieController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.LogManager;
import br.com.ufabc.amem.util.Strings;

public class CreateTie extends Function{
	
	/**
	 * 
	 */
	public CreateTie() {
		
		this.name           = "createTie";
		this.description    = "Create a tie anchor modeling object linking 2 anchors";
		this.parameters     = new ArrayList<String>();
		this.parameters.add("anchor1Capsule");
		this.parameters.add("anchor1");
		this.parameters.add("anchor1Role");
		this.parameters.add("anchor1Identifier");
		this.parameters.add("anchor2Capsule");
		this.parameters.add("anchor2");
		this.parameters.add("anchor2Role");
		this.parameters.add("anchor2Identifier");
		this.parameters.add("capsule");
		this.parameters.add("description");
		this.parameters.add("timeRange");
	}

	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.function.Function#execute(java.lang.String[])
	 */
	@Override
	public String execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated, IOException {
		
		validateParameters(params);

		new TieController().createTie(params[0], params[1], params[2], params[3], params[4], params[5],
				params[6], params[7], params[8], params[9], params[10]);
		
		LogManager logManager = new LogManager();
		logManager.writeLog(new FunctionAndParams(this, params));
		
		return Strings.getString("objectCreated");
	}

	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.function.Function#getImpact(java.lang.String[])
	 */
	@Override
	public ImpactList getImpact(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		validateParameters(params);
		
		return new TieController().createTieImpacts(params[0], params[1], params[2], params[3], params[4], params[5],
				params[6], params[7], params[8], params[9], params[10]);
	}
}