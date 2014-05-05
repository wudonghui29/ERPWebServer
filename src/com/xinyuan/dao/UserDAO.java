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

	User getUser(String username);

	public List<Object> getAllUsersPermissions() ;
	
	public String getUserApnsToken(String username) ;
	
	public void setUserApnsToken(String username, String apnsToke) ;
}
