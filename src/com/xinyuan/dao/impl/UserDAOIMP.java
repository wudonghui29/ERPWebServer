package com.xinyuan.dao.impl;

import java.util.List;

import org.hibernate.NonUniqueResultException;

import com.xinyuan.dao.UserDAO;
import com.xinyuan.model.User;

public class UserDAOIMP extends BaseDAOIMP implements UserDAO {

	public boolean isSignup(String username) {
		return this.getUser(username) != null;
	}

	public void modify(User user) {
	}

	public void delete(User user) {
	}

	public User getUser(String username) {
		return (User)super.getObject("from User Where username= '" + username + "'");
	}

	public List getUsers() {
		String hqlString = //"select user.username , user.permissions from User as user Where id != 0";
							"from User as user Where id != 0"; 
		return super.getObjects(hqlString);
	}

	public boolean saveUser(User user) {
		return this.isSignup(user.getUsername()) ? false : super.saveObject(user);
	}

}
