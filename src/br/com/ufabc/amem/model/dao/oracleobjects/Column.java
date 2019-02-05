package br.com.ufabc.amem.model.dao.oracleobjects;

public class Column {
	
	private String  name;
	private String  dataType;
	private boolean notNull;
	private boolean primaryKey;
	private boolean autoIncrement;
	
	public Column(String name, 
				  String dataType, 
				  boolean notNull, 
				  boolean primaryKey,
				  boolean autoIncrement) {
		
		this.name          = name;
		this.dataType      = dataType;
		this.notNull       = notNull;
		this.primaryKey    = primaryKey;
		this.autoIncrement = autoIncrement;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public String getDataType() {
		
		return dataType;
	}

	public void setDataType(String dataType) {
		
		this.dataType = dataType;
	}

	public boolean isNotNull() {
		
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		
		this.notNull = notNull;
	}

	public boolean isPrimaryKey() {
		
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		
		this.primaryKey = primaryKey;
	}

	public boolean isAutoIncrement() {
		
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		
		this.autoIncrement = autoIncrement;
	}
}
