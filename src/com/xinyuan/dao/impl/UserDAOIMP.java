package com.xinyuan.dao.impl;

import java.util.List;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;

import com.xinyuan.dao.UserDAO;
import com.xinyuan.model.User.User;

public class UserDAOIMP extends HibernateDAO implements UserDAO {

	public boolean isSignup(String username) {
		return this.getUser(username) != null;
	}

	public void modify(User user) {
		super.updateObject(user);
	}

	public void delete(User user) {
	}

	public User getUser(String username) {
		String hql = "from User user Where user.username=:username";
		Query query = super.getSession().createQuery(hql);
		query.setParameter("username", username);
		return (User)query.uniqueResult();
	}
	

	public List getAllUsers() {
		String hqlString = 
							"select user.username , user.permissions, user.categories from User as user Where id > 0";
//							"from User as user Where id != 0"; 
		return super.getObjects(hqlString);
	}

	public boolean createUser(User user) {
		return this.isSignup(user.getUsername()) ? false : (Integer)super.saveObject(user) > 0;
	}
	
	
	public String getUserApnsToken(String username) {
		String hqlString = "select user.apnsToken from User as user Where user.username = '" + username + "'";
		return (String) super.getObject(hqlString);
	}

}
