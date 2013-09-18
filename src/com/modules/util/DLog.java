package com.modules.util;

public class DLog {
	public static void log(String message) {
		StackTraceElement[] elements = new Throwable().getStackTrace();
		String classNameString = elements[1].getClassName();
		System.out.println("\n ----- " + "[" + classNameString + "]" + "  " + message);
	}
}
