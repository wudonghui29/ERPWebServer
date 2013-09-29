package com.xinyuan.dao.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.util.IntrospectorHelper;

public class BaseDAOIMP extends HibernateDAO implements BaseDAO {
	
	@Override
	public <E extends Object> E readUnique(E object, Serializable id) throws Exception {
		return (E) super.getObject(object.getClass(), id);
	}
	
	
	@Override
	public <E extends Object> E readUnique(E object, Set<String> keys) throws Exception {
		String hql = "from " + object.getClass().getName();
		Query query = query(hql, object, keys);
		return (E) query.uniqueResult();
	}
	
	@Override
	public <E extends Object> List<E> read(E object, Set<String> keys) throws Exception {
		String hql = "from " + object.getClass().getName();
		Query query = query(hql, object, keys);
		return query.list();			// if no result , will be empty list
	}
	
	
	@Override
	public <E extends Object> List<E> read(E object, Set<String> keys, List<String> fields) throws Exception {
		String wholeClassName = object.getClass().getName();
		
		String shortClassName = wholeClassName.substring(wholeClassName.lastIndexOf(".") + 1);
		
		String shortClassNameLowerCase = shortClassName.toLowerCase();
		
		String hql  = "select " ;
		int fieldSize = fields.size();
		for (int i = 0; i < fieldSize; i++) {
			String field = fields.get(i);
			hql += (shortClassNameLowerCase + "." + field );
			if (i != fieldSize - 1) hql += ", ";
		}
		hql += " from " + wholeClassName + " " + shortClassNameLowerCase;
		
		Query query = query(hql, object, keys);
		return query.list();		// if no result , will be empty list
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
	private <E extends Object> Query query(String hql, E object, Set<String> keys) throws Exception {
		String whereString = "";
		
		
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			
//			System.out.println("Value: " + key + " ");
			
			if (keys.size() == 1 && key.equals("1")) {
				
				whereString = " " + "1" + "=" + "1";
				
			} else {
				
				whereString += " " + key + " = " + ":_" + key;
				
				if (iterator.hasNext()) whereString += " and";
				
			}
		}
		
		if (!whereString.isEmpty()) hql = hql  + " Where" +  whereString;
		Query query = super.getSession().createQuery(hql);
		
		for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
			if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
				
				String propertyname = pd.getName() ;
				
				if (IntrospectorHelper.isContains(keys, propertyname)){
					Object propertyvalue =  pd.getReadMethod().invoke(object);
					query.setParameter("_"+propertyname, propertyvalue);
				}
			}
		}
		
		return query;
	}
	
	

}
