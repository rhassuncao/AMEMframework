package br.com.ufabc.amem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.ufabc.amem.util.ConnectionPool;

public class SequenceDao {

	// TODO this may have injection
	public void createSequence(String schema, String name) throws SQLException {

		String sql = "CREATE SEQUENCE " + schema + "." + name + " START WITH 1 INCREMENT BY 1 NOCYCLE";

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		ConnectionPool.getInstance().releaseConnection(conn);

	}
}
