package com.xinyuan.action;

import j2se.modules.Introspector.ObjectIntrospector;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.model.User.User;

public class AdministratorAction extends ActionBase {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected SuperDAO getDao() { return null; }
	

	public String modifyUserPermissions() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		
		UserDAO userDAO = new UserDAOIMP();
		
		List<Map<String, String>> identities = requestMessage.getIDENTITYS();
		for (int i = 0; i < models.size(); i++) {
			User model = (User)models.get(i);
			Set<String> keys = modelsKeys.get(i);
			
			String username = identities.get(i).get(ConfigJSON.USERNAME);
			User persistence = userDAO.getUser(username); 	// po
			ObjectIntrospector.copyVoToPo(model, persistence, keys);
			
			userDAO.modify(persistence);
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	

}
