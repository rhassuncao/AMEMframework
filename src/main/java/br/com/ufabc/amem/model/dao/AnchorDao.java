package br.com.ufabc.amem.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.DBSearch;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;
import br.com.ufabc.amem.model.function.impact.ImpactList;

public class AnchorDao {

	public void createAnchor(Anchor anchor) throws SQLException {

		String schema     = anchor.getCapsule().getName();
		String table      = anchor.getTable();
		String columnName = anchor.getMnemonic() + "_ID";
		String columnType = anchor.getIdentity();
		
		Column column             = new Column(columnName, columnType, true, true, true);
		ArrayList<Column> columns = new ArrayList<>();
		columns.add(column);
		
		Table table2           = new Table();
		table2.createTable(schema, table, columns, new ArrayList<FK>());
	}

	public Anchor selectAnchor(Anchor anchor) throws SQLException {

		Table table        = new Table();
		String schema      = anchor.getCapsule().getName();
		String anchorTable = anchor.getTable();
		
		ArrayList<Column> columns = table.selectTable(schema, anchorTable);
		
		if(columns.isEmpty()) {
			
			anchor = null;
			
		} else {
			
			String identity = columns.get(0).getDataType();
			anchor.setIdentity(identity);
		}
		
		return anchor;
	}

	public ImpactList createAnchorImpacts(Anchor anchor) throws SQLException {
		
		ImpactList impactList = new ImpactList();
		impactList.addAnchorImpact(anchor, "Create");
		
		DBSearch dbSearch = new DBSearch();
		
		ArrayList<String> procedureImpacts =  dbSearch.object(anchor.getCapsule().getName(), anchor.getTable(), "PROCEDURE");
		
		for(int i = 0; i <= procedureImpacts.size(); i++) {
			
			impactList.addProcedureImpact(procedureImpacts.get(i), "Invalid");
		}
		
		ArrayList<String> viewImpacts =  dbSearch.object(anchor.getCapsule().getName(), anchor.getTable(), "VIEW");
		
		for(int i = 0; i <= viewImpacts.size(); i++) {
			
			impactList.addViewImpact(viewImpacts.get(i), "Invalid");
		}
		
		ArrayList<String> functionImpacts =  dbSearch.object(anchor.getCapsule().getName(), anchor.getTable(), "FUNCTION");
		
		for(int i = 0; i <= functionImpacts.size(); i++) {
			
			impactList.addFunctionImpact(functionImpacts.get(i), "Invalid");
		}
		
		ArrayList<String> triggerImpacts =  dbSearch.object(anchor.getCapsule().getName(), anchor.getTable(), "FUNCTION");
		
		for(int i = 0; i <= triggerImpacts.size(); i++) {
			
			impactList.addFunctionImpact(triggerImpacts.get(i), "Invalid");
		}
		
		return impactList;
	}
}