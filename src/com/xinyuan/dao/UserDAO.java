package com.xinyuan.dao;

import java.util.List;

import com.xinyuan.model.User.User;

/**
 * Interface of user operation
 */
public interface UserDAO {

	boolean isSignup(String username); 

	public boolean createUser(User user) ;

	void modify(User user);

	void delete(User user);

	User getUser(String username);

	List getAllUsers();
	
	public String getUserApnsToken(String username) ;
}
