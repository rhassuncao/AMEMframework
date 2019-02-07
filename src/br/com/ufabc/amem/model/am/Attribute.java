package br.com.ufabc.amem.model.am;

public class Attribute  extends AnchorObject{

	protected Anchor  anchor;
	protected String  descriptor;
	protected String  mnemonic;
	protected Capsule capsule;
	protected String  dataRange;
	protected String  description;
	protected String  timeRange;
	protected Knot    knot;
	
	public Attribute(Anchor  anchor,
					 String  descriptor, 
				     String  mnemonic, 
				     Capsule capsule, 
				     String  dataRange, 
				     String  description,
				     String  timeRange,
				     Knot    knot) {
		
		this.anchor      = anchor;
		this.descriptor  = descriptor;
		this.mnemonic    = mnemonic;
		this.capsule     = capsule;
		this.dataRange   = dataRange;
		this.description = description;
		this.timeRange   = timeRange;
		this.knot        = knot;
	}
	
	public Anchor getAnchor() {
		
		return anchor;
	}

	public void setAnchor(Anchor anchor) {
		
		this.anchor = anchor;
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

	public String getDescription() {
		
		return description;
	}

	public void setDescription(String description) {
		
		this.description = description;
	}
	
	public String getTimeRange() {
		
		return this.timeRange;
	}
	
	public void setTimeRange(String timeRange) {
		
		this.timeRange = timeRange;
	}
	
	public Knot getKnot() {
		
		return this.knot;
	}
	
	public void setKnot(Knot knot) {
		
		this.knot = knot;
	}
	
	public String toString() {
		
		return this.getCapsule().getName() + "." + getTable();
	}
	
	public String getTable() {
		
		return 	this.getAnchor().getMnemonic()       + "_" 
			      + this.getMnemonic()               + "_"
			      + this.getAnchor().getDescriptor() + "_"
			      + this.getDescriptor();
	}
	
	public boolean isKnotted() {
		
		if(this.knot == null || this.dataRange != null) {
			
			return false;
		}
		
		return true;
	}
	
	public boolean isHistorized() {
		
		if(this.timeRange == null) {
			
			return false;
		}
		
		return true;
	}
}
