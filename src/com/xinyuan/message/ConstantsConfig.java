package com.xinyuan.message;

public class ConstantsConfig {
	
	public static final boolean APNS_IN_PRODUCTION = false;			// TODO: in production , replace it with true
	public static final String APNS_CERTIFICATE_PASSWORD = "12345";
	public static final String APNS_CERTIFICATE_PATH = "../apnsDevelop.p12";
	
	public static final String ACTION = "Action" ;
	
	
	public static final String VERIFYCODE = "VERIFYCODE";
	public static final String VERIFYCODE_TYPE = "VERIFYCODE_TYPE";
	public static final String VERIFYCODE_COUNT = "VERIFYCODE_COUNT";
	
	
	public static final String APNSTOKEN = "APNSTOKEN";
	
	public static final String ORDERNO = "orderNO";
	public static final String IDENTIFIER = "id";
	
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String PERMISSION = "permission";
	
	
	public static final String SIGNIN_USER = "signin_user";
	
	
	public static final String DAOIMP_SUFFIX = "DAOIMP";
	public static final String MODELPACKAGE = "com.xinyuan.model.";
	
	
	public static final String METHOD_READ = "read";
	public static final String ACTION_APPROVAL = "Approval";		// every user have Approval Package read permission
	
	
	public static final String OBJECTS = "OBJECTS";
	public static final String MODELS = "MODELS";
	public static final String FIELDS = "FIELDS";			// for multi tables queries
	public static final String JOINS = "JOINS";				// for cross tables queries with join (left join, right join)
	public static final String SORTS = "SORTS";
	public static final String CRITERIAS = "CRITERIAS";		// for query
	public static final String IDENTITYS = "IDENTITYS";		// for modify， delete
	public static final String PARAMETERS = "PARAMETERS"; 	// for user connect 's verify code's length or type , and so on ...
	
	public static final String PASSWORDS = "PASSWORDS";		// for create user
	
	
	
	public static final String APNS_CONTENTS = "APNS_CONTENTS";
	public static final String APNS_FORWARDS = "APNS_FORWARDS";
	
	
	
	public static final String DENY = "DENY";
	public static final String JSON = "JSON";
	public static final String USERS = "USERS";
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
