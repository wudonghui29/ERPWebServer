package com.xinyuan.action;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.modules.httpWriter.ResponseWriter;
import com.modules.introspector.POIntrospector;
import com.modules.util.DLog;
import com.modules.util.SecurityCode;
import com.modules.util.VerifyCode;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ResponseMessage;
import com.xinyuan.model.User;

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

		UserAction.sessionPut(ConfigConstants.VERIFYCODE, verifyCode);
		ResponseWriter.write(imageBytes);
		message.status = ResponseMessage.STATUS_SUCCESS;
		DLog.log(ConfigConstants.VERIFYCODE + " : " + verifyCode);

		return Action.NONE;
	}

	public String signin() throws Exception {
		if (! this.isVerifyCodeError()) {

			String username = request.getParameter(ConfigConstants.USERNAME);
			String password = request.getParameter(ConfigConstants.PASSWORD);
			String apnsToken = request.getParameter(ConfigConstants.APNS_TOKEN);

			User user = userDAO.getUser(username);
			if (user == null) {
				message.description = ConfigConstants.USER.UserNotExist;
			} else if (password.equals(user.getPassword())) {
				message.status = ResponseMessage.STATUS_SUCCESS;
				message.description = ConfigConstants.USER.UserLoginSuccess;
				
				// put the permission in session
				String perssionStr = user.getPermissions();
				UserAction.sessionPut(ConfigConstants.PERMISSIONS, perssionStr.split(","));
				UserAction.sessionPut(ConfigConstants.SIGNIN_USER, user);
				
				// update the apnsToken in db
				String userToken = user.getApnsToken();
				if (apnsToken != null && !apnsToken.equals(userToken)) {	// TODO: when update token , logout first and clear auto login
					user.setApnsToken(apnsToken);
					userDAO.modify(user);
				}
				
			} else {
				message.description = ConfigConstants.USER.UserPasswordError;
			}
		}
		return Action.NONE;
	}
	
	public String signout() throws Exception {
		// TODO: LOGOUT
		return Action.NONE;
	}
	
	public String signup() throws Exception {
		if (! this.isVerifyCodeError()) {  // verify code &  isAdmin
			Map parameters = request.getParameterMap(); // TODO: handle MD5
			Set keySet = parameters.keySet();
	
			Object[] keys = keySet.toArray();
			int keysCount = keys.length;
			
			if (keysCount > 100) return Action.NONE;
			
			User user = new User();
			for (int i = 0 ; i < keysCount; i ++ ) {
				Object keyObject = keys[i];
				String parakey = keyObject.toString();
				String paravalue = ((String[]) parameters.get(keyObject))[0];
				POIntrospector.setProperty(user, parakey, paravalue);
			}
			
	
			String username = user.getUsername();
			String password = user.getPassword();
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				message.description = ConfigConstants.USER.UserNamePasswordNULL;
			} else {
				if (userDAO.isSignup(username)) {
					message.description = ConfigConstants.USER.UserNameExisted;
				} else {
					if (userDAO.createUser(user)) message.status = ResponseMessage.STATUS_SUCCESS;
				}
			}
		}

		return Action.NONE;
	}
	
	public String read() throws Exception {
		List users = userDAO.getUsers();
		for (int i = 0; i < users.size(); i++) {
			User user = (User)users.get(i);
			user.setPassword(null);
		}
		message.object = users;
		message.status = ResponseMessage.STATUS_SUCCESS;
		
		return Action.NONE;
	}
	
	public String update() throws Exception {
		// TODO: update
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
