package com.tayyarah.browsingHistory.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.BrowsingHistoryFilter;
import com.isl.admin.page.BrowsingHistoryPage;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.tayyarah.browsingHistory.model.BrowsingHistory;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetail;

public class BrowsingHistoryDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BrowsingHistoryDao.class);

	public BrowsingHistory insert(BrowsingHistory browsingHistory){
		Session session=null;
		Transaction tx=null;
		try{
			if(browsingHistory!=null){
				session=HibernateUtil.getSessionFactory().openSession();
				tx = session.beginTransaction();	
				if(browsingHistory.getDetails() != null)
				{
					List<BrowsingHistoryDetail> browsingHistoryDetails = browsingHistory.getDetails();
					for (BrowsingHistoryDetail browsingHistoryDetail : browsingHistoryDetails) {
						browsingHistoryDetail.setHistory(browsingHistory);
						session.save(browsingHistoryDetail);
					}					
				} 
				session.save(browsingHistory); 
				tx.commit();
			}
		}
		catch(HibernateException he){
			//logger.info("HibernateException "+he);
			he.printStackTrace();
			if(tx!=null) 
				tx.rollback();
			//throw new RuntimeException("DB is not connecting ,please make connections properly.");
		}
		finally {
			if(session!=null)
				session.close();
		}
		return browsingHistory;		
	}
	public List<BrowsingHistory> getBrowsingHistory(){
		List<BrowsingHistory> list = new ArrayList<BrowsingHistory>();
		int availablePages = 0;
		int availableItems = 0;
		Session session=null;

		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BrowsingHistory.class);			
			list=criteria.list();
		}
		catch(HibernateException he){
			//logger.info("HibernateException "+he);
			he.printStackTrace();

		}
		finally {
			if(session!=null)
				session.close();
		}
		return list;		

	}
	public int GetuserId(BrowsingHistoryPage browsingHistoryPage,String username)
	{
		int userid = 0;
		//logger.info("browsingHistoryPage.getBrowsingHistoryList()" +browsingHistoryPage.getBrowsingHistoryList().size());
		for (BrowsingHistory browsingHistory : browsingHistoryPage.getBrowsingHistoryList()) {	
			//logger.info("browsingHistory user" +browsingHistory.getUser().getUsername());
			if(browsingHistory.getUser()!= null &&  browsingHistory.getUser().getUsername().equalsIgnoreCase(username)){
				userid = browsingHistory.getUser().getId();
			}
		}
		return userid;
	}
	
	public BrowsingHistoryPage GetBrowsingHistory(BrowsingHistoryPage browsingHistoryPage)
	{
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BrowsingHistory.class);
			Conjunction conjunction = Restrictions.conjunction();
			BrowsingHistoryFilter browsingHistoryFilter =null;
			if(browsingHistoryPage!=null && browsingHistoryPage.isFilterEnabled())
			{

				browsingHistoryFilter = browsingHistoryPage.getBrowsingHistoryFilter();
			
				if(browsingHistoryFilter != null && browsingHistoryFilter.getUserName()!=null && !browsingHistoryFilter.getUserName().equalsIgnoreCase(""))
				{				
					UserDAO userdao = new UserDAO();
					User user = userdao.getUserByUserName(browsingHistoryFilter.getUserName());
					logger.info("##########userid  added----"+user.getId());
					conjunction.add(Restrictions.eq("userId", user.getId()));					
				}	
				criteria.add(conjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(browsingHistoryPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % browsingHistoryPage.getMaxItems() == 0)?(availableItems / browsingHistoryPage.getMaxItems()):((availableItems / browsingHistoryPage.getMaxItems()) + 1);
					browsingHistoryPage.setAvailableItems(availableItems);
					browsingHistoryPage.setAvailablePages(availablePages);
				} 
				int pageIndexDb = (browsingHistoryPage.getCurrentPageIndex() > 1)?browsingHistoryPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * browsingHistoryPage.getMaxItems();
				logger.info("setFirstResult-------"+itemIndex);
				List<BrowsingHistory> browsingHistories =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(BrowsingHistory.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(browsingHistoryPage.getMaxItems());
					browsingHistories = criteria.list();
					logger.info("browsingHistories size------"+browsingHistories.size());					
				}
				browsingHistoryPage.setBrowsingHistoryList(browsingHistories);
				
			}	
			else
			{
				browsingHistoryPage.setAvailableItems(0);
				browsingHistoryPage.setAvailablePages(0);
				browsingHistoryPage.setBrowsingHistoryList(new ArrayList<BrowsingHistory>());
			}

		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());

		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}	
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return browsingHistoryPage;

	}
	
	/*public List<BrowsingHistory> filterBrowsingHistory(BrowsingHistory browsingHistory)
	{
		List<BrowsingHistory> browsingHistory = null;
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(BrowsingHistory.class);
			if(!browsingHistory.getBrowsingHistory().equals("") && !browsingHistory.getBrowsingHistory().equals("")){
				cr.add(Restrictions.eq("country", airport.getCountry()));
				cr.add(Restrictions.eq("airport_code", airport.getAirport_code()));
			}
			if(airport.getCountry().equals("") && !airport.getAirport_code().equals("")){
				cr.add(Restrictions.eq("airport_code", airport.getAirport_code()));
			}
			if(airport.getAirport_code().equals("") && !airport.getCountry().equals("")){
				cr.add(Restrictions.eq("country", airport.getCountry()));
			}
			browsingHistory = cr.list();
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return browsingHistory;
	}
	 */


}
