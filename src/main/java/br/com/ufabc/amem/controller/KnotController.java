package br.com.ufabc.amem.controller;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.am.Knot;
import br.com.ufabc.amem.model.dao.KnotDao;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Validate;

public class KnotController {

	/**
	 * @param descriptorString
	 * @param mnemonicString
	 * @param capsuleString
	 * @param dataRange
	 * @param identityString
	 * @param generatorString
	 * @param description
	 * @throws InvalidObject
	 * @throws SQLException
	 */
	public void createKnot(String descriptorString, 
						   String mnemonicString, 
						   String capsuleString,
						   String dataRange,
						   String identityString,
						   String generatorString, 
						   String description) throws InvalidObject, SQLException {

		//Validate objects
		Validate validate = new Validate();
		descriptorString  = validate.validateDescriptor(descriptorString);
		mnemonicString    = validate.validateMnemonic(mnemonicString, new Knot(null, null, null, null, null, false, null));
		Capsule capsule   = validate.validateCapsule(capsuleString);
		dataRange         = validate.validateDataRange(dataRange);
		identityString    = validate.validateIdentity(identityString);
		boolean generator = validate.validateGenerator(generatorString);
		description       = validate.validateDescription(description);

		Knot knot = new Knot(descriptorString, 
							 mnemonicString, 
							 capsule, 
							 dataRange,
							 identityString, 
							 generator, 
							 description);

		KnotDao knotDao = new KnotDao();
		knotDao.createKnot(knot);
	}

	/**
	 * @param descriptorString
	 * @param mnemonicString
	 * @param capsuleString
	 * @param dataRange
	 * @param identityString
	 * @param generatorString
	 * @param description
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 */
	public ImpactList createKnotImpact(String descriptorString, 
									   String mnemonicString, 
									   String capsuleString,
									   String dataRange,
									   String identityString,
									   String generatorString, 
									   String description) throws InvalidObject, SQLException {

		//Validate objects
		Validate validate = new Validate();
		descriptorString  = validate.validateDescriptor(descriptorString);
		mnemonicString    = validate.validateMnemonic(mnemonicString, new Knot(null, null, null, null, null, false, null));
		Capsule capsule   = validate.validateCapsule(capsuleString);
		dataRange         = validate.validateDataRange(dataRange);
		identityString    = validate.validateIdentity(identityString);
		boolean generator = validate.validateGenerator(generatorString);
		description       = validate.validateDescription(description);
		
		Knot knot = new Knot(descriptorString, 
						 mnemonicString, 
						 capsule, 
						 dataRange,
						 identityString, 
						 generator, 
						 description);
		
		return new KnotDao().createKnotImpact(knot);
	}
}