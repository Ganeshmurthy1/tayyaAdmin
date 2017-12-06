package com.admin.dashboardsearch.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import com.admin.dashboardsearch.VO.DashBoardSearchCommonVirtualObject;
import com.admin.dashboardsearch.dao.DashBoardSearchDao;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.opensymphony.xwork2.ActionSupport;

public class DashBoardSearchAction extends ActionSupport implements SessionAware{
	/**
	 * @author Basha in 04-08-2017
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DashBoardSearchAction.class);
	String searchingvalue = null;
	String airline = "SpiceJet";
	private DashBoardSearchDao dashBoardSearchDao=new DashBoardSearchDao();
	List<DashBoardSearchCommonVirtualObject> dashBoardSearchCommonVirtualObject=new ArrayList<>();
	DashBoardSearchCommonVirtualObject dashBoardSearchModel=new DashBoardSearchCommonVirtualObject();
	SessionMap<String, Object> sessionMap=null;
	int availablePages = 0;
	int availableItems = 0;
	Session session=null;
	public String dashboardsearchmanager() {
		Company sessionCompany = (Company) sessionMap.get("Company");
		System.out.println("searched value is-------------" + searchingvalue);
		List<FlightOrderRow> flightOrderRows = dashBoardSearchDao.getFlightOrderRowDetailList(searchingvalue,sessionCompany);
		List<HotelOrderRow> hotelOrderRows = dashBoardSearchDao.getHotelOrderRowByIdList(searchingvalue,sessionCompany);
		List<CarOrderRow> carOrderRows = dashBoardSearchDao.getCarOrderRowDetailList(searchingvalue,sessionCompany);
		List<BusOrderRow> busOrderRows = dashBoardSearchDao.getBusOrderRowDetailList(searchingvalue,sessionCompany);
		List<TrainOrderRow> trainOrderRows = dashBoardSearchDao.getTrainOrderRowDetailList(searchingvalue,sessionCompany);
		List<VisaOrderRow> visaOrderRows = dashBoardSearchDao.getVisaOrderRowDetailList(searchingvalue,sessionCompany);
		List<InsuranceOrderRow> insuranceOrderRows = dashBoardSearchDao.getInsuranceOrderRowDetailList(searchingvalue,sessionCompany);
		List<MiscellaneousOrderRow> miscellaneousOrderRows = dashBoardSearchDao.getMiscellaneousOrderRowDetailList(searchingvalue,sessionCompany);
		List<DashBoardSearchCommonVirtualObject> list=new ArrayList<>();
		
		if (flightOrderRows != null && flightOrderRows.size()>0) {
			for(FlightOrderRow flightrowIterate:flightOrderRows){
				dashBoardSearchModel=dashBoardSearchDao.generateFlightRequiredData(flightrowIterate,sessionCompany);
				if(dashBoardSearchModel!=null){
					list.add(dashBoardSearchModel);	
				}
			}
		}
		if (hotelOrderRows != null && hotelOrderRows.size()>0) {
			for(HotelOrderRow hotelrowIterate:hotelOrderRows){
				dashBoardSearchModel=dashBoardSearchDao.generateHotelRequiredData(hotelrowIterate,sessionCompany);
				if(dashBoardSearchModel!=null){
					list.add(dashBoardSearchModel);	
				}
			}
		}
		if (carOrderRows != null && carOrderRows.size()>0) {
			for(CarOrderRow carrowIterate:carOrderRows){
				dashBoardSearchModel=dashBoardSearchDao.generateCarRequiredData(carrowIterate,sessionCompany);
				if(dashBoardSearchModel!=null){
					list.add(dashBoardSearchModel);	
				}
			}
		}
		if (busOrderRows != null && busOrderRows.size()>0) {
			for(BusOrderRow busrowIterate:busOrderRows){
				dashBoardSearchModel=dashBoardSearchDao.generateBusRequiredData(busrowIterate,sessionCompany);
				if(dashBoardSearchModel!=null){
					list.add(dashBoardSearchModel);	
				}
			}
		}
		if (trainOrderRows != null && trainOrderRows.size()>0) {
			for(TrainOrderRow trainrowIterate:trainOrderRows){
				dashBoardSearchModel=dashBoardSearchDao.generateTrainRequiredData(trainrowIterate,sessionCompany);
				if(dashBoardSearchModel!=null){
					list.add(dashBoardSearchModel);	
				}
			}
		}
		if (visaOrderRows != null && visaOrderRows.size()>0) {
			for(VisaOrderRow visarowIterate:visaOrderRows){
				dashBoardSearchModel=dashBoardSearchDao.generateVisaRequiredData(visarowIterate,sessionCompany);
				if(dashBoardSearchModel!=null){
					list.add(dashBoardSearchModel);	
				}
			}
		}
		if (insuranceOrderRows != null && insuranceOrderRows.size()>0) {
			for(InsuranceOrderRow insurancerowIterate:insuranceOrderRows){
				dashBoardSearchModel=dashBoardSearchDao.generateInsuranceRequiredData(insurancerowIterate,sessionCompany);
				if(dashBoardSearchModel!=null){
					list.add(dashBoardSearchModel);	
				}
			}
		}
		if (miscellaneousOrderRows != null && miscellaneousOrderRows.size()>0) {
			for(MiscellaneousOrderRow miscellaneousrowIterate:miscellaneousOrderRows){
				dashBoardSearchModel=dashBoardSearchDao.generateMiscellaneousRequiredData(miscellaneousrowIterate,sessionCompany);
				if(dashBoardSearchModel!=null){
					list.add(dashBoardSearchModel);	
				}
			}
		}
		/* 
		System.out.println(list);
		
		if(airline!=null && !airline.equals("")){
			for(DashBoardSearchCommonVirtualObject dashBoardSearchCommonVirtualObjectNew:list){
				if(dashBoardSearchCommonVirtualObjectNew.getAirline().equals(airline)){
					dashBoardSearchCommonVirtualObject.add(dashBoardSearchCommonVirtualObjectNew);
				}
			}
		}
		
		else*/
		 setDashBoardSearchCommonVirtualObject(list);
		setSearchingvalue(searchingvalue);
		return SUCCESS;
	}  
	public String getSearchingvalue() {
		return searchingvalue;
	}
	public void setSearchingvalue(String searchingvalue) {
		this.searchingvalue = searchingvalue;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	public List<DashBoardSearchCommonVirtualObject> getDashBoardSearchCommonVirtualObject() {
		return dashBoardSearchCommonVirtualObject;
	}
	public void setDashBoardSearchCommonVirtualObject(
			List<DashBoardSearchCommonVirtualObject> dashBoardSearchCommonVirtualObject) {
		this.dashBoardSearchCommonVirtualObject = dashBoardSearchCommonVirtualObject;
	}
	public DashBoardSearchCommonVirtualObject getDashBoardSearchModel() {
		return dashBoardSearchModel;
	}
	public void setDashBoardSearchModel(DashBoardSearchCommonVirtualObject dashBoardSearchModel) {
		this.dashBoardSearchModel = dashBoardSearchModel;
	}
	public int getAvailablePages() {
		return availablePages;
	}
	public void setAvailablePages(int availablePages) {
		this.availablePages = availablePages;
	}
	public int getAvailableItems() {
		return availableItems;
	}
	public void setAvailableItems(int availableItems) {
		this.availableItems = availableItems;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
}
