package com.lintas.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.BugTrackerDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.BugTestCase;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BugTestCaseAction extends ActionSupport implements ModelDriven<BugTestCase> ,SessionAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BugTestCaseAction.class);
	SessionMap<String, Object> sessionMap=null;
	BugTrackerDao bugTrackerDao=new BugTrackerDao();
	BugTestCase bugTestCase=new BugTestCase();
	UserDAO userDao=new UserDAO();
	private Long bugTestCaseId=null;
	private Long id=null;

	public String updateBugTestCase(){
		User user=(User)sessionMap.get("User");
		bugTestCaseId=bugTestCase.getId();
		BugTestCase bugTestCaseToUpdate = bugTrackerDao.showBugTestCase(bugTestCaseId);
		if(bugTestCaseToUpdate!=null)  { 
			bugTestCaseToUpdate.setUpdatedAt(new Timestamp(new Date().getTime()));
			bugTestCaseToUpdate.setTestCase(bugTestCase.getTestCase());
			bugTestCaseToUpdate.setUpdatedBy(user.getId());
			bugTrackerDao.updateBugTestCase(bugTestCaseToUpdate);
			addActionMessage("Successfully Updated.");
		}
		else{
			addActionError("We found some error. Try again.");
		}
		return SUCCESS;

	}


	public String deleteBugTestCase(){
		bugTestCaseId=bugTestCase.getId();
		bugTrackerDao.deleteBugTestCase(id);
		addActionMessage("Successfully Deleted.");
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}




	@Override
	public BugTestCase getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public BugTestCase getBugTestCase() {
		return bugTestCase;
	}

	public void setBugTestCase(BugTestCase bugTestCase) {
		this.bugTestCase = bugTestCase;
	}

	public Long getBugTestCaseId() {
		return bugTestCaseId;
	}

	public void setBugTestCaseId(Long bugTestCaseId) {
		this.bugTestCaseId = bugTestCaseId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}





}
