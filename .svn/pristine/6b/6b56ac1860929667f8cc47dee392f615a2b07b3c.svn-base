package com.admin.dashboard.analysis.json.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.analysis.json.vo.CreditNotesCommonVo;
import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.config.HibernateUtil;

public class CommonServiceFetchCreditNoteDao {
	
	
	/**
	 * @author Basha
	 * date 10-10-2017
	 */

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonServiceFetchCreditNoteDao.class);

	
	
	public CreditNotesCommonVo getCreditNoteForFlight(List<String> userList,Long rowId,int companyid ){
		Criteria criteria=null;
		Session session = null;
		CreditNotesCommonVo creditNoteListVo=new CreditNotesCommonVo();
		List<CreditNote> creditNoteList=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			criteria=session.createCriteria(CreditNote.class);
			conjunction.add(Restrictions.in("userId", userList));
			conjunction.add(Restrictions.eq("rowId", rowId.intValue()));
			conjunction.add(Restrictions.eq("companyId",Integer.toString(companyid)));
			criteria.add(conjunction);
			creditNoteList=criteria.list();
			if(creditNoteList!=null && creditNoteList.size()>0){
				for(CreditNote list:creditNoteList){
					CreditNotesCommonVo iterateList=new CreditNotesCommonVo();
					iterateList.setCompanyId(list.getCompanyId());
					iterateList.setCreditnoteIssued(list.isCreditnoteIssued());
					iterateList.setId(list.getId());
					iterateList.setRefundedAmount(list.getRefundedAmount());
					iterateList.setRowId(list.getRowId());
					iterateList.setTotalBookingAmount(list.getTotalBookingAmount());
					iterateList.setUserId(list.getUserId());
					creditNoteListVo=iterateList;
				}
			}
			
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return creditNoteListVo;
	}
	
	
	
	
	public CreditNotesCommonVo getCreditNoteForHotel(List<String> userList,Long rowId,int companyid ){
		Criteria criteria=null;
		Session session = null;
		CreditNotesCommonVo creditNoteListVo=new CreditNotesCommonVo();
		List<HotelCreditNote> creditNoteList=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			criteria=session.createCriteria(HotelCreditNote.class);
			conjunction.add(Restrictions.in("userId", userList));
			conjunction.add(Restrictions.eq("rowId", rowId.intValue()));
			conjunction.add(Restrictions.eq("companyId",Integer.toString(companyid)));
			criteria.add(conjunction);
			creditNoteList=criteria.list();
			if(creditNoteList!=null && creditNoteList.size()>0){
				for(HotelCreditNote list:creditNoteList){
					CreditNotesCommonVo iterateList=new CreditNotesCommonVo();
					iterateList.setCompanyId(list.getCompanyId());
					iterateList.setCreditnoteIssued(list.isCreditnoteIssued());
					iterateList.setId(list.getId());
					iterateList.setRefundedAmount(list.getRefundedAmount());
					iterateList.setRowId(list.getRowId());
					iterateList.setTotalBookingAmount(list.getTotalBookingAmount());
					iterateList.setUserId(list.getUserId());
					
					creditNoteListVo=iterateList;
				}
			}
			
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return creditNoteListVo;
	}
	
	public CreditNotesCommonVo getCreditNoteForCar(List<String> userList,Long rowId,int companyid ){
		Criteria criteria=null;
		Session session = null;
		CreditNotesCommonVo creditNoteListVo=new CreditNotesCommonVo();
		List<CarCreditNote> creditNoteList=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			criteria=session.createCriteria(CarCreditNote.class);
			conjunction.add(Restrictions.in("userId", userList));
			conjunction.add(Restrictions.eq("rowId", rowId.intValue()));
			conjunction.add(Restrictions.eq("companyId",Integer.toString(companyid)));
			criteria.add(conjunction);
			creditNoteList=criteria.list();
			if(creditNoteList!=null && creditNoteList.size()>0){
				for(CarCreditNote list:creditNoteList){
					CreditNotesCommonVo iterateList=new CreditNotesCommonVo();
					iterateList.setCompanyId(list.getCompanyId());
					iterateList.setCreditnoteIssued(list.isCreditnoteIssued());
					iterateList.setId(list.getId());
					iterateList.setRefundedAmount(list.getRefundedAmount());
					iterateList.setRowId(list.getRowId());
					iterateList.setTotalBookingAmount(list.getTotalBookingAmount());
					iterateList.setUserId(list.getUserId());
					
					creditNoteListVo=iterateList;
				}
			}
			
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return creditNoteListVo;
	}
	
	
	public CreditNotesCommonVo getCreditNoteForBus(List<String> userList,Long rowId,int companyid ){
		Criteria criteria=null;
		Session session = null;
		CreditNotesCommonVo creditNoteListVo=new CreditNotesCommonVo();
		List<BusCreditNote> creditNoteList=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			criteria=session.createCriteria(BusCreditNote.class);
			conjunction.add(Restrictions.in("userId", userList));
			conjunction.add(Restrictions.eq("rowId", rowId.intValue()));
			conjunction.add(Restrictions.eq("companyId",Integer.toString(companyid)));
			criteria.add(conjunction);
			creditNoteList=criteria.list();
			if(creditNoteList!=null && creditNoteList.size()>0){
				for(BusCreditNote list:creditNoteList){
					CreditNotesCommonVo iterateList=new CreditNotesCommonVo();
					iterateList.setCompanyId(list.getCompanyId());
					iterateList.setCreditnoteIssued(list.isCreditnoteIssued());
					iterateList.setId(list.getId());
					iterateList.setRefundedAmount(list.getRefundedAmount());
					iterateList.setRowId(list.getRowId());
					iterateList.setTotalBookingAmount(list.getTotalBookingAmount());
					iterateList.setUserId(list.getUserId());
					
					creditNoteListVo=iterateList;
				}
			}
			
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return creditNoteListVo;
	}
	
	
	public CreditNotesCommonVo getCreditNoteForTrain(List<String> userList,Long rowId,int companyid ){
		Criteria criteria=null;
		Session session = null;
		CreditNotesCommonVo creditNoteListVo=new CreditNotesCommonVo();
		List<TrainCreditNote> creditNoteList=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			criteria=session.createCriteria(TrainCreditNote.class);
			conjunction.add(Restrictions.in("userId", userList));
			conjunction.add(Restrictions.eq("rowId", rowId.intValue()));
			conjunction.add(Restrictions.eq("companyId",Integer.toString(companyid)));
			criteria.add(conjunction);
			creditNoteList=criteria.list();
			if(creditNoteList!=null && creditNoteList.size()>0){
				for(TrainCreditNote list:creditNoteList){
					CreditNotesCommonVo iterateList=new CreditNotesCommonVo();
					iterateList.setCompanyId(list.getCompanyId());
					iterateList.setCreditnoteIssued(list.isCreditnoteIssued());
					iterateList.setId(list.getId());
					iterateList.setRefundedAmount(list.getRefundedAmount());
					iterateList.setRowId(list.getRowId());
					iterateList.setTotalBookingAmount(list.getTotalBookingAmount());
					iterateList.setUserId(list.getUserId());
					
					creditNoteListVo=iterateList;
				}
			}
			
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return creditNoteListVo;
	}
	
	
	public CreditNotesCommonVo getCreditNoteForVisa(List<String> userList,Long rowId,int companyid ){
		Criteria criteria=null;
		Session session = null;
		CreditNotesCommonVo creditNoteListVo=new CreditNotesCommonVo();
		List<VisaCreditNote> creditNoteList=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			criteria=session.createCriteria(VisaCreditNote.class);
			conjunction.add(Restrictions.in("userId", userList));
			conjunction.add(Restrictions.eq("rowId", rowId.intValue()));
			conjunction.add(Restrictions.eq("companyId",Integer.toString(companyid)));
			criteria.add(conjunction);
			creditNoteList=criteria.list();
			if(creditNoteList!=null && creditNoteList.size()>0){
				for(VisaCreditNote list:creditNoteList){
					CreditNotesCommonVo iterateList=new CreditNotesCommonVo();
					iterateList.setCompanyId(list.getCompanyId());
					iterateList.setCreditnoteIssued(list.isCreditnoteIssued());
					iterateList.setId(list.getId());
					iterateList.setRefundedAmount(list.getRefundedAmount());
					iterateList.setRowId(list.getRowId());
					iterateList.setTotalBookingAmount(list.getTotalBookingAmount());
					iterateList.setUserId(list.getUserId());
					
					creditNoteListVo=iterateList;
				}
			}
			
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return creditNoteListVo;
	}
	
	
	public CreditNotesCommonVo getCreditNoteForInsurance(List<String> userList,Long rowId,int companyid ){
		Criteria criteria=null;
		Session session = null;
		CreditNotesCommonVo creditNoteListVo=new CreditNotesCommonVo();
		List<InsuranceCreditNote> creditNoteList=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			criteria=session.createCriteria(InsuranceCreditNote.class);
			conjunction.add(Restrictions.in("userId", userList));
			conjunction.add(Restrictions.eq("rowId", rowId.intValue()));
			conjunction.add(Restrictions.eq("companyId",Integer.toString(companyid)));
			criteria.add(conjunction);
			creditNoteList=criteria.list();
			if(creditNoteList!=null && creditNoteList.size()>0){
				for(InsuranceCreditNote list:creditNoteList){
					CreditNotesCommonVo iterateList=new CreditNotesCommonVo();
					iterateList.setCompanyId(list.getCompanyId());
					iterateList.setCreditnoteIssued(list.isCreditnoteIssued());
					iterateList.setId(list.getId());
					iterateList.setRefundedAmount(list.getRefundedAmount());
					iterateList.setRowId(list.getRowId());
					iterateList.setTotalBookingAmount(list.getTotalBookingAmount());
					iterateList.setUserId(list.getUserId());
					
					creditNoteListVo=iterateList;
				}
			}
			
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return creditNoteListVo;
	}
	
	public CreditNotesCommonVo getCreditNoteForMisc(List<String> userList,Long rowId,int companyid ){
		Criteria criteria=null;
		Session session = null;
		CreditNotesCommonVo creditNoteListVo=new CreditNotesCommonVo();
		List<MiscellaneousCreditNote> creditNoteList=new ArrayList<>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Conjunction conjunction=Restrictions.conjunction();
			criteria=session.createCriteria(MiscellaneousCreditNote.class);
			conjunction.add(Restrictions.in("userId", userList));
			conjunction.add(Restrictions.eq("rowId", rowId.intValue()));
			conjunction.add(Restrictions.eq("companyId",Integer.toString(companyid)));
			criteria.add(conjunction);
			creditNoteList=criteria.list();
			if(creditNoteList!=null && creditNoteList.size()>0){
				for(MiscellaneousCreditNote list:creditNoteList){
					CreditNotesCommonVo iterateList=new CreditNotesCommonVo();
					iterateList.setCompanyId(list.getCompanyId());
					iterateList.setCreditnoteIssued(list.isCreditnoteIssued());
					iterateList.setId(list.getId());
					iterateList.setRefundedAmount(list.getRefundedAmount());
					iterateList.setRowId(list.getRowId());
					iterateList.setTotalBookingAmount(list.getTotalBookingAmount());
					iterateList.setUserId(list.getUserId());
					
					creditNoteListVo=iterateList;
				}
			}
			
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return creditNoteListVo;
	}
	
	
}
