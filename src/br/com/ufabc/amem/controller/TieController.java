package br.com.ufabc.amem.controller;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.am.Tie;
import br.com.ufabc.amem.model.dao.TieDao;
import br.com.ufabc.amem.util.Validate;

public class TieController {

	public void createTie(String capsuleAnchor1String, 
			   			  String anchor1String, 
			   			  String role1String, 
			   			  String identifier1NString, 
			   			  String capsuleAnchor2String,
			   			  String anchor2String, 
			   			  String role2String, 
			   			  String identifier2NString, 
			   			  String capsuleString, 
			   			  String descriptionString, 
			   			  String timeRangeString) throws InvalidObject, SQLException {
	
		//Validate objects
		Validate validate       = new Validate();
		Capsule  capsuleAnchor1 = validate.validateCapsule(capsuleAnchor1String);
		Anchor   anchor1        = validate.validateAnchor(anchor1String, capsuleAnchor1);
		String   role1          = validate.validateRole(role1String);
		boolean  identifier1n   = validate.validateIdentifierN(identifier1NString);
		Capsule  capsuleAnchor2 = validate.validateCapsule(capsuleAnchor2String);
		Anchor   anchor2        = validate.validateAnchor(anchor2String, capsuleAnchor2);
		String   role2          = validate.validateRole(role2String);
		boolean  identifier2n   = validate.validateIdentifierN(identifier2NString);
		Capsule  capsule        = validate.validateCapsule(capsuleString);
		String   description    = validate.validateDescription(descriptionString);
		String   timeRange      = validate.validateTimeRange(timeRangeString);

		Tie tie = new Tie(capsuleAnchor1,
						  anchor1,
						  role1,
						  identifier1n,
						  capsuleAnchor2,
						  anchor2,role2,
						  identifier2n,
						  capsule,
						  description,
						  timeRange);
		
		TieDao tieDao = new TieDao();
		tieDao.createTie(tie);
	}
}