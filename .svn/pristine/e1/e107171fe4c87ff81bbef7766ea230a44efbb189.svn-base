package com.common.dsr.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.dao.CarTravelRequestDao;
import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.insurance.model.InsuranceOrderCustomerDetail;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.CommonDsrPage;
import com.common.dsr.CommonDsrTravelReportData;
import com.common.dsr.helper.vo.CommonDsrHelperVO;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.utility.DateConversion;
import com.tayyarah.bus.model.BusOrderCustomerDetail;
import com.tayyarah.car.model.CarOrderCustomer;
import com.tayyarah.miscellaneous.model.MiscellaneousOrderCustomer;
import com.tayyarah.train.model.TrainOrderCustomer;
import com.tayyarah.visa.model.VisaOrderCustomer;

public class CommonDsrDaoSubHelper {
	DsrRmConfigHelperDao dsrRmConfigHelperDao=new DsrRmConfigHelperDao();
	public   CommonDsrTravelReportData  buildCarCustomerRmList(CommonDsrPage commonDsrPage,CarOrderRow  carOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,CompanyConfig newCompanyConfig,CommonDsrHelperVO commonDsrHelperVO,CarOrderCustomer  carOrderCustomer){
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		commonDsrTravelReportData.setBkgRef(carOrderRow.getOrderId()); 
		if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
			commonDsrTravelReportData.setSystemInvoiceId(carOrderRow.getInvoiceNo()); 
		else 
			commonDsrTravelReportData.setSystemInvoiceId("-"); 

		commonDsrTravelReportData.setBookingType(carOrderRow.getBookingMode());
		if(carOrderRow.isCreditNoteIssued()) 
			commonDsrTravelReportData.setAmendmentType("Confirmed");
		else
			commonDsrTravelReportData.setAmendmentType(carOrderRow.getStatusAction());
		commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(carOrderRow.getCreatedAt()));
		commonDsrTravelReportData.setBookingDate(carOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringToDate(carOrderRow.getBookingDate()):"-");
		Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(carOrderRow.getCompanyId()));
		User user=new UserDAO().GetUserProfile(Integer.parseInt(carOrderRow.getUserId().trim().equals("")?"0":carOrderRow.getUserId()));
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
		ApiProvider apiProvider=ApiProviderDao.getApiProviderDetailsByVendorName(carOrderRow.getSupplierName());
		if(apiProvider!=null && apiProvider.getApiProvider()!=null) 
			commonDsrTravelReportData.setSupplierCode(apiProvider.getApiProvider());
		else
			commonDsrTravelReportData.setSupplierCode("-");
		commonDsrTravelReportData.setSupplierName(carOrderRow.getSupplierName());
		commonDsrTravelReportData.setTaxType(taxType);
		commonDsrTravelReportData.setDriverAllowancedayCharge(commonDsrHelperVO.driverAllowanceDay.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setDriverAllowanceNightCharge(commonDsrHelperVO.driverAllowanceNight.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setTollorParkingCharge(commonDsrHelperVO.tollOrParkingCharges.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setExtraKmCharge(commonDsrHelperVO.extraKM.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setExtraHourCharge(commonDsrHelperVO.extraHours.setScale(2, BigDecimal.ROUND_UP).toString());

		commonDsrTravelReportData.setBaseFare(commonDsrHelperVO.basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		if (sessionCompany.getCompanyRole().isSuperUser())
			commonDsrTravelReportData.setSupplierCharge(commonDsrHelperVO.apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		else
			commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());
		if(carOrderRow.getCarOrderCustomerList()!=null && carOrderRow.getCarOrderCustomerList().size()>0) 
			commonDsrTravelReportData.setTraveller(carOrderCustomer.getTitle()+" "+carOrderCustomer.getFirstName()+" "+carOrderCustomer.getLastName());
		else
			commonDsrTravelReportData.setTraveller(carOrderRow.getOrderCustomer().getTitle()+" "+carOrderRow.getOrderCustomer().getFirstName()+" "+carOrderRow.getOrderCustomer().getLastName());
		commonDsrTravelReportData.setProductType("Other Products");
		commonDsrTravelReportData.setProductName("Car");
		String itineraryType=commonDsrTravelReportData.getProductName();
		commonDsrTravelReportData.setItineraryType(itineraryType);
		commonDsrTravelReportData.setProductCode("-");
		CarTravelRequestQuotation newObj=new CarTravelRequestDao().getCarQuotationDetails(carOrderRow.getId());
		if(newObj!=null) 
			commonDsrTravelReportData.setDescription("CarCompanyName:"+carOrderRow.getCarCompanyName()+",PickUp:"+newObj.getPickUp()+", DropOff:"+newObj.getDropOff());
		else
			commonDsrTravelReportData.setDescription("-");	

		commonDsrTravelReportData.setDescription2(carOrderRow.getRemarks());

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
		commonDsrTravelReportData.setMarkup(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());

		BigDecimal grossFare=commonDsrHelperVO.basePriceInBooking.add(commonDsrHelperVO.taxesPriceInBooking).add(commonDsrHelperVO.markup).add(commonDsrHelperVO.driverAllowanceNight).add(commonDsrHelperVO.driverAllowanceDay).add(commonDsrHelperVO.tollOrParkingCharges).add(commonDsrHelperVO.extraHours).add(commonDsrHelperVO.extraKM);
		commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setYQTax("0");
		commonDsrTravelReportData.setYRTax(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setPSFTax("0");
		commonDsrTravelReportData.setUDFTax("0");
		commonDsrTravelReportData.setJNTax("0");
		commonDsrTravelReportData.setOBTax("0");
		commonDsrTravelReportData.setOCTax("0");
		commonDsrTravelReportData.setK3Tax("0");
		commonDsrTravelReportData.setCGST(commonDsrHelperVO.CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSGSTorUGSTorIGST(commonDsrHelperVO.SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		BigDecimal TotalServiceTax = new BigDecimal("0");
		BigDecimal netFare=new BigDecimal("0");
		TotalServiceTax =commonDsrHelperVO.baseServiceTax.add(commonDsrHelperVO.swachBharatCess).add(commonDsrHelperVO.krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getTotalTax()!=null?carOrderRow.getCarOrderRowServiceTax().getTotalTax():new BigDecimal(0));

		if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
			netFare=grossFare.add(commonDsrHelperVO.gstOrSrviceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);
		else
			netFare = grossFare.add(TotalServiceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);

		commonDsrTravelReportData.setTotGstTax(commonDsrHelperVO.gstOrSrviceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setTayyarahServiceCharges(commonDsrHelperVO.managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setOtherTaxes(commonDsrHelperVO.taxesPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setExtraCharge("0");
		commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
		commonDsrTravelReportData.setServiceTaxBase(commonDsrHelperVO.baseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSwachBharatCess(commonDsrHelperVO.swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setKrishiKalyanCess(commonDsrHelperVO.krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setConvenienceCharge(commonDsrHelperVO.convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setDiscount("0");
		commonDsrTravelReportData.setNetFare(netFare.setScale(0, RoundingMode.UP).toString());
		commonDsrTravelReportData.setINTax("0");
		commonDsrTravelReportData.setVfsCharges("0");
		commonDsrTravelReportData.setCourierCharges("0");
		PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(carOrderRow.getConfirmationNumber());
		if(paymentTransaction!=null) 
			commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

		else 
			commonDsrTravelReportData.setModeOfPayment("-");
		commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
		commonDsrTravelReportData.setPersonalBooking("No");
		String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
		commonDsrTravelReportData.setCorporateCurrency(currencyCode);

		//commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
		RmConfigTripDetailsModel rmConfigTripDetails =null;
		if(carOrderCustomer!=null &&carOrderCustomer.getRmConfigTripDetailsModel()!=null ) 
			if(carOrderRow.getCarOrderRowRmConfigStruct()!=null && carOrderRow.getCarOrderRowRmConfigStruct().getRmDynamicData()!=null)
			rmConfigTripDetails =dsrRmConfigHelperDao.getCarPaxRmConfigTripDetails(carOrderCustomer.getRmConfigTripDetailsModel(),carOrderRow.getCarOrderRowRmConfigStruct().getRmDynamicData());
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getCarPaxRmConfigTripDetails(carOrderCustomer.getRmConfigTripDetailsModel(),null);
			else
			rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(carOrderRow.getOrderId(),company.getCompanyid());

		if(rmConfigTripDetails!=null)
		{
			if(sessionCompany.getCompanyRole().isSuperUser()){
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
		commonDsrTravelReportData.setCreditnoteIssued(carOrderRow.isCreditNoteIssued());
		return commonDsrTravelReportData;
	}
	
	public   CommonDsrTravelReportData  buildBusCustomerRmList(CommonDsrPage commonDsrPage,BusOrderRow  busOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,CompanyConfig newCompanyConfig,CommonDsrHelperVO commonDsrHelperVO,BusOrderCustomerDetail busOrderCustomer){
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		commonDsrTravelReportData.setBkgRef(busOrderRow.getOrderId()); 
		if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
			commonDsrTravelReportData.setSystemInvoiceId(busOrderRow.getInvoiceNo()); 
		else 
			commonDsrTravelReportData.setSystemInvoiceId("-"); 

		commonDsrTravelReportData.setBookingType(busOrderRow.getBookingMode());
		if(busOrderRow.isCreditNoteIssued()) 
			commonDsrTravelReportData.setAmendmentType("Confirmed");
		else
			commonDsrTravelReportData.setAmendmentType(busOrderRow.getStatusAction());
		commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(busOrderRow.getCreatedAt()));
		commonDsrTravelReportData.setBookingDate(busOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringToDate(busOrderRow.getBookingDate()):"-");
		Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(busOrderRow.getCompanyId()));
		User user=new UserDAO().GetUserProfile(Integer.parseInt(busOrderRow.getUserId()== null || busOrderRow.getUserId().trim().equals("")?"0":busOrderRow.getUserId()));
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
		ApiProvider apiProvider=ApiProviderDao.getApiProviderDetailsByVendorName(busOrderRow.getSupplierName());
		if(apiProvider!=null && apiProvider.getApiProvider()!=null) 
			commonDsrTravelReportData.setSupplierCode(apiProvider.getApiProvider());
		else
			commonDsrTravelReportData.setSupplierCode("-");
		commonDsrTravelReportData.setSupplierName(busOrderRow.getSupplierName());

		commonDsrTravelReportData.setTaxType(taxType);
		commonDsrTravelReportData.setBaseFare(commonDsrHelperVO.basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		if (sessionCompany.getCompanyRole().isSuperUser())
			commonDsrTravelReportData.setSupplierCharge(commonDsrHelperVO.apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		else
			commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());
	 
		if(busOrderRow.getBusOrderCustomerDetails()!=null && busOrderRow.getBusOrderCustomerDetails().size()>0) 
			commonDsrTravelReportData.setTraveller(busOrderCustomer.getTitle()+" "+busOrderCustomer.getFirstName()+" "+busOrderCustomer.getLastName());
		else
			commonDsrTravelReportData.setTraveller(busOrderRow.getOrderCustomer().getTitle()+" "+busOrderRow.getOrderCustomer().getFirstName()+" "+busOrderRow.getOrderCustomer().getLastName());
		commonDsrTravelReportData.setProductType("Other Products");
		commonDsrTravelReportData.setProductName("Bus");
		String itineraryType=commonDsrTravelReportData.getProductName();
		commonDsrTravelReportData.setItineraryType(itineraryType);
		commonDsrTravelReportData.setProductCode("-");
		BusTravelRequestQuotation newObj=new BusTravelRequestDao().getBusQuotationDetails(busOrderRow.getId());
		if(newObj!=null) 
			commonDsrTravelReportData.setDescription("Bus Type:"+newObj.getBusType()+",Location:"+newObj.getLocation()+", PickUp:"+newObj.getPickUp()+", DropOff:"+newObj.getDropOff());
		else
			commonDsrTravelReportData.setDescription("-");	
		commonDsrTravelReportData.setDescription2(busOrderRow.getRemarks());
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

		BigDecimal grossFare=commonDsrHelperVO.basePriceInBooking.add(commonDsrHelperVO.taxesPriceInBooking).add(commonDsrHelperVO.markup);
		commonDsrTravelReportData.setMarkup(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setYQTax("0");
		commonDsrTravelReportData.setYRTax(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setPSFTax("0");
		commonDsrTravelReportData.setUDFTax("0");
		commonDsrTravelReportData.setJNTax("0");
		commonDsrTravelReportData.setOBTax("0");
		commonDsrTravelReportData.setOCTax("0");
		commonDsrTravelReportData.setK3Tax("0");

		BigDecimal TotalServiceTax   =commonDsrHelperVO.baseServiceTax.setScale(2, BigDecimal.ROUND_UP).add(commonDsrHelperVO.swachBharatCess.setScale(2, BigDecimal.ROUND_UP)).add(commonDsrHelperVO.krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP)); 
		BigDecimal netFare=null;
		if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
			netFare=grossFare.add(commonDsrHelperVO.gstOrSrviceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);
		else
			netFare = grossFare.add(TotalServiceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);

		commonDsrTravelReportData.setCGST(commonDsrHelperVO.CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSGSTorUGSTorIGST(commonDsrHelperVO.SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setTotGstTax(commonDsrHelperVO.gstOrSrviceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setTayyarahServiceCharges(commonDsrHelperVO.managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setOtherTaxes(commonDsrHelperVO.taxesPriceInBooking.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setExtraCharge("0");
		commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
		commonDsrTravelReportData.setServiceTaxBase(commonDsrHelperVO.baseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSwachBharatCess(commonDsrHelperVO.swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setKrishiKalyanCess(commonDsrHelperVO.krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setConvenienceCharge(commonDsrHelperVO.convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setDiscount("0");
		commonDsrTravelReportData.setNetFare(netFare.setScale(0, RoundingMode.UP).toString());
		PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(busOrderRow.getConfirmationNumber());
		if(paymentTransaction!=null) 
			commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

		else
			commonDsrTravelReportData.setModeOfPayment("-");
		commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
		commonDsrTravelReportData.setPersonalBooking("No");
		String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
		commonDsrTravelReportData.setCorporateCurrency(currencyCode);
		//commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
		RmConfigTripDetailsModel rmConfigTripDetails =null;
		if(busOrderCustomer!=null && busOrderCustomer.getRmConfigTripDetailsModel()!=null)
			if(busOrderRow.getBusOrderRowRmConfigStruct()!=null && busOrderRow.getBusOrderRowRmConfigStruct().getRmDynamicData()!=null)
				rmConfigTripDetails =dsrRmConfigHelperDao.getBusPaxRmConfigTripDetails(busOrderCustomer.getRmConfigTripDetailsModel(),busOrderRow.getBusOrderRowRmConfigStruct().getRmDynamicData());
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getBusPaxRmConfigTripDetails(busOrderCustomer.getRmConfigTripDetailsModel(),null);
			else
			rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(busOrderRow.getOrderId(),company.getCompanyid());
		if(rmConfigTripDetails!=null)
		{
			if(sessionCompany.getCompanyRole().isSuperUser()){
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
		commonDsrTravelReportData.setINTax("0");
		commonDsrTravelReportData.setVfsCharges("0");
		commonDsrTravelReportData.setCourierCharges("0");
		commonDsrTravelReportData.setCreditnoteIssued(busOrderRow.isCreditNoteIssued());
		 
		return commonDsrTravelReportData;
		
	}
	public  CommonDsrTravelReportData  buildTrainCustomerRmList(CommonDsrPage commonDsrPage,TrainOrderRow  trainOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,CompanyConfig newCompanyConfig,CommonDsrHelperVO commonDsrHelperVO,TrainOrderCustomer  trainOrderCustomer){
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		commonDsrTravelReportData.setBkgRef(trainOrderRow.getOrderId()); 
		if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
			commonDsrTravelReportData.setSystemInvoiceId(trainOrderRow.getInvoiceNo()); 
		else 
			commonDsrTravelReportData.setSystemInvoiceId("-"); 

		commonDsrTravelReportData.setBookingType(trainOrderRow.getBookingMode());
		if(trainOrderRow.isCreditNoteIssued()) 
			commonDsrTravelReportData.setAmendmentType("Confirmed");
		else
			commonDsrTravelReportData.setAmendmentType(trainOrderRow.getStatusAction());
		commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(trainOrderRow.getCreatedAt()));
		commonDsrTravelReportData.setBookingDate(trainOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringToDate(trainOrderRow.getBookingDate()):"-");
		Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(trainOrderRow.getCompanyId()));
		User user=new UserDAO().GetUserProfile(Integer.parseInt(trainOrderRow.getUserId().trim().equals("")?"0":trainOrderRow.getUserId()));
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
		ApiProvider apiProvider=ApiProviderDao.getApiProviderDetailsByVendorName(trainOrderRow.getSupplierName());
		if(apiProvider!=null && apiProvider.getApiProvider()!=null) 
			commonDsrTravelReportData.setSupplierCode(apiProvider.getApiProvider());
		else
			commonDsrTravelReportData.setSupplierCode("-");
		commonDsrTravelReportData.setSupplierName(trainOrderRow.getSupplierName());


		BigDecimal grossFare=null;
		BigDecimal netFare=null;
		commonDsrTravelReportData.setTaxType(taxType);


		commonDsrTravelReportData.setBaseFare(commonDsrHelperVO.basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		if (sessionCompany.getCompanyRole().isSuperUser())
			commonDsrTravelReportData.setSupplierCharge(commonDsrHelperVO.apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		else
			commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());
		if(trainOrderRow.getTrainOrderCustomerList()!=null && trainOrderRow.getTrainOrderCustomerList().size()>0) 
			commonDsrTravelReportData.setTraveller(trainOrderCustomer.getTitle()+" "+trainOrderCustomer.getFirstName()+" "+trainOrderCustomer.getLastName());
		else
			commonDsrTravelReportData.setTraveller(trainOrderRow.getOrderCustomer().getTitle()+" "+trainOrderRow.getOrderCustomer().getFirstName()+" "+trainOrderRow.getOrderCustomer().getLastName());
		commonDsrTravelReportData.setProductType("Other Products");
		commonDsrTravelReportData.setProductName("Train");
		String itineraryType=commonDsrTravelReportData.getProductName();
		commonDsrTravelReportData.setItineraryType(itineraryType);
		commonDsrTravelReportData.setProductCode("-");
		TrainTravelRequestQuotation newObj=new TrainTravelRequestDao().getTrainQuotationDetails(trainOrderRow.getId());
		if(newObj!=null) 
			commonDsrTravelReportData.setDescription("TrainNumber:"+newObj.getTrainNumber()+" ,Ticket Type:"+trainOrderRow.getTicketType()+",From:"+newObj.getFromlocation()+", To:"+newObj.getTolocation());
		else
			commonDsrTravelReportData.setDescription("-");	
		commonDsrTravelReportData.setDescription2(trainOrderRow.getRemarks());
		//commonDsrTravelReportData.setDescription(trainOrderRow.getRemarks());
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
		commonDsrTravelReportData.setMarkup(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());

		grossFare=commonDsrHelperVO.basePriceInBooking.add(commonDsrHelperVO.taxesPriceInBooking).add(commonDsrHelperVO.markup);
		commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setYQTax("0");
		commonDsrTravelReportData.setYRTax(trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setPSFTax("0");
		commonDsrTravelReportData.setUDFTax("0");
		commonDsrTravelReportData.setJNTax("0");
		commonDsrTravelReportData.setOBTax("0");
		commonDsrTravelReportData.setOCTax("0");
		commonDsrTravelReportData.setK3Tax("0");
		//BaseServiceTax = managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getBasicTax()!=null?trainOrderRow.getTrainOrderRowServiceTax().getBasicTax():new BigDecimal(0));

		BigDecimal TotalServiceTax = new BigDecimal("0");
		TotalServiceTax =commonDsrHelperVO.baseServiceTax.add(commonDsrHelperVO.swachBharatCess).add(commonDsrHelperVO.krishiKalyanCess); 
		if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
			netFare=grossFare.add(commonDsrHelperVO.gstOrSrviceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);
		else
			netFare = grossFare.add(TotalServiceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);

		commonDsrTravelReportData.setCGST(commonDsrHelperVO.CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSGSTorUGSTorIGST(commonDsrHelperVO.SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setTotGstTax(commonDsrHelperVO.gstOrSrviceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setTayyarahServiceCharges(commonDsrHelperVO.managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setOtherTaxes(commonDsrHelperVO.taxesPriceInBooking.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setExtraCharge("0");
		commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
		commonDsrTravelReportData.setServiceTaxBase(commonDsrHelperVO.baseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSwachBharatCess(commonDsrHelperVO.swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setKrishiKalyanCess(commonDsrHelperVO.krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setConvenienceCharge(commonDsrHelperVO.convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setDiscount("0");
		commonDsrTravelReportData.setNetFare(netFare.setScale(0, RoundingMode.UP).toString());
		commonDsrTravelReportData.setINTax("0");
		commonDsrTravelReportData.setVfsCharges("0");
		commonDsrTravelReportData.setCourierCharges("0");
		PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(trainOrderRow.getConfirmationNumber());
		if(paymentTransaction!=null) 
			commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

		else
			commonDsrTravelReportData.setModeOfPayment("-");
		commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
		commonDsrTravelReportData.setPersonalBooking("No");
		String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
		commonDsrTravelReportData.setCorporateCurrency(currencyCode);
		//commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
		RmConfigTripDetailsModel rmConfigTripDetails =null;
		if(trainOrderCustomer!=null && trainOrderCustomer.getRmConfigTripDetailsModel()!=null) 
			if(trainOrderRow.getTrainOrderRowRmConfigStruct()!=null && trainOrderRow.getTrainOrderRowRmConfigStruct().getRmDynamicData()!=null)
				rmConfigTripDetails =dsrRmConfigHelperDao.getTrainPaxRmConfigTripDetails(trainOrderCustomer.getRmConfigTripDetailsModel(),trainOrderRow.getTrainOrderRowRmConfigStruct().getRmDynamicData());
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getTrainPaxRmConfigTripDetails(trainOrderCustomer.getRmConfigTripDetailsModel(),null);
			else
			rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(trainOrderRow.getOrderId(),company.getCompanyid());
		if(rmConfigTripDetails!=null)
		{
			if(sessionCompany.getCompanyRole().isSuperUser()){
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
		commonDsrTravelReportData.setCreditnoteIssued(trainOrderRow.isCreditNoteIssued());
		return commonDsrTravelReportData;
		
	}
	public  CommonDsrTravelReportData  buildVisaCustomerRmList(CommonDsrPage commonDsrPage,VisaOrderRow  visaOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,CompanyConfig newCompanyConfig,CommonDsrHelperVO commonDsrHelperVO,VisaOrderCustomer visaOrderCustomer){
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		commonDsrTravelReportData.setBkgRef(visaOrderRow.getOrderId()); 
		if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
			commonDsrTravelReportData.setSystemInvoiceId(visaOrderRow.getInvoiceNo()); 
		else 
			commonDsrTravelReportData.setSystemInvoiceId("-"); 

		commonDsrTravelReportData.setBookingType(visaOrderRow.getBookingMode());
		if(visaOrderRow.isCreditNoteIssued()) 
			commonDsrTravelReportData.setAmendmentType("Confirmed");
		else
			commonDsrTravelReportData.setAmendmentType(visaOrderRow.getStatusAction());
		commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(visaOrderRow.getCreatedAt()));
		commonDsrTravelReportData.setBookingDate(visaOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringToDate(visaOrderRow.getBookingDate()):"-");
		Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(visaOrderRow.getCompanyId()));
		User user=new UserDAO().GetUserProfile(Integer.parseInt(visaOrderRow.getUserId().trim().equals("")?"0":visaOrderRow.getUserId()));
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
		ApiProvider apiProvider=ApiProviderDao.getApiProviderDetailsByVendorName(visaOrderRow.getSupplierName());
		if(apiProvider!=null && apiProvider.getApiProvider()!=null) 
			commonDsrTravelReportData.setSupplierCode(apiProvider.getApiProvider());
		else
			commonDsrTravelReportData.setSupplierCode("-");
		commonDsrTravelReportData.setSupplierName(visaOrderRow.getSupplierName());
		BigDecimal grossFare=null;
		BigDecimal netFare=null;
		commonDsrTravelReportData.setTaxType(taxType);
		commonDsrTravelReportData.setBaseFare(commonDsrHelperVO.basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		if (sessionCompany.getCompanyRole().isSuperUser())
			commonDsrTravelReportData.setSupplierCharge(commonDsrHelperVO.apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		else
			commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

		if(visaOrderRow.getVisaOrderCustomerList()!=null && visaOrderRow.getVisaOrderCustomerList().size()>0) 
			commonDsrTravelReportData.setTraveller(visaOrderCustomer.getTitle()+" "+visaOrderCustomer.getFirstName()+" "+visaOrderCustomer.getLastName());
		else
			commonDsrTravelReportData.setTraveller(visaOrderRow.getOrderCustomer().getTitle()+" "+visaOrderRow.getOrderCustomer().getFirstName()+" "+visaOrderRow.getOrderCustomer().getLastName());
		commonDsrTravelReportData.setProductType("Other Products");
		commonDsrTravelReportData.setProductName("Visa");
		String itineraryType=commonDsrTravelReportData.getProductName();
		commonDsrTravelReportData.setItineraryType(itineraryType);
		commonDsrTravelReportData.setProductCode("-");
		commonDsrTravelReportData.setDescription("-");
		commonDsrTravelReportData.setDescription2(visaOrderRow.getRemarks());

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
		grossFare=commonDsrHelperVO.basePriceInBooking.add(commonDsrHelperVO.taxesPriceInBooking).add(commonDsrHelperVO.markup).add(commonDsrHelperVO.vfsCharges).add(commonDsrHelperVO.courierCharges);
		commonDsrTravelReportData.setMarkup(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setYQTax("0");
		commonDsrTravelReportData.setYRTax(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setPSFTax("0");
		commonDsrTravelReportData.setUDFTax("0");
		commonDsrTravelReportData.setJNTax("0");
		commonDsrTravelReportData.setOBTax("0");
		commonDsrTravelReportData.setOCTax("0");
		commonDsrTravelReportData.setK3Tax("0");
		BigDecimal TotalServiceTax = new BigDecimal("0");
		TotalServiceTax =commonDsrHelperVO.baseServiceTax.add(commonDsrHelperVO.swachBharatCess).add(commonDsrHelperVO.krishiKalyanCess); 
		if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
			netFare=grossFare.add(commonDsrHelperVO.gstOrSrviceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);
		else
			netFare = grossFare.add(TotalServiceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);

		commonDsrTravelReportData.setCGST(commonDsrHelperVO.CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSGSTorUGSTorIGST(commonDsrHelperVO.SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());

		commonDsrTravelReportData.setTotGstTax(commonDsrHelperVO.gstOrSrviceTax.setScale(2, BigDecimal.ROUND_UP).toString());

		commonDsrTravelReportData.setTayyarahServiceCharges(commonDsrHelperVO.managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setOtherTaxes(commonDsrHelperVO.taxesPriceInBooking.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setCourierCharges(commonDsrHelperVO.courierCharges.setScale(0, RoundingMode.UP).toString());
		commonDsrTravelReportData.setVfsCharges(commonDsrHelperVO.vfsCharges.setScale(0, RoundingMode.UP).toString());
		commonDsrTravelReportData.setExtraCharge("0");
		commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
		commonDsrTravelReportData.setServiceTaxBase(commonDsrHelperVO.baseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSwachBharatCess(commonDsrHelperVO.swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setKrishiKalyanCess(commonDsrHelperVO.krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setConvenienceCharge(commonDsrHelperVO.convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setDiscount("0");
		commonDsrTravelReportData.setNetFare(netFare.setScale(0, RoundingMode.UP).toString());
		commonDsrTravelReportData.setINTax("0");
		PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(visaOrderRow.getConfirmationNumber());
		if(paymentTransaction!=null) 
			commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

		else
			commonDsrTravelReportData.setModeOfPayment("-");
		commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());

		commonDsrTravelReportData.setPersonalBooking("No");
		String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
		commonDsrTravelReportData.setCorporateCurrency(currencyCode);
		//commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
		RmConfigTripDetailsModel rmConfigTripDetails =null;
		if(visaOrderCustomer!=null && visaOrderCustomer.getRmConfigTripDetailsModel()!=null) 
			if(visaOrderRow.getVisaOrderRowRmConfigStruct()!=null && visaOrderRow.getVisaOrderRowRmConfigStruct().getRmDynamicData()!=null)
				rmConfigTripDetails =dsrRmConfigHelperDao.getVisaPaxRmConfigTripDetails(visaOrderCustomer.getRmConfigTripDetailsModel(),visaOrderRow.getVisaOrderRowRmConfigStruct().getRmDynamicData());
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getVisaPaxRmConfigTripDetails(visaOrderCustomer.getRmConfigTripDetailsModel(),null);
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(visaOrderRow.getOrderId(),company.getCompanyid());
		if(rmConfigTripDetails!=null )
		{
			if(sessionCompany.getCompanyRole().isSuperUser()){
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
		commonDsrTravelReportData.setCreditnoteIssued(visaOrderRow.isCreditNoteIssued());
		return commonDsrTravelReportData;
		
	}
	
	public  CommonDsrTravelReportData  buildInsuranceCustomerRmList(CommonDsrPage commonDsrPage,InsuranceOrderRow  insuranceOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,CompanyConfig newCompanyConfig,CommonDsrHelperVO commonDsrHelperVO,InsuranceOrderCustomerDetail insuranceOrderCustomer){
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		commonDsrTravelReportData.setBkgRef(insuranceOrderRow.getOrderId()); 
		if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
			commonDsrTravelReportData.setSystemInvoiceId(insuranceOrderRow.getInvoiceNo()); 
		else 
			commonDsrTravelReportData.setSystemInvoiceId("-"); 

		commonDsrTravelReportData.setBookingType(insuranceOrderRow.getBookingMode());
		if(insuranceOrderRow.isCreditNoteIssued()) 
			commonDsrTravelReportData.setAmendmentType("Confirmed");
		else
			commonDsrTravelReportData.setAmendmentType(insuranceOrderRow.getStatusAction());
		commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getCreatedAt()));
		commonDsrTravelReportData.setBookingDate(insuranceOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringToDate(insuranceOrderRow.getBookingDate()):"-");
		Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(insuranceOrderRow.getCompanyId()));
		User user=new UserDAO().GetUserProfile(Integer.parseInt(insuranceOrderRow.getUserId().trim().equals("")?"0":insuranceOrderRow.getUserId()));
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
		ApiProvider apiProvider=ApiProviderDao.getApiProviderDetailsByVendorName(insuranceOrderRow.getSupplierName());
		if(apiProvider!=null && apiProvider.getApiProvider()!=null) 
			commonDsrTravelReportData.setSupplierCode(apiProvider.getApiProvider());
		else
			commonDsrTravelReportData.setSupplierCode("-");
		commonDsrTravelReportData.setSupplierName(insuranceOrderRow.getSupplierName());
		BigDecimal grossFare=null;
		BigDecimal netFare=null;
		commonDsrTravelReportData.setTaxType(taxType);
		commonDsrTravelReportData.setBaseFare(commonDsrHelperVO.basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		if (sessionCompany.getCompanyRole().isSuperUser())
			commonDsrTravelReportData.setSupplierCharge(commonDsrHelperVO.apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		else
			commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

		if(insuranceOrderRow.getInsuranceOrderCustomerDetails() !=null && insuranceOrderRow.getInsuranceOrderCustomerDetails().size()>0) 
			commonDsrTravelReportData.setTraveller(insuranceOrderCustomer.getTitle()+" "+insuranceOrderCustomer.getFirstName()+" "+insuranceOrderCustomer.getLastName());
		else
			commonDsrTravelReportData.setTraveller(insuranceOrderRow.getOrderCustomer().getTitle()+" "+insuranceOrderRow.getOrderCustomer().getFirstName()+" "+insuranceOrderRow.getOrderCustomer().getLastName());
		commonDsrTravelReportData.setProductType("Other Products");
		commonDsrTravelReportData.setProductName("Insurance");
		String itineraryType=commonDsrTravelReportData.getProductName();
		commonDsrTravelReportData.setItineraryType(itineraryType);
		commonDsrTravelReportData.setProductCode("-");
		if(insuranceOrderRow.getCountryOfTravel()==true) 
			commonDsrTravelReportData.setDescription("INCLUDE US AND CANADA");
		else
			commonDsrTravelReportData.setDescription("EXCLUDE US AND CANADA");
		commonDsrTravelReportData.setDescription2(insuranceOrderRow.getRemarks());
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
		commonDsrTravelReportData.setCGST(commonDsrHelperVO.CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSGSTorUGSTorIGST(commonDsrHelperVO.SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		grossFare=commonDsrHelperVO.basePriceInBooking.add(commonDsrHelperVO.taxesPriceInBooking).add(commonDsrHelperVO.markup);
		commonDsrTravelReportData.setMarkup(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setYQTax("0");
		commonDsrTravelReportData.setYRTax(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setPSFTax("0");
		commonDsrTravelReportData.setUDFTax("0");
		commonDsrTravelReportData.setJNTax("0");
		commonDsrTravelReportData.setOBTax("0");
		commonDsrTravelReportData.setOCTax("0");
		commonDsrTravelReportData.setK3Tax("0");
		BigDecimal swachBharatCess = new BigDecimal("0");
		BigDecimal krishiKalyanCess = new BigDecimal("0");
		BigDecimal TotalServiceTax = new BigDecimal("0");
		TotalServiceTax =commonDsrHelperVO.baseServiceTax.add(swachBharatCess).add(krishiKalyanCess); 
		if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
			netFare=grossFare.add(commonDsrHelperVO.gstOrSrviceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);
		else
			netFare = grossFare.add(TotalServiceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);
		commonDsrTravelReportData.setTotGstTax(commonDsrHelperVO.gstOrSrviceTax.setScale(2, BigDecimal.ROUND_UP).toString());

		commonDsrTravelReportData.setTayyarahServiceCharges(commonDsrHelperVO.managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setOtherTaxes(commonDsrHelperVO.taxesPriceInBooking.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setExtraCharge("0");
		commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
		commonDsrTravelReportData.setServiceTaxBase(commonDsrHelperVO.baseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setConvenienceCharge(commonDsrHelperVO.convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setDiscount("0");
		commonDsrTravelReportData.setNetFare(netFare.setScale(0, RoundingMode.UP).toString());
		PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(insuranceOrderRow.getConfirmationNumber());
		if(paymentTransaction!=null) 
			commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

		else
			commonDsrTravelReportData.setModeOfPayment("-");
		commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
		commonDsrTravelReportData.setPersonalBooking("No");
		String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
		commonDsrTravelReportData.setCorporateCurrency(currencyCode);
		//commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
		commonDsrTravelReportData.setINTax("0");
		commonDsrTravelReportData.setVfsCharges("0");
		commonDsrTravelReportData.setCourierCharges("0");
		RmConfigTripDetailsModel rmConfigTripDetails =null;
		if(insuranceOrderCustomer!=null &&  insuranceOrderCustomer.getRmConfigTripDetailsModel()!=null) 
			if(insuranceOrderRow.getInsuranceOrderRowRmConfigStruct()!=null && insuranceOrderRow.getInsuranceOrderRowRmConfigStruct().getRmDynamicData()!=null)
				rmConfigTripDetails =dsrRmConfigHelperDao.getInsurancePaxRmConfigTripDetails(insuranceOrderCustomer.getRmConfigTripDetailsModel(),insuranceOrderRow.getInsuranceOrderRowRmConfigStruct().getRmDynamicData());
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getInsurancePaxRmConfigTripDetails(insuranceOrderCustomer.getRmConfigTripDetailsModel(),null);
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(insuranceOrderRow.getOrderId(),company.getCompanyid());

		if(rmConfigTripDetails!=null)
		{
			if(sessionCompany.getCompanyRole().isSuperUser()){
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
		commonDsrTravelReportData.setCreditnoteIssued(insuranceOrderRow.isCreditNoteIssued());
		 
		return commonDsrTravelReportData;
		
	}
	public  CommonDsrTravelReportData  buildMiscellaneousCustomerRmList(CommonDsrPage commonDsrPage,MiscellaneousOrderRow  miscellaneousOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,CompanyConfig newCompanyConfig,CommonDsrHelperVO commonDsrHelperVO,MiscellaneousOrderCustomer  miscellaneousOrderCustomer){
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		commonDsrTravelReportData.setBkgRef(miscellaneousOrderRow.getOrderId()); 
		if (newCompanyObj.getCompanyRole().isSuperUser() || newCompanyObj.getCompanyRole().isCorporate())
			commonDsrTravelReportData.setSystemInvoiceId(miscellaneousOrderRow.getInvoiceNo()); 
		else 
			commonDsrTravelReportData.setSystemInvoiceId("-"); 

		commonDsrTravelReportData.setBookingType(miscellaneousOrderRow.getBookingMode());
		if(miscellaneousOrderRow.isCreditNoteIssued()) 
			commonDsrTravelReportData.setAmendmentType("Confirmed");
		else
			commonDsrTravelReportData.setAmendmentType(miscellaneousOrderRow.getStatusAction());
		commonDsrTravelReportData.setInvoicedate(DateConversion.convertDateToStringToDate(miscellaneousOrderRow.getCreatedAt()));
		commonDsrTravelReportData.setBookingDate(miscellaneousOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDateddMMyyyy(miscellaneousOrderRow.getBookingDate()):"-");
		Company company=new CompanyDAO().getCompanyProfile(miscellaneousOrderRow.getCompanyId());
		User user=new UserDAO().GetUserProfile(miscellaneousOrderRow.getUserId());
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
		ApiProvider apiProvider=ApiProviderDao.getApiProviderDetailsByVendorName(miscellaneousOrderRow.getSupplierName());
		if(apiProvider!=null && apiProvider.getApiProvider()!=null) 
			commonDsrTravelReportData.setSupplierCode(apiProvider.getApiProvider());
		else
			commonDsrTravelReportData.setSupplierCode("-");
		commonDsrTravelReportData.setSupplierName(miscellaneousOrderRow.getSupplierName());

		BigDecimal grossFare=null;
		BigDecimal netFare=null;
		commonDsrTravelReportData.setTaxType(taxType);
		commonDsrTravelReportData.setBaseFare(commonDsrHelperVO.basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		if (sessionCompany.getCompanyRole().isSuperUser())
			commonDsrTravelReportData.setSupplierCharge(commonDsrHelperVO.apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
		else
			commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());
		if(miscellaneousOrderRow.getMiscellaneousOrderCustomerList() !=null && miscellaneousOrderRow.getMiscellaneousOrderCustomerList().size()>0) 
			commonDsrTravelReportData.setTraveller(miscellaneousOrderCustomer.getTitle()+" "+miscellaneousOrderCustomer.getFirstName()+" "+miscellaneousOrderCustomer.getLastName());
		else
			commonDsrTravelReportData.setTraveller(miscellaneousOrderRow.getOrderCustomer().getTitle()+" "+miscellaneousOrderRow.getOrderCustomer().getFirstName()+" "+miscellaneousOrderRow.getOrderCustomer().getLastName());
 		commonDsrTravelReportData.setProductType("Other Products");
		commonDsrTravelReportData.setProductName("Miscellaneous");
		String itineraryType=commonDsrTravelReportData.getProductName();
		commonDsrTravelReportData.setItineraryType(itineraryType);
		commonDsrTravelReportData.setProductCode("-");
		String details1=miscellaneousOrderRow.getDetails1()!=null && !miscellaneousOrderRow.getDetails1().equals("")?miscellaneousOrderRow.getDetails1():"NA";
		String details2=miscellaneousOrderRow.getDetails2()!=null && !miscellaneousOrderRow.getDetails2().equals("")?miscellaneousOrderRow.getDetails2():"NA";
		commonDsrTravelReportData.setDescription(details1+"/"+details2);
		commonDsrTravelReportData.setDescription2(miscellaneousOrderRow.getRemarks());
		commonDsrTravelReportData.setAirlinePNRorProvBooking("-");
		commonDsrTravelReportData.setGDSPNR("-");
		commonDsrTravelReportData.setTicketNumorFinalBooking(miscellaneousOrderRow.getConfirmationNumber());
		commonDsrTravelReportData.setFareType("-");
		commonDsrTravelReportData.setBookingRefundType("-");
		commonDsrTravelReportData.setFareBasis("-");
		commonDsrTravelReportData.setTotalNights("0");
		commonDsrTravelReportData.setTripStartsDate("-");
		commonDsrTravelReportData.setTripEndDate("-");
		commonDsrTravelReportData.setJourneyType("NA");
		commonDsrTravelReportData.setCGST(commonDsrHelperVO.CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSGSTorUGSTorIGST(commonDsrHelperVO.SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
		grossFare=commonDsrHelperVO.basePriceInBooking.add(commonDsrHelperVO.taxesPriceInBooking).add(commonDsrHelperVO.markup);
		commonDsrTravelReportData.setMarkup(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setYQTax("0");
		commonDsrTravelReportData.setYRTax(commonDsrHelperVO.markup.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setPSFTax("0");
		commonDsrTravelReportData.setUDFTax("0");
		commonDsrTravelReportData.setJNTax("0");
		commonDsrTravelReportData.setOBTax("0");
		commonDsrTravelReportData.setOCTax("0");
		commonDsrTravelReportData.setK3Tax("0");
		BigDecimal BaseServiceTax =new BigDecimal(0);/*miscellaneousOrderRow.getServiceTax()!=null?miscellaneousOrderRow.getServiceTax():*///basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getBasicTax()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getBasicTax():new BigDecimal(0));
		BigDecimal swachBharatCess = new BigDecimal("0");
		//swachBharatCess = basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getSwatchBharathCess()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
		BigDecimal krishiKalyanCess = new BigDecimal("0");
		//krishiKalyanCess = basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getKrishiKalyanCess()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
		BigDecimal TotalServiceTax = new BigDecimal("0");
		TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getTotalTax()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getTotalTax():new BigDecimal(0));
		if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
			netFare=grossFare.add(commonDsrHelperVO.gstOrSrviceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);
		else
			netFare = grossFare.add(TotalServiceTax).add(commonDsrHelperVO.convenienceFee).add(commonDsrHelperVO.managementFee);
		commonDsrTravelReportData.setTotGstTax(commonDsrHelperVO.gstOrSrviceTax.setScale(2, BigDecimal.ROUND_UP).toString());

		commonDsrTravelReportData.setTayyarahServiceCharges(commonDsrHelperVO.managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setOtherTaxes(commonDsrHelperVO.taxesPriceInBooking.setScale(2,BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setExtraCharge("0");
		commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
		commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setConvenienceCharge(commonDsrHelperVO.convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportData.setDiscount("0");
		commonDsrTravelReportData.setNetFare(netFare.setScale(0, RoundingMode.UP).toString());
		PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(miscellaneousOrderRow.getConfirmationNumber());
		if(paymentTransaction!=null) 
			commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

		else
			commonDsrTravelReportData.setModeOfPayment("-");
		commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
		commonDsrTravelReportData.setPersonalBooking("No");
		String currencyCode=company!=null && company.getCurrencyCode()!=null ?company.getCurrencyCode():"-";
		commonDsrTravelReportData.setCorporateCurrency(currencyCode);
		//commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
		commonDsrTravelReportData.setINTax("0");
		commonDsrTravelReportData.setVfsCharges("0");
		commonDsrTravelReportData.setCourierCharges("0");
		RmConfigTripDetailsModel rmConfigTripDetails =null;
		if(miscellaneousOrderCustomer!=null && miscellaneousOrderCustomer.getRmConfigTripDetailsModel()!=null)
			if(miscellaneousOrderRow.getMiscellaneousOrderRowRmConfigStruct()!=null && miscellaneousOrderRow.getMiscellaneousOrderRowRmConfigStruct().getRmDynamicData()!=null)
				rmConfigTripDetails =dsrRmConfigHelperDao.getMiscellaneousPaxRmConfigTripDetails(miscellaneousOrderCustomer.getRmConfigTripDetailsModel(),miscellaneousOrderRow.getMiscellaneousOrderRowRmConfigStruct().getRmDynamicData());
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getMiscellaneousPaxRmConfigTripDetails(miscellaneousOrderCustomer.getRmConfigTripDetailsModel(),null);
			else
				rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(miscellaneousOrderRow.getOrderId(),company.getCompanyid());

		if(rmConfigTripDetails!=null)
		{
			if(sessionCompany.getCompanyRole().isSuperUser()){
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
			if (rmConfigTripDetails.getCostCenter() !=null) 
				commonDsrTravelReportData.setCostCenter(rmConfigTripDetails.getCostCenter());
			if (rmConfigTripDetails.getDepartment() !=null) 
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
		commonDsrTravelReportData.setCreditnoteIssued(miscellaneousOrderRow.isCreditNoteIssued());
		return commonDsrTravelReportData;
		
	}
	
	
	
	
	
}
