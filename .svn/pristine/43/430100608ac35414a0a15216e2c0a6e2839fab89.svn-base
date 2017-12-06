package com.isl.admin.commission.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.isl.admin.commission.model.AirlineCommissionBlock;
import com.isl.admin.commission.model.AirlineCommissionCompanyBlock;
import com.isl.admin.commission.model.AirlineCommissionSheet;
import com.lintas.config.HibernateUtil;



public class AirlineCommissionBlockDaoImp implements AirlineCommissionBlockDao {

	private SessionFactory  sessionFactory;
	public static final Logger logger = Logger.getLogger(AirlineCommissionBlockDaoImp.class);
	@Override
	public List<AirlineCommissionBlock> getAirlineCommissionBlock(
			Integer parentCompanyId, Integer childCompanyId)
					throws HibernateException {
		List<AirlineCommissionBlock> list  = new ArrayList<AirlineCommissionBlock>(); 
		Session sess = null;   
		//logger.info("########################## DB AirlineCommissionBlockRow retriving call" +parentCompanyId + ""+childCompanyId);
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = sess.createCriteria(AirlineCommissionBlock.class);
			crit.add(Restrictions.eq("parentCompanyId", parentCompanyId));
			crit.add(Restrictions.eq("childCompanyId", childCompanyId));




			LogicalExpression logicalExpression  = Restrictions.and(Restrictions.eq("parentCompanyId", parentCompanyId), 
					Restrictions.eq("childCompanyId", childCompanyId));
			crit.add(logicalExpression);   
			crit.setProjection(Projections.projectionList()
					.add(Projections.property("id"), "id")
					.add(Projections.property("iataCode"), "iataCode")
					.add(Projections.property("sheetId"), "sheetId")
					.add(Projections.property("parentCompanyId"), "parentCompanyId")
					.add(Projections.property("childCompanyId"), "childCompanyId")							
					.add(Projections.property("plbCommissionRetain"), "plbCommissionRetain")
					.add(Projections.property("iataCommissionRetain"), "iataCommissionRetain")

					)
			.setResultTransformer(Transformers.aliasToBean(AirlineCommissionBlock.class));	


			list = (List<AirlineCommissionBlock>) crit.list();   
		}catch (HibernateException e) {     
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
 
	@Override
	public List<AirlineCommissionBlock> createAirlineCommissionBlock(
			Integer parentCompanyId,  Integer childCompanyId, Long sheetId)
					throws HibernateException {
		Session sess = null;
		Transaction tx = null;
		logger.info("insertAirlineCommissionBlock called " + parentCompanyId + childCompanyId + sheetId);
		List<AirlineCommissionBlock> listnew  = new ArrayList<AirlineCommissionBlock>();	

		try{
			sess = HibernateUtil.getSessionFactory().openSession();			

			Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);
			cr.add(Restrictions.eq("sheetId", sheetId));

			cr.setProjection(Projections.projectionList()
					.add(Projections.property("id"), "id")
					.add(Projections.property("iataCode"), "iataCode")
					.add(Projections.property("sheetId"), "sheetId")					
					)
			.setResultTransformer(Transformers.aliasToBean(AirlineCommissionSheet.class));		

			List<AirlineCommissionSheet> list = cr.list();
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}
			sess = null;
			sess = HibernateUtil.getSessionFactory().openSession();
			logger.info("list " +list.size());
			int count=0;

			if(list!=null && list.size()>0)
			{
				int sheetsize = list.size() + 1;
				for (AirlineCommissionSheet airlineCommissionSheetRow : list) {
					AirlineCommissionBlock airlineCommissionBlockRow = new AirlineCommissionBlock();
					String tempid = String.valueOf(sheetsize); 		
					Long id = new Long(tempid);	
					airlineCommissionBlockRow.setId(id);

					airlineCommissionBlockRow.setIataCode(airlineCommissionSheetRow.getIataCode());
					airlineCommissionBlockRow.setIataCommissionRetain(new BigDecimal(0));
					airlineCommissionBlockRow.setPlbCommissionRetain(new BigDecimal(0));				

					tx = sess.beginTransaction();
					sess.save(airlineCommissionBlockRow);

					if ( ++count % 100 == 0 ) {
						sess.flush();
						sess.clear();
					}					
					tx.commit();
					listnew.add(airlineCommissionBlockRow);
					sheetsize++;
				}
			}		


		}catch (HibernateException e) {
			//logger.info("########################## DB insertAirlineCommissionBlock call error");
			 logger.error("HibernateException" ,e); 
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
	public Map<Integer, AirlineCommissionBlock> getAirlineCommissionBlockMap(
			Integer childCompanyId, String iataCode)
					throws HibernateException {
		Map<Integer, AirlineCommissionBlock> airlineCommissionBlockMap  = new HashMap<Integer, AirlineCommissionBlock>(); 
		Session sess = null;   

		try{
			sess =   HibernateUtil.getSessionFactory().openSession();		   
			//Criteria crit = sess.createCriteria(AirlineCommissionBlockRow.class);

			AirlineCommissionBlock airlineCommissionBlockRow = null;
			while(childCompanyId!=null)
			{   
 
				String sql = "select * from airline_commission_block where child_company_id = "+childCompanyId+" and  iata_code= '"+iataCode+"'";
				logger.info("########################## DB get airline_commission_block company query--"+sql);
				SQLQuery query = sess.createSQLQuery(sql);
				query.addEntity(AirlineCommissionBlock.class);
				airlineCommissionBlockRow = (AirlineCommissionBlock) query.uniqueResult();
				//airlineCommissionBlockRow  = (AirlineCommissionBlockRow) crit.uniqueResult(); 
				if(airlineCommissionBlockRow != null)
				{
					//logger.info("##########################  deal block for CompanyId -"+childCompanyId + "deal block-"+airlineCommissionBlockRow.toString() );
					//logger.info("##########################  deal block parent company id -"+airlineCommissionBlockRow.getParentCompanyId());

					//airlineCommissionBlockMap.put(airlineCommissionBlockRow.getParentCompanyId(), airlineCommissionBlockRow);
					//childCompanyId = airlineCommissionBlockRow.getParentCompanyId();
				}
				else
				{
					childCompanyId = null;
					logger.info("########################## unable to find deal block for CompanyId -"+childCompanyId);
					break;
				}
			}

		}catch (HibernateException e) {     
			logger.error(e);
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{    
				sess.close(); 
			}

		}
		return airlineCommissionBlockMap;
	}


	@Override
	public List<AirlineCommissionBlock> insertAirlineCommissionBlock(
			List<AirlineCommissionBlock> airlineCommissionBlockRows)
					throws HibernateException {
		Session sess = null;
		Transaction tx = null;
		logger.info("insertAirlineCommissionBlock called ");
		List<AirlineCommissionBlock> listnew  = new ArrayList<AirlineCommissionBlock>();	
		try{
			sess = sessionFactory.openSession();
			tx = sess.beginTransaction();
			int count=0;
			for (AirlineCommissionBlock airlineCommissionBlockRow : airlineCommissionBlockRows) {

				airlineCommissionBlockRow = (AirlineCommissionBlock) sess.save(airlineCommissionBlockRow);
				if ( ++count % 100 == 0 ) {
					sess.flush();
					sess.clear();
				}
				listnew.add(airlineCommissionBlockRow);
			}

		}catch (HibernateException e) {
			logger.info("########################## DB insertAirlineCommissionBlock call error");
			logger.error(e);
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
	public List<AirlineCommissionBlock> updateAirlineCommissionBlock(
			List<AirlineCommissionBlock> airlineCommissionBlockRows)
					throws HibernateException {
		Session sess = null;
		Transaction tx = null;
		logger.info("updateAirlineCommissionBlock called ");
		List<AirlineCommissionBlock> listnew  = new ArrayList<AirlineCommissionBlock>();	
		try{
			sess = HibernateUtil.getSessionFactory().openSession();

			int count=0;
			for (AirlineCommissionBlock airlineCommissionBlockRow : airlineCommissionBlockRows) {


				Criteria crit = sess.createCriteria(AirlineCommissionBlock.class);			
				crit.add(Restrictions.eq("id", airlineCommissionBlockRow.getId()));			
				AirlineCommissionBlock airlineCommissionBlockRowDb = (AirlineCommissionBlock) crit.uniqueResult();
				airlineCommissionBlockRowDb.setIataCommissionRetain(airlineCommissionBlockRow.getIataCommissionRetain());
				airlineCommissionBlockRowDb.setPlbCommissionRetain(airlineCommissionBlockRow.getPlbCommissionRetain());
				tx = sess.beginTransaction();	
				sess.update(airlineCommissionBlockRowDb);
				if ( ++count % 100 == 0 ) {
					sess.flush();
					sess.clear();
				}
				tx.commit();
				listnew.add(airlineCommissionBlockRowDb);
			}

		}catch (HibernateException e) {
			logger.info("########################## DB updateAirlineCommissionBlock update call error");
			logger.error(e);
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
	public AirlineCommissionCompanyBlock createCompanyDealSheetBlock(AirlineCommissionCompanyBlock commissionBlockSheet) throws HibernateException {
		Session sess = null;
		Transaction tx = null;
		try{
			sess = HibernateUtil.getSessionFactory().openSession();
			tx = sess.beginTransaction();	
			commissionBlockSheet.setSheetInfo("");
			sess.save(commissionBlockSheet);
			tx.commit();
		}catch (HibernateException e) {
			logger.info("########################## DB updateAirlineCommissionBlock update call error");
			logger.error(e);
			if (tx!=null) tx.rollback();
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}
		}
		return commissionBlockSheet;
	}
	@Override
	public AirlineCommissionCompanyBlock createCompanyCommissionBlock(AirlineCommissionCompanyBlock airlineCommissionCompanyBlock)
			throws HibernateException, Exception{
		Session sess = null;
		Transaction tx = null;
		logger.info("createCompanyCommissionBlock called ");
		List<AirlineCommissionBlock> listnew  = new ArrayList<AirlineCommissionBlock>();	
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();
			if(airlineCommissionCompanyBlock.getCreatedByCompanyID()!=0 && airlineCommissionCompanyBlock.getCreatedByUserID()!=0 && airlineCommissionCompanyBlock.getAppliedSheetId() != null)
			{
				if(airlineCommissionCompanyBlock.getAppliedSheetId()==null)
				{
					airlineCommissionCompanyBlock.setAppliedSheetId(0L);
				}
				airlineCommissionCompanyBlock.setMasterBlock(false);
				airlineCommissionCompanyBlock.setName(airlineCommissionCompanyBlock.getName());
				tx = sess.beginTransaction();
				airlineCommissionCompanyBlock.setSheetInfo("");
				sess.save(airlineCommissionCompanyBlock);			
				tx.commit();
				logger.info("company block created -- block id--"+airlineCommissionCompanyBlock.getId());

				Criteria cr = sess.createCriteria(AirlineCommissionSheet.class);
				cr.add(Restrictions.eq("sheetId", airlineCommissionCompanyBlock.getAppliedSheetId()));
				cr.setProjection(Projections.projectionList()							
						.add(Projections.property("sheetId"), "sheetId")						
						.add(Projections.property("iataCode"), "iataCode")					
						)
				.setResultTransformer(Transformers.aliasToBean(AirlineCommissionSheet.class));
				List<AirlineCommissionSheet> list  = cr.list();	
				if(list != null && list.size()>0)
				{				
					int count=0;
					for (AirlineCommissionSheet airlineCommissionSheetRow : list) {

						AirlineCommissionBlock airlineCommissionBlockRow = new AirlineCommissionBlock();
						airlineCommissionBlockRow.setAirlineCommissionCompanyBlock(airlineCommissionCompanyBlock);
						airlineCommissionBlockRow.setIataCode(airlineCommissionSheetRow.getIataCode());
						airlineCommissionBlockRow.setIataCommissionRetain(new BigDecimal(0));
						airlineCommissionBlockRow.setPlbCommissionRetain(new BigDecimal(0));	
						airlineCommissionBlockRow.setAirline(airlineCommissionSheetRow.getAirline());
						Timestamp updated_at = new Timestamp(new Date().getTime());	
						airlineCommissionBlockRow.setLastModifiedAt(updated_at);

						tx = sess.beginTransaction();
						sess.save(airlineCommissionBlockRow);
						if (++count % 100 == 0 ) {
							sess.flush();
							sess.clear();
						} 
						listnew.add(airlineCommissionBlockRow);
						tx.commit();
					}
					//airlineCommissionCompanyBlock.setAirlineCommissionBlockRowList(listnew);
					//logger.info("##### AirlineCommissionBlockRow items created.. ");
				}			
			}
			else
			{
				throw new Exception("Comp id, user id or sheet id null"); 
			}
		}catch (HibernateException e) {
			//logger.info("########################## DB insertAirlineCommissionSheet call error" +e.getLocalizedMessage());
			 logger.error(e);
			if (tx!=null) tx.rollback();
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}
		}
		return airlineCommissionCompanyBlock;
	}
	@Override
	public List<AirlineCommissionCompanyBlock> getChildrenCompanyCommissionBlocks(boolean isMaster, int companyId)
			throws HibernateException {
		Session sess = null;
		logger.info("getChildrenCompanyCommissionBlocks called ");
		List<AirlineCommissionCompanyBlock> listnew =null;
		try{			
			logger.info("children block search for companyId--"+companyId);
			sess =  HibernateUtil.getSessionFactory().openSession();
			Criteria cr = sess.createCriteria(AirlineCommissionCompanyBlock.class);

			Conjunction conjuction = Restrictions.conjunction();
			conjuction.add(Restrictions.eq("createdByCompanyID", companyId));
			conjuction.add(Restrictions.eq("isMasterBlock", isMaster));
			/*conjuction.add(Restrictions.eq("isActive", true));*/
			/*LogicalExpression logicalExpression  = Restrictions.and(Restrictions.eq("createdByCompanyID", companyId), 
					Restrictions.eq("isMasterBlock", isMaster)); */
			cr.add(conjuction);
			listnew  = cr.list();
			logger.info("List<AirlineCommissionCompanyBlock> list--------"+listnew.size());

		}catch (HibernateException e) {
			logger.info("########################## DB insertAirlineCommissionSheet call error");
			logger.error(e);
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
	public AirlineCommissionCompanyBlock getAirlineCommissionCompanyBlock(Long blockId) throws HibernateException {
		// TODO Auto-generated method stub
		Session session=null;
		AirlineCommissionCompanyBlock companyBlock=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(AirlineCommissionCompanyBlock.class);
			cr.add(Restrictions.eq("id", blockId));
			companyBlock=(AirlineCommissionCompanyBlock) cr.uniqueResult();
		}
		catch (HibernateException e) {
			companyBlock = null;
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyBlock;
	}
	@Override
	public AirlineCommissionBlock getAirlineCommissionBlockRow (String iatacode,Long blockId) throws HibernateException {
		// TODO Auto-generated method stub
		Session session=null;

		AirlineCommissionBlock companyRow=null;
		try{
			session= HibernateUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(AirlineCommissionBlock.class);
			LogicalExpression logicalExpression  = Restrictions.and(Restrictions.eq("iataCode", iatacode), 
					Restrictions.eq("airlineCommissionCompanyBlock.id", blockId));
			cr.add(logicalExpression);   
			companyRow=(AirlineCommissionBlock) cr.uniqueResult();
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return companyRow;

	}
	@Override
	public List<AirlineCommissionBlock> getAirlineCommissionBlockRow (Long blockId) throws HibernateException {
		// TODO Auto-generated method stub
		Session session=null;		 
		List<AirlineCommissionBlock> airlineCommissionBlockRows = null;
		logger.info("blockId" +blockId);
		try{
			session= HibernateUtil.getSessionFactory().openSession();			
			Criteria cr = session.createCriteria(AirlineCommissionBlock.class);
			cr.add(Restrictions.eq("airlineCommissionCompanyBlock.id", blockId)); 
			airlineCommissionBlockRows =  cr.list();
			logger.info("airlineCommissionBlockRows" +airlineCommissionBlockRows.size());
		}
		catch (HibernateException e) {
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return airlineCommissionBlockRows;

	}
	@Override
	public AirlineCommissionCompanyBlock updateAirlineCommissionCompanyBlock(AirlineCommissionCompanyBlock airlineCommissionCompanyBlock) {
		// TODO Auto-generated method stub
		Session session=null;	
		Transaction  tx=null;	
		AirlineCommissionCompanyBlock airlineCommissionCompanyBlockDB = null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			airlineCommissionCompanyBlockDB = (AirlineCommissionCompanyBlock)session.get(AirlineCommissionCompanyBlock.class, airlineCommissionCompanyBlock.getId()); 
			airlineCommissionCompanyBlockDB.setActive(airlineCommissionCompanyBlock.isActive());
			airlineCommissionCompanyBlockDB.setName(airlineCommissionCompanyBlock.getName());
			airlineCommissionCompanyBlockDB.setDescription(airlineCommissionCompanyBlock.getDescription());
			airlineCommissionCompanyBlockDB.setAppliedSheetId(airlineCommissionCompanyBlock.getAppliedSheetId());
			airlineCommissionCompanyBlock.setSheetInfo("");
			session.saveOrUpdate(airlineCommissionCompanyBlockDB); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return airlineCommissionCompanyBlockDB;
	}
	public AirlineCommissionCompanyBlock createDuplicateCompanyCommissionBlock(AirlineCommissionCompanyBlock airlineCommissionCompanyBlock)
			throws HibernateException, Exception{
		Session sess = null;
		Transaction tx = null;
		logger.info("createCompanyCommissionBlock called ");
		AirlineCommissionCompanyBlock commissionCompanyBlock=new AirlineCommissionCompanyBlock();
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();
			tx = sess.beginTransaction();
			if(airlineCommissionCompanyBlock!=null && airlineCommissionCompanyBlock.getCreatedByCompanyID()!=0 && airlineCommissionCompanyBlock.getCreatedByUserID()!=0 && airlineCommissionCompanyBlock.getAppliedSheetId() != null)
			{
				commissionCompanyBlock.setMasterBlock(false);
				commissionCompanyBlock.setName(airlineCommissionCompanyBlock.getName());
				commissionCompanyBlock.setSheetInfo("");
				commissionCompanyBlock.setActive(airlineCommissionCompanyBlock.isActive());
				commissionCompanyBlock.setAppliedSheetId(airlineCommissionCompanyBlock.getAppliedSheetId());
				commissionCompanyBlock.setCreatedByCompanyID(airlineCommissionCompanyBlock.getCreatedByCompanyID());
				commissionCompanyBlock.setCreatedByUserID(airlineCommissionCompanyBlock.getCreatedByUserID());
				commissionCompanyBlock.setDescription(airlineCommissionCompanyBlock.getDescription());
				sess.save(commissionCompanyBlock);			
				logger.info("company block Duplicated -- block id--"+commissionCompanyBlock.getId());

				// copy commision block for block id now
				Criteria cr = sess.createCriteria(AirlineCommissionBlock.class);
				cr.add(Restrictions.eq("airlineCommissionCompanyBlock.id", airlineCommissionCompanyBlock.getId()));
				List<AirlineCommissionBlock> list  = cr.list();	
				//logger.info("list----------"+list.size());

				if(list != null && list.size()>0)
				{				
					int count=0;
					for (AirlineCommissionBlock airlineCommissionBlock : list) {
						AirlineCommissionBlock airlineCommissionBlockNew = new AirlineCommissionBlock();
						AirlineCommissionCompanyBlock commissionCompanyBlockNew=new AirlineCommissionCompanyBlock();
						commissionCompanyBlockNew=commissionCompanyBlock;
						airlineCommissionBlockNew.setAirlineCommissionCompanyBlock(commissionCompanyBlockNew);
						airlineCommissionBlockNew.setIataCode(airlineCommissionBlock.getIataCode());
						airlineCommissionBlockNew.setIataCommissionRetain(airlineCommissionBlock.getIataCommissionRetain());
						airlineCommissionBlockNew.setPlbCommissionRetain(airlineCommissionBlock.getPlbCommissionRetain());					
						Timestamp updated_at = new Timestamp(new Date().getTime());	
						airlineCommissionBlockNew.setLastModifiedAt(updated_at);
						sess.save(airlineCommissionBlockNew);
						if (++count % 100 == 0 ) {
							sess.flush();
							sess.clear();
						} 
					}
					//airlineCommissionCompanyBlock.setAirlineCommissionBlockRowList(listnew);
					//logger.info("##### AirlineCommissionBlockRow items created.. ");
				}			
				tx.commit();
			}
			else
			{
				throw new Exception("Comp id, user id or sheet id null"); 
			}
		}catch (HibernateException e) {
			///logger.info("########################## DB insertAirlineCommissionSheet call error" +e.getLocalizedMessage());
			 logger.error(e);
			if (tx!=null) tx.rollback();
			throw e; 
		}finally {
			if(sess != null && sess.isOpen())
			{				
				sess.close(); 
			}
		}
		return commissionCompanyBlock;
	}

	public AirlineCommissionCompanyBlock updateBlockApproval(AirlineCommissionCompanyBlock airlineCommissionBlockSheet) {
		Session sess=null;	
		Transaction  tx=null;
		AirlineCommissionCompanyBlock commissionCompanyBlockNew=null;
		try{
			sess =  HibernateUtil.getSessionFactory().openSession();
			tx = sess.beginTransaction();
			commissionCompanyBlockNew= (AirlineCommissionCompanyBlock)sess.get(AirlineCommissionCompanyBlock.class, airlineCommissionBlockSheet.getId()); 
			commissionCompanyBlockNew.setActive(airlineCommissionBlockSheet.isActive());
			sess.update(commissionCompanyBlockNew);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null)
				tx.rollback();
		}finally {
			if(sess != null && sess.isOpen())
				sess.close(); 
		}
		return commissionCompanyBlockNew;
	}
	
	public boolean setDefaultCommissions (Long blockId,  BigDecimal iataDefault, BigDecimal plbDefault) throws HibernateException {
		// TODO Auto-generated method stub
		Transaction  tx=null;
		Session session=null;	
		boolean response = false;
		List<AirlineCommissionBlock> airlineCommissionBlockRows = null;
		//logger.info("blockId" +blockId);
		try{
			session= HibernateUtil.getSessionFactory().openSession();			
			Criteria cr = session.createCriteria(AirlineCommissionBlock.class);
			cr.add(Restrictions.eq("airlineCommissionCompanyBlock.id", blockId)); 
			airlineCommissionBlockRows =  cr.list();
			if(airlineCommissionBlockRows != null && airlineCommissionBlockRows.size()>0)
			{				
				int count=0;
				tx = session.beginTransaction();
				for (AirlineCommissionBlock airlineCommissionBlock : airlineCommissionBlockRows) {					
					airlineCommissionBlock.setIataCode(airlineCommissionBlock.getIataCode());
					if (iataDefault != null) {
						airlineCommissionBlock.setIataCommissionRetain(iataDefault);
					}
					if(plbDefault != null)
					{
						airlineCommissionBlock.setPlbCommissionRetain(plbDefault);		
					}
					
								
					Timestamp updated_at = new Timestamp(new Date().getTime());	
					airlineCommissionBlock.setLastModifiedAt(updated_at);
					session.update(airlineCommissionBlock);
					if (++count % 100 == 0 ) {
						session.flush();
						session.clear();
					} 
				}
				response = true;
				tx.commit();
				//airlineCommissionCompanyBlock.setAirlineCommissionBlockRowList(listnew);
				//logger.info("##### AirlineCommissionBlockRow items created.. ");
			}			
			//logger.info("airlineCommissionBlockRows" +airlineCommissionBlockRows.size());
		}
		catch (HibernateException e) {
			if (tx!=null)
				tx.rollback();
			response = false;
			logger.error("-------HibernateException-------"+  e.getMessage());
		}
		finally {
			if(session != null && session.isOpen())
				session.close(); 
		}
		return response;

	}

}
