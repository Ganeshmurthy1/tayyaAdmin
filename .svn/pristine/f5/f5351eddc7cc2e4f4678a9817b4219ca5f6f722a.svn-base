package com.lintas.session;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionInterceptor implements Interceptor {

	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SessionInterceptor.class);
	private static final long serialVersionUID = 1L;
	 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("SessionInterceptor destroy() is called...");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		logger.info("SessionInterceptor init() is called...");
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		ActionContext context = actionInvocation.getInvocationContext();
		Map<String, Object> sessionMap = context.getSession();
		//logger.info("retrived session..."+ sessionMap);		
        if(sessionMap == null || sessionMap.isEmpty()) {		
   		//logger.info("session expired...");
   		 return "expired";
   		}
       
        String actionResult = actionInvocation.invoke();
		return actionResult;
         
         
        
		
		
		
		
		
		
		
		
		/*ActionContext context = actionInvocation.getInvocationContext();
		SessionMap<String, Object> session = (SessionMap<String, Object>) context.getSession();
		logger.info( "before session size:...."+session.size());
	 session.clear();
	session.invalidate() ;
		 if(session == null || session.isEmpty()) {	
			 logger.info("session expired...");
			 logger.info("After  session size:...."+session.size());	
			 return "expired";
		}
		  
		 return  actionInvocation.invoke();*/
	}

}
