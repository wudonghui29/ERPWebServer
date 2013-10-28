package com.xinyuan.dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xinyuan.dao.ModelDAO;
import com.xinyuan.message.ConstantsConfig;
import com.xinyuan.model.OrderModel;

/**
 * 
 * Limited specified XDAO access XMODEL  
 * 
 */
public abstract class ModelDAOIMP extends BaseDAOIMP implements ModelDAO {
	
	
	
	private final String Model_Scope = getModelScope();		// "com.xinyuan.model.Cards"
	private String getModelScope() {
		String wholeClassName = getClass().getName();
		String shortClassName = wholeClassName.substring(wholeClassName.lastIndexOf(".") + 1);
		String superModelName = shortClassName.substring(0, shortClassName.indexOf(ConstantsConfig.DAOIMP_SUFFIX));
		return ConstantsConfig.MODELPACKAGE + "." + superModelName;
	}
	private boolean checkModelScope(Object object) {		// check "com.xinyuan.model.Cards.CardsAlbums" is under "com.xinyuan.model.Cards"
		return object.getClass().getName().indexOf(Model_Scope) == -1;
	}
	
	
	
	
	
	
	
	
	// Implement ModelDAO Methods:
	
	@Override
	public <E extends Object> E read(E model) throws Exception {
		if (checkModelScope(model)) return null;
		Set<String> keys = new HashSet<String>();
		if (model instanceof OrderModel) {
			OrderModel orderModel = (OrderModel)model;
			if (orderModel.getOrderNO() != null) keys.add("orderNO");
			if (orderModel.getId() != 0) keys.add("id");
		}
		setUniqueResultKeys(model, keys);
		return readUnique(model, keys);
	}
	protected void setUniqueResultKeys(Object model, Set<String> keys) {}
	
	
	
	
	
	
	
	
	
	
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
	public <E extends Object> List<E> read(E object, Set<String> keys, List<String> fields, Map<String, Map> criterias, List<String> sorts) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.read(object, keys, fields, criterias, sorts);
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
	
	
	
	
	
	@Override
	public <E extends Object> List<E> readJoined(List<Object> models, List<Set<String>> objectKeys, List<List<String>> outterFields, List<Map<String, Map>> outterCriterials, List<Map<String, String>> outterJoins, List<List<String>> outterSorts) throws Exception {
		return null;
	}
	
	
}
