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
	
	public ImpactList() {
		
		this.anchorImpacts    = new HashMap<Anchor,    String>();
		this.tieImpacts       = new HashMap<Tie,       String>();
		this.knotImpacts      = new HashMap<Knot,      String>();
		this.attributeImpacts = new HashMap<Attribute, String>();
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
	
	@Override
	public String toString() {
		
		String returnString = "";

		returnString += "===================================================================================================\n";
		returnString += Strings.getString("impacts") + "\n";
		returnString += "===================================================================================================\n";
		returnString += Strings.getString("anchorImpacts") + "\n";
		returnString += "===================================================================================================\n";
		returnString += Strings.getString("anchorImpact") + "\n";

		for (Entry<Anchor, String> impact : anchorImpacts.entrySet()) {
				
			returnString += Strings.completeWithSpace(impact.getKey().getDescriptor(), 20) + ": ";
			returnString += impact.getValue();
			returnString += "\n";
		}
		
		returnString += "===================================================================================================\n";
		
		returnString += Strings.getString("attributeImpacts") + "\n";
		returnString += "===================================================================================================\n";
		returnString += Strings.getString("attributeImpact") + "\n";

		for (Entry<Attribute, String> impact : attributeImpacts.entrySet()) {
				
			returnString += Strings.completeWithSpace(impact.getKey().getDescriptor(), 20) + ": ";
			returnString += impact.getValue();
			returnString += "\n";
		}

		returnString += "===================================================================================================\n";

		returnString += "===================================================================================================\n";
	
		returnString += Strings.getString("tieImpacts") + "\n";
		returnString += "===================================================================================================\n";
		returnString += Strings.getString("tieImpact") + "\n";

		for (Entry<Tie, String> impact : tieImpacts.entrySet()) {
				
			returnString += Strings.completeWithSpace(impact.getKey().getDescriptor(), 20) + ": ";
			returnString += impact.getValue();
			returnString += "\n";
		}

		returnString += "===================================================================================================\n";
		
		returnString += Strings.getString("knotImpacts") + "\n";
		returnString += "===================================================================================================\n";
		returnString += Strings.getString("knotImpact") + "\n";

		for (Entry<Knot, String> impact : knotImpacts.entrySet()) {
				
			returnString += Strings.completeWithSpace(impact.getKey().getDescriptor(), 20) + ": ";
			returnString += impact.getValue();
			returnString += "\n";
		}

		returnString += "===================================================================================================\n";
		
		return returnString;
	}
}