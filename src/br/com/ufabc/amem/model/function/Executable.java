package br.com.ufabc.amem.model.function;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;

public interface Executable {

	String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated;
}