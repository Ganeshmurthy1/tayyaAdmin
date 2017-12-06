package com.admin.dashboard.analysis.json.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.dashboard.analysis.json.dao.AirlineAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.AirlineAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.AirlineAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.AirlineAnalysisVO;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.WeeklySalesDataVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesVO;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
public class AirlineAnalysisAction  extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 10 Aug 2017
	 */
	AirlineAnalysisDao airlineAnalysisDao=new  AirlineAnalysisDaoImpl();
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	AirlineAnalysisVO airData=new AirlineAnalysisVO();
	 WeeklySalesVO flightWeeklyData=new  WeeklySalesVO();
	public String airlineBookingCount(){
		flightWeeklyData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,AirlineAnalysisDataVO> airlineMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			airData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> airlineListFromDB=airlineAnalysisDao.getAirlineBookedCount(sessionCompany);
			List<AirlineAnalysisDataVO> airlineList= new ArrayList<>();
			Map<String,AirlineAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(airlineListFromDB!=null && airlineListFromDB.size()>0){
				for(String airline:airlineListFromDB){
					airline=airline!=null && !airline.equals("")?airline.trim():"NA";
					if(hashMap.containsKey(airline.trim()))
					{
						AirlineAnalysisDataVO airlineAnalysisDataVO = hashMap.get(airline.trim());
						airlineAnalysisDataVO.setTotalCount(airlineAnalysisDataVO.getTotalCount()+1);
						hashMap.put(airline.trim(), airlineAnalysisDataVO);
					}
					else
					{
						AirlineAnalysisDataVO airlineAnalysisDataVO = new AirlineAnalysisDataVO();
						airlineAnalysisDataVO.setTotalCount(1);
						airlineAnalysisDataVO.setName(airline);
						hashMap.put(airline.trim(), airlineAnalysisDataVO);
					}
				}
				toatCount= airlineListFromDB.size();
				airlineMap=sortByValues(hashMap,airlineListFromDB.size());
			}

			if(airlineMap!=null && airlineMap.size()>0)
			{
				for(Entry<String, AirlineAnalysisDataVO> airlineEntry : airlineMap.entrySet())
				{
					airlineList.add(airlineEntry.getValue());
				}
			}
			if(airlineMap!=null && airlineMap.size()>0){
				airData.setTotalCount(toatCount);
				airData.setAirlineList(airlineList);
			}
			return SUCCESS;
		}
	}
	
 public String airApiProviderBookingCount(){
	 flightWeeklyData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,AirlineAnalysisDataVO> apiProviderMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			airData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> airlineListFromDB=airlineAnalysisDao.getAirApiProviderBookingCount(sessionCompany);
			List<AirlineAnalysisDataVO> apiProviderList= new ArrayList<>();
			Map<String,AirlineAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(airlineListFromDB!=null && airlineListFromDB.size()>0){
				for(String airline:airlineListFromDB){
					airline=airline!=null && !airline.equals("")?airline.trim():"NA";
					if(hashMap.containsKey(airline.trim()))
					{
						AirlineAnalysisDataVO airlineAnalysisDataVO = hashMap.get(airline.trim());
						airlineAnalysisDataVO.setTotalCount(airlineAnalysisDataVO.getTotalCount()+1);
						hashMap.put(airline.trim(), airlineAnalysisDataVO);
					}
					else
					{
						AirlineAnalysisDataVO airlineAnalysisDataVO = new AirlineAnalysisDataVO();
						airlineAnalysisDataVO.setTotalCount(1);
						airlineAnalysisDataVO.setName(airline);
						hashMap.put(airline.trim(), airlineAnalysisDataVO);
					}
				}
				toatCount= airlineListFromDB.size();
				apiProviderMap=sortByValues(hashMap,airlineListFromDB.size());
			}

			if(apiProviderMap!=null && apiProviderMap.size()>0)
			{
				for(Entry<String, AirlineAnalysisDataVO> airlineEntry : apiProviderMap.entrySet())
				{
					apiProviderList.add(airlineEntry.getValue());
				}
			}
			if(apiProviderMap!=null && apiProviderMap.size()>0){
				airData.setTotalCount(toatCount);
				airData.setApiProviderList(apiProviderList);
			}
			return SUCCESS;
		}
	} 
	 
 public String airDestinationBookingCount(){
	 flightWeeklyData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,AirlineAnalysisDataVO> apiProviderMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			airData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> airlineListFromDB=airlineAnalysisDao.getAirDestinationBookingCount(sessionCompany);
			List<AirlineAnalysisDataVO> destinationList= new ArrayList<>();
			Map<String,AirlineAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(airlineListFromDB!=null && airlineListFromDB.size()>0){
				for(String airline:airlineListFromDB){
					airline=airline!=null && !airline.equals("")?airline.trim():"NA";
					if(hashMap.containsKey(airline.trim()))
					{
						AirlineAnalysisDataVO airlineAnalysisDataVO = hashMap.get(airline.trim());
						airlineAnalysisDataVO.setTotalCount(airlineAnalysisDataVO.getTotalCount()+1);
						hashMap.put(airline.trim(), airlineAnalysisDataVO);
					}
					else
					{
						AirlineAnalysisDataVO airlineAnalysisDataVO = new AirlineAnalysisDataVO();
						airlineAnalysisDataVO.setTotalCount(1);
						airlineAnalysisDataVO.setName(airline);
						hashMap.put(airline.trim(), airlineAnalysisDataVO);
					}
				}
				toatCount= airlineListFromDB.size();
				apiProviderMap=sortByValues(hashMap,airlineListFromDB.size());
			}

			if(apiProviderMap!=null && apiProviderMap.size()>0)
			{
				for(Entry<String, AirlineAnalysisDataVO> airlineEntry : apiProviderMap.entrySet())
				{
					destinationList.add(airlineEntry.getValue());
				}
			}
			if(apiProviderMap!=null && apiProviderMap.size()>0){
				airData.setTotalCount(toatCount);
				airData.setDestinationList(destinationList);
			}
			return SUCCESS;
		}
	} 
	
	public String weeklySalesTop5Airlines(){
		airData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			flightWeeklyData.setError(error);
			return SUCCESS;
		}
		else{
			List<FlightOrderRow> flightOrderRowList=airlineAnalysisDao.getAirlineWeeklySales(sessionCompany);	
			if(flightOrderRowList!=null && flightOrderRowList.size()>0){
				Map<String,List<WeeklySalesDataVO>> weekDayMap=generateWeekDayMap(flightOrderRowList);
				 if(weekDayMap!=null) 
					 flightWeeklyData.setWeekDayMap(weekDayMap);
				 
				 else{
					 ErrorMsg error=new ErrorMsg();
						error.setMessage("No Data.");
						flightWeeklyData.setError(error);
				 }
			}
			else{
				 ErrorMsg error=new ErrorMsg();
					error.setMessage("No Data.");
					flightWeeklyData.setError(error);
			}
		}
		return SUCCESS;
	}
	public 	Map<String,List<WeeklySalesDataVO>> generateWeekDayMap(List<FlightOrderRow> hotelOrderRowList ) {
		List<WeeklySalesDataVO> monHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> tueHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> wedHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> thuHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> friHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> satHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> sunHotelsList=new LinkedList<>();
		Map<String,List<WeeklySalesDataVO>> weekDayMap=new LinkedHashMap<>();
		for(FlightOrderRow hotelOrderRow:hotelOrderRowList){
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
			String day=simpleDateformat.format(hotelOrderRow.getCreatedAt());
			String airline=hotelOrderRow.getAirline()!=null?hotelOrderRow.getAirline():"NA";
				 WeeklySalesDataVO hotelWeeklySalesDataVO=new  WeeklySalesDataVO();
				if(day.equals("Mon")){
					hotelWeeklySalesDataVO.setName(airline);
					hotelWeeklySalesDataVO.setWeekDay(day);
					monHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Tue")) {
					hotelWeeklySalesDataVO.setName(airline);
					hotelWeeklySalesDataVO.setWeekDay(day);
					tueHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Wed")){
					hotelWeeklySalesDataVO.setName(airline);
					hotelWeeklySalesDataVO.setWeekDay(day);
					wedHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Thu")){
					hotelWeeklySalesDataVO.setName(airline);
					hotelWeeklySalesDataVO.setWeekDay(day);
					thuHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Fri")) {
					hotelWeeklySalesDataVO.setName(airline);
					hotelWeeklySalesDataVO.setWeekDay(day);
					friHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sat")){ 
					hotelWeeklySalesDataVO.setName(airline);
					hotelWeeklySalesDataVO.setWeekDay(day);
					satHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sun")){ 
					hotelWeeklySalesDataVO.setName(airline);
					hotelWeeklySalesDataVO.setWeekDay(day);
					sunHotelsList.add(hotelWeeklySalesDataVO);
				}
			 
		}
		Map<String,WeeklySalesDataVO> hotelsMap=null;
		hotelsMap=countRepeatedHotelName(monHotelsList);
		monHotelsList=sortWeeklyTop5HotelList(hotelsMap);
		//if(!monHotelsList.isEmpty()) 
		weekDayMap.put("MON", monHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(tueHotelsList);
		tueHotelsList=sortWeeklyTop5HotelList(hotelsMap);
		//if(!tueHotelsList.isEmpty()) 
			weekDayMap.put("TUE", tueHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(wedHotelsList);
		wedHotelsList=sortWeeklyTop5HotelList(hotelsMap);
		//if(!wedHotelsList.isEmpty()) 
		weekDayMap.put("WED", wedHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(thuHotelsList);
		thuHotelsList=sortWeeklyTop5HotelList(hotelsMap);
		//if(!thuHotelsList.isEmpty()) 
		weekDayMap.put("THU", thuHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(friHotelsList);
		friHotelsList=sortWeeklyTop5HotelList(hotelsMap);
		//if(!friHotelsList.isEmpty()) 
		weekDayMap.put("FRI", friHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(satHotelsList);
		satHotelsList=sortWeeklyTop5HotelList(hotelsMap);
		//if(!satHotelsList.isEmpty())
		weekDayMap.put("SAT", satHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(sunHotelsList);
		sunHotelsList=sortWeeklyTop5HotelList(hotelsMap);
		//if(!sunHotelsList.isEmpty())
		weekDayMap.put("SUN", sunHotelsList);
		return weekDayMap; 
	}
	public 	List<WeeklySalesDataVO> sortWeeklyTop5HotelList(Map<String, WeeklySalesDataVO> hotelMap) {
		Set<Entry<String, WeeklySalesDataVO>> set = hotelMap.entrySet();
		List<Entry<String, WeeklySalesDataVO>> list = new ArrayList<Entry<String, WeeklySalesDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, WeeklySalesDataVO>>()
		{
			public int compare(Map.Entry<String, WeeklySalesDataVO> o1, Map.Entry<String, WeeklySalesDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		List<WeeklySalesDataVO>  hotelList = new LinkedList<>();
		int count=0;
		for(Map.Entry<String, WeeklySalesDataVO> entry:list){
			count++;
			if(count<=5)
				hotelList.add(entry.getValue());
			else
				break;
		}
		return hotelList; 
	}
	
	public static 	Map<String,WeeklySalesDataVO> countRepeatedHotelName(List<WeeklySalesDataVO> hotelsList) {
		Map<String,WeeklySalesDataVO> tueHotelsMap=new LinkedHashMap<>();
		if(hotelsList!=null && hotelsList.size()>0){
			for(WeeklySalesDataVO hotelWeeklySalesDataVO:hotelsList){
				String name=hotelWeeklySalesDataVO.getName()!=null && !hotelWeeklySalesDataVO.getName().equals("")?hotelWeeklySalesDataVO.getName():"NA";
				if(tueHotelsMap.containsKey(name)) {
					WeeklySalesDataVO hotelWeeklySalesDataVONew=tueHotelsMap.get(name);
					hotelWeeklySalesDataVONew.setTotalCount(hotelWeeklySalesDataVONew.getTotalCount()+1);
					tueHotelsMap.put(name, hotelWeeklySalesDataVONew);	
				}
				else{
					WeeklySalesDataVO hotelWeeklySalesDataVONew=new WeeklySalesDataVO();
					hotelWeeklySalesDataVONew.setName(name);
					hotelWeeklySalesDataVONew.setTotalCount(1);
					tueHotelsMap.put(name, hotelWeeklySalesDataVONew);
				}
			}
			
		}
		return tueHotelsMap;
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
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	public AirlineAnalysisVO getAirData() {
		return airData;
	}

	public void setAirData(AirlineAnalysisVO airData) {
		this.airData = airData;
	}
	public WeeklySalesVO getFlightWeeklyData() {
		return flightWeeklyData;
	}

	public void setFlightWeeklyData(WeeklySalesVO flightWeeklyData) {
		this.flightWeeklyData = flightWeeklyData;
	}

}
