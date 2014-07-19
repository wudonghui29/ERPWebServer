package com.Global;

import j2se.modules.Helper.DLog;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;



public class TransactionFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		PrintHelper.printServeletRequestHeader();
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		DLog.println("\n\n\n");
		DLog.log("(" + new Date() + ")"  + "  " + request.getRemoteAddr() + ":" + request.getRemotePort() + " -> " + httpServletRequest.getRequestURL() );
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
