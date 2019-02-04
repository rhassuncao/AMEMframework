package br.com.ufabc.amem.model.function;

import java.sql.SQLException;

import br.com.ufabc.amem.controller.KnotController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.util.Strings;

public class CreateKnot implements Executable {

	private String command;
	
	@Override
	public void execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {
		
		if (params.length == 7) {

			KnotController knotController = new KnotController();
			knotController.createKnot(params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
			System.out.println(Strings.getString("objectCreated"));

		} else {

			throw new InvalidParameterNumber(command);
		}
	}
}
