package com.xinyuan.dao;

import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import com.xinyuan.model.BaseOrderModel;


public interface BaseDAO {
	
	<E extends BaseOrderModel> List<E> read(E object, Map<String, Object> fields) throws Exception;
	
	<E extends BaseOrderModel> E read(E object) throws Exception;

	<E> Integer create(E object) throws Exception;
	
	<E> boolean modify(E object) throws Exception;
	
	<E> boolean delete(E object) throws Exception;
	
	<E> boolean apply(E object) throws Exception;

}
