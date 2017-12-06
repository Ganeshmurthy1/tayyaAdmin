/**
 * 
 */
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

import com.admin.dashboard.analysis.json.dao.AirlineAnalysisDao;
import com.admin.dashboard.analysis.json.dao.BusAnalysisDao;
import com.admin.dashboard.analysis.json.dao.CarAnalysisDao;
import com.admin.dashboard.analysis.json.dao.HotelAnalysisDao;
import com.admin.dashboard.analysis.json.dao.TrainAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.AirlineAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.daoImpl.BusAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.daoImpl.CarAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.daoImpl.HotelAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.daoImpl.TrainAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.AirlineAnalysisVO;
import com.admin.dashboard.analysis.json.vo.AirlineAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author MANISH
 *
 */
public class DestinationAnalysisCommonAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	AirlineAnalysisVO commonData=new AirlineAnalysisVO();
	AirlineAnalysisDao airlineAnalysisDao=new  AirlineAnalysisDaoImpl();
	HotelAnalysisDao hotelAnalysisDao=new HotelAnalysisDaoImpl();
	BusAnalysisDao busAnalysisDao=new BusAnalysisDaoImpl();
	CarAnalysisDao carAnalysisDao=new CarAnalysisDaoImpl();
	TrainAnalysisDao trainAnalysisDao=new TrainAnalysisDaoImpl();
	String serviceType;
	
	public String commonDestinationBookingCount(){
			Company sessionCompany=(Company) sessionMap.get("Company");
			Map<String,AirlineAnalysisDataVO> apiProviderMap=null;
			List<String> destinationListFromDB=new ArrayList<String>();
			if(sessionCompany==null){
				ErrorMsg error=new ErrorMsg();
				error.setMessage("Session is expired.");
				commonData.setError(error);
				return SUCCESS;
			}
			else{
				if(serviceType!=null && !serviceType.trim().equalsIgnoreCase("")){
					if(serviceType.equalsIgnoreCase("flight"))
						destinationListFromDB=airlineAnalysisDao.getAirDestinationBookingCount(sessionCompany);
					else if(serviceType.equalsIgnoreCase("hotel"))
						destinationListFromDB=hotelAnalysisDao.getHotelDestinationBookingCount(sessionCompany);
					else if(serviceType.equalsIgnoreCase("car"))
						destinationListFromDB=carAnalysisDao.getCarDestinationBookingCount(sessionCompany);
					else if(serviceType.equalsIgnoreCase("bus"))
						destinationListFromDB=busAnalysisDao.getBusDestinationBookingCount(sessionCompany);
					else if(serviceType.equalsIgnoreCase("train"))
						destinationListFromDB=trainAnalysisDao.getTrainDestinationBookingCount(sessionCompany);
					else if(serviceType.equalsIgnoreCase("visa"))
						destinationListFromDB=airlineAnalysisDao.getAirDestinationBookingCount(sessionCompany);
					else if(serviceType.equalsIgnoreCase("insurance"))
						destinationListFromDB=airlineAnalysisDao.getAirDestinationBookingCount(sessionCompany);
				}
				
				List<AirlineAnalysisDataVO> destinationList= new ArrayList<>();
				Map<String,AirlineAnalysisDataVO> hashMap= new HashMap<>();
				int toatCount=0;
				if(destinationListFromDB!=null && destinationListFromDB.size()>0){
					for(String destination:destinationListFromDB){
						
						 if(destination.equalsIgnoreCase("bengaluru") || destination.equalsIgnoreCase("benguluru") ||destination.equalsIgnoreCase("bengulure")||destination.equalsIgnoreCase("Bengaluru(Bangalore)"))
							 destination="Bangalore";
						
						destination=destination!=null && !destination.equals("")?destination.trim():"NA";
						if(hashMap.containsKey(destination.trim()))
						{
							AirlineAnalysisDataVO airlineAnalysisDataVO = hashMap.get(destination.trim());
							airlineAnalysisDataVO.setTotalCount(airlineAnalysisDataVO.getTotalCount()+1);
							hashMap.put(destination.trim(), airlineAnalysisDataVO);
						}
						else
						{
							AirlineAnalysisDataVO airlineAnalysisDataVO = new AirlineAnalysisDataVO();
							airlineAnalysisDataVO.setTotalCount(1);
							airlineAnalysisDataVO.setName(destination);
							hashMap.put(destination.trim(), airlineAnalysisDataVO);
						}
					}
					toatCount= destinationListFromDB.size();
					apiProviderMap=sortByValues(hashMap,destinationListFromDB.size());
				}

				if(apiProviderMap!=null && apiProviderMap.size()>0)
				{
					for(Entry<String, AirlineAnalysisDataVO> airlineEntry : apiProviderMap.entrySet())
					{
						destinationList.add(airlineEntry.getValue());
					}
				}
				if(apiProviderMap!=null && apiProviderMap.size()>0){
					commonData.setTotalCount(toatCount);
					commonData.setDestinationList(destinationList);
				}
				return SUCCESS;
			}
		}
	
	public Map<String,AirlineAnalysisDataVO>  sortByValues(Map<String, AirlineAnalysisDataVO> hashMap,int totalCount) {
		Set<Entry<String, AirlineAnalysisDataVO>> set = hashMap.entrySet();
		List<Entry<String, AirlineAnalysisDataVO>> list = new ArrayList<Entry<String, AirlineAnalysisDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, AirlineAnalysisDataVO>>()
		{
			public int compare(Map.Entry<String, AirlineAnalysisDataVO> o1, Map.Entry<String, AirlineAnalysisDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		Map<String,AirlineAnalysisDataVO> sortedHashMap = new LinkedHashMap<>();
		int count=0;
		for(Map.Entry<String, AirlineAnalysisDataVO> entry:list){
			count++;
			AirlineAnalysisDataVO airlineAnalysisDataVO = entry.getValue();
			double percentage = ((double)airlineAnalysisDataVO.getTotalCount()/(double)totalCount)*100;
			airlineAnalysisDataVO.setPercentage(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_UP).doubleValue());
			if(count<=10)
				sortedHashMap.put(entry.getKey(), airlineAnalysisDataVO);
			else
				break;
		}
		return sortedHashMap; 
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;		
	}

	public AirlineAnalysisVO getCommonData() {
		return commonData;
	}

	public void setCommonData(AirlineAnalysisVO commonData) {
		this.commonData = commonData;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	} 
}
