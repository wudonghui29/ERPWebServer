package com.xinyuan.action.command.category;

import j2se.modules.Introspector.IntrospectHelper;
import j2se.modules.Introspector.ObjectIntrospector;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import com.xinyuan.action.command.CommandApply;
import com.xinyuan.dao.SuperDAO;
import com.xinyuan.dao.impl.SuperDAOIMP;
import com.xinyuan.model.Articles.ArticlesCHOrder;
import com.xinyuan.model.Articles.ArticlesOrder;


public class ArticlesCommandApply extends CommandApply {
	protected void handleFinalApprovalProcess(SuperDAO dao, Object persistence) throws Exception {
		super.handleFinalApprovalProcess(dao, persistence);
		
		SuperDAOIMP daoImp = (SuperDAOIMP)dao;
		
		if (persistence instanceof ArticlesCHOrder) {
			ArticlesCHOrder articlesCHOrder = (ArticlesCHOrder)persistence;
			ArticlesOrder articlesOrder =  (ArticlesOrder) daoImp.getObject(ArticlesOrder.class, "id", articlesCHOrder.getId());
			if (articlesOrder == null) {
				
				articlesOrder = new ArticlesOrder();
				articlesOrder.setArticles(articlesCHOrder.getArticles_N());
				articlesOrder.setTitle(articlesCHOrder.getTitle_N());
				articlesOrder.setId(articlesCHOrder.getId());
				dao.create(articlesOrder); 
				
		   }else {
			  for (PropertyDescriptor pd : Introspector.getBeanInfo(articlesCHOrder.getClass()).getPropertyDescriptors()) {
					if (pd.getReadMethod() != null && !IntrospectHelper.isClassPropertyName(pd.getName())) {
						String propertyNameNew = pd.getName() ;
						if (propertyNameNew.indexOf("_N") >=0 ) 
						{
							String propertyNames[] = propertyNameNew.split("_");
							String originPropertyName = propertyNames[0];
							
							Object value = ObjectIntrospector.getProperty(articlesCHOrder, propertyNameNew);
							
							if (value == null) continue; 		// ... null
							ObjectIntrospector.setProperty(articlesOrder, originPropertyName, value);
							
						}
					}
			 }
			 dao.modify(articlesOrder);
		}
	  }
   }
}
