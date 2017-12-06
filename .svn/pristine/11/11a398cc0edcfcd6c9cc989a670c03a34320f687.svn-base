package com.lintas.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.filter.dao.CompanyFilterDao;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.DAO.CompanyDAO;
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

public class CompanyFilterAction extends ActionSupport implements ModelDriven<CompanyFilterPage>,SessionAware{
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyFilterAction.class);
	SessionMap<String, Object> sessionmap;
	private  CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	CompanyDAO ComregDAO = new CompanyDAO();
	CompanyFilterDao companyFilterDao=new CompanyFilterDao();
	private String approvalType;
	private Integer pageId;
	private Integer actionId;
	public Integer getDetailType() {
		return detailType;
	}

	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}

	private Integer detailType;
	
	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	private Integer statusCode;
	
	public String getAllCompaniesList()
	{
		
		Company companySessionObj=(Company)sessionmap.get("Company");
		User userSessionObj=(User)sessionmap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setLoginUser(userSessionObj);
		companyFilter.setCompanyType(getText("global.Wholesaler"));
		companyFilterPage.setCompanyFilter(companyFilter);

		CompanyFilterPage newCompanyFilterPage=companyFilterDao.getCompanyListByCompanyUserId(companyFilterPage);
		List<Company> newCompaniesList= newCompanyFilterPage.getCompanyList();//new ArrayList<Company>();
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getCompanyList()!=null && newCompanyFilterPage.getCompanyList().size()>0)
		{
			User user = (User)sessionmap.get("User");
			HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.SUBMIT.getId(), String.valueOf(user.getCompanyid()),"AgentSalesLeadCompanyList filter submit  click");
			 
		}

		//		companyFilterPage.setCompanyList(newCompaniesList);
		if(newCompaniesList != null && newCompaniesList.size()>0){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			HistoryInfo  historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(newCompaniesList),"marketng sales leads company list filter submit click ");
		}
		else{
			statusCode = ActionStatusEnum.FAILED.getCode();	
			HistoryInfo  historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(newCompaniesList),"marketng sales leads company list filter submit click ");
		}
		if(pageId == null)		
			pageId = BrowsingOptionPageEnum.ALL_COMPANY.getId();
		
		/*HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo()); 		
		historyInfo.changeNature(BrowsingOptionPageEnum.getPageEnum(pageId), BrowsingOptionActionEnum.getActionEnum(actionId), ActionStatusEnum.getStatusEnum(statusCode)); 
		historyInfo.setDetails(BrowsingHistoryDetailTypeEnum.getBrowsingHistoryDetailTypeEnum(0), "d34343", "descrption");
		 */
		//HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, pageId, actionId, statusCode, detailType, inventoryId, description);

		HistoryInfo historyInfo = new HistoryManager().inSertHistory(sessionmap, pageId, actionId, statusCode);
		
		/*HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ALL_COMPANY, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);*/
		// historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_FILTER_SUBMIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.FILTER_SUBMIT.getId(), String.valueOf(newCompaniesList),"marketng sales leads company list filter submit click ");
		
		return SUCCESS;
	}

	public String getApprovalCompanyList()
	{
		User user=(User)sessionmap.get("User");
		setApprovalType("YES");
		Company companySessionObj=(Company)sessionmap.get("Company");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setCompanyType(getText("global.Wholesaler"));
		companyFilter.setApproveType(getApprovalType());
		companyFilterPage.setCompanyFilter(companyFilter);

		CompanyFilterPage newCompanyFilterPage=companyFilterDao.getApprovalCompanyList(companyFilterPage);
		logger.info("----------getApprovalCompanyList-------------------------"+newCompanyFilterPage.getApprovalCompanyList().size());
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getApprovalCompanyList()!=null){
			companyFilterPage= newCompanyFilterPage;
		}
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.APPROVED_COMPANIES, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);
		historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.MARKETING_SALES_LEADS_COMPANY_LIST.getId(), BrowsingOptionActionEnum.ACTION_APPROVED_COMPANIES.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.APPROVED_COMPANIES.getId(), String.valueOf(user.getCompanyid()),"marketng sales leads approved companies list click ");
		
		return SUCCESS;
	}
	public String getNonApprovalCompaniesList()
	{
		setApprovalType("NOT");
		Company companySessionObj=(Company)sessionmap.get("Company");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setCompanyType(getText("global.Wholesaler"));
		companyFilter.setApproveType(getApprovalType());
		companyFilterPage.setCompanyFilter(companyFilter);

		CompanyFilterPage newCompanyFilterPage=companyFilterDao.getApprovalCompanyList(companyFilterPage);
		logger.info("----------getApprovalCompanyList-------------------------"+newCompanyFilterPage.getApprovalCompanyList().size());
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getApprovalCompanyList()!=null){
			companyFilterPage= newCompanyFilterPage;
			statusCode = ActionStatusEnum.SUCCESS.getCode();
		}
		else
		{
			statusCode = ActionStatusEnum.FAILED.getCode();	
		}

		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());	
			
		if(pageId == null)		
			pageId = BrowsingOptionPageEnum.NEW_COMPANY_APPROVALS.getId();
		historyInfo.changeNature(BrowsingOptionPageEnum.getPageEnum(pageId), BrowsingOptionActionEnum.getActionEnum(actionId), ActionStatusEnum.getStatusEnum(statusCode)); 
		
		//historyInfo.changeNature(BrowsingOptionPageEnum.NEW_COMPANY_APPROVALS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;
	}


	public String getAllDistributorslist()
	{
		Company companySessionObj=(Company)sessionmap.get("Company");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setCompanyType(getText("global.Wholesaler"));
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage = companyFilterDao.getAllDistributors(companyFilterPage);
		logger.info("----------getCompanyList-------------------------"+newCompanyFilterPage.getCompanyList().size());
		List<Company> distributorList = new ArrayList<>();
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getCompanyList()!=null){
			/*for (Company company : newCompanyFilterPage.getCompanyList()) {
				if(company.getCompanyRole().isDistributor()){
					distributorList.add(company);
				}
					
			}
			newCompanyFilterPage.setCompanyList(distributorList);*/
			companyFilterPage= newCompanyFilterPage;

		}
		return SUCCESS;
	}

	
	public String getAllCorporatelist()
	{
		Company companySessionObj=(Company)sessionmap.get("Company");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setCompanyType(getText("global.Corporate"));
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=companyFilterDao.getAllCorporate(companyFilterPage);
		logger.info("----------getCorporateList-------------------------"+newCompanyFilterPage.getCompanyList().size());
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getCompanyList()!=null){
			companyFilterPage= newCompanyFilterPage;

		}
		return SUCCESS;
	}


	public String showAllAgenciesByCompanyId(){
		Company companySessionObj=(Company)sessionmap.get("Company");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setCompanyType(getText("global.Wholesaler"));
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=companyFilterDao.getAllAgencyCompanyIds(companyFilterPage);
		List<Company> agenciesList = new ArrayList<>();
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getCompanyList()!=null){
			for (Company company : newCompanyFilterPage.getCompanyList()) {
				if(company.getCompanyRole().isAgent()){
					agenciesList.add(company);
				}
				newCompanyFilterPage.setCompanyList(agenciesList);	
			}
		}
		companyFilterPage= newCompanyFilterPage;
		return SUCCESS;
	}


	@Override
	public CompanyFilterPage getModel() {
		// TODO Auto-generated method stub
		return companyFilterPage;
	}
	public CompanyFilterPage getCompanyFilterPage() {
		if(companyFilterPage==null){
			companyFilterPage=new CompanyFilterPage(); 
		}
		return companyFilterPage;
	}

	public void setCompanyFilterPage(CompanyFilterPage companyFilterPage) {
		this.companyFilterPage = companyFilterPage;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}


}
