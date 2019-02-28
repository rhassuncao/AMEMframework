package br.com.ufabc.amem.model.am;

public class Anchor extends AnchorObject{
	
	/**
	 * 
	 */
	protected String  descriptor;
	/**
	 * 
	 */
	protected String  mnemonic;
	/**
	 * 
	 */
	protected Capsule capsule;
	/**
	 * 
	 */
	protected String  identity;
	/**
	 * 
	 */
	protected boolean generator;
	/**
	 * 
	 */
	protected String  description;
	
	/**
	 * @param descriptor
	 * @param mnemonic
	 * @param capsule
	 * @param identity
	 * @param generator
	 * @param description
	 */
	public Anchor(String  descriptor, 
				  String  mnemonic, 
				  Capsule  capsule, 
				  String  identity, 
				  boolean generator,
				  String  description) {
		
		this.descriptor  = descriptor;
		this.mnemonic    = mnemonic;
		this.capsule     = capsule;
		this.identity    = identity;
		this.generator   = generator;
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.am.AnchorObject#getDescriptor()
	 */
	public String getDescriptor() {
		
		return descriptor;
	}

	/**
	 * @param descriptor
	 */
	public void setDescriptor(String descriptor) {
		
		this.descriptor = descriptor;
	}

	/**
	 * @return
	 */
	public String getMnemonic() {
		
		return mnemonic;
	}

	/**
	 * @param mnemonic
	 */
	public void setMnemonic(String mnemonic) {
		
		this.mnemonic = mnemonic;
	}

	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.am.AnchorObject#getCapsule()
	 */
	public Capsule getCapsule() {
		
		return capsule;
	}

	/**
	 * @param capsule
	 */
	public void setCapsule(Capsule capsule) {
		
		this.capsule = capsule;
	}

	/**
	 * @return
	 */
	public String getIdentity() {
		
		return identity;
	}

	/**
	 * @param identity
	 */
	public void setIdentity(String identity) {
		
		this.identity = identity;
	}

	/**
	 * @return
	 */
	public boolean isGenerator() {
		
		return generator;
	}

	/**
	 * @param generator
	 */
	public void setGenerator(boolean generator) {
		
		this.generator = generator;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		return this.getCapsule().getName() + "." + getTable();
	}
	
	/* (non-Javadoc)
	 * @see br.com.ufabc.amem.model.am.AnchorObject#getTable()
	 */
	public String getTable() {
		
		return this.getMnemonic() + "_" + this.getDescriptor();
	}
}
