package com.lintas.session;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class StatusCheckIntercepter implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(StatusCheckIntercepter.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		//logger.info("StatusCheckIntercepter destroy() is called...");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//logger.info("StatusCheckIntercepter init() is called...");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
        String className = invocation.getAction().getClass().getName();
        long startTime = System.currentTimeMillis();
        //logger.info("Before calling action name: " + className);
        String result = invocation.invoke();
 
        long endTime = System.currentTimeMillis();
 /*logger.info("After calling action: " + className
                + " Time taken: " + (endTime - startTime) + " ms"); */
 
        return result;
    }
	 
}
