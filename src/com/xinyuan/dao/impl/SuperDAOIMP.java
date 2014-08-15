package com.xinyuan.dao.impl;

import j2se.modules.Introspector.IntrospectHelper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.Global.HibernateAbstractDAO;
import com.xinyuan.Query.QueryCriteriasHelper;
import com.xinyuan.Query.QueryFieldsHelper;
import com.xinyuan.Query.QueryLimitsHelper;
import com.xinyuan.Query.QueryObjectsHelper;
import com.xinyuan.Query.QuerySortsHelper;
import com.xinyuan.dao.SuperDAO;

public class SuperDAOIMP extends HibernateAbstractDAO implements SuperDAO {
	private static final String k_COMMA = ", ";
	private static final String k_AND = " AND";
	private static final String k_FROM = " FROM ";
	private static final String k_SELECT = "SELECT ";
	private static final String k_WHERE = " WHERE";
	private static final String k_ORDERBY = " ORDER BY ";
	
	private static final String k_ON 	= " ON ";
	private static final String k_LEFTJOIN = " LEFT JOIN ";
	
	private static final String K_SELECT_COUNT_ALL = "SELECT COUNT(*) ";
	private static final String k_SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";           // Cause 'SQL_CALC_FOUND_ROWS' , this will ignore 'LIMIT' clause.
	private static final String k_SELECT_SQL_CALC_FOUND_ROWS = "SELECT SQL_CALC_FOUND_ROWS ";
	
	
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
	public <E extends Object> E readUnique(E object, Serializable id) throws Exception {
		return (E) super.getObject(object.getClass(), id);
	}
	
	@Override
	public <E extends Object> E readUnique(Class<?> cls, String uniqueColumnName, Serializable uniqueValue) throws Exception {
		return (E) super.getObject(cls, uniqueColumnName, uniqueValue);
	}
	
	@Override
	public <E extends Object> E readUnique(E object, Set<String> keys) throws Exception {
		String hqlString = createHQLString(object, keys, null, null, null);
		return (E) createHQLQuery(hqlString, object, keys, null, null).uniqueResult();
	}
	
	@Override
	public <E extends Object> String getTotalRows(E object,  Set<String> keys,  List<String> fields,  Map<String, Map<String,String>> criterias) throws Exception {
		/**
		 * 0. the from clause need 'object' and the where A.a = a clause need 'keys'
		 * 1. select clause need 'fields'
		 * 2. criteria clause need 'criterias'
		 */
		String hqlString = createHQLString(object, keys, fields, criterias, null);
		// substring the string before 'from'
		String totalRowsHQL = hqlString.substring(hqlString.indexOf(k_FROM), hqlString.length());
		// add 'select count(*)'
		totalRowsHQL = K_SELECT_COUNT_ALL + totalRowsHQL;			
		
		Query query = createHQLQuery(totalRowsHQL, object, keys, criterias, null);
		String totalRow = query.uniqueResult().toString();
		return totalRow;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Object> List<E> read( E object,  Set<String> keys,  List<String> fields,  Map<String, Map<String,String>> criterias, List<String> sorts, List<String> limits) throws Exception {
		/**
		 * 0. the from clause need 'object' and the where A.a = a clause need 'keys'
		 * 1. select clause need 'fields'
		 * 2. criteria clause need 'criterias'
		 */
		String hqlString = createHQLString(object, keys, fields, criterias, sorts);
//		DLog.log(hqlString);
		Query query = createHQLQuery(hqlString, object, keys, criterias, limits);
		return query.list();	// if no result , will be empty list
	}
	
	@Override
	public String getJoinedTotalRows() {
		SQLQuery query = super.createSQLQuery(k_SELECT_FOUND_ROWS);
		return query.uniqueResult().toString();
	}
	
	@Override
	public <E extends Object> List<E> readJoined(
													List<Object> outterObjects, 
													List<Set<String>> outterKeys, 
													List<List<String>> outterFields, 
													List<Map<String, Map<String, String>>> outterCriterials, 
													List<Map<String, String>> outterJoins,
													List<List<String>> outterSorts,
													List<List<String>> outterLimits
													
												) throws Exception {
		
		// Create Query String
		String fromClause 		= "";
		String limitsClause		= "";
		String selectClause 	= "";
		String orderByClause 	= "";
		String leftJoinClause 	= "";
		String whereEqualsClause 		= "";
		String whereCriterialsClause 	= "";
		
		for (int i = 0; i < outterObjects.size(); i++) {
			Object object = outterObjects.get(i);
			Set<String> keys = outterKeys.get(i);
			List<String> sorts = outterSorts != null && outterSorts.size() > i ? outterSorts.get(i) : null;
			List<String> fields = outterFields != null && outterFields.size() > i ? outterFields.get(i) : null;
			Map<String, String> joins = outterJoins != null && outterJoins.size() > i ? outterJoins.get(i) : null;
			Map<String, Map<String,String>> criterias = outterCriterials != null && outterCriterials.size() > i ? outterCriterials.get(i) : null;
			
			// alias , table too
			String alias = IntrospectHelper.getShortClassName(object);
			
			// joined on :  A Left Join B on A.a = B.b Left Join C on A.a = C.c
			if (i == 0) {
				leftJoinClause += alias ;		
			}else {
				String joinONClause = QueryCriteriasHelper.assembleCriteriasJoinOnClause(joins);
				if (! joinONClause.isEmpty()) {
					leftJoinClause += k_LEFTJOIN ;
					leftJoinClause += alias;
					leftJoinClause += k_ON + joinONClause;
				}
			}
			
			// select fields :
			String selectClauseTemp = QueryFieldsHelper.assembleFieldsSelectClause(alias, fields);
			if (! selectClauseTemp.isEmpty()) {
				selectClause += selectClause.isEmpty() ? selectClauseTemp : (k_COMMA + selectClauseTemp);
			}
			
			// where keys (=) :
			String whereEqualsClauseTemp = QueryObjectsHelper.assembleObjectsWhereClause(alias, keys);
			if (! whereEqualsClauseTemp.isEmpty()) {
				whereEqualsClause += ( whereEqualsClause.isEmpty() ? whereEqualsClauseTemp : (k_AND + whereEqualsClauseTemp));
			}
			
			// where criterias (>, < , != , like , between ...) :
			String whereCriterialClauseTemp = QueryCriteriasHelper.assembleCriteriasWhereClause(alias, criterias);
			if (! whereCriterialClauseTemp.isEmpty()) {
				whereCriterialsClause += (whereCriterialsClause.isEmpty() ? whereCriterialClauseTemp : (k_AND + whereCriterialClauseTemp));
			}
			
			// order by sorts :
			String orderByClauseTemp = QuerySortsHelper.assembleOrderByClause(alias, sorts);
			if (!orderByClauseTemp.isEmpty()) {
				orderByClause += orderByClause.isEmpty() ? orderByClauseTemp : (k_COMMA + orderByClauseTemp);
			}
			
		}
		
		// Limit Clause :
		limitsClause = QueryLimitsHelper.assembleJoinedLimitsClause(outterLimits);
		
		fromClause += k_FROM;
		if (!selectClause.isEmpty()) selectClause = (limitsClause.isEmpty() ?  k_SELECT : k_SELECT_SQL_CALC_FOUND_ROWS) + selectClause;
		if (!whereEqualsClause.isEmpty()) whereEqualsClause = k_WHERE + whereEqualsClause;
		if (!whereCriterialsClause.isEmpty()) whereCriterialsClause = (whereEqualsClause.isEmpty() ? k_WHERE : k_AND) + whereCriterialsClause;
		if (!orderByClause.isEmpty()) orderByClause = k_ORDERBY + orderByClause;
		
		String sqlString = "";
		
		sqlString += selectClause;
		sqlString += fromClause;
		sqlString += leftJoinClause;
		sqlString += whereEqualsClause;
		sqlString += whereCriterialsClause;
		sqlString += orderByClause;
		sqlString += limitsClause;
		
//		DLog.log(sqlString);
		// Create SQL Query From Query String
		SQLQuery query = super.createSQLQuery(sqlString);
		
		// Set Parameters to Query
		for (int i = 0; i < outterObjects.size(); i++) {
			Object object = outterObjects.get(i);
			String alias = IntrospectHelper.getShortClassName(object);
			Set<String> keys = outterKeys.get(i);
			QueryObjectsHelper.setObjectsWhereValues(query, object, keys);
			
			Map<String, Map<String,String>> criterias = outterCriterials != null && outterCriterials.size() > i ? outterCriterials.get(i) : null;
			QueryCriteriasHelper.setCriteriasWhereValues(alias,query, criterias);
		}
		
		if (!limitsClause.isEmpty() && QueryLimitsHelper.isFirstTimeGetLimitsNumber(outterLimits.get(0))) query.list();	// Fix the weird result in first time 
		
		return query.list();
	}
	
	
	private <E extends Object> Query createHQLQuery(String hqlString, E object, Set<String> keys, Map<String, Map<String,String>> criterias, List<String> limits) throws Exception {
		// Create HQL Query From Query String
		Query query = super.createQuery(hqlString);
		
		String alias = IntrospectHelper.getShortClassName(object);
		
		// Set Parameters to Query
		QueryObjectsHelper.setObjectsWhereValues(query, object, keys);
		QueryCriteriasHelper.setCriteriasWhereValues(alias, query, criterias);
		
		// Set the limit scope
		if (QueryLimitsHelper.isNeedLimit(limits)) query.setFirstResult(Integer.valueOf(limits.get(0))).setMaxResults(Integer.valueOf(limits.get(1)));		
		
		return query;
	}
	
	
	private <E extends Object> String createHQLString(E object, Set<String> keys, List<String> fields, Map<String, Map<String,String>> criterias, List<String> sorts) {
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
		whereCriterialsClause = QueryCriteriasHelper.assembleCriteriasWhereClause(alias, criterias);
		
		// order by sorts :
		orderByClause = QuerySortsHelper.assembleOrderByClause(alias, sorts);

		fromClause = k_FROM + fromClause;
		if (!selectClause.isEmpty()) selectClause = k_SELECT + selectClause;
		if (!whereEqualsClause.isEmpty()) whereEqualsClause = k_WHERE + whereEqualsClause;
		if (!whereCriterialsClause.isEmpty()) whereCriterialsClause = (whereEqualsClause.isEmpty() ? k_WHERE : k_AND) + whereCriterialsClause;
		if (!orderByClause.isEmpty()) orderByClause = k_ORDERBY + orderByClause;
		
		String hqlString = "";
		
		hqlString += selectClause;
		hqlString += fromClause;
		hqlString += whereEqualsClause;
		hqlString += whereCriterialsClause;
		hqlString += orderByClause;
		
		return hqlString;
	}
	
	
}
