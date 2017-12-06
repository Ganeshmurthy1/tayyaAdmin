package com.common.salesreport.dsr;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.admin.common.quotation.dao.CarTravelRequestDao;
import com.admin.common.quotation.dao.TrainTravelRequestDao;
import com.admin.common.quotation.model.CarTravelRequestQuotation;
import com.admin.common.quotation.model.TrainTravelRequestQuotation;
import com.admin.insurance.model.InsuranceOrderCustomerDetail;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.common.dsr.CommonDsrDao;
import com.common.dsr.CommonDsrPage;
import com.common.dsr.CommonDsrReportDao;
import com.common.dsr.CommonDsrTravelReportData;
import com.common.dsr.CommonDsrUtility;
import com.common.dsr.helper.DsrCreditNoteDaoHelper;
import com.common.dsr.helper.DsrRmConfigHelperDao;
import com.common.dsr.helper.DsrRmConfigOrderCancelHelper;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.HotelCreditNote;
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
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.lintas.utility.ArithmeticUti;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.tayyarah.bus.model.BusOrderCustomerDetail;
import com.tayyarah.car.model.CarOrderCustomer;
import com.tayyarah.miscellaneous.model.MiscellaneousOrderCustomer;
import com.tayyarah.train.model.TrainOrderCustomer;
import com.tayyarah.visa.model.VisaOrderCustomer;

public class SalesReportDao {
	CommonDsrReportDao commonDsrReportDao=new CommonDsrReportDao();
	CommonDsrDao commonDsrDao = new CommonDsrDao();
	DsrRmConfigHelperDao dsrRmConfigHelperDao=new DsrRmConfigHelperDao();
	DsrRmConfigOrderCancelHelper dsrRmConfigOrderCancelHelper=new DsrRmConfigOrderCancelHelper();
	DsrCreditNoteDaoHelper dsrCreditNoteDaoHelper=new DsrCreditNoteDaoHelper();
	public SalesReportCalSummary generateSalesReportsToExportExcel(CommonDsrPage commonDsrPage) {
		SalesReportCalSummary salesReportCalSummary = new SalesReportCalSummary();
		List<FlightOrderRow> flightOrderRowList = null;
		List<HotelOrderRow> hotelOrderRowList = null;
		List<CarOrderRow> carOrderRowList = null;
		List<BusOrderRow> busOrderRowList = null;
		List<TrainOrderRow> trainOrderRowList = null;
		List<VisaOrderRow> visaOrderRowList = null;
		List<InsuranceOrderRow> insuranceOrderRowList = null;
		List<MiscellaneousOrderRow> miscellaneousOrderRowList = null;
		CommonDsrUtility CommonDsrUtility = new CommonDsrUtility();

		String travelReportType = commonDsrPage.getCommonDsrFilters().getTravelReportType();
		switch (travelReportType) {
		case "AirSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrFlightReports(commonDsrPage);
			flightOrderRowList = CommonDsrUtility.getFlightOrderRowList();
			buildAirReportData(commonDsrPage, flightOrderRowList, salesReportCalSummary, CommonDsrUtility.getTaxType());
			break;
			// ADDED BY BASHA FOR AIR ROUTE WISE REPORT
		case "AirRouteWiseSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrFlightReports(commonDsrPage);
			flightOrderRowList = CommonDsrUtility.getFlightOrderRowList();
			buildAirRouteWiseReportData(commonDsrPage, flightOrderRowList, salesReportCalSummary,CommonDsrUtility.getTaxType());
			break;
			// ADDED BY BASHA FOR Air Advanced Purchase Sales REPORT
		case "AirAdvencedPurchaseSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrFlightReports(commonDsrPage);
			flightOrderRowList = CommonDsrUtility.getFlightOrderRowList();
			buildAirAdvencedPurchaseSalesReportData(commonDsrPage, flightOrderRowList, salesReportCalSummary,CommonDsrUtility.getTaxType());
			break;

		case "plannedAirTripReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrFlightReports(commonDsrPage);
			flightOrderRowList = CommonDsrUtility.getFlightOrderRowList();
			buildPlannedAirTripReportSalesReportData(commonDsrPage, flightOrderRowList, salesReportCalSummary,
					CommonDsrUtility.getTaxType());
			break;

		case "HotelSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrHotelReports(commonDsrPage);
			hotelOrderRowList = CommonDsrUtility.getHotelOrderRowList();
			buildHotelReportData(commonDsrPage, hotelOrderRowList, salesReportCalSummary,
					CommonDsrUtility.getTaxType());
			break;
		case "HotelcitywiseSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrHotelReports(commonDsrPage);
			hotelOrderRowList = CommonDsrUtility.getHotelOrderRowList();
			buildHotelCityWiseSalesReportData(hotelOrderRowList, salesReportCalSummary, CommonDsrUtility.getTaxType());
			break;
		case "Advancepurchasehotelreport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrHotelReports(commonDsrPage);
			hotelOrderRowList = CommonDsrUtility.getHotelOrderRowList();
			buildHotelAdvanceReportData(commonDsrPage, hotelOrderRowList, salesReportCalSummary,
					CommonDsrUtility.getTaxType());
			break;

		case "CarSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrCarReports(commonDsrPage);
			carOrderRowList = CommonDsrUtility.getCarOrderRowList();
			buildCarReportData(commonDsrPage, carOrderRowList, salesReportCalSummary, CommonDsrUtility.getTaxType());
			break;
		case "BusSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrBusReports(commonDsrPage);
			busOrderRowList = CommonDsrUtility.getBusOrderRowList();
			buildBusReportData(commonDsrPage, busOrderRowList, salesReportCalSummary, CommonDsrUtility.getTaxType());
			break;
		case "TrainSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrTrainReports(commonDsrPage);
			trainOrderRowList = CommonDsrUtility.getTrainOrderRowList();
			buildTrainReportData(commonDsrPage, trainOrderRowList, salesReportCalSummary,CommonDsrUtility.getTaxType());
			break;
		case "VisaSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrVisaReports(commonDsrPage);
			visaOrderRowList = CommonDsrUtility.getVisaOrderRowList();
			buildVisaReportData(commonDsrPage, visaOrderRowList, salesReportCalSummary, CommonDsrUtility.getTaxType());
			break;
		case "InsuranceSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrInsuranceReports(commonDsrPage);
			insuranceOrderRowList = CommonDsrUtility.getInsuranceOrderRowList();
			buildInsuranceReportData(commonDsrPage, insuranceOrderRowList, salesReportCalSummary,
					CommonDsrUtility.getTaxType());
			break;
		case "MiscellaneousSalesReport":
			CommonDsrUtility = commonDsrReportDao.getCommonDsrMiscellaneousReports(commonDsrPage);
			miscellaneousOrderRowList = CommonDsrUtility.getMiscellaneousOrderRowList();
			buildMiscellaneousReportData(commonDsrPage, miscellaneousOrderRowList, salesReportCalSummary,
					CommonDsrUtility.getTaxType());
			break;
		default:
			break;
		}
		return salesReportCalSummary;
	}

	public SalesReportCalSummary buildHotelReportData(CommonDsrPage commonDsrPage,
			List<HotelOrderRow> hotelOrderRowList, SalesReportCalSummary salesReportCalSummary, String taxType) {
		Company newCompanyObj = !commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")
				? new CompanyDAO().CompanyLogin(commonDsrPage.getCommonDsrFilters().getCompanyUserId())
						: commonDsrPage.getCommonDsrFilters().getLoginCompany();
				Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();

				Set<String> cityList = new LinkedHashSet<>();
				List<CommonDsrTravelReportData> commonDsrTravelReports = new ArrayList<>();
				if (hotelOrderRowList != null && hotelOrderRowList.size() > 0) {
					for (HotelOrderRow hotelOrderRow : hotelOrderRowList) {
						Object hotelOrderRowObj = hotelOrderRow;
						BigDecimal serviceOrGstTax = new BigDecimal("0.00");
						if (taxType.equalsIgnoreCase("GST"))
							serviceOrGstTax = hotelOrderRow.getTotGst();
						else if (taxType.equalsIgnoreCase("Service"))
							serviceOrGstTax = hotelOrderRow.getServiceTax();
						else
							serviceOrGstTax = new BigDecimal("0");

						if (hotelOrderRow != null && hotelOrderRow.getHotelOrderRoomInfos() != null
								&& hotelOrderRow.getHotelOrderRoomInfos().size() > 0) {
							CompanyEntity companyEntity = null;
							String billingEntity = "-";
							if (hotelOrderRow.getCompanyEntityId() != null)
								companyEntity = new CompanyDAO()
								.companyEntityProfile(hotelOrderRow.getCompanyEntityId().intValue());
							if (companyEntity != null && companyEntity.getCompanyEntityName() != null)
								billingEntity = companyEntity.getCompanyEntityName();
							for (int j = 0; j < hotelOrderRow.getHotelOrderRoomInfos().size(); j++) {
								CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
								HotelOrderRoomInfo hotelOrderRoomInfo = hotelOrderRow.getHotelOrderRoomInfos().get(j);
								commonDsrTravelReportData.setBkgRef(hotelOrderRow.getOrderReference());
								if (newCompanyObj.getCompanyRole().isSuperUser()
										|| newCompanyObj.getCompanyRole().isCorporate())
									commonDsrTravelReportData.setSystemInvoiceId(hotelOrderRow.getInvoiceNo());
								else
									commonDsrTravelReportData.setSystemInvoiceId("-");

								commonDsrTravelReportData.setBookingType(hotelOrderRow.getBookingMode());
								if(hotelOrderRow.isCreditNoteIssued()) 
									commonDsrTravelReportData.setAmendmentType("Confirmed");
								else
									commonDsrTravelReportData.setAmendmentType(hotelOrderRow.getStatusAction());
								commonDsrTravelReportData.setBookingTime("-");
								commonDsrTravelReportData
								.setInvoicedate(DateConversion.convertDateToStringToDate(hotelOrderRow.getCreatedAt()));
								commonDsrTravelReportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(
										hotelOrderRow.getBookingDate() != null ? hotelOrderRow.getBookingDate() : "-"));
								Company company = new CompanyDAO()
										.getCompanyProfile(Integer.parseInt(hotelOrderRow.getCompanyId()));
								User user = new UserDAO().GetUserProfile(Integer.parseInt(hotelOrderRow.getUserId()));
								if (company != null && company.getCompanyname() != null)
									commonDsrTravelReportData.setCorporateName(company.getCompanyname());
								else
									commonDsrTravelReportData.setCorporateName("-");
								commonDsrTravelReportData.setBillingEntity(billingEntity);

								if (user != null && user.getUsername() != null) 
									commonDsrTravelReportData.setBookerName(user.getUsername());
								else 
									commonDsrTravelReportData.setBookerName("-");
								commonDsrTravelReportData.setBookersLoginId(user!=null && user.getEmail()!=null?user.getEmail():"NA");
								BigDecimal basePriceInBooking = null;
								BigDecimal apiPriceInBooking = null;
								BigDecimal taxesPriceInBooking = null;
								BigDecimal discountInBooking = null;
								BigDecimal roomBasePriceInBooking = null;
								BigDecimal roomApiPriceInBooking = null;
								BigDecimal roomTaxesPriceInBooking = null;
								BigDecimal roomDiscountInBooking = null;
								BigDecimal roomMarkupInBooking = null;
								BigDecimal grossFare = null;
								BigDecimal feeAmount = null;
								BigDecimal netFare = null;
								BigDecimal transactionFee = new BigDecimal("0");
								BigDecimal BaseServiceTax = new BigDecimal("0");
								BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
								BigDecimal managementFeePerRoom = new BigDecimal("0");
								BigDecimal convenienceFeePerRoom = new BigDecimal("0");
								BigDecimal serviceOrGstPerRoom = new BigDecimal("0");
								if (hotelOrderRow.getApiPrice() != null && hotelOrderRow.getTaxes() != null) {
									basePriceInBooking = hotelOrderRow.getApiPrice().subtract(hotelOrderRow.getTaxes())
											.multiply(hotelOrderRow.getApiToBaseExchangeRate())
											.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
									roomBasePriceInBooking = basePriceInBooking
											.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
								} else
									roomBasePriceInBooking = new BigDecimal("0");

								if (hotelOrderRow.getApiPrice() != null) {
									apiPriceInBooking = hotelOrderRow.getApiPrice()
											.multiply(hotelOrderRow.getApiToBaseExchangeRate())
											.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
									roomApiPriceInBooking = apiPriceInBooking
											.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
								} else
									roomApiPriceInBooking = new BigDecimal("0");

								if (hotelOrderRow.getTaxes() != null) {
									taxesPriceInBooking = hotelOrderRow.getTaxes()
											.multiply(hotelOrderRow.getApiToBaseExchangeRate())
											.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
									roomTaxesPriceInBooking = taxesPriceInBooking
											.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
								} else
									roomTaxesPriceInBooking = new BigDecimal("0");

								if (hotelOrderRow.getDiscountAmount() != null) {
									discountInBooking = hotelOrderRow.getDiscountAmount()
											.multiply(hotelOrderRow.getApiToBaseExchangeRate())
											.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
									roomDiscountInBooking = discountInBooking
											.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
								} else
									roomDiscountInBooking = new BigDecimal("0");

								if (hotelOrderRow.getMarkupAmount() != null)
									roomMarkupInBooking = hotelOrderRow.getMarkupAmount()
									.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));

								else
									roomMarkupInBooking = new BigDecimal("0");

								if (hotelOrderRow.getFeeAmount() != null)
									feeAmount = hotelOrderRow.getFeeAmount()
									.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
								else
									feeAmount = new BigDecimal("0");

								if (taxType.equalsIgnoreCase("Service")) {
									if (hotelOrderRow.getHotelOrderRowServiceTax() != null
											&& hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee() != null)
										managementFeePerRoom = hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee()
										.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
									else
										managementFeePerRoom = new BigDecimal("0");
									if (hotelOrderRow.getHotelOrderRowServiceTax() != null
											&& hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee() != null)
										convenienceFeePerRoom = hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()
										.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
									else
										convenienceFeePerRoom = new BigDecimal("0");
								}
								if (taxType.equalsIgnoreCase("GST")) {
									if (hotelOrderRow.getHotelOrderRowGstTax() != null
											&& hotelOrderRow.getHotelOrderRowGstTax().getManagementFee() != null)
										managementFeePerRoom = hotelOrderRow.getHotelOrderRowGstTax().getManagementFee()
										.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
									else
										managementFeePerRoom = new BigDecimal("0");
									if (hotelOrderRow.getHotelOrderRowGstTax() != null
											&& hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee() != null)
										convenienceFeePerRoom = hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee()
										.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
									else
										convenienceFeePerRoom = new BigDecimal("0");

								}
								serviceOrGstPerRoom = ArithmeticUti.divideTo2Decimal(serviceOrGstTax,
										new BigDecimal(hotelOrderRow.getNoOfRooms()));
								commonDsrTravelReportData.setTaxType(taxType);
								commonDsrTravelReportData
								.setMarkup(roomMarkupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData
								.setBaseFare(roomBasePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
								if (sessionCompany.getCompanyRole().isSuperUser())
									commonDsrTravelReportData.setSupplierCharge(
											roomApiPriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
								else
									commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());

								commonDsrTravelReportData.setProductType("Hotel");
								String itineraryType = hotelOrderRow.getHotelOrderHotelData().getCountry()
										.equalsIgnoreCase("India") ? "Domestic Hotel" : "International Hotel";
								commonDsrTravelReportData.setItineraryType(itineraryType);
								commonDsrTravelReportData.setProductName(hotelOrderRow.getHotelOrderHotelData().getName());
								commonDsrTravelReportData
								.setDescription("Hotel Name: " + hotelOrderRow.getHotelOrderHotelData().getName());
								commonDsrTravelReportData.setTicketNumorFinalBooking(hotelOrderRow.getApiConfirmationNo());
								commonDsrTravelReportData.setGuestCount(hotelOrderRoomInfo.getHotelOrderGuests().size());

								commonDsrTravelReportData
								.setRating(String.valueOf(hotelOrderRow.getHotelOrderHotelData().getStars() == null
								? "-" : hotelOrderRow.getHotelOrderHotelData().getStars()));
								commonDsrTravelReportData.setCity(hotelOrderRow.getHotelOrderHotelData().getCity());
								commonDsrTravelReportData.setAddress(hotelOrderRow.getHotelOrderHotelData().getAddress1());
								commonDsrTravelReportData.setPincode("-");
								commonDsrTravelReportData.setFareType(hotelOrderRow.getHotelOrderHotelData().getType()!=null && !hotelOrderRow.getHotelOrderHotelData().getType().trim().equalsIgnoreCase("")?hotelOrderRow.getHotelOrderHotelData().getType():"-");
								commonDsrTravelReportData.setPhone(hotelOrderRow.getHotelOrderHotelData().getTelephone());
								commonDsrTravelReportData.setState(hotelOrderRow.getHotelOrderHotelData().getState()!=null && !hotelOrderRow.getHotelOrderHotelData().getState().trim().equalsIgnoreCase("")?hotelOrderRow.getHotelOrderHotelData().getState():"-" );
								commonDsrTravelReportData.setCountry(hotelOrderRow.getHotelOrderHotelData().getCountry()!=null && !hotelOrderRow.getHotelOrderHotelData().getCountry().trim().equalsIgnoreCase("")?hotelOrderRow.getHotelOrderHotelData().getCountry():"-");
								if (hotelOrderRoomInfo.getHotelOrderGuests() != null
										&& hotelOrderRoomInfo.getHotelOrderGuests().size() > 0) {
									for (HotelOrderGuest hotelOrderGuest : hotelOrderRoomInfo.getHotelOrderGuests()) {
										if (hotelOrderGuest != null && hotelOrderGuest.getLeader()) {
											commonDsrTravelReportData.setPaxType(hotelOrderGuest.getPaxType());
											commonDsrTravelReportData.setTraveller(hotelOrderGuest.getTitle() + " "
													+ hotelOrderGuest.getFirstName() + " " + hotelOrderGuest.getLastName());
											commonDsrTravelReportData
											.setTravelerEmail(hotelOrderRow.getOrderCustomer().getEmail() != null && !hotelOrderRow.getOrderCustomer().getEmail().trim().equalsIgnoreCase("")
											? hotelOrderRow.getOrderCustomer().getEmail() : "-");
											commonDsrTravelReportData
											.setTravelerPhone(hotelOrderRow.getOrderCustomer().getPhone() != null && !hotelOrderRow.getOrderCustomer().getPhone().trim().equalsIgnoreCase("")
											? hotelOrderRow.getOrderCustomer().getPhone() : "-");
										}

									}
								}

								String checkInDate = DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate());
								String checkOutDate = DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate());
								int noOfNights = 0;
								try {
									noOfNights = CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								commonDsrTravelReportData.setTotalNights(String.valueOf(noOfNights));
								commonDsrTravelReportData.setTripStartsDate(
										DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate()));
								commonDsrTravelReportData.setTripEndDate(
										DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate()));
								commonDsrTravelReportData.setJourneyType("NA");
								otherTaxesWithMarkup = roomTaxesPriceInBooking.setScale(2, BigDecimal.ROUND_UP)
										.add(roomMarkupInBooking).setScale(2, BigDecimal.ROUND_UP);
								grossFare = roomBasePriceInBooking.add(otherTaxesWithMarkup);// .add(roomMarkupInBooking);

								commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData.setYQTax("0");
								commonDsrTravelReportData
								.setYRTax(roomMarkupInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData.setPSFTax("0");
								commonDsrTravelReportData.setUDFTax("0");
								commonDsrTravelReportData.setJNTax("0");
								commonDsrTravelReportData.setOBTax("0");
								commonDsrTravelReportData.setOCTax("0");
								transactionFee = feeAmount;
								commonDsrTravelReportData.setDriverAllowancedayCharge("0.00");
								commonDsrTravelReportData.setDriverAllowanceNightCharge("0.00");
								commonDsrTravelReportData.setTollorParkingCharge("0.00");

								commonDsrTravelReportData.setExtraKmCharge("0.00");
								commonDsrTravelReportData.setExtraHourCharge("0.00");
								BaseServiceTax = grossFare.add(managementFeePerRoom).setScale(2, BigDecimal.ROUND_UP)
										.divide(new BigDecimal(100))
										.multiply(hotelOrderRow.getHotelOrderRowServiceTax() != null
										&& hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax() != null
										? hotelOrderRow.getHotelOrderRowServiceTax().getBasicTax()
												: new BigDecimal(0));
								BigDecimal swachBharatCess = new BigDecimal("0");
								swachBharatCess = grossFare.add(managementFeePerRoom).divide(new BigDecimal(100))
										.multiply(hotelOrderRow.getHotelOrderRowServiceTax() != null
										&& hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess() != null
										? hotelOrderRow.getHotelOrderRowServiceTax().getSwatchBharathCess()
												: new BigDecimal(0));
								BigDecimal krishiKalyanCess = new BigDecimal("0");
								krishiKalyanCess = grossFare.add(managementFeePerRoom).divide(new BigDecimal(100))
										.multiply(hotelOrderRow.getHotelOrderRowServiceTax() != null
										&& hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess() != null
										? hotelOrderRow.getHotelOrderRowServiceTax().getKrishiKalyanCess()
												: new BigDecimal(0));
								BigDecimal TotalServiceTax = new BigDecimal("0");
								TotalServiceTax = BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);// BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//basePriceInBooking.setScale(0,

								if (taxType.equalsIgnoreCase("GST"))
									if(serviceOrGstPerRoom!=null)
										TotalServiceTax = serviceOrGstPerRoom;
									else
										TotalServiceTax =new BigDecimal("0");
								netFare = grossFare.add(TotalServiceTax).add(managementFeePerRoom).add(convenienceFeePerRoom);
								commonDsrTravelReportData.setTayyarahServiceCharges(
										managementFeePerRoom.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData
								.setOtherTaxes(otherTaxesWithMarkup.setScale(2, BigDecimal.ROUND_UP).toString());// otherTaxesWithMarkup.setScale(0,

								commonDsrTravelReportData.setExtraCharge("0");
								commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
								commonDsrTravelReportData
								.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData
								.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData
								.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData
								.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData.setConvenienceCharge(
										convenienceFeePerRoom.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData
								.setDiscount(roomDiscountInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
								commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
								PaymentTransaction paymentTransaction = new HotelOrderDao()
										.getPaymentTransactionInfo(hotelOrderRow.getOrderReference());
								if (paymentTransaction != null)
									commonDsrTravelReportData.setModeOfPayment(paymentTransaction.getPayment_method());

								commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
								commonDsrTravelReportData.setPersonalBooking("No");
								commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
								commonDsrTravelReportData.setINTax("0");
								commonDsrTravelReportData.setVfsCharges("0");
								commonDsrTravelReportData.setCourierCharges("0");
								
								
								
								
								//added by basha for rm config per pax on 24-10-2017
								RmConfigTripDetailsModel 	rmConfigTripDetails=dsrRmConfigHelperDao.getLeadPersonRmDetails(hotelOrderRoomInfo.getHotelOrderGuests());
								if(rmConfigTripDetails!=null) 
									rmConfigTripDetails =dsrRmConfigHelperDao.getHotelPaxRmConfigTripDetails(rmConfigTripDetails,hotelOrderRow.getHotelOrderRowRmConfigStruct().getRmDynamicData());
								else
									rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(hotelOrderRow.getOrderReference(),company.getCompanyid());

								if(rmConfigTripDetails!=null)
								{
									if(sessionCompany.getCompanyRole().isSuperUser()){
										if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("H") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
											commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
										else 
											commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
									}
									else 
										commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
									
								}
								buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
								//ended by basha for rm config per pax on 24-10-2017
								
								commonDsrTravelReportData.setCreditnoteIssued(hotelOrderRow.isCreditNoteIssued());
								commonDsrTravelReports.add(commonDsrTravelReportData);
								CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("H",hotelOrderRowObj, commonDsrTravelReportData);
								if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
									commonDsrTravelReportsNew.setCity(commonDsrTravelReportData.getCity());
									commonDsrTravelReportsNew.setAddress(commonDsrTravelReportData.getAddress());
									commonDsrTravelReportsNew.setPincode(commonDsrTravelReportData.getPincode());
									commonDsrTravelReportsNew.setState(commonDsrTravelReportData.getState());
									commonDsrTravelReportsNew.setCountry(commonDsrTravelReportData.getCountry());
									commonDsrTravelReportsNew.setPhone(commonDsrTravelReportData.getPhone());
									commonDsrTravelReportsNew.setBookingDate(commonDsrTravelReportData.getInvoicedate());
									commonDsrTravelReports.add(commonDsrTravelReportsNew);
								}
							}
						}
						if (hotelOrderRow.getHotelOrderHotelData() != null) {
							String narray[] = null;
							String city = hotelOrderRow.getHotelOrderHotelData().getCity()!=null?hotelOrderRow.getHotelOrderHotelData().getCity():"NA,";
							if (city != null && city.contains(",")) {
								narray = city.split(",");
								city = narray[0];
							}
							if(city.equalsIgnoreCase("Bangalore") || city.equalsIgnoreCase("Bengaluru") ){
								city="Bengaluru(Bangalore)";
							}
							cityList.add(city);
						}
					}
				}
				salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);
				salesReportCalSummary.setAirOrHotelSalesReportList(buildRequiredDataFromSummaryMap(buildHotelSalesSummary(cityList, hotelOrderRowList,taxType),"H"));
				return salesReportCalSummary;
	}

	// ADDED BY BASHA FOR AIR ROUTE WISE REPORT
	private SalesReportCalSummary buildAirRouteWiseReportData(CommonDsrPage commonDsrPage,List<FlightOrderRow> flightOrderRowList, SalesReportCalSummary salesReportCalSummary, String taxType) {
		Set<String> filterAirlineList = new LinkedHashSet<>();
		List<CommonDsrTravelReportData> commonDsrTravelReports = new ArrayList<>();
		if (flightOrderRowList != null && flightOrderRowList.size() > 0) {
			for (FlightOrderRow flightOrderRow : flightOrderRowList) {
				if (flightOrderRow.getOrigin() != null && flightOrderRow.getDestination() != null) {
					StringBuilder tripCode = new StringBuilder();
					for (int i = 0; i < flightOrderRow.getFlightOrderTripDetails().size(); i++) {
						FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
						String srcCode = trips.getOriginCode() + "-";
						String descode = trips.getDestinationCode();
						String islastindexcode = "";
						if (i != flightOrderRow.getFlightOrderTripDetails().size() - 1) {
							islastindexcode = "-";
						}
						tripCode.append(srcCode + descode + islastindexcode);
					}
					filterAirlineList.add(tripCode.toString());
				}
			}
			salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);
			salesReportCalSummary.setAirOrHotelSalesReportList(buildRequiredAirRouteWiseDataFromSummaryMap(buildAirRouteWiseSalesSummary(filterAirlineList, flightOrderRowList, taxType)));
		}
		return salesReportCalSummary;

	}

	// ADDED BY BASHA FOR AIR ROUTE WISE REPORT
	private List<SalesReportCalSummary> buildRequiredAirRouteWiseDataFromSummaryMap(Map<String, List<SalesReportCalSummary>> buildAirRouteWiseSalesSummary) {
		Set<String> keySet = buildAirRouteWiseSalesSummary.keySet();
		List<SalesReportCalSummary> summaryTempList = new LinkedList<>();
		for (String key : keySet) {
			int totalCount = 0;
			int cancelledCount = 0;
			BigDecimal totalAmount = new BigDecimal(0);
			BigDecimal avgTotalAmount = new BigDecimal(0);
			BigDecimal totaltktAmount = new BigDecimal(0);
			BigDecimal avgtktAmount = new BigDecimal(0);
			BigDecimal cancelledTktAmount = new BigDecimal(0);
			BigDecimal totalRefundAmount = new BigDecimal(0);
			BigDecimal netSale = new BigDecimal(0);
			List<SalesReportCalSummary> listNew = buildAirRouteWiseSalesSummary.get(key);
			SalesReportCalSummary summaryTemp = new SalesReportCalSummary();
			for (SalesReportCalSummary sum : listNew) {
				totalAmount = totalAmount.add(sum.getInvoiceAmount());
			 //totaltktAmount = totaltktAmount.add(sum.getTotalTktCost());
				totalCount++;
				if (sum.getStatus().equals("Cancelled")) {
					cancelledTktAmount = cancelledTktAmount.add(sum.getRefundAmount());
					cancelledCount++;
				}
			}
			if (totalCount > 0)
				avgTotalAmount = ArithmeticUti.divideTo2Decimal(totalAmount, new BigDecimal(totalCount));
			else
				avgTotalAmount = totalAmount;
			
			netSale=totalAmount.setScale(2, BigDecimal.ROUND_UP).add(cancelledTktAmount.setScale(2, BigDecimal.ROUND_UP));
			summaryTemp.setRouting(key);
			//summaryTemp.setTotalTktCost(totaltktAmount.setScale(2, BigDecimal.ROUND_UP));
			summaryTemp.setTotalFare(totalAmount.setScale(2, BigDecimal.ROUND_UP));
			//summaryTemp.setAvgTktCost(avgtktAmount.setScale(2, BigDecimal.ROUND_UP));
			summaryTemp.setAvgTotalFare(avgTotalAmount.setScale(2, BigDecimal.ROUND_UP));
			summaryTemp.setCancelledCount(cancelledCount);
			summaryTemp.setTotalCount(totalCount);
			summaryTemp.setNetSale(netSale);
			summaryTemp.setRefundAmount(cancelledTktAmount);
			summaryTempList.add(summaryTemp);
		}

		return summaryTempList;

	}

	// ADDED BY BASHA FOR AIR ROUTE WISE REPORT
	public Map<String, List<SalesReportCalSummary>> buildAirRouteWiseSalesSummary(Set<String> airlineList,List<FlightOrderRow> flightOrderRowList, String taxType) {
		Map<String, List<SalesReportCalSummary>> summaryMap = new LinkedHashMap<>();

		if (airlineList != null && airlineList.size() > 0) {
			for (String trips : airlineList) {
				List<SalesReportCalSummary> list = new LinkedList<>();
				String trips1 = trips.split("-")[0];
				String trips2 = trips.substring(trips.lastIndexOf('-') + 1);
				String tripsnew = trips1 + trips2;
				for (FlightOrderRow flightOrderRow : flightOrderRowList) {
					Object flightOrderRowObj=flightOrderRow;
					if (tripsnew.equalsIgnoreCase(flightOrderRow.getOrigin() + flightOrderRow.getDestination())) {
						SalesReportCalSummary salesReportCalSummary = new SalesReportCalSummary();
						if (taxType.equalsIgnoreCase("Service")){
							if(flightOrderRow.getServiceTax()!=null && !flightOrderRow.getServiceTax().equals("")){
								salesReportCalSummary.setInvoiceAmount(flightOrderRow.getFinalPrice().add(flightOrderRow.getServiceTax()).add(flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()).add(flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()));
							}
						}
							if (taxType.equalsIgnoreCase("GST")){
								if(flightOrderRow.getGstOnFlights()!=null && !flightOrderRow.getGstOnFlights().equals(""))
									salesReportCalSummary.setInvoiceAmount(flightOrderRow.getFinalPrice().add(flightOrderRow.getGstOnFlights()).add(flightOrderRow.getFlightOrderRowGstTax()!=null && flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee():new BigDecimal(0)).add(flightOrderRow.getFlightOrderRowGstTax()!=null && flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getManagementFee():new BigDecimal(0)));
							}
							salesReportCalSummary.setStatus(flightOrderRow.getStatusAction());
							BigDecimal refundAmount=getRefundAmount("F", flightOrderRowObj);
							if(refundAmount!=null) 
								salesReportCalSummary.setRefundAmount(refundAmount);
							else
								salesReportCalSummary.setRefundAmount(new BigDecimal(0));
							list.add(salesReportCalSummary);
					 
					}
				}
				summaryMap.put(trips, list);
			}
		}
		return summaryMap;
	}

	public SalesReportCalSummary buildAirReportData(CommonDsrPage commonDsrPage,
			List<FlightOrderRow> flightOrderRowList, SalesReportCalSummary salesReportCalSummary, String taxType) {
		Company newCompanyObj = !commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")
				? new CompanyDAO().CompanyLogin(commonDsrPage.getCommonDsrFilters().getCompanyUserId())
						: commonDsrPage.getCommonDsrFilters().getLoginCompany();
				Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
				Set<String> filterAirlineList = new LinkedHashSet<>();
				List<CommonDsrTravelReportData> commonDsrTravelReports = new ArrayList<>();
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
				if (flightOrderRowList != null && flightOrderRowList.size() > 0) {
					for (FlightOrderRow flightOrderRow : flightOrderRowList) {
						Object flightOrderRowObj = flightOrderRow;
						BigDecimal convenienceFee = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal taxes = new BigDecimal("0");
						BigDecimal gstPerPassenger = new BigDecimal("0");
						BigDecimal CGSTPerPassenger = new BigDecimal("0");
						BigDecimal SGSTorIGSTorUGSTPerPassenger = new BigDecimal("0");
						if (flightOrderRow.getFlightOrderRowGstTax() != null && taxType != null
								&& taxType.equalsIgnoreCase("GST")) {
							managementFee = flightOrderRow.getFlightOrderRowGstTax().getManagementFee() != null
									? flightOrderRow.getFlightOrderRowGstTax().getManagementFee() : new BigDecimal(0);
									convenienceFee = flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee() != null
											? flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee() : new BigDecimal(0);
											CGSTPerPassenger = managementFee.divide(new BigDecimal(100))
													.multiply(flightOrderRow.getFlightOrderRowGstTax().getCGST());
											if (flightOrderRow.getFlightOrderRowGstTax().getSGST().compareTo(new BigDecimal(0)) > 0)
												SGSTorIGSTorUGSTPerPassenger = managementFee.divide(new BigDecimal(100))
												.multiply(flightOrderRow.getFlightOrderRowGstTax().getSGST());
											if (flightOrderRow.getFlightOrderRowGstTax().getUGST().compareTo(new BigDecimal(0)) > 0)
												SGSTorIGSTorUGSTPerPassenger = managementFee.divide(new BigDecimal(100))
												.multiply(flightOrderRow.getFlightOrderRowGstTax().getUGST());
											if (flightOrderRow.getFlightOrderRowGstTax().getIGST().compareTo(new BigDecimal(0)) > 0)
												SGSTorIGSTorUGSTPerPassenger = managementFee.divide(new BigDecimal(100))
												.multiply(flightOrderRow.getFlightOrderRowGstTax().getIGST());
						} else {
							convenienceFee = flightOrderRow.getFlightOrderRowServiceTax() != null
									&& flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee() != null
									? flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()
											: new BigDecimal(0);
									managementFee = flightOrderRow.getFlightOrderRowServiceTax() != null
											&& flightOrderRow.getFlightOrderRowServiceTax().getManagementFee() != null
											? flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()
													: new BigDecimal(0);
						}
						convenienceFee = ArithmeticUti.divideTo2Decimal(convenienceFee,
								new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
						managementFee = ArithmeticUti.divideTo2Decimal(managementFee,
								new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
						gstPerPassenger = ArithmeticUti.divideTo2Decimal(flightOrderRow.getGstOnFlights(),
								new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
						CGSTPerPassenger = ArithmeticUti.divideTo2Decimal(CGSTPerPassenger,
								new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
						SGSTorIGSTorUGSTPerPassenger = ArithmeticUti.divideTo2Decimal(SGSTorIGSTorUGSTPerPassenger,
								new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
						BigDecimal extraBaggagePrice = ArithmeticUti
								.divideTo2Decimal(
										flightOrderRow.getExtrabaggageprice() != null ? flightOrderRow.getExtrabaggageprice()
												: new BigDecimal(0),
												new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));// flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice().divide(new

						BigDecimal extraMealPrice = ArithmeticUti
								.divideTo2Decimal(
										flightOrderRow.getExtramealprice() != null ? flightOrderRow.getExtramealprice()
												: new BigDecimal(0),
												new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));// flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice().divide(new

						String billingEntity = "-";
						CompanyEntity companyEntity=null;
						if (flightOrderRow.getCompanyEntityId() != null)
							companyEntity = new CompanyDAO().companyEntityProfile(flightOrderRow.getCompanyEntityId().intValue());
						if (companyEntity != null && companyEntity.getCompanyEntityName() != null)
							billingEntity = companyEntity.getCompanyEntityName();
						if (flightOrderRow != null && flightOrderRow.getFlightOrderCustomers() != null
								&& flightOrderRow.getFlightOrderCustomers().size() > 0) {
							if(flightOrderRow.getFlightOrderCustomers().size()==flightOrderRow.getFlightOrderCustomerPriceBreakups().size()){
								for (int j = 0; j < flightOrderRow.getFlightOrderCustomers().size(); j++) {
									CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
									FlightOrderCustomer flightOrderCustomer = flightOrderRow.getFlightOrderCustomers().get(j);
									FlightOrderCustomerPriceBreakup flightOrderCustomerPriceBreakup = flightOrderRow
											.getFlightOrderCustomerPriceBreakups().get(j);

									commonDsrTravelReportData.setBkgRef(flightOrderRow.getOrderId());
									if (newCompanyObj.getCompanyRole().isSuperUser()
											|| newCompanyObj.getCompanyRole().isCorporate())
										commonDsrTravelReportData.setSystemInvoiceId(flightOrderRow.getInvoiceNo());
									else
										commonDsrTravelReportData.setSystemInvoiceId("-");
									if (flightOrderRow.getGstOnFlights() != null)
										commonDsrTravelReportData
										.setTotGstTax(gstPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
									commonDsrTravelReportData.setBookingType(flightOrderRow.getBookingMode());

									if(flightOrderRow.isCreditNoteIssued()){ 
										commonDsrTravelReportData.setTravelStatus("Confirmed");
										commonDsrTravelReportData.setAmendmentType("Confirmed");
									}
									else{
										commonDsrTravelReportData.setTravelStatus(flightOrderRow.getStatusAction());
										commonDsrTravelReportData.setAmendmentType(flightOrderRow.getStatusAction());
									}
									commonDsrTravelReportData.setInvoicedate(
											DateConversion.convertDateToStringToDate(flightOrderRow.getCreatedAt()));
									commonDsrTravelReportData.setBookingDate(
											DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
									commonDsrTravelReportData.setDepTime("00:00");
									commonDsrTravelReportData.setArrTime("00:00");
									Company company = new CompanyDAO()
											.getCompanyProfile(Integer.parseInt(flightOrderRow.getCompanyId()));
									User user = new UserDAO().GetUserProfile(Integer.parseInt(flightOrderRow.getUserId()));
									if (company != null && company.getCompanyname() != null)
										commonDsrTravelReportData.setCorporateName(company.getCompanyname());
									else
										commonDsrTravelReportData.setCorporateName("-");
									commonDsrTravelReportData.setBillingEntity(billingEntity);
									if (user != null && user.getUsername() != null) 
										commonDsrTravelReportData.setBookerName(user.getUsername());
									else 
										commonDsrTravelReportData.setBookerName("-");
									commonDsrTravelReportData.setBookersLoginId(user!=null && user.getEmail()!=null?user.getEmail():"NA");
									if (flightOrderRow.getCustomer() != null && flightOrderRow.getCustomer().getCountryId() != null)
										commonDsrTravelReportData.setCountryId(!flightOrderRow.getCustomer().getCountryId().trim().equalsIgnoreCase("")?flightOrderRow.getCustomer().getCountryId():"-");
									else
										commonDsrTravelReportData.setCountryId("-");
									commonDsrTravelReportData.setSegmentNumber("-");
									BigDecimal basePriceInBooking = new BigDecimal(0);
									BigDecimal taxesInBooking = new BigDecimal(0);
									BigDecimal othertaxesInBooking = new BigDecimal(0);
									BigDecimal discountInBooking = new BigDecimal(0);
									BigDecimal markupInBooking = new BigDecimal(0);
									BigDecimal supplierDiscountInBooking = new BigDecimal(0);
									BigDecimal netFare = new BigDecimal(0);
									BigDecimal grossFare = new BigDecimal(0);
									BigDecimal supplierCharge = new BigDecimal(0);
									BigDecimal baggagePriceInBooking = new BigDecimal(0);
									BigDecimal mealPriceInBooking = new BigDecimal(0);
									BigDecimal totExtraChargeInBooking = new BigDecimal(0);
									totExtraChargeInBooking = extraBaggagePrice.add(extraMealPrice);

									BigDecimal otherTaxesinculdetdscharges = new BigDecimal("0");
									otherTaxesinculdetdscharges = flightOrderRow.getSupplierTds() !=null ?ArithmeticUti.divideTo2Decimal(flightOrderRow.getSupplierTds(), new BigDecimal(flightOrderRow.getFlightOrderCustomerPriceBreakups().size()).setScale(2)):new BigDecimal(0);
									if (flightOrderCustomerPriceBreakup.getTax() != null) {
										BigDecimal tax = flightOrderCustomerPriceBreakup.getTax()
												.multiply(flightOrderRow.getApiToBaseExchangeRate());
										taxesInBooking = tax.multiply(flightOrderRow.getBaseToBookingExchangeRate());
									} else
										taxesInBooking = new BigDecimal("0");

									if (flightOrderCustomerPriceBreakup.getSupplierDiscount() != null) {
										BigDecimal supplierDiscount = flightOrderCustomerPriceBreakup.getSupplierDiscount()
												.multiply(flightOrderRow.getApiToBaseExchangeRate());
										discountInBooking = supplierDiscount
												.multiply(flightOrderRow.getBaseToBookingExchangeRate());
									} else
										discountInBooking = new BigDecimal("0");

									if (flightOrderCustomerPriceBreakup.getBaseFare() != null) {
										BigDecimal basePrice = flightOrderCustomerPriceBreakup.getBaseFare()
												.multiply(flightOrderRow.getApiToBaseExchangeRate());
										basePriceInBooking = basePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
									} else
										basePriceInBooking = new BigDecimal("0");

									if (flightOrderCustomerPriceBreakup.getSupplierDiscount() != null) {
										BigDecimal discount = flightOrderCustomerPriceBreakup.getSupplierDiscount()
												.multiply(flightOrderRow.getApiToBaseExchangeRate());
										supplierDiscountInBooking = discount
												.multiply(flightOrderRow.getBaseToBookingExchangeRate());
									} else
										supplierDiscountInBooking = new BigDecimal("0");

									if (flightOrderCustomerPriceBreakup.getMarkup() != null)
										markupInBooking = new BigDecimal(flightOrderCustomerPriceBreakup.getMarkup())
										.multiply(flightOrderRow.getBaseToBookingExchangeRate());
									else
										markupInBooking = new BigDecimal("0");

									taxesInBooking = taxesInBooking.subtract(otherTaxesinculdetdscharges);
									supplierCharge = basePriceInBooking.add(taxesInBooking).subtract(supplierDiscountInBooking);
									if (sessionCompany.getCompanyRole().isSuperUser())
										commonDsrTravelReportData
										.setSupplierCharge(supplierCharge.setScale(2, BigDecimal.ROUND_UP).toString());
									else
										commonDsrTravelReportData.setSupplierCharge(new BigDecimal(0).toString());
									commonDsrTravelReportData.setSupplierCode(flightOrderRow.getOrderId());
									commonDsrTravelReportData.setSupplierName(flightOrderRow.getProviderAPI());
									commonDsrTravelReportData.setProductType("Air");
									
									boolean isInternational=false;
									if(flightOrderRow.getFlightOrderTripDetails()!=null && flightOrderRow.getFlightOrderTripDetails().size()>0){
										for(FlightOrderTripDetail flightOrderTripDetail:flightOrderRow.getFlightOrderTripDetails()){
											boolean  isInternationaltest=CommonDsrDao.isDomesticOrInternational(flightOrderTripDetail.getDestinationCode(), flightOrderTripDetail.getOriginCode());
											if(isInternationaltest){
												isInternational=isInternationaltest;
												break;
											}
										}
									}
									String itineraryType=isInternational?"International":"Domestic";
									commonDsrTravelReportData.setItineraryType(itineraryType);
									commonDsrTravelReportData.setProductName(flightOrderRow.getAirline());
									StringBuilder descode = new StringBuilder();
									StringBuilder srcCode = new StringBuilder();
									StringBuilder flightnumber = new StringBuilder();
									for (int i = 0; i < flightOrderRow.getFlightOrderTripDetails().size(); i++) {
										FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
										if (i == flightOrderRow.getFlightOrderTripDetails().size() - 1) {
											srcCode.append(trips.getOriginCode()+"-"+trips.getDestinationCode());
											flightnumber.append(trips.getFlightNumber());
										} else {
											srcCode.append(trips.getOriginCode()+"-"+trips.getDestinationCode()+"/");
											flightnumber.append(trips.getFlightNumber()+"/");
										}
										commonDsrTravelReportData.setProductCode(trips.getOperatedByCode() != null
												? trips.getOperatedByCode()
														: "NA" + "-" + trips.getFlightNumber() != null ? trips.getFlightNumber() : "NA");
										commonDsrTravelReportData.setCabinClass(trips.getClassOfService()!=null && !trips.getClassOfService().trim().equalsIgnoreCase("")?trips.getClassOfService():"-");
										commonDsrTravelReportData.setBookingClass(trips.getFareClass()!=null && !trips.getFareClass().trim().equalsIgnoreCase("")?trips.getFareClass():"-");
										commonDsrTravelReportData.setFareBasis(trips.getFareBasisCode());
										commonDsrTravelReportData.setFareType(trips.getCraft());

									}
									commonDsrTravelReportData.setFlightNumber(flightnumber.toString());
									commonDsrTravelReportData.setDescription(srcCode.toString());
									commonDsrTravelReportData.setGDSPNR(flightOrderRow.getPnr());
									commonDsrTravelReportData.setTicketNumorFinalBooking(flightOrderCustomer.getEticketnumber());
									commonDsrTravelReportData.setPaxType(flightOrderCustomer.getPassengerTypeCode());
									commonDsrTravelReportData.setTraveller(flightOrderCustomer.getTitle() + " "
											+ flightOrderCustomer.getFirstName() + " " + flightOrderCustomer.getLastName());
									if(flightOrderRow.getCreatedAt()!=null )
										commonDsrTravelReportData.setBookingTime(DateConversion.convertTimestampToStringWithTime(flightOrderRow.getCreatedAt()));
									else
										commonDsrTravelReportData.setBookingTime("-");
									commonDsrTravelReportData.setTripStartsDate(
											DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
									commonDsrTravelReportData.setTripEndDate(
											DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getArrivalDate()));
									commonDsrTravelReportData.setTravelerEmail(flightOrderRow.getCustomer().getEmail()!=null && !flightOrderRow.getCustomer().getEmail().trim().equalsIgnoreCase("")?flightOrderRow.getCustomer().getEmail():"-");
									commonDsrTravelReportData.setTravelerPhone(flightOrderRow.getCustomer().getPhone()!=null && !flightOrderRow.getCustomer().getPhone().trim().equalsIgnoreCase("")?flightOrderRow.getCustomer().getPhone():"-");
									commonDsrTravelReportData.setSegmentNumber("-");
									String tripType = null;
									if (flightOrderRow.getTripType().equals("O"))
										tripType = "One Way";
									if (flightOrderRow.getTripType().equals("R"))
										tripType = "Round Trip";
									if (flightOrderRow.getTripType().equals("SR"))
										tripType = "Special Round Trip";
									if (flightOrderRow.getTripType().equals("M"))
										tripType = "Multi Trip";

									commonDsrTravelReportData.setJourneyType(tripType);
									BigDecimal transactionFee = new BigDecimal("0");
									BigDecimal BaseServiceTax = new BigDecimal("0");
									BigDecimal otherTaxesWithOutMarkup = new BigDecimal("0");
										YQTax = flightOrderCustomerPriceBreakup.getYQTax() != null? flightOrderCustomerPriceBreakup.getYQTax() : new BigDecimal("0");
										YRTax = flightOrderCustomerPriceBreakup.getYRTax() != null ? flightOrderCustomerPriceBreakup.getYRTax() : new BigDecimal("0");
										WOTax = flightOrderCustomerPriceBreakup.getWOTax() != null ? flightOrderCustomerPriceBreakup.getWOTax() : new BigDecimal("0");
										PSFTax = flightOrderCustomerPriceBreakup.getPSFTax() != null ? flightOrderCustomerPriceBreakup.getPSFTax() : new BigDecimal("0");
										UDFTax = flightOrderCustomerPriceBreakup.getUDFTax() != null ? flightOrderCustomerPriceBreakup.getUDFTax() : new BigDecimal("0");
										JNTax = flightOrderCustomerPriceBreakup.getJNTax() != null ? flightOrderCustomerPriceBreakup.getJNTax() : new BigDecimal("0");
										INTax = flightOrderCustomerPriceBreakup.getINTax() != null ? flightOrderCustomerPriceBreakup.getINTax() : new BigDecimal("0");
										K3Tax = flightOrderCustomerPriceBreakup.getK3Tax() != null ? flightOrderCustomerPriceBreakup.getK3Tax() : new BigDecimal("0");
										F6Tax = flightOrderCustomerPriceBreakup.getF6Tax() != null ? flightOrderCustomerPriceBreakup.getF6Tax() : new BigDecimal("0");
										G1Tax = flightOrderCustomerPriceBreakup.getG1Tax() != null ? flightOrderCustomerPriceBreakup.getG1Tax() : new BigDecimal("0");
										USTax = flightOrderCustomerPriceBreakup.getUSTax() != null ? flightOrderCustomerPriceBreakup.getUSTax() : new BigDecimal("0");
										XATax = flightOrderCustomerPriceBreakup.getXATax() != null ? flightOrderCustomerPriceBreakup.getXATax() : new BigDecimal("0");
										XFTax = flightOrderCustomerPriceBreakup.getXFTax() != null ? flightOrderCustomerPriceBreakup.getXFTax() : new BigDecimal("0");
										XYTax = flightOrderCustomerPriceBreakup.getXYTax() != null ? flightOrderCustomerPriceBreakup.getXYTax() : new BigDecimal("0");
										YCTax = flightOrderCustomerPriceBreakup.getYCTax() != null ? flightOrderCustomerPriceBreakup.getYCTax() : new BigDecimal("0");
										ZRTax = flightOrderCustomerPriceBreakup.getZRTax() != null ? flightOrderCustomerPriceBreakup.getZRTax() : new BigDecimal("0");
										BigDecimal totalotherTaxes = INTax.add(YQTax).add(YRTax).add(PSFTax).add(UDFTax).add(JNTax).add(K3Tax).add(WOTax).add(F6Tax).add(G1Tax).add(USTax).add(XATax).add(XFTax).add(XYTax)
												.add(YCTax).add(ZRTax);
										otherTaxesWithOutMarkup = taxesInBooking.subtract(totalotherTaxes);
										otherTaxesWithOutMarkup = otherTaxesWithOutMarkup.add(otherTaxesinculdetdscharges);
										grossFare = basePriceInBooking.add(taxesInBooking).add(totExtraChargeInBooking).add(markupInBooking).add(otherTaxesinculdetdscharges);
										commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
										transactionFee = transactionFee.add(flightOrderCustomerPriceBreakup.getTransactionFee() != null ? flightOrderCustomerPriceBreakup.getTransactionFee() : new BigDecimal("0"));
										BaseServiceTax = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax() != null && flightOrderRow.getFlightOrderRowServiceTax().getBasicTax() != null ? flightOrderRow.getFlightOrderRowServiceTax().getBasicTax() : new BigDecimal(0));
										BigDecimal swachBharatCess = new BigDecimal("0");
										swachBharatCess = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax() != null && flightOrderRow.getFlightOrderRowServiceTax().getSwatchBharathCess() != null ? flightOrderRow.getFlightOrderRowServiceTax().getSwatchBharathCess() : new BigDecimal(0));
										BigDecimal krishiKalyanCess = new BigDecimal("0");
										krishiKalyanCess = basePriceInBooking.add(YQTax).divide(new BigDecimal(100)).multiply(flightOrderRow.getFlightOrderRowServiceTax() != null && flightOrderRow.getFlightOrderRowServiceTax().getKrishiKalyanCess() != null ? flightOrderRow.getFlightOrderRowServiceTax().getKrishiKalyanCess() : new BigDecimal(0));
										BigDecimal totalCess = swachBharatCess.add(krishiKalyanCess);
										BigDecimal TotalServiceTax = new BigDecimal("0");
										TotalServiceTax = BaseServiceTax.add(totalCess.setScale(2, BigDecimal.ROUND_UP));// BaseServiceTax.add(swachBharatCess).add(krishiKalyanCess);//

										if (taxType != null && taxType.equalsIgnoreCase("GST"))
											netFare = grossFare.add(gstPerPassenger).add(managementFee).add(convenienceFee);
										else
											netFare = grossFare.add(TotalServiceTax).add(managementFee).add(convenienceFee);

										commonDsrTravelReportData .setTayyarahServiceCharges(managementFee.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData .setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP).toString());// .add(taxesInBooking)
										commonDsrTravelReportData.setYQTax(YQTax.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData .setYRTax(YRTax.add(markupInBooking).setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setPSFTax(PSFTax.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setUDFTax(UDFTax.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setJNTax(JNTax.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setINTax(INTax.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setK3Tax(K3Tax.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setOBTax("0");
										commonDsrTravelReportData.setOCTax("0");
										commonDsrTravelReportData.setCGST(CGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setSGSTorUGSTorIGST(SGSTorIGSTorUGSTPerPassenger.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setOtherTaxes(otherTaxesWithOutMarkup.add(WOTax).add(F6Tax).add(G1Tax).add(USTax).add(XATax).add(XFTax).add(XYTax).add(YCTax).add(ZRTax).setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setExtraCharge(totExtraChargeInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setSupplierAmendmentOrCancellationFee("0");
										commonDsrTravelReportData.setNetDiscount("0");
										commonDsrTravelReportData.setServiceTaxBase(BaseServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setSwachBharatCess(swachBharatCess.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setKrishiKalyanCess(krishiKalyanCess.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setServiceTax(TotalServiceTax.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setConvenienceCharge(convenienceFee.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setDiscount(discountInBooking.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setTaxType(taxType);
										commonDsrTravelReportData.setTravelStatus(commonDsrTravelReportData.getAmendmentType());
										commonDsrTravelReportData.setCorporateCurrency(company.getCurrencyCode());
										
										//ADDED BY BASHA FOR RM CONFIG ON 24-10-2017
										
										RmConfigTripDetailsModel rmConfigTripDetails =null;
										if(flightOrderCustomer.getRmConfigTripDetailsModel()!=null) 
											rmConfigTripDetails =dsrRmConfigHelperDao.getFlightPaxRmConfigTripDetails(flightOrderCustomer.getRmConfigTripDetailsModel(),flightOrderRow.getFlightOrderRowRmConfigStruct().getRmDynamicData());
										else
											rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(flightOrderRow.getOrderId(),company.getCompanyid());
										
										if(rmConfigTripDetails!=null)
										{
											if(sessionCompany.getCompanyRole().isSuperUser()){
												if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("F") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
													commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
												else 
													commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
										}
										else 
											commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
										
										buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
										}
										
										//ENDED BY BASHA FOR RM CONFIG ON 24-10-2017
										commonDsrTravelReportData.setCreditnoteIssued(flightOrderRow.isCreditNoteIssued());
										commonDsrTravelReports.add(commonDsrTravelReportData);
										CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("F",flightOrderRowObj, commonDsrTravelReportData);
										if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
											commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
											commonDsrTravelReportsNew.setBookingDate(commonDsrTravelReportsNew.getInvoicedate());
											commonDsrTravelReports.add(commonDsrTravelReportsNew);
										}
								}
							}
						}
						if(flightOrderRow.getAirline() != null) 
							filterAirlineList.add(flightOrderRow.getAirline());
					}
					sortDataByStringDate(commonDsrTravelReports);
					salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);
					salesReportCalSummary.setAirOrHotelSalesReportList(buildRequiredDataFromSummaryMap(buildAirSalesSummary(filterAirlineList, flightOrderRowList,taxType),"F"));
				}
				return salesReportCalSummary;
	}

	public SalesReportCalSummary buildCarReportData(CommonDsrPage commonDsrPage, List<CarOrderRow> carOrderRowList,
			SalesReportCalSummary salesReportCalSummary, String taxType) {
		List<CommonDsrTravelReportData> commonDsrTravelReports = new LinkedList<>();
		BigDecimal totalNetfare = new BigDecimal("0");
		BigDecimal avgNetFare = new BigDecimal("0");
		BigDecimal totalRefundAmount = new BigDecimal("0");
		BigDecimal netSale = new BigDecimal("0");
		int cancelledCount = 0;
		int confirmedCount = 0;
		int totalCount = 0;
		Company  sessionCompany=commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if (carOrderRowList != null && carOrderRowList.size() > 0) {
			for (CarOrderRow carOrderRow : carOrderRowList) {
				Object carOrderRowObj=carOrderRow;
				
				if(carOrderRow!=null){
					if(carOrderRow.getCarOrderCustomerList()!=null && carOrderRow.getCarOrderCustomerList().size()>0){
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(carOrderRow.getCompanyId()));
						for(CarOrderCustomer carOrderCustomer:carOrderRow.getCarOrderCustomerList()){
							int count=carOrderRow.getCarOrderCustomerList().size();
							BigDecimal convenienceFee = new BigDecimal("0");
							BigDecimal managementFee = new BigDecimal("0");
							BigDecimal taxes = new BigDecimal("0");
							BigDecimal serviceOrGstTax = new BigDecimal("0");
							BigDecimal driverAllowanceDay = new BigDecimal("0");
							BigDecimal driverAllowanceDayNight = new BigDecimal("0");
							BigDecimal tollOrPackagingCharges = new BigDecimal("0");
							BigDecimal extraKm = new BigDecimal("0");
							BigDecimal extraHour = new BigDecimal("0");
							convenienceFee = carOrderRow.getConvenienceFee() != null ? carOrderRow.getConvenienceFee(): new BigDecimal(0);
							managementFee = carOrderRow.getManagementFee() != null ? carOrderRow.getManagementFee(): new BigDecimal(0);
							driverAllowanceDay = carOrderRow.getDriverAllowanceDay() != null ? carOrderRow.getDriverAllowanceDay(): new BigDecimal(0);
							driverAllowanceDayNight = carOrderRow.getDriverAllowanceNight() != null ? carOrderRow.getDriverAllowanceNight() : new BigDecimal(0);
							tollOrPackagingCharges = carOrderRow.getTollOrParkingCharges() != null ? carOrderRow.getTollOrParkingCharges() : new BigDecimal(0);
							extraKm = new BigDecimal(carOrderRow.getExtraKM() != null ? carOrderRow.getExtraKM() : "0.00");
							extraHour = new BigDecimal(carOrderRow.getExtraHours() != null ? carOrderRow.getExtraHours() : "0.00");
											
						convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(count));
						managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(count));
						//taxes=	ArithmeticUti.divideTo2Decimal(taxes, new BigDecimal(count));
						//serviceOrGstTax=	ArithmeticUti.divideTo2Decimal(serviceOrGstTax, new BigDecimal(count));
						driverAllowanceDay=ArithmeticUti.divideTo2Decimal(driverAllowanceDay, new BigDecimal(count));
						driverAllowanceDayNight=ArithmeticUti.divideTo2Decimal(driverAllowanceDayNight, new BigDecimal(count));
						tollOrPackagingCharges=	ArithmeticUti.divideTo2Decimal(tollOrPackagingCharges, new BigDecimal(count));
						extraKm=ArithmeticUti.divideTo2Decimal(extraKm, new BigDecimal(count));
						extraHour=ArithmeticUti.divideTo2Decimal(extraHour, new BigDecimal(count));
						CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
						commonDsrTravelReportData.setSystemInvoiceId(carOrderRow.getOrderId());
						//commonDsrTravelReportData.setTraveller(carOrderRow.getEmpNmae());
						commonDsrTravelReportData.setTraveller(carOrderCustomer.getTitle()+" "+carOrderCustomer.getFirstName()+" "+carOrderCustomer.getLastName());
						commonDsrTravelReportData.setGDSPNR(carOrderRow.getConfirmationNumber());
						commonDsrTravelReportData.setAmendmentType(carOrderRow.getCarCompanyName());
						commonDsrTravelReportData.setDescription2(carOrderRow.getRemarks());
						if(carOrderRow.isCreditNoteIssued()) 
							commonDsrTravelReportData.setTravelStatus("Confirmed");
						else
							commonDsrTravelReportData.setTravelStatus(carOrderRow.getStatusAction());
						CarTravelRequestQuotation carTravelRequestQuotation = new CarTravelRequestDao()
								.getCarQuotationDetails(carOrderRow.getId());
						if (carTravelRequestQuotation != null)
							commonDsrTravelReportData.setDescription(
									carTravelRequestQuotation.getPickUp() + "-" + carTravelRequestQuotation.getDropOff());
						else
							commonDsrTravelReportData.setDescription("-");
						commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(carOrderRow.getTravelDate()));
						if (commonDsrTravelReportData.getTripStartsDate() == null)
							commonDsrTravelReportData.setTripStartsDate("-");
						
						
						if (taxType.equalsIgnoreCase("GST"))
							serviceOrGstTax = carOrderRow.getTotalGstTax();
						if (taxType.equalsIgnoreCase("Service"))
							serviceOrGstTax = carOrderRow.getServiceTax();
						
						serviceOrGstTax=ArithmeticUti.divideTo2Decimal(serviceOrGstTax, new BigDecimal(count));
						BigDecimal basePrice=new BigDecimal(0);
						BigDecimal markUp=new BigDecimal(0);
						BigDecimal otherTaxes=new BigDecimal(0);
						basePrice=ArithmeticUti.divideTo2Decimal(carOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
						markUp=ArithmeticUti.divideTo2Decimal(carOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
						otherTaxes=ArithmeticUti.divideTo2Decimal(carOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
						commonDsrTravelReportData.setBaseFare(basePrice.toString());
						taxes = taxes.add(otherTaxes).add(markUp).add(convenienceFee).add(managementFee);
						commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
						BigDecimal grossFare = otherTaxes.add(basePrice).add(markUp);
						BigDecimal netFare = grossFare.add(serviceOrGstTax).add(extraKm).add(extraHour).add(convenienceFee).add(managementFee).add(driverAllowanceDay).add(driverAllowanceDayNight).add(tollOrPackagingCharges);
						commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
						RmConfigTripDetailsModel rmConfigTripDetails =null;
						if(carOrderCustomer.getRmConfigTripDetailsModel()!=null)
							rmConfigTripDetails =dsrRmConfigHelperDao.getCarPaxRmConfigTripDetails(carOrderCustomer.getRmConfigTripDetailsModel(),carOrderRow.getCarOrderRowRmConfigStruct().getRmDynamicData());
						else
							rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(carOrderRow.getOrderId(),company.getCompanyid());

						if(rmConfigTripDetails!=null)
						{
							if(sessionCompany.getCompanyRole().isSuperUser()){
								if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("C") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							}
							else 
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
							commonDsrTravelReportData.setCreditnoteIssued(carOrderRow.isCreditNoteIssued());
							commonDsrTravelReports.add(commonDsrTravelReportData);
							CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("C",carOrderRowObj, commonDsrTravelReportData);
							if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
								commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
								commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
								commonDsrTravelReports.add(commonDsrTravelReportsNew);
								totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
							}
							totalCount++;
							if (carOrderRow.getStatusAction().equals("Confirmed")) {
								confirmedCount++;
							}
							if (carOrderRow.getStatusAction().equals("Cancelled")) {
								cancelledCount++;
							}
							totalNetfare = totalNetfare.add(netFare);
							
						}
						}
							
					}else{
						BigDecimal convenienceFee = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal taxes = new BigDecimal("0");
						BigDecimal serviceOrGstTax = new BigDecimal("0");
						BigDecimal driverAllowanceDay = new BigDecimal("0");
						BigDecimal driverAllowanceDayNight = new BigDecimal("0");
						BigDecimal tollOrPackagingCharges = new BigDecimal("0");
						BigDecimal extraKm = new BigDecimal("0");
						BigDecimal extraHour = new BigDecimal("0");
						convenienceFee = carOrderRow.getConvenienceFee() != null ? carOrderRow.getConvenienceFee(): new BigDecimal(0);
						managementFee = carOrderRow.getManagementFee() != null ? carOrderRow.getManagementFee(): new BigDecimal(0);
						driverAllowanceDay = carOrderRow.getDriverAllowanceDay() != null ? carOrderRow.getDriverAllowanceDay(): new BigDecimal(0);
						driverAllowanceDayNight = carOrderRow.getDriverAllowanceNight() != null ? carOrderRow.getDriverAllowanceNight() : new BigDecimal(0);
						tollOrPackagingCharges = carOrderRow.getTollOrParkingCharges() != null ? carOrderRow.getTollOrParkingCharges() : new BigDecimal(0);
										extraKm = new BigDecimal(carOrderRow.getExtraKM() != null ? carOrderRow.getExtraKM() : "0.00");
										extraHour = new BigDecimal(carOrderRow.getExtraHours() != null ? carOrderRow.getExtraHours() : "0.00");
										CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
										commonDsrTravelReportData.setSystemInvoiceId(carOrderRow.getOrderId());
										commonDsrTravelReportData.setTraveller(carOrderRow.getEmpNmae());
										commonDsrTravelReportData.setGDSPNR(carOrderRow.getConfirmationNumber());
										commonDsrTravelReportData.setAmendmentType(carOrderRow.getCarCompanyName());
										commonDsrTravelReportData.setDescription2(carOrderRow.getRemarks());
										if(carOrderRow.isCreditNoteIssued()) 
											commonDsrTravelReportData.setTravelStatus("Confirmed");
										else
											commonDsrTravelReportData.setTravelStatus(carOrderRow.getStatusAction());
										CarTravelRequestQuotation carTravelRequestQuotation = new CarTravelRequestDao()
												.getCarQuotationDetails(carOrderRow.getId());
										if (carTravelRequestQuotation != null)
											commonDsrTravelReportData.setDescription(
													carTravelRequestQuotation.getPickUp() + "-" + carTravelRequestQuotation.getDropOff());
										else
											commonDsrTravelReportData.setDescription("-");
										commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(carOrderRow.getTravelDate()));
										if (commonDsrTravelReportData.getTripStartsDate() == null)
											commonDsrTravelReportData.setTripStartsDate("-");
										if (taxType.equalsIgnoreCase("GST"))
											serviceOrGstTax = carOrderRow.getTotalGstTax();
										if (taxType.equalsIgnoreCase("Service"))
											serviceOrGstTax = carOrderRow.getServiceTax();
										commonDsrTravelReportData.setBaseFare(carOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP).toString());
										taxes = taxes.add(carOrderRow.getOtherTaxes()).add(carOrderRow.getMarkUp()).add(convenienceFee).add(managementFee);
										commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
										BigDecimal grossFare = carOrderRow.getOtherTaxes().add(carOrderRow.getBasePrice()).add(carOrderRow.getMarkUp());
										BigDecimal netFare = grossFare.add(serviceOrGstTax).add(extraKm).add(extraHour).add(convenienceFee)
												.add(managementFee).add(driverAllowanceDay).add(driverAllowanceDayNight)
												.add(tollOrPackagingCharges);
										commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
										commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
										
										RmConfigTripDetailsModel rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(carOrderRow.getOrderId(), Integer.parseInt(carOrderRow.getCompanyId()));
											if (sessionCompany.getCompanyRole().isSuperUser()) {
												if (commonDsrPage.getCommonDsrFilters().getTravelType().equals("C")
														&& commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
													commonDsrTravelReportData
													.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
												else
													commonDsrTravelReportData
													.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
											} else
												commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
										 
										buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
										commonDsrTravelReportData.setCreditnoteIssued(carOrderRow.isCreditNoteIssued());
										commonDsrTravelReports.add(commonDsrTravelReportData);
										CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("C",carOrderRowObj, commonDsrTravelReportData);
										if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
											commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
											commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
											commonDsrTravelReports.add(commonDsrTravelReportsNew);
											totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
										}
										totalCount++;
										if (carOrderRow.getStatusAction().equals("Confirmed")) {
											confirmedCount++;
										}
										if (carOrderRow.getStatusAction().equals("Cancelled")) {
											cancelledCount++;
										}
										totalNetfare = totalNetfare.add(netFare);
										

					}
					if (totalCount > 0) {
						avgNetFare = ArithmeticUti.divideTo2Decimal(totalNetfare, new BigDecimal(totalCount));
					}
					netSale=totalNetfare.add(totalRefundAmount);
						
					}
				}
					
			}
		//sortDataByStringDate(commonDsrTravelReports);
		return buildCommonSalesSummary(totalNetfare, avgNetFare, totalRefundAmount, netSale, cancelledCount,
				confirmedCount, commonDsrTravelReports, salesReportCalSummary);
	}

	public SalesReportCalSummary buildBusReportData(CommonDsrPage commonDsrPage, List<BusOrderRow> busOrderRowList,
			SalesReportCalSummary salesReportCalSummary, String taxType) {
		List<CommonDsrTravelReportData> commonDsrTravelReports = new LinkedList<>();
		BigDecimal totalNetfare = new BigDecimal("0");
		BigDecimal avgNetFare = new BigDecimal("0");
		BigDecimal totalRefundAmount = new BigDecimal("0");
		BigDecimal netSale = new BigDecimal("0");
		int cancelledCount = 0;
		int confirmedCount = 0;
		int totalCount = 0;
		Company  sessionCompany=commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if (busOrderRowList != null && busOrderRowList.size() > 0) {
			for (BusOrderRow busOrderRow : busOrderRowList) {
				Object busOrderRowObj=busOrderRow;
				
				if(busOrderRow!=null){
					if(busOrderRow.getBusOrderCustomerDetails()!=null && busOrderRow.getBusOrderCustomerDetails().size()>0){
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(busOrderRow.getCompanyId()));
						for(BusOrderCustomerDetail busOrderCustomer:busOrderRow.getBusOrderCustomerDetails()){
							int count=busOrderRow.getBusOrderCustomerDetails().size();
							BigDecimal convenienceFee = new BigDecimal("0");
							BigDecimal baseFare = new BigDecimal("0");
							BigDecimal otherTaxes = new BigDecimal("0");
							BigDecimal markup = new BigDecimal("0");
							BigDecimal managementFee = new BigDecimal("0");
							BigDecimal taxes = new BigDecimal("0");
							BigDecimal serviceOrGstTax = new BigDecimal("0");
							convenienceFee = busOrderRow.getConvenienceFee() != null ? busOrderRow.getConvenienceFee(): new BigDecimal(0);
							managementFee = busOrderRow.getManagementFee() != null ? busOrderRow.getManagementFee(): new BigDecimal(0);
							convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee, new BigDecimal(count));
							managementFee=ArithmeticUti.divideTo2Decimal(managementFee, new BigDecimal(count));
							CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
							commonDsrTravelReportData.setSystemInvoiceId(busOrderRow.getOrderId());
							commonDsrTravelReportData.setTraveller(busOrderCustomer.getTitle()+" "+busOrderCustomer.getFirstName()+" "+busOrderCustomer.getLastName());
							commonDsrTravelReportData.setGDSPNR(busOrderRow.getConfirmationNumber()!=null && !busOrderRow.getConfirmationNumber().trim().equalsIgnoreCase("")?busOrderRow.getConfirmationNumber():"-");
							if(busOrderRow.isCreditNoteIssued()) 
								commonDsrTravelReportData.setTravelStatus("Confirmed");
							else
								commonDsrTravelReportData.setTravelStatus(busOrderRow.getStatusAction());
							commonDsrTravelReportData.setDescription(busOrderRow.getPickUp() + "-" + busOrderRow.getDropOff());
							commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(busOrderRow.getTravelDate()));
							if (commonDsrTravelReportData.getTripStartsDate() == null)
								commonDsrTravelReportData.setTripStartsDate("-");
							baseFare=ArithmeticUti.divideTo2Decimal(busOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							otherTaxes=ArithmeticUti.divideTo2Decimal(busOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							markup=ArithmeticUti.divideTo2Decimal(busOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							commonDsrTravelReportData.setBaseFare(baseFare.toString());
							if (taxType.equalsIgnoreCase("GST"))
								serviceOrGstTax = ArithmeticUti.divideTo2Decimal(busOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							if (taxType.equalsIgnoreCase("Service"))
								serviceOrGstTax = ArithmeticUti.divideTo2Decimal(busOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							taxes = taxes.add(otherTaxes).add(markup).add(convenienceFee).add(managementFee);
							commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
							BigDecimal grossFare = otherTaxes.add(baseFare).add(markup);
							BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
							commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
							
							RmConfigTripDetailsModel rmConfigTripDetails =null;
							if(busOrderCustomer.getRmConfigTripDetailsModel()!=null) 
								rmConfigTripDetails =dsrRmConfigHelperDao.getBusPaxRmConfigTripDetails(busOrderCustomer.getRmConfigTripDetailsModel(),busOrderRow.getBusOrderRowRmConfigStruct().getRmDynamicData());
							else
								rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(busOrderRow.getOrderId(),company.getCompanyid());
							
							if(rmConfigTripDetails!=null)
							{
								if(sessionCompany.getCompanyRole().isSuperUser()){
									if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("B") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
										commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
									else 
										commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
								}
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
								
							}
							buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
							
							
							commonDsrTravelReportData.setCreditnoteIssued(busOrderRow.isCreditNoteIssued());
							commonDsrTravelReports.add(commonDsrTravelReportData);
							CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("B",busOrderRowObj, commonDsrTravelReportData);
							if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
								commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
								commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
								commonDsrTravelReports.add(commonDsrTravelReportsNew);
								totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
							}
							totalCount++;
							if (busOrderRow.getStatusAction().equals("Confirmed")) {
								confirmedCount++;
							}
							if (busOrderRow.getStatusAction().equals("Cancelled")) {
								cancelledCount++;
							}
							totalNetfare = totalNetfare.add(netFare);
						}
					}
					else{
						
						BigDecimal convenienceFee = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal taxes = new BigDecimal("0");
						BigDecimal serviceOrGstTax = new BigDecimal("0");
						convenienceFee = busOrderRow.getConvenienceFee() != null ? busOrderRow.getConvenienceFee()
								: new BigDecimal(0);
						managementFee = busOrderRow.getManagementFee() != null ? busOrderRow.getManagementFee()
								: new BigDecimal(0);
						CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
						commonDsrTravelReportData.setSystemInvoiceId(busOrderRow.getOrderId());
						commonDsrTravelReportData.setTraveller(busOrderRow.getEmpName());
						commonDsrTravelReportData.setGDSPNR(busOrderRow.getConfirmationNumber()!=null && !busOrderRow.getConfirmationNumber().trim().equalsIgnoreCase("")?busOrderRow.getConfirmationNumber():"-");
						if(busOrderRow.isCreditNoteIssued()) 
							commonDsrTravelReportData.setTravelStatus("Confirmed");
						else
							commonDsrTravelReportData.setTravelStatus(busOrderRow.getStatusAction());
						commonDsrTravelReportData.setDescription(busOrderRow.getPickUp() + "-" + busOrderRow.getDropOff());
						commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(busOrderRow.getTravelDate()));

						if (commonDsrTravelReportData.getTripStartsDate() == null)
							commonDsrTravelReportData.setTripStartsDate("-");
						commonDsrTravelReportData.setBaseFare(busOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP).toString());
						if (taxType.equalsIgnoreCase("GST"))
							serviceOrGstTax = busOrderRow.getTotalGstTax();
						if (taxType.equalsIgnoreCase("Service"))
							serviceOrGstTax = busOrderRow.getServiceTax();
						taxes = taxes.add(busOrderRow.getOtherTaxes()).add(busOrderRow.getMarkUp()).add(convenienceFee)
								.add(managementFee);
						commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
						BigDecimal grossFare = busOrderRow.getOtherTaxes().add(busOrderRow.getBasePrice())
								.add(busOrderRow.getMarkUp());
						BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
						commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
						RmConfigTripDetailsModel rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(busOrderRow.getOrderId(), Integer.parseInt(busOrderRow.getCompanyId()));
					 
							if (sessionCompany.getCompanyRole().isSuperUser()) {
								if (commonDsrPage.getCommonDsrFilters().getTravelType().equals("B")
										&& commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData
									.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else
									commonDsrTravelReportData
									.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							} else
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
						 
						buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
						commonDsrTravelReportData.setCreditnoteIssued(busOrderRow.isCreditNoteIssued());
						commonDsrTravelReports.add(commonDsrTravelReportData);
						CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("B",busOrderRowObj, commonDsrTravelReportData);
						if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
							commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
							commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
							commonDsrTravelReports.add(commonDsrTravelReportsNew);
							totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
						}
						totalCount++;
						if (busOrderRow.getStatusAction().equals("Confirmed")) {
							confirmedCount++;
						}
						if (busOrderRow.getStatusAction().equals("Cancelled")) {
							cancelledCount++;
						}
						totalNetfare = totalNetfare.add(netFare);


						
						
						
					}
				}
				
				
				
				
				
			}
			if (totalCount > 0) {
				avgNetFare = ArithmeticUti.divideTo2Decimal(totalNetfare, new BigDecimal(totalCount));
			}
			netSale=totalNetfare.add(totalRefundAmount);

		}
		return buildCommonSalesSummary(totalNetfare, avgNetFare, totalRefundAmount, netSale, cancelledCount,
				confirmedCount, commonDsrTravelReports, salesReportCalSummary);
	}

	public SalesReportCalSummary buildTrainReportData(CommonDsrPage commonDsrPage,
			List<TrainOrderRow> trainOrderRowList, SalesReportCalSummary salesReportCalSummary, String taxType) {
		List<CommonDsrTravelReportData> commonDsrTravelReports = new LinkedList<>();
		Company  sessionCompany=commonDsrPage.getCommonDsrFilters().getLoginCompany();
		BigDecimal totalNetfare = new BigDecimal("0");
		BigDecimal avgNetFare = new BigDecimal("0");
		BigDecimal totalRefundAmount = new BigDecimal("0");
		BigDecimal netSale = new BigDecimal("0");
		int cancelledCount = 0;
		int confirmedCount = 0;
		int totalCount = 0;
		if (trainOrderRowList != null && trainOrderRowList.size() > 0) {
			for (TrainOrderRow trainOrderRow : trainOrderRowList) {
				Object trainOrderRowObj=trainOrderRow;
				
				if(trainOrderRow!=null){
					if(trainOrderRow.getTrainOrderCustomerList()!=null && trainOrderRow.getTrainOrderCustomerList().size()>0){
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(trainOrderRow.getCompanyId()));
						for(TrainOrderCustomer trainOrderCustomer:trainOrderRow.getTrainOrderCustomerList()){
							int count=trainOrderRow.getTrainOrderCustomerList().size();
							BigDecimal convenienceFee = new BigDecimal("0");
							BigDecimal managementFee = new BigDecimal("0");
							BigDecimal baseFare = new BigDecimal("0");
							BigDecimal otherTaxes = new BigDecimal("0");
							BigDecimal markup = new BigDecimal("0");
							BigDecimal taxes = new BigDecimal("0");
							BigDecimal serviceOrGstTax = new BigDecimal("0");
							convenienceFee = trainOrderRow.getConvenienceFee() != null ? trainOrderRow.getConvenienceFee(): new BigDecimal(0);
							if (!trainOrderRow.getTicketType().equals("")){ 
								if (trainOrderRow.getTicketType().equalsIgnoreCase("tatkal"))
									managementFee = trainOrderRow.getManagementFeeTatkal() != null || !trainOrderRow.getManagementFeeTatkal().equals("")? trainOrderRow.getManagementFeeTatkal() : new BigDecimal(0);
									if (trainOrderRow.getTicketType().equalsIgnoreCase("normal"))
										managementFee = trainOrderRow.getManagementFee() != null ? trainOrderRow.getManagementFee(): new BigDecimal(0);
							}else
								managementFee = new BigDecimal(0);
									
									convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
									managementFee=ArithmeticUti.divideTo2Decimal(managementFee.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
									CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
									commonDsrTravelReportData.setSystemInvoiceId(trainOrderRow.getOrderId());
									commonDsrTravelReportData.setTraveller(trainOrderCustomer.getTitle()+" "+trainOrderCustomer.getFirstName()+" "+trainOrderCustomer.getLastName());
									commonDsrTravelReportData.setGDSPNR(trainOrderRow.getConfirmationNumber()!=null && !trainOrderRow.getConfirmationNumber().trim().equalsIgnoreCase("")?trainOrderRow.getConfirmationNumber():"-");
									TrainTravelRequestQuotation trainTravelRequestQuotation = new TrainTravelRequestDao()
											.getTrainQuotationDetails(trainOrderRow.getId());
									if (trainTravelRequestQuotation != null)
										commonDsrTravelReportData.setDescription(trainTravelRequestQuotation.getFromlocation() + "-"
												+ trainTravelRequestQuotation.getTolocation());
									else
										commonDsrTravelReportData.setDescription("-");
									if(trainOrderRow.isCreditNoteIssued()) 
										commonDsrTravelReportData.setTravelStatus("Confirmed");
									else
										commonDsrTravelReportData.setTravelStatus(trainOrderRow.getStatusAction());
									commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(trainOrderRow.getTravelDate()));
									if (commonDsrTravelReportData.getTripStartsDate() == null)
										commonDsrTravelReportData.setTripStartsDate("-");
									if (taxType.equalsIgnoreCase("GST"))
										serviceOrGstTax = ArithmeticUti.divideTo2Decimal(trainOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
									if (taxType.equalsIgnoreCase("Service"))
										serviceOrGstTax = ArithmeticUti.divideTo2Decimal(trainOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
									baseFare=ArithmeticUti.divideTo2Decimal(trainOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
									commonDsrTravelReportData.setBaseFare(baseFare.toString());
									otherTaxes=ArithmeticUti.divideTo2Decimal(trainOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
									markup=ArithmeticUti.divideTo2Decimal(trainOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
									taxes = taxes.add(otherTaxes).add(markup).add(convenienceFee).add(managementFee);
									commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
									BigDecimal grossFare = otherTaxes.add(baseFare).add(markup);
									BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
									commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
									commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
									
									RmConfigTripDetailsModel rmConfigTripDetails =null;
									if(trainOrderCustomer.getRmConfigTripDetailsModel()!=null) 
										rmConfigTripDetails =dsrRmConfigHelperDao.getTrainPaxRmConfigTripDetails(trainOrderCustomer.getRmConfigTripDetailsModel(),trainOrderRow.getTrainOrderRowRmConfigStruct().getRmDynamicData());
									else
										rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(trainOrderRow.getOrderId(),company.getCompanyid());
									if(rmConfigTripDetails!=null)
									{
										if(sessionCompany.getCompanyRole().isSuperUser()){
											if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("T") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
												commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
											else 
												commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
										}
										else 
											commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
									}
									
									
									
									buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
									commonDsrTravelReportData.setCreditnoteIssued(trainOrderRow.isCreditNoteIssued());
									commonDsrTravelReports.add(commonDsrTravelReportData);
									CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("T",trainOrderRowObj, commonDsrTravelReportData);
									if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
										commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
										commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
										commonDsrTravelReports.add(commonDsrTravelReportsNew);
										totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
									}
									totalCount++;
									if (trainOrderRow.getStatusAction().equals("Confirmed")) {
										confirmedCount++;
									}
									if (trainOrderRow.getStatusAction().equals("Cancelled")) {
										cancelledCount++;
									}
									totalNetfare = totalNetfare.add(netFare);


							
						}
						
						
					}else{
						
						
						
						BigDecimal convenienceFee = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal taxes = new BigDecimal("0");
						BigDecimal serviceOrGstTax = new BigDecimal("0");
						convenienceFee = trainOrderRow.getConvenienceFee() != null ? trainOrderRow.getConvenienceFee(): new BigDecimal(0);
						if (!trainOrderRow.getTicketType().equals("")) {
							if (trainOrderRow.getTicketType().equalsIgnoreCase("tatkal"))
								managementFee = trainOrderRow.getManagementFeeTatkal() != null
								|| !trainOrderRow.getManagementFeeTatkal().equals("")
								? trainOrderRow.getManagementFeeTatkal() : new BigDecimal(0);
								if (trainOrderRow.getTicketType().equalsIgnoreCase("normal"))
									managementFee = trainOrderRow.getManagementFee() != null ? trainOrderRow.getManagementFee()
											: new BigDecimal(0);
						} else
							managementFee = new BigDecimal(0);

						CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
						commonDsrTravelReportData.setSystemInvoiceId(trainOrderRow.getOrderId());
						commonDsrTravelReportData.setTraveller(trainOrderRow.getEmpNmae());
						commonDsrTravelReportData.setGDSPNR(trainOrderRow.getConfirmationNumber()!=null && !trainOrderRow.getConfirmationNumber().trim().equalsIgnoreCase("")?trainOrderRow.getConfirmationNumber():"-");
						TrainTravelRequestQuotation trainTravelRequestQuotation = new TrainTravelRequestDao()
								.getTrainQuotationDetails(trainOrderRow.getId());
						if (trainTravelRequestQuotation != null)
							commonDsrTravelReportData.setDescription(trainTravelRequestQuotation.getFromlocation() + "-"
									+ trainTravelRequestQuotation.getTolocation());
						else
							commonDsrTravelReportData.setDescription("-");
						if(trainOrderRow.isCreditNoteIssued()) 
							commonDsrTravelReportData.setTravelStatus("Confirmed");
						else
							commonDsrTravelReportData.setTravelStatus(trainOrderRow.getStatusAction());
						commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(trainOrderRow.getTravelDate()));
						if (commonDsrTravelReportData.getTripStartsDate() == null)
							commonDsrTravelReportData.setTripStartsDate("-");
						if (taxType.equalsIgnoreCase("GST"))
							serviceOrGstTax = trainOrderRow.getTotalGstTax();
						if (taxType.equalsIgnoreCase("Service"))
							serviceOrGstTax = trainOrderRow.getServiceTax();
						commonDsrTravelReportData
						.setBaseFare(trainOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP).toString());
						taxes = taxes.add(trainOrderRow.getOtherTaxes()).add(trainOrderRow.getMarkUp()).add(convenienceFee)
								.add(managementFee);
						commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
						BigDecimal grossFare = trainOrderRow.getOtherTaxes().add(trainOrderRow.getBasePrice())
								.add(trainOrderRow.getMarkUp());
						BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
						commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
						RmConfigTripDetailsModel rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(trainOrderRow.getOrderId(), Integer.parseInt(trainOrderRow.getCompanyId()));
				 
							if (sessionCompany.getCompanyRole().isSuperUser()) {
								if (commonDsrPage.getCommonDsrFilters().getTravelType().equals("T")
										&& commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData
									.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else
									commonDsrTravelReportData
									.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							} else
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
					 
						buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
						commonDsrTravelReportData.setCreditnoteIssued(trainOrderRow.isCreditNoteIssued());
						commonDsrTravelReports.add(commonDsrTravelReportData);
						CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("T",trainOrderRowObj, commonDsrTravelReportData);
						if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
							commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
							commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
							commonDsrTravelReports.add(commonDsrTravelReportsNew);
							totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
						}
						totalCount++;
						if (trainOrderRow.getStatusAction().equals("Confirmed")) {
							confirmedCount++;
						}
						if (trainOrderRow.getStatusAction().equals("Cancelled")) {
							cancelledCount++;
						}
						totalNetfare = totalNetfare.add(netFare);


					}
					}
					if (totalCount > 0) {
						avgNetFare = ArithmeticUti.divideTo2Decimal(totalNetfare, new BigDecimal(totalCount));
					}
					netSale=totalNetfare.add(totalRefundAmount);
					
					
				}
				
		}
		return buildCommonSalesSummary(totalNetfare, avgNetFare, totalRefundAmount, netSale, cancelledCount,
				confirmedCount, commonDsrTravelReports, salesReportCalSummary);
	}

	public SalesReportCalSummary buildVisaReportData(CommonDsrPage commonDsrPage, List<VisaOrderRow> visaOrderRowList,
			SalesReportCalSummary salesReportCalSummary, String taxType) {
		List<CommonDsrTravelReportData> commonDsrTravelReports = new LinkedList<>();
		Company  sessionCompany=commonDsrPage.getCommonDsrFilters().getLoginCompany();
		BigDecimal totalNetfare = new BigDecimal("0");
		BigDecimal avgNetFare = new BigDecimal("0");
		BigDecimal totalRefundAmount = new BigDecimal("0");
		BigDecimal netSale = new BigDecimal("0");
		int cancelledCount = 0;
		int confirmedCount = 0;
		int totalCount = 0;
		if (visaOrderRowList != null && visaOrderRowList.size() > 0) {
			for (VisaOrderRow visaOrderRow : visaOrderRowList) {
				Object visaOrderRowObj=visaOrderRow;
				
				if(visaOrderRow!=null){
					if(visaOrderRow.getVisaOrderCustomerList()!=null && visaOrderRow.getVisaOrderCustomerList().size()>0){
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(visaOrderRow.getCompanyId()));
						for(VisaOrderCustomer visaOrderCustomer:visaOrderRow.getVisaOrderCustomerList()){
							int count=visaOrderRow.getVisaOrderCustomerList().size();
							BigDecimal convenienceFee = new BigDecimal("0");
							BigDecimal vfsCharges = new BigDecimal("0");
							BigDecimal courierCharges = new BigDecimal("0");
							BigDecimal managementFee = new BigDecimal("0");
							BigDecimal taxes = new BigDecimal("0");
							BigDecimal baseFare = new BigDecimal("0");
							BigDecimal otherTaxes = new BigDecimal("0");
							BigDecimal markup = new BigDecimal("0");
							BigDecimal serviceOrGstTax = new BigDecimal("0");
							convenienceFee = visaOrderRow.getConvenienceFee() != null ? visaOrderRow.getConvenienceFee(): new BigDecimal(0);
							managementFee = visaOrderRow.getManagementFee() != null ? visaOrderRow.getManagementFee(): new BigDecimal(0);
							vfsCharges = visaOrderRow.getVfsCharges() != null ? visaOrderRow.getVfsCharges() : new BigDecimal(0);
							courierCharges = visaOrderRow.getCourierCharges() != null ? visaOrderRow.getCourierCharges(): new BigDecimal(0);
							convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							managementFee=ArithmeticUti.divideTo2Decimal(managementFee.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							vfsCharges=ArithmeticUti.divideTo2Decimal(vfsCharges.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							courierCharges=ArithmeticUti.divideTo2Decimal(courierCharges.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							
							CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
							commonDsrTravelReportData.setSystemInvoiceId(visaOrderRow.getOrderId());
							commonDsrTravelReportData.setTraveller(visaOrderCustomer.getTitle()+" "+visaOrderCustomer.getFirstName()+" "+visaOrderCustomer.getLastName());
							commonDsrTravelReportData.setGDSPNR(visaOrderRow.getConfirmationNumber());
							commonDsrTravelReportData.setDescription(visaOrderRow.getRemarks());
							if(visaOrderRow.isCreditNoteIssued()) 
								commonDsrTravelReportData.setTravelStatus("Confirmed");
							else
								commonDsrTravelReportData.setTravelStatus(visaOrderRow.getStatusAction());
							commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(visaOrderRow.getTravelDate()));
							if (commonDsrTravelReportData.getTripStartsDate() == null)
								commonDsrTravelReportData.setTripStartsDate("-");
							baseFare=ArithmeticUti.divideTo2Decimal(visaOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							otherTaxes=ArithmeticUti.divideTo2Decimal(visaOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							markup=ArithmeticUti.divideTo2Decimal(visaOrderRow.getMarkUp().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							commonDsrTravelReportData.setBaseFare(baseFare.toString());
							taxes = taxes.add(otherTaxes).add(markup).add(convenienceFee).add(managementFee);
							commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
							BigDecimal grossFare = otherTaxes.add(baseFare).add(markup);
							if (taxType.equalsIgnoreCase("GST"))
								serviceOrGstTax = ArithmeticUti.divideTo2Decimal(visaOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							if (taxType.equalsIgnoreCase("Service"))
								serviceOrGstTax = ArithmeticUti.divideTo2Decimal(visaOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee).add(vfsCharges).add(courierCharges);
							commonDsrTravelReportData.setGrossFare((vfsCharges.add(courierCharges).add(grossFare)).setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
							RmConfigTripDetailsModel rmConfigTripDetails =null;
							if(visaOrderCustomer.getRmConfigTripDetailsModel()!=null) 
								rmConfigTripDetails =dsrRmConfigHelperDao.getVisaPaxRmConfigTripDetails(visaOrderCustomer.getRmConfigTripDetailsModel(),visaOrderRow.getVisaOrderRowRmConfigStruct().getRmDynamicData());
							else
								rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(visaOrderRow.getOrderId(),company.getCompanyid());
							if(rmConfigTripDetails!=null)
							{
								if(sessionCompany.getCompanyRole().isSuperUser()){
									if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("V") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
										commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
									else 
										commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
								}
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							}
							buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
							commonDsrTravelReportData.setCreditnoteIssued(visaOrderRow.isCreditNoteIssued());
							commonDsrTravelReports.add(commonDsrTravelReportData);
							CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("V",visaOrderRowObj, commonDsrTravelReportData);
							if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
								commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
								commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
								commonDsrTravelReports.add(commonDsrTravelReportsNew);
								totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
							}
							totalCount++;
							if (visaOrderRow.getStatusAction().equals("Confirmed")) {
								confirmedCount++;
							}
							if (visaOrderRow.getStatusAction().equals("Cancelled")) {
								cancelledCount++;
							}
							totalNetfare = totalNetfare.add(netFare);
								
						}
					}else{
						
						BigDecimal convenienceFee = new BigDecimal("0");
						BigDecimal vfsCharges = new BigDecimal("0");
						BigDecimal courierCharges = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal taxes = new BigDecimal("0");
						BigDecimal serviceOrGstTax = new BigDecimal("0");
						convenienceFee = visaOrderRow.getConvenienceFee() != null ? visaOrderRow.getConvenienceFee()
								: new BigDecimal(0);
						managementFee = visaOrderRow.getManagementFee() != null ? visaOrderRow.getManagementFee()
								: new BigDecimal(0);
						vfsCharges = visaOrderRow.getVfsCharges() != null ? visaOrderRow.getVfsCharges() : new BigDecimal(0);
						courierCharges = visaOrderRow.getCourierCharges() != null ? visaOrderRow.getCourierCharges()
								: new BigDecimal(0);
						CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
						commonDsrTravelReportData.setSystemInvoiceId(visaOrderRow.getOrderId());
						commonDsrTravelReportData.setTraveller(visaOrderRow.getEmpNmae());
						commonDsrTravelReportData.setGDSPNR(visaOrderRow.getConfirmationNumber());
						commonDsrTravelReportData.setDescription(visaOrderRow.getRemarks());
						if(visaOrderRow.isCreditNoteIssued()) 
							commonDsrTravelReportData.setTravelStatus("Confirmed");
						else
							commonDsrTravelReportData.setTravelStatus(visaOrderRow.getStatusAction());
						commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(visaOrderRow.getTravelDate()));
						if (commonDsrTravelReportData.getTripStartsDate() == null)
							commonDsrTravelReportData.setTripStartsDate("-");
						commonDsrTravelReportData
						.setBaseFare(visaOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP).toString());
						taxes = taxes.add(visaOrderRow.getOtherTaxes()).add(visaOrderRow.getMarkUp()).add(convenienceFee)
								.add(managementFee);
						commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
						BigDecimal grossFare = visaOrderRow.getOtherTaxes().add(visaOrderRow.getBasePrice())
								.add(visaOrderRow.getMarkUp());
						if (taxType.equalsIgnoreCase("GST"))
							serviceOrGstTax = visaOrderRow.getTotalGstTax();
						if (taxType.equalsIgnoreCase("Service"))
							serviceOrGstTax = visaOrderRow.getServiceTax();
						BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee)
								.add(vfsCharges).add(courierCharges);
						commonDsrTravelReportData.setGrossFare((vfsCharges.add(courierCharges).add(grossFare)).setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
						RmConfigTripDetailsModel rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(visaOrderRow.getOrderId(), Integer.parseInt(visaOrderRow.getCompanyId()));
						 
							if (sessionCompany.getCompanyRole().isSuperUser()) {
								if (commonDsrPage.getCommonDsrFilters().getTravelType().equals("V")
										&& commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData
									.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else
									commonDsrTravelReportData
									.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							} else
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
						 
						buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
						commonDsrTravelReportData.setCreditnoteIssued(visaOrderRow.isCreditNoteIssued());
						commonDsrTravelReports.add(commonDsrTravelReportData);
						CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("V",visaOrderRowObj, commonDsrTravelReportData);
						if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
							commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
							commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
							commonDsrTravelReports.add(commonDsrTravelReportsNew);
							totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
						}
						totalCount++;
						if (visaOrderRow.getStatusAction().equals("Confirmed")) {
							confirmedCount++;
						}
						if (visaOrderRow.getStatusAction().equals("Cancelled")) {
							cancelledCount++;
						}
						totalNetfare = totalNetfare.add(netFare);
						}
					if (totalCount > 0) {
						avgNetFare = ArithmeticUti.divideTo2Decimal(totalNetfare, new BigDecimal(totalCount));
					}
					netSale=totalNetfare.add(totalRefundAmount);
					
				}
			}
			}
		return buildCommonSalesSummary(totalNetfare, avgNetFare, totalRefundAmount, netSale, cancelledCount,
				confirmedCount, commonDsrTravelReports, salesReportCalSummary);
	}

	public SalesReportCalSummary buildInsuranceReportData(CommonDsrPage commonDsrPage,
			List<InsuranceOrderRow> insuranceOrderRowList, SalesReportCalSummary salesReportCalSummary,
			String taxType) {
		List<CommonDsrTravelReportData> commonDsrTravelReports = new LinkedList<>();
		Company  sessionCompany=commonDsrPage.getCommonDsrFilters().getLoginCompany();
		BigDecimal totalNetfare = new BigDecimal("0");
		BigDecimal avgNetFare = new BigDecimal("0");
		BigDecimal totalRefundAmount = new BigDecimal("0");
		BigDecimal netSale = new BigDecimal("0");
		int cancelledCount = 0;
		int confirmedCount = 0;
		int totalCount = 0;
		if (insuranceOrderRowList != null && insuranceOrderRowList.size() > 0) {
			for (InsuranceOrderRow insuranceOrderRow : insuranceOrderRowList) {
				Object insuranceOrderRowObj=insuranceOrderRow;
				
				if(insuranceOrderRow!=null){
					if(insuranceOrderRow.getInsuranceOrderCustomerDetails()!=null && insuranceOrderRow.getInsuranceOrderCustomerDetails().size()>0){
						Company company=new CompanyDAO().getCompanyProfile(Integer.parseInt(insuranceOrderRow.getCompanyId()));
						for(InsuranceOrderCustomerDetail  insuranceOrderCustomer:insuranceOrderRow.getInsuranceOrderCustomerDetails()){
							int count=insuranceOrderRow.getInsuranceOrderCustomerDetails().size();
							BigDecimal convenienceFee = new BigDecimal("0");
							BigDecimal managementFee = new BigDecimal("0");
							BigDecimal taxes = new BigDecimal("0");
							BigDecimal serviceOrGstTax = new BigDecimal("0");
							BigDecimal baseFare = new BigDecimal("0");
							BigDecimal otherTaxes = new BigDecimal("0");
							BigDecimal markup = new BigDecimal("0");
							convenienceFee = insuranceOrderRow.getConvenienceFee() != null ? insuranceOrderRow.getConvenienceFee(): new BigDecimal(0);
							managementFee = insuranceOrderRow.getManagementFee() != null ? insuranceOrderRow.getManagementFee(): new BigDecimal(0);
							convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							managementFee=ArithmeticUti.divideTo2Decimal(managementFee.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
							commonDsrTravelReportData.setSystemInvoiceId(insuranceOrderRow.getOrderId());
							commonDsrTravelReportData.setTraveller(insuranceOrderCustomer.getTitle()+" "+insuranceOrderCustomer.getFirstName()+" "+insuranceOrderCustomer.getLastName());
							commonDsrTravelReportData.setGDSPNR(insuranceOrderRow.getConfirmationNumber());
							commonDsrTravelReportData.setDescription(insuranceOrderRow.getRemarks());
							if(insuranceOrderRow.isCreditNoteIssued()) 
								commonDsrTravelReportData.setTravelStatus("Confirmed");
							else
								commonDsrTravelReportData.setTravelStatus(insuranceOrderRow.getStatusAction());
							commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getTravelDate()));
							if (commonDsrTravelReportData.getTripStartsDate() == null)
								commonDsrTravelReportData.setTripStartsDate("-");
							baseFare=ArithmeticUti.divideTo2Decimal(insuranceOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							otherTaxes=ArithmeticUti.divideTo2Decimal(insuranceOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							markup=ArithmeticUti.divideTo2Decimal(insuranceOrderRow.getMarkUpamount().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							commonDsrTravelReportData.setBaseFare(baseFare.toString());
							taxes = taxes.add(otherTaxes).add(markup).add(convenienceFee).add(managementFee);
							commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
							if (taxType.equalsIgnoreCase("GST"))
								serviceOrGstTax = ArithmeticUti.divideTo2Decimal(insuranceOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							if (taxType.equalsIgnoreCase("Service"))
								serviceOrGstTax =ArithmeticUti.divideTo2Decimal(insuranceOrderRow.getServiceTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count)); 
							BigDecimal grossFare = otherTaxes.add(baseFare).add(markup);
							BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
							commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
							RmConfigTripDetailsModel rmConfigTripDetails =null;
							if(insuranceOrderCustomer.getRmConfigTripDetailsModel()!=null) 
								rmConfigTripDetails =dsrRmConfigHelperDao.getInsurancePaxRmConfigTripDetails(insuranceOrderCustomer.getRmConfigTripDetailsModel(),insuranceOrderRow.getInsuranceOrderRowRmConfigStruct().getRmDynamicData());
							else
								rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(insuranceOrderRow.getOrderId(),company.getCompanyid());
							if(rmConfigTripDetails!=null)
							{
								if(sessionCompany.getCompanyRole().isSuperUser()){
									if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("I") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
										commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
									else 
										commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
								}
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							}
							buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
							commonDsrTravelReportData.setCreditnoteIssued(insuranceOrderRow.isCreditNoteIssued());
							commonDsrTravelReports.add(commonDsrTravelReportData);
							CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("I",insuranceOrderRowObj, commonDsrTravelReportData);
							if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
								commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
								commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
								commonDsrTravelReports.add(commonDsrTravelReportsNew);
								totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
							}
							totalCount++;
							if (insuranceOrderRow.getStatusAction().equals("Confirmed")) {
								confirmedCount++;
							}
							if (insuranceOrderRow.getStatusAction().equals("Cancelled")) {
								cancelledCount++;
							}
							totalNetfare = totalNetfare.add(netFare);
						}
						
					}else{
						
						BigDecimal convenienceFee = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal taxes = new BigDecimal("0");
						BigDecimal serviceOrGstTax = new BigDecimal("0");
						convenienceFee = insuranceOrderRow.getConvenienceFee() != null ? insuranceOrderRow.getConvenienceFee(): new BigDecimal(0);
						managementFee = insuranceOrderRow.getManagementFee() != null ? insuranceOrderRow.getManagementFee(): new BigDecimal(0);
						CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
						commonDsrTravelReportData.setSystemInvoiceId(insuranceOrderRow.getOrderId());
						commonDsrTravelReportData.setTraveller(insuranceOrderRow.getEmpName());
						commonDsrTravelReportData.setGDSPNR(insuranceOrderRow.getConfirmationNumber());
						commonDsrTravelReportData.setDescription(insuranceOrderRow.getRemarks());
						if(insuranceOrderRow.isCreditNoteIssued()) 
							commonDsrTravelReportData.setTravelStatus("Confirmed");
						else
							commonDsrTravelReportData.setTravelStatus(insuranceOrderRow.getStatusAction());
						commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(insuranceOrderRow.getTravelDate()));
						if (commonDsrTravelReportData.getTripStartsDate() == null)
							commonDsrTravelReportData.setTripStartsDate("-");

						commonDsrTravelReportData.setBaseFare(insuranceOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP).toString());
						taxes = taxes.add(insuranceOrderRow.getOtherTaxes()).add(insuranceOrderRow.getMarkUpamount()).add(convenienceFee).add(managementFee);
						commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
						if (taxType.equalsIgnoreCase("GST"))
							serviceOrGstTax = insuranceOrderRow.getTotalGstTax();
						if (taxType.equalsIgnoreCase("Service"))
							serviceOrGstTax = insuranceOrderRow.getServiceTax();

						BigDecimal grossFare = insuranceOrderRow.getOtherTaxes().add(insuranceOrderRow.getBasePrice()).add(insuranceOrderRow.getMarkUpamount());
						BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
						commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
						RmConfigTripDetailsModel rmConfigTripDetails = dsrRmConfigHelperDao.getRmConfigTripDetails(insuranceOrderRow.getOrderId(), Integer.parseInt(insuranceOrderRow.getCompanyId()));
					 
							if (sessionCompany.getCompanyRole().isSuperUser()) {
								if (commonDsrPage.getCommonDsrFilters().getTravelType().equals("I")
										&& commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData
									.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else
									commonDsrTravelReportData
									.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							} else
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
						 
						buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
						
						commonDsrTravelReportData.setCreditnoteIssued(insuranceOrderRow.isCreditNoteIssued());
						commonDsrTravelReports.add(commonDsrTravelReportData);
						CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("I",insuranceOrderRowObj, commonDsrTravelReportData);
						if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
							commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
							commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
							commonDsrTravelReports.add(commonDsrTravelReportsNew);
							totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
						}
						totalCount++;
						if (insuranceOrderRow.getStatusAction().equals("Confirmed")) {
							confirmedCount++;
						}
						if (insuranceOrderRow.getStatusAction().equals("Cancelled")) {
							cancelledCount++;
						}
						totalNetfare = totalNetfare.add(netFare);
						
						
						
					}
					if (totalCount > 0) {
						avgNetFare = ArithmeticUti.divideTo2Decimal(totalNetfare, new BigDecimal(totalCount));
					}
					netSale=totalNetfare.add(totalRefundAmount);

				}
				
				
				
				

			}
			
		}
		return buildCommonSalesSummary(totalNetfare, avgNetFare, totalRefundAmount, netSale, cancelledCount,
				confirmedCount, commonDsrTravelReports, salesReportCalSummary);
	}


	public SalesReportCalSummary buildMiscellaneousReportData(CommonDsrPage commonDsrPage,
			List<MiscellaneousOrderRow> miscellaneousOrderRowList, SalesReportCalSummary salesReportCalSummary,
			String taxType) {
		List<CommonDsrTravelReportData> commonDsrTravelReports = new LinkedList<>();
		Company  sessionCompany=commonDsrPage.getCommonDsrFilters().getLoginCompany();
		BigDecimal totalNetfare = new BigDecimal("0");
		BigDecimal avgNetFare = new BigDecimal("0");
		BigDecimal totalRefundAmount = new BigDecimal("0");
		BigDecimal netSale = new BigDecimal("0");
		int cancelledCount = 0;
		int confirmedCount = 0;
		int totalCount = 0;
		if (miscellaneousOrderRowList != null && miscellaneousOrderRowList.size() > 0) {
			for (MiscellaneousOrderRow  miscellaneousOrderRow : miscellaneousOrderRowList) {
				Object miscellaneousOrderRowObj=miscellaneousOrderRow;
				if(miscellaneousOrderRow!=null){
					if(miscellaneousOrderRow.getMiscellaneousOrderCustomerList()!=null && miscellaneousOrderRow.getMiscellaneousOrderCustomerList().size()>0){
						Company company=new CompanyDAO().getCompanyProfile(miscellaneousOrderRow.getCompanyId());
						for(MiscellaneousOrderCustomer miscOrderCustomer:miscellaneousOrderRow.getMiscellaneousOrderCustomerList()){
							int count=miscellaneousOrderRow.getMiscellaneousOrderCustomerList().size();
							BigDecimal convenienceFee = new BigDecimal("0");
							BigDecimal managementFee = new BigDecimal("0");
							BigDecimal taxes = new BigDecimal("0");
							BigDecimal serviceOrGstTax = new BigDecimal("0");
							BigDecimal baseFare = new BigDecimal("0");
							BigDecimal otherTaxes = new BigDecimal("0");
							BigDecimal markup = new BigDecimal("0");
							convenienceFee = miscellaneousOrderRow.getConvenienceFee() != null ? miscellaneousOrderRow.getConvenienceFee(): new BigDecimal(0);
							managementFee = miscellaneousOrderRow.getManagementFee() != null ? miscellaneousOrderRow.getManagementFee(): new BigDecimal(0);
							convenienceFee=ArithmeticUti.divideTo2Decimal(convenienceFee.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							managementFee=ArithmeticUti.divideTo2Decimal(managementFee.setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
							commonDsrTravelReportData.setBkgRef(miscellaneousOrderRow.getOrderId()); 
							commonDsrTravelReportData.setSystemInvoiceId(miscellaneousOrderRow.getInvoiceNo()); 
							commonDsrTravelReportData.setTraveller(miscellaneousOrderRow.getOrderCustomer().getTitle()+" "+miscellaneousOrderRow.getOrderCustomer().getFirstName()+" "+miscellaneousOrderRow.getOrderCustomer().getLastName());
							commonDsrTravelReportData.setGDSPNR(miscellaneousOrderRow.getConfirmationNumber());
							String details1=miscellaneousOrderRow.getDetails1()!=null && !miscellaneousOrderRow.getDetails1().equals("")?miscellaneousOrderRow.getDetails1():"NA";
							String details2=miscellaneousOrderRow.getDetails2()!=null && !miscellaneousOrderRow.getDetails2().equals("")?miscellaneousOrderRow.getDetails2():"NA";
							commonDsrTravelReportData.setDescription(details1+"/"+details2);
							if(miscellaneousOrderRow.isCreditNoteIssued()) 
								commonDsrTravelReportData.setTravelStatus("Confirmed");
							else
								commonDsrTravelReportData.setTravelStatus(miscellaneousOrderRow.getStatusAction());

							commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(miscellaneousOrderRow.getBookingDate()));
							if (commonDsrTravelReportData.getTripStartsDate() == null)
								commonDsrTravelReportData.setTripStartsDate("-");
							baseFare=ArithmeticUti.divideTo2Decimal(miscellaneousOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							otherTaxes=ArithmeticUti.divideTo2Decimal(miscellaneousOrderRow.getOtherTaxes().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							markup=ArithmeticUti.divideTo2Decimal(miscellaneousOrderRow.getMarkUpamount().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							commonDsrTravelReportData.setBaseFare(baseFare.toString());
							taxes = taxes.add(otherTaxes).add(markup).add(convenienceFee).add(managementFee);
							commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
							if (taxType.equalsIgnoreCase("GST"))
								serviceOrGstTax = ArithmeticUti.divideTo2Decimal(miscellaneousOrderRow.getTotalGstTax().setScale(2, BigDecimal.ROUND_UP), new BigDecimal(count));
							if (taxType.equalsIgnoreCase("Service"))
								serviceOrGstTax = new BigDecimal(0);
							BigDecimal grossFare = otherTaxes.add(baseFare).add(markup);
							BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
							commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
							commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
							RmConfigTripDetailsModel rmConfigTripDetails =null;
							if(miscOrderCustomer.getRmConfigTripDetailsModel()!=null) 
								rmConfigTripDetails =dsrRmConfigHelperDao.getMiscellaneousPaxRmConfigTripDetails(miscOrderCustomer.getRmConfigTripDetailsModel(),miscellaneousOrderRow.getMiscellaneousOrderRowRmConfigStruct().getRmDynamicData());
							else
								rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(miscellaneousOrderRow.getOrderId(),company.getCompanyid());
							if(rmConfigTripDetails!=null)
							{
								if(sessionCompany.getCompanyRole().isSuperUser()){
									if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("M") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
										commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
									else 
										commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
								}
								else 
									commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							}
							buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
							commonDsrTravelReportData.setCreditnoteIssued(miscellaneousOrderRow.isCreditNoteIssued());
							commonDsrTravelReports.add(commonDsrTravelReportData);
							CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("M",miscellaneousOrderRowObj, commonDsrTravelReportData);
							if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
								commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
								commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
								commonDsrTravelReports.add(commonDsrTravelReportsNew);
								totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
							}
							totalCount++;
							if (miscellaneousOrderRow.getStatusAction().equals("Confirmed")) {
								confirmedCount++;
							}
							if (miscellaneousOrderRow.getStatusAction().equals("Cancelled")) {
								cancelledCount++;
							}
							totalNetfare = totalNetfare.add(netFare);
							
						}
					}else{
						
						
						BigDecimal convenienceFee = new BigDecimal("0");
						BigDecimal managementFee = new BigDecimal("0");
						BigDecimal taxes = new BigDecimal("0");
						BigDecimal serviceOrGstTax = new BigDecimal("0");
						convenienceFee = miscellaneousOrderRow.getConvenienceFee() != null ? miscellaneousOrderRow.getConvenienceFee(): new BigDecimal(0);
						managementFee = miscellaneousOrderRow.getManagementFee() != null ? miscellaneousOrderRow.getManagementFee(): new BigDecimal(0);
						CommonDsrTravelReportData commonDsrTravelReportData=new CommonDsrTravelReportData();
						commonDsrTravelReportData.setBkgRef(miscellaneousOrderRow.getOrderId()); 
						commonDsrTravelReportData.setSystemInvoiceId(miscellaneousOrderRow.getInvoiceNo()); 
						commonDsrTravelReportData.setTraveller(miscellaneousOrderRow.getOrderCustomer().getTitle()+" "+miscellaneousOrderRow.getOrderCustomer().getFirstName()+" "+miscellaneousOrderRow.getOrderCustomer().getLastName());
						commonDsrTravelReportData.setGDSPNR(miscellaneousOrderRow.getConfirmationNumber());
						String details1=miscellaneousOrderRow.getDetails1()!=null && !miscellaneousOrderRow.getDetails1().equals("")?miscellaneousOrderRow.getDetails1():"NA";
						String details2=miscellaneousOrderRow.getDetails2()!=null && !miscellaneousOrderRow.getDetails2().equals("")?miscellaneousOrderRow.getDetails2():"NA";
						commonDsrTravelReportData.setDescription(details1+"/"+details2);
						if(miscellaneousOrderRow.isCreditNoteIssued()) 
							commonDsrTravelReportData.setTravelStatus("Confirmed");
						else
							commonDsrTravelReportData.setTravelStatus(miscellaneousOrderRow.getStatusAction());

						commonDsrTravelReportData.setTripStartsDate(DateConversion.convertDateToStringToDate(miscellaneousOrderRow.getBookingDate()));
						if (commonDsrTravelReportData.getTripStartsDate() == null)
							commonDsrTravelReportData.setTripStartsDate("-");

						commonDsrTravelReportData.setBaseFare(miscellaneousOrderRow.getBasePrice().setScale(2, BigDecimal.ROUND_UP).toString());
						taxes = taxes.add(miscellaneousOrderRow.getOtherTaxes()).add(miscellaneousOrderRow.getMarkUpamount()).add(convenienceFee).add(managementFee);
						commonDsrTravelReportData.setOtherTaxes(taxes.setScale(2, BigDecimal.ROUND_UP).toString());
						if (taxType.equalsIgnoreCase("GST"))
							serviceOrGstTax = miscellaneousOrderRow.getTotalGstTax();
						if (taxType.equalsIgnoreCase("Service"))
							serviceOrGstTax = new BigDecimal(0);

						BigDecimal grossFare = miscellaneousOrderRow.getOtherTaxes().add(miscellaneousOrderRow.getBasePrice()).add(miscellaneousOrderRow.getMarkUpamount());
						BigDecimal netFare = grossFare.add(serviceOrGstTax).add(convenienceFee).add(managementFee);
						commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());
						commonDsrTravelReportData.setNetFare(netFare.setScale(2, BigDecimal.ROUND_UP).toString());
						RmConfigTripDetailsModel rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(miscellaneousOrderRow.getOrderId(), miscellaneousOrderRow.getCompanyId());
						 
							if (sessionCompany.getCompanyRole().isSuperUser()) {
								if (commonDsrPage.getCommonDsrFilters().getTravelType().equals("M")
										&& commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
									commonDsrTravelReportData
									.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
								else
									commonDsrTravelReportData
									.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
							} else
								commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
						 
						buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
						commonDsrTravelReportData.setCreditnoteIssued(miscellaneousOrderRow.isCreditNoteIssued());
						commonDsrTravelReports.add(commonDsrTravelReportData);
						CommonDsrTravelReportData commonDsrTravelReportsNew  =dsrRmConfigOrderCancelHelper.showCancelledOrderDetails("M",miscellaneousOrderRowObj, commonDsrTravelReportData);
						if(commonDsrTravelReportsNew!=null && commonDsrTravelReportsNew.isCreditnoteIssued()){
							commonDsrTravelReportsNew.setTripStartsDate(commonDsrTravelReportsNew.getInvoicedate());
							commonDsrTravelReportsNew.setGrossFare(commonDsrTravelReportsNew.getNetFare());
							commonDsrTravelReports.add(commonDsrTravelReportsNew);
							totalRefundAmount=totalRefundAmount.add(new BigDecimal(commonDsrTravelReportsNew.getGrossFare()));
						}
						totalCount++;
						if (miscellaneousOrderRow.getStatusAction().equals("Confirmed")) {
							confirmedCount++;
						}
						if (miscellaneousOrderRow.getStatusAction().equals("Cancelled")) {
							cancelledCount++;
						}
						totalNetfare = totalNetfare.add(netFare);
						
					}
					if (totalCount > 0) {
						avgNetFare = ArithmeticUti.divideTo2Decimal(totalNetfare, new BigDecimal(totalCount));
					}
					netSale=totalNetfare.add(totalRefundAmount);
				}
				
			}
			
		}
		return buildCommonSalesSummary(totalNetfare, avgNetFare, totalRefundAmount, netSale, cancelledCount,
				confirmedCount, commonDsrTravelReports, salesReportCalSummary);
	}


	public SalesReportCalSummary buildCommonSalesSummary(BigDecimal totalNetfare, BigDecimal avgNetFare,
			BigDecimal totalTktfare, BigDecimal avgTktfare, int cancelledCount, int confirmedCount,
			List<CommonDsrTravelReportData> commonDsrTravelReports, SalesReportCalSummary salesReportCalSummary) {
		salesReportCalSummary.setAvgTktCost(avgTktfare.setScale(2, BigDecimal.ROUND_UP));
		salesReportCalSummary.setAvgTotalFare(avgNetFare.setScale(2, BigDecimal.ROUND_UP));
		salesReportCalSummary.setCancelledCount(cancelledCount);
		salesReportCalSummary.setTotalCount(confirmedCount+cancelledCount);
		salesReportCalSummary.setTotalFare(totalNetfare.setScale(2, BigDecimal.ROUND_UP));
		salesReportCalSummary.setTotalTktCost(totalTktfare.setScale(2, BigDecimal.ROUND_UP));
		salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);
		return salesReportCalSummary;
	}

	public Map<String, List<SalesReportCalSummary>> buildAirSalesSummary(Set<String> airlineList,
			List<FlightOrderRow> flightOrderRowList, String taxType) {
		Map<String, List<SalesReportCalSummary>> summaryMap = new LinkedHashMap<>();

		if (airlineList != null && airlineList.size() > 0) {
			for (String airline : airlineList) {
				List<SalesReportCalSummary> list = new LinkedList<>();
				for (FlightOrderRow flightOrderRow : flightOrderRowList) {
					Object flightOrderRowObj=flightOrderRow;
					if (airline.equalsIgnoreCase(flightOrderRow.getAirline())) {
						SalesReportCalSummary salesReportCalSummary = new SalesReportCalSummary();
						if (taxType.equalsIgnoreCase("Service")){
							if(flightOrderRow.getServiceTax()!=null && !flightOrderRow.getServiceTax().equals("")){
								salesReportCalSummary.setInvoiceAmount(flightOrderRow.getFinalPrice().add(flightOrderRow.getServiceTax()).add(flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()).add(flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()));
							}
						}
							if (taxType.equalsIgnoreCase("GST")){
								if(flightOrderRow.getGstOnFlights()!=null && !flightOrderRow.getGstOnFlights().equals(""))
									salesReportCalSummary.setInvoiceAmount(flightOrderRow.getFinalPrice().add(flightOrderRow.getGstOnFlights()).add(flightOrderRow.getFlightOrderRowGstTax()!=null && flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee():new BigDecimal(0)).add(flightOrderRow.getFlightOrderRowGstTax()!=null && flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getManagementFee():new BigDecimal(0)));
							}
							salesReportCalSummary.setStatus(flightOrderRow.getStatusAction());
							BigDecimal refundAmount=getRefundAmount("F", flightOrderRowObj);
							if(refundAmount!=null) 
								salesReportCalSummary.setRefundAmount(refundAmount);
							else
								salesReportCalSummary.setRefundAmount(new BigDecimal(0));
							list.add(salesReportCalSummary);
						}
					}
					summaryMap.put(airline, list);
				}
			}
		 
			return summaryMap;
		}

		public Map<String, List<SalesReportCalSummary>> buildHotelSalesSummary(Set<String> cityList,
				List<HotelOrderRow> hotelOrderRowList, String taxType){
			Map<String, List<SalesReportCalSummary>> summaryMap = new LinkedHashMap<>();
			if (cityList != null && cityList.size() > 0) {
				for (String city : cityList) {
					List<SalesReportCalSummary> list = new LinkedList<>();
					for (HotelOrderRow hotelOrderRow : hotelOrderRowList) {
						Object hotelOrderRowObj=hotelOrderRow;
						String cityNew=hotelOrderRow.getHotelOrderHotelData()!=null&&hotelOrderRow.getHotelOrderHotelData().getCity()!=null?hotelOrderRow.getHotelOrderHotelData().getCity():"NA";
						if(cityNew.equalsIgnoreCase("Bangalore")||cityNew.equalsIgnoreCase("Bengaluru")) 
							cityNew="Bengaluru(Bangalore)";
						if (city.equalsIgnoreCase(cityNew)) {
							SalesReportCalSummary salesReportCalSummary = new SalesReportCalSummary();
							BigDecimal serviceTaxOrgst=new BigDecimal(0);

							if(taxType.equalsIgnoreCase("Service")){
								if(hotelOrderRow.getServiceTax()!=null && !hotelOrderRow.getServiceTax().equals(""))
									serviceTaxOrgst=serviceTaxOrgst.add(hotelOrderRow.getServiceTax());

								if(hotelOrderRow.getHotelOrderRowServiceTax()!=null) 
									salesReportCalSummary.setInvoiceAmount(hotelOrderRow.getFinalPrice().add(serviceTaxOrgst.add(hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()).add(hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee())));
								else
									salesReportCalSummary.setInvoiceAmount(hotelOrderRow.getFinalPrice().add(serviceTaxOrgst));

							}
							if(taxType.equalsIgnoreCase("GST")){
								if(hotelOrderRow.getTotGst()!=null && !hotelOrderRow.getTotGst().equals(""))
									serviceTaxOrgst=serviceTaxOrgst.add(hotelOrderRow.getTotGst());

								if(hotelOrderRow.getHotelOrderRowGstTax()!=null) 
									salesReportCalSummary.setInvoiceAmount(hotelOrderRow.getFinalPrice().add(serviceTaxOrgst.add(hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee()).add(hotelOrderRow.getHotelOrderRowGstTax().getManagementFee())));
								else
									salesReportCalSummary.setInvoiceAmount(hotelOrderRow.getFinalPrice().add(serviceTaxOrgst));
							}
							String checkInDate= DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate());
							String checkOutDate= DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate());
							salesReportCalSummary.setCheckInDate(checkInDate);
							salesReportCalSummary.setCheckOutDate(checkOutDate);
							salesReportCalSummary.setStatus(hotelOrderRow.getStatusAction());
							salesReportCalSummary.setNoOfRooms(hotelOrderRow.getNoOfRooms());
							BigDecimal refundAmount=getRefundAmount("H", hotelOrderRowObj);
							if(refundAmount!=null) 
								salesReportCalSummary.setRefundAmount(refundAmount);
							else
								salesReportCalSummary.setRefundAmount(new BigDecimal(0));
							list.add(salesReportCalSummary);
						}
					}
					summaryMap.put(city, list);
				}
			}
			return summaryMap;
		}

		public static 	 List<SalesReportCalSummary>  buildRequiredDataFromSummaryMap(Map<String,List<SalesReportCalSummary>> summaryMap,String type){
			Set<String> keySet = summaryMap.keySet();
			List<SalesReportCalSummary> summaryTempList = new LinkedList<>();
			for (String key : keySet) {
				int totalCount = 0;
				int cancelledCount = 0;
				BigDecimal totalAmount = new BigDecimal(0);
				BigDecimal avgTotalAmount = new BigDecimal(0);
				BigDecimal totalRefundAmount = new BigDecimal(0);
				BigDecimal netSale = new BigDecimal(0);
				List<SalesReportCalSummary> listNew = summaryMap.get(key);
				SalesReportCalSummary summaryTemp = new SalesReportCalSummary();
				int noOfNightsConfirmed = 0;
				int noOfNightsCancelled = 0;
				for (SalesReportCalSummary sum : listNew) {
					totalAmount = totalAmount.add(sum.getInvoiceAmount());
					//totalCount++;
					if(type.equalsIgnoreCase("H")){
						try {
							noOfNightsConfirmed =noOfNightsConfirmed+(sum.getNoOfRooms()* CommonUtil.getNoofStayDays(sum.getCheckInDate(), sum.getCheckOutDate()));

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						//noOfRoomsConfirmed=noOfRoomsConfirmed+sum.getNoOfRooms();

					} 
					else{
						totalCount++;
					}


					if (sum.getStatus().equals("Cancelled")) {
						totalRefundAmount = totalRefundAmount.add(sum.getRefundAmount());
						//cancelledCount++;
						if(type.equalsIgnoreCase("H")){
							try {
								noOfNightsCancelled = noOfNightsCancelled+(sum.getNoOfRooms()*CommonUtil.getNoofStayDays(sum.getCheckInDate(), sum.getCheckOutDate()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//noOfRoomsCancelled=noOfRoomsCancelled+sum.getNoOfRooms();
						} 
						else{
							cancelledCount++;
						}
					}
					
				}

				if(type.equalsIgnoreCase("H")){
					totalCount=noOfNightsConfirmed;
					cancelledCount=noOfNightsCancelled;
				}

				if (totalCount > 0)
					avgTotalAmount = ArithmeticUti.divideTo2Decimal(totalAmount, new BigDecimal(totalCount));
				else
					avgTotalAmount = totalAmount;
				
				netSale=totalAmount.setScale(2, BigDecimal.ROUND_UP).add(totalRefundAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setAirlineOrHotel(key);
				summaryTemp.setTotalFare(totalAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setCancelledTktAmount(totalRefundAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setNetSale(netSale.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setAvgTotalFare(avgTotalAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setCancelledCount(cancelledCount);
				summaryTemp.setTotalCount(totalCount);
				summaryTempList.add(summaryTemp);
			}
			return summaryTempList;
		}
		public static 	 List<SalesReportCalSummary>  buildHotelRequiredDataFromSummaryMap(Map<String,List<SalesReportCalSummary>> summaryMap){
			Set<String> keySet=summaryMap.keySet();
			List<SalesReportCalSummary> summaryTempList =new LinkedList<>();
			for(String  key:keySet){
				BigDecimal totalAmount=new BigDecimal(0);
				BigDecimal avgTotalAmount=new BigDecimal(0);
				int noofBookedNights = 0;
				int noofancelledNights = 0;
				BigDecimal  cancelledTktAmount=new BigDecimal(0);
				List<SalesReportCalSummary> listNew=summaryMap.get(key);
				SalesReportCalSummary   summaryTemp=new SalesReportCalSummary();
				for(SalesReportCalSummary sum:listNew){
					totalAmount=totalAmount.add(sum.getInvoiceAmount());

					try {
						int noOfNights=CommonUtil.getNoofStayDays(sum.getCheckInDate(), sum.getCheckOutDate());
						noofBookedNights=noofBookedNights+noOfNights*sum.getNoOfRooms();
					} catch (ParseException e) {	
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(sum.getStatus().equals("Cancelled")){
						cancelledTktAmount=cancelledTktAmount.add(sum.getInvoiceAmount());
						try {
							int noOfNights=CommonUtil.getNoofStayDays(sum.getCheckInDate(), sum.getCheckOutDate());
							noofancelledNights=noofancelledNights+noOfNights*sum.getNoOfRooms();
						} catch (ParseException e) {	
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(noofBookedNights>0)
					avgTotalAmount=ArithmeticUti.divideTo2Decimal(totalAmount, new BigDecimal(noofBookedNights)) ;
				else
					avgTotalAmount=totalAmount;
				summaryTemp.setNoofBookedNights(noofBookedNights);
				summaryTemp.setNoofancelledNights(noofancelledNights);
				summaryTemp.setAirlineOrHotel(key);
				summaryTemp.setTotalFare(totalAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setAvgTotalFare(avgTotalAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setCancelledTktAmount(cancelledTktAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTempList.add(summaryTemp);
			}

			return summaryTempList;

		}
		public SalesReportCalSummary buildHotelCityWiseSalesReportData(List<HotelOrderRow> hotelOrderRowList,
				SalesReportCalSummary salesReportCalSummary, String taxType) {
			List<CommonDsrTravelReportData> commonDsrTravelReports = new LinkedList<>();
			BigDecimal totalBasePriceAmount = new BigDecimal("0");
			BigDecimal basePriceInBooking = new BigDecimal("0");
			BigDecimal avgBasePrice = new BigDecimal("0");
			BigDecimal totInvoiceAmount = new BigDecimal("0");
			BigDecimal avgTotal = new BigDecimal("0");
			int noOfNightstotal = 0;
			int totalNightWithCancellation=0;
			int noOfRoomsBooked = 0;
			int cancelledCount = 0;
			BigDecimal netFare = new BigDecimal("0");
			BigDecimal grossFare = null;
			BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
			BigDecimal roomTaxesPriceInBooking = null;
			BigDecimal taxesPriceInBooking = null;
			BigDecimal roomMarkupInBooking = null;
			BigDecimal managementFeePerRoom = new BigDecimal("0");
			BigDecimal convenienceFeePerRoom = new BigDecimal("0");
			BigDecimal TotalServiceOrGstTax = new BigDecimal("0");

			if (hotelOrderRowList != null && hotelOrderRowList.size() > 0) {
				for (HotelOrderRow hotelOrderRow : hotelOrderRowList) {
					CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
					String checkInDate=null;
					String checkOutDate=null;
					String name = hotelOrderRow.getHotelOrderHotelData().getName();
					String city = hotelOrderRow.getHotelOrderHotelData().getCity();
					String guestName = hotelOrderRow.getOrderCustomer().getTitle()+" "+
							hotelOrderRow.getOrderCustomer().getFirstName()+" "+hotelOrderRow.getOrderCustomer().getLastName();
					String status = hotelOrderRow.getStatusAction();
					if(hotelOrderRow.getCheckInDate() !=null)
						checkInDate = DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate());
					if(hotelOrderRow.getCheckOutDate() !=null)
						checkOutDate = DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate());
					int noOfNights = 0;
					int noOfNightsIfCancelled=0;

					if (checkInDate != null && checkOutDate != null) {
						try {
							noOfNights = CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if(hotelOrderRow.getStatusAction().equalsIgnoreCase("Cancelled")){
						try {
							noOfNightsIfCancelled = CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (hotelOrderRow.getTaxes() != null) {
						taxesPriceInBooking = hotelOrderRow.getTaxes()
								.multiply(hotelOrderRow.getApiToBaseExchangeRate())
								.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
						roomTaxesPriceInBooking = taxesPriceInBooking;
					} else
						roomTaxesPriceInBooking = new BigDecimal("0");

					if (hotelOrderRow.getMarkupAmount() != null)
						roomMarkupInBooking = hotelOrderRow.getMarkupAmount();
					else
						roomMarkupInBooking = new BigDecimal("0");

					otherTaxesWithMarkup = roomTaxesPriceInBooking.setScale(2, BigDecimal.ROUND_UP)
							.add(roomMarkupInBooking).setScale(2, BigDecimal.ROUND_UP);


					if (hotelOrderRow.getApiPrice() != null && hotelOrderRow.getTaxes() != null) {
						basePriceInBooking = hotelOrderRow.getApiPrice().subtract(hotelOrderRow.getTaxes())
								.multiply(hotelOrderRow.getApiToBaseExchangeRate())
								.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
						//	roomBasePriceInBooking = basePriceInBooking;

					} 
					totalBasePriceAmount=totalBasePriceAmount.add(basePriceInBooking).setScale(2, RoundingMode.HALF_UP);
					grossFare = basePriceInBooking.add(otherTaxesWithMarkup);


					if (taxType.equalsIgnoreCase("Service")) {
						if (hotelOrderRow.getHotelOrderRowServiceTax() != null
								&& hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee() != null)
							managementFeePerRoom = hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee();

						else
							managementFeePerRoom = new BigDecimal("0");

						if (hotelOrderRow.getHotelOrderRowServiceTax() != null
								&& hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee() != null)
							convenienceFeePerRoom = hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee()
							.divide(new BigDecimal(hotelOrderRow.getNoOfRooms()));
						else
							convenienceFeePerRoom = new BigDecimal("0");

						if (hotelOrderRow.getHotelOrderRowServiceTax() != null
								&& hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax() != null)
							TotalServiceOrGstTax=hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax();
					} 
					if (taxType.equalsIgnoreCase("GST")) {
						if (hotelOrderRow.getHotelOrderRowGstTax() != null
								&& hotelOrderRow.getHotelOrderRowGstTax().getManagementFee() != null)
							managementFeePerRoom = hotelOrderRow.getHotelOrderRowGstTax().getManagementFee();
						else
							managementFeePerRoom = new BigDecimal("0");
						if (hotelOrderRow.getHotelOrderRowGstTax() != null
								&& hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee() != null)
							convenienceFeePerRoom = hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee();

						else
							convenienceFeePerRoom = new BigDecimal("0");

						if (hotelOrderRow.getHotelOrderRowGstTax() != null
								&& hotelOrderRow.getHotelOrderRowGstTax().getTotalGst() != null)
							TotalServiceOrGstTax=hotelOrderRow.getHotelOrderRowGstTax().getTotalGst();


					}
					netFare = grossFare.add(TotalServiceOrGstTax).add(managementFeePerRoom).add(convenienceFeePerRoom);

					totInvoiceAmount = totInvoiceAmount.add(netFare).setScale(2, RoundingMode.HALF_UP);

					totalNightWithCancellation=totalNightWithCancellation+noOfNights;
					noOfNightstotal = noOfNightstotal + noOfNights;
					noOfRoomsBooked = noOfRoomsBooked + hotelOrderRow.getNoOfRooms();
					if (hotelOrderRow.getStatusAction().equalsIgnoreCase("Cancelled")) {
						cancelledCount++;
						noOfNightstotal = noOfNightstotal-noOfNightsIfCancelled;
					}
					commonDsrTravelReportData.setHotelName(name);
					commonDsrTravelReportData.setCity(city);
					commonDsrTravelReportData.setGuestName(guestName);
					commonDsrTravelReportData.setStatus(status);
					commonDsrTravelReportData.setTotalInvoiceAmount(netFare.setScale(2, RoundingMode.HALF_UP).toString());
					commonDsrTravelReportData.setBasePrice(basePriceInBooking.setScale(2, RoundingMode.HALF_UP).toString());
					commonDsrTravelReportData.setCheckInDate(checkInDate);
					commonDsrTravelReportData.setCheckOutDate(checkOutDate);
					commonDsrTravelReportData.setNoOfNights(String.valueOf(noOfNights));
					commonDsrTravelReports.add(commonDsrTravelReportData);
				}
			}


			totInvoiceAmount = totInvoiceAmount.setScale(2, RoundingMode.HALF_UP);
			BigDecimal divider1 = ArithmeticUti.multiplyTo2Decimal(new BigDecimal(noOfNightstotal),
					new BigDecimal(noOfRoomsBooked));

			if (divider1.compareTo(BigDecimal.ZERO) != 0){
				String avgprice1 = totInvoiceAmount.divide(divider1, 2, RoundingMode.HALF_UP).toPlainString();
				avgTotal = new BigDecimal(avgprice1).setScale(2, RoundingMode.HALF_UP);
				avgTotal = avgTotal.setScale(2, RoundingMode.HALF_UP);
			}
			else if((divider1.compareTo(BigDecimal.ZERO) == 0)){
				divider1=new BigDecimal("1.0000000000");
				String avgprice1 = totInvoiceAmount.divide(divider1, 2, RoundingMode.HALF_UP).toPlainString();
				avgTotal = new BigDecimal(avgprice1).setScale(2, RoundingMode.HALF_UP);
				avgTotal = avgTotal.setScale(2, RoundingMode.HALF_UP);

			}


			totalBasePriceAmount = totalBasePriceAmount.setScale(2, RoundingMode.HALF_UP);
			BigDecimal divider = ArithmeticUti.multiplyTo2Decimal(new BigDecimal(noOfNightstotal),
					new BigDecimal(noOfRoomsBooked));

			if (divider.compareTo(BigDecimal.ZERO) != 0){
				String avgprice = totalBasePriceAmount.divide(divider, 2, RoundingMode.HALF_UP).toPlainString();
				avgBasePrice = new BigDecimal(avgprice).setScale(2, RoundingMode.HALF_UP);
				avgBasePrice = avgBasePrice.setScale(2, RoundingMode.HALF_UP);
			}
			else if((divider.compareTo(BigDecimal.ZERO) == 0)){
				divider=new BigDecimal("1.0000000000");
				String avgprice = totalBasePriceAmount.divide(divider, 2, RoundingMode.HALF_UP).toPlainString();
				avgBasePrice = new BigDecimal(avgprice).setScale(2, RoundingMode.HALF_UP);
				avgBasePrice = avgBasePrice.setScale(2, RoundingMode.HALF_UP);

			}
 
			salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);
			salesReportCalSummary = buildHotelCityWiseSalesSummary(avgTotal, avgBasePrice, cancelledCount, totalNightWithCancellation,
					commonDsrTravelReports, salesReportCalSummary);
			return salesReportCalSummary;
		}

		public SalesReportCalSummary buildHotelCityWiseSalesSummary(BigDecimal avgTotal, BigDecimal avgBasePrice,
				int cancelledCount, int totalNightWithCancellation, List<CommonDsrTravelReportData> commonDsrTravelReports,
				SalesReportCalSummary salesReportCalSummary) {
			salesReportCalSummary.setNoofBookedNights(totalNightWithCancellation);
			salesReportCalSummary.setNoofancelledNights(cancelledCount);
			salesReportCalSummary.setAvgBaseRoomNightCost(avgBasePrice);
			salesReportCalSummary.setAvgTotalRoomNightCost(avgTotal);
			return salesReportCalSummary;

		}

		public static List<SalesReportCalSummary> buildHotelCityWiseRequiredDataFromSummaryMap(
				Map<String, List<SalesReportCalSummary>> summaryMap) {
			Set<String> keySet = summaryMap.keySet();
			List<SalesReportCalSummary> summaryTempList = new LinkedList<>();
			for (String key : keySet) {
				int totalCount = 0;
				int cancelledCount = 0;
				BigDecimal totalAmount = new BigDecimal(0);
				BigDecimal avgTotalAmount = new BigDecimal(0);
				BigDecimal cancelledTktAmount = new BigDecimal(0);
				List<SalesReportCalSummary> listNew = summaryMap.get(key);
				SalesReportCalSummary summaryTemp = new SalesReportCalSummary();
				for (SalesReportCalSummary sum : listNew) {
					totalAmount = totalAmount.add(sum.getInvoiceAmount());
					totalCount++;
					if (sum.getStatus().equals("Cancelled")) {
						cancelledTktAmount = cancelledTktAmount.add(sum.getInvoiceAmount());
						cancelledCount++;
					}
				}
				if (totalCount > 0)
					avgTotalAmount = ArithmeticUti.divideTo2Decimal(totalAmount, new BigDecimal(totalCount));
				else
					avgTotalAmount = totalAmount;
				// summaryTemp.setAirlineOrHotel(key);
				summaryTemp.setTotalFare(totalAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setAvgTotalFare(avgTotalAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setCancelledTktAmount(cancelledTktAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setCancelledCount(cancelledCount);
				summaryTemp.setTotalCount(totalCount);
				summaryTempList.add(summaryTemp);
			}
			return summaryTempList;
		}

		// ADDED BY BASHA FOR Air Advanced Purchase Sales Report
		private SalesReportCalSummary buildAirAdvencedPurchaseSalesReportData(CommonDsrPage commonDsrPage,
				List<FlightOrderRow> flightOrderRowList, SalesReportCalSummary salesReportCalSummary,String taxType) {
			Set<String> filterAirlineList = new LinkedHashSet<>();
			List<CommonDsrTravelReportData> commonDsrTravelReports = new ArrayList<>();
			BigDecimal convenienceFee = new BigDecimal("0");
			BigDecimal managementFee = new BigDecimal("0");
			BigDecimal gstOrserviceTax = new BigDecimal("0");
			if (flightOrderRowList != null && flightOrderRowList.size() > 0) {
				for (FlightOrderRow flightOrderRow : flightOrderRowList) {
					if (flightOrderRow.getFlightOrderRowGstTax() != null && taxType != null && taxType.equalsIgnoreCase("GST")) {
						managementFee = flightOrderRow.getFlightOrderRowGstTax().getManagementFee() != null ? flightOrderRow.getFlightOrderRowGstTax().getManagementFee() : new BigDecimal(0);
								convenienceFee = flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee() != null ? flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee() : new BigDecimal(0);
								gstOrserviceTax=flightOrderRow.getGstOnFlights()!=null?	 flightOrderRow.getGstOnFlights(): new BigDecimal("0");
					
					} else {
						convenienceFee = flightOrderRow.getFlightOrderRowServiceTax() != null && flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee() != null ? flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee() : new BigDecimal(0);
								managementFee = flightOrderRow.getFlightOrderRowServiceTax() != null && flightOrderRow.getFlightOrderRowServiceTax().getManagementFee() != null ? flightOrderRow.getFlightOrderRowServiceTax().getManagementFee() : new BigDecimal(0);
								gstOrserviceTax=flightOrderRow.getServiceTax()!=null?	 flightOrderRow.getServiceTax(): new BigDecimal("0");
					}
					/*convenienceFee = ArithmeticUti.divideTo2Decimal(convenienceFee,
							new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
					managementFee = ArithmeticUti.divideTo2Decimal(managementFee,
							new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));*/
					
					BigDecimal netfare = flightOrderRow.getFinalPrice().add(managementFee).add(convenienceFee).add(gstOrserviceTax).setScale(2, RoundingMode.UP);
					if (flightOrderRow != null && flightOrderRow.getFlightOrderCustomers() != null
							&& flightOrderRow.getFlightOrderCustomers().size() > 0) {
						for (int j = 0; j < flightOrderRow.getFlightOrderCustomers().size(); j++) {
							CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
							FlightOrderCustomer flightOrderCustomer = flightOrderRow.getFlightOrderCustomers().get(j);
							commonDsrTravelReportData.setTraveller(flightOrderCustomer.getTitle() + " "
									+ flightOrderCustomer.getFirstName() + " " + flightOrderCustomer.getLastName());
							commonDsrTravelReportData.setAirlinePNRorProvBooking(flightOrderRow.getPnr());
							if (flightOrderRow.getGdsPnr() != null
									&& !flightOrderRow.getGdsPnr().trim().equalsIgnoreCase(""))
								commonDsrTravelReportData.setGDSPNR(flightOrderRow.getGdsPnr());
							else
								commonDsrTravelReportData.setGDSPNR("-");
							if (flightOrderRow.getOrderId() != null
									&& !flightOrderRow.getOrderId().trim().equalsIgnoreCase(""))
								commonDsrTravelReportData.setBkgRef(flightOrderRow.getOrderId());
							else
								commonDsrTravelReportData.setBkgRef("-");

							commonDsrTravelReportData.setBookingDate(
									DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getBookingDate()));
							commonDsrTravelReportData.setTripStartsDate(
									DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
							commonDsrTravelReportData.setTicketNumorFinalBooking(flightOrderCustomer.getEticketnumber());
							netfare=ArithmeticUti.divideTo2Decimal(netfare, new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));
							 commonDsrTravelReportData.setNetFare(netfare.setScale(2, BigDecimal.ROUND_UP).toString());
							if (flightOrderRow.getOrigin() != null && flightOrderRow.getDestination() != null) {
								StringBuilder tripCode = new StringBuilder();
								for (int i = 0; i < flightOrderRow.getFlightOrderTripDetails().size(); i++) {
									FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
									String srcCode = trips.getOriginCode() + "-";
									String descode = trips.getDestinationCode();
									String islastindexcode = "";
									if (i != flightOrderRow.getFlightOrderTripDetails().size() - 1) {
										islastindexcode = "-";
									}
									tripCode.append(srcCode + descode + islastindexcode);

								}

								commonDsrTravelReportData.setDescription(tripCode.toString());
								commonDsrTravelReportData.setSegmentNumber(
										Integer.toString(flightOrderRow.getFlightOrderTripDetails().size()));

								// filterAirlineList.add(tripCode.toString());
								filterAirlineList.add(flightOrderRow.getOrderId());
							}

							commonDsrTravelReports.add(commonDsrTravelReportData);
						}
					}

				}
				salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);
				salesReportCalSummary.setAirOrHotelSalesReportList(buildRequiredAirAdvencedPurchaseSalesFromSummaryMap(buildAirAdvencedPurchaseSalesSummary(filterAirlineList, flightOrderRowList,taxType)));

			}

			// TODO Auto-generated method stub
			return salesReportCalSummary;
		}

		// ADDED BY BASHA FOR Air Advanced Purchase Sales REPORT

		private Map<String, List<SalesReportCalSummary>> buildAirAdvencedPurchaseSalesSummary(Set<String> orderIdList,List<FlightOrderRow> flightOrderRowList,String taxType) {
			Map<String, List<SalesReportCalSummary>> summaryMap = new LinkedHashMap<>();
			if (orderIdList != null && orderIdList.size() > 0) {
				for (String orderId : orderIdList) {
					List<SalesReportCalSummary> list = new LinkedList<>();

					for (FlightOrderRow flightOrderRow : flightOrderRowList) {
						if (orderId.equalsIgnoreCase(flightOrderRow.getOrderId())) {
							SalesReportCalSummary salesReportCalSummary = new SalesReportCalSummary();
							salesReportCalSummary.setTotalTktCost(flightOrderRow.getPrice());
							salesReportCalSummary.setNoofSegmentsCount(flightOrderRow.getFlightOrderTripDetails().size());
							int diffOfDays = 0;
							SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
							SimpleDateFormat myPrefferFormat = new SimpleDateFormat("dd-MM-yyyy");
							String fromUserTravDate = flightOrderRow.getDepartureDate();
							String fromUserBookingDate = flightOrderRow.getBookingDate();
							try {
								Date strDateTravDate = format1.parse(fromUserTravDate);
								Date strDateBookingDate = format1.parse(fromUserBookingDate);
								diffOfDays = CommonUtil.getNoofStayDays(myPrefferFormat.format(strDateBookingDate),myPrefferFormat.format(strDateTravDate));
								// System.out.println("diffOfDays"+diffOfDays);
							} catch (ParseException e) {
								e.printStackTrace();

							}
							salesReportCalSummary.setNoofDaysCount(diffOfDays);
							BigDecimal gstOrServiceTax=new BigDecimal(0);
							BigDecimal managementFee=new BigDecimal(0);
							BigDecimal convFee=new BigDecimal(0);
							if (taxType.equalsIgnoreCase("Service")){
								if(flightOrderRow.getServiceTax()!=null && !flightOrderRow.getServiceTax().equals("")){
									managementFee=flightOrderRow.getFlightOrderRowServiceTax()!=null&&  flightOrderRow.getFlightOrderRowServiceTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getManagementFee():new BigDecimal(0);
									convFee=flightOrderRow.getFlightOrderRowServiceTax()!=null&&  flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee():new BigDecimal(0);
									gstOrServiceTax=flightOrderRow.getServiceTax();
								}
							}
								if (taxType.equalsIgnoreCase("GST")){
									if(flightOrderRow.getGstOnFlights()!=null && !flightOrderRow.getGstOnFlights().equals("")){
										managementFee=flightOrderRow.getFlightOrderRowGstTax()!=null&&  flightOrderRow.getFlightOrderRowGstTax().getManagementFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getManagementFee():new BigDecimal(0);
										convFee=flightOrderRow.getFlightOrderRowGstTax()!=null&&  flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee()!=null?flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee():new BigDecimal(0);
										gstOrServiceTax=flightOrderRow.getGstOnFlights();
									}
								}
								
							salesReportCalSummary.setInvoiceAmount(flightOrderRow.getFinalPrice().add(gstOrServiceTax).add(managementFee).add(convFee));
							salesReportCalSummary.setTotalTax(flightOrderRow.getTaxes().add(gstOrServiceTax).add(managementFee).add(convFee).add(flightOrderRow.getMarkUp()!=null?flightOrderRow.getMarkUp():new BigDecimal(0))) ;
							// if(taxType.equalsIgnoreCase("GST"))
							// salesReportCalSummary.setInvoiceAmount(flightOrderRow.getFinalPrice().add(flightOrderRow.getGstOnFlights()));
							// salesReportCalSummary.setTotalTax(flightOrderRow.getTaxes().add(flightOrderRow.getGstOnFlights()));
							salesReportCalSummary.setStatus(flightOrderRow.getStatusAction());
							list.add(salesReportCalSummary);
						}
					}
					summaryMap.put(orderId, list);
				}
			}
			return summaryMap;
		}


		// ADDED BY BASHA FOR Air Advanced Purchase Sales REPORT
		private List<SalesReportCalSummary> buildRequiredAirAdvencedPurchaseSalesFromSummaryMap(Map<String, List<SalesReportCalSummary>> buildAirAdvencedPurchaseSalesSummary) {
			Set<String> keySet = buildAirAdvencedPurchaseSalesSummary.keySet();
			List<SalesReportCalSummary> summaryTempList = new LinkedList<>();
			for (String key : keySet) {
				int totalCount = 0;
				BigDecimal totalAmount = new BigDecimal(0);
				BigDecimal avgTotalAmount = new BigDecimal(0);
				BigDecimal totaltktAmount = new BigDecimal(0);
				BigDecimal avgtktAmount = new BigDecimal(0);
				BigDecimal totaltaxes = new BigDecimal(0);
				int totaldayscount = 0;
				int totalsegment = 0;

				List<SalesReportCalSummary> listNew = buildAirAdvencedPurchaseSalesSummary.get(key);
				SalesReportCalSummary summaryTemp = new SalesReportCalSummary();
				for (SalesReportCalSummary sum : listNew) {

					totalAmount = totalAmount.add(sum.getInvoiceAmount());
					totaltktAmount = totaltktAmount.add(sum.getTotalTktCost());
					totaltaxes = totaltaxes.add(sum.getTotalTax());
					totaldayscount = sum.getNoofDaysCount();
					totalsegment = sum.getNoofSegmentsCount();
					totalCount++;

				}
				if (totalCount > 0) {
					avgTotalAmount = ArithmeticUti.divideTo2Decimal(totalAmount, new BigDecimal(totalCount));
					avgtktAmount = ArithmeticUti.divideTo2Decimal(totaltktAmount, new BigDecimal(totalCount));
				} else {
					avgTotalAmount = totalAmount;
					avgtktAmount = totaltktAmount;
				}
				summaryTemp.setNoofDaysCount(totaldayscount);
				summaryTemp.setNoofSegmentsCount(totalsegment);
				summaryTemp.setTotalTktCost(totaltktAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setTotalFare(totalAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setTotalTax(totaltaxes.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setAvgTktCost(avgtktAmount.setScale(2, BigDecimal.ROUND_UP));
				summaryTemp.setAvgTotalFare(avgTotalAmount.setScale(2, BigDecimal.ROUND_UP));

				summaryTempList.add(summaryTemp);
			}

			return summaryTempList;
		}

		private SalesReportCalSummary buildHotelAdvanceReportData(CommonDsrPage commonDsrPage,List<HotelOrderRow> hotelOrderRowList, SalesReportCalSummary salesReportCalSummary, String taxType) {
			List<CommonDsrTravelReportData> commonDsrTravelReports = new LinkedList<>();
			BigDecimal totalBasePriceAmount = new BigDecimal("0");
			BigDecimal basePriceInBooking = new BigDecimal("0");
			BigDecimal avgBasePrice = new BigDecimal("0");
			BigDecimal totInvoiceAmount = new BigDecimal("0");
			BigDecimal avgTotal = new BigDecimal("0");
			int noOfNightstotal = 0;
			int totalNightWithCancellation=0;
			int noOfRoomsBooked = 0;
			int cancelledCount = 0;
			int noOfDaysInAdvance=0;
			BigDecimal netFare = new BigDecimal("0");
			BigDecimal grossFare = null;
			BigDecimal otherTaxesWithMarkup = new BigDecimal("0");
			BigDecimal roomTaxesPriceInBooking = null;
			BigDecimal taxesPriceInBooking = null;
			BigDecimal roomMarkupInBooking = null;
			BigDecimal managementFeePerRoom = new BigDecimal("0");
			BigDecimal convenienceFeePerRoom = new BigDecimal("0");
			BigDecimal TotalServiceOrGstTax = new BigDecimal("0");
			BigDecimal totalTax = new BigDecimal("0");

			if (hotelOrderRowList != null && hotelOrderRowList.size() > 0) {
				for (HotelOrderRow hotelOrderRow : hotelOrderRowList) {
					CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
					String checkInDate=null;
					String checkOutDate=null;
					String name = hotelOrderRow.getHotelOrderHotelData().getName();
					String city = hotelOrderRow.getHotelOrderHotelData().getCity();
					String guestName = hotelOrderRow.getOrderCustomer().getTitle()+" "+
							hotelOrderRow.getOrderCustomer().getFirstName()+" "+hotelOrderRow.getOrderCustomer().getLastName();
					String status = hotelOrderRow.getStatusAction();
					if(hotelOrderRow.getCheckInDate() !=null)
						checkInDate = DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate());
					if(hotelOrderRow.getCheckOutDate() !=null)
						checkOutDate = DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate());
					int noOfNights = 0;
					int noOfNightsIfCancelled=0;

					if (checkInDate != null && checkOutDate != null) {
						try {
							noOfNights = CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
							noOfNightsIfCancelled = CommonUtil.getNoofStayDays(checkInDate, checkOutDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (hotelOrderRow.getTaxes() != null) {
						taxesPriceInBooking = hotelOrderRow.getTaxes()
								.multiply(hotelOrderRow.getApiToBaseExchangeRate())
								.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
						roomTaxesPriceInBooking = taxesPriceInBooking;
					} else
						roomTaxesPriceInBooking = new BigDecimal("0");

					if (hotelOrderRow.getMarkupAmount() != null)
						roomMarkupInBooking = hotelOrderRow.getMarkupAmount();
					else
						roomMarkupInBooking = new BigDecimal("0");

					otherTaxesWithMarkup = roomTaxesPriceInBooking.setScale(2, BigDecimal.ROUND_UP)
							.add(roomMarkupInBooking).setScale(2, BigDecimal.ROUND_UP);


					if (hotelOrderRow.getApiPrice() != null && hotelOrderRow.getTaxes() != null) {
						basePriceInBooking = hotelOrderRow.getApiPrice().subtract(hotelOrderRow.getTaxes())
								.multiply(hotelOrderRow.getApiToBaseExchangeRate())
								.multiply(hotelOrderRow.getBaseToBookingExchangeRate());
						//	roomBasePriceInBooking = basePriceInBooking;

					} 
					totalBasePriceAmount=totalBasePriceAmount.add(basePriceInBooking).setScale(2, RoundingMode.HALF_UP);
					grossFare = basePriceInBooking.add(otherTaxesWithMarkup);


					if (taxType.equalsIgnoreCase("Service")) {
						if (hotelOrderRow.getHotelOrderRowServiceTax() != null
								&& hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee() != null)
							managementFeePerRoom = hotelOrderRow.getHotelOrderRowServiceTax().getManagementFee();

						else
							managementFeePerRoom = new BigDecimal("0");

						if (hotelOrderRow.getHotelOrderRowServiceTax() != null
								&& hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee() != null)
							convenienceFeePerRoom = hotelOrderRow.getHotelOrderRowServiceTax().getConvenienceFee();

						else
							convenienceFeePerRoom = new BigDecimal("0");

						if (hotelOrderRow.getHotelOrderRowServiceTax() != null
								&& hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax() != null)
							TotalServiceOrGstTax=hotelOrderRow.getHotelOrderRowServiceTax().getTotalTax();
					}
					if (taxType.equalsIgnoreCase("GST")) {
						if (hotelOrderRow.getHotelOrderRowGstTax() != null
								&& hotelOrderRow.getHotelOrderRowGstTax().getManagementFee() != null)
							managementFeePerRoom = hotelOrderRow.getHotelOrderRowGstTax().getManagementFee();
						else
							managementFeePerRoom = new BigDecimal("0");
						if (hotelOrderRow.getHotelOrderRowGstTax() != null
								&& hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee() != null)
							convenienceFeePerRoom = hotelOrderRow.getHotelOrderRowGstTax().getConvenienceFee();

						else
							convenienceFeePerRoom = new BigDecimal("0");

						if (hotelOrderRow.getHotelOrderRowGstTax() != null
								&& hotelOrderRow.getHotelOrderRowGstTax().getTotalGst() != null)
							TotalServiceOrGstTax=hotelOrderRow.getHotelOrderRowGstTax().getTotalGst();


					}
					totalTax=totalTax.add(TotalServiceOrGstTax).add(managementFeePerRoom).add(convenienceFeePerRoom).setScale(2, RoundingMode.HALF_UP);
					netFare = grossFare.add(TotalServiceOrGstTax).add(managementFeePerRoom).add(convenienceFeePerRoom);

					totInvoiceAmount = totInvoiceAmount.add(netFare).setScale(2, RoundingMode.HALF_UP);

					totalNightWithCancellation=totalNightWithCancellation+noOfNights;
					noOfNightstotal = noOfNightstotal + noOfNights;
					noOfRoomsBooked = noOfRoomsBooked + hotelOrderRow.getNoOfRooms();
					if (hotelOrderRow.getStatusAction().equalsIgnoreCase("Cancelled")) {
						cancelledCount++;
						noOfNightstotal = noOfNightstotal-noOfNightsIfCancelled;
					}

					try {
						noOfDaysInAdvance = noOfDaysInAdvance + CommonUtil.getNoofStayDays(DateConversion.getBluestarDateddMMyyyy(
								hotelOrderRow.getBookingDate() != null ? hotelOrderRow.getBookingDate() : "-"),checkInDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
					}


					commonDsrTravelReportData.setSystemInvoiceId(hotelOrderRow.getOrderReference());
					commonDsrTravelReportData.setTripStartsDate(checkInDate);
					commonDsrTravelReportData.setTripEndDate(checkOutDate);
					commonDsrTravelReportData.setBookingDate(DateConversion.getBluestarDateddMMyyyy(
							hotelOrderRow.getBookingDate() != null ? hotelOrderRow.getBookingDate() : "-"));
					if (hotelOrderRow.getOrderCustomer() != null)
						commonDsrTravelReportData.setBookerName(hotelOrderRow.getOrderCustomer().getTitle() + ""+ hotelOrderRow.getOrderCustomer().getFirstName() + ""+ hotelOrderRow.getOrderCustomer().getLastName());
					if (hotelOrderRow.getHotelOrderHotelData() != null){
						commonDsrTravelReportData.setHotelName(hotelOrderRow.getHotelOrderHotelData().getName());
						commonDsrTravelReportData.setCity(hotelOrderRow.getHotelOrderHotelData().getCity());
					}
					commonDsrTravelReportData.setNetFare(netFare.setScale(2, RoundingMode.HALF_UP).toString());

					commonDsrTravelReports.add(commonDsrTravelReportData);
				}
			}

			totInvoiceAmount = totInvoiceAmount.setScale(2, RoundingMode.HALF_UP);
			BigDecimal divider1 = ArithmeticUti.multiplyTo2Decimal(new BigDecimal(noOfNightstotal),
					new BigDecimal(noOfRoomsBooked));
			String avgprice1 = totInvoiceAmount.divide(divider1, 2, RoundingMode.HALF_UP).toPlainString();
			avgTotal = new BigDecimal(avgprice1).setScale(2, RoundingMode.HALF_UP);
			avgTotal = avgTotal.setScale(2, RoundingMode.HALF_UP);

			System.out.println("avgTotal===="+avgTotal);

			totalBasePriceAmount = totalBasePriceAmount.setScale(2, RoundingMode.HALF_UP);
			BigDecimal divider = ArithmeticUti.multiplyTo2Decimal(new BigDecimal(noOfNightstotal),
					new BigDecimal(noOfRoomsBooked));
			String avgprice = totalBasePriceAmount.divide(divider, 2, RoundingMode.HALF_UP).toPlainString();
			avgBasePrice = new BigDecimal(avgprice).setScale(2, RoundingMode.HALF_UP);
			avgBasePrice = avgBasePrice.setScale(2, RoundingMode.HALF_UP);

			System.out.println("avgBasePrice===="+avgBasePrice);

			salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);

			salesReportCalSummary = buildHotelAdvanceSalesSummary(totInvoiceAmount, noOfDaysInAdvance, noOfRoomsBooked,totalNightWithCancellation, basePriceInBooking, totalTax, avgTotal, avgBasePrice, commonDsrTravelReports,salesReportCalSummary);
			return salesReportCalSummary;
		}

		public SalesReportCalSummary buildHotelAdvanceSalesSummary(BigDecimal totInvoiceAmount, int noOfDaysInAdvance,int noOfRoomsBooked, int noOfNights, BigDecimal basePriceInBooking, BigDecimal taxes, BigDecimal avgTotal,
				BigDecimal avgBasePrice, List<CommonDsrTravelReportData> commonDsrTravelReports,
				SalesReportCalSummary salesReportCalSummary) {
			salesReportCalSummary.setNoofDaysInAdvance(noOfDaysInAdvance);
			salesReportCalSummary.setNoOfRooms(noOfRoomsBooked);
			salesReportCalSummary.setNoofBookedNights(noOfNights);
			salesReportCalSummary.setBaseFare(basePriceInBooking.setScale(2, BigDecimal.ROUND_UP));
			salesReportCalSummary.setTaxes(taxes.setScale(2, BigDecimal.ROUND_UP));
			totInvoiceAmount = totInvoiceAmount.setScale(2, RoundingMode.HALF_UP);
			salesReportCalSummary.setTotalFare(totInvoiceAmount);
			salesReportCalSummary.setAvgTotalRoomNightCost(avgTotal.setScale(2, BigDecimal.ROUND_UP));
			salesReportCalSummary.setAvgBaseRoomNightCost(avgBasePrice.setScale(2, BigDecimal.ROUND_UP));
			salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);
			return salesReportCalSummary;
		}

		private SalesReportCalSummary buildPlannedAirTripReportSalesReportData(CommonDsrPage commonDsrPage,
				List<FlightOrderRow> flightOrderRowList, SalesReportCalSummary salesReportCalSummary, String taxType) {
			List<CommonDsrTravelReportData> commonDsrTravelReports = new ArrayList<>();
			BigDecimal basePriceInBooking = new BigDecimal("0");
			if (flightOrderRowList != null && flightOrderRowList.size() > 0) {
				for (FlightOrderRow flightOrderRow : flightOrderRowList) {

					if (flightOrderRow != null && flightOrderRow.getFlightOrderCustomers() != null
							&& flightOrderRow.getFlightOrderCustomers().size() > 0) {
						for (int j = 0; j < flightOrderRow.getFlightOrderCustomers().size(); j++) {
							CommonDsrTravelReportData commonDsrTravelReportData = new CommonDsrTravelReportData();
							FlightOrderCustomer flightOrderCustomer = flightOrderRow.getFlightOrderCustomers().get(j);
							BigDecimal taxesInBooking = new BigDecimal(0);
							BigDecimal markupInBooking = new BigDecimal(0);
							BigDecimal grossFare = new BigDecimal(0);
							BigDecimal totExtraChargeInBooking = new BigDecimal(0);
							FlightOrderCustomerPriceBreakup flightOrderCustomerPriceBreakup = flightOrderRow
									.getFlightOrderCustomerPriceBreakups().get(j);
							BigDecimal extraBaggagePrice = ArithmeticUti.divideTo2Decimal(
									flightOrderRow.getExtrabaggageprice() != null ? flightOrderRow.getExtrabaggageprice()
											: new BigDecimal(0),
											new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));// flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice().divide(new
							BigDecimal extraMealPrice = ArithmeticUti.divideTo2Decimal(
									flightOrderRow.getExtramealprice() != null ? flightOrderRow.getExtramealprice()
											: new BigDecimal(0),
											new BigDecimal(flightOrderRow.getFlightOrderCustomers().size()));// flightOrderRow.getExtrabaggageprice()!=null?flightOrderRow.getExtrabaggageprice().divide(new
							BigDecimal otherTaxesinculdetdscharges = new BigDecimal("0");
							if (flightOrderCustomerPriceBreakup.getTax() != null) {
								BigDecimal tax = flightOrderCustomerPriceBreakup.getTax()
										.multiply(flightOrderRow.getApiToBaseExchangeRate());
								taxesInBooking = tax.multiply(flightOrderRow.getBaseToBookingExchangeRate());
							} else
								taxesInBooking = new BigDecimal("0");
							otherTaxesinculdetdscharges = flightOrderRow.getSupplierTds() != null ? flightOrderRow
									.getSupplierTds()
									.divide(new BigDecimal(flightOrderRow.getFlightOrderCustomerPriceBreakups().size()))
									.setScale(2) : new BigDecimal(0);
									totExtraChargeInBooking = extraBaggagePrice.add(extraMealPrice);
									commonDsrTravelReportData.setOrderId(flightOrderRow.getOrderId());
									commonDsrTravelReportData.setSystemInvoiceId(flightOrderRow.getInvoiceNo());
									commonDsrTravelReportData.setStatus(flightOrderRow.getStatusAction());
									commonDsrTravelReportData.setTripStartsDate(
											DateConversion.getBluestarDateddMMyyyy(flightOrderRow.getDepartureDate()));
									StringBuilder descode = new StringBuilder();
									StringBuilder srcCode = new StringBuilder();
									StringBuilder flightnumber = new StringBuilder();
									for (int i = 0; i < flightOrderRow.getFlightOrderTripDetails().size(); i++) {
										FlightOrderTripDetail trips = flightOrderRow.getFlightOrderTripDetails().get(i);
										if (i == flightOrderRow.getFlightOrderTripDetails().size() - 1) {
											descode.append(trips.getDestinationCode());
											srcCode.append(trips.getOriginCode());
											flightnumber.append(trips.getFlightNumber());
										} else {
											descode.append(trips.getDestinationCode() + "-");
											srcCode.append(trips.getOriginCode() + "-");
											flightnumber.append("-");
										}
										commonDsrTravelReportData.setProductCode(trips.getOperatedByCode() != null
												? trips.getOperatedByCode()
														: "NA" + "-" + trips.getFlightNumber() != null ? trips.getFlightNumber() : "NA");
										commonDsrTravelReportData.setCabinClass(trips.getClassOfService());
										commonDsrTravelReportData.setBookingClass(trips.getFareClass());
										commonDsrTravelReportData.setFareBasis(trips.getFareBasisCode());
										commonDsrTravelReportData.setFareType(trips.getCraft());
										commonDsrTravelReportData.setDepTime(trips.getDepartureTime().toString());
										commonDsrTravelReportData.setArrTime(trips.getArrivalTime().toString())  ;
									}
									Company company = new CompanyDAO()
											.getCompanyProfile(Integer.parseInt(flightOrderRow.getCompanyId()));
									Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
									commonDsrTravelReportData.setDescription(srcCode + "/" + descode);
									commonDsrTravelReportData.setTraveller(flightOrderCustomer.getTitle() + " "
											+ flightOrderCustomer.getFirstName() + " " + flightOrderCustomer.getLastName());
									commonDsrTravelReportData.setBandCode("-");
									
									//ADDED BY BASHA FOR RM CONFIG ON 24-10-2017
									
									RmConfigTripDetailsModel rmConfigTripDetails =null;
									if(flightOrderCustomer.getRmConfigTripDetailsModel()!=null) 
										rmConfigTripDetails =dsrRmConfigHelperDao.getFlightPaxRmConfigTripDetails(flightOrderCustomer.getRmConfigTripDetailsModel(),flightOrderRow.getFlightOrderRowRmConfigStruct().getRmDynamicData());
									else
										rmConfigTripDetails =dsrRmConfigHelperDao.getRmConfigTripDetails(flightOrderRow.getOrderId(),company.getCompanyid());
									
									if(rmConfigTripDetails!=null)
									{
										if(sessionCompany.getCompanyRole().isSuperUser()){
											if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("F") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
												commonDsrTravelReportData.setExtraRmConfigDetails(rmConfigTripDetails.getExtraRmConfigDetails());
											else 
												commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
									}
									else 
										commonDsrTravelReportData.setRmConfigMap(rmConfigTripDetails.getRmconfigFieldsMap());
									
									buildRmConifigData(rmConfigTripDetails, commonDsrTravelReportData);
									}
									
									//ENDED BY BASHA FOR RM CONFIG ON 24-10-2017
									
									
									
									
									commonDsrTravelReportData.setFlightNumber(flightnumber.toString());
									commonDsrTravelReportData.setGDSPNR(flightOrderRow.getGdsPnr());
									commonDsrTravelReportData.setAirlinePnr(flightOrderRow.getPnr());
									commonDsrTravelReportData.setTicketNumorFinalBooking(flightOrderCustomer.getEticketnumber());
									if (flightOrderCustomerPriceBreakup.getBaseFare() != null) {
										BigDecimal basePrice = flightOrderCustomerPriceBreakup.getBaseFare()
												.multiply(flightOrderRow.getApiToBaseExchangeRate());
										basePriceInBooking = basePrice.multiply(flightOrderRow.getBaseToBookingExchangeRate());
									} else
										basePriceInBooking = new BigDecimal("0");
									BigDecimal allGst=new BigDecimal("0");
									if(flightOrderRow.getFlightOrderRowGstTax()!=null)
										allGst=flightOrderRow.getFlightOrderRowGstTax().getConvenienceFee().add(flightOrderRow.getFlightOrderRowGstTax().getManagementFee()).add(flightOrderRow.getFlightOrderRowGstTax().getTotalGst());
									else if(flightOrderRow.getFlightOrderRowServiceTax()!=null)
										allGst=flightOrderRow.getFlightOrderRowServiceTax().getConvenienceFee().add(flightOrderRow.getFlightOrderRowServiceTax().getManagementFee())
										.add(flightOrderRow.getFlightOrderRowServiceTax().getTotalTax());

									grossFare = flightOrderRow.getFinalPrice().add(markupInBooking).add(otherTaxesinculdetdscharges).add(allGst.setScale(2, BigDecimal.ROUND_UP));
									commonDsrTravelReportData.setGrossFare(grossFare.setScale(2, BigDecimal.ROUND_UP).toString());

									commonDsrTravelReportData
									.setTravelerEmail(flightOrderRow.getFlightCustomer().getEmail() != null
									? flightOrderRow.getFlightCustomer().getEmail() : "-");
									commonDsrTravelReportData
									.setTravelerPhone(flightOrderRow.getFlightCustomer().getPhone() != null
									? flightOrderRow.getFlightCustomer().getPhone() : "-");


									commonDsrTravelReportData.setMobile(flightOrderRow.getFlightCustomer().getMobile()!= null
											? flightOrderRow.getFlightCustomer().getMobile() : "-");
									commonDsrTravelReports.add(commonDsrTravelReportData);
						}
					}
					salesReportCalSummary.setCommonDsrTravelReports(commonDsrTravelReports);
					if (flightOrderRow != null && flightOrderRow.getFlightOrderCustomers() != null
							&& flightOrderRow.getFlightOrderCustomers().size() > 0) {
					}
				}
			}
			return salesReportCalSummary;
		}
		
		
		public void buildRmConifigData(RmConfigTripDetailsModel rmConfigTripDetails,CommonDsrTravelReportData commonDsrTravelReportData){
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
		public  BigDecimal  getRefundAmount(String travelType,Object orderRowObj){
			BigDecimal refundAmount=null;
			switch (travelType) {
			case "F":
				FlightOrderRow flightOrderRow= (FlightOrderRow) orderRowObj;
				if(flightOrderRow.isCreditNoteIssued()){
					List<CreditNote> creditNoteList=dsrCreditNoteDaoHelper.getFlightCreditNoteDetails(flightOrderRow);
					if(creditNoteList!=null && creditNoteList.size()>0){
						for(CreditNote creditNote:creditNoteList){
							if(creditNote.getCompanyId().equals(flightOrderRow.getCompanyId())){
								refundAmount=creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate();
							}
						}
					}
				}
				break;
			case "H":
				HotelOrderRow hotelOrderRow= (HotelOrderRow) orderRowObj;
				if(hotelOrderRow.isCreditNoteIssued()){
					List<HotelCreditNote> hotelCreditNoteList=dsrCreditNoteDaoHelper.getHotelCreditNoteDetails(hotelOrderRow);
					if(hotelCreditNoteList!=null && hotelCreditNoteList.size()>0){
						for(HotelCreditNote creditNote:hotelCreditNoteList){
							if(creditNote.getCompanyId().equals(hotelOrderRow.getCompanyId())){
								refundAmount=creditNote.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP).negate();
							}
						}
					}
				}
				break;
			 
			default:
				break;
			}
			return refundAmount;

		}
		public void sortDataByStringDate(List<CommonDsrTravelReportData> commonDsrTravelReports){
			Collections.sort(commonDsrTravelReports, new Comparator<CommonDsrTravelReportData>() {
				DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
				@Override
				public int compare(CommonDsrTravelReportData o1, CommonDsrTravelReportData o2) {
					// TODO Auto-generated method stub
					 try {
			                return f.parse(o1.getBookingDate()).compareTo(f.parse(o2.getBookingDate()));
			            } catch (ParseException e) {
			                throw new IllegalArgumentException(e);
			            }
				}
		        
		    });
	}
}
