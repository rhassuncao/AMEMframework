package br.com.ufabc.amem.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.am.Attribute;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.DBSearch;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;
import br.com.ufabc.amem.model.dao.oracleobjects.Unique;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.ConnectionPool;

public class AttributeDao {

	/**
	 * @param attribute
	 * @throws SQLException
	 */
	public void createAttribute(Attribute attribute) throws SQLException {

		String schema           = attribute.getCapsule().getName();
		String attributeTable   = attribute.getTable();
		String columnAnchorName = attribute.getAnchor().getMnemonic() + "_ID";
		String columnAnchorType = attribute.getAnchor().getIdentity();
		
		ArrayList<Column> columns = new ArrayList<>();
		ArrayList<FK>     FKs     = new ArrayList<>();
		
		Column columnAnchor = new Column(columnAnchorName, columnAnchorType, true, true, false);
		columns.add(columnAnchor);
		
		String columnAnchorFKName = "FK1_" + attributeTable;
		String anchorTable        = attribute.getAnchor().getTable();
		String anchorSchema       = attribute.getAnchor().getCapsule().getName();

		FK fkAnchor = new FK(columnAnchorFKName, schema, attributeTable, columnAnchor, anchorSchema , anchorTable , columnAnchor);
		FKs.add(fkAnchor);

		if (attribute.isKnotted()) {

			String columnKnotName   = attribute.getKnot().getMnemonic() + "_ID";
			String columnKnotType   = attribute.getKnot().getIdentity();
			Column columnKnotted    = new Column(columnKnotName , columnKnotType, true, false, false);
			columns.add(columnKnotted);
			
			String columnknotFKName = "FK2_" + attributeTable;
			String knotTable        = attribute.getKnot().getTable();
			String knotSchema       = attribute.getKnot().getCapsule().getName();
			FK fkKnot 				= new FK(columnknotFKName, schema, attributeTable, columnKnotted, knotSchema, knotTable, columnKnotted);
			FKs.add(fkKnot);

		} else {

			String columnStaticName = attribute.getAnchor().getMnemonic()   + "_" 
									+ attribute.getMnemonic()               + "_"
									+ attribute.getAnchor().getDescriptor() + "_" 
									+ attribute.getDescriptor();
			String columnStaticType = attribute.getDataRange();

			Column columnStatic = new Column(columnStaticName , columnStaticType, true, false, false);
			columns.add(columnStatic);
		}

		if (attribute.isHistorized()) {

			String historyColumnName = attribute.getAnchor().getMnemonic() + "_" 
									 + attribute.getMnemonic()             + "_ValidFrom";
			String historyColumnType = attribute.getTimeRange();
			
			Column historyColumn = new Column(historyColumnName , historyColumnType, true, true, false);
			columns.add(historyColumn);
		}
		
		Table table = new Table();
		table.createTable(schema, attributeTable, columns, FKs, new ArrayList<Unique>());
	}

	/**
	 * @param attribute
	 * @return
	 * @throws SQLException
	 */
	public Attribute selectAttribute(Attribute attribute) throws SQLException {
		
		Table table           = new Table();
		String schema         = attribute.getCapsule().getName();
		String attributeTable = attribute.getTable();
		
		ArrayList<Column> columns = table.selectTable(schema, attributeTable);
		
		if(columns.isEmpty()) {
			
			attribute = null;
			
		} else {

			String columnStaticName = attribute.getAnchor().getMnemonic()   + "_" 
									+ attribute.getMnemonic()               + "_"
									+ attribute.getAnchor().getDescriptor() + "_" 
									+ attribute.getDescriptor();

			for (int i = 0; i < columns.size(); i++) {
				
				if (columnStaticName.equalsIgnoreCase(columns.get(i).getName())) {
					
					attribute.setDataRange(columns.get(i).getDataType());
				}
			}
			
			if(attribute.isKnotted()) {
				
				String identityColumn = attribute.getAnchor().getMnemonic() + "_ID";
				
				for (int i = 0; i < columns.size(); i++) {
					
					boolean isTheIdColumn   = identityColumn.equalsIgnoreCase(columns.get(i).getName());
					boolean isTheTimeColumn = columnStaticName.equalsIgnoreCase(columns.get(i).getName());
					
					if (!isTheTimeColumn && !isTheIdColumn) {
						
						attribute.setDataRange(columns.get(i).getDataType());;
					}
				}
			}

			String historyColumn = attribute.getAnchor().getMnemonic() + "_" 
								 + attribute.getMnemonic() 			   + "_ValidFrom";
			
			for (int i = 0; i < columns.size(); i++) {
				
				if ((historyColumn).equalsIgnoreCase(columns.get(i).getName())) {
					
					attribute.setTimeRange(columns.get(i).getDataType());;
				}
			}   
		}
		
		return attribute;
	}
	
	//TODO this may have injection
	//TODO wrong way to do it because de valid from must be part of pk
	/**
	 * @param attribute
	 * @param defaultTime
	 * @param defaultTimeFormat
	 * @throws ObjectAlreadyCreated
	 * @throws SQLException
	 */
	public void historizeAttribute(Attribute attribute, String defaultTime, String defaultTimeFormat) throws ObjectAlreadyCreated, SQLException {

		String schema         = attribute.getCapsule().getName();
		String attributeTable = attribute.getTable();
		String historyColumn  = attribute.getAnchor().getMnemonic() + "_"
							   + attribute.getMnemonic() 			+ "_ValidFrom";

		String sql = "alter table " + schema + "." + attributeTable + " add (" + historyColumn + " "
				+ attribute.getTimeRange() 
				+ " DEFAULT TO_DATE('" + defaultTime + "', '" + defaultTimeFormat + "')"
				+ " not null)";

		Connection conn = ConnectionPool.getInstance().getConnection();
		PreparedStatement preparedStatment = conn.prepareStatement(sql);
		preparedStatment.execute();
		ConnectionPool.getInstance().releaseConnection(conn);
	}

	/**
	 * @param attribute
	 * @return
	 * @throws SQLException
	 */
	public ImpactList createAttributeImpact(Attribute attribute) throws SQLException {
		
		ImpactList impactList = new ImpactList();
		
		DBSearch dbSearch = new DBSearch();
		
		impactList.setProcedureImpacts(dbSearch.objectImpacts(attribute, "PROCEDURE"));
		impactList.setFunctionImpacts( dbSearch.objectImpacts(attribute, "FUNCTION"));
		impactList.setTableImpacts(    dbSearch.objectImpacts(attribute, "TABLE"));
		impactList.setViewImpacts(     dbSearch.objectImpacts(attribute, "VIEW"));
		impactList.setTriggerImpacts(  dbSearch.objectImpacts(attribute, "TRIGGER"));
		
		impactList.addAttributeImpact(attribute,          "Create");
		impactList.addAnchorImpact(attribute.getAnchor(), "Link");
		impactList.addTableImpact(attribute.getTable(),   "Create");
		
		if(attribute.getKnot() != null) {
			
			impactList.addKnotImpact(attribute.getKnot(), "Link");
		}
		
		return impactList;
	}
}