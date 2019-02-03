package br.com.ufabc.amem.model.dao.oracleobjects;

public class FK {
	
	private String schema;
	private String name;
	private String tableSchema;
	private String table;
	private Column column;
	private String externalTableSchema;
	private String externalTable;
	private Column externalColumn;
	
	public FK(String schema, 
			  String name, 
			  String tableSchema, 
			  String table, 
			  Column column, 
			  String externalTableSchema,
			  String externalTable, 
			  Column externalColumn) {
		
		this.schema              = schema;
		this.name                = name;
		this.tableSchema         = tableSchema;
		this.table               = table;
		this.column              = column;
		this.externalTableSchema = externalTableSchema;
		this.externalTable       = externalTable;
		this.externalColumn      = externalColumn;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public String getExternalTableSchema() {
		return externalTableSchema;
	}

	public void setExternalTableSchema(String externalTableSchema) {
		this.externalTableSchema = externalTableSchema;
	}

	public String getExternalTable() {
		return externalTable;
	}

	public void setExternalTable(String externalTable) {
		this.externalTable = externalTable;
	}

	public Column getExternalColumn() {
		return externalColumn;
	}

	public void setExternalColumn(Column externalColumn) {
		this.externalColumn = externalColumn;
	}
}
