package com.xinyuan.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;

import com.modules.introspector.IntrospectHelper;
import com.xinyuan.dao.BaseDAO;
import com.xinyuan.util.QueryCriteriasHelper;
import com.xinyuan.util.QueryFieldsHelper;
import com.xinyuan.util.QueryObjectsHelper;
import com.xinyuan.util.QuerySortsHelper;

public class BaseDAOIMP extends HibernateDAO implements BaseDAO {
	
	@Override
	public <E extends Object> E readUnique(E object, Serializable id) throws Exception {
		return (E) super.getObject(object.getClass(), id);
	}
	
	
	@Override
	public <E extends Object> E readUnique(E object, Set<String> keys) throws Exception {
		return (E) createQuery(object, keys, null, null, null).uniqueResult();
	}
	
	
	@Override
	public <E extends Object> List<E> read(E object, Set<String> keys, List<String> fields, Map<String, Map> criterias, List<String> sorts) throws Exception {
		/**
		 * 
		 * 0. the from clause need 'object' and the where A.a = a clause need 'keys'
		 * 
		 * 1. select clause need 'fields'
		 * 
		 * 2. criteria clause need 'criterias'
		 * 
		 */
		return createQuery(object, keys, fields, criterias, sorts).list();	// if no result , will be empty list
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

	
	
	
	
	@Override
	public <E extends Object> List<E> readJoined(List<Object> models, List<Set<String>> objectKeys, List<List<String>> outterFields, List<Map<String, Map>> outterCriterials, List<Map<String, String>> outterJoins, List<List<String>> outterSorts) throws Exception {
		
		
		String fromClause = " from ";
		String selectClause = "";
		String whereEqualsClause = "";
		String whereCriterialClause = "";
		
		String orderClause = "";
		
		
		
		for (int i = 0; i < models.size(); i++) {
			
			Object model = models.get(i);
			
			Set<String> keys = objectKeys.get(i);
			
			List<String> fields = outterFields == null ? null : outterFields.get(i);
			
			Map<String, Map> criterias = outterCriterials == null ? null : outterCriterials.get(i);
			
			Map<String, String> joins = outterJoins == null || outterJoins.size() <= i ? null : outterJoins.get(i);
			
			List<String> sorts = outterSorts == null ? null : outterSorts.get(i);
			
			
			
			
			String shortClassName = IntrospectHelper.getShortClassName(model);
			
			// fromClause
			if (i != 0) fromClause += " LEFT JOIN " ;						// TODO:  to support form A Left Join B on A.a = B.b Left Join C on A.a = C.c
			fromClause += /*model.getClass().getName() + " " + */shortClassName ;		// in sql , remove the long class name
			// joinedClause
			String joinONClause = QueryCriteriasHelper.assembleCriteriasJoinClause(joins);
			if (joinONClause != null && i != 0) {
				fromClause += " ON " + joinONClause;
			}
			
			
			// selectClause
			String selectClauseTemp = QueryFieldsHelper.assembleFieldsSelectClause(shortClassName, fields);
			if (selectClauseTemp != null) selectClause += (selectClause.isEmpty() ? selectClauseTemp : ", "+selectClauseTemp);
			
			// whereClause
			// =
			String whereEqualsClauseTemp = QueryObjectsHelper.assembleObjectsWhereClause(keys,shortClassName);
			if (whereEqualsClauseTemp != null) whereEqualsClause += ( whereEqualsClause.isEmpty() ? whereEqualsClauseTemp : (" and" + whereEqualsClauseTemp));
			// >, < , != , like , between ...
			String whereCriterialClauseTemp = QueryCriteriasHelper.assembleCriteriasWhereClause(criterias);
			if (whereCriterialClauseTemp != null) whereCriterialClause += whereCriterialClauseTemp;
			
			
			// orderClause
			String orderByClauseTemp = QuerySortsHelper.assembleOrderByClause(sorts, shortClassName);
			if (orderByClauseTemp != null) orderClause += orderClause.isEmpty() ? orderByClauseTemp : ", " + orderByClauseTemp;
			
			
		}
		
		
		String hql = fromClause;
		
		
		// SELECT
		if (!selectClause.isEmpty()) hql = "select " + selectClause + hql;
		
		// WHERE
		if (!whereEqualsClause.isEmpty()) hql += " Where" + whereEqualsClause;
		if (!whereCriterialClause.isEmpty())  hql += (whereEqualsClause == null) ? " Where" + whereCriterialClause : " and" + whereCriterialClause;
		
		
		// ORDER BY
		if (!orderClause.isEmpty()) hql += " order by " + orderClause;
		
		
		
		// Create Query
		Query query = super.getSession().createSQLQuery(hql);
		
		
		
		for (int i = 0; i < models.size(); i++) {
			
			Object model = models.get(i);
			
			Set<String> keys = objectKeys.get(i);
			
			Map<String, Map> criterias = outterCriterials == null ? null : outterCriterials.get(i);
			
			String shortClassName = IntrospectHelper.getShortClassName(model);
			
			QueryObjectsHelper.setObjectsWhereValues(query, model, keys, shortClassName);
			
			QueryCriteriasHelper.setCriteriasWhereValues(query, criterias);
		}
		
		
		return query.list();
	}
	
	
	
	
	
	
	// private methods 
	private <E extends Object> Query createQuery(E object, Set<String> keys, List<String> fields, Map<String, Map> criterias, List<String> sorts) throws Exception {
		
		String hql = "";
		
		// from :
		String shortClassName = IntrospectHelper.getShortClassName(object);
		
		String fromClause = " from " + object.getClass().getName() + " " + shortClassName;
		
		hql += fromClause;
		
		// select :
		String selectClause = QueryFieldsHelper.assembleFieldsSelectClause(shortClassName, fields);
		
		if (selectClause != null)  hql = "select " + selectClause + hql;
		
		
		
		// where :
		
		// =
		String whereEqualsClause = QueryObjectsHelper.assembleObjectsWhereClause(keys, shortClassName);
		
		if (whereEqualsClause != null)  hql += " Where" + whereEqualsClause;
		
		
		
		
		// >, < , != , like , between ...
		String whereCriterialClause = QueryCriteriasHelper.assembleCriteriasWhereClause(criterias);
		
		if (whereCriterialClause != null) hql += (whereEqualsClause == null) ? " Where" + whereCriterialClause : " and" + whereCriterialClause;
		
		
		
		// order by :
		String orderByClause = QuerySortsHelper.assembleOrderByClause(sorts, shortClassName);
		if (orderByClause != null) hql += " order by " + orderByClause;
		
		
		// Create query
		Query query = super.getSession().createQuery(hql);
		
		
		
		
		QueryObjectsHelper.setObjectsWhereValues(query, object, keys, shortClassName);
		
		QueryCriteriasHelper.setCriteriasWhereValues(query, criterias);
		
		return query;
	}
	
	

	
}
