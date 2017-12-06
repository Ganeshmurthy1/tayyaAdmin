package com.common.dsr;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.helper.CommonDsrDaoHelper;
import com.common.dsr.helper.DsrRmConfigHelperDao;
import com.common.dsr.helper.DsrRmConfigOrderCancelHelper;
import com.common.dsr.helper.MergeOrderRowsUtility;
import com.isl.admin.filter.FlightReportFilter;
import com.lintas.admin.DAO.CompanyConfigDao;
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
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Airport;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.SalesLeadGeneration;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.config.HibernateUtil;
import com.lintas.utility.ArithmeticUti;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;

public class CommonDsrDao {
	MergeOrderRowsUtility  mergeOrderRowsUtility=new MergeOrderRowsUtility();
	CommonDsrReportDao commonDsrReportDao=new CommonDsrReportDao();
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonDsrDao.class);
	
	public List<CommonDsrTravelReportData> getCommonDsrTravelReportsToExportExcel(CommonDsrPage commonDsrPage){
		CommonDsrDaoHelper commonDsrDaoHelper=new CommonDsrDaoHelper();
		DsrRmConfigHelperDao dsrRmConfigHelperDao=new DsrRmConfigHelperDao();
		DsrRmConfigOrderCancelHelper dsrRmConfigOrderCancelHelper=new DsrRmConfigOrderCancelHelper();
		List<CommonDsrTravelReportData> commonDsrTravelReports=new ArrayList<>();
		List<FlightOrderRow> flightOrderRowList=null;
		List<HotelOrderRow> hotelOrderRowList=null;
		List<CarOrderRow> carOrderRowList=null;
		List<BusOrderRow> busOrderRowList=null;
		List<TrainOrderRow> trainOrderRowList=null;
		List<VisaOrderRow> visaOrderRowList=null;
		List<InsuranceOrderRow> insuranceOrderRowList=null;
		List<MiscellaneousOrderRow> miscellaneousOrderRowList=null;
		List<FlightOrderRow> flightOrderRowCreditList=null;
		List<HotelOrderRow> hotelOrderRowCreditList=null;
		List<CarOrderRow> carOrderRowCreditList=null;
		List<BusOrderRow> busOrderRowCreditList=null;
		List<TrainOrderRow> trainOrderRowCreditList=null;
		List<VisaOrderRow> visaOrderRowCreditList=null;
		List<InsuranceOrderRow> insuranceOrderRowCreditList=null;
		List<MiscellaneousOrderRow> miscellaneousOrderRowCreditList=null;
		boolean isCreditNoteList=false;
		boolean isOrderRowList= false;
		CommonDsrUtility commonDsrUtility=null;
		Company    newCompanyObj=!commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")?new CompanyDAO().CompanyLogin(commonDsrPage.getCommonDsrFilters().getCompanyUserId()):commonDsrPage.getCommonDsrFilters().getLoginCompany();
		Company   sessionCompany=commonDsrPage.getCommonDsrFilters().getLoginCompany();
		CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(sessionCompany.getCompanyid());

		if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("F")){ 
			commonDsrUtility=commonDsrReportDao.getCommonDsrFlightReports(commonDsrPage);
			flightOrderRowList=commonDsrUtility.getFlightOrderRowList();
			flightOrderRowCreditList=commonDsrUtility.getFlightOrderRowCreditList();
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("H")){
			commonDsrUtility=commonDsrReportDao.getCommonDsrHotelReports(commonDsrPage);
			hotelOrderRowList=commonDsrUtility.getHotelOrderRowList();
			hotelOrderRowCreditList=commonDsrUtility.getHotelOrderRowCreditList();
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("C")){ 
			commonDsrUtility=commonDsrReportDao.getCommonDsrCarReports(commonDsrPage);
			carOrderRowList=commonDsrUtility.getCarOrderRowList();
			carOrderRowCreditList=commonDsrUtility.getCarOrderRowCreditList();
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("T")){
			commonDsrUtility=commonDsrReportDao.getCommonDsrTrainReports(commonDsrPage);
			trainOrderRowList=commonDsrUtility.getTrainOrderRowList();
			trainOrderRowCreditList=commonDsrUtility.getTrainOrderRowCreditList();
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("B")){
			commonDsrUtility=commonDsrReportDao.getCommonDsrBusReports(commonDsrPage);
			busOrderRowList=commonDsrUtility.getBusOrderRowList();
			busOrderRowCreditList=commonDsrUtility.getBusOrderRowCreditList();
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("V")){ 
			commonDsrUtility=commonDsrReportDao.getCommonDsrVisaReports(commonDsrPage);
			visaOrderRowList=commonDsrUtility.getVisaOrderRowList();
			visaOrderRowCreditList=commonDsrUtility.getVisaOrderRowCreditList();
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("I")){
			commonDsrUtility=commonDsrReportDao.getCommonDsrInsuranceReports(commonDsrPage);
			insuranceOrderRowList=commonDsrUtility.getInsuranceOrderRowList();
			insuranceOrderRowCreditList=commonDsrUtility.getInsuranceOrderRowCreditList();
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("M")){
			commonDsrUtility=commonDsrReportDao.getCommonDsrMiscellaneousReports(commonDsrPage);
			miscellaneousOrderRowList=commonDsrUtility.getMiscellaneousOrderRowList();
			miscellaneousOrderRowCreditList=commonDsrUtility.getMiscellaneousOrderRowCreditList();
		}
		else{
			commonDsrUtility=commonDsrReportDao.getCommonDsrFlightReports(commonDsrPage);
			flightOrderRowList=commonDsrUtility.getFlightOrderRowList();
			flightOrderRowCreditList=commonDsrUtility.getFlightOrderRowCreditList();
			commonDsrUtility=commonDsrReportDao.getCommonDsrHotelReports(commonDsrPage);
			hotelOrderRowList=commonDsrUtility.getHotelOrderRowList();
			hotelOrderRowCreditList=commonDsrUtility.getHotelOrderRowCreditList();
			commonDsrUtility=commonDsrReportDao.getCommonDsrCarReports(commonDsrPage);
			carOrderRowList=commonDsrUtility.getCarOrderRowList();
			carOrderRowCreditList=commonDsrUtility.getCarOrderRowCreditList();
			commonDsrUtility=commonDsrReportDao.getCommonDsrTrainReports(commonDsrPage);
			trainOrderRowList=commonDsrUtility.getTrainOrderRowList();
			trainOrderRowCreditList=commonDsrUtility.getTrainOrderRowCreditList();
			commonDsrUtility=commonDsrReportDao.getCommonDsrBusReports(commonDsrPage);
			busOrderRowList=commonDsrUtility.getBusOrderRowList();
			busOrderRowCreditList=commonDsrUtility.getBusOrderRowCreditList();
			commonDsrUtility=commonDsrReportDao.getCommonDsrVisaReports(commonDsrPage);
			visaOrderRowList=commonDsrUtility.getVisaOrderRowList();
			visaOrderRowCreditList=commonDsrUtility.getVisaOrderRowCreditList();
			commonDsrUtility=commonDsrReportDao.getCommonDsrInsuranceReports(commonDsrPage);
			insuranceOrderRowList=commonDsrUtility.getInsuranceOrderRowList();
			insuranceOrderRowCreditList=commonDsrUtility.getInsuranceOrderRowCreditList();
			commonDsrUtility=commonDsrReportDao.getCommonDsrMiscellaneousReports(commonDsrPage);
			miscellaneousOrderRowList=commonDsrUtility.getMiscellaneousOrderRowList();
			miscellaneousOrderRowCreditList=commonDsrUtility.getMiscellaneousOrderRowCreditList();
		}

		BigDecimal YQTax = new BigDecimal("0");
		BigDecimal YRTax = new BigDecimal("0");
		BigDecimal WOTax = new BigDecimal("0");
		BigDecimal PSFTax = new BigDecimal("0");
		BigDecimal UDFTax = new BigDecimal("0");
		BigDecimal JNTax = new BigDecimal("0");
		BigDecimal INTax = new BigDecimal("0");
		BigDecimal K3Tax = new BigDecimal("0");
		BigDecimal F6Tax = new BigDecimal("0");
		BigDecimal G1Tax = new BigDecimal("0");
		BigDecimal USTax = new BigDecimal("0");
		BigDecimal XATax = new BigDecimal("0");	
		BigDecimal XFTax = new BigDecimal("0");
		BigDecimal XYTax = new BigDecimal("0");
		BigDecimal YCTax = new BigDecimal("0");
		BigDecimal ZRTax = new BigDecimal("0");


		if(flightOrderRowList!=null && flightOrderRowList.size()>0 ){
			for(FlightOrderRow flightOrderRow:flightOrderRowList){
				BigDecimal convenienceFee = new BigDecimal("0");
				BigDecimal managementFee = new BigDecimal("0");
				BigDecimal gstPerPassenger =new BigDecimal("0"); 
				BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
				BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
				String taxType=commonDsrUtility.getTaxType();
				/*if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST"))
					taxType=newCompanyConfig.getTaxtype();*/
				/*if(flightOrderRow.getStatusAction().equalsIgnoreCase("Cancelled")){*/


				CompanyEntity companyEntity=null;
				String billingEntity="-";
				if(flightOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(flightOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(flightOrderRow.getFlightOrderRowGstTax()!=null && taxType!=null && taxType.equalsIgnoreCase("GST")){ 
					managementFee=flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getManagementFee():new BigDecimal(0);
					convenienceFee=flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee():new BigDecimal(0);
					CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowGstTax().getCGST());					  
					if(flightOrderRow.getFlightOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
						SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowGstTax().getSGST());					  
					if(flightOrderRow.getFlightOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
						SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowGstTax().getUGST());					  
					if(flightOrderRow.getFlightOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
						SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowGstTax().getIGST());					  
				}
				else{
					convenienceFee= flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
					managementFee= flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getManagementFee():new BigDecimal(0);
				}
				gstPerPassenger=ArithmeticUti.divideTo2Decimal(flightOrderRow.getGstOnFlights(), new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
				convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));//convenienceFee.divide(new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
				managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())); //managementFee.divide(new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
				CGSTPerPassenger=ArithmeticUti.divideTo2Decimal(CGSTPerPassenger, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())); 
				SGSTorIGSTorUGSTPerPassenger=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())); 
				BigDecimal extraBaggagePrice=ArithmeticUti.divideTo2Decimal(flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice():new BigDecimal(0), new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));//flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice().divide(new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())):new BigDecimal(0);
				BigDecimal extraMealPrice=ArithmeticUti.divideTo2Decimal(flightOrderRow.getExtramealprice()!=null?flightOrderRow.getExtramealprice():new BigDecimal(0), new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));//flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice().divide(new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())):new BigDecimal(0);
				boolean isInternational=false;
				if(flightOrderRow.getFlightOrderTripDetails()!=null && flightOrderRow.getFlightOrderTripDetails().size()>0){
					for(FlightOrderTripDetail flightOrderTripDetail:flightOrderRow.getFlightOrderTripDetails()){
						boolean  isInternationaltest=isDomesticOrInternational(flightOrderTripDetail.getDestinationCode(), flightOrderTripDetail.getOriginCode());
						if(isInternationaltest){
							isInternational=isInternationaltest;
							break;
						}
					}
				}
				String itineraryType=isInternational?"International":"Domestic";
				if(flightOrderRow!=null && flightOrderRow.getFlightOrderCustomers()!=null && flightOrderRow.getFlightOrderCustomers().size()>0){
					if(flightOrderRow.getFlightOrderCustomers().size()==flightOrderRow.getFlightOrderCustomerPriceBreakups().size()){
						for(int j=0;j<flightOrderRow.getFlightOrderCustomers().size();j++){
							CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
							commonDsrTravelReportData.setItineraryType(itineraryType);
							if(flightOrderRow.getGstOnFlights()!=null) 
								commonDsrTravelReportData.setTotGstTax(gstPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setTaxType(taxType);
							FlightOrderCustomer flightOrderCustomer=flightOrderRow.getFlightOrderCustomers().get(j);
							FlightOrderCustomerPriceBreakup flightOrderCustomerPriceBreakup= flightOrderRow.getFlightOrderCustomerPriceBreakups().get(j);
							//FlightOrderCustomerSSR flightOrderCustomerSSR= flightOrderRow.getFlightOrderCustomerSSR().get(j);
							commonDsrTravelReportData.setBkgRef(flightOrderRow.getOrderId()); 
							if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
								commonDsrTravelReportData.setSystemInvoiceId(flightOrderRow.getInvoiceNo()); 
							else 
								commonDsrTravelReportData.setSystemInvoiceId("-"); 

							commonDsrTravelReportData.setBookingType(flightOrderRow.getBookingMode());
							if(flightOrderRow.isCreditNoteIssued()) 
								commonDsrTravelReportData.setAmendmentType("Confirmed");
							else
								commonDsrTravelReportData.setAmendmentType(flightOrderRow.getStatusAction());
							commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(flightOrderRow.getCreatedAt()));
							commonDsrTravelReportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
							Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(flightOrderRow.getCompanyId()));

							User user=new UserDAO().GetUserProfile(Integer.parseInt(flightOrderRow.getUserId().trim().equals("")?"0":flightOrderRow.getUserId()));
							if(company!=null && company.getCompanyname()!=null){
								commonDsrTravelReportData.setCorporateName(company.getCompanyname());
								commonDsrTravelReportData.setClientCode(company.getCompany_userid());
							}
							else{
								commonDsrTravelReportData.setCorporateName("-");
								commonDsrTravelReportData.setClientCode("-");
							}

							commonDsrTravelReportData.setBillingEntity(billingEntity);
							if(user!=null && user.getUsername()!=null) 
								commonDsrTravelReportData.setBookerName(user.getUsername());
							else
								commonDsrTravelReportData.setBookerName("-");
							String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
							commonDsrTravelReportData.setBookersLoginId(email);
							//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
							BigDecimal basePriceInBooking=new BigDecimal(0);
							BigDecimal taxesInBooking=new BigDecimal(0);
							BigDecimal discountInBooking=new BigDecimal(0);
							BigDecimal markupInBooking=new BigDecimal(0);
							BigDecimal supplierDiscountInBooking=new BigDecimal(0);
							BigDecimal netFare=new BigDecimal(0);
							BigDecimal grossFare=new BigDecimal(0);
							BigDecimal supplierCharge=new BigDecimal(0);
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
							totExtraChargeInBooking=extraBaggagePrice.add(extraMealPrice); 

							BigDecimal otherTaxesinculdetdscharges = new BigDecimal("0");
							otherTaxesinculdetdscharges = flightOrderRow.getSupplierTds()!=null?ArithmeticUti.divideTo2Decimal(flightOrderRow.getSupplierTds(),new BigDecimal(flightOrderRow.getFlightOrderCustomerPriceBreakups().size())).setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);  

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

							taxesInBooking = taxesInBooking.subtract(otherTaxesinculdetdscharges);
							supplierCharge=basePriceInBooking.add(taxesInBooking).subtract(supplierDiscountInBooking);
							if (sessionCompany.getCompanyRole().isSuperUser())
								commonDsrTravelReportData.setSupplierCharge(supplierCharge.setScale(2, BigDecimal.ROUND_UP).toString());
							else
								commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

							ApiProvider apiProvider=ApiProviderDao.getApiProviderDetailsByVendorName(flightOrderRow.getProviderAPI());
							if(apiProvider!=null && apiProvider.getApiProvider()!=null) 
								commonDsrTravelReportData.setSupplierCode(apiProvider.getApiProvider());
							else
								commonDsrTravelReportData.setSupplierCode("-");
							commonDsrTravelReportData.setSupplierName(flightOrderRow.getProviderAPI());
							commonDsrTravelReportData.setProductType("Air");
							commonDsrTravelReportData.setProductName(flightOrderRow.getAirline());
							StringBuilder srcCode = new StringBuilder();
							StringBuilder productCode = new StringBuilder();
							for(int i=0;i<flightOrderRow.getFlightOrderTripDetails().size();i++){
								FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
								if(i == flightOrderRow.getFlightOrderTripDetails().size()-1) {
									//descode.append();
									srcCode.append(trips.getOriginCode()+"-"+trips.getDestinationCode());
									productCode.append(trips.getFlightNumber());
								}
								else{
									//descode.append(trips.getDestinationCode() + "-");
									srcCode.append(trips.getOriginCode()+"-"+trips.getDestinationCode()+"/");
									productCode.append(trips.getFlightNumber()+"/");
								}
								commonDsrTravelReportData.setCabinClass(trips.getClassOfService());
								commonDsrTravelReportData.setBookingClass(trips.getFareClass());
								commonDsrTravelReportData.setFareBasis(trips.getFareBasisCode());
								commonDsrTravelReportData.setFareType(trips.getCraft());
								if(flightOrderRow.getTripType().equals("O")) 
									commonDsrTravelReportData.setTripEndDate("-");
								else
									commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(trips.getArrivalDate()));
							}
							commonDsrTravelReportData.setProductCode(productCode.toString()!=null?productCode.toString():"-");
							commonDsrTravelReportData.setMarkup(markupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setDescription(srcCode.toString());
							commonDsrTravelReportData.setDescription2("-");
							commonDsrTravelReportData.setAirlinePNRorProvBooking(flightOrderRow.getPnr());
							commonDsrTravelReportData.setGDSPNR(flightOrderRow.getGdsPnr()!=null?flightOrderRow.getGdsPnr():"NA");
							commonDsrTravelReportData.setTicketNumorFinalBooking(flightOrderCustomer.getEticketnumber());
							commonDsrTravelReportData.setBookingRefundType("-");
							commonDsrTravelReportData.setPaxType(flightOrderCustomer.getPassengerTypeCode());
							commonDsrTravelReportData.setTraveller(flightOrderCustomer.getTitle()+" "+flightOrderCustomer.getFirstName()+" "+flightOrderCustomer.getLastName());
							commonDsrTravelReportData.setTotalNights("0");
							commonDsrTravelReportData.setTripStartsDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));

							String tripType=null;
							if(flightOrderRow.getTripType().equals("O")) 
								tripType="One Way";
							if(flightOrderRow.getTripType().equals("R")) 
								tripType="Round Trip";
							if(flightOrderRow.getTripType().equals("SR")) 
								tripType="Special Round Trip";
							if(flightOrderRow.getTripType().equals("M")) 
								tripType="Multi Trip";

							commonDsrTravelReportData.setJourneyType(tripType);
							//grossFare=basePriceInBooking.add(taxesInBooking);
							BigDecimal transactionFee = new BigDecimal("0");
							BigDecimal BaseServiceTax = new BigDecimal("0");
							BigDecimal otherTaxesWithOutMarkup = new BigDecimal("0");

							YQTax =flightOrderCustomerPriceBreakup.getYQTax()!=null?flightOrderCustomerPriceBreakup.getYQTax():new BigDecimal("0");
							YRTax =  flightOrderCustomerPriceBreakup.getYRTax()!=null?flightOrderCustomerPriceBreakup.getYRTax():new BigDecimal("0");
							WOTax = flightOrderCustomerPriceBreakup.getWOTax()!=null?flightOrderCustomerPriceBreakup.getWOTax():new BigDecimal("0");
							PSFTax = flightOrderCustomerPriceBreakup.getPSFTax()!=null?flightOrderCustomerPriceBreakup.getPSFTax():new BigDecimal("0");
							UDFTax = flightOrderCustomerPriceBreakup.getUDFTax()!=null?flightOrderCustomerPriceBreakup.getUDFTax():new BigDecimal("0");
							JNTax =  flightOrderCustomerPriceBreakup.getJNTax()!=null?flightOrderCustomerPriceBreakup.getJNTax():new BigDecimal("0");
							INTax =  flightOrderCustomerPriceBreakup.getINTax()!=null?flightOrderCustomerPriceBreakup.getINTax():new BigDecimal("0");
							K3Tax=flightOrderCustomerPriceBreakup.getK3Tax()!=null?flightOrderCustomerPriceBreakup.getK3Tax():new BigDecimal("0");
							F6Tax = flightOrderCustomerPriceBreakup.getF6Tax()!=null?flightOrderCustomerPriceBreakup.getF6Tax():new BigDecimal("0");
							G1Tax = flightOrderCustomerPriceBreakup.getG1Tax()!=null?flightOrderCustomerPriceBreakup.getG1Tax():new BigDecimal("0");
							USTax =flightOrderCustomerPriceBreakup.getUSTax()!=null?flightOrderCustomerPriceBreakup.getUSTax():new BigDecimal("0");
							XATax = flightOrderCustomerPriceBreakup.getXATax()!=null?flightOrderCustomerPriceBreakup.getXATax():new BigDecimal("0");
							XFTax =flightOrderCustomerPriceBreakup.getXFTax()!=null?flightOrderCustomerPriceBreakup.getXFTax():new BigDecimal("0");
							XYTax = flightOrderCustomerPriceBreakup.getXYTax()!=null?flightOrderCustomerPriceBreakup.getXYTax():new BigDecimal("0");
							YCTax = flightOrderCustomerPriceBreakup.getYCTax()!=null?flightOrderCustomerPriceBreakup.getYCTax():new BigDecimal("0");
							ZRTax = flightOrderCustomerPriceBreakup.getZRTax()!=null?flightOrderCustomerPriceBreakup.getZRTax():new BigDecimal("0");

							BigDecimal totalotherTaxes = INTax.add(YQTax).add(YRTax).add(PSFTax).add(UDFTax).add(JNTax)
									.add(K3Tax).add(WOTax).add(F6Tax).add(G1Tax).add(USTax).add(XATax).add(XFTax).add(XYTax).add(YCTax).add(ZRTax);			
							otherTaxesWithOutMarkup=taxesInBooking.subtract(totalotherTaxes);
							otherTaxesWithOutMarkup = otherTaxesWithOutMarkup.add(otherTaxesinculdetdscharges);

							grossFare=basePriceInBooking.add(taxesInBooking).add(totExtraChargeInBooking).add(markupInBooking).add(otherTaxesinculdetdscharges);
							commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
							transactionFee = transactionFee.add(flightOrderCustomerPriceBreakup.getTransactionFee()!=null?flightOrderCustomerPriceBreakup.getTransactionFee():new BigDecimal("0"));		
							BaseServiceTax = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getBasicTax()!=null?flightOrderRow.getFlightOrderRowServiceTax().getBasicTax():new BigDecimal(0));
							BigDecimal swachBharatCess = new BigDecimal("0");
							swachBharatCess = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null &&  flightOrderRow.getFlightOrderRowServiceTax().getSwatchBharathCess()!=null?flightOrderRow.getFlightOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
							BigDecimal krishiKalyanCess = new BigDecimal("0");
							krishiKalyanCess = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getKrishiKalyanCess()!=null?flightOrderRow.getFlightOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
							BigDecimal totalCess= swachBharatCess.add(krishiKalyanCess);
							BigDecimal TotalServiceTax = new BigDecimal("0");
							TotalServiceTax =BaseServiceTax.add(totalCess.setScale(2, BigDecimal.ROUND_UP));//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getTotalTax()!=null?flightOrderRow.getFlightOrderRowServiceTax().getTotalTax():new BigDecimal(0));
							if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
								netFare=grossFare.add(gstPerPassenger).add(managementFee).add(convenienceFee);
							else
								netFare = grossFare.add(TotalServiceTax).add(managementFee).add(convenienceFee);

							commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());//.add(taxesInBooking)
							commonDsrTravelReportData.setYQTax(YQTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setYRTax(YRTax.add(markupInBooking).setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setPSFTax(PSFTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setUDFTax(UDFTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setJNTax(JNTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setINTax(INTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setK3Tax(K3Tax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setOBTax("0");
							commonDsrTravelReportData.setOCTax("0");
							commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2,BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2,BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setOtherTaxes(otherTaxesWithOutMarkup.add(WOTax).add(F6Tax).add(G1Tax).add(USTax).add(XATax).add(XFTax).add(XYTax).add(YCTax).add(ZRTax).setScale(2,BigDecimal.ROUND_UP).toString()); 
							commonDsrTravelReportData.setExtraCharge(totExtraChargeInBooking.setScale(2, BigDecimal.ROUND_UP).toString()); 
							commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
							commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setDiscount(discountInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setModeOfPayment(flightOrderRow.getPaidBy());
							commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
							commonDsrTravelReportData.setPersonalBooking("No");
							String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
							commonDsrTravelReportData.setCorporateCurrency(currencyCode);
							//commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
							commonDsrTravelReportData.setVfsCharges("0");
							commonDsrTravelReportData.setCourierCharges("0");
							RmConfigTripDetailsModel rmConfigTripDetails=null;
							if(flightOrderCustomer.getRmConfigTripDetailsModel()!=null) 
								if(flightOrderRow.getFlightOrderRowRmConfigStruct()!=null && flightOrderRow.getFlightOrderRowRmConfigStruct().getRmDynamicData()!=null)
									rmConfigTripDetails =dsrRmConfigHelperDao.getFlightPaxRmConfigTripDetails(flightOrderCustomer.getRmConfigTripDetailsModel(),flightOrderRow.getFlightOrderRowRmConfigStruct().getRmDynamicData());
								else
									rmConfigTripDetails =dsrRmConfigHelperDao.getFlightPaxRmConfigTripDetails(flightOrderCustomer.getRmConfigTripDetailsModel(),null);
								else
									rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(flightOrderRow.getOrderId(),company.getCompanyid());

							if(rmConfigTripDetails!=null)
							{	if(sessionCompany.getCompanyRole().isSuperUser()){
								if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("All") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							}
							else 
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());

							commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
							commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
							commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
							commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
							commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
							commonDsrTravelReportData.setEmpCode(rmConfigTripDetails.getEmpCode());
							commonDsrTravelReportData.setLocation(rmConfigTripDetails.getLocation());
							commonDsrTravelReportData.setProjectCode(rmConfigTripDetails.getProjectCode());
							commonDsrTravelReportData.setReasonForTravel(rmConfigTripDetails.getReasonForTravel());
							commonDsrTravelReportData.setTravelRequestNumber(rmConfigTripDetails.getTrNumber());

							}
							commonDsrTravelReportData.setDriverAllowancedayCharge("0.00");
							commonDsrTravelReportData.setDriverAllowanceNightCharge("0.00");
							commonDsrTravelReportData.setTollorParkingCharge("0.00");
							commonDsrTravelReportData.setExtraKmCharge("0.00");
							commonDsrTravelReportData.setExtraHourCharge("0.00");
							commonDsrTravelReportData.setCreditnoteIssued(flightOrderRow.isCreditNoteIssued());
							commonDsrTravelReports.add(commonDsrTravelReportData);
						}
					}
				}
			}



		}

		if(flightOrderRowCreditList!=null && flightOrderRowCreditList.size()>0){
			for(FlightOrderRow flightOrderRow:flightOrderRowCreditList){
				Object flightOrderRowObj=flightOrderRow;
				BigDecimal convenienceFee = new BigDecimal("0");
				BigDecimal managementFee = new BigDecimal("0");
				BigDecimal gstPerPassenger =new BigDecimal("0"); 
				BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
				BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
				String taxType=commonDsrUtility.getTaxType();
				/*if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST"))
					taxType=newCompanyConfig.getTaxtype();*/
				/*if(flightOrderRow.getStatusAction().equalsIgnoreCase("Cancelled")){*/


				CompanyEntity companyEntity=null;
				String billingEntity="-";
				if(flightOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(flightOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(flightOrderRow.getFlightOrderRowGstTax()!=null && taxType!=null && taxType.equalsIgnoreCase("GST")){ 
					managementFee=flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getManagementFee():new BigDecimal(0);
					convenienceFee=flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee():new BigDecimal(0);
					CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowGstTax().getCGST());					  
					if(flightOrderRow.getFlightOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
						SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowGstTax().getSGST());					  
					if(flightOrderRow.getFlightOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
						SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowGstTax().getUGST());					  
					if(flightOrderRow.getFlightOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
						SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowGstTax().getIGST());					  
				}
				else{
					convenienceFee= flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
					managementFee= flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getManagementFee():new BigDecimal(0);
				}
				gstPerPassenger=ArithmeticUti.divideTo2Decimal(flightOrderRow.getGstOnFlights(), new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
				convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));//convenienceFee.divide(new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
				managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())); //managementFee.divide(new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
				CGSTPerPassenger=ArithmeticUti.divideTo2Decimal(CGSTPerPassenger, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())); 
				SGSTorIGSTorUGSTPerPassenger=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())); 
				BigDecimal extraBaggagePrice=ArithmeticUti.divideTo2Decimal(flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice():new BigDecimal(0), new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));//flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice().divide(new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())):new BigDecimal(0);
				BigDecimal extraMealPrice=ArithmeticUti.divideTo2Decimal(flightOrderRow.getExtramealprice()!=null?flightOrderRow.getExtramealprice():new BigDecimal(0), new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));//flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice().divide(new BigDecimal(flightOrderRow.getFlightOrderCustomers().size())):new BigDecimal(0);
				boolean isInternational=false;
				if(flightOrderRow.getFlightOrderTripDetails()!=null && flightOrderRow.getFlightOrderTripDetails().size()>0){
					for(FlightOrderTripDetail flightOrderTripDetail:flightOrderRow.getFlightOrderTripDetails()){
						boolean  isInternationaltest=isDomesticOrInternational(flightOrderTripDetail.getDestinationCode(), flightOrderTripDetail.getOriginCode());
						if(isInternationaltest){
							isInternational=isInternationaltest;
							break;
						}
					}
				}
				String itineraryType=isInternational?"International":"Domestic";
				if(flightOrderRow!=null && flightOrderRow.getFlightOrderCustomers()!=null && flightOrderRow.getFlightOrderCustomers().size()>0){
					if(flightOrderRow.getFlightOrderCustomers().size()==flightOrderRow.getFlightOrderCustomerPriceBreakups().size()){
						for(int j=0;j<flightOrderRow.getFlightOrderCustomers().size();j++){
							CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
							commonDsrTravelReportData.setItineraryType(itineraryType);
							if(flightOrderRow.getGstOnFlights()!=null) 
								commonDsrTravelReportData.setTotGstTax(gstPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setTaxType(taxType);
							FlightOrderCustomer flightOrderCustomer=flightOrderRow.getFlightOrderCustomers().get(j);
							FlightOrderCustomerPriceBreakup flightOrderCustomerPriceBreakup= flightOrderRow.getFlightOrderCustomerPriceBreakups().get(j);
							//FlightOrderCustomerSSR flightOrderCustomerSSR= flightOrderRow.getFlightOrderCustomerSSR().get(j);
							commonDsrTravelReportData.setBkgRef(flightOrderRow.getOrderId()); 
							if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
								commonDsrTravelReportData.setSystemInvoiceId(flightOrderRow.getInvoiceNo()); 
							else 
								commonDsrTravelReportData.setSystemInvoiceId("-"); 

							commonDsrTravelReportData.setBookingType(flightOrderRow.getBookingMode());
							if(flightOrderRow.isCreditNoteIssued()) 
								commonDsrTravelReportData.setAmendmentType("Confirmed");
							else
								commonDsrTravelReportData.setAmendmentType(flightOrderRow.getStatusAction());
							commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(flightOrderRow.getCreatedAt()));
							commonDsrTravelReportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
							Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(flightOrderRow.getCompanyId()));
							User user=new UserDAO().GetUserProfile(Integer.parseInt(flightOrderRow.getUserId().trim().equals("")?"0":flightOrderRow.getUserId()));
							if(company!=null && company.getCompanyname()!=null){
								commonDsrTravelReportData.setCorporateName(company.getCompanyname());
								commonDsrTravelReportData.setClientCode(company.getCompany_userid());
							}
							else{
								commonDsrTravelReportData.setCorporateName("-");
								commonDsrTravelReportData.setClientCode("-");
							}

							commonDsrTravelReportData.setBillingEntity(billingEntity);
							if(user!=null && user.getUsername()!=null) 
								commonDsrTravelReportData.setBookerName(user.getUsername());
							else
								commonDsrTravelReportData.setBookerName("-");
							String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
							commonDsrTravelReportData.setBookersLoginId(email);
							//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
							BigDecimal basePriceInBooking=new BigDecimal(0);
							BigDecimal taxesInBooking=new BigDecimal(0);
							BigDecimal discountInBooking=new BigDecimal(0);
							BigDecimal markupInBooking=new BigDecimal(0);
							BigDecimal supplierDiscountInBooking=new BigDecimal(0);
							BigDecimal netFare=new BigDecimal(0);
							BigDecimal grossFare=new BigDecimal(0);
							BigDecimal supplierCharge=new BigDecimal(0);
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
							totExtraChargeInBooking=extraBaggagePrice.add(extraMealPrice); 

							BigDecimal otherTaxesinculdetdscharges = new BigDecimal("0");
							otherTaxesinculdetdscharges = flightOrderRow.getSupplierTds()!=null?ArithmeticUti.divideTo2Decimal(flightOrderRow.getSupplierTds(),new BigDecimal(flightOrderRow.getFlightOrderCustomerPriceBreakups().size())).setScale(2,BigDecimal.ROUND_UP):new BigDecimal(0);  

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

							taxesInBooking = taxesInBooking.subtract(otherTaxesinculdetdscharges);
							supplierCharge=basePriceInBooking.add(taxesInBooking).subtract(supplierDiscountInBooking);
							if (sessionCompany.getCompanyRole().isSuperUser())
								commonDsrTravelReportData.setSupplierCharge(supplierCharge.setScale(2, BigDecimal.ROUND_UP).toString());
							else
								commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());
							ApiProvider apiProvider=ApiProviderDao.getApiProviderDetailsByVendorName(flightOrderRow.getProviderAPI());
							if(apiProvider!=null && apiProvider.getApiProvider()!=null) 
								commonDsrTravelReportData.setSupplierCode(apiProvider.getApiProvider());
							else
								commonDsrTravelReportData.setSupplierCode("-");
							commonDsrTravelReportData.setSupplierName(flightOrderRow.getProviderAPI());
							commonDsrTravelReportData.setProductType("Air");
							commonDsrTravelReportData.setProductName(flightOrderRow.getAirline());
							StringBuilder srcCode = new StringBuilder();
							StringBuilder productCode = new StringBuilder();
							for(int i=0;i<flightOrderRow.getFlightOrderTripDetails().size();i++){
								FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
								if(i == flightOrderRow.getFlightOrderTripDetails().size()-1) {
									//descode.append();
									srcCode.append(trips.getOriginCode()+"-"+trips.getDestinationCode());
									productCode.append(trips.getFlightNumber());
								}
								else{
									//descode.append(trips.getDestinationCode() + "-");
									srcCode.append(trips.getOriginCode()+"-"+trips.getDestinationCode()+"/");
									productCode.append(trips.getFlightNumber()+"/");
								}
								commonDsrTravelReportData.setCabinClass(trips.getClassOfService());
								commonDsrTravelReportData.setBookingClass(trips.getFareClass());
								commonDsrTravelReportData.setFareBasis(trips.getFareBasisCode());
								commonDsrTravelReportData.setFareType(trips.getCraft());
								if(flightOrderRow.getTripType().equals("O")) 
									commonDsrTravelReportData.setTripEndDate("-");
								else
									commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(trips.getArrivalDate()));
							}
							commonDsrTravelReportData.setProductCode(productCode.toString()!=null?productCode.toString():"-");
							commonDsrTravelReportData.setMarkup(markupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setDescription(srcCode.toString());
							commonDsrTravelReportData.setDescription2("-");
							commonDsrTravelReportData.setAirlinePNRorProvBooking(flightOrderRow.getPnr());
							commonDsrTravelReportData.setGDSPNR(flightOrderRow.getGdsPnr()!=null?flightOrderRow.getGdsPnr():"NA");
							commonDsrTravelReportData.setTicketNumorFinalBooking(flightOrderCustomer.getEticketnumber());
							commonDsrTravelReportData.setBookingRefundType("-");
							commonDsrTravelReportData.setPaxType(flightOrderCustomer.getPassengerTypeCode());
							commonDsrTravelReportData.setTraveller(flightOrderCustomer.getTitle()+" "+flightOrderCustomer.getFirstName()+" "+flightOrderCustomer.getLastName());
							commonDsrTravelReportData.setTotalNights("0");
							commonDsrTravelReportData.setTripStartsDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));

							String tripType=null;
							if(flightOrderRow.getTripType().equals("O")) 
								tripType="One Way";
							if(flightOrderRow.getTripType().equals("R")) 
								tripType="Round Trip";
							if(flightOrderRow.getTripType().equals("SR")) 
								tripType="Special Round Trip";
							if(flightOrderRow.getTripType().equals("M")) 
								tripType="Multi Trip";

							commonDsrTravelReportData.setJourneyType(tripType);
							//grossFare=basePriceInBooking.add(taxesInBooking);
							BigDecimal transactionFee = new BigDecimal("0");
							BigDecimal BaseServiceTax = new BigDecimal("0");
							BigDecimal otherTaxesWithOutMarkup = new BigDecimal("0");

							YQTax =flightOrderCustomerPriceBreakup.getYQTax()!=null?flightOrderCustomerPriceBreakup.getYQTax():new BigDecimal("0");
							YRTax =  flightOrderCustomerPriceBreakup.getYRTax()!=null?flightOrderCustomerPriceBreakup.getYRTax():new BigDecimal("0");
							WOTax = flightOrderCustomerPriceBreakup.getWOTax()!=null?flightOrderCustomerPriceBreakup.getWOTax():new BigDecimal("0");
							PSFTax = flightOrderCustomerPriceBreakup.getPSFTax()!=null?flightOrderCustomerPriceBreakup.getPSFTax():new BigDecimal("0");
							UDFTax = flightOrderCustomerPriceBreakup.getUDFTax()!=null?flightOrderCustomerPriceBreakup.getUDFTax():new BigDecimal("0");
							JNTax =  flightOrderCustomerPriceBreakup.getJNTax()!=null?flightOrderCustomerPriceBreakup.getJNTax():new BigDecimal("0");
							INTax =  flightOrderCustomerPriceBreakup.getINTax()!=null?flightOrderCustomerPriceBreakup.getINTax():new BigDecimal("0");
							K3Tax=flightOrderCustomerPriceBreakup.getK3Tax()!=null?flightOrderCustomerPriceBreakup.getK3Tax():new BigDecimal("0");
							F6Tax = flightOrderCustomerPriceBreakup.getF6Tax()!=null?flightOrderCustomerPriceBreakup.getF6Tax():new BigDecimal("0");
							G1Tax = flightOrderCustomerPriceBreakup.getG1Tax()!=null?flightOrderCustomerPriceBreakup.getG1Tax():new BigDecimal("0");
							USTax =flightOrderCustomerPriceBreakup.getUSTax()!=null?flightOrderCustomerPriceBreakup.getUSTax():new BigDecimal("0");
							XATax = flightOrderCustomerPriceBreakup.getXATax()!=null?flightOrderCustomerPriceBreakup.getXATax():new BigDecimal("0");
							XFTax =flightOrderCustomerPriceBreakup.getXFTax()!=null?flightOrderCustomerPriceBreakup.getXFTax():new BigDecimal("0");
							XYTax = flightOrderCustomerPriceBreakup.getXYTax()!=null?flightOrderCustomerPriceBreakup.getXYTax():new BigDecimal("0");
							YCTax = flightOrderCustomerPriceBreakup.getYCTax()!=null?flightOrderCustomerPriceBreakup.getYCTax():new BigDecimal("0");
							ZRTax = flightOrderCustomerPriceBreakup.getZRTax()!=null?flightOrderCustomerPriceBreakup.getZRTax():new BigDecimal("0");

							BigDecimal totalotherTaxes = INTax.add(YQTax).add(YRTax).add(PSFTax).add(UDFTax).add(JNTax)
									.add(K3Tax).add(WOTax).add(F6Tax).add(G1Tax).add(USTax).add(XATax).add(XFTax).add(XYTax).add(YCTax).add(ZRTax);			
							otherTaxesWithOutMarkup=taxesInBooking.subtract(totalotherTaxes);
							otherTaxesWithOutMarkup = otherTaxesWithOutMarkup.add(otherTaxesinculdetdscharges);

							grossFare=basePriceInBooking.add(taxesInBooking).add(totExtraChargeInBooking).add(markupInBooking).add(otherTaxesinculdetdscharges);
							commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
							transactionFee = transactionFee.add(flightOrderCustomerPriceBreakup.getTransactionFee()!=null?flightOrderCustomerPriceBreakup.getTransactionFee():new BigDecimal("0"));		
							BaseServiceTax = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getBasicTax()!=null?flightOrderRow.getFlightOrderRowServiceTax().getBasicTax():new BigDecimal(0));
							BigDecimal swachBharatCess = new BigDecimal("0");
							swachBharatCess = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null &&  flightOrderRow.getFlightOrderRowServiceTax().getSwatchBharathCess()!=null?flightOrderRow.getFlightOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
							BigDecimal krishiKalyanCess = new BigDecimal("0");
							krishiKalyanCess = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getKrishiKalyanCess()!=null?flightOrderRow.getFlightOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
							BigDecimal totalCess= swachBharatCess.add(krishiKalyanCess);
							BigDecimal TotalServiceTax = new BigDecimal("0");
							TotalServiceTax =BaseServiceTax.add(totalCess.setScale(2, BigDecimal.ROUND_UP));//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax()!=null && flightOrderRow.getFlightOrderRowServiceTax().getTotalTax()!=null?flightOrderRow.getFlightOrderRowServiceTax().getTotalTax():new BigDecimal(0));
							if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
								netFare=grossFare.add(gstPerPassenger).add(managementFee).add(convenienceFee);
							else
								netFare = grossFare.add(TotalServiceTax).add(managementFee).add(convenienceFee);

							commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());//.add(taxesInBooking)
							commonDsrTravelReportData.setYQTax(YQTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setYRTax(YRTax.add(markupInBooking).setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setPSFTax(PSFTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setUDFTax(UDFTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setJNTax(JNTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setINTax(INTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setK3Tax(K3Tax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setOBTax("0");
							commonDsrTravelReportData.setOCTax("0");
							commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2,BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2,BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setOtherTaxes(otherTaxesWithOutMarkup.add(WOTax).add(F6Tax).add(G1Tax).add(USTax).add(XATax).add(XFTax).add(XYTax).add(YCTax).add(ZRTax).setScale(2,BigDecimal.ROUND_UP).toString()); 
							commonDsrTravelReportData.setExtraCharge(totExtraChargeInBooking.setScale(2, BigDecimal.ROUND_UP).toString()); 
							commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
							commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setDiscount(discountInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setModeOfPayment(flightOrderRow.getPaidBy());
							commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
							commonDsrTravelReportData.setPersonalBooking("No");
							String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
							commonDsrTravelReportData.setCorporateCurrency(currencyCode);
							//commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
							commonDsrTravelReportData.setVfsCharges("0");
							commonDsrTravelReportData.setCourierCharges("0");
							RmConfigTripDetailsModel rmConfigTripDetails=null;
							if(flightOrderCustomer.getRmConfigTripDetailsModel()!=null) 
								if(flightOrderRow.getFlightOrderRowRmConfigStruct()!=null && flightOrderRow.getFlightOrderRowRmConfigStruct().getRmDynamicData()!=null)
									rmConfigTripDetails =dsrRmConfigHelperDao.getFlightPaxRmConfigTripDetails(flightOrderCustomer.getRmConfigTripDetailsModel(),flightOrderRow.getFlightOrderRowRmConfigStruct().getRmDynamicData());
								else
									rmConfigTripDetails =dsrRmConfigHelperDao.getFlightPaxRmConfigTripDetails(flightOrderCustomer.getRmConfigTripDetailsModel(),null);
								else
									rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(flightOrderRow.getOrderId(),company.getCompanyid());

							if(rmConfigTripDetails!=null)
							{	if(sessionCompany.getCompanyRole().isSuperUser()){
								if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("All") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							}
							else 
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());

							commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
							commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
							commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
							commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
							commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
							commonDsrTravelReportData.setEmpCode(rmConfigTripDetails.getEmpCode());
							commonDsrTravelReportData.setLocation(rmConfigTripDetails.getLocation());
							commonDsrTravelReportData.setProjectCode(rmConfigTripDetails.getProjectCode());
							commonDsrTravelReportData.setReasonForTravel(rmConfigTripDetails.getReasonForTravel());
							commonDsrTravelReportData.setTravelRequestNumber(rmConfigTripDetails.getTrNumber());

							}
							commonDsrTravelReportData.setDriverAllowancedayCharge("0.00");
							commonDsrTravelReportData.setDriverAllowanceNightCharge("0.00");
							commonDsrTravelReportData.setTollorParkingCharge("0.00");
							commonDsrTravelReportData.setExtraKmCharge("0.00");
							commonDsrTravelReportData.setExtraHourCharge("0.00");
							commonDsrTravelReportData.setCreditnoteIssued(flightOrderRow.isCreditNoteIssued());
							CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("F",flightOrderRowObj, commonDsrTravelReportData);
							if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
								commonDsrTravelReportsNew.setBillingEntity(billingEntity);
								commonDsrTravelReports.add(commonDsrTravelReportsNew);


							}
						}
					}
				}
			}



		}

		if(hotelOrderRowList!=null && hotelOrderRowList.size()>0){
			for(HotelOrderRow hotelOrderRow:hotelOrderRowList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(hotelOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(hotelOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();

				if(hotelOrderRow!=null && hotelOrderRow.getHotelOrderRoomInfos()!=null && hotelOrderRow.getHotelOrderRoomInfos().size()>0){
					for(int j=0;j<hotelOrderRow.getHotelOrderRoomInfos().size();j++){


						CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
						HotelOrderRoomInfo hotelOrderRoomInfo=hotelOrderRow.getHotelOrderRoomInfos().get(j);
						commonDsrTravelReportData.setBkgRef(hotelOrderRow.getOrderReference()); 
						if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
							commonDsrTravelReportData.setSystemInvoiceId(hotelOrderRow.getInvoiceNo()); 
						else 
							commonDsrTravelReportData.setSystemInvoiceId("-"); 

						commonDsrTravelReportData.setBookingType(hotelOrderRow.getBookingMode());
						if(hotelOrderRow.isCreditNoteIssued()) 
							commonDsrTravelReportData.setAmendmentType("Confirmed");
						else
							commonDsrTravelReportData.setAmendmentType(hotelOrderRow.getStatusAction());


						commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCreatedAt()));
						commonDsrTravelReportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(hotelOrderRow.getBookingDate()!=null?hotelOrderRow.getBookingDate():"-"));
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(hotelOrderRow.getCompanyId()));
						User user=new UserDAO().GetUserProfile(Integer.parseInt(hotelOrderRow.getUserId().trim().equals("")?"0":hotelOrderRow.getUserId()));
						if(company!=null && company.getCompanyname()!=null){
							commonDsrTravelReportData.setCorporateName(company.getCompanyname());
							commonDsrTravelReportData.setClientCode(company.getCompany_userid());
						}
						else{
							commonDsrTravelReportData.setCorporateName("-");
							commonDsrTravelReportData.setClientCode("-");
						}
						commonDsrTravelReportData.setBillingEntity(billingEntity);
						if(user!=null && user.getUsername()!=null) 
							commonDsrTravelReportData.setBookerName(user.getUsername());
						else
							commonDsrTravelReportData.setBookerName("-");
						String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
						commonDsrTravelReportData.setBookersLoginId(email);
						//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
						String apiProviderName="-";
						String apiProviderCode="-";
						ApiProvider apiProvoder=null;
						if(hotelOrderRow.getBookingMode() !=null &&hotelOrderRow.getBookingMode().equalsIgnoreCase("Offline"))
							apiProviderName=hotelOrderRow.getApiProvoder();
						if(hotelOrderRow.getBookingMode() !=null && hotelOrderRow.getBookingMode().equalsIgnoreCase("Online")) 
							apiProvoder=new ApiProviderDao().getApiPrividerDetails(hotelOrderRow.getApiProvoder());
						if(apiProvoder!=null){
							apiProviderName=apiProvoder.getVendorName();
							if(apiProvoder.getApiProvider()!=null) 
								apiProviderCode=apiProvoder.getApiProvider();

						}
						commonDsrTravelReportData.setSupplierCode(apiProviderCode);
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
						BigDecimal netFare=null;
						BigDecimal BaseServiceTax = new BigDecimal("0");
						BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
						BigDecimal managementFeePerRoom = new BigDecimal("0");
						BigDecimal convenienceFeePerRoom = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal convenienceFee  = new BigDecimal("0");
						BigDecimal gstPerRoom = new BigDecimal("0");
						BigDecimal CGSTPerRoom=new BigDecimal("0"); 
						BigDecimal SGSTorIGSTorUGSTPerPerRoom =new BigDecimal("0"); 
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


						commonDsrTravelReportData.setTaxType(taxType);
						if(hotelOrderRow.getHotelOrderRowGstTax()!=null && taxType!=null && taxType.equalsIgnoreCase("GST")){ 
							managementFee=hotelOrderRow.getHotelOrderRowGstTax().getManagementFee()!=null?hotelOrderRow.getHotelOrderRowGstTax().getManagementFee():new BigDecimal(0);
							convenienceFee=hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee()!=null?hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee():new BigDecimal(0);
							CGSTPerRoom=managementFee.divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowGstTax().getCGST());					  
							if(hotelOrderRow.getHotelOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
								SGSTorIGSTorUGSTPerPerRoom=managementFee.divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowGstTax().getSGST());					  
							if(hotelOrderRow.getHotelOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
								SGSTorIGSTorUGSTPerPerRoom=managementFee.divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowGstTax().getUGST());					  
							if(hotelOrderRow.getHotelOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
								SGSTorIGSTorUGSTPerPerRoom=managementFee.divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowGstTax().getIGST());					  

						}
						else{
							convenienceFee= hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
							managementFee= hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee():new BigDecimal(0);
						}

						managementFeePerRoom=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(hotelOrderRow.getHotelOrderRoomInfos().size()));
						convenienceFeePerRoom= ArithmeticUti.divideTo2Decimal(convenienceFee,new BigDecimal(hotelOrderRow.getNoOfRooms()));
						gstPerRoom=ArithmeticUti.divideTo2Decimal(hotelOrderRow.getTotGst()!=null?hotelOrderRow.getTotGst():new BigDecimal("0.00"), new BigDecimal(hotelOrderRow.getHotelOrderRoomInfos().size()));
						CGSTPerRoom=ArithmeticUti.divideTo2Decimal(CGSTPerRoom, new BigDecimal(hotelOrderRow.getNoOfRooms())); 
						SGSTorIGSTorUGSTPerPerRoom=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPerRoom, new BigDecimal(hotelOrderRow.getNoOfRooms())); 
						commonDsrTravelReportData.setTotGstTax(gstPerRoom.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setMarkup(roomMarkupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setBaseFare(roomBasePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						if (sessionCompany.getCompanyRole().isSuperUser())
							commonDsrTravelReportData.setSupplierCharge(roomApiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						else
							commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

						commonDsrTravelReportData.setProductType("Hotel");
						String country=hotelOrderRow.getHotelOrderHotelData().getCountry()!=null?hotelOrderRow.getHotelOrderHotelData().getCountry():"India";
						String itineraryType=country.equalsIgnoreCase("India") ?"Domestic Hotel":"International Hotel";
						commonDsrTravelReportData.setItineraryType(itineraryType);
						commonDsrTravelReportData.setProductName(hotelOrderRow.getHotelOrderHotelData().getName());
						commonDsrTravelReportData.setProductCode("-");
						commonDsrTravelReportData.setDescription("Hotel Name: "+hotelOrderRow.getHotelOrderHotelData().getName());
						commonDsrTravelReportData.setDescription2("-");
						commonDsrTravelReportData.setAirlinePNRorProvBooking("");
						commonDsrTravelReportData.setGDSPNR("-");
						commonDsrTravelReportData.setTicketNumorFinalBooking(hotelOrderRow.getApiConfirmationNo());
						commonDsrTravelReportData.setFareType("-");
						commonDsrTravelReportData.setBookingRefundType("-");
						commonDsrTravelReportData.setFareBasis("-");
						if(hotelOrderRoomInfo.getHotelOrderGuests()!=null && hotelOrderRoomInfo.getHotelOrderGuests().size()>0){
							for(HotelOrderGuest hotelOrderGuest:hotelOrderRoomInfo.getHotelOrderGuests()){
								if(hotelOrderGuest!=null && hotelOrderGuest.getLeader()){
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
						otherTaxesWithMarkup=roomTaxesPriceInBooking;

						grossFare=roomBasePriceInBooking.add(otherTaxesWithMarkup).add(roomMarkupInBooking);

						commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setYQTax("0");
						commonDsrTravelReportData.setYRTax(roomMarkupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setPSFTax("0");
						commonDsrTravelReportData.setUDFTax("0");
						commonDsrTravelReportData.setJNTax("0");
						commonDsrTravelReportData.setOBTax("0");
						commonDsrTravelReportData.setOCTax("0");
						commonDsrTravelReportData.setK3Tax("0");
						commonDsrTravelReportData.setDriverAllowancedayCharge("0.00");
						commonDsrTravelReportData.setDriverAllowanceNightCharge("0.00");
						commonDsrTravelReportData.setTollorParkingCharge("0.00");
						commonDsrTravelReportData.setExtraKmCharge("0.00");
						commonDsrTravelReportData.setExtraHourCharge("0.00");
						BaseServiceTax = grossFare.add(managementFeePerRoom).setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax():new BigDecimal(0));
						BigDecimal swachBharatCess = new BigDecimal("0");
						swachBharatCess = grossFare.add(managementFeePerRoom).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
						BigDecimal krishiKalyanCess = new BigDecimal("0");
						krishiKalyanCess = grossFare.add(managementFeePerRoom).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
						BigDecimal TotalServiceTax = new BigDecimal("0");
						TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax():new BigDecimal(0));
						//BigDecimal convenienceFee = new BigDecimal("0");
						//BigDecimal managementFee = new BigDecimal("0");
						//convenienceFee=hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()!=null? hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
						//managementFee=hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()!=null? hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee():new BigDecimal(0);
						if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
							netFare=grossFare.add(gstPerRoom).add(managementFeePerRoom).add(convenienceFeePerRoom);
						else
							netFare = grossFare.add(TotalServiceTax).add(managementFeePerRoom).add(convenienceFeePerRoom);

						commonDsrTravelReportData.setTayyarahServiceCharges(managementFeePerRoom.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(2, BigDecimal.ROUND_UP).toString());//otherTaxesWithMarkup.setScale(0, RoundingMode.UP).toString()
						commonDsrTravelReportData.setCGST(CGSTPerRoom.setScale(2,BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPerRoom.setScale(2,BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setExtraCharge("0");
						commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
						commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setConvenienceCharge(convenienceFeePerRoom.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setDiscount(roomDiscountInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
						PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(hotelOrderRow.getOrderReference());
						if(paymentTransaction!=null)
							commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

						commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
						commonDsrTravelReportData.setPersonalBooking("No");
						String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
						commonDsrTravelReportData.setCorporateCurrency(currencyCode);
						commonDsrTravelReportData.setINTax("0");
						commonDsrTravelReportData.setVfsCharges("0");
						commonDsrTravelReportData.setCourierCharges("0");
						RmConfigTripDetailsModel 	rmConfigTripDetails=dsrRmConfigHelperDao.getLeadPersonRmDetails(hotelOrderRoomInfo.getHotelOrderGuests());
						if(rmConfigTripDetails!=null)
							if(hotelOrderRow.getHotelOrderRowRmConfigStruct()!=null && hotelOrderRow.getHotelOrderRowRmConfigStruct().getRmDynamicData()!=null)
								rmConfigTripDetails =dsrRmConfigHelperDao.getHotelPaxRmConfigTripDetails(rmConfigTripDetails,hotelOrderRow.getHotelOrderRowRmConfigStruct().getRmDynamicData());
							else
								rmConfigTripDetails =dsrRmConfigHelperDao.getHotelPaxRmConfigTripDetails(rmConfigTripDetails,null);
							else
								rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(hotelOrderRow.getOrderReference(),company.getCompanyid());

						if(rmConfigTripDetails!=null)
						{
							commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
							commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
							commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
							commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
							commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
							commonDsrTravelReportData.setEmpCode(rmConfigTripDetails.getEmpCode());
							commonDsrTravelReportData.setLocation(rmConfigTripDetails.getLocation());
							commonDsrTravelReportData.setProjectCode(rmConfigTripDetails.getProjectCode());
							commonDsrTravelReportData.setReasonForTravel(rmConfigTripDetails.getReasonForTravel());
							commonDsrTravelReportData.setTravelRequestNumber(rmConfigTripDetails.getTrNumber());
							if(sessionCompany.getCompanyRole().isSuperUser()){
								if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("All") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							}
							else 
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
						}
						commonDsrTravelReportData.setCreditnoteIssued(hotelOrderRow.isCreditNoteIssued());
						commonDsrTravelReports.add(commonDsrTravelReportData);
					}
				}
			}
		}


		if(hotelOrderRowCreditList!=null && hotelOrderRowCreditList.size()>0){
			for(HotelOrderRow hotelOrderRow:hotelOrderRowCreditList){
				Object hotelOrderRowObj=hotelOrderRow;
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(hotelOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(hotelOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();

				if(hotelOrderRow!=null && hotelOrderRow.getHotelOrderRoomInfos()!=null && hotelOrderRow.getHotelOrderRoomInfos().size()>0){
					for(int j=0;j<hotelOrderRow.getHotelOrderRoomInfos().size();j++){
						CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
						HotelOrderRoomInfo hotelOrderRoomInfo=hotelOrderRow.getHotelOrderRoomInfos().get(j);
						commonDsrTravelReportData.setBkgRef(hotelOrderRow.getOrderReference()); 
						if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
							commonDsrTravelReportData.setSystemInvoiceId(hotelOrderRow.getInvoiceNo()); 
						else 
							commonDsrTravelReportData.setSystemInvoiceId("-"); 

						commonDsrTravelReportData.setBookingType(hotelOrderRow.getBookingMode());
						if(hotelOrderRow.isCreditNoteIssued()) 
							commonDsrTravelReportData.setAmendmentType("Confirmed");
						else
							commonDsrTravelReportData.setAmendmentType(hotelOrderRow.getStatusAction());


						commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCreatedAt()));
						commonDsrTravelReportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(hotelOrderRow.getBookingDate()!=null?hotelOrderRow.getBookingDate():"-"));
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(hotelOrderRow.getCompanyId()));
						User user=new UserDAO().GetUserProfile(Integer.parseInt(hotelOrderRow.getUserId().trim().equals("")?"0":hotelOrderRow.getUserId()));
						if(company!=null && company.getCompanyname()!=null){
							commonDsrTravelReportData.setCorporateName(company.getCompanyname());
							commonDsrTravelReportData.setClientCode(company.getCompany_userid());
						}
						else{
							commonDsrTravelReportData.setCorporateName("-");
							commonDsrTravelReportData.setClientCode("-");
						}
						commonDsrTravelReportData.setBillingEntity(billingEntity);
						if(user!=null && user.getUsername()!=null) 
							commonDsrTravelReportData.setBookerName(user.getUsername());
						else
							commonDsrTravelReportData.setBookerName("-");
						String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
						commonDsrTravelReportData.setBookersLoginId(email);
						String apiProviderName="-";
						String apiProviderCode="-";
						ApiProvider apiProvoder=null;
						if(hotelOrderRow.getBookingMode() !=null &&hotelOrderRow.getBookingMode().equalsIgnoreCase("Offline"))
							apiProviderName=hotelOrderRow.getApiProvoder();
						if(hotelOrderRow.getBookingMode() !=null && hotelOrderRow.getBookingMode().equalsIgnoreCase("Online")) 
							apiProvoder=new ApiProviderDao().getApiPrividerDetails(hotelOrderRow.getApiProvoder());
						if(apiProvoder!=null){
							apiProviderName=apiProvoder.getVendorName();
							if(apiProvoder.getApiProvider()!=null) 
								apiProviderCode=apiProvoder.getApiProvider();

						}
						commonDsrTravelReportData.setSupplierCode(apiProviderCode);
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
						BigDecimal netFare=null;
						BigDecimal BaseServiceTax = new BigDecimal("0");
						BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
						BigDecimal managementFeePerRoom = new BigDecimal("0");
						BigDecimal convenienceFeePerRoom = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal convenienceFee  = new BigDecimal("0");
						BigDecimal gstPerRoom = new BigDecimal("0");
						BigDecimal CGSTPerRoom=new BigDecimal("0"); 
						BigDecimal SGSTorIGSTorUGSTPerPerRoom =new BigDecimal("0"); 
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
						commonDsrTravelReportData.setTaxType(taxType);
						if(hotelOrderRow.getHotelOrderRowGstTax()!=null && taxType!=null && taxType.equalsIgnoreCase("GST")){ 
							managementFee=hotelOrderRow.getHotelOrderRowGstTax().getManagementFee()!=null?hotelOrderRow.getHotelOrderRowGstTax().getManagementFee():new BigDecimal(0);
							convenienceFee=hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee()!=null?hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee():new BigDecimal(0);
							CGSTPerRoom=managementFee.divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowGstTax().getCGST());					  
							if(hotelOrderRow.getHotelOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
								SGSTorIGSTorUGSTPerPerRoom=managementFee.divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowGstTax().getSGST());					  
							if(hotelOrderRow.getHotelOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
								SGSTorIGSTorUGSTPerPerRoom=managementFee.divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowGstTax().getUGST());					  
							if(hotelOrderRow.getHotelOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
								SGSTorIGSTorUGSTPerPerRoom=managementFee.divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowGstTax().getIGST());					  

						}
						else{
							convenienceFee= hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
							managementFee= hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee():new BigDecimal(0);
						}

						managementFeePerRoom=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(hotelOrderRow.getHotelOrderRoomInfos().size()));
						convenienceFeePerRoom= ArithmeticUti.divideTo2Decimal(convenienceFee,new BigDecimal(hotelOrderRow.getNoOfRooms()));
						gstPerRoom=ArithmeticUti.divideTo2Decimal(hotelOrderRow.getTotGst()!=null?hotelOrderRow.getTotGst():new BigDecimal("0.00"), new BigDecimal(hotelOrderRow.getHotelOrderRoomInfos().size()));
						CGSTPerRoom=ArithmeticUti.divideTo2Decimal(CGSTPerRoom, new BigDecimal(hotelOrderRow.getNoOfRooms())); 
						SGSTorIGSTorUGSTPerPerRoom=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPerRoom, new BigDecimal(hotelOrderRow.getNoOfRooms())); 
						commonDsrTravelReportData.setTotGstTax(gstPerRoom.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setMarkup(roomMarkupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setBaseFare(roomBasePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						if (sessionCompany.getCompanyRole().isSuperUser())
							commonDsrTravelReportData.setSupplierCharge(roomApiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						else
							commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

						commonDsrTravelReportData.setProductType("Hotel");
						String country=hotelOrderRow.getHotelOrderHotelData().getCountry()!=null?hotelOrderRow.getHotelOrderHotelData().getCountry():"India";
						String itineraryType=country.equalsIgnoreCase("India") ?"Domestic Hotel":"International Hotel";
						commonDsrTravelReportData.setItineraryType(itineraryType);
						commonDsrTravelReportData.setProductName(hotelOrderRow.getHotelOrderHotelData().getName());
						commonDsrTravelReportData.setProductCode("-");
						commonDsrTravelReportData.setDescription("Hotel Name: "+hotelOrderRow.getHotelOrderHotelData().getName());
						commonDsrTravelReportData.setDescription2("-");
						commonDsrTravelReportData.setAirlinePNRorProvBooking("");
						commonDsrTravelReportData.setGDSPNR("-");
						commonDsrTravelReportData.setTicketNumorFinalBooking(hotelOrderRow.getApiConfirmationNo());
						commonDsrTravelReportData.setFareType("-");
						commonDsrTravelReportData.setBookingRefundType("-");
						commonDsrTravelReportData.setFareBasis("-");
						if(hotelOrderRoomInfo.getHotelOrderGuests()!=null && hotelOrderRoomInfo.getHotelOrderGuests().size()>0){
							for(HotelOrderGuest hotelOrderGuest:hotelOrderRoomInfo.getHotelOrderGuests()){
								if(hotelOrderGuest!=null && hotelOrderGuest.getLeader()){
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
						otherTaxesWithMarkup=roomTaxesPriceInBooking;

						grossFare=roomBasePriceInBooking.add(otherTaxesWithMarkup).add(roomMarkupInBooking);

						commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setYQTax("0");
						commonDsrTravelReportData.setYRTax(roomMarkupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setPSFTax("0");
						commonDsrTravelReportData.setUDFTax("0");
						commonDsrTravelReportData.setJNTax("0");
						commonDsrTravelReportData.setOBTax("0");
						commonDsrTravelReportData.setOCTax("0");
						commonDsrTravelReportData.setK3Tax("0");
						commonDsrTravelReportData.setDriverAllowancedayCharge("0.00");
						commonDsrTravelReportData.setDriverAllowanceNightCharge("0.00");
						commonDsrTravelReportData.setTollorParkingCharge("0.00");
						commonDsrTravelReportData.setExtraKmCharge("0.00");
						commonDsrTravelReportData.setExtraHourCharge("0.00");
						BaseServiceTax = grossFare.add(managementFeePerRoom).setScale(2, BigDecimal.ROUND_UP).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax():new BigDecimal(0));
						BigDecimal swachBharatCess = new BigDecimal("0");
						swachBharatCess = grossFare.add(managementFeePerRoom).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
						BigDecimal krishiKalyanCess = new BigDecimal("0");
						krishiKalyanCess = grossFare.add(managementFeePerRoom).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
						BigDecimal TotalServiceTax = new BigDecimal("0");
						TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax()!=null?hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax():new BigDecimal(0));
						//BigDecimal convenienceFee = new BigDecimal("0");
						//BigDecimal managementFee = new BigDecimal("0");
						//convenienceFee=hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()!=null? hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
						//managementFee=hotelOrderRow.getHotelOrderRowServiceTax()!=null && hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()!=null? hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee():new BigDecimal(0);
						if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
							netFare=grossFare.add(gstPerRoom).add(managementFeePerRoom).add(convenienceFeePerRoom);
						else
							netFare = grossFare.add(TotalServiceTax).add(managementFeePerRoom).add(convenienceFeePerRoom);

						commonDsrTravelReportData.setTayyarahServiceCharges(managementFeePerRoom.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(2, BigDecimal.ROUND_UP).toString());//otherTaxesWithMarkup.setScale(0, RoundingMode.UP).toString()
						commonDsrTravelReportData.setCGST(CGSTPerRoom.setScale(2,BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPerRoom.setScale(2,BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setExtraCharge("0");
						commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
						commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setConvenienceCharge(convenienceFeePerRoom.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setDiscount(roomDiscountInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
						PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(hotelOrderRow.getOrderReference());
						if(paymentTransaction!=null)
							commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

						commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
						commonDsrTravelReportData.setPersonalBooking("No");
						String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
						commonDsrTravelReportData.setCorporateCurrency(currencyCode);
						commonDsrTravelReportData.setINTax("0");
						commonDsrTravelReportData.setVfsCharges("0");
						commonDsrTravelReportData.setCourierCharges("0");
						RmConfigTripDetailsModel 	rmConfigTripDetails=dsrRmConfigHelperDao.getLeadPersonRmDetails(hotelOrderRoomInfo.getHotelOrderGuests());
						if(rmConfigTripDetails!=null)
							if(hotelOrderRow.getHotelOrderRowRmConfigStruct()!=null && hotelOrderRow.getHotelOrderRowRmConfigStruct().getRmDynamicData()!=null)
								rmConfigTripDetails =dsrRmConfigHelperDao.getHotelPaxRmConfigTripDetails(rmConfigTripDetails,hotelOrderRow.getHotelOrderRowRmConfigStruct().getRmDynamicData());
							else
								rmConfigTripDetails =dsrRmConfigHelperDao.getHotelPaxRmConfigTripDetails(rmConfigTripDetails,null);
							else
								rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(hotelOrderRow.getOrderReference(),company.getCompanyid());

						if(rmConfigTripDetails!=null)
						{
							commonDsrTravelReportData.setApproverName(rmConfigTripDetails.getApproverName());
							commonDsrTravelReportData.setBillNonBill(rmConfigTripDetails.getBillNonBill());
							commonDsrTravelReportData.setBusinessUnit(rmConfigTripDetails.getBussinessUnit());
							commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
							commonDsrTravelReportData.setDepartment(rmConfigTripDetails.getDepartment());
							commonDsrTravelReportData.setEmpCode(rmConfigTripDetails.getEmpCode());
							commonDsrTravelReportData.setLocation(rmConfigTripDetails.getLocation());
							commonDsrTravelReportData.setProjectCode(rmConfigTripDetails.getProjectCode());
							commonDsrTravelReportData.setReasonForTravel(rmConfigTripDetails.getReasonForTravel());
							commonDsrTravelReportData.setTravelRequestNumber(rmConfigTripDetails.getTrNumber());
							if(sessionCompany.getCompanyRole().isSuperUser()){
								if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("All") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							}
							else 
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
						}
						commonDsrTravelReportData.setCreditnoteIssued(hotelOrderRow.isCreditNoteIssued());
						CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("H",hotelOrderRowObj, commonDsrTravelReportData);
						if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
							commonDsrTravelReportsNew.setBillingEntity(billingEntity);
							commonDsrTravelReports.add(commonDsrTravelReportsNew);

						}
					}
				}
			}
		}




		if(carOrderRowList!=null && carOrderRowList.size()>0){
			isOrderRowList=true;
			isCreditNoteList=false;
			for(CarOrderRow carOrderRow:carOrderRowList){
				String taxType=commonDsrUtility.getTaxType();
				CompanyEntity companyEntity=null;
				String billingEntity="-";
				if(carOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(carOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(carOrderRow!=null) 
					commonDsrDaoHelper.getCarCustomerRmList(commonDsrTravelReports, commonDsrPage, carOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);
			}
		}
		if(carOrderRowCreditList!=null && carOrderRowCreditList.size()>0){
			isOrderRowList=false;
			isCreditNoteList=true;
			for(CarOrderRow carOrderRow:carOrderRowCreditList){
				String taxType=commonDsrUtility.getTaxType();
				CompanyEntity companyEntity=null;
				String billingEntity="-";
				if(carOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(carOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(carOrderRow!=null ) 
					commonDsrDaoHelper.getCarCustomerRmList(commonDsrTravelReports, commonDsrPage, carOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);

			}
		}
		if(busOrderRowList!=null && busOrderRowList.size()>0){
			isOrderRowList=true;
			isCreditNoteList=false;
			for(BusOrderRow busOrderRow:busOrderRowList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(busOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(busOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(busOrderRow!=null ) 
					commonDsrDaoHelper.getBusCustomerRmList(commonDsrTravelReports, commonDsrPage, busOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);
			}
		}


		if(busOrderRowCreditList!=null && busOrderRowCreditList.size()>0){
			isOrderRowList=false;
			isCreditNoteList=true;
			for(BusOrderRow busOrderRow:busOrderRowCreditList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(busOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(busOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(busOrderRow!=null)
					commonDsrDaoHelper.getBusCustomerRmList(commonDsrTravelReports, commonDsrPage, busOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);
			}
		}



		if(trainOrderRowList!=null && trainOrderRowList.size()>0){
			isOrderRowList=true;
			isCreditNoteList=false;
			for(TrainOrderRow trainOrderRow:trainOrderRowList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(trainOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(trainOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(trainOrderRow!=null ) 
					commonDsrDaoHelper.getTrainCustomerRmList(commonDsrTravelReports, commonDsrPage, trainOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);
			}
		}

		if(trainOrderRowCreditList!=null && trainOrderRowCreditList.size()>0){
			isOrderRowList=false;
			isCreditNoteList=true;
			for(TrainOrderRow trainOrderRow:trainOrderRowCreditList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(trainOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(trainOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(trainOrderRow!=null ) 
					commonDsrDaoHelper.getTrainCustomerRmList(commonDsrTravelReports, commonDsrPage, trainOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);

			}
		}


		if(visaOrderRowList!=null && visaOrderRowList.size()>0){
			isOrderRowList=true;
			isCreditNoteList=false;
			for(VisaOrderRow visaOrderRow:visaOrderRowList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(visaOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(visaOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(visaOrderRow!=null ) 
					commonDsrDaoHelper.getVisaCustomerRmList(commonDsrTravelReports, commonDsrPage, visaOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);

			}
		}

		if(visaOrderRowCreditList!=null && visaOrderRowCreditList.size()>0){
			isOrderRowList=false;
			isCreditNoteList=true;
			for(VisaOrderRow visaOrderRow:visaOrderRowCreditList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(visaOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(visaOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(visaOrderRow!=null ) 
					commonDsrDaoHelper.getVisaCustomerRmList(commonDsrTravelReports, commonDsrPage, visaOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);

			}
		}

		if(insuranceOrderRowList!=null && insuranceOrderRowList.size()>0){
			isOrderRowList=true;
			isCreditNoteList=false;
			for(InsuranceOrderRow insuranceOrderRow:insuranceOrderRowList){
				String taxType=commonDsrUtility.getTaxType();
				CompanyEntity companyEntity=null;
				String billingEntity="-";
				if(insuranceOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(insuranceOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(insuranceOrderRow!=null ) 
					commonDsrDaoHelper.getInsuranceCustomerRmList(commonDsrTravelReports, commonDsrPage, insuranceOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);


			}
		}

		if(insuranceOrderRowCreditList!=null && insuranceOrderRowCreditList.size()>0){
			isCreditNoteList=true;
			isOrderRowList=false;
			for(InsuranceOrderRow insuranceOrderRow:insuranceOrderRowCreditList){
				String taxType=commonDsrUtility.getTaxType();
				CompanyEntity companyEntity=null;
				String billingEntity="-";
				if(insuranceOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(insuranceOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(insuranceOrderRow!=null ) 
					commonDsrDaoHelper.getInsuranceCustomerRmList(commonDsrTravelReports, commonDsrPage, insuranceOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);

			}
		}

		if(miscellaneousOrderRowList!=null && miscellaneousOrderRowList.size()>0){
			isOrderRowList=true;
			isCreditNoteList=false;
			for(MiscellaneousOrderRow miscellaneousOrderRow:miscellaneousOrderRowList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(miscellaneousOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(miscellaneousOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(miscellaneousOrderRow!=null )
					commonDsrDaoHelper.getMiscellaneousCustomerRmList(commonDsrTravelReports, commonDsrPage, miscellaneousOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);

			}
		}


		if(miscellaneousOrderRowCreditList!=null && miscellaneousOrderRowCreditList.size()>0){
			isCreditNoteList=true;
			isOrderRowList=false;
			for(MiscellaneousOrderRow miscellaneousOrderRow:miscellaneousOrderRowCreditList){
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(miscellaneousOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(miscellaneousOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(miscellaneousOrderRow!=null )
					commonDsrDaoHelper.getMiscellaneousCustomerRmList(commonDsrTravelReports, commonDsrPage, miscellaneousOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, isCreditNoteList, isOrderRowList, newCompanyConfig);

			}
		}
		//sortDataByStringDate(commonDsrTravelReports);
		return commonDsrTravelReports;
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
}