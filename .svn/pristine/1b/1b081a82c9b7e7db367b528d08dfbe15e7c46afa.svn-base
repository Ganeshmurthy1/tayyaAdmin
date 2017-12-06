package com.tayyarah.browsingHistory.json.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.browsingHistory.model.BrowsingHistory;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class BrowsingHistoryJson extends ActionSupport implements SessionAware {
	/**
	 * 
	 */public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BrowsingHistoryJson.class);

	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	BrowsingHistory browsingHistory = new BrowsingHistory();	
	String pageHistoryInfo;
	public String getPageHistoryInfo() {
		return pageHistoryInfo;
	}

	public void setPageHistoryInfo(String pageHistoryInfo) {
		this.pageHistoryInfo = pageHistoryInfo;
	}

	public String insertPageBrowsingHistoryLogin() {
		logger.info("login--------------" + pageHistoryInfo);
		try {
			JSONObject json = new JSONObject(pageHistoryInfo);
			JSONObject geoIp = json.getJSONObject("geoIp");			
			HistoryInfo historyInfo = new HistoryInfo();
			historyInfo.setGeoIP(geoIp);			
			sessionMap.put("history",historyInfo); 		

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.info("JSONExceptionp-----------------"+e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap<String, Object>) arg0;
	}


}