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
		
		for (int i = 0; i < columns.size(); i++) {
			
			Column col = columns.get(0);
			
			sql += col.getName() + " " + col.getDataType();
			
			if(col.isPrimaryKey()) {
				
				sql += " PRIMARY KEY";
			}
			
			if(col.isNotNull()) {
				
				sql += " NOT NULL";
			}
			
			if(i+1 != columns.size() || !FKs.isEmpty()) {
				
				sql += ",";
			}
		}
		
		for(FK fk: FKs) {
			
			sql += "CONSTRAINT " + fk.getSchema() + "." + fk.getName() + " FOREIGN KEY "
				+ fk.getColumn().getName() + "(" + fk.getTable() + ")" 
				+ "  REFERENCES " + fk.getExternalTable() + "(" + fk.getExternalColumn().getName() + ")";
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