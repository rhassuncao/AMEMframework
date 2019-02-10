package br.com.ufabc.amem.view;

import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JFrame;
import br.com.ufabc.amem.exceptions.InvalidObject;
import br.com.ufabc.amem.exceptions.InvalidParameterNumber;
import br.com.ufabc.amem.exceptions.ObjectAlreadyCreated;
import br.com.ufabc.amem.model.function.Function;
import br.com.ufabc.amem.model.function.Functions;
import br.com.ufabc.amem.model.function.impact.ImpactList;
import br.com.ufabc.amem.util.Strings;

public class TerminalInterface {

	public static void run() {
		
		
		JFrame window = new VisualInterface();
		window.setVisible(true);

		System.out.println(Strings.getString("welcome"));
		
		Scanner scanner     = new Scanner(System.in);
		String functionName = null;
		
//		//TODO remov - Just for test
//		ArrayList<String> testList = new ArrayList<>();
//		testList.add("help");
//		testList.add("connectionTest");
//		testList.add("CONNECTIONTEST");
//		testList.add("connectionInfo");
//		testList.add("connectionTest");
//		testList.add("createAnchor(Container, CO, RGHA, number(10), true, container)");
//		testList.add("createAnchor(Container, CO, RGHA, number(10), true, container)");
//		testList.add("createAttribute( RGHA, CO_CONTAINER, Sigla, SIG, RGHA, varchar(10), descricao, null, null, null)");
//		testList.add("createKnot(Colors, COS, RGHA, varchar(15), number(5), false, knot)");
//		testList.add("createAttribute(RGHA, CO_CONTAINER, Color, COL, RGHA, varchar(10), descricao, TIMESTAMP(3), RGHA, COS_COLORS)");
//		testList.add("historizeAttribute(RGHA, co_sig_container_sigla, RGHA, timestamp(3), 24/07/1992, dd/mm/yyyy)");
//		testList.add("invalidoperation");
//		testList.add("exit");
//		int t = 0;

		do {

			String[] params   = null;
			String showScreen = "";
			String line     = scanner.nextLine();
			//TODO remov - Just for test
//			String line = testList.get(t);
//			System.out.println("$" + line);
//			t++;
			
			if(line.contains("(")) {
				
				functionName = line.substring(0, line.indexOf("(")).trim().toUpperCase();
				params       = line.substring(line.indexOf("(") + 1, line.length()).trim().split(",");
				
				for(int i = 0; i < params.length; i++) {
					
					params[i] = params[i].trim();
				}
				
				params[params.length-1] = params[params.length-1].substring(0, params[params.length-1].length()-1);
				
			} else {
				
				functionName = line.trim().toUpperCase();
			}
			
			try {
				
				Function function = Functions.getFunction(functionName);
				
				if(function != null) {
					
					ImpactList impactList = function.getImpact(params);
					
					if(impactList != null) {
						
						System.out.println(impactList.toString());
						System.out.println(Strings.getString("confirmOperation"));
						
						if(scanner.nextLine().equalsIgnoreCase(Strings.getString("operationConfirmed"))) {
							
							showScreen = function.execute(params);
							
						} else {
							
							showScreen = Strings.getString("operationCanceled");
						}
						
					} else {
						
						showScreen = function.execute(params);
					}
				
				} else {
					
					showScreen = Strings.getString("invalidCommand");
				}
			} catch (InvalidParameterNumber | InvalidObject | SQLException | ObjectAlreadyCreated exception) {

				showScreen = exception.getMessage();
			}
			
			System.out.println(showScreen);
			
		} while (!functionName.equalsIgnoreCase("EXIT"));

		scanner.close();
	}
}