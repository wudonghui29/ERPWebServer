package com.xinyuan.message;

public class ConfigConstants {
	
	public static final String STRING_TO_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TO_STRING_FORMAT = "yyyyMMddHHmmss";
	
	
	public static final String VERIFYCODE = "verify-code";
	public static final String APNS_TOKEN = "apnsToken";
	
	public static final String ORDERNO = "orderNO";
	public static final String IDENTIFIER = "id";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	
	public static final String SIGNIN_USER = "signin_user";
	
	public static final String DAOIMP_SUFFIX = "DAOIMP";
	public static final String MODELPACKAGE = "com.xinyuan.model.";
	
	public static final String MODELS = "MODELS";
	public static final String OBJECTS = "OBJECTS";
	public static final String OPTIOINS = "OPTIONS";
	public static final String PASSWORDS = "PASSWORDS";
	
	public static final String DENY = "DENY";
	public static final String JSON = "JSON";
	public static final String USERS = "USERS";
	public static final String PARAMETERS = "PARAMETERS";
	public static final String PERMISSIONS = "PERMISSIONS";
	public static final String REQUEST_ERROR = "REQUEST_ERROR";
	
	
	public static final String APNS = "APNS";
	public static final String APNS_FORWARDS = "FORWARDS";

	
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
	
	public static class EMPLOYEE {
		public static final String EmployeeNOIsExisted = "Employee_NO_Existed";
	}
	
}
