package com.modules.Util;

public class DLog {
	
	public static boolean isDebugingMode = true;		// Set it to no when in production
	
	public static void log(String message) {
		if (! isDebugingMode) return; 
		StackTraceElement[] elements = new Throwable().getStackTrace();
		String classNameString = elements[1].getClassName();
		System.out.println("\n ----- " + "[" + classNameString + "]" + "  " + message);
	}
	
	public static void log(String prefix, String message) {
		if (! isDebugingMode) return; 
		System.out.println(prefix);
		log(message);
	}
}
