package com.modules.util;

import java.io.File;
import java.util.List;

public class FileHelper {
	
	public static void listFilesForFolder(File folder, List<String> list) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry, list);
	        } else {
//	            System.out.println(fileEntry.getName());
	        	list.add(fileEntry.getParentFile().getName() + "." + fileEntry.getName());
	        }
	    }
	}
	
	
	public static void listFilesForSubFolder(File folder, List<String> list) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry, list);
	        }
	    }
	}

}
