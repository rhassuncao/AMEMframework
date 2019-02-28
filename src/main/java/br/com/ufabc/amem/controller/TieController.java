package br.com.ufabc.amem.controller;

import java.sql.SQLException;

import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.model.am.Anchor;
import br.com.ufabc.amem.model.am.Capsule;
import br.com.ufabc.amem.model.am.Tie;
import br.com.ufabc.amem.model.dao.TieDao;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Validate;

public class TieController {

	/**
	 * @param capsuleAnchor1String
	 * @param anchor1String
	 * @param role1String
	 * @param identifier1NString
	 * @param capsuleAnchor2String
	 * @param anchor2String
	 * @param role2String
	 * @param identifier2NString
	 * @param capsuleString
	 * @param descriptionString
	 * @param timeRangeString
	 * @throws InvalidObject
	 * @throws SQLException
	 */
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
						  anchor2,
						  role2,
						  identifier2n,
						  capsule,
						  description,
						  timeRange);
		
		TieDao tieDao = new TieDao();
		tieDao.createTie(tie);
	}

	/**
	 * @param capsuleAnchor1String
	 * @param anchor1String
	 * @param role1String
	 * @param identifier1NString
	 * @param capsuleAnchor2String
	 * @param anchor2String
	 * @param role2String
	 * @param identifier2NString
	 * @param capsuleString
	 * @param descriptionString
	 * @param timeRangeString
	 * @return
	 * @throws InvalidObject
	 * @throws SQLException
	 */
	public ImpactList createTieImpacts(String capsuleAnchor1String, 
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
			  		anchor2,
			  		role2,
			  		identifier2n,
			  		capsule,
			  		description,
			  		timeRange);

		return new TieDao().createTieImpacts(tie);
	}
}