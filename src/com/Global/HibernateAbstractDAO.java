package com.Global;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


public abstract class HibernateAbstractDAO {

	public Serializable saveObject(Object obj) {
		return getSession().save(obj);
	}

	public void updateObject(Object obj) {
		getSession().update(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getObjects(String hsql) {
		return getSession().createQuery(hsql).list();
	}

	public Object getObject(String hqsl) {
		return getSession().createQuery(hqsl).uniqueResult();
	}

	public Object getObject(Class<?> cls, Serializable id) {
		return getSession().get(cls, id);
	}

	public void deleteObject(Object obj) {
		getSession().delete(obj);
	}

	/**
	 * Get the object by the column has the unique value
	 * 
	 * @param cls
	 * @param uniqueColumnName
	 * @param uniqueValue
	 * @return
	 * 
	 */
	public Object getObject(Class<?> cls, String uniqueColumnName, Serializable uniqueValue) {
		String clsName = cls.getName();
		String alias = clsName.substring(clsName.lastIndexOf(".") + 1);
		String queryString = "FROM " + clsName + " " + alias + " WHERE "
				+ uniqueColumnName + " = " + ":" + uniqueColumnName;
		Query query = createQuery(queryString);
		query.setParameter(uniqueColumnName, uniqueValue);
		return query.uniqueResult();
	}

	public Query createQuery(String queryString) {
		return getSession().createQuery(queryString);
	}

	public SQLQuery createSQLQuery(String queryString) {
		return getSession().createSQLQuery(queryString);
	}

	public boolean isTableEmpty(String table){
		String queryString = "SELECT EXISTS (SELECT NULL FROM " + table + ")";
		SQLQuery query = this.createSQLQuery(queryString);
		
		Number result = (Number)query.uniqueResult();
		
		return result.intValue() == 0;
	}
	
	
	private Session getSession() {
		return HibernateInitializer.getSessionFactory().getCurrentSession();
	}
	
}
