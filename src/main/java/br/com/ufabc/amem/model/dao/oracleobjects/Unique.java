package br.com.ufabc.amem.model.dao.oracleobjects;

public class Unique {

	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private Column column;
	
	/**
	 * @param name
	 * @param column
	 */
	public Unique(String name, Column column) {

		this.name   = name;
		this.column = column;
	}

	/**
	 * @return
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * @return
	 */
	public Column getColumn() {
		
		return column;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * @param column
	 */
	public void setColumn(Column column) {
		
		this.column = column;
	}
}