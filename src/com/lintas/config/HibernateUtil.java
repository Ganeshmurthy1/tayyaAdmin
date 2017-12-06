/**
@Author VimalSusaiRaj
20-Jul-2015 2015
HibernateUtil.java
 */
/**
 * 
 */
package com.lintas.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory = buildSessionFactory();
	   private static SessionFactory buildSessionFactory()
	   {
	      try
	      {
	         if (sessionFactory == null)
	         {
	            Configuration configuration = new Configuration().configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
	            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
	            serviceRegistryBuilder.applySettings(configuration.getProperties());
	            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
	            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	         }
	         return sessionFactory;
	      } catch (Throwable ex)
	      {
	    	  logger.error("--------------Initial SessionFactory creation failed.-----------------"+ex.getMessage());
	         throw new ExceptionInInitializerError(ex);
	      }
	   }
	 
	   public static SessionFactory getSessionFactory()
	   {
	      return sessionFactory;
	   }
	 
	   public static void shutdown()
	   {
	      getSessionFactory().close();
	   }
}
