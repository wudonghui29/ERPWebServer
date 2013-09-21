package com.xinyuan.dao.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.xinyuan.dao.BaseDAO;
import com.xinyuan.model.BaseOrderModel;

public class BaseDAOIMP extends HibernateDAO implements BaseDAO {
	
	@Override
	public <E extends BaseOrderModel> E read(E object) throws Exception {		//note: object contains id
		return (E) super.getObject(object.getClass(), object.getId());
	}
	
	@Override
	public <E extends BaseOrderModel> List<E> read(E object, Map<String, Object> fields) throws Exception {
		
		String hqlString = "from " + object.getClass().getName();
		
		String whereString = "";
		Set<String> keys = fields.keySet();
		List list = new ArrayList();
		list.addAll(keys);
		
		if (list.size() == 1 && list.get(0).equals("1")) {
			String key = (String) list.get(0);
			whereString = " " + key + "=" + fields.get(key);
			
		} else {
			for (int i = 0; i < list.size(); i++) {
				String key = (String) list.get(i);
				
				whereString += " " + key + " = " + ":_" + key;
				
				if (i != (list.size() -1 )) whereString += " and";
			}
		}
		
		if (!whereString.isEmpty()) hqlString = hqlString  + " Where" +  whereString;
		Query query = super.getSession().createQuery(hqlString);
		
		for (PropertyDescriptor pd : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
			if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
				
				String propertyname = pd.getName() ;
				Object propertyvalue =  pd.getReadMethod().invoke(object);
				
				if (fields.containsKey(propertyname)) {
					query.setParameter("_"+propertyname, propertyvalue);
				}
				
//				System.out.println(propertyname + " : " + propertyvalue);
			}
		}
		
		List result = query.list();
		return result;
	}
	
	@Override
	public <E> Integer create(E object) throws Exception {
		return (Integer)super.saveObject(object);
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

	@Override
	public <E> boolean apply(E object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
