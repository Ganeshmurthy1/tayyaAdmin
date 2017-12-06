package com.lintas.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CommonConfigFilter;
import com.isl.admin.page.CommonConfigPage;
import com.lintas.admin.DAO.CommonConfigDao;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class CommonConfigFilterAction extends ActionSupport implements ModelDriven<CommonConfigPage>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonConfigFilterAction.class);
	CommonConfigPage commonConfigPage=new CommonConfigPage();
	CommonConfigDao commonConfigDao=new CommonConfigDao();
	private Integer statusCode;
	SessionMap<String, Object> sessionMap=null;
	 private Integer actionId;
	 private Integer detailType;
	 
	

	public  String  fetchCommonConfigList(){
		User user=(User)sessionMap.get("User");
		 String actionMessage="";
		
		CommonConfigFilter commonConfigFilter = commonConfigPage.getCommonConfigFilter();
		commonConfigPage.setCommonConfigFilter(commonConfigFilter);
		CommonConfigPage newApiProviderPage=commonConfigDao.fetchList(commonConfigPage);
		if(newApiProviderPage!=null && newApiProviderPage.getCommonConfigList()!=null){
			 
			
			
		/*	commonConfigPage=newApiProviderPage;
			statusCode = ActionStatusEnum.SUCCESS.getCode();
		      actionId=BrowsingOptionActionEnum.ACTION_ADDNEW.getId();
		      detailType=BrowsingHistoryDetailTypeEnum.ADD_NEW.getId();
		      actionMessage="company configList";*/
		}
		else{
			
			commonConfigPage=newApiProviderPage;
			/*statusCode = ActionStatusEnum.FAILED.getCode();
		      actionId=BrowsingOptionActionEnum.ACTION_ADDNEW.getId();
		      detailType=BrowsingHistoryDetailTypeEnum.ADD_NEW.getId();
		      actionMessage="companyconfigList";*/
		}
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"company config list filter submit  click ");
			
		/*HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.ALL_COMPANY.getId(), actionId, ActionStatusEnum.SUCCESS.getCode(), detailType, String.valueOf(user.getCompanyid()),actionMessage);*/
		return SUCCESS;
	 }

	@Override
	public CommonConfigPage getModel() {
		// TODO Auto-generated method stub
		return commonConfigPage;
	}

	public CommonConfigPage getCommonConfigPage() {
		return commonConfigPage;
	}

	public void setCommonConfigPage(CommonConfigPage commonConfigPage) {
		this.commonConfigPage = commonConfigPage;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	
}
