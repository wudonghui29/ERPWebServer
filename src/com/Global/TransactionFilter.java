package com.Global;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.modules.Util.DLog;


public class TransactionFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("\n\n\n");			// TODO: Remove it in production!
		DLog.log("" + ((HttpServletRequest)request).getRequestURL());
		DLog.log("*************************** ----- Begin Transaction");
		
		Transaction transaction = null;	
		try {
			Session session = HibernateInitializer.getSessionFactory().getCurrentSession();	
			transaction = session.beginTransaction();
			chain.doFilter(request, response);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
				DLog.log("Transaction Rolled Back");
			}
			e.printStackTrace();
			// throw e;
		} finally{
			// Close the session or not?
			// Read : http://stackoverflow.com/questions/4040761/control-the-hibernate-sessionwhen-to-close-it-manually
		}
		
		
		
		DLog.log("*************************** ----- Done Transaction");
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
