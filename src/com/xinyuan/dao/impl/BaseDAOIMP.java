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
import com.xinyuan.util.CriteriaHelper;

public class BaseDAOIMP extends HibernateDAO implements BaseDAO {
	
	@Override
	public <E extends Object> E readUnique(E object, Serializable id) throws Exception {
		return (E) super.getObject(object.getClass(), id);
	}
	
	
	@Override
	public <E extends Object> E readUnique(E object, Set<String> keys) throws Exception {
		return (E) createQuery(object, keys, null, null).uniqueResult();
	}
	
	
	@Override
	public <E extends Object> List<E> read(E object, Set<String> keys, List<String> fields, Map<String, String> criterias) throws Exception {
		return createQuery(object, keys, fields, criterias).list();	// if no result , will be empty list
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

	
	
	
	
	
	
	
	
	private static String MARK = "_";
	
	
	// private methods 
	private String getAlias(Object object) {
		String wholeClassName = object.getClass().getName();
		String alias = wholeClassName.substring(wholeClassName.lastIndexOf(".") + 1).toLowerCase();
		return alias;
	}
	
	
	private <E extends Object> String assembleSelectClause(String alias, List<String> fields) {
		if (fields == null) return null;
		
		String hql  = "select " ;
		int fieldSize = fields.size();
		for (int i = 0; i < fieldSize; i++) {
			String field = fields.get(i);
			hql += (alias + "." + field );
			if (i != fieldSize - 1) hql += ", ";
		}
		
		return hql;
	}
	
	
	private String assembleWhereClause(Set<String> keys) {
		if (keys.size() == 0) return null;
		
		String whereString = "";
		
		// assemble the hql string
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			whereString += " " + key + " = " + ":" + MARK + key;
			if (iterator.hasNext()) whereString += " and";
		}
		
		return whereString;
	}
	
	
	private <E extends Object> void setParametersValues(Query query, E object, Set<String> keys) throws Exception {
		// set values
		for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
			if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
				String propertyname = pd.getName() ;
				if (IntrospectHelper.isContains(keys, propertyname)){
					Object propertyvalue =  pd.getReadMethod().invoke(object);
					query.setParameter(MARK + propertyname, propertyvalue);
				}
			}
		}
		
	}
	
	
	private <E extends Object> Query createQuery(E object, Set<String> keys, List<String> fields, Map<String, String> criterias) throws Exception {
		
		String alias = getAlias(object);
		
		String hql = " from " + object.getClass().getName() + " " + alias;
		
		String selectClause = assembleSelectClause(alias, fields);
		
		hql = selectClause == null ? hql : selectClause + hql;
		
		String whereClause = assembleWhereClause(keys);
		
		hql = whereClause == null ? hql : hql + " Where" + whereClause;
		
		
		String criterialClause = CriteriaHelper.assembleCriteriaClause(criterias);
		hql = criterialClause == null ? hql : (whereClause == null ? hql + " Where" + criterialClause : hql + criterialClause );
		
		
		Query query = super.getSession().createQuery(hql);
		
		setParametersValues(query, object, keys);
		
		CriteriaHelper.setCriteriaValues(object, query, criterias);
		
		return query;
	}
	
}
