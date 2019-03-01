package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.util.ConnectionPool;

public class Table {
	
	// TODO this may have injection
	/**
	 * @param schema
	 * @param table
	 * @param columns
	 * @param FKs
	 * @throws SQLException
	 */
	public void createTable(String schema, 
							String table, 
							ArrayList<Column> columns, 
							ArrayList<FK> FKs,
							ArrayList<Unique> uniques) throws SQLException {
		
		String sql = "CREATE TABLE " + schema + "." + table + "(";
		
		ArrayList<String> primaryKeys = new ArrayList<>();
		
		for (int i = 0; i < columns.size(); i++) {
			
			Column col = columns.get(i);
			
			sql += col.getName() + " " + col.getDataType();
			
			if(col.isPrimaryKey()) {
				
				primaryKeys.add(col.getName());
			}
			
			if(col.isNotNull()) {
				
				sql += " NOT NULL";
			}
			
			if(i+1 != columns.size() || !FKs.isEmpty() || !primaryKeys.isEmpty() || !uniques.isEmpty()) {
				
				sql += ",";
			}
		}
		
		for (int j = 0; j < FKs.size(); j++) {
			
			FK fk = FKs.get(j);
			
			sql += "CONSTRAINT " + fk.getName() + " FOREIGN KEY "
				+  "(" + fk.getColumn().getName() + ")" 
				+ "  REFERENCES " + fk.getExternalTableSchema() + "." + fk.getExternalTable() + "(" + fk.getExternalColumn().getName() + ")";
			
			if(j+1 != FKs.size() || !primaryKeys.isEmpty() || !uniques.isEmpty()) {
				
				sql += ",";
			}
		}
		
		for (int k = 0; k < uniques.size(); k++) {
			
			Unique unique = uniques.get(k);
			
			sql += "CONSTRAINT " + unique.getName() + " UNIQUE "
				+  "(" + unique.getColumn().getName() + ")";
			
			if(k+1 != uniques.size() || !primaryKeys.isEmpty()) {
				
				sql += ",";
			}
		}
		
		if(!primaryKeys.isEmpty()) {
			 
			sql += " CONSTRAINT PK_" + table +  " PRIMARY KEY (";
			
			for (int l = 0; l < primaryKeys.size(); l++) {
				
				sql += primaryKeys.get(l);
				
				if(l+1 != primaryKeys.size()) {
					
					sql += ",";
				}
			}
			
			sql += ")";
		}
		
		sql += ")";
		
		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		ConnectionPool.getInstance().releaseConnection(conn);

		for (Column col: columns) {
			
			if (col.isAutoIncrement()) {

				AutoIncrement autoIncrementDao = new AutoIncrement();
				autoIncrementDao.createAutoIncrement(schema, table, col.getName());
			}
		}
	}
	
	/**
	 * @param schema
	 * @param table
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Column> selectTable(String schema, String table) throws SQLException {
		
		Connection conn = ConnectionPool.getInstance().getConnection();

		String sql    = "SELECT data_type, DATA_PRECISION, DATA_LENGTH, column_name from all_tab_columns where owner = UPPER(?) and table_name = UPPER(?)";
		
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, table);
		ResultSet resultSet = preparedStatment.executeQuery();
		
		ArrayList<Column> columns = new ArrayList<>();

		while (resultSet.next()) {

			String dataType = resultSet.getString("DATA_TYPE");
			
			ArrayList<String> lenghtTypes = new ArrayList<>();
			lenghtTypes.add("VARCHAR");
			lenghtTypes.add("VARCHAR2");
			
			ArrayList<String> precisionTypes = new ArrayList<>();
			precisionTypes.add("NUMBER");
			
			if(lenghtTypes.contains(dataType)) {
				
				dataType += "(" + resultSet.getString("DATA_LENGTH") + ")";
				
			} else if(precisionTypes.contains(dataType)){
				
				dataType += "(" + resultSet.getString("DATA_PRECISION") + ")";
				
			}

			String columnName =  resultSet.getString("column_name");
			Column column     = new Column(columnName, dataType, false, false, false);
			columns.add(column);
		}

		ConnectionPool.getInstance().releaseConnection(conn);
		return columns;
	}
	
	public void historizeTable(String table,
							   String schema,
			   				   String defaultTime, 
			   				   String defaultTimeFormat) 
			   						   throws SQLException {
		
		String sql = "";
		//TODO
		//Create table_new with all fields
		
		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		
		sql = "";
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
//		String historyColumn  = attribute.getAnchor().getMnemonic() + "_"
//		   + attribute.getMnemonic() 			+ "_ValidFrom";
		
		//make a trigger to coppy new data

		//get current timestamp um centcenconds
		sql = "select dbms_utility.get_time from dual";
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		
		String currentTime = "";
		
		//TODO validate to max 128 chars
		String trigger = table + "_historize_temp" + currentTime;
		//Copy data
		sql = "";
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		
		//enable trigger
		sql = "";
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		
		sql = "DROP TABLE " + table;
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		
		sql = "ALTER TABLE " + table + "_NEW RENAME TO " + table;
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		
		sql = "DROP TRIGGER " + trigger;
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();

		ConnectionPool.getInstance().releaseConnection(conn);
	}
}