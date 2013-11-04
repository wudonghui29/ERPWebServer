package com.xinyuan.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;

import com.modules.introspector.IntrospectHelper;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.util.QueryCriteriasHelper;
import com.xinyuan.util.QueryFieldsHelper;
import com.xinyuan.util.QueryLimitsHelper;
import com.xinyuan.util.QueryObjectsHelper;
import com.xinyuan.util.QuerySortsHelper;

public class SuperDAOIMP extends HibernateDAO implements SuperDAO {
	
	@Override
	public <E extends Object> E readUnique(E object, Serializable id) throws Exception {
		return (E) super.getObject(object.getClass(), id);
	}
	
	@Override
	public <E extends Object> E readUnique(E object, Set<String> keys) throws Exception {
		return (E) createQuery(object, keys, null, null, null).uniqueResult();
	}
	
	@Override
	public <E extends Object> List<E> read( E object,  Set<String> keys,  List<String> fields,  Map<String, Map> criterias, List<String> sorts ) throws Exception {
		/**
		 * 0. the from clause need 'object' and the where A.a = a clause need 'keys'
		 * 1. select clause need 'fields'
		 * 2. criteria clause need 'criterias'
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
	public <E extends Object> List<E> readJoined(
													List<Object> outterObjects, 
													List<Set<String>> outterKeys, 
													List<List<String>> outterFields, 
													List<Map<String, Map>> outterCriterials, 
													List<Map<String, String>> outterJoins,
													List<List<String>> outterSorts,
													List<List<String>> outterLimits
													
												) throws Exception {
		
		// Create Query String
		
		// limit [[5, 10]] :
		boolean isLimitPage = QueryLimitsHelper.isLimits(outterLimits);
		String limitClause = isLimitPage ? "LIMIT " + outterLimits.get(0).get(0) + ", " + outterLimits.get(0).get(1) : "";
		
		String fromClause 		= "";
		String selectClause 	= "";
		String orderByClause 	= "";
		String leftJoinClause 	= "";
		String whereEqualsClause 		= "";
		String whereCriterialsClause 	= "";
		
		for (int i = 0; i < outterObjects.size(); i++) {
			Object object = outterObjects.get(i);
			Set<String> keys = outterKeys.get(i);
			List<String> sorts = outterSorts == null ? null : outterSorts.get(i);
			List<String> fields = outterFields == null ? null : outterFields.get(i);
			Map<String, String> joins = outterJoins == null ? null : outterJoins.get(i);
			Map<String, Map> criterias = outterCriterials == null ? null : outterCriterials.get(i);
			
			// alias , table too
			String alias = IntrospectHelper.getShortClassName(object);
			
			// joined on :  A Left Join B on A.a = B.b Left Join C on A.a = C.c
			if (i == 0) {
				leftJoinClause += alias ;		
			}else {
				String joinONClause = QueryCriteriasHelper.assembleCriteriasJoinOnClause(joins);
				if (! joinONClause.isEmpty()) {
					leftJoinClause += " LEFT JOIN " ;
					leftJoinClause += alias;
					leftJoinClause += " ON " + joinONClause;
				}
			}
			
			// select fields :
			String selectClauseTemp = QueryFieldsHelper.assembleFieldsSelectClause(alias, fields);
			selectClause += (selectClause.isEmpty() ? selectClauseTemp : ", "+selectClauseTemp);
			
			// where keys (=) :
			String whereEqualsClauseTemp = QueryObjectsHelper.assembleObjectsWhereClause(alias, keys);
			whereEqualsClause += ( whereEqualsClause.isEmpty() ? whereEqualsClauseTemp : (" AND" + whereEqualsClauseTemp));
			
			// where criterias (>, < , != , like , between ...) :
			String whereCriterialClauseTemp = QueryCriteriasHelper.assembleCriteriasWhereClause(criterias);
			whereCriterialsClause += (whereCriterialsClause.isEmpty() ? whereCriterialClauseTemp : (" AND" + whereCriterialClauseTemp));
			
			// order by sorts :
			String orderByClauseTemp = QuerySortsHelper.assembleOrderByClause(alias, sorts);
			orderByClause += orderByClause.isEmpty() ? orderByClauseTemp : ", " + orderByClauseTemp;
			
		}
		
		fromClause += " FROM ";
		if (!selectClause.isEmpty()) selectClause = "SELECT " + selectClause;
		if (!whereEqualsClause.isEmpty()) whereEqualsClause = " WHERE" + whereEqualsClause;
		if (!whereCriterialsClause.isEmpty()) whereCriterialsClause = (whereEqualsClause.isEmpty() ? " WHERE" : " AND") + whereCriterialsClause;
		if (!orderByClause.isEmpty()) orderByClause = " ORDER BY " + orderByClause;
		
		String sqlString = "";
		
		sqlString += selectClause;
		sqlString += fromClause;
		sqlString += leftJoinClause;
		sqlString += whereEqualsClause;
		sqlString += whereCriterialsClause;
		sqlString += orderByClause;
		
		// Create SQL Query From Query String
		Query query = super.getSession().createSQLQuery(sqlString);
		
		// Set Parameters to Query
		for (int i = 0; i < outterObjects.size(); i++) {
			Object object = outterObjects.get(i);
			Set<String> keys = outterKeys.get(i);
			Map<String, Map> criterias = null;
			if (outterCriterials != null) criterias = outterCriterials.get(i);
			QueryObjectsHelper.setObjectsWhereValues(query, object, keys);
			QueryCriteriasHelper.setCriteriasWhereValues(query, criterias);
		}
		
		return query.list();
	}
	
	
	private <E extends Object> Query createQuery(E object, Set<String> keys, List<String> fields, Map<String, Map> criterias, List<String> sorts) throws Exception {
		
		// Create Query String
		String hqlString = createHQL(object, keys, fields, criterias, sorts);
		
		// Create HQL Query From Query String
		Query query = super.getSession().createQuery(hqlString);
		
		// Set Parameters to Query
		QueryObjectsHelper.setObjectsWhereValues(query, object, keys);
		QueryCriteriasHelper.setCriteriasWhereValues(query, criterias);
		return query;
	}
	
	
	private <E extends Object> String createHQL(E object, Set<String> keys, List<String> fields, Map<String, Map> criterias, List<String> sorts) {
		
		String fromClause 		= "";
		String selectClause 	= "";
		String orderByClause 	= "";
		String whereEqualsClause 		= "";
		String whereCriterialsClause 	= "";
		
		// alias
		String alias = IntrospectHelper.getShortClassName(object);
		
		// from Clause :
		fromClause = object.getClass().getName() + " " + alias;
		
		// select fields :
		selectClause = QueryFieldsHelper.assembleFieldsSelectClause(alias, fields);
		
		// where keys (=) :
		whereEqualsClause = QueryObjectsHelper.assembleObjectsWhereClause(alias, keys);
		
		// where criterias (>, < , != , like , between ...) :
		whereCriterialsClause = QueryCriteriasHelper.assembleCriteriasWhereClause(criterias);
		
		// order by sorts :
		orderByClause = QuerySortsHelper.assembleOrderByClause(alias, sorts);

		
		fromClause = " FROM " + fromClause;
		if (!selectClause.isEmpty()) selectClause = "SELECT " + selectClause;
		if (!whereEqualsClause.isEmpty()) whereEqualsClause = " WHERE" + whereEqualsClause;
		if (!whereCriterialsClause.isEmpty()) whereCriterialsClause = (whereEqualsClause.isEmpty() ? " WHERE" : " AND") + whereCriterialsClause;
		if (!orderByClause.isEmpty()) orderByClause = " ORDER BY " + orderByClause;
		
		String hqlString = "";
		
		hqlString += selectClause;
		hqlString += fromClause;
		hqlString += whereEqualsClause;
		hqlString += whereCriterialsClause;
		hqlString += orderByClause;
		
		return hqlString;
	}
	
}
