package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.impact.ImpactList;

public class createTie extends Function{
	
	public createTie() {
		
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
		this.parameters.add("description");
		this.parameters.add("timeRange");
	}

	@Override
	public String execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImpactList getImpact(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		// TODO Auto-generated method stub
		return null;
	}
}