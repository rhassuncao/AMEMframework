package br.com.ufabc.amem.model.function;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.util.ConnectionPool;
import br.com.ufabc.amem.util.Strings;

public class ConnectionTest extends Function{
	
	public ConnectionTest(){
		
		this.name        = "connectionTest";
		this.description = "Create an Attribute anchor modeling object";
		this.parameters  = new ArrayList<String>();
	}
	
	@Override
	public String execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {

		String returnString = "";
		Connection conn = ConnectionPool.getInstance().getConnection();
		
		if (conn != null) {
			
			returnString = Strings.getString("sucess");
			
		} else {
			
			returnString = Strings.getString("failed");
		}
		
		ConnectionPool.getInstance().releaseConnection(conn);
		return returnString;
	}
}