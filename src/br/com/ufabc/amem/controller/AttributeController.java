package br.com.ufabc.amem.controller;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.Attribute;
import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.dao.AttributeDao;
import br.com.ufabc.amem.util.Validate;

public class AttributeController {

	public void createAttribute(String  anchorCapsuleString,
							   String  anchorString,
			 				   String  descriptor, 
		                       String  mnemonicString, 
		                       String  capsuleString, 
		                       String  dataRange, 
		                       String  description,
		                       String  timeRange,
		                       String  StringCapsuleKnot,
		                       String  knotString) throws InvalidObject, SQLException {
		
		//Validate objects
		//TODO why not put the validation in the objetc model?
		Validate validate         = new Validate();
		Capsule  capsuleAnchor    = validate.validateCapsule(anchorCapsuleString);
		Anchor   anchor           = validate.validateAnchor(anchorString, capsuleAnchor);
		descriptor                = validate.validateDescriptor(descriptor);
		String   mnemonic         = validate.validateMnemonic(mnemonicString, new Attribute (null, null, null, null, null, null, null, null));
		Capsule  capsuleAttribute = validate.validateCapsule(capsuleString);
		dataRange                 = validate.validateDataRange(dataRange);
		description               = validate.validateDescription(description);
		timeRange                 = validate.validateTimeRange(timeRange);
		Capsule  capsuleknot      = validate.validateCapsule(StringCapsuleKnot);
		Knot     knot             = validate.validateKnot(knotString, capsuleknot);
		
		Attribute attribute = new Attribute(anchor, descriptor, mnemonic, capsuleAttribute, 
				dataRange, description, timeRange, knot);
		
		AttributeDao attributeDao = new AttributeDao();
		attributeDao.createAttribute(attribute);
	}

	public void historizeAttribute(String attributeCapsuleString, 
								   String attributeString,
								   String anchorCapsuleString,
								   String timeRange, 
								   String defaultTime) throws InvalidObject, ObjectAlreadyCreated, SQLException {
		
		//Validate objects
		Validate validate        = new Validate();
		Capsule attributeCapsule = validate.validateCapsule(attributeCapsuleString);
		Capsule anchorCapsule    = validate.validateCapsule(anchorCapsuleString);
		Attribute attribute      = validate.validateAttribute(attributeCapsule, attributeString, anchorCapsule);
		timeRange                = validate.validateTimeRange(timeRange);
		
		attribute.setTimeRange(timeRange);
				
		AttributeDao attributeDao = new AttributeDao();
		attributeDao.historizeAttribute(attribute);
	}
}