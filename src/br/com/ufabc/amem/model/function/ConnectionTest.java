package br.com.ufabc.amem.model.function;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.util.ConnectionPool;
import br.com.ufabc.amem.util.Strings;

public class ConnectionTest implements Executable {

	@Override
	public void execute(String[] params)
			throws InvalidObject, SQLException, InvalidParameterNumber, ObjectAlreadyCreated {

		Connection conn = ConnectionPool.getInstance().getConnection();
		
		if (conn != null) {
			
			System.out.println(Strings.getString("sucess"));
			
		} else {
			
			System.out.println(Strings.getString("failed"));
		}
		
		ConnectionPool.getInstance().releaseConnection(conn);
	}
}