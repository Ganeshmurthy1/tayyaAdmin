package com.admin.dashboard.analysis.json.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.dashboard.analysis.json.dao.AllServicesGraphDao;
import com.admin.dashboard.analysis.json.daoImpl.AllServicesGraphDaoImpl;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.WeeklySalesDataVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesVO;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class AllServicesLineGraphAction extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 30 Aug 2017
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	WeeklySalesVO  allServicesWeeklyData=new  WeeklySalesVO();
	AllServicesGraphDao allServicesGraphDao=new AllServicesGraphDaoImpl();
	public String allServicesWeeklySales(){
		Company sessionCompany=(Company) sessionMap.get("Company");
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			allServicesWeeklyData.setError(error);
			return SUCCESS;
		}
		else{
			List<WeeklySalesDataVO> weeklySalesDataVOList=allServicesGraphDao.getAllservicesWeeklySales(sessionCompany);	
			if(weeklySalesDataVOList!=null && weeklySalesDataVOList.size()>0){
				Map<String,List<WeeklySalesDataVO>> weekDayMap=generateWeekDayMap(weeklySalesDataVOList);
				if(weekDayMap!=null) 
					 allServicesWeeklyData.setWeekDayMap(weekDayMap);
				 
				 else{
					 ErrorMsg error=new ErrorMsg();
						error.setMessage("No Data.");
						error.setCount(0);
						allServicesWeeklyData.setError(error);
				 }
			}
			else{
				 ErrorMsg error=new ErrorMsg();
					error.setMessage("No Data.");
					error.setCount(0);
					allServicesWeeklyData.setError(error);
			}
		}
		return SUCCESS;
	}
	public 	Map<String,List<WeeklySalesDataVO>> generateWeekDayMap(List<WeeklySalesDataVO> weeklySalesDataVOList ) {
		List<WeeklySalesDataVO> monHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> tueHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> wedHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> thuHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> friHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> satHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> sunHotelsList=new LinkedList<>();
		Map<String,List<WeeklySalesDataVO>> weekDayMap=new LinkedHashMap<>();
		for(WeeklySalesDataVO weeklySalesDataVO:weeklySalesDataVOList){
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
			String day=simpleDateformat.format(weeklySalesDataVO.getCreatedAt());
			BigDecimal bookingAmount= weeklySalesDataVO.getBookingAmount();
			String serviceName=weeklySalesDataVO.getName()!=null?weeklySalesDataVO.getName():"NA";
				 WeeklySalesDataVO hotelWeeklySalesDataVO=new  WeeklySalesDataVO();
				if(day.equals("Mon")){
						hotelWeeklySalesDataVO.setName(serviceName);
						hotelWeeklySalesDataVO.setWeekDay(day);
						hotelWeeklySalesDataVO.setBookingAmount(bookingAmount);
						monHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Tue")) {
					hotelWeeklySalesDataVO.setName(serviceName);
					hotelWeeklySalesDataVO.setWeekDay(day);
					hotelWeeklySalesDataVO.setBookingAmount(bookingAmount);
					tueHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Wed")){
					hotelWeeklySalesDataVO.setName(serviceName);
					hotelWeeklySalesDataVO.setWeekDay(day);
					hotelWeeklySalesDataVO.setBookingAmount(bookingAmount);
					wedHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Thu")){
					hotelWeeklySalesDataVO.setName(serviceName);
					hotelWeeklySalesDataVO.setWeekDay(day);
					hotelWeeklySalesDataVO.setBookingAmount(bookingAmount);
					thuHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Fri")) {
					hotelWeeklySalesDataVO.setName(serviceName);
					hotelWeeklySalesDataVO.setWeekDay(day);
					hotelWeeklySalesDataVO.setBookingAmount(bookingAmount);
					friHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sat")){ 
					hotelWeeklySalesDataVO.setName(serviceName);
					hotelWeeklySalesDataVO.setWeekDay(day);
					hotelWeeklySalesDataVO.setBookingAmount(bookingAmount);
					satHotelsList.add(hotelWeeklySalesDataVO);
				}
				if(day.equals("Sun")){ 
					hotelWeeklySalesDataVO.setName(serviceName);
					hotelWeeklySalesDataVO.setWeekDay(day);
					hotelWeeklySalesDataVO.setBookingAmount(bookingAmount);
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
			if(count<=8)
				hotelList.add(entry.getValue());
			else
				break;
		}
		return hotelList; 
	}
	
	public 	Map<String,WeeklySalesDataVO> countRepeatedHotelName(List<WeeklySalesDataVO> hotelsList) {
		Map<String,WeeklySalesDataVO> tueHotelsMap=new LinkedHashMap<>();
		BigDecimal  hoteltotalAmount=new BigDecimal(0);
		BigDecimal  airtotalAmount=new BigDecimal(0);
		BigDecimal  cartotalAmount=new BigDecimal(0);
		BigDecimal  bustotalAmount=new BigDecimal(0);
		BigDecimal  traintotalAmount=new BigDecimal(0);
		BigDecimal  visatotalAmount=new BigDecimal(0);
		BigDecimal  inurancetotalAmount=new BigDecimal(0);
		BigDecimal  misctotalAmount=new BigDecimal(0);
		if(hotelsList!=null && hotelsList.size()>0){
			for(WeeklySalesDataVO hotelWeeklySalesDataVO:hotelsList){
				
				String name=hotelWeeklySalesDataVO.getName()!=null && !hotelWeeklySalesDataVO.getName().equals("")?hotelWeeklySalesDataVO.getName():"NA";
				BigDecimal bookingAmount=hotelWeeklySalesDataVO.getBookingAmount()!=null?hotelWeeklySalesDataVO.getBookingAmount():new BigDecimal(0);
				if(name.equals("Hotel")){
					hoteltotalAmount=hoteltotalAmount.add(bookingAmount);
					repeatedCountMap(tueHotelsMap, name,hoteltotalAmount);
				}
				
				else if(name.equals("Air")){
					airtotalAmount=airtotalAmount.add(bookingAmount);
					repeatedCountMap(tueHotelsMap, name,airtotalAmount);
				}
				else if(name.equals("Car")){
					cartotalAmount=cartotalAmount.add(bookingAmount);
					repeatedCountMap(tueHotelsMap, name,cartotalAmount);
				}
				else if(name.equals("Bus")){
					bustotalAmount=bustotalAmount.add(bookingAmount);
					repeatedCountMap(tueHotelsMap, name,bustotalAmount);
				}
				else if(name.equals("Train")){
					traintotalAmount=traintotalAmount.add(bookingAmount);
					repeatedCountMap(tueHotelsMap, name,traintotalAmount);
				}
				else if(name.equals("Visa")){
					visatotalAmount=visatotalAmount.add(bookingAmount);
					repeatedCountMap(tueHotelsMap, name,visatotalAmount);
				}
				else if(name.equals("Insurance")){
					inurancetotalAmount=inurancetotalAmount.add(bookingAmount);
					repeatedCountMap(tueHotelsMap, name,inurancetotalAmount);
				}
				else if(name.equals("Miscellaneous")){
					misctotalAmount=misctotalAmount.add(misctotalAmount);
					repeatedCountMap(tueHotelsMap, name,misctotalAmount);
				}
				
				
			
				
			}
			
		}
		return tueHotelsMap;
	}
	 
	public void repeatedCountMap(Map<String,WeeklySalesDataVO> tueHotelsMap,String name,BigDecimal bookingAmount){
		if(tueHotelsMap.containsKey(name)) {
			WeeklySalesDataVO hotelWeeklySalesDataVONew=tueHotelsMap.get(name);
			hotelWeeklySalesDataVONew.setTotalCount(hotelWeeklySalesDataVONew.getTotalCount()+1);
			//totalAmount=totalAmount.add(hotelWeeklySalesDataVONew.getBookingAmount()!=null?hotelWeeklySalesDataVONew.getBookingAmount():bookingAmount);
			hotelWeeklySalesDataVONew.setPrice(bookingAmount.setScale(2, BigDecimal.ROUND_UP));
			tueHotelsMap.put(name, hotelWeeklySalesDataVONew);	
		}
		else{
			WeeklySalesDataVO hotelWeeklySalesDataVONew=new WeeklySalesDataVO();
			hotelWeeklySalesDataVONew.setName(name);
			hotelWeeklySalesDataVONew.setTotalCount(1);
			hotelWeeklySalesDataVONew.setPrice(bookingAmount);
			tueHotelsMap.put(name, hotelWeeklySalesDataVONew);
		}
	}
	 
 
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
		
	}
	public WeeklySalesVO getAllServicesWeeklyData() {
		return allServicesWeeklyData;
	}
	public void setAllServicesWeeklyData(WeeklySalesVO allServicesWeeklyData) {
		this.allServicesWeeklyData = allServicesWeeklyData;
	}
	
}
