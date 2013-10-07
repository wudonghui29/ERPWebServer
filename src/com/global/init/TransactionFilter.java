package com.global.init;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.modules.util.DLog;


public class TransactionFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Transaction transaction = null;	
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			chain.doFilter(request, response);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
				DLog.log("Transaction Roll Back");
			}
			e.printStackTrace();
//			throw e;
		}
		finally{
			
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
