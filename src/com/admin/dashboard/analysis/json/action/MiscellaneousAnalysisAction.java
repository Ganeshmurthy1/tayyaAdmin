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

import com.admin.dashboard.analysis.json.dao.MiscellaneousAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.MiscellaneousAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.MiscellaneousAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.MiscellaneousAnalysisVO;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class MiscellaneousAnalysisAction   extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 10 Aug 2017
	 */
	MiscellaneousAnalysisDao miscellaneousAnalysisDao=new  MiscellaneousAnalysisDaoImpl();
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	MiscellaneousAnalysisVO miscellaneousData=new MiscellaneousAnalysisVO();
	public String  miscellaneousApiProviderCount(){
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,MiscellaneousAnalysisDataVO> miscellaneousMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			miscellaneousData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> miscellaneousListFromDB=miscellaneousAnalysisDao.getMiscellaneousApiProviderCount(sessionCompany);
			List<MiscellaneousAnalysisDataVO> miscellaneousList= new ArrayList<>();
			Map<String,MiscellaneousAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(miscellaneousListFromDB!=null && miscellaneousListFromDB.size()>0){
				for(String miscellaneous:miscellaneousListFromDB){
					if(hashMap.containsKey(miscellaneous.trim()))
					{
						MiscellaneousAnalysisDataVO miscellaneousAnalysisDataVO = hashMap.get(miscellaneous.trim());
						miscellaneousAnalysisDataVO.setTotalCount(miscellaneousAnalysisDataVO.getTotalCount()+1);
						hashMap.put(miscellaneous.trim(), miscellaneousAnalysisDataVO);
					}
					else
					{
						MiscellaneousAnalysisDataVO miscellaneousAnalysisDataVO = new MiscellaneousAnalysisDataVO();
						miscellaneousAnalysisDataVO.setTotalCount(1);
						miscellaneousAnalysisDataVO.setName(miscellaneous);
						hashMap.put(miscellaneous.trim(), miscellaneousAnalysisDataVO);
					}
				}
				toatCount= miscellaneousListFromDB.size();
				miscellaneousMap=sortByValues(hashMap,miscellaneousListFromDB.size());
			}

			if(miscellaneousMap!=null && miscellaneousMap.size()>0)
			{
				for(Entry<String, MiscellaneousAnalysisDataVO> miscellaneousEntry : miscellaneousMap.entrySet())
				{
					miscellaneousList.add(miscellaneousEntry.getValue());
				}
			}
			if(miscellaneousMap!=null && miscellaneousMap.size()>0){
				miscellaneousData.setTotalCount(toatCount);
				miscellaneousData.setMiscellaneousList(miscellaneousList);
			}
			return SUCCESS;
		}
	}
	public Map<String,MiscellaneousAnalysisDataVO>  sortByValues(Map<String, MiscellaneousAnalysisDataVO> hashMap,int totalCount) {
		Set<Entry<String, MiscellaneousAnalysisDataVO>> set = hashMap.entrySet();
		List<Entry<String, MiscellaneousAnalysisDataVO>> list = new ArrayList<Entry<String, MiscellaneousAnalysisDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, MiscellaneousAnalysisDataVO>>()
		{
			public int compare(Map.Entry<String, MiscellaneousAnalysisDataVO> o1, Map.Entry<String, MiscellaneousAnalysisDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		Map<String,MiscellaneousAnalysisDataVO> sortedHashMap = new LinkedHashMap<>();
		int count=0;
		for(Map.Entry<String, MiscellaneousAnalysisDataVO> entry:list){
			count++;
			MiscellaneousAnalysisDataVO miscellaneousAnalysisDataVO = entry.getValue();
			double percentage = ((double)miscellaneousAnalysisDataVO.getTotalCount()/(double)totalCount)*100;
			miscellaneousAnalysisDataVO.setPercentage(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_UP).doubleValue());
			if(count<=10)
				sortedHashMap.put(entry.getKey(), miscellaneousAnalysisDataVO);
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
	public MiscellaneousAnalysisVO getMiscellaneousData() {
		return miscellaneousData;
	}

	public void setMiscellaneousData(MiscellaneousAnalysisVO miscellaneousData) {
		this.miscellaneousData = miscellaneousData;
	}
}
