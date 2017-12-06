package com.lintas.admin.DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.isl.admin.filter.CommonConfigFilter;
import com.isl.admin.page.CommonConfigPage;
import com.lintas.admin.model.CommonConfig;
import com.lintas.config.HibernateUtil;
public class CommonConfigDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonConfigDao.class);
	public boolean save(CommonConfig commonConfig){
		Session session=null;
		Transaction tx=null;
		boolean isSave=false;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.save(commonConfig);
			tx.commit();
			if(commonConfig!=null && commonConfig.getId()>0) 
				isSave=true;
		}
		catch(HibernateException hb){
			if(tx!=null)
				tx.rollback();
			hb.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen())  
				session.close();
		}
		return isSave;
	}
	public CommonConfigPage fetchList(CommonConfigPage commonConfigPage){
		Session session = null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CommonConfig.class);
			Conjunction conjunction = Restrictions.conjunction();
			CommonConfigFilter commonConfigFilter  =null;
			if(commonConfigPage!=null && commonConfigPage.isFilterEnabled())
			{
				commonConfigFilter = commonConfigPage.getCommonConfigFilter();
				if(commonConfigFilter.getMode() != null && !commonConfigFilter.getMode().equalsIgnoreCase("ALL"))
				{
					conjunction.add(Restrictions.eq("serverMode",commonConfigFilter.getMode()));
					 
				}

				criteria.add(conjunction);

			}
			Long rowCount= (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();

			if(rowCount>0)
			{
				if(commonConfigPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % commonConfigPage.getMaxItems() == 0)?(availableItems / commonConfigPage.getMaxItems()):((availableItems / commonConfigPage.getMaxItems()) + 1);
					commonConfigPage.setAvailableItems(availableItems);
					commonConfigPage.setAvailablePages(availablePages);
				} 
				int pageIndexDb = (commonConfigPage.getCurrentPageIndex() > 1)?commonConfigPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * commonConfigPage.getMaxItems();

				List<CommonConfig> filterCommonConfigList =  null;
				if(itemIndex <= rowCount)
				{
					criteria = session.createCriteria(CommonConfig.class);
					criteria.add(conjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(commonConfigPage.getMaxItems());
					filterCommonConfigList = criteria.list();
					commonConfigPage.setCommonConfigList(filterCommonConfigList);
				}
				else
				{
					commonConfigPage.setAvailableItems(0);
					commonConfigPage.setCommonConfigList(new ArrayList<CommonConfig>());
				}
			}	
			else
			{
				commonConfigPage.setAvailableItems(0);
				commonConfigPage.setAvailablePages(0);
				commonConfigPage.setCommonConfigList(new ArrayList<CommonConfig>());
			}
		}
		catch(HibernateException e){
			logger.info("---------HibernateException-------------"+e.getMessage());
		}
		finally{
			if(session != null && session.isOpen())
				session.close();
		}
		return commonConfigPage;
	}

	public  CommonConfig  update(CommonConfig commonConfig){
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(commonConfig);
			tx.commit();
		}
		catch(HibernateException hb){
			if(tx!=null)
				tx.rollback();
			hb.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen())  
				session.close();
		}
		return commonConfig;
	}
	public  CommonConfig  getCommonConfigDetails(CommonConfig commonConfig){
		Session session=null;
		Transaction tx=null;
		CommonConfig commonConfigNew=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Criteria criteria=session.createCriteria(CommonConfig.class);
			commonConfigNew=(CommonConfig) criteria.add(Restrictions.eq("id", commonConfig.getId())).uniqueResult();
			tx.commit();
		}
		catch(HibernateException hb){
			if(tx!=null)
				tx.rollback();
			hb.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen())  
				session.close();
		}
		return commonConfigNew;
	}
	public  CommonConfig  activeStatus(CommonConfig commonConfig){
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			session.saveOrUpdate(commonConfig);
			tx.commit();
		}
		catch(HibernateException hb){
			if(tx!=null)
				tx.rollback();
			hb.printStackTrace();
		}
		finally{
			if(session!=null && session.isOpen())  
				session.close();
		}
		return commonConfig;
	}


}
