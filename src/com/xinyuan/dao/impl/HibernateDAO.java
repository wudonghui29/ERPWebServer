package com.xinyuan.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.global.init.HibernateUtil;


public class HibernateDAO {
	/**
	 * 保存实体的对象
	 * @param obj 被保存的实体对象
	 * @return 返回保存是否成功，true代表是。
	 * */
	public Serializable saveObject(Object obj){
		return HibernateUtil.getSessionFactory().getCurrentSession().save(obj);
	}
	
	/**
	 * 修改实体的对象
	 * @param obj 被修改的实体对象
	 * @return 修改是否成功，true代表是.
	  */
	public void updateObject(Object obj){
		HibernateUtil.getSessionFactory().getCurrentSession().update(obj);
	}
	
	/**
	 * 使用HQL语句来得到实体对象的列表
	 * @param hsql 需要执行的HQL语句
	 * @return 附和条件的实体对象的列表
	 */
	public List getObjects(String hsql){
		List result = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(hsql).list();
		return result;
	}
	
	/**使用HQL来得到对象或者其他类型对象的实例
	 * @param hsql 需要执行的HQL语句
	 * @return HQL语句的执行结果
	 */
	public Object getObject(String hqsl) {
		return HibernateUtil.getSessionFactory().getCurrentSession().createQuery(hqsl).uniqueResult();
	}
	
	/**
	 *通过ID值来得到某种类型的实体对象
	 *@param cls 实体对象的类型
	 *@param id 实体对象的标识符
	 *@return 标识符相匹配的实体对象
	 */
	public Object getObject(Class cls,	Serializable id){
		Object result = HibernateUtil.getSessionFactory().getCurrentSession().get(cls, id);
		return result;
	}
	
	/**
	 * 删除实体对象
	 * @param id 通过ID要删除的实体对象
	 */
	public void deleteObject(Object obj){
		HibernateUtil.getSessionFactory().getCurrentSession().delete(obj);
	}
	
	/**
	 * 得到当前线程的Session对象的实例
	 * @return 当前线程相关联的Session对象的实例
	 */
	 protected Session getSession(){
		 return HibernateUtil.getSessionFactory().getCurrentSession();
	 }

}
