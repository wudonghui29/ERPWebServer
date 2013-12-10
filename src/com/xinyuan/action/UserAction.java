package com.xinyuan.action;

import java.util.HashMap;
import java.util.Map;

import com.Global.SessionManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modules.Util.StringHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.model.User.User;

public class UserAction extends ActionBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected SuperDAO getDao() { return null; }
	
	private UserDAO userDAO = new UserDAOIMP();

	
	
	public String signin() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		
		String userVerifyCode = JsonHelper.getParameter(requestMessage, ConfigJSON.VERIFYCODE);
		if (this.isVerifyCodeError(userVerifyCode)) return Action.NONE;
		
//		for (int i = 0; i < models.size(); i++) {
			
		User model = (User)models.get(0);
		
		
		String username = model.getUsername();
		String password = model.getPassword();
		String apnsToken = JsonHelper.getParameter(requestMessage,ConfigJSON.APNSTOKEN);
		
		User user = userDAO.getUser(username);
		if (user == null) {
			responseMessage.description = ConfigConstants.USER.UserNotExist;
		} else if (password.equals(user.getPassword())) {
			
			// update the apnsToken in db
			String userToken = ApprovalHelper.getAPNSToken(user.getUsername());
			if (apnsToken != null && !apnsToken.equals(userToken)) {	// TODO: when update token , logout first and clear auto login
				ApprovalHelper.setAPNSToken(username, apnsToken);
			}
			
			// put result in response
			Map<String, Object> map = new HashMap<String, Object>();
			responseMessage.status = ConfigConstants.STATUS_POSITIVE;
			responseMessage.description = ConfigConstants.USER.UserLoginSuccess;
			
			map.put(ConfigJSON.IDENTIFIER, user.getId());
			map.put(ConfigConstants.PERMISSIONS, userDAO.getAllUsers());
			responseMessage.objects = map;
			
			// put the permission in session
			String perssionStr = user.getPermissions();
			perssionStr = StringHelper.isEmpty(perssionStr) ? ConfigConstants.DEFAULT_PERMISSION : perssionStr;
			JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(perssionStr);
			Map<String, Object> permissions = JsonHelper.translateElementToMap(jsonObject);
			SessionManager.put(ConfigConstants.PERMISSIONS, permissions);
			SessionManager.put(ConfigConstants.SIGNIN_USER, user);
			
			
		} else {
			responseMessage.description = ConfigConstants.USER.UserPasswordError;
		}
			
//		}

		return Action.NONE;
	}
	
	public String signout() throws Exception {
		SessionManager.remove(ConfigConstants.PERMISSIONS);
		SessionManager.remove(ConfigConstants.SIGNIN_USER);
		
		User user = (User) SessionManager.get(ConfigConstants.SIGNIN_USER);
//		ApprovalHelper.deleteAPNSToken(username, apnsToken);
		ApprovalHelper.setAPNSToken(user.getUsername(), "");
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	
	
	public String modify() throws Exception {
		
		return Action.NONE;
	}
	
	
	
	/**
	 * Private Methods
	 * @return
	 */
	private boolean isVerifyCodeError(String userVerifyCode) {
		
		String verifyCode = (String) SessionManager.get(ConfigJSON.VERIFYCODE);
		SessionManager.remove(ConfigJSON.VERIFYCODE);
		
		boolean isError = userVerifyCode == null || userVerifyCode.isEmpty() || !userVerifyCode.equals(verifyCode);
		if (isError) responseMessage.description = ConfigConstants.USER.VerifyCodeError;
		
//		return isError;
		
		return false ; // TODO: For test now
	}

	
	
}
