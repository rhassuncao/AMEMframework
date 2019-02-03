package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.util.ConnectionPool;

public class Table {
	
	// TODO this may have injection
	public void createTable(String schema, 
							   String table, 
							   ArrayList<Column> columns, 
							   ArrayList<FK> FKs) throws SQLException {
		
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
			
			if(i+1 != columns.size() || !FKs.isEmpty() || !primaryKeys.isEmpty()) {
				
				sql += ",";
			}
		}
		
		for (int j = 0; j < FKs.size(); j++) {
			
			FK fk = FKs.get(j);
			
			sql += "CONSTRAINT " + fk.getName() + " FOREIGN KEY "
				+  "(" + fk.getColumn().getName() + ")" 
				+ "  REFERENCES " + fk.getExternalTable() + "(" + fk.getExternalColumn().getName() + ")";
			
			if(j+1 != FKs.size() || !primaryKeys.isEmpty()) {
				
				sql += ",";
			}
		}
		
		if(!primaryKeys.isEmpty()) {
			 
			sql += " CONSTRAINT PK_" + table +  " PRIMARY KEY (";
			
			for (int k = 0; k < primaryKeys.size(); k++) {
				
				sql += primaryKeys.get(k);
				
				if(k+1 != primaryKeys.size()) {
					
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
}