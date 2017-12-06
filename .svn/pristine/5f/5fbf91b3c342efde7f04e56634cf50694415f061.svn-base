package com.admin.dashboard.analysis.json.action;

import java.text.SimpleDateFormat;
import java.math.BigDecimal;
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

import com.admin.dashboard.analysis.json.dao.BusAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.BusAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.BusAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.BusAnalysisVO;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.WeeklySalesDataVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesVO;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class BusAnalysisAction extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 10 Aug 2017
	 */
	BusAnalysisDao busAnalysisDao=new  BusAnalysisDaoImpl();
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	BusAnalysisVO busData=new BusAnalysisVO();
	WeeklySalesVO busWeeklyData=new  WeeklySalesVO();
	public String busApiProviderCount(){
		busWeeklyData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,BusAnalysisDataVO> busMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			busData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> busListFromDB=busAnalysisDao.getBusCompanyNameCount(sessionCompany);
			List<BusAnalysisDataVO> busList= new ArrayList<>();
			Map<String,BusAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(busListFromDB!=null && busListFromDB.size()>0){
				for(String bus:busListFromDB){
					if(hashMap.containsKey(bus.trim()))
					{
						BusAnalysisDataVO busAnalysisDataVO = hashMap.get(bus.trim());
						busAnalysisDataVO.setTotalCount(busAnalysisDataVO.getTotalCount()+1);
						hashMap.put(bus.trim(), busAnalysisDataVO);
					}
					else
					{
						BusAnalysisDataVO busAnalysisDataVO = new BusAnalysisDataVO();
						busAnalysisDataVO.setTotalCount(1);
						busAnalysisDataVO.setName(bus);
						hashMap.put(bus.trim(), busAnalysisDataVO);
					}
				}
				toatCount= busListFromDB.size();
				busMap=sortByValues(hashMap,busListFromDB.size());
			}

			if(busMap!=null && busMap.size()>0)
			{
				for(Entry<String, BusAnalysisDataVO> busEntry : busMap.entrySet())
				{
					busList.add(busEntry.getValue());
				}
			}
			if(busMap!=null && busMap.size()>0){
				busData.setTotalCount(toatCount);
				busData.setBusCompanyNameList(busList);
			}
			return SUCCESS;
		}
	}
	
	public String weeklySalesTop5BusLocations(){
		busData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			busWeeklyData.setError(error);
			return SUCCESS;
		}
		else{
			List<BusOrderRow> busOrderRowList=busAnalysisDao.getBusWeeklySales(sessionCompany);	
			if(busOrderRowList!=null && busOrderRowList.size()>0){
				Map<String,List<WeeklySalesDataVO>> weekDayMap=generateWeekDayMap(busOrderRowList);
				 if(weekDayMap!=null) 
					 busWeeklyData.setWeekDayMap(weekDayMap);
				 
				 else{
					 ErrorMsg error=new ErrorMsg();
						error.setMessage("No Data.");
						busWeeklyData.setError(error);
				 }
			}
			else{
				 ErrorMsg error=new ErrorMsg();
					error.setMessage("No Data.");
					busWeeklyData.setError(error);
			}
		}
		return SUCCESS;
	}
	 
	public 	Map<String,List<WeeklySalesDataVO>> generateWeekDayMap(List<BusOrderRow> busOrderRowList ) {
		List<WeeklySalesDataVO> monHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> tueHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> wedHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> thuHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> friHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> satHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> sunHotelsList=new LinkedList<>();
		Map<String,List<WeeklySalesDataVO>> weekDayMap=new LinkedHashMap<>();
		for(BusOrderRow busOrderRow:busOrderRowList){
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
			String day=simpleDateformat.format(busOrderRow.getCreatedAt());
			String origin=busOrderRow.getLocation()!=null?busOrderRow.getLocation():"NA";
			if(origin.equalsIgnoreCase("bengaluru") || origin.equalsIgnoreCase("benguluru")|| origin.equalsIgnoreCase("bengulure"))
				origin="Bangalore";
			origin=origin.toUpperCase();
				 WeeklySalesDataVO hotelWeeklySalesDataVO=new  WeeklySalesDataVO();
				if(day.equals("Mon")){
					hotelWeeklySalesDataVO.setName(origin);
					hotelWeeklySalesDataVO.setWeekDay(day);
					monHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Tue")) {
					hotelWeeklySalesDataVO.setName(origin);
					hotelWeeklySalesDataVO.setWeekDay(day);
					tueHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Wed")){
					hotelWeeklySalesDataVO.setName(origin);
					hotelWeeklySalesDataVO.setWeekDay(day);
					wedHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Thu")){
					hotelWeeklySalesDataVO.setName(origin);
					hotelWeeklySalesDataVO.setWeekDay(day);
					thuHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Fri")) {
					hotelWeeklySalesDataVO.setName(origin);
					hotelWeeklySalesDataVO.setWeekDay(day);
					friHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sat")){ 
					hotelWeeklySalesDataVO.setName(origin);
					hotelWeeklySalesDataVO.setWeekDay(day);
					satHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sun")){ 
					hotelWeeklySalesDataVO.setName(origin);
					hotelWeeklySalesDataVO.setWeekDay(day);
					sunHotelsList.add(hotelWeeklySalesDataVO);
				}
			 
		}
		Map<String,WeeklySalesDataVO> hotelsMap=null;
		hotelsMap=countRepeatedHotelName(monHotelsList);
		monHotelsList=sortWeeklyTop5LocationsList(hotelsMap);
		//if(!monHotelsList.isEmpty()) 
		weekDayMap.put("MON", monHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(tueHotelsList);
		tueHotelsList=sortWeeklyTop5LocationsList(hotelsMap);
		//if(!tueHotelsList.isEmpty()) 
			weekDayMap.put("TUE", tueHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(wedHotelsList);
		wedHotelsList=sortWeeklyTop5LocationsList(hotelsMap);
		//if(!wedHotelsList.isEmpty()) 
		weekDayMap.put("WED", wedHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(thuHotelsList);
		thuHotelsList=sortWeeklyTop5LocationsList(hotelsMap);
		//if(!thuHotelsList.isEmpty()) 
		weekDayMap.put("THU", thuHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(friHotelsList);
		friHotelsList=sortWeeklyTop5LocationsList(hotelsMap);
		//if(!friHotelsList.isEmpty()) 
		weekDayMap.put("FRI", friHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(satHotelsList);
		satHotelsList=sortWeeklyTop5LocationsList(hotelsMap);
		//if(!satHotelsList.isEmpty())
		weekDayMap.put("SAT", satHotelsList);
		hotelsMap=null;
		hotelsMap=countRepeatedHotelName(sunHotelsList);
		sunHotelsList=sortWeeklyTop5LocationsList(hotelsMap);
		//if(!sunHotelsList.isEmpty())
		weekDayMap.put("SUN", sunHotelsList);
		return weekDayMap; 
	}
	public 	List<WeeklySalesDataVO> sortWeeklyTop5LocationsList(Map<String, WeeklySalesDataVO> hotelMap) {
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
	
	public 	Map<String,WeeklySalesDataVO> countRepeatedHotelName(List<WeeklySalesDataVO> hotelsList) {
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
	
	public Map<String,BusAnalysisDataVO>  sortByValues(Map<String, BusAnalysisDataVO> hashMap,int totalCount) {
		Set<Entry<String, BusAnalysisDataVO>> set = hashMap.entrySet();
		List<Entry<String, BusAnalysisDataVO>> list = new ArrayList<Entry<String, BusAnalysisDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, BusAnalysisDataVO>>()
		{
			public int compare(Map.Entry<String, BusAnalysisDataVO> o1, Map.Entry<String, BusAnalysisDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		Map<String,BusAnalysisDataVO> sortedHashMap = new LinkedHashMap<>();
		int count=0;
		for(Map.Entry<String, BusAnalysisDataVO> entry:list){
			count++;
			BusAnalysisDataVO busAnalysisDataVO = entry.getValue();
			double percentage = ((double)busAnalysisDataVO.getTotalCount()/(double)totalCount)*100;
			busAnalysisDataVO.setPercentage(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_UP).doubleValue());
			if(count<=10)
				sortedHashMap.put(entry.getKey(), busAnalysisDataVO);
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
	public BusAnalysisVO getBusData() {
		return busData;
	}

	public void setBusData(BusAnalysisVO busData) {
		this.busData = busData;
	}
	public WeeklySalesVO getBusWeeklyData() {
		return busWeeklyData;
	}
	public void setBusWeeklyData(WeeklySalesVO busWeeklyData) {
		this.busWeeklyData = busWeeklyData;
	}
}