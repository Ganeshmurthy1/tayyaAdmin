package com.lintas.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CrmDetailsDao;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.model.PassengerProfileReport;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class PassengerProfileAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PassengerProfileAction.class);
	SessionMap<String, Object> sessionMap;
	PassengerProfileReport hotel_orderrow = new PassengerProfileReport();
	private List<OrderCustomer> reportData_list;
	private List<FlightOrderCustomer> FlyerreportData_list;
	CrmDetailsDao crmdao = new CrmDetailsDao();
	private String firstName;
	private String lastName;
	private String frequent_flyer_number;
	private String frequent_flyer_airline;
	private String passfirstName;
	private String passlastName;
	private String passemail;
	private String passmobile;
	private String emergencyfirstName;
	private String emergencylastName;
	private String emergencyemail;
	private String example_length;
	private String pageId;




	private String result = "success";

	private Map<String,String> jsonobj  =  new HashMap<String, String>();

	/*method for get superUser hotel reports	*/
	public String  getPassengerProfileList(){
		try {
			User user = (User)sessionMap.get("User");
			if(user.getUserrole_id().isSuperuser())
				reportData_list = crmdao.GetAllPassengerDetails();
			else
				reportData_list = crmdao.GetPassengerDetails(user);
			

			HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());
			 
			/*BrowsingOptionPageEnum i=BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE;*/
			/*logger.info(getPageId());
			logger.info(historyInfo.getPage().getId());
			logger.info(BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId());*/
			if(Integer.valueOf(getPageId())==BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId()) 
				historyInfo.changeNature(BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
			
			if(Integer.valueOf(getPageId())==BrowsingOptionPageEnum.CRM_EMERGENCY_CONTACT_DETAILS.getId())
				historyInfo.changeNature(BrowsingOptionPageEnum.CRM_EMERGENCY_CONTACT_DETAILS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
			new HistoryManager().inSertHistory(historyInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.error("---------Exception............."+e.getMessage());
		}


		return SUCCESS;

	}

	public String  getFilteredPassengerProfileList(){
		User user = (User)sessionMap.get("User");
		try {

			
			if(user.getUserrole_id().isSuperuser()){
				reportData_list = crmdao.GetFilteredAllPassengerDetails(getPassfirstName(),getPasslastName(),getPassemail(),getPassmobile());
				 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"CRM passenger profile list filter submit  click ");
					
			}
			
				else
				{
				
					reportData_list = crmdao.GettFilteredPassengerDetails(user,getPassfirstName(),getPasslastName(),getPassemail(),getPassmobile());
					HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"CRM passenger profile list filter submit  click ");
				}
			//sessionMap.put("ordercustomerlist",reportData_list);



		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.error("---------Exception............."+e.getMessage());
		}
		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_PASSENGER_PROFILE.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"CRM passenger profile list filter submit  click ");
			
		return SUCCESS;

	}

	public String  getLimitedPassengerProfileList(){
		try {
			logger.info("getLimitedPassengerProfileList" );
			sessionMap.remove("ordercustomerlist");
			User user = (User)sessionMap.get("User");
			if(user.getUserrole_id().isSuperuser())
				reportData_list = crmdao.GetLimitedAllPassengerDetails();
			else
				reportData_list = crmdao.GettFilteredPassengerDetails(user,getPassfirstName(),getPasslastName(),getPassemail(),getPassmobile());

			//sessionMap.put("ordercustomerlist",reportData_list);
			jsonobj.put("result",result);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.error("---------Exception............."+e.getMessage());
		}

		return SUCCESS;

	}



	public String  getPassportdetailsList(){
		try {
			User user = (User)sessionMap.get("User");
			if(user.getUserrole_id().isSuperuser())
				reportData_list = crmdao.GetAllPassportDetailsList();
			else
				reportData_list = crmdao.GetPassengerDetails(user);

			//sessionMap.put("ordercustomerlist",reportData_list);



		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.error("---------Exception............."+e.getMessage());
		}

		return SUCCESS;

	}

	public String  getFrequentFlyerList(){
		try {

			User user = (User)sessionMap.get("User");
			if(user.getUserrole_id().isSuperuser())
				FlyerreportData_list = crmdao.GetSuperUserFlightFrequentFlyerList();
			else
				FlyerreportData_list = crmdao.GetFrequentFlyerDetailsList(user);

			//	sessionMap.put("frequentflyerlist",FlyerreportData_list);

			HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
			if(Integer.valueOf(getPageId())==BrowsingOptionPageEnum.CRM_FREQUENT_FLYER.getId())
				historyInfo.changeNature(BrowsingOptionPageEnum.CRM_FREQUENT_FLYER, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 

			if(Integer.valueOf(getPageId())==BrowsingOptionPageEnum.CRM_PHOTO_PASSPORT_VISA_DETAILS.getId()) 
				historyInfo.changeNature(BrowsingOptionPageEnum.CRM_PHOTO_PASSPORT_VISA_DETAILS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
			new HistoryManager().inSertHistory(historyInfo);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.error("---------Exception............."+e.getMessage());
		}
		 
		return SUCCESS;

	}
	/*public String  getPassportList(){
		try {

			User user = (User)sessionMap.get("User");
			if(user.getUserrole_id().isSuperuser())
				FlyerreportData_list = crmdao.GetSuperUserFlightFrequentFlyerList();
			else
				FlyerreportData_list = crmdao.GetFrequentFlyerDetailsList(user);

			//	sessionMap.put("frequentflyerlist",FlyerreportData_list);



		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.error("---------Exception............."+e.getMessage());
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());  
		historyInfo.changeNature(BrowsingOptionPageEnum.CRM_PHOTO_PASSPORT_VISA_DETAILS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;

	}
*/
	public String  getFliteredFrequentFlyerList(){
		User user = (User)sessionMap.get("User");
		try {
			logger.info("firstname" + getFirstName());

			
			if(user.getUserrole_id().isSuperuser()){
				
				FlyerreportData_list = crmdao.GetSuperUserFilteredFlightFrequentFlyerList(getFirstName(),getLastName(),getFrequent_flyer_number(),getFrequent_flyer_airline());
				 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_FREQUENT_FLYER.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"CRM Frequent Flyer Details filter submit  click ");
					
			}
				else{
				
					FlyerreportData_list = crmdao.GetFilteredFrequentFlyerDetailsList(user,getFirstName(),getLastName(),getFrequent_flyer_number(),getFrequent_flyer_airline());
					 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_FREQUENT_FLYER.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"CRM Frequent Flyer Details filter submit  click ");
						
				}
			//sessionMap.put("frequentflyerlist",FlyerreportData_list);



		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.error("---------Exception............."+e.getMessage());
		}
		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.CRM_FREQUENT_FLYER.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"CRM Frequent Flyer Details filter submit  click ");
			
		return SUCCESS;

	}


	/**
	 * @return the jsonResult
	 */
	public Map<String,String> getJsonResult() {
		return jsonobj;
	}

	/**
	 * @param jsonResult the jsonResult to set
	 */
	public void setJsonResult(Map<String,String> jsonResult) {
		this.jsonobj = jsonResult;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFrequent_flyer_number() {
		return frequent_flyer_number;
	}

	public void setFrequent_flyer_number(String frequent_flyer_number) {
		this.frequent_flyer_number = frequent_flyer_number;
	}

	public String getFrequent_flyer_airline() {
		return frequent_flyer_airline;
	}

	public void setFrequent_flyer_airline(String frequent_flyer_airline) {
		this.frequent_flyer_airline = frequent_flyer_airline;
	}

	public String getPassfirstName() {
		return passfirstName;
	}

	public void setPassfirstName(String passfirstName) {
		this.passfirstName = passfirstName;
	}

	public String getPasslastName() {
		return passlastName;
	}

	public void setPasslastName(String passlastName) {
		this.passlastName = passlastName;
	}

	public String getPassemail() {
		return passemail;
	}

	public void setPassemail(String passemail) {
		this.passemail = passemail;
	}

	public String getPassmobile() {
		return passmobile;
	}

	public void setPassmobile(String passmobile) {
		this.passmobile = passmobile;
	}

	public String getEmergencyfirstName() {
		return emergencyfirstName;
	}

	public void setEmergencyfirstName(String emergencyfirstName) {
		this.emergencyfirstName = emergencyfirstName;
	}

	public String getEmergencyemail() {
		return emergencyemail;
	}

	public void setEmergencyemail(String emergencyemail) {
		this.emergencyemail = emergencyemail;
	}

	public String getEmergencylastName() {
		return emergencylastName;
	}

	public void setEmergencylastName(String emergencylastName) {
		this.emergencylastName = emergencylastName;
	}

	public String getExample_length() {
		return example_length;
	}

	public void setExample_length(String example_length) {
		this.example_length = example_length;
	}

	public List<OrderCustomer> getReportData_list() {
		return reportData_list;
	}

	public void setReportData_list(List<OrderCustomer> reportData_list) {
		this.reportData_list = reportData_list;
	}

	public List<FlightOrderCustomer> getFlyerreportData_list() {
		return FlyerreportData_list;
	}

	public void setFlyerreportData_list(
			List<FlightOrderCustomer> flyerreportData_list) {
		FlyerreportData_list = flyerreportData_list;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}







}