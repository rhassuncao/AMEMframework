package br.com.ufabc.amem.model.function;

import java.sql.SQLException;

import br.com.ufabc.amem.controller.AnchorController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.util.Strings;

public class CreateAnchor implements Executable {
	
	private String command;

	@Override
	public void execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		if (params.length == 6) {

			AnchorController anchorController = new AnchorController();
			anchorController.createAnchor(params[0], params[1], params[2], params[3], params[4], params[5]);
			System.out.println(Strings.getString("objectCreated"));

		} else {

			throw new InvalidParameterNumber(command);
		}
	}
}
