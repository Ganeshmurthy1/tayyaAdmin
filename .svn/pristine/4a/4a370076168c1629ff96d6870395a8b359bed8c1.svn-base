package com.common.dsr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.admin.api.provider.dao.ApiProviderDao;
import com.admin.api.provider.model.ApiProvider;
import com.admin.common.quotation.dao.BusTravelRequestDao;
import com.admin.common.quotation.dao.CarTravelRequestDao;
import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.BusTravelRequestQuotation;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.knockoff.dao.PaymentKnockDao;
import com.admin.knockoff.dao.PaymentKnockDaoImpl;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.payment.recievable.PaymentKnockOffRow;
import com.admin.payment.recievable.PaymentKnockOffRowTx;
import com.common.dsr.helper.DsrRmConfigHelperDao;
import com.common.dsr.helper.DsrRmConfigOrderCancelHelper;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.MyTransactionsDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.PaymentTransaction;
import com.lintas.admin.flight.model.FlightOrderCustomer;
import com.lintas.admin.flight.model.FlightOrderCustomerPriceBreakup;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderTripDetail;
import com.lintas.admin.hotel.model.HotelOrderGuest;
import com.lintas.admin.hotel.model.HotelOrderRoomInfo;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyEntity;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.utility.ArithmeticUti;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;

public class OutStandingDao {
	MyTransactionsDao myTransactionsDao=new MyTransactionsDao();
	PaymentKnockDao paymentKnockDao=new PaymentKnockDaoImpl();
	DsrRmConfigHelperDao dsrRmConfigHelperDao=new DsrRmConfigHelperDao();
	DsrRmConfigOrderCancelHelper dsrRmConfigOrderCancelHelper=new DsrRmConfigOrderCancelHelper();
	CommonDsrReportDao commonDsrReportDao=new CommonDsrReportDao();
	public List<CommonDsrTravelReportData> getOutStandingDsrTravelReportsToExportExcel(CommonDsrPage commonDsrPage){
		List<CommonDsrTravelReportData> commonDsrTravelReports=new ArrayList<>();
		CommonDsrUtility commonDsrUtility=null;
		Company  newCompanyObj=!commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")?new CompanyDAO().CompanyLogin(commonDsrPage.getCommonDsrFilters().getCompanyUserId()):commonDsrPage.getCommonDsrFilters().getLoginCompany();
		Company  sessionCompany=commonDsrPage.getCommonDsrFilters().getLoginCompany();
		List<WalletAmountTranferHistory> brvHistoryList=myTransactionsDao.getWalletHistoryBRVListwithCompanyId(newCompanyObj);
		/*if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("F")){ 
			commonDsrUtility=commonDsrDao.getCommonDsrFlightReports(commonDsrPage);
			flightOrderRowList=commonDsrUtility.getFlightOrderRowList();
		}*/

		if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("B") ){
			commonDsrUtility=commonDsrReportDao.getCommonDsrBusReports(commonDsrPage);
			busOutstandingReports(commonDsrPage,commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("F") ){
			commonDsrUtility=commonDsrReportDao.getCommonDsrFlightReports(commonDsrPage);
			flightOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("H") ){
			commonDsrUtility=commonDsrReportDao.getCommonDsrHotelReports(commonDsrPage);
			hotelOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("C") ){
			commonDsrUtility=commonDsrReportDao.getCommonDsrCarReports(commonDsrPage);
			carOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
		}

		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("T") ){
			commonDsrUtility=commonDsrReportDao.getCommonDsrTrainReports(commonDsrPage);
			trainOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("I") ){
			commonDsrUtility=commonDsrReportDao.getCommonDsrInsuranceReports(commonDsrPage);
			insuranceOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("V") ){
			commonDsrUtility=commonDsrReportDao.getCommonDsrVisaReports(commonDsrPage);
			visaOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
		}
		else if(commonDsrPage.getCommonDsrFilters().getTravelType().equalsIgnoreCase("M") ){
			commonDsrUtility=commonDsrReportDao.getCommonDsrMiscellaneousReports(commonDsrPage);
			miscellaneousOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
		}
		else{
			commonDsrUtility=commonDsrReportDao.getCommonDsrFlightReports(commonDsrPage);
			flightOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
			commonDsrUtility=commonDsrReportDao.getCommonDsrHotelReports(commonDsrPage);
			hotelOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany ); 
			commonDsrUtility=commonDsrReportDao.getCommonDsrCarReports(commonDsrPage);
			carOutstandingReports(commonDsrPage, commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany );
			commonDsrUtility=commonDsrReportDao.getCommonDsrBusReports(commonDsrPage);
			busOutstandingReports(commonDsrPage,commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany); 
			commonDsrUtility=commonDsrReportDao.getCommonDsrTrainReports(commonDsrPage);
			trainOutstandingReports(commonDsrPage,commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany);
			commonDsrUtility=commonDsrReportDao.getCommonDsrVisaReports(commonDsrPage);
			visaOutstandingReports(commonDsrPage,commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany);
			commonDsrUtility=commonDsrReportDao.getCommonDsrInsuranceReports(commonDsrPage);
			insuranceOutstandingReports(commonDsrPage,commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany);
			commonDsrUtility=commonDsrReportDao.getCommonDsrMiscellaneousReports(commonDsrPage);
			miscellaneousOutstandingReports(commonDsrPage,commonDsrUtility, commonDsrTravelReports, newCompanyObj, sessionCompany); 
		}
		getBRVCommonDsrReports(commonDsrTravelReports, commonDsrUtility, brvHistoryList,  commonDsrUtility.getTaxType());
		return commonDsrTravelReports;
	}

	public List<CommonDsrTravelReportData> getBRVCommonDsrReports(List<CommonDsrTravelReportData>  commonDsrTravelReports,CommonDsrUtility commonDsrUtility,List<WalletAmountTranferHistory> brvHistoryList,String taxType){
		Map<String,  BigDecimal> BRVAmountMap=new LinkedHashMap<>();
		if(brvHistoryList!=null && brvHistoryList.size()>0){
			for(WalletAmountTranferHistory walletAmountTranferHistory:brvHistoryList){
				BigDecimal totBrvAmount=new BigDecimal(0);
				List<PaymentKnockOffRowTx> paymentKnockOffRowTxList=paymentKnockDao.fetchPaymentKnockOffRowTxs(walletAmountTranferHistory.getActionId()); 
				if(paymentKnockOffRowTxList!=null && paymentKnockOffRowTxList.size()>0){
					for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowTxList){
						totBrvAmount=totBrvAmount.add(paymentKnockOffRowTx.getAmount());
					}
					totBrvAmount=walletAmountTranferHistory.getAmount().subtract(totBrvAmount);
				}
				else 
					totBrvAmount=walletAmountTranferHistory.getAmount();
				
				
				

				BigDecimal totBrvAmountTemp=totBrvAmount.setScale(2, BigDecimal.ROUND_UP);
				if(totBrvAmountTemp.compareTo(new BigDecimal(0))==0) 
					totBrvAmountTemp=new BigDecimal(0);
				BRVAmountMap.put(walletAmountTranferHistory.getActionId(), totBrvAmountTemp);
				
			}
		}
		if(BRVAmountMap!=null && BRVAmountMap.size()>0){
			for(Map.Entry<String, BigDecimal> entry:BRVAmountMap.entrySet()){
				if(entry.getValue().compareTo(new BigDecimal(0))==0){
				}
				else{
					CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
					commonDsrTravelReportData.setTaxType(taxType);
					commonDsrTravelReportData.setBkgRef(entry.getKey());
					commonDsrTravelReportData.setSystemInvoiceId(entry.getKey());
					commonDsrTravelReportData.setOutstandingAmount(entry.getValue().setScale(2, BigDecimal.ROUND_HALF_UP).negate().toString());
					commonDsrTravelReports.add(commonDsrTravelReportData);
				}
			}
		}	
		return commonDsrTravelReports;
	}
	public List<CommonDsrTravelReportData> miscellaneousOutstandingReports(CommonDsrPage commonDsrPage,CommonDsrUtility commonDsrUtility,List<CommonDsrTravelReportData> commonDsrTravelReports,Company  newCompanyObj,Company  sessionCompany ){
		if(commonDsrUtility!=null && commonDsrUtility.getMiscellaneousOrderRowList()!=null && commonDsrUtility.getMiscellaneousOrderRowList().size()>0){
			for(MiscellaneousOrderRow miscellaneousOrderRow:commonDsrUtility.getMiscellaneousOrderRowList()){
				Object miscellaneousOrderRowObj=miscellaneousOrderRow;
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(miscellaneousOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(miscellaneousOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(miscellaneousOrderRow!=null ){
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
					commonDsrTravelReportData.setBookingDate(miscellaneousOrderRow.getBookingDate()!=null?DateConversion.convertDateToStringDate(miscellaneousOrderRow.getBookingDate()):"-");
					Company company=new CompanyDAO().getCompanyProfile(miscellaneousOrderRow.getCompanyId());
					User user=new UserDAO().GetUserProfile(miscellaneousOrderRow.getUserId());
					if(company!=null && company.getCompanyname()!=null) 
						commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					else
						commonDsrTravelReportData.setCorporateName("-");
					commonDsrTravelReportData.setBillingEntity(billingEntity);
					if(user!=null && user.getUsername()!=null) 
						commonDsrTravelReportData.setBookerName(user.getUsername());
					else
						commonDsrTravelReportData.setBookerName("-");
					String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
					commonDsrTravelReportData.setBookersLoginId(email);
					//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode(miscellaneousOrderRow.getSupplierName());
					commonDsrTravelReportData.setSupplierName(miscellaneousOrderRow.getSupplierName());
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
					if(miscellaneousOrderRow.getBasePrice()!=null) 
						basePriceInBooking=miscellaneousOrderRow.getBasePrice().multiply(miscellaneousOrderRow.getBaseToBookingExchangeRate());
					else
						basePriceInBooking=new BigDecimal("0");

					if(miscellaneousOrderRow.getSupplierPrice()!=null)
						apiPriceInBooking=miscellaneousOrderRow.getSupplierPrice().multiply(miscellaneousOrderRow.getApiToBaseExchangeRate()).multiply(miscellaneousOrderRow.getBaseToBookingExchangeRate()); 
					else
						apiPriceInBooking=new BigDecimal("0");

					if(miscellaneousOrderRow.getOtherTaxes()!=null) 
						taxesPriceInBooking=miscellaneousOrderRow.getOtherTaxes().multiply(miscellaneousOrderRow.getApiToBaseExchangeRate()).multiply(miscellaneousOrderRow.getBaseToBookingExchangeRate()); 
					else
						taxesPriceInBooking=new BigDecimal("0");


					commonDsrTravelReportData.setTaxType(taxType);


					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					if (sessionCompany.getCompanyRole().isSuperUser())
						commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					else
						commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

					commonDsrTravelReportData.setTraveller(miscellaneousOrderRow.getOrderCustomer().getTitle()+" "+miscellaneousOrderRow.getOrderCustomer().getFirstName()+" "+miscellaneousOrderRow.getOrderCustomer().getLastName());
					commonDsrTravelReportData.setProductType("Other Products");
					commonDsrTravelReportData.setProductName("Miscellaneous");
					String itineraryType=commonDsrTravelReportData.getProductName();
					commonDsrTravelReportData.setItineraryType(itineraryType);
					commonDsrTravelReportData.setProductCode("-");
					commonDsrTravelReportData.setDescription("-");
					commonDsrTravelReportData.setDescription2(miscellaneousOrderRow.getRemarks());
					commonDsrTravelReportData.setAirlinePNRorProvBooking("-");
					commonDsrTravelReportData.setGDSPNR("-");
					commonDsrTravelReportData.setTicketNumorFinalBooking(miscellaneousOrderRow.getConfirmationNumber());
					commonDsrTravelReportData.setFareType("-");
					commonDsrTravelReportData.setBookingRefundType("-");
					commonDsrTravelReportData.setFareBasis("-");
					commonDsrTravelReportData.setTotalNights("0");
					//commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(miscellaneousOrderRow.getTravelDate()));
					//commonDsrTravelReportData.setTripEndDate(DateConversion.convertDateToStringToDate(miscellaneousOrderRow.getTravelDate()));
					commonDsrTravelReportData.setTripStartsDate("-");
					commonDsrTravelReportData.setTripEndDate("-");
					commonDsrTravelReportData.setJourneyType("NA");
					BigDecimal markup=miscellaneousOrderRow.getMarkUpamount()!=null?miscellaneousOrderRow.getMarkUpamount():new BigDecimal("0");
					otherTaxesWithMarkup=taxesPriceInBooking;
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal gstOrSrviceTax  = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					convenienceFee=miscellaneousOrderRow.getConvenienceFee()!=null ?miscellaneousOrderRow.getConvenienceFee():new BigDecimal(0);
					managementFee=miscellaneousOrderRow.getManagementFee()!=null?miscellaneousOrderRow.getManagementFee():new BigDecimal(0);
					gstOrSrviceTax=miscellaneousOrderRow.getTotalGstTax()!=null?miscellaneousOrderRow.getTotalGstTax():new BigDecimal(0);




					BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
					BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
					if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax()!=null && taxType !=null &&  taxType .equalsIgnoreCase("GST")){ 
						CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getCGST());					  
						if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getSGST());					  
						if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getUGST());					  
						if(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(miscellaneousOrderRow.getMiscellaneousOrderRowGstTax().getIGST());					  
					}
					commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					grossFare=basePriceInBooking.add(otherTaxesWithMarkup).add(markup);
					commonDsrTravelReportData.setMarkup(markup.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax(markup.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					commonDsrTravelReportData.setK3Tax("0");
					transactionFee = feeAmount;
					BaseServiceTax =/*miscellaneousOrderRow.getServiceTax()!=null?miscellaneousOrderRow.getServiceTax():*/new BigDecimal(0);//basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getBasicTax()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					//swachBharatCess = basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getSwatchBharathCess()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					//krishiKalyanCess = basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getKrishiKalyanCess()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getTotalTax()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getTotalTax():new BigDecimal(0));
					if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
						netFare=grossFare.add(gstOrSrviceTax).add(convenienceFee).add(managementFee);
					else
						netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);

					BigDecimal totAmount=new BigDecimal(0);
					PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(miscellaneousOrderRow.getInvoiceNo(), miscellaneousOrderRow.getCompanyId());
					if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
						for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
							totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
						}
						totAmount=paymentKnockOffRowNew.getBillAmount().subtract(totAmount);
					}
					else
						totAmount=netFare;
					commonDsrTravelReportData.setOutstandingAmount(totAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					commonDsrTravelReportData.setTotGstTax(gstOrSrviceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
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
					RmConfigTripDetailsModel 	rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(miscellaneousOrderRow.getOrderId(),company.getCompanyid());
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
					commonDsrTravelReportData.setKnockOff(miscellaneousOrderRow.isKnockOff());
					commonDsrTravelReportData.setCompanyId(miscellaneousOrderRow.getCompanyId());
					commonDsrTravelReports.add(commonDsrTravelReportData);
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("M",miscellaneousOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}

			}

		}
		return commonDsrTravelReports;
	}
	public List<CommonDsrTravelReportData> insuranceOutstandingReports(CommonDsrPage commonDsrPage,CommonDsrUtility commonDsrUtility,List<CommonDsrTravelReportData> commonDsrTravelReports,Company  newCompanyObj,Company  sessionCompany ){
		if(commonDsrUtility!=null && commonDsrUtility.getInsuranceOrderRowList()!=null && commonDsrUtility.getInsuranceOrderRowList().size()>0){
			for(InsuranceOrderRow insuranceOrderRow:commonDsrUtility.getInsuranceOrderRowList()){
				Object insuranceOrderRowObj=insuranceOrderRow;
				String taxType=commonDsrUtility.getTaxType();
				CompanyEntity companyEntity=null;
				String billingEntity="-";
				if(insuranceOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(insuranceOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(insuranceOrderRow!=null ){
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
					commonDsrTravelReportData.setBookingDate(insuranceOrderRow.getInsuranceBookingDate()!=null?insuranceOrderRow.getInsuranceBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(insuranceOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(insuranceOrderRow.getUserId().trim().equals("")?"0":insuranceOrderRow.getUserId()));
					if(company!=null && company.getCompanyname()!=null) 
						commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					else
						commonDsrTravelReportData.setCorporateName("-");
					commonDsrTravelReportData.setBillingEntity(billingEntity);
					if(user!=null && user.getUsername()!=null) 
						commonDsrTravelReportData.setBookerName(user.getUsername());
					else
						commonDsrTravelReportData.setBookerName("-");
					String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
					commonDsrTravelReportData.setBookersLoginId(email);
					//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode(insuranceOrderRow.getSupplierName());
					commonDsrTravelReportData.setSupplierName(insuranceOrderRow.getSupplierName());
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
						basePriceInBooking=insuranceOrderRow.getBasePrice().multiply(insuranceOrderRow.getBaseToBookingExchangeRate());
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


					commonDsrTravelReportData.setTaxType(taxType);


					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					if (sessionCompany.getCompanyRole().isSuperUser())
						commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					else
						commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

					commonDsrTravelReportData.setTraveller(insuranceOrderRow.getOrderCustomer().getTitle()+" "+insuranceOrderRow.getOrderCustomer().getFirstName()+" "+insuranceOrderRow.getOrderCustomer().getLastName());
					commonDsrTravelReportData.setProductType("Other Products");
					commonDsrTravelReportData.setProductName("Insurance");
					String itineraryType=commonDsrTravelReportData.getProductName();
					commonDsrTravelReportData.setItineraryType(itineraryType);
					commonDsrTravelReportData.setProductCode("-");
					commonDsrTravelReportData.setDescription("-");
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
					BigDecimal markup=insuranceOrderRow.getMarkUpamount()!=null?insuranceOrderRow.getMarkUpamount():new BigDecimal("0");
					otherTaxesWithMarkup=taxesPriceInBooking;
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					BigDecimal gstOrSrviceTax  = new BigDecimal("0");
					convenienceFee=insuranceOrderRow.getConvenienceFee()!=null ?insuranceOrderRow.getConvenienceFee():new BigDecimal(0);
					managementFee=insuranceOrderRow.getManagementFee()!=null?insuranceOrderRow.getManagementFee():new BigDecimal(0);
					gstOrSrviceTax=insuranceOrderRow.getTotalGstTax()!=null?insuranceOrderRow.getTotalGstTax():new BigDecimal(0);

					BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
					BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
					if(insuranceOrderRow.getInsuranceOrderRowGstTax()!=null && taxType !=null &&  taxType .equalsIgnoreCase("GST")){ 
						CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowGstTax().getCGST());					  
						if(insuranceOrderRow.getInsuranceOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowGstTax().getSGST());					  
						if(insuranceOrderRow.getInsuranceOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowGstTax().getUGST());					  
						if(insuranceOrderRow.getInsuranceOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowGstTax().getIGST());					  
					}
					commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					grossFare=basePriceInBooking.add(otherTaxesWithMarkup).add(markup);
					commonDsrTravelReportData.setMarkup(markup.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax(markup.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					commonDsrTravelReportData.setK3Tax("0");
					transactionFee = feeAmount;
					BaseServiceTax =insuranceOrderRow.getServiceTax()!=null?insuranceOrderRow.getServiceTax():new BigDecimal(0); ;//basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getBasicTax()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					//swachBharatCess = basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getSwatchBharathCess()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					//krishiKalyanCess = basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getKrishiKalyanCess()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(insuranceOrderRow.getInsuranceOrderRowServiceTax()!=null && insuranceOrderRow.getInsuranceOrderRowServiceTax().getTotalTax()!=null?insuranceOrderRow.getInsuranceOrderRowServiceTax().getTotalTax():new BigDecimal(0));
					if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
						netFare=grossFare.add(gstOrSrviceTax).add(convenienceFee).add(managementFee);
					else
						netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);
					BigDecimal totAmount=new BigDecimal(0);
					PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(insuranceOrderRow.getInvoiceNo(), Integer.parseInt(insuranceOrderRow.getCompanyId()));
					if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
						for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
							totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
						}
						totAmount=paymentKnockOffRowNew.getBillAmount().subtract(totAmount);
					}
					else
						totAmount=netFare;
					commonDsrTravelReportData.setOutstandingAmount(totAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

					commonDsrTravelReportData.setTotGstTax(insuranceOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
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
					RmConfigTripDetailsModel 	rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(insuranceOrderRow.getOrderId(),company.getCompanyid());
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
					commonDsrTravelReportData.setKnockOff(insuranceOrderRow.isKnockOff());
					commonDsrTravelReportData.setCompanyId(Integer.parseInt(insuranceOrderRow.getCompanyId()));
					commonDsrTravelReports.add(commonDsrTravelReportData);
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("I",insuranceOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}

			}

		}
		return commonDsrTravelReports;
	}
	public List<CommonDsrTravelReportData> visaOutstandingReports(CommonDsrPage commonDsrPage,CommonDsrUtility commonDsrUtility,List<CommonDsrTravelReportData> commonDsrTravelReports,Company  newCompanyObj,Company  sessionCompany ){
		if(commonDsrUtility!=null && commonDsrUtility.getVisaOrderRowList()!=null && commonDsrUtility.getVisaOrderRowList().size()>0){
			for(VisaOrderRow visaOrderRow:commonDsrUtility.getVisaOrderRowList()){
				Object visaOrderRowObj=visaOrderRow;
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(visaOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(visaOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(visaOrderRow!=null ){
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
					commonDsrTravelReportData.setBookingDate(visaOrderRow.getVisaBookingDate()!=null?visaOrderRow.getVisaBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(visaOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(visaOrderRow.getUserId().trim().equals("")?"0":visaOrderRow.getUserId()));
					if(company!=null && company.getCompanyname()!=null) 
						commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					else
						commonDsrTravelReportData.setCorporateName("-");
					commonDsrTravelReportData.setBillingEntity(billingEntity);
					if(user!=null && user.getUsername()!=null) 
						commonDsrTravelReportData.setBookerName(user.getUsername());
					else
						commonDsrTravelReportData.setBookerName("-");
					String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
					commonDsrTravelReportData.setBookersLoginId(email);
					//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode(visaOrderRow.getSupplierName());
					commonDsrTravelReportData.setSupplierName(visaOrderRow.getSupplierName());
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
						basePriceInBooking=visaOrderRow.getBasePrice().multiply(visaOrderRow.getBaseToBookingExchangeRate());
					else
						basePriceInBooking=new BigDecimal("0");

					if(visaOrderRow.getSupplierPrice()!=null)
						apiPriceInBooking=visaOrderRow.getSupplierPrice().multiply(visaOrderRow.getApiToBaseExchangeRate()).multiply(visaOrderRow.getBaseToBookingExchangeRate()); 
					else
						apiPriceInBooking=new BigDecimal("0");

					if(visaOrderRow.getOtherTaxes()!=null) 
						taxesPriceInBooking=visaOrderRow.getOtherTaxes().multiply(visaOrderRow.getApiToBaseExchangeRate()).multiply(visaOrderRow.getBaseToBookingExchangeRate()); 
					else
						taxesPriceInBooking=new BigDecimal("0");


					commonDsrTravelReportData.setTaxType(taxType);

					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					if (sessionCompany.getCompanyRole().isSuperUser())
						commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					else
						commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

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
					BigDecimal markup=visaOrderRow.getMarkUp()!=null?visaOrderRow.getMarkUp():new BigDecimal("0");
					otherTaxesWithMarkup=taxesPriceInBooking;
					grossFare=basePriceInBooking.add(otherTaxesWithMarkup).add(markup).add(visaOrderRow.getVfsCharges()).add(visaOrderRow.getCourierCharges());
					commonDsrTravelReportData.setMarkup(markup.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax(markup.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					commonDsrTravelReportData.setK3Tax("0");
					transactionFee = feeAmount;
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					BigDecimal gstOrSrviceTax  = new BigDecimal("0");
					convenienceFee=visaOrderRow.getConvenienceFee()!=null ?visaOrderRow.getConvenienceFee():new BigDecimal(0);
					managementFee=visaOrderRow.getManagementFee()!=null?visaOrderRow.getManagementFee():new BigDecimal(0);
					gstOrSrviceTax=visaOrderRow.getTotalGstTax()!=null?visaOrderRow.getTotalGstTax():new BigDecimal(0);
					BaseServiceTax = managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getBasicTax()!=null?visaOrderRow.getVisaOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					swachBharatCess = managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getSwatchBharathCess()!=null?visaOrderRow.getVisaOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					krishiKalyanCess = managementFee.divide(new BigDecimal(100)).multiply(visaOrderRow.getVisaOrderRowServiceTax()!=null && visaOrderRow.getVisaOrderRowServiceTax().getKrishiKalyanCess()!=null?visaOrderRow.getVisaOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess); 
					if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
						netFare=grossFare.add(gstOrSrviceTax).add(convenienceFee).add(managementFee);
					else
						netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);
					BigDecimal totAmount=new BigDecimal(0);
					PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(visaOrderRow.getInvoiceNo(), Integer.parseInt(visaOrderRow.getCompanyId()));
					if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
						for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
							totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
						}
						totAmount=paymentKnockOffRowNew.getBillAmount().subtract(totAmount);
					}
					else
						totAmount=netFare;
					commonDsrTravelReportData.setOutstandingAmount(totAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

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
					commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());

					commonDsrTravelReportData.setTotGstTax(visaOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP).toString());

					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setCourierCharges(visaOrderRow.getCourierCharges().setScale(0, RoundingMode.UP).toString());
					commonDsrTravelReportData.setVfsCharges(visaOrderRow.getVfsCharges().setScale(0, RoundingMode.UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
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
					RmConfigTripDetailsModel 	rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(visaOrderRow.getOrderId(),company.getCompanyid());
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
					commonDsrTravelReportData.setCompanyId(Integer.parseInt(visaOrderRow.getCompanyId()));
					commonDsrTravelReportData.setKnockOff(visaOrderRow.isKnockOff());
					commonDsrTravelReports.add(commonDsrTravelReportData);
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("V",visaOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}

			}

		}
		return commonDsrTravelReports;
	}
	public List<CommonDsrTravelReportData> trainOutstandingReports(CommonDsrPage commonDsrPage,CommonDsrUtility commonDsrUtility,List<CommonDsrTravelReportData> commonDsrTravelReports,Company  newCompanyObj,Company  sessionCompany ){
		if(commonDsrUtility!=null && commonDsrUtility.getTrainOrderRowList()!=null && commonDsrUtility.getTrainOrderRowList().size()>0){
			for(TrainOrderRow trainOrderRow:commonDsrUtility.getTrainOrderRowList()){
				Object trainOrderRowObj=trainOrderRow;
				String taxType=commonDsrUtility.getTaxType();
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(trainOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(trainOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();
				if(trainOrderRow!=null ){
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
					commonDsrTravelReportData.setBookingDate(trainOrderRow.getTrainBookingDate()!=null?trainOrderRow.getTrainBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(trainOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(trainOrderRow.getUserId().trim().equals("")?"0":trainOrderRow.getUserId()));
					if(company!=null && company.getCompanyname()!=null) 
						commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					else
						commonDsrTravelReportData.setCorporateName("-");
					commonDsrTravelReportData.setBillingEntity(billingEntity);
					if(user!=null && user.getUsername()!=null) 
						commonDsrTravelReportData.setBookerName(user.getUsername());
					else
						commonDsrTravelReportData.setBookerName("-");
					String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
					commonDsrTravelReportData.setBookersLoginId(email);
					//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode(trainOrderRow.getSupplierName());
					commonDsrTravelReportData.setSupplierName(trainOrderRow.getSupplierName());
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
						basePriceInBooking=trainOrderRow.getBasePrice().multiply(trainOrderRow.getBaseToBookingExchangeRate());
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

					commonDsrTravelReportData.setTaxType(taxType);

					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					BigDecimal gstOrSrviceTax  = new BigDecimal("0");
					convenienceFee=trainOrderRow.getConvenienceFee()!=null ?trainOrderRow.getConvenienceFee():new BigDecimal(0);
					if(!trainOrderRow.getTicketType().equals("")){
						if(trainOrderRow.getTicketType().equalsIgnoreCase("tatkal"))
							managementFee=trainOrderRow.getManagementFeeTatkal()!=null ||!trainOrderRow.getManagementFeeTatkal().equals("")?trainOrderRow.getManagementFeeTatkal():new BigDecimal(0);
							if(trainOrderRow.getTicketType().equalsIgnoreCase("normal")) 
								managementFee=trainOrderRow.getManagementFee()!=null?trainOrderRow.getManagementFee():new BigDecimal(0);
					}
					else
						managementFee=new BigDecimal(0);
					gstOrSrviceTax=trainOrderRow.getTotalGstTax()!=null?trainOrderRow.getTotalGstTax():new BigDecimal(0);

					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					if (sessionCompany.getCompanyRole().isSuperUser())
						commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					else
						commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

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
					commonDsrTravelReportData.setMarkup(trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP).toString());
					otherTaxesWithMarkup=taxesPriceInBooking;
					grossFare=basePriceInBooking.add(otherTaxesWithMarkup).add(trainOrderRow.getMarkUp());
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax(trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					commonDsrTravelReportData.setK3Tax("0");
					transactionFee = feeAmount;
					BaseServiceTax = managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getBasicTax()!=null?trainOrderRow.getTrainOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					swachBharatCess = managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getSwatchBharathCess()!=null?trainOrderRow.getTrainOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					krishiKalyanCess = managementFee.divide(new BigDecimal(100)).multiply(trainOrderRow.getTrainOrderRowServiceTax()!=null && trainOrderRow.getTrainOrderRowServiceTax().getKrishiKalyanCess()!=null?trainOrderRow.getTrainOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess); 
					if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
						netFare=grossFare.add(gstOrSrviceTax).add(convenienceFee).add(managementFee);
					else
						netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);
					BigDecimal totAmount=new BigDecimal(0);
					PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(trainOrderRow.getInvoiceNo(), Integer.parseInt(trainOrderRow.getCompanyId()));
					if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
						for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
							totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
						}
						totAmount=paymentKnockOffRowNew.getBillAmount().subtract(totAmount);
					}
					else
						totAmount=netFare;
					commonDsrTravelReportData.setOutstandingAmount(totAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());


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
					commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());


					commonDsrTravelReportData.setTotGstTax(trainOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
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
					RmConfigTripDetailsModel 	rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(trainOrderRow.getOrderId(),company.getCompanyid());
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
					commonDsrTravelReportData.setKnockOff(trainOrderRow.isKnockOff());
					commonDsrTravelReportData.setCompanyId(Integer.parseInt(trainOrderRow.getCompanyId()));
					commonDsrTravelReports.add(commonDsrTravelReportData);
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("T",trainOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}

			}
		}
		return commonDsrTravelReports;
	}



	public List<CommonDsrTravelReportData> busOutstandingReports(CommonDsrPage commonDsrPage,CommonDsrUtility commonDsrUtility,List<CommonDsrTravelReportData> commonDsrTravelReports,Company  newCompanyObj,Company  sessionCompany ){
		if(commonDsrUtility!=null && commonDsrUtility.getBusOrderRowList()!=null && commonDsrUtility.getBusOrderRowList().size()>0){
			for(BusOrderRow busOrderRow:commonDsrUtility.getBusOrderRowList()){
				String taxType=commonDsrUtility.getTaxType();
				Object busOrderRowObj=busOrderRow;
				String billingEntity="-";
				CompanyEntity companyEntity=null;
				if(busOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(busOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();

				if(busOrderRow!=null ){
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
					commonDsrTravelReportData.setBookingDate(busOrderRow.getBusBookingDate()!=null?busOrderRow.getBusBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(busOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(busOrderRow.getUserId()));
					if(company!=null && company.getCompanyname()!=null) 
						commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					else
						commonDsrTravelReportData.setCorporateName("-");
					commonDsrTravelReportData.setBillingEntity(billingEntity);
					if(user!=null && user.getUsername()!=null) 
						commonDsrTravelReportData.setBookerName(user.getUsername());
					else
						commonDsrTravelReportData.setBookerName("-");
					commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode(busOrderRow.getSupplierName());
					commonDsrTravelReportData.setSupplierName(busOrderRow.getSupplierName());
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
						basePriceInBooking=busOrderRow.getBasePrice().multiply(busOrderRow.getBaseToBookingExchangeRate());
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


					commonDsrTravelReportData.setTaxType(taxType);
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					BigDecimal gstOrSrviceTax  = new BigDecimal("0");
					convenienceFee=busOrderRow.getConvenienceFee()!=null ?busOrderRow.getConvenienceFee():new BigDecimal(0);
					gstOrSrviceTax=busOrderRow.getTotalGstTax()!=null?busOrderRow.getTotalGstTax():new BigDecimal(0);
					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					if (sessionCompany.getCompanyRole().isSuperUser())
						commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					else
						commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

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
					BigDecimal markup=busOrderRow.getMarkUp()!=null?busOrderRow.getMarkUp():new BigDecimal("0");
					otherTaxesWithMarkup=taxesPriceInBooking;

					grossFare=basePriceInBooking.add(otherTaxesWithMarkup).add(markup);
					commonDsrTravelReportData.setMarkup(markup.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax(commonDsrTravelReportData.getMarkup());
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					commonDsrTravelReportData.setK3Tax("0");
					transactionFee = feeAmount;
					BaseServiceTax = (busOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getBasicTax()!=null?busOrderRow.getBusOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					swachBharatCess =(busOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getSwatchBharathCess()!=null?busOrderRow.getBusOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					krishiKalyanCess =(busOrderRow.getTotalAmount().subtract(convenienceFee)).divide(new BigDecimal(100)).multiply(busOrderRow.getBusOrderRowServiceTax()!=null && busOrderRow.getBusOrderRowServiceTax().getKrishiKalyanCess()!=null?busOrderRow.getBusOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).add(swachBharatCess.setScale(2, BigDecimal.ROUND_UP)).add(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP)); 
					managementFee=busOrderRow.getManagementFee()!=null?busOrderRow.getManagementFee():new BigDecimal(0);
					if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
						netFare=grossFare.add(gstOrSrviceTax).add(convenienceFee).add(managementFee);
					else
						netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);

					BigDecimal totAmount=new BigDecimal(0);
					PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(busOrderRow.getInvoiceNo(), Integer.parseInt(busOrderRow.getCompanyId()));
					if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
						for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
							totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
						}
						totAmount=paymentKnockOffRowNew.getBillAmount().subtract(totAmount);
					}
					else
						totAmount=netFare;
					commonDsrTravelReportData.setOutstandingAmount(totAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

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
					commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTotGstTax(busOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setDiscount("0");
					commonDsrTravelReportData.setNetFare(netFare.setScale(0, RoundingMode.UP).toString());
					PaymentTransaction paymentTransaction=new HotelOrderDao().getPaymentTransactionInfo(busOrderRow.getConfirmationNumber());
					if(paymentTransaction!=null) 
						commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

					else
						commonDsrTravelReportData.setModeOfPayment("-");
					commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
					commonDsrTravelReportData.setPersonalBooking("No");
					commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
					RmConfigTripDetailsModel 	rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(busOrderRow.getOrderId(),company.getCompanyid());
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
					commonDsrTravelReportData.setKnockOff(busOrderRow.isKnockOff());
					commonDsrTravelReportData.setCompanyId(Integer.parseInt(busOrderRow.getCompanyId()));
					commonDsrTravelReports.add(commonDsrTravelReportData);
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("B",busOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}

			}
		}

		return commonDsrTravelReports;

	}
	public List<CommonDsrTravelReportData> carOutstandingReports(CommonDsrPage commonDsrPage,CommonDsrUtility commonDsrUtility,List<CommonDsrTravelReportData> commonDsrTravelReports,Company  newCompanyObj,Company  sessionCompany ){
		if(commonDsrUtility!=null && commonDsrUtility.getCarOrderRowList()!=null && commonDsrUtility.getCarOrderRowList().size()>0){
			for(CarOrderRow carOrderRow:commonDsrUtility.getCarOrderRowList()){
				Object carOrderRowObj=carOrderRow;
				String taxType=commonDsrUtility.getTaxType();
				CompanyEntity companyEntity=null;
				String billingEntity="-";
				if(carOrderRow.getCompanyEntityId()!=null) 
					companyEntity =	new CompanyDAO().companyEntityProfile(carOrderRow.getCompanyEntityId().intValue());
				if(companyEntity!=null && companyEntity.getCompanyEntityName()!=null) 
					billingEntity=companyEntity.getCompanyEntityName();

				if(carOrderRow!=null ){
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
					commonDsrTravelReportData.setBookingDate(carOrderRow.getCarBookingDate()!=null?carOrderRow.getCarBookingDate():"-");
					Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(carOrderRow.getCompanyId()));
					User user=new UserDAO().GetUserProfile(Integer.parseInt(carOrderRow.getUserId().trim().equals("")?"0":carOrderRow.getUserId()));
					if(company!=null && company.getCompanyname()!=null) 
						commonDsrTravelReportData.setCorporateName(company.getCompanyname());
					else
						commonDsrTravelReportData.setCorporateName("-");
					commonDsrTravelReportData.setBillingEntity(billingEntity);
					if(user!=null && user.getUsername()!=null) 
						commonDsrTravelReportData.setBookerName(user.getUsername());
					else
						commonDsrTravelReportData.setBookerName("-");
					String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
					commonDsrTravelReportData.setBookersLoginId(email);
					//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
					commonDsrTravelReportData.setSupplierCode(carOrderRow.getSupplierName());
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
						basePriceInBooking=carOrderRow.getBasePrice().multiply(carOrderRow.getBaseToBookingExchangeRate());
					else
						basePriceInBooking=new BigDecimal("0");

					if(carOrderRow.getSupplierPrice()!=null)
						apiPriceInBooking=carOrderRow.getSupplierPrice().multiply(carOrderRow.getApiToBaseExchangeRate()).multiply(carOrderRow.getBaseToBookingExchangeRate()); 
					else
						apiPriceInBooking=new BigDecimal("0");

					commonDsrTravelReportData.setTaxType(taxType);
					BigDecimal driverAllowanceNight=carOrderRow.getDriverAllowanceNight()!=null?carOrderRow.getDriverAllowanceNight(): new BigDecimal(0);
					BigDecimal driverAllowanceDay=carOrderRow.getDriverAllowanceDay()!=null?carOrderRow.getDriverAllowanceDay(): new BigDecimal(0);
					BigDecimal tollOrParkingCharges=carOrderRow.getTollOrParkingCharges()!=null?carOrderRow.getTollOrParkingCharges(): new BigDecimal(0);
					String extraKM=carOrderRow.getExtraKM()!=null?carOrderRow.getExtraKM():new String("0");
					String extraHours=carOrderRow.getExtraHours()!=null?carOrderRow.getExtraHours():new String("0");

					commonDsrTravelReportData.setDriverAllowancedayCharge(driverAllowanceDay.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setDriverAllowanceNightCharge(driverAllowanceNight.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTollorParkingCharge(tollOrParkingCharges.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraKmCharge(new BigDecimal(extraKM).setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraHourCharge(new BigDecimal(extraHours).setScale(2, BigDecimal.ROUND_UP).toString());

					BigDecimal otherTaxes  =carOrderRow.getOtherTaxes()!=null?carOrderRow.getOtherTaxes():new BigDecimal(0);
					taxesPriceInBooking=otherTaxes; 
					commonDsrTravelReportData.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					if (sessionCompany.getCompanyRole().isSuperUser())
						commonDsrTravelReportData.setSupplierCharge(apiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
					else
						commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

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
					commonDsrTravelReportData.setMarkup(carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP).toString());
					otherTaxesWithMarkup=taxesPriceInBooking;
					BigDecimal convenienceFee = new BigDecimal("0");
					BigDecimal managementFee =new BigDecimal("0");
					BigDecimal gstOrSrviceTax  = new BigDecimal("0");
					grossFare=basePriceInBooking.add(otherTaxesWithMarkup).add(carOrderRow.getMarkUp()).add(driverAllowanceNight).add(driverAllowanceDay).add(tollOrParkingCharges).add(new BigDecimal(extraHours)).add(new BigDecimal(extraKM));
					commonDsrTravelReportData.setGrossFare(grossFare.setScale(2,BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setYQTax("0");
					commonDsrTravelReportData.setYRTax(carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setPSFTax("0");
					commonDsrTravelReportData.setUDFTax("0");
					commonDsrTravelReportData.setJNTax("0");
					commonDsrTravelReportData.setOBTax("0");
					commonDsrTravelReportData.setOCTax("0");
					commonDsrTravelReportData.setK3Tax("0");
					transactionFee = feeAmount;
					convenienceFee=carOrderRow.getConvenienceFee()!=null?carOrderRow.getConvenienceFee():new BigDecimal(0);
					managementFee=carOrderRow.getManagementFee()!=null?carOrderRow.getManagementFee():new BigDecimal(0);
					gstOrSrviceTax=carOrderRow.getTotalGstTax()!=null?carOrderRow.getTotalGstTax():new BigDecimal(0);
					BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
					BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
					if(carOrderRow.getCarOrderRowGstTax()!=null   && taxType!=null && taxType.equalsIgnoreCase("GST")){ 
						CGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowGstTax().getCGST());					  
						if(carOrderRow.getCarOrderRowGstTax().getSGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowGstTax().getSGST());					  
						if(carOrderRow.getCarOrderRowGstTax().getUGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowGstTax().getUGST());					  
						if(carOrderRow.getCarOrderRowGstTax().getIGST().compareTo(new BigDecimal(0))>0)
							SGSTorIGSTorUGSTPerPassenger=managementFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowGstTax().getIGST());					  
					}
					commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
					BigDecimal  totalAmountAfterDeductconvenienceFee=carOrderRow.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).subtract(convenienceFee.setScale(2, BigDecimal.ROUND_UP));
					BaseServiceTax =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getBasicTax()!=null?carOrderRow.getCarOrderRowServiceTax().getBasicTax():new BigDecimal(0));
					BigDecimal swachBharatCess = new BigDecimal("0");
					swachBharatCess =totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getSwatchBharathCess()!=null?carOrderRow.getCarOrderRowServiceTax().getSwatchBharathCess():new BigDecimal(0));					
					BigDecimal krishiKalyanCess = new BigDecimal("0");
					krishiKalyanCess = totalAmountAfterDeductconvenienceFee.divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getKrishiKalyanCess()!=null?carOrderRow.getCarOrderRowServiceTax().getKrishiKalyanCess():new BigDecimal(0));
					BigDecimal TotalServiceTax = new BigDecimal("0");
					TotalServiceTax =BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// basePriceInBooking.setScale(0, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal(100)).multiply(carOrderRow.getCarOrderRowServiceTax()!=null && carOrderRow.getCarOrderRowServiceTax().getTotalTax()!=null?carOrderRow.getCarOrderRowServiceTax().getTotalTax():new BigDecimal(0));
					if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
						netFare=grossFare.add(gstOrSrviceTax).add(convenienceFee).add(managementFee);
					else
						netFare = grossFare.add(TotalServiceTax).add(convenienceFee).add(managementFee);
					BigDecimal totAmount=new BigDecimal(0);
					PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(carOrderRow.getInvoiceNo(), Integer.parseInt(carOrderRow.getCompanyId()));
					if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
						for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
							totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
						}
						totAmount=paymentKnockOffRowNew.getBillAmount().subtract(totAmount);
					}
					else
						totAmount=netFare;
					commonDsrTravelReportData.setOutstandingAmount(totAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					commonDsrTravelReportData.setTotGstTax(carOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setOtherTaxes(otherTaxesWithMarkup.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setExtraCharge("0");
					commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
					commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
					commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
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
					RmConfigTripDetailsModel 	rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(carOrderRow.getOrderId(),company.getCompanyid());
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
					commonDsrTravelReportData.setKnockOff(carOrderRow.isKnockOff());
					commonDsrTravelReportData.setCompanyId(Integer.parseInt(carOrderRow.getCompanyId()));
					commonDsrTravelReports.add(commonDsrTravelReportData);
					CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("C",carOrderRowObj, commonDsrTravelReportData);
					if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
						commonDsrTravelReportsNew.setBillingEntity(billingEntity);
						commonDsrTravelReports.add(commonDsrTravelReportsNew);
					}
				}

			}
		}
		return commonDsrTravelReports;

	}  

	public List<CommonDsrTravelReportData> hotelOutstandingReports(CommonDsrPage commonDsrPage,CommonDsrUtility commonDsrUtility,List<CommonDsrTravelReportData> commonDsrTravelReports,Company  newCompanyObj,Company  sessionCompany ){
		if(commonDsrUtility!=null && commonDsrUtility.getHotelOrderRowList()!=null && commonDsrUtility.getHotelOrderRowList().size()>0){
			for(HotelOrderRow hotelOrderRow:commonDsrUtility.getHotelOrderRowList()){
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
						if(company!=null && company.getCompanyname()!=null) 
							commonDsrTravelReportData.setCorporateName(company.getCompanyname());
						else
							commonDsrTravelReportData.setCorporateName("-");
						commonDsrTravelReportData.setBillingEntity(billingEntity);
						if(user!=null && user.getUsername()!=null) 
							commonDsrTravelReportData.setBookerName(user.getUsername());
						else
							commonDsrTravelReportData.setBookerName("-");
						String email=user!=null && user.getEmail()!=null?user.getEmail():"NA";
						commonDsrTravelReportData.setBookersLoginId(email);
						//commonDsrTravelReportData.setBookersLoginId(user.getEmail());
						String apiProviderName="-";
						ApiProvider apiProvoder=null;
						if(hotelOrderRow.getBookingMode() !=null &&hotelOrderRow.getBookingMode().equalsIgnoreCase("Offline"))
							apiProviderName=hotelOrderRow.getApiProvoder();
						if(hotelOrderRow.getBookingMode() !=null && hotelOrderRow.getBookingMode().equalsIgnoreCase("Online")) 
							apiProvoder=new ApiProviderDao().getApiPrividerDetails(hotelOrderRow.getApiProvoder());
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

						if(hotelOrderRow.getFeeAmount()!=null)
							feeAmount= hotelOrderRow.getFeeAmount().divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
						else  
							feeAmount=new BigDecimal("0");


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
						transactionFee = feeAmount;
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
						if(commonDsrTravelReportData.getTaxType()!=null && commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
							netFare=grossFare.add(gstPerRoom).add(managementFeePerRoom).add(convenienceFeePerRoom);
						else
							netFare = grossFare.add(TotalServiceTax).add(managementFeePerRoom).add(convenienceFeePerRoom);

						BigDecimal totAmount=new BigDecimal(0);
						PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(hotelOrderRow.getInvoiceNo(), Integer.parseInt(hotelOrderRow.getCompanyId()));
						if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
							for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
								totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
							}
							totAmount=paymentKnockOffRowNew.getBillAmount().subtract(totAmount);
							totAmount=ArithmeticUti.divideTo2Decimal(totAmount, new BigDecimal(hotelOrderRow.getHotelOrderRoomInfos().size()));
						}
						else
							totAmount=netFare;
						commonDsrTravelReportData.setOutstandingAmount(totAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
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
						RmConfigTripDetailsModel 	rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(hotelOrderRow.getOrderReference(),company.getCompanyid());
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
						commonDsrTravelReportData.setKnockOff(hotelOrderRow.isKnockOff());
						commonDsrTravelReportData.setCompanyId(Integer.parseInt(hotelOrderRow.getCompanyId()));
						commonDsrTravelReports.add(commonDsrTravelReportData);
						CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("H",hotelOrderRowObj, commonDsrTravelReportData);
						if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
							commonDsrTravelReportsNew.setBillingEntity(billingEntity);
							commonDsrTravelReports.add(commonDsrTravelReportsNew);
						}
					}
				}
			}
		}
		return commonDsrTravelReports;

	}  

	public List<CommonDsrTravelReportData> flightOutstandingReports(CommonDsrPage commonDsrPage,CommonDsrUtility commonDsrUtility,List<CommonDsrTravelReportData> commonDsrTravelReports,Company  newCompanyObj,Company  sessionCompany ){
		if(commonDsrUtility!=null && commonDsrUtility.getFlightOrderRowList()!=null && commonDsrUtility.getFlightOrderRowList().size()>0){
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
			for(FlightOrderRow flightOrderRow:commonDsrUtility.getFlightOrderRowList()){
				Object flightOrderRowObj=flightOrderRow;
				BigDecimal convenienceFee = new BigDecimal("0");
				BigDecimal managementFee = new BigDecimal("0");
				BigDecimal gstPerPassenger =new BigDecimal("0"); 
				BigDecimal CGSTPerPassenger =new BigDecimal("0"); 
				BigDecimal SGSTorIGSTorUGSTPerPassenger =new BigDecimal("0"); 
				String taxType=commonDsrUtility.getTaxType();
				/*if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST"))
					taxType=newCompanyConfig.getTaxtype();*/
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

				if(flightOrderRow!=null && flightOrderRow.getFlightOrderCustomers()!=null && flightOrderRow.getFlightOrderCustomers().size()>0){
					for(int j=0;j<flightOrderRow.getFlightOrderCustomers().size();j++){
						CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
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
						if(company!=null && company.getCompanyname()!=null) 
							commonDsrTravelReportData.setCorporateName(company.getCompanyname());
						else
							commonDsrTravelReportData.setCorporateName("-");

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
						BigDecimal othertaxesInBooking=new BigDecimal(0);
						BigDecimal discountInBooking=new BigDecimal(0);
						BigDecimal markupInBooking=new BigDecimal(0);
						BigDecimal supplierDiscountInBooking=new BigDecimal(0);
						BigDecimal netFare=new BigDecimal(0);
						BigDecimal grossFare=new BigDecimal(0);
						BigDecimal supplierCharge=new BigDecimal(0);
						BigDecimal baggagePriceInBooking=new BigDecimal(0);
						BigDecimal mealPriceInBooking=new BigDecimal(0);
						BigDecimal totExtraChargeInBooking=new BigDecimal(0);
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
						commonDsrTravelReportData.setSupplierCode(flightOrderRow.getProviderAPI());
						commonDsrTravelReportData.setSupplierName(flightOrderRow.getProviderAPI());
						commonDsrTravelReportData.setProductType("Air");
						String itineraryType=CommonDsrDao.isDomesticOrInternational(flightOrderRow.getDestination(),flightOrderRow.getOrigin())?"International":"Domestic";
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
							commonDsrTravelReportData.setProductCode(trips.getOperatedByCode()!=null?trips.getOperatedByCode()+"-":""+trips.getFlightNumber());
							commonDsrTravelReportData.setCabinClass(trips.getClassOfService());
							commonDsrTravelReportData.setBookingClass(trips.getFareClass());
							commonDsrTravelReportData.setFareBasis(trips.getFareBasisCode());
							commonDsrTravelReportData.setFareType(trips.getCraft());

						}

						commonDsrTravelReportData.setMarkup(markupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setDescription(srcCode+"/"+descode);
						commonDsrTravelReportData.setDescription2("-");
						commonDsrTravelReportData.setAirlinePNRorProvBooking(flightOrderRow.getPnr());
						commonDsrTravelReportData.setGDSPNR(flightOrderRow.getGdsPnr()!=null?flightOrderRow.getGdsPnr():"NA");
						commonDsrTravelReportData.setTicketNumorFinalBooking(flightOrderCustomer.getEticketnumber());
						commonDsrTravelReportData.setBookingRefundType("-");
						commonDsrTravelReportData.setPaxType(flightOrderCustomer.getPassengerTypeCode());
						commonDsrTravelReportData.setTraveller(flightOrderCustomer.getTitle()+" "+flightOrderCustomer.getFirstName()+" "+flightOrderCustomer.getLastName());
						commonDsrTravelReportData.setTotalNights("0");
						commonDsrTravelReportData.setTripStartsDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
						commonDsrTravelReportData.setTripEndDate(DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getArrivalDate()));
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


						BigDecimal totAmount=new BigDecimal(0);
						PaymentKnockOffRow  paymentKnockOffRowNew=paymentKnockDao.fetchPaymentKnockOffRow(flightOrderRow.getInvoiceNo(), Integer.parseInt(flightOrderRow.getCompanyId()));
						if(paymentKnockOffRowNew!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs()!=null && paymentKnockOffRowNew.getPaymentKnockOffRowTxs().size()>0){
							for(PaymentKnockOffRowTx paymentKnockOffRowTx:paymentKnockOffRowNew.getPaymentKnockOffRowTxs()){
								totAmount=totAmount.add(paymentKnockOffRowTx.getAmount());
							}
							totAmount=paymentKnockOffRowNew.getBillAmount().subtract(totAmount);
							totAmount=ArithmeticUti.divideTo2Decimal(totAmount, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
						}
						else
							totAmount=netFare;
						commonDsrTravelReportData.setOutstandingAmount(totAmount.setScale(2, BigDecimal.ROUND_UP).toString());
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
						RmConfigTripDetailsModel rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(flightOrderRow.getOrderId(),company.getCompanyid());
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
						commonDsrTravelReportData.setKnockOff(flightOrderRow.isKnockOff());
						commonDsrTravelReportData.setCompanyId(Integer.parseInt(flightOrderRow.getCompanyId()));
						commonDsrTravelReports.add(commonDsrTravelReportData);

						CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("F",flightOrderRowObj, commonDsrTravelReportData);
						if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
							commonDsrTravelReportsNew.setBillingEntity(billingEntity);
							commonDsrTravelReports.add(commonDsrTravelReportsNew);
						}
					}
				}

			}
		}
		return commonDsrTravelReports;
	}
 

}
