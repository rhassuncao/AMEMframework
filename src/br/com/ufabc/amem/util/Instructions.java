package br.com.ufabc.amem.util;

import java.util.HashMap;
import java.util.Map;

public class Instructions {
	
	Map<String, String> map;

	//TODO add description
	public Instructions() {
		
		this.map = new HashMap<String, String>();
		map.put("createAnchor",       "descriptor mnemonic capsule dataRange generator description");
		map.put("createAttribute",    "anchorCapcule anchor descriptor mnemonic attributeCapsule dataRange description timeRange knotCapsule knot");
		map.put("createKnot",         "knot mnemonic dataRange identity generator description");
		map.put("createTie",          "");
		map.put("connectionInfo",     "no prameters");
		map.put("connectiontest",     "no parameters");
		map.put("exit",               "no parameters");
		map.put("historizeAttribute", "capsule, attribute, anchorCapsule, timeRange, defaultTime");
		//map.put("", "");
	}
	
	public String getInstruction(String function) {
		
		return this.map.get(function);
	}
	
	public Map<String, String> getAllInstructions() {
		
		return this.map;
	}
}
