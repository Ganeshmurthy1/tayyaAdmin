package com.lintas.frontuser;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class FrontUserAction  extends ActionSupport implements ModelDriven<CompanyFilterPage>, SessionAware{ 
	/**@author RAHAM 
	 * date:05-03-2016
	 */

	private static final long serialVersionUID = 1L;
	CompanyFilterPage  companyFilterPage = new CompanyFilterPage();
	SessionMap<String, Object>  sessionMap;
	FrontUserDao frontUserDao = new FrontUserDao();
	FrontUserDetail frontUserDetail = new FrontUserDetail();
	Long frontUserid;
	//String id;

	public String loadAllFrontUsers(){
		 
		
		Company companySessionObj=(Company)sessionMap.get("Company");
		User user = (User)sessionMap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilterPage.setCompanyFilter(companyFilter);
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setLoginUser(user); 
		companyFilter.setReportType(companyFilterPage.getCompanyFilter().getReportType());
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=frontUserDao.loadAllFrontUsers(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getFrontUserDetailList()!=null){
			companyFilterPage= newCompanyFilterPage;
		}
		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.B2C_USERS.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(user.getCompanyid()),"CRM B2C show front users filter submit  click ");
			
		return SUCCESS;
	}
	
public String showFrontUserDetails(){ 
		System.out.println("----------------------"+frontUserid);
		FrontUserDetail selectedReportItem=frontUserDao.getFrontUserDetail(frontUserid);
	
		if(selectedReportItem!=null){ 
			frontUserDetail = selectedReportItem;
		}
		return SUCCESS; 
	}
	@Override
	public CompanyFilterPage getModel() {
		// TODO Auto-generated method stub
		return companyFilterPage;
	}
	public CompanyFilterPage getCompanyFilterPage() {
		return companyFilterPage;
	}
	public void setCompanyFilterPage(CompanyFilterPage companyFilterPage) {
		this.companyFilterPage = companyFilterPage;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
		
	}
/*	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}*/
	/*public FrontUserDetail getFrontUser() {
		return frontUser;
	}
	public void setFrontUser(FrontUserDetail frontUser) {
		this.frontUser = frontUser;
	}*/
	public FrontUserDetail getFrontUserDetail() {
		return frontUserDetail;
	}
	public void setFrontUserDetail(FrontUserDetail frontUserDetail) {
		this.frontUserDetail = frontUserDetail;
	}
	public Long getFrontUserid() {
		return frontUserid;
	}
	public void setFrontUserid(Long frontUserid) {
		this.frontUserid = frontUserid;
	}
}