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
import java.util.Set;
import java.util.Map.Entry;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.dashboard.analysis.json.dao.TrainAnalysisDao;
import com.admin.dashboard.analysis.json.daoImpl.TrainAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.TrainAnalysisDataVO;
import com.admin.dashboard.analysis.json.vo.TrainAnalysisVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesDataVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesVO;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainOrderRow;
import com.opensymphony.xwork2.ActionSupport;

public class TrainAnalysisAction  extends ActionSupport implements SessionAware {
	/**
	 * @author raham
	 * 10 Aug 2017
	 */
	TrainAnalysisDao trainAnalysisDao=new  TrainAnalysisDaoImpl();
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	TrainAnalysisVO trainData=new TrainAnalysisVO();
	WeeklySalesVO trainWeeklyData=new  WeeklySalesVO();
	public String  trainApiProviderCount(){
		trainWeeklyData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		Map<String,TrainAnalysisDataVO> trainMap=null;
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			trainData.setError(error);
			return SUCCESS;
		}
		else{
			List<String> trainListFromDB=trainAnalysisDao.getTrainApiProviderCount(sessionCompany);
			List<TrainAnalysisDataVO> trainList= new ArrayList<>();
			Map<String,TrainAnalysisDataVO> hashMap= new HashMap<>();
			int toatCount=0;
			if(trainListFromDB!=null && trainListFromDB.size()>0){
				for(String train:trainListFromDB){
					if(hashMap.containsKey(train.trim()))
					{
						TrainAnalysisDataVO trainAnalysisDataVO = hashMap.get(train.trim());
						trainAnalysisDataVO.setTotalCount(trainAnalysisDataVO.getTotalCount()+1);
						hashMap.put(train.trim(), trainAnalysisDataVO);
					}
					else
					{
						TrainAnalysisDataVO trainAnalysisDataVO = new TrainAnalysisDataVO();
						trainAnalysisDataVO.setTotalCount(1);
						trainAnalysisDataVO.setName(train);
						hashMap.put(train.trim(), trainAnalysisDataVO);
					}
				}
				toatCount= trainListFromDB.size();
				trainMap=sortByValues(hashMap,trainListFromDB.size());
			}

			if(trainMap!=null && trainMap.size()>0)
			{
				for(Entry<String, TrainAnalysisDataVO> trainEntry : trainMap.entrySet())
				{
					trainList.add(trainEntry.getValue());
				}
			}
			if(trainMap!=null && trainMap.size()>0){
				trainData.setTotalCount(toatCount);
				trainData.setTrainList(trainList);
			}
			return SUCCESS;
		}
	}
	public String weeklySalesTop5TrainLocations(){
		trainData=null;
		Company sessionCompany=(Company) sessionMap.get("Company");
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			trainWeeklyData.setError(error);
			return SUCCESS;
		}
		else{
			List<TrainOrderRow> trainOrderRowList=trainAnalysisDao.getTrainWeeklySales(sessionCompany);	
			if(trainOrderRowList!=null && trainOrderRowList.size()>0){
				Map<String,List<WeeklySalesDataVO>> weekDayMap=generateWeekDayMap(trainOrderRowList);
				 if(weekDayMap!=null) 
					 trainWeeklyData.setWeekDayMap(weekDayMap);
				 
				 else{
					 ErrorMsg error=new ErrorMsg();
						error.setMessage("No Data.");
						trainWeeklyData.setError(error);
				 }
			}
			else{
				 ErrorMsg error=new ErrorMsg();
					error.setMessage("No Data.");
					trainWeeklyData.setError(error);
			}
		}
		return SUCCESS;
	}
	 
	public 	Map<String,List<WeeklySalesDataVO>> generateWeekDayMap(List<TrainOrderRow> trainOrderRowList ) {
		List<WeeklySalesDataVO> monHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> tueHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> wedHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> thuHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> friHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> satHotelsList=new LinkedList<>();
		List<WeeklySalesDataVO> sunHotelsList=new LinkedList<>();
		Map<String,List<WeeklySalesDataVO>> weekDayMap=new LinkedHashMap<>();
		for(TrainOrderRow trainOrderRow:trainOrderRowList){
			SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
			String day=simpleDateformat.format(trainOrderRow.getCreatedAt());
			TrainTravelRequestQuotation trainTravelRequestQuotation=new TrainTravelRequestDao().getTrainTravelRequestQuotationDetailsByTrainOrderRowId(trainOrderRow.getId());
			String location=trainTravelRequestQuotation!=null && trainTravelRequestQuotation.getTolocation()!=null?trainTravelRequestQuotation.getTolocation():"NA";
			if(location.equalsIgnoreCase("bengaluru") || location.equalsIgnoreCase("benguluru") || location.equalsIgnoreCase("bengulure") )
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
	
	
	
	
	public Map<String,TrainAnalysisDataVO>  sortByValues(Map<String, TrainAnalysisDataVO> hashMap,int totalCount) {
		Set<Entry<String, TrainAnalysisDataVO>> set = hashMap.entrySet();
		List<Entry<String, TrainAnalysisDataVO>> list = new ArrayList<Entry<String, TrainAnalysisDataVO>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, TrainAnalysisDataVO>>()
		{
			public int compare(Map.Entry<String, TrainAnalysisDataVO> o1, Map.Entry<String, TrainAnalysisDataVO> o2 )
			{
				return (o2.getValue().getTotalCount()).compareTo( o1.getValue().getTotalCount() );
			}
		} );
		Map<String,TrainAnalysisDataVO> sortedHashMap = new LinkedHashMap<>();
		int count=0;
		for(Map.Entry<String, TrainAnalysisDataVO> entry:list){
			count++;
			TrainAnalysisDataVO trainAnalysisDataVO = entry.getValue();
			double percentage = ((double)trainAnalysisDataVO.getTotalCount()/(double)totalCount)*100;
			trainAnalysisDataVO.setPercentage(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_UP).doubleValue());
			if(count<=10)
				sortedHashMap.put(entry.getKey(), trainAnalysisDataVO);
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
	public TrainAnalysisVO getTrainData() {
		return trainData;
	}

	public void setTrainData(TrainAnalysisVO trainData) {
		this.trainData = trainData;
	}

	public WeeklySalesVO getTrainWeeklyData() {
		return trainWeeklyData;
	}
	public void setTrainWeeklyData(WeeklySalesVO trainWeeklyData) {
		this.trainWeeklyData = trainWeeklyData;
	}
}
