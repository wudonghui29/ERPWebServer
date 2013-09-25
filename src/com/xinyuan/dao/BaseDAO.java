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
	<E extends Object> E read(E object, Serializable id) throws Exception;
	
	
	
	/**
	 * 
	 * @param object	vo have some properties
	 * @param fields	the fields is some of all the object's properties' names , which will put into the where HQL statement
	 * @return			read the results in the database , which have the same value as the object have
	 * @throws Exception
	 */
	<E extends Object> List<E> read(E object, Set<String> keys) throws Exception ;
	
	
	
	/**
	 * 
	 * @param object
	 * @return   return the serializable id
	 * @throws Exception
	 */
	<E> Serializable create(E object) throws Exception;
	
	
	
	<E> boolean modify(E object) throws Exception;
	
	<E> boolean delete(E object) throws Exception;
	
}
