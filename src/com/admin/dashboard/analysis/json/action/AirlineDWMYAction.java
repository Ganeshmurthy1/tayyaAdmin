package com.admin.dashboard.analysis.json.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.dashboard.analysis.json.daoImpl.AirlineAnalysisDaoImpl;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.WeeklySalesDataVO;
import com.admin.dashboard.analysis.json.vo.WeeklySalesVO;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.model.Company;
import com.lintas.utility.ArithmeticUti;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
public class AirlineDWMYAction  extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1156197427882542748L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AirlineDWMYAction.class);
	SessionMap<String, Object> sessionMap;
	AirlineAnalysisDaoImpl airlineAnalysisDaoImpl=new AirlineAnalysisDaoImpl();
	private String presentDate=null;
	private String type=null;
	private WeeklySalesVO flightComaprisonData=new  WeeklySalesVO();
	public String DWMYAirBookingComparison(){
		List<String> yearList=new LinkedList<>();
		Company sessionCompany=(Company) sessionMap.get("Company");
		if(sessionCompany==null){
			ErrorMsg error=new ErrorMsg();
			error.setMessage("Session is expired.");
			flightComaprisonData.setError(error);
			presentDate=null;
			type=null;
			return SUCCESS;
		}
		else{
			List<FlightOrderRow> flightOrderRowList=null;
			Map<String,	List<FlightOrderRow>> flightOrderRowMap= new TreeMap<>();
			if(type.equals("D")) 
				flightOrderRowList=airlineAnalysisDaoImpl.getDWMYBookings(sessionCompany, presentDate,type);
			if(type.equals("M")) 
				flightOrderRowList=airlineAnalysisDaoImpl.getDWMYBookings(sessionCompany, presentDate,type);
			if(type.equals("W")){
				flightOrderRowList=airlineAnalysisDaoImpl.getDWMYBookings(sessionCompany, presentDate,type);
				flightOrderRowMap.put("WEEK1", flightOrderRowList);
				for(int i=2;i<5;i++){
					presentDate=DateConversion.previousWeekStartDate(presentDate,type);
					flightOrderRowList=airlineAnalysisDaoImpl.getDWMYBookings(sessionCompany, presentDate,type);
					flightOrderRowMap.put("WEEK"+String.valueOf(i), flightOrderRowList);
				}
				flightOrderRowList=mergeWeekorYearFlightOrderRowList(flightOrderRowMap);
			}
			if(type.equals("Y")){
				flightOrderRowList=airlineAnalysisDaoImpl.getDWMYBookings(sessionCompany, presentDate,type);
				String[] splitDate=presentDate!=null && presentDate.contains("-")?presentDate.split("-"):null;
				String year=null;
				if(splitDate!=null)
					year=splitDate[2];
				yearList.add(year);
				flightOrderRowMap.put(year, flightOrderRowList);
				for(int i=1;i<4;i++){
					int yearNew=Integer.parseInt(year)-i;
					presentDate=DateConversion.previousWeekStartDate(presentDate,type);
					flightOrderRowList=airlineAnalysisDaoImpl.getDWMYBookings(sessionCompany, presentDate,type);
					flightOrderRowMap.put(String.valueOf(yearNew), flightOrderRowList);
					yearList.add(String.valueOf(yearNew));
				}
				flightOrderRowList=mergeWeekorYearFlightOrderRowList(flightOrderRowMap);
			}
			if(flightOrderRowList!=null && flightOrderRowList.size()>0){
				if(flightOrderRowList!=null && flightOrderRowList.size()>0){
					Map<String,WeeklySalesDataVO> comaprisonMap=null;
					if(type.equals("D"))
						comaprisonMap=generateDayMap(flightOrderRowList);
					if(type.equals("W"))
						comaprisonMap=generateWeekMap(flightOrderRowList);
					if(type.equals("M"))
						comaprisonMap=generateMonthMap(flightOrderRowList);
					 if(type.equals("Y"))
						comaprisonMap=generateYearMap(flightOrderRowList,yearList);
					if(comaprisonMap!=null && comaprisonMap.size()>0 ) 
						flightComaprisonData.setComparisonMap(comaprisonMap);
					else{
						ErrorMsg error=new ErrorMsg();
						error.setMessage("No Data.");
						flightComaprisonData.setError(error);
					}
				}
			}
			else{
				ErrorMsg error=new ErrorMsg();
				error.setMessage("No Data.");
				flightComaprisonData.setError(error);
			}
			presentDate=null;
			type=null; 
			return SUCCESS;
		}

	}
	
	public 	Map<String, WeeklySalesDataVO> generateDayMap(List<FlightOrderRow> flightOrderRowList ) {
		List<WeeklySalesDataVO> monFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> tueFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> wedFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> thuFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> friFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> satFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> sunFlightList=new LinkedList<>();
		WeeklySalesDataVO monDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO tueDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO wedDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO thuDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO friDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO satDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO sunDataVO=new  WeeklySalesDataVO();
		Map<String, WeeklySalesDataVO> yearDayMap=new LinkedHashMap<>();
		BigDecimal monTotalBookingAmount=new BigDecimal(0);
		BigDecimal tueTotalBookingAmount=new BigDecimal(0);
		BigDecimal wedTotalBookingAmount=new BigDecimal(0);
		BigDecimal thuTotalBookingAmount=new BigDecimal(0);
		BigDecimal friTotalBookingAmount=new BigDecimal(0);
		BigDecimal satTotalBookingAmount=new BigDecimal(0);
		BigDecimal sunTotalBookingAmount=new BigDecimal(0);

		for(FlightOrderRow flightOrderRow:flightOrderRowList){
			if(flightOrderRow!=null && flightOrderRow.getFlightOrderTripDetails()!=null && flightOrderRow.getFlightOrderTripDetails().size()>0){
				SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the year abbreviated
				String day=simpleDateformat.format(flightOrderRow.getCreatedAt());

				for(FlightOrderTripDetail trip:flightOrderRow.getFlightOrderTripDetails()){
					WeeklySalesDataVO flightDaySalesDataVO=new  WeeklySalesDataVO();
					String origin=trip.getOriginCode()!=null?trip.getOriginCode():"NA";
					String destination=trip.getDestinationCode()!=null?trip.getDestinationCode():"NA";
					/*1. Total Sales :20000INR
					2. Percentage Change in Sales :-2%
					3 Segment Count : 100
					4. Percent change in segment : 5%*/

					if(day.equals("Mon")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(day);
						monFlightList.add(flightDaySalesDataVO);
					}
					if(day.equals("Tue")) {
						flightDaySalesDataVO.setName(origin+"-"+destination);
						flightDaySalesDataVO.setWeekDay(day);
						tueFlightList.add(flightDaySalesDataVO);
					}
					if(day.equals("Wed")){
						flightDaySalesDataVO.setName(origin+"-"+destination);
						flightDaySalesDataVO.setWeekDay(day);
						wedFlightList.add(flightDaySalesDataVO);
					}
					if(day.equals("Thu")){
						flightDaySalesDataVO.setName(origin+"-"+destination);
						flightDaySalesDataVO.setWeekDay(day);
						thuFlightList.add(flightDaySalesDataVO);
					}
					if(day.equals("Fri")) {
						flightDaySalesDataVO.setName(origin+"-"+destination);
						flightDaySalesDataVO.setWeekDay(day);
						friFlightList.add(flightDaySalesDataVO);
					}
					if(day.equals("Sat")){ 
						flightDaySalesDataVO.setName(origin+"-"+destination);
						flightDaySalesDataVO.setWeekDay(day);
						satFlightList.add(flightDaySalesDataVO);
					}
					if(day.equals("Sun")){ 
						flightDaySalesDataVO.setName(origin+"-"+destination);
						flightDaySalesDataVO.setWeekDay(day);
						sunFlightList.add(flightDaySalesDataVO);
					}
				}

				if(day.equals("Mon")) 
					monTotalBookingAmount= monTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(day.equals("Tue")) 
					tueTotalBookingAmount= monTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(day.equals("Wed")) 
					wedTotalBookingAmount= monTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(day.equals("Thu")) 
					thuTotalBookingAmount= monTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(day.equals("Fri")) 
					friTotalBookingAmount= monTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(day.equals("Sat")) 
					satTotalBookingAmount= monTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(day.equals("Sun")) 
					sunTotalBookingAmount= monTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
			}

		}


		monDataVO.setTotalCount(monFlightList.size());
		monDataVO.setBookingAmount(monTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		tueDataVO.setTotalCount(tueFlightList.size());
		tueDataVO.setBookingAmount(tueTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		wedDataVO.setTotalCount(wedFlightList.size());
		wedDataVO.setBookingAmount(wedTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		thuDataVO.setTotalCount(thuFlightList.size());
		thuDataVO.setBookingAmount(thuTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		friDataVO.setTotalCount(friFlightList.size());
		friDataVO.setBookingAmount(friTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		satDataVO.setTotalCount(satFlightList.size());
		satDataVO.setBookingAmount(satTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		sunDataVO.setTotalCount(sunFlightList.size());
		sunDataVO.setBookingAmount(sunTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));


		//int tueToMonDifference=tueDataVO.getTotalCount()-monDataVO.getTotalCount();
		tueDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(tueDataVO, monDataVO));
		tueDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(tueDataVO, monDataVO));

		//int wedToTueDifference=wedDataVO.getTotalCount()-tueDataVO.getTotalCount();
		wedDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(wedDataVO, tueDataVO));
		wedDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(wedDataVO, tueDataVO));

		//int thuToWedDifference=thuDataVO.getTotalCount()-wedDataVO.getTotalCount();
		thuDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(thuDataVO, wedDataVO));
		thuDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(thuDataVO, wedDataVO));

		//int friToThuDifference=friDataVO.getTotalCount()-thuDataVO.getTotalCount();
		friDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(friDataVO, thuDataVO));
		friDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(friDataVO, thuDataVO));

		//int satToFriDifference=satDataVO.getTotalCount()-friDataVO.getTotalCount();
		satDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(satDataVO, friDataVO));
		satDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(satDataVO, friDataVO));

		//int sunToSatDifference=sunDataVO.getTotalCount()-satDataVO.getTotalCount();
		sunDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(sunDataVO, satDataVO));
		sunDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(sunDataVO, satDataVO));

		//int monToSunDifference=monDataVO.getTotalCount()-sunDataVO.getTotalCount();
		monDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(monDataVO, sunDataVO));
		monDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(monDataVO, sunDataVO));

		yearDayMap.put("MON", monDataVO);
		yearDayMap.put("TUE", tueDataVO);
		yearDayMap.put("WED", wedDataVO);
		yearDayMap.put("THU", thuDataVO);
		yearDayMap.put("FRI", friDataVO);
		yearDayMap.put("SAT", satDataVO);
		yearDayMap.put("SUN", sunDataVO);
		return yearDayMap; 
	}
	
	private Map<String, WeeklySalesDataVO> generateMonthMap(List<FlightOrderRow> flightOrderRowList) {
		// TODO Auto-generated method stub
		List<WeeklySalesDataVO> janFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> febFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> marFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> aprFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> mayFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> junFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> julFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> augFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> sepFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> octFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> novFlightList=new LinkedList<>();
		List<WeeklySalesDataVO> decFlightList=new LinkedList<>();
		
		WeeklySalesDataVO janDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO febDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO marDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO aprDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO mayDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO junDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO julDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO  augDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO sepDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO octDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO novDataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO decDataVO=new  WeeklySalesDataVO();
		
		Map<String, WeeklySalesDataVO> monthMap=new LinkedHashMap<>();
		BigDecimal janTotalBookingAmount=new BigDecimal(0);
		BigDecimal febTotalBookingAmount=new BigDecimal(0);
		BigDecimal marTotalBookingAmount=new BigDecimal(0);
		BigDecimal aprTotalBookingAmount=new BigDecimal(0);
		BigDecimal mayTotalBookingAmount=new BigDecimal(0);
		BigDecimal junTotalBookingAmount=new BigDecimal(0);
		BigDecimal julTotalBookingAmount=new BigDecimal(0);
		BigDecimal augTotalBookingAmount=new BigDecimal(0);
		BigDecimal sepTotalBookingAmount=new BigDecimal(0);
		BigDecimal octTotalBookingAmount=new BigDecimal(0);
		BigDecimal novTotalBookingAmount=new BigDecimal(0);
		BigDecimal decTotalBookingAmount=new BigDecimal(0);
		  
		for(FlightOrderRow flightOrderRow:flightOrderRowList){
			if(flightOrderRow!=null && flightOrderRow.getFlightOrderTripDetails()!=null && flightOrderRow.getFlightOrderTripDetails().size()>0){
				SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMM"); // the day of the year abbreviated
				String month=simpleDateformat.format(flightOrderRow.getCreatedAt());
				for(FlightOrderTripDetail trip:flightOrderRow.getFlightOrderTripDetails()){
					WeeklySalesDataVO flightDaySalesDataVO=new  WeeklySalesDataVO();
					String origin=trip.getOriginCode()!=null?trip.getOriginCode():"NA";
					String destination=trip.getDestinationCode()!=null?trip.getDestinationCode():"NA";

					if(month.equals("Jan")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						janFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Feb")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						febFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Mar")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						marFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Apr")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						aprFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("May")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						mayFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Jun")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						junFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Jul")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						julFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Aug")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						augFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Sep")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						sepFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Oct")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						octFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Nov")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						novFlightList.add(flightDaySalesDataVO);
					}
					if(month.equals("Dec")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(month);
						decFlightList.add(flightDaySalesDataVO);
					}
					
				}
				if(month.equals("Jan")) 
					janTotalBookingAmount= janTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Feb")) 
					febTotalBookingAmount= febTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Mar")) 
					marTotalBookingAmount= marTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Apr")) 
					aprTotalBookingAmount= aprTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("May")) 
					mayTotalBookingAmount= mayTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Jun")) 
					junTotalBookingAmount= junTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Jul")) 
					julTotalBookingAmount= julTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Aug")) 
					augTotalBookingAmount= augTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Sep")) 
					sepTotalBookingAmount= sepTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Oct")) 
					octTotalBookingAmount= octTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Nov")) 
					novTotalBookingAmount= novTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(month.equals("Dec")) 
					decTotalBookingAmount= decTotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				 
			}
		}
		janDataVO.setTotalCount(janFlightList.size());
		janDataVO.setBookingAmount(janTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		febDataVO.setTotalCount(febFlightList.size());
		febDataVO.setBookingAmount(febTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		marDataVO.setTotalCount(marFlightList.size());
		marDataVO.setBookingAmount(marTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		aprDataVO.setTotalCount(aprFlightList.size());
		aprDataVO.setBookingAmount(aprTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		mayDataVO.setTotalCount(mayFlightList.size());
		mayDataVO.setBookingAmount(mayTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		junDataVO.setTotalCount(junFlightList.size());
		junDataVO.setBookingAmount(junTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		julDataVO.setTotalCount(julFlightList.size());
		julDataVO.setBookingAmount(julTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		augDataVO.setTotalCount(augFlightList.size());
		augDataVO.setBookingAmount(augTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		sepDataVO.setTotalCount(sepFlightList.size());
		sepDataVO.setBookingAmount(sepTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		octDataVO.setTotalCount(octFlightList.size());
		octDataVO.setBookingAmount(octTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		novDataVO.setTotalCount(novFlightList.size());
		novDataVO.setBookingAmount(novTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		decDataVO.setTotalCount(decFlightList.size());
		decDataVO.setBookingAmount(decTotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		
		febDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(febDataVO, janDataVO));
		febDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(febDataVO, janDataVO));
		marDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(marDataVO, febDataVO));
		marDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(marDataVO, febDataVO));
		aprDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(aprDataVO, marDataVO));
		aprDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(aprDataVO, marDataVO));
		mayDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(mayDataVO, aprDataVO));
		mayDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(mayDataVO, aprDataVO));
		junDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(junDataVO, mayDataVO));
		junDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(junDataVO, mayDataVO));
		julDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(julDataVO, junDataVO));
		julDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(julDataVO, junDataVO));
		augDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(augDataVO, julDataVO));
		augDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(augDataVO, julDataVO));
		sepDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(sepDataVO, augDataVO));
		sepDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(sepDataVO, augDataVO));
		octDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(octDataVO, sepDataVO));
		octDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(octDataVO, sepDataVO));
		novDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(novDataVO, octDataVO));
		novDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(novDataVO, octDataVO));
		decDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(decDataVO, novDataVO));
		decDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(decDataVO, novDataVO));
		janDataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(decDataVO, janDataVO));
		janDataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(decDataVO, janDataVO));

		monthMap.put("Jan", janDataVO);
		monthMap.put("Feb", febDataVO);
		monthMap.put("Mar", marDataVO);
		monthMap.put("Apr", aprDataVO);
		monthMap.put("May", mayDataVO);
		monthMap.put("Jun", junDataVO);
		monthMap.put("Jul", julDataVO);
		monthMap.put("Aug", augDataVO);
		monthMap.put("Sep", sepDataVO);
		monthMap.put("Oct", octDataVO);
		monthMap.put("Nov", novDataVO);
		monthMap.put("Dec", decDataVO);
		return monthMap;
	}

	private Map<String, WeeklySalesDataVO> generateWeekMap(List<FlightOrderRow> flightOrderRowList) {
		// TODO Auto-generated method stub
		List<WeeklySalesDataVO> year1FlightList=new LinkedList<>();
		List<WeeklySalesDataVO> year2FlightList=new LinkedList<>();
		List<WeeklySalesDataVO> year3FlightList=new LinkedList<>();
		List<WeeklySalesDataVO> year4FlightList=new LinkedList<>();
		WeeklySalesDataVO year1DataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO year2DataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO year3DataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO year4DataVO=new  WeeklySalesDataVO();
		Map<String, WeeklySalesDataVO> yearDayMap=new LinkedHashMap<>();
		BigDecimal year1TotalBookingAmount=new BigDecimal(0);
		BigDecimal year2TotalBookingAmount=new BigDecimal(0);
		BigDecimal year3TotalBookingAmount=new BigDecimal(0);
		BigDecimal year4TotalBookingAmount=new BigDecimal(0);
		for(FlightOrderRow flightOrderRow:flightOrderRowList){
			if(flightOrderRow!=null && flightOrderRow.getFlightOrderTripDetails()!=null && flightOrderRow.getFlightOrderTripDetails().size()>0){
				for(FlightOrderTripDetail trip:flightOrderRow.getFlightOrderTripDetails()){
					WeeklySalesDataVO flightDaySalesDataVO=new  WeeklySalesDataVO();
					String origin=trip.getOriginCode()!=null?trip.getOriginCode():"NA";
					String destination=trip.getDestinationCode()!=null?trip.getDestinationCode():"NA";
					if(flightOrderRow.getWeekType().equals("WEEK1")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(flightOrderRow.getWeekType());
						year1FlightList.add(flightDaySalesDataVO);
					}
					if(flightOrderRow.getWeekType().equals("WEEK2")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(flightOrderRow.getWeekType());
						year2FlightList.add(flightDaySalesDataVO);
					}
					if(flightOrderRow.getWeekType().equals("WEEK3")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(flightOrderRow.getWeekType());
						year3FlightList.add(flightDaySalesDataVO);
					}
					if(flightOrderRow.getWeekType().equals("WEEK4")){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(flightOrderRow.getWeekType());
						year4FlightList.add(flightDaySalesDataVO);
					}

				}
				if(flightOrderRow.getWeekType().equals("WEEK1")) 
					year1TotalBookingAmount= year1TotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(flightOrderRow.getWeekType().equals("WEEK2")) 
					year2TotalBookingAmount= year2TotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(flightOrderRow.getWeekType().equals("WEEK3")) 
					year3TotalBookingAmount= year3TotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(flightOrderRow.getWeekType().equals("WEEK4")) 
					year4TotalBookingAmount= year4TotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
			}
		}

		year1DataVO.setTotalCount(year1FlightList.size());
		year1DataVO.setBookingAmount(year1TotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		year2DataVO.setTotalCount(year2FlightList.size());
		year2DataVO.setBookingAmount(year2TotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		year3DataVO.setTotalCount(year3FlightList.size());
		year3DataVO.setBookingAmount(year3TotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		 year4DataVO.setTotalCount(year4FlightList.size());
		year4DataVO.setBookingAmount(year4TotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		 
		year2DataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(year2DataVO, year1DataVO));
		year2DataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(year2DataVO, year1DataVO));
		year3DataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(year3DataVO, year2DataVO));
		year3DataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(year3DataVO, year2DataVO));
		 year4DataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(year4DataVO, year3DataVO));
		year4DataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(year4DataVO, year3DataVO)); 
		year1DataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(year1DataVO, year4DataVO));
		year1DataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(year1DataVO, year4DataVO));

		yearDayMap.put("WEEK1", year1DataVO);
		yearDayMap.put("WEEK2", year2DataVO);
		yearDayMap.put("WEEK3", year3DataVO);
		 yearDayMap.put("WEEK4", year4DataVO);

		return yearDayMap; 
	}
	private LinkedHashMap<String, WeeklySalesDataVO> generateYearMap(List<FlightOrderRow> flightOrderRowList,List<String> yearLIst) {
		List<WeeklySalesDataVO> year1FlightList=new LinkedList<>();
		List<WeeklySalesDataVO> year2FlightList=new LinkedList<>();
		List<WeeklySalesDataVO> year3FlightList=new LinkedList<>();
		List<WeeklySalesDataVO> year4FlightList=new LinkedList<>();
		WeeklySalesDataVO year1DataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO year2DataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO year3DataVO=new  WeeklySalesDataVO();
		WeeklySalesDataVO year4DataVO=new  WeeklySalesDataVO();
		LinkedHashMap<String, WeeklySalesDataVO> yearDayMap=new LinkedHashMap<>();
		BigDecimal year1TotalBookingAmount=new BigDecimal(0);
		BigDecimal year2TotalBookingAmount=new BigDecimal(0);
		BigDecimal year3TotalBookingAmount=new BigDecimal(0);
		BigDecimal year4TotalBookingAmount=new BigDecimal(0);
		for(FlightOrderRow flightOrderRow:flightOrderRowList){
			if(flightOrderRow!=null && flightOrderRow.getFlightOrderTripDetails()!=null && flightOrderRow.getFlightOrderTripDetails().size()>0){
				for(FlightOrderTripDetail trip:flightOrderRow.getFlightOrderTripDetails()){
					WeeklySalesDataVO flightDaySalesDataVO=new  WeeklySalesDataVO();
					String origin=trip.getOriginCode()!=null?trip.getOriginCode():"NA";
					String destination=trip.getDestinationCode()!=null?trip.getDestinationCode():"NA";
					if(flightOrderRow.getWeekType().equals(yearLIst.get(0))){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(flightOrderRow.getWeekType());
						year1FlightList.add(flightDaySalesDataVO);
					}
					if(flightOrderRow.getWeekType().equals(yearLIst.get(1))){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(flightOrderRow.getWeekType());
						year2FlightList.add(flightDaySalesDataVO);
					}
					if(flightOrderRow.getWeekType().equals(yearLIst.get(2))){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(flightOrderRow.getWeekType());
						year3FlightList.add(flightDaySalesDataVO);
					}
					if(flightOrderRow.getWeekType().equals(yearLIst.get(3))){
						flightDaySalesDataVO.setName(origin+"-"+destination); 
						flightDaySalesDataVO.setWeekDay(flightOrderRow.getWeekType());
						year4FlightList.add(flightDaySalesDataVO);
					}

				}
				if(flightOrderRow.getWeekType().equals(yearLIst.get(0))) 
					year1TotalBookingAmount= year1TotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(flightOrderRow.getWeekType().equals(yearLIst.get(1))) 
					year2TotalBookingAmount= year2TotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(flightOrderRow.getWeekType().equals(yearLIst.get(2))) 
					year3TotalBookingAmount= year3TotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
				if(flightOrderRow.getWeekType().equals(yearLIst.get(3))) 
					year4TotalBookingAmount= year4TotalBookingAmount.add(calculateTotInvoiceAmount(flightOrderRow));
			}
		}

		year1DataVO.setTotalCount(year1FlightList.size());//2017
		year1DataVO.setBookingAmount(year1TotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		year2DataVO.setTotalCount(year2FlightList.size());//2016
		year2DataVO.setBookingAmount(year2TotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		year3DataVO.setTotalCount(year3FlightList.size());//2015
		year3DataVO.setBookingAmount(year3TotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		 year4DataVO.setTotalCount(year4FlightList.size());//2014
		year4DataVO.setBookingAmount(year4TotalBookingAmount.setScale(2, BigDecimal.ROUND_UP));
		 
		year2DataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(year2DataVO, year1DataVO));
		year2DataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(year2DataVO, year1DataVO));
		year3DataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(year3DataVO, year2DataVO));
		year3DataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(year3DataVO, year2DataVO));
		 year4DataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(year4DataVO, year3DataVO));
		year4DataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(year4DataVO, year3DataVO)); 
		year1DataVO.setPercentageChangeInTotalCount(calculateTotCountPercentage(year1DataVO, year4DataVO));
		year1DataVO.setPercentageChangeInBookingAmount(calculateTotAmountPercentage(year1DataVO, year4DataVO));
 
		 yearDayMap.put("YEAR1", year1DataVO);
		yearDayMap.put("YEAR2", year2DataVO);
		yearDayMap.put("YEAR3", year3DataVO);
		yearDayMap.put("YEAR4", year4DataVO); 
			 
		 
		return yearDayMap; 
	}
	public List<FlightOrderRow> mergeWeekorYearFlightOrderRowList(Map<String,	List<FlightOrderRow>> map){
		List<FlightOrderRow> list=new LinkedList<>();
		if(map!=null && map.size()>0){
			for(Entry<String, List<FlightOrderRow>> entry:map.entrySet()){
				for(FlightOrderRow flightOrderRow:entry.getValue()){
					flightOrderRow.setWeekType(entry.getKey());
					list.add(flightOrderRow);
				}
			}
		}
		return list;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPresentDate() {
		return presentDate;
	}
	public void setPresentDate(String presentDate) {
		this.presentDate = presentDate;
	}
	 
	public BigDecimal  calculateTotInvoiceAmount(FlightOrderRow flightOrderRow){
		BigDecimal invoiceAmount= new BigDecimal(0);
		if(flightOrderRow!=null && flightOrderRow.getFlightOrderRowServiceTax()!=null) 
			invoiceAmount=flightOrderRow.getFinalPrice()!=null?flightOrderRow.getFinalPrice():new BigDecimal(0).add(flightOrderRow.getServiceTax()!=null ?flightOrderRow.getServiceTax():new BigDecimal(0)).add(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee():new BigDecimal(0)).add(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getManagementFee():new BigDecimal(0)) ;

			else if(flightOrderRow!=null && flightOrderRow.getFlightOrderRowGstTax()!=null)
				invoiceAmount=(flightOrderRow.getFinalPrice()!=null ?flightOrderRow.getFinalPrice():new BigDecimal(0)).add(flightOrderRow.getGstOnFlights()!=null?flightOrderRow.getGstOnFlights():new BigDecimal(0)).add(flightOrderRow.getFlightOrderRowGstTax()!=null && flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee():new BigDecimal(0)).add(flightOrderRow.getFlightOrderRowGstTax()!=null && flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getManagementFee():new BigDecimal(0));
		return invoiceAmount;
	}

	public BigDecimal  calculateTotAmountPercentage(WeeklySalesDataVO  toDayVO,WeeklySalesDataVO yesterDayVO){
		BigDecimal  percentage=null;
		if(toDayVO.getBookingAmount().compareTo(new BigDecimal(0))<=0 && yesterDayVO.getBookingAmount().compareTo(new BigDecimal(0))<=0) 
			percentage=new BigDecimal(0);
		else if(toDayVO.getBookingAmount().compareTo(new BigDecimal(0))<=0 &&  yesterDayVO.getBookingAmount().compareTo(new BigDecimal(0))!=0) 
			percentage=new BigDecimal(100).negate();
		else if(yesterDayVO.getBookingAmount().compareTo(new BigDecimal(0))<=0 && toDayVO.getBookingAmount().compareTo(new BigDecimal(0))!=0) 
			percentage=new BigDecimal(100);

		else{
			percentage=toDayVO.getBookingAmount().subtract(yesterDayVO.getBookingAmount());
			if(percentage.compareTo(new BigDecimal(0))==0) 
				percentage=new BigDecimal(0);
			else{
				percentage= ArithmeticUti.multiplyTo2Decimal(percentage, new BigDecimal(100));
				percentage= ArithmeticUti.divideTo2Decimal(percentage,yesterDayVO.getBookingAmount());
			}
		}
		return percentage.setScale(2, BigDecimal.ROUND_UP);
	}

	public BigDecimal  calculateTotCountPercentage(WeeklySalesDataVO  toDatVO,WeeklySalesDataVO yesterDayVO){
		BigDecimal  percentage=null;
		if(toDatVO.getTotalCount()<=0 && yesterDayVO.getTotalCount()<=0) 
			percentage=new BigDecimal(0);
		else if(toDatVO.getTotalCount()<=0 && yesterDayVO.getTotalCount()!=0) 
			percentage=new BigDecimal(100).negate();
		else if(yesterDayVO.getTotalCount()<=0 && toDatVO.getTotalCount()!=0) 
			percentage=new BigDecimal(100);

		else{
			percentage=new BigDecimal(toDatVO.getTotalCount()).subtract(new BigDecimal(yesterDayVO.getTotalCount())) ;
			if(percentage.compareTo(new BigDecimal(0))==0) 
				percentage=new BigDecimal(0);
			else{
				percentage= ArithmeticUti.multiplyTo2Decimal(percentage, new BigDecimal(100));
				percentage= ArithmeticUti.divideTo2Decimal(percentage,new BigDecimal(yesterDayVO.getTotalCount()));
			}
		}
		return percentage.setScale(2, BigDecimal.ROUND_UP);
	}
	public WeeklySalesVO getFlightComaprisonData() {
		return flightComaprisonData;
	}
	public void setFlightComaprisonData(WeeklySalesVO flightComaprisonData) {
		this.flightComaprisonData = flightComaprisonData;
	}
	
}
