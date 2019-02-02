package br.com.ufabc.amem.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ufabc.amem.util.ConnectionPool;

public class TriggerDao {
	
	//TODO this may have injection
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
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}