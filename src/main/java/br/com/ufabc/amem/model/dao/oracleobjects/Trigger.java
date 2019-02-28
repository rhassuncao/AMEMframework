package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ufabc.amem.util.ConnectionPool;

public class Trigger {
	
	//TODO this may have injection
	/**
	 * @param schema
	 * @param triggerName
	 * @param table
	 * @param field
	 * @param sequenceName
	 */
	protected void createAutoIncrementTrigger(String schema, 
								 			  String triggerName, 
								 			  String table, 
								 			  String field, 
								 			  String sequenceName) {
		
		String sql = "CREATE TRIGGER " + schema + "." + triggerName +
				"  before insert on " + schema + "." + table + 
				"  for each row  " +
				"declare " +
				"INCREMENT INTEGER; " +
				"begin " + 
				"SELECT " + schema + "." + sequenceName + ".NEXTVAL INTO INCREMENT FROM DUAL WHERE ROWNUM = 1; :NEW." + field + " := INCREMENT; " +
				"end;";
		try {
			
			Connection conn = ConnectionPool.getInstance().getConnection();
			Statement stmt = conn.prepareStatement(sql);
			stmt.executeQuery(sql);
			ConnectionPool.getInstance().releaseConnection(conn);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}