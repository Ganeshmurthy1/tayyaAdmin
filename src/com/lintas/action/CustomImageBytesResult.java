package com.lintas.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;

public class CustomImageBytesResult {

	public void execute(ActionInvocation invocation) throws Exception {
		 
		//ImageAction action = (ImageAction) invocation.getAction();
	//	logger.info("action.getCustomContentType()" +action.getCustomContentType());
		HttpServletResponse response = ServletActionContext.getResponse();

		
		//response.setContentType(action.getCustomContentType());
		//response.getOutputStream().write(action.getCustomImageInBytes());
		//response.getOutputStream().flush();
		
	}
	
}
