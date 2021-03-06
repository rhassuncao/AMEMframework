package br.com.ufabc.amem.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.am.Attribute;
import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.dao.oracleobjects.Column;
import br.com.ufabc.amem.model.dao.oracleobjects.DBSearch;
import br.com.ufabc.amem.model.dao.oracleobjects.FK;
import br.com.ufabc.amem.model.dao.oracleobjects.Table;
import br.com.ufabc.amem.model.dao.oracleobjects.Unique;
import br.com.ufabc.amem.model.function.impact.ImpactList;

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
		
		Column columnAnchor = new Column(columnAnchorName, columnAnchorType, true, true, false, null);
		columns.add(columnAnchor);
		
		String columnAnchorFKName = "FK1_" + attributeTable;
		String anchorTable        = attribute.getAnchor().getTable();
		String anchorSchema       = attribute.getAnchor().getCapsule().getName();

		FK fkAnchor = new FK(columnAnchorFKName, schema, attributeTable, columnAnchor, anchorSchema , anchorTable , columnAnchor);
		FKs.add(fkAnchor);

		if (attribute.isKnotted()) {

			String columnKnotName   = attribute.getKnot().getMnemonic() + "_ID";
			String columnKnotType   = attribute.getKnot().getIdentity();
			Column columnKnotted    = new Column(columnKnotName , columnKnotType, true, false, false, null);
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

			Column columnStatic = new Column(columnStaticName , columnStaticType, true, false, false, null);
			columns.add(columnStatic);
		}

		if (attribute.isHistorized()) {

			String historyColumnName = attribute.getAnchor().getMnemonic() + "_" 
									 + attribute.getMnemonic()             + "_ValidFrom";
			String historyColumnType = attribute.getTimeRange();
			
			Column historyColumn = new Column(historyColumnName , historyColumnType, true, true, false, null);
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
	public void historizeAttribute(Attribute attribute, 
								   String    defaultTime, 
								   String    defaultTimeFormat) 
										   throws SQLException, ObjectAlreadyCreated {

		Table  table          = new Table();
		String schema         = attribute.getCapsule().getName();
		String attributeTable = attribute.getTable();
		table.historizeTable(schema, attributeTable, defaultTime, defaultTimeFormat);
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

	public ImpactList historizeAttributeImpact(Attribute attribute) throws SQLException {
		
		ImpactList impactList = new ImpactList();
		
		DBSearch dbSearch = new DBSearch();
		
		impactList.setProcedureImpacts(dbSearch.objectImpacts(attribute, "PROCEDURE"));
		impactList.setFunctionImpacts( dbSearch.objectImpacts(attribute, "FUNCTION"));
		impactList.setTableImpacts(    dbSearch.objectImpacts(attribute, "TABLE"));
		impactList.setViewImpacts(     dbSearch.objectImpacts(attribute, "VIEW"));
		impactList.setTriggerImpacts(  dbSearch.objectImpacts(attribute, "TRIGGER"));
		
		impactList.addAttributeImpact(attribute,          "Historize");
		impactList.addTableImpact(attribute.getTable(),   "Recreate");
		
		return impactList;
	}

	@SuppressWarnings("unlikely-arg-type")
	public ImpactList knotAttributeImpact(Attribute attribute, Knot knot) throws SQLException {
		
		ImpactList impactList = new ImpactList();
		
		DBSearch dbSearch = new DBSearch();
		
		impactList.setProcedureImpacts(dbSearch.objectImpacts(attribute, "PROCEDURE"));
		impactList.setFunctionImpacts( dbSearch.objectImpacts(attribute, "FUNCTION"));
		impactList.setTableImpacts(    dbSearch.objectImpacts(attribute, "TABLE"));
		impactList.setViewImpacts(     dbSearch.objectImpacts(attribute, "VIEW"));
		impactList.setTriggerImpacts(  dbSearch.objectImpacts(attribute, "TRIGGER"));
		
		impactList.addAttributeImpact(attribute,          "Toggle knotted");
		impactList.addTableImpact(attribute.getTable(),   "Recreate");
		
		HashMap<String, String> knotProcedureImpacts = dbSearch.objectImpacts(knot, "PROCEDURE");
		
		for(int i = 0; i < knotProcedureImpacts.size(); i++) {
			
			impactList.addProcedureImpact(knotProcedureImpacts.get(i), "Invalid");
		}
		
		HashMap<String, String> knotFunctionImpacts = dbSearch.objectImpacts(knot, "FUNCTION");
		
		for(int i = 0; i < knotFunctionImpacts.size(); i++) {
			
			impactList.addFunctionImpact(knotFunctionImpacts.get(i), "Invalid");
		}
		
		HashMap<String, String> knotTableImpacts = dbSearch.objectImpacts(knot, "TABLE");
		
		for(int i = 0; i < knotTableImpacts .size(); i++) {
			
			impactList.addTableImpact(knotTableImpacts.get(i), "Invalid");
		}
		
		HashMap<String, String> knotViewImpacts = dbSearch.objectImpacts(knot, "VIEW");
		
		for(int i = 0; i < knotViewImpacts .size(); i++) {
			
			impactList.addViewImpact(knotViewImpacts.get(i), "Invalid");
		}
		
		HashMap<String, String> knotTriggerImpacts = dbSearch.objectImpacts(knot, "TRIGGER");
		
		for(int i = 0; i < knotTriggerImpacts.size(); i++) {
			
			impactList.addTriggerImpact(knotTriggerImpacts.get(i), "Invalid");
		}
		
		impactList.addKnotImpact(knot,             "create");
		impactList.addTableImpact(knot.getTable(), "create");
		
		return impactList;
	}

	public Object knotAttribute(Attribute attribute, Knot knot) {
		// TODO Auto-generated method stub
		return null;
	}
}