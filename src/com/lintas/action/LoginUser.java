/**
@Author VimalSusaiRaj
20-Jul-2015 2015
LoginUser.java
 */
/**
 * 
 */
package com.lintas.action;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.MailStatusDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyBookingSystemType;
import com.lintas.admin.model.CompanyRole;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.Language;
import com.lintas.admin.model.MailStatus;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserRole;
import com.lintas.config.encryptions;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class LoginUser extends ActionSupport implements SessionAware,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;  
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(LoginUser.class);

	private String email;
	private String Password;
	private String company_userid="";
	static int count=0; 
	SessionMap<String, Object> sessionMap;
	private String forgetPasswordMail;
	MailStatus ms=new MailStatus();
	MailStatusDao mailDao=new MailStatusDao();
	UserDAO userDao = new UserDAO();
	CompanyDAO comdao = new CompanyDAO();

	CountryDao cDAO = new CountryDao();
	CompanyConfigDao ccDao = new CompanyConfigDao();
	private encryptions enc = new encryptions();
	private List<Country> CountryList ;
	private List<Language> LanguageList ;
	private String encryptedid;
	private String pemail="raham@gmail.com";
	private String pphone="465478678789";
	private String pname="raham";
	private boolean emulate=true;
	private int emulateToCompanyId;
	private HistoryInfo historyInfo = new HistoryInfo(BrowsingOptionPageEnum.LOGIN, BrowsingOptionActionEnum.ACTION_LOGIN, ActionStatusEnum.DEFAULT);
	private String actionMsg ="";



	/*this method for logging the user or company based on roles */
	public String authenticateEmulate() throws Exception
	{
		if(emulateToCompanyId>0)
		{
			Company emulateByCompany = (Company)sessionMap.get("Company");
			User emulateByUser=(User)sessionMap.get("User");
			sessionMap.invalidate();
			if(emulateByCompany!=null)
				sessionMap.put("emulateByCompany",emulateByCompany);
			if(emulateByUser!=null)
			{
				sessionMap.put("emulateByUser",emulateByUser);
				sessionMap.put("emulateByUserId",emulateByUser.getId());
			}
			Company company= comdao.getCompanyProfile(emulateToCompanyId);
			User user= userDao.userLogin(company.getEmail(), company.getPassword(),company.getCompany_userid());
			logger.info("company object-------------------"+company+"------user obj----"+user+"");
			logger.info("company object-------------------"+company+"------user obj----"+user+"");

			if(company==null  ||user==null)
			{
				addActionError(getText("global.authenticateerrorinvalidnameerror"));
				setActionMsg(getText("global.authenticateerrorinvalidnameerror"));
			}

			boolean emulateToLockStatus=false;
			if(company!=null  && user!=null && emulateByCompany!=null && emulateByUser!=null)
			{
				if((company.isStatus()&&user.isStatus()&&!user.isLocked()))
				{
					emulateToLockStatus=true;
				}
				else
				{
					emulateToLockStatus=false;
					addActionError(getText("global.loginfromibealredyaccountlockederror"));
					setActionMsg(getText("global.loginfromibealredyaccountlockederror"));
				}

			}
			if(!emulateToLockStatus && emulateByCompany!=null && emulateByUser!=null && emulateByCompany.isStatus()&&emulateByUser.isStatus()&&!emulateByUser.isLocked())
			{
				emulateToLockStatus=true;
				user = emulateByUser;
				company = emulateByCompany;
				sessionMap.invalidate();
			}

			if(emulateToLockStatus)
			{
				CountryList  = cDAO.getCountryList();
				LanguageList = cDAO.getLanguageList();
				//String Encryptedid = enc.encryptAES(company.getEmail()+"-"+company.getPassword()+"-"+company.getCompany_userid()+"-"+true+"-"+emulateByCompany.getCompanyid()+"-"+emulateByUser.getId());
				String Encryptedid = enc.encryptAES(company.getEmail()+"-!&"+company.getPassword()+"-!&"+company.getCompany_userid()+"-!&"+true+"-!&"+emulateByCompany.getCompanyid()+"-!&"+emulateByUser.getId());
				sessionMap.put("Company",company);
				sessionMap.put("User",user);
				sessionMap.put("Encryptedid",Encryptedid);
				logger.info("-----------COMPANY CREDENTIALS AND  USER CREDENTIALS MATCHED---------------------");

				historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());
				historyInfo.setUser(user);
				historyInfo.changeNature(BrowsingOptionPageEnum.HOME, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.DEFAULT);					
				new HistoryManager().inSertHistory(historyInfo);
			}
			else{
				addActionError(getText("global.loginfromibealredyaccountlockederror"));
				setActionMsg(getText("global.loginfromibealredyaccountlockederror"));
				historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());				
				historyInfo.changeNature(BrowsingOptionPageEnum.LOGIN, BrowsingOptionActionEnum.ACTION_LOGIN, ActionStatusEnum.FAILED);
				new HistoryManager().inSertHistory(historyInfo);
				return INPUT; 
			}
		}
		else{
			addActionError(getText("global.error.emulate.mising.companyid"));
			setActionMsg(getText("global.error.emulate.mising.companyid"));
			historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());				
			historyInfo.changeNature(BrowsingOptionPageEnum.LOGIN, BrowsingOptionActionEnum.ACTION_LOGIN, ActionStatusEnum.FAILED);
			new HistoryManager().inSertHistory(historyInfo);
			//addActionError("Invalid Email/Password/company id. Please try again.");                      
			return INPUT; 
		}
		return SUCCESS;
	} 

	/*this method for logging the user or company based on roles */
	public String authenticate() throws Exception
	{
		// invalidate Emulated User for Home Company Login
		
		invalidateEmulateSession(sessionMap);
		Company company = comdao.CompanyLogin(this.email, this.Password,this.company_userid);
		Date todayDate =new Date();
		if(company!=null && company.getCompanyRole()!=null && company.getCompanyRole().isCorporate() && company.getAgreementExpiryDate().before(todayDate))
		{
//			Company companyListCorporateExpire=comdao.getAllCorporatesBasedOnAgrementExpiresDateExact(this.company_userid);/*Added by basha */
			
			if(company.getAgreementExpiryDate().before(todayDate))
			{
				addActionError(getText("global.corporteAgreementTodayExpireMsg"));/*Added by basha */
				return INPUT;/*Added by basha */
			}
		}else{
			User userLogin= userDao.userLogin(this.email, this.Password,this.company_userid);
			logger.info("company object-------------------"+company+"------user obj----"+userLogin+"");
			UserRole role= null;
			if(userLogin!=null){
				role=userLogin.getUserrole_id();
			}

			if(company!=null && company.getPassword().equals(this.Password)){
				User user= userDao.userLogin(this.email, this.Password,company.getCompany_userid());
				if(user!=null && user.getPassword().equals(this.Password)){
					CountryList  = cDAO.getCountryList();
					LanguageList = cDAO.getLanguageList();
					if((company.isStatus()&&user.isStatus()&&!user.isLocked())){
						String Encryptedid = enc.encryptAES(this.email+"-!&"+this.Password+"-!&"+company.getCompany_userid()+"-!&"+false+"-!&"+"-!&");
						//					String Encryptedid = enc.encryptAES(this.email+"-"+this.Password+"-"+company.getCompany_userid());

						sessionMap.put("Company",company);
						sessionMap.put("User",user);
						sessionMap.put("Encryptedid",Encryptedid);
						logger.info("-----------COMPANY CREDENTIALS AND  USER CREDENTIALS MATCHED---------------------");

						historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());
						historyInfo.setUser(user);
						historyInfo.changeNature(BrowsingOptionPageEnum.HOME, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.DEFAULT);					
						new HistoryManager().inSertHistory(historyInfo);
					}
					else{

						historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());
						historyInfo.setUser(user);
						historyInfo.changeNature(BrowsingOptionPageEnum.LOGIN, BrowsingOptionActionEnum.ACTION_LOGIN, ActionStatusEnum.FAILED);
						new HistoryManager().inSertHistory(historyInfo);
						addActionError(getText("global.authenticateerror"));
						//addActionError("Your account temporarily disabled or locked.Please contact your company.");                       
						return INPUT; 
					}
				}
				else{
					addActionError(getText("global.authenticateerrorinvalidnameerror"));
					historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());				
					historyInfo.changeNature(BrowsingOptionPageEnum.LOGIN, BrowsingOptionActionEnum.ACTION_LOGIN, ActionStatusEnum.FAILED);
					new HistoryManager().inSertHistory(historyInfo);
					//addActionError("Invalid Email/Password/company id. Please try again.");                      
					return INPUT; 
				}
			}
			else if(userLogin!=null && !userLogin.getUserrole_id().isSuperuser() && !userLogin.getUserrole_id().isUsermode()){
				if(userLogin.isStatus() && !userLogin.isLocked()){

					CountryList  = cDAO.getCountryList();
					LanguageList = cDAO.getLanguageList();
					logger.info("ENTERING USER DASH BOARD...");
					Company companyInner=comdao.getCompanyProfile(userLogin.getCompanyid());
					//				String Encryptedid = enc.encryptAES(this.email+"-"+this.Password+"-"+companyInner.getCompany_userid());
					String Encryptedid = enc.encryptAES(this.email+"-!&"+this.Password+"-!&"+companyInner.getCompany_userid()+"-!&"+false+"-!&"+"-!&");
					sessionMap.put("Company",companyInner);
					sessionMap.put("User",userLogin);
					sessionMap.put("Encryptedid",Encryptedid);
					logger.info("-----------USER CREDENTIALS MATCHED---------------------");
				}
				else{
					addActionError(getText("global.authenticateerroraccountlocked"));
					//addActionError("Your account temporarily disabled or locked.Please contact your company.");                       
					return ERROR; 
				}
			}

			else if(role!=null&&!role.isSuperuser()){
				if(!userLogin.isLocked()){
					User noofAttempts=userDao.setUserAttemps(userLogin);
					if(!userLogin.getPassword().equals(this.Password)&& !userLogin.isLocked()){
						int loginAttempt =noofAttempts.getAttemt();
						logger.info("loginAttempt....."+loginAttempt);
						if(loginAttempt<3){
							loginAttempt++;
							noofAttempts.setId(userLogin.getId());
							noofAttempts.setAttemt(loginAttempt);
							userDao.setUserAttemps(noofAttempts);
							//addActionError(getText("global.authenticatewrongcredential"));
							addActionError("If u enter wrong credentials more than 3 times,Account will be locked"+"..."+loginAttempt+"time.");                       
							return INPUT;
						}
						else{

							User userLockedObj=userDao.setUserLock(userLogin.getId());
							if(userLockedObj.isLocked()){
								if(userLockedObj.getUserrole_id().isUsermode() && !userLockedObj.getUserrole_id().isSuperuser()){
									userDao.setCompanyLock(userLockedObj.getCompanyid());
									comdao.insertEmail(String.valueOf(userLockedObj.getId()), 0,Email.EMAIL_TYPE_BLOCKED_USER);
								}
								noofAttempts.setId(userLogin.getId());
								noofAttempts.setAttemt(0);
								userDao.setUserAttemps(noofAttempts);
								addActionError(getText("global.authenticateaccountlockederror"));
								//addActionError("Account locked.");
								return ERROR;
							}
						} 
					}
					else{
						noofAttempts.setId(userLogin.getId());
						noofAttempts.setAttemt(0);
						userDao.setUserAttemps(noofAttempts);
						addActionError(getText("global.authenticateinvalidaccountsuccess"));
						//addActionError("Invalid Email/Password/company id. Please try again");
						return ERROR;
					} 
				}
				else{
					addActionError(getText("global.authenticateaccountlockederror"));
					//addActionError("Already account locked.please contact company.");
					return ERROR;
				}
			}

			else{
				addActionError(getText("global.authenticateinvalidaccountsuccess"));
				//addActionError("Invalid Email/Password/company id. Please try again");
				return INPUT;
			}
		}
		/*Added by basha */
		
		return SUCCESS;
	} 



	private void invalidateEmulateSession(SessionMap<String, Object> sessionMap) {
		sessionMap.invalidate();
		sessionMap.put("CompanyByEmulate",null);
		sessionMap.put("UserByEmulate",null);
	}



	public String home() throws Exception
	{		
		CountryList  = cDAO.getCountryList();
		LanguageList = cDAO.getLanguageList();
		historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());	
		historyInfo.changeNature(BrowsingOptionPageEnum.HOME, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.DEFAULT);	
		new HistoryManager().inSertHistory(historyInfo);		
		return SUCCESS;
	} 


	/*this method for corporate logging the user or company based on roles */

	public String loginfromibe() throws Exception
	{

		String decrString = enc.decryptAES(encryptedid);
		/*		logger.info("decrString" +decrString);*/
		String[] parts ;		
		String timestamp;
		parts = decrString.split("-!&");
		this.email = parts[0];  		
		this.Password = parts[1]; 		
		this.company_userid = parts[2]; 		
		timestamp = parts[3]; 	

		Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());	
		long timelong = Long.parseLong(timestamp);
		Timestamp oldtimestamp = new Timestamp(timelong);
		long diff =DateConversion.compareTwoTimeStamps(currentTimestamp,oldtimestamp);	

		if(diff < 60)
		{

			Company company = comdao.CompanyLogin(this.email, this.Password,this.company_userid);
			User userLogin= userDao.userLogin(this.email, this.Password,this.company_userid);
			logger.info("company object-------------------"+company+"------user obj----"+userLogin+"");
			User userPwd= userDao.getUserPasswordForLock(this.email);
			UserRole role= null;
			CompanyRole companyrole= null;
			CompanyBookingSystemType bookingType=null;

			if(userPwd!=null){
				role=userPwd.getUserrole_id();
			}

			if(company!=null && company.getPassword().equals(this.Password)){
				User user= userDao.userLogin(this.email, this.Password,company.getCompany_userid());
				if(user!=null && user.getPassword().equals(this.Password)){

					CountryList  = cDAO.getCountryList();			
					LanguageList = cDAO.getLanguageList();				
					if((company.isStatus()&&user.isStatus()&&!user.isLocked())){
						role=user.getUserrole_id();
						companyrole=company.getCompanyRole();
						bookingType=company.getBookingSystemType();
						if(role.isSuperuser()){
							logger.info("ENTERING Company SUPER USER  DASH BOARD...");

						}
						else if(companyrole.isAgent() && bookingType.isIBE()){
							logger.info("ENTERING INTO AGENCY DASH BOARD...");
						}
						else if(companyrole.isDistributor() && bookingType.isIBE()){
							logger.info("ENTERING INTO IBE DIUSTRIBUTOR COMPANY DASH BOARD...");
						}
						else if(companyrole.isDistributor() && bookingType.isAPI()){
							logger.info("ENTERING INTO API DIUSTRIBUTOR COMPANY DASH BOARD...");
						}
						else{
							logger.info("ENTERING INTO USER DASH BOARD...");
						}
						sessionMap.put("Company",company);
						sessionMap.put("User",user);

						logger.info("-----------COMPANY CREDENTIALS AND  USER CREDENTIALS MATCHED---------------------");


					}
					else{
						addActionError(getText("global.loginfromibeerror"));
						//addActionError("Your account temporarily disabled or locked.Please contact your company.");                       
						return ERROR; 
					}
				}
				else{
					addActionError(getText("global.loginfromibeinvalidemailerror"));
					//addActionError("Invalid Email/Password/company id. Please try again");                      
					return ERROR; 
				}
			}
			else if(userLogin!=null && !userLogin.getUserrole_id().isSuperuser() && !userLogin.getUserrole_id().isUsermode()){
				if(userLogin.isStatus() && !userLogin.isLocked()){

					CountryList  = cDAO.getCountryList();				
					LanguageList = cDAO.getLanguageList();			

					sessionMap.put("User",userLogin);
					Company c=comdao.getCompanyProfile(userLogin.getCompanyid());
					sessionMap.put("Company",c);
					logger.info("-----------USER CREDENTIALS MATCHED---------------------");

				}
				else{
					addActionError(getText("global.loginfromibeaccountdisablederror"));
					//addActionError("Your account temporarily disabled or locked.Please contact your company.");                       
					return ERROR; 
				}
			}

			else if(role!=null&&!role.isSuperuser()){
				if(!userPwd.isLocked()){
					User noofAttempts=userDao.setUserAttemps(userPwd);
					if(!userPwd.getPassword().equals(this.Password)&& !userPwd.isLocked()){
						int loginAttempt =noofAttempts.getAttemt();
						logger.info("loginAttempt....."+loginAttempt);
						if(loginAttempt<3){
							loginAttempt++;
							noofAttempts.setId(userPwd.getId());
							noofAttempts.setAttemt(loginAttempt);
							userDao.setUserAttemps(noofAttempts);
							//addActionError(getText("global.loginfromibewrongcredential"));
							addActionError("If u enter wrong credentials more than 3 times,Account will be locked"+"..."+loginAttempt+"time.");                       
							return INPUT;
						}
						else{
							//boolean locked=userDao.setUserLock(userPwd.getId());
							User userLockedObj=userDao.setUserLock(userPwd.getId());
							//logger.info("userLockedObj----"+userLockedObj.isLocked());
							//logger.info("user mode----"+userLockedObj.getUserrole_id().isUsermode());
							if(userLockedObj.isLocked()){
								if(userLockedObj.getUserrole_id().isUsermode() && !userLockedObj.getUserrole_id().isSuperuser()){
									userDao.setCompanyLock(userLockedObj.getCompanyid());
									comdao.insertEmail(String.valueOf(userLockedObj.getId()), 0,Email.EMAIL_TYPE_BLOCKED_USER);
								}

								noofAttempts.setId(userPwd.getId());
								noofAttempts.setAttemt(0);
								userDao.setUserAttemps(noofAttempts);
								addActionError(getText("global.loginfromibeaccountlockederror"));
								//addActionError("Account locked.");
								return ERROR;
							}
						} 
					}
					else{
						noofAttempts.setId(userPwd.getId());
						noofAttempts.setAttemt(0);
						userDao.setUserAttemps(noofAttempts);
						addActionError(getText("global.loginfromibinvalidemail"));
						//addActionError("Invalid Email/Password/company id. Please try again.");

						return ERROR;
					} 
				}
				else{
					addActionError(getText("global.loginfromibealredyaccountlockederror"));
					//addActionError("Already account locked.please contact company.");
					return ERROR;
				}
			}

			else {
				addActionError(getText("global.loginfromibeinvalidemailsuccess"));
				//addActionError("Invalid Email/Password/company id. Please try again.");
				return INPUT;
			}
			return SUCCESS;
		}
		else
		{
			addActionError(getText("global.loginfromibesessionexpirederror"));
			//addActionError("Your Session Expired.");
			return ERROR;
		}
	} 

	//this  method for   send forgot password

	public String requestPassword()  
	{
		logger.info("-----------------------getForgetPasswordMail()-----------------------"+getForgetPasswordMail());
		int userId= userDao.getUserPassword(getForgetPasswordMail());
		logger.info("-----------userId-----------------------------------"+userId);
		if(userId>0){
			comdao.insertEmail(String.valueOf(userId), 0,Email.EMAIL_TYPE_USER_FORGOT_PWD_REGISTRATION);
			addActionMessage(getText("global.requestPassword"));

		}
		else{
			addActionMessage(getText("global.requestPasswordsuccess"));

		}
		return SUCCESS;
	}

	public String emailConfirmation() {
		return SUCCESS;	
	}

	@Override
	public void setSession(Map<String, Object> Smap) {
		this.sessionMap = (SessionMap<String, Object>) Smap;

	}

	/*END by raham*/

	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getCompany_userid() {
		return company_userid;
	}
	public void setCompany_userid(String company_userid) {
		this.company_userid = company_userid;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Country> getCountryList() {

		return CountryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.CountryList = countryList;
	}
	public List<Language> getLanguageList() {
		return LanguageList;
	}
	public void setLanguageList(List<Language> languageList) {
		this.LanguageList = languageList;
	}
	public String getForgetPasswordMail() {
		return forgetPasswordMail;
	}
	public void setForgetPasswordMail(String forgetPasswordMail) {
		this.forgetPasswordMail = forgetPasswordMail;
	}
	public String getEncryptedid() {
		return encryptedid;
	}
	public void setEncryptedid(String encryptedid) {
		this.encryptedid = encryptedid;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public String getPemail() {
		return pemail;
	}
	public void setPemail(String pemail) {
		this.pemail = pemail;
	}
	public String getPphone() {
		return pphone;
	}
	public void setPphone(String pphone) {
		this.pphone = pphone;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}



	public boolean isEmulate() {
		return emulate;
	}



	public void setEmulate(boolean emulate) {
		this.emulate = emulate;
	}



	public int getEmulateToCompanyId() {
		return emulateToCompanyId;
	}



	public void setEmulateToCompanyId(int emulateToCompanyId) {
		this.emulateToCompanyId = emulateToCompanyId;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}


}
