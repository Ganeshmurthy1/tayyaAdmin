package com.lintas.admin.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.model.BugTestCase;
import com.lintas.admin.model.BugTracker;
import com.lintas.admin.model.BugTrackerComment;
import com.lintas.admin.model.BugTrackerHistory;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.DateConversion;

public class BugTrackerDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(BugTrackerDao.class);
	public BugTracker  insertBugTracker(BugTracker backtracker){
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(backtracker);
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return backtracker;
	}

	public boolean isVerifyCheck(Long id){
		Session session=null;
		Transaction tx=null;
		boolean isVerify=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			BugTestCase  bugTestCaseNew=(BugTestCase) session.get(BugTestCase.class,id);
			bugTestCaseNew.setVerify(true);
			session.saveOrUpdate(bugTestCaseNew);
			tx.commit();
			isVerify=true;
		}

		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return isVerify;

	}


	public boolean  insertBugTestCaseList(List<BugTestCase> bugTestCaseList,BugTracker bugTracker, User user){
		Session session=null;
		Transaction tx=null;
		boolean isInserted=false;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			if(bugTestCaseList!=null && bugTestCaseList.size()>0){
				for (BugTestCase bugTestCase:bugTestCaseList) {
					bugTestCase.setCreatedByUserName(user.getUsername());
					bugTestCase.setUpdatedBy(user.getId());
					bugTestCase.setCreatedBy(user.getId());
					bugTestCase.setCreatedAt(new Timestamp(new Date().getTime()));
					bugTestCase.setBugTracker(bugTracker);
					session.save(bugTestCase);
					isInserted=true;
				}
			}

			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return isInserted;
	}


	public BugTestCase getBugTestCaseDetails(Long id) {
		// TODO Auto-generated method stub
		BugTestCase  bugTestCase=null;
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTestCase.class);
			criteria.add(Restrictions.eq("id", id));
			bugTestCase=(BugTestCase) criteria.uniqueResult();
			tx.commit();

		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTestCase;
	}

	public void updateBugTestCase(BugTestCase bugTestCase) {
		Session session=null;
		Transaction tx=null;

		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.update(bugTestCase);
			tx.commit();
		}
		catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}






	public BugTestCase showBugTestCase(Long id ) {
		Session session = null;
		Transaction tx=null;
		BugTestCase bugTestCase=null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			bugTestCase=(BugTestCase) session.get(BugTestCase.class, id);
		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
		return bugTestCase;
	}


	public void deleteBugTestCase(Long id ) {
		Session session = null;
		Transaction tx=null;
		String hql="delete from BugTestCase bugtest  where bugtest.id=:id";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery(hql);
			query.setParameter("id", id);
			int result = query.executeUpdate();
			tx.commit();

		} catch (HibernateException e) {
			if(tx!=null){
				tx.rollback();	
			}
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		} finally {
			if (session != null && session.isOpen())
				session.close();			
		}
	}

	public  BugTrackerHistory  insertBugTrackerHistory(BugTrackerHistory bugTrackerHistory){

		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(bugTrackerHistory);
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTrackerHistory;

	}
	public  List<BugTracker> fetchBugTracker(){
		List<BugTracker> newBugTrackerList= new ArrayList<>();
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTracker.class);
			criteria.addOrder(Order.desc("id"));
			List<BugTracker> bugTrackerList=criteria.list();
			tx.commit();
			if(bugTrackerList!=null && bugTrackerList.size()>0){
				for(BugTracker bugTracker:bugTrackerList){
					User createdBy=new UserDAO().getUserDetailsByUserId(bugTracker.getCreatedBy()).get(0);
					User updatedBy=new UserDAO().getUserDetailsByUserId(bugTracker.getUpdatedBy()).get(0);
					bugTracker.setCreatedByName(createdBy.getUsername());
					bugTracker.setUpdatedByName(updatedBy.getUsername());
					bugTracker.setCreateDate(DateConversion.convertDateToStringToDate(bugTracker.getCreatedAt()));
					newBugTrackerList.add(bugTracker);

				}
			}

		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return newBugTrackerList;	

	}
	public BugTracker updateBugTracker(BugTracker bugTrackerObj) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.update(bugTrackerObj);
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTrackerObj;


	}



	public BugTrackerHistory updateBugTrackerHistoryImagepath(BugTrackerHistory bugTrackerHistoryObj) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(bugTrackerHistoryObj);
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTrackerHistoryObj;
	}
	public   BugTracker getBugTrackerDetails(Long id) {
		BugTracker  bugTracker=null;
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTracker.class);
			criteria.add(Restrictions.eq("id", id));
			bugTracker=(BugTracker) criteria.uniqueResult();
			tx.commit();

		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTracker;	
	}




	public  List<BugTrackerHistory> bugTrackerHistoryList(Long bugTrackerId) {
		// TODO Auto-generated method stub
		List<BugTrackerHistory>  bugTrackerHistoryList=null;
		Session session = null;
		Transaction tx=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTrackerHistory.class);
			criteria.add(Restrictions.eq("bugTracker.id", bugTrackerId));
			criteria.addOrder(Order.desc("createdAt"));
			bugTrackerHistoryList=criteria.list();
			tx.commit();

		}
		catch(Exception e){
			logger.error("---------Exception---------"+e.getMessage());
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return bugTrackerHistoryList;
	}


	public BugTrackerHistory getBugTrackerHistoryDetails(Long bugTrackerId) {
		// TODO Auto-generated method stub
		BugTrackerHistory  bugTrackerHistory=null;
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTrackerHistory.class);
			criteria.add(Restrictions.eq("id", bugTrackerId));
			bugTrackerHistory=(BugTrackerHistory) criteria.uniqueResult();
			tx.commit();

		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTrackerHistory;
	}


	public BugTrackerHistory updateBugTrackerHistory(BugTrackerHistory bugTrackerHistory) {
		Session session=null;
		Transaction tx=null;
		BugTrackerHistory updateBugTrackerHistoryObj = null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			updateBugTrackerHistoryObj =  (BugTrackerHistory) session.get(BugTrackerHistory.class,bugTrackerHistory.getId());
			updateBugTrackerHistoryObj.setAssignTo(bugTrackerHistory.getAssignTo());
			updateBugTrackerHistoryObj.setAssignedBy(bugTrackerHistory.getAssignedBy());
			updateBugTrackerHistoryObj.setFilePath(bugTrackerHistory.getFilePath());
			updateBugTrackerHistoryObj.setLevel(bugTrackerHistory.getLevel());
			updateBugTrackerHistoryObj.setStatus(bugTrackerHistory.getStatus());
			updateBugTrackerHistoryObj.setUsername(bugTrackerHistory.getUsername());
			updateBugTrackerHistoryObj.setUserEmail(bugTrackerHistory.getUserEmail());
			updateBugTrackerHistoryObj.setAssignDate(bugTrackerHistory.getAssignDate());
			updateBugTrackerHistoryObj.setStartToWorkDate(bugTrackerHistory.getStartToWorkDate());
			updateBugTrackerHistoryObj.setWorkFinishDate(bugTrackerHistory.getWorkFinishDate());
			updateBugTrackerHistoryObj.setEstimatedHours(bugTrackerHistory.getEstimatedHours());
			updateBugTrackerHistoryObj.setDeveloperEstimatedHours(bugTrackerHistory.getDeveloperEstimatedHours());
			updateBugTrackerHistoryObj.setWorkingHours(bugTrackerHistory.getWorkingHours());
			updateBugTrackerHistoryObj.setExtraHours(bugTrackerHistory.getExtraHours());
			updateBugTrackerHistoryObj.setBugType(bugTrackerHistory.getBugType());
			updateBugTrackerHistoryObj.getBugTracker().setDescription(bugTrackerHistory.getBugTracker().getDescription());
			session.update(updateBugTrackerHistoryObj);
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return updateBugTrackerHistoryObj;
	}

	public  List<BugTrackerHistory> fetchBugTrackerHistoryList(){
		List<BugTrackerHistory> newBugTrackerHistoryList= new ArrayList<>();
		Session session=null;
		Transaction tx=null;
		Disjunction disjunction=Restrictions.disjunction();
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTrackerHistory.class);
			disjunction.add(Restrictions.eq("status", "Created"));
			disjunction.add(Restrictions.eq("status", "Assigned"));
			disjunction.add(Restrictions.eq("status", "Closed"));
			disjunction.add(Restrictions.eq("status", "Pending"));
			disjunction.add(Restrictions.eq("status", "Review"));
			disjunction.add(Restrictions.eq("status", "StillInProgress"));
			disjunction.add(Restrictions.eq("status", "TestReview"));
			disjunction.add(Restrictions.eq("status", "WorkInProgress"));
			criteria.add(disjunction);
			List<BugTrackerHistory> bugTrackerList=criteria.list();
			tx.commit();
			if(bugTrackerList!=null && bugTrackerList.size()>0){
				for(BugTrackerHistory bugTrackerHistory:bugTrackerList){
					User createdBy=new UserDAO().getUserDetailsByUserId(bugTrackerHistory.getBugTracker().getCreatedBy()).get(0);
					User updatedBy=new UserDAO().getUserDetailsByUserId(bugTrackerHistory.getBugTracker().getUpdatedBy()).get(0);
					User assignedBy= new UserDAO().getUserByUserId(bugTrackerHistory.getAssignedBy());
					User assignedTo= new UserDAO().getUserByUserId(bugTrackerHistory.getAssignTo());
					bugTrackerHistory.setCreatedByName(createdBy.getUsername());
					bugTrackerHistory.setUpdatedByName(updatedBy.getUsername());
					if(assignedBy!=null){
						bugTrackerHistory.setAssignedByName(assignedBy.getUsername());
					}else{
						bugTrackerHistory.setAssignedByName("NA");
					}

					if(assignedTo!=null){
						bugTrackerHistory.setAssignedToName(assignedTo.getUsername());
					}else{
						bugTrackerHistory.setAssignedToName("NA");
					}

					bugTrackerHistory.setCreateDate(DateConversion.convertDateToStringToDate(bugTrackerHistory.getBugTracker().getCreatedAt()));
					newBugTrackerHistoryList.add(bugTrackerHistory);

				}
			}

		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return newBugTrackerHistoryList;	

	}
	public BugTrackerComment  insertBugTrackerComment(BugTrackerComment bugTrackerComment){
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(bugTrackerComment);
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTrackerComment;
	}
	public BugTrackerComment updateBugTrackerComment(BugTrackerComment bugTrackerComment) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.update(bugTrackerComment);
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTrackerComment;


	}

	public   BugTrackerComment getBugTrackerComment(Long id) {
		BugTrackerComment  bugTrackerComment=null;
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTrackerComment.class);
			criteria.add(Restrictions.eq("id", id));
			bugTrackerComment=(BugTrackerComment) criteria.uniqueResult();
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTrackerComment;	
	}
	public  List<BugTrackerComment> fetchBugTrackerCommentList(Long bugTrackerId){
		List<BugTrackerComment> bugTrackerComments= new ArrayList<>();
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTrackerComment.class);
			criteria.add(Restrictions.eq("bugTracker.id", bugTrackerId));
			criteria.addOrder(Order.desc("createdAt"));
			bugTrackerComments=criteria.list();
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTrackerComments;	
	}

	public  List<BugTestCase> fetchBugTrackerTestCaseList(Long bugTrackerId){
		List<BugTestCase> bugTestCases= new ArrayList<>();
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(BugTestCase.class);
			criteria.add(Restrictions.eq("bugTracker.id", bugTrackerId));
			criteria.addOrder(Order.desc("createdAt"));
			bugTestCases=criteria.list();
			tx.commit();
		}
		catch(HibernateException he){
			logger.error("HibernateException"+he.getMessage());
			if(tx !=null)
				tx.rollback();		
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return bugTestCases;	
	}
}


