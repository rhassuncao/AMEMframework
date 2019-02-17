package br.com.ufabc.amem.view;

import javax.swing.JFrame;

public class Amem {
	
public static void main(String[] args) {
		
		if (args.length > 1 && args[0].equalsIgnoreCase("SCRIPT")) {
			
			new TerminalInterface();
			TerminalInterface.run();
			
			
		} else {
			
			JFrame window = new VisualInterface();
			window.setVisible(true);
		}
	}
}