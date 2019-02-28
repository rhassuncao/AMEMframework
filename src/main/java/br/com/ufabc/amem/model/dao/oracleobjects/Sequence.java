package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.ufabc.amem.util.ConnectionPool;
import br.com.ufabc.amem.util.Validate;

public class Sequence {

	// TODO this may have injection
	/**
	 * @param schema
	 * @param name
	 * @throws SQLException
	 */
	public void createSequence(String schema, String name) throws SQLException {

		name = new Validate().validateSequenceName(name);
		
		String sql = "CREATE SEQUENCE " + schema + "." + name + " START WITH 1 INCREMENT BY 1 NOCYCLE";

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		ConnectionPool.getInstance().releaseConnection(conn);
	}
}