package com.xinyuan.message;

import com.opensymphony.xwork2.ActionContext;
import com.xinyuan.action.ActionBase;

public class MessagesKeys {
	
	public static final String DEFAULT = "M_DEFAULT";
	public static final String CONNECTOR = ",";
	public static final String FORMATTER = "%@";
	
	
	public static final String KEYS_PRE = "KEYS.";
	public static final String MESSAGE_PRE = "MESSAGE_";
	
	
	
	
	
	
	
	public static final String REQUEST_ERROR = MESSAGE_PRE + "REQUEST_ERROR";
	
	public static final String SystemNeedInitialed = MESSAGE_PRE + "System_Need_Initialized";
    
    
    public static class USER {
        public static final String VerifyCodeError = MESSAGE_PRE + "VerifyCode_Error";
        
        
        public static final String UserNotExist = MESSAGE_PRE + "User_Not_Exist";
        public static final String UserNotSignIn = MESSAGE_PRE + "User_Not_Sign_In";
        public static final String UserNameExisted = MESSAGE_PRE + "User_Name_Existed";
        public static final String UserPasswordError = MESSAGE_PRE + "User_Password_Error";
        public static final String UserNamePasswordNULL = MESSAGE_PRE + "User_Name_OR_Password_NULL"; 
    }
    
    
    public static class APNS {
        public static final String PushAPNSFailed = MESSAGE_PRE + "Push_APNS_Failed";
        public static final String ApprovalFailed = MESSAGE_PRE + "Approval_Failed";
    }
	

	public static class HR {
		
		public static final String EMPLOYEE_HAVING_APPROVALS = MESSAGE_PRE + "HAVING_APPROVALS";
		
	}
	
	
	public static ResponseMessage getCurrentResponseMessage() {
	    ResponseMessage responseMessage = ((ActionBase)ActionContext.getContext().getActionInvocation().getAction()).getResponseMessage();
	    return responseMessage;
    }
}
