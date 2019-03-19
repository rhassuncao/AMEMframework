package br.com.ufabc.amem.model.dao.oracleobjects;

public class Column {
	
	/**
	 * 
	 */
	private String  name;
	/**
	 * 
	 */
	private String  dataType;
	/**
	 * 
	 */
	private boolean notNull;
	/**
	 * 
	 */
	private boolean primaryKey;
	/**
	 * 
	 */
	private boolean autoIncrement;
	
	
	private String defaultValue;
	
	/**
	 * @param name
	 * @param dataType
	 * @param notNull
	 * @param primaryKey
	 * @param autoIncrement
	 */
	public Column(String name, 
				  String dataType, 
				  boolean notNull, 
				  boolean primaryKey,
				  boolean autoIncrement,
				  String  defaultValue) {
		
		this.name          = name;
		this.dataType      = dataType;
		this.notNull       = notNull;
		this.primaryKey    = primaryKey;
		this.autoIncrement = autoIncrement;
		this.defaultValue  = defaultValue;
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
	public String getDataType() {
		
		return dataType;
	}

	/**
	 * @param dataType
	 */
	public void setDataType(String dataType) {
		
		this.dataType = dataType;
	}

	/**
	 * @return
	 */
	public boolean isNotNull() {
		
		return notNull;
	}

	/**
	 * @param notNull
	 */
	public void setNotNull(boolean notNull) {
		
		this.notNull = notNull;
	}

	/**
	 * @return
	 */
	public boolean isPrimaryKey() {
		
		return primaryKey;
	}

	/**
	 * @param primaryKey
	 */
	public void setPrimaryKey(boolean primaryKey) {
		
		this.primaryKey = primaryKey;
	}

	/**
	 * @return
	 */
	public boolean isAutoIncrement() {
		
		return autoIncrement;
	}

	/**
	 * @param autoIncrement
	 */
	public void setAutoIncrement(boolean autoIncrement) {
		
		this.autoIncrement = autoIncrement;
	}
	
	/**
	 * @return
	 */
	public String getDefaultValue() {
		
		return this.defaultValue;
	}

	/**
	 * @param name
	 */
	public void setDefaultValue(String defaultValue) {
		
		this.defaultValue = defaultValue;
	}
}
