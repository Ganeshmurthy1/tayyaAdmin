package com.lintas.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CommonConfigDao;
import com.lintas.admin.model.CommonConfig;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;


public class CommonConfigAction  extends ActionSupport implements ModelDriven<CommonConfig>,SessionAware{
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonConfigAction.class);
	private static final long serialVersionUID = 1L;
	CommonConfig commonConfig=new CommonConfig();
	CommonConfigDao commonConfigDao=new CommonConfigDao();
	private List<String> toEmailList= new ArrayList<>();
	private List<String> ccEmailList= new ArrayList<>();
	private List<String> bccEmailList= new ArrayList<>();
	SessionMap<String,Object> sessionMap;

	public  String goCommonConfig(){
		User user=(User)sessionMap.get("User");
	/*	HistoryInfo historyInfo = (HistoryInfo) ((sesssionMap.get("history")!=null)?sesssionMap.get("history"):new HistoryInfo());	
		historyInfo.changeNature(BrowsingOptionPageEnum.ADD_NEWCOMPANY_CONFIG, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.DEFAULT);	
		new HistoryManager().inSertHistory(historyInfo);		*/
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_ADDNEW.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.ADD_NEW.getId(), String.valueOf(user.getCompanyid()),"Add CommonConfig ADDNEW click");
			return SUCCESS;	
		 

	}




	public  String save(){
		logger.info(commonConfig.toString());
		User user=(User) sessionMap.get("User");
		logger.info(user.getCompanyid());
		
		
		HistoryInfo historyInfo=null;
		commonConfig.setCreatedAt(new Date());
		if(commonConfigDao.save(commonConfig)){
			addActionMessage(getText("global.add"));
		  historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.ADD_NEWCOMPANY_CONFIG.getId(), BrowsingOptionActionEnum.ACTION_ADD.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.ADD.getId(), String.valueOf(user.getCompanyid()),"Add CommonConfig");
			return SUCCESS;	
		 
		}
		else{
			addActionError(getText("global.error"));
			  historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.ADD_NEWCOMPANY_CONFIG.getId(), BrowsingOptionActionEnum.ACTION_ADD.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.ADD.getId(), String.valueOf(user.getCompanyid()),"Add CommonConfig");
			return ERROR;	
		}

	}

	public  String edit(){

		CommonConfig commonConfigDetails=commonConfigDao.getCommonConfigDetails(commonConfig);
		User user=(User) sessionMap.get("User");
		logger.info(user.getCompanyid());
		if(commonConfigDetails!=null){
			commonConfig=commonConfigDetails;
		}
		else{
			addActionError(getText("global.error"));
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.COMPANY_EDIT.getId(), String.valueOf(user.getCompanyid()),"CommonConfig list edit click");
				
			return ERROR;	
		}
		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.COMPANY_EDIT.getId(), String.valueOf(user.getCompanyid()),"CommonConfig list edit click");
		return SUCCESS;
	}

	public  String update(){
		User user=(User) sessionMap.get("User");

		CommonConfig commonConfigNew=commonConfigDao.update(commonConfig);
		if(commonConfigNew!=null && commonConfigNew.getId()==commonConfig.getId()){
			addActionMessage(getText("global.update"));
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"update CommonConfig update click");
				
			return SUCCESS;
		}
		else{
			addActionError(getText("global.error"));
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"update CommonConfig update click");
				
			return ERROR;	
		}

	}

	public  String statusUpdate(){

		CommonConfig commonConfigDetails=commonConfigDao.getCommonConfigDetails(commonConfig);
		User user=(User) sessionMap.get("User");
		logger.info(user.getCompanyid());

		if(commonConfigDetails!=null){
			if(commonConfig.isActive()){
				commonConfigDetails.setActive(false);

			}
			else{
				commonConfigDetails.setActive(true); 
			}

		}
		CommonConfig commonConfigNew=commonConfigDao.activeStatus(commonConfigDetails);
		if(commonConfigNew!=null){
			if(commonConfigNew.isActive()){
				addActionMessage("Locked.");
				 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_LOCK.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.COMPANY_LOCK.getId(), String.valueOf(user.getCompanyid()),"CommonConfig list lock  click");
					
			}
			else{
				addActionMessage("Un Locked.");
				 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_UNLOCK.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.COMPANY_UNLOCK.getId(), String.valueOf(user.getCompanyid()),"CommonConfig list unlock  click");
					
			}

		}
		else{
			addActionError(getText("global.error"));
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_LOCK.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.COMPANY_LOCK.getId(), String.valueOf(user.getCompanyid()),"CommonConfig list lock failed click");
				

		}
		
		return SUCCESS;
	}


	public  String getCommonConfigEmailDetails(){
		User user=(User)sessionMap.get("User");
		CommonConfig commonConfigDetails=commonConfigDao.getCommonConfigDetails(commonConfig);
		StringTokenizer st = null;
		List<String> toEmails=new ArrayList<>();

		if(commonConfigDetails!=null){
			if(commonConfigDetails.getToEmails()!=null){
				if(commonConfigDetails.getToEmails().contains(",")){
					st =new StringTokenizer(commonConfigDetails.getToEmails(), ",");
					while (st.hasMoreTokens()) {  
						toEmailList.add(st.nextToken());
					} 
				} 
				else if(commonConfigDetails.getToEmails().contains(";")){
					st =new StringTokenizer(commonConfigDetails.getToEmails(), ";");
					while (st.hasMoreTokens()) {  
						toEmailList.add(st.nextToken());
					} 
				}
				else if(commonConfigDetails.getToEmails().contains(" ")){
					st =new StringTokenizer(commonConfigDetails.getToEmails(), " ");
					while (st.hasMoreTokens()) {  
						toEmailList.add(st.nextToken());
					} 
				}

				else{
					toEmailList.add(commonConfigDetails.getToEmails());
				}
			}

			if(commonConfigDetails.getCcEmails()!=null){
				if(commonConfigDetails.getCcEmails().contains(",")){
					st =new StringTokenizer(commonConfigDetails.getCcEmails(), ",");
					while (st.hasMoreTokens()) {  
						ccEmailList.add(st.nextToken());
					} 
				} 
				else if(commonConfigDetails.getCcEmails().contains(";")){
					st =new StringTokenizer(commonConfigDetails.getCcEmails(), ";");
					while (st.hasMoreTokens()) {  
						ccEmailList.add(st.nextToken());
					} 
				}
				else if(commonConfigDetails.getCcEmails().contains(" ")){
					st =new StringTokenizer(commonConfigDetails.getCcEmails(), " ");
					while (st.hasMoreTokens()) {  
						ccEmailList.add(st.nextToken());
					} 
				}

				else{
					ccEmailList.add(commonConfigDetails.getToEmails());
				}
			}	

			if(commonConfigDetails.getCcEmails()!=null){
				if(commonConfigDetails.getCcEmails().contains(",")){
					st =new StringTokenizer(commonConfigDetails.getCcEmails(), ",");
					while (st.hasMoreTokens()) { 
						logger.info("st.nextToken()---------"+st.nextToken());

						bccEmailList.add(st.nextToken());
					} 
				} 
				else if(commonConfigDetails.getCcEmails().contains(";")){
					st =new StringTokenizer(commonConfigDetails.getCcEmails(), ";");
					while (st.hasMoreTokens()) {  
						bccEmailList.add(st.nextToken());
					} 
				}
				else if(commonConfigDetails.getCcEmails().contains(" ")){
					st =new StringTokenizer(commonConfigDetails.getCcEmails(), " ");
					while (st.hasMoreTokens()) {  
						bccEmailList.add(st.nextToken());
					} 
				}

				else{
					bccEmailList.add(commonConfigDetails.getToEmails());
				}
			}	
		}		

		logger.info(bccEmailList);
		
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_COMMON_CONFIG_LIST.getId(), BrowsingOptionActionEnum.ACTION_EMAIL.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.EMAIL.getId(), String.valueOf(user.getCompanyid()),"company common config list email  click ");
			
		return SUCCESS;

	}

	public List<String> getToEmailList() {
		return toEmailList;
	}

	public void setToEmailList(List<String> toEmailList) {
		this.toEmailList = toEmailList;
	}

	@Override
	public CommonConfig getModel() {
		// TODO Auto-generated method stub
		return commonConfig;
	}

	public CommonConfig getCommonConfig() {
		return commonConfig;
	}

	public void setCommonConfig(CommonConfig commonConfig) {
		this.commonConfig = commonConfig;
	}

	public List<String> getCcEmailList() {
		return ccEmailList;
	}

	public void setCcEmailList(List<String> ccEmailList) {
		this.ccEmailList = ccEmailList;
	}

	public List<String> getBccEmailList() {
		return bccEmailList;
	}

	public void setBccEmailList(List<String> bccEmailList) {
		this.bccEmailList = bccEmailList;
	}




	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}




}
