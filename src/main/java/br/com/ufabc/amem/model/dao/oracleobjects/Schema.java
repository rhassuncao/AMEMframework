package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ufabc.amem.util.ConnectionPool;

public class Schema {

	/**
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public boolean selectSchema(String name) throws SQLException {
		
		boolean returnBool = false;
		
		String sql    = "select * from SYS.ALL_USERS where USERNAME = ?";

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, name);
		ResultSet resultSet = preparedStatment.executeQuery();

		if (!resultSet.next()) {

			returnBool = true;
		} 

		ConnectionPool.getInstance().releaseConnection(conn);
		return returnBool;
	}
}