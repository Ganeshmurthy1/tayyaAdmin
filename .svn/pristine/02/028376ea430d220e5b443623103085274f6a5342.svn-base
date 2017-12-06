package com.admin.dashboard.analysis.json.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.dashboard.analysis.json.dao.InsuranceAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.InsuranceAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.InsuranceAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.InsuranceAnalysisVO;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class InsuranceAnalysisAction  extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 10 Aug 2017
	 */
	InsuranceAnalysisDao insuranceAnalysisDao=new  InsuranceAnalysisDaoImpl();
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	InsuranceAnalysisVO insuranceData=new InsuranceAnalysisVO();
	public String  insuranceApiProviderCount(){
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,InsuranceAnalysisDataVO> insuranceMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			insuranceData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> insuranceListFromDB=insuranceAnalysisDao.getInsuranceApiProviderCount(sessionCompany);
			List<InsuranceAnalysisDataVO> insuranceList= new ArrayList<>();
			Map<String,InsuranceAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(insuranceListFromDB!=null && insuranceListFromDB.size()>0){
				for(String insurance:insuranceListFromDB){
					if(hashMap.containsKey(insurance.trim()))
					{
						InsuranceAnalysisDataVO insuranceAnalysisDataVO = hashMap.get(insurance.trim());
						insuranceAnalysisDataVO.setTotalCount(insuranceAnalysisDataVO.getTotalCount()+1);
						hashMap.put(insurance.trim(), insuranceAnalysisDataVO);
					}
					else
					{
						InsuranceAnalysisDataVO insuranceAnalysisDataVO = new InsuranceAnalysisDataVO();
						insuranceAnalysisDataVO.setTotalCount(1);
						insuranceAnalysisDataVO.setName(insurance);
						hashMap.put(insurance.trim(), insuranceAnalysisDataVO);
					}
				}
				toatCount= insuranceListFromDB.size();
				insuranceMap=sortByValues(hashMap,insuranceListFromDB.size());
			}

			if(insuranceMap!=null && insuranceMap.size()>0)
			{
				for(Entry<String, InsuranceAnalysisDataVO> insuranceEntry : insuranceMap.entrySet())
				{
					insuranceList.add(insuranceEntry.getValue());
				}
			}
			if(insuranceMap!=null && insuranceMap.size()>0){
				insuranceData.setTotalCount(toatCount);
				insuranceData.setInsuranceList(insuranceList);
			}
			return SUCCESS;
		}
	}
	public Map<String,InsuranceAnalysisDataVO>  sortByValues(Map<String, InsuranceAnalysisDataVO> hashMap,int totalCount) {
		Set<Entry<String, InsuranceAnalysisDataVO>> set = hashMap.entrySet();
		List<Entry<String, InsuranceAnalysisDataVO>> list = new ArrayList<Entry<String, InsuranceAnalysisDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, InsuranceAnalysisDataVO>>()
		{
			public int compare(Map.Entry<String, InsuranceAnalysisDataVO> o1, Map.Entry<String, InsuranceAnalysisDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		Map<String,InsuranceAnalysisDataVO> sortedHashMap = new LinkedHashMap<>();
		int count=0;
		for(Map.Entry<String, InsuranceAnalysisDataVO> entry:list){
			count++;
			InsuranceAnalysisDataVO insuranceAnalysisDataVO = entry.getValue();
			double percentage = ((double)insuranceAnalysisDataVO.getTotalCount()/(double)totalCount)*100;
			insuranceAnalysisDataVO.setPercentage(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_UP).doubleValue());
			if(count<=10)
				sortedHashMap.put(entry.getKey(), insuranceAnalysisDataVO);
			else
				break;
		}
		return sortedHashMap; 
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	public InsuranceAnalysisVO getInsuranceData() {
		return insuranceData;
	}

	public void setInsuranceData(InsuranceAnalysisVO insuranceData) {
		this.insuranceData = insuranceData;
	}

}
