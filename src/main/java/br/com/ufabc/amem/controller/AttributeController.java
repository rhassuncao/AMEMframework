package br.com.ufabc.amem.controller;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.Attribute;
import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.dao.AttributeDao;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Validate;

public class AttributeController {

	/**
	 * @param anchorCapsuleString
	 * @param anchorString
	 * @param descriptor
	 * @param mnemonicString
	 * @param capsuleString
	 * @param dataRange
	 * @param description
	 * @param timeRange
	 * @param StringCapsuleKnot
	 * @param knotString
	 * @throws InvalidObject
	 * @throws SQLException
	 */
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
	
	/**
	 * @param anchorCapsuleString
	 * @param anchorString
	 * @param descriptor
	 * @param mnemonicString
	 * @param capsuleString
	 * @param dataRange
	 * @param description
	 * @param timeRange
	 * @param StringCapsuleKnot
	 * @param knotString
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 */
	public ImpactList createAttributeImpact(String  anchorCapsuleString,
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
		
		return new AttributeDao().createAttributeImpact(attribute);
	}

	/**
	 * @param attributeCapsuleString
	 * @param attributeString
	 * @param anchorCapsuleString
	 * @param timeRange
	 * @param defaultTime
	 * @param defaultTimeFormat
	 * @throws InvalidObject
	 * @throws ObjectAlreadyCreated
	 * @throws SQLException
	 */
	public void historizeAttribute(String attributeCapsuleString, 
								   String attributeString,
								   String anchorCapsuleString,
								   String timeRange, 
								   String defaultTime,
								   String defaultTimeFormat) throws InvalidObject, ObjectAlreadyCreated, SQLException {
		
		//Validate objects
		Validate  validate         = new Validate();
		Capsule   attributeCapsule = validate.validateCapsule(attributeCapsuleString);
		Capsule   anchorCapsule    = validate.validateCapsule(anchorCapsuleString);
		Attribute attribute        = validate.validateAttribute(attributeCapsule, attributeString, anchorCapsule);
		
		timeRange                  = validate.validateTimeRange(timeRange);
		defaultTimeFormat          = validate.validateTimeFormat(defaultTimeFormat);
		
		attribute.setTimeRange(timeRange);
				
		AttributeDao attributeDao = new AttributeDao();
		attributeDao.historizeAttribute(attribute, defaultTime, defaultTimeFormat);
	}

	public ImpactList historizeAttributeImpacts(String attributeCapsuleString, 
								   String attributeString,
								   String anchorCapsuleString,
								   String timeRange, 
								   String defaultTime,
								   String defaultTimeFormat) throws InvalidObject, ObjectAlreadyCreated, SQLException {
		
		//Validate objects
		Validate  validate         = new Validate();
		Capsule   attributeCapsule = validate.validateCapsule(attributeCapsuleString);
		Capsule   anchorCapsule    = validate.validateCapsule(anchorCapsuleString);
		Attribute attribute        = validate.validateAttribute(attributeCapsule, attributeString, anchorCapsule);
		
		timeRange                  = validate.validateTimeRange(timeRange);
		defaultTimeFormat          = validate.validateTimeFormat(defaultTimeFormat);
		
		attribute.setTimeRange(timeRange);
				
		return new AttributeDao().historizeAttributeImpact(attribute);
	}

	public ImpactList knotAttributeImpacts(String attributeCapsuleString, 
										   String attributeString,
										   String anchorCapsuleString,
										   String descriptorString, 
										   String mnemonicString, 
										   String capsuleString,
										   String dataRange,
										   String identityString,
										   String generatorString, 
										   String description) throws InvalidObject, SQLException {

		//Validate objects
		Validate validate          = new Validate();
		Capsule   attributeCapsule = validate.validateCapsule(attributeCapsuleString);
		Capsule   anchorCapsule    = validate.validateCapsule(anchorCapsuleString);
		Attribute attribute        = validate.validateAttribute(attributeCapsule, attributeString, anchorCapsule);
		descriptorString           = validate.validateDescriptor(descriptorString);
		mnemonicString             = validate.validateMnemonic(mnemonicString, new Knot(null, null, null, null, null, false, null));
		Capsule capsule            = validate.validateCapsule(capsuleString);
		dataRange                  = validate.validateDataRange(dataRange);
		identityString             = validate.validateIdentity(identityString);
		boolean generator          = validate.validateGenerator(generatorString);
		description                = validate.validateDescription(description);

		Knot knot = new Knot(descriptorString, 
							 mnemonicString, 
							 capsule, 
							 dataRange,
							 identityString, 
							 generator, 
							 description);
		
		
		return new AttributeDao().knotAttributeImpact(attribute, knot);
	}

	public void knotAttribute(String attributeCapsuleString, 
			   String attributeString,
			   String anchorCapsuleString,
			   String descriptorString, 
			   String mnemonicString, 
			   String capsuleString,
			   String dataRange,
			   String identityString,
			   String generatorString, 
			   String description) throws InvalidObject, SQLException {

		//Validate objects
		Validate validate          = new Validate();
		Capsule   attributeCapsule = validate.validateCapsule(attributeCapsuleString);
		Capsule   anchorCapsule    = validate.validateCapsule(anchorCapsuleString);
		Attribute attribute        = validate.validateAttribute(attributeCapsule, attributeString, anchorCapsule);
		descriptorString           = validate.validateDescriptor(descriptorString);
		mnemonicString             = validate.validateMnemonic(mnemonicString, new Knot(null, null, null, null, null, false, null));
		Capsule capsule            = validate.validateCapsule(capsuleString);
		dataRange                  = validate.validateDataRange(dataRange);
		identityString             = validate.validateIdentity(identityString);
		boolean generator          = validate.validateGenerator(generatorString);
		description                = validate.validateDescription(description);
		
		Knot knot = new Knot(descriptorString, 
		mnemonicString, 
		capsule, 
		dataRange,
		identityString, 
		generator, 
		description);
		
		new AttributeDao().knotAttribute(attribute, knot);
	}
}