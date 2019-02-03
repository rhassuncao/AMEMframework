package br.com.ufabc.amem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.model.Anchor;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;
import br.com.ufabc.amem.util.ConnectionPool;

public class AnchorDao {

	public void createAnchor(Anchor anchor) throws SQLException {

		String schema     = anchor.getCapsule().getName();
		String table      = anchor.getTable();
		String columnName = anchor.getMnemonic() + "_ID";
		String columnType = anchor.getIdentity();
		
		Column column             = new Column(columnName, columnType, true, true, true);
		ArrayList<Column> columns = new ArrayList<>();
		columns.add(column);
		
		Table table2           = new Table();
		table2.createTable(schema, table, columns, new ArrayList<FK>());
	}

	public Anchor selectAnchor(Anchor anchor) throws SQLException {

		String sql = "select 1 from ALL_TABLES where OWNER = UPPER(?) AND TABLE_NAME = UPPER(?)";
		String schema = anchor.getCapsule().getName();
		String table = anchor.getMnemonic() + "_" + anchor.getDescriptor();

		Connection conn = ConnectionPool.getInstance().getConnection();

		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, table);
		ResultSet resultSet = preparedStatment.executeQuery();

		if (resultSet.next()) {

			sql = "SELECT data_type, DATA_PRECISION from all_tab_columns where owner = UPPER(?) and table_name = UPPER(?) and column_name = UPPER(?)";
			String column = anchor.getMnemonic() + "_ID";

			preparedStatment = conn.prepareStatement(sql);
			preparedStatment.setString(1, schema);
			preparedStatment.setString(2, table);
			preparedStatment.setString(3, column);
			resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {

				String dataType = resultSet.getString("DATA_TYPE") + "(" + resultSet.getString("DATA_PRECISION") + ")";
				anchor.setIdentity(dataType);
				return anchor;
			}
		} 

		ConnectionPool.getInstance().releaseConnection(conn);
		return null;
	}
}