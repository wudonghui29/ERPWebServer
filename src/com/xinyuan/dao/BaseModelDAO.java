package com.xinyuan.dao;

import com.xinyuan.model.BaseOrderModel;

public interface BaseModelDAO {
	
	/**
	 * 
	 * @param object  BaseOrderModel vo have orderNO.
	 * @return read the whole properties on database according to the object with id , be sure have unique result
	 * @throws Exception
	 */
	public <E extends BaseOrderModel> E read(E model) throws Exception ;

}
