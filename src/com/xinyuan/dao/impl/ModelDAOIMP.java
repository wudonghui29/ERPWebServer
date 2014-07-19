package com.xinyuan.dao.impl;

import j2se.modules.Helper.FileHelper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xinyuan.dao.ModelDAO;
import com.xinyuan.message.ConfigConstants;

/**
 * 
 * Limited specified XDAO access XMODEL  
 * 
 */
public abstract class ModelDAOIMP extends SuperDAOIMP implements ModelDAO {
	
	
	
	private final String Model_Scope = getModelScope();		// "com.xinyuan.model.Cards"
	private String getModelScope() {
		String wholeClassName = getClass().getName();
		String shortClassName = wholeClassName.substring(wholeClassName.lastIndexOf(FileHelper.JAVA_PACKAGE_CONNECTOR) + 1);
		String superModelName = shortClassName.substring(0, shortClassName.indexOf(ConfigConstants.DAOIMP_SUFFIX));
		return ConfigConstants.MODELPACKAGE + FileHelper.JAVA_PACKAGE_CONNECTOR + superModelName;
	}
	private boolean checkModelScope(Object object) {		// check "com.xinyuan.model.Cards.CardsAlbums" is under "com.xinyuan.model.Cards"
		return object.getClass().getName().indexOf(Model_Scope) == -1;
	}
	
	
	
	
	// Override SuperDAOIMP Methods :
	
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
	public <E extends Object> List<E> read(E object, Set<String> keys, List<String> fields, Map<String, Map<String,String>> criterias, List<String> sorts, List<String> limits) throws Exception {
		if (checkModelScope(object)) return null;
		
		return super.read(object, keys, fields, criterias, sorts, limits);
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
	
	
	
	
	// only for super
	@Override
	public <E extends Object> List<E> readJoined(List<Object> models, List<Set<String>> objectKeys, List<List<String>> outterFields, List<Map<String, Map<String,String>>> outterCriterials, List<Map<String, String>> outterJoins, List<List<String>> outterSorts, List<List<String>> outterLimits) throws Exception {
		return null;
	}
	
	
}
