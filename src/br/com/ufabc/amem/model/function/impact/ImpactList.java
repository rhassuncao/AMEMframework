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
	
	public Map<Anchor,    String> anchorImpacts;
	public Map<Tie,       String> tieImpacts;
	public Map<Knot,      String> knotImpacts;
	public Map<Attribute, String> attributeImpacts;
	public Map<String,    String> tableImpacts;
	public Map<String,    String> functionImpacts;
	public Map<String,    String> procedureImpacts;
	public Map<String,    String> triggerImpacts;
	public Map<String,    String> constraintImpacts;
	public Map<String,    String> viewtImpacts;
	
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
		this.viewtImpacts      = new HashMap<String,    String>();
	}

	public void addAnchorImpact(Anchor anchor, String operation) {
		
		this.anchorImpacts.put(anchor, operation);
	}
	
	public void addTieImpact (Tie tie, String operation) {
		
		this.tieImpacts.put(tie, operation);
	}
	
	public void addKnotImpact(Knot knot, String operation) {
		
		this.knotImpacts.put(knot, operation);
	}
	
	public void addAttributeImpact(Attribute attribute, String operation) {
		
		this.attributeImpacts.put(attribute, operation);
	}
	
	public void addTableImpact(String table, String operation) {
		
		this.tableImpacts.put(table, operation);
	}
	
	public void addFunctionImpact(String function, String operation) {
		
		this.functionImpacts.put(function, operation);
	}
	
	public void addProcedureImpact(String procedure, String operation) {
		
		this.procedureImpacts.put(procedure, operation);
	}
	
	public void addTriggerImpact(String trigger, String operation) {
		
		this.triggerImpacts.put(trigger, operation);
	}

	public void addConstraintImpact(String constraint, String operation) {
		
		this.constraintImpacts.put(constraint, operation);
	}
	
	public void addViewImpact(String view, String operation) {
		
		this.constraintImpacts.put(view, operation);
	}

	public Map<Anchor, String> getAnchorImpacts() {
		
		return anchorImpacts;
	}

	public Map<Tie, String> getTieImpacts() {
		
		return tieImpacts;
	}

	public Map<Knot, String> getKnotImpacts() {
		
		return knotImpacts;
	}

	public Map<Attribute, String> getAttributeImpacts() {
		
		return attributeImpacts;
	}
	
	public Map<String, String> getTableImpacts() {
		
		return tableImpacts;
	}

	public Map<String, String> getFunctionImpacts() {
		
		return functionImpacts;
	}

	public Map<String, String> getProcedureImpacts() {
		
		return procedureImpacts;
	}

	public Map<String, String> getTriggerImpacts() {
		
		return triggerImpacts;
	}

	public Map<String, String> getConstraintsImpacts() {
		
		return constraintImpacts;
	}
	
	public Map<String, String> getViewImpacts() {
		
		return this.viewtImpacts;
	}
	
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