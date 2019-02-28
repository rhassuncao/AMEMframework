package br.com.ufabc.amem.model.function;

import java.io.IOException;
import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;

public interface Executable {

	/**
	 * @param params
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 * @throws InvalidParameterNumber
	 * @throws ObjectAlreadyCreated
	 * @throws IOException
	 */
	String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated, IOException;
}