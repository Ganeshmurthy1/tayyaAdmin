package com.admin.flight.fin.sheet.action;

import com.admin.flight.fin.sheet.Dao.FlightTravelRequestDao;
import com.admin.flight.fin.sheet.model.FlightTravelRequestQuotation;
import com.admin.hotel.fin.sheet.Dao.HotelTravelRequestDao;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FlightTravelQuotationCRUDAction extends ActionSupport  implements ModelDriven<FlightTravelRequestQuotation>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FlightTravelRequestQuotation flightTravelRequestQuotation=new FlightTravelRequestQuotation();
	FlightTravelRequestDao flightTravelRequestDao=new FlightTravelRequestDao();
	public String flightRequestQuotationUpdate(){
		FlightTravelRequestQuotation updatedQuotationObj=flightTravelRequestDao.flightRequestQuotationUpdate(flightTravelRequestQuotation);
			if(updatedQuotationObj!=null){
				addActionMessage("Successfully Updated."); 
			}
			else{
				addActionMessage("We found some error.Please try again."); 
			}
			return SUCCESS;	
		}
	
	@Override
	public FlightTravelRequestQuotation getModel() {
		// TODO Auto-generated method stub
		return flightTravelRequestQuotation;
	}
	public FlightTravelRequestQuotation getFlightTravelRequestQuotation() {
		return flightTravelRequestQuotation;
	}
	public void setFlightTravelRequestQuotation(FlightTravelRequestQuotation flightTravelRequestQuotation) {
		this.flightTravelRequestQuotation = flightTravelRequestQuotation;
	}
}
