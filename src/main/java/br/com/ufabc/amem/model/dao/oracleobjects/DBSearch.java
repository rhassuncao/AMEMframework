package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.ufabc.amem.model.am.AnchorObject;
import br.com.ufabc.amem.util.ConnectionPool;

public class DBSearch {
	
	//TODO oracle upper on schema and objects name
	/**
	 * @param schema
	 * @param object
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> object (String schema, String object, String type) throws SQLException{
		
		String sql    = "SELECT * FROM all_source WHERE owner = upper(?) "
				+ "AND TYPE = upper(?) AND text LIKE upper('% " + schema + "." + object + " %')";

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.setString(1, schema);
		preparedStatment.setString(2, type);
		ResultSet resultSet = preparedStatment.executeQuery();
		
		ArrayList<String> objects = new ArrayList<>();

		while(resultSet.next()) {

			objects.add(resultSet.getString("NAME"));
		} 

		ConnectionPool.getInstance().releaseConnection(conn);
		return objects;
	}
	
	/**
	 * @param anchorObject
	 * @param databaseObject
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> objectImpacts(AnchorObject anchorObject, String databaseObject) throws SQLException {
		
		ArrayList<String> objectImpacts =  object(anchorObject.getCapsule().getName(), anchorObject.getTable(), databaseObject);
		HashMap<String, String> objectImpactsReturn = new HashMap<>();
		
		for(int i = 0; i < objectImpacts.size(); i++) {
			
			objectImpactsReturn.put(objectImpacts.get(i), "Invalid");
		}
		
		return objectImpactsReturn;
	}
}