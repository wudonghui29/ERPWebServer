package com.modules.Util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class CollectionHelper {
	
	public static void removeStartWithElement(Set<String> set , String startWith) {
		Set<String> tempSet = new HashSet<String>();
		tempSet.addAll(set);
		for (String value : tempSet) {
			if (value.startsWith(startWith)) {
				set.remove(value);
			}
		}
	}
	
	
	public static void removeNotStartWithElement(Set<String> set , String notStartWith) {
		Set<String> tempSet = new HashSet<String>();
		tempSet.addAll(set);
		for (String value : tempSet) {
			if (!value.startsWith(notStartWith)) {
				set.remove(value);
			}
		}
	}
	
	
	public static boolean isContains(List<String> list, String element){
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if (string.equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void removeElement(List<String> list, String element){
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			if (string.equals(element)) {
				iterator.remove();
			}
		}
	}

}
