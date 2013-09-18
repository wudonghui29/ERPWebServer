package com.xinyuan.message;

public class MessageConstants {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static final String SIGNIN_USER = "signin_user";
	
	public static final String COM_MODEL = "com.xinyuan.model";
	
	
	public static final String JSON = "JSON";
	public static final String USERS = "USERS";
	public static final String MODELS = "MODELS";
	public static final String OBJECTS = "OBJECTS";
	public static final String PARAMETERS = "PARAMETERS";
	public static final String PERMISSIONS = "PERMISSIONS";
	
	public static final String DENY = "DENY";
	
	public static class USER {
		public static final String VerifyCodeError = "VerifyCode_Error";
		public static final String UserNotExist = "User_Not_Exist";
		public static final String UserNotSignIn = "User_Not_Sign_In";
		public static final String UserNameExisted = "UserName_Existed";
		public static final String UserCreateFailed = "User_Create_Failed";
		public static final String UserNamePasswordNULL = "UserName_OR_Password_NULL"; 
		public static final String UserNamePasswordError = "UserName_OR_Password_Error";
	}
	
	public static class EMPLOYEE {
		public static final String EmployeeNOIsExisted = "Employee_NO_Existed";
	}
	
}
