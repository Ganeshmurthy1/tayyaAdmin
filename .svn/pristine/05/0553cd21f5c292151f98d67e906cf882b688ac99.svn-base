package com.isl.admin.filter.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.BugReportFilter;
import com.isl.admin.page.BugReportPage;
import com.isl.admin.page.Page;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.BugReportData;
import com.lintas.admin.model.BugTracker;
import com.lintas.admin.model.BugTrackerHistory;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class BugFilterDao {
	/**
	 @author Shaik Basha
	 added date: 10-06-2017
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BugFilterDao.class);
	FlightOrderDao flightOrderDao=new FlightOrderDao();

	@SuppressWarnings("unchecked")
	public BugReportPage getBugTrackerListByFilter(BugReportPage bugReportPage) {
		List<BugReportData>  orderList=new ArrayList<>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteriaBugTrackerHistory = session.createCriteria(BugTracker.class);				
			Disjunction disjunction = Restrictions.disjunction();
			Conjunction conjunction=Restrictions.conjunction();
			if(bugReportPage!=null && bugReportPage.isFilterEnabled())
			{
				BugReportFilter bugReportFilter = bugReportPage.getBugReportFilter();
				Company company=null;
				User user=null;
				if(bugReportFilter.getCompanyId()>0) {
					company=new CompanyDAO().getCompanyProfile(bugReportFilter.getCompanyId());
					user=new UserDAO().GetUserProfile(bugReportFilter.getUserId());
				}
				else {
					company=bugReportFilter.getLoginCompany();
					user=bugReportFilter.getLoginUser();
				}
				List<String> companyIdList = flightOrderDao.getCompanyIdList(company, bugReportFilter.getReportType(), bugReportFilter.getCompanyName());
				List<Integer> companyIdListNew=new LinkedList<>();
				if(companyIdList!=null && companyIdList.size()>0){
					for(String id:companyIdList){
						companyIdListNew.add(Integer.parseInt( id));
					}
				}
				List<Integer> userIdList = flightOrderDao.getUserIdList(user,companyIdListNew,bugReportFilter.getReportType());
				if(userIdList == null || userIdList.size() <= 0)
				{
					bugReportPage.setAvailableItems(0);					
					bugReportPage.setItems(new ArrayList<BugReportData>());
					return bugReportPage;
				}
				if((bugReportFilter.getAssignTo()!=null && !bugReportFilter.getAssignTo().trim().equals("")) && (bugReportFilter.getAssignedBy() ==null  || bugReportFilter.getAssignedBy().trim().equals("")) ){
					disjunction.add(Restrictions.eq("assignTo",Integer.parseInt(bugReportFilter.getAssignTo())));
					criteriaBugTrackerHistory.add(Restrictions.in("assignTo", userIdList)).add(disjunction);
				}
				else if((bugReportFilter.getAssignedBy() !=null  && !bugReportFilter.getAssignedBy().trim().equals("")) && ((bugReportFilter.getAssignTo()==null || bugReportFilter.getAssignTo().trim().equals("")))){
					disjunction.add(Restrictions.eq("assignedBy",Integer.parseInt(bugReportFilter.getAssignedBy())));
					criteriaBugTrackerHistory.add(Restrictions.in("assignedBy", userIdList)).add(disjunction);
				}
				else if((bugReportFilter.getAssignedBy() !=null  && !bugReportFilter.getAssignedBy().trim().equals("")) && bugReportFilter.getAssignedBy() !=null  && !bugReportFilter.getAssignedBy().trim().equals("") ){
					conjunction.add(Restrictions.eq("assignTo",Integer.parseInt(bugReportFilter.getAssignTo()))).add(Restrictions.eq("assignedBy",Integer.parseInt(bugReportFilter.getAssignedBy())));
					conjunction.add(Restrictions.in("assignTo", userIdList)).add(Restrictions.in("assignedBy", userIdList));
					criteriaBugTrackerHistory.add(conjunction);
				}
				else{
					/*if(!(bugReportFilter.getShowtype()!=null && bugReportFilter.getShowtype().equalsIgnoreCase("ALL")))
					{
						disjunction.add(Restrictions.in("assignTo", userIdList));
						disjunction.add(Restrictions.in("assignedBy", userIdList));
						criteriaBugTrackerHistory.add(disjunction);
					}
*/
				}

				criteriaBugTrackerHistory.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteriaBugTrackerHistory.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				List<BugTracker> list = null;
				if(bugReportPage.getMaxItems()==Page.ALL_ITEMS){

					logger.info("MaxResults-------"+bugReportPage.getMaxItems());
					criteriaBugTrackerHistory = session.createCriteria(BugTracker.class);						
					criteriaBugTrackerHistory.add(disjunction);
					criteriaBugTrackerHistory.addOrder(Order.desc("id"));
					list = criteriaBugTrackerHistory.list();
					logger.info("Reports size------"+list.size());			
					bugReportPage.setAvailableItems(list.size());
					bugReportPage.setAvailablePages(1);

				}

				else{
					if(bugReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % bugReportPage.getMaxItems() == 0)?(availableItems / bugReportPage.getMaxItems()):((availableItems / bugReportPage.getMaxItems()) + 1);
						bugReportPage.setAvailableItems(availableItems);
						bugReportPage.setAvailablePages(availablePages);
					} 
					//Retrive report with pagination .......

					int pageIndexDb = (bugReportPage.getCurrentPageIndex() > 1)?bugReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * bugReportPage.getMaxItems();
					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+bugReportPage.getMaxItems());
						criteriaBugTrackerHistory = session.createCriteria(BugTracker.class);						
						criteriaBugTrackerHistory.add(disjunction);
						criteriaBugTrackerHistory.setFirstResult(itemIndex);
						criteriaBugTrackerHistory.setMaxResults(bugReportPage.getMaxItems());
						criteriaBugTrackerHistory.addOrder(Order.desc("id"));
						list = criteriaBugTrackerHistory.list();
						logger.info("Reports size------"+list.size());					
					}
				}
				if(list!=null && list.size()>0){
					for(BugTracker bugTracker:list){
						if(bugTracker!=null)
						{
							BugReportData bugTrackerData=new BugReportData();
							User assignedBy= new UserDAO().getUserByUserId(bugTracker.getAssignedBy());
							User assignedTo= new UserDAO().getUserByUserId(bugTracker.getAssignTo());
							User createdby= new UserDAO().getUserByUserId(bugTracker.getCreatedBy());
							User updatedby= new UserDAO().getUserByUserId(bugTracker.getUpdatedBy());
							if(assignedBy!=null){
								bugTrackerData.setAssignedByName(assignedBy.getUsername());
							}else{
								bugTrackerData.setAssignedByName("NA");
							}
							if(assignedTo!=null){
								bugTrackerData.setAssignedToName(assignedTo.getUsername());
							}else{
								bugTrackerData.setAssignedToName("NA");
							}
							if(createdby!=null){
								bugTrackerData.setCreatedByName(createdby.getUsername());
							}else{
								bugTrackerData.setCreatedByName("NA");
							}
							if(updatedby!=null){
								bugTrackerData.setUpdatedByName(updatedby.getUsername());
							}else{
								bugTrackerData.setUpdatedByName("NA");
							}

							bugTrackerData.setActive(bugTracker.isActive());
							bugTrackerData.setAssignDate(bugTracker.getAssignDate());
							bugTrackerData.setAssignTo(bugTracker.getAssignTo());
							bugTrackerData.setBugTracker(bugTracker);
							bugTrackerData.setBugType(bugTracker.getBugType());
							bugTrackerData.setCreateDate(DateConversion.convertDateToStringToDate(bugTracker.getCreatedAt()));
							bugTrackerData.setCreatedBynew(bugTracker.getCreatedBy());
							bugTrackerData.setDescription(bugTracker.getDescription());
							bugTrackerData.setDeveloperEstimatedHours(bugTracker.getTotalDeveloperEstimatedHours());
							bugTrackerData.setEstimatedHours(bugTracker.getTotalEstimatedHours());
							bugTrackerData.setExtraHoursForBug(bugTracker.getTotalExtraHours());
							bugTrackerData.setLevel(bugTracker.getLevel());
							bugTrackerData.setReferenceNo(bugTracker.getReferenceNo());
							bugTrackerData.setStartToWorkDate(bugTracker.getStartToWorkDate());
							bugTrackerData.setStatus(bugTracker.getStatus());
							bugTrackerData.setTag(bugTracker.getTag());
							bugTrackerData.setTitle(bugTracker.getTitle());
							bugTrackerData.setUpdatedBynew(bugTracker.getUpdatedBy());
							bugTrackerData.setWorkFinishDate(bugTracker.getWorkFinishDate());
							orderList.add(bugTrackerData);
						}
						bugReportPage.setItems(orderList);
					}

				}
				else
				{
					//current page is having empty items..
					bugReportPage.setAvailableItems(0);					
					bugReportPage.setItems(new ArrayList<BugReportData>());
				}
			}
			else
			{
				bugReportPage.setAvailableItems(0);
				bugReportPage.setAvailablePages(0);
				bugReportPage.setItems(new ArrayList<BugReportData>());
			}


		}
		catch (HibernateException e) {
			e.printStackTrace();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return bugReportPage;
	}

	@SuppressWarnings("unchecked")
	public BugReportPage getBugTrackerHistoryListByFilter(BugReportPage bugReportPage) {
		List<BugReportData>  orderList=new ArrayList<>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteriaBugTrackerHistory = session.createCriteria(BugTrackerHistory.class);				
			Disjunction disjunction = Restrictions.disjunction();
			Conjunction conjunction=Restrictions.conjunction();
			if(bugReportPage!=null && bugReportPage.isFilterEnabled())
			{
				BugReportFilter bugReportFilter = bugReportPage.getBugReportFilter();
				Company company=null;
				User user=null;
				if(bugReportFilter.getCompanyId()>0) {
					company=new CompanyDAO().getCompanyProfile(bugReportFilter.getCompanyId());
					user=new UserDAO().GetUserProfile(bugReportFilter.getUserId());
				}
				else {
					company=bugReportFilter.getLoginCompany();
					user=bugReportFilter.getLoginUser();
				}
				List<String> companyIdList = flightOrderDao.getCompanyIdList(company, bugReportFilter.getReportType(), bugReportFilter.getCompanyName());
				List<Integer> companyIdListNew=new LinkedList<>();
				if(companyIdList!=null && companyIdList.size()>0){
					for(String id:companyIdList){
						companyIdListNew.add(Integer.parseInt( id));
					}
				}
				List<Integer> userIdList = flightOrderDao.getUserIdList(user,companyIdListNew,bugReportFilter.getReportType());
				if(userIdList == null || userIdList.size() <= 0)
				{
					bugReportPage.setAvailableItems(0);					
					bugReportPage.setItems(new ArrayList<BugReportData>());
					return bugReportPage;
				}
				if((bugReportFilter.getAssignTo()!=null && !bugReportFilter.getAssignTo().trim().equals("")) && (bugReportFilter.getAssignedBy() ==null  || bugReportFilter.getAssignedBy().trim().equals("")) ){
					disjunction.add(Restrictions.eq("assignTo",Integer.parseInt(bugReportFilter.getAssignTo())));
					criteriaBugTrackerHistory.add(Restrictions.in("assignTo", userIdList)).add(disjunction);
				}
				else if((bugReportFilter.getAssignedBy() !=null  && !bugReportFilter.getAssignedBy().trim().equals("")) && ((bugReportFilter.getAssignTo()==null || bugReportFilter.getAssignTo().trim().equals("")))){
					disjunction.add(Restrictions.eq("assignedBy",Integer.parseInt(bugReportFilter.getAssignedBy())));
					criteriaBugTrackerHistory.add(Restrictions.in("assignedBy", userIdList)).add(disjunction);
				}
				else if((bugReportFilter.getAssignedBy() !=null  && !bugReportFilter.getAssignedBy().trim().equals("")) && bugReportFilter.getAssignedBy() !=null  && !bugReportFilter.getAssignedBy().trim().equals("") ){
					conjunction.add(Restrictions.eq("assignTo",Integer.parseInt(bugReportFilter.getAssignTo()))).add(Restrictions.eq("assignedBy",Integer.parseInt(bugReportFilter.getAssignedBy())));
					conjunction.add(Restrictions.in("assignTo", userIdList)).add(Restrictions.in("assignedBy", userIdList));
					criteriaBugTrackerHistory.add(conjunction);
				}
				else{
					if(user.getUserrole_id()!=null && !user.getUserrole_id().isTechHead())
					{
						disjunction.add(Restrictions.in("assignTo", userIdList));
						disjunction.add(Restrictions.in("assignedBy", userIdList));
						criteriaBugTrackerHistory.add(disjunction);
					}
				}
				criteriaBugTrackerHistory.addOrder(Order.desc("id"));
			}
			Long rowCount= (Long) criteriaBugTrackerHistory.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				List<BugTrackerHistory> list = null;
				if(bugReportPage.getMaxItems()==Page.ALL_ITEMS){

					logger.info("MaxResults-------"+bugReportPage.getMaxItems());
					criteriaBugTrackerHistory = session.createCriteria(BugTrackerHistory.class);						
					criteriaBugTrackerHistory.add(disjunction);
					criteriaBugTrackerHistory.addOrder(Order.desc("id"));
					list = criteriaBugTrackerHistory.list();
					logger.info("Reports size------"+list.size());			
					bugReportPage.setAvailableItems(list.size());
					bugReportPage.setAvailablePages(1);

				}

				else{
					if(bugReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % bugReportPage.getMaxItems() == 0)?(availableItems / bugReportPage.getMaxItems()):((availableItems / bugReportPage.getMaxItems()) + 1);
						bugReportPage.setAvailableItems(availableItems);
						bugReportPage.setAvailablePages(availablePages);
					} 
					//Retrive report with pagination .......

					int pageIndexDb = (bugReportPage.getCurrentPageIndex() > 1)?bugReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * bugReportPage.getMaxItems();
					logger.info("setFirstResult-------"+itemIndex);

					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+bugReportPage.getMaxItems());
						criteriaBugTrackerHistory = session.createCriteria(BugTrackerHistory.class);						
						criteriaBugTrackerHistory.add(disjunction);
						criteriaBugTrackerHistory.setFirstResult(itemIndex);
						criteriaBugTrackerHistory.setMaxResults(bugReportPage.getMaxItems());
						criteriaBugTrackerHistory.addOrder(Order.desc("id"));
						list = criteriaBugTrackerHistory.list();
						logger.info("Reports size------"+list.size());					
					}
				}
				if(list!=null && list.size()>0){
					for(BugTrackerHistory bugTrackerHistoryInner:list){
						BugTracker bugTracker =null;
						if(bugTrackerHistoryInner.getBugTracker()!=null){
							bugTracker =bugTrackerHistoryInner.getBugTracker();
							BugReportData bugFilterPage2=new BugReportData();
							User assignedBy= new UserDAO().getUserByUserId(bugTrackerHistoryInner.getAssignedBy());
							User assignedTo= new UserDAO().getUserByUserId(bugTrackerHistoryInner.getAssignTo());
							User createdby= new UserDAO().getUserByUserId(bugTracker.getCreatedBy());
							User updatedby= new UserDAO().getUserByUserId(bugTracker.getUpdatedBy());
							if(assignedBy!=null){
								bugFilterPage2.setAssignedByName(assignedBy.getUsername());
							}else{
								bugFilterPage2.setAssignedByName("NA");
							}
							if(assignedTo!=null){
								bugFilterPage2.setAssignedToName(assignedTo.getUsername());
							}else{
								bugFilterPage2.setAssignedToName("NA");
							}
							if(createdby!=null){
								bugFilterPage2.setCreatedByName(createdby.getUsername());
							}else{
								bugFilterPage2.setCreatedByName("NA");
							}
							if(updatedby!=null){
								bugFilterPage2.setUpdatedByName(updatedby.getUsername());
							}else{
								bugFilterPage2.setUpdatedByName("NA");
							}

							bugFilterPage2.setActive(bugTracker.isActive());
							bugFilterPage2.setAssignDate(bugTrackerHistoryInner.getAssignDate());
							bugFilterPage2.setAssignTo(bugTrackerHistoryInner.getAssignTo());
							bugFilterPage2.setBugTracker(bugTracker);
							bugFilterPage2.setBugType(bugTrackerHistoryInner.getBugType());
							bugFilterPage2.setCreateDate(DateConversion.convertDateToStringToDate(bugTracker.getCreatedAt()));
							bugFilterPage2.setCreatedBynew(bugTracker.getCreatedBy());
							bugFilterPage2.setDescription(bugTracker.getDescription());
							bugFilterPage2.setDeveloperEstimatedHours(bugTrackerHistoryInner.getDeveloperEstimatedHours());
							bugFilterPage2.setEstimatedHours(bugTrackerHistoryInner.getEstimatedHours());
							bugFilterPage2.setExtraHoursForBug(bugTrackerHistoryInner.getExtraHours());
							bugFilterPage2.setLevel(bugTrackerHistoryInner.getLevel());
							bugFilterPage2.setReferenceNo(bugTracker.getReferenceNo());
							bugFilterPage2.setStartToWorkDate(bugTrackerHistoryInner.getStartToWorkDate());
							bugFilterPage2.setStatus(bugTrackerHistoryInner.getStatus());
							bugFilterPage2.setTag(bugTracker.getTag());
							bugFilterPage2.setTitle(bugTracker.getTitle());
							bugFilterPage2.setUpdatedBynew(bugTracker.getUpdatedBy());
							bugFilterPage2.setWorkFinishDate(bugTrackerHistoryInner.getWorkFinishDate());
							orderList.add(bugFilterPage2);
						}
						bugReportPage.setItems(orderList);
					}

				}
				else
				{
					//current page is having empty items..
					bugReportPage.setAvailableItems(0);					
					bugReportPage.setItems(new ArrayList<BugReportData>());
				}
			}
			else
			{
				bugReportPage.setAvailableItems(0);
				bugReportPage.setAvailablePages(0);
				bugReportPage.setItems(new ArrayList<BugReportData>());
			}


		}
		catch (HibernateException e) {
			e.printStackTrace();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}

		return bugReportPage;
	}
}
