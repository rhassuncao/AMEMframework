package br.com.ufabc.amem.model.am;

public abstract class AnchorObject {
	
	/**
	 * @return
	 */
	public abstract String getDescriptor();

	/**
	 * @return
	 */
	public abstract Capsule getCapsule();
	
	/**
	 * @return
	 */
	public abstract String  getTable();
}