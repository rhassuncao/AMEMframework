package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
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
		
		//TODO set default value
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
		ArrayList<String> pks = selectPKS(schema, table);

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
			
			boolean isPK = false;
			
			if(pks.contains(columnName)) {
				
				isPK = true;
			}
			
			//TODO how to know if it is autoIncrement and how to know the default value
			Column column     = new Column(columnName, dataType, false, isPK, false, null);
			columns.add(column);
		}

		ConnectionPool.getInstance().releaseConnection(conn);
		return columns;
	}
	
	public void historizeTable(String schema,
							   String table,
			   				   String defaultTime, 
			   				   String defaultTimeFormat) 
			   						   throws SQLException, ObjectAlreadyCreated {
		
		String currentTime = "" + new Date().getTime();
		String tempTable   = table + "_NEW_" + currentTime;
		
		ArrayList<Column> oldTableColumns = selectTable(schema, table);
		ArrayList<Column> tableColumns    = new ArrayList<Column>();
		
		for(Column column : oldTableColumns) {
			
			tableColumns.add(column);
		}
		
		String historyColumn  = table + "_VALIDFROM";
		
		for(Column column : tableColumns) {
			
			if(column.getName().equalsIgnoreCase(historyColumn)) {
				
				throw new ObjectAlreadyCreated(historyColumn, "Column", "HistorizeAttribute");
			}
		}
		
		String defaultValue = "TO_DATE('" + defaultTime + "', '" + defaultTimeFormat + "')";
		tableColumns.add(new Column(historyColumn, "DATE", true, true, false, defaultValue));
		
		ArrayList<FK>     fks          = selectFKS(    schema, table);
		
		for(FK fk : fks) {
			
			fk.setName(fk.getName() + "_" + currentTime);
		}
		
		ArrayList<Unique> uniques      = selectUniques(schema, table);
		
		for(Unique unique : uniques) {
			
			unique.setName(unique.getName() + "_" + currentTime);
		}
		
		createTable(schema, tempTable, tableColumns, fks, uniques);
		
		//TODO validate to max 128 chars
		String trigger = table + "_HISTORIZE_TEMP_" + currentTime;
		String sql = "CREATE OR REPLACE TRIGGER " + trigger + "\r\n" + 
				"	AFTER INSERT ON " + schema + "." + table + "\r\n" + 
				"	FOR EACH ROW\r\n" + 
				"	DISABLE\r\n" + 
				"BEGIN\r\n" + 
				"	INSERT INTO " + schema + "." + tempTable + "\r\n" +
				"	(\r\n";
		
		for(int i = 0; i < tableColumns.size(); i++) {
			
			sql += "		" + tableColumns.get(i).getName();
			
			if(i != tableColumns.size() - 1) {
				
				sql += ",";
			}
			
			sql += "\r\n";
		}
		
		sql += "	) VALUES (\r\n";
		
		
		for(int i = 0; i < tableColumns.size(); i++) {
			
			if(tableColumns.get(i).getName().equalsIgnoreCase(historyColumn)) {
				
				sql += "		SYSDATE";
				
			} else {
				
				sql += "		:NEW." + tableColumns.get(i).getName();
			}
			
			if(i != tableColumns.size() - 1) {
				
				sql += ",";
			}
			
			sql += "\r\n";
		}

		sql += "	);\r\n" +  
				"END;\r\n" + 
				"/";
				
		
		Connection conn = ConnectionPool.getInstance().getConnection();	
		Statement stmt = conn.prepareStatement(sql);
		stmt.executeQuery(sql);
		
		//Copy data
		sql = " INSERT INTO " + schema + "." + tempTable + " ( \r\n";
		
		for(int i = 0; i < oldTableColumns.size(); i++) {
			
			sql += "	" + oldTableColumns.get(i).getName();
			
			if(i != oldTableColumns.size() - 1) {
				
				sql += " , ";
			} 
			else {
				sql += " ) ";
			}
			
			sql += " \r\n ";
		}
				
		sql += " SELECT \r\n ";
		
		for(int i = 0; i < oldTableColumns.size(); i++) {
			
			sql += "	" + table + "." + oldTableColumns.get(i).getName();
			
			if(i != oldTableColumns.size() - 1) {
				
				sql += " , ";
			}
			
			sql += " \r\n";
		}
		
		sql += " FROM " + schema + "." + table;
		
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.executeQuery();
		
		sql = "ALTER TRIGGER " + trigger + " ENABLE";
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		
		sql = "DROP TABLE " + table;
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		//Also drops the trigger
		
		sql = "ALTER TABLE " + tempTable + " RENAME TO " + table;
		preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		
		ConnectionPool.getInstance().releaseConnection(conn);
	}
	
	public ArrayList<Unique> selectUniques(String schema, String table) throws SQLException{

		String sql = "select \r\n" + 
				"    ACC.COLUMN_NAME, ACC.CONSTRAINT_NAME\r\n" + 
				"from \r\n" + 
				"    all_constraints AC\r\n" + 
				"    JOIN all_cons_columns aCC ON AC.CONSTRAINT_NAME = ACC.CONSTRAINT_NAME\r\n" + 
				"where\r\n" + 
				"    AC.owner               = UPPER(?)\r\n" + 
				"    and AC.table_name      = UPPER(?)\r\n" + 
				"    and AC.status          = 'ENABLED'	 \r\n" + 
				"    and AC.constraint_type = 'C'";
		
		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, table);
		ResultSet resultSet = preparedStatment.executeQuery();
		
		ArrayList<Unique> uniques = new ArrayList<>();

		while (resultSet.next()) {
			
			String constraintName  = resultSet.getString("CONSTRAINT_NAME");
			String columnName      = resultSet.getString("COLUMN_NAME");
			Column column          = new Column(columnName, null, false, false, false, null);
            				
            uniques.add(new Unique(constraintName, column));
		}
		
		ConnectionPool.getInstance().releaseConnection(conn);
		return uniques;
	}
	
	public ArrayList<FK> selectFKS(String schema, String table) throws SQLException{

		String sql = "SELECT \r\n" + 
				"    a.constraint_name NAME, c.owner TABLESCHEMA, a.table_name \"TABLE\", a.column_name \"COLUMN\", \r\n" + 
				"    c.r_owner \"EXTERNALTABLESCHEMA\", c_pk.table_name \"EXTERNALTABLE\", a.column_name \"EXTERNALCOLUMN\"\r\n" + 
				"  FROM all_cons_columns a\r\n" + 
				"  JOIN all_constraints c \r\n" + 
				"    ON a.owner = c.owner AND a.constraint_name = c.constraint_name\r\n" + 
				"  JOIN all_constraints c_pk \r\n" + 
				"    ON c.r_owner = c_pk.owner AND c.r_constraint_name = c_pk.constraint_name\r\n" + 
				" WHERE c.constraint_type = 'R'\r\n" + 
				"   AND a.owner      = UPPER(?)\r\n" + 
				"   AND a.table_name = UPPER(?)";
		
		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, table);
		ResultSet resultSet = preparedStatment.executeQuery();
		
		ArrayList<FK> FKs = new ArrayList<>();

		while (resultSet.next()) {
			
			String name                 = resultSet.getString("NAME");
			Column column               = new Column(resultSet.getString("COLUMN"),  null, false, false, false, null);
			String externalTableSchema  = resultSet.getString("EXTERNALTABLESCHEMA");
			String externalTable        = resultSet.getString("EXTERNALTABLE");
			Column externalColumn       = new Column(resultSet.getString("EXTERNALCOLUMN"),  null, false, false, false, null);

            
			FK fk = new FK(name, schema, table, column, externalTableSchema, externalTable, externalColumn);
            FKs.add(fk);
		}
		
		ConnectionPool.getInstance().releaseConnection(conn);
		return FKs;
	}
	
	public ArrayList<String> selectPKS(String schema, String table) throws SQLException{

		String sql = "select \r\n" + 
				"    a.column_name COLUMNNAME\r\n" + 
				"from all_cons_columns a\r\n" + 
				"    JOIN all_constraints c on a.constraint_name = c.constraint_name\r\n" + 
				"where \r\n" + 
				"    a.owner           = UPPER(?)\r\n" + 
				"and a.table_name      = UPPER(?)\r\n" + 
				"and c.constraint_type = 'P'";
		
		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, table);
		ResultSet resultSet = preparedStatment.executeQuery();
		
		ArrayList<String> PKs = new ArrayList<>();

		while (resultSet.next()) {
			
			String pk = resultSet.getString("COLUMNNAME");
            PKs.add(pk);
		}
		
		ConnectionPool.getInstance().releaseConnection(conn);
		return PKs;
	}
}