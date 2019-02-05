package br.com.ufabc.amem.model.function;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.util.ConnectionPool;

public class ConnectionInfo extends Function{
	
	public ConnectionInfo(){
		
		this.name        = "ConnectionInfo";
		this.description = "Shows information about the connection";
		this.parameters  = new ArrayList<String>();
	}

	@Override
	public String execute(String[] params) throws InvalidObject, SQLException, InvalidParameterNumber {
		
		return ConnectionPool.getUrl();
	}
}