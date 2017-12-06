/**
 * 
 */
package com.admin.designationband;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.lintas.config.HibernateUtil;

/**
 * @author MANISH
 *
 */
public class DesignationDao {

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DesignationDao.class);

	public  List<EmployeeBandModel>  getAllBandNameList(){
		Session session=null;
		Criteria criteria=null;
		List<EmployeeBandModel> bandModelList=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(EmployeeBandModel.class);
					/*.setProjection(Projections.projectionList()
							.add(Projections.property("bandName"), "bandName")
							.add(Projections.property("bandCode"), "bandCode")
							.add(Projections.property("bandCode"), "bandCode")
							.add(Projections.property("companyid"), "companyid"))
					.setResultTransformer(Transformers.aliasToBean(EmployeeBandModel.class));*/

			bandModelList = criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return bandModelList;
	}
	public EmployeeBandModel getOneBandRecordById(int bandId){
		Session session=null;
		Criteria criteria=null;
		EmployeeBandModel bandModel=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(EmployeeBandModel.class);
			criteria.add(Restrictions.eq("bandId", bandId));
			bandModel = (EmployeeBandModel) criteria.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return bandModel;
	}

	public  List<EmployeeDesignationsModel>  getAllDesgNameList(){
		Session session=null;
		Criteria criteria=null;
		List<EmployeeDesignationsModel> desgModelList=null;

		try{
			session= HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(EmployeeDesignationsModel.class);
					/*.setProjection(Projections.projectionList()
							.add(Projections.property("desgName"), "desgName")
							.add(Projections.property("desgId"), "desgId")
							.add(Projections.property("desgCode"), "desgCode"))
					.setResultTransformer(Transformers.aliasToBean(EmployeeDesignationsModel.class));*/

			desgModelList = criteria.list();

			/*criteria.setProjection(Projections.property("bandName"));
			bandModelList=criteria.list();*/
			/*setList=new TreeSet<String>(bandModelList);*/
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return desgModelList;
	}

	public List<EmployeeDesignationModel> getAllBandRecords(){
		Session session=null;
		Criteria criteria=null;
		List<EmployeeDesignationModel> bandModelList=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(EmployeeDesignationModel.class);
			bandModelList=criteria.list();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return bandModelList;
	}
	
	public EmployeeDesignationsModel getDesignationInfoById(String desgName){               // ned to change this by below getDesignationsInfoById(int desgId);
		Session session=null;
		Criteria criteria=null;
		EmployeeDesignationsModel desgModelInfo=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(EmployeeDesignationsModel.class);
			criteria.add(Restrictions.eq("desgName", desgName));
			desgModelInfo=(EmployeeDesignationsModel) criteria.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return desgModelInfo;
	}
	
	public EmployeeDesignationsModel getDesignationsInfoById(int desgId){               
		Session session=null;
		Criteria criteria=null;
		EmployeeDesignationsModel desgModelInfo=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(EmployeeDesignationsModel.class);
			criteria.add(Restrictions.eq("desgId", desgId));
			desgModelInfo=(EmployeeDesignationsModel) criteria.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return desgModelInfo;
	}
	
	
	
	
	public EmployeeDesignationModel getEmployeeRecordsById(int id){
		Session session=null;
		Criteria criteria=null;
		EmployeeDesignationModel bandModel=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(EmployeeDesignationModel.class);
			criteria.add(Restrictions.eq("id", id));
			bandModel=(EmployeeDesignationModel) criteria.uniqueResult();
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return bandModel;
	}
	public Set<String> chekingDesignationAssociatedToWhichBand(EmployeeDesignationModel employeeDesignationModel){
		Session session=null;
		Criteria criteria=null;
		List<String> bandModelList=new ArrayList<String>();
		Set<String> setList=null;
		EmployeeDesignationsModel employeeDesignationsModel=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			criteria=session.createCriteria(EmployeeDesignationsModel.class);
			criteria.add(Restrictions.eq("desgName", employeeDesignationModel.getDesignation()));
//			criteria.setProjection(Projections.property("employeeDesignationModels.bandName"));
			employeeDesignationsModel=(EmployeeDesignationsModel) criteria.uniqueResult();
			bandModelList.add(employeeDesignationsModel.getEmployeeBandModel().getBandName());
			setList=new TreeSet<String>(bandModelList);
			logger.info(bandModelList);
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
			logger.info("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return setList;
	}

	public void insertingBandName(EmployeeBandModel employeeBandModel){
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			session.save(employeeBandModel);
			transaction.commit();
		}catch (HibernateException e) {
			transaction.rollback();
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			transaction.rollback();
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
	}
	public void insertingDesgName(EmployeeDesignationsModel employeeDesignationsModel)//String desgName,String desgCode,EmployeeBandModel bandModel){
	{	
		Session session=null;
		Transaction transaction=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			session.save(employeeDesignationsModel);
			transaction.commit();
		}catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}

	}
	public void updateEmployeeInfoById(EmployeeDesignationModel designationModel){
		Session session=null;
		Transaction transaction =null;
		EmployeeDesignationModel updateDesignationModel=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			updateDesignationModel=(EmployeeDesignationModel) session.get(EmployeeDesignationModel.class, designationModel.getId());
			updateDesignationModel.setBandName(designationModel.getBandName());
			updateDesignationModel.setDesignation(designationModel.getDesignation());
			updateDesignationModel.setUserName(designationModel.getUserName());
			updateDesignationModel.setBandId(designationModel.getBandId());
			session.saveOrUpdate(updateDesignationModel);
			transaction.commit();
		}catch (Exception e) {
			transaction.rollback();
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
	}
	
	public boolean updateDesignationInfoById(EmployeeDesignationsModel designationModel){
		Session session=null;
		Transaction transaction =null;
		boolean returnResponse=false;
		EmployeeDesignationsModel updateDesignationModel=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			updateDesignationModel=(EmployeeDesignationsModel) session.get(EmployeeDesignationsModel.class, designationModel.getDesgId());
			updateDesignationModel.setDesgName(designationModel.getDesgName());
			updateDesignationModel.setDesgCode(designationModel.getDesgCode());
			session.saveOrUpdate(updateDesignationModel);
			transaction.commit();
			returnResponse=true;
		}catch (Exception e) {
			transaction.rollback();
			returnResponse=false;
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return returnResponse;
	}
	
	public boolean updateBandInfoById(EmployeeBandModel bandModel){
		Session session=null;
		Transaction transaction =null;
		boolean returnResponse=false;
		EmployeeBandModel updatebandModel=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			updatebandModel=(EmployeeBandModel) session.get(EmployeeBandModel.class, bandModel.getBandId());
			updatebandModel.setBandName(bandModel.getBandName());
			updatebandModel.setBandCode(bandModel.getBandCode());
			session.saveOrUpdate(updatebandModel);
			transaction.commit();
			returnResponse=true;
		}catch (Exception e) {
			transaction.rollback();
			returnResponse=false;
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return returnResponse;
	}
	
	
	public boolean deleteEmployeeInfoById(int id){
		Session session=null;
		boolean isDeleted=false;
		Transaction transaction =null;
		EmployeeDesignationModel updateDesignationModel=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			updateDesignationModel=(EmployeeDesignationModel) session.get(EmployeeDesignationModel.class, id);
			session.delete(updateDesignationModel);
			transaction.commit();
			isDeleted=true;
		}catch (Exception e) {
			transaction.rollback();
			isDeleted=false;
		}
		finally {
			if(session != null && session.isOpen())
			{
				session.close();
			}
		}
		return isDeleted;
	}
	public void insertIntoEmployeeDesignation(EmployeeDesignationModel designationModel){
		Session session=null;
		Transaction transaction =null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			transaction=session.beginTransaction();
			session.save(designationModel);
			transaction.commit();
		}catch (Exception e) {
			transaction.rollback();

		}
		finally {
				if(session != null && session.isOpen())
				{
					session.close();
				}
			}
		}
	}
