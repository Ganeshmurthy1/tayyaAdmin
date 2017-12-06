package com.lintas.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.isl.admin.filter.ApiProviderFilter;
import com.isl.admin.page.ApiProviderPage;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TripRequestFilterAtion  extends ActionSupport implements ModelDriven<ApiProviderPage>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApiProviderPage apiProviderPage=new ApiProviderPage();
	ApiProviderDao apiProviderDao=new ApiProviderDao();
	SessionMap<String, Object> sessionMap;
	public String apiProviderList(){
		ApiProviderFilter apiProviderFilter = apiProviderPage.getApiProviderFilter();
		apiProviderPage.setApiProviderFilter(apiProviderFilter);
		ApiProviderPage newApiProviderPage=apiProviderDao.fetchApiProviderList(apiProviderPage);
		if(newApiProviderPage!=null && newApiProviderPage.getApiProviderList()!=null){
			apiProviderPage=newApiProviderPage;
		}
		/*HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.tripR_APIPROVIDER_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);*/
		 return SUCCESS;
	}
	@Override
	public ApiProviderPage getModel() {
		// TODO Auto-generated method stub
		return apiProviderPage;
	}
	public ApiProviderPage getApiProviderPage() {
		if(apiProviderPage==null){
			apiProviderPage=new ApiProviderPage();
		}
		 return apiProviderPage;
	}
	public void setApiProviderPage(ApiProviderPage apiProviderPage) {
		this.apiProviderPage = apiProviderPage;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
 	
}
