package br.com.ufabc.amem.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.DBSearch;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;
import br.com.ufabc.amem.model.function.impact.ImpactList;

public class KnotDao {

	/**
	 * @param knot
	 * @throws SQLException
	 */
	public void createKnot(Knot knot) throws SQLException {

		String schema          = knot.getCapsule().getName();
		String knotTable       = knot.getTable();
		String columnIdName    = knot.getMnemonic() + "_ID";
		String columnIdType    = knot.getIdentity();
		String columnDescName  = knot.getMnemonic() + "_" + knot.getDescriptor();
		String columnDescType  = knot.getDataRange();

		Column columnId           = new Column(columnIdName, columnIdType, true, true, true);
		Column columnDesc         = new Column(columnDescName, columnDescType, true, false, false);
		ArrayList<Column> columns = new ArrayList<>();
		columns.add(columnId);
		columns.add(columnDesc);
		
		Table table2 = new Table();
		table2.createTable(schema, knotTable, columns, new ArrayList<FK>());
	}
	
	/**
	 * @param knot
	 * @return
	 * @throws SQLException
	 */
	public Knot selectKnot(Knot knot) throws SQLException {
		
		Table table      = new Table();
		String schema    = knot.getCapsule().getName();
		String knotTable = knot.getTable();
		
		ArrayList<Column> columns = table.selectTable(schema, knotTable);
		
		if(columns.isEmpty()) {
			
			knot = null;
			
		} else {
			
			String identity = columns.get(1).getDataType();
			knot.setIdentity(identity);
		}
		
		return knot;
	}

	/**
	 * @param knot
	 * @return
	 * @throws SQLException
	 */
	public ImpactList createKnotImpact(Knot knot) throws SQLException {
		
		ImpactList impactList = new ImpactList();
		
		DBSearch dbSearch = new DBSearch();
		
		impactList.setProcedureImpacts(dbSearch.objectImpacts(knot, "PROCEDURE"));
		impactList.setFunctionImpacts( dbSearch.objectImpacts(knot, "FUNCTION"));
		impactList.setTableImpacts(    dbSearch.objectImpacts(knot, "TABLE"));
		impactList.setViewImpacts(     dbSearch.objectImpacts(knot, "VIEW"));
		impactList.setTriggerImpacts(  dbSearch.objectImpacts(knot, "TRIGGER"));
		
		impactList.addKnotImpact(knot,             "Create");
		impactList.addTableImpact(knot.getTable(), "Create");
		
		return impactList;
	}
}