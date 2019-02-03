package br.com.ufabc.amem.model.dao.oracleobjects;

import java.sql.SQLException;

public class AutoIncrement {

	public void createAutoIncrement(String schema, String table, String primaryKey) throws SQLException {

		//Create the sequence
		String sequenceName = table + "_" 
							+ primaryKey          
							+ "_SEQ";
		
		Sequence sequenceDao = new Sequence();
		sequenceDao.createSequence(schema, sequenceName);
		
		//Create the trigger
		Trigger triggerDao = new Trigger();
		String triggerName    = "TRG_" + table + "_" + primaryKey;
		triggerDao.createAutoIncrementTrigger(schema, triggerName , table, primaryKey, sequenceName);
	}
}