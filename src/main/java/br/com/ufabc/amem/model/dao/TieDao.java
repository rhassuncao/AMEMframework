package br.com.ufabc.amem.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.model.am.Tie;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.DBSearch;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;
import br.com.ufabc.amem.model.dao.oracleobjects.Unique;
import br.com.ufabc.amem.model.function.impact.ImpactList;

public class TieDao {

	/**
	 * @param tie
	 * @throws SQLException 
	 */
	public void createTie(Tie tie) throws SQLException {
		
		String schema     = tie.getCapsule().getName();
		String table      = tie.getTable();
		
		ArrayList<Column> columns = new ArrayList<>();
		
		Column column1 = new Column(tie.getAnchor1().getMnemonic() + "_ID_" + tie.getRole1(), 
				tie.getAnchor1().getIdentity(), true, true, false);
		columns.add(column1);
		
		Column column2 = new Column(tie.getAnchor2().getMnemonic() + "_ID_" + tie.getRole2(), 
				tie.getAnchor2().getIdentity(), true, true, false);
		columns.add(column2);
		
		if(tie.getTimeRange() != null) {
			
			String historyColumnName = tie.getTable() + "_ChangedAt";
			String historyColumnType = tie.getTimeRange();

			Column historyColumn = new Column(historyColumnName , historyColumnType, true, true, false);
			columns.add(historyColumn);
		}
		
		ArrayList<FK> fks = new ArrayList<FK>();
		
		Column anchor1Column = new Column(tie.getAnchor1().getMnemonic()+ "_ID", tie.getAnchor1().getIdentity(), true, true, true);
		
		FK fk1 = new FK("FK1_" + tie.getTable()
			+ tie.getAnchor2().getMnemonic() + "_" + tie.getRole2(), 
			schema, table, column1, tie.getAnchor1().getCapsule().getName(), tie.getAnchor1().getTable(), anchor1Column);
		fks.add(fk1);
		
		Column anchor2Column = new Column(tie.getAnchor2().getMnemonic()+ "_ID", tie.getAnchor2().getIdentity(), true, true, true);
		
		FK fk2 = new FK("FK2_" + tie.getTable()
			+ tie.getAnchor2().getMnemonic() + "_" + tie.getRole2(), 
			schema, table, column2, tie.getAnchor2().getCapsule().getName(), tie.getAnchor2().getTable(), anchor2Column );
		fks.add(fk2);

		ArrayList<Unique> uniques = new ArrayList<>();
		
		if(tie.getTimeRange() == null) {
			
			if(!tie.isIdentifier1N()) {
				
				Unique uniqueColumn1 = new Unique("UQ1_" + tie.getTable() + tie.getAnchor1().getMnemonic() +  "_" + tie.getRole1(), column1);
				uniques.add(uniqueColumn1);
			}
	
			if(!tie.isIdentifier1N()) {
				Unique uniqueColumn2 = new Unique("UQ1_" + tie.getTable() + tie.getAnchor2().getMnemonic() +  "_" + tie.getRole2(), column2);
				uniques.add(uniqueColumn2);
			}
		}
		
		Table table2 = new Table();
		table2.createTable(schema, table, columns, fks, uniques);
	}

	public ImpactList createTieImpacts(Tie tie) throws SQLException {
		
		ImpactList impactList = new ImpactList();
		
		DBSearch dbSearch = new DBSearch();
		
		impactList.setProcedureImpacts(dbSearch.objectImpacts(tie, "PROCEDURE"));
		impactList.setFunctionImpacts( dbSearch.objectImpacts(tie, "FUNCTION"));
		impactList.setTableImpacts(    dbSearch.objectImpacts(tie, "TABLE"));
		impactList.setViewImpacts(     dbSearch.objectImpacts(tie, "VIEW"));
		impactList.setTriggerImpacts(  dbSearch.objectImpacts(tie, "TRIGGER"));
		
		impactList.addTieImpact(tie,              "Create");
		impactList.addTableImpact(tie.getTable(), "Create");
		impactList.addAnchorImpact(tie.getAnchor1(), "Link");
		impactList.addAnchorImpact(tie.getAnchor2(), "Link");
		
		return impactList;
	}
}