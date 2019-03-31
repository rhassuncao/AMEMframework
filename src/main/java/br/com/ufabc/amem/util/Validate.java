package br.com.ufabc.amem.util;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.AnchorObject;
import br.com.ufabc.amem.model.am.Attribute;
import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.am.Tie;
import br.com.ufabc.amem.model.dao.AnchorDao;
import br.com.ufabc.amem.model.dao.AttributeDao;
import br.com.ufabc.amem.model.dao.CapsuleDao;
import br.com.ufabc.amem.model.dao.KnotDao;

public class Validate {
	
	/**
	 * @param anchorString
	 * @param capsule
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 */
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
	
	/**
	 * @param capsuleString
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 */
	public Capsule validateCapsule(String capsuleString) throws InvalidObject, SQLException {
		
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
	/**
	 * @param mnemonic
	 * @param object
	 * @return
	 * @throws InvalidObject
	 */
	public String validateMnemonic(String mnemonic, AnchorObject object) throws InvalidObject {

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
	/**
	 * @param knotString
	 * @param capsule
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 */
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
	
	/**
	 * @param description
	 * @return
	 * @throws InvalidObject
	 */
	public String validateDescription (String description) throws InvalidObject {
		
		//biggest description size for oracle
		if(description.length() > 4000) {
			
			throw new InvalidObject(description);
		}
		return description;
	}
	
	/**
	 * @param identity
	 * @return
	 */
	public String  validateIdentity(String identity) {
		//TODO
		return identity;
	}

	/**
	 * @param generatorString
	 * @return
	 */
	public boolean validateGenerator(String generatorString) {
		
		return Boolean.valueOf(generatorString);
	}

	/**
	 * @param dataRange
	 * @return
	 */
	public String validateDataRange(String dataRange) {
		// TODO
		return dataRange;
	}

	/**
	 * @param timeRange
	 * @return
	 */
	public String validateTimeRange(String timeRange) {
		
		if(timeRange.equals("null")){
			
			return null;
		}
		//TODO verify the timeRange
		return timeRange;
	}

	/**
	 * @param descriptorString
	 * @return
	 */
	public String validateDescriptor(String descriptorString) {
		// TODO
		return descriptorString;
	}

	/**
	 * @param attributeCapsule
	 * @param attributeString
	 * @param anchorCapsule
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 */
	public Attribute validateAttribute(Capsule attributeCapsule, 
									   String  attributeString,
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

	/**
	 * @param role
	 * @return
	 */
	public String validateRole(String role) {
		// TODO Auto-generated method stub
		return role;
	}

	/**
	 * @param identifierNString
	 * @return
	 */
	public boolean validateIdentifierN(String identifierNString) {

		return Boolean.valueOf(identifierNString);
	}

	/**
	 * @param sequenceName
	 * @return
	 */
	public String validateSequenceName(String sequenceName) {
		
		//max oracle sequence name size
		if(sequenceName.length() > 30) {
			
			sequenceName = sequenceName.substring(0, 30);
		}
		
		return sequenceName;
	}

	/**
	 * @param defaultTimeFormat
	 * @return
	 */
	public String validateTimeFormat(String defaultTimeFormat) {
		// TODO Auto-generated method stub
		return defaultTimeFormat;
	}
}