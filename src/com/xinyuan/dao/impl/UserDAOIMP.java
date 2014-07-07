package com.xinyuan.dao.impl;

import java.util.List;

import com.Global.HibernateAbstractDAO;
import com.xinyuan.dao.UserDAO;
import com.xinyuan.model.User.User;

public class UserDAOIMP extends HibernateAbstractDAO implements UserDAO {

	public boolean isSignup(String username) {
		return this.getUser(username) != null;
	}

	public void modify(User user) {
		super.updateObject(user);
	}

	public User getUser(String username) {
		return (User)super.getObject(User.class, "username", username);
	}
	
	public boolean createUser(User user) {
		return this.isSignup(user.getUsername()) ? false : (Integer)super.saveObject(user) > 0;
	}
	

	public List<Object> getAllUsersPermissions() {
		String queryString = "select user.username , user.permissions, user.categories from User as user Where id != 0";
		return super.getObjects(queryString);
	}

	
	
	
	
	public String getUserApnsToken(String username) {
		String queryString = "SELECT user.apnsToken from User as user WHERE user.username = '" + username + "'";
		return (String) super.getObject(queryString);
	}
	
	
	public void setUserApnsToken(String username, String apnsToken) {
		String queryString = "UPDATE User as user SET user.apnsToken = '" + apnsToken + "'" + " WHERE user.username = '" + username + "'";
		super.createQuery(queryString).executeUpdate();
	}

}
