package br.com.ufabc.amem.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Strings {
	
	private static final String BUNDLE_NAME = "br.com.ufabc.amem.util.strings";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Strings() {
		
	}

	public static String getString(String key) {
		
		try {
			
			return RESOURCE_BUNDLE.getString(key);
			
		} catch (MissingResourceException e) {
			
			return '!' + key + '!';
		}
	}
	
	public static String completeWithSpace(String text, int size) {
		
		for (int i = text.length(); i < size; i++) {
			
			text += " ";
		}
		
		return text;
	}
}