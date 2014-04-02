package com.xinyuan.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface SuperDAO {
	
	/**
	 * 
	 * @param object
	 * @return   return the serializable id
	 * @throws Exception
	 */
	<E> Serializable create(E object) throws Exception;
	
	<E> void modify(E object) throws Exception;
	
	<E> void delete(E object) throws Exception;
	
	
	/**
	 * 
	 * @param object	vo have the unique property
	 * @param unique
	 * @return read the whole properties on database according to the object with a unique column value , be sure have unique result
	 * @throws Exception
	 */
	<E extends Object> E readUnique(E object, Serializable id) throws Exception;
	
	
	
	/**
	 * 
	 * @param object	vo have some properties and values
	 * @param keys		the keys is some of all the object's properties' names , which will put into the where HQL statement for
	 * 					query the results in the database 
	 * @return			The HQL must have an unique result
	 * @throws Exception
	 */
	<E extends Object> E readUnique(E object, Set<String> keys) throws Exception ;
	
	
	public <E extends Object> E readUnique(Class<?> cls, String uniqueColumnName, Serializable uniqueValue) throws Exception;
	
	
	
	/**
	 * for hql string
	 * @return
	 */
	<E extends Object> String getTotalRows(E object,  Set<String> keys,  List<String> fields,  Map<String, Map<String,String>> criterias) throws Exception;
	
	/**
   
	 * @param object		vo carries some properties and values
	 * @param keys			the keys is some of all the object's properties' names , which will put into the where HQL statement for
	 * 						query the results in the database , which have the same value as the object have
	 * @param fields		the fields value you want to get / select
	 * 
	 * @param criteria		the criteria , e.g. ' = , <= , >= , between ... and ... , like ...' 
	 * 
	 * @param sorts 		the order by clause
	 * 
	 * @return
	 * 						use the 'SELECT' clause
	 * 						// (if no fields specified , there will be no 'SELECT' clause. Then will return fields' key-value pairs)
	 *						this method is different from above, this result will return some columns(the keys specified it's column name) values 
	 * 						but the above method will return all column values
	 * @throws Exception
	 */
	<E extends Object> List<E> read(E object, Set<String> keys, List<String> fields, Map<String, Map<String,String>> criterias, List<String> sorts, List<String> limits) throws Exception ;
	
	
	
	/**
	 * When use Limit 0. 10 clause, for (joined) sql string
	 * @return  the total count of rows
	 */
	String getJoinedTotalRows() ;
	
	
	<E extends Object> List<E> readJoined(List<Object> outterObjects, List<Set<String>> outterKeys, List<List<String>> outterFields, List<Map<String, Map<String, String>>> outterCriterials, List<Map<String, String>> outterJoins, List<List<String>> outterSorts, List<List<String>> outterLimits) throws Exception;
	
	
	
	
}
