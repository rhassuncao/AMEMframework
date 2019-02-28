package br.com.ufabc.amem.model.dao;

import java.sql.SQLException;

import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.dao.oracleobjects.Schema;

public class CapsuleDao {
	
	/**
	 * @param capsule
	 * @return
	 * @throws SQLException
	 */
	public Capsule selectCapsule(Capsule capsule) throws SQLException {
		
		Schema schema     = new Schema();
		
		boolean returnBool = schema.selectSchema(capsule.getName());
		
		if(!returnBool) {
			
			capsule = null;
		} 
		
		return capsule;
	}
}