package com.xinyuan.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.Global.SessionManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modules.HttpWriter.ResponseWriter;
import com.modules.Introspector.ModelIntrospector;
import com.modules.Util.DLog;
import com.modules.Util.SecurityCode;
import com.modules.Util.StringHelper;
import com.modules.Util.VerifyCode;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Config.ConfigConstants;
import com.xinyuan.Config.ConfigJSON;
import com.xinyuan.Util.ApprovalHelper;
import com.xinyuan.Util.JsonHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.model.User.User;

public class UserAction extends ActionModelBase {
	
	@Override
	protected SuperDAO getDao() { return null; }
	
	private UserDAO userDAO = new UserDAOIMP();
	
	
	public String connect() throws Exception {
		String VERIFYCODE_TYPE = JsonHelper.getParameter(allJsonObject, ConfigJSON.VERIFYCODE_TYPE);
		String VERIFYCODE_COUNT = JsonHelper.getParameter(allJsonObject, ConfigJSON.VERIFYCODE_COUNT);
		
		int count = 7;
		try {
			count = VERIFYCODE_COUNT == null ? 4 : Integer.valueOf(VERIFYCODE_COUNT) ;
		} catch (NumberFormatException e) {
			//
		}
		
		boolean randomBool = (VERIFYCODE_TYPE == null) ? (new Random()).nextBoolean() : Boolean.valueOf(VERIFYCODE_TYPE);
		String verifyCode = randomBool ? VerifyCode.generateCode(count) : SecurityCode.getSecurityCode(count);
		byte[] imageBytes = randomBool ? VerifyCode.generateImageBytes(verifyCode) : SecurityCode.getImageAsBytes(verifyCode);

		SessionManager.put(ConfigJSON.VERIFYCODE, verifyCode);
		ResponseWriter.write(imageBytes);
		message.status = ConfigConstants.STATUS_SUCCESS;
		DLog.log(ConfigJSON.VERIFYCODE + " : " + verifyCode);

		return Action.NONE;
	}

	
	
	public String signin() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		if (this.isVerifyCodeError()) return Action.NONE;
		
//		for (int i = 0; i < models.size(); i++) {
			
		User model = (User)models.get(0);
		
		
		String username = model.getUsername();
		String password = model.getPassword();
		String apnsToken = JsonHelper.getParameter(allJsonObject,ConfigJSON.APNSTOKEN);
		
		User user = userDAO.getUser(username);
		if (user == null) {
			message.description = ConfigConstants.USER.UserNotExist;
		} else if (password.equals(user.getPassword())) {
			
			// update the apnsToken in db
			String userToken = ApprovalHelper.getAPNSToken(user.getUsername());
			if (apnsToken != null && !apnsToken.equals(userToken)) {	// TODO: when update token , logout first and clear auto login
				ApprovalHelper.setAPNSToken(username, apnsToken);
			}
			
			// put result in response
			Map<String, Object> map = new HashMap<String, Object>();
			message.status = ConfigConstants.STATUS_SUCCESS;
			message.description = ConfigConstants.USER.UserLoginSuccess;
			
			map.put(ConfigJSON.IDENTIFIER, user.getId());
			map.put(ConfigConstants.PERMISSIONS, userDAO.getAllUsers());
			message.objects = map;
			
			// put the permission in session
			String perssionStr = user.getPermissions();
			perssionStr = StringHelper.isEmpty(perssionStr) ? ConfigConstants.DEFAULT_PERMISSION : perssionStr;
			JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(perssionStr);
			Map<String, Object> permissions = JsonHelper.translateElementToMap(jsonObject);
			SessionManager.put(ConfigConstants.PERMISSIONS, permissions);
			SessionManager.put(ConfigConstants.SIGNIN_USER, user);
			
			
		} else {
			message.description = ConfigConstants.USER.UserPasswordError;
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
		
		message.status = ConfigConstants.STATUS_SUCCESS;
		return Action.NONE;
	}
	
	
	public String modify() throws Exception {
		
		return Action.NONE;
	}
	
	
	
	
	
	/**
	 * 
	 * Admin Begin
	 * 
	 * 
	 * 
	 */
	
	
	public String adminModify() throws Exception {
//		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		List<Map<String, String>> identities = requestMessage.getIDENTITYS();
		for (int i = 0; i < models.size(); i++) {
			User model = (User)models.get(i);
			Set<String> keys = objectKeys.get(i);
			
			String username = identities.get(i).get(ConfigJSON.USERNAME);
			User persistence = userDAO.getUser(username); 	// po
			ModelIntrospector.copyVoToPo(model, persistence, keys);
			
			userDAO.modify(persistence);
		}
		
		message.status = ConfigConstants.STATUS_SUCCESS;
		return Action.NONE;
	}
	
	
	public String signup() throws Exception {
		return Action.NONE; // TODO: ...
	}
	
	
	
	
	/**
	 * 
	 * Admin End
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	/**
	 * Private Methods
	 * @return
	 */
	private boolean isVerifyCodeError() {
		
		String userVerifyCode = JsonHelper.getParameter(allJsonObject, ConfigJSON.VERIFYCODE);
		String verifyCode = (String) SessionManager.get(ConfigJSON.VERIFYCODE);
		SessionManager.remove(ConfigJSON.VERIFYCODE);
		
		boolean isError = userVerifyCode == null || userVerifyCode.isEmpty() || !userVerifyCode.equals(verifyCode);
		if (isError) message.description = ConfigConstants.USER.VerifyCodeError;
		
//		return isError;
		
		return false ; // TODO: For test
	}

	
	
}
