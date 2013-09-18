package com.xinyuan.dao;

import java.util.List;

import com.xinyuan.model.User;

/**
 * Interface of user operation
 */
public interface UserDAO {

	/**
	 * check if this username had signed up
	 * @param username
	 * @return
	 */
	boolean isSignup(String username); 

	/**
	 * 
	 * @param user
	 */
	boolean saveUser(User user);
	
	/**
	 * modify the user profile
	 * @param user
	 */
	void modify(User user);

	/**
	 * delete the user
	 * @param user
	 */
	void delete(User user);

	/**
	 * get user by user name
	 * @param username
	 * @return
	 */
	User getUser(String username);

	/**
	 * 
	 * @param hql
	 * @return
	 */
	List getUsers();
}
