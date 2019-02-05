package br.com.ufabc.amem.util;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.Attribute;
import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.am.Tie;
import br.com.ufabc.amem.model.dao.AnchorDao;
import br.com.ufabc.amem.model.dao.AttributeDao;
import br.com.ufabc.amem.model.dao.CapsuleDao;
import br.com.ufabc.amem.model.dao.KnotDao;

public class Validate {
	
	public Anchor validateAnchor(String anchorString, Capsule capsule) throws InvalidObject, SQLException {
		
		//TODO uppercase is only at oracle
		String[] parts      = anchorString.split("_");
		Anchor anchorTest   = new Anchor(parts[1].toUpperCase(), parts[0].toUpperCase(), capsule, null, false, null);
		AnchorDao anchorDao = new AnchorDao();
		Anchor anchor       = anchorDao.selectAnchor(anchorTest);
		
		if(anchor == null) {
			
			throw new InvalidObject(anchorTest);
		} 
		return anchor;
	}
	
	public Capsule validateCapsule(String capsuleString) throws InvalidObject, SQLException {
		
		//TODO uppercase is only at oracle
		//TODO I did it because a knot capsule can be null in the attribute creatin
		//But what is someone put an anchor capsule as null?
		if(capsuleString.equals("null")) {
			
			return null;
		} 
		
		CapsuleDao capsuleDao = new CapsuleDao();
		Capsule capsuleTest   = new Capsule(capsuleString.toUpperCase());
		Capsule capsule       = capsuleDao.selectCapsule(capsuleTest);
		
		if(capsule == null) {
			
			throw new InvalidObject(capsuleTest);
		}
		return capsule;
	}

	//TODO uppercase is only at oracle
	public String validateMnemonic(String mnemonic, Object object) throws InvalidObject {

		//TODO
		if(object instanceof Anchor || object instanceof Tie) {
			
			if(mnemonic.length() != 2) {
				
				throw new InvalidObject(mnemonic);
			}
		} else if(object instanceof Knot || object instanceof Attribute) {
			
			if(mnemonic.length() != 3) {
				
				throw new InvalidObject(mnemonic);
			}
		}
		return mnemonic.toUpperCase();
	}

	//TODO uppercase is only at oracle
	public Knot validateKnot(String knotString, Capsule capsule) throws InvalidObject, SQLException {
		
		if(knotString.equals("null")) {
			
			return null;
		} 
		
		String[] parts  = knotString.split("_");
		Knot knotTest   = new Knot(parts[1].toUpperCase(), parts[0].toUpperCase(), capsule, null, null, false, null);
		KnotDao knotDao = new KnotDao();
		Knot knot       = knotDao.selectKnot(knotTest);
		
		if(knot == null) {
			
			throw new InvalidObject(knotTest);
		}
		return knot;
	}
	
	public String validateDescription (String description) throws InvalidObject {
		
		//biggest description size for oracle
		if(description.length() > 4000) {
			
			throw new InvalidObject(description);
		}
		return description;
	}
	
	public String  validateIdentity(String identity) {
		//TODO
		return identity;
	}

	public boolean validateGenerator(String generatorString) {
		
		return Boolean.valueOf(generatorString);
	}

	public String validateDataRange(String dataRange) {
		// TODO
		return dataRange;
	}

	public String validateTimeRange(String timeRange) {
		
		if(timeRange.equals("null")){
			
			return null;
		}
		//TODO verify the timeRange
		return timeRange;
	}

	public String validateDescriptor(String descriptorString) {
		// TODO
		return descriptorString;
	}

	public Attribute validateAttribute(Capsule attributeCapsule, 
									   String attributeString,
									   Capsule anchorCapsule) throws InvalidObject, SQLException {
		
		String[] parts      = attributeString.split("_");
		String anchorString = parts[0] + "_" + parts[2];
		Anchor anchorTest   = validateAnchor(anchorString, anchorCapsule);
		AnchorDao anchorDao = new AnchorDao();
		Anchor anchor       = anchorDao.selectAnchor(anchorTest);
		
		if(anchor == null) {
			
			throw new InvalidObject(anchorTest);
		} 
			
		Attribute attribute       = new Attribute(anchor, parts[3], parts[1], attributeCapsule, null, null, null, null);
		AttributeDao attributeDao = new AttributeDao();
		attribute                 = attributeDao.selectAttribute(attribute);
		
		return attribute;
	}

	public String validateRole(String role) {
		// TODO Auto-generated method stub
		return role;
	}

	public boolean validateIdentifierN(String identifierNString) {

		return Boolean.valueOf(identifierNString);
	}

	public String validateSequenceName(String sequenceName) {
		
		//max oracle sequence name size
		if(sequenceName.length() > 30) {
			
			sequenceName = sequenceName.substring(0, 30);
		}
		
		return sequenceName;
	}

	public String validateTimeFormat(String defaultTimeFormat) {
		// TODO Auto-generated method stub
		return defaultTimeFormat;
	}
}