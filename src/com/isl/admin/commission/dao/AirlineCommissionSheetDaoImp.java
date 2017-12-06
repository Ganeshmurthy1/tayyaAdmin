package com.isl.admin.commission.dao;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.isl.admin.commission.model.AirlineCommissionBlock;
import com.isl.admin.commission.model.AirlineCommissionCompanyBlock;
import com.isl.admin.commission.model.AirlineCommissionMasterSheet;
import com.isl.admin.commission.model.AirlineCommissionSheet;
import com.isl.admin.commission.model.CommissionActionStatus;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.User;
import com.lintas.config.HibernateUtil;
public class AirlineCommissionSheetDaoImp implements AirlineCommissionSheetDao {
	public static final Logger logger = Logger.getLogger(AirlineCommissionSheetDaoImp.class);
	@Override
	public List<AirlineCommissionSheet> getAirlineCommissionSheet(
			Long sheetId) throws HibernateException {
		List<AirlineCommissionSheet> list  = new ArrayList<AirlineCommissionSheet>();	
		Session sess = null;			
		logger.info("########################## DB AirlineCommissionSheetRow retriving call");
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();					
			Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);
			cr.add(Restrictions.eq("sheetId", sheetId));

			/*cr.setProjection(Projections.projectionList()
					.add(Projections.property("id"), "id")
					.add(Projections.property("iataCode"), "iataCode")
					.add(Projections.property("sheetId"), "sheetId")
					.add(Projections.property("isPlbFixed"), "isPlbFixed")
					.add(Projections.property("plbCommission"), "plbCommission")			
					.add(Projections.property("isIataFixed"), "isIataFixed")
					.add(Projections.property("iataCommission"), "iataCommission")					
					.add(Projections.property("severityLevel"), "severityLevel")
					.add(Projections.property("apiSupplierId"), "apiSupplierId")
					.add(Projections.property("updatedByUserId"), "updatedByUserId")
					.add(Projections.property("updatedByUserId"), "updatedByUserId")
					.add(Projections.property("lastModifiedAt"), "lastModifiedAt")
					)
			.setResultTransformer(Transformers.aliasToBean(AirlineCommissionSheetRow.class));	*/	
			list = cr.list();
			logger.info("########################## AirlineCommissionSheetRow....items " +list.size());
		}catch (HibernateException e) {		
			logger.info("########################## AirlineCommissionSheetRow....error " +e.getMessage());
			logger.error(e);
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}
		}
		return list;
	}
	public boolean isAnyItemInAirlineCommissionSheetRow(
			Long sheetId) throws HibernateException {
		boolean isExist=false;	
		Session sess = null;			
		logger.info("########################## DB AirlineCommissionSheetRow retriving call");
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();					
			Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);
			cr.add(Restrictions.eq("sheetId", sheetId));

			/*cr.setProjection(Projections.projectionList()
					.add(Projections.property("id"), "id")
					.add(Projections.property("iataCode"), "iataCode")
					.add(Projections.property("sheetId"), "sheetId")
					.add(Projections.property("isPlbFixed"), "isPlbFixed")
					.add(Projections.property("plbCommission"), "plbCommission")			
					.add(Projections.property("isIataFixed"), "isIataFixed")
					.add(Projections.property("iataCommission"), "iataCommission")					
					.add(Projections.property("severityLevel"), "severityLevel")
					.add(Projections.property("apiSupplierId"), "apiSupplierId")
					.add(Projections.property("updatedByUserId"), "updatedByUserId")
					.add(Projections.property("updatedByUserId"), "updatedByUserId")
					.add(Projections.property("lastModifiedAt"), "lastModifiedAt")
					)
			.setResultTransformer(Transformers.aliasToBean(AirlineCommissionSheetRow.class));	*/	
			if((Long)cr.setProjection(Projections.rowCount()).uniqueResult()>0)
			{
				isExist= true;
			}
		}catch (HibernateException e) {		
			logger.info("########################## AirlineCommissionSheetRow....error " +e.getMessage());
			isExist=false;
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}
		}
		return isExist;
	}


	@Override
	public AirlineCommissionSheet getAirlineCommissionSheetCommons(
			Long sheetId) throws HibernateException {
		AirlineCommissionSheet airlineCommissionSheetRow  = new AirlineCommissionSheet();	
		Session sess = null;			
		logger.info("########################## DB AirlineCommissionSheetRow retriving call");
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();					

			Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);
			cr.add(Restrictions.eq("sheetId", sheetId));		
			cr.setProjection(Projections.projectionList()							
					.add(Projections.property("sheetId"), "sheetId")						
					.add(Projections.property("dtValidFrom"), "dtValidFrom")
					.add(Projections.property("dtValidTill"), "dtValidTill")
					.add(Projections.property("dealSheetVersion"), "dealSheetVersion")
					.add(Projections.property("createdAt"), "createdAt")
					.add(Projections.property("createdByUserId"), "createdByUserId")
					)
			.setResultTransformer(Transformers.aliasToBean(AirlineCommissionSheet.class));
			List<AirlineCommissionSheet> list  = cr.list();	
			if(list != null && list.size()>0)
				airlineCommissionSheetRow =  list.get(0);

		}catch (HibernateException e) {		
			logger.info("########################## AirlineCommissionSheetRow....error " +e.getMessage());
			logger.error(e);
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}
		return airlineCommissionSheetRow;
	}


	@Override
	public AirlineCommissionSheet getAirlineCommissionSheetRemark(
			Long sheetId, String iataCode, boolean isPlbRemark) throws HibernateException {
		AirlineCommissionSheet airlineCommissionSheetRow  = new AirlineCommissionSheet();	
		Session sess = null;			
		logger.info("########################## DB AirlineCommissionSheetRow retriving call");
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();					

			Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);			   
			LogicalExpression logicalExpression  = Restrictions.and(Restrictions.eq("sheetId", sheetId), 
					Restrictions.eq("iataCode", iataCode));   
			cr.add(logicalExpression);		
			if(isPlbRemark)
			{
				cr.setProjection(Projections.projectionList()							
						.add(Projections.property("plbRemark"), "plbRemark")				
						)
				.setResultTransformer(Transformers.aliasToBean(AirlineCommissionSheet.class));
			}
			else
			{
				cr.setProjection(Projections.projectionList()							
						.add(Projections.property("iataRemark"), "iataRemark")				
						)
				.setResultTransformer(Transformers.aliasToBean(AirlineCommissionSheet.class));
			}


			List<AirlineCommissionSheet> list  = cr.list();	
			if(list != null && list.size()>0)
				airlineCommissionSheetRow =  list.get(0);

		}catch (HibernateException e) {		
			logger.info("########################## AirlineCommissionSheetRow....error " +e.getMessage());
			logger.error(e);
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}
		return airlineCommissionSheetRow;
	}


	@Override
	public AirlineCommissionSheet getAirlineCommissionSheetComplete(
			Long sheetId, String iataCode) throws HibernateException {
		AirlineCommissionSheet airlineCommissionSheetRow  = new AirlineCommissionSheet();	
		Session sess = null;			
		logger.info("########################## DB AirlineCommissionSheetRow retriving call");
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();					
			Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);			   
			LogicalExpression logicalExpression  = Restrictions.and(Restrictions.eq("sheetId", sheetId), 
					Restrictions.eq("iataCode", iataCode));   
			cr.add(logicalExpression);		
			List<AirlineCommissionSheet> list  = cr.list();	
			if(list != null && list.size()>0)
				airlineCommissionSheetRow =  list.get(0);

		}catch (HibernateException e) {		
			logger.info("########################## AirlineCommissionSheetRow....error " +e.getMessage());
			logger.error(e);
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}
		return airlineCommissionSheetRow;
	}

	@Override
	public List<AirlineCommissionSheet> getAirlineCommissionSheetRows(
			Long sheetId) throws HibernateException {
		List<AirlineCommissionSheet> airlineCommissionSheetRows  = new ArrayList<AirlineCommissionSheet>();	
		Session sess = null;			
		logger.info("########################## DB AirlineCommissionSheetRow retriving call");
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();
			Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);
			cr.add(Restrictions.eq("sheetId", sheetId));					
			List<AirlineCommissionSheet> list  = cr.list();	
			if(list != null && list.size()>0)
				airlineCommissionSheetRows = cr.list();

		}catch (HibernateException e) {		
			logger.info("########################## AirlineCommissionSheetRows....error " +e.getMessage());
			logger.error(e);
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}
		return airlineCommissionSheetRows;
	}



	public CommissionActionStatus insertMasterSheetItems(User user)
			throws HibernateException {
		Session sess = null;
		Transaction tx = null;
		logger.info("insertAirlineCommissionSheet called ");
		CommissionActionStatus commissionActionStatus = new CommissionActionStatus(CommissionActionStatus.CODE_DEFAULT, CommissionActionStatus.MESSAGE_DEFAULT);
		int code  = CommissionActionStatus.CODE_SUCCESS;
		String message  = CommissionActionStatus.MESSAGE_SUCCESS;
		try{			
			sess =  HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = sess.createCriteria(Airlinelist.class);
			List<Airlinelist> airlinelists = criteria.list();
			if(airlinelists != null && airlinelists.size()>0)
			{
				int count=0;
				tx = sess.beginTransaction();
				for (Airlinelist airlinelist : airlinelists) {
					++count;
					//if(count<=10)
					//{
					AirlineCommissionSheet airlineCommissionSheetRow = new AirlineCommissionSheet();
					Timestamp updated_at = new Timestamp(new Date().getTime());	
					try{
						airlineCommissionSheetRow.setApiSupplierId(new Long("0")); 
						airlineCommissionSheetRow.setDealSheetVersion("v0");
						airlineCommissionSheetRow.setIataCode(airlinelist.getAirlinecode());
						airlineCommissionSheetRow.setIataCommission(new BigDecimal(0));
						airlineCommissionSheetRow.setIataRemark(null);
						airlineCommissionSheetRow.setIsIataFixed(true);
						airlineCommissionSheetRow.setPlbCommission(new BigDecimal(0));
						airlineCommissionSheetRow.setPlbRemark(null);
						airlineCommissionSheetRow.setIsPlbFixed(true);					
						airlineCommissionSheetRow.setDtValidFrom(updated_at);
						airlineCommissionSheetRow.setDtValidTill(updated_at);					
						airlineCommissionSheetRow.setCreatedAt(updated_at);
						airlineCommissionSheetRow.setLastModifiedAt(updated_at);
						airlineCommissionSheetRow.setSheetId(new Long("0"));	
						airlineCommissionSheetRow.setAirline(airlinelist.getAirlinename());
						Integer createdByUserId = new Integer(user.getId());					
						airlineCommissionSheetRow.setCreatedByUserId(createdByUserId.longValue());						
						sess.save(airlineCommissionSheetRow);
						logger.info("insertion count-----"+count);
						if (count % 100 == 0 ) {
							sess.flush();
							sess.clear();
						} 
					}
					catch(Exception e)
					{
						if (tx!=null)
							tx.rollback();
						code  = CommissionActionStatus.CODE_SHEET_DUPLICATION_FALIED;
						message  = CommissionActionStatus.MESSAGE_SHEET_DUPLICATION_FALIED;
					}
					//}
				}
				tx.commit();
			}
			else
			{
				code  = CommissionActionStatus.CODE_SHEET_INSERTION_FALIED;
				message  = CommissionActionStatus.MESSAGE_SHEET_INSERTION_FALIED;
				logger.info("################ Sheet row duplication error empty rows-");		
			}

		}catch (HibernateException e) {
			logger.info("########################## DB insertAirlineCommissionSheet call error"+e.getMessage());
			logger.error(e);
			e.printStackTrace();
			if (tx!=null) tx.rollback();
			code  = CommissionActionStatus.CODE_SHEET_INSERTION_FALIED;
			message  = CommissionActionStatus.MESSAGE_SHEET_INSERTION_FALIED;
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}
		commissionActionStatus.setStatus(code);
		commissionActionStatus.setMessage(message);
		return commissionActionStatus;
	}


	@Override
	public CommissionActionStatus duplicateMasterSheetItems(Long sheetIdBackUp, int superUserCompanyId, AirlineCommissionMasterSheet superUserDealSheet)
			throws HibernateException {
		Session sess = null;
		Transaction tx = null;
		logger.info("insertAirlineCommissionSheet called ");
		CommissionActionStatus commissionActionStatus = new CommissionActionStatus(CommissionActionStatus.CODE_DEFAULT, CommissionActionStatus.MESSAGE_DEFAULT);


		boolean isMasterBlockCreation = false;
		int code  = CommissionActionStatus.CODE_SUCCESS;
		String message  = CommissionActionStatus.MESSAGE_SUCCESS;
		try{
			if(sheetIdBackUp!=null)
				sheetIdBackUp=new Long(0);
			sess =  HibernateUtil.getSessionFactory().openSession();
			tx = sess.beginTransaction();
			Criteria cr2 = sess.createCriteria(AirlineCommissionCompanyBlock.class);
			cr2.add(Restrictions.eq("isMasterBlock", true));
			cr2.add(Restrictions.eq("isActive", true));
			AirlineCommissionCompanyBlock airlineCommissionCompanyBlock = (AirlineCommissionCompanyBlock) cr2.uniqueResult();
			if(airlineCommissionCompanyBlock == null)	
			{
				isMasterBlockCreation = true;
				airlineCommissionCompanyBlock = new AirlineCommissionCompanyBlock();			
				airlineCommissionCompanyBlock.setAppliedSheetId(superUserDealSheet.getId());
				//logger.info("############ super user block created -- block id--"+airlineCommissionCompanyBlock.getId());
			
				airlineCommissionCompanyBlock.setName("Super User Master Block");				
				airlineCommissionCompanyBlock.setCreatedByCompanyID(superUserCompanyId);
				airlineCommissionCompanyBlock.setSheetInfo("");
				airlineCommissionCompanyBlock.setCreatedByUserID(superUserDealSheet.getCreatedByUserID());
				airlineCommissionCompanyBlock.setDescription(superUserDealSheet.getDescription());
				airlineCommissionCompanyBlock.setMasterBlock(true);
				airlineCommissionCompanyBlock.setActive(true);	
				//sess.save(airlineCommissionCompanyBlock);			
				sess.saveOrUpdate(airlineCommissionCompanyBlock);			
				
			}
			else{
				//airlineCommissionCompanyBlock = new AirlineCommissionCompanyBlock();				
			}
			
			// create a airlineCommissionCompanyBlock with setMasterBlock
			//= true for super user else make it false for agency & distributor

			logger.info("airlineCommissionCompanyBlock created -- block id--"+airlineCommissionCompanyBlock.getId());

			// It will be used for saving in config of user or updating
			commissionActionStatus.setCompanyBlock(airlineCommissionCompanyBlock);	


			// create Duplicate sheet here by getting super user sheet
			logger.info("################Sheet row duplication started--");			
			Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);
			cr.add(Restrictions.eq("sheetId", sheetIdBackUp));	
			List<AirlineCommissionSheet> list  = cr.list();	
			tx.commit();


			if(list != null && list.size()>0)
			{		
				tx = sess.beginTransaction();
				int count=0;
				for (AirlineCommissionSheet airlineCommissionSheetRow : list) {
					++count;
					//if(count<=10)
					//{
					AirlineCommissionSheet airlineCommissionSheetRowDuplicate =  new AirlineCommissionSheet();
					Timestamp updated_at = new Timestamp(new Date().getTime());	
					airlineCommissionSheetRowDuplicate.setApiSupplierId(airlineCommissionSheetRow.getApiSupplierId()); 
					airlineCommissionSheetRowDuplicate.setDealSheetVersion(airlineCommissionSheetRow.getDealSheetVersion());
					airlineCommissionSheetRowDuplicate.setIataCode(airlineCommissionSheetRow.getIataCode());
					airlineCommissionSheetRowDuplicate.setIataCommission(airlineCommissionSheetRow.getIataCommission());
					airlineCommissionSheetRowDuplicate.setIataRemark(airlineCommissionSheetRow.getIataRemark());
					airlineCommissionSheetRowDuplicate.setIsIataFixed(airlineCommissionSheetRow.getIsIataFixed());
					airlineCommissionSheetRowDuplicate.setPlbCommission(airlineCommissionSheetRow.getPlbCommission());
					airlineCommissionSheetRowDuplicate.setPlbRemark(airlineCommissionSheetRow.getPlbRemark());
					airlineCommissionSheetRowDuplicate.setIsPlbFixed(airlineCommissionSheetRow.getIsPlbFixed());					
					airlineCommissionSheetRowDuplicate.setDtValidFrom(airlineCommissionSheetRow.getDtValidFrom());
					airlineCommissionSheetRowDuplicate.setDtValidTill(airlineCommissionSheetRow.getDtValidTill());
					airlineCommissionSheetRowDuplicate.setAirline(airlineCommissionSheetRow.getAirline());
					airlineCommissionSheetRowDuplicate.setCreatedAt(updated_at);
					airlineCommissionSheetRowDuplicate.setLastModifiedAt(updated_at);
					airlineCommissionSheetRowDuplicate.setSheetId(superUserDealSheet.getId());					
					Integer createdByUserId = new Integer(superUserDealSheet.getCreatedByUserID());					
					airlineCommissionSheetRowDuplicate.setCreatedByUserId(createdByUserId.longValue());
					sess.save(airlineCommissionSheetRowDuplicate);
					if (count % 100 == 0 ) {
						sess.flush();
						sess.clear();
					} 

					// create a AirlineCommissionBlock with setMasterBlock
					//= true for super user only
					if(isMasterBlockCreation)
					{
						AirlineCommissionBlock airlineCommissionBlock = new AirlineCommissionBlock();
						airlineCommissionBlock.setAirlineCommissionCompanyBlock(airlineCommissionCompanyBlock);
						airlineCommissionBlock.setIataCode(airlineCommissionSheetRow.getIataCode());
						airlineCommissionBlock.setIataCommissionRetain(new BigDecimal(0));
						airlineCommissionBlock.setPlbCommissionRetain(new BigDecimal(0));	
						airlineCommissionBlock.setAirline(airlineCommissionSheetRowDuplicate.getAirline());
						airlineCommissionBlock.setLastModifiedAt(updated_at);
						sess.save(airlineCommissionBlock);
						if (count % 100 == 0 ) {
							sess.flush();
							sess.clear();
						} 
						//logger.info("################ master commission block creation end-"+list);							
					}
					//}	
					//logger.info("################Sheet row duplication over --list-"+list.size());					
				}

				cr = sess.createCriteria(AirlineCommissionSheet.class);
				cr.add(Restrictions.eq("sheetId", sheetIdBackUp));	
				List<AirlineCommissionSheet> listd  = cr.list();	
				//logger.info("################Sheet row duplication over --list-"+listd.size());	
				tx.commit();

				cr = sess.createCriteria(AirlineCommissionSheet.class);
				cr.add(Restrictions.eq("sheetId", sheetIdBackUp));	
				List<AirlineCommissionSheet> listd0  = cr.list();	
				//logger.info("################Sheet row duplication over --list-"+listd0.size());
				code  = CommissionActionStatus.CODE_SUCCESS;
				message  = CommissionActionStatus.MESSAGE_SUCCESS;
			}
			else
			{
				code  = CommissionActionStatus.CODE_SHEET_NO_SHEETROW_FOUND;
				message  = CommissionActionStatus.MESSAGE_SHEET_NO_SHEETROW_FOUND;
				logger.info("################ Sheet row duplication error empty rows-");		
			}

		}catch (HibernateException e) {
			//logger.info("########################## DB insertAirlineCommissionSheet call error"+e.getMessage());
			logger.error(e);
			e.printStackTrace();
			if (tx.isActive()) {
				try {
					tx.rollback();
				} catch (Exception e1) {
					logger.error("Error rolling back transaction", e1);
				}
			}
			try {
				if(sess != null && sess.isOpen())
					sess.close();
			} catch (Exception e1) {
				logger.error("Error closing session", e1);
			}
			code  = CommissionActionStatus.CODE_SHEET_INSERTION_FALIED;
			message  = CommissionActionStatus.MESSAGE_SHEET_INSERTION_FALIED;
			throw e; 
		}finally {
			try {
				if(sess != null && sess.isOpen())
					sess.close();
			} catch (Exception e1) {
				logger.error("Error closing session", e1);
			}
		}
		commissionActionStatus.setStatus(code);
		commissionActionStatus.setMessage(message);
		return commissionActionStatus;
	}
	@Override
	public List<AirlineCommissionSheet> updateAirlineCommissionSheet(
			List<AirlineCommissionSheet> airlineCommissionSheetRows)
					throws HibernateException {
		Session sess = null;
		Transaction tx = null;
		logger.info("updateAirlineCommissionSheet called ");
		List<AirlineCommissionSheet> listnew  = new ArrayList<AirlineCommissionSheet>();	
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();			
			int count=0;
			for (AirlineCommissionSheet airlineCommissionSheetRow : airlineCommissionSheetRows) {
				Criteria crit = sess.createCriteria(AirlineCommissionSheet.class);			
				crit.add(Restrictions.eq("id", airlineCommissionSheetRow.getId()));			
				AirlineCommissionSheet airlineCommissionSheetRowdb = (AirlineCommissionSheet) crit.uniqueResult();
				airlineCommissionSheetRowdb.setApiSupplierId(airlineCommissionSheetRow.getApiSupplierId());
				airlineCommissionSheetRowdb.setDealSheetVersion(airlineCommissionSheetRow.getDealSheetVersion());
				airlineCommissionSheetRowdb.setDtValidFrom(airlineCommissionSheetRow.getDtValidFrom());
				airlineCommissionSheetRowdb.setDtValidTill(airlineCommissionSheetRow.getDtValidTill());
				airlineCommissionSheetRowdb.setIataCommission(airlineCommissionSheetRow.getIataCommission());
				airlineCommissionSheetRowdb.setIsIataFixed(airlineCommissionSheetRow.getIsIataFixed());
				airlineCommissionSheetRowdb.setIataRemark(airlineCommissionSheetRow.getIataRemark());
				airlineCommissionSheetRowdb.setPlbCommission(airlineCommissionSheetRow.getPlbCommission());
				airlineCommissionSheetRowdb.setIsPlbFixed(airlineCommissionSheetRow.getIsPlbFixed());
				airlineCommissionSheetRowdb.setPlbRemark(airlineCommissionSheetRow.getPlbRemark());
				airlineCommissionSheetRowdb.setSeverityLevel(airlineCommissionSheetRow.getSeverityLevel());
				Timestamp updated_at = new Timestamp(new Date().getTime());		


				airlineCommissionSheetRowdb.setLastModifiedAt(updated_at);	
				tx = sess.beginTransaction();
				sess.update(airlineCommissionSheetRowdb);
				if ( ++count % 100 == 0 ) {
					sess.flush();
					sess.clear();
				}
				tx.commit();
				listnew.add(airlineCommissionSheetRowdb);
			}

		}catch (HibernateException e) {
			//logger.info("########################## DB EupdateAirlineCommissionSheet update call error");
			logger.error("HibernateException", e);
			if (tx!=null) tx.rollback();
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}
		return listnew;
	}

	@Override
	public AirlineCommissionMasterSheet getSuperUserAirlineCommissionSheet(Long sheetId) {
		Session session=null;

		AirlineCommissionMasterSheet superUserAirlineCommissionSheet = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = session.createCriteria(AirlineCommissionMasterSheet.class);			
			crit.add(Restrictions.eq("id", sheetId));	
			superUserAirlineCommissionSheet = (AirlineCommissionMasterSheet) crit.uniqueResult();
		}catch(HibernateException he){
			logger.error(he.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return superUserAirlineCommissionSheet;

	}

	@Override
	public AirlineCommissionMasterSheet saveSuperUserSheetDetails(AirlineCommissionMasterSheet sheet) {

		//List<AirlineCommissionMasterSheet> list = null;		
		//list = UpdateDealSheetStatus();
		Session session=null;
		Transaction transaction=null;		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			//if(list!=null){
			session.save(sheet);
			transaction.commit();
			//}		


		}catch(HibernateException he){
			if (transaction!=null)
				transaction.rollback();
			logger.error(he.getMessage());

		}
		finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return sheet;

	}

	@Override
	public List<AirlineCommissionMasterSheet> getSuperUserSheetList() {
		Session session=null;
		Criteria criteria =null;
		List<AirlineCommissionMasterSheet> list=null;

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(AirlineCommissionMasterSheet.class);
			//criteria.add(Restrictions.eq("isActive", true));
			list=criteria.list();
			//logger.info("SuperUserAirlineCommissionSheet  list---"+list.size());
		}catch(HibernateException he){
			logger.error(he.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return list;
	}

	@Override
	public List<AirlineCommissionSheet> updateAirlineCommissionSheetDates(String dtValidFromStr,
			String dtValidTillStr, Long sheetId) throws HibernateException {
		// TODO Auto-generated method stub
		Session sess = null;
		Transaction tx = null;

		List<AirlineCommissionSheet> listnew  = new ArrayList<AirlineCommissionSheet>();	
		try{

			Timestamp updated_at = new Timestamp(new Date().getTime());	
			Timestamp dtValidFrom = new Timestamp(getFormattedDateFromStringSQL(dtValidFromStr).getTime());	
			Timestamp dtValidTill = new Timestamp(getFormattedDateFromStringSQL(dtValidTillStr).getTime());	
			logger.info(dtValidFrom);
			logger.info(dtValidTill);
			sess =  HibernateUtil.getSessionFactory().openSession();				
			Criteria crit = sess.createCriteria(AirlineCommissionSheet.class);			
			crit.add(Restrictions.eq("sheetId", sheetId));	
			List<AirlineCommissionSheet> airlineCommissionSheetRows = crit.list();

			int count=0;			
			for (AirlineCommissionSheet airlineCommissionSheetRow : airlineCommissionSheetRows) {				
				airlineCommissionSheetRow.setLastModifiedAt(updated_at);
				airlineCommissionSheetRow.setDtValidFrom(dtValidFrom);
				airlineCommissionSheetRow.setDtValidTill(dtValidTill);
				tx = sess.beginTransaction();
				sess.update(airlineCommissionSheetRow);
				if ( ++count % 100 == 0 ) {
					sess.flush();
					sess.clear();
				}
				tx.commit();		

				listnew.add(airlineCommissionSheetRow);
			}

		}
		catch (java.text.ParseException e) {
			 logger.error("########################## DB ParseException validity time exception");
			 logger.error("ParseException" ,e);
			if (tx!=null) 
				tx.rollback();

		}
		catch (HibernateException e) {
			 logger.error("########################## DB EupdateAirlineCommissionSheet update call error");
			 logger.error("ParseException" ,e);
			if (tx!=null) tx.rollback();

		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}

		}
		return listnew;
	}
	public static Date getFormattedDateFromStringSQL(String dateStr) throws java.text.ParseException
	{
		//22 Dec 2015 00:00:00  
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sdf.parse(dateStr); 
		return date; 

	}
	@Override
	public AirlineCommissionMasterSheet getDealSheetDetails(Long sheetId) {
		// TODO Auto-generated method stub
		Session session=null;	
		AirlineCommissionMasterSheet commissionSheetDetails=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(AirlineCommissionMasterSheet.class);
			criteria.add(Restrictions.eq("id", sheetId));
			commissionSheetDetails=(AirlineCommissionMasterSheet) criteria.uniqueResult();

		}
		catch(HibernateException e){
			logger.error(e);
		}
		finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return commissionSheetDetails;
	}

	@Override
	public AirlineCommissionMasterSheet updateDealSheet(AirlineCommissionMasterSheet commissionSheet) {
		// TODO Auto-generated method stub

		List<AirlineCommissionMasterSheet> list = null;		
		list = UpdateDealSheetStatus();
		Session session=null;	
		Transaction  tx=null;			
		AirlineCommissionMasterSheet commissionSheetDetails=null;		
		try{
			session=HibernateUtil.getSessionFactory().openSession();			
			tx = session.beginTransaction();
			if(list!=null){
				commissionSheetDetails= (AirlineCommissionMasterSheet)session.get(AirlineCommissionMasterSheet.class, commissionSheet.getId()); 
				commissionSheetDetails.setActive(commissionSheet.isActive());
				commissionSheetDetails.setName(commissionSheet.getName());
				commissionSheetDetails.setDescription(commissionSheet.getDescription());
				session.saveOrUpdate(commissionSheetDetails); 
				tx.commit();
			}

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return commissionSheetDetails;
	}



	public AirlineCommissionMasterSheet toggleActiveSheet(AirlineCommissionMasterSheet activeSheet) {
		// TODO Auto-generated method stub
		List<AirlineCommissionMasterSheet> list = null;	
		Session session=null;	
		Transaction  tx=null;			
		AirlineCommissionMasterSheet commissionSheetDetails=null;		
		try{
			session=HibernateUtil.getSessionFactory().openSession();			
			commissionSheetDetails = (AirlineCommissionMasterSheet)session.get(AirlineCommissionMasterSheet.class, activeSheet.getId()); 
			commissionSheetDetails.setActive(activeSheet.isActive());
			tx = session.beginTransaction();
			session.saveOrUpdate(commissionSheetDetails); 
			tx.commit();
			//change other sheets if any sheet is activated
			if(activeSheet!=null && activeSheet.isActive())
			{
				Criteria criteria=session.createCriteria(AirlineCommissionMasterSheet.class);
				criteria.add(Restrictions.ne("id", commissionSheetDetails.getId()));
				list = criteria.list();
				int count = 0;
				if(list != null)
				{
					tx = session.beginTransaction();
					for (AirlineCommissionMasterSheet airlineCommissionMasterSheet : list) {

						airlineCommissionMasterSheet.setActive(false);
						session.update(airlineCommissionMasterSheet);
						if ( ++count % 100 == 0 ) {
							session.flush();
							session.clear();
						}

					}
					tx.commit();
				}
			}
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return commissionSheetDetails;
	}



	@Override
	public List<AirlineCommissionMasterSheet> UpdateDealSheetStatus() {
		Session session=null;
		Criteria criteria =null;
		Transaction  tx=null;		
		List<AirlineCommissionMasterSheet> list = null;
		List<AirlineCommissionMasterSheet> updatedlist = new ArrayList<AirlineCommissionMasterSheet>();

		try{
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(AirlineCommissionMasterSheet.class);
			list = criteria.list();
			if(session!=null && session.isOpen())
				session.close();

			session = HibernateUtil.getSessionFactory().openSession();
			for (AirlineCommissionMasterSheet superUserAirlineCommissionSheet : list) {
				superUserAirlineCommissionSheet.setActive(false);
				tx = session.beginTransaction();
				session.saveOrUpdate(superUserAirlineCommissionSheet);
				tx.commit();
				updatedlist.add(superUserAirlineCommissionSheet);
			}			


			 logger.info("SuperUserAirlineCommissionSheet  updatedlist---"+updatedlist.size());
		}catch(HibernateException he){
			logger.error(he.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return updatedlist;
	}

	public boolean UpdateSheetandBlockStatus() {
		Session session=null;
		Criteria criteria =null;
		Criteria blockcriteria =null;
		boolean isUpdated=false;		

		Transaction  tx=null;		
		AirlineCommissionMasterSheet  sheetNew=null;
		AirlineCommissionCompanyBlock blockNew=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			criteria = session.createCriteria(AirlineCommissionMasterSheet.class);
			criteria.add(Restrictions.eq("isActive", true));
			AirlineCommissionMasterSheet airlineCommissionMasterSheet=(AirlineCommissionMasterSheet) criteria.uniqueResult();
			//logger.info("airlineCommissionMasterSheet  ----ID#########"+airlineCommissionMasterSheet.getId());
			blockcriteria=session.createCriteria(AirlineCommissionCompanyBlock.class);
			LogicalExpression logicalExpression  = Restrictions.and(Restrictions.eq("isActive", true), 
					Restrictions.eq("isMasterBlock", true));  
			blockcriteria.add(logicalExpression);
			AirlineCommissionCompanyBlock airlineCommissionCompanyBlock=(AirlineCommissionCompanyBlock) blockcriteria.uniqueResult();
			//logger.info("airlineCommissionCompanyBlock  ----getAppliedSheetId#########"+airlineCommissionCompanyBlock.getAppliedSheetId());
			tx = session.beginTransaction();	
			if(airlineCommissionMasterSheet!=null && airlineCommissionCompanyBlock!=null){
				if(airlineCommissionMasterSheet.getId()==airlineCommissionCompanyBlock.getAppliedSheetId()){
					sheetNew =  (AirlineCommissionMasterSheet) session.get(AirlineCommissionMasterSheet.class,airlineCommissionMasterSheet.getId());
					sheetNew.setActive(false);
					session.saveOrUpdate(sheetNew);
					blockNew =  (AirlineCommissionCompanyBlock) session.get(AirlineCommissionCompanyBlock.class,airlineCommissionCompanyBlock.getId());
					blockNew.setActive(false);
					session.saveOrUpdate(blockNew);
					tx.commit();
					isUpdated=true;
				}
			}

			//logger.info("AirlineCommissionSheet sheetNew    updated ---"+isUpdated);
		}catch(HibernateException he){
			logger.error(he.getMessage());
			if(tx!=null) 
				tx.rollback();
			isUpdated=false;
		}
		finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return isUpdated;
	}




	public AirlineCommissionMasterSheet updateDealSheetApproval(AirlineCommissionMasterSheet superUserDealSheet) {
		Session session=null;	
		Transaction  tx=null;
		Criteria blockcriteria =null;
		AirlineCommissionMasterSheet commissionSheetDetails=null;	
		AirlineCommissionCompanyBlock blockNew=null;
		boolean isUpdated=UpdateSheetandBlockStatus();
		//logger.info("isUpdated-----------"+isUpdated);
		try{
			session=HibernateUtil.getSessionFactory().openSession();			
			tx = session.beginTransaction();
			if(isUpdated){

				//logger.info("Inside-----isUpdated------");
				//logger.info("superUserDealSheet.getId()------"+superUserDealSheet.getId());

				commissionSheetDetails= (AirlineCommissionMasterSheet)session.get(AirlineCommissionMasterSheet.class, superUserDealSheet.getId()); 
				commissionSheetDetails.setActive(superUserDealSheet.isActive());
				session.saveOrUpdate(commissionSheetDetails); 
				logger.info("updated-----commissionSheetDetails------"+commissionSheetDetails.isActive());

				blockcriteria=session.createCriteria(AirlineCommissionCompanyBlock.class);
				Conjunction conjuction = Restrictions.conjunction();
				conjuction.add(Restrictions.eq("appliedSheetId", commissionSheetDetails.getId()));
				conjuction.add(Restrictions.eq("isMasterBlock", true));
				conjuction.add(Restrictions.eq("isActive", false));
				blockcriteria.add(conjuction);
				AirlineCommissionCompanyBlock airlineCommissionCompanyBlock=(AirlineCommissionCompanyBlock) blockcriteria.uniqueResult();
				//logger.info("airlineCommissionCompanyBlock----blockId--"+airlineCommissionCompanyBlock.isActive());
				if(airlineCommissionCompanyBlock.getAppliedSheetId()==commissionSheetDetails.getId()){
					blockNew =  (AirlineCommissionCompanyBlock) session.get(AirlineCommissionCompanyBlock.class,airlineCommissionCompanyBlock.getId());
					blockNew.setActive(true);
					session.saveOrUpdate(blockNew);
					tx.commit();
				}
			}

		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			if(session!=null && session.isOpen())
				session.close();
		}
		return commissionSheetDetails;
	}
}




