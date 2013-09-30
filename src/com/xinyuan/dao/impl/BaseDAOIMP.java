package com.xinyuan.dao.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;

import com.modules.introspector.IntrospectHelper;
import com.xinyuan.dao.BaseDAO;

public class BaseDAOIMP extends HibernateDAO implements BaseDAO {
	
	@Override
	public <E extends Object> E readUnique(E object, Serializable id) throws Exception {
		return (E) super.getObject(object.getClass(), id);
	}
	
	
	@Override
	public <E extends Object> E readUnique(E object, Set<String> keys) throws Exception {
		return (E) createQuery(object, keys, null).uniqueResult();
	}
	
	
	@Override
	public <E extends Object> List<E> read(E object, Set<String> keys, List<String> fields, Map<String, String> criterias) throws Exception {
		return createQuery(object, keys, getSelectClause(object, fields)).list();	// if no result , will be empty list
	}
	
	
	@Override
	public <E> Serializable create(E object) throws Exception {
		return super.saveObject(object);
	}

	@Override
	public <E> void modify(E object) throws Exception {
		super.updateObject(object);
	}

	@Override
	public <E> void delete(E object) throws Exception {
		super.deleteObject(object);
	}

	
	
	
	
	
	
	
	
	
	
	
	// private methods 
	
	private <E extends Object> String getSelectClause(E object, List<String> fields) {
		if (fields == null) return null;
		
		String wholeClassName = object.getClass().getName();
		String alias = wholeClassName.substring(wholeClassName.lastIndexOf(".") + 1).toLowerCase();
		
		String hql  = "select " ;
		int fieldSize = fields.size();
		for (int i = 0; i < fieldSize; i++) {
			String field = fields.get(i);
			hql += (alias + "." + field );
			if (i != fieldSize - 1) hql += ", ";
		}
		
		return hql;
	}
	
	
	private <E extends Object> Query createQuery(E object, Set<String> keys, String selectClause) throws Exception {
		String wholeClassName = object.getClass().getName();
		String alias = wholeClassName.substring(wholeClassName.lastIndexOf(".") + 1).toLowerCase();
		String hql = " from " + object.getClass().getName() + " " + alias;
		
		hql = selectClause == null ? hql : selectClause + hql;
		
		return getQuery(hql, object, keys);
	}
	
	
	private <E extends Object> Query getQuery(String hql, E object, Set<String> keys) throws Exception {
		String whereString = "";
		
		// assemble the hql string
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			whereString += " " + key + " = " + ":_" + key;
			if (iterator.hasNext()) whereString += " and";
		}
		
		if (!whereString.isEmpty()) hql = hql  + " Where" +  whereString;
		Query query = super.getSession().createQuery(hql);
		
		
		// set values
		for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
			if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
				String propertyname = pd.getName() ;
				if (IntrospectHelper.isContains(keys, propertyname)){
					Object propertyvalue =  pd.getReadMethod().invoke(object);
					query.setParameter("_"+propertyname, propertyvalue);
				}
			}
		}
		
		
		return query;
	}
	
	

}
