package br.com.ufabc.amem.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.model.Anchor;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;

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
}