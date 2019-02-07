package br.com.ufabc.amem.controller;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.dao.AnchorDao;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Validate;

public class AnchorController {
	
	public void createAnchor(String descriptorString, 
							 String mnemonicString, 
							 String capsuleString, 
							 String identityString, 
							 String generatorString, 
							 String description) throws InvalidObject, SQLException {
		
		//Validate objects
		Validate validate = new Validate();
		descriptorString  = validate.validateDescriptor(descriptorString);
		mnemonicString    = validate.validateMnemonic(mnemonicString, new Anchor(null, null, null, null, false, null));
		Capsule capsule   = validate.validateCapsule(capsuleString);
		identityString    = validate.validateIdentity(identityString);
		boolean generator = validate.validateGenerator(generatorString);
		description       = validate.validateDescription(description);

		Anchor anchor = new Anchor(descriptorString, 
								   mnemonicString , 
								   capsule, 
								   identityString, 
								   generator, 
								   description);
		
		new AnchorDao().createAnchor(anchor);
	}

	public ImpactList createAnchorImpacts(String descriptorString, 
			 String mnemonicString, 
			 String capsuleString, 
			 String identityString, 
			 String generatorString, 
			 String description) throws InvalidObject, SQLException {

		//Validate objects
		Validate validate = new Validate();
		descriptorString  = validate.validateDescriptor(descriptorString);
		mnemonicString    = validate.validateMnemonic(mnemonicString, new Anchor(null, null, null, null, false, null));
		Capsule capsule   = validate.validateCapsule(capsuleString);
		identityString    = validate.validateIdentity(identityString);
		boolean generator = validate.validateGenerator(generatorString);
		description       = validate.validateDescription(description);

		Anchor anchor = new Anchor(descriptorString, 
				   mnemonicString , 
				   capsule, 
				   identityString, 
				   generator, 
				   description);

		return new AnchorDao().createAnchorImpacts(anchor);
	}
}