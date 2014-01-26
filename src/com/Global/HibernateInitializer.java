package com.Global;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * 
 * Hibernate Initializer，For Hibernate's configuration and startup。
 * <p>
 * Create the <tt>Configuration</tt> , <tt>SessionFactory</tt> and <tt>ServiceRegistry</tt> instance .
 * <p>
 *
 */
public class HibernateInitializer 
{

    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static void initialize() {
    	try {
    		configureSessionFactory();
		} catch (Exception e) {
            e.printStackTrace();
		}
    }
    
    public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
    
    
    /**
     * http://stackoverflow.com/a/8830848/1749293
     * @throws HibernateException
     */
    private static void configureSessionFactory() throws HibernateException {
    	configuration = new Configuration();
    	configuration.configure();		// "/hibernate.cfg.xml"
    	serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
    	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

}

