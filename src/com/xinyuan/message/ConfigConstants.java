package com.xinyuan.message;

import java.io.File;

import javax.servlet.ServletContext;


public class ConfigConstants {
	
	public static String FS = System.getProperty("file.separator");
	public static String Apns_Certificate_Path = ".." + FS + "apnsDevelop.p12";
	
	public static String Context_Real_Path ;
	public static String Models_Class_Files_Path ;
	
	public static void initializeContextVariables(ServletContext context) {
		//ServletActionContext.getServletContext()
		Context_Real_Path = context.getRealPath(File.separator); 
		Apns_Certificate_Path = Context_Real_Path + Apns_Certificate_Path;
		Models_Class_Files_Path = Context_Real_Path + "WEB-INF" + FS + "classes" + FS + "com" + FS + "xinyuan" + FS + "model" + FS;
	}
	
	
	
	public static final boolean APNS_IN_PRODUCTION = false;			// TODO: in production , replace it with true
	public static final String APNS_CERTIFICATE_PASSWORD = "12345";
	
	
	
	public static final String SUFFIX_CLASS = ".class";
	public static final String PACKAGE_CONNECTOR = ".";
	
	public static final String CONTENT_DIVIDER = ",";
	public static final String CONTENT_CONNECTOR = ".";
	
	
	
	public static final String ACTION = "Action" ;
	
	public static final String MODEL_APPROVALS = "Approvals";
	
	public static final String CATEGORIE_USER = "User";
	public static final String CATEGORIE_APPROVAL = "Approval";  // every user have Approval Package read permission
	public static final String CATEGORIE_EXTENSIONS = "Extensions";
	
	
	public static final String METHOD_READ = "read";
	public static final String METHOD_CREATE = "create";
	public static final String METHOD_MODIFY = "modify";
	public static final String METHOD_DELETE = "delete";
	public static final String METHOD_APPLY = "apply";
	
	public static final String APP_PREFIX = "app";
	
	public static final String DEFAULT_PERMISSION = "{}";
	
	
	public static final String SIGNIN_USER = "signin_user";
	
	
	public static final String DAOIMP_SUFFIX = "DAOIMP";
	public static final String MODELPACKAGE = "com.xinyuan.model";
	
	
	public static final String DENY = "DENY";

	public static final String PERMISSIONS = "PERMISSIONS";
	
	public static final String REQUEST_ERROR = "REQUEST_ERROR";
	

	public static final String EMPTY_STRING = "";
	public static final String STATUS_NEGATIVE = "0";
	public static final String STATUS_POSITIVE = "1";
	
	
	
	// --------------------------    Against to DB
	
	
	
	
	
	public static class USER {
		public static final String VerifyCodeError = "VerifyCode_Error";
		public static final String UserNotExist = "User_Not_Exist";
		public static final String UserNotSignIn = "User_Not_Sign_In";
		public static final String UserNameExisted = "UserName_Existed";
		public static final String UserLoginSuccess = "User_Login_Success";
		public static final String UserCreateFailed = "User_Create_Failed";
		public static final String UserNamePasswordNULL = "UserName_OR_Password_NULL"; 
		public static final String UserPasswordError = "User_Password_Error";
	}
	
	
	public static class MESSAGE {
		public static final String PushAPNSFailed = "Push_APNS_Failed";
	}
}
