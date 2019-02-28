package br.com.ufabc.amem.model.function.impact;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.Attribute;
import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.am.Tie;
import br.com.ufabc.amem.util.Strings;

public class ImpactList {
	
	/**
	 * 
	 */
	public Map<Anchor,    String> anchorImpacts;
	/**
	 * 
	 */
	public Map<Tie,       String> tieImpacts;
	/**
	 * 
	 */
	public Map<Knot,      String> knotImpacts;
	/**
	 * 
	 */
	public Map<Attribute, String> attributeImpacts;
	/**
	 * 
	 */
	public Map<String,    String> tableImpacts;
	/**
	 * 
	 */
	public Map<String,    String> functionImpacts;
	/**
	 * 
	 */
	public Map<String,    String> procedureImpacts;
	/**
	 * 
	 */
	public Map<String,    String> triggerImpacts;
	/**
	 * 
	 */
	public Map<String,    String> constraintImpacts;
	/**
	 * 
	 */
	public Map<String,    String> viewImpacts;
	
	/**
	 * 
	 */
	public ImpactList() {
		
		this.anchorImpacts     = new HashMap<Anchor,    String>();
		this.tieImpacts        = new HashMap<Tie,       String>();
		this.knotImpacts       = new HashMap<Knot,      String>();
		this.attributeImpacts  = new HashMap<Attribute, String>();
		this.tableImpacts      = new HashMap<String,    String>();
		this.functionImpacts   = new HashMap<String,    String>();
		this.procedureImpacts  = new HashMap<String,    String>();
		this.triggerImpacts    = new HashMap<String,    String>();
		this.constraintImpacts = new HashMap<String,    String>();
		this.viewImpacts       = new HashMap<String,    String>();
	}

	/**
	 * @param anchor
	 * @param operation
	 */
	public void addAnchorImpact(Anchor anchor, String operation) {
		
		this.anchorImpacts.put(anchor, operation);
	}
	
	/**
	 * @param tie
	 * @param operation
	 */
	public void addTieImpact (Tie tie, String operation) {
		
		this.tieImpacts.put(tie, operation);
	}
	
	/**
	 * @param knot
	 * @param operation
	 */
	public void addKnotImpact(Knot knot, String operation) {
		
		this.knotImpacts.put(knot, operation);
	}
	
	/**
	 * @param attribute
	 * @param operation
	 */
	public void addAttributeImpact(Attribute attribute, String operation) {
		
		this.attributeImpacts.put(attribute, operation);
	}
	
	/**
	 * @param table
	 * @param operation
	 */
	public void addTableImpact(String table, String operation) {
		
		this.tableImpacts.put(table, operation);
	}
	
	/**
	 * @param function
	 * @param operation
	 */
	public void addFunctionImpact(String function, String operation) {
		
		this.functionImpacts.put(function, operation);
	}
	
	/**
	 * @param procedure
	 * @param operation
	 */
	public void addProcedureImpact(String procedure, String operation) {
		
		this.procedureImpacts.put(procedure, operation);
	}
	
	/**
	 * @param trigger
	 * @param operation
	 */
	public void addTriggerImpact(String trigger, String operation) {
		
		this.triggerImpacts.put(trigger, operation);
	}

	/**
	 * @param constraint
	 * @param operation
	 */
	public void addConstraintImpact(String constraint, String operation) {
		
		this.constraintImpacts.put(constraint, operation);
	}
	
	/**
	 * @param view
	 * @param operation
	 */
	public void addViewImpact(String view, String operation) {
		
		this.constraintImpacts.put(view, operation);
	}

	/**
	 * @return
	 */
	public Map<Anchor, String> getAnchorImpacts() {
		
		return anchorImpacts;
	}

	/**
	 * @return
	 */
	public Map<Tie, String> getTieImpacts() {
		
		return tieImpacts;
	}

	/**
	 * @return
	 */
	public Map<Knot, String> getKnotImpacts() {
		
		return knotImpacts;
	}

	/**
	 * @return
	 */
	public Map<Attribute, String> getAttributeImpacts() {
		
		return attributeImpacts;
	}
	
	/**
	 * @return
	 */
	public Map<String, String> getTableImpacts() {
		
		return tableImpacts;
	}

	/**
	 * @return
	 */
	public Map<String, String> getFunctionImpacts() {
		
		return functionImpacts;
	}

	/**
	 * @return
	 */
	public Map<String, String> getProcedureImpacts() {
		
		return procedureImpacts;
	}

	/**
	 * @return
	 */
	public Map<String, String> getTriggerImpacts() {
		
		return triggerImpacts;
	}

	/**
	 * @return
	 */
	public Map<String, String> getConstraintImpacts() {
		
		return constraintImpacts;
	}
	
	/**
	 * @return
	 */
	public Map<String, String> getViewImpacts() {
		
		return this.viewImpacts;
	}
	
	/**
	 * @param anchorImpacts
	 */
	public void setAnchorImpacts(Map<Anchor, String> anchorImpacts) {
		
		this.anchorImpacts = anchorImpacts;
	}

	/**
	 * @param tieImpacts
	 */
	public void setTieImpacts(Map<Tie, String> tieImpacts) {
		
		this.tieImpacts = tieImpacts;
	}

	/**
	 * @param knotImpacts
	 */
	public void setKnotImpacts(Map<Knot, String> knotImpacts) {
		
		this.knotImpacts = knotImpacts;
	}

	/**
	 * @param attributeImpacts
	 */
	public void setAttributeImpacts(Map<Attribute, String> attributeImpacts) {
		
		this.attributeImpacts = attributeImpacts;
	}

	/**
	 * @param tableImpacts
	 */
	public void setTableImpacts(Map<String, String> tableImpacts) {
		
		this.tableImpacts = tableImpacts;
	}

	/**
	 * @param functionImpacts
	 */
	public void setFunctionImpacts(Map<String, String> functionImpacts) {
		
		this.functionImpacts = functionImpacts;
	}

	/**
	 * @param procedureImpacts
	 */
	public void setProcedureImpacts(Map<String, String> procedureImpacts) {
		
		this.procedureImpacts = procedureImpacts;
	}

	/**
	 * @param triggerImpacts
	 */
	public void setTriggerImpacts(Map<String, String> triggerImpacts) {
		
		this.triggerImpacts = triggerImpacts;
	}

	/**
	 * @param constraintImpacts
	 */
	public void setConstraintImpacts(Map<String, String> constraintImpacts) {
		
		this.constraintImpacts = constraintImpacts;
	}

	/**
	 * @param viewImpacts
	 */
	public void setViewImpacts(Map<String, String> viewImpacts) {
		
		this.viewImpacts = viewImpacts;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String returnString = "";

		returnString += "===================================================================================================\n";
		
		if(!anchorImpacts.isEmpty()) {
			
			returnString += Strings.getString("anchorImpacts") + "\n";
			returnString += "===================================================================================================\n";
			returnString += Strings.getString("anchorImpact") + "\n";

			for (Entry<Anchor, String> impact : anchorImpacts.entrySet()) {
					
				returnString += Strings.completeWithSpace(impact.getKey().getDescriptor(), 20) + ": ";
				returnString += impact.getValue();
				returnString += "\n";
			}
			returnString += "===================================================================================================\n";
		}
		
		if(!attributeImpacts.isEmpty()) {
			
			returnString += Strings.getString("attributeImpacts") + "\n";
			returnString += "===================================================================================================\n";
			returnString += Strings.getString("attributeImpact") + "\n";

			for (Entry<Attribute, String> impact : attributeImpacts.entrySet()) {
					
				returnString += Strings.completeWithSpace(impact.getKey().getDescriptor(), 20) + ": ";
				returnString += impact.getValue();
				returnString += "\n";
			}
			returnString += "===================================================================================================\n";	
		}

		if(!tieImpacts.isEmpty()) {
			
			returnString += Strings.getString("tieImpacts") + "\n";
			returnString += "===================================================================================================\n";
			returnString += Strings.getString("tieImpact") + "\n";

			for (Entry<Tie, String> impact : tieImpacts.entrySet()) {
					
				returnString += Strings.completeWithSpace(impact.getKey().getDescriptor(), 20) + ": ";
				returnString += impact.getValue();
				returnString += "\n";
			}
			returnString += "===================================================================================================\n";	
		}
	
		if(!knotImpacts.isEmpty()) {
			returnString += Strings.getString("knotImpacts") + "\n";
			returnString += "===================================================================================================\n";
			returnString += Strings.getString("knotImpact") + "\n";

			for (Entry<Knot, String> impact : knotImpacts.entrySet()) {
					
				returnString += Strings.completeWithSpace(impact.getKey().getDescriptor(), 20) + ": ";
				returnString += impact.getValue();
				returnString += "\n";
			}
			returnString += "===================================================================================================\n";
			
		}
		return returnString;
	}
}