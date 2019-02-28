package br.com.ufabc.amem.controller;

import java.io.IOException;
import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.Function;
import br.com.ufabc.amem.model.function.FunctionAndParams;
import br.com.ufabc.amem.model.function.Functions;
import br.com.ufabc.amem.util.Strings;

public class FunctionController {

	/**
	 * @param functionAndParams
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 * @throws InvalidParameterNumber
	 * @throws ObjectAlreadyCreated
	 * @throws IOException
	 */
	public String execute(FunctionAndParams functionAndParams) 
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated, IOException {

		String showScreen = "";
		Function function = functionAndParams.getFunction();
		String[] params   = functionAndParams.getParams();

		if (function != null) {

			showScreen = function.execute(params);

		} else {

			showScreen = Strings.getString("invalidCommand");
		}
		
		return showScreen;
	}

	/**
	 * @param line
	 * @return
	 */
	public FunctionAndParams read(String line) {

		String[] params = null;
		String functionName = null;

		if (line.contains("(")) {

			functionName = line.substring(0, line.indexOf("(")).trim().toUpperCase();
			params = line.substring(line.indexOf("(") + 1, line.length()).trim().split(",");

			for (int i = 0; i < params.length; i++) {

				params[i] = params[i].trim();
			}

			params[params.length - 1] = params[params.length - 1].substring(0, params[params.length - 1].length() - 1);

		} else {

			functionName = line.trim().toUpperCase();
		}

		Function function = Functions.getFunction(functionName);
		FunctionAndParams functionAndParams = new FunctionAndParams(function, params);

		return (functionAndParams);
	}
}