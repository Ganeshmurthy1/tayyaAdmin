package com.common.dsr.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
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
import com.lintas.utility.ArithmeticUti;
import com.lintas.utility.DateConversion;
import com.tayyarah.bus.model.BusOrderCustomerDetail;
import com.tayyarah.car.model.CarOrderCustomer;
import com.tayyarah.miscellaneous.model.MiscellaneousOrderCustomer;
import com.tayyarah.train.model.TrainOrderCustomer;
import com.tayyarah.visa.model.VisaOrderCustomer;

public class CommonDsrDaoHelper {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CommonDsrDaoHelper.class);
	DsrRmConfigHelperDao dsrRmConfigHelperDao=new DsrRmConfigHelperDao();
	DsrRmConfigOrderCancelHelper dsrRmConfigOrderCancelHelper=new DsrRmConfigOrderCancelHelper();
	CommonDsrDaoSubHelper commonDsrDaoSubHelper=new CommonDsrDaoSubHelper();
	public  List<CommonDsrTravelReportData> getCarCustomerRmList(List<CommonDsrTravelReportData> commonDsrTravelReports,CommonDsrPage commonDsrPage,CarOrderRow  carOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,boolean isCreditNoteList,boolean isOrderRowList,CompanyConfig newCompanyConfig){
		Object carOrderRowObj= carOrderRow;
		int count=carOrderRow.getCarOrderCustomerList()!=null && carOrderRow.getCarOrderCustomerList().size()>0? carOrderRow.getCarOrderCustomerList().size():1;
		BigDecimal basePriceInBooking=carOrderRow.getBasePrice()!=null?carOrderRow.getBasePrice().multiply(carOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal apiPriceInBooking=carOrderRow.getSupplierPrice()!=null?carOrderRow.getSupplierPrice().multiply(carOrderRow.getApiToBaseExchangeRate()).multiply(carOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal taxesPriceInBooking  =carOrderRow.getOtherTaxes()!=null?carOrderRow.getOtherTaxes():new BigDecimal(0);
		BigDecimal markup= carOrderRow.getMarkUp()!=null?carOrderRow.getMarkUp():new BigDecimal(0);
		BigDecimal driverAllowanceNight=carOrderRow.getDriverAllowanceNight()!=null?carOrderRow.getDriverAllowanceNight(): new BigDecimal(0);
		BigDecimal driverAllowanceDay=carOrderRow.getDriverAllowanceDay()!=null?carOrderRow.getDriverAllowanceDay(): new BigDecimal(0);
		BigDecimal tollOrParkingCharges=carOrderRow.getTollOrParkingCharges()!=null?carOrderRow.getTollOrParkingCharges(): new BigDecimal(0);
		BigDecimal extraKM=carOrderRow.getExtraKM()!=null?new BigDecimal(carOrderRow.getExtraKM()):new BigDecimal("0");
		BigDecimal extraHours=carOrderRow.getExtraHours()!=null?new BigDecimal(carOrderRow.getExtraHours()):new BigDecimal("0");
		BigDecimal convenienceFee = carOrderRow.getConvenienceFee()!=null?carOrderRow.getConvenienceFee():new BigDecimal(0);
		BigDecimal  totalAmountAfterDeductconvenienceFee=carOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).subtract(convenienceFee.setScale(2, BigDecimal.ROUND_UP));
		BigDecimal managementFee =carOrderRow.getManagementFee()!=null?carOrderRow.getManagementFee():new BigDecimal(0);
		BigDecimal gstOrSrviceTax=carOrderRow.getTotalGstTax()!=null?carOrderRow.getTotalGstTax():new BigDecimal(0);
		BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
		BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
		BigDecimal baseServiceTax =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getBasicTax()!=null?carOrderRow.getCarOrderRowServiceTax().getBasicTax():new BigDecimal(0));
		BigDecimal swachBharatCess =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getSwatchBharathCess()!=null?carOrderRow.getCarOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
		BigDecimal krishiKalyanCess   = totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getKrishiKalyanCess()!=null?carOrderRow.getCarOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));

		if(carOrderRow.getCarOrderRowGstTax()!=null && newCompanyConfig!=null && taxType!=null && taxType.equalsIgnoreCase("GST")){ 
			CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowGstTax().getCGST());					  
			if(carOrderRow.getCarOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowGstTax().getSGST());					  
			if(carOrderRow.getCarOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowGstTax().getUGST());					  
			if(carOrderRow.getCarOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowGstTax().getIGST());					  
		}

		basePriceInBooking=ArithmeticUti.divideTo2Decimal(basePriceInBooking, new BigDecimal(count));
		apiPriceInBooking=ArithmeticUti.divideTo2Decimal(apiPriceInBooking, new BigDecimal(count));
		taxesPriceInBooking=ArithmeticUti.divideTo2Decimal(taxesPriceInBooking, new BigDecimal(count));
		markup=ArithmeticUti.divideTo2Decimal(markup, new BigDecimal(count));
		driverAllowanceNight=ArithmeticUti.divideTo2Decimal(driverAllowanceNight, new BigDecimal(count));
		driverAllowanceDay =ArithmeticUti.divideTo2Decimal(driverAllowanceDay, new BigDecimal(count));
		tollOrParkingCharges=ArithmeticUti.divideTo2Decimal(tollOrParkingCharges, new BigDecimal(count));
		extraKM=ArithmeticUti.divideTo2Decimal(extraKM, new BigDecimal(count));
		extraHours=ArithmeticUti.divideTo2Decimal(extraHours, new BigDecimal(count));
		convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(count));
		managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(count));
		gstOrSrviceTax=ArithmeticUti.divideTo2Decimal(gstOrSrviceTax, new BigDecimal(count));
		CGSTPerPassenger=ArithmeticUti.divideTo2Decimal(CGSTPerPassenger, new BigDecimal(count));
		SGSTorIGSTorUGSTPerPassenger=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger, new BigDecimal(count));
		baseServiceTax=ArithmeticUti.divideTo2Decimal(baseServiceTax, new BigDecimal(count));
		swachBharatCess=ArithmeticUti.divideTo2Decimal(swachBharatCess, new BigDecimal(count));
		krishiKalyanCess=ArithmeticUti.divideTo2Decimal(krishiKalyanCess, new BigDecimal(count));
		CommonDsrHelperVO commonDsrHelperVO=new CommonDsrHelperVO(basePriceInBooking, apiPriceInBooking, taxesPriceInBooking, markup, driverAllowanceNight, driverAllowanceDay, tollOrParkingCharges, extraKM, extraHours, convenienceFee, totalAmountAfterDeductconvenienceFee, managementFee, gstOrSrviceTax, CGSTPerPassenger, SGSTorIGSTorUGSTPerPassenger, baseServiceTax, swachBharatCess, krishiKalyanCess,null,null);
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		if(carOrderRow.getCarOrderCustomerList()!=null && carOrderRow.getCarOrderCustomerList().size()>0){
			for(CarOrderCustomer carOrderCustomer:carOrderRow.getCarOrderCustomerList()){
				commonDsrTravelReportData=commonDsrDaoSubHelper.buildCarCustomerRmList(commonDsrPage, carOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, carOrderCustomer);
				if(isOrderRowList)
					commonDsrTravelReports.add(commonDsrTravelReportData);
				if(isCreditNoteList){
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("C",carOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}
			}
		}
		else{
			commonDsrTravelReportData=commonDsrDaoSubHelper.buildCarCustomerRmList(commonDsrPage, carOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, null);
			if(isOrderRowList)
				commonDsrTravelReports.add(commonDsrTravelReportData);
			if(isCreditNoteList){
				CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("C",carOrderRowObj, commonDsrTravelReportData);
				if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
					commonDsrTravelReportsNew.setBillingEntity(billingEntity);
					commonDsrTravelReports.add(commonDsrTravelReportsNew);
				}
			}
		}
		return commonDsrTravelReports;
	}
	 
	public  List<CommonDsrTravelReportData> getBusCustomerRmList(List<CommonDsrTravelReportData> commonDsrTravelReports,CommonDsrPage commonDsrPage,BusOrderRow  busOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,boolean isCreditNoteList,boolean isOrderRowList,CompanyConfig newCompanyConfig){
		Object busOrderRowObj= busOrderRow;
		int count=busOrderRow.getBusOrderCustomerDetails().size();
		BigDecimal basePriceInBooking=busOrderRow.getBasePrice()!=null?busOrderRow.getBasePrice().multiply(busOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal apiPriceInBooking=busOrderRow.getSupplierPrice()!=null?busOrderRow.getSupplierPrice().multiply(busOrderRow.getApiToBaseExchangeRate()).multiply(busOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal taxesPriceInBooking=busOrderRow.getOtherTaxes()!=null?busOrderRow.getOtherTaxes().multiply(busOrderRow.getApiToBaseExchangeRate()).multiply(busOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal convenienceFee=busOrderRow.getConvenienceFee()!=null ?busOrderRow.getConvenienceFee():new BigDecimal(0);
		BigDecimal managementFee=busOrderRow.getManagementFee()!=null?busOrderRow.getManagementFee():new BigDecimal(0);
		BigDecimal gstOrSrviceTax  =busOrderRow.getTotalGstTax()!=null?busOrderRow.getTotalGstTax():new BigDecimal(0);
		BigDecimal markup=busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp():new BigDecimal("0");
		BigDecimal  totalAmountAfterDeductconvenienceFee=busOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).subtract(convenienceFee.setScale(2, BigDecimal.ROUND_UP));
		BigDecimal  baseServiceTax = totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getBasicTax()!=null?busOrderRow.getBusOrderRowServiceTax().getBasicTax():new BigDecimal(0));
		BigDecimal swachBharatCess  =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getSwatchBharathCess()!=null?busOrderRow.getBusOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
		BigDecimal krishiKalyanCess   =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getKrishiKalyanCess()!=null?busOrderRow.getBusOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
		BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
		BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
		if(busOrderRow.getBusOrderRowGstTax()!=null && taxType !=null && taxType .equalsIgnoreCase("GST")){ 
			CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowGstTax().getCGST());					  
			if(busOrderRow.getBusOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowGstTax().getSGST());					  
			if(busOrderRow.getBusOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowGstTax().getUGST());					  
			if(busOrderRow.getBusOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowGstTax().getIGST());					  
		}

		basePriceInBooking=ArithmeticUti.divideTo2Decimal(basePriceInBooking, new BigDecimal(count));
		apiPriceInBooking=ArithmeticUti.divideTo2Decimal(apiPriceInBooking, new BigDecimal(count));
		taxesPriceInBooking=ArithmeticUti.divideTo2Decimal(taxesPriceInBooking, new BigDecimal(count));
		markup=ArithmeticUti.divideTo2Decimal(markup, new BigDecimal(count));
		convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(count));
		managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(count));
		gstOrSrviceTax=ArithmeticUti.divideTo2Decimal(gstOrSrviceTax, new BigDecimal(count));
		CGSTPerPassenger=ArithmeticUti.divideTo2Decimal(CGSTPerPassenger, new BigDecimal(count));
		SGSTorIGSTorUGSTPerPassenger=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger, new BigDecimal(count));
		baseServiceTax=ArithmeticUti.divideTo2Decimal(baseServiceTax, new BigDecimal(count));
		swachBharatCess=ArithmeticUti.divideTo2Decimal(swachBharatCess, new BigDecimal(count));
		krishiKalyanCess=ArithmeticUti.divideTo2Decimal(krishiKalyanCess, new BigDecimal(count));
		CommonDsrHelperVO commonDsrHelperVO=new CommonDsrHelperVO(basePriceInBooking, apiPriceInBooking, taxesPriceInBooking, markup, null, null, null, null, null, convenienceFee, totalAmountAfterDeductconvenienceFee, managementFee, gstOrSrviceTax, CGSTPerPassenger, SGSTorIGSTorUGSTPerPassenger, baseServiceTax, swachBharatCess, krishiKalyanCess,null,null);
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		if(busOrderRow.getBusOrderCustomerDetails()!=null && busOrderRow.getBusOrderCustomerDetails().size()>0){
			for(BusOrderCustomerDetail busOrderCustomer: busOrderRow.getBusOrderCustomerDetails()){
				commonDsrTravelReportData=commonDsrDaoSubHelper.buildBusCustomerRmList(commonDsrPage, busOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, busOrderCustomer);
				if(isOrderRowList)
					commonDsrTravelReports.add(commonDsrTravelReportData);
				if(isCreditNoteList){
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("B",busOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}
			}
		}
		else{
			commonDsrTravelReportData=commonDsrDaoSubHelper.buildBusCustomerRmList(commonDsrPage, busOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, null);
			if(isOrderRowList)
				commonDsrTravelReports.add(commonDsrTravelReportData);
			if(isCreditNoteList){
				CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("B",busOrderRowObj, commonDsrTravelReportData);
				if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
					commonDsrTravelReportsNew.setBillingEntity(billingEntity);
					commonDsrTravelReports.add(commonDsrTravelReportsNew);
				}
			}
		}
		
		return commonDsrTravelReports;
	}
	 
	public  List<CommonDsrTravelReportData> getTrainCustomerRmList(List<CommonDsrTravelReportData> commonDsrTravelReports,CommonDsrPage commonDsrPage,TrainOrderRow  trainOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,boolean isCreditNoteList,boolean isOrderRowList,CompanyConfig newCompanyConfig){
		Object trainOrderRowObj= trainOrderRow;
		int count=trainOrderRow.getTrainOrderCustomerList().size();
		BigDecimal basePriceInBooking=trainOrderRow.getBasePrice()!=null?trainOrderRow.getBasePrice().multiply(trainOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal apiPriceInBooking=trainOrderRow.getSupplierPrice()!=null?trainOrderRow.getSupplierPrice().multiply(trainOrderRow.getApiToBaseExchangeRate()).multiply(trainOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal taxesPriceInBooking=trainOrderRow.getOtherTaxes()!=null?trainOrderRow.getOtherTaxes().multiply(trainOrderRow.getApiToBaseExchangeRate()).multiply(trainOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal convenienceFee=trainOrderRow.getConvenienceFee()!=null ?trainOrderRow.getConvenienceFee():new BigDecimal(0);
		BigDecimal managementFee=null;
		if(!trainOrderRow.getTicketType().equals("")){
			if(trainOrderRow.getTicketType().equalsIgnoreCase("tatkal"))
				managementFee=trainOrderRow.getManagementFeeTatkal()!=null ||!trainOrderRow.getManagementFeeTatkal().equals("")?trainOrderRow.getManagementFeeTatkal():new BigDecimal(0);
				if(trainOrderRow.getTicketType().equalsIgnoreCase("normal")) 
					managementFee=trainOrderRow.getManagementFee()!=null?trainOrderRow.getManagementFee():new BigDecimal(0);
		}
		else
			managementFee=new BigDecimal(0);

		BigDecimal gstOrSrviceTax  =trainOrderRow.getTotalGstTax()!=null?trainOrderRow.getTotalGstTax():new BigDecimal(0);
		BigDecimal markup=trainOrderRow.getMarkUp()!=null?trainOrderRow.getMarkUp():new BigDecimal("0");
		BigDecimal  totalAmountAfterDeductconvenienceFee=trainOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).subtract(convenienceFee.setScale(2, BigDecimal.ROUND_UP));
		BigDecimal  baseServiceTax = totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getBasicTax()!=null?trainOrderRow.getTrainOrderRowServiceTax().getBasicTax():new BigDecimal(0));
		BigDecimal swachBharatCess  =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getSwatchBharathCess()!=null?trainOrderRow.getTrainOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
		BigDecimal krishiKalyanCess   =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getKrishiKalyanCess()!=null?trainOrderRow.getTrainOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
		BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
		BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
		if(trainOrderRow.getTrainOrderRowGstTax()!=null && taxType !=null &&   taxType.equalsIgnoreCase("GST")){ 
			CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowGstTax().getCGST());					  
			if(trainOrderRow.getTrainOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowGstTax().getSGST());					  
			if(trainOrderRow.getTrainOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowGstTax().getUGST());					  
			if(trainOrderRow.getTrainOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowGstTax().getIGST());					  
		}

		basePriceInBooking=ArithmeticUti.divideTo2Decimal(basePriceInBooking, new BigDecimal(count));
		apiPriceInBooking=ArithmeticUti.divideTo2Decimal(apiPriceInBooking, new BigDecimal(count));
		taxesPriceInBooking=ArithmeticUti.divideTo2Decimal(taxesPriceInBooking, new BigDecimal(count));
		markup=ArithmeticUti.divideTo2Decimal(markup, new BigDecimal(count));
		convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(count));
		managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(count));
		gstOrSrviceTax=ArithmeticUti.divideTo2Decimal(gstOrSrviceTax, new BigDecimal(count));
		CGSTPerPassenger=ArithmeticUti.divideTo2Decimal(CGSTPerPassenger, new BigDecimal(count));
		SGSTorIGSTorUGSTPerPassenger=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger, new BigDecimal(count));
		baseServiceTax=ArithmeticUti.divideTo2Decimal(baseServiceTax, new BigDecimal(count));
		swachBharatCess=ArithmeticUti.divideTo2Decimal(swachBharatCess, new BigDecimal(count));
		krishiKalyanCess=ArithmeticUti.divideTo2Decimal(krishiKalyanCess, new BigDecimal(count));
		CommonDsrHelperVO commonDsrHelperVO=new CommonDsrHelperVO(basePriceInBooking, apiPriceInBooking, taxesPriceInBooking, markup, null, null, null, null, null, convenienceFee, totalAmountAfterDeductconvenienceFee, managementFee, gstOrSrviceTax, CGSTPerPassenger, SGSTorIGSTorUGSTPerPassenger, baseServiceTax, swachBharatCess, krishiKalyanCess,null,null);
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		if(trainOrderRow.getTrainOrderCustomerList()!=null && trainOrderRow.getTrainOrderCustomerList().size()>0){
			for(TrainOrderCustomer trainOrderCustomer: trainOrderRow.getTrainOrderCustomerList()){
				commonDsrTravelReportData=commonDsrDaoSubHelper.buildTrainCustomerRmList(commonDsrPage, trainOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, trainOrderCustomer);
				if(isOrderRowList)
					commonDsrTravelReports.add(commonDsrTravelReportData);
				if(isCreditNoteList){
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("T",trainOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}
			}
		}
		else{
			commonDsrTravelReportData=commonDsrDaoSubHelper.buildTrainCustomerRmList(commonDsrPage, trainOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, null);
			if(isOrderRowList)
				commonDsrTravelReports.add(commonDsrTravelReportData);
			if(isCreditNoteList){
				CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("T",trainOrderRowObj, commonDsrTravelReportData);
				if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
					commonDsrTravelReportsNew.setBillingEntity(billingEntity);
					commonDsrTravelReports.add(commonDsrTravelReportsNew);
				}
			}
			
		}
		return commonDsrTravelReports;
	}
	public  List<CommonDsrTravelReportData> getVisaCustomerRmList(List<CommonDsrTravelReportData> commonDsrTravelReports,CommonDsrPage commonDsrPage,VisaOrderRow  visaOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,boolean isCreditNoteList,boolean isOrderRowList,CompanyConfig newCompanyConfig){
		Object visaOrderRowObj= visaOrderRow;
		int count=visaOrderRow.getVisaOrderCustomerList().size();
		BigDecimal basePriceInBooking=visaOrderRow.getBasePrice()!=null?visaOrderRow.getBasePrice().multiply(visaOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal apiPriceInBooking=visaOrderRow.getSupplierPrice()!=null?visaOrderRow.getSupplierPrice().multiply(visaOrderRow.getApiToBaseExchangeRate()).multiply(visaOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal taxesPriceInBooking=visaOrderRow.getOtherTaxes()!=null?visaOrderRow.getOtherTaxes().multiply(visaOrderRow.getApiToBaseExchangeRate()).multiply(visaOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal convenienceFee=visaOrderRow.getConvenienceFee()!=null ?visaOrderRow.getConvenienceFee():new BigDecimal(0);
		BigDecimal managementFee =visaOrderRow.getManagementFee()!=null  ?visaOrderRow.getManagementFee():new BigDecimal(0);
		BigDecimal vfsCharges=visaOrderRow.getVfsCharges()!=null?visaOrderRow.getVfsCharges():new BigDecimal(0);
		BigDecimal courierCharges=visaOrderRow.getCourierCharges()!=null?visaOrderRow.getCourierCharges():new BigDecimal(0);
		BigDecimal gstOrSrviceTax  =visaOrderRow.getTotalGstTax()!=null?visaOrderRow.getTotalGstTax():new BigDecimal(0);
		BigDecimal markup=visaOrderRow.getMarkUp()!=null?visaOrderRow.getMarkUp():new BigDecimal("0");
		BigDecimal  totalAmountAfterDeductconvenienceFee=visaOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).subtract(convenienceFee.setScale(2, BigDecimal.ROUND_UP));
		BigDecimal  baseServiceTax = totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getBasicTax()!=null?visaOrderRow.getVisaOrderRowServiceTax().getBasicTax():new BigDecimal(0));
		BigDecimal swachBharatCess  =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getSwatchBharathCess()!=null?visaOrderRow.getVisaOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
		BigDecimal krishiKalyanCess   =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getKrishiKalyanCess()!=null?visaOrderRow.getVisaOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
		BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
		BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
		if(visaOrderRow.getVisaOrderRowGstTax()!=null && taxType !=null &&   taxType.equalsIgnoreCase("GST")){ 
			CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowGstTax().getCGST());					  
			if(visaOrderRow.getVisaOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowGstTax().getSGST());					  
			if(visaOrderRow.getVisaOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowGstTax().getUGST());					  
			if(visaOrderRow.getVisaOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowGstTax().getIGST());					  
		}
		basePriceInBooking=ArithmeticUti.divideTo2Decimal(basePriceInBooking, new BigDecimal(count));
		apiPriceInBooking=ArithmeticUti.divideTo2Decimal(apiPriceInBooking, new BigDecimal(count));
		taxesPriceInBooking=ArithmeticUti.divideTo2Decimal(taxesPriceInBooking, new BigDecimal(count));
		markup=ArithmeticUti.divideTo2Decimal(markup, new BigDecimal(count));
		convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(count));
		managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(count));
		gstOrSrviceTax=ArithmeticUti.divideTo2Decimal(gstOrSrviceTax, new BigDecimal(count));
		CGSTPerPassenger=ArithmeticUti.divideTo2Decimal(CGSTPerPassenger, new BigDecimal(count));
		SGSTorIGSTorUGSTPerPassenger=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger, new BigDecimal(count));
		baseServiceTax=ArithmeticUti.divideTo2Decimal(baseServiceTax, new BigDecimal(count));
		swachBharatCess=ArithmeticUti.divideTo2Decimal(swachBharatCess, new BigDecimal(count));
		krishiKalyanCess=ArithmeticUti.divideTo2Decimal(krishiKalyanCess, new BigDecimal(count));
		vfsCharges=ArithmeticUti.divideTo2Decimal(vfsCharges, new BigDecimal(count));
		courierCharges=ArithmeticUti.divideTo2Decimal(courierCharges, new BigDecimal(count));
		CommonDsrHelperVO commonDsrHelperVO=new CommonDsrHelperVO(basePriceInBooking, apiPriceInBooking, taxesPriceInBooking, markup, null, null, null, null, null, convenienceFee, totalAmountAfterDeductconvenienceFee, managementFee, gstOrSrviceTax, CGSTPerPassenger, SGSTorIGSTorUGSTPerPassenger, baseServiceTax, swachBharatCess, krishiKalyanCess,courierCharges,vfsCharges);
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		if(visaOrderRow.getVisaOrderCustomerList()!=null && visaOrderRow.getVisaOrderCustomerList().size()>0){
			for(VisaOrderCustomer visaOrderCustomer: visaOrderRow.getVisaOrderCustomerList()){
				commonDsrTravelReportData=commonDsrDaoSubHelper.buildVisaCustomerRmList(commonDsrPage, visaOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, visaOrderCustomer);
				if(isOrderRowList)
					commonDsrTravelReports.add(commonDsrTravelReportData);
				if(isCreditNoteList){
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("V",visaOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}
			}
		}
		else{
			commonDsrTravelReportData=commonDsrDaoSubHelper.buildVisaCustomerRmList(commonDsrPage, visaOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, null);
			if(isOrderRowList)
				commonDsrTravelReports.add(commonDsrTravelReportData);
			if(isCreditNoteList){
				CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("V",visaOrderRowObj, commonDsrTravelReportData);
				if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
					commonDsrTravelReportsNew.setBillingEntity(billingEntity);
					commonDsrTravelReports.add(commonDsrTravelReportsNew);
				}
			}
		}
		return commonDsrTravelReports;
	}
	
	public  List<CommonDsrTravelReportData> getInsuranceCustomerRmList(List<CommonDsrTravelReportData> commonDsrTravelReports,CommonDsrPage commonDsrPage,InsuranceOrderRow  insuranceOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,boolean isCreditNoteList,boolean isOrderRowList,CompanyConfig newCompanyConfig){
		Object insuranceOrderRowObj= insuranceOrderRow;
		int count=insuranceOrderRow.getInsuranceOrderCustomerDetails().size();
		BigDecimal basePriceInBooking=insuranceOrderRow.getBasePrice()!=null?insuranceOrderRow.getBasePrice().multiply(insuranceOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal apiPriceInBooking=insuranceOrderRow.getSupplierPrice()!=null?insuranceOrderRow.getSupplierPrice().multiply(insuranceOrderRow.getApiToBaseExchangeRate()).multiply(insuranceOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal taxesPriceInBooking  =insuranceOrderRow.getOtherTaxes()!=null?insuranceOrderRow.getOtherTaxes():new BigDecimal(0);
		BigDecimal markup= insuranceOrderRow.getMarkUpamount()!=null?insuranceOrderRow.getMarkUpamount():new BigDecimal(0);
		BigDecimal convenienceFee = insuranceOrderRow.getConvenienceFee()!=null?insuranceOrderRow.getConvenienceFee():new BigDecimal(0);
		BigDecimal managementFee =insuranceOrderRow.getManagementFee()!=null?insuranceOrderRow.getManagementFee():new BigDecimal(0);
		BigDecimal gstOrSrviceTax=insuranceOrderRow.getTotalGstTax()!=null?insuranceOrderRow.getTotalGstTax():new BigDecimal(0);
		BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
		BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
		BigDecimal baseServiceTax =insuranceOrderRow.getServiceTax()!=null?insuranceOrderRow.getServiceTax():new BigDecimal(0);
		if(insuranceOrderRow.getInsuranceOrderRowGstTax()!=null && newCompanyConfig!=null && taxType!=null && taxType.equalsIgnoreCase("GST")){ 
			CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowGstTax().getCGST());					  
			if(insuranceOrderRow.getInsuranceOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowGstTax().getSGST());					  
			if(insuranceOrderRow.getInsuranceOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowGstTax().getUGST());					  
			if(insuranceOrderRow.getInsuranceOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowGstTax().getIGST());					  
		}

		basePriceInBooking=ArithmeticUti.divideTo2Decimal(basePriceInBooking, new BigDecimal(count));
		apiPriceInBooking=ArithmeticUti.divideTo2Decimal(apiPriceInBooking, new BigDecimal(count));
		taxesPriceInBooking=ArithmeticUti.divideTo2Decimal(taxesPriceInBooking, new BigDecimal(count));
		markup=ArithmeticUti.divideTo2Decimal(markup, new BigDecimal(count));
		convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(count));
		managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(count));
		gstOrSrviceTax=ArithmeticUti.divideTo2Decimal(gstOrSrviceTax, new BigDecimal(count));
		baseServiceTax=ArithmeticUti.divideTo2Decimal(baseServiceTax, new BigDecimal(count));
		CGSTPerPassenger=ArithmeticUti.divideTo2Decimal(CGSTPerPassenger, new BigDecimal(count));
		SGSTorIGSTorUGSTPerPassenger=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger, new BigDecimal(count));
		CommonDsrHelperVO commonDsrHelperVO=new CommonDsrHelperVO(basePriceInBooking, apiPriceInBooking, taxesPriceInBooking, markup, null, null, null, null, null, convenienceFee, null, managementFee, gstOrSrviceTax, CGSTPerPassenger,SGSTorIGSTorUGSTPerPassenger, baseServiceTax, null, null, null, null);
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		if(insuranceOrderRow.getInsuranceOrderCustomerDetails()!=null && insuranceOrderRow.getInsuranceOrderCustomerDetails().size()>0){
			for(InsuranceOrderCustomerDetail insuranceOrderCustomer: insuranceOrderRow.getInsuranceOrderCustomerDetails()){
				commonDsrTravelReportData=commonDsrDaoSubHelper.buildInsuranceCustomerRmList(commonDsrPage, insuranceOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, insuranceOrderCustomer);
				if(isOrderRowList)
					commonDsrTravelReports.add(commonDsrTravelReportData);
				if(isCreditNoteList){
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("I",insuranceOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}
			}
		}
		else{
			commonDsrTravelReportData=commonDsrDaoSubHelper.buildInsuranceCustomerRmList(commonDsrPage, insuranceOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, null);
			if(isOrderRowList)
				commonDsrTravelReports.add(commonDsrTravelReportData);
			if(isCreditNoteList){
				CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("I",insuranceOrderRowObj, commonDsrTravelReportData);
				if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
					commonDsrTravelReportsNew.setBillingEntity(billingEntity);
					commonDsrTravelReports.add(commonDsrTravelReportsNew);
				}
			}
		}
		return commonDsrTravelReports;
	}
	
	public  List<CommonDsrTravelReportData> getMiscellaneousCustomerRmList(List<CommonDsrTravelReportData> commonDsrTravelReports,CommonDsrPage commonDsrPage,MiscellaneousOrderRow miscellaneousOrderRow,Company newCompanyObj,String billingEntity,String taxType,Company sessionCompany,boolean isCreditNoteList,boolean isOrderRowList,CompanyConfig newCompanyConfig){
		Object miscellaneousOrderRowObj=miscellaneousOrderRow;
		int count=miscellaneousOrderRow.getMiscellaneousOrderCustomerList().size();
		BigDecimal basePriceInBooking=miscellaneousOrderRow.getBasePrice()!=null?miscellaneousOrderRow.getBasePrice().multiply(miscellaneousOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal apiPriceInBooking=miscellaneousOrderRow.getSupplierPrice()!=null?miscellaneousOrderRow.getSupplierPrice().multiply(miscellaneousOrderRow.getApiToBaseExchangeRate()).multiply(miscellaneousOrderRow.getBaseToBookingExchangeRate()):new BigDecimal("0");
		BigDecimal taxesPriceInBooking  =miscellaneousOrderRow.getOtherTaxes()!=null?miscellaneousOrderRow.getOtherTaxes():new BigDecimal(0);
		BigDecimal markup= miscellaneousOrderRow.getMarkUpamount()!=null?miscellaneousOrderRow.getMarkUpamount():new BigDecimal(0);
		BigDecimal convenienceFee = miscellaneousOrderRow.getConvenienceFee()!=null?miscellaneousOrderRow.getConvenienceFee():new BigDecimal(0);
		BigDecimal managementFee =miscellaneousOrderRow.getManagementFee()!=null?miscellaneousOrderRow.getManagementFee():new BigDecimal(0);
		BigDecimal gstOrSrviceTax=miscellaneousOrderRow.getTotalGstTax()!=null?miscellaneousOrderRow.getTotalGstTax():new BigDecimal(0);
		BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
		BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
		if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax()!=null && newCompanyConfig!=null && taxType!=null && taxType.equalsIgnoreCase("GST")){ 
			CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getCGST());					  
			if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getSGST());					  
			if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getUGST());					  
			if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
				SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getIGST());					  
		}

		basePriceInBooking=ArithmeticUti.divideTo2Decimal(basePriceInBooking, new BigDecimal(count));
		apiPriceInBooking=ArithmeticUti.divideTo2Decimal(apiPriceInBooking, new BigDecimal(count));
		taxesPriceInBooking=ArithmeticUti.divideTo2Decimal(taxesPriceInBooking, new BigDecimal(count));
		markup=ArithmeticUti.divideTo2Decimal(markup, new BigDecimal(count));

		convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(count));
		managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(count));
		gstOrSrviceTax=ArithmeticUti.divideTo2Decimal(gstOrSrviceTax, new BigDecimal(count));
		CGSTPerPassenger=ArithmeticUti.divideTo2Decimal(CGSTPerPassenger, new BigDecimal(count));
		SGSTorIGSTorUGSTPerPassenger=ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger, new BigDecimal(count));
		CommonDsrHelperVO commonDsrHelperVO=new CommonDsrHelperVO(basePriceInBooking, apiPriceInBooking, taxesPriceInBooking, markup, null, null, null, null, null, convenienceFee, null, managementFee, gstOrSrviceTax, CGSTPerPassenger,SGSTorIGSTorUGSTPerPassenger, null, null, null, null, null);
		CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
		if(miscellaneousOrderRow.getMiscellaneousOrderCustomerList()!=null && miscellaneousOrderRow.getMiscellaneousOrderCustomerList().size()>0){
			for( MiscellaneousOrderCustomer   miscellaneousOrderCustomer: miscellaneousOrderRow.getMiscellaneousOrderCustomerList()){
				commonDsrTravelReportData=commonDsrDaoSubHelper.buildMiscellaneousCustomerRmList(commonDsrPage, miscellaneousOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO,miscellaneousOrderCustomer);
				if(isOrderRowList)
					commonDsrTravelReports.add(commonDsrTravelReportData);
				if(isCreditNoteList){
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("M",miscellaneousOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}
			}
		}
		else{
			commonDsrTravelReportData=commonDsrDaoSubHelper.buildMiscellaneousCustomerRmList(commonDsrPage, miscellaneousOrderRow, newCompanyObj, billingEntity, taxType, sessionCompany, newCompanyConfig, commonDsrHelperVO, null);
			if(isOrderRowList)
				commonDsrTravelReports.add(commonDsrTravelReportData);
			if(isCreditNoteList){
				CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("M",miscellaneousOrderRowObj, commonDsrTravelReportData);
				if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
					commonDsrTravelReportsNew.setBillingEntity(billingEntity);
					commonDsrTravelReports.add(commonDsrTravelReportsNew);
				}
			}	
		}
		return commonDsrTravelReports;
	}
}
