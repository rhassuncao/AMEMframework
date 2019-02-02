package br.com.ufabc.amem.model;

public class Knot {
	
	protected String   descriptor;
	protected String   mnemonic;
	protected Capsule  capsule;
	protected String   dataRange;
	protected String   identity;
	protected boolean  generator;
	protected String   description;
	
	public Knot(String descriptor, 
			    String mnemonic, 
			    Capsule capsule, 
			    String dataRange, 
			    String identity,
			    boolean generator, 
			    String description) {

		this.descriptor  = descriptor;
		this.mnemonic    = mnemonic;
		this.capsule     = capsule;
		this.dataRange   = dataRange;
		this.identity    = identity;
		this.generator   = generator;
		this.description = description;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public Capsule getCapsule() {
		return capsule;
	}

	public void setCapsule(Capsule capsule) {
		this.capsule = capsule;
	}

	public String getDataRange() {
		return dataRange;
	}

	public void setDataRange(String dataRange) {
		this.dataRange = dataRange;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public boolean isGenerator() {
		return generator;
	}

	public void setGenerator(boolean generator) {
		this.generator = generator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		
		return this.capsule.getName() + "." + getTable();
	}
	
	public String getTable() {
		
		return this.getMnemonic() + "_" + this.getDescriptor();
	}
}
