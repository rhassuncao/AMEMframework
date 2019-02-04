package br.com.ufabc.amem.model.function;

import java.sql.SQLException;

import br.com.ufabc.amem.controller.AttributeController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.util.Strings;

public class HistorizeAttribute implements Executable {
	
	private String command;

	@Override
	public void execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {
		
		if (params.length == 5) {

			AttributeController attributeControler = new AttributeController();
			attributeControler.historizeAttribute(params[0], params[1], params[2], params[3], params[4]);
			System.out.println(Strings.getString("objectCreated"));

		} else {

			throw new InvalidParameterNumber(command);
		}
	}
}
