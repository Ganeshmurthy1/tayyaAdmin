/**
 * 
 */
package com.admin.entity.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.api.provider.model.ApiProviderCommonConfig;
import com.admin.entity.pojo.CommonGstTax;
import com.admin.enums.utility.IndianUnionTerritories;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderRowGstTax;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.config.HibernateUtil;
import com.tayyarah.gst.model.FlightGstTax;

/**
 * @author MANISH
 *
 */
public class EntityDao {
	
	public String getGstTaxInfoByEntityId(int id){
			 Session session = null;
			String stateName=null;
				try{
					session = HibernateUtil.getSessionFactory().openSession();
					Criteria crit = session.createCriteria(CompanyEntity.class);
					crit.add(Restrictions.eq("companyEntityId", id));
					crit.setProjection(Projections.property("State"));
					stateName=(String) crit.uniqueResult();
				}catch (HibernateException e) {
				}finally {
					session.close(); 
				}
			 return stateName;
	}
	
	


}
