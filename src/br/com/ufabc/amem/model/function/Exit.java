package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Strings;

public class Exit extends Function{
	
	public Exit(){
		
		this.name        = "Exit";
		this.description = "Exit the system";
		this.parameters  = new ArrayList<String>();
	}

	@Override
	public String execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {

		return Strings.getString("bye") + "\n";
	}

	@Override
	public ImpactList getImpact(String[] params) {
		// TODO Auto-generated method stub
		return null;
	}
}