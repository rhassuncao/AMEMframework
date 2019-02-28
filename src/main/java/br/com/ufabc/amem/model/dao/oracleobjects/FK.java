package br.com.ufabc.amem.model.dao.oracleobjects;

public class FK {
	
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String tableSchema;
	/**
	 * 
	 */
	private String table;
	/**
	 * 
	 */
	private Column column;
	/**
	 * 
	 */
	private String externalTableSchema;
	/**
	 * 
	 */
	private String externalTable;
	/**
	 * 
	 */
	private Column externalColumn;
	
	/**
	 * @param name
	 * @param tableSchema
	 * @param table
	 * @param column
	 * @param externalTableSchema
	 * @param externalTable
	 * @param externalColumn
	 */
	public FK(String name, 
			  String tableSchema, 
			  String table, 
			  Column column, 
			  String externalTableSchema,
			  String externalTable, 
			  Column externalColumn) {
		
		this.name                = name;
		this.tableSchema         = tableSchema;
		this.table               = table;
		this.column              = column;
		this.externalTableSchema = externalTableSchema;
		this.externalTable       = externalTable;
		this.externalColumn      = externalColumn;
	}

	/**
	 * @return
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getTableSchema() {
		
		return tableSchema;
	}

	/**
	 * @param tableSchema
	 */
	public void setTableSchema(String tableSchema) {
		
		this.tableSchema = tableSchema;
	}

	/**
	 * @return
	 */
	public String getTable() {
		
		return table;
	}

	/**
	 * @param table
	 */
	public void setTable(String table) {
		
		this.table = table;
	}

	/**
	 * @return
	 */
	public Column getColumn() {
		
		return column;
	}

	/**
	 * @param column
	 */
	public void setColumn(Column column) {
		
		this.column = column;
	}

	/**
	 * @return
	 */
	public String getExternalTableSchema() {
		
		return externalTableSchema;
	}

	/**
	 * @param externalTableSchema
	 */
	public void setExternalTableSchema(String externalTableSchema) {
		
		this.externalTableSchema = externalTableSchema;
	}

	/**
	 * @return
	 */
	public String getExternalTable() {
		
		return externalTable;
	}

	/**
	 * @param externalTable
	 */
	public void setExternalTable(String externalTable) {
		
		this.externalTable = externalTable;
	}

	/**
	 * @return
	 */
	public Column getExternalColumn() {
		
		return externalColumn;
	}

	/**
	 * @param externalColumn
	 */
	public void setExternalColumn(Column externalColumn) {
		
		this.externalColumn = externalColumn;
	}
}