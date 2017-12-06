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

import com.admin.dashboard.analysis.json.dao.HotelAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.HotelAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.HotelAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.HotelAnalysisVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesDataVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesVO;
import com.lintas.admin.hotel.model.HotelOrderHotelData;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class HotelAnalysisAction extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 10 Aug 2017
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	HotelAnalysisDao hotelAnalysisDao=new HotelAnalysisDaoImpl();
	HotelAnalysisVO hotelData=new HotelAnalysisVO();
	 WeeklySalesVO hotelWeeklyData=new  WeeklySalesVO();
	public String hotelNameBookingCount(){
		hotelWeeklyData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,HotelAnalysisDataVO> hotelMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			hotelData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> hotelListFromDB=hotelAnalysisDao.getHotelNameBookingCount(sessionCompany);
			List<HotelAnalysisDataVO> hotelList= new ArrayList<>();
			Map<String,HotelAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(hotelListFromDB!=null && hotelListFromDB.size()>0){
				for(String hotel:hotelListFromDB){
					if(hashMap.containsKey(hotel.trim()))
					{
						HotelAnalysisDataVO hotelAnalysisDataVO = hashMap.get(hotel.trim());
						hotelAnalysisDataVO.setTotalCount(hotelAnalysisDataVO.getTotalCount()+1);
						hashMap.put(hotel.trim(), hotelAnalysisDataVO);
					}
					else
					{
						HotelAnalysisDataVO hotelAnalysisDataVO = new HotelAnalysisDataVO();
						hotelAnalysisDataVO.setTotalCount(1);
						hotelAnalysisDataVO.setName(hotel);
						hashMap.put(hotel.trim(), hotelAnalysisDataVO);
					}
				}
				toatCount= hotelListFromDB.size();
				hotelMap=sortByValues(hashMap,hotelListFromDB.size());
			}

			if(hotelMap!=null && hotelMap.size()>0)
			{
				for(Entry<String, HotelAnalysisDataVO> hotelEntry : hotelMap.entrySet())
				{
					hotelList.add(hotelEntry.getValue());
				}
			}
			if(hotelMap!=null && hotelMap.size()>0){
				hotelData.setTotalCount(toatCount);
				hotelData.setHotelList(hotelList);
			}
			return SUCCESS;
		}

	}

	public String hotelApiProviderBookingCount(){
		hotelWeeklyData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,HotelAnalysisDataVO> hotelMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			hotelData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> hotelListFromDB=hotelAnalysisDao.getHotelApiProviderBookingCount(sessionCompany);
			List<HotelAnalysisDataVO> apiProviderList = new ArrayList<>();
			Map<String,HotelAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(hotelListFromDB!=null && hotelListFromDB.size()>0){
				for(String hotel:hotelListFromDB){
					if(hashMap.containsKey(hotel.trim()))
					{
						HotelAnalysisDataVO hotelAnalysisDataVO = hashMap.get(hotel.trim());
						hotelAnalysisDataVO.setTotalCount(hotelAnalysisDataVO.getTotalCount()+1);
						hashMap.put(hotel.trim(), hotelAnalysisDataVO);
					}
					else
					{
						HotelAnalysisDataVO hotelAnalysisDataVO = new HotelAnalysisDataVO();
						hotelAnalysisDataVO.setTotalCount(1);
						hotelAnalysisDataVO.setName(hotel);
						hashMap.put(hotel.trim(), hotelAnalysisDataVO);
					}
				}
				toatCount= hotelListFromDB.size();
				hotelMap=sortByValues(hashMap,hotelListFromDB.size());
			}

			if(hotelMap!=null && hotelMap.size()>0)
			{
				for(Entry<String, HotelAnalysisDataVO> hotelEntry : hotelMap.entrySet())
				{
					apiProviderList.add(hotelEntry.getValue());
				}
			}
			if(hotelMap!=null && hotelMap.size()>0){
				hotelData.setTotalCount(toatCount);
				hotelData.setApiProviderList(apiProviderList);
			}
			return SUCCESS;
		}

	}

	public String weeklySalesTop5Hotels(){
		hotelData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			hotelWeeklyData.setError(error);
			return SUCCESS;
		}
		else{
			List<HotelOrderRow> hotelOrderRowList=hotelAnalysisDao.getHotelWeeklySales(sessionCompany);	
			if(hotelOrderRowList!=null && hotelOrderRowList.size()>0){
				Map<String,List<WeeklySalesDataVO>> weekDayMap=generateWeekDayMap(hotelOrderRowList);
				 if(weekDayMap==null){
					 ErrorMsg error=new ErrorMsg();
						error.setMessage("No Data.");
						hotelWeeklyData.setError(error);
				 }
				 else{
					 hotelWeeklyData.setWeekDayMap(weekDayMap);
				 }
			}
			else{
				 ErrorMsg error=new ErrorMsg();
					error.setMessage("No Data.");
					hotelWeeklyData.setError(error);
			}
		}
		return SUCCESS;
	}
	public 	Map<String,List<WeeklySalesDataVO>> generateWeekDayMap(List<HotelOrderRow> hotelOrderRowList ) {
		List<WeeklySalesDataVO> monHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> tueHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> wedHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> thuHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> friHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> satHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> sunHotelsList=new LinkedList<>();
		Map<String,List<WeeklySalesDataVO>> weekDayMap=new LinkedHashMap<>();
		for(HotelOrderRow hotelOrderRow:hotelOrderRowList){
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
			String day=simpleDateformat.format(hotelOrderRow.getCreatedAt());
			HotelOrderHotelData hotelData=hotelOrderRow.getHotelOrderHotelData();
			if(hotelData!=null && hotelData.getName()!=null){
				 WeeklySalesDataVO hotelWeeklySalesDataVO=new  WeeklySalesDataVO();
				if(day.equals("Mon")){
					hotelWeeklySalesDataVO.setName(hotelData.getName());
					hotelWeeklySalesDataVO.setWeekDay(day);
					monHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Tue")) {
					hotelWeeklySalesDataVO.setName(hotelData.getName());
					hotelWeeklySalesDataVO.setWeekDay(day);
					tueHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Wed")){
					hotelWeeklySalesDataVO.setName(hotelData.getName());
					hotelWeeklySalesDataVO.setWeekDay(day);
					wedHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Thu")){
					hotelWeeklySalesDataVO.setName(hotelData.getName());
					hotelWeeklySalesDataVO.setWeekDay(day);
					thuHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Fri")) {
					hotelWeeklySalesDataVO.setName(hotelData.getName());
					hotelWeeklySalesDataVO.setWeekDay(day);
					friHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sat")){ 
					hotelWeeklySalesDataVO.setName(hotelData.getName());
					hotelWeeklySalesDataVO.setWeekDay(day);
					satHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sun")){ 
					hotelWeeklySalesDataVO.setName(hotelData.getName());
					hotelWeeklySalesDataVO.setWeekDay(day);
					sunHotelsList.add(hotelWeeklySalesDataVO);
				}
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
	
	
	
	
	
	public Map<String,HotelAnalysisDataVO>  sortByValues(Map<String, HotelAnalysisDataVO> hashMap,int totalCount) {
		Set<Entry<String, HotelAnalysisDataVO>> set = hashMap.entrySet();
		List<Entry<String, HotelAnalysisDataVO>> list = new ArrayList<Entry<String, HotelAnalysisDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, HotelAnalysisDataVO>>()
		{
			public int compare(Map.Entry<String, HotelAnalysisDataVO> o1, Map.Entry<String, HotelAnalysisDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		Map<String,HotelAnalysisDataVO> sortedHashMap = new LinkedHashMap<>();
		int count=0;
		for(Map.Entry<String, HotelAnalysisDataVO> entry:list){
			count++;
			HotelAnalysisDataVO hotelAnalysisDataVO = entry.getValue();
			double percentage = ((double)hotelAnalysisDataVO.getTotalCount()/(double)totalCount)*100;
			hotelAnalysisDataVO.setPercentage(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_UP).doubleValue());
			if(count<=10)
				sortedHashMap.put(entry.getKey(), hotelAnalysisDataVO);
			else
				break;
		}
		return sortedHashMap; 
	}
	
	public HotelAnalysisVO getHotelData() {
		return hotelData;
	}
	public void setHotelData(HotelAnalysisVO hotelData) {
		this.hotelData = hotelData;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	public  WeeklySalesVO getHotelWeeklyData() {
		return hotelWeeklyData;
	}

	public void setHotelWeeklyData(WeeklySalesVO hotelWeeklyData) {
		this.hotelWeeklyData = hotelWeeklyData;
	}
}
