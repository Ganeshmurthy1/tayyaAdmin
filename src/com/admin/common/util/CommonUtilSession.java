package com.admin.common.util;

import org.apache.struts2.dispatcher.SessionMap;

public class CommonUtilSession {

	public static boolean checkEmulatedUser(SessionMap<String, Object> sessionMap) {
		if(sessionMap.get("emulateByUserId")!=null && !sessionMap.get("emulateByUserId").equals(""))
		{
			return true;	
		}
		return false;
	}
	public static int getEmulatedUserIdInt(SessionMap<String, Object> sessionMap) {
		if(sessionMap.get("emulateByUserId")!=null && !sessionMap.get("emulateByUserId").equals(""))
		{
			int userid = (int) sessionMap.get("emulateByUserId");
			return userid ;	
		}
		return 1;
	}
	public static String checkEmulatedUserIdString(SessionMap<String, Object> sessionMap) {
		if(sessionMap.get("emulateByUserId")!=null && !sessionMap.get("emulateByUserId").equals(""))
		{
			return (String) sessionMap.get("emulateByUserId");	
		}
		return "";
	}
}
