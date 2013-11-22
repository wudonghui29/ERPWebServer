package com.modules.Util;

import java.util.Iterator;
import java.util.Map;

public class CollectionHelper {
	
	public static void printMapKeysValues(Map map) {
	    Iterator iterator = map.entrySet().iterator();
	    while (iterator.hasNext()) {
	        Map.Entry pairs = (Map.Entry)iterator.next();
	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        iterator.remove(); // avoids a ConcurrentModificationException
	    }
	}


}
