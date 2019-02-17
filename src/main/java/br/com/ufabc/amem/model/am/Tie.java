package br.com.ufabc.amem.model.am;

public class Tie  extends AnchorObject{
	
	private Capsule capsuleAnchor1;
	private Anchor  anchor1;
	private String  role1;
	private boolean identifier1N;
	private Capsule capsuleAnchor2;
	private Anchor  anchor2;
	private String  role2;
	private boolean identifier2N;
	private Capsule capsule;
	private String  description;
	private String  timeRange;
	
	public Tie(Capsule capsuleAnchor1, 
			   Anchor  anchor1, 
			   String  role1, 
			   boolean identifier1n, 
			   Capsule capsuleAnchor2,
			   Anchor  anchor2, 
			   String  role2, 
			   boolean identifier2n, 
			   Capsule capsule, 
			   String  description, 
			   String  timeRange) {

		this.capsuleAnchor1 = capsuleAnchor1;
		this.anchor1        = anchor1;
		this.role1          = role1;
		this.identifier1N   = identifier1n;
		this.capsuleAnchor2 = capsuleAnchor2;
		this.anchor2        = anchor2;
		this.role2          = role2;
		this.identifier2N   = identifier2n;
		this.capsule        = capsule;
		this.description    = description;
		this.timeRange      = timeRange;
	}

	public Capsule getCapsuleAnchor1() {
		
		return capsuleAnchor1;
	}

	public void setCapsuleAnchor1(Capsule capsuleAnchor1) {
		
		this.capsuleAnchor1 = capsuleAnchor1;
	}

	public Anchor getAnchor1() {
		
		return anchor1;
	}

	public void setAnchor1(Anchor anchor1) {
		
		this.anchor1 = anchor1;
	}

	public String getRole1() {
		
		return role1;
	}

	public void setRole1(String role1) {
		
		this.role1 = role1;
	}

	public boolean isIdentifier1N() {
		
		return identifier1N;
	}

	public void setIdentifier1N(boolean identifier1n) {
		
		identifier1N = identifier1n;
	}

	public Capsule getCapsuleAnchor2() {
		
		return capsuleAnchor2;
	}

	public void setCapsuleAnchor2(Capsule capsuleAnchor2) {
		
		this.capsuleAnchor2 = capsuleAnchor2;
	}

	public Anchor getAnchor2() {
		
		return anchor2;
	}

	public void setAnchor2(Anchor anchor2) {
		
		this.anchor2 = anchor2;
	}

	public String getRole2() {
		
		return role2;
	}

	public void setRole2(String role2) {
		
		this.role2 = role2;
	}

	public boolean isIdentifier2N() {
		
		return identifier2N;
	}

	public void setIdentifier2N(boolean identifier2n) {
		
		identifier2N = identifier2n;
	}

	public Capsule getCapsule() {
		
		return capsule;
	}

	public void setCapsule(Capsule capsule) {
		
		this.capsule = capsule;
	}

	public String getDescription() {
		
		return description;
	}

	public void setDescription(String description) {
		
		this.description = description;
	}

	public String getTimeRange() {
		
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		
		this.timeRange = timeRange;
	}

	public String toString() {
		
		return this.getCapsule().getName() + "." + getTable();
	}
	
	public String getTable() {
		
		return this.getAnchor1().getMnemonic()    + "_"
				+ this.getRole1()                 + "_"
				+ this.getAnchor2().getMnemonic() + "_"
				+ this.getRole2() ;
	}

	public String getDescriptor() {
		// TODO Auto-generated method stub
		return "";
	}
}