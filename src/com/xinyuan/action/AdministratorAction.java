package com.xinyuan.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.modules.Introspector.ModelIntrospector;
import com.opensymphony.xwork2.Action;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.dao.impl.UserDAOIMP;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.message.ConfigJSON;
import com.xinyuan.model.Extensions.APPOrderAttributes;
import com.xinyuan.model.User.User;

public class AdministratorAction extends ActionBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected SuperDAO getDao() { return null; }
	

	public String modifyPermissions() throws Exception {
		if (models.size() != 1) return Action.NONE;		// Forbid modified multi-
		
		UserDAO userDAO = new UserDAOIMP();
		
		List<Map<String, String>> identities = requestMessage.getIDENTITYS();
		for (int i = 0; i < models.size(); i++) {
			User model = (User)models.get(i);
			Set<String> keys = objectKeys.get(i);
			
			String username = identities.get(i).get(ConfigJSON.USERNAME);
			User persistence = userDAO.getUser(username); 	// po
			ModelIntrospector.copyVoToPo(model, persistence, keys);
			
			userDAO.modify(persistence);
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		return Action.NONE;
	}
	
	
	public String modifyApprovals() throws Exception {
		if (models.size() != 1) return Action.NONE;
		
		List<Map<String, String>> identities = requestMessage.getIDENTITYS();
		
		SuperDAO dao = new SuperDAOIMP();
		for(int i = 0 ; i < models.size(); i++) {
			Map<String, String> idenfier = identities.get(i);
			
			APPOrderAttributes attributesVO = (APPOrderAttributes) models.get(i);
			ModelIntrospector.setProperty(attributesVO, idenfier);
			APPOrderAttributes attributesPO =  dao.readUnique(attributesVO, idenfier.keySet());
			if (attributesPO == null) {
				attributesPO = attributesVO;
				dao.create(attributesPO);
			} else {
				Set<String> keys = objectKeys.get(i);
				ModelIntrospector.copyVoToPo(attributesVO, attributesPO, keys);
				dao.modify(attributesPO);
			}
		}
		
		responseMessage.status = ConfigConstants.STATUS_POSITIVE;
		
		return Action.NONE;
	}

}
