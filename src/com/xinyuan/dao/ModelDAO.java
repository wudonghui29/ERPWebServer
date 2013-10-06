package com.xinyuan.dao;


public interface ModelDAO {
	
	/**
	 * 
	 * @param object  BaseOrderModel vo have orderNO or id.
	 * @return read the whole properties on database according to the object with id , be sure have unique result
	 * @throws Exception
	 */
	public <E extends Object> E read(E model) throws Exception ;

}
