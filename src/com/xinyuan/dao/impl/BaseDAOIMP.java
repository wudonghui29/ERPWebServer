package com.xinyuan.dao.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.message.ConfigConstants;
import com.xinyuan.model.BaseOrderModel;
import com.xinyuan.util.DaoHelper;

public class BaseDAOIMP extends HibernateDAO implements BaseDAO {
	
	@Override
	public <E extends Object> E read(E object, Serializable id) throws Exception {
		return (E) super.getObject(object.getClass(), id);
	}
	
	@Override
	public <E extends Object> List<E> read(E object, Set<String> keys) throws Exception {
		
		String hqlString = "from " + object.getClass().getName();
		
		String whereString = "";
		
		
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			
			System.out.println("Value: " + key + " ");
			
			if (keys.size() == 1 && key.equals("1")) {
				
				whereString = " " + "1" + "=" + "1";
				
			} else {
				
				whereString += " " + key + " = " + ":_" + key;
				
				if (iterator.hasNext()) whereString += " and";
				
			}
		}
		
		if (!whereString.isEmpty()) hqlString = hqlString  + " Where" +  whereString;
		Query query = super.getSession().createQuery(hqlString);
		
		for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
			if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
				
				String propertyname = pd.getName() ;
				Object propertyvalue =  pd.getReadMethod().invoke(object);
				
				if (DaoHelper.isContains(keys, propertyname)){
					query.setParameter("_"+propertyname, propertyvalue);
				}
			}
		}
		
		return query.list();
	}
	
	@Override
	public <E> Serializable create(E object) throws Exception {
		return super.saveObject(object);
	}

	@Override
	public <E> boolean modify(E object) throws Exception {
		boolean isSuccess = true;
		try {
			super.updateObject(object);
		} catch (HibernateException e) {
			e.printStackTrace();
			isSuccess = false;
		}
		return isSuccess;
	}

	@Override
	public <E> boolean delete(E object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
	
	

}
