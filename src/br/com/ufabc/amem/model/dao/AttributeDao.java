package br.com.ufabc.amem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.Attribute;
import br.com.ufabc.amem.model.Capsule;
import br.com.ufabc.amem.model.Knot;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;
import br.com.ufabc.amem.util.ConnectionPool;

public class AttributeDao {

	// TODO this may have injection
	public void historizeAttribute(Attribute attribute) throws ObjectAlreadyCreated, SQLException {

		String schema         = attribute.getCapsule().getName();
		String attributeTable = attribute.getTable();
		String historyColumn  = attribute.getAnchor().getMnemonic() 
							  + "_" + attribute.getMnemonic() + "_ValidFrom";

		String sql = "alter table " + schema + "." + attributeTable + " add (" + historyColumn + " "
				+ attribute.getTimeRange() + " not null)";

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		ConnectionPool.getInstance().releaseConnection(conn);
	}

	public void createAttribute(Attribute attribute) throws SQLException {

		String schema           = attribute.getCapsule().getName();
		String attributeTable   = attribute.getTable();
		String columnAnchorName = attribute.getAnchor().getMnemonic() + "_ID";
		String columnAnchorType = attribute.getAnchor().getIdentity();
		
		ArrayList<Column> columns = new ArrayList<>();
		ArrayList<FK>     FKs     = new ArrayList<>();
		
		Column columnAnchor = new Column(columnAnchorName, columnAnchorType, true, true, true);
		columns.add(columnAnchor);
		
		String columnAnchorFKName = "FK1_" + attributeTable;
		String anchorTable        = attribute.getAnchor().getTable();
		String anchorSchema       = attribute.getAnchor().getCapsule().getName();

		FK fkAnchor = new FK(columnAnchorFKName, schema, attributeTable, columnAnchor, anchorSchema , anchorTable , columnAnchor);
		FKs.add(fkAnchor);

		if (attribute.isKnotted()) {

			String columnKnotName   = attribute.getKnot().getMnemonic() + "_ID";
			String columnKnotType   = attribute.getKnot().getIdentity();
			Column columnKnotted    = new Column(columnKnotName , columnKnotType, true, false, false);
			columns.add(columnKnotted);
			
			String columnknotFKName = "FK2_" + attributeTable;
			String knotTable        = attribute.getKnot().getTable();
			String knotSchema       = attribute.getKnot().getCapsule().getName();
			FK fkKnot 				= new FK(columnknotFKName, schema, attributeTable, columnKnotted, knotSchema, knotTable, columnKnotted);
			FKs.add(fkKnot);

		} else {

			String columnStaticName = attribute.getAnchor().getMnemonic() + "_" 
									+ attribute.getMnemonic() + "_"
									+ attribute.getAnchor().getDescriptor() + "_" 
									+ attribute.getDescriptor();
			String columnStaticType = attribute.getDataRange();

			Column columnStatic = new Column(columnStaticName , columnStaticType, true, false, false);
			columns.add(columnStatic);
		}

		if (attribute.isHistorized()) {

			String historyColumnName = attribute.getAnchor().getMnemonic() + "_" + attribute.getMnemonic() + "_ValidFrom";
			String historyColumnType = attribute.getTimeRange();
			
			Column historyColumn = new Column(historyColumnName , historyColumnType, true, true, false);
			columns.add(historyColumn);
		}
		
		Table table = new Table();
		table.createTable(schema, attributeTable, columns, FKs);
	}

	public Attribute selectAttribute(Attribute attribute) throws SQLException {

		String sql = "select * from ALL_TABLES where OWNER = UPPER(?) AND TABLE_NAME = UPPER(?)";
		String schema = attribute.getCapsule().getName();
		String table = attribute.getAnchor().getMnemonic() + "_" + attribute.getMnemonic() + "_"
				+ attribute.getAnchor().getDescriptor() + "_" + attribute.getDescriptor();

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, table);
		ResultSet resultSet = preparedStatment.executeQuery();

		if (resultSet.next()) {

			sql = "SELECT data_type, DATA_PRECISION from all_tab_columns where owner = UPPER(?) and table_name = UPPER(?) and column_name = UPPER(?)";

			String column = table;

			preparedStatment = conn.prepareStatement(sql);
			preparedStatment.setString(1, schema);
			preparedStatment.setString(2, table);
			preparedStatment.setString(3, column);
			resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {

				// static attribute
				String dataType = resultSet.getString("DATA_TYPE");

				// TODO set for diferent data types
				if (!dataType.equalsIgnoreCase("VARCHAR2")) {

					dataType += "(" + resultSet.getString("DATA_PRECISION") + ")";
				}
				attribute.setDataRange(dataType);
				attribute.setKnot(null);

			} else {

				// knotted attribute
				// TODO serach knot
				sql = "SELECT c.r_owner, c_pk.table_name r_table_name" + "  FROM all_cons_columns a"
						+ "  JOIN all_constraints c ON a.owner = c.owner"
						+ "                        AND a.constraint_name = c.constraint_name"
						+ "  JOIN all_constraints c_pk ON c.r_owner = c_pk.owner"
						+ "                           AND c.r_constraint_name = c_pk.constraint_name"
						+ " WHERE c.constraint_type = 'R'" + "   AND a.table_name = ?"
						+ "   AND a.CONSTRAINT_NAME like '%FK2%'";

				preparedStatment = conn.prepareStatement(sql);
				preparedStatment.setString(1, table);
				resultSet = preparedStatment.executeQuery();

				if (resultSet.next()) {

					String knotCapsuleString = resultSet.getString("r_owner");
					CapsuleDao capsuleDao = new CapsuleDao();
					Capsule knotCapsule = new Capsule(knotCapsuleString);
					knotCapsule = capsuleDao.selectCapsule(knotCapsule);

					String knotTable = resultSet.getString("r_table_name");
					String[] params = knotTable.split("_");
					KnotDao knotDao = new KnotDao();
					Knot knot = new Knot(params[1], params[0], knotCapsule, null, null, false, null);
					knot = knotDao.selectKnot(knot);

					attribute.setKnot(knot);
				}
			}

			column = attribute.getAnchor().getMnemonic() + "_" + attribute.getMnemonic() + "_VALIDFROM";

			preparedStatment = conn.prepareStatement(sql);
			preparedStatment.setString(1, schema);
			preparedStatment.setString(2, table);
			preparedStatment.setString(3, column);
			resultSet = preparedStatment.executeQuery();

			if (resultSet.next()) {

				// historized
				String timeRange = resultSet.getString("DATA_TYPE") + "(" + resultSet.getString("DATA_PRECISION") + ")";
				attribute.setTimeRange(timeRange);

			} else {

				attribute.setTimeRange(null);
			}
		} else {
			
			attribute = null;
		}
		ConnectionPool.getInstance().releaseConnection(conn);
		return attribute;
	}
}