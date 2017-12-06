package com.lintas.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.BrowsingHistoryFilter;
import com.isl.admin.page.BrowsingHistoryPage;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.Dao.BrowsingHistoryDao;
import com.tayyarah.browsingHistory.model.BrowsingHistory;

public class BrowsingHistoryAction extends ActionSupport implements SessionAware,ModelDriven<BrowsingHistoryPage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* BrowsingHistoryPage browsingHistoryPage = new BrowsingHistoryPage();*/
	List<BrowsingHistory> browsingHistorylist = new ArrayList<BrowsingHistory>();
	BrowsingHistoryDao browsingHistoryDao = new BrowsingHistoryDao();
	SessionMap<String , Object> sessionMap;
	BrowsingHistoryPage browsingHistoryPage = new BrowsingHistoryPage();
	/*private BrowsingHistoryPage newBrowsingHistoryPage;*/
	public String Getallhistory()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		if(companySessionObj!=null){
		browsingHistoryPage = getBrowsingHistoryPage();		
		BrowsingHistoryFilter browsingHistoryFilter = browsingHistoryPage.getBrowsingHistoryFilter();
		browsingHistoryFilter.setCompanyName(companySessionObj.getCompanyname());
		//browsingHistoryFilter.setUserName(userSessionObj.getUsername());
		//browsingHistoryFilter.setUserName(userSessionObj.getFirstname());
		browsingHistoryPage.setBrowsingHistoryFilter(browsingHistoryFilter);
		BrowsingHistoryPage browsingHistoryPageNew = new BrowsingHistoryDao().GetBrowsingHistory(browsingHistoryPage);
		if(browsingHistoryPageNew!=null && browsingHistoryPageNew.getBrowsingHistoryList()!=null) {			
			List<BrowsingHistory> redefinedList = new ArrayList<BrowsingHistory>();
			for (BrowsingHistory browsingHistory : browsingHistoryPageNew.getBrowsingHistoryList()) {	
				browsingHistory.initActionName();
				browsingHistory.initPageName();
				browsingHistory.getCreatedAt();
				if(browsingHistory.getUserId()!=null){
					User userobj = new UserDAO().getUserByUserId(browsingHistory.getUserId());
					browsingHistory.setUser(userobj);
				}				
				redefinedList.add(browsingHistory);
			}
			browsingHistoryPageNew.setBrowsingHistoryList(redefinedList);
			browsingHistoryPage = browsingHistoryPageNew;
		}


		return SUCCESS;
		}
		else
		{
			return ERROR;
		}


	}

	@Override
	public BrowsingHistoryPage getModel() {		
		return browsingHistoryPage;

	}
	public BrowsingHistoryPage getBrowsingHistoryPage() {
		return browsingHistoryPage;
	}
	public void setBrowsingHistoryPage(BrowsingHistoryPage browsingHistoryPage) {
		this.browsingHistoryPage = browsingHistoryPage;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap<String, Object>) arg0;
	}

	public List<BrowsingHistory> getBrowsingHistorylist() {
		return browsingHistorylist;
	}

	public void setBrowsingHistorylist(List<BrowsingHistory> browsingHistorylist) {
		this.browsingHistorylist = browsingHistorylist;
	}

}
