package com.lintas.action;

import java.io.File;
import java.io.IOException;
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

import com.lintas.admin.DAO.BugTrackerDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.BugTracker;
import com.lintas.admin.model.BugTrackerComment;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.config.RandomConfigurationNumber;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BugTrackerCommentAction extends ActionSupport implements ModelDriven<BugTrackerComment> ,SessionAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BugTrackerCommentAction.class);
	BugTrackerDao bugTrackerDao=new BugTrackerDao();
	BugTracker bugTracker=new BugTracker();
	BugTrackerComment bugTrackerComment=new BugTrackerComment();

	List<BugTrackerComment> bugTrackerCommentList = new ArrayList<>();

	SessionMap<String, Object> sessionMap=null;
	UserDAO userDao=new UserDAO();
	Long bugTrackerId;

	public String saveBugTrackerComment() {
		User user=(User)sessionMap.get("User");
		if(bugTrackerComment!=null)
		{
			bugTrackerComment.setCreatedBy(user.getId());
			bugTrackerComment.setUpdatedBy(user.getId());
			bugTrackerComment.setCreatedByUserName(user.getUsername());
			bugTrackerComment.setCreatedByUserEmail(user.getEmail());
			bugTrackerComment.setCreatedAt(new Timestamp(new Date().getTime()));
		}
		BugTrackerComment bugTrackerCommentFromDB=bugTrackerDao.insertBugTrackerComment(bugTrackerComment);
		if(bugTrackerCommentFromDB!=null && bugTrackerCommentFromDB.getId()>0)
		{
			BugTracker bugTrackerFromDB=bugTrackerDao.getBugTrackerDetails(bugTrackerId);
			bugTrackerCommentFromDB.setBugTracker(bugTrackerFromDB);

			bugTrackerCommentFromDB.setFilePath(uploadBugFile(bugTrackerFromDB.getId()));
			bugTrackerCommentFromDB= bugTrackerDao.updateBugTrackerComment(bugTrackerCommentFromDB);
			new CompanyDAO().insertEmail(String.valueOf(bugTrackerCommentFromDB.getId().longValue()), 0, Email.EMAIL_TYPE_BUG_TRACKER_HISTORY_CREATED);
			return SUCCESS;
		}
		else{
			addActionError("We found some error.Try Again.");	
			return ERROR;	

		}
	}


	/*public String updateBugTrackerComment(){
		BugTrackerComment bugTrackerCommentFromDB=bugTrackerDao.getBugTrackerComment(bugTrackerComment.getId());
		User user=(User)sessionMap.get("User");
		if(bugTrackerComment!=null)
		{
			bugTrackerCommentFromDB.setUpdatedBy(user.getId());
			bugTrackerCommentFromDB.setComments(bugTrackerComment.getComments());
			bugTrackerCommentFromDB= bugTrackerDao.updateBugTrackerComment(bugTrackerCommentFromDB);
		}
		return SUCCESS;
	}*/

	/*public String listBugTrackerComment() {
		bugTrackerCommentList = bugTrackerDao.fetchBugTrackerCommentList();
		return SUCCESS;
	}*/



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


	public BugTracker getBugTracker() {
		return bugTracker;
	}
	public void setBugTracker(BugTracker bugTracker) {
		this.bugTracker = bugTracker;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}


	@Override
	public BugTrackerComment getModel() {
		// TODO Auto-generated method stub
		return bugTrackerComment;
	}


	public List<BugTrackerComment> getBugTrackerCommentList() {
		return bugTrackerCommentList;
	}


	public void setBugTrackerCommentList(List<BugTrackerComment> bugTrackerCommentList) {
		this.bugTrackerCommentList = bugTrackerCommentList;
	}


	public Long getBugTrackerId() {
		return bugTrackerId;
	}


	public void setBugTrackerId(Long bugTrackerId) {
		this.bugTrackerId = bugTrackerId;
	}

}
