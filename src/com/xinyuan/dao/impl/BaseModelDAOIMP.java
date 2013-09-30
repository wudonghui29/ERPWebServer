package com.xinyuan.dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xinyuan.dao.BaseModelDAO;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.model.BaseOrderModel;

/**
 * 
 * Limited specified XDAO access XMODEL  
 * 
 */
public abstract class BaseModelDAOIMP extends BaseDAOIMP implements BaseModelDAO {
	
	private final String Model_Scope = getModelScope();
	
	private String getModelScope() {
		String wholeClassName = getClass().getName();
		String shortClassName = wholeClassName.substring(wholeClassName.lastIndexOf(".") + 1);
		String superModelName = shortClassName.substring(0, shortClassName.indexOf(ConstantsConfig.DAOIMP_SUFFIX));
		return ConstantsConfig.MODELPACKAGE + superModelName;
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
		setUniqueResultKeys(model, keys);
		return readUnique(model, keys);
	}
	protected void setUniqueResultKeys(BaseOrderModel model, Set<String> keys) {}
	
	
	
	
	// Override BaseDAOIMP Methods:
	
	
	@Override
	public <E extends Object> E readUnique(E object, Serializable id) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.readUnique(object, id);
	}
	
	@Override
	public <E extends Object> E readUnique(E object, Set<String> keys) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.readUnique(object, keys);
	}
	
	
	@Override
	public <E extends Object> List<E> read(E object, Set<String> keys, List<String> fields, Map<String, String> criterias) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.read(object, keys, fields, criterias);
	}
	
	@Override
	public <E> Serializable create(E object) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.saveObject(object);
	}

	@Override
	public <E> void modify(E object) throws Exception {
		if (checkModelScope(object)) return ;
		
		super.modify(object);
	}

	@Override
	public <E> void delete(E object) throws Exception {
		if (checkModelScope(object)) return ;
		
		super.delete(object);
	}
	
}
