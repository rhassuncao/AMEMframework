package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.util.ConnectionPool;

public class DBSearch {
	
	//TODO oracle upper on schema and objects name
	public ArrayList<String> object (String schema, String object, String type) throws SQLException{
		
		String sql    = "SELECT * FROM all_source WHERE owner = upper(?) "
				+ "AND TYPE = upper(?) AND text LIKE upper('% ?.? %')";

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, type);
		preparedStatment.setString(3, schema);
		preparedStatment.setString(4, object);
		ResultSet resultSet = preparedStatment.executeQuery();
		
		ArrayList<String> objects = new ArrayList<>();

		while(resultSet.next()) {

			objects.add(resultSet.getString("NAME"));
		} 

		ConnectionPool.getInstance().releaseConnection(conn);
		return objects;
	}
}