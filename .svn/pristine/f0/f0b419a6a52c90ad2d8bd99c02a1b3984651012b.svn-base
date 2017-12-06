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

import com.admin.dashboard.analysis.json.dao.VisaAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.VisaAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.VisaAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.VisaAnalysisVO;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class VisaAnalysisAction  extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 10 Aug 2017
	 */
	VisaAnalysisDao visaAnalysisDao=new  VisaAnalysisDaoImpl();
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	VisaAnalysisVO visaData=new VisaAnalysisVO();
	public String  visaApiProviderCount(){
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,VisaAnalysisDataVO> visaMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			visaData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> visaListFromDB=visaAnalysisDao.getVisaApiProviderCount(sessionCompany);
			List<VisaAnalysisDataVO> visaList= new ArrayList<>();
			Map<String,VisaAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(visaListFromDB!=null && visaListFromDB.size()>0){
				for(String visa:visaListFromDB){
					if(hashMap.containsKey(visa.trim()))
					{
						VisaAnalysisDataVO visaAnalysisDataVO = hashMap.get(visa.trim());
						visaAnalysisDataVO.setTotalCount(visaAnalysisDataVO.getTotalCount()+1);
						hashMap.put(visa.trim(), visaAnalysisDataVO);
					}
					else
					{
						VisaAnalysisDataVO visaAnalysisDataVO = new VisaAnalysisDataVO();
						visaAnalysisDataVO.setTotalCount(1);
						visaAnalysisDataVO.setName(visa);
						hashMap.put(visa.trim(), visaAnalysisDataVO);
					}
				}
				toatCount= visaListFromDB.size();
				visaMap=sortByValues(hashMap,visaListFromDB.size());
			}

			if(visaMap!=null && visaMap.size()>0)
			{
				for(Entry<String, VisaAnalysisDataVO> visaEntry : visaMap.entrySet())
				{
					visaList.add(visaEntry.getValue());
				}
			}
			if(visaMap!=null && visaMap.size()>0){
				visaData.setTotalCount(toatCount);
				visaData.setVisaList(visaList);
			}
			return SUCCESS;
		}
	}
	public Map<String,VisaAnalysisDataVO>  sortByValues(Map<String, VisaAnalysisDataVO> hashMap,int totalCount) {
		Set<Entry<String, VisaAnalysisDataVO>> set = hashMap.entrySet();
		List<Entry<String, VisaAnalysisDataVO>> list = new ArrayList<Entry<String, VisaAnalysisDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, VisaAnalysisDataVO>>()
		{
			public int compare(Map.Entry<String, VisaAnalysisDataVO> o1, Map.Entry<String, VisaAnalysisDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		Map<String,VisaAnalysisDataVO> sortedHashMap = new LinkedHashMap<>();
		int count=0;
		for(Map.Entry<String, VisaAnalysisDataVO> entry:list){
			count++;
			VisaAnalysisDataVO visaAnalysisDataVO = entry.getValue();
			double percentage = ((double)visaAnalysisDataVO.getTotalCount()/(double)totalCount)*100;
			visaAnalysisDataVO.setPercentage(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_UP).doubleValue());
			if(count<=10)
				sortedHashMap.put(entry.getKey(), visaAnalysisDataVO);
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
	public VisaAnalysisVO getVisaData() {
		return visaData;
	}

	public void setVisaData(VisaAnalysisVO visaData) {
		this.visaData = visaData;
	}

}
