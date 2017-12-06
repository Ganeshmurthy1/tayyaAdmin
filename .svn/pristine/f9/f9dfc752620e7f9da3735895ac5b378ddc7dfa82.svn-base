package com.lintas.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.admin.common.commonDsrReportConfg.CommonDsrReportConfg;
import com.admin.flight.fin.sheet.model.HotelandFlightDisReportProperty;
import com.common.dsr.CommonDsrMissedFlightReport;
import com.common.dsr.CommonDsrPage;
import com.common.dsr.CommonDsrTravelReportData;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;

import freemarker.template.utility.DateUtil.DateParseException;

public class CommonDsrTravelReportsExportToExcel {
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CommonDsrTravelReportsExportToExcel.class);

	public static boolean generateCommonTravelReportExcel(List<CommonDsrTravelReportData> list, String path,
			Company companyObj,CommonDsrPage commonDsrPage)  {
		List<String> dates=null;
		String gstDateStart="2017-06-30";
		//String gstDateStartForDate="30-06-2017";
		String fromDateNew=commonDsrPage.getCommonDsrFilters().getFromDate();
		String toDateNew=commonDsrPage.getCommonDsrFilters().getToDate();
		//added by basha
		//DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		//String dateToday = df.format(new Date());
		/*if(!fromDateNew.trim().equalsIgnoreCase("") && !toDateNew.trim().equalsIgnoreCase("")){
			dates = DateConversion.getDaysBetweenDates(fromDateNew,toDateNew);
		}else{
			 dates = DateConversion.getDaysBetweenDates(gstDateStartForDate,dateToday);
		}*/
		 
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
		DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date gstStartDateObj=null;
		Date fromDate=null;
		Date toDate=null;
		try {
			gstStartDateObj = targetFormat.parse(gstDateStart);
			if(!fromDateNew.equalsIgnoreCase("")){
				fromDate = originalFormat.parse(fromDateNew);
				String formattedFromDate = targetFormat.format(fromDate); 
				fromDate = targetFormat.parse(formattedFromDate);
			}
			if(!toDateNew.equalsIgnoreCase("")){
				toDate = originalFormat.parse(toDateNew);
				String formattedToDate = targetFormat.format(toDate); 
				toDate = targetFormat.parse(formattedToDate);
			}
			

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean isGenerated = false;
		try {
			path = path + "excel" + File.separator + "CommonDSRReport.xls";
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			logger.info("Exception " + e);
		}
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HHmmssSSS");
		// get current date time with Date()
		Date date = new Date();
		String pdfCreateDate = dateFormat.format(date);
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Common DSR Report Info");
		// Create row object
		XSSFRow row;
		// This data needs to be written (Object[])
		// Map<String, Object[]> flightReportInfoMap = new TreeMap<String,
		// Object[]>();
		SortedMap<Integer, List<Object>> flightReportInfoMap = new TreeMap<>();
		CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(companyObj.getCompanyid());

		int i = 1;
		List<Object> headerList = new LinkedList<>();
		headerList.add("Booking Reference");
		headerList.add("System Invoice Id");
		headerList.add("Booking/Billing Type");
		headerList.add("Amendment Type");
		headerList.add("Invoice date");
		headerList.add("Booking Date");
		headerList.add("Corporate Name");
		headerList.add("Billing Entity");
		headerList.add("Booker Name");
		headerList.add("Bookers Login Id");
		//companyObj=!commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")?new CompanyDAO().CompanyLogin(commonDsrPage.getCommonDsrFilters().getCompanyUserId()):commonDsrPage.getCommonDsrFilters().getLoginCompany();
		// these headers on need to display super User
		if (companyObj.getCompanyRole().isSuperUser()) {
			headerList.add("Supplier Code");
			headerList.add("Supplier Name");
			headerList.add("Client Code");
			headerList.add("Supplier Charge");
		}

		// end

		headerList.add("Product Type");
		headerList.add("Itinerary Type");
		headerList.add("Product Name");
		headerList.add("Product Code");
		headerList.add("Description");
		headerList.add("Description 2");
		headerList.add("Airline PNR/Prov Booking");
		headerList.add("GDS PNR");
		headerList.add("Ticket Num/Final Booking");
		headerList.add("Fare Type");
		headerList.add("Booking Refund Type");
		headerList.add("Fare Basis");
		headerList.add("Cabin Class");
		headerList.add("Booking Class");
		headerList.add("Pax Type");
		headerList.add("Traveller Name");
		headerList.add("Total Nights");
		headerList.add("Trip Starts Date");
		headerList.add("Trip End Date");
		headerList.add("Journey Type");
		headerList.add("Base Fare");
		headerList.add("YQ Tax");
		headerList.add("YR Tax");
		headerList.add("K3 Tax");
		headerList.add("PSF Tax");
		headerList.add("UDF Tax");
		headerList.add("JN Tax");
		headerList.add("IN Tax");
		headerList.add("OC Tax");
		headerList.add("Other Taxes");
		headerList.add("Vfs Charges");
		headerList.add("Courier Charges");
		headerList.add("Extra Km Charge");
		headerList.add("Driver Allowance Day Charge");
		headerList.add("Driver Allowance Night Charge");
		headerList.add("Toll Or Parking Charge");
		headerList.add("Extra Hour Charge");
		headerList.add("Extra Charge(Meal/Baggage Etc.)");
		headerList.add("Supplier Amendment/Cancellation Fee");
		headerList.add("Gross Fare");
		//if(newCompanyConfig!=null && newCompanyConfig.getTaxtype()!=null && newCompanyConfig.getTaxtype().equalsIgnoreCase("GST")){
		if(fromDate!=null && toDate!=null){
			if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
			{
				headerList.add("CGST");
				headerList.add("SGST/IGST/UGST");
				headerList.add("Gst Tax");
			}

			else{
				headerList.add("Service Tax Base");
				headerList.add("Swach Bharat Cess");
				headerList.add("Krishi Kalyan Cess");
				headerList.add("Service Tax");
			}

		}
		else{
			headerList.add("CGST");
			headerList.add("SGST/IGST/UGST");
			headerList.add("Gst Tax");
		}	


		//}

		headerList.add("Tayyarah Charges");
		headerList.add("Convenience Charge");
		headerList.add("Discount");
		headerList.add("Net Fare");

		// these headers on need to display super User
		if (companyObj.getCompanyRole().isSuperUser())
			headerList.add("Total Markup");

		// end
		headerList.add("Mode of Payment");
		headerList.add("Travel Status");
		headerList.add("Personal Booking");
		headerList.add("Corporate Currency");
		headerList.add("Approver Name");
		if(companyObj.getCompanyRole().isSuperUser()){

			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("All") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList=getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=getExcelHeadings(list, headerList,"K");


		headerList.add("Bill NoBill");
		headerList.add("Business Unit");
		headerList.add("Cost Center");
		headerList.add("Emp Code");
		headerList.add("Travel Request Number");
		headerList.add("Location");
		headerList.add("Department");
		headerList.add("Project Code");
		headerList.add("Reason for Travel");
		flightReportInfoMap.put(i, headerList);

		if (list != null && list.size() > 0) {
			for (int j=0;j<list.size();j++) {
				CommonDsrTravelReportData reportData=list.get(j);
				i++;
				List<Object> dataList = new LinkedList<>();
				/*if(dates.contains(reportData.getInvoicedate()) || dates.contains(reportData.getInvoicedate())){*/
				dataList.add(reportData.getBkgRef()!=null && !reportData.getBkgRef().equals("")?reportData.getBkgRef():"-");
				dataList.add(reportData.getSystemInvoiceId()!=null && !reportData.getSystemInvoiceId().equals("")?reportData.getSystemInvoiceId():"-");
				dataList.add(reportData.getBookingType()!=null && !reportData.getBookingType().equals("")?reportData.getBookingType():"-");
				dataList.add(reportData.getAmendmentType()!=null && !reportData.getAmendmentType().equals("")?reportData.getAmendmentType():"-");
				dataList.add(reportData.getInvoicedate()!=null && !reportData.getInvoicedate().equals("")?reportData.getInvoicedate():"-");
				dataList.add(reportData.getBookingDate()!=null && !reportData.getBookingDate().equals("")?reportData.getBookingDate():"-");
				dataList.add(reportData.getCorporateName()!=null && !reportData.getCorporateName().equals("")?reportData.getCorporateName():"-");
				dataList.add(reportData.getBillingEntity()!=null && !reportData.getBillingEntity().equals("")?reportData.getBillingEntity():"-");
				dataList.add(reportData.getBookerName()!=null && !reportData.getBookerName().equals("")?reportData.getBookerName():"-");
				dataList.add(reportData.getBookersLoginId()!=null && !reportData.getBookersLoginId().equals("")?reportData.getBookersLoginId():"-");
				if (companyObj.getCompanyRole().isSuperUser()) {
					dataList.add(reportData.getSupplierCode());
					dataList.add(reportData.getSupplierName());
					dataList.add(reportData.getClientCode());
					dataList.add(reportData.getSupplierCharge());
				}

				dataList.add(reportData.getProductType()!=null && !reportData.getProductType().equals("")?reportData.getProductType():"-");
				dataList.add(reportData.getItineraryType());
				dataList.add(reportData.getProductName());
				dataList.add(reportData.getProductCode()!=null && !reportData.getProductCode().equals("")?reportData.getProductCode():"-");
				dataList.add(reportData.getDescription()!=null && !reportData.getDescription().equals("")?reportData.getDescription():"-");
				dataList.add(reportData.getDescription2());
				dataList.add(reportData.getAirlinePNRorProvBooking()!=null && !reportData.getAirlinePNRorProvBooking().equals("")?reportData.getAirlinePNRorProvBooking():"-");
				dataList.add(reportData.getGDSPNR());
				dataList.add(reportData.getTicketNumorFinalBooking()!=null && !reportData.getTicketNumorFinalBooking().equals("")?reportData.getTicketNumorFinalBooking():"-");
				dataList.add(reportData.getFareType()!=null && !reportData.getFareType().equals("")?reportData.getFareType():"-");
				dataList.add(reportData.getBookingRefundType()!=null && !reportData.getBookingRefundType().equals("")?reportData.getBookingRefundType():"-");
				dataList.add(reportData.getFareBasis()!=null && !reportData.getFareBasis().equals("")?reportData.getFareBasis():"-");
				dataList.add(reportData.getCabinClass()!=null && !reportData.getCabinClass().equals("")?reportData.getCabinClass():"-");
				dataList.add(reportData.getBookingClass()!=null && !reportData.getBookingClass().equals("")?reportData.getBookingClass():"-");
				dataList.add(reportData.getPaxType()!=null && !reportData.getPaxType().equals("")?reportData.getPaxType():"-");
				dataList.add(reportData.getTraveller()!=null && !reportData.getTraveller().equals("")?reportData.getTraveller():"-");
				dataList.add(reportData.getTotalNights());
				dataList.add(reportData.getTripStartsDate()!=null && !reportData.getTripStartsDate().equals("")?reportData.getTripStartsDate():"-");
				dataList.add(reportData.getTripEndDate()!=null && !reportData.getTripEndDate().equals("")?reportData.getTripEndDate():"-");
				dataList.add(reportData.getJourneyType());
				dataList.add(reportData.getBaseFare());
				dataList.add(reportData.getYQTax());
				dataList.add(reportData.getYRTax());
				dataList.add(reportData.getK3Tax());
				dataList.add(reportData.getPSFTax());
				dataList.add(reportData.getUDFTax());
				dataList.add(reportData.getJNTax());
				dataList.add(reportData.getINTax());
				dataList.add(reportData.getOCTax());
				dataList.add(reportData.getOtherTaxes());
				dataList.add(reportData.getVfsCharges());
				dataList.add(reportData.getCourierCharges());
				dataList.add(reportData.getExtraKmCharge());
				dataList.add(reportData.getDriverAllowancedayCharge());
				dataList.add(reportData.getDriverAllowanceNightCharge());
				dataList.add(reportData.getTollorParkingCharge());
				dataList.add(reportData.getExtraHourCharge());
				dataList.add(reportData.getExtraCharge());
				dataList.add(reportData.getSupplierAmendmentOrCancellationFee());
				dataList.add(reportData.getGrossFare());

				if(reportData.getTaxType()!=null && reportData.getTaxType().equalsIgnoreCase("GST")){
					dataList.add(reportData.getCGST());
					dataList.add(reportData.getSGSTorUGSTorIGST());
					dataList.add(reportData.getTotGstTax());
				}
				else{

					dataList.add(reportData.getServiceTaxBase());
					dataList.add(reportData.getSwachBharatCess());
					dataList.add(reportData.getKrishiKalyanCess());
					dataList.add(reportData.getServiceTax());
				} 
				dataList.add(reportData.getTayyarahServiceCharges());
				dataList.add(reportData.getConvenienceCharge());
				dataList.add(reportData.getDiscount());
				dataList.add(reportData.getNetFare());
				if (companyObj.getCompanyRole().isSuperUser())
					dataList.add(reportData.getMarkup());

				dataList.add(reportData.getModeOfPayment()!=null && !reportData.getModeOfPayment().equals("")?reportData.getModeOfPayment():"-");
				dataList.add(reportData.getTravelStatus());
				dataList.add(reportData.getPersonalBooking());
				dataList.add(reportData.getCorporateCurrency());
				dataList.add(reportData.getApproverName()!=null && !reportData.getApproverName().equals("")?reportData.getApproverName():"-");
				if(companyObj.getCompanyRole().isSuperUser()){
					if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("All") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
						dataList.add(reportData.getExtraRmConfigDetails());
					else{
						dataList=getExcelData(list.get(j),dataList);
					}
				}
				else 
					dataList= getExcelData(list.get(j),dataList);

				dataList.add(reportData.getBillNonBill()!=null && !reportData.getBillNonBill().equals("")?reportData.getBillNonBill():"-");
				dataList.add(reportData.getBusinessUnit()!=null&& !reportData.getBusinessUnit().equals("")?reportData.getBusinessUnit():"-");
				dataList.add(reportData.getCostCenter()!=null&& !reportData.getCostCenter().equals("")?reportData.getCostCenter():"-");
				dataList.add(reportData.getEmpCode()!=null&& !reportData.getEmpCode().equals("")?reportData.getEmpCode():"-");
				dataList.add(reportData.getTravelRequestNumber()!=null&& !reportData.getTravelRequestNumber().equals("")?reportData.getTravelRequestNumber():"-");
				dataList.add(reportData.getLocation()!=null&& !reportData.getLocation().equals("")?reportData.getLocation():"-");
				dataList.add(reportData.getDepartment()!=null&& !reportData.getDepartment().equals("")?reportData.getDepartment():"-");
				dataList.add(reportData.getProjectCode()!=null&& !reportData.getProjectCode().equals("")?reportData.getProjectCode():"-");
				dataList.add(reportData.getReasonForTravel()!=null && !reportData.getReasonForTravel().equals("")?reportData.getReasonForTravel():"-");
				flightReportInfoMap.put(i, dataList);
			/*}*/
			}

		} /*else {
			List<Object> dataList = new LinkedList<>();
			dataList.add("-------------------------NO DATA AVAILABLE-------------------------");
			flightReportInfoMap.put(0, dataList);
		}*/

		// Iterate over data and write to sheet
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				workbook.getSheetAt(0).autoSizeColumn(rowid);
				row = spreadsheet.createRow(rowid++);

				if (row.getRowNum() == 0) {
					row.setHeight((short) 500);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short) 400);
				}

				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(0).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					//workbook.getSheetAt(0).autoSizeColumn(0);
					//workbook.getSheetAt(0).autoSizeColumn(cellid);
					workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.GOLD.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}
			isGenerated=true; 
		}

		// Write the workbook in file system
		FileOutputStream out;
		try {
			// out = new FileOutputStream(new
			// File("D://excel//"+pdfCreateDate+"_"+"flight_report"+".xlsx"));
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated=true; 
			// logger.info("Writesheet.xlsx written successfully" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated=false; 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated=false; 
		}
		return isGenerated;
	}


	public static boolean generateCustomDsrTravelReportExcel(List<CommonDsrTravelReportData> list, String path,
			Company companyObj,CommonDsrPage commonDsrPage,CommonDsrReportConfg commonDsrReportConfg) {
		String gstDateStart="2017-06-30";
		String fromDateNew=commonDsrPage.getCommonDsrFilters().getFromDate();
		String toDateNew=commonDsrPage.getCommonDsrFilters().getToDate();
		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
		DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date gstStartDateObj=null;
		Date fromDate=null;
		Date toDate=null;
		try {
			gstStartDateObj = targetFormat.parse(gstDateStart);
			if(!fromDateNew.equalsIgnoreCase("")){
				fromDate = originalFormat.parse(fromDateNew);
				String formattedFromDate = targetFormat.format(fromDate); 
				fromDate = targetFormat.parse(formattedFromDate);
			}
			if(!toDateNew.equalsIgnoreCase("")){
				toDate = originalFormat.parse(toDateNew);
				String formattedToDate = targetFormat.format(toDate); 
				toDate = targetFormat.parse(formattedToDate);
			}
			/*fromDate = originalFormat.parse(fromDateNew);
			String formattedFromDate = targetFormat.format(fromDate); 
			fromDate = targetFormat.parse(formattedFromDate);
			toDate = originalFormat.parse(toDateNew);
			String formattedToDate = targetFormat.format(toDate); 
			toDate = targetFormat.parse(formattedToDate);*/

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			logger.error("--------------Exception-----------------"+e1.getMessage());
			//e1.printStackTrace();
		}

		boolean isGenerated = false;
		try {
			path = path + "excel" + File.separator + "CustomDsrReport.xls";
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			logger.info("Exception " + e);
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Custom DSR Report Info");
		// Create row object
		XSSFRow row;
		SortedMap<Integer, List<Object>> flightReportInfoMap = new TreeMap<>();
		int i = 1;

		List<Object> headerList = new LinkedList<>();
		if(commonDsrReportConfg.isBookingReference()) 
			headerList.add("Booking Reference");
		if(commonDsrReportConfg.isSystemInvoiceId()) 
			headerList.add("System Invoice Id");
		if(commonDsrReportConfg.isBookingType()) 
			headerList.add("Booking/Billing Type");
		if(commonDsrReportConfg.isAmendmentType()) 
			headerList.add("Amendment Type");
		if(commonDsrReportConfg.isInvoiceDate()) 
			headerList.add("Invoice date");
		if(commonDsrReportConfg.isBookingDate()) 
			headerList.add("Booking Date");
		if(commonDsrReportConfg.isCorporateName()) 
			headerList.add("Corporate Name");
		if(commonDsrReportConfg.isBillingEntity()) 
			headerList.add("Billing Entity");
		if(commonDsrReportConfg.isBookerName()) 
			headerList.add("Booker Name");
		if(commonDsrReportConfg.isBookersLoginId()) 
			headerList.add("Bookers Login Id");


		/*headerList.add("Booking Reference");
		headerList.add("System Invoice Id");
		headerList.add("Booking/Billing Type");
		headerList.add("Amendment Type");
		headerList.add("Invoice date");
		headerList.add("Booking Date");
		headerList.add("Corporate Name");
		headerList.add("Billing Entity");
		headerList.add("Booker Name");
		headerList.add("Bookers Login Id");*/
		if (companyObj.getCompanyRole().isSuperUser()) {
			headerList.add("Supplier Code");
			headerList.add("Supplier Name");
			headerList.add("Supplier Charge");
		}
		if(commonDsrReportConfg.isProductType()) 
			headerList.add("Product Type");
		if(commonDsrReportConfg.isItineraryType()) 
			headerList.add("Itinerary Type");
		if(commonDsrReportConfg.isProductName()) 
			headerList.add("Product Name");
		if(commonDsrReportConfg.isProductCode() ) 
			headerList.add("Product Code");
		if(commonDsrReportConfg.isDescription()) 
			headerList.add("Description");
		if(commonDsrReportConfg.isDescription2()) 
			headerList.add("Description 2");
		if(commonDsrReportConfg.isProvBooking()) 
			headerList.add("Airline PNR/Prov Booking");
		if(commonDsrReportConfg.isGdsPnr()) 
			headerList.add("GDS PNR");
		if(commonDsrReportConfg.isTicketNum()) 
			headerList.add("Ticket Num/Final Booking");
		if(commonDsrReportConfg.isFareType()) 
			headerList.add("Fare Type");
		if(commonDsrReportConfg.isBookingRefundType()) 
			headerList.add("Booking Refund Type");
		if(commonDsrReportConfg.isFareBasis()) 
			headerList.add("Fare Basis");
		if(commonDsrReportConfg.isCabinClass()) 
			headerList.add("Cabin Class");
		if(commonDsrReportConfg.isBookingClass()) 
			headerList.add("Booking Class");
		if(commonDsrReportConfg.isPaxType()) 
			headerList.add("Pax Type");
		if(commonDsrReportConfg.isTravellerName()) 
			headerList.add("Traveller Name");
		if(commonDsrReportConfg.isTotalNights()) 
			headerList.add("Total Nights");
		if(commonDsrReportConfg.isTripStartDate()) 
			headerList.add("Trip Starts Date");
		if(commonDsrReportConfg.isTripEndDate()) 
			headerList.add("Trip End Date");
		if(commonDsrReportConfg.isJourneyType()) 
			headerList.add("Journey Type");
		if(commonDsrReportConfg.isBaseFare()) 
			headerList.add("Base Fare");
		if(commonDsrReportConfg.isYqTax()) 
			headerList.add("YQ Tax");
		if(commonDsrReportConfg.isYrTax()) 
			headerList.add("YR Tax");
		if(commonDsrReportConfg.isK3Tax()) 
			headerList.add("K3 Tax");
		if(commonDsrReportConfg.isPsfTax()) 
			headerList.add("PSF Tax");
		if(commonDsrReportConfg.isuDfTax()) 
			headerList.add("UDF Tax");
		if(commonDsrReportConfg.isJnTax()) 
			headerList.add("JN Tax");
		if(commonDsrReportConfg.isInTax()) 
			headerList.add("IN Tax");
		if(commonDsrReportConfg.isOcTax()) 
			headerList.add("OC Tax");
		if(commonDsrReportConfg.isOtherTax()) 
			headerList.add("Other Taxes");
		if(commonDsrReportConfg.isvFSTax()) 
			headerList.add("Vfs Charges");
		if(commonDsrReportConfg.isCourierCharge()) 
			headerList.add("Courier Charges");
		if(commonDsrReportConfg.isExtraKmCharge()) 
			headerList.add("Extra Km Charge");
		if(commonDsrReportConfg.isDriverAllowDay()) 
			headerList.add("Driver Allowance Day Charge");
		if(commonDsrReportConfg.isDriverAllowNight()) 
			headerList.add("Driver Allowance Night Charge");
		if(commonDsrReportConfg.isTollOrParkingCharge()) 
			headerList.add("Toll Or Parking Charge");
		if(commonDsrReportConfg.isExtraHourCharge()) 
			headerList.add("Extra Hour Charge");
		if(commonDsrReportConfg.isExtraCharge()) 
			headerList.add("Extra Charge(Meal/Baggage Etc.)");
		if(commonDsrReportConfg.isSupplierAmendment()) 
			headerList.add("Supplier Amendment/Cancellation Fee");
		if(commonDsrReportConfg.isGrossFare()) 
			headerList.add("Gross Fare");	


		if(fromDate!=null && toDate!=null){
			if((fromDate.compareTo(gstStartDateObj)>0) && (toDate.compareTo(gstStartDateObj)>0))
			{
				if(commonDsrReportConfg.isCgst()) 
					headerList.add("CGST");
				if(commonDsrReportConfg.isSgstorIgstorUgst()) 
					headerList.add("SGST/IGST/UGST");
				if(commonDsrReportConfg.isGstTax()) 
					headerList.add("Gst Tax");
			}

			else{
				headerList.add("Service Tax Base");
				headerList.add("Swach Bharat Cess");
				headerList.add("Krishi Kalyan Cess");
				headerList.add("Service Tax");
			}

		}
		else{
			if(commonDsrReportConfg.isCgst()) 
				headerList.add("CGST");
			if(commonDsrReportConfg.isSgstorIgstorUgst()) 
				headerList.add("SGST/IGST/UGST");
			if(commonDsrReportConfg.isGstTax()) 
				headerList.add("Gst Tax");
		}	

		if(commonDsrReportConfg.isTayyarahCharge())  
			headerList.add("Tayyarah Charges");
		if(commonDsrReportConfg.isConvienceFee())
			headerList.add("Convenience Charge");
		if(commonDsrReportConfg.isDiscount())
			headerList.add("Discount");
		if(commonDsrReportConfg.isNetFare())
			headerList.add("Net Fare");

		// these headers on need to display super User
		if (companyObj.getCompanyRole().isSuperUser())
			headerList.add("Total Markup");

		// end
		if(commonDsrReportConfg.isModeOfPayment())
			headerList.add("Mode of Payment");
		if(commonDsrReportConfg.isTravelStatus())
			headerList.add("Travel Status");
		if(commonDsrReportConfg.isPersonalBooking())
			headerList.add("Personal Booking");
		if(commonDsrReportConfg.isCorporateCurrency())
			headerList.add("Corporate Currency");
		headerList.add("Approver Name");
		if(companyObj.getCompanyRole().isSuperUser()){

			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("All") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList=getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=getExcelHeadings(list, headerList,"K");


		headerList.add("Bill NoBill");
		headerList.add("Business Unit");
		headerList.add("Cost Center");
		headerList.add("Emp Code");
		headerList.add("Travel Request Number");
		headerList.add("Location");
		headerList.add("Department");
		headerList.add("Project Code");
		headerList.add("Reason for Travel");
		flightReportInfoMap.put(i, headerList);

		if (list != null && list.size() > 0) {
			for (int j=0;j<list.size();j++) {
				String gstDateStart1="2017-06-30";
				String fromDateNew1=commonDsrPage.getCommonDsrFilters().getFromDate();
				String toDateNew1=commonDsrPage.getCommonDsrFilters().getToDate();
				SimpleDateFormat targetFormat1 = new SimpleDateFormat("yyyy-MM-dd");	
				DateFormat originalFormat1 = new SimpleDateFormat("dd-MM-yyyy");
				Date gstStartDateObj1=null;
				Date fromDate1=null;
				Date toDate1=null;
				try {
					gstStartDateObj1 = targetFormat1.parse(gstDateStart1);
					if(!fromDateNew1.equalsIgnoreCase("")){
						fromDate1 = originalFormat1.parse(fromDateNew);
						String formattedFromDate = targetFormat.format(fromDate1); 
						fromDate1 = targetFormat.parse(formattedFromDate);
					}
					if(!toDateNew1.equalsIgnoreCase("")){
						toDate1 = originalFormat1.parse(toDateNew);
						String formattedToDate = targetFormat.format(toDate1); 
						toDate1 = targetFormat.parse(formattedToDate);
					}
					
					/*fromDate1 = originalFormat1.parse(fromDateNew1);
					String formattedFromDate = targetFormat1.format(fromDate1); 
					fromDate1 = targetFormat1.parse(formattedFromDate);
					toDate1 = originalFormat1.parse(toDateNew1);
					String formattedToDate = targetFormat1.format(toDate1); 
					toDate1 = targetFormat1.parse(formattedToDate);*/

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					logger.error("--------------Exception-----------------"+e1.getMessage());
					//e1.printStackTrace();
				}

				CommonDsrTravelReportData reportData=list.get(j);
				i++;
				List<Object> dataList = new LinkedList<>();
				if(commonDsrReportConfg.isBookingReference())
					dataList.add(reportData.getBookingReference());
				if(commonDsrReportConfg.isSystemInvoiceId())
					dataList.add(reportData.getSystemInvoiceId());
				if(commonDsrReportConfg.isBookingType())
					dataList.add(reportData.getBookingType());
				//				dataList.add(reportData.getBkgRef());
				if(commonDsrReportConfg.isAmendmentType())
					dataList.add(reportData.getAmendmentType());
				if(commonDsrReportConfg.isInvoiceDate())
					dataList.add(reportData.getInvoicedate());
				if(commonDsrReportConfg.isBookingDate())
					dataList.add(reportData.getBookingDate());
				if(commonDsrReportConfg.isCorporateName())
					dataList.add(reportData.getCorporateName());
				if(commonDsrReportConfg.isBillingEntity())
					dataList.add(reportData.getBillingEntity());
				if(commonDsrReportConfg.isBookerName())
					dataList.add(reportData.getBookerName());
				if(commonDsrReportConfg.isBookersLoginId())
					dataList.add(reportData.getBookersLoginId());

				if (companyObj.getCompanyRole().isSuperUser()) {
					if(commonDsrReportConfg.isSupplierCode())
						dataList.add(reportData.getSupplierCode());
					if(commonDsrReportConfg.isSupplierName())
						dataList.add(reportData.getSupplierName());
					if(commonDsrReportConfg.isSupplierCharge())
						dataList.add(reportData.getSupplierCharge());
				}

				if(commonDsrReportConfg.isProductType())
					dataList.add(reportData.getProductType());
				if(commonDsrReportConfg.isItineraryType())
					dataList.add(reportData.getItineraryType());
				if(commonDsrReportConfg.isProductName())
					dataList.add(reportData.getProductName());
				if(commonDsrReportConfg.isProductCode())
					dataList.add(reportData.getProductCode()) ;
				if(commonDsrReportConfg.isDescription())
					dataList.add(reportData.getDescription());
				if(commonDsrReportConfg.isDescription2())
					dataList.add(reportData.getDescription2());
				if(commonDsrReportConfg.isProvBooking())
					dataList.add(reportData.getAirlinePNRorProvBooking());
				if(commonDsrReportConfg.isGdsPnr())
					dataList.add(reportData.getGDSPNR());
				if(commonDsrReportConfg.isTicketNum())
					dataList.add(reportData.getTicketNumorFinalBooking());
				if(commonDsrReportConfg.isFareType())
					dataList.add(reportData.getFareType());
				if(commonDsrReportConfg.isBookingRefundType())
					dataList.add(reportData.getBookingRefundType());
				if(commonDsrReportConfg.isFareBasis())
					dataList.add(reportData.getFareBasis());
				if(commonDsrReportConfg.isCabinClass())
					dataList.add(reportData.getCabinClass());
				if(commonDsrReportConfg.isBookingClass())
					dataList.add(reportData.getBookingClass());
				if(commonDsrReportConfg.isPaxType())
					dataList.add(reportData.getPaxType());
				if(commonDsrReportConfg.isTravellerName())
					dataList.add(reportData.getTraveller());
				if(commonDsrReportConfg.isTotalNights())
					dataList.add(reportData.getTotalNights());
				if(commonDsrReportConfg.isTripStartDate())
					dataList.add(reportData.getTripStartsDate());
				if(commonDsrReportConfg.isTripEndDate())
					dataList.add(reportData.getTripEndDate());
				if(commonDsrReportConfg.isJourneyType())
					dataList.add(reportData.getJourneyType());
				if(commonDsrReportConfg.isBaseFare())
					dataList.add(reportData.getBaseFare());
				if(commonDsrReportConfg.isYqTax())
					dataList.add(reportData.getYQTax());
				if(commonDsrReportConfg.isYrTax())
					dataList.add(reportData.getYRTax());
				if(commonDsrReportConfg.isK3Tax())
					dataList.add(reportData.getK3Tax());
				if(commonDsrReportConfg.isPsfTax())
					dataList.add(reportData.getPSFTax());
				if(commonDsrReportConfg.isuDfTax())
					dataList.add(reportData.getUDFTax());
				if(commonDsrReportConfg.isJnTax())
					dataList.add(reportData.getJNTax());
				if(commonDsrReportConfg.isInTax())
					dataList.add(reportData.getINTax());
				if(commonDsrReportConfg.isOcTax())
					dataList.add(reportData.getOCTax());
				if(commonDsrReportConfg.isOtherTax())
					dataList.add(reportData.getOtherTaxes());
				if(commonDsrReportConfg.isvFSTax())
					dataList.add(reportData.getVfsCharges());
				if(commonDsrReportConfg.isCourierCharge())
					dataList.add(reportData.getCourierCharges());
				if(commonDsrReportConfg.isExtraKmCharge())
					dataList.add(reportData.getExtraKmCharge());
				if(commonDsrReportConfg.isDriverAllowDay())
					dataList.add(reportData.getDriverAllowancedayCharge());
				if(commonDsrReportConfg.isDriverAllowNight())
					dataList.add(reportData.getDriverAllowanceNightCharge());
				if(commonDsrReportConfg.isTollOrParkingCharge())
					dataList.add(reportData.getTollorParkingCharge());
				if(commonDsrReportConfg.isExtraHourCharge())
					dataList.add(reportData.getExtraHourCharge());
				if(commonDsrReportConfg.isExtraCharge())
					dataList.add(reportData.getExtraCharge());
				if(commonDsrReportConfg.isSupplierAmendment())
					dataList.add(reportData.getSupplierAmendmentOrCancellationFee());
				if(commonDsrReportConfg.isGrossFare())
					dataList.add(reportData.getGrossFare());

				if(fromDate!=null && toDate!=null){
					if(reportData.getTaxType()!=null && reportData.getTaxType().equalsIgnoreCase("GST")){
						if(commonDsrReportConfg.isCgst()) 
							dataList.add(reportData.getCGST());
						if(commonDsrReportConfg.isSgstorIgstorUgst()) 
							dataList.add(reportData.getSGSTorUGSTorIGST());
						if(commonDsrReportConfg.isGstTax()) 
							dataList.add(reportData.getTotGstTax());
					}
					else{
						dataList.add(reportData.getServiceTaxBase());
						dataList.add(reportData.getSwachBharatCess());
						dataList.add(reportData.getKrishiKalyanCess());
						dataList.add(reportData.getServiceTax());
					} 

				}
				else{
					if(commonDsrReportConfg.isCgst()) 
						dataList.add(reportData.getCGST());
					if(commonDsrReportConfg.isSgstorIgstorUgst()) 
						dataList.add(reportData.getSGSTorUGSTorIGST());
					if(commonDsrReportConfg.isGstTax()) 
						dataList.add(reportData.getTotGstTax());
				}	


				if(commonDsrReportConfg.isTayyarahCharge())  
					dataList.add(reportData.getTayyarahServiceCharges());
				if(commonDsrReportConfg.isConvienceFee())
					dataList.add(reportData.getConvenienceCharge());
				if(commonDsrReportConfg.isDiscount())
					dataList.add(reportData.getDiscount());
				if(commonDsrReportConfg.isNetFare())
					dataList.add(reportData.getNetFare());
				
				if (companyObj.getCompanyRole().isSuperUser())
					dataList.add(reportData.getMarkup());
				
				if(commonDsrReportConfg.isModeOfPayment())
				dataList.add(reportData.getModeOfPayment());
				if(commonDsrReportConfg.isTravelStatus())
				dataList.add(reportData.getTravelStatus());
				if(commonDsrReportConfg.isPersonalBooking())
				dataList.add(reportData.getPersonalBooking());
				if(commonDsrReportConfg.isCorporateCurrency())
				dataList.add(reportData.getCorporateCurrency());
				dataList.add(reportData.getApproverName());
				if(companyObj.getCompanyRole().isSuperUser()){
					if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("All") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals(""))
						dataList.add(reportData.getExtraRmConfigDetails());
					else{
						dataList=getExcelData(list.get(j),dataList);
					}
				}
				else 
					dataList= getExcelData(list.get(j),dataList);

				dataList.add(reportData.getBillNonBill());
				dataList.add(reportData.getBusinessUnit());
				dataList.add(reportData.getCostCenter());
				dataList.add(reportData.getEmpCode());
				dataList.add(reportData.getTravelRequestNumber());
				dataList.add(reportData.getLocation());
				dataList.add(reportData.getDepartment() );
				dataList.add(reportData.getProjectCode());
				dataList.add(reportData.getReasonForTravel());
				flightReportInfoMap.put(i, dataList);
			}

		}  
		// Iterate over data and write to sheet
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				workbook.getSheetAt(0).autoSizeColumn(rowid);
				row = spreadsheet.createRow(rowid++);

				if (row.getRowNum() == 0) {
					row.setHeight((short) 500);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short) 400);
				}

				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(0).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					//workbook.getSheetAt(0).autoSizeColumn(0);
					//workbook.getSheetAt(0).autoSizeColumn(cellid);
					workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.GOLD.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}
				//System.out.println("cellid ======="+cellid);
			}

			isGenerated=true; 
		}

		// Write the workbook in file system
		FileOutputStream out;
		try {
			// out = new FileOutputStream(new
			// File("D://excel//"+pdfCreateDate+"_"+"flight_report"+".xlsx"));
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated=true; 
			// logger.info("Writesheet.xlsx written successfully" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated=false; 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated=false; 
		}
		return isGenerated;
	}





	public static boolean generateHotelReportExcel(List<HotelOrderRow> list,
			HotelandFlightDisReportProperty hotelDisReportProperty, String path) {
		boolean isGenerated = false;
		try {
			path = path + "excel" + File.separator + "hotel_dsr_report.xls";
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			boolean b = false;
			if (!file.exists()) {
				file.createNewFile();
			}
			if (b) {
				logger.info("Directory successfully created");
			}

			else {
				logger.info("Alredy directory createded");
			}

		} catch (Exception e) {
			logger.info("Exception " + e);
		}

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HHmmssSSS");
		// get current date time with Date()
		Date date = new Date();
		String pdfCreateDate = dateFormat.format(date);
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Hotel DSR Report Info");
		// Create row object
		XSSFRow row;
		// This data needs to be written (Object[])
		// Map<String, Object[]> flightReportInfoMap = new TreeMap<String,
		// Object[]>();
		Map<Integer, List<Object>> flightReportInfoMap = new TreeMap<>();

		int i = 1;
		List<Object> headerList = new LinkedList<>();
		if (hotelDisReportProperty != null) {
			headerList.add("BookingDate");
			if (hotelDisReportProperty.isGuestName())
				headerList.add("GuestName");
			if (hotelDisReportProperty.isHotelName())
				headerList.add("HotelName");
			if (hotelDisReportProperty.isCheckIn())
				headerList.add("CheckInDate");
			if (hotelDisReportProperty.isCheckOut())
				headerList.add("CheckOutDate");
			if (hotelDisReportProperty.isFinalPrice())
				headerList.add("FinalPrice");
			if (hotelDisReportProperty.isInvoiceNo())
				headerList.add("InvoiceNumber");
			if (hotelDisReportProperty.isOrderRef())
				headerList.add("OrderReference");
			if (hotelDisReportProperty.isStatusAction())
				headerList.add("BookingStatus");
			if (hotelDisReportProperty.isPaymentStatus())
				headerList.add("Paymentstatus");
			if (hotelDisReportProperty.isAgency())
				headerList.add("AgencyName");

		}

		flightReportInfoMap.put(i,
				headerList /*
				 * new Object[]{ /*"PAX NAME"
				 * ,"PNR","AIRLINE","ORIGIN","DESTINATION",
				 * "BOOKING DATE","FINAL AMOUNT","BOOKING STATUS",
				 * "PAYMENT STATUS","AGENCY","ORDER ID"
				 */
				/* } */);

		if (list != null && list.size() > 0) {

			for (HotelOrderRow hotelOrderRow : list) {
				i++;

				List<Object> dataList = new LinkedList<>();
				if (hotelDisReportProperty != null) {

					dataList.add(DateConversion.convertDateToStringToDateWithTIME(hotelOrderRow.getCreatedAt()));
					if (hotelDisReportProperty.isGuestName())
						dataList.add(hotelOrderRow.getOrderCustomer().getFirstName() + " "
								+ hotelOrderRow.getOrderCustomer().getLastName());
					if (hotelDisReportProperty.isHotelName())
						dataList.add(hotelOrderRow.getHotelOrderHotelData().getName());
					if (hotelDisReportProperty.isCheckIn())
						dataList.add(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckInDate()));
					if (hotelDisReportProperty.isCheckOut())
						dataList.add(DateConversion.convertDateToStringToDate(hotelOrderRow.getCheckOutDate()));
					if (hotelDisReportProperty.isFinalPrice())
						dataList.add(hotelOrderRow.getFinalPrice().setScale(BigDecimal.ROUND_UP, 2).toString());
					if (hotelDisReportProperty.isInvoiceNo())
						dataList.add(hotelOrderRow.getInvoiceNo());
					if (hotelDisReportProperty.isOrderRef())
						dataList.add(hotelOrderRow.getOrderReference());
					if (hotelDisReportProperty.isStatusAction())
						dataList.add(hotelOrderRow.getStatusAction());
					if (hotelDisReportProperty.isPaymentStatus())
						dataList.add(hotelOrderRow.getPaymentStatus());
					if (hotelDisReportProperty.isAgency())
						dataList.add(hotelOrderRow.getCreatedBy().replace("+", " "));

				}

				flightReportInfoMap.put(i, dataList);

				// logger.info("index---"+i+"-------flightReportInfoMap----"+flightReportInfoMap);
			}

		}

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);

		// Iterate over data and write to sheet
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		for (Integer key : keyid) {

			row = spreadsheet.createRow(rowid++);

			// Object [] objectArr = flightReportInfoMap.get(key);
			List<Object> objectArr = flightReportInfoMap.get(key);
			// logger.info(objectArr);
			int cellid = 0;
			for (Object obj : objectArr) {

				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		// Write the workbook in file system
		FileOutputStream out;
		try {
			// out = new FileOutputStream(new
			// File("D://excel//"+pdfCreateDate+"_"+"flight_report"+".xlsx"));
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated = true;
			// logger.info("Writesheet.xlsx written successfully" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isGenerated;
	}

	public static boolean generateFlightReportExcel(List<FlightOrderRow> list,
			HotelandFlightDisReportProperty hotelDisReportProperty, String path) {
		boolean isGenerated = false;
		try {
			path = path + "excel" + File.separator + "FlightDisreport.xls";
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			boolean b = false;
			if (!file.exists()) {
				file.createNewFile();
			}
			/*
			 * if (b){ logger.info("Directory successfully created"); }
			 * 
			 * else{ logger.info("Alredy directory createded"); }
			 */

		} catch (Exception e) {
			logger.info("Exception " + e);
		}

		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Flight DSR Report Info");
		// Create row object
		XSSFRow row;
		// This data needs to be written (Object[])
		// Map<String, Object[]> flightReportInfoMap = new TreeMap<String,
		// Object[]>();
		SortedMap<String, List<Object>> flightReportInfoMap = new TreeMap<String, List<Object>>();

		int i = 1;
		List<Object> headerList = new LinkedList<>();
		if (hotelDisReportProperty != null) {
			headerList.add("BookingDate");
			if (hotelDisReportProperty.isGuestName())
				headerList.add("PaxName");
			if (hotelDisReportProperty.isPnr())
				headerList.add("PNR");
			if (hotelDisReportProperty.isAirline())
				headerList.add("Airline");
			if (hotelDisReportProperty.isFinalPrice())
				headerList.add("FinalPrice");
			if (hotelDisReportProperty.isInvoiceNo())
				headerList.add("InvoiceNumber");
			if (hotelDisReportProperty.isOrderRef())
				headerList.add("OrderReference");
			if (hotelDisReportProperty.isStatusAction())
				headerList.add("BookingStatus");
			if (hotelDisReportProperty.isPaymentStatus())
				headerList.add("Paymentstatus");
			if (hotelDisReportProperty.isAgency())
				headerList.add("AgencyName");

		}

		flightReportInfoMap.put(String.valueOf(i),
				headerList /*
				 * new Object[]{ /*"PAX NAME"
				 * ,"PNR","AIRLINE","ORIGIN","DESTINATION",
				 * "BOOKING DATE","FINAL AMOUNT","BOOKING STATUS",
				 * "PAYMENT STATUS","AGENCY","ORDER ID"
				 */
				/* } */);

		if (list != null && list.size() > 0) {
			for (FlightOrderRow flightOrderRow : list) {
				i++;
				List<Object> dataList = new LinkedList<>();
				if (hotelDisReportProperty != null) {

					dataList.add(DateConversion.convertDateToStringToDateWithTIME(flightOrderRow.getCreatedAt()));
					if (hotelDisReportProperty.isGuestName())
						dataList.add(flightOrderRow.getCustomer().getFirstName() + " "
								+ flightOrderRow.getCustomer().getLastName());
					if (hotelDisReportProperty.isPnr())
						dataList.add(flightOrderRow.getPnr());
					if (hotelDisReportProperty.isAirline())
						dataList.add(flightOrderRow.getAirline());
					if (hotelDisReportProperty.isFinalPrice())
						dataList.add(flightOrderRow.getFinalPrice().setScale(BigDecimal.ROUND_UP, 2).toString());
					if (hotelDisReportProperty.isInvoiceNo())
						dataList.add(flightOrderRow.getInvoiceNo());
					if (hotelDisReportProperty.isOrderRef())
						dataList.add(flightOrderRow.getOrderId());
					if (hotelDisReportProperty.isStatusAction())
						dataList.add(flightOrderRow.getStatusAction());
					if (hotelDisReportProperty.isPaymentStatus())
						dataList.add(flightOrderRow.getPaymentStatus());
					if (hotelDisReportProperty.isAgency())
						dataList.add(flightOrderRow.getCreatedBy().replace("+", " "));

				}
				flightReportInfoMap.put(String.valueOf(i), dataList);
			}

		}

		// Iterate over data and write to sheet
		Set<String> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			// Object [] objectArr = flightReportInfoMap.get(key);
			List<Object> objectArr = flightReportInfoMap.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		// Write the workbook in file system
		FileOutputStream out;
		try {
			// out = new FileOutputStream(new
			// File("D://excel//"+pdfCreateDate+"_"+"flight_report"+".xlsx"));
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated = true;
			// logger.info("Writesheet.xlsx written successfully" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isGenerated;
	}

	public static List<Object> getExcelHeadings(List<CommonDsrTravelReportData> list,List<Object> headerList,String pairType){
		if(list!=null && list.size()>0){
			CommonDsrTravelReportData commonDsrTravelReportData=list.get(0);
			if(commonDsrTravelReportData.getExtraRmConfigDetails()==null){
				Map<String,String> fieldsMap=commonDsrTravelReportData.getRmConfigMap();
				if(fieldsMap!=null){
					for(Map.Entry<String,String> entry:fieldsMap.entrySet()){ 
						if(pairType.equals("K"))
							headerList.add(entry.getKey()!=null && !entry.getKey().equals("")?entry.getKey():"NA");

					}
				}
			}

		}
		return headerList;
	}

	public static  List<Object> getExcelData(CommonDsrTravelReportData commonDsrTravelReportData,List<Object>  dataList){
		if(commonDsrTravelReportData!=null ){
			if(commonDsrTravelReportData.getExtraRmConfigDetails()==null){
				Map<String,String> fieldsMap=commonDsrTravelReportData.getRmConfigMap();
				if(fieldsMap!=null){
					for(Map.Entry<String,String> entry:fieldsMap.entrySet()){
						dataList.add(entry.getValue()!=null && !entry.getValue().equals("")?entry.getValue():"-");
					}
				}
			}
		}
		return dataList;
	}

	public static boolean generateMissedAirReportExcel(List<CommonDsrMissedFlightReport>  list, String path,
			Company companyObj,CommonDsrPage commonDsrPage) {
		String gstDateStart="2017-06-30";
		String fromDateNew=commonDsrPage.getCommonDsrFilters().getFromDate();
		String toDateNew=commonDsrPage.getCommonDsrFilters().getToDate();

		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");	
		DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date gstStartDateObj=null;
		Date fromDate=null;
		Date toDate=null;
		try {
			gstStartDateObj = targetFormat.parse(gstDateStart);
			if(!fromDateNew.equalsIgnoreCase("")){
				fromDate = originalFormat.parse(fromDateNew);
				String formattedFromDate = targetFormat.format(fromDate); 
				fromDate = targetFormat.parse(formattedFromDate);
			}
			if(!toDateNew.equalsIgnoreCase("")){
				toDate = originalFormat.parse(toDateNew);
				String formattedToDate = targetFormat.format(toDate); 
				toDate = targetFormat.parse(formattedToDate);
			}
			
			/*fromDate = originalFormat.parse(fromDateNew);
			String formattedFromDate = targetFormat.format(fromDate); 
			fromDate = targetFormat.parse(formattedFromDate);
			toDate = originalFormat.parse(toDateNew);
			String formattedToDate = targetFormat.format(toDate); 
			toDate = targetFormat.parse(formattedToDate);*/

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean isGenerated = false;
		try {
			path = path + "excel" + File.separator + "AirMissedSavingReport.xlsx";
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
			logger.info("Exception " + e);
		}
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HHmmssSSS");
		// get current date time with Date()
		Date date = new Date();
		String pdfCreateDate = dateFormat.format(date);
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Air Missed Saving Report");
		// Create row object
		XSSFRow row;
		// This data needs to be written (Object[])
		// Map<String, Object[]> flightReportInfoMap = new TreeMap<String,
		// Object[]>();
		SortedMap<Integer, List<Object>> flightReportInfoMap = new TreeMap<>();
		CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(companyObj.getCompanyid());

		int i = 1;
		List<Object> headerList = new LinkedList<>();
		headerList.add("   Cart  /  OrderId   ");
		headerList.add("Booking Date");
		headerList.add("Cost Center");
		headerList.add("Type Of Journey");
		headerList.add("Sector (Ori-Dest)");
		headerList.add("Traveller");
		headerList.add("Emp Code");
		headerList.add("Band Code");
		headerList.add("Department Code");
		headerList.add("Location Code");
		headerList.add("Ticketed Flight Details (For Booked Flight)");
		headerList.add("Lowest Flight Details1 (For Cheapest Flight 1)");
		headerList.add("Lowest Flight Details2 (For Cheapest Flight 2)");
		headerList.add("Threshold Buffer");
		headerList.add("Time Window Minute");
		headerList.add("Reason of Fare Alert");
		headerList.add("Missed Realised Saving");
		headerList.add("Ticketed Vs Lowest1 Fare Difference");
		headerList.add("Ticketed Vs Lowest2 Fare Difference");
		headerList.add("Wasted Amount");
		
		flightReportInfoMap.put(i, headerList);

		if (list != null && list.size() > 0) {
			for (int j=0;j<list.size();j++) {
				CommonDsrMissedFlightReport reportData=list.get(j);
				i++;
				List<Object> dataList = new LinkedList<>();
				dataList.add(reportData.getCart());
				dataList.add(reportData.getBookingDate());
				dataList.add(reportData.getCostCenter());
				dataList.add(reportData.getType());
				dataList.add(reportData.getSector());
				dataList.add(reportData.getTravellor());
				dataList.add(reportData.getEmpCode());
				dataList.add(reportData.getBandCode());
				dataList.add(reportData.getDepartmentCode());
				dataList.add(reportData.getLocationCode());
				dataList.add(reportData.getTicketedFlightDetails());
				dataList.add(reportData.getLowestFlightDetails1());
				dataList.add(reportData.getLowestFlightDetails2());
				dataList.add(reportData.getThresholdBuffer());
				dataList.add(reportData.getTimeWindowMinute());
				dataList.add(reportData.getReason());
				dataList.add(reportData.getMisseedRealisedSaving());
				dataList.add(reportData.getTicketedVsLowest1FareDifference());
				dataList.add(reportData.getTicketedVsLowest2FareDifference());
				dataList.add(reportData.getWastedAmount());
				flightReportInfoMap.put(i, dataList);
			}

		} /*else {
			List<Object> dataList = new LinkedList<>();
			dataList.add("-------------------------NO DATA AVAILABLE-------------------------");
			flightReportInfoMap.put(0, dataList);
		}*/

		// Iterate over data and write to sheet
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				workbook.getSheetAt(0).autoSizeColumn(rowid);
				row = spreadsheet.createRow(rowid++);

				if (row.getRowNum() == 0) {
					row.setHeight((short) 500);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short) 400);
				}

				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(0).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					//workbook.getSheetAt(0).autoSizeColumn(0);
					//workbook.getSheetAt(0).autoSizeColumn(cellid);
					workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.GOLD.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}
			isGenerated=true; 
		}

		// Write the workbook in file system
		FileOutputStream out;
		try {
			// out = new FileOutputStream(new
			// File("D://excel//"+pdfCreateDate+"_"+"flight_report"+".xlsx"));
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated=true; 
			// logger.info("Writesheet.xlsx written successfully" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated=false; 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated=false; 
		}
		return isGenerated;
	}
	
	

}
