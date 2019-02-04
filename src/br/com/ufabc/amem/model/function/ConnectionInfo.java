package br.com.ufabc.amem.model.function;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.util.ConnectionPool;

public class ConnectionInfo implements Executable{

	@Override
	public void execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		System.out.println(ConnectionPool.getUrl());
	}
}
