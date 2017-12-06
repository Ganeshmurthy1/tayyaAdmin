package com.lintas.action;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.BugTrackerDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.BugTestCase;
import com.lintas.admin.model.BugTracker;
import com.lintas.admin.model.BugTrackerComment;
import com.lintas.admin.model.BugTrackerHistory;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.BugTrackerUtility;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BugTrackerAction extends ActionSupport implements ModelDriven<BugTracker> ,SessionAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BugTrackerAction.class);
	BugTrackerDao bugTrackerDao=new BugTrackerDao();
	BugTracker bugTracker=new BugTracker();
	List<BugTracker> bugTrackers=null;
	BugTrackerHistory bugTrackerHistory=new BugTrackerHistory();
	private List<BugTrackerHistory> bugHistoryList=new ArrayList<>();
	private String bugActiveStatus;
	SessionMap<String, Object> sessionMap=null;
	BugTrackerUtility bugTrackerUtility=new BugTrackerUtility();
	List<BugTestCase> bugTestCasesList= new ArrayList<>();
	List<BugTrackerComment> bugTrackerCommentList = new ArrayList<>();
	BugTestCase bugTestCase=new BugTestCase();
	UserDAO userDao=new UserDAO();
	private Long id;
	private Long bugTestCaseId=null;
	private Long bugTrackerhistoryId=null;


	public String addBugTracker() {
		setBugTrackerUtility(bugTrackerUtility);
		return SUCCESS;
	}

	public String saveBugTracker() {
		User user=(User)sessionMap.get("User");
		if(bugTracker!=null)
		{
			if(bugActiveStatus.equals("1")) 
				bugTracker.setActive(true);
			else 
				bugTracker.setActive(false);
			bugTracker.setCreatedBy(user.getId());
			bugTracker.setUpdatedBy(user.getId());
			bugTracker.setCreatedByUserName(user.getUsername());
			bugTracker.setCreatedByUserEmail(user.getEmail());


			bugTracker.setCreatedAt(new Timestamp(new Date().getTime()));
			if(StringUtils.isNotBlank(bugTracker.getTransAssignedDate()))
				bugTracker.setAssignDate(DateConversion.StringToDate(bugTracker.getTransAssignedDate()));
			if(StringUtils.isNotBlank(bugTracker.getTransStartToWorkDate()))
				bugTracker.setStartToWorkDate(DateConversion.StringToDate(bugTracker.getTransStartToWorkDate()));
			if(StringUtils.isNotBlank(bugTracker.getTransWorkFinishDate()))
				bugTracker.setWorkFinishDate(DateConversion.StringToDate(bugTracker.getTransWorkFinishDate()));
		}
		BugTracker bugTrackerObj=bugTrackerDao.insertBugTracker(bugTracker);
		if(bugTrackerObj!=null && bugTrackerObj.getId()>0)
		{
			bugTrackerObj.setReferenceNo(RandomConfigurationNumber.generateBugReferenceNumber(bugTrackerObj.getId()));

			bugTrackerObj= bugTrackerDao.updateBugTracker(bugTrackerObj);

			if(bugTrackerObj!=null)
			{
				buildBugTrackerHistoryItems(bugTracker,bugTrackerHistory,user);
				bugTrackerHistory.setBugTracker(bugTrackerObj);

				BugTrackerHistory newBugTrackerHistory =bugTrackerDao.insertBugTrackerHistory(bugTrackerHistory);
				if(newBugTrackerHistory!=null && newBugTrackerHistory.getId()>0)
				{
					newBugTrackerHistory.setFilePath(uploadBugFile(newBugTrackerHistory.getId()));
					BugTrackerHistory updatedBugTrackerHistory= bugTrackerDao.updateBugTrackerHistoryImagepath(newBugTrackerHistory);
					if(updatedBugTrackerHistory!=null && updatedBugTrackerHistory.getId()==newBugTrackerHistory.getId()){
						new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_CREATED);
						return SUCCESS;
					}
					else{
						addActionError("We found some error.Try Again.");
						return ERROR;	
					}
				}
				else{
					addActionError("We found some error.Try Again.");	
					return ERROR;	
				}
			}
			else{
				addActionError("We found some error.Try Again.");
				return ERROR;	
			}	
		}
		else{
			addActionError("We found some error.Try Again.");	
			return ERROR;	

		}
	}

	public String viewBugTracker(){
		BugTracker bugtrackerold=bugTrackerDao.getBugTrackerDetails(bugTracker.getId());
		if(bugtrackerold!=null)
		{
			bugTracker=bugtrackerold;
			bugHistoryList  = bugTrackerDao.bugTrackerHistoryList(bugTracker.getId());
			bugTrackerCommentList = bugTrackerDao.fetchBugTrackerCommentList(bugTracker.getId());
			bugTestCasesList = bugTrackerDao.fetchBugTrackerTestCaseList(bugTracker.getId());
			setBugTrackerUtility(bugTrackerUtility);
		}
		return SUCCESS;
	}

	public String editBugTracker(){
		BugTracker bugtrackerold=bugTrackerDao.getBugTrackerDetails(bugTracker.getId());
		if(bugtrackerold!=null)
		{
			bugTracker=bugtrackerold;
			bugTracker.setTransStartToWorkDate(DateConversion.convertDateToStringToDate(bugtrackerold.getStartToWorkDate()));
			bugTracker.setTransWorkFinishDate(DateConversion.convertDateToStringToDate(bugtrackerold.getWorkFinishDate()));
			bugTracker.setTransAssignedDate(DateConversion.convertDateToStringToDate(bugtrackerold.getAssignDate()));
			setBugTrackerUtility(bugTrackerUtility);
		}
		return SUCCESS;
	}

	public String updateBugTracker(){
		User user=(User)sessionMap.get("User");
		BugTracker bugtrackerold=bugTrackerDao.getBugTrackerDetails(bugTracker.getId());

		if(bugtrackerold!=null){
			bugtrackerold.setTitle(bugTracker.getTitle());
			bugtrackerold.setDescription(bugTracker.getDescription());
			bugtrackerold.setActive(bugTracker.isActive());
			bugtrackerold.setCreatedByUserName(user.getUsername());
			bugtrackerold.setCreatedByUserEmail(user.getEmail());
			bugtrackerold.setUpdatedBy(user.getId());
			bugtrackerold.setUpdatedAt(new Timestamp(new Date().getTime()));
			bugTrackerDao.updateBugTracker(bugtrackerold);
			addActionMessage("Successfully Updated");
		}else{
			addActionError("Updation Is Failed try again");
			return ERROR;
		}

		return SUCCESS;

	}

	public String updateBugTrackerStatus()
	{
		User user=(User)sessionMap.get("User");
		BugTracker bugTrackerFromDB=bugTrackerDao.getBugTrackerDetails(bugTracker.getId());	
		if(bugTrackerFromDB!=null)
		{
			if( bugTracker.isStatusChanged())
			{
				bugTrackerFromDB.setStatus(bugTracker.getStatus());
				bugTrackerFromDB.setUpdatedBy(user.getId());
				bugTracker = bugTrackerFromDB;
			}

			buildBugTrackerItems(bugTrackerFromDB,bugTracker,user);
			buildBugTrackerHistoryItems(bugTracker,bugTrackerHistory,user);

			BugTracker updatedBugTrackerObj= bugTrackerDao.updateBugTracker(bugTrackerFromDB);
			bugTrackerHistory.setBugTracker(updatedBugTrackerObj); 

			BugTrackerHistory newBugTrackerHistory =bugTrackerDao.insertBugTrackerHistory(bugTrackerHistory);
			if(newBugTrackerHistory!=null && newBugTrackerHistory.getId()>0){
				newBugTrackerHistory.setFilePath(uploadBugFile(newBugTrackerHistory.getId()));
				BugTrackerHistory updatedBugTrackerHistory= bugTrackerDao.updateBugTrackerHistoryImagepath(newBugTrackerHistory);
				if(updatedBugTrackerHistory!=null && updatedBugTrackerHistory.getId()==newBugTrackerHistory.getId()){

					buildAndInsertEmailForBugHistory(updatedBugTrackerHistory);
					addActionMessage("successfully created");	
					bugTrackerhistoryId=newBugTrackerHistory.getBugTracker().getId();
					setBugTrackerhistoryId(bugTrackerhistoryId);
					return SUCCESS;
				}
				else{
					addActionError("We found some error.Try Again.");	
					return ERROR;	
				}
			}
			else{
				addActionError("We found some error.Try Again.");	
				return ERROR;	
			}
		}
		else{
			return ERROR;	
		}
	}



	public String createNewBugHistory()
	{
		User user=(User)sessionMap.get("User");
		BugTracker bugTrackerFromDB=bugTrackerDao.getBugTrackerDetails(bugTracker.getId());	
		if(bugTrackerFromDB!=null)
		{
			buildBugTrackerItems(bugTrackerFromDB,bugTracker,user);
			buildBugTrackerHistoryItems(bugTracker,bugTrackerHistory,user);

			BugTracker updatedBugTrackerObj= bugTrackerDao.updateBugTracker(bugTrackerFromDB);
			bugTrackerHistory.setBugTracker(updatedBugTrackerObj); 

			BugTrackerHistory newBugTrackerHistory =bugTrackerDao.insertBugTrackerHistory(bugTrackerHistory);
			if(newBugTrackerHistory!=null && newBugTrackerHistory.getId()>0){
				newBugTrackerHistory.setFilePath(uploadBugFile(newBugTrackerHistory.getId()));
				BugTrackerHistory updatedBugTrackerHistory= bugTrackerDao.updateBugTrackerHistoryImagepath(newBugTrackerHistory);
				if(updatedBugTrackerHistory!=null && updatedBugTrackerHistory.getId()==newBugTrackerHistory.getId()){

					buildAndInsertEmailForBugHistory(updatedBugTrackerHistory);
					addActionMessage("successfully created");	
					bugTrackerhistoryId=newBugTrackerHistory.getBugTracker().getId();
					setBugTrackerhistoryId(bugTrackerhistoryId);
					return SUCCESS;
				}
				else{
					addActionError("We found some error.Try Again.");	
					return ERROR;	
				}
			}
			else{
				addActionError("We found some error.Try Again.");	
				return ERROR;	
			}
		}
		else{
			return ERROR;	
		}
	}

	public String listBugTracker() {
		List<BugTracker> bugTrackersList = bugTrackerDao.fetchBugTracker();
		List<BugTracker> bugTrackersListNew = new  LinkedList<>();
		User assignedby=null;
		User assignedto =null;
		if(bugTrackersList!=null){
			for(BugTracker bugTracker:bugTrackersList){
				if(bugTracker!=null && bugTracker.getBugTrackerHistoryList()!=null){
					Collections.reverse(bugTracker.getBugTrackerHistoryList());
					BugTrackerHistory bugTrackerHistory=bugTracker.getBugTrackerHistoryList().get(0);
					logger.info(bugTrackerHistory.getBugTracker().getId()+
							"getStatus--------------"+bugTrackerHistory.getStatus());
					if(bugTrackerHistory.getAssignedBy()>0){
						assignedby=new UserDAO().getUserByUserId(bugTrackerHistory.getAssignedBy());
						bugTracker.setAssignedByName(assignedby.getUsername());
					}
					if(bugTrackerHistory.getAssignTo()>0){
						assignedto=new UserDAO().getUserByUserId(bugTrackerHistory.getAssignTo());
						bugTracker.setAssignedToName(assignedto.getUsername());
					}
					bugTracker.setTag(bugTrackerHistory.getStatus());
				}
				bugTrackersListNew.add(bugTracker);
			}
			bugTrackers=bugTrackersListNew;
		}
		return SUCCESS;
	}

	public String addNewBugTestCase(){
		User user=(User)sessionMap.get("User");
		BugTracker bugTrackerNew=bugTrackerDao.getBugTrackerDetails(bugTracker.getId());
		boolean isInserted=false;
		if(bugTrackerNew!=null && bugTrackerNew.getId()!=null) 
		{
			isInserted=bugTrackerDao.insertBugTestCaseList(getBugTestCasesList(),bugTrackerNew,user);
		}	
		if(isInserted) 
			return SUCCESS;
		else
			return ERROR;

	}
	public String goTestCases(){
		return SUCCESS;
	}


	public String getTestCases(){
		BugTracker bugTrackerNew = bugTrackerDao.getBugTrackerDetails(bugTracker.getId());
		if(bugTrackerNew!=null){
			setBugTracker(bugTrackerNew);
		}

		/*List<BugTrackerHistory> bugTrackerHistories=bugTrackerDao.bugTrackerHistoryList(bugTrackerId);*/
		return SUCCESS;
	}
	public String testCasesVerify(){
		boolean isVerify=bugTrackerDao.isVerifyCheck(bugTracker.getId());
		if(isVerify!=false)
		{
			bugTestCase.setVerify(true);
			addActionMessage("successfully Verified");	
		}
		return SUCCESS;
	}

	public String editBugTestCase(){
		BugTestCase bugTestCase = bugTrackerDao.getBugTestCaseDetails(bugTracker.getId());
		if(bugTestCase!=null){
			this.bugTestCase=bugTestCase;
		}
		return SUCCESS;
	}

	public String addNewBugHistory(){
		setBugTrackerUtility(bugTrackerUtility);
		return SUCCESS;
	}



	//method for upload user  upload Passport Scan Copy  image file
	public String uploadBugFile(long id){
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
				//.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("png")|| fileType.equalsIgnoreCase("csv")|| fileType.equalsIgnoreCase("xlsx")
				if(files!=null && files.length>0)
				{
					for (UploadedFile cf : files) {
						fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."))+RandomConfigurationNumber.generateRandomNmmber();
						fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
						if(fileType!=null){
							String file_path = "/home/bug_tracker/";
							//String file_path = "D:\\bug_tracker\\";
							File fileToCreate = new File(file_path, fileName+"_"+id+"."+fileType);
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
							imageName =  fileName+"_"+id+"."+fileType;

						}
					}
				}
			}
		}
		return imageName;

	}


	private void buildBugTrackerHistoryItems(BugTracker bugTracker, BugTrackerHistory bugTrackerHistory, User user) {

		bugTrackerHistory.setAssignTo(bugTracker.getAssignTo());
		bugTrackerHistory.setAssignedBy(bugTracker.getAssignedBy());

		bugTrackerHistory.setLevel(bugTracker.getLevel());
		bugTrackerHistory.setStatus(bugTracker.getStatus());
		bugTrackerHistory.setBugType(bugTracker.getBugType());

		bugTrackerHistory.setDeveloperEstimatedHours(bugTracker.getTotalDeveloperEstimatedHours());
		bugTrackerHistory.setEstimatedHours(bugTracker.getTotalEstimatedHours());
		bugTrackerHistory.setWorkingHours(bugTracker.getTotalWorkingHours());
		bugTrackerHistory.setExtraHours(bugTracker.getTotalExtraHours());

		bugTrackerHistory.setCreatedAt(new Timestamp(new Date().getTime()));
		if(StringUtils.isNotBlank(bugTracker.getTransAssignedDate()))
			bugTrackerHistory.setAssignDate(DateConversion.StringToDate(bugTracker.getTransAssignedDate()));
		if(StringUtils.isNotBlank(bugTracker.getTransStartToWorkDate()))
			bugTrackerHistory.setStartToWorkDate(DateConversion.StringToDate(bugTracker.getTransStartToWorkDate()));
		if(StringUtils.isNotBlank(bugTracker.getTransWorkFinishDate()))
			bugTrackerHistory.setWorkFinishDate(DateConversion.StringToDate(bugTracker.getTransWorkFinishDate()));
		bugTrackerHistory.setUsername(user.getUsername());
		bugTrackerHistory.setUserEmail(user.getEmail());
	}

	private void buildBugTrackerItems(BugTracker bugTrackerFromDb, BugTracker bugTracker, User user) 
	{
		bugTrackerFromDb.setTitle(bugTracker.getTitle());
		bugTrackerFromDb.setDescription(bugTracker.getDescription());

		bugTrackerFromDb.setAssignTo(bugTracker.getAssignTo());
		bugTrackerFromDb.setAssignedBy(bugTracker.getAssignedBy());
		bugTrackerFromDb.setLevel(bugTracker.getLevel());
		bugTrackerFromDb.setStatus(bugTracker.getStatus());
		/*bugTrackerFromDb.setCreatedByUserName(bugTracker.getCreatedByUserName());
		bugTrackerFromDb.setCreatedByUserEmail(bugTracker.getCreatedByUserEmail());*/
		bugTrackerFromDb.setAssignDate(bugTracker.getAssignDate());
		bugTrackerFromDb.setStartToWorkDate(bugTracker.getStartToWorkDate());
		bugTrackerFromDb.setWorkFinishDate(bugTracker.getWorkFinishDate());
		bugTrackerFromDb.setTotalEstimatedHours(bugTracker.getTotalEstimatedHours());
		bugTrackerFromDb.setTotalDeveloperEstimatedHours(bugTracker.getTotalDeveloperEstimatedHours());
		bugTrackerFromDb.setTotalWorkingHours(bugTracker.getTotalWorkingHours());
		bugTrackerFromDb.setTotalExtraHours(bugTracker.getTotalExtraHours());
		bugTrackerFromDb.setBugType(bugTracker.getBugType());

		bugTrackerFromDb.setUpdatedBy(user.getId());
		if(StringUtils.isNotBlank(bugTracker.getTransAssignedDate()))
			bugTrackerFromDb.setAssignDate(DateConversion.StringToDate(bugTracker.getTransAssignedDate()));
		if(StringUtils.isNotBlank(bugTracker.getTransStartToWorkDate()))
			bugTrackerFromDb.setStartToWorkDate(DateConversion.StringToDate(bugTracker.getTransStartToWorkDate()));
		if(StringUtils.isNotBlank(bugTracker.getTransWorkFinishDate()))
			bugTrackerFromDb.setWorkFinishDate(DateConversion.StringToDate(bugTracker.getTransWorkFinishDate()));
	}

	private void buildAndInsertEmailForBugHistory(BugTrackerHistory updatedBugTrackerHistory) {
		if(updatedBugTrackerHistory.getStatus().equalsIgnoreCase("Created")){
			new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_CREATED);
		}
		if(updatedBugTrackerHistory.getStatus().equalsIgnoreCase("Assigned")){
			new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_ASSIGNED);
		}
		if(updatedBugTrackerHistory.getStatus().equalsIgnoreCase("Pending")){
			new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_PENDING);
		}
		if(updatedBugTrackerHistory.getStatus().equalsIgnoreCase("WorkInProgress")){
			new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_WORK_IN_PROGRESS);
		}

		if(updatedBugTrackerHistory.getStatus().equalsIgnoreCase("StillInProgress")){
			new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_STILL_IN_PROGRESS);
		}
		if(updatedBugTrackerHistory.getStatus().equalsIgnoreCase("Review")){
			new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_REVIEW);
		}
		if(updatedBugTrackerHistory.getStatus().equalsIgnoreCase("TestReview")){
			new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_TEST_REVIEW);
		}
		if(updatedBugTrackerHistory.getStatus().equalsIgnoreCase("Closed")){
			new CompanyDAO().insertEmail(String.valueOf(updatedBugTrackerHistory.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_CLOSED);
		}		
	}

	@Override
	public com.lintas.admin.model.BugTracker getModel() {
		return bugTracker;
	}
	public BugTracker getBugTracker() {
		return bugTracker;
	}
	public void setBugTracker(BugTracker bugTracker) {
		this.bugTracker = bugTracker;
	}
	public List<BugTracker> getBugTrackers() {
		return bugTrackers;
	}
	public void setBugTrackers(List<BugTracker> bugTrackers) {
		this.bugTrackers = bugTrackers;
	}
	public BugTrackerHistory getBugTrackerHistory() {
		return bugTrackerHistory;
	}
	public void setBugTrackerHistory(BugTrackerHistory bugTrackerHistory) {
		this.bugTrackerHistory = bugTrackerHistory;
	}
	public String getBugActiveStatus() {
		return bugActiveStatus;
	}
	public void setBugActiveStatus(String bugActiveStatus) {
		this.bugActiveStatus = bugActiveStatus;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	public List<BugTrackerHistory> getBugHistoryList() {
		return bugHistoryList;
	}
	public void setBugHistoryList(List<BugTrackerHistory> bugHistoryList) {
		this.bugHistoryList = bugHistoryList;
	}
	public BugTrackerUtility getBugTrackerUtility() {
		return bugTrackerUtility;
	}
	public void setBugTrackerUtility(BugTrackerUtility bugTrackerUtility) {
		this.bugTrackerUtility = bugTrackerUtility;
	}


	public List<BugTestCase> getBugTestCasesList() {
		return bugTestCasesList;
	}


	public void setBugTestCasesList(List<BugTestCase> bugTestCasesList) {
		this.bugTestCasesList = bugTestCasesList;
	}


	public BugTestCase getBugTestCase() {
		return bugTestCase;
	}


	public void setBugTestCase(BugTestCase bugTestCase) {
		this.bugTestCase = bugTestCase;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBugTestCaseId() {
		return bugTestCaseId;
	}
	public void setBugTestCaseId(Long bugTestCaseId) {
		this.bugTestCaseId = bugTestCaseId;
	}
	public Long getBugTrackerhistoryId() {
		return bugTrackerhistoryId;
	}
	public void setBugTrackerhistoryId(Long bugTrackerhistoryId) {
		this.bugTrackerhistoryId = bugTrackerhistoryId;
	}

	public List<BugTrackerComment> getBugTrackerCommentList() {
		return bugTrackerCommentList;
	}

	public void setBugTrackerCommentList(List<BugTrackerComment> bugTrackerCommentList) {
		this.bugTrackerCommentList = bugTrackerCommentList;
	}

}
