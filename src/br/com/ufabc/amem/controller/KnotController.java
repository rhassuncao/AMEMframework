package br.com.ufabc.amem.controller;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.model.Capsule;
import br.com.ufabc.amem.model.Knot;
import br.com.ufabc.amem.model.dao.KnotDao;
import br.com.ufabc.amem.util.Validate;

public class KnotController {

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
}