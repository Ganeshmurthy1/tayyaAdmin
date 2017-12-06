package com.lintas.admin.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyRole;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.config.HibernateUtil;

public class RmConfigDao {

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RmConfigDao.class);

	public RmConfigModel insertConfigDetails(RmConfigModel rmConfigModel) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(rmConfigModel);
			tx.commit();
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return rmConfigModel;
	}

	public RmConfigModel getConfigDetailsById(int id) {
		Session session = null;
		RmConfigModel rmConfigModel =null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(RmConfigModel.class);
			criteria.add(Restrictions.eq("id", id));
			rmConfigModel = (RmConfigModel) criteria.uniqueResult();
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return rmConfigModel;
	}
	public RmConfigModel getConfigDetailsByCompanyId(int id) {
		Session session = null;
		RmConfigModel rmConfigModel =null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(RmConfigModel.class);
			criteria.add(Restrictions.eq("corporateId", id));
			rmConfigModel = (RmConfigModel) criteria.uniqueResult();
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return rmConfigModel;
	}

	public List<Long> getAllCompanyIdCorporateOnly() {
		Session session = null;
		List<Long> companyRoleIds = new ArrayList<Long>();
		Disjunction disjunction=Restrictions.disjunction();

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CompanyRole.class);
			disjunction.add(Restrictions.eq("isAgent", true));
			disjunction.add(Restrictions.eq("isDistributor", true));
			disjunction.add(Restrictions.eq("isCorporate", true));
			criteria.add(disjunction);
			criteria.setProjection(Projections.property("companyRoleId"));
			companyRoleIds = criteria.list();
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return companyRoleIds;
	}

	public List<Company> getCompanyNameById(List<Integer> listId,boolean bool) {
		Session session = null;
		List<Company> listOfCompany = null;
		try {
			if (listId != null && listId.size() > 0) {
				session = HibernateUtil.getSessionFactory().openSession();
				Conjunction conj=Restrictions.conjunction();
				Criteria criteria = session.createCriteria(Company.class);
				conj.add(Restrictions.in("companyid", listId));
				conj.add(Restrictions.eq("rmConfig",bool));
				criteria.add(conj);
				//				criteria.setProjection(Projections.projectionList().add(Projections.property("Companyname")).add(Projections.property("companyid")));
				listOfCompany=criteria.list();
			}
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listOfCompany;
	}

	public boolean insertTripConfigDetails(RmConfigTripDetailsModel configTripDetailsModel) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(configTripDetailsModel);
			tx.commit();
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return true;
	}

	public boolean updateTripConfigDetails(RmConfigTripDetailsModel configTripDetailsModel) {
		Session session = null;
		Transaction tx = null;
		boolean isUpdated = false;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();	
			RmConfigTripDetailsModel rmConfigTripDetailsModelUpdate = (RmConfigTripDetailsModel) session.get(RmConfigTripDetailsModel.class,configTripDetailsModel.getId());
			if(rmConfigTripDetailsModelUpdate != null){
				rmConfigTripDetailsModelUpdate.setApproverName(configTripDetailsModel.getApproverName());	
				rmConfigTripDetailsModelUpdate.setBillNonBill(configTripDetailsModel.getBillNonBill());
				rmConfigTripDetailsModelUpdate.setBussinessUnit(configTripDetailsModel.getBussinessUnit());
				rmConfigTripDetailsModelUpdate.setCostCenter(configTripDetailsModel.getCostCenter());
				rmConfigTripDetailsModelUpdate.setDepartment(configTripDetailsModel.getDepartment());
				rmConfigTripDetailsModelUpdate.setEmpCode(configTripDetailsModel.getEmpCode());
				rmConfigTripDetailsModelUpdate.setLocation(configTripDetailsModel.getLocation());
				rmConfigTripDetailsModelUpdate.setManualField1(configTripDetailsModel.getManualField1());
				rmConfigTripDetailsModelUpdate.setManualField2(configTripDetailsModel.getManualField2());
				rmConfigTripDetailsModelUpdate.setManualField3(configTripDetailsModel.getManualField3());
				rmConfigTripDetailsModelUpdate.setManualField4(configTripDetailsModel.getManualField4());
				rmConfigTripDetailsModelUpdate.setManualField5(configTripDetailsModel.getManualField5());
				rmConfigTripDetailsModelUpdate.setOrderId(configTripDetailsModel.getOrderId());
				rmConfigTripDetailsModelUpdate.setOrdertype(configTripDetailsModel.getOrdertype());
				rmConfigTripDetailsModelUpdate.setProjectCode(configTripDetailsModel.getProjectCode());
				rmConfigTripDetailsModelUpdate.setReasonForTravel(configTripDetailsModel.getReasonForTravel());
				rmConfigTripDetailsModelUpdate.setTrNumber(configTripDetailsModel.getTrNumber());
				session.update(rmConfigTripDetailsModelUpdate);
				tx.commit();
				isUpdated = true;
			}
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());
			if (tx != null)
				tx.rollback();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isUpdated;
	}

	public RmConfigTripDetailsModel getTripConfigDetails(long tripid) {
		Session session = null;
		RmConfigTripDetailsModel rmConfigTripDetailsModel = null;
		List<RmConfigTripDetailsModel> list = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Conjunction conj = Restrictions.conjunction();
			Criteria criteria = session.createCriteria(RmConfigTripDetailsModel.class);			
			conj.add(Restrictions.eq("tripId",tripid));
			criteria.add(conj);
			list =  criteria.list();
			if(list.size() > 0){
				rmConfigTripDetailsModel = list.get(0);
			}

		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());			

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return rmConfigTripDetailsModel;
	}

	public RmConfigTripDetailsModel getTripConfigDetailsByOrdertype(long tripid,String Type) {
		Session session = null;
		RmConfigTripDetailsModel rmConfigTripDetailsModel = null;
		List<RmConfigTripDetailsModel> list = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Conjunction conj = Restrictions.conjunction();
			Criteria criteria = session.createCriteria(RmConfigTripDetailsModel.class);			
			conj.add(Restrictions.eq("tripId",tripid));
			conj.add(Restrictions.eq("ordertype",Type));
			criteria.add(conj);
			list =  criteria.list();
			if(list.size() > 0){
				rmConfigTripDetailsModel = list.get(0);
			}

		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());			

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return rmConfigTripDetailsModel;
	}

	public RmConfigTripDetailsModel getTripConfigDetailsByOrderType(long tripid,String ordertype) {
		Session session = null;
		RmConfigTripDetailsModel rmConfigTripDetailsModel = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();			
			Conjunction conj = Restrictions.conjunction();
			Criteria criteria = session.createCriteria(RmConfigTripDetailsModel.class);			
			conj.add(Restrictions.eq("tripId",tripid));
			conj.add(Restrictions.eq("ordertype",ordertype));
			criteria.add(conj);
			rmConfigTripDetailsModel = (RmConfigTripDetailsModel) criteria.list().get(0);
		} catch (HibernateException he) {
			logger.error("HibernateException" + he.getMessage());			

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return rmConfigTripDetailsModel;
	}

	public int updateCompanyRmConfigFlag(RmConfigModel rmConfigModel) {
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			RmConfigModel rmConfigModelUpdate =  (RmConfigModel) session.get(RmConfigModel.class,rmConfigModel.getId());
			if(rmConfigModel!=null)
			{
				rmConfigModelUpdate.setApproverName(rmConfigModel.isApproverName());
				rmConfigModelUpdate.setBillNonBill(rmConfigModel.isBillNonBill());
				rmConfigModelUpdate.setBussinessUnit(rmConfigModel.isBussinessUnit());
				rmConfigModelUpdate.setCostCenter(rmConfigModel.isCostCenter());
				rmConfigModelUpdate.setDepartment(rmConfigModel.isDepartment());
				rmConfigModelUpdate.setEmpCode(rmConfigModel.isEmpCode());
				rmConfigModelUpdate.setLocation(rmConfigModel.isLocation());
				rmConfigModelUpdate.setProjectCode(rmConfigModel.isProjectCode());
				rmConfigModelUpdate.setReasonForTravel(rmConfigModel.isReasonForTravel());
				rmConfigModelUpdate.setTrNumber(rmConfigModel.isTrNumber());

				rmConfigModelUpdate.setApproverNameType(rmConfigModel.getApproverNameType());
				rmConfigModelUpdate.setBillNonBillType(rmConfigModel.getBillNonBillType());
				rmConfigModelUpdate.setBussinessUnitType(rmConfigModel.getBussinessUnitType());
				rmConfigModelUpdate.setCostCenterType(rmConfigModel.getCostCenterType());
				rmConfigModelUpdate.setDepartmentType(rmConfigModel.getDepartmentType());
				rmConfigModelUpdate.setEmpCodeType(rmConfigModel.getEmpCodeType());
				rmConfigModelUpdate.setLocationType(rmConfigModel.getLocationType());
				rmConfigModelUpdate.setProjectCodeType(rmConfigModel.getProjectCodeType());
				rmConfigModelUpdate.setReasonForTravelType(rmConfigModel.getReasonForTravelType());
				rmConfigModelUpdate.setTrNumberType(rmConfigModel.getTrNumberType());
				rmConfigModelUpdate.setDynamicFieldsData(rmConfigModel.getDynamicFieldsData());

				session.update(rmConfigModelUpdate);
				transaction.commit();
				return 1;
			}

		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}
		return 0;
	}

	public  boolean getAndUpdateCompanyDetails(int companyId) {
		Session session = null;
		Transaction transaction = null;
		boolean updateFlag=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();			
			Company company =  (Company) session.get(Company.class,companyId);
			if(company!=null)
			{
				company.setRmConfig(true);
				session.update(company);
				transaction.commit();
				updateFlag=true;
			}

		}catch(Exception e){
			if(transaction!=null){
				transaction.rollback();
			}
			logger.error("---------Exception---------"+e.getMessage());
		}
		return updateFlag;
	}


	public boolean isCompanyHavingRmConfig(Company company){
		Session session = null;
		boolean isExists=false;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(RmConfigModel.class);
			criteria.add(Restrictions.eq("corporateId", company.getCompanyid()));
			RmConfigModel rmConfigModel =(RmConfigModel) criteria.uniqueResult();
			if(rmConfigModel!=null)
				isExists=true;
		}catch(HibernateException exception){
			logger.error("---------Exception---------"+exception.getMessage());
		}
		finally {
			session.close();
		}
		return isExists;
	}





}
