package com.xinyuan.action;

import java.util.HashMap;
import java.util.Map;

import com.Global.SessionManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modules.Util.StringHelper;
import com.opensymphony.xwork2.Action;
import com.xinyuan.Util.AppCryptoHelper;
import com.xinyuan.Util.GsonHelper;
import com.xinyuan.Util.ParametersHelper;
import com.xinyuan.Util.SettingHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.model.Setting.APPSettings;
import com.xinyuan.model.User.User;

public class UserAction extends ActionBase {
	
	private static final long serialVersionUID = 1L;
	@Override
	protected SuperDAO getDao() { return null; }
	
	private UserDAOIMP userDAO = new UserDAOIMP();

	
	
	public String signin() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		
		String userVerifyCode = ParametersHelper.getParameter(requestMessage, ConfigJSON.VERIFYCODE);
		if (this.isVerifyCodeError(userVerifyCode)) return Action.NONE;
		
			
		User model = (User)models.get(0);
		
		String username = model.getUsername();
		String newApnsToken = ParametersHelper.getParameter(requestMessage,ConfigJSON.APNSTOKEN);
		
		User user = userDAO.getUser(username);
		if (user == null) {
			responseMessage.descriptions = ConfigConstants.USER.UserNotExist;
		} else if (AppCryptoHelper.isUserImpacted(model, user)) {
			
			// update the apnsToken in db
			String apnsToken = userDAO.getUserApnsToken(user.getUsername());
			if (newApnsToken != null && !newApnsToken.trim().equalsIgnoreCase("")){
				if (!newApnsToken.equals(apnsToken)) userDAO.setUserApnsToken(username, newApnsToken);
			}
			
			// put result in response
			Map<String, Object> map = new HashMap<String, Object>();
			responseMessage.status = ConfigConstants.STATUS_POSITIVE;
			responseMessage.descriptions = ConfigConstants.USER.UserLoginSuccess;
			
			map.put(ConfigJSON.IDENTIFIER, user.getId());
			map.put(ConfigConstants.ALLUSER_PERMISSIONS, userDAO.getAllUsersPermissions());
			responseMessage.results = map;
			
			// put the permission in session
			String perssionStr = user.getPermissions();
			perssionStr = StringHelper.isEmpty(perssionStr) ? ConfigConstants.DEFAULT_PERMISSION : perssionStr;
			JsonObject jsonObject = (JsonObject)(new JsonParser()).parse(perssionStr);
			Map<String, Object> permissions = GsonHelper.translateElementToMap(jsonObject);
			SessionManager.put(ConfigConstants.ALLUSER_PERMISSIONS, permissions);
			SessionManager.put(ConfigConstants.SIGNIN_USER, user);
			
			
		} else {
			responseMessage.descriptions = ConfigConstants.USER.UserPasswordError;
		}
			
		return Action.NONE;
	}
	
	public String signout() throws Exception {
		SessionManager.remove(ConfigConstants.ALLUSER_PERMISSIONS);
		SessionManager.remove(ConfigConstants.SIGNIN_USER);
		
		User user = (User) SessionManager.get(ConfigConstants.SIGNIN_USER);
		System.out.println(user);// check if sign out succefullly .
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	
	
	public String signup() throws Exception {

		// signup administrator
		if (SettingHelper.isUserTableEmpty()) {
			
			for (int i = 0; i < models.size(); i++) {
				User user = (User) models.get(i);
				
				
				if (i != 0) {
					// AppSettings : ADMIN_APPROVALS - {"HumanResource":{"Employee":{"app1":[["0000"],""]}}}
					SuperDAO superDao = new SuperDAOIMP();
					APPSettings appSetting = new APPSettings();
					appSetting.setType(ConfigConstants.APPSettings_TYPE_ADMIN_APPROVALS);
					String settings = "{\"HumanResource\":{\"Employee\":{\"app1\":{\"USERS\":[\"" +user.getUsername()+ "\"]}}}}" ;
					appSetting.setSettings(settings);
					
					superDao.create(appSetting);
					
					
					// User : permissions - {"HumanResource":{"Employee":["read","create","modify","delete","apply"]}} , categories - ["HumanResource"]
					String createEmployeePermission = "{\"HumanResource\":{\"Employee\":[\"read\",\"create\",\"modify\",\"delete\",\"apply\"]}}";
					user.setPermissions(createEmployeePermission);
					String humanResouceCategory = "[\"HumanResource\"]";
					user.setCategories(humanResouceCategory);
				}
				
				userDAO.createUser(user);
				
				
				
				String queryString = "UPDATE USER SET id = " + -i + " WHERE username = '" + user.getUsername()+"'";
				userDAO.createSQLQuery(queryString).executeUpdate();
				
			}
			
			SettingAction.isInitilized = true;
			responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		}
		
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
		if (isError) responseMessage.descriptions = ConfigConstants.USER.VerifyCodeError;
		
//		return isError;
		
		return false ; // TODO: For test now
	}

	
	
}
