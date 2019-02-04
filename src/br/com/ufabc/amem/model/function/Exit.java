package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;

public class Exit extends Function implements Executable{

	public Exit(String name, ArrayList<String> parameters, String description) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {

	}
}
