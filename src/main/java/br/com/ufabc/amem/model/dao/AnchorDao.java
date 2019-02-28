package br.com.ufabc.amem.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.DBSearch;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;
import br.com.ufabc.amem.model.dao.oracleobjects.Unique;
import br.com.ufabc.amem.model.function.impact.ImpactList;

public class AnchorDao {

	/**
	 * @param anchor
	 * @throws SQLException
	 */
	public void createAnchor(Anchor anchor) throws SQLException {

		String schema     = anchor.getCapsule().getName();
		String table      = anchor.getTable();
		String columnName = anchor.getMnemonic() + "_ID";
		String columnType = anchor.getIdentity();
		
		Column column             = new Column(columnName, columnType, true, true, true);
		ArrayList<Column> columns = new ArrayList<>();
		columns.add(column);
		
		Table table2           = new Table();
		table2.createTable(schema, table, columns, new ArrayList<FK>(), new ArrayList<Unique>());
	}

	/**
	 * @param anchor
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * @param anchor
	 * @return
	 * @throws SQLException
	 */
	public ImpactList createAnchorImpacts(Anchor anchor) throws SQLException {
		
		ImpactList impactList = new ImpactList();
		
		DBSearch dbSearch = new DBSearch();
		
		impactList.setProcedureImpacts(dbSearch.objectImpacts(anchor, "PROCEDURE"));
		impactList.setFunctionImpacts( dbSearch.objectImpacts(anchor, "FUNCTION"));
		impactList.setTableImpacts(    dbSearch.objectImpacts(anchor, "TABLE"));
		impactList.setViewImpacts(     dbSearch.objectImpacts(anchor, "VIEW"));
		impactList.setTriggerImpacts(  dbSearch.objectImpacts(anchor, "TRIGGER"));
		
		impactList.addAnchorImpact(anchor,           "Create");
		impactList.addTableImpact(anchor.getTable(), "Create");
		
		return impactList;
	}
}