package br.com.ufabc.amem.util;

import java.util.HashMap;
import java.util.Map;

public class Instructions {
	
	Map<String, String> map;

	//TODO add description
	public Instructions() {
		
		this.map = new HashMap<String, String>();
		map.put("CREATEANCHOR",       "descriptor, mnemonic, capsule, dataRange, generator, description");
		map.put("CREATEATTRIBUTE",    "anchorCapcule, anchor, descriptor, mnemonic, attributeCapsule, dataRange, description, timeRange, knotCapsule, knot");
		map.put("CREATEKNOT",         "knot, mnemonic, dataRange, identity, generator, description");
		map.put("CREATETIE",          "");
		map.put("CONNECTIONINFO",     "no prameters");
		map.put("CONNECTIONTEST",     "no parameters");
		map.put("EXIT",               "no parameters");
		map.put("HELP",               "functionName");
		map.put("HISTORIZEATTRIBUTE", "capsule, attribute, anchorCapsule, timeRange, defaultTime");
		//map.put("", "");
	}
	
	public String getInstruction(String function) {
		
		return this.map.get(function);
	}
	
	public Map<String, String> getAllInstructions() {
		
		return this.map;
	}
}
