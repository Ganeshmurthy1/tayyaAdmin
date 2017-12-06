package com.common.dsr.helper;

import java.math.BigDecimal;
import java.util.List;

import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.CommonDsrTravelReportData;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.utility.ArithmeticUti;
import com.lintas.utility.DateConversion;

public class DsrRmConfigOrderCancelHelper {
	DsrCreditNoteDaoHelper dsrCreditNoteDaoHelper=new DsrCreditNoteDaoHelper();
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DsrRmConfigOrderCancelHelper.class);
	public  CommonDsrTravelReportData showCancelledOrderDetails(String travelType,Object orderRowObj, CommonDsrTravelReportData  commonDsrTravelReportData){
		CommonDsrTravelReportData commonDsrTravelReportDataNew  = null;
		switch (travelType) {
		case "F":
			FlightOrderRow flightOrderRow= (FlightOrderRow) orderRowObj;
			if(flightOrderRow.isCreditNoteIssued()){
				List<CreditNote> creditNoteList=dsrCreditNoteDaoHelper.getFlightCreditNoteDetails(flightOrderRow);
				CreditNote superCreditNote=null;
				if(creditNoteList!=null && creditNoteList.size()>0){
					superCreditNote=creditNoteList.get(0);
					for(CreditNote creditNote:creditNoteList){
						if(creditNote.getCompanyId().equals(flightOrderRow.getCompanyId())){
							commonDsrTravelReportDataNew=buildFlightCancelledOrderDetails(commonDsrTravelReportData, creditNote, superCreditNote,flightOrderRow);
						}
					}
				}
			}
			break;
		case "H":
			HotelOrderRow hotelOrderRow= (HotelOrderRow) orderRowObj;
			if(hotelOrderRow.isCreditNoteIssued()){
				List<HotelCreditNote> hotelCreditNoteList=dsrCreditNoteDaoHelper.getHotelCreditNoteDetails(hotelOrderRow);
				HotelCreditNote superCreditNote=null;
				if(hotelCreditNoteList!=null && hotelCreditNoteList.size()>0){
					superCreditNote=hotelCreditNoteList.get(0);
					for(HotelCreditNote creditNote:hotelCreditNoteList){
						if(creditNote.getCompanyId().equals(hotelOrderRow.getCompanyId())){
							commonDsrTravelReportDataNew=buildHotelCancelledOrderDetails(commonDsrTravelReportData, creditNote, superCreditNote,hotelOrderRow);
						}
					}
				}
			}
			break;
		case "C":
			CarOrderRow carOrderRow= (CarOrderRow) orderRowObj;
			if(carOrderRow.isCreditNoteIssued()){
				List<CarCreditNote> carCreditNoteList=dsrCreditNoteDaoHelper.getCarCreditNoteDetails(carOrderRow);
				CarCreditNote superCreditNote=null;
				if(carCreditNoteList!=null && carCreditNoteList.size()>0){
					superCreditNote=carCreditNoteList.get(0);
					for(CarCreditNote creditNote:carCreditNoteList){
						if(creditNote.getCompanyId().equals(carOrderRow.getCompanyId())){
							commonDsrTravelReportDataNew=buildCarCancelledOrderDetails(commonDsrTravelReportData, creditNote, superCreditNote,carOrderRow);

						}
					}
				}
			}
			break;

		case "B":
			BusOrderRow busOrderRow= (BusOrderRow) orderRowObj;
			if(busOrderRow.isCreditNoteIssued()){
				List<BusCreditNote> busCreditNoteList=dsrCreditNoteDaoHelper.getBusCreditNoteDetails(busOrderRow);
				BusCreditNote superCreditNote=null;
				if(busCreditNoteList!=null && busCreditNoteList.size()>0){
					superCreditNote=busCreditNoteList.get(0);
					for(BusCreditNote creditNote:busCreditNoteList){
						if(creditNote.getCompanyId().equals(busOrderRow.getCompanyId())){
							commonDsrTravelReportDataNew=buildBusCancelledOrderDetails(commonDsrTravelReportData, creditNote, superCreditNote,busOrderRow);
						}
					}
				}
			}
			break;
		case "T":
			TrainOrderRow trainOrderRow= (TrainOrderRow) orderRowObj;
			if(trainOrderRow.isCreditNoteIssued()){
				List<TrainCreditNote> trainCreditNoteList=dsrCreditNoteDaoHelper.getTrainCreditNoteDetails(trainOrderRow);
				TrainCreditNote superCreditNote=null;
				if(trainCreditNoteList!=null && trainCreditNoteList.size()>0){
					superCreditNote=trainCreditNoteList.get(0);
					for(TrainCreditNote creditNote:trainCreditNoteList){
						if(creditNote.getCompanyId().equals(trainOrderRow.getCompanyId())){
							commonDsrTravelReportDataNew=buildTrainCancelledOrderDetails(commonDsrTravelReportData, creditNote, superCreditNote, trainOrderRow);
						}
					}
				}
			}
			break;
		case "V":
			VisaOrderRow visaOrderRow= (VisaOrderRow) orderRowObj;
			if(visaOrderRow.isCreditNoteIssued()){
				List<VisaCreditNote> visaCreditNoteList=dsrCreditNoteDaoHelper.getVisaCreditNoteDetails(visaOrderRow);
				VisaCreditNote superCreditNote=null;
				if(visaCreditNoteList!=null && visaCreditNoteList.size()>0){
					superCreditNote=visaCreditNoteList.get(0);
					for(VisaCreditNote creditNote:visaCreditNoteList){
						if(creditNote.getCompanyId().equals(visaOrderRow.getCompanyId())){
							commonDsrTravelReportDataNew=buildVisaCancelledOrderDetails(commonDsrTravelReportData, creditNote, superCreditNote,	visaOrderRow);
						}
					}
				}
			}
			break;
		case "I":
			InsuranceOrderRow insuranceOrderRow= (InsuranceOrderRow) orderRowObj;
			if(insuranceOrderRow.isCreditNoteIssued()){
				List<InsuranceCreditNote> insuranceCreditNoteList=dsrCreditNoteDaoHelper.getInsuranceCreditNoteDetails(insuranceOrderRow);
				InsuranceCreditNote superCreditNote=null;
				if(insuranceCreditNoteList!=null && insuranceCreditNoteList.size()>0){
					superCreditNote=insuranceCreditNoteList.get(0);
					for(InsuranceCreditNote creditNote:insuranceCreditNoteList){
						if(creditNote.getCompanyId().equals(insuranceOrderRow.getCompanyId())){
							commonDsrTravelReportDataNew=buildInsuranceCancelledOrderDetails(commonDsrTravelReportData, creditNote, superCreditNote,insuranceOrderRow);
						}
					}
				}
			}
			break;
		case "M":
			MiscellaneousOrderRow miscellaneousOrderRow= (MiscellaneousOrderRow) orderRowObj;
			if(miscellaneousOrderRow.isCreditNoteIssued()){
				List<MiscellaneousCreditNote> miscellaneousCreditNoteList=dsrCreditNoteDaoHelper.getMiscellaneousCreditNoteDetails(miscellaneousOrderRow);
				MiscellaneousCreditNote superCreditNote=null;
				if(miscellaneousCreditNoteList!=null && miscellaneousCreditNoteList.size()>0){
					superCreditNote=miscellaneousCreditNoteList.get(0);
					for(MiscellaneousCreditNote creditNote:miscellaneousCreditNoteList){
						if(creditNote.getCompanyId().equals(String.valueOf(miscellaneousOrderRow.getCompanyId()))){
							commonDsrTravelReportDataNew=buildMiscellaneousCancelledOrderDetails(commonDsrTravelReportData, creditNote, superCreditNote,miscellaneousOrderRow);
						}
					}
				}
			}
			break;


		default:
			break;
		}
		return commonDsrTravelReportDataNew;

	}

	public  CommonDsrTravelReportData buildFlightCancelledOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData,CreditNote creditNote,CreditNote superCreditNote,FlightOrderRow flightOrderRow){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=buildCancelledCommonOrderDetails(commonDsrTravelReportData);
		commonDsrTravelReportDataNew.setSupplierCharge(superCreditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setConvenienceCharge(superCreditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setAmendmentType(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setTravelStatus(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setInvoicedate(DateConversion.convertDateToStringToDate(creditNote.getIssuedAt()));
		int count=flightOrderRow.getFlightOrderCustomers()!=null && flightOrderRow.getFlightOrderCustomers().size()>0?flightOrderRow.getFlightOrderCustomers().size():0;
		BigDecimal netFare=creditNote.getRefundedAmount();
		BigDecimal cancelFee=superCreditNote.getCancellationFees();
		if(count>0){
			netFare=ArithmeticUti.divideTo2Decimal(netFare,new 	BigDecimal(count));
			cancelFee=ArithmeticUti.divideTo2Decimal(cancelFee,new 	BigDecimal(count));
		}
		commonDsrTravelReportDataNew.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setOutstandingAmount(creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate().toString());
		buildCreditNoteDatawithZeroGST(commonDsrTravelReportDataNew);
		commonDsrTravelReportDataNew.setSupplierAmendmentOrCancellationFee(cancelFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setSystemInvoiceId(creditNote.getCNINumber());
		return commonDsrTravelReportDataNew;
	}

	public  CommonDsrTravelReportData buildHotelCancelledOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData,HotelCreditNote creditNote,HotelCreditNote superCreditNote,HotelOrderRow hotelOrderRow){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=buildCancelledCommonOrderDetails(commonDsrTravelReportData);
		commonDsrTravelReportDataNew.setSupplierCharge(superCreditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setConvenienceCharge(superCreditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setAmendmentType(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setTravelStatus(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setInvoicedate(DateConversion.convertDateToStringToDate(creditNote.getIssuedAt()));
		int count=hotelOrderRow.getHotelOrderRoomInfos()!=null && hotelOrderRow.getHotelOrderRoomInfos().size()>0?hotelOrderRow.getHotelOrderRoomInfos().size():0;
		 
		BigDecimal netFare=creditNote.getRefundedAmount();
		BigDecimal cancelFee=superCreditNote.getCancellationFees();
		if(count>0){
			netFare=ArithmeticUti.divideTo2Decimal(netFare,new 	BigDecimal(count));
			cancelFee=ArithmeticUti.divideTo2Decimal(cancelFee,new 	BigDecimal(count));
		}
		commonDsrTravelReportDataNew.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setOutstandingAmount(creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setSupplierAmendmentOrCancellationFee(cancelFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setSystemInvoiceId(creditNote.getCNINumber());
		buildCreditNoteDatawithZeroGST(commonDsrTravelReportDataNew);
		return commonDsrTravelReportDataNew;
	}

	public  CommonDsrTravelReportData buildCarCancelledOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData,CarCreditNote creditNote,CarCreditNote superCreditNote,CarOrderRow carOrderRow){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=buildCancelledCommonOrderDetails(commonDsrTravelReportData);
		commonDsrTravelReportDataNew.setSupplierCharge(superCreditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setConvenienceCharge(superCreditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setAmendmentType(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setTravelStatus(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setInvoicedate(DateConversion.convertDateToStringToDate(creditNote.getIssuedAt()));
		int count=carOrderRow.getCarOrderCustomerList()!=null && carOrderRow.getCarOrderCustomerList().size()>0?carOrderRow.getCarOrderCustomerList().size():0;
		BigDecimal netFare=creditNote.getRefundedAmount();
		BigDecimal cancelFee=superCreditNote.getCancellationFees();
		if(count>0){
			netFare=ArithmeticUti.divideTo2Decimal(netFare,new 	BigDecimal(count));
			cancelFee=ArithmeticUti.divideTo2Decimal(cancelFee,new 	BigDecimal(count));
		}
		commonDsrTravelReportDataNew.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setOutstandingAmount(creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setSupplierAmendmentOrCancellationFee(cancelFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setSystemInvoiceId(creditNote.getCNINumber());
		buildCreditNoteDatawithZeroGST(commonDsrTravelReportDataNew);
		return commonDsrTravelReportDataNew;
	}

	public  CommonDsrTravelReportData buildBusCancelledOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData,BusCreditNote creditNote,BusCreditNote superCreditNote,BusOrderRow busOrderRow){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=buildCancelledCommonOrderDetails(commonDsrTravelReportData);
		commonDsrTravelReportDataNew.setSupplierCharge(superCreditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setConvenienceCharge(superCreditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setAmendmentType(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setTravelStatus(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setInvoicedate(DateConversion.convertDateToStringToDate(creditNote.getIssuedAt()));
		int count=busOrderRow.getBusOrderCustomerDetails()!=null && busOrderRow.getBusOrderCustomerDetails().size()>0?busOrderRow.getBusOrderCustomerDetails().size():0;
		BigDecimal netFare=creditNote.getRefundedAmount();
		BigDecimal cancelFee=superCreditNote.getCancellationFees();
		if(count>0){
			netFare=ArithmeticUti.divideTo2Decimal(netFare,new 	BigDecimal(count));
			cancelFee=ArithmeticUti.divideTo2Decimal(cancelFee,new 	BigDecimal(count));
		}
		commonDsrTravelReportDataNew.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setOutstandingAmount(creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setSupplierAmendmentOrCancellationFee(cancelFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setSystemInvoiceId(creditNote.getCNINumber());
		buildCreditNoteDatawithZeroGST(commonDsrTravelReportDataNew);
		return commonDsrTravelReportDataNew;
	}

	public  CommonDsrTravelReportData buildTrainCancelledOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData,TrainCreditNote creditNote,TrainCreditNote superCreditNote,TrainOrderRow trainOrderRow){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=buildCancelledCommonOrderDetails(commonDsrTravelReportData);
		commonDsrTravelReportDataNew.setSupplierCharge(superCreditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setConvenienceCharge(superCreditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setAmendmentType(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setTravelStatus(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setInvoicedate(DateConversion.convertDateToStringToDate(creditNote.getIssuedAt()));
		int count=trainOrderRow.getTrainOrderCustomerList()!=null && trainOrderRow.getTrainOrderCustomerList().size()>0?trainOrderRow.getTrainOrderCustomerList().size():0;
		BigDecimal netFare=creditNote.getRefundedAmount();
		BigDecimal cancelFee=superCreditNote.getCancellationFees();
		if(count>0){
			netFare=ArithmeticUti.divideTo2Decimal(netFare,new 	BigDecimal(count));
			cancelFee=ArithmeticUti.divideTo2Decimal(cancelFee,new 	BigDecimal(count));
		}
		commonDsrTravelReportDataNew.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setOutstandingAmount(creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setSupplierAmendmentOrCancellationFee(cancelFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setSystemInvoiceId(creditNote.getCNINumber());
		buildCreditNoteDatawithZeroGST(commonDsrTravelReportDataNew);
		return commonDsrTravelReportDataNew;
	}
	public  CommonDsrTravelReportData buildVisaCancelledOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData,VisaCreditNote creditNote,VisaCreditNote superCreditNote,VisaOrderRow visaOrderRow){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=buildCancelledCommonOrderDetails(commonDsrTravelReportData);
		commonDsrTravelReportDataNew.setSupplierCharge(superCreditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setConvenienceCharge(superCreditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setAmendmentType(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setTravelStatus(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setInvoicedate(DateConversion.convertDateToStringToDate(creditNote.getIssuedAt()));
		int count=visaOrderRow.getVisaOrderCustomerList()!=null && visaOrderRow.getVisaOrderCustomerList().size()>0?visaOrderRow.getVisaOrderCustomerList().size():0;
		BigDecimal netFare=creditNote.getRefundedAmount();
		BigDecimal cancelFee=superCreditNote.getCancellationFees();
		if(count>0){
			netFare=ArithmeticUti.divideTo2Decimal(netFare,new 	BigDecimal(count));
			cancelFee=ArithmeticUti.divideTo2Decimal(cancelFee,new 	BigDecimal(count));
		}
		commonDsrTravelReportDataNew.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setOutstandingAmount(creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setSupplierAmendmentOrCancellationFee(cancelFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setSystemInvoiceId(creditNote.getCNINumber());
		buildCreditNoteDatawithZeroGST(commonDsrTravelReportDataNew);
		return commonDsrTravelReportDataNew;
	}
	public  CommonDsrTravelReportData buildInsuranceCancelledOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData,InsuranceCreditNote creditNote,InsuranceCreditNote superCreditNote,InsuranceOrderRow insuranceOrderRow){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=buildCancelledCommonOrderDetails(commonDsrTravelReportData);
		commonDsrTravelReportDataNew.setSupplierCharge(superCreditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setConvenienceCharge(superCreditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setAmendmentType(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setTravelStatus(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setInvoicedate(DateConversion.convertDateToStringToDate(creditNote.getIssuedAt()));
		int count=insuranceOrderRow.getInsuranceOrderCustomerDetails()!=null && insuranceOrderRow.getInsuranceOrderCustomerDetails().size()>0?insuranceOrderRow.getInsuranceOrderCustomerDetails().size():0;
		BigDecimal netFare=creditNote.getRefundedAmount();
		BigDecimal cancelFee=superCreditNote.getCancellationFees();
		if(count>0){
			netFare=ArithmeticUti.divideTo2Decimal(netFare,new 	BigDecimal(count));
			cancelFee=ArithmeticUti.divideTo2Decimal(cancelFee,new 	BigDecimal(count));
		}
		
		commonDsrTravelReportDataNew.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setOutstandingAmount(creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setSupplierAmendmentOrCancellationFee(cancelFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setSystemInvoiceId(creditNote.getCNINumber());
		buildCreditNoteDatawithZeroGST(commonDsrTravelReportDataNew);
		return commonDsrTravelReportDataNew;
	}
	public  CommonDsrTravelReportData buildMiscellaneousCancelledOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData,MiscellaneousCreditNote creditNote,MiscellaneousCreditNote superCreditNote,MiscellaneousOrderRow miscellaneousOrderRow){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=buildCancelledCommonOrderDetails(commonDsrTravelReportData);
		commonDsrTravelReportDataNew.setSupplierCharge(superCreditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setConvenienceCharge(superCreditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setAmendmentType(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setTravelStatus(creditNote.getAfterStatus());
		commonDsrTravelReportDataNew.setInvoicedate(DateConversion.convertDateToStringToDate(creditNote.getIssuedAt()));
		int count=miscellaneousOrderRow.getMiscellaneousOrderCustomerList()!=null && miscellaneousOrderRow.getMiscellaneousOrderCustomerList().size()>0?miscellaneousOrderRow.getMiscellaneousOrderCustomerList().size():0;
		BigDecimal netFare=creditNote.getRefundedAmount();
		BigDecimal cancelFee=superCreditNote.getCancellationFees();
		if(count>0){
			netFare=ArithmeticUti.divideTo2Decimal(netFare,new 	BigDecimal(count));
			cancelFee=ArithmeticUti.divideTo2Decimal(cancelFee,new 	BigDecimal(count));
		}
		commonDsrTravelReportDataNew.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setOutstandingAmount(creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate().toString());
		commonDsrTravelReportDataNew.setSupplierAmendmentOrCancellationFee(cancelFee.setScale(2, BigDecimal.ROUND_UP).toString());
		commonDsrTravelReportDataNew.setSystemInvoiceId(creditNote.getCNINumber());
		buildCreditNoteDatawithZeroGST(commonDsrTravelReportDataNew);
		return commonDsrTravelReportDataNew;
	}

	public  CommonDsrTravelReportData buildCancelledCommonOrderDetails(CommonDsrTravelReportData commonDsrTravelReportData){
		CommonDsrTravelReportData commonDsrTravelReportDataNew=new CommonDsrTravelReportData();
		commonDsrTravelReportDataNew.setAirlinePNRorProvBooking(commonDsrTravelReportData.getAirlinePNRorProvBooking());
		commonDsrTravelReportDataNew.setApproverName(commonDsrTravelReportData.getApproverName());
		commonDsrTravelReportDataNew.setBaseFare(new BigDecimal(commonDsrTravelReportData.getBaseFare()!=null?commonDsrTravelReportData.getBaseFare():"0").negate().toString());
		commonDsrTravelReportDataNew.setBillingEntity(commonDsrTravelReportData.getBillingEntity());
		commonDsrTravelReportDataNew.setBillNonBill(commonDsrTravelReportData.getBillNonBill());
		commonDsrTravelReportDataNew.setBkgRef(commonDsrTravelReportData.getBkgRef());
		commonDsrTravelReportDataNew.setBookerName(commonDsrTravelReportData.getBookerName());
		commonDsrTravelReportDataNew.setBookersLoginId(commonDsrTravelReportData.getBookersLoginId());
		commonDsrTravelReportDataNew.setBookingClass(commonDsrTravelReportData.getBookingClass());
		commonDsrTravelReportDataNew.setBookingDate(commonDsrTravelReportData.getBookingDate());
		commonDsrTravelReportDataNew.setBookingRefundType(commonDsrTravelReportData.getBookingRefundType());
		commonDsrTravelReportDataNew.setBookingType(commonDsrTravelReportData.getBookingType());
		commonDsrTravelReportDataNew.setBusinessUnit(commonDsrTravelReportData.getBusinessUnit());
		commonDsrTravelReportDataNew.setCabinClass(commonDsrTravelReportData.getCabinClass());
		commonDsrTravelReportDataNew.setConvenienceCharge(commonDsrTravelReportData.getConvenienceCharge());
		commonDsrTravelReportDataNew.setCorporateCurrency(commonDsrTravelReportData.getCorporateCurrency());
		commonDsrTravelReportDataNew.setCorporateName(commonDsrTravelReportData.getCorporateName());
		commonDsrTravelReportDataNew.setClientCode(commonDsrTravelReportData.getClientCode());
		commonDsrTravelReportDataNew.setCostCenter(commonDsrTravelReportData.getCostCenter());
		commonDsrTravelReportDataNew.setCourierCharges(new BigDecimal(commonDsrTravelReportData.getCourierCharges()!=null?commonDsrTravelReportData.getCourierCharges():"0").negate().toString());
		commonDsrTravelReportDataNew.setCreditnoteIssued(commonDsrTravelReportData.isCreditnoteIssued());
		commonDsrTravelReportDataNew.setDepartment(commonDsrTravelReportData.getDepartment());
		commonDsrTravelReportDataNew.setDescription(commonDsrTravelReportData.getDescription());
		commonDsrTravelReportDataNew.setDescription2(commonDsrTravelReportData.getDescription2());
		commonDsrTravelReportDataNew.setDiscount(commonDsrTravelReportData.getDiscount());
		commonDsrTravelReportDataNew.setDriverAllowancedayCharge(new BigDecimal(commonDsrTravelReportData.getDriverAllowancedayCharge()!=null?commonDsrTravelReportData.getDriverAllowancedayCharge():"0").negate().toString());
		commonDsrTravelReportDataNew.setDriverAllowanceNightCharge(new BigDecimal(commonDsrTravelReportData.getDriverAllowanceNightCharge()!=null?commonDsrTravelReportData.getDriverAllowanceNightCharge():"0").negate().toString());
		commonDsrTravelReportDataNew.setEmpCode(commonDsrTravelReportData.getEmpCode());
		commonDsrTravelReportDataNew.setExtraCharge(new BigDecimal(commonDsrTravelReportData.getExtraCharge()!=null?commonDsrTravelReportData.getExtraCharge():"0").negate().toString());
		commonDsrTravelReportDataNew.setExtraHourCharge(new BigDecimal(commonDsrTravelReportData.getExtraHourCharge()!=null?commonDsrTravelReportData.getExtraHourCharge():"0").negate().toString());
		commonDsrTravelReportDataNew.setExtraKmCharge(new BigDecimal(commonDsrTravelReportData.getExtraKmCharge()!=null?commonDsrTravelReportData.getExtraKmCharge():"0").negate().toString());
		commonDsrTravelReportDataNew.setExtraRmConfigDetails(commonDsrTravelReportData.getExtraRmConfigDetails());
		commonDsrTravelReportDataNew.setFareBasis(commonDsrTravelReportData.getFareBasis());
		commonDsrTravelReportDataNew.setFareType(commonDsrTravelReportData.getFareType());
		commonDsrTravelReportDataNew.setGDSPNR(commonDsrTravelReportData.getGDSPNR());
		commonDsrTravelReportDataNew.setGrossFare(new BigDecimal(commonDsrTravelReportData.getGrossFare()!=null?commonDsrTravelReportData.getGrossFare():"0").negate().toString());
		commonDsrTravelReportDataNew.setINTax(new BigDecimal(commonDsrTravelReportData.getINTax()!=null?commonDsrTravelReportData.getINTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setItineraryType(commonDsrTravelReportData.getItineraryType());
		commonDsrTravelReportDataNew.setJNTax(new BigDecimal(commonDsrTravelReportData.getJNTax()!=null?commonDsrTravelReportData.getJNTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setK3Tax(new BigDecimal(commonDsrTravelReportData.getK3Tax()!=null?commonDsrTravelReportData.getK3Tax():"0").negate().toString());
		commonDsrTravelReportDataNew.setJourneyType(commonDsrTravelReportData.getJourneyType());
		commonDsrTravelReportDataNew.setKrishiKalyanCess(new BigDecimal(commonDsrTravelReportData.getKrishiKalyanCess()!=null?commonDsrTravelReportData.getKrishiKalyanCess():"0").negate().toString());
		commonDsrTravelReportDataNew.setCGST(new BigDecimal(commonDsrTravelReportData.getCGST()!=null?commonDsrTravelReportData.getCGST():"0").negate().toString());
		commonDsrTravelReportDataNew.setSGSTorUGSTorIGST(new BigDecimal(commonDsrTravelReportData.getSGSTorUGSTorIGST()!=null?commonDsrTravelReportData.getSGSTorUGSTorIGST():"0").negate().toString());
		commonDsrTravelReportDataNew.setTotGstTax(new BigDecimal(commonDsrTravelReportData.getTotGstTax()!=null?commonDsrTravelReportData.getTotGstTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setLocation(commonDsrTravelReportData.getLocation());
		commonDsrTravelReportDataNew.setMarkup(commonDsrTravelReportData.getMarkup());
		commonDsrTravelReportDataNew.setModeOfPayment(commonDsrTravelReportData.getModeOfPayment());
		commonDsrTravelReportDataNew.setOBTax(new BigDecimal(commonDsrTravelReportData.getOBTax()!=null?commonDsrTravelReportData.getOBTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setOCTax(new BigDecimal(commonDsrTravelReportData.getOCTax()!=null?commonDsrTravelReportData.getOCTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setOtherTaxes(new BigDecimal(commonDsrTravelReportData.getOtherTaxes()!=null?commonDsrTravelReportData.getOtherTaxes():"0").negate().toString());
		commonDsrTravelReportDataNew.setPaxType(commonDsrTravelReportData.getPaxType());
		commonDsrTravelReportDataNew.setPersonalBooking(commonDsrTravelReportData.getPersonalBooking());
		commonDsrTravelReportDataNew.setProductCode(commonDsrTravelReportData.getProductCode());
		commonDsrTravelReportDataNew.setProductName(commonDsrTravelReportData.getProductName());
		commonDsrTravelReportDataNew.setProductType(commonDsrTravelReportData.getProductType());
		commonDsrTravelReportDataNew.setProjectCode(commonDsrTravelReportData.getProjectCode());
		commonDsrTravelReportDataNew.setPSFTax(new BigDecimal(commonDsrTravelReportData.getPSFTax()!=null?commonDsrTravelReportData.getPSFTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setReasonForTravel(commonDsrTravelReportData.getReasonForTravel());
		commonDsrTravelReportDataNew.setRemarks(commonDsrTravelReportData.getRemarks());
		commonDsrTravelReportDataNew.setServiceTax(new BigDecimal(commonDsrTravelReportData.getServiceTax()!=null?commonDsrTravelReportData.getServiceTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setServiceTaxBase(new BigDecimal(commonDsrTravelReportData.getServiceTaxBase()!=null?commonDsrTravelReportData.getServiceTaxBase():"0").negate().toString());
		commonDsrTravelReportDataNew.setSupplierCode(commonDsrTravelReportData.getSupplierCode());
		commonDsrTravelReportDataNew.setSupplierName(commonDsrTravelReportData.getSupplierName());
		commonDsrTravelReportDataNew.setSwachBharatCess(new BigDecimal(commonDsrTravelReportData.getSwachBharatCess()!=null?commonDsrTravelReportData.getSwachBharatCess():"0").negate().toString());
		commonDsrTravelReportDataNew.setTayyarahServiceCharges(commonDsrTravelReportData.getTayyarahServiceCharges());
		commonDsrTravelReportDataNew.setTicketNumorFinalBooking(commonDsrTravelReportData.getTicketNumorFinalBooking());
		commonDsrTravelReportDataNew.setTollorParkingCharge(new BigDecimal(commonDsrTravelReportData.getTollorParkingCharge()!=null?commonDsrTravelReportData.getTollorParkingCharge():"0").negate().toString());
		commonDsrTravelReportDataNew.setTotalNights(commonDsrTravelReportData.getTotalNights());
		commonDsrTravelReportDataNew.setTraveller(commonDsrTravelReportData.getTraveller());
		commonDsrTravelReportDataNew.setTravelRequestNumber(commonDsrTravelReportData.getTravelRequestNumber());
		commonDsrTravelReportDataNew.setTripEndDate(commonDsrTravelReportData.getTripEndDate());
		commonDsrTravelReportDataNew.setTripStartsDate(commonDsrTravelReportData.getTripStartsDate());
		commonDsrTravelReportDataNew.setUDFTax(new BigDecimal(commonDsrTravelReportData.getUDFTax()!=null?commonDsrTravelReportData.getUDFTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setVfsCharges(new BigDecimal(commonDsrTravelReportData.getVfsCharges()!=null?commonDsrTravelReportData.getVfsCharges():"0").negate().toString());
		commonDsrTravelReportDataNew.setYQTax(new BigDecimal(commonDsrTravelReportData.getYQTax()!=null?commonDsrTravelReportData.getYQTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setYRTax(new BigDecimal(commonDsrTravelReportData.getYRTax()!=null?commonDsrTravelReportData.getYRTax():"0").negate().toString());
		commonDsrTravelReportDataNew.setNetDiscount(commonDsrTravelReportData.getNetDiscount());
		commonDsrTravelReportDataNew.setArrTime(commonDsrTravelReportData.getArrTime());
		commonDsrTravelReportDataNew.setDepTime(commonDsrTravelReportData.getDepTime());
		commonDsrTravelReportDataNew.setCountryId(commonDsrTravelReportData.getCountryId());
		commonDsrTravelReportDataNew.setFlightNumber(commonDsrTravelReportData.getFlightNumber());
		commonDsrTravelReportDataNew.setSegmentNumber(commonDsrTravelReportData.getSegmentNumber());
		commonDsrTravelReportDataNew.setTravelerPhone(commonDsrTravelReportData.getTravelerPhone());
		commonDsrTravelReportDataNew.setTravelerEmail(commonDsrTravelReportData.getTravelerEmail());
		commonDsrTravelReportDataNew.setBookingTime(commonDsrTravelReportData.getBookingTime());
		commonDsrTravelReportDataNew.setTaxType(commonDsrTravelReportData.getTaxType());
		commonDsrTravelReportDataNew.setRmConfigMap(commonDsrTravelReportData.getRmConfigMap());
		commonDsrTravelReportDataNew.setKnockOff(commonDsrTravelReportData.isKnockOff());
		commonDsrTravelReportDataNew.setCompanyId(commonDsrTravelReportData.getCompanyId());

		return commonDsrTravelReportDataNew;

	}
	private void buildCreditNoteDatawithZeroGST(CommonDsrTravelReportData commonDsrTravelReportDataNew){
		commonDsrTravelReportDataNew.setCGST("0");
		commonDsrTravelReportDataNew.setSGSTorUGSTorIGST("0");
		commonDsrTravelReportDataNew.setTotGstTax("0");
		commonDsrTravelReportDataNew.setTayyarahServiceCharges("0");
		/*commonDsrTravelReportDataNew.setConvenienceCharge("0");*/
	}
}
