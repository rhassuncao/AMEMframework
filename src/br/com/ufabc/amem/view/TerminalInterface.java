package br.com.ufabc.amem.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import br.com.ufabc.amem.controller.AnchorController;
import br.com.ufabc.amem.controller.AttributeController;
import br.com.ufabc.amem.controller.KnotController;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.util.ConnectionPool;
import br.com.ufabc.amem.util.Instructions;

public class TerminalInterface {

	public static void main(String[] args) {

		System.out.println("Welcome to AMEM - Anchor Modeling Evolution Manager V 0.0.0.0");
		Scanner scanner = new Scanner(System.in);
		String command;

		do {

			String line = scanner.nextLine();
			//String line = "createAnchor Container CO RGHA number(10) true tabela_container";
			//String line = "createAttribute RGHA CO_CONTAINER Sigla SIG RGHA varchar(10) descricao null null null";
			//String line = "createKnot Colors COS RGHA varchar(15) number(5) true knot";
			//String line = "createAttribute RGHA CO_CONTAINER Color COL RGHA varchar(10) descricao TIMESTAMP(3) RGHA COS_COLORS";
			//String line = "historizeAttribute RGHA co_sig_container_sigla RGHA timestamp(3) 24/07/1992";
			
			//drop table RGHA.CO_CONTAINER

			// drop sequence RGHA.CO_CONTAINER_CO_ID_SEQ

			// drop trigger RGHA.TRG_CO_CONTAINER_CO_ID

			// drop table RGHA.CO_SIG_CONTAINER_SIGLA

			// drop table RGHA.COS_COLORS

			// drop table RGHA.CO_COL_CONTAINER_COLOR

			String[] params = line.split(" ");
			command = params[0].toUpperCase();

			try {

				switch (command) {
				
				case "CONNECTIONINFO":

					System.out.println(ConnectionPool.getUrl());
					
					break;
					
				case "CREATEANCHOR":

					if (params.length == 7) {

						AnchorController anchorController = new AnchorController();
						anchorController.createAnchor(params[1], params[2], params[3], params[4], params[5], params[6]);
						System.out.println("Object created");

					} else {

						throw new InvalidParameterNumber(params[0]);
					}
					
					break;

				case "CREATEATTRIBUTE":

					if (params.length == 11) {

						AttributeController attributeControler = new AttributeController();
						attributeControler.createAttribute(params[1], params[2], params[3], params[4], params[5],
								params[6], params[7], params[8], params[9], params[10]);
						System.out.println("Object created");

					} else {

						throw new InvalidParameterNumber(params[0]);
					}

					break;

				case "HELP":
					
					if (params.length == 1) {

						System.out.println(
								"===================================================================================================");
						System.out.println(
								"========================================   HELP   =================================================");
						System.out.println(
								"===================================================================================================");
						System.out.println("   OPERATOR    |                   PARAMETERS");

						Map<String, String> instructions = new Instructions().getAllInstructions();

						for (Map.Entry<String, String> instruction : instructions.entrySet()) {
							System.out.println(instruction.getKey() + ": " + instruction.getValue());
						}

						System.out.println(
								"===================================================================================================");
					
					} else if (params.length == 2) {
						
						String instruction = new Instructions().getInstruction(params[1]);
						
						if(instruction == null) {
							
							throw new InvalidObject(params[1]);
							
						} else {
							
							System.out.println(instruction);
						}
						
					} else {

						throw new InvalidParameterNumber(params[0]);
					}
					break;

				case "HISTORIZEATTRIBUTE":

					if (params.length == 6) {

						AttributeController attributeControler = new AttributeController();
						attributeControler.historizeAttribute(params[1], params[2], params[3], params[4], params[5]);
						System.out.println("Object historized");

					} else {

						throw new InvalidParameterNumber(params[0]);
					}
					break;

				case "CREATEKNOT":

					if (params.length == 8) {

						KnotController knotController = new KnotController();
						knotController.createKnot(params[1], params[2], params[3], params[4], params[5], params[6],
								params[7]);
						System.out.println("Object created");

					} else {

						throw new InvalidParameterNumber(params[0]);
					}

					break;

				case "CONNECTIONTEST":
					
					Connection conn = ConnectionPool.getInstance().getConnection();
					
					if (conn != null) {
						
						System.out.println("Success!");
						
					} else {
						
						System.out.println("Failed!");
					}
					
					ConnectionPool.getInstance().releaseConnection(conn);
					
					break;
				
				case "EXIT":
					
					System.out.println("Bye!");
					break;
					
				default:

					System.out.println("This is not a valid command!");
				}

			} catch (InvalidParameterNumber | InvalidObject | ObjectAlreadyCreated | SQLException exception) {

				System.out.println(exception.getMessage());
			}

		} while (!command.equals("EXIT")); //(false);

		scanner.close();
	}
}