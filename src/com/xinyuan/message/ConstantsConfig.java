package com.xinyuan.message;

public class ConstantsConfig {
	
	public static final boolean APNS_IN_PRODUCTION = false;			// TODO: in production , replace it with true
	public static final String APNS_CERTIFICATE_PASSWORD = "12345";
	public static final String APNS_CERTIFICATE_PATH = "../apnsDevelop.p12";
	
	
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
	public static final String FIELDS = "FIELDS";
	public static final String CRITERIAS = "CRITERIAS";
	public static final String IDENTITYS = "IDENTITYS";
	public static final String PASSWORDS = "PASSWORDS";
	
	public static final String APNS_CONTENTS = "APNS_CONTENTS";
	public static final String APNS_FORWARDS = "APNS_FORWARDS";
	
	
	
	public static final String DENY = "DENY";
	public static final String JSON = "JSON";
	public static final String USERS = "USERS";
	public static final String PARAMETERS = "PARAMETERS";
	public static final String PERMISSIONS = "PERMISSIONS";
	public static final String REQUEST_ERROR = "REQUEST_ERROR";
	

	
	public static final String NONE = "";
	public static final String STATUS_SUCCESS = "1";
	public static final String STATUS_FAILED = "0";
	
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
