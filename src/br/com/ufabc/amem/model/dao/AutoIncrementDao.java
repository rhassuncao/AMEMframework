package br.com.ufabc.amem.model.dao;

import java.sql.SQLException;

public class AutoIncrementDao {

	public void createAutoIncrement(String schema, String table, String primaryKey) throws SQLException {

		//Create the sequence
		String sequenceName = table + "_" 
							+ primaryKey          
							+ "_SEQ";
		
		SequenceDao sequenceDao = new SequenceDao();
		sequenceDao.createSequence(schema, sequenceName);
		
		//Create the trigger
		TriggerDao triggerDao = new TriggerDao();
		String triggerName    = "TRG_" + table + "_" + primaryKey;
		triggerDao.createAutoIncrementTrigger(schema, triggerName , table, primaryKey, sequenceName);
	}
}