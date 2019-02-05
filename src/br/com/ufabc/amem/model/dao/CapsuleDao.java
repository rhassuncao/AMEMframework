package br.com.ufabc.amem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.util.ConnectionPool;

public class CapsuleDao {

	//TODO convert this to oracle object
	public Capsule selectCapsule(Capsule capsule) throws SQLException {

		String sql    = "select * from SYS.ALL_USERS where USERNAME = ?";
		String schema = capsule.getName();

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		ResultSet resultSet = preparedStatment.executeQuery();

		if (!resultSet.next()) {

			capsule = null;
		} 

		ConnectionPool.getInstance().releaseConnection(conn);
		return capsule;
	}
}