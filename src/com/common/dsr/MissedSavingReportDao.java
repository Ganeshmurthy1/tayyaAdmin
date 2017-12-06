package com.common.dsr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.common.dsr.helper.DsrRmConfigHelperDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.flight.model.FlightFareAlertConnectingFlight;
import com.lintas.admin.flight.model.FlightFareAlertDetail;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.RmConfigTripDetailsModel;

public class MissedSavingReportDao {
	public  List<CommonDsrMissedFlightReport> getCommonDsrMissedSavingReport(CommonDsrPage commonDsrPage){
		DsrRmConfigHelperDao dsrRmConfigHelperDao=new DsrRmConfigHelperDao();
		List<CommonDsrMissedFlightReport> dsrMissedFlightReportList= new ArrayList<CommonDsrMissedFlightReport>();
		CommonDsrUtility commonDsrUtility=null;
		List<FlightOrderRow> flightOrderRowList=null;
		commonDsrUtility=new CommonDsrReportDao().getCommonDsrFlightReports(commonDsrPage);
		flightOrderRowList=commonDsrUtility.getFlightOrderRowList();

		for(FlightOrderRow flightOrderRow:flightOrderRowList){
			RmConfigTripDetailsModel rmConfigTripDetails=new RmConfigTripDetailsModel();
			Company company = new CompanyDAO() .getCompanyProfile(Integer.parseInt(flightOrderRow.getCompanyId()));
			rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(flightOrderRow.getOrderId(), company.getCompanyid());
			CommonDsrMissedFlightReport dsrMissedFlightReport=new CommonDsrMissedFlightReport();
			List<FlightFareAlertDetail> fareAlertDetail=new FareAlertDao().getFlightFareAlertDetail(flightOrderRow.getOrderId());
			if(fareAlertDetail!=null && fareAlertDetail.size()>0){
				List<String> lowestFlightDetails=new ArrayList<String>();
				List<String> ticketedVsLowestFareDifference=new ArrayList<String>();
				for(FlightFareAlertDetail alertDetail:fareAlertDetail){
					List<FlightFareAlertConnectingFlight> fareAlertConnectingFlightList=alertDetail.getFlightFareAlertConnectingFlightList();

					ticketedVsLowestFareDifference.add(String.valueOf(flightOrderRow.getFinalPrice().setScale(2, BigDecimal.ROUND_UP).subtract(alertDetail.getTotalFare())));

					lowestFlightDetails.add(alertDetail.getAirlineName()+","+alertDetail.getFlightNo()+","+alertDetail.getFareClass()+","+alertDetail.getArrivalTime()+","+alertDetail.getDepartureTime()+","+alertDetail.getTotalFare());

					if(fareAlertConnectingFlightList.size()>0){
						for(FlightFareAlertConnectingFlight connectingFlight:fareAlertConnectingFlightList)
							dsrMissedFlightReport.setSector(flightOrderRow.getOrigin()+"-"+connectingFlight.getDestinationcode());
					}
					else
						dsrMissedFlightReport.setSector(flightOrderRow.getOrigin()+"-"+flightOrderRow.getDestination());

				}
				/*faredetails*/
				if(ticketedVsLowestFareDifference.size()==1){
					dsrMissedFlightReport.setTicketedVsLowest1FareDifference(ticketedVsLowestFareDifference.get(0));
					dsrMissedFlightReport.setTicketedVsLowest2FareDifference("");
				}
				else if(ticketedVsLowestFareDifference.size()==2){
					dsrMissedFlightReport.setTicketedVsLowest1FareDifference(ticketedVsLowestFareDifference.get(0));
					dsrMissedFlightReport.setTicketedVsLowest2FareDifference(ticketedVsLowestFareDifference.get(1));
				}
				else{
					dsrMissedFlightReport.setTicketedVsLowest1FareDifference("");
					dsrMissedFlightReport.setTicketedVsLowest2FareDifference("");
				}

				if(lowestFlightDetails.size()==1){
					dsrMissedFlightReport.setLowestFlightDetails1(lowestFlightDetails.get(0));
					dsrMissedFlightReport.setLowestFlightDetails2("");
				}
				else if(lowestFlightDetails.size()==2){
					dsrMissedFlightReport.setLowestFlightDetails1(lowestFlightDetails.get(0));
					dsrMissedFlightReport.setLowestFlightDetails2(lowestFlightDetails.get(1));
				}
				else {
					dsrMissedFlightReport.setLowestFlightDetails1("");
					dsrMissedFlightReport.setLowestFlightDetails2("");
				}

				dsrMissedFlightReport.setReason(fareAlertDetail.size()>0 && fareAlertDetail.get(0)!=null?fareAlertDetail.get(0).getReasons():"-");

				dsrMissedFlightReport.setThresholdBuffer(String.valueOf(company.getFlightThresHoldBuffer()!=null?company.getFlightThresHoldBuffer().setScale(2, BigDecimal.ROUND_UP):""));
				dsrMissedFlightReport.setTimeWindowMinute(String.valueOf(company.getFlightTimeWindowMinutes()!=null?company.getFlightTimeWindowMinutes():""));

				BigDecimal saving=flightOrderRow.getFinalPrice().setScale(2, BigDecimal.ROUND_UP).subtract(fareAlertDetail.size()>0 && fareAlertDetail.get(0)!=null?fareAlertDetail.get(0).getTotalFare():new BigDecimal("0.00"));
				dsrMissedFlightReport.setMisseedRealisedSaving(String.valueOf(saving.multiply(new BigDecimal(flightOrderRow.getPassengerCount()))));


				if(ticketedVsLowestFareDifference.size()>1){
					int wastedAmount=new BigDecimal(ticketedVsLowestFareDifference.get(0)).compareTo(new BigDecimal(ticketedVsLowestFareDifference.get(1)));
					dsrMissedFlightReport.setWastedAmount(String.valueOf(wastedAmount >=0 ?ticketedVsLowestFareDifference.get(0):ticketedVsLowestFareDifference.get(1)));
				}
				else if(ticketedVsLowestFareDifference.size()>0)
					dsrMissedFlightReport.setWastedAmount(String.valueOf(ticketedVsLowestFareDifference.get(0)));

				else
					dsrMissedFlightReport.setWastedAmount("-");

				dsrMissedFlightReport.setCart(flightOrderRow.getOrderId());
				dsrMissedFlightReport.setBookingDate(flightOrderRow.getBookingDate());
				dsrMissedFlightReport.setTravellor(String.valueOf(flightOrderRow.getPassengerCount()));
				String ticketedFlightDetails=flightOrderRow.getAirline()+","+ flightOrderRow.getArrivalDate()+","+ flightOrderRow.getDepartureDate()+","+ flightOrderRow.getFinalPrice().setScale(2, BigDecimal.ROUND_UP);
				dsrMissedFlightReport.setTicketedFlightDetails(ticketedFlightDetails);

				try{
					boolean isIsInternational=CommonDsrDao.isDomesticOrInternational(flightOrderRow.getDestination(),flightOrderRow.getOrigin());
					if(isIsInternational)
						dsrMissedFlightReport.setType("International"); 
					else
						dsrMissedFlightReport.setType("Domestic"); 
				}catch (Exception e) {
					dsrMissedFlightReport.setType("-"); 
				}


				/*rm*/
				if(rmConfigTripDetails!=null && rmConfigTripDetails.getId()>0){

					dsrMissedFlightReport.setCostCenter(rmConfigTripDetails.getCostCenter());
					dsrMissedFlightReport.setEmpCode(rmConfigTripDetails.getEmpCode());
					dsrMissedFlightReport.setBandCode("-");
					dsrMissedFlightReport.setDepartmentCode(rmConfigTripDetails.getDepartment());
					dsrMissedFlightReport.setLocationCode(rmConfigTripDetails.getLocation());
				}

				dsrMissedFlightReportList.add(dsrMissedFlightReport);
			}

		}

		return dsrMissedFlightReportList;
	}
}
