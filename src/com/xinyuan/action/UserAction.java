package com.xinyuan.action;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.modules.httpWriter.ResponseWriter;
import com.modules.util.DLog;
import com.modules.util.SecurityCode;
import com.modules.util.VerifyCode;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.model.User;
import com.xinyuan.util.ApprovalHelper;

public class UserAction extends ActionBase {
	
	private HttpServletRequest request = ServletActionContext.getRequest();
	
	private UserDAO userDAO = new UserDAOIMP();
	
	public String connect() throws Exception {
//		HttpServletRequest request = ServletActionContext.getRequest();
//		Enumeration<String> enumeration = request.getHeaderNames();
//		while (enumeration.hasMoreElements()) {
//			String name = (String) enumeration.nextElement();
//			String value = request.getHeader(name);
//			System.out.println(name + " : " + value);
//		}
		
//		HttpSession session = ServletActionContext.getRequest().getSession();
//		String sessionID = session.getId();
//		DLog.log(sessionID);

		int count = 4;
		boolean randomBool = (new Random()).nextBoolean();
		String verifyCode = randomBool ? VerifyCode.generateCode(count) : SecurityCode.getSecurityCode(count);
		byte[] imageBytes = randomBool ? VerifyCode.generateImageBytes(verifyCode) : SecurityCode.getImageAsBytes(verifyCode);

		UserAction.sessionPut(ConstantsConfig.VERIFYCODE, verifyCode);
		ResponseWriter.write(imageBytes);
		message.status = ConstantsConfig.STATUS_SUCCESS;
		DLog.log(ConstantsConfig.VERIFYCODE + " : " + verifyCode);

		return Action.NONE;
	}

	public String signin() throws Exception {
		if (! this.isVerifyCodeError()) {

			String username = request.getParameter(ConstantsConfig.USERNAME);
			String password = request.getParameter(ConstantsConfig.PASSWORD);
			String apnsToken = request.getParameter(ConstantsConfig.APNS_TOKEN);

			User user = userDAO.getUser(username);
			if (user == null) {
				message.description = ConstantsConfig.USER.UserNotExist;
			} else if (password.equals(user.getPassword())) {
				message.status = ConstantsConfig.STATUS_SUCCESS;
				message.description = ConstantsConfig.USER.UserLoginSuccess;
				message.object = user.getPermissions();
				
				// put the permission in session
				String perssionStr = user.getPermissions();
				UserAction.sessionPut(ConstantsConfig.PERMISSIONS, perssionStr.split(","));
				UserAction.sessionPut(ConstantsConfig.SIGNIN_USER, user);
				
				// update the apnsToken in db
				String userToken = ApprovalHelper.getAPNSToken(user.getUsername());
				if (apnsToken != null && !apnsToken.equals(userToken)) {	// TODO: when update token , logout first and clear auto login
					ApprovalHelper.setAPNSToken(username, apnsToken);
				}
				
			} else {
				message.description = ConstantsConfig.USER.UserPasswordError;
			}
		}
		return Action.NONE;
	}
	
	public String signout() throws Exception {
		UserAction.sessionRemove(ConstantsConfig.PERMISSIONS);
		UserAction.sessionRemove(ConstantsConfig.SIGNIN_USER);
		
		User user = (User) UserAction.sessionGet(ConstantsConfig.SIGNIN_USER);
//		ApprovalHelper.deleteAPNSToken(username, apnsToken);
		ApprovalHelper.setAPNSToken(user.getUsername(), "");
		
		message.status = ConstantsConfig.STATUS_SUCCESS;
		return Action.NONE;
	}
	
	public String signup() throws Exception {
		if (! this.isVerifyCodeError()) {  // verify code &  isAdmin
			
		}
		return Action.NONE;
	}
	
	
	public String update() throws Exception {
		// TODO: update the permissions 
		return Action.NONE;
	}
	
	private boolean isVerifyCodeError() {
		return false ; // TODO: For test
		/**
		String userVerifyCode = request.getParameter(VERIFYCODE);
		String verifyCode = (String) this.sessionGet(VERIFYCODE);
		this.sessionRemove(VERIFYCODE);
		
		boolean isError = userVerifyCode == null || userVerifyCode.isEmpty() || !userVerifyCode.equals(verifyCode);
		if (isError) message.description = MessageConstants.User.VerifyCodeError;
		
		return isError;
		**/
	}
	
	public static void sessionPut(String key , Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}
	
	public static void sessionRemove(String key) {
		ActionContext.getContext().getSession().remove(key);
	}
	
	public static Object sessionGet(String key) {
		return ActionContext.getContext().getSession().get(key);
	}
	
}
