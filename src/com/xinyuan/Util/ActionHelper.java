package com.xinyuan.Util;

import com.xinyuan.action.command.Command;

public class ActionHelper {

	// actionClassName = Category+'Action' , modelName = Category's Model, type=[Read|Create|Delete|Modify|Apply]
	public static Command getCommand(String categoryName, String modelName, String type) throws Exception {
		String commandTypeShortClassName = "Command"+type;									// 'Command'+{['Alter'|....]}
		String modelCommandShortClassName = modelName + commandTypeShortClassName;			// Model + 'Command'+{['Alter'|....]}
		String categotyCommandShortClassName = categoryName + commandTypeShortClassName ; 	// Category+'Command'+{['Alter'|....]}
		
		String commandPackage = "com.xinyuan.action.command.";
		Class<?> commandClass = null;
		try {
			String modelCommandClassName = commandPackage + categoryName + "." + modelCommandShortClassName;
			commandClass = Class.forName(modelCommandClassName);
		} catch (ClassNotFoundException e) {
			
			try {
				String categoryCommandClassName = commandPackage + "category." + categotyCommandShortClassName;
				commandClass = Class.forName(categoryCommandClassName);
			} catch (ClassNotFoundException e2) {
				String baseCommandClassName = commandPackage + commandTypeShortClassName;
				commandClass = Class.forName(baseCommandClassName);
			}
		}	
		
		Object obj = null;
		try {
			obj = commandClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return (Command) obj;
	}
	
	
}
