package br.com.ufabc.amem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ufabc.amem.model.Knot;
import br.com.ufabc.amem.util.ConnectionPool;

public class KnotDao {

	public Knot selectKnot(Knot knot) throws SQLException {

		String sql = "select * from ALL_TABLES where OWNER = UPPER(?) AND TABLE_NAME = UPPER(?)";
		String schema = knot.getCapsule().getName();
		String table = knot.getMnemonic() + "_" + knot.getDescriptor();

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, table);
		ResultSet resultSet = preparedStatment.executeQuery();

		if (resultSet.next()) {

			sql = "SELECT data_type, DATA_PRECISION from all_tab_columns where owner = UPPER(?) and table_name = UPPER(?) and column_name = UPPER(?)";
			String column = knot.getMnemonic() + "_ID";

			preparedStatment = conn.prepareStatement(sql);
			preparedStatment.setString(1, schema);
			preparedStatment.setString(2, table);
			preparedStatment.setString(3, column);
			resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {

				String dataType = resultSet.getString("DATA_TYPE") + "(" + resultSet.getString("DATA_PRECISION") + ")";
				knot.setIdentity(dataType);
			}
		} else {

			knot = null;
		}

		ConnectionPool.getInstance().releaseConnection(conn);
		return knot;
	}

	// TODO this may have injection
	public void createKnot(Knot knot) throws SQLException {

		String schema = knot.getCapsule().getName();
		String table = knot.getMnemonic() + "_" + knot.getDescriptor();
		String primaryKey = knot.getMnemonic() + "_ID";
		String primaryKeyType = knot.getIdentity();
		String column = knot.getMnemonic() + "_" + knot.getDescriptor();
		String columnType = knot.getDataRange();

		String sql = "CREATE TABLE " + schema + "." + table + " (" + primaryKey + " " + primaryKeyType
				+ " PRIMARY KEY not null," + column + " " + columnType + " not null)";

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		ConnectionPool.getInstance().releaseConnection(conn);
	}
}