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

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.designationband.DesignationDao;
import com.admin.designationband.EmployeeBandModel;
import com.admin.designationband.EmployeeDesignationsModel;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.Language;
import com.lintas.admin.model.MailStatus;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserDesignation;
import com.lintas.admin.model.UserDesignationRole;
import com.lintas.admin.model.UserRole;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.EmployeeExceltoDBUtil;
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

public class UserRegistrationAction extends ActionSupport implements ModelDriven<User>,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(UserRegistrationAction.class);
	private User user = new User();
	private User userLock ;
	private Company  currentCompany;
	private User  CurrentProfile;
	private UserDAO DAO = new UserDAO();
	private  CompanyDAO cDAO = new CompanyDAO();
	private  CountryDao countryDAO = new CountryDao();
	SessionMap<String, Object> sessionmap ;
	private  MailStatus ms=new MailStatus();
	private List<User> li;
	private List<User> usersList;
	private List<User> walletusersList;
	private  UserWallet agentWallet=new UserWallet();
	private BigDecimal userPreviousWalletBalance;
	private BigDecimal allotedAmount;
	private String typeOfWallet="Prepaid"; 
	private String  postAmount="0.00"; 
	private InputStream inputStream;
	private  List<Language> LanguageList;
	private List<Country> CountryList;
	private List<UserDesignation> designationList;
	private List<User> companyUsersList;
	private String Userdesignation;
	private File file;
	private String contentType;
	private String filename;
	private List<Company> allcompanylist;
	private String filterCompanyType;
	private String lockID;
	private String remarks="No Remarks";
	private Integer statusCode;
	private Integer pageId;
	private Integer actionId;
	private Integer detailType;
	private List<String> userroletype = new ArrayList<String>();
	private String currency;

	private List<EmployeeDesignationsModel> employeeDesignationsList=null;
	private List<EmployeeBandModel> bandModelList=null;
	DesignationDao designationDao=new DesignationDao();

	/* method for user register based on user roles */
	public String Uservalidate() throws Exception
	{ 
		if(!DAO.UserIdExists(user.getEmail()))
		{
			Company c = (Company)sessionmap.get("Company");
			User userSessionObj=(User)sessionmap.get("User");
			if(c!=null && !c.getCompanyRole().isCorporate())
			{
				user.setCreateddate(new Date());
				user.setModifieddate(new Date());
				user.setCompanyid(c.getCompanyid());
				if(userSessionObj.getUserrole_id().isUsermode()){
					user.setCompany_userid(c.getCompany_userid());
				}
				else if(userSessionObj.getUserrole_id().isSuperuser()){
					user.setCompany_userid(c.getCompany_userid());
				}
				UserRole user_role =new UserRole(); 

				if(userroletype!=null && userroletype.size()>0){
					if(userroletype.contains("admin"))
						user_role.setAdmin(true);
					if(userroletype.contains("report"))
						user_role.setReports(true);
					if(userroletype.contains("order"))
						user_role.setOrder(true);
					if(userroletype.contains("cms"))
						user_role.setCms(true);
					if(userroletype.contains("tech_head"))
						user_role.setTechHead(true);
					if(userroletype.contains("tech_support"))
						user_role.setTechSupport(true);
					if(userroletype.contains("demo") || userSessionObj.getUserrole_id().isDemoUser()) 
						user_role.setDemoUser(true);

				}

				user.setCompany_userid(c.getCompany_userid());
				user.setUserrole_id(user_role);
				user.setLocked(false);
				user.setStatus(false);
				user.setCurrencyCode(c.getCurrencyCode());
				if(getTypeOfWallet().equalsIgnoreCase("Postpaid")){
					agentWallet.setWalletBalanceRange(new BigDecimal(getPostAmount()).negate());
				}
				else if(getTypeOfWallet().equalsIgnoreCase("Prepaid")) {
					if(getPostAmount().equalsIgnoreCase("")){
						agentWallet.setWalletBalanceRange(new BigDecimal("0.00"));
					}
					else{
						agentWallet.setWalletBalanceRange(new BigDecimal(getPostAmount()));	
					}

				} 

				logger.info("----getTypeOfWallet---"+getTypeOfWallet());
				logger.info("----negative amount---:"+agentWallet.getWalletBalanceRange());
				agentWallet.setWalletType(getTypeOfWallet());
				agentWallet.setWalletbalance(new BigDecimal("0.00"));
				agentWallet.setDepositBalance(new BigDecimal("0.00"));
				agentWallet.setCurrencyCode(c.getCurrencyCode());
				agentWallet.setCreatedAt(new Timestamp(new Date().getTime()));
				agentWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
				agentWallet.setTransactionType("Credit");
				user.setAgentWallet(agentWallet);
				try{
					User userObj=DAO.RegisterUser(user, userSessionObj);
					logger.info("-----After user register--------USER ID------------"+userObj.getId());
					if(userObj!=null){
						userObj.setImagepath(uploadImageFile(userObj.getId()));	
						userObj.setEmailCode(RandomConfigurationNumber.getEncryptedEmailCode(String.valueOf(userObj.getId())));
						cDAO.updateUserImagePath(userObj);
						new NotificationAction().insertNotificationOneandAll(userSessionObj,String.valueOf(user.getId()),"Employee created","Employee created",NotificationInventoryTypeEnum.USER.getId(),true,false,false,true,false,false);
						cDAO.insertEmail(String.valueOf(userObj.getId()), 0, Email.EMAIL_TYPE_USER_REGISTRATION);
						addActionMessage(getText("global.validatechecksuccess"));
					}

				}catch(Exception e)
				{
					logger.error("---------Exception-----------"+e.getMessage());
					addActionMessage(getText("global.Uservalidateexceptionerror"));
					//addActionMessage("OOPs! server down.");
					return ERROR;
				}
			}
			else if(c.getCompanyRole().isCorporate())
			{
				user.setCreateddate(new Date());
				user.setModifieddate(new Date());
				user.setCompanyid(c.getCompanyid());

				if(userSessionObj.getUserrole_id().isUsermode()){
					user.setCompany_userid(c.getCompany_userid());
				}
				else if(userSessionObj.getUserrole_id().isSuperuser()){
					user.setCompany_userid(c.getCompany_userid());
				}
				UserRole user_role =new UserRole(); 
				UserDesignationRole user_designationrole = new UserDesignationRole();
				if(userroletype!=null && userroletype.size()>0){
					if(userroletype.contains("admin"))
						user_role.setAdmin(true);
					if(userroletype.contains("report"))
						user_role.setReports(true);
					if(userroletype.contains("order"))
						user_role.setOrder(true);
					if(userroletype.contains("traveldesk"))
						user_role.setTravelDesk(true);
					if(userroletype.contains("corporateemployee"))
						user_role.setCorporateemployee(true);
					if(userroletype.contains("demo") || userSessionObj.getUserrole_id().isDemoUser())
						user_role.setDemoUser(true);
				}
				user.setCompany_userid(c.getCompany_userid());
				user.setUserrole_id(user_role);
				user.setLocked(false);
				user.setStatus(false);
				user.setCurrencyCode(c.getCurrencyCode());
				if(getTypeOfWallet().equalsIgnoreCase("Postpaid")){
					agentWallet.setWalletBalanceRange(new BigDecimal(getPostAmount()).negate());
				}
				else if(getTypeOfWallet().equalsIgnoreCase("Prepaid")) {
					if(getPostAmount().equalsIgnoreCase("")){
						agentWallet.setWalletBalanceRange(new BigDecimal("0.00"));
					}
					else{
						agentWallet.setWalletBalanceRange(new BigDecimal(getPostAmount()));	
					}
				} 
				logger.info("----getTypeOfWallet---"+getTypeOfWallet());
				logger.info("----negative amount---:"+agentWallet.getWalletBalanceRange());

				agentWallet.setWalletType(getTypeOfWallet());
				agentWallet.setWalletbalance(new BigDecimal("0.00"));
				agentWallet.setDepositBalance(new BigDecimal("0.00"));
				agentWallet.setCurrencyCode(c.getCurrencyCode());
				agentWallet.setCreatedAt(new Timestamp(new Date().getTime()));
				agentWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
				user.setAgentWallet(agentWallet);
				try{
					User userObj = DAO.RegisterUser(user, userSessionObj);
					logger.info("-----After user register--------USER ID------------"+userObj.getId());
					if(userObj!=null){
						userObj.setEmailCode(RandomConfigurationNumber.getEncryptedEmailCode(String.valueOf(userObj.getId())));
						userObj.setImagepath(uploadImageFile(userObj.getId()));
						userObj.setPassportSizeImage(uploadPassportImageFile(userObj.getId()));	
						userObj.setPassportScanCopy(uploadPassportScanCopyFile(userObj.getId()));
						user_designationrole.setRoleid(user_role.getRoleid());
						user_designationrole.setDesignation(Userdesignation);
						userObj.setDesignation(user.getDesignation());
						userObj.setBandName(user.getBandName());
						cDAO.InsertUserDesignationRole(user_designationrole);
						cDAO.updateUserImagePath(userObj);
						new NotificationAction().insertNotificationOneandAll(userSessionObj,String.valueOf(user.getId()),"Employee created","Employee created",NotificationInventoryTypeEnum.USER.getId(),true,false,false,true,false,false);
						cDAO.insertEmail(String.valueOf(userObj.getId()), 0, Email.EMAIL_TYPE_USER_REGISTRATION);
						addActionMessage(getText("global.validatechecksuccess"));
					}
					else{
						addActionMessage(getText("global.companyUpdateprofileerror"));
					}

				}catch(Exception e)

				{
					logger.error("---------Exception-----------"+e.getMessage());
					//addActionMessage(getText("usererror.gistrationregisterfail"));
					addActionMessage(getText("global.child_ParentCompanyRegOops"));
					return ERROR;
				}
			}
			else
			{
				addActionMessage("Company not found.");
				return ERROR;
			}
		}
		else
		{
			addActionMessage("Already existed.");
			return ERROR;
		}
		return SUCCESS;
	}


	public String doExcelUpload() {
		logger.info("UploadAction doExcelUpload start");
		logger.info("*** " + file + "\t" + file);
		logger.info("*** " + file + "\t" + file.length());
		logger.info("filenames:");
		logger.info("*** " + filename);
		logger.info("content types:");
		logger.info("*** " + contentType);	            
		boolean IsUploaed = EmployeeExceltoDBUtil.getFileDataFromExcel(file);	      
		if(IsUploaed)
		{
			logger.info("UploadAction doExcelUpload end");
			addActionMessage(getText("global.doExcelUploadsuccess"));
			//addActionMessage("File Uploaded Successfully.");
			return SUCCESS;
		}
		else
		{
			logger.info("UploadAction doExcelUpload Failed");
			addActionMessage(getText("global.doExcelUploaderror"));
			//addActionError("UploadAction Failed.");
			return ERROR;
		}

	}

	//method for upload user  upload Passport Scan Copy  image file
	public String uploadPassportScanCopyFile(long id){
		logger.info("uploadPassportImageFile id-------------"+id);
		String imageName=null;
		if(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)
		{
			MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();
			//String[] fileParameterNames = multiWrapper.getFileNames("Imagepath");
			Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
			if(fileParameterNames.hasMoreElements()){
				String inputValue = (String) fileParameterNames.nextElement(); 
				String[] localFileNames = multiWrapper.getFileNames(inputValue);
				UploadedFile[] files = multiWrapper.getFiles(inputValue); 
				String fileName = "";
				String fileType = "";
				for (UploadedFile cf : files) {
					logger.info("---uploadPassportImageFile-------file length...-------"+cf.length());
					logger.info("uploadPassportImageFile   and  file length..."+cf.length());
					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
					if(fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("png")){

						//String file_path = servletRequest.getSession().getServletContext().getRealPath("/admin_profile_pics" );
						String file_path = "/home/emp_passportscan_copies/";
						//String file_path = "D:\\passportscancopy\\";
						//logger.info("----------Server path:...-------"+ file_path);
						//logger.info("Server path:" + file_path);
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




	//method for upload user  Passport size image file
	public String uploadPassportImageFile(long id){
		logger.info("uploadPassportImageFile id-------------"+id);
		String imageName=null;
		if(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)
		{
			MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();
			//String[] fileParameterNames = multiWrapper.getFileNames("Imagepath");
			Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
			if(fileParameterNames.hasMoreElements()){
				String inputValue = (String) fileParameterNames.nextElement(); 
				String[] localFileNames = multiWrapper.getFileNames(inputValue);
				UploadedFile[] files = multiWrapper.getFiles(inputValue); 
				String fileName = "";
				String fileType = "";
				for (UploadedFile cf : files) {

					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
					if(fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("png")){

						//String file_path = servletRequest.getSession().getServletContext().getRealPath("/admin_profile_pics" );
						String file_path = "/home/emp_passportsize/";
						//String file_path = "D:\\passport\\";
						//logger.info("----------Server path:...-------"+ file_path);
						//logger.info("Server path:" + file_path);
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
	//method for upload user image file
	public String uploadImageFile(long id){
		String imageName=null;

		if(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)
		{
			MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();

			//String[] fileParameterNames = multiWrapper.getFileNames("Imagepath");
			Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();

			if(fileParameterNames.hasMoreElements()){
				String inputValue = (String) fileParameterNames.nextElement(); 
				String[] localFileNames = multiWrapper.getFileNames(inputValue);
				UploadedFile[] files = multiWrapper.getFiles(inputValue); 
				String fileName = "";
				String fileType = "";

				for (UploadedFile cf : files) {

					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
					if(fileType.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("png")){

						//String file_path = servletRequest.getSession().getServletContext().getRealPath("/admin_profile_pics" );
						String file_path = "/home/profilepics/";
						//String file_path = "D:\\vimalprofilepic\\";
						//logger.info("----------Server path:...-------"+ file_path);
						//logger.info("Server path:" + file_path);
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



	/*  load all UsersList  under company based on user  roles*/
	public String getCompanyUserList(){
		//c=(Company)sessionmap.get("Company");
		user=(User)sessionmap.get("User");
		li=cDAO.getCompanyUserList(user);
		List<User> userList=new ArrayList<User>();
		for(User user:li){
			UserRole role=user.getUserrole_id();

			if(!role.isSuperuser() && !role.isUsermode()){
				userList.add(user);
			}
		}
		sessionmap.put("userList", userList);
		return SUCCESS;
	}


	/*this method for passing companyId fetching superUser Users profile*/
	public String superUser_UserUpdate(){
		//logger.info("superUser_UserUpdate........"+user.getId());
		try{
			setEmployeeDesignationsList(designationDao.getAllDesgNameList());
			setBandModelList(designationDao.getAllBandNameList());
		}catch (Exception e) {
		}
		CurrentProfile = DAO.getSuperUser_UserProfile(user);
		getCountryandlanguagelist();
		//sessionmap.put("superUser_UserProfile",getProfile);
		return SUCCESS;
	}


	/* this method for superUser UserProfile Updation*/
	public String superUser_UserProfileUpdate(){
		CurrentProfile = DAO.getSuperUser_UserProfile(user);
		User userSessionObj=(User)sessionmap.get("User");
		UserRole user_role =new UserRole();
		user_role.setRoleid(CurrentProfile.getUserrole_id().getRoleid());
		if(userroletype.size()>0){
			if(userroletype.contains("cms")){
				user_role.setCms(true);
			}
			if(userroletype.contains("tech_head")){
				user_role.setTechHead(true);
			}
			if(userroletype.contains("tech_support")){
				user_role.setTechSupport(true);
			}
			if(userroletype.contains("admin")){
				user_role.setAdmin(true);
			}
			if(userroletype.contains("report")){
				user_role.setReports(true);
			}
			if(userroletype.contains("order")){
				user_role.setOrder(true);
			}
			if(userroletype.contains("traveldesk")){
				user_role.setTravelDesk(true);
			}
			if(userroletype.contains("corporateemployee")){
				user_role.setCorporateemployee(true);
			}
			if(userroletype.contains("demo") || userSessionObj.getUserrole_id().isDemoUser())
				user_role.setDemoUser(true);
		}
		else{
			user_role.setAdmin(CurrentProfile.getUserrole_id().isAdmin());
			user_role.setReports(CurrentProfile.getUserrole_id().isReports());
			user_role.setOrder(CurrentProfile.getUserrole_id().isOrder());
			user_role.setCms(CurrentProfile.getUserrole_id().isCms());
			user_role.setTechHead(CurrentProfile.getUserrole_id().isTechHead());
			user_role.setTechSupport(CurrentProfile.getUserrole_id().isTechSupport());
			user_role.setTravelDesk(CurrentProfile.getUserrole_id().isTravelDesk());
			user_role.setCorporateemployee(CurrentProfile.getUserrole_id().isCorporateemployee());
			user_role.setDemoUser(CurrentProfile.getUserrole_id().isDemoUser());

		}

		/*if(CurrentProfile.getUserrole_id().isCorporateemployee()){
			user_role.setCorporateemployee(CurrentProfile.getUserrole_id().isCorporateemployee());
		}*/

		user.setId(CurrentProfile.getId());
		//user.setEmail(CurrentProfile.getEmail());
		//user.setModifiedby(userObj.getUsername());
		if(user.getImagepath()==null || user.getImagepath().equals("") ){
			if(CurrentProfile.getImagepath()!=null && CurrentProfile.getImagepath().contains(".")){
				user.setImagepath(CurrentProfile.getImagepath());
			}
		}
		else{
			user.setImagepath(uploadImageFile(CurrentProfile.getId()));
		}
		if(user.getPassportSizeImage()==null || user.getPassportSizeImage().equals("") ){
			if(CurrentProfile.getPassportSizeImage()!=null && CurrentProfile.getPassportSizeImage().contains(".")){
				user.setPassportSizeImage(CurrentProfile.getPassportSizeImage());
			}
		}
		else{
			user.setPassportSizeImage(uploadPassportImageFile(CurrentProfile.getId()));
		}

		if(user.getPassportScanCopy()==null || user.getPassportScanCopy().equals("") ){
			if(CurrentProfile.getPassportScanCopy()!=null && CurrentProfile.getPassportScanCopy().contains(".")){
				user.setPassportScanCopy(CurrentProfile.getPassportScanCopy());
			}
		}
		else{
			user.setPassportScanCopy(uploadPassportScanCopyFile(CurrentProfile.getId()));
		}
		/*	if(user.getImagepath().equalsIgnoreCase("") || user.getImagepath()==null){
			user.setImagepath(CurrentProfile.getImagepath());
		}
		else{
			user.setImagepath(uploadImageFile(CurrentProfile.getId()));
		}*/
		logger.info("..---------user.getImagepath()........."+user.getImagepath());
		//user.setSuper_userid(userObj.getSuper_userid());
		user.setCompany_userid(CurrentProfile.getCompany_userid());
		logger.info("......role id...."+CurrentProfile.getUserrole_id().getRoleid());
		logger.info("......Status...."+user.isStatus());
		logger.info("......Language...."+user.getLanguage());
		logger.info("......modified by...."+userSessionObj.getUsername());
		//logger.info("company_id--"+profileUpdate.getCompanyid());

		user.setLocked(CurrentProfile.isLocked());
		user.setStatus(CurrentProfile.isStatus());

		user.setCompanyid(CurrentProfile.getCompanyid());
		user.setCreateddate(CurrentProfile.getCreateddate());
		user.setModifieddate(new Date());
		user.setCurrencyCode(CurrentProfile.getCurrencyCode());
		user.setModifiedbyCompanyUserId(userSessionObj.getId());
		user.setCreatedbyCompanyUserId(CurrentProfile.getCreatedbyCompanyUserId());
		user.setUserrole_id(CurrentProfile.getUserrole_id());
		user.setAgentWallet(CurrentProfile.getAgentWallet());
		user.setPassword(CurrentProfile.getPassword());
		user.setEmailCode(CurrentProfile.getEmailCode());
		user.setMailStatus(CurrentProfile.getMailStatus());

		/* boolean isUpdate=DAO.updateUserProfileInUser(user,user_role); */
		User userNewObj=DAO.updateUserProfile(user,user_role);
		if(userNewObj!=null && userNewObj.getId()>0){
			DAO.updateUserRole(user_role);
			if(sessionmap.get("superUser_UserProfile")!=null){
				sessionmap.remove("superUser_UserProfile");
				sessionmap.put("superUser_UserProfile",userNewObj);
			}
			addActionMessage(getText("global.superUser_UserProfileUpdate"));
			new NotificationAction().insertNotificationOneandAll(user,String.valueOf(userNewObj.getId()),"Employee updated","Employee updated",NotificationInventoryTypeEnum.USER.getId(),true,false,false,true,false,false);
			//addActionMessage("Successfully Updated.");	
		}
		else{
			addActionMessage(getText("global.superUser_UserProfileUpdateerror"));
			//addActionError("Failed.Try again.");	
			return ERROR;
		}  
		return SUCCESS;
	}


	/* get user profile passing userEmail */
	public String EditUserProfile()
	{
		User user=(User)sessionmap.get("User");
		//logger.info("--------ID---------"+user.getId());
		User newuser = DAO.GetUserProfile(user.getId());  
		if(newuser!=null)
		{
			logger.info("--------EditUserProfile ........"+newuser.getEmail());
			sessionmap.put("userProfile",newuser);
			return SUCCESS;
		}
		else
		{
			return ERROR;
		}

	}
	public String resetUserPassword(){
		setCurrentProfile(user);
		return SUCCESS;
	}

	public String updateUserPassword(){
		try
		{
			user = DAO.updateUserPassword(user); 
			if(user != null){
				cDAO.insertEmail(String.valueOf(user.getId()), 0,Email.EMAIL_TYPE_USER_RESET_PASSWORD);
				Company company = DAO.checkUserExistInCompany(user);
				if(company != null)
				{
					company.setPassword(user.getPassword());
					DAO.updateCompanyPasswordInCompany(company);
				}
				addActionMessage(getText("global.updateUserPassword"));
				//addActionMessage("Successfully updated.");
			}
			else{
				addActionMessage(getText("global.updateUserPassworderror"));
				//addActionMessage("Failed try again.");
				return ERROR;
			}
		}
		catch(Exception e)
		{
			logger.error(e);
		}

		return SUCCESS;
	}

	public String resetCompanyUserPassword(){
		setCurrentProfile(user);
		return SUCCESS;
	}
	public String updateCompanyUserPassword(){
		if(DAO.updateUserPassword(user)!=null){
			cDAO.insertEmail(String.valueOf(user.getId()), 0,Email.EMAIL_TYPE_USER_RESET_PASSWORD);
			addActionMessage("Successfully updated.");
		}
		else{
			addActionMessage(getText("global.updateCompanyUserPassworderror"));
			//addActionMessage("Failed try again.");
			return ERROR;
		}
		return SUCCESS;
	}

	/* this method for   UserProfile Updation*/
	public String userProfileUpdate(){
		User sessionUser=(User)sessionmap.get("User");

		user.setId(sessionUser.getId());
		user.setEmail(sessionUser.getEmail());
		user.setModifieddate(new Date());
		user.setUsername(sessionUser.getUsername());
		user.setCreateddate(sessionUser.getCreateddate());
		user.setAgentWallet(sessionUser.getAgentWallet());
		user.setUserrole_id(sessionUser.getUserrole_id());
		user.setModifiedbyCompanyUserId(sessionUser.getId());
		user.setCreatedbyCompanyUserId(sessionUser.getCreatedbyCompanyUserId());

		user.setId(sessionUser.getId());
		user.setCompany_userid(sessionUser.getCompany_userid());

		user.setStatus(sessionUser.isStatus());
		user.setLocked(sessionUser.isLocked());
		user.setCompanyid(sessionUser.getCompanyid());
		user.setLockedDate(sessionUser.getLockedDate());
		user.setCurrencyCode(sessionUser.getCurrencyCode());
		user.setPassword(sessionUser.getPassword());
		user.setCountryname(sessionUser.getCountryname());
		logger.info("");
		if(user.getImagepath()!=null ){
			user.setImagepath(uploadImageFile(sessionUser.getId()));
		}
		else{

			user.setImagepath(sessionUser.getImagepath());
		} 
		User newUser=DAO.updateUserProfile(user);	
		if(newUser!=null){
			if(sessionmap.get("User")!=null){
				sessionmap.remove("User");
				sessionmap.put("User",newUser);
			}
			addActionMessage(getText("global.userProfileUpdatesuccess"));
			//addActionMessage("Successfully updated.");
		}
		else{
			addActionMessage(getText("global.userProfileUpdateerror"));
			//addActionError("Failed.Try again.");
			return ERROR;
		} 
		return SUCCESS;
	}

	public String showAllUsersByCompanyId(){
		User userSessionObj=(User)sessionmap.get("User");
		Company companySessionObj=(Company)sessionmap.get("Company");
		//List<User>  allUsersList=DAO.getAllAgentsByCompanyId(userSessionObj, companySessionObj);
		List<User>  allUsersList=DAO.getAllAgentsCompanyTypeByCompanyId(userSessionObj, companySessionObj);
		List<User> newUsersList=new ArrayList<User>();
		if(allUsersList!=null  &&   allUsersList.size()>0){
			for(User userNew:allUsersList) {
				if(!userNew.getUserrole_id().isSuperuser() && !userNew.getUserrole_id().isUsermode()){
					if(!userNew.getEmail().equalsIgnoreCase("directuser@intellicommsolutions.com")){
						newUsersList.add(userNew);
					}
				}
			}
		}
		usersList=newUsersList;
		if(usersList!= null){
			setUsersList(usersList);
		}
		return SUCCESS;
	}

	public String showAllAgentsByCompanyId(){
		User userSessionObj=(User)sessionmap.get("User");
		Company companySessionObj=(Company)sessionmap.get("Company");
		List<User> usersList=DAO.getAllAgentsByCompanyId(userSessionObj, companySessionObj);
		List<User> newUsersList=new ArrayList<User>();
		if(usersList.size()>0){
			for(User userNew:usersList) {
				//if(!userNew.getUserrole_id().isSuperuser() && !userNew.getUserrole_id().isUsermode()){
				newUsersList.add(userNew);

				//}
			}
		}
		if(newUsersList!=null && newUsersList.size()>0)
			sessionmap.put("companyUsersList", newUsersList);
		else
			sessionmap.remove("companyUsersList");
		return SUCCESS;
	}
	public String showAllAgency(){
		User userSessionObj=(User)sessionmap.get("User");
		Company companySessionObj=(Company)sessionmap.get("Company");
		List<Company> usersList = DAO.getAllAgencyByCompanyId(userSessionObj, companySessionObj);
		return SUCCESS;
	}
	/* fetching all wallet users under particular user by passing user id*/
	public String showAllWalletUsersByUserId(){
		User userSessionObj=(User)sessionmap.get("User");
		List<User> usersList = DAO.getWalletUsersCompanyNameByComapanyId(userSessionObj);
		List<User> newUsersList=new ArrayList<User>();

		if(usersList!=null &&  usersList.size()>0){
			for(User userNew:usersList) {
				if(userSessionObj.getId()!=userNew.getId()){
					UserWallet newWallet=userNew.getAgentWallet();
					if(newWallet.getWalletbalance()!=null)
						newWallet.setWalletbalance(newWallet.getWalletbalance().setScale(2, BigDecimal.ROUND_UP));
					else
						newWallet.setWalletbalance(new BigDecimal(0).setScale(2, BigDecimal.ROUND_UP));

					if(newWallet.getDepositBalance()!=null)
						newWallet.setDepositBalance(newWallet.getDepositBalance().setScale(2, BigDecimal.ROUND_UP));
					else
						newWallet.setDepositBalance(new BigDecimal(0).setScale(2, BigDecimal.ROUND_UP));

					if(newWallet.getWalletBalanceRange()!=null)
						newWallet.setWalletBalanceRange(newWallet.getWalletBalanceRange().setScale(2, BigDecimal.ROUND_UP));

					if(newWallet.getWalletBalanceRange()!=null)
						newWallet.setWalletBalanceRange(new BigDecimal(0).setScale(2, BigDecimal.ROUND_UP));

					userNew.setAgentWallet(newWallet);
					newUsersList.add(userNew);
				}
			}
		}
		walletusersList = newUsersList;
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ADD_MYWALLET, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  

		return SUCCESS;
	}

	/* fetching all wallet users under particular user by passing user id and company type*/
	public String showAllWalletUsersBycompanytype(){
		User userSessionObj=(User)sessionmap.get("User");
		List<User> usersList = DAO.getWalletUsersByCompanytype(userSessionObj,filterCompanyType);

		List<User> newUsersList=new ArrayList<User>();
		if(usersList.size()>0){
			for(User userNew:usersList) {
				if(userSessionObj.getId()!=userNew.getId()){
					//logger.info("new userNew" +userNew.getAgentWallet().getWalletbalance());
					newUsersList.add(userNew);
				}
			}
		}
		walletusersList = newUsersList;

		return SUCCESS;
	}

	/* this method for setting  SuperUser Comapany Status and under users*/
	public String setSuperUserComapanyStatus(){

		logger.info("---------- getStatus  ........"+user.isStatus());
		logger.info(" ---------getCompanyid() ........"+user.getCompanyid());
		if(user.isStatus()){
			user.setStatus(false);
		}
		else{
			user.setStatus(true);
		}
		Company companyStatusObj =cDAO.updateCompanyActiveOrInactive(user);
		if(companyStatusObj!=null){
			for(User userObj:cDAO.getUserIdList(companyStatusObj)){
				logger.info(" ---------User Id ........"+userObj.getId());
				userObj.setStatus(companyStatusObj.isStatus());
				User statusObj= cDAO.updateSuperUserCompanyActiveOrInactive(userObj);
				if(statusObj!=null){
					if(statusObj.isStatus()){
						addActionMessage(getText("global.setSuperUserComapanyStatussuccess"));
						//addActionMessage("Successfully account activated.");
					}
					else{
						addActionMessage(getText("global.setSuperUserComapanyStatuserror"));
						//addActionMessage("Successfully account deactivated.");
						return ERROR;
					}
				}
			}
		}
		/*HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ALL_COMPANY, BrowsingOptionActionEnum.ACTION_ALLCOMPANY, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);*/

		return SUCCESS;
	}
	/* this method for set superuser companys  lock  */
	public String setSuperUserCompanyLock(){

		logger.info("----------------getUserid........"+user.getCompanyid());
		logger.info("----------------getLocked......."+user.isLocked());
		String actionMessage="";
		if(user.isLocked()){
			user.setLocked(false);
		}
		else{
			user.setLocked(true);
		}
		Company companyStatusObj =cDAO.updateCompanyLockOrUnlock(user);
		if(companyStatusObj!=null){
			for(User userObj:cDAO.getUserIdList(companyStatusObj)){
				logger.info(" ---------User Id ........"+userObj.getId());
				userObj.setLocked(companyStatusObj.isLocked());
				User lockObj= cDAO.updateSuperUserCompanyLockOrUnlock(userObj);
				if(lockObj!=null){
					if(lockObj.isLocked()){
						statusCode = ActionStatusEnum.SUCCESS.getCode();
						actionId=BrowsingOptionActionEnum.ACTION_LOCK.getId();
						detailType=BrowsingHistoryDetailTypeEnum.COMPANY_LOCK.getId();
						actionMessage="company Lock";
						addActionMessage(getText("global.setSuperUserCompanyLock"));
						//addActionMessage("Successfully account locked.");
					}
					else{
						statusCode = ActionStatusEnum.SUCCESS.getCode();
						actionId=BrowsingOptionActionEnum.ACTION_UNLOCK.getId();
						detailType=BrowsingHistoryDetailTypeEnum.COMPANY_UNLOCK.getId();
						actionMessage="company UnLock";
						addActionMessage(getText("global.setSuperUserCompanyLockerror"));
						//addActionMessage("Successfully account unlocked.");
						return ERROR;
					}
				}
			}
		}

		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.ALL_COMPANY.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);

		return SUCCESS;
	}

	/*this method for passing userid fetch the details of user*/
	public String fetchUserDetailsByUserId(){
		logger.info("-----USER ID------------------"+user.getId());
		List<User> userDetailsList=DAO.getUserDetailsByUserId(user.getId());
		if(userDetailsList!=null && userDetailsList.size()>0)
			sessionmap.put("userDetailsList", userDetailsList);
		return SUCCESS;
	}
	/* this method for setCompany Users lock or unlock */
	public String setCompanyUserLock(){

		User status=null;
		String stat="yes";
		/*if(user.getLocked().equals(stat)){
			stat="no";
		}
		user.setLocked(stat);
		status =cDAO.updateCompanyUserLockOrUnlock(user);
		if(status!=null){
			if(status.getLocked().equals("yes")){
				addActionMessage("Successfully account locked.");
				return SUCCESS;
			}
			else{
				addActionMessage("Successfully account unlocked.");
				return ERROR;
			}

		}else{
			addActionMessage("failed");
			return ERROR;
		}*/
		return SUCCESS;
	}
	/* this method for setting  SuperUser Users Status  */
	public String setSuperUser_UserStatus(){
		logger.info("------------getCompanyid........"+user.getId());
		logger.info("--------------getStatus........"+user.isStatus());
		if(user.isStatus()){
			user.setStatus(false);
		}
		else{
			user.setStatus(true);
		}

		User status = cDAO.updateSuperUser_UserActiveOrInactive(user);
		if(status!=null){
			if(status.isStatus()){
				cDAO.insertEmail(String.valueOf(status.getId()), 0, Email.EMAIL_TYPE_USER_CREDENTIALS);
				addActionMessage(getText("global.setSuperUser_UserStatus"));
				// addActionMessage("Account activated.Login credentials sent employee email.");
			}
			else{
				addActionMessage(getText("global.setSuperUser_UserStatussuccess"));
				//addActionMessage("Account deactivated.");
			}
		}
		return SUCCESS;
	}

	/* this method for setting Company Users  Status  */
	public String setCompanyUserStatus(){
		logger.info("-------------getCompanyid........"+user.getCompanyid());
		logger.info("-----------------getStatus........"+user.isStatus());
		String stat="active";
		/*if(user.getStatus().equalsIgnoreCase(stat)){
			stat="inactive";
		}
		user.setStatus(stat);
		status =cDAO.updateCompanyUserActiveOrInactive(user);
		if(status!=null){
			if(status.getStatus().equalsIgnoreCase("active")){
				addActionMessage("Successfully account activated.");
				return SUCCESS;
			}
			else{
				addActionMessage("Successfully account deactivated.");
				return ERROR;
			}

		}else{
			addActionMessage("failed");
			return ERROR;
		}
		 */
		return SUCCESS;
	}
	/* this method for set super user user  lock  */
	public String setSuperUser_UserLock(){

		logger.info("--------------getUserid........"+user.getId());
		logger.info("-------------------getLocked......."+user.isLocked());
		if(user.isLocked()){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_LOCK.getId();
			detailType=BrowsingHistoryDetailTypeEnum.COMPANY_LOCK.getId();	
			user.setLocked(false);
		}
		else{
			statusCode = ActionStatusEnum.FAILED.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_UNLOCK.getId();
			detailType=BrowsingHistoryDetailTypeEnum.COMPANY_UNLOCK.getId();	
			user.setLocked(true);
		}
		User status =cDAO.updateSuperUser_UserLockOrUnlock(user);
		if(status!=null){
			sessionmap.put("userLock", status);

			if(status.isLocked()){

				addActionMessage(getText("global.setSuperUser_UserLocksuccess"));
				//addActionMessage("Successfully account locked.");
			}
			else{
				addActionMessage(getText("global.setSuperUser_UserLockerror"));
				//addActionMessage("Successfully account unlocked.");
				return ERROR;
			}
		} 
		String actionMessage = null;
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.ACTION_LOCK.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
		return SUCCESS;
	}
	//this method for add  MyWallet based on user id
	public String goMyWallet(){
		User sessiomUser=(User)sessionmap.get("User");
		UserWallet userWallet=DAO.getParentUserWalletAmount(sessiomUser.getAgentWallet().getWalletId());
		logger.info("----User Wallet balance------------"+userWallet.getWalletbalance());

		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ADD_MYWALLET, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo); 		
		return SUCCESS;
	}
	//this method for add  MyWallet based on user id
	public String addMyWallet(){
		User sessionUser=(User)sessionmap.get("User");
		UserDAO  userDAO = new UserDAO();
		if(user!=null && user.getPassword()!=null && !user.getPassword().equalsIgnoreCase(""))
		{
			if(!userDAO.validateUserByIdPassword(sessionUser.getId(),user.getPassword()))
			{
				addActionError(getText("global.addMyWalletsuccess"));
				//addActionError("Wrong passwrod, Please try again.");
				return SUCCESS;
			}
		}
		else{
			addActionError(getText("global.addMyWalletsuccess"));
			//addActionError("Wrong passwrod, Please try again.");
			return SUCCESS;
		}
		UserWallet userWalletSessionObj=DAO.getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId());
		//logger.info("-------before update userwallet--------"+userWalletSessionObj.getWalletbalance());

		if(user!=null){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_ADD_WALLET.getId();
			detailType=BrowsingHistoryDetailTypeEnum.UPDATE.getId();		

			UserWallet userOpeningBalance=DAO.userOpeningBalane(user.getAgentWallet().getWalletId());
			agentWallet.setCurrencyCode(user.getAgentWallet().getCurrencyCode());
			agentWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
			
			if(user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Deposit")){
				agentWallet.setDepositBalance(userWalletSessionObj.getDepositBalance().add(user.getAgentWallet().getWalletbalance()));
				agentWallet.setWalletbalance(userWalletSessionObj.getWalletbalance());
			}
				
			if(user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Credit")){
				agentWallet.setWalletbalance(userWalletSessionObj.getWalletbalance().add(user.getAgentWallet().getWalletbalance()));
				if(userWalletSessionObj.getDepositBalance()!=null) 
					agentWallet.setDepositBalance(userWalletSessionObj.getDepositBalance());
				else 
					agentWallet.setDepositBalance(new BigDecimal(0));
			}
			
			if(user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Debit")){
				agentWallet.setWalletbalance(userWalletSessionObj.getWalletbalance().subtract(user.getAgentWallet().getWalletbalance()));
				if(userWalletSessionObj.getDepositBalance()!=null) 
					agentWallet.setDepositBalance(userWalletSessionObj.getDepositBalance());
				else 
					agentWallet.setDepositBalance(new BigDecimal(0));
			}
			
			//agentWallet.setWalletbalance(user.getAgentWallet().getWalletbalance());
			agentWallet.setWalletId(user.getAgentWallet().getWalletId());
			agentWallet.setTransactionType(user.getAgentWallet().getTransactionType());
			agentWallet.setCreatedAt(userOpeningBalance.getCreatedAt());
			agentWallet.setWalletBalanceRange(userOpeningBalance.getWalletBalanceRange());

			agentWallet.setWalletType(userOpeningBalance.getWalletType());
			WalletAmountTranferHistory walletAmountTranferHistory = DAO.updateParentWallet(agentWallet,sessionUser,userOpeningBalance.getWalletbalance(),user,getRemarks());
			if(walletAmountTranferHistory != null){
				//new CompanyDAO().insertEmail(String.valueOf(transferId), 0, Email.EMAIL_TYPE_WALLET_NOTIFICATION);
				addActionMessage(getText("global.addmywalletbalanceupdate"));
				new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(walletAmountTranferHistory.getActionId()),"Wallet balance "+agentWallet.getTransactionType()+"ed","Wallet balance notification",NotificationInventoryTypeEnum.USER_WALLET.getId(),false,false,false,true,false,false);

			}
			else{
				statusCode = ActionStatusEnum.FAILED_WRONG_USER_ID.getCode();
				actionId=BrowsingOptionActionEnum.ACTION_FAILED_WALLET.getId();
				detailType=BrowsingHistoryDetailTypeEnum.FAILED.getId();		

				addActionMessage(getText("global.addmywalletbalancefailed"));
			}
		}
		String actionMessage = null;
		new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.ADD_MYWALLET.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
		return SUCCESS;
	}

	//this method for going userWallet based on user id
	public String goAgentWallet(){
		User userNew=DAO.GetUserProfile(user.getId());
		UserWallet newUserWallet= userNew.getAgentWallet();
		newUserWallet.setDepositBalance(newUserWallet.getDepositBalance().setScale(2, BigDecimal.ROUND_UP));
		userNew.setAgentWallet(newUserWallet);
		setUser(userNew);
		return SUCCESS;
	}

	public String goOutStandingBalance(){
		User userNew=DAO.GetUserProfile(user.getId());
		UserWallet newUserWallet= userNew.getAgentWallet();
		newUserWallet.setDepositBalance(newUserWallet.getDepositBalance().setScale(2, BigDecimal.ROUND_UP));
		newUserWallet.setWalletbalance(newUserWallet.getWalletbalance().setScale(2, BigDecimal.ROUND_UP));
		userNew.setAgentWallet(newUserWallet);
		setUser(userNew);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	//this method for add  userWallet based on user id
	public String addAgentWallet(){
		User sessionUser=(User)sessionmap.get("User");
		UserDAO  userDAO = new UserDAO();
		User newUser=userDAO.GetUserProfile(user.getId());
		
		
		if(user!=null && user.getPassword()!=null && !user.getPassword().equalsIgnoreCase(""))
		{
			if(!userDAO.validateUserByIdPassword(sessionUser.getId(),user.getPassword()))
			{
				addActionError(getText("global.addAgentWalletsuccess"));
				return SUCCESS;
			}
		}
		else{
			addActionError(getText("global.addAgentWalletsuccess"));
			return SUCCESS;
		}
		UserWallet newWalletObject=new UserWallet();
		UserWallet parentUserWalletAmount=DAO.getParentUserWalletAmount(sessionUser.getAgentWallet().getWalletId());
		logger.info("-------parent wallet Type--------------"+sessionUser.getAgentWallet().getWalletType());
		UserWallet parentUserWalletAmountUpdate=null;

		if(user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Deposit")){
			UserWallet newWalletObjectNew=DAO.getParentUserWalletAmount(user.getAgentWallet().getWalletId());
			logger.info("user.getAgentWallet().getTransactionType()============"+user.getAgentWallet().getTransactionType());
			BigDecimal newDepositBalace=null;
			if(newWalletObjectNew!=null)
			{
				if(newWalletObjectNew.getDepositBalance()==null)
					newWalletObjectNew.setDepositBalance(new BigDecimal(0));
				//logger.info("user.getAgentWallet().getWalletbalance()======"+user.getAgentWallet().getWalletbalance());
				if(parentUserWalletAmount.getDepositBalance().compareTo(user.getAgentWallet().getWalletbalance())==1){
					newDepositBalace=newWalletObjectNew.getDepositBalance().add(user.getAgentWallet().getWalletbalance());
				}
				else{
					addActionError(getText("global.addAgentWalletbalance"));
					return SUCCESS;
				}
				
			}
			else
				newDepositBalace=new BigDecimal("0.00");
			UserWallet newWalletObj= DAO.updateUserWalletByWalletId(user,newDepositBalace);
			//logger.info("user.getAgentWallet().getWalletbalance()======"+user.getAgentWallet().getWalletbalance());
			if(newWalletObj!=null){
				BigDecimal parentOpeningBalance=parentUserWalletAmount.getDepositBalance();
				BigDecimal parentClosingBalance=parentUserWalletAmount.getDepositBalance().subtract(user.getAgentWallet().getWalletbalance());
				
				//deduct the balance from parent deposit balance
				parentUserWalletAmountUpdate=parentUserWalletAmount;
				
				//set as parentClosingDeposit balance
				//parentUserWalletAmountUpdate.setWalletbalance(parentClosingBalance);
				parentUserWalletAmountUpdate.setDepositBalance(parentClosingBalance);
				DAO.updateParentWallet(parentUserWalletAmountUpdate);
				//parent and user wallet done ##############################################SSSS
				WalletAmountTranferHistory  tranferHistory=new WalletAmountTranferHistory();
				tranferHistory.setCurrency(newWalletObj.getCurrencyCode());
				//through wallet balance i am taking
				tranferHistory.setAmount(user.getAgentWallet().getWalletbalance());
				tranferHistory.setWalletId(newWalletObj.getWalletId());
				tranferHistory.setOpeningBalance(newWalletObjectNew.getDepositBalance());
				tranferHistory.setClosingBalance(newDepositBalace);
				tranferHistory.setUserId(user.getId());
				tranferHistory.setParentUserId(sessionUser.getId());
				if(user.getAgentWallet().getReferenceNumber()!=null && !user.getAgentWallet().getReferenceNumber().equals(""))
				{
					tranferHistory.setActionId(user.getAgentWallet().getReferenceNumber());
				}else{
					tranferHistory.setActionId(RandomConfigurationNumber.generateRandomTxID());
				}
				tranferHistory.setTransactionType(user.getAgentWallet().getTransactionType());
				tranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
				tranferHistory.setParentOpeningBalance(parentOpeningBalance);
				tranferHistory.setRemarks(remarks);
				tranferHistory.setParentClosingBalance(parentClosingBalance);
				tranferHistory.setAction("Deposited");
				tranferHistory.setCompanyId(newUser.getCompanyid());
				tranferHistory =DAO.insertWalletAmountTransferHistory(tranferHistory);
				if(tranferHistory!=null && tranferHistory.getId()>0){
					new CompanyDAO().insertEmail(String.valueOf(tranferHistory.getId()), 0, Email.EMAIL_TYPE_WALLET_DEPOSIT_PARENT_NOTIFICATION);
					new CompanyDAO().insertEmail(String.valueOf(tranferHistory.getId()), 0, Email.EMAIL_TYPE_WALLET_DEPOSIT_CHILD_NOTIFICATION);
					addActionMessage("Deposit balance updated successfully.");
				}
				else
					addActionError(getText("global.addAgentWalletsetwalletbalanceemailfailed"));


			}

		}
		else if(user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Withdrawal")){
			UserWallet newWalletObjectNew=DAO.getParentUserWalletAmount(user.getAgentWallet().getWalletId());
			logger.info("user.getAgentWallet().getTransactionType()============"+user.getAgentWallet().getTransactionType());
			BigDecimal newDepositBalace=null;
			if(newWalletObjectNew!=null)
			{
				if(newWalletObjectNew.getDepositBalance()==null)
					newWalletObjectNew.setDepositBalance(new BigDecimal(0));
				newDepositBalace=newWalletObjectNew.getDepositBalance().subtract(user.getAgentWallet().getWalletbalance());
			}
			else
				newDepositBalace=new BigDecimal("0.00");
			UserWallet newWalletObj= DAO.updateUserWalletByWalletId(user,newDepositBalace);
			if(newWalletObj!=null){
				BigDecimal parentOpeningBalance=parentUserWalletAmount.getWalletbalance();
				BigDecimal parentClosingBalance=parentUserWalletAmount.getWalletbalance().add(user.getAgentWallet().getWalletbalance());
				parentUserWalletAmountUpdate=parentUserWalletAmount;
				parentUserWalletAmountUpdate.setWalletbalance(parentClosingBalance);
				DAO.updateParentWallet(parentUserWalletAmountUpdate);
				WalletAmountTranferHistory  tranferHistory=new WalletAmountTranferHistory();
				tranferHistory.setCurrency(newWalletObj.getCurrencyCode());
				tranferHistory.setAmount(user.getAgentWallet().getWalletbalance());
				tranferHistory.setWalletId(newWalletObj.getWalletId());
				tranferHistory.setOpeningBalance(newWalletObjectNew.getDepositBalance());
				tranferHistory.setClosingBalance(newWalletObj.getDepositBalance());
				tranferHistory.setUserId(user.getId());
				tranferHistory.setCompanyId(newUser.getCompanyid());
				if(user.getAgentWallet().getReferenceNumber()!=null && !user.getAgentWallet().getReferenceNumber().equals(""))
					tranferHistory.setActionId(user.getAgentWallet().getReferenceNumber());
				else
					tranferHistory.setActionId(RandomConfigurationNumber.generateRandomTxID());
				tranferHistory.setTransactionType(user.getAgentWallet().getTransactionType());
				tranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
				tranferHistory.setParentOpeningBalance(parentOpeningBalance);
				tranferHistory.setRemarks(remarks);
				tranferHistory.setParentClosingBalance(parentClosingBalance);
				tranferHistory.setAction("Withdrawn");
				tranferHistory =DAO.insertWalletAmountTransferHistory(tranferHistory);
				if(tranferHistory!=null && tranferHistory.getId()>0){
					new CompanyDAO().insertEmail(String.valueOf(tranferHistory.getId()), 0, Email.EMAIL_TYPE_WALLET_WITHDRAW_PARENT_NOTIFICATION);
					new CompanyDAO().insertEmail(String.valueOf(tranferHistory.getId()), 0, Email.EMAIL_TYPE_WALLET_WITHDRAW_CHILD_NOTIFICATION);
					addActionMessage("Amount Withdrawn successfully.");
				}
				else
					addActionError(getText("global.addAgentWalletsetwalletbalanceemailfailed"));


			}

		}
		else if(parentUserWalletAmount!=null){
			if(sessionUser.getAgentWallet().getWalletType().equals("Postpaid")){
				if(user.getAgentWallet().getWalletbalance().compareTo(parentUserWalletAmount.getWalletbalance().subtract(parentUserWalletAmount.getWalletBalanceRange()))<=0)
				{
					if (user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Credit"))
					{
						BigDecimal empTotalAmnt=getUserPreviousWalletBalance().add(user.getAgentWallet().getWalletbalance());
						BigDecimal disremainAmount=parentUserWalletAmount.getWalletbalance().subtract(user.getAgentWallet().getWalletbalance());
						newWalletObject.setWalletbalance(disremainAmount);
						newWalletObject.setTransactionType("Debit");
					}
					else if (user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Debit"))
					{
						BigDecimal empTotalAmnt=getUserPreviousWalletBalance().subtract(user.getAgentWallet().getWalletbalance());
						BigDecimal disremainAmount=parentUserWalletAmount.getWalletbalance().add(user.getAgentWallet().getWalletbalance());
						newWalletObject.setWalletbalance(disremainAmount);
						newWalletObject.setTransactionType("Credit");
					}
					newWalletObject.setWalletId(parentUserWalletAmount.getWalletId());
					newWalletObject.setCurrencyCode(parentUserWalletAmount.getCurrencyCode());
					newWalletObject.setUpdatedAt(new Timestamp(new Date().getTime()));

					newWalletObject.setWalletType(parentUserWalletAmount.getWalletType());
					newWalletObject.setCreatedAt(parentUserWalletAmount.getCreatedAt());
					newWalletObject.setWalletBalanceRange(parentUserWalletAmount.getWalletBalanceRange());
					newWalletObject.setDepositBalance(parentUserWalletAmount.getDepositBalance());
					UserWallet returnWalletObj= DAO.updateParentWallet(newWalletObject);
					if(returnWalletObj!=null){
						logger.info("-----parent in postpaid------Return Wallet Amount---------------"+returnWalletObj.getWalletbalance()); 
						if(sessionmap.get("userWallet")!=null){
							sessionmap.remove("userWallet");
						}
						else{
							sessionmap.put("userWallet",returnWalletObj);
						} 
						UserWallet userOpeningBalance=DAO.userOpeningBalane(user.getAgentWallet().getWalletId());

						agentWallet.setCurrencyCode(user.getAgentWallet().getCurrencyCode());
						if (user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Credit"))
						{
							agentWallet.setWalletbalance(getUserPreviousWalletBalance().add(user.getAgentWallet().getWalletbalance()));
							agentWallet.setTransactionType("Credit");
						}
						else if (user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Debit"))
						{
							agentWallet.setWalletbalance(getUserPreviousWalletBalance().subtract(user.getAgentWallet().getWalletbalance()));
							agentWallet.setTransactionType("Debit");
						}
						agentWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
						agentWallet.setWalletId(user.getAgentWallet().getWalletId());
						agentWallet.setCreatedAt(userOpeningBalance.getCreatedAt());
						agentWallet.setWalletBalanceRange(userOpeningBalance.getWalletBalanceRange());
						agentWallet.setDepositBalance(userOpeningBalance.getDepositBalance());
						agentWallet.setWalletType(userOpeningBalance.getWalletType());
						WalletAmountTranferHistory  tranferHistoryNew=DAO.updateAgentWallet(agentWallet,sessionUser,user,userOpeningBalance.getWalletbalance(), parentUserWalletAmount,returnWalletObj,getRemarks());
						if(tranferHistoryNew !=null){
							if(tranferHistoryNew.getTransactionType().equalsIgnoreCase("Credit")){
								new CompanyDAO().insertEmail(String.valueOf(tranferHistoryNew.getId()), 0, Email.EMAIL_TYPE_WALLET_CREDIT_CHILD_NOTIFICATION);
								new CompanyDAO().insertEmail(String.valueOf(tranferHistoryNew.getId()), 0, Email.EMAIL_TYPE_WALLET_CREDIT_PARENT_NOTIFICATION);
							}
							if(tranferHistoryNew.getTransactionType().equalsIgnoreCase("Debit")){
								new CompanyDAO().insertEmail(String.valueOf(tranferHistoryNew.getId()), 0, Email.EMAIL_TYPE_WALLET_DEBIT_CHILD_NOTIFICATION);
								new CompanyDAO().insertEmail(String.valueOf(tranferHistoryNew.getId()), 0, Email.EMAIL_TYPE_WALLET_DEBIT_PARENT_NOTIFICATION);
							}

							new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(tranferHistoryNew.getActionId()),"Wallet balance "+agentWallet.getTransactionType()+"ed","Wallet balance notification",NotificationInventoryTypeEnum.USER_WALLET.getId(),true,false,false,true,false,false);

							//new CompanyDAO().insertEmail(String.valueOf(transferId), 0, Email.EMAIL_TYPE_WALLET_NOTIFICATION);
							addActionMessage(getText("global.addAgentWallettransferId"));
							//addActionMessage("Balance updated successfully.");
						}
						else{
							addActionError(getText("global.addAgentWallettransferIdfailed"));
							//addActionMessage("Failed try again.");
						}
					} 
				}
				else{
					BigDecimal defaultAmount=new BigDecimal("0.00");
					user.getAgentWallet().setWalletbalance(defaultAmount);
					addActionError(getText("global.addAgentWalletbalance"));
					//addActionError("Balance out of range.");  
				}
			}

			else{

				logger.info("TransactionType()------"+user.getAgentWallet().getTransactionType());
				logger.info("------------------AMOUNTS CHECKING------------------");
				logger.info("-------childAmount------"+user.getAgentWallet().getWalletbalance());
				logger.info("-------ParentAmount------"+parentUserWalletAmount.getWalletbalance());
				if (user.getAgentWallet().getWalletbalance().compareTo(parentUserWalletAmount.getWalletbalance())<=0 && user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Credit"))
				{
					logger.info("---INSIDE---Credit----------");
					BigDecimal remainAmount=parentUserWalletAmount.getWalletbalance().subtract(user.getAgentWallet().getWalletbalance());
					logger.info("-----------remainAmount---------------"+remainAmount);
					newWalletObject.setWalletbalance(remainAmount);
					newWalletObject.setTransactionType("Debit");

				}


				else if (user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Debit"))
				{
					BigDecimal remainAmount=parentUserWalletAmount.getWalletbalance().add(user.getAgentWallet().getWalletbalance());
					logger.info("-----------remainAmount---------------"+remainAmount);
					newWalletObject.setWalletbalance(remainAmount);
					newWalletObject.setTransactionType("Credit");
				}
				else 
				{
					user.getAgentWallet().setWalletbalance(new BigDecimal("0.00"));
					addActionError(getText("global.addAgentWalletsetwalletbalance"));
					//addActionError("Insufficient balance."); 
					return SUCCESS;
				}
				newWalletObject.setWalletId(parentUserWalletAmount.getWalletId());
				newWalletObject.setCurrencyCode(parentUserWalletAmount.getCurrencyCode());
				newWalletObject.setUpdatedAt(new Timestamp(new Date().getTime()));

				newWalletObject.setWalletType(parentUserWalletAmount.getWalletType());
				newWalletObject.setCreatedAt(parentUserWalletAmount.getCreatedAt());
				newWalletObject.setWalletBalanceRange(parentUserWalletAmount.getWalletBalanceRange());
				newWalletObject.setDepositBalance(parentUserWalletAmount.getDepositBalance());
				UserWallet returnWalletObj= DAO.updateParentWallet(newWalletObject);
				if(returnWalletObj!=null){
					logger.info("-----------Return Wallet Amount---------------"+returnWalletObj.getWalletbalance()); 
					UserWallet userOpeningBalance=DAO.userOpeningBalane(user.getAgentWallet().getWalletId());
					agentWallet.setCurrencyCode(user.getAgentWallet().getCurrencyCode());
					if (user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Credit"))
					{
						agentWallet.setWalletbalance(getUserPreviousWalletBalance().add(user.getAgentWallet().getWalletbalance()));
						agentWallet.setTransactionType("Credit");	
					}
					if (user.getAgentWallet().getTransactionType()!=null && user.getAgentWallet().getTransactionType().equalsIgnoreCase("Debit"))
					{
						agentWallet.setWalletbalance(getUserPreviousWalletBalance().subtract(user.getAgentWallet().getWalletbalance()));
						agentWallet.setTransactionType("Debit");	
					}
					agentWallet.setUpdatedAt(new Timestamp(new Date().getTime()));
					agentWallet.setWalletId(user.getAgentWallet().getWalletId());
					agentWallet.setCreatedAt(userOpeningBalance.getCreatedAt());
					agentWallet.setWalletBalanceRange(userOpeningBalance.getWalletBalanceRange());
					agentWallet.setDepositBalance(userOpeningBalance.getDepositBalance());
					agentWallet.setWalletType(userOpeningBalance.getWalletType());

					WalletAmountTranferHistory  tranferHistoryNew=DAO.updateAgentWallet(agentWallet,sessionUser,user,userOpeningBalance.getWalletbalance(), parentUserWalletAmount,returnWalletObj,getRemarks());
					if(tranferHistoryNew !=null){
						if(tranferHistoryNew.getTransactionType().equalsIgnoreCase("Credit")){
							new CompanyDAO().insertEmail(String.valueOf(tranferHistoryNew.getId()), 0, Email.EMAIL_TYPE_WALLET_CREDIT_CHILD_NOTIFICATION);
							new CompanyDAO().insertEmail(String.valueOf(tranferHistoryNew.getId()), 0, Email.EMAIL_TYPE_WALLET_CREDIT_PARENT_NOTIFICATION);
						}
						if(tranferHistoryNew.getTransactionType().equalsIgnoreCase("Debit")){
							new CompanyDAO().insertEmail(String.valueOf(tranferHistoryNew.getId()), 0, Email.EMAIL_TYPE_WALLET_DEBIT_CHILD_NOTIFICATION);
							new CompanyDAO().insertEmail(String.valueOf(tranferHistoryNew.getId()), 0, Email.EMAIL_TYPE_WALLET_DEBIT_PARENT_NOTIFICATION);
						}

						new NotificationAction().insertNotificationOneandAll(sessionUser,String.valueOf(tranferHistoryNew.getActionId()),"Wallet balance "+agentWallet.getTransactionType()+"ed","Wallet balance notification",NotificationInventoryTypeEnum.USER_WALLET.getId(),true,false,false,true,false,false);
						addActionMessage(getText("global.addAgentWalletsetwalletbalanceemail"));
					}
					else{
						addActionError(getText("global.addAgentWalletsetwalletbalanceemailfailed"));
					}
				} 
			}
		}  
		return SUCCESS;
	}

	public String showAgentDetailsByUserId(){
		logger.info("---------showCompanyDetailsBy ComapnyId--------------"+user.getId());
		CurrentProfile = DAO.GetUserProfile(user.getId());
		//sessionmap.put("agentDetailsObj", detailsObj);
		return SUCCESS; 

	}

	public String getCountryandlanguagelist()
	{
		currentCompany = (Company) sessionmap.get("Company");
		CountryList  = countryDAO.getCountryList();	
		LanguageList = countryDAO.getLanguageList();
		if(currentCompany.getCompanyRole()!=null && currentCompany.getCompanyRole().isCorporate())
		{
			designationList = DAO.GetUserDesignationList(currentCompany);
			try{
				setEmployeeDesignationsList(designationDao.getAllDesgNameList());
				setBandModelList(designationDao.getAllBandNameList());
			}catch (Exception e) {
			}
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ADD_NEW_EMPLOYEE, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo); 
		return SUCCESS;
	}


	@Override
	public User getModel() {		
		return user;
	}
	@Override
	public void setSession(Map<String, Object> sess) {
		sessionmap = (SessionMap<String, Object>) sess;

	}

	/*Craeted by raham.......for user mail details*/
	private MailStatus getMailRegStausData(User user,String mailType,String status){
		ms.setUserId(user.getId());
		ms.getfromMail();
		ms.setToMail(user.getEmail());
		ms.getSubject();
		ms.getBody();
		ms.getLinkAttached();
		ms.setEmailType(mailType);
		ms.setMailSendDate(user.getCreateddate());
		ms.setMailStatus(status);
		return ms;
	}

	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}


	public List<String> getUserroletype() {
		return userroletype;
	}

	public void setUserroletype(List<String> userroletype) {
		this.userroletype = userroletype;
	}


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getUserPreviousWalletBalance() {
		return userPreviousWalletBalance;
	}
	public void setUserPreviousWalletBalance(BigDecimal userPreviousWalletBalance) {
		this.userPreviousWalletBalance = userPreviousWalletBalance;
	}
	public BigDecimal getAllotedAmount() {
		return allotedAmount;
	}
	public void setAllotedAmount(BigDecimal allotedAmount) {
		this.allotedAmount = allotedAmount;
	}
	public String getTypeOfWallet() {
		return typeOfWallet;
	}
	public void setTypeOfWallet(String typeOfWallet) {
		this.typeOfWallet = typeOfWallet;
	}
	public String getPostAmount() {
		return postAmount;
	}
	public void setPostAmount(String postAmount) {
		this.postAmount = postAmount;
	}
	public List<User> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}
	public List<User> getWalletusersList() {
		return walletusersList;
	}
	public void setWalletusersList(List<User> walletusersList) {
		this.walletusersList = walletusersList;
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

	public Company getCurrentCompany() {
		return currentCompany;
	}
	public void setCurrentCompany(Company currentCompany) {
		this.currentCompany = currentCompany;
	}

	public void setLanguageList(List<Language> languageList) {
		this.LanguageList = languageList;
	}
	public User getCurrentProfile() {
		return CurrentProfile;
	}
	public void setCurrentProfile(User currentProfile) {
		CurrentProfile = currentProfile;
	}
	public List<User> getCompanyUsersList() {
		return companyUsersList;
	}
	public void setCompanyUsersList(List<User> companyUsersList) {
		this.companyUsersList = companyUsersList;
	}
	public List<UserDesignation> getDesignationList() {
		return designationList;
	}
	public void setDesignationList(List<UserDesignation> designationList) {
		this.designationList = designationList;
	}
	public String getUserdesignation() {
		return Userdesignation;
	}
	public void setUserdesignation(String userdesignation) {
		Userdesignation = userdesignation;
	}
	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}


	public List<Company> getAllcompanylist() {
		return allcompanylist;
	}


	public void setAllcompanylist(List<Company> allcompanylist) {
		this.allcompanylist = allcompanylist;
	}


	public String getFilterCompanyType() {
		return filterCompanyType;
	}


	public void setFilterCompanyType(String filterCompanyType) {
		this.filterCompanyType = filterCompanyType;
	}


	public User getUserLock() {
		return userLock;
	}


	public void setUserLock(User userLock) {
		this.userLock = userLock;
	}


	public String getLockID() {
		return lockID;
	}


	public void setLockID(String lockID) {
		this.lockID = lockID;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
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


	public Integer getDetailType() {
		return detailType;
	}


	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}


	public InputStream getInputStream() {
		return inputStream;
	}


	public List<EmployeeDesignationsModel> getEmployeeDesignationsList() {
		return employeeDesignationsList;
	}


	public void setEmployeeDesignationsList(List<EmployeeDesignationsModel> employeeDesignationsList) {
		this.employeeDesignationsList = employeeDesignationsList;
	}


	public List<EmployeeBandModel> getBandModelList() {
		return bandModelList;
	}


	public void setBandModelList(List<EmployeeBandModel> bandModelList) {
		this.bandModelList = bandModelList;
	}








}
