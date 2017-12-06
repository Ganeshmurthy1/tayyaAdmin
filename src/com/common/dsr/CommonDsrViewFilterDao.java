package com.common.dsr;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.dao.CarTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.common.dsr.helper.DsrRmConfigHelperDao;
import com.isl.admin.filter.FlightReportFilter;
import com.isl.admin.page.Page;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.FlightAndHotelBookApiResponse;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.flight.model.ReportData;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Airport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.FlightOrderRowMarkup;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.SalesLeadGeneration;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;

public class CommonDsrViewFilterDao {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonDsrViewFilterDao.class);
CommonDsrDao commonDsrDao=new CommonDsrDao();
DsrRmConfigHelperDao dsrRmConfigHelperDao=new DsrRmConfigHelperDao();
	public List<CommonDsrTravelReportData> getCommonDsrTravelViewReports(CommonDsrPage commonDsrPage){
		List<CommonDsrTravelReportData> commonDsrTravelReports=new ArrayList<>();
		List<FlightOrderRow> flightOrderRowList=null;
		List<HotelOrderRow> hotelOrderRowList=null;
		List<CarOrderRow> carOrderRowList=null;
		List<BusOrderRow> busOrderRowList=null;
		List<TrainOrderRow> trainOrderRowList=null;
		List<VisaOrderRow> visaOrderRowList=null;
		List<InsuranceOrderRow> insuranceOrderRowList=null;

		if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("F")) 
			flightOrderRowList=getCommonDsrFlightReports(commonDsrPage);
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("H")) 
			hotelOrderRowList=getCommonDsrHotelReports(commonDsrPage);
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("C")) 
			carOrderRowList=getCommonDsrCarReports(commonDsrPage);
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("T")) 
			trainOrderRowList=getCommonDsrTrainReports(commonDsrPage);
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("B")) 
			busOrderRowList=getCommonDsrBusReports(commonDsrPage);
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("V")) 
			visaOrderRowList=getCommonDsrVisaReports(commonDsrPage);
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("I")) 
			insuranceOrderRowList=getCommonDsrInsuranceReports(commonDsrPage);
		/*else{
			flightOrderRowList=getCommonDsrFlightReports(commonDsrPage);
			hotelOrderRowList=getCommonDsrHotelReports(commonDsrPage);
			carOrderRowList=getCommonDsrCarReports(commonDsrPage);
			trainOrderRowList=getCommonDsrTrainReports(commonDsrPage);
			busOrderRowList=getCommonDsrBusReports(commonDsrPage);
			visaOrderRowList=getCommonDsrVisaReports(commonDsrPage);
			insuranceOrderRowList=getCommonDsrInsuranceReports(commonDsrPage);
		}*/

		BigDecimal YQTax = new BigDecimal("0");
		BigDecimal YRTax = new BigDecimal("0");
		BigDecimal WOTax = new BigDecimal("0");
		BigDecimal PSFTax = new BigDecimal("0");
		BigDecimal UDFTax = new BigDecimal("0");
		BigDecimal JNTax = new BigDecimal("0");
		BigDecimal INTax = new BigDecimal("0");

		if(flightOrderRowList!=null && flightOrderRowList.size()>0){
			for(FlightOrderRow flightOrderRow:flightOrderRowList){
				if(flightOrderRow!=null && flightOrderRow.getFlightOrderCustomers()!=null && flightOrderRow.getFlightOrderCustomers().size()>0){
					for(int j=0;j<flightOrderRow.getFlightOrderCustomers().size();j++){
						CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
						FlightOrderCustomer flightOrderCustomer=flightOrderRow.getFlightOrderCustomers().get(j);
						FlightOrderCustomerPriceBreakup flightOrderCustomerPriceBreakup=flightOrderRow.getFlightOrderCustomerPriceBreakups().get(j);
						//FlightOrderCustomerSSR flightOrderCustomerSSR= flightOrderRow.getFlightOrderCustomerSSR().get(j);
						commonDsrTravelReportData.setBkgRef(flightOrderRow.getOrderId()); 
						commonDsrTravelReportData.setSystemInvoiceId(flightOrderRow.getInvoiceNo()); 
						commonDsrTravelReportData.setBookingType(flightOrderRow.getBookingMode());
						commonDsrTravelReportData.setAmendmentType(flightOrderRow.getStatusAction());
						commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(flightOrderRow.getCreatedAt()));
						commonDsrTravelReportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(flightOrderRow.getCompanyId()));
						User user=new UserDAO().GetUserProfile(Integer.parseInt(flightOrderRow.getUserId()));
						commonDsrTravelReportData.setCorporateName(company.getCompanyname());
						commonDsrTravelReportData.setBillingEntity(company.getCompanyname());
						commonDsrTravelReportData.setBookerName(user.getUsername());
						commonDsrTravelReportData.setBookersLoginId(user.getEmail());
						commonDsrTravelReportData.setSupplierCode(flightOrderRow.getProviderAPI());
						commonDsrTravelReportData.setSupplierName(flightOrderRow.getProviderAPI());
						BigDecimal basePriceInBooking=null;
						BigDecimal taxesInBooking=null;
						BigDecimal discountInBooking=null;
						BigDecimal markupInBooking=null;
						BigDecimal supplierDiscountInBooking=null;
						BigDecimal netFare=null;
						BigDecimal grossFare=null;
						BigDecimal supplierCharge=null;
						BigDecimal baggagePriceInBooking=null;
						BigDecimal mealPriceInBooking=null;
						BigDecimal totExtraChargeInBooking=new BigDecimal(0);
						/*if(flightOrderCustomerSSR!=null){
							BigDecimal baggagePrice=flightOrderCustomerSSR.getBaggagePrice()!=null?new BigDecimal(flightOrderCustomerSSR.getBaggagePrice()).multiply(flightOrderRow.getApiToBaseExchangeRate()):new BigDecimal(0);
							baggagePriceInBooking=baggagePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
							if(baggagePriceInBooking!=null) 
								baggagePriceInBooking=new BigDecimal("0");
						  BigDecimal mealPrice=flightOrderCustomerSSR.getMealPrice()!=null?new BigDecimal(flightOrderCustomerSSR.getMealPrice()).multiply(flightOrderRow.getApiToBaseExchangeRate()):new BigDecimal(0);
							mealPriceInBooking=mealPrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
							if(mealPriceInBooking!=null) 
								mealPriceInBooking=new BigDecimal("0");
						 }*/
						/*totExtraChargeInBooking=baggagePriceInBooking.add(mealPriceInBooking);*/

						if(flightOrderCustomerPriceBreakup.getTax()!=null){
							BigDecimal tax= flightOrderCustomerPriceBreakup.getTax().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
							taxesInBooking=tax.multiply(flightOrderRow.getBaseToBookingExchangeRate());
						}
						else  
							taxesInBooking=new BigDecimal("0");

						if(flightOrderCustomerPriceBreakup.getSupplierDiscount()!=null){
							BigDecimal supplierDiscount= flightOrderCustomerPriceBreakup.getSupplierDiscount().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
							discountInBooking=supplierDiscount.multiply(flightOrderRow.getBaseToBookingExchangeRate());
						}
						else  
							discountInBooking=new BigDecimal("0");

						if(flightOrderCustomerPriceBreakup.getBaseFare()!=null){
							BigDecimal basePrice= flightOrderCustomerPriceBreakup.getBaseFare().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
							basePriceInBooking=basePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
						}
						else  
							basePriceInBooking=new BigDecimal("0");

						if(flightOrderCustomerPriceBreakup.getSupplierDiscount()!=null){
							BigDecimal discount= flightOrderCustomerPriceBreakup.getSupplierDiscount().multiply(flightOrderRow.getApiToBaseExchangeRate()) ;
							supplierDiscountInBooking=discount.multiply(flightOrderRow.getBaseToBookingExchangeRate());
						}
						else  
							supplierDiscountInBooking=new BigDecimal("0");

						if(flightOrderCustomerPriceBreakup.getMarkup()!=null) 
							markupInBooking= new BigDecimal(flightOrderCustomerPriceBreakup.getMarkup()).multiply(flightOrderRow.getBaseToBookingExchangeRate());
						else  
							markupInBooking=new BigDecimal("0");



						supplierCharge=basePriceInBooking.add(taxesInBooking).subtract(supplierDiscountInBooking);
						commonDsrTravelReportData.setSupplierCharge(supplierCharge.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
						commonDsrTravelReportData.setProductType("Air");
						String itineraryType=isDomesticOrInternational(flightOrderRow.getDestination(),flightOrderRow.getOrigin())?"International":"Domestic";
						commonDsrTravelReportData.setItineraryType(itineraryType);
						commonDsrTravelReportData.setProductName(flightOrderRow.getAirline());
						StringBuilder descode = new StringBuilder();
						StringBuilder srcCode = new StringBuilder();
						for(int i=0;i<flightOrderRow.getFlightOrderTripDetails().size();i++){
							FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
							if(i == flightOrderRow.getFlightOrderTripDetails().size()-1) {
								descode.append(trips.getDestinationCode());
								srcCode.append(trips.getOriginCode());
							}
							else{
								descode.append(trips.getDestinationCode() + "-");
								srcCode.append(trips.getOriginCode() + "-");
							}
							commonDsrTravelReportData.setProductCode(trips.getOperatedByCode()+"-"+trips.getFlightNumber());
							commonDsrTravelReportData.setCabinClass(trips.getClassOfService());
							commonDsrTravelReportData.setBookingClass(trips.getFareClass());
							commonDsrTravelReportData.setFareBasis(trips.getFareBasisCode());
							commonDsrTravelReportData.setFareType(trips.getCraft());

						}

						commonDsrTravelReportData.setMarkup(markupInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setDescription(srcCode+"/"+descode);
						commonDsrTravelReportData.setAirlinePNRorProvBooking(flightOrderRow.getPnr());
						commonDsrTravelReportData.setGDSPNR(flightOrderRow.getPnr());
						commonDsrTravelReportData.setTicketNumorFinalBooking(flightOrderCustomer.getEticketnumber());
						commonDsrTravelReportData.setBookingRefundType("-");
						commonDsrTravelReportData.setPaxType(flightOrderCustomer.getPassengerTypeCode());
						commonDsrTravelReportData.setTraveller(flightOrderCustomer.getTitle()+" "+flightOrderCustomer.getFirstName()+" "+flightOrderCustomer.getLastName());
						commonDsrTravelReportData.setTotalNights("0");
						commonDsrTravelReportData.setTripStartsDate(DateConversion.getBluestarDate(flightOrderRow.getDepartureDate()));
						commonDsrTravelReportData.setTripEndDate(DateConversion.getBluestarDate(flightOrderRow.getArrivalDate()));
						String tripType=null;
						if(flightOrderRow.getTripType().equals("O")) 
							tripType="One Way";
						if(flightOrderRow.getTripType().equals("R")) 
							tripType="Round Trip";
						if(flightOrderRow.getTripType().equals("SR")) 
							tripType="Special Round Trip";

						commonDsrTravelReportData.setJourneyType(tripType);
						//grossFare=basePriceInBooking.add(taxesInBooking);

						BigDecimal transactionFee = new BigDecimal("0");
						BigDecimal BaseServiceTax = new BigDecimal("0");
						BigDecimal otherTaxesWithMarkup = new BigDecimal("0");

						YQTax =flightOrderCustomerPriceBreakup.getYQTax()!=null?flightOrderCustomerPriceBreakup.getYQTax():new BigDecimal("0");
						YRTax =  flightOrderCustomerPriceBreakup.getYRTax()!=null?flightOrderCustomerPriceBreakup.getYRTax():new BigDecimal("0");
						WOTax = flightOrderCustomerPriceBreakup.getWOTax()!=null?flightOrderCustomerPriceBreakup.getWOTax():new BigDecimal("0");
						PSFTax = flightOrderCustomerPriceBreakup.getPSFTax()!=null?flightOrderCustomerPriceBreakup.getPSFTax():new BigDecimal("0");
						UDFTax = flightOrderCustomerPriceBreakup.getUDFTax()!=null?flightOrderCustomerPriceBreakup.getUDFTax():new BigDecimal("0");
						JNTax =  flightOrderCustomerPriceBreakup.getJNTax()!=null?flightOrderCustomerPriceBreakup.getJNTax():new BigDecimal("0");
						INTax =  flightOrderCustomerPriceBreakup.getINTax()!=null?flightOrderCustomerPriceBreakup.getINTax():new BigDecimal("0");
						otherTaxesWithMarkup=taxesInBooking.add(markupInBooking)/*.add(YQTax).add(YRTax).add(PSFTax).add(UDFTax).add(JNTax)*/;
						grossFare=basePriceInBooking.add(otherTaxesWithMarkup).add(totExtraChargeInBooking);
						commonDsrTravelReportData.setGrossFare(grossFare.setScale(0, BigDecimal.ROUND_UP).toString());
						transactionFee = transactionFee.add(flightOrderCustomerPriceBreakup.getTransactionFee()!=null?flightOrderCustomerPriceBreakup.getTransactionFee():new BigDecimal("0"));		
						BaseServiceTax = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getBasicTax()!=null?flightOrderRow.getFlightOrderRowServiceTax().getBasicTax():new BigDecimal(0));
						BigDecimal swachBharatCess = new BigDecimal("0");
						swachBharatCess = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null &&  flightOrderRow.getFlightOrderRowServiceTax().getSwatchBharathCess()!=null?flightOrderRow.getFlightOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
						BigDecimal krishiKalyanCess = new BigDecimal("0");
						krishiKalyanCess = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getKrishiKalyanCess()!=null?flightOrderRow.getFlightOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
						BigDecimal TotalServiceTax = new BigDecimal("0");
						TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//basePriceInBooking.setScale(0, BigDecimal.ROUND_UP).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getTotalTax()!=null?flightOrderRow.getFlightOrderRowServiceTax().getTotalTax():new BigDecimal(0));
						BigDecimal convenienceFee = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						convenienceFee= flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
						managementFee= flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getManagementFee():new BigDecimal(0);

						netFare = grossFare.add(TotalServiceTax).add(managementFee).add(convenienceFee);
						commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setYQTax(YQTax.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setYRTax(YRTax.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setPSFTax(PSFTax.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setUDFTax(UDFTax.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setJNTax(JNTax.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setOBTax("0");
						commonDsrTravelReportData.setOCTax("0");
						commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setExtraCharge(totExtraChargeInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
						commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setDiscount(discountInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(0, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setModeOfPayment(flightOrderRow.getPaidBy());
						commonDsrTravelReportData.setTravelStatus(flightOrderRow.getStatusAction());
						commonDsrTravelReportData.setPersonalBooking("No");
						commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
						RmConfigTripDetailsModel 	rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(flightOrderRow.getOrderId(),company.getCompanyid());
						commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
						commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
						commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
						commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
						commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
						commonDsrTravelReportData.setEmpCode("0");
						commonDsrTravelReports.add(commonDsrTravelReportData);

					}
				}

			}

		}
		if(hotelOrderRowList!=null && hotelOrderRowList.size()>0){
			for(HotelOrderRow hotelOrderRow:hotelOrderRowList){
				if(hotelOrderRow!=null && hotelOrderRow.getHotelOrderRoomInfos()!=null && hotelOrderRow.getHotelOrderRoomInfos().size()>0){
					for(int j=0;j<hotelOrderRow.getHotelOrderRoomInfos().size();j++){
						CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
						HotelOrderRoomInfo hotelOrderRoomInfo=hotelOrderRow.getHotelOrderRoomInfos().get(j);
						commonDsrTravelReportData.setBkgRef(hotelOrderRow.getOrderReference()); 
						commonDsrTravelReportData.setSystemInvoiceId(hotelOrderRow.getInvoiceNo()); 
						commonDsrTravelReportData.setBookingType(hotelOrderRow.getBookingMode());
						commonDsrTravelReportData.setAmendmentType(hotelOrderRow.getStatusAction());
						commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCreatedAt()));
						commonDsrTravelReportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(hotelOrderRow.getBookingDate()!=null?hotelOrderRow.getBookingDate():"-"));
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(hotelOrderRow.getCompanyId()));
						User user=new UserDAO().GetUserProfile(Integer.parseInt(hotelOrderRow.getUserId()));
						commonDsrTravelReportData.setCorporateName(company.getCompanyname());
						commonDsrTravelReportData.setBillingEntity(company.getCompanyname());
						commonDsrTravelReportData.setBookerName(user.getUsername());
						commonDsrTravelReportData.setBookersLoginId(user.getEmail());
						String apiProviderName="-";
						ApiProvider apiProvoder=null;
						if(hotelOrderRow.getBookingMode() !=null &&hotelOrderRow.getBookingMode().equalsIgnoreCase("Offline")) 
							apiProviderName=hotelOrderRow.getApiProvoder();
							if(hotelOrderRow.getBookingMode() !=null && hotelOrderRow.getBookingMode().equalsIgnoreCase("Online")) 
								apiProvoder=getApiPrividerDetails(hotelOrderRow.getApiProvoder());
							if(apiProvoder!=null) 
								apiProviderName=apiProvoder.getVendorName();
							else
								apiProvoder=new ApiProvider();
							commonDsrTravelReportData.setSupplierCode(apiProviderName);
							commonDsrTravelReportData.setSupplierName(apiProviderName);
							BigDecimal basePriceInBooking=null;
							BigDecimal apiPriceInBooking=null;
							BigDecimal taxesPriceInBooking=null;
							BigDecimal discountInBooking=null;
							BigDecimal roomBasePriceInBooking=null;
							BigDecimal roomApiPriceInBooking=null;
							BigDecimal roomTaxesPriceInBooking=null;
							BigDecimal roomDiscountInBooking=null;
							BigDecimal roomMarkupInBooking=null;
							BigDecimal grossFare=null;
							BigDecimal feeAmount=null;
							BigDecimal netFare=null;
							BigDecimal transactionFee = new BigDecimal("0");
							BigDecimal BaseServiceTax = new BigDecimal("0");
							BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
							BigDecimal managementFeePerRoom = new BigDecimal("0");
							if(hotelOrderRow.getApiPrice()!=null && hotelOrderRow.getTaxes()!=null){ 
								basePriceInBooking=hotelOrderRow.getApiPrice().subtract(hotelOrderRow.getTaxes()).multiply(hotelOrderRow.getApiToBaseExchangeRate()).multiply(hotelOrderRow.getBaseToBookingExchangeRate());
								roomBasePriceInBooking=basePriceInBooking.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
							}
							else
								roomBasePriceInBooking=new BigDecimal("0");

							if(hotelOrderRow.getApiPrice()!=null ){
								apiPriceInBooking=hotelOrderRow.getApiPrice().multiply(hotelOrderRow.getApiToBaseExchangeRate()).multiply(hotelOrderRow.getBaseToBookingExchangeRate()); 
								roomApiPriceInBooking=apiPriceInBooking.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
							}
							else
								roomApiPriceInBooking=new BigDecimal("0");

							if(hotelOrderRow.getTaxes()!=null){
								taxesPriceInBooking=hotelOrderRow.getTaxes().multiply(hotelOrderRow.getApiToBaseExchangeRate()).multiply(hotelOrderRow.getBaseToBookingExchangeRate()); 
								roomTaxesPriceInBooking=taxesPriceInBooking.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
							}
							else
								roomTaxesPriceInBooking=new BigDecimal("0");

							if(hotelOrderRow.getDiscountAmount()!=null){
								discountInBooking= hotelOrderRow.getDiscountAmount().multiply(hotelOrderRow.getApiToBaseExchangeRate()).multiply(hotelOrderRow.getBaseToBookingExchangeRate()); 
								roomDiscountInBooking=discountInBooking.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
							}
							else  
								roomDiscountInBooking=new BigDecimal("0");

							if(hotelOrderRow.getMarkupAmount()!=null) 
								roomMarkupInBooking= hotelOrderRow.getMarkupAmount().divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));

							else  
								roomMarkupInBooking=new BigDecimal("0");

							if(hotelOrderRow.getFeeAmount()!=null)
								feeAmount= hotelOrderRow.getFeeAmount().divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
							else  
								feeAmount=new BigDecimal("0");

							if(hotelOrderRow.getHotelOrderRowServiceTax() !=null && hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()!=null)
								managementFeePerRoom= hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee().divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
							else  
								managementFeePerRoom=new BigDecimal("0");



							commonDsrTravelReportData.setMarkup(roomMarkupInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setBaseFare(roomBasePriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setSupplierCharge(roomApiPriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setProductType("Hotel");
							String itineraryType=hotelOrderRow.getHotelOrderHotelData().getCountry().equalsIgnoreCase("India") ?"Domestic Hotel":"International Hotel";
							commonDsrTravelReportData.setItineraryType(itineraryType);
							commonDsrTravelReportData.setProductName(hotelOrderRow.getHotelOrderHotelData().getName());
							commonDsrTravelReportData.setProductCode("-");
							commonDsrTravelReportData.setDescription("Hotel Name: "+hotelOrderRow.getHotelOrderHotelData().getName());
							commonDsrTravelReportData.setAirlinePNRorProvBooking("");
							commonDsrTravelReportData.setGDSPNR("-");
							commonDsrTravelReportData.setTicketNumorFinalBooking(hotelOrderRow.getApiConfirmationNo());
							commonDsrTravelReportData.setFareType("-");
							commonDsrTravelReportData.setBookingRefundType("-");
							commonDsrTravelReportData.setFareBasis("-");
							if(hotelOrderRoomInfo.getHotelOrderGuests()!=null && hotelOrderRoomInfo.getHotelOrderGuests().size()>0){
								for(HotelOrderGuest hotelOrderGuest:hotelOrderRoomInfo.getHotelOrderGuests()){
									if(hotelOrderGuest.getLeader()){
										commonDsrTravelReportData.setPaxType(hotelOrderGuest.getPaxType());
										commonDsrTravelReportData.setTraveller(hotelOrderGuest.getTitle()+" "+hotelOrderGuest.getFirstName()+" "+hotelOrderGuest.getLastName());
									}

								}
							}
							String checkInDate=DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate());
							String checkOutDate=DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate());
							int noOfNights = 0;
							try {
								noOfNights = CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							commonDsrTravelReportData.setTotalNights(String.valueOf(noOfNights));
							commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate()));
							commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate()));
							commonDsrTravelReportData.setJourneyType("NA");
							otherTaxesWithMarkup=roomTaxesPriceInBooking.add(roomMarkupInBooking);

							grossFare=roomBasePriceInBooking.add(otherTaxesWithMarkup);

							commonDsrTravelReportData.setGrossFare(grossFare.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setYQTax("0");
							commonDsrTravelReportData.setYRTax("0");
							commonDsrTravelReportData.setPSFTax("0");
							commonDsrTravelReportData.setUDFTax("0");
							commonDsrTravelReportData.setJNTax("0");
							commonDsrTravelReportData.setOBTax("0");
							commonDsrTravelReportData.setOCTax("0");
							transactionFee = feeAmount;

							BaseServiceTax = roomBasePriceInBooking.setScale(0, BigDecimal.ROUND_UP).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax():new BigDecimal(0));
							BigDecimal swachBharatCess = new BigDecimal("0");
							swachBharatCess = basePriceInBooking.add(managementFeePerRoom).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
							BigDecimal krishiKalyanCess = new BigDecimal("0");
							krishiKalyanCess = basePriceInBooking.add(managementFeePerRoom).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
							BigDecimal TotalServiceTax = new BigDecimal("0");
							TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax():new BigDecimal(0));
							BigDecimal convenienceFee = new BigDecimal("0");
							BigDecimal managementFee = new BigDecimal("0");
							convenienceFee=hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()!=null? hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
							managementFee=hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()!=null? hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee():new BigDecimal(0);
							netFare = grossFare.add(TotalServiceTax).add(managementFee).add(convenienceFee);
							commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setExtraCharge("0");
							commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
							commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setDiscount(roomDiscountInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setNetFare(netFare.setScale(0, BigDecimal.ROUND_UP).toString());
							PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(hotelOrderRow.getOrderReference());
							if(paymentTransaction!=null) 
								commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

							commonDsrTravelReportData.setTravelStatus(hotelOrderRow.getStatusAction());
							commonDsrTravelReportData.setPersonalBooking("No");
							commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
							RmConfigTripDetailsModel 	rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(hotelOrderRow.getOrderReference(),company.getCompanyid());
							commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
							commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
							commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
							commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
							commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
							commonDsrTravelReportData.setEmpCode("0");
							commonDsrTravelReports.add(commonDsrTravelReportData);

						 

					}
				}
			}
		}
		if(carOrderRowList!=null && carOrderRowList.size()>0){
			for(CarOrderRow carOrderRow:carOrderRowList){
				if(carOrderRow!=null ){
					CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
					commonDsrTravelReportData.setBkgRef(carOrderRow.getOrderId()); 
					commonDsrTravelReportData.setSystemInvoiceId(carOrderRow.getInvoiceNo()); 
					commonDsrTravelReportData.setBookingType(carOrderRow.getBookingMode());
					commonDsrTravelReportData.setAmendmentType(carOrderRow.getStatusAction());
					commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(carOrderRow.getCreatedAt()));
					commonDsrTravelReportData.setBookingDate(carOrderRow.getCarBookingDate()!=null?carOrderRow.getCarBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(carOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(carOrderRow.getUserId()));
					commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					commonDsrTravelReportData.setBillingEntity(company.getCompanyname());
					commonDsrTravelReportData.setBookerName(user.getUsername());
					commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode("-");
					commonDsrTravelReportData.setSupplierName(carOrderRow.getSupplierName());
					BigDecimal basePriceInBooking=null;
					BigDecimal apiPriceInBooking=null;
					BigDecimal taxesPriceInBooking=null;
					BigDecimal discountInBooking=null;

					BigDecimal grossFare=null;
					BigDecimal feeAmount=null;
					BigDecimal netFare=null;
					BigDecimal transactionFee = new BigDecimal("0");
					BigDecimal BaseServiceTax = new BigDecimal("0");
					BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
					if(carOrderRow.getBasePrice()!=null) 
						basePriceInBooking=carOrderRow.getBasePrice().multiply(carOrderRow.getBaseToBookingExchangeRate()).add(carOrderRow.getMarkUp());
					else
						basePriceInBooking=new BigDecimal("0");

					if(carOrderRow.getSupplierPrice()!=null)
						apiPriceInBooking=carOrderRow.getSupplierPrice().multiply(carOrderRow.getApiToBaseExchangeRate()).multiply(carOrderRow.getBaseToBookingExchangeRate()); 
					else
						apiPriceInBooking=new BigDecimal("0");


					BigDecimal driverAllowanceNight=carOrderRow.getDriverAllowanceNight()!=null?carOrderRow.getDriverAllowanceNight(): new BigDecimal(0);
					BigDecimal driverAllowanceDay=carOrderRow.getDriverAllowanceDay()!=null?carOrderRow.getDriverAllowanceDay(): new BigDecimal(0);
					BigDecimal tollOrParkingCharges=carOrderRow.getTollOrParkingCharges()!=null?carOrderRow.getTollOrParkingCharges(): new BigDecimal(0);
					String extraKM=carOrderRow.getExtraKM()!=null?carOrderRow.getExtraKM():new String("0");
					String extraHours=carOrderRow.getExtraHours()!=null?carOrderRow.getExtraHours():new String("0");
					BigDecimal otherTaxes  =carOrderRow.getOtherTaxes()!=null?carOrderRow.getOtherTaxes():new BigDecimal(0);
					taxesPriceInBooking=otherTaxes.add(driverAllowanceNight).add(driverAllowanceDay).add(tollOrParkingCharges).add(new BigDecimal(extraKM)).add(new BigDecimal(extraHours)); 
					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());

					commonDsrTravelReportData.setTraveller(carOrderRow.getOrderCustomer().getTitle()+" "+carOrderRow.getOrderCustomer().getFirstName()+" "+carOrderRow.getOrderCustomer().getLastName());
					commonDsrTravelReportData.setProductType("Other Products");
					commonDsrTravelReportData.setProductName("Car");
					String itineraryType=commonDsrTravelReportData.getProductName();
					commonDsrTravelReportData.setItineraryType(itineraryType);
					commonDsrTravelReportData.setProductCode("-");
					CarTravelRequestQuotation newObj=new CarTravelRequestDao().getCarQuotationDetails(carOrderRow.getId());
					if(newObj!=null) 
						commonDsrTravelReportData.setDescription("CarCompanyName:"+carOrderRow.getCarCompanyName()+",PickUp:"+newObj.getPickUp()+", DropOff:"+newObj.getDropOff()+", Remarks:"+newObj.getRemarks());
					else
						commonDsrTravelReportData.setDescription("-");	
					commonDsrTravelReportData.setAirlinePNRorProvBooking("-");
					commonDsrTravelReportData.setGDSPNR("-");
					commonDsrTravelReportData.setTicketNumorFinalBooking(carOrderRow.getConfirmationNumber());
					commonDsrTravelReportData.setFareType("-");
					commonDsrTravelReportData.setBookingRefundType("-");
					commonDsrTravelReportData.setFareBasis("-");
					commonDsrTravelReportData.setTotalNights("0");
					commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(carOrderRow.getTravelDate()));
					commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(carOrderRow.getTravelDate()));
					commonDsrTravelReportData.setJourneyType("NA");
					commonDsrTravelReportData.setMarkup(carOrderRow.getMarkUp().setScale(0, BigDecimal.ROUND_UP).toString());
					otherTaxesWithMarkup=taxesPriceInBooking;
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					grossFare=basePriceInBooking.add(otherTaxesWithMarkup);
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax("0");
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					transactionFee = feeAmount;
					convenienceFee=carOrderRow.getConvenienceFee()!=null?carOrderRow.getConvenienceFee():new BigDecimal(0);
					managementFee=carOrderRow.getManagementFee()!=null?carOrderRow.getManagementFee():new BigDecimal(0);
					BaseServiceTax = (carOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getBasicTax()!=null?carOrderRow.getCarOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					swachBharatCess = (carOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getSwatchBharathCess()!=null?carOrderRow.getCarOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					krishiKalyanCess = (carOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getKrishiKalyanCess()!=null?carOrderRow.getCarOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).add(swachBharatCess.setScale(0, BigDecimal.ROUND_UP)).add(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP));//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getTotalTax()!=null?carOrderRow.getCarOrderRowServiceTax().getTotalTax():new BigDecimal(0));

					netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setDiscount("0");
					commonDsrTravelReportData.setNetFare(netFare.setScale(0, BigDecimal.ROUND_UP).toString());
					PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(carOrderRow.getConfirmationNumber());
					if(paymentTransaction!=null) 
						commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

					else
						commonDsrTravelReportData.setModeOfPayment("-");
					commonDsrTravelReportData.setTravelStatus(carOrderRow.getStatusAction());
					commonDsrTravelReportData.setPersonalBooking("No");
					commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
					RmConfigTripDetailsModel 	rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(carOrderRow.getOrderId(),company.getCompanyid());
					commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
					commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
					commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
					commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
					commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
					commonDsrTravelReportData.setEmpCode("0");
					commonDsrTravelReports.add(commonDsrTravelReportData);

				}

			}
		}

		if(busOrderRowList!=null && busOrderRowList.size()>0){
			for(BusOrderRow busOrderRow:busOrderRowList){
				if(busOrderRow!=null ){
					CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
					commonDsrTravelReportData.setBkgRef(busOrderRow.getOrderId()); 
					commonDsrTravelReportData.setSystemInvoiceId(busOrderRow.getInvoiceNo()); 
					commonDsrTravelReportData.setBookingType(busOrderRow.getBookingMode());
					commonDsrTravelReportData.setAmendmentType(busOrderRow.getStatusAction());
					commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(busOrderRow.getCreatedAt()));
					commonDsrTravelReportData.setBookingDate(busOrderRow.getBusBookingDate()!=null?busOrderRow.getBusBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(busOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(busOrderRow.getUserId()));
					commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					commonDsrTravelReportData.setBillingEntity(company.getCompanyname());
					commonDsrTravelReportData.setBookerName(user.getUsername());
					commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode("-");
					commonDsrTravelReportData.setSupplierName("-");
					BigDecimal basePriceInBooking=null;
					BigDecimal apiPriceInBooking=null;
					BigDecimal taxesPriceInBooking=null;
					BigDecimal discountInBooking=null;
					BigDecimal grossFare=null;
					BigDecimal feeAmount=null;
					BigDecimal netFare=null;
					BigDecimal transactionFee = new BigDecimal("0");
					BigDecimal BaseServiceTax = new BigDecimal("0");
					BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
					if(busOrderRow.getBasePrice()!=null) 
						basePriceInBooking=busOrderRow.getBasePrice().multiply(busOrderRow.getBaseToBookingExchangeRate()).add(busOrderRow.getMarkUp());
					else
						basePriceInBooking=new BigDecimal("0");

					if(busOrderRow.getSupplierPrice()!=null)
						apiPriceInBooking=busOrderRow.getSupplierPrice().multiply(busOrderRow.getApiToBaseExchangeRate()).multiply(busOrderRow.getBaseToBookingExchangeRate()); 
					else
						apiPriceInBooking=new BigDecimal("0");

					if(busOrderRow.getOtherTaxes()!=null) 
						taxesPriceInBooking=busOrderRow.getOtherTaxes().multiply(busOrderRow.getApiToBaseExchangeRate()).multiply(busOrderRow.getBaseToBookingExchangeRate()); 
					else
						taxesPriceInBooking=new BigDecimal("0");
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					convenienceFee=busOrderRow.getConvenienceFee()!=null ?busOrderRow.getConvenienceFee():new BigDecimal(0);
					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTraveller(busOrderRow.getOrderCustomer().getTitle()+" "+busOrderRow.getOrderCustomer().getFirstName()+" "+busOrderRow.getOrderCustomer().getLastName());
					commonDsrTravelReportData.setProductType("Other Products");
					commonDsrTravelReportData.setProductName("Bus");
					String itineraryType=commonDsrTravelReportData.getProductName();
					commonDsrTravelReportData.setItineraryType(itineraryType);
					commonDsrTravelReportData.setProductCode("-");
					BusTravelRequestQuotation newObj=new BusTravelRequestDao().getBusQuotationDetails(busOrderRow.getId());
					if(newObj!=null) 
						commonDsrTravelReportData.setDescription("Bus Type:"+newObj.getBusType()+",Location:"+newObj.getLocation()+", PickUp:"+newObj.getPickUp()+", DropOff:"+newObj.getDropOff()+", Remarks:"+newObj.getRemarks());
					else
						commonDsrTravelReportData.setDescription("-");	
					commonDsrTravelReportData.setAirlinePNRorProvBooking("-");
					commonDsrTravelReportData.setGDSPNR("-");
					commonDsrTravelReportData.setTicketNumorFinalBooking(busOrderRow.getConfirmationNumber());
					commonDsrTravelReportData.setFareType("-");
					commonDsrTravelReportData.setBookingRefundType("-");
					commonDsrTravelReportData.setFareBasis("-");
					commonDsrTravelReportData.setTotalNights("0");
					commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(busOrderRow.getTravelDate()));
					commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(busOrderRow.getTravelDate()));
					commonDsrTravelReportData.setJourneyType("NA");
					BigDecimal markup=busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp():new BigDecimal("0");
					otherTaxesWithMarkup=taxesPriceInBooking;
					grossFare=basePriceInBooking.add(otherTaxesWithMarkup);
					commonDsrTravelReportData.setMarkup(markup.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax("0");
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					transactionFee = feeAmount;
					BaseServiceTax = (busOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getBasicTax()!=null?busOrderRow.getBusOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					swachBharatCess =(busOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getSwatchBharathCess()!=null?busOrderRow.getBusOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					krishiKalyanCess =(busOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getKrishiKalyanCess()!=null?busOrderRow.getBusOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).add(swachBharatCess.setScale(0, BigDecimal.ROUND_UP)).add(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP)); 

					managementFee=busOrderRow.getManagementFee()!=null?busOrderRow.getManagementFee():new BigDecimal(0);
					netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setDiscount("0");
					commonDsrTravelReportData.setNetFare(netFare.setScale(0, BigDecimal.ROUND_UP).toString());
					PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(busOrderRow.getConfirmationNumber());
					if(paymentTransaction!=null) 
						commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

					else
						commonDsrTravelReportData.setModeOfPayment("-");
					commonDsrTravelReportData.setTravelStatus(busOrderRow.getStatusAction());
					commonDsrTravelReportData.setPersonalBooking("No");
					commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
					RmConfigTripDetailsModel 	rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(busOrderRow.getOrderId(),company.getCompanyid());
					commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
					commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
					commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
					commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
					commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
					commonDsrTravelReportData.setEmpCode("0");
					commonDsrTravelReports.add(commonDsrTravelReportData);

				}

			}
		}

		if(trainOrderRowList!=null && trainOrderRowList.size()>0){
			for(TrainOrderRow trainOrderRow:trainOrderRowList){
				if(trainOrderRow!=null ){
					CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
					commonDsrTravelReportData.setBkgRef(trainOrderRow.getOrderId()); 
					commonDsrTravelReportData.setSystemInvoiceId(trainOrderRow.getInvoiceNo()); 
					commonDsrTravelReportData.setBookingType(trainOrderRow.getBookingMode());
					commonDsrTravelReportData.setAmendmentType(trainOrderRow.getStatusAction());
					commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(trainOrderRow.getCreatedAt()));
					commonDsrTravelReportData.setBookingDate(trainOrderRow.getTrainBookingDate()!=null?trainOrderRow.getTrainBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(trainOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(trainOrderRow.getUserId()));
					commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					commonDsrTravelReportData.setBillingEntity(company.getCompanyname());
					commonDsrTravelReportData.setBookerName(user.getUsername());
					commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode("-");
					commonDsrTravelReportData.setSupplierName("-");
					BigDecimal basePriceInBooking=null;
					BigDecimal apiPriceInBooking=null;
					BigDecimal taxesPriceInBooking=null;
					BigDecimal discountInBooking=null;
					BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
					BigDecimal grossFare=null;
					BigDecimal feeAmount=null;
					BigDecimal netFare=null;
					BigDecimal transactionFee = new BigDecimal("0");
					BigDecimal BaseServiceTax = new BigDecimal("0");
					if(trainOrderRow.getBasePrice()!=null) 
						basePriceInBooking=trainOrderRow.getBasePrice().multiply(trainOrderRow.getBaseToBookingExchangeRate()).add(trainOrderRow.getMarkUp());
					else
						basePriceInBooking=new BigDecimal("0");

					if(trainOrderRow.getSupplierPrice()!=null)
						apiPriceInBooking=trainOrderRow.getSupplierPrice().multiply(trainOrderRow.getApiToBaseExchangeRate()).multiply(trainOrderRow.getBaseToBookingExchangeRate()); 
					else
						apiPriceInBooking=new BigDecimal("0");

					if(trainOrderRow.getOtherTaxes()!=null) 
						taxesPriceInBooking=trainOrderRow.getOtherTaxes().multiply(trainOrderRow.getApiToBaseExchangeRate()).multiply(trainOrderRow.getBaseToBookingExchangeRate()); 
					else
						taxesPriceInBooking=new BigDecimal("0");
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					convenienceFee=trainOrderRow.getConvenienceFee()!=null ?trainOrderRow.getConvenienceFee():new BigDecimal(0);
					if(trainOrderRow.getTicketType()!=null && !trainOrderRow.getTicketType().equals("")){
						if(trainOrderRow.getTicketType().equalsIgnoreCase("tatkal"))
							managementFee=trainOrderRow.getManagementFeeTatkal()!=null ||!trainOrderRow.getManagementFeeTatkal().equals("")?trainOrderRow.getManagementFeeTatkal():new BigDecimal(0);
							if(trainOrderRow.getTicketType().equalsIgnoreCase("normal")) 
								managementFee=trainOrderRow.getManagementFee()!=null?trainOrderRow.getManagementFee():new BigDecimal(0);
					}
					else
						managementFee=new BigDecimal(0);

					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTraveller(trainOrderRow.getOrderCustomer().getTitle()+" "+trainOrderRow.getOrderCustomer().getFirstName()+" "+trainOrderRow.getOrderCustomer().getLastName());
					commonDsrTravelReportData.setProductType("Other Products");

					commonDsrTravelReportData.setProductName("Train");
					String itineraryType=commonDsrTravelReportData.getProductName();
					commonDsrTravelReportData.setItineraryType(itineraryType);
					commonDsrTravelReportData.setProductCode("-");
					commonDsrTravelReportData.setDescription(trainOrderRow.getRemarks());
					commonDsrTravelReportData.setAirlinePNRorProvBooking("-");
					commonDsrTravelReportData.setGDSPNR("-");
					commonDsrTravelReportData.setTicketNumorFinalBooking(trainOrderRow.getConfirmationNumber());
					commonDsrTravelReportData.setFareType("-");
					commonDsrTravelReportData.setBookingRefundType("-");
					commonDsrTravelReportData.setFareBasis("-");
					commonDsrTravelReportData.setTotalNights("0");
					commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(trainOrderRow.getTravelDate()));
					commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(trainOrderRow.getTravelDate()));
					commonDsrTravelReportData.setJourneyType("NA");
					commonDsrTravelReportData.setMarkup(trainOrderRow.getMarkUp().setScale(0, BigDecimal.ROUND_UP).toString());
					otherTaxesWithMarkup=taxesPriceInBooking;


					grossFare=basePriceInBooking.add(otherTaxesWithMarkup);
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax("0");
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					transactionFee = feeAmount;
					BaseServiceTax = managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getBasicTax()!=null?trainOrderRow.getTrainOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					swachBharatCess = managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getSwatchBharathCess()!=null?trainOrderRow.getTrainOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					krishiKalyanCess = managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getKrishiKalyanCess()!=null?trainOrderRow.getTrainOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).add(swachBharatCess.setScale(0, BigDecimal.ROUND_UP)).add(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP)); 

					netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setDiscount("0");
					commonDsrTravelReportData.setNetFare(netFare.setScale(0, BigDecimal.ROUND_UP).toString());
					PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(trainOrderRow.getConfirmationNumber());
					if(paymentTransaction!=null) 
						commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

					else
						commonDsrTravelReportData.setModeOfPayment("-");
					commonDsrTravelReportData.setTravelStatus(trainOrderRow.getStatusAction());
					commonDsrTravelReportData.setPersonalBooking("No");
					commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
					RmConfigTripDetailsModel 	rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(trainOrderRow.getOrderId(),company.getCompanyid());
					commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
					commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
					commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
					commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
					commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
					commonDsrTravelReportData.setEmpCode("0");
					commonDsrTravelReports.add(commonDsrTravelReportData);

				}

			}
		}




		if(visaOrderRowList!=null && visaOrderRowList.size()>0){
			for(VisaOrderRow visaOrderRow:visaOrderRowList){
				if(visaOrderRow!=null ){
					CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
					commonDsrTravelReportData.setBkgRef(visaOrderRow.getOrderId()); 
					commonDsrTravelReportData.setSystemInvoiceId(visaOrderRow.getInvoiceNo()); 
					commonDsrTravelReportData.setBookingType(visaOrderRow.getBookingMode());
					commonDsrTravelReportData.setAmendmentType(visaOrderRow.getStatusAction());
					commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(visaOrderRow.getCreatedAt()));
					commonDsrTravelReportData.setBookingDate(visaOrderRow.getVisaBookingDate()!=null?visaOrderRow.getVisaBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(visaOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(visaOrderRow.getUserId()));
					commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					commonDsrTravelReportData.setBillingEntity(company.getCompanyname());
					commonDsrTravelReportData.setBookerName(user.getUsername());
					commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode("-");
					commonDsrTravelReportData.setSupplierName("-");
					BigDecimal basePriceInBooking=null;
					BigDecimal apiPriceInBooking=null;
					BigDecimal taxesPriceInBooking=null;
					BigDecimal discountInBooking=null;
					BigDecimal grossFare=null;
					BigDecimal feeAmount=null;
					BigDecimal netFare=null;
					BigDecimal transactionFee = new BigDecimal("0");
					BigDecimal BaseServiceTax = new BigDecimal("0");
					BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
					if(visaOrderRow.getBasePrice()!=null) 
						basePriceInBooking=visaOrderRow.getBasePrice().multiply(visaOrderRow.getBaseToBookingExchangeRate()).add(visaOrderRow.getMarkUp());
					else
						basePriceInBooking=new BigDecimal("0");

					if(visaOrderRow.getSupplierPrice()!=null)
						apiPriceInBooking=visaOrderRow.getSupplierPrice().multiply(visaOrderRow.getApiToBaseExchangeRate()).multiply(visaOrderRow.getBaseToBookingExchangeRate()); 
					else
						apiPriceInBooking=new BigDecimal("0");

					if(visaOrderRow.getOtherTaxes()!=null) 
						taxesPriceInBooking=visaOrderRow.getOtherTaxes().multiply(visaOrderRow.getApiToBaseExchangeRate()).multiply(visaOrderRow.getBaseToBookingExchangeRate()).add(visaOrderRow.getVfsCharges()).add(visaOrderRow.getCourierCharges()); 
					else
						taxesPriceInBooking=new BigDecimal("0");

					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTraveller(visaOrderRow.getOrderCustomer().getTitle()+" "+visaOrderRow.getOrderCustomer().getFirstName()+" "+visaOrderRow.getOrderCustomer().getLastName());
					commonDsrTravelReportData.setProductType("Other Products");
					commonDsrTravelReportData.setProductName("Visa");
					String itineraryType=commonDsrTravelReportData.getProductName();
					commonDsrTravelReportData.setItineraryType(itineraryType);
					commonDsrTravelReportData.setProductCode("-");
					commonDsrTravelReportData.setDescription(visaOrderRow.getRemarks());
					commonDsrTravelReportData.setAirlinePNRorProvBooking("-");
					commonDsrTravelReportData.setGDSPNR("-");
					commonDsrTravelReportData.setTicketNumorFinalBooking(visaOrderRow.getConfirmationNumber());
					commonDsrTravelReportData.setFareType("-");
					commonDsrTravelReportData.setBookingRefundType("-");
					commonDsrTravelReportData.setFareBasis("-");
					commonDsrTravelReportData.setTotalNights("0");
					commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(visaOrderRow.getTravelDate()));
					commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(visaOrderRow.getTravelDate()));
					commonDsrTravelReportData.setJourneyType("NA");
					BigDecimal markup=visaOrderRow.getMarkUp()!=null?visaOrderRow.getMarkUp():new BigDecimal("0");
					otherTaxesWithMarkup=taxesPriceInBooking;


					grossFare=basePriceInBooking.add(otherTaxesWithMarkup.setScale(0, BigDecimal.ROUND_UP));
					commonDsrTravelReportData.setMarkup(markup.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax("0");
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					transactionFee = feeAmount;
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					convenienceFee=visaOrderRow.getConvenienceFee()!=null ?visaOrderRow.getConvenienceFee():new BigDecimal(0);
					managementFee=visaOrderRow.getManagementFee()!=null?visaOrderRow.getManagementFee():new BigDecimal(0);

					BaseServiceTax = managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getBasicTax()!=null?visaOrderRow.getVisaOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					swachBharatCess = managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getSwatchBharathCess()!=null?visaOrderRow.getVisaOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					krishiKalyanCess = managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getKrishiKalyanCess()!=null?visaOrderRow.getVisaOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).setScale(0, BigDecimal.ROUND_UP).add(swachBharatCess.setScale(0, BigDecimal.ROUND_UP)).add(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP)); 

					netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setDiscount("0");
					commonDsrTravelReportData.setNetFare(netFare.setScale(0, BigDecimal.ROUND_UP).toString());
					PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(visaOrderRow.getConfirmationNumber());
					if(paymentTransaction!=null) 
						commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

					else
						commonDsrTravelReportData.setModeOfPayment("-");
					commonDsrTravelReportData.setTravelStatus(visaOrderRow.getStatusAction());
					commonDsrTravelReportData.setPersonalBooking("No");
					commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
					RmConfigTripDetailsModel 	rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(visaOrderRow.getOrderId(),company.getCompanyid());
					commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
					commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
					commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
					commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
					commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
					commonDsrTravelReportData.setEmpCode("0");
					commonDsrTravelReports.add(commonDsrTravelReportData);

				}

			}
		}

		if(insuranceOrderRowList!=null && insuranceOrderRowList.size()>0){
			for(InsuranceOrderRow insuranceOrderRow:insuranceOrderRowList){
				if(insuranceOrderRow!=null ){
					CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
					commonDsrTravelReportData.setBkgRef(insuranceOrderRow.getOrderId()); 
					commonDsrTravelReportData.setSystemInvoiceId(insuranceOrderRow.getInvoiceNo()); 
					commonDsrTravelReportData.setBookingType(insuranceOrderRow.getBookingMode());
					commonDsrTravelReportData.setAmendmentType(insuranceOrderRow.getStatusAction());
					commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getCreatedAt()));
					commonDsrTravelReportData.setBookingDate(insuranceOrderRow.getInsuranceBookingDate()!=null?insuranceOrderRow.getInsuranceBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(insuranceOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(insuranceOrderRow.getUserId()));
					commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					commonDsrTravelReportData.setBillingEntity(company.getCompanyname());
					commonDsrTravelReportData.setBookerName(user.getUsername());
					commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode("-");
					commonDsrTravelReportData.setSupplierName("-");
					BigDecimal basePriceInBooking=null;
					BigDecimal apiPriceInBooking=null;
					BigDecimal taxesPriceInBooking=null;
					BigDecimal discountInBooking=null;
					BigDecimal grossFare=null;
					BigDecimal feeAmount=null;
					BigDecimal netFare=null;
					BigDecimal transactionFee = new BigDecimal("0");
					BigDecimal BaseServiceTax = new BigDecimal("0");
					BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
					if(insuranceOrderRow.getBasePrice()!=null) 
						basePriceInBooking=insuranceOrderRow.getBasePrice().multiply(insuranceOrderRow.getBaseToBookingExchangeRate()).add(insuranceOrderRow.getMarkUpamount());
					else
						basePriceInBooking=new BigDecimal("0");

					if(insuranceOrderRow.getSupplierPrice()!=null)
						apiPriceInBooking=insuranceOrderRow.getSupplierPrice().multiply(insuranceOrderRow.getApiToBaseExchangeRate()).multiply(insuranceOrderRow.getBaseToBookingExchangeRate()); 
					else
						apiPriceInBooking=new BigDecimal("0");

					if(insuranceOrderRow.getOtherTaxes()!=null) 
						taxesPriceInBooking=insuranceOrderRow.getOtherTaxes().multiply(insuranceOrderRow.getApiToBaseExchangeRate()).multiply(insuranceOrderRow.getBaseToBookingExchangeRate()); 
					else
						taxesPriceInBooking=new BigDecimal("0");

					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTraveller(insuranceOrderRow.getOrderCustomer().getTitle()+" "+insuranceOrderRow.getOrderCustomer().getFirstName()+" "+insuranceOrderRow.getOrderCustomer().getLastName());
					commonDsrTravelReportData.setProductType("Other Products");
					commonDsrTravelReportData.setProductName("Insurance");
					String itineraryType=commonDsrTravelReportData.getProductName();
					commonDsrTravelReportData.setItineraryType(itineraryType);
					commonDsrTravelReportData.setProductCode("-");
					commonDsrTravelReportData.setDescription(insuranceOrderRow.getRemarks());
					commonDsrTravelReportData.setAirlinePNRorProvBooking("-");
					commonDsrTravelReportData.setGDSPNR("-");
					commonDsrTravelReportData.setTicketNumorFinalBooking(insuranceOrderRow.getConfirmationNumber());
					commonDsrTravelReportData.setFareType("-");
					commonDsrTravelReportData.setBookingRefundType("-");
					commonDsrTravelReportData.setFareBasis("-");
					commonDsrTravelReportData.setTotalNights("0");
					commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getTravelDate()));
					commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getTravelDate()));
					commonDsrTravelReportData.setJourneyType("NA");
					BigDecimal markup=insuranceOrderRow.getMarkUpamount()!=null?insuranceOrderRow.getMarkUpamount():new BigDecimal("0");
					otherTaxesWithMarkup=taxesPriceInBooking;
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					convenienceFee=insuranceOrderRow.getConvenienceFee()!=null ?insuranceOrderRow.getConvenienceFee():new BigDecimal(0);
					managementFee=insuranceOrderRow.getManagementFee()!=null?insuranceOrderRow.getManagementFee():new BigDecimal(0);

					grossFare=basePriceInBooking.add(otherTaxesWithMarkup);
					commonDsrTravelReportData.setMarkup(markup.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax("0");
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					transactionFee = feeAmount;
					BaseServiceTax =insuranceOrderRow.getServiceTax()!=null?insuranceOrderRow.getServiceTax():new BigDecimal(0); ;//basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getBasicTax()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					//swachBharatCess = basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getSwatchBharathCess()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					//krishiKalyanCess = basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getKrishiKalyanCess()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).add(swachBharatCess.setScale(0, BigDecimal.ROUND_UP)).add(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP));//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getTotalTax()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getTotalTax():new BigDecimal(0));

					netFare = grossFare.add(TotalServiceTax.setScale(0, BigDecimal.ROUND_UP)).add(convenienceFee.setScale(0, BigDecimal.ROUND_UP)).add(managementFee.setScale(0, BigDecimal.ROUND_UP));
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(0, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setDiscount("0");
					commonDsrTravelReportData.setNetFare(netFare.setScale(0, BigDecimal.ROUND_UP).toString());
					PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(insuranceOrderRow.getConfirmationNumber());
					if(paymentTransaction!=null) 
						commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

					else
						commonDsrTravelReportData.setModeOfPayment("-");
					commonDsrTravelReportData.setTravelStatus(insuranceOrderRow.getStatusAction());
					commonDsrTravelReportData.setPersonalBooking("No");
					commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
					RmConfigTripDetailsModel 	rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(insuranceOrderRow.getOrderId(),company.getCompanyid());
					commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
					commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
					commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
					commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
					commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
					commonDsrTravelReportData.setEmpCode("0");
					commonDsrTravelReports.add(commonDsrTravelReportData);

				}

			}
		}
		return commonDsrTravelReports;
	}


	public  CommonDsrPage getCompanyFlightReports(CommonDsrPage flightReportPage){
		List<ReportData>  reportData_list=new ArrayList<ReportData>();
		int availablePages = 0;
		int availableItems = 0;
		Session session = null;
		try{
			//2016-06-28
			SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			ProjectionList prjectionList = Projections.projectionList();

			Conjunction conjunctionFlightOrderCustomer = Restrictions.conjunction();
			// To get total row count.
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters flightReportFilter = flightReportPage.getCommonDsrFilters();
				logger.info("####ReportType-------------"+flightReportFilter.getReportType());
				logger.info("####getAirlineName-------------"+flightReportFilter.getTravelReportType());
				/* Add multiple condition separated by AND clause within brackets. */
				List<String> companyIdList  = new ArrayList<String>();
				companyIdList = getCompanyIdList(flightReportFilter.getLoginCompany(), flightReportFilter.getReportType(), flightReportFilter.getCompanyName());
				logger.info("companyIdList--------------"+companyIdList.size());
				//if(companyIdList != null && companyIdList.size() > 0)
				//{
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
					return flightReportPage;
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				logger.info("companyIdList--------------"+companyIdList);

				/*if(flightReportFilter.getTravelReportType().equals("Airline") )
				{
					 prjectionList.add(Projections.property("airline"));
				}
				 */
				//criteria.setProjection(prjectionList);
				criteria.add(reportConjunction);
				//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	

			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);

			List<FlightOrderRow> list =null;
			if(rowCount>0)
			{
				if(flightReportPage.getMaxItems()==Page.ALL_ITEMS){
					logger.info(":::: retriving all items for excel export------");

					criteria = session.createCriteria(FlightOrderRow.class);
					//criteria.setProjection(prjectionList);
					criteria.add(reportConjunction);
					//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info(":::: retriving all items for excel export-----list-"+list);
					logger.info("rowCountForExcel list.size ------"+((list != null)?0:list.size()));	
					flightReportPage.setAvailableItems(list.size());
					flightReportPage.setAvailablePages(1);

				}
				else{
					if(flightReportPage.isPagination())
					{
						availableItems = rowCount.intValue();
						availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
						flightReportPage.setAvailableItems(availableItems);
						flightReportPage.setAvailablePages(availablePages);
					} 
					//Retrive report with pagination .......

					int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
					int itemIndex = pageIndexDb * flightReportPage.getMaxItems();
					logger.info("setFirstResult-------"+itemIndex);
					if(itemIndex <= rowCount)
					{
						logger.info("setFirstResult-------"+itemIndex);
						logger.info("MaxResults-------"+flightReportPage.getMaxItems());
						criteria = session.createCriteria(FlightOrderRow.class);
						//criteria.setProjection(prjectionList);
						criteria.add(reportConjunction);
						//criteria.createCriteria("flightCustomer").add(conjunctionFlightOrderCustomer);	
						criteria.setFirstResult(itemIndex);
						criteria.setMaxResults(flightReportPage.getMaxItems());
						criteria.addOrder(Order.desc("id"));
						list = criteria.list();
						logger.info("Reports size------"+list.size());	

					}
				}
				if(list!=null && list.size()>0)
				{
					for (FlightOrderRow flightOrderRow :list)
					{
						ReportData reportData=new ReportData();

						if(flightReportPage.getHotelandFlightDisReportProperty().isGuestName()){
							reportData.setGuestName(flightOrderRow.getFlightCustomer().getFirstName()+" "+flightOrderRow.getFlightCustomer().getLastName());
						}
						if(flightReportPage.getHotelandFlightDisReportProperty().isPnr()){
							reportData.setPnr(flightOrderRow.getPnr());
						}
						if(flightReportPage.getHotelandFlightDisReportProperty().isOrderRef()){
							reportData.setOrderId(flightOrderRow.getOrderId());
						}
						if(flightReportPage.getHotelandFlightDisReportProperty().isAirline()){
							reportData.setAirline(flightOrderRow.getAirline());
						}
						StringBuilder descode = new StringBuilder();
						for(int i=0;i<flightOrderRow.getFlightOrderTripDetails().size();i++){
							FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
							if(i == flightOrderRow.getFlightOrderTripDetails().size()-1)
								descode.append(trips.getDestinationCode());
							else
								descode.append(trips.getDestinationCode() + "/");
						}
						StringBuilder srcCode = new StringBuilder();
						for(int i=0;i<flightOrderRow.getFlightOrderTripDetails().size();i++){
							FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
							if(i == flightOrderRow.getFlightOrderTripDetails().size()-1)
								srcCode.append(trips.getOriginCode());
							else
								srcCode.append(trips.getOriginCode() + "/");
						}
						reportData.setOrigin(srcCode.toString());
						reportData.setRoute(descode.toString());
						reportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
						if(flightReportPage.getHotelandFlightDisReportProperty().isFinalPrice()){
							reportData.setFinalPrice(flightOrderRow.getFinalPrice().setScale(2,BigDecimal.ROUND_UP));
							FlightOrderRowMarkup flightOrderRowMarkup=  getCompanyMarkup(flightOrderRow.getCompanyId(), flightOrderRow.getId());
							if(flightOrderRowMarkup!=null) 
								reportData.setMarkUp(flightOrderRowMarkup.getMarkUp().multiply(new BigDecimal(flightOrderRow.getPassengerCount())).setScale(0, BigDecimal.ROUND_HALF_UP));
							else 
								reportData.setMarkUp(new BigDecimal("0"));	
							BigDecimal netPaybleAmount=flightOrderRow.getFinalPrice().subtract(reportData.getMarkUp());
							reportData.setNetAmnt(netPaybleAmount.setScale(2,BigDecimal.ROUND_UP));

						}

						if(flightReportPage.getHotelandFlightDisReportProperty().isInvoiceNo())
							reportData.setInvoiceNo(flightOrderRow.getInvoiceNo());

						if(flightReportPage.getHotelandFlightDisReportProperty().isStatusAction())
							reportData.setStatusAction(flightOrderRow.getStatusAction());

						if(flightReportPage.getHotelandFlightDisReportProperty().isPaymentStatus())
							reportData.setPaymentStatus(flightOrderRow.getPaymentStatus());

						if(flightReportPage.getHotelandFlightDisReportProperty().isAgency()){
							if(flightOrderRow.getCreatedBy()!=null){
								reportData.setCreatedBy(flightOrderRow.getCreatedBy().replace("+", " "));	
							}
							else{
								reportData.setCreatedBy(flightOrderRow.getCreatedBy());	
							}

						} 
						reportData.setDepartureDate(DateConversion.getBluestarDate(flightOrderRow.getDepartureDate()));


						reportData_list.add(reportData);
					}					
					flightReportPage.setItems(reportData_list);

				}
				else
				{
					//current page is having empty items..
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setItems(new ArrayList<ReportData>());
				}

			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setItems(new ArrayList<ReportData>());
			}


		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return flightReportPage;
	}

 

	public  List<FlightOrderRow> getCommonDsrFlightReports(CommonDsrPage flightReportPage){
		Session session = null;
		List<FlightOrderRow> list=null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(FlightOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters commonDsrFilters = flightReportPage.getCommonDsrFilters();
				List<String>  companyIdList =null;

				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") ||commonDsrFilters.getTravelType().equalsIgnoreCase("F") && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All")||commonDsrFilters.getTravelType().equalsIgnoreCase("F")&& commonDsrFilters.getTravelReportType().equalsIgnoreCase("All")){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}

				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setFlightOrderRowList(new ArrayList<FlightOrderRow>());
					return flightReportPage.getFlightOrderRowList();
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(commonDsrFilters.getPnr()!=null &&  !commonDsrFilters.getPnr().equals("")){
					reportConjunction.add(Restrictions.eq("pnr",commonDsrFilters.getPnr()));
				}
				if(commonDsrFilters.getAirline()!=null &&  !commonDsrFilters.getAirline().equals("ALL")){
					reportConjunction.add(Restrictions.eq("airline",commonDsrFilters.getAirline()));
				}
				if((commonDsrFilters.getOrigin()!=null && !commonDsrFilters.getOrigin().equals(""))  &&  (commonDsrFilters.getDestination()!=null && !commonDsrFilters.getDestination().equals("")))
				{
					reportConjunction.add(Restrictions.eq("origin",commonDsrFilters.getOrigin()));
					reportConjunction.add(Restrictions.eq("destination",commonDsrFilters.getDestination()));

				} 
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("bookingDate",DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getBookingDate())));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
				reportConjunction.add(Restrictions.eq("departureDate",DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getTravelDate())));
				}
				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(commonDsrFilters.getFromDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));


					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}

				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(flightReportPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
					flightReportPage.setAvailableItems(availableItems);
					flightReportPage.setAvailablePages(availablePages);
				} 
				//Retrive report with pagination .......

				int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

				//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

				logger.info("setFirstResult-------"+itemIndex);

				if(itemIndex <= rowCount)
				{
					logger.info("setFirstResult-------"+itemIndex);
					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteria = session.createCriteria(FlightOrderRow.class);
					criteria.add(reportConjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(flightReportPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info("Reports size------"+list.size());	
				}


				if(list!=null && list.size()>0){
				}
				else
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setFlightOrderRowList(new ArrayList<FlightOrderRow>());
				}


			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setFlightOrderRowList(new ArrayList<FlightOrderRow>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	public List<HotelOrderRow> getCommonDsrHotelReports(CommonDsrPage flightReportPage){
		Session session = null;
		List<HotelOrderRow> list=null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(HotelOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters commonDsrFilters = flightReportPage.getCommonDsrFilters();
				List<String>  companyIdList =null;
				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("H") && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All")  && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") || commonDsrFilters.getTravelType().equalsIgnoreCase("H")  && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All")){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}
				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setFlightOrderRowList(new ArrayList<FlightOrderRow>());
					return flightReportPage.getHotelOrderRowList();
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(!commonDsrFilters.getOrderReference().equals("")){
					reportConjunction.add(Restrictions.eq("orderReference",commonDsrFilters.getOrderReference()));
				}
				if(commonDsrFilters.getCountry()!=null && !commonDsrFilters.getCountry().equalsIgnoreCase("ALL") ){
					criteria.createCriteria("hotelOrderHotelData").add(Restrictions.eq("country",commonDsrFilters.getCountry()));
				}
				if(!commonDsrFilters.getCity().equals("")){
					criteria.createCriteria("hotelOrderHotelData").add(Restrictions.eq("city",commonDsrFilters.getCity()));
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("bookingDate",DateConversion.convertDDMMYYtoYYMMDD(commonDsrFilters.getBookingDate())));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("checkInDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				
				if(commonDsrFilters.getFromDate()!=null && commonDsrFilters.getToDate()!=null)
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(commonDsrFilters.getFromDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));


					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(flightReportPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
					flightReportPage.setAvailableItems(availableItems);
					flightReportPage.setAvailablePages(availablePages);
				} 
				//Retrive report with pagination .......

				int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

				//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

				logger.info("setFirstResult-------"+itemIndex);

				if(itemIndex <= rowCount)
				{
					logger.info("setFirstResult-------"+itemIndex);
					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteria = session.createCriteria(HotelOrderRow.class);
					criteria.add(reportConjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(flightReportPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info("Reports size------"+list.size());	
				}


				if(list!=null && list.size()>0){
				}
				else
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setHotelOrderRowList(new ArrayList<HotelOrderRow>());
				}


			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setHotelOrderRowList(new ArrayList<HotelOrderRow>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}
	public List<CarOrderRow> getCommonDsrCarReports(CommonDsrPage flightReportPage){
		Session session = null;
		List<CarOrderRow> list=null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CarOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters commonDsrFilters = flightReportPage.getCommonDsrFilters();
				List<String>  companyIdList =null;

				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") ||commonDsrFilters.getTravelType().equalsIgnoreCase("C") && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All")||commonDsrFilters.getTravelType().equalsIgnoreCase("C")&& commonDsrFilters.getTravelReportType().equalsIgnoreCase("All")){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}

				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setCarOrderRowList(new ArrayList<CarOrderRow>());
					return flightReportPage.getCarOrderRowList();
				}

				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("carBookingDate",commonDsrFilters.getBookingDate()));
				}
				
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				
				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}
				 
				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(commonDsrFilters.getFromDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));


					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(flightReportPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
					flightReportPage.setAvailableItems(availableItems);
					flightReportPage.setAvailablePages(availablePages);
				} 
				//Retrive report with pagination .......

				int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

				//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

				logger.info("setFirstResult-------"+itemIndex);

				if(itemIndex <= rowCount)
				{
					logger.info("setFirstResult-------"+itemIndex);
					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteria = session.createCriteria(CarOrderRow.class);
					criteria.add(reportConjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(flightReportPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info("Reports size------"+list.size());	
				}


				if(list!=null && list.size()>0){
				}
				else
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setCarOrderRowList(new ArrayList<CarOrderRow>());
				}


			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setCarOrderRowList(new ArrayList<CarOrderRow>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	public List<TrainOrderRow> getCommonDsrTrainReports(CommonDsrPage flightReportPage){
		Session session = null;
		List<TrainOrderRow> list=null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(TrainOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters commonDsrFilters = flightReportPage.getCommonDsrFilters();
				List<String>  companyIdList =null;

				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") ||commonDsrFilters.getTravelType().equalsIgnoreCase("T") && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All")||commonDsrFilters.getTravelType().equalsIgnoreCase("T")&& commonDsrFilters.getTravelReportType().equalsIgnoreCase("All")){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}

				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setTrainOrderRowList(new ArrayList<TrainOrderRow>());
					return flightReportPage.getTrainOrderRowList();
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("trainBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}
				 
				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(commonDsrFilters.getFromDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));


					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(flightReportPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
					flightReportPage.setAvailableItems(availableItems);
					flightReportPage.setAvailablePages(availablePages);
				} 
				//Retrive report with pagination .......

				int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

				//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

				logger.info("setFirstResult-------"+itemIndex);

				if(itemIndex <= rowCount)
				{
					logger.info("setFirstResult-------"+itemIndex);
					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteria = session.createCriteria(TrainOrderRow.class);
					criteria.add(reportConjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(flightReportPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info("Reports size------"+list.size());	
				}


				if(list!=null && list.size()>0){
				}
				else
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setTrainOrderRowList(new ArrayList<TrainOrderRow>());
				}


			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setTrainOrderRowList(new ArrayList<TrainOrderRow>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	public List<BusOrderRow> getCommonDsrBusReports(CommonDsrPage flightReportPage){
		Session session = null;
		List<BusOrderRow> list=null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(BusOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters commonDsrFilters = flightReportPage.getCommonDsrFilters();
				List<String>  companyIdList =null;

				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") ||commonDsrFilters.getTravelType().equalsIgnoreCase("B") && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All")||commonDsrFilters.getTravelType().equalsIgnoreCase("B")&& commonDsrFilters.getTravelReportType().equalsIgnoreCase("All")){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}

				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setBusOrderRowList(new ArrayList<BusOrderRow>());
					return flightReportPage.getBusOrderRowList();
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("busBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}
				 
				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(commonDsrFilters.getFromDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));


					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(flightReportPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
					flightReportPage.setAvailableItems(availableItems);
					flightReportPage.setAvailablePages(availablePages);
				} 
				//Retrive report with pagination .......

				int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

				//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

				logger.info("setFirstResult-------"+itemIndex);

				if(itemIndex <= rowCount)
				{
					logger.info("setFirstResult-------"+itemIndex);
					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteria = session.createCriteria(BusOrderRow.class);
					criteria.add(reportConjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(flightReportPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info("Reports size------"+list.size());	
				}


				if(list!=null && list.size()>0){
				}
				else
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setBusOrderRowList(new ArrayList<BusOrderRow>());
				}


			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setBusOrderRowList(new ArrayList<BusOrderRow>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	public List<VisaOrderRow> getCommonDsrVisaReports(CommonDsrPage flightReportPage){
		Session session = null;
		List<VisaOrderRow> list=null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(VisaOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters commonDsrFilters = flightReportPage.getCommonDsrFilters();
				List<String>  companyIdList =null;

				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") ||commonDsrFilters.getTravelType().equalsIgnoreCase("V") && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All")||commonDsrFilters.getTravelType().equalsIgnoreCase("V")&& commonDsrFilters.getTravelReportType().equalsIgnoreCase("All")){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}

				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setVisaOrderRowList(new ArrayList<VisaOrderRow>());
					return flightReportPage.getVisaOrderRowList();
				}

				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("visaBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}
				 
				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(commonDsrFilters.getFromDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));


					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(flightReportPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
					flightReportPage.setAvailableItems(availableItems);
					flightReportPage.setAvailablePages(availablePages);
				} 
				//Retrive report with pagination .......

				int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

				//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

				logger.info("setFirstResult-------"+itemIndex);

				if(itemIndex <= rowCount)
				{
					logger.info("setFirstResult-------"+itemIndex);
					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteria = session.createCriteria(VisaOrderRow.class);
					criteria.add(reportConjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(flightReportPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info("Reports size------"+list.size());	
				}


				if(list!=null && list.size()>0){
				}
				else
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setVisaOrderRowList(new ArrayList<VisaOrderRow>());
				}


			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setVisaOrderRowList(new ArrayList<VisaOrderRow>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}

	public List<InsuranceOrderRow> getCommonDsrInsuranceReports(CommonDsrPage flightReportPage){
		Session session = null;
		List<InsuranceOrderRow> list=null;
		int availablePages = 0;
		int availableItems = 0;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(InsuranceOrderRow.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			if(flightReportPage!=null && flightReportPage.isFilterEnabled())
			{
				CommonDsrFilters commonDsrFilters = flightReportPage.getCommonDsrFilters();
				List<String>  companyIdList =null;

				if(commonDsrFilters.getTravelType().equalsIgnoreCase("All") ||commonDsrFilters.getTravelType().equalsIgnoreCase("I") && commonDsrFilters.getTravelReportType().equalsIgnoreCase("All") && commonDsrFilters.getCompanyUserId()!=null && !commonDsrFilters.getCompanyUserId().equals("")){
					Company selectedCompany=new Company();
					if(commonDsrFilters.getCompanyUserId().equals("")) 
						companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
					else{
						selectedCompany.setCompany_userid(commonDsrFilters.getCompanyUserId());
						companyIdList = getCompanyIdList(selectedCompany,1);
					}

				}
				else if(commonDsrFilters.getTravelType().equalsIgnoreCase("All")||commonDsrFilters.getTravelType().equalsIgnoreCase("I")&& commonDsrFilters.getTravelReportType().equalsIgnoreCase("All")){
					companyIdList = getCompanyIdList(commonDsrFilters.getLoginCompany(),0);
				}

				if(companyIdList == null || companyIdList.size() <= 0)
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setInsuranceOrderRowList(new ArrayList<InsuranceOrderRow>());
					return flightReportPage.getInsuranceOrderRowList();
				}

				if(commonDsrFilters.getBookingStatus()!=null && !commonDsrFilters.getBookingStatus().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("statusAction",commonDsrFilters.getBookingStatus()));
				}
				if(commonDsrFilters.getBookingMode()!=null && !commonDsrFilters.getBookingMode().equalsIgnoreCase("All")){
					reportConjunction.add(Restrictions.eq("bookingMode",commonDsrFilters.getBookingMode()));
				}
				if(!commonDsrFilters.getBookingDate().equals("")){
					reportConjunction.add(Restrictions.eq("insuranceBookingDate",commonDsrFilters.getBookingDate()));
				}
				if(!commonDsrFilters.getTravelDate().equals("")){
					reportConjunction.add(Restrictions.eq("travelDate",DateConversion.StringToDate(commonDsrFilters.getTravelDate())));
				}
				if(!commonDsrFilters.getConfirmationNumber().equals("")){
					reportConjunction.add(Restrictions.eq("confirmationNumber",commonDsrFilters.getConfirmationNumber()));
				}
				 
				if(!commonDsrFilters.getFromDate().equals("") && !commonDsrFilters.getToDate().equals(""))
				{
					//2016-06-28
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
					DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
					try{
						Date date = originalFormat.parse(commonDsrFilters.getFromDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.SECONDS.toMillis(1));
						reportConjunction.add(Restrictions.ge("createdAt", date));

					}catch(Exception ex)
					{
						logger.info("##########date min format exception---"+ex.getMessage());

					}
					try{
						Date date = originalFormat.parse(commonDsrFilters.getToDate());
						String formattedDate = targetFormat.format(date); 
						date = targetFormat.parse(formattedDate);
						date = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
						reportConjunction.add(Restrictions.lt("createdAt", date));


					}catch(Exception ex)
					{
						logger.info("##########date max format exception---"+ex.getMessage());
					}
				}
				reportConjunction.add(Restrictions.in("companyId",companyIdList));
				criteria.add(reportConjunction);
			}
			Long rowCount= (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
			logger.info("rowCount------"+rowCount);
			if(rowCount>0)
			{
				if(flightReportPage.isPagination())
				{
					availableItems = rowCount.intValue();
					availablePages = (availableItems % flightReportPage.getMaxItems() == 0)?(availableItems / flightReportPage.getMaxItems()):((availableItems / flightReportPage.getMaxItems()) + 1);
					flightReportPage.setAvailableItems(availableItems);
					flightReportPage.setAvailablePages(availablePages);
				} 
				//Retrive report with pagination .......

				int pageIndexDb = (flightReportPage.getCurrentPageIndex() > 1)?flightReportPage.getCurrentPageIndex() -1 : 0;
				int itemIndex = pageIndexDb * flightReportPage.getMaxItems();

				//int itemIndex = flightReportPage.getCurrentPageIndex() * flightReportPage.getMaxItems();

				logger.info("setFirstResult-------"+itemIndex);

				if(itemIndex <= rowCount)
				{
					logger.info("setFirstResult-------"+itemIndex);
					logger.info("MaxResults-------"+flightReportPage.getMaxItems());
					criteria = session.createCriteria(InsuranceOrderRow.class);
					criteria.add(reportConjunction);
					criteria.setFirstResult(itemIndex);
					criteria.setMaxResults(flightReportPage.getMaxItems());
					criteria.addOrder(Order.desc("id"));
					list = criteria.list();
					logger.info("Reports size------"+list.size());	
				}


				if(list!=null && list.size()>0){
				}
				else
				{
					flightReportPage.setAvailableItems(0);					
					flightReportPage.setInsuranceOrderRowList(new ArrayList<InsuranceOrderRow>());
				}


			}
			else
			{
				flightReportPage.setAvailableItems(0);
				flightReportPage.setAvailablePages(0);
				flightReportPage.setInsuranceOrderRowList(new ArrayList<InsuranceOrderRow>());
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return list;
	}




	public  List<String> getCompanyIdList(Company company, int reportType)
	{
		List<String> companyIdList = new ArrayList<String>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			// To get total row count.
			List<Company> list = null;
			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
			case 0:	
				companyIdList.add(String.valueOf(company.getCompanyid()));
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				criteria.add(reportConjunction);
				list = criteria.list();
				break;
			}				
			criteria.add(reportConjunction);
			logger.error("--------------probable Company list -----------------"+list);
			if(list!=null && list.size()>0)
			{
				logger.error("--------------probable Company list size-----------------"+list.size());
				for (Company companyChild :list)
				{
					companyIdList.add(String.valueOf(companyChild.getCompanyid()));
				}						
			}		
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return companyIdList;
	}



	public  List<String> getCompanyIdList(Company company, int reportType, String companyPreferable)
	{
		List<String> companyIdList = new ArrayList<String>();
		Session session = null;
		try{			
			session = HibernateUtil.getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Company.class);
			Conjunction reportConjunction = Restrictions.conjunction();
			// To get total row count.
			List<Company> list = null;
			logger.info("##########company name preferable---"+companyPreferable);

			switch (reportType){
			case FlightReportFilter.REPORTS_MINE:
				reportConjunction.add(Restrictions.eq("company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}

				criteria.add(reportConjunction);
				list = criteria.list();
				break;			
			case FlightReportFilter.REPORTS_ALL:
				if(!company.getCompanyRole().isAgent() && !company.getCompanyRole().isDistributor()){
					reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));	
				}
				else{
					reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				}
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;	

			case FlightReportFilter.REPORTS_MY_AFFILIATES:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;

			case FlightReportFilter.REPORTS_MY_AGENCIES:
				logger.info("reportType---------"+reportType);


				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();

				break;
			case FlightReportFilter.REPORTS_MY_DISTRIBUTORS:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				break;
			case FlightReportFilter.REPORTS_MY_CORPORATES:
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isCorporate",true));
				list = criteria.list();
				break;
			case FlightReportFilter.REPORTS_ALL_AFFILIATES:
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;

			case FlightReportFilter.REPORTS_ALL_AGENCIES:
				logger.info("reportType---------"+reportType);

				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isAgent",true));
				list = criteria.list();
				break;
			case FlightReportFilter.REPORTS_ALL_DISTRIBUTORS:
				//For direct distributors...
				reportConjunction.add(Restrictions.eq("super_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				criteria.createCriteria("companyRole").add(Restrictions.eq("isDistributor",true));
				list = criteria.list();
				//reportConjunction.add(Restrictions.eq("companyRole.isDistributor",true));
				break;


			case FlightReportFilter.ORDERS_ALL:
				reportConjunction.add(Restrictions.eq("companyid",company.getCompanyid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;		


			default:	
				companyIdList.add(String.valueOf(company.getCompanyid()));
				reportConjunction.add(Restrictions.eq("parent_company_userid",company.getCompany_userid()));
				if((companyPreferable != null && companyPreferable.length()  > 0 ))
				{
					Disjunction reportDisjunction = Restrictions.disjunction();
					reportDisjunction.add(Restrictions.like("Username",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("Companyname",companyPreferable, MatchMode.ANYWHERE));
					reportDisjunction.add(Restrictions.like("company_userid",companyPreferable, MatchMode.ANYWHERE));
					reportConjunction.add(reportDisjunction);
				}
				criteria.add(reportConjunction);
				list = criteria.list();
				break;
			}				
			criteria.add(reportConjunction);
			logger.error("--------------probable Company list -----------------"+list);
			if(list!=null && list.size()>0)
			{
				logger.error("--------------probable Company list size-----------------"+list.size());
				for (Company companyChild :list)
				{
					companyIdList.add(String.valueOf(companyChild.getCompanyid()));
				}						
			}		
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return companyIdList;
	}
	public User  getSalesPersonName(String userId){
		User user = null;
		Session session=null;
		String sql = "from SalesLeadGeneration frm where frm.childUserId in(:childUserId)";
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(sql);
			query.setParameter("childUserId", Integer.parseInt(userId));
			SalesLeadGeneration salesLeadGeneration=(SalesLeadGeneration) query.uniqueResult();
			if(salesLeadGeneration!=null && salesLeadGeneration.getUser()!=null){
				user=salesLeadGeneration.getUser();
			}
			else{
				user=new User();
			}
		}
		catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}
		catch (Exception e) {
			logger.error("--------------Exception-----------------"+e.getMessage());
		}
		finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return user;
	}
	public   FlightOrderRowMarkup getCompanyMarkup (String companyId,Long orderRowId) {

		FlightOrderRowMarkup flightOrderRowMarkup= null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			String sql = "from FlightOrderRowMarkup hor where hor.CompanyId=:companyid and hor.flightOrderRow.id=:orderRowId";
			Query query = session.createQuery(sql);
			query.setParameter("companyid", companyId);
			query.setParameter("orderRowId", orderRowId);
			flightOrderRowMarkup = (FlightOrderRowMarkup) query.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}

		return flightOrderRowMarkup;
	}
	public FlightAndHotelBookApiResponse getApiStatusMessage(long orderid){
		FlightAndHotelBookApiResponse flightAndHotelBookApiResponse =  null;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();			
			Criteria criteria = session.createCriteria(FlightAndHotelBookApiResponse.class);
			Conjunction conjunction = Restrictions.conjunction();
			conjunction.add(Restrictions.eq("orderRowId", orderid));
			criteria.add(conjunction);
			flightAndHotelBookApiResponse = (FlightAndHotelBookApiResponse) criteria.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return  flightAndHotelBookApiResponse;
	}

	public static  boolean  isDomesticOrInternational(String destination, String origin){
		boolean  isInternational =  false;
		Session session=null;
		try{
			session = HibernateUtil.getSessionFactory().openSession();			
			Criteria criteria = session.createCriteria(Airport.class);
			criteria.add(Restrictions.eq("airport_code", destination));
			Airport airport = (Airport) criteria.uniqueResult();

			Criteria criteria2 = session.createCriteria(Airport.class);
			criteria2.add(Restrictions.eq("airport_code", origin));
			Airport airportOrigin = (Airport) criteria2.uniqueResult();

			if(airportOrigin!=null && airport!=null && !airport.getCountry().equalsIgnoreCase(airportOrigin.getCountry()))
				isInternational=true;
		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}finally {
			if(session!=null && session.isOpen())
				session.close(); 
		}
		return isInternational;
	}


	public  ApiProvider getApiPrividerDetails(String providerId){
		Session session=null;
		ApiProvider apiProvider=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			Criteria criteria=session.createCriteria(ApiProvider.class);
			criteria.add(Restrictions.eq("id", Integer.parseInt(providerId)));
			apiProvider = (ApiProvider) criteria.uniqueResult();

		}catch (HibernateException e) {
			logger.error("--------------HibernateException-----------------"+e.getMessage());
		}  
		return apiProvider;
	}



}