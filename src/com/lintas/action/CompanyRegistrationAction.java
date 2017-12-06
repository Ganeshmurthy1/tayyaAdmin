/**
0@Author VimalSusaiRaj
21-Jul-2015 2015
CompanyregistrationAction.java
 */
/**
 * 
 */
package com.lintas.action;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.util.StateInfo;
import com.hotel.cancellation.HttpClient;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.MailStatusDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyBookingSystemType;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.admin.model.CompanyRole;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.Language;
import com.lintas.admin.model.MailStatus;
import com.lintas.admin.model.SalesLeadGeneration;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserRole;
import com.lintas.admin.model.UserWallet;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.config.encryptions;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;
public class CompanyRegistrationAction extends ActionSupport implements ModelDriven<Company>,SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyRegistrationAction.class);
	Company Comreg = new Company();
	CompanyDAO ComregDAO = new CompanyDAO();
	UserDAO userDAO = new UserDAO();
	SessionMap<String, Object> sessionmap ;
	MailStatus ms=new MailStatus();
	MailStatusDao mailDao=new MailStatusDao();
	//private String mailStatus="";
	private Company CurrentCompany;
	private List<Company> Allcompanylist;
	private List<Company> AllApprovalcompanylist;
	private InputStream inputStream;
	User user = new User();
	private String companyType;
	private String termsAndConditions;
	private String distributorType;
	private String typeOfWallet="Prepaid"; 
	private String  postAmount="0.00"; 
	//private String currency;
	String imagename  = "";
	CountryDao cDAO = new CountryDao();
	private List<Country> CountryList ;
	private List<StateInfo> StateList ;
	private List<Language> LanguageList ;
	private List<User> walletCompaniesList;
	private encryptions enc = new encryptions();
	private String Registerid = "";
	private String approvalType;
	private String salesPersonUserId;
	private User companyUserProfile;
	private Integer statusCode;
	private Integer pageId;
	private Integer actionId;
	private Integer detailType;
	private String email;
	private int companyid;
	private List<User>  salesPersonRecords=new ArrayList<User>();
	private List<CompanyEntity> companyEntityList;
	//private Company  superUserCompanyProfile;
	/*this method for register the company based on roles*/
	public String validatecheck(){
		User sessionUser=(User)sessionmap.get("User");
		Company  sessionCom=(Company)sessionmap.get("Company");
		String billingState=Comreg.getBillingstate();
		if(billingState!=null && billingState.contains(",")) 
			billingState=billingState.replace(",", "") ;
		else 
			billingState="Karnataka";
		Comreg.setBillingstate(billingState);
		try{
			boolean isCompanyExist= ComregDAO.ifAnyCompanyExist();
			UserWallet userWallet=new UserWallet();
			int salesPersonUserId;
			if(Comreg.getEmail()!=null || !Comreg.getEmail().equalsIgnoreCase(""))
			{
				if(!ComregDAO.CompanyExists(Comreg.getEmail()) && !userDAO.UserIdExists(Comreg.getEmail()))
				{ 
					Comreg.setCreateddate(new Date());
					Comreg.setModifieddate(new Date());
					logger.info("-------image path------"+Comreg.getImagepath());
					UserRole user_role =new UserRole();
					CompanyRole companyRole = new CompanyRole();
					SalesLeadGeneration salesLeadGeneration = new SalesLeadGeneration();
					CompanyBookingSystemType bookingSystemType =new CompanyBookingSystemType();
					logger.info("-isCompanyExist--------obj------"+isCompanyExist);
					if(!isCompanyExist){
						user_role.setSuperuser(true);
						Comreg.setStatus(true);
						Comreg.setLocked(false);
						companyRole.setSuperUser(true);
						companyRole.setAgent(false);
						companyRole.setDistributor(false);
						bookingSystemType.setAPI(false);
						bookingSystemType.setIBE(false);
						logger.info("-isCompanyExist--------obj------"+Comreg);
					}
					else{
						if(companyType.equalsIgnoreCase("agent")){
							companyRole.setAgent(true);
							bookingSystemType.setIBE(true);
							Comreg.setRmConfig(false);
							logger.info(".................companyType.............."+companyType);
						}
						else if(companyType.equalsIgnoreCase("corporate")){
							companyRole.setCorporate(true);
							bookingSystemType.setIBE(true);
							Comreg.setRmConfig(false);
							if(StringUtils.isNotBlank(Comreg.getAgreementExpiryDt()))
								Comreg.setAgreementExpiryDate(DateConversion.StringToDate(Comreg.getAgreementExpiryDt()));
							logger.info(".................companyType.............."+companyType);
						}
						else if(companyType.equalsIgnoreCase("distributor")){
							companyRole.setDistributor(true);
							logger.info(".................companyType.............."+companyType);
							if(distributorType.equalsIgnoreCase("ibe")){
								bookingSystemType.setIBE(true);
								Comreg.setRmConfig(false);
								logger.info(".................distributorType.............."+distributorType);
							}
							else if(distributorType.equalsIgnoreCase("api")){
								bookingSystemType.setAPI(true);
								Comreg.setRmConfig(false);
								logger.info(".................distributorType.............."+distributorType);
							}
						}
						user_role.setSuperuser(false);
						user_role.setCms(false);
						user_role.setUsermode(true);
						user_role.setDemoUser(Comreg.isDemoUser());
						Comreg.setStatus(false);
						Comreg.setLocked(false);
						logger.info("------Comreg.isMyEmailDir()----------"+Comreg.isMyEmailDir());
						Comreg.setCurrencyCode(sessionCom.getCurrencyCode());
						if(getSalesPersonUserId()==null || getSalesPersonUserId().equals("") ){
							salesPersonUserId=sessionUser.getId();
						}
						else{
							salesPersonUserId=Integer.parseInt(getSalesPersonUserId());
						}
						logger.info("salesPersonUserId---"+salesPersonUserId);
						salesLeadGeneration.setUser(userDAO.GetUserProfile(salesPersonUserId));
						salesLeadGeneration.setLeadType(Comreg.getSalesLeadGeneration().getLeadType());
						salesLeadGeneration.setCompanyRemarks(Comreg.getSalesLeadGeneration().getCompanyRemarks());
						salesLeadGeneration.setSalesPersonRemarks(Comreg.getSalesLeadGeneration().getSalesPersonRemarks());
						Comreg.setSalesLeadGeneration(salesLeadGeneration);
					}

					/*if(getSalesPersonUserId()==null || getSalesPersonUserId().equals("") ){
						salesPersonUserId=sessionUser.getId();
					}
					else{
						salesPersonUserId=Integer.parseInt(getSalesPersonUserId());
					}
					logger.info("salesPersonUserId---"+salesPersonUserId);
					salesLeadGeneration.setUser(userDAO.GetUserProfile(salesPersonUserId));
					salesLeadGeneration.setLeadType(Comreg.getSalesLeadGeneration().getLeadType());
					salesLeadGeneration.setCompanyRemarks(Comreg.getSalesLeadGeneration().getCompanyRemarks());
					salesLeadGeneration.setSalesPersonRemarks(Comreg.getSalesLeadGeneration().getSalesPersonRemarks());
					Comreg.setSalesLeadGeneration(salesLeadGeneration);*/
					//Comreg.getSalesLeadGeneration().setUser(userDAO.GetUserProfile(salesPersonUserId));
					Comreg.setCompanyRole(companyRole);
					Comreg.setBookingSystemType(bookingSystemType);
					Comreg.setModifieddate(new Date());
					logger.info("-companyNew--------obj------"+Comreg);
					Company savedcompanyObj = ComregDAO.registerCompany(Comreg, sessionCom);
					logger.info("savedcompanyObj.getImagepath()------"+savedcompanyObj.getImagepath());
					String company_userid=null;
					if(savedcompanyObj!=null){
						company_userid=savedcompanyObj.getCompanyname().substring(0,2).toUpperCase()+savedcompanyObj.getCompanyid(); 
						if(companyEntityList!=null && companyEntityList.size()>0){
							for(CompanyEntity companyEntity: companyEntityList){
								if(!companyEntity.getEmail().equalsIgnoreCase("")){
									String [] arr=companyEntity.getState().split("-");
									companyEntity.setState(arr[0]);
									companyEntity.setStateCode(arr[1]);
									companyEntity.setCompany(savedcompanyObj);
									ComregDAO.registerCompanyEntity(companyEntity);
								}
							}
						}
					}

					user.setCompany_userid(company_userid);
					user.setCompanyid(savedcompanyObj.getCompanyid());
					user.setUsername(Comreg.getUsername());
					user.setFirstname(Comreg.getUsername());
					user.setLastname(Comreg.getUsername());
					user.setPassword(Comreg.getPassword());
					user.setEmail(Comreg.getEmail());
					user.setPhone(Comreg.getPhone());
					//added by basha this country code
					user.setCountryCode(Comreg.getCountryCode());
					user.setDescription(Comreg.getCompanydescription());
					user.setCreateddate(Comreg.getCreateddate());
					user.setModifieddate(new Date());
					user.setAddress(Comreg.getAddress());
					user.setSecurityquestion(Comreg.getSecurityquestion());
					user.setSecurityanswer(Comreg.getSecurityanswer());
					user.setCity(Comreg.getCity()); 
					user.setLanguage(Comreg.getLanguage());
					user.setCountryname(savedcompanyObj.getCountryname());
					user.setStatus(savedcompanyObj.isStatus());
					user.setLocked(savedcompanyObj.isLocked());
					user.setUserrole_id(user_role);
					user.setCurrencyCode(Comreg.getCurrencyCode());
					if(getTypeOfWallet().equalsIgnoreCase("Postpaid")){
						userWallet.setWalletBalanceRange(new BigDecimal(getPostAmount()).negate());
					}
					else if(getTypeOfWallet().equalsIgnoreCase("Prepaid")) {
						if(getPostAmount().equalsIgnoreCase("")){
							userWallet.setWalletBalanceRange(new BigDecimal("0.00"));
						}
						else{
							userWallet.setWalletBalanceRange(new BigDecimal(getPostAmount()));	
						}
					} 
					logger.info("----getTypeOfWallet---"+getTypeOfWallet());
					logger.info("----negative amount---:"+userWallet.getWalletBalanceRange());
					userWallet.setWalletType(getTypeOfWallet());
					userWallet.setWalletbalance(new BigDecimal("0.00"));
					userWallet.setDepositBalance(new BigDecimal("0.00"));
					userWallet.setTransactionType("Credit");
					userWallet.setCurrencyCode(Comreg.getCurrencyCode());
					userWallet.setCreatedAt(new Timestamp(new Date().getTime()));
					user.setAgentWallet(userWallet);
					if(company_userid!=null && company_userid.length()>0){

						User userObj = userDAO.RegisterUser(user, sessionUser);
						logger.info(" -------------After register----userObj.getId--------"+userObj.getId());
						if(userObj != null && userObj.getId() > 0)
						{
							userObj.setImagepath(uploadImageFile(userObj.getId()));
							if(userObj.getUserrole_id().isSuperuser()){
								userObj.setCreatedbyCompanyUserId(userObj.getId());
								userObj.setModifiedbyCompanyUserId(userObj.getId());
							}
							User updatedImageObj=ComregDAO.updateUserImagePath(userObj);

							savedcompanyObj.setEmailCode(RandomConfigurationNumber.getEncryptedEmailCode(String.valueOf(savedcompanyObj.getCompanyid())));
							if(updatedImageObj!=null){
								savedcompanyObj.setImagepath(updatedImageObj.getImagepath());
							}
							savedcompanyObj.setCompany_userid(company_userid);
							Company companyNew=ComregDAO.updatCompanyImagepath(savedcompanyObj);

							if(companyNew!=null){
								logger.info("global.createDynamicFolderUrl "+getText("global.createDynamicFolderUrl"));
								if(companyNew.isMyEmailDir()){
									new HttpClient().sendPostParams(companyNew.getCompany_userid(), getText("global.createDynamicFolderUrl"));
								}

								ComregDAO.updateSalesPersonChildUserId(companyNew, userObj);
								//logger.info(("companyNew.getImagepath()-----------"+companyNew.getImagepath());
								ComregDAO.insertEmail(String.valueOf(userObj.getId()), 0, Email.EMAIL_TYPE_COMPANY_REGISTRATION); 
								addActionMessage(getText("global.validatechecksuccess"));
								//addActionMessage("successfully registered.please check mail.");
								statusCode = ActionStatusEnum.SUCCESS.getCode();								
								if(pageId == null)		
									pageId = BrowsingOptionPageEnum.ADD_COMPANY.getId();

								new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_REGISTER.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.REGISTER.getId(), String.valueOf(user.getCompanyid()),"marketng sales leads add company register click ");

								//// Insert Notification for company registeration 
								new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(companyNew.getCompanyid()),"Company Registered","Company Registered",NotificationInventoryTypeEnum.COMPANY.getId(),true,false,false,true,false,false);
								new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(companyNew.getCompanyid()),"Company Approval Pending","Company Approval Pending",NotificationInventoryTypeEnum.COMPANY_APPROVAL.getId(),true,false,false,true,false,false);


								return SUCCESS;
							} 
						}
					} 
				}
				else
				{
					CountryList  = cDAO.getCountryList();	
					LanguageList = cDAO.getLanguageList();
					addActionError(getText("global.validatecheckcompanyexist"));
					//addActionError("Company Or Email-Id already exist.");
					statusCode = ActionStatusEnum.FAILED.getCode();								
					if(pageId == null)		
						pageId = BrowsingOptionPageEnum.ADD_COMPANY.getId();
					return INPUT;
				}
			}
			else
			{
				CountryList  = cDAO.getCountryList();	
				LanguageList = cDAO.getLanguageList();
				addActionError(getText("global.validatechecknoinputdata"));
				//addActionError("No Input Data , Please check.");
				statusCode = ActionStatusEnum.FAILED.getCode();								
				if(pageId == null)		
					pageId = BrowsingOptionPageEnum.ADD_COMPANY.getId();

				return INPUT;
			}   
		}catch(Exception e){
			e.getStackTrace();
			addActionError(getText("global.validatecheckerror"));
			//addActionError("Ooops!, Please try again, We found some error.");
			logger.error("...................Exception................."+e.getMessage());
			statusCode = ActionStatusEnum.EXCEPTION.getCode();								
			if(pageId == null)		
				pageId = BrowsingOptionPageEnum.ADD_COMPANY.getId();

			HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_REGISTER.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.REGISTER.getId(), String.valueOf(user.getCompanyid()),"marketng sales leads add company register click ");

			return ERROR;
		} 
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());  
		statusCode = ActionStatusEnum.SUCCESS.getCode();								
		if(pageId == null)		
			pageId = BrowsingOptionPageEnum.ADD_COMPANY.getId();
		historyInfo.changeNature(BrowsingOptionPageEnum.getPageEnum(pageId), BrowsingOptionActionEnum.getActionEnum(actionId), ActionStatusEnum.getStatusEnum(statusCode)); 
		new HistoryManager().inSertHistory(historyInfo);

		historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_REGISTER.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.REGISTER.getId(), String.valueOf(user.getCompanyid()),"marketng sales leads add company register click ");


		return SUCCESS;
	}

	/*this method for  get the company profile based on email*/
	public String EditCompanyProfile() throws Exception
	{ 
		HttpServletRequest request = ServletActionContext.getRequest();
		Company profileObj = ComregDAO.GetCompanyProfile((Company)sessionmap.get("Company")); 

		if(request.getParameter("userid") != null)
		{
			user = userDAO.GetUserProfile(Integer.parseInt(request.getParameter("userid")));
		}
		if(profileObj!=null)
		{
			CurrentCompany = profileObj;
			getCountryandlanguagelist();
			return SUCCESS;
		}
		else
		{
			return ERROR;
		} 
	}

	public String uploadImageFile(long id){
		String imageName=null;
		if(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)
		{
			MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();
			Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
			if(fileParameterNames.hasMoreElements()){
				String inputValue = (String) fileParameterNames.nextElement(); 
				String[] localFileNames = multiWrapper.getFileNames(inputValue);
				UploadedFile[] files = multiWrapper.getFiles(inputValue); 
				String fileName = "";
				String fileType = "";
				for (UploadedFile cf : files) {
					logger.info("----------file length...-------"+cf.length());
					logger.info("file length..."+cf.length());
					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
					logger.info("fileType----------------"+fileType);
					if(fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("png")){
						//String file_path = servletRequest.getSession().getServletContext().getRealPath("/admin_profile_pics" );
						String file_path = "/home/profilepics/";
						//String file_path = "D:\\vimalprofilepic\\";
						////logger.info(("----------Server path:...-------"+ file_path);
						//logger.info(("Server path:" + file_path);
						File fileToCreate = new File(file_path, id+"."+fileType);
						try {
							if(cf!=null && cf.getContent()!=null)
							{
								File fi = (File) cf.getContent();

								FileUtils.copyFile(fi, fileToCreate);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							logger.error("----------IOEXCEPTION-----------"+e.getMessage());
							//e.printStackTrace();
						}
						imageName =  id+"."+fileType;
					}
				}
			}
		}
		return imageName;
	}


	/*this method for update the  company profile based on company id*/
	public String companyUpdateprofile()
	{
		Company profile=(Company)sessionmap.get("Company");
		User sessionUser=(User)sessionmap.get("User");
		Comreg.setCompanyid(profile.getCompanyid());
		Comreg.setCreateddate(profile.getCreateddate());
		Comreg.setUsername(profile.getUsername()); 
		Comreg.setModifieddate(new Date());
		Comreg.setCompany_userid(profile.getCompany_userid());
		Comreg.setLocked(profile.isLocked());
		Comreg.setStatus(profile.isStatus());
		Comreg.setParent_company_userid(profile.getParent_company_userid());
		Comreg.setSuper_company_userid(profile.getSuper_company_userid());
		Comreg.setBookingSystemType(profile.getBookingSystemType());
		Comreg.setCompanyRole(profile.getCompanyRole());
		Comreg.setCurrencyCode(profile.getCurrencyCode());
		//Comreg.setAgreementTranExpiryDate(profile.getAgreementTranExpiryDate());
		Comreg.setMailStatus(profile.getMailStatus());
		Comreg.setEmailCode(profile.getEmailCode());   
		Comreg.setSalesLeadGeneration(profile.getSalesLeadGeneration());   

		if(StringUtils.isNotBlank(Comreg.getAgreementExpiryDt()))
			Comreg.setAgreementExpiryDate(DateConversion.StringToDate(Comreg.getAgreementTranExpiryDate()));
		else
			Comreg.setAgreementExpiryDate(profile.getAgreementExpiryDate());
		if(Comreg.getEmail().equalsIgnoreCase("")){
			Comreg.setEmail(profile.getEmail()); 
		}
		if(Comreg.getImagepath()==null || Comreg.getImagepath().equals("") ){
			if(profile.getImagepath()!=null && profile.getImagepath().contains(".")){
				Comreg.setImagepath(profile.getImagepath());
			}
		}
		else{
			Comreg.setImagepath(uploadImageFile(sessionUser.getId()));
		}

		Comreg.setPassword(profile.getPassword());
		Company updatedCompany=ComregDAO.superUserCompanyProfileUpdate(Comreg);
		logger.info("superUserCompanyProfileUpdate...companyId....."+updatedCompany.getCompanyid());
		if(updatedCompany!=null){
			if(sessionmap.get("Company")!=null){
				sessionmap.remove("Company");
				sessionmap.put("Company",updatedCompany);
			}
			for(User userObj:ComregDAO.getCompanyListFromUser(updatedCompany)){
				boolean isUpdate = false;
				if(userObj.getUserrole_id().isSuperuser() || userObj.getUserrole_id().isUsermode()){
					User updateObj=new User();
					updateObj.setUsername(Comreg.getUsername());
					updateObj.setFirstname(Comreg.getUsername());
					updateObj.setLastname(Comreg.getUsername());
					updateObj.setEmail(Comreg.getEmail());
					updateObj.setPhone(Comreg.getPhone());
					updateObj.setDescription(Comreg.getCompanydescription());
					updateObj.setModifieddate(new Date());
					updateObj.setAddress(Comreg.getAddress());
					updateObj.setImagepath(Comreg.getImagepath());
					updateObj.setSecurityquestion(Comreg.getSecurityquestion());
					updateObj.setSecurityanswer(Comreg.getSecurityanswer());
					updateObj.setCity(Comreg.getCity()); 
					updateObj.setLanguage(Comreg.getLanguage()); 
					updateObj.setCountryname(Comreg.getCountryname());
					updateObj.setModifiedbyCompanyUserId(userObj.getId());
					updateObj.setCreatedbyCompanyUserId(userObj.getCreatedbyCompanyUserId());
					updateObj.setUserrole_id(userObj.getUserrole_id()); 
					updateObj.setId(userObj.getId());
					updateObj.setCompany_userid(userObj.getCompany_userid());
					updateObj.setAgentWallet(userObj.getAgentWallet());
					updateObj.setStatus(userObj.isStatus());
					updateObj.setLocked(userObj.isLocked());
					updateObj.setCompanyid(userObj.getCompanyid());
					updateObj.setLockedDate(userObj.getLockedDate());
					updateObj.setCurrencyCode(userObj.getCurrencyCode());
					updateObj.setPassword(userObj.getPassword());
					updateObj.setCreateddate(userObj.getCreateddate());
					isUpdate=ComregDAO.updateCompanyProfileInUser(updateObj);
					logger.info("----------Role isSuperuser----- id-------"+userObj.getId());
					logger.info("------User Role----------"+userObj.getUserrole_id().isSuperuser());
					if(isUpdate){
						UserWallet wallet=new UserWallet();
						wallet.setCreatedAt(updateObj.getAgentWallet().getCreatedAt());
						wallet.setUpdatedAt(new Timestamp(new Date().getTime()));
						wallet.setCurrencyCode(updateObj.getAgentWallet().getCurrencyCode());
						wallet.setWalletId(updateObj.getAgentWallet().getWalletId());
						wallet.setWalletType(getTypeOfWallet());
						wallet.setWalletbalance(updateObj.getAgentWallet().getWalletbalance());
						wallet.setDepositBalance(updateObj.getAgentWallet().getDepositBalance());
						wallet.setWalletBalanceRange(updateObj.getAgentWallet().getWalletBalanceRange());
						if(userDAO.updateUserWallet(wallet)!=null && userDAO.updateUserWallet(wallet).getWalletId()== updateObj.getAgentWallet().getWalletId()){
							ComregDAO.insertEmail(String.valueOf(userObj.getId()), 0, Email.EMAIL_TYPE_COMPANY_UPDATION);
							addActionMessage(getText("global.companyUpdateprofilesuccessfully"));
						}
						else{
							addActionMessage(getText("global.companyUpdateprofileerror"));
							return ERROR;
						}
					}
					else{
						addActionMessage(getText("Failed.Try again.")); 
						return ERROR;
					}

				}
			}
		}
		return SUCCESS;
	}

	/*this method for passing company id fetching superUser company profile*/
	public String superUserCompanyUpdate() throws Exception{
		Company getProfile=ComregDAO.getCompanyProfile(Comreg.getCompanyid());
		Company getparentProfile=ComregDAO.getCompanyByCompanyUserId(getProfile.getSuper_company_userid());
		if(getProfile!=null){
			User companyUserObj=ComregDAO.getCompanyProfileFromUser(getProfile.getCompanyid(), getProfile.getEmail());
			if(companyUserObj!=null){
				if (companyUserObj.getAgentWallet().getWalletBalanceRange().compareTo(BigDecimal.ZERO)>0){
					companyUserProfile=companyUserObj;
					statusCode = ActionStatusEnum.SUCCESS.getCode();
					HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.EDIT.getId(), String.valueOf(user.getCompanyid()),"markting sales leads company list edit click ");

				}
				else {
					companyUserProfile=companyUserObj;
					statusCode = ActionStatusEnum.FAILED.getCode();	
					HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.EDIT.getId(), String.valueOf(user.getCompanyid()),"markting sales leads company list edit click ");

				}

			}
			CurrentCompany=getProfile;
			setTermsAndConditions(getparentProfile.getTemsandcondtions());

			getCountryandlanguagelist();
		}

		statusCode = ActionStatusEnum.SUCCESS.getCode();	
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, pageId, actionId, statusCode, detailType, String.valueOf(Comreg.getCompanyid()), "Company Edit click");
		historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.EDIT.getId(), String.valueOf(user.getCompanyid()),"markting sales leads company list edit click ");

		return SUCCESS;
	}

	public String superUserCompanyEntityUpdate() throws Exception{
		CurrentCompany=ComregDAO.getCompanyProfile(Comreg.getCompanyid());
		StateList= cDAO.getStateList();
		CountryList  = cDAO.getCountryList();	
		return SUCCESS;
	}

	public String addCompanyEntity() throws Exception{
		CurrentCompany=ComregDAO.getCompanyProfile(Comreg.getCompanyid());
		StateList= cDAO.getStateList();
		CountryList  = cDAO.getCountryList();	
		return SUCCESS;
	}

	public String addSuperCompanyEntity() throws Exception{
		System.out.println("---------showCompanyDetailsBy ComapnyId--------------"+Comreg.getCompanyid());

		CurrentCompany = ComregDAO.getCompanyProfile(Comreg.getCompanyid());
		if(CurrentCompany!=null){
			User companyUserObj = ComregDAO.getCompanyProfileFromUser(CurrentCompany.getCompanyid(), CurrentCompany.getEmail());
			if(companyUserObj!=null){
				companyUserProfile=companyUserObj;			
			} 
			for(CompanyEntity companyEntity: companyEntityList){
				String [] arr=companyEntity.getState().split("-");
				companyEntity.setState(arr[0]);
				companyEntity.setStateCode(arr[1]);

				companyEntity.setCompany(CurrentCompany);
				CompanyEntity savecompanyentity = ComregDAO.registerCompanyEntity(companyEntity);

			}


			statusCode = ActionStatusEnum.FAILED.getCode();	
		}
		statusCode = ActionStatusEnum.SUCCESS.getCode();	
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, pageId, actionId, statusCode, detailType, String.valueOf(Comreg.getCompanyid()), "Company View click");
		setEmail(CurrentCompany.getEmail());
		setCompanyid(CurrentCompany.getCompanyid());
		addActionMessage("Successfully Entity Created.");
		return SUCCESS;


	}


	/* this method for superUser CompanyProfile Update based on company id*/
	public String superUserCompanyProfileUpdate(){
		Company profile=ComregDAO.getCompanyProfile(Comreg.getCompanyid());
		User sessionUser=(User)sessionmap.get("User");
		String billingState=Comreg.getBillingstate();
		if(billingState!=null && billingState.contains(","))
			billingState=billingState.replace(",", "") ;
		Comreg.setBillingstate(billingState);
		Comreg.setCompanyid(profile.getCompanyid());
		Comreg.setCreateddate(profile.getCreateddate());
		Comreg.setUsername(profile.getUsername());
		Comreg.setModifieddate(new Date());
		Comreg.setCompany_userid(profile.getCompany_userid());
		Comreg.setLocked(profile.isLocked());
		Comreg.setStatus(profile.isStatus());
		Comreg.setParent_company_userid(profile.getParent_company_userid());
		Comreg.setSuper_company_userid(profile.getSuper_company_userid());
		Comreg.setBookingSystemType(profile.getBookingSystemType());
		Comreg.setCompanyRole(profile.getCompanyRole());
		Comreg.setCurrencyCode(profile.getCurrencyCode());
		Comreg.setMailStatus(profile.getMailStatus());
		Comreg.setRmConfig(profile.getRmConfig());
		if(StringUtils.isNotBlank(Comreg.getAgreementTranExpiryDate()))
			Comreg.setAgreementExpiryDate(DateConversion.StringToDate(Comreg.getAgreementTranExpiryDate()));
		else
			Comreg.setAgreementExpiryDate(profile.getAgreementExpiryDate());
		if(profile.getEmailCode().equals("0")) 
			Comreg.setEmailCode(RandomConfigurationNumber.getEncryptedEmailCode(String.valueOf(profile.getCompanyid())));

		else 
			Comreg.setEmailCode(profile.getEmailCode());


		if(Comreg.getEmail().equalsIgnoreCase("")){
			Comreg.setEmail(profile.getEmail()); 
		}


		if(Comreg.getImagepath()==null || Comreg.getImagepath().equals("") ){
			if(profile.getImagepath()!=null && profile.getImagepath().contains(".")){
				Comreg.setImagepath(profile.getImagepath());

			}
		}
		else{
			User user=userDAO.getUserPasswordForLock(profile.getEmail());
			if(user!=null){
				Comreg.setImagepath(uploadImageFile(user.getId()));
			}
			else{
				addActionMessage(getText("Failed.Try again.")); 
				return ERROR;
			}

		}

		/*	if(Comreg.getImagepath()==null || Comreg.getImagepath().equals("") ){
			Comreg.setImagepath(profile.getImagepath());
		}
		else{
			userId=profile.getImagepath().substring(0, profile.getImagepath().lastIndexOf('.'));
			Comreg.setImagepath(uploadImageFile(Integer.parseInt(userId)));
		}*/
		SalesLeadGeneration salesLeadGeneration = new SalesLeadGeneration();
		Comreg.setPassword(profile.getPassword());
		int salesPersonUserId;
		if(getSalesPersonUserId()==null || getSalesPersonUserId().equals("")){
			salesPersonUserId=sessionUser.getId();
		}
		else{
			salesPersonUserId=Integer.parseInt(getSalesPersonUserId());
		}	
		if(profile.getSalesLeadGeneration()==null){
			salesLeadGeneration.setUser(userDAO.GetUserProfile(salesPersonUserId));
			salesLeadGeneration.setId(Comreg.getSalesLeadGeneration().getId());
			salesLeadGeneration.setLeadType(Comreg.getSalesLeadGeneration().getLeadType());
			salesLeadGeneration.setCompanyRemarks(Comreg.getSalesLeadGeneration().getCompanyRemarks());
			salesLeadGeneration.setSalesPersonRemarks(Comreg.getSalesLeadGeneration().getSalesPersonRemarks());
			Comreg.setSalesLeadGeneration(salesLeadGeneration);
		}
		else{
			salesLeadGeneration.setUser(userDAO.GetUserProfile(salesPersonUserId));
			salesLeadGeneration.setId(Comreg.getSalesLeadGeneration().getId());
			salesLeadGeneration.setLeadType(Comreg.getSalesLeadGeneration().getLeadType());
			salesLeadGeneration.setCompanyRemarks(Comreg.getSalesLeadGeneration().getCompanyRemarks());
			salesLeadGeneration.setSalesPersonRemarks(Comreg.getSalesLeadGeneration().getSalesPersonRemarks());
			salesLeadGeneration.setChildUserId(profile.getSalesLeadGeneration().getChildUserId());
			Comreg.setSalesLeadGeneration(salesLeadGeneration);	
		}
		if(Comreg.getCountryCode()!=null)
			Comreg.setCountryCode(Comreg.getCountryCode());
		else
			Comreg.setCountryCode("91");
		

		Company updatedCompany=ComregDAO.superUserCompanyProfileUpdate(Comreg);
		logger.info("superUserCompanyProfileUpdate...companyId....."+updatedCompany.getCompanyid());
		if(updatedCompany!=null){
			setCurrentCompany(updatedCompany);
			//userWallet.setWalletType(getTypeOfWallet());
			for(User userObj:ComregDAO.getCompanyListFromUser(updatedCompany)){
				boolean isUpdate = false;
				if(userObj.getUserrole_id().isUsermode()){
					userObj.getUserrole_id().setDemoUser(Comreg.isDemoUser());
					User updateObj=new User();
					//updateObj.setAgentWallet(userWallet);
					updateObj.setUsername(Comreg.getCompanyname());
					updateObj.setFirstname(Comreg.getCompanyname());
					updateObj.setLastname(Comreg.getCompanyname());
					//updateObj.setPassword(Comreg.getPassword());
					updateObj.setEmail(Comreg.getEmail());
					updateObj.setPhone(Comreg.getPhone());
					if(Comreg.getCountryCode()!=null)
						updateObj.setCountryCode(Comreg.getCountryCode());
					else
						updateObj.setCountryCode("91");

					updateObj.setDescription(Comreg.getCompanydescription());
					updateObj.setModifieddate(new Date());
					updateObj.setAddress(Comreg.getAddress());
					updateObj.setImagepath(Comreg.getImagepath());
					updateObj.setSecurityquestion(Comreg.getSecurityquestion());
					updateObj.setSecurityanswer(Comreg.getSecurityanswer());
					updateObj.setCity(Comreg.getCity()); 
					updateObj.setLanguage(Comreg.getLanguage()); 
					updateObj.setCountryname(Comreg.getCountryname());
					updateObj.setModifiedbyCompanyUserId(sessionUser.getId());
					updateObj.setCreatedbyCompanyUserId(userObj.getCreatedbyCompanyUserId());
					//updateObj.setAgentWallet(userObj.getAgentWallet());
					updateObj.setUserrole_id(userObj.getUserrole_id()); 
					updateObj.setId(userObj.getId());
					updateObj.setCompany_userid(userObj.getCompany_userid());
					updateObj.setAgentWallet(userObj.getAgentWallet());
					updateObj.setStatus(userObj.isStatus());
					updateObj.setLocked(userObj.isLocked());
					updateObj.setCompanyid(userObj.getCompanyid());
					updateObj.setLockedDate(userObj.getLockedDate());
					updateObj.setCurrencyCode(userObj.getCurrencyCode());
					updateObj.setPassword(userObj.getPassword());
					updateObj.setCreateddate(userObj.getCreateddate());
					isUpdate=ComregDAO.updateCompanyProfileInUser(updateObj);
					logger.info("----------User----- id-------"+userObj.getId());
					logger.info("------User Role----------"+userObj.getUserrole_id().isSuperuser());
					if(isUpdate){
						ComregDAO.updateSalesPersonChildUserId(updatedCompany, updateObj);
						UserWallet wallet=new UserWallet();
						wallet.setCreatedAt(updateObj.getAgentWallet().getCreatedAt());
						wallet.setUpdatedAt(new Timestamp(new Date().getTime()));
						wallet.setCurrencyCode(updateObj.getAgentWallet().getCurrencyCode());
						wallet.setWalletId(updateObj.getAgentWallet().getWalletId());
						wallet.setWalletType(getTypeOfWallet());
						wallet.setWalletbalance(updateObj.getAgentWallet().getWalletbalance());
						wallet.setDepositBalance(updateObj.getAgentWallet().getDepositBalance());
						logger.info("getPostAmount---------------"+getPostAmount());
						if (new BigDecimal(getPostAmount()).compareTo(BigDecimal.ZERO) > 0){
							wallet.setWalletBalanceRange(new BigDecimal(getPostAmount()).negate());
						}
						else{
							wallet.setWalletBalanceRange(new BigDecimal(getPostAmount()));	
						}
						if(userDAO.updateUserWallet(wallet)!=null && userDAO.updateUserWallet(wallet).getWalletId()== updateObj.getAgentWallet().getWalletId()){
							User companyUserObj=ComregDAO.getCompanyProfileFromUser(updatedCompany.getCompanyid(), updatedCompany.getEmail());
							companyUserProfile=companyUserObj;
							new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(updatedCompany.getCompanyid()),"Company details updated","Company details updated",NotificationInventoryTypeEnum.COMPANY.getId(),true,false,false,true,false,false);
							ComregDAO.insertEmail(String.valueOf(companyUserObj.getId()), 0, Email.EMAIL_TYPE_COMPANY_UPDATION);
							addActionMessage(getText("global.superUserCompanyProfileUpdatesuccessfully"));
							return SUCCESS;
						}
						else{
							addActionMessage(getText("global.superUserCompanyProfileUpdateerror"));
							return ERROR;
						}
					}
					else{
						addActionMessage(getText("Failed.Try again.")); 
						return ERROR;
					}
				}
			}
		}
		return SUCCESS;
	}

	public String resetCompanyPassword(){
		setCurrentCompany(Comreg);
		return SUCCESS;
	}

	public String updateCompanyPassword(){
		if(userDAO.updateCompanyPasswordInUser(Comreg)){
			ComregDAO.insertEmail(String.valueOf(Comreg.getCompanyid()), 0,Email.EMAIL_TYPE_COMPANY_RESET_PASSWORD);
			addActionMessage(getText("global.resetCompanyPasswordsuccess"));
		}
		else{
			addActionMessage(getText("global.resetCompanyPassworderror"));
			return ERROR;
		}
		return SUCCESS;
	}
	/*Method for load countries,languages,currency codes*/
	public String getCountryInfo(){
		try{
			CountryList =new CountryDao().getCountryList();
			LanguageList= new CountryDao().getLanguageList();
			StateList= new CountryDao().getStateList();
		}catch(Exception e){
			logger.error("----------------EXCEPTION-------------"+e.getMessage());
		}
		return SUCCESS;
	}
	/*Method for load countries,languages,currency codes*/
	public String RegisterwithoutLogin(){
		if(!Registerid.equalsIgnoreCase("Registerid"))
		{
			try{
				String decrString = enc.decryptAES(Registerid.replaceAll(" ","+"));
				String[] parts ;		
				parts = decrString.split("-");
				String email = parts[0];  		
				String Password = parts[1]; 		
				String company_userid = parts[2]; 
				Company company = ComregDAO.CompanyLogin(email, Password,company_userid);
				User userLogin= userDAO.userLogin(email, Password,company_userid);
				logger.info("company object-------------------"+company+"------user obj----"+userLogin+"");
				User userPwd= userDAO.getUserPasswordForLock(email);
				UserRole role= null;
				CompanyRole companyrole= null;
				CompanyBookingSystemType bookingType=null;
				if(userPwd!=null){
					role=userPwd.getUserrole_id();
				}
				if(company!=null && company.getPassword().equals(Password)){
					User user= userDAO.userLogin(email, Password,company.getCompany_userid());
					if(user!=null && user.getPassword().equals(Password)){
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
							sessionmap.put("Company",company);
							sessionmap.put("User",user); 

							logger.info("-----------COMPANY CREDENTIALS AND  USER CREDENTIALS MATCHED---------------------");
							// addActionMessage("Registered Successfully");
							return SUCCESS;
						}
						else{
							addActionError(getText("global.RegisterwithoutLoginerror"));
							return ERROR; 
						}
					}
				}
				addActionError(getText("global.RegisterwithoutLoginregistererror"));
				return ERROR; 
			}catch(Exception e){
				logger.error("----------------EXCEPTION-------------"+e.getMessage());
				addActionError(getText("global.RegisterwithoutLoginregisterexception"));
				//addActionError("Register Failed");   
				return ERROR; 
			}
		}
		else
		{
			addActionError(getText("global.RegisterwithoutLoginregistererror"));
			//addActionError("Register Id Needed.");   
			return ERROR; 
		}
	}


	public String logout()
	{
		if(sessionmap!=null){

			HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
			historyInfo.changeNature(BrowsingOptionPageEnum.HOME, BrowsingOptionActionEnum.ACTION_LOGOUT, ActionStatusEnum.SUCCESS);	
			new HistoryManager().inSertHistory(historyInfo);
			sessionmap.clear();
			sessionmap.invalidate();
			addActionMessage("You have been Successfully Logged Out");
		}
		return SUCCESS;
	}

	@Override
	public Company getModel() {
		// TODO Auto-generated method stub
		return Comreg;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionmap = (SessionMap<String, Object>) arg0;

	}
	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}

	public InputStream getInputStream() {
		return inputStream;
	}
	/*----------------get all approval comapanies list---------------------- */
	public String getAllCompaniesList()
	{
		Company companySessionObj=(Company)sessionmap.get("Company");
		User userSessionObj=(User)sessionmap.get("User");
		List<Company> companyLi=ComregDAO.getAllCompaniesByCompanyUserId(userSessionObj, companySessionObj);
		List<Company> newCompaniesList=new ArrayList<Company>();
		if(companyLi!=null && companyLi.size()>0)
		{
			for(Company companyNew:companyLi){
				if(companyNew.getCompanyRole().isAgent() || companyNew.getCompanyRole().isDistributor()){
					if(companySessionObj.getCompanyid()!=companyNew.getCompanyid()){
						if(companyNew.getCompanyRole().isAgent())
							companyNew.setCompType("Agency");
						if(companyNew.getCompanyRole().isDistributor())
							companyNew.setCompType(getText("global.Wholesaler"));
						newCompaniesList.add(companyNew);
					}
				}
			}
			logger.info("(-----companyList-- size-----)"+newCompaniesList.size());
		}
		if(newCompaniesList!=null && newCompaniesList.size()>0){
			Allcompanylist = newCompaniesList;

		}
		return SUCCESS;
	}

	public String getAllDistributorslist()
	{
		Company companySessionObj=(Company)sessionmap.get("Company");
		User userSessionObj=(User)sessionmap.get("User");
		List<Company> companyLi = ComregDAO.getAllDistributors(userSessionObj, companySessionObj);
		if(companyLi!=null && companyLi.size()>0){
			Allcompanylist = companyLi;
		}
		return SUCCESS;
	}



	public String getCountryandlanguagelist() throws Exception
	{
		Company CurrentCompany = ComregDAO.GetCompanyProfile((Company)sessionmap.get("Company"));   
		User sessionUser=(User)sessionmap.get("User");
		String encryptid = enc.encryptAES(CurrentCompany.getEmail()+"-"+CurrentCompany.getPassword()+"-"+CurrentCompany.getCompany_userid()); 
		Registerid = encryptid; 
		LanguageList = cDAO.getLanguageList(); 
		StateList= cDAO.getStateList();
		CountryList  = cDAO.getCountryList();
		if(CurrentCompany.getCompanyRole().isSuperUser()){
			setTermsAndConditions(CurrentCompany.getTemsandcondtions());
		}
		List<User> salesPersonList=ComregDAO.getSalesPersons(sessionUser); 
		if(salesPersonList!=null && salesPersonList.size()>0)
		{
			for(User user:salesPersonList){
				if(user.getUserrole_id().isAdmin() || user.getUserrole_id().isUsermode() || user.getUserrole_id().isSuperuser()){
					salesPersonRecords.add(user);
				}
			}

		}  

		else{
			HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
			historyInfo.changeNature(BrowsingOptionPageEnum.ADD_COMPANY, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
			new HistoryManager().inSertHistory(historyInfo);
			historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_ADDNEW.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.ADD_NEW.getId(), String.valueOf(user.getCompanyid()),"marketng sales leads company list addnew click ");

		}

		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_ADDNEW.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.ADD_NEW.getId(), String.valueOf(user.getCompanyid()),"marketng sales leads company list addnew click ");

		return SUCCESS;
	}
	/*----------------get all nonapproval comapanies list---------------------- */
	public String getNonApprovalCompaniesList()
	{
		approvalType="NOT";
		Company company=(Company)sessionmap.get("Company");
		logger.info("------SUPER USER ID----------"+company.getCompany_userid());
		User user=(User)sessionmap.get("User");
		logger.info("------IF USER IS ADMIN COMAPNY ID----"+user.getCompanyid()+ "AND ----COMAPNY ID IS ----------"+company.getCompanyid());
		List<Company> approvalCompanyLi=ComregDAO.getApprovalCompaniesList(company);
		List<Company> newApprovalCompanyLi= new ArrayList<Company>();
		if(approvalCompanyLi!=null && approvalCompanyLi.size()>0)
		{
			logger.info("(-----companyList-- size-----)"+approvalCompanyLi.size());
			if(user.getUserrole_id().isSuperuser() ||(user.getUserrole_id().isAdmin() &&(user.getCompanyid()==company.getCompanyid()))){
				for(Company c:approvalCompanyLi){
					if(c.getCompanyRole()!=null && c.getBookingSystemType()!=null){
						logger.info("(-----isStatus--approval company list-----)"+c.isStatus());
						if(c.getCompanyRole().isAgent()|| c.getCompanyRole().isDistributor() || c.getCompanyRole().isCorporate() ){
							if(!c.isStatus())
							{
								if(c.getCompanyRole().isAgent()){
									c.setCompType("Agency");
									newApprovalCompanyLi.add(c);
								}
								if(c.getCompanyRole().isDistributor()){
									c.setCompType("WholeSaler");
									newApprovalCompanyLi.add(c);
								}
								if(c.getCompanyRole().isCorporate()){
									c.setCompType("Corporate");
									newApprovalCompanyLi.add(c);
								}
								if(newApprovalCompanyLi!=null && newApprovalCompanyLi.size()>0){
									AllApprovalcompanylist = newApprovalCompanyLi;

								}
							} 
						}
					}
				}
			}
			else{
				for(Company c:approvalCompanyLi){
					if(c.getCompanyRole()!=null && c.getBookingSystemType()!=null){
						if(c.getCompanyRole().isAgent()){
							if(!c.isStatus())
							{
								logger.info("(-----isStatus-------)"+c.isStatus());
								c.setCompType("Agency");
								newApprovalCompanyLi.add(c);
								AllApprovalcompanylist = newApprovalCompanyLi;

							}
						}

					}

				}
			}
		}
		return SUCCESS;
	}


	/*----------------get all approval comapanies list---------------------- */
	public String getApprovalCompaniesList()
	{
		approvalType="YES";
		Company company=(Company)sessionmap.get("Company");
		logger.info("------SUPER USER ID----------"+company.getCompany_userid());
		User user=(User)sessionmap.get("User");
		logger.info("------IF USER IS ADMIN COMAPNY ID----"+user.getCompanyid()+ "AND ----COMAPNY ID IS ----------"+company.getCompanyid());
		List<Company> approvalCompanyLi=ComregDAO.getApprovalCompaniesList(company);
		List<Company> newApprovalCompanyLi= new ArrayList<Company>();
		if(approvalCompanyLi!=null && approvalCompanyLi.size()>0)
		{
			logger.info("(-----companyList-- size-----)"+approvalCompanyLi.size());
			if(user.getUserrole_id().isSuperuser() ||(user.getUserrole_id().isAdmin() &&(user.getCompanyid()==company.getCompanyid()))){
				for(Company c:approvalCompanyLi){
					if(c.getCompanyRole()!=null && c.getBookingSystemType()!=null){
						logger.info("(-----isStatus--approval company list-----)"+c.isStatus());
						if(c.getCompanyRole().isAgent()|| c.getCompanyRole().isDistributor() || c.getCompanyRole().isCorporate() ){
							if(c.isStatus())
							{
								if(c.getCompanyRole().isAgent()){
									c.setCompType("Agency");
									newApprovalCompanyLi.add(c);
								}
								if(c.getCompanyRole().isDistributor()){
									c.setCompType("WholeSaler");
									newApprovalCompanyLi.add(c);
								}
								if(c.getCompanyRole().isCorporate()){
									c.setCompType("Corporate");
									newApprovalCompanyLi.add(c);
								}

								if(newApprovalCompanyLi!=null && newApprovalCompanyLi.size()>0){
									AllApprovalcompanylist = newApprovalCompanyLi;

								}

							}
						}
					}
				}
			}
			else{
				for(Company c:approvalCompanyLi){
					if(c.getCompanyRole()!=null && c.getBookingSystemType()!=null){
						if(c.getCompanyRole().isAgent()){
							if(c.isStatus())
							{
								logger.info("(-----isStatus-------)"+c.isStatus());
								c.setCompType("Agency");
								newApprovalCompanyLi.add(c);
								AllApprovalcompanylist = newApprovalCompanyLi;

							}
						}
					}

				}
			}
		}
		return SUCCESS;
	}
	/*----------------set company approval ---------------------- */
	public String setComapanyApproval(){
		statusCode = ActionStatusEnum.DEFAULT.getCode();
		User sessionObj=(User)sessionmap.get("User");
		User user=new User();
		logger.info("-----Approval------Company id------------"+Comreg.getCompanyid());
		if(Comreg.isStatus()) {
			logger.info("-----Approval--true----Company status------------"+Comreg.isStatus());
			Comreg.setStatus(false);
		}
		else{
			logger.info("-----Approval--false----Company status------------"+Comreg.isStatus());
			Comreg.setStatus(true);
		}
		Company  status = ComregDAO.updateCompanyApproval(Comreg);
		if(status!=null){
			logger.info("---company ---Appoval status ------"+status.isStatus());
			logger.info("---company ---ID ------"+status.getCompanyid());
			if(status.isStatus()){
				logger.info("--------------COMPANY------------APPROVAL-----------Email ------"+status.getEmail());
				logger.info("--------------COMPANY------------APPROVAL-----------USER---ID ------"+userDAO.getUserId(status.getCompanyid(),status.getEmail()));
				user.setId(userDAO.getUserId(status.getCompanyid(),status.getEmail()));
				user.setStatus(status.isStatus());
				if(userDAO.updateUserApproval(user)){					
					new NotificationAction().insertNotificationOneandAll(sessionObj,String.valueOf(Comreg.getCompanyid()),"Company Approved","Company Approved",NotificationInventoryTypeEnum.COMPANY_APPROVAL.getId(),true,false,false,true,false,false);
					ComregDAO.insertEmail(String.valueOf(user.getId()), 0, Email.EMAIL_TYPE_COMPANY_APPROVAL);
					addActionMessage(getText("global.setComapanyApprovalaccount"));
					statusCode = ActionStatusEnum.SUCCESS.getCode();	
				}
				else{

					statusCode = ActionStatusEnum.FAILED.getCode();	
					addActionMessage(getText("global.setComapanyApprovalfailed"));

				}
			}
			else{
				logger.info("--------------COMPANY------------APPROVAL-----------USER---ID ------"+userDAO.getUserId(status.getCompanyid(),status.getEmail()));
				user.setId(userDAO.getUserId(status.getCompanyid(),status.getEmail()));
				user.setStatus(status.isStatus());
				if(userDAO.updateUserApproval(user)){
					addActionError(getText("global.setComapanyApprovaldis"));
				}
				statusCode = ActionStatusEnum.FAILED.getCode();
				new HistoryManager().inSertHistoryAndDetail(sessionmap, pageId, actionId, statusCode, detailType, String.valueOf(Comreg.getCompanyid()), "Company disapproval click");
				new NotificationAction().insertNotificationOneandAll(sessionObj,String.valueOf(Comreg.getCompanyid()),"Company Disapproved","Company Disapproved",NotificationInventoryTypeEnum.COMPANY_APPROVAL.getId(),true,false,false,true,false,false);

				return ERROR;
			}
		}
		else{
			addActionMessage(getText("global.setComapanyApprovalfail"));
			statusCode = ActionStatusEnum.FAILED.getCode();	
			return ERROR;
		}

		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, pageId, actionId, statusCode, detailType, String.valueOf(Comreg.getCompanyid()), "Company approval click");

		return SUCCESS; 
	}

	public String showCompanyDetailsByComapnyId(){
		logger.info("---------showCompanyDetailsBy ComapnyId--------------"+Comreg.getCompanyid());
		CurrentCompany = ComregDAO.getCompanyProfile(Comreg.getCompanyid());
		if(CurrentCompany!=null){
			User companyUserObj = ComregDAO.getCompanyProfileFromUser(CurrentCompany.getCompanyid(), CurrentCompany.getEmail());
			if(companyUserObj!=null){
				companyUserProfile=companyUserObj;			
			}
			CurrentCompany.getCompanyEntities(); 
			statusCode = ActionStatusEnum.FAILED.getCode();	
		}
		statusCode = ActionStatusEnum.SUCCESS.getCode();	
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, pageId, actionId, statusCode, detailType, String.valueOf(Comreg.getCompanyid()), "Company View click");
		return SUCCESS; 
	}
	public String showWalletCompaniesList(){
		Comreg=(Company)sessionmap.get("Company");
		user=(User)sessionmap.get("User");

		walletCompaniesList = ComregDAO.getUserIdsByCompanyIds(ComregDAO.getCompanyListByCompanyUserId(Comreg.getCompany_userid()));
		if(walletCompaniesList!=null && walletCompaniesList.size()>0){
			for(User userObj:walletCompaniesList){
				if(userObj.getUserrole_id().isUsermode() &&(user.getId()!=userObj.getId())){
					walletCompaniesList.add(userObj);
				}
			}
		}
		return SUCCESS; 
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getDistributorType() {
		return distributorType;
	}

	public void setDistributorType(String distributorType) {
		this.distributorType = distributorType;
	}



	public String getPostAmount() {
		return postAmount;
	}

	public void setPostAmount(String postAmount) {
		this.postAmount = postAmount;
	}

	public String getTypeOfWallet() {
		return typeOfWallet;
	}

	public void setTypeOfWallet(String typeOfWallet) {
		this.typeOfWallet = typeOfWallet;
	}

	public List<Company> getAllcompanylist() {
		return Allcompanylist;
	}

	public void setAllcompanylist(List<Company> allcompanylist) {
		Allcompanylist = allcompanylist;
	}

	public List<Company> getAllApprovalcompanylist() {
		return AllApprovalcompanylist;
	}

	public void setAllApprovalcompanylist(List<Company> allApprovalcompanylist) {
		AllApprovalcompanylist = allApprovalcompanylist;
	}

	public Company getCurrentCompany() {
		return CurrentCompany;
	}

	public void setCurrentCompany(Company currentCompany) {
		CurrentCompany = currentCompany;
	}

	public List<Country> getCountryList() {

		return CountryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.CountryList = countryList;
	}

	public List<StateInfo> getStateList() {
		return StateList;
	}

	public void setStateList(List<StateInfo> stateList) {
		StateList = stateList;
	}


	public List<Language> getLanguageList() {
		return LanguageList;
	}



	public void setLanguageList(List<Language> languageList) {
		this.LanguageList = languageList;
	}



	public void setCompanyUserProfile(User companyUserProfile) {
		this.companyUserProfile = companyUserProfile;
	}

	public List<User> getWalletCompaniesList() {
		return walletCompaniesList;
	}

	public void setWalletCompaniesList(List<User> walletCompaniesList) {
		this.walletCompaniesList = walletCompaniesList;
	}

	public String getRegisterid() {
		return Registerid;
	}

	public void setRegisterid(String registerid) {
		Registerid = registerid;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getSalesPersonUserId() {
		return salesPersonUserId;
	}

	public void setSalesPersonUserId(String salesPersonUserId) {
		this.salesPersonUserId = salesPersonUserId;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}


	public User getCompanyUserProfile() {
		return companyUserProfile;
	}


	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}
	public List<User> getSalesPersonRecords() {
		return salesPersonRecords;
	}

	public void setSalesPersonRecords(List<User> salesPersonRecords) {
		this.salesPersonRecords = salesPersonRecords;
	}

	public List<CompanyEntity> getCompanyEntityList() {
		return companyEntityList;
	}

	public void setCompanyEntityList(List<CompanyEntity> companyEntityList) {
		this.companyEntityList = companyEntityList;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}



	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}





}
