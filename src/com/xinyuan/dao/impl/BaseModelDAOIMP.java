package com.xinyuan.dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.apple.jobjc.SEL;
import com.xinyuan.dao.BaseModelDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.BaseOrderModel;

public abstract class BaseModelDAOIMP extends BaseDAOIMP implements BaseModelDAO {
	
	private final String Model_Scope = getModelScope();
	
	private String getModelScope() {
		String wholeClassName = getClass().getName();
		String shortClassName = wholeClassName.substring(wholeClassName.lastIndexOf(".") + 1);
		String superModelName = shortClassName.substring(0, shortClassName.indexOf(ConfigConstants.DAOIMP_SUFFIX));
		return ConfigConstants.MODELPACKAGE + superModelName;
	}
	
	private boolean checkModelScope(Object object) {
		return object.getClass().getName().indexOf(Model_Scope) == -1;
	}
	
	
	
	// Implement BaseModelDAO Methods:
	
	
	@Override
	public <E extends BaseOrderModel> E read(E model) throws Exception {
		if (checkModelScope(model)) return null;
		
		
		Set<String> keys = new HashSet<String>();
		
		if (model.getOrderNO() != null) keys.add("orderNO");
		if (model.getId() != 0) keys.add("id");
		
		List<E> list = read(model, keys);
		
		if (list.size() == 1) return list.get(0);
		
		return null;
	}
	
	
	
	
	// Override BaseDAOIMP Methods:
	
	
	@Override
	public <E extends Object> E read(E object, Serializable id) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.read(object, id);
	}
	
	@Override
	public <E extends Object> List<E> read(E object, Set<String> keys) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.read(object, keys);
	}
	
	@Override
	public <E> Serializable create(E object) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.saveObject(object);
	}

	@Override
	public <E> boolean modify(E object) throws Exception {
		if (checkModelScope(object)) return false;
		
		return super.modify(object);
	}

	@Override
	public <E> boolean delete(E object) throws Exception {
		if (checkModelScope(object)) return false;
		
		return super.delete(object);
	}
	
}
