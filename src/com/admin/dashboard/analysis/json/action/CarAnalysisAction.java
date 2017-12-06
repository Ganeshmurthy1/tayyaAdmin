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

import com.admin.dashboard.analysis.json.dao.CarAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.CarAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.CarAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.CarAnalysisVO;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.WeeklySalesDataVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesVO;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class CarAnalysisAction extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 10 Aug 2017
	 */
	CarAnalysisDao carAnalysisDao=new  CarAnalysisDaoImpl();
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	CarAnalysisVO carData=new CarAnalysisVO();
	WeeklySalesVO carWeeklyData=new  WeeklySalesVO();
	public String carApiProviderCount(){
		carWeeklyData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,CarAnalysisDataVO> carMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			carData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> carListFromDB=carAnalysisDao.getCarApiProviderCount(sessionCompany);
			List<CarAnalysisDataVO> carList= new ArrayList<>();
			Map<String,CarAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(carListFromDB!=null && carListFromDB.size()>0){
				for(String car:carListFromDB){
					if(hashMap.containsKey(car.trim()))
					{
						CarAnalysisDataVO carAnalysisDataVO = hashMap.get(car.trim());
						carAnalysisDataVO.setTotalCount(carAnalysisDataVO.getTotalCount()+1);
						hashMap.put(car.trim(), carAnalysisDataVO);
					}
					else
					{
						CarAnalysisDataVO carAnalysisDataVO = new CarAnalysisDataVO();
						carAnalysisDataVO.setTotalCount(1);
						carAnalysisDataVO.setName(car);
						hashMap.put(car.trim(), carAnalysisDataVO);
					}
				}
				toatCount= carListFromDB.size();
				carMap=sortByValues(hashMap,carListFromDB.size());
			}

			if(carMap!=null && carMap.size()>0)
			{
				for(Entry<String, CarAnalysisDataVO> carEntry : carMap.entrySet())
				{
					carList.add(carEntry.getValue());
				}
			}
			if(carMap!=null && carMap.size()>0){
				carData.setTotalCount(toatCount);
				carData.setCarProviderList(carList);
			}
			return SUCCESS;
		}
	}
	
	public String weeklySalesTop5CarLocations(){
		carData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			carWeeklyData.setError(error);
			return SUCCESS;
		}
		else{
			List<CarOrderRow> carOrderRowList=carAnalysisDao.getCarWeeklySales(sessionCompany);	
			if(carOrderRowList!=null && carOrderRowList.size()>0){
				Map<String,List<WeeklySalesDataVO>> weekDayMap=generateWeekDayMap(carOrderRowList);
				 if(weekDayMap!=null) 
					 carWeeklyData.setWeekDayMap(weekDayMap);
				 
				 else{
					 ErrorMsg error=new ErrorMsg();
						error.setMessage("No Data.");
						carWeeklyData.setError(error);
				 }
			}
			else{
				 ErrorMsg error=new ErrorMsg();
					error.setMessage("No Data.");
					carWeeklyData.setError(error);
			}
		}
		return SUCCESS;
	}
	
	public 	Map<String,List<WeeklySalesDataVO>> generateWeekDayMap(List<CarOrderRow> carOrderRowList ) {
		List<WeeklySalesDataVO> monHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> tueHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> wedHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> thuHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> friHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> satHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> sunHotelsList=new LinkedList<>();
		Map<String,List<WeeklySalesDataVO>> weekDayMap=new LinkedHashMap<>();
		for(CarOrderRow carOrderRow:carOrderRowList){
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
			String day=simpleDateformat.format(carOrderRow.getCreatedAt());
			String location=carOrderRow.getLocation()!=null?carOrderRow.getLocation():"NA";
			if(location.equalsIgnoreCase("bengaluru") || location.equalsIgnoreCase("benguluru") ||location.equalsIgnoreCase("bengulure"))
				location="Bangalore";
			location=location.toUpperCase();
				 WeeklySalesDataVO hotelWeeklySalesDataVO=new  WeeklySalesDataVO();
				if(day.equals("Mon")){
					hotelWeeklySalesDataVO.setName(location);
					hotelWeeklySalesDataVO.setWeekDay(day);
					monHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Tue")) {
					hotelWeeklySalesDataVO.setName(location);
					hotelWeeklySalesDataVO.setWeekDay(day);
					tueHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Wed")){
					hotelWeeklySalesDataVO.setName(location);
					hotelWeeklySalesDataVO.setWeekDay(day);
					wedHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Thu")){
					hotelWeeklySalesDataVO.setName(location);
					hotelWeeklySalesDataVO.setWeekDay(day);
					thuHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Fri")) {
					hotelWeeklySalesDataVO.setName(location);
					hotelWeeklySalesDataVO.setWeekDay(day);
					friHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sat")){ 
					hotelWeeklySalesDataVO.setName(location);
					hotelWeeklySalesDataVO.setWeekDay(day);
					satHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sun")){ 
					hotelWeeklySalesDataVO.setName(location);
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
	
	
	
	
	
	public Map<String,CarAnalysisDataVO>  sortByValues(Map<String, CarAnalysisDataVO> hashMap,int totalCount) {
		Set<Entry<String, CarAnalysisDataVO>> set = hashMap.entrySet();
		List<Entry<String, CarAnalysisDataVO>> list = new ArrayList<Entry<String, CarAnalysisDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, CarAnalysisDataVO>>()
		{
			public int compare(Map.Entry<String, CarAnalysisDataVO> o1, Map.Entry<String, CarAnalysisDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		Map<String,CarAnalysisDataVO> sortedHashMap = new LinkedHashMap<>();
		int count=0;
		for(Map.Entry<String, CarAnalysisDataVO> entry:list){
			count++;
			CarAnalysisDataVO carAnalysisDataVO = entry.getValue();
			double percentage = ((double)carAnalysisDataVO.getTotalCount()/(double)totalCount)*100;
			carAnalysisDataVO.setPercentage(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_UP).doubleValue());
			if(count<=10)
				sortedHashMap.put(entry.getKey(), carAnalysisDataVO);
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
	public CarAnalysisVO getCarData() {
		return carData;
	}

	public void setCarData(CarAnalysisVO carData) {
		this.carData = carData;
	}
	public WeeklySalesVO getCarWeeklyData() {
		return carWeeklyData;
	}

	public void setCarWeeklyData(WeeklySalesVO carWeeklyData) {
		this.carWeeklyData = carWeeklyData;
	}

}