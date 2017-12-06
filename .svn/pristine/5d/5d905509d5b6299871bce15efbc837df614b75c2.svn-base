package com.lintas.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CrmDetailsDao;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class ProfileAction  extends ActionSupport implements SessionAware{

	    /**
	 * 
	 */
	   private static final long serialVersionUID = 1L;
	   public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ProfileAction.class);
		SessionMap<String, Object> sessionMap;
		CrmDetailsDao crmdao = new CrmDetailsDao();
	    private String passfirstName;
	    private String passlastName;
	    private String passemail;
	    private String passmobile;
		List<OrderCustomer> reportData_list=null;
	    private String result = "success";
	    
	    private Map<String,String> jsonobj  =  new HashMap<String, String>();
	    
	    public String  getLimitedPassengerProfileList(){
			try {
				logger.info("getLimitedPassengerProfileList" );
				User user = (User)sessionMap.get("User");
				if(user.getUserrole_id().isSuperuser())
					reportData_list = crmdao.GetLimitedAllPassengerDetails();
				else
					reportData_list = crmdao.GettFilteredPassengerDetails(user,getPassfirstName(),getPasslastName(),getPassemail(),getPassmobile());
				
				sessionMap.put("ordercustomerlist",reportData_list);
				jsonobj.put("result",result);
			

			} catch (Exception e) {
				// TODO Auto-generated catch block
				//logger.error("---------Exception............."+e.getMessage());
			}

			return SUCCESS;

		}
		
	    @Override
		public void setSession(Map<String, Object> map) {
	    	sessionMap=(SessionMap<String, Object>) map;
			
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
}
