package com.xinyuan.Util;

import com.modules.Introspector.IntrospectHelper;
import com.xinyuan.action.command.Command;
import com.xinyuan.message.ConfigConstants;

public class ActionHelper {

	public static Command getCommand(String actionClassName, String type) {
		String shortClassName = IntrospectHelper.getClassNameLastComponent(actionClassName);			// SecurityAction
		String commandClassNameTemp = actionClassName.replace("action.", "action.command.");			// com.xinyuan.action.command.SecurityAction
		String commandClassName = commandClassNameTemp.replace(ConfigConstants.ACTION_CLASS_SUFFIX, "Command"+type); // com.xinyuan.action.command.SecurityCommand{['Alter'|....]}
		Class<?> commandClass = null;
		try {
			commandClass = Class.forName(commandClassName);
		} catch (ClassNotFoundException e) {
			String category = shortClassName.replace(ConfigConstants.ACTION_CLASS_SUFFIX, "");		// Security
			String baseCommandClassName = commandClassName.replaceAll(category, "");				// com.xinyuan.action.command.Command{['Alter'|....]}
			try {
				commandClass = Class.forName(baseCommandClassName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
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
