package com.admin.dashboard.analysis.json.daoImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.admin.dashboard.analysis.json.dao.AgencyAnalysisDao;
import com.admin.dashboard.analysis.json.vo.AgencyAnalysisDataVO;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.ArithmeticUti;

public class AgencyAnalysisDaoImpl implements AgencyAnalysisDao{
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AgencyAnalysisDaoImpl.class);
	@Override
	public List<AgencyAnalysisDataVO> calculateBookingAmount(List<Integer> companyIds) {
		// TODO Auto-generated method stub
		Session session=null;
		List<WalletAmountTranferHistory> walletHistoryList   = null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Disjunction disjunction=Restrictions.disjunction();
			Conjunction conjunction=Restrictions.conjunction();
			Criteria criteria = session.createCriteria(WalletAmountTranferHistory.class);
			conjunction.add(Restrictions.in("companyId",companyIds));
			conjunction.add(Restrictions.ne("failed", true));
			disjunction.add(Restrictions.eq("action", "Train Booking payment"));
			disjunction.add(Restrictions.eq("action", "Visa Booking payment"));
			disjunction.add(Restrictions.eq("action", "Bus Booking payment"));
			disjunction.add(Restrictions.eq("action", "Flight Booking payment"));
			disjunction.add(Restrictions.eq("action", "Hotel Booking payment"));
			disjunction.add(Restrictions.eq("action", "Car Booking payment"));
			disjunction.add(Restrictions.eq("action", "Insurance Booking payment"));
			disjunction.add(Restrictions.eq("action", "Miscellaneous Booking payment"));
			criteria.add(conjunction).add(disjunction);
			walletHistoryList=criteria.list();

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
		return sortAndGetData(walletHistoryList);
	}

	public Map<Integer, BigDecimal>  generateCommonAmountsMap(List<WalletAmountTranferHistory> list) {
		Map<Integer, BigDecimal> commonMap = new LinkedHashMap<>();
		if(list!=null && list.size()>0){
			for (WalletAmountTranferHistory history  : list) {
				if(history!=null){
					if (commonMap.containsKey(history.getCompanyId()))
						commonMap.put(history.getCompanyId(),commonMap.get(history.getCompanyId()).add(history.getAmount()));
					else 
						commonMap.put(history.getCompanyId(),history.getAmount());
				}

			}
		}
		return commonMap;
	}

	public List<AgencyAnalysisDataVO> sortAndGetData(List<WalletAmountTranferHistory> list){
		CompanyDAO companyDAO=new CompanyDAO();
		Map<Integer, BigDecimal> commonMap=generateCommonAmountsMap(list);
		List<AgencyAnalysisDataVO> corporateDataList=new LinkedList<>();
		int count=0;
		BigDecimal totalAmount=new BigDecimal(0);
		if(commonMap !=null && commonMap.size()>0){
			Set<Entry<Integer, BigDecimal>> set=commonMap.entrySet();
			for (Entry<Integer, BigDecimal> entry : set) {
				totalAmount=totalAmount.add(entry.getValue());
			}
			for (Entry<Integer, BigDecimal> entry : set) {
				count++;
				AgencyAnalysisDataVO agencyAnalysisDataVO=new AgencyAnalysisDataVO();
				Company company= companyDAO.getCompanyProfile(entry.getKey());
				if(company !=null)
					agencyAnalysisDataVO.setName(company.getCompanyname()!=null?company.getCompanyname():"NA");
				
				BigDecimal percentage=ArithmeticUti.divideTo2Decimal(entry.getValue(),totalAmount).multiply(new BigDecimal(100));
				agencyAnalysisDataVO.setPetcentage(percentage.setScale(2,BigDecimal.ROUND_UP));
				agencyAnalysisDataVO.setTotalAmount(entry.getValue().setScale(2, BigDecimal.ROUND_UP));
				
				if(count<=10)
					corporateDataList.add(agencyAnalysisDataVO);
				else
					break;

			}
			Collections.sort(corporateDataList,new Comparator<AgencyAnalysisDataVO>() {
				@Override
				public int compare(AgencyAnalysisDataVO o1, AgencyAnalysisDataVO o2) {
					return  o2.getTotalAmount().compareTo(o1.getTotalAmount())  ;
				}
			});

		}
		return corporateDataList;
	}
	
	public BigDecimal calTotalAmount(List<AgencyAnalysisDataVO> corporateDataList){
		BigDecimal totalAmount=new BigDecimal(0);
		for (AgencyAnalysisDataVO agencyAnalysisDataVO : corporateDataList) {
			totalAmount=totalAmount.add(agencyAnalysisDataVO.getTotalAmount());
		}
		return totalAmount;
		
	}
	
 

}