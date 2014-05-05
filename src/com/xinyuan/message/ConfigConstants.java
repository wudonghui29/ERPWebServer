package com.xinyuan.message;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.modules.Util.FileHelper;


public class ConfigConstants {
	
	public static String FS = FileHelper.FILE_SEPARATOR;
	public static String Context_Real_Path = ConfigConstants.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	public static String Context_Classes_Path = Context_Real_Path;
	public static String Apns_Certificate_Path = ".." + FS + "apnsDevelop.p12";
	
	public static String userInfomations_PropertiesPath = ".." + FS + "userDevelop.properties";
	public static String serialsNumber_PropertiesPath = "resources" + FS + "serials.properties";
	
	
	public static Properties serialNumberProperties = null;
	public static Properties userInfomationProperties = null;
	
	
	
	
	// For in server end
	public static void initializeContextVariables(ServletContext context) {
		//ServletActionContext.getServletContext()
		Context_Real_Path = context.getRealPath(File.separator); 
		Context_Classes_Path = Context_Real_Path + "WEB-INF" + FS + "classes" + FS;
		Apns_Certificate_Path = Context_Real_Path + Apns_Certificate_Path;
		userInfomations_PropertiesPath = Context_Real_Path + userInfomations_PropertiesPath;
		serialsNumber_PropertiesPath = Context_Real_Path + serialsNumber_PropertiesPath;
	}
	
	public static void initializeSystemProperties() {
		
		try {
			serialNumberProperties = new Properties();
			serialNumberProperties.load(new BufferedInputStream(new FileInputStream(serialsNumber_PropertiesPath)));
			
			userInfomationProperties = new Properties();
			FileHelper.createFileIfNotExist(userInfomations_PropertiesPath);
			userInfomationProperties.load(new BufferedInputStream(new FileInputStream(userInfomations_PropertiesPath)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	public static final boolean APNS_IN_PRODUCTION = false;			// TODO: in production , replace it with true
	public static final String APNS_CERTIFICATE_PASSWORD = "12345";
	
	
	
	
	public static final String CONTENT_DIVIDER = ",";
	public static final String CONTENT_CONNECTOR = ".";
	
	
	
	public static final String ACTION_CLASS_SUFFIX = "Action" ;
	
	public static final String CATEGORIE_USER 		= "User";
	public static final String CATEGORIE_APPROVAL 	= "Approval";  // every user have Approval Package read permission
	public static final String CATEGORIE_SETTING 	= "Setting";
	
	
	public static final String METHOD_READ = "read";
	public static final String METHOD_CREATE = "create";
	public static final String METHOD_MODIFY = "modify";
	public static final String METHOD_DELETE = "delete";
	public static final String METHOD_APPLY = "apply";
	
	public static final String APPKEY_PREFIX = "app";
	
	public static final String SIGNIN_USER = "signin_user";
	public static final String SIGNIN_USER_PERMISSIONS = "signin_user_permissions";
	
	
	public static final String DAOIMP_SUFFIX = "DAOIMP";
	public static final String MODELPACKAGE = "com.xinyuan.model";
	
	
	public static final String DENY = "DENY";

	public static final String USER_IDENTIFIER = "USER_IDENTIFIER";
	public static final String ALL_USERS_PERMISSIONS = "ALL_USERS_PERMISSIONS";
	
	
	public static final String REQUEST_ERROR = "REQUEST_ERROR";
	

	public static final String EMPTY_STRING = "";
	public static final String STATUS_NEGATIVE = "0";
	public static final String STATUS_POSITIVE = "1";
	
	
	
	// --------------------------    Against to DB
	public static final String APPSettings_TYPE_PREFIX_ADMIN = "ADMIN";
	public static final String APPSettings_TYPE_ADMIN_APPROVALS = "ADMIN_APPROVALS";
	public static final String APPSettings_TYPE_ADMIN_ORDERSEXPIRATIONS = "ADMIN_ORDERSEXPIRATIONS";
	
	
	public static final String SystemNeedInitialed = "System_Need_Initialized";
	
	
	public static class USER {
		public static final String VerifyCodeError = "VerifyCode_Error";
		
		
		public static final String UserNotExist = "User_Not_Exist";
		public static final String UserNotSignIn = "User_Not_Sign_In";
		public static final String UserNameExisted = "UserName_Existed";
		public static final String UserPasswordError = "User_Password_Error";
		public static final String UserNamePasswordNULL = "UserName_OR_Password_NULL"; 
	}
	
	
	public static class MESSAGE {
		public static final String PushAPNSFailed = "Push_APNS_Failed";
		public static final String ApprovalFailed = "Approval_Failed";
	}
}
