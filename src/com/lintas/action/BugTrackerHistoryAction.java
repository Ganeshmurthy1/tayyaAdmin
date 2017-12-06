package com.lintas.action;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.BugReportFilter;
import com.isl.admin.filter.dao.BugFilterDao;
import com.isl.admin.page.BugReportPage;
import com.lintas.admin.DAO.BugTrackerDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.BugTracker;
import com.lintas.admin.model.BugTrackerHistory;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.utility.BugTrackerUtility;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BugTrackerHistoryAction  extends ActionSupport  implements ModelDriven<BugTrackerHistory>, SessionAware{
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BugTrackerHistoryAction.class);
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap=null;
	private Long bugTrackerId=null;
	BugTrackerDao bugTrackerDao=new BugTrackerDao();
	BugTrackerHistory bugTrackerHistory=new BugTrackerHistory();
	private List<BugTrackerHistory> bugHistoryList=null;
	BugTrackerUtility bugTrackerUtility=new BugTrackerUtility();
	BugFilterDao bugFilterDao=new BugFilterDao();
	private Integer pageId;
	private Integer actionId;
	private Integer detailType;
	private Integer statusCode;
	private BugTracker bugTracker=new BugTracker();
	private BugReportPage bugReportPage=new BugReportPage();
	private BugReportFilter bugReportFilter = new BugReportFilter();

	public String editBugTracker() {
		setBugTrackerId(bugTrackerId);
		List<BugTrackerHistory> bugHistoryNewList = bugTrackerDao.bugTrackerHistoryList(bugTrackerId);
		if(bugHistoryNewList!=null&& bugHistoryNewList.size()>0){
			bugHistoryList=bugHistoryNewList;
		}
		setBugTrackerUtility(bugTrackerUtility);

		return SUCCESS;

	}

	public String listBugTracker() {
		User userSessionObj=(User)sessionMap.get("User");
		Company companySessionObj=(Company)sessionMap.get("Company");

		if(bugReportPage.getBugReportFilter().getCompanyId()>0){
			companySessionObj=new CompanyDAO().getCompanyProfile(bugReportPage.getBugReportFilter().getCompanyId());
		}
		if(bugReportPage.getBugReportFilter().getUserId()>0){
			userSessionObj=new UserDAO().GetUserProfile(bugReportPage.getBugReportFilter().getUserId());
		}
		bugReportFilter.setLoginCompany(companySessionObj);
		bugReportFilter.setLoginUser(userSessionObj);
		bugReportPage.setBugReportFilter(bugReportFilter);
		//call filters here
		bugReportPage=bugFilterDao.getBugTrackerListByFilter(bugReportPage);
		return SUCCESS;
	}


	public String editBugHistory(){
		User user=(User)sessionMap.get("User");
		BugTrackerHistory bugTrackerHistory = bugTrackerDao.getBugTrackerHistoryDetails(bugTrackerId);
		if(bugTrackerHistory!=null){
			bugTrackerHistory.setTransStartToWorkDate(DateConversion.convertDateToStringToDate(bugTrackerHistory.getStartToWorkDate()));
			bugTrackerHistory.setTransWorkFinishDate(DateConversion.convertDateToStringToDate(bugTrackerHistory.getWorkFinishDate()));
			bugTrackerHistory.setTransAssignedDate(DateConversion.convertDateToStringToDate(bugTrackerHistory.getAssignDate()));
			this.bugTrackerHistory=bugTrackerHistory;
		}
		return SUCCESS;
	}

	public String updateNewBugHistory(){
		User user=(User)sessionMap.get("User");
		BugTrackerHistory bugTrackerHistoryDetails = bugTrackerDao.getBugTrackerHistoryDetails(bugTrackerHistory.getId());
		if(bugTrackerHistory.getFilePath()==null || bugTrackerHistory.getFilePath().equals("") ){
			if(bugTrackerHistoryDetails.getFilePath()!=null && bugTrackerHistoryDetails.getFilePath().contains(".")){
				bugTrackerHistory.setFilePath(bugTrackerHistoryDetails.getFilePath());
			}
		}
		else{
			bugTrackerHistory.setFilePath(uploadBugFile(bugTrackerHistoryDetails.getId()));
		}
		//edited by basha
		if(bugTrackerHistory.getTransStartToWorkDate()!=null)
			bugTrackerHistory.setStartToWorkDate(DateConversion.StringToDate(bugTrackerHistory.getTransStartToWorkDate()));
		if(bugTrackerHistory.getTransWorkFinishDate()!=null)
			bugTrackerHistory.setWorkFinishDate(DateConversion.StringToDate(bugTrackerHistory.getTransWorkFinishDate()));
		if(bugTrackerHistory.getTransWorkFinishDate()!=null)
			bugTrackerHistory.setAssignDate(DateConversion.StringToDate(bugTrackerHistory.getTransAssignedDate()));


		bugTrackerHistory.setAssignDate(DateConversion.StringToDate(bugTrackerHistory.getTransAssignedDate()));
		bugTrackerHistory.setUsername(user.getUsername());
		bugTrackerHistory.setUserEmail(user.getEmail());
		if(bugTrackerHistory.getBugTracker().getDescription()!=null){
			bugTracker.setDescription(bugTrackerHistory.getBugTracker().getDescription());
			bugTrackerHistory.setBugTracker(bugTracker);
		}
		//ended by basha
		BugTrackerHistory newbBugTrackerHistory = bugTrackerDao.updateBugTrackerHistory(bugTrackerHistory);
		if(newbBugTrackerHistory!=null ){
			bugTrackerId=newbBugTrackerHistory.getBugTracker().getId();
			setBugTrackerId(bugTrackerId);
		}

		else{
			addActionError("We found some error. Try again.");
			return ERROR;
		}

		addActionMessage("Successfully Updated.");
		return SUCCESS;

	}

	//method for upload user  upload Passport Scan Copy  image file
	public String uploadBugFile(long id){
		logger.info("uploadBugFile id-------------"+id);
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
				for (UploadedFile cf : files) {
					logger.info("---uploadBugFile-------file length...-------"+cf.length());
					logger.info("uploadBugFile   and  file length..."+cf.length());
					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					logger.info("fileName..."+fileName);
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
		return imageName;

	}

	public List<BugTrackerHistory> getBugHistoryList() {
		return bugHistoryList;
	}


	public void setBugHistoryList(List<BugTrackerHistory> bugHistoryList) {
		this.bugHistoryList = bugHistoryList;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}


	public Long getBugTrackerId() {
		return bugTrackerId;
	}


	public void setBugTrackerId(Long bugTrackerId) {
		this.bugTrackerId = bugTrackerId;
	}


	public BugTracker getBugTracker() {
		return bugTracker;
	}


	public void setBugTracker(BugTracker bugTracker) {
		this.bugTracker = bugTracker;
	}
	public BugTrackerHistory getBugTrackerHistory() {
		return bugTrackerHistory;
	}
	public void setBugTrackerHistory(BugTrackerHistory bugTrackerHistory) {
		this.bugTrackerHistory = bugTrackerHistory;
	}
	@Override
	public BugTrackerHistory getModel() {
		// TODO Auto-generated method stub
		return bugTrackerHistory;
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
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public BugTrackerUtility getBugTrackerUtility() {
		return bugTrackerUtility;
	}
	public void setBugTrackerUtility(BugTrackerUtility bugTrackerUtility) {
		this.bugTrackerUtility = bugTrackerUtility;
	}

	public BugReportPage getBugReportPage() {
		return bugReportPage;
	}

	public void setBugReportPage(BugReportPage bugReportPage) {
		this.bugReportPage = bugReportPage;
	}

	public BugReportFilter getBugReportFilter() {
		return bugReportFilter;
	}

	public void setBugReportFilter(BugReportFilter bugReportFilter) {
		this.bugReportFilter = bugReportFilter;
	}


}
