package com.lintas.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.api.provider.model.ApiProviderTravelType;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class ApiProviderAction extends ActionSupport implements ModelDriven<ApiProvider>,SessionAware{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ApiProviderAction.class);
	ApiProvider apiProvider =new ApiProvider();
	private ApiProvider apiProviderNew =new ApiProvider();
	ApiProviderDao apiProviderDao=new ApiProviderDao();
	SessionMap<String, Object> sessionMap;
	private List<String> travelTypeList = new ArrayList<String>();
	private  List<ApiProvider> apiProviderList ;
	public String saveApiProviderDetails(){
		ApiProviderTravelType  apiProviderTravelType=new ApiProviderTravelType();
		StringBuilder stringBuilder=new StringBuilder();
		if(getTravelTypeList()!=null && getTravelTypeList().size()>0){
			for (int i = 0; i < getTravelTypeList().size(); i++) {
				String travelType= (String)getTravelTypeList().get(i);
				if(i == getTravelTypeList().size()-1)
					stringBuilder.append(""+travelType+"");
				else
					stringBuilder.append(""+travelType+","); 
			}
		}
		apiProvider.setTravelType(stringBuilder.toString());

		if(getTravelTypeList()!=null && getTravelTypeList().size()>0){

			if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("hotel")&&getTravelTypeList().contains("bus")&&getTravelTypeList().contains("car")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setHotel(true);
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setCar(true);
			}
			else if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("hotel")&&getTravelTypeList().contains("bus")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setHotel(true);
				apiProviderTravelType.setBus(true);
			}
			else if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("hotel")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setHotel(true); 
			}

			else if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("bus")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setBus(true);
			}

			else if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("car")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setCar(true); 
			}
			else if(getTravelTypeList().contains("hotel")&&getTravelTypeList().contains("bus") && getTravelTypeList().contains("car")){
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setHotel(true); 
				apiProviderTravelType.setCar(true);
			}
			else if(getTravelTypeList().contains("hotel") && getTravelTypeList().contains("car")){
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setHotel(true); 
			}
			else if(getTravelTypeList().contains("car") && getTravelTypeList().contains("bus")){
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setHotel(true); 
			}
			else if(getTravelTypeList().contains("hotel") && getTravelTypeList().contains("bus")){
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setHotel(true); 
			}
			else if(getTravelTypeList().contains("flight")){
				apiProviderTravelType.setFlight(true);

			}
			else if(getTravelTypeList().contains("hotel")){
				apiProviderTravelType.setHotel(true);

			}
			else if(getTravelTypeList().contains("bus")){
				apiProviderTravelType.setBus(true);
			}
			else if(getTravelTypeList().contains("car")){
				apiProviderTravelType.setCar(true);

			}
		}
		apiProvider.setApiTravelType(apiProviderTravelType);
		ApiProvider apiProviderNew= apiProviderDao.insert(apiProvider); 
		boolean isUpdated=false;
		 if(apiProviderNew!=null && apiProviderNew.getId()>0){
			int  id=100+apiProviderNew.getId();
			String apiProviderId="V"+String.valueOf(id);
			isUpdated=apiProviderDao.updateApiProviderDetails(apiProviderNew.getId(), apiProviderId);
		 }
		 
		 else if(isUpdated){
			addActionMessage("Successfully Added.");
		}
		else{
			//addActionError("Supplier already existed.");
			 addActionError("We found some error.Please try again.");
			return ERROR;
		}
		  return SUCCESS;
		
	}

	public String apiProviderEdit(){
		User user=(User)sessionMap.get("User");
		apiProviderNew=apiProviderDao.getApiProviderDetails(apiProvider.getId());	
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_APIPROVIDER_LIST.getId(), BrowsingOptionActionEnum.ACTION_DETAILS.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.DETAIL.getId(), String.valueOf(user.getCompanyid()),"Api provider list edit click ");
		return SUCCESS;
	}
	
	
	public String addApiProvider(){
		User user=(User)sessionMap.get("User");
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_ADD_APIPROVIDER, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		  historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_ADD_APIPROVIDER.getId(), BrowsingOptionActionEnum.ACTION_ADD.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.ADD.getId(), String.valueOf(user.getCompanyid()),"add api provider add  click ");
			
		return SUCCESS;
	}
	
	

	public String apiProviderUpdate(){
		User user=(User)sessionMap.get("User");
		ApiProviderTravelType  apiProviderTravelType=new ApiProviderTravelType();

		StringBuilder stringBuilder=new StringBuilder();
		if(getTravelTypeList()!=null && getTravelTypeList().size()>0){
			for (int i = 0; i < getTravelTypeList().size(); i++) {
				String travelType= (String)getTravelTypeList().get(i);
				if(i == getTravelTypeList().size()-1)
					stringBuilder.append(""+travelType+"");
				else
					stringBuilder.append(""+travelType+","); 
			}
		}
		apiProvider.setTravelType(stringBuilder.toString());

		if(getTravelTypeList()!=null && getTravelTypeList().size()>0){

			if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("hotel")&&getTravelTypeList().contains("bus")&&getTravelTypeList().contains("car")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setHotel(true);
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setCar(true);
			}
			else if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("hotel")&&getTravelTypeList().contains("bus")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setHotel(true);
				apiProviderTravelType.setBus(true);
			}
			else if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("hotel")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setHotel(true); 
			}

			else if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("bus")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setBus(true);
			}

			else if(getTravelTypeList().contains("flight")&&getTravelTypeList().contains("car")){
				apiProviderTravelType.setFlight(true);
				apiProviderTravelType.setCar(true); 
			}
			else if(getTravelTypeList().contains("hotel")&&getTravelTypeList().contains("bus") && getTravelTypeList().contains("car")){
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setHotel(true); 
				apiProviderTravelType.setCar(true);
			}
			else if(getTravelTypeList().contains("hotel") && getTravelTypeList().contains("car")){
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setHotel(true); 
			}
			else if(getTravelTypeList().contains("car") && getTravelTypeList().contains("bus")){
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setHotel(true); 
			}
			else if(getTravelTypeList().contains("hotel") && getTravelTypeList().contains("bus")){
				apiProviderTravelType.setBus(true);
				apiProviderTravelType.setHotel(true); 
			}
			else if(getTravelTypeList().contains("flight")){
				apiProviderTravelType.setFlight(true);

			}
			else if(getTravelTypeList().contains("hotel")){
				apiProviderTravelType.setHotel(true);

			}
			else if(getTravelTypeList().contains("bus")){
				apiProviderTravelType.setBus(true);
			}
			else if(getTravelTypeList().contains("car")){
				apiProviderTravelType.setCar(true);

			}
		}
		apiProviderTravelType.setTravelTypeId(apiProvider.getApiTravelType().getTravelTypeId());
		apiProvider.setApiTravelType(apiProviderTravelType);
		if(apiProvider.getApiProvider().equals("")){
			int  id=100+apiProvider.getId();
			String apiProviderId="V"+String.valueOf(id);
			apiProvider.setApiProvider(apiProviderId);
		}
		ApiProvider apiProviderNew=apiProviderDao.updateApiProviderDetails(apiProvider);
		if(apiProviderNew!=null &&(apiProviderNew.getId()==apiProvider.getId())) {
			addActionMessage("Successfully updated.");

			HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_APIPROVIDER_LIST.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"Update ApiProvider Details update click ");
			
		}
		else{
			addActionError("We found some error.Please try again.");

			HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_APIPROVIDER_LIST.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"Update ApiProvider Details update click ");
			
		}
		//HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.MASTER_CONFIGURATIONS_APIPROVIDER_LIST.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATE.getId(), String.valueOf(user.getCompanyid()),"Update ApiProvider Details update click ");
		
		return SUCCESS;
	}





	@Override
	public ApiProvider getModel() {
		// TODO Auto-generated method stub
		return apiProvider;
	}
	public List<ApiProvider> getApiProviderList() {
		return apiProviderList;
	}
	public void setApiProviderList(List<ApiProvider> apiProviderList) {
		this.apiProviderList = apiProviderList;
	}

	public List<String> getTravelTypeList() {
		return travelTypeList;
	}
	public void setTravelTypeList(List<String> travelTypeList) {
		this.travelTypeList = travelTypeList;
	}
	public ApiProvider getApiProviderNew() {
		return apiProviderNew;
	}
	public void setApiProviderNew(ApiProvider apiProviderNew) {
		this.apiProviderNew = apiProviderNew;
	}

	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
		
	
}


