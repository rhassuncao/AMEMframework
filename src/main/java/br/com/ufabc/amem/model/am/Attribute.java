package br.com.ufabc.amem.model.am;

public class Attribute  extends AnchorObject{

	/**
	 * 
	 */
	protected Anchor  anchor;
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
	protected String  dataRange;
	/**
	 * 
	 */
	protected String  description;
	/**
	 * 
	 */
	protected String  timeRange;
	/**
	 * 
	 */
	protected Knot    knot;
	
	/**
	 * @param anchor
	 * @param descriptor
	 * @param mnemonic
	 * @param capsule
	 * @param dataRange
	 * @param description
	 * @param timeRange
	 * @param knot
	 */
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
	
	/**
	 * @return
	 */
	public Anchor getAnchor() {
		
		return anchor;
	}

	/**
	 * @param anchor
	 */
	public void setAnchor(Anchor anchor) {
		
		this.anchor = anchor;
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
	public String getDataRange() {
		
		return dataRange;
	}

	/**
	 * @param dataRange
	 */
	public void setDataRange(String dataRange) {
		
		this.dataRange = dataRange;
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
	
	/**
	 * @return
	 */
	public String getTimeRange() {
		
		return this.timeRange;
	}
	
	/**
	 * @param timeRange
	 */
	public void setTimeRange(String timeRange) {
		
		this.timeRange = timeRange;
	}
	
	/**
	 * @return
	 */
	public Knot getKnot() {
		
		return this.knot;
	}
	
	/**
	 * @param knot
	 */
	public void setKnot(Knot knot) {
		
		this.knot = knot;
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
		
		return 	this.getAnchor().getMnemonic()       + "_" 
			      + this.getMnemonic()               + "_"
			      + this.getAnchor().getDescriptor() + "_"
			      + this.getDescriptor();
	}
	
	/**
	 * @return
	 */
	public boolean isKnotted() {
		
		if(this.knot == null || this.dataRange != null) {
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * @return
	 */
	public boolean isHistorized() {
		
		if(this.timeRange == null) {
			
			return false;
		}
		
		return true;
	}
}
