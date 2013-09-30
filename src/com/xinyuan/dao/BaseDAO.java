package com.xinyuan.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.DocFlavor.STRING;

import com.xinyuan.model.BaseOrderModel;


public interface BaseDAO {
	
	
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
	 * @param object	vo have some properties 
	 * @param keys		the keys is some of all the object's properties' names , which will put into the where HQL statement for
	 * 					query the results in the database 
	 * @return			The HQL must have an unique result
	 * @throws Exception
	 */
	<E extends Object> E readUnique(E object, Set<String> keys) throws Exception ;
	
	
	
	
	
	

	/**
   
	 * @param object		vo have some properties
	 * @param keys			the keys is some of all the object's properties' names , which will put into the where HQL statement for
	 * 						query the results in the database , which have the same value as the object have
	 * @param fields		the fields value you want to get / select
	 * 
	 * @param criteria		the criteria , e.g. ' = , <= , >= , between ... and ... , like ...' 
	 * 
	 * @return
	 * 						use the 'SELECT' clause
	 * 						// (if no fields specified , there will be no 'SELECT' clause. Then will return fields' key-value pairs)
	 *						this method is different from above, this result will return some columns(the keys specified it's column name) values 
	 * 						but the above method will return all column values
	 * @throws Exception
	 */
	<E extends Object> List<E> read(E object, Set<String> keys, List<String> fields, Map<String, String> criterias) throws Exception ;
	
	
	
	
	
	/**
	 * 
	 * @param object
	 * @return   return the serializable id
	 * @throws Exception
	 */
	<E> Serializable create(E object) throws Exception;
	
	
	
	<E> void modify(E object) throws Exception;
	
	<E> void delete(E object) throws Exception;
	
}
