package com.lintas.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.common.dsr.CommonDsrPage;
import com.common.dsr.CommonDsrTravelReportData;
import com.lintas.admin.model.Company;

public class OutStandingTravelReportsExportToExcel {
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OutStandingTravelReportsExportToExcel.class);

	public static boolean generateOutstandingTravelReportExcel(List<CommonDsrTravelReportData> list, String path,
			Company companyObj,CommonDsrPage commonDsrPage,String fileName) {
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
			path = path + "excel" + File.separator +fileName;
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
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Outstanding Report Info");
		// Create row object
		XSSFRow row;
		SortedMap<Integer, List<Object>> flightReportInfoMap = new TreeMap<>();
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
		 headerList.add("Outstanding");

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
				BigDecimal outstandingAmount=new BigDecimal(reportData.getOutstandingAmount());
				if(!reportData.isKnockOff() && (outstandingAmount.compareTo(new BigDecimal(10))>0 ||  outstandingAmount.compareTo(new BigDecimal(10).negate())<0)){
					i++;
					List<Object> dataList = new LinkedList<>();
					dataList.add(reportData.getBkgRef());
					 dataList.add(reportData.getSystemInvoiceId());
					dataList.add(reportData.getBookingType());
					dataList.add(reportData.getAmendmentType());
					dataList.add(reportData.getInvoicedate());
					dataList.add(reportData.getBookingDate());
					dataList.add(reportData.getCorporateName());
					dataList.add(reportData.getBillingEntity());
					dataList.add(reportData.getBookerName());
					dataList.add(reportData.getBookersLoginId());
					if (companyObj.getCompanyRole().isSuperUser()) {
						dataList.add(reportData.getSupplierCode());
						dataList.add(reportData.getSupplierName());
						dataList.add(reportData.getSupplierCharge());
					}

					dataList.add(reportData.getProductType());
					dataList.add(reportData.getItineraryType());
					dataList.add(reportData.getProductName());
					dataList.add(reportData.getProductCode());
					dataList.add(reportData.getDescription());
					dataList.add(reportData.getDescription2());
					dataList.add(reportData.getAirlinePNRorProvBooking()!=null   && !reportData.getAirlinePNRorProvBooking().equals("")?reportData.getAirlinePNRorProvBooking():"-");
					dataList.add(reportData.getGDSPNR());
					dataList.add(reportData.getTicketNumorFinalBooking()!=null && !reportData.getTicketNumorFinalBooking().equals("")?reportData.getTicketNumorFinalBooking():"-");
					dataList.add(reportData.getFareType());
					dataList.add(reportData.getBookingRefundType());
					dataList.add(reportData.getFareBasis()!=null&& !reportData.getFareBasis().equals("")?reportData.getFareBasis():"-");
					dataList.add(reportData.getCabinClass()!=null && !reportData.getCabinClass().equals("")?reportData.getCabinClass():"-");
					dataList.add(reportData.getBookingClass()!=null && !reportData.getBookingClass().equals("")?reportData.getBookingClass():"-");
					dataList.add(reportData.getPaxType()!=null && !reportData.getPaxType().equals("")?reportData.getPaxType():"-");
					dataList.add(reportData.getTraveller());
					dataList.add(reportData.getTotalNights());
					dataList.add(reportData.getTripStartsDate());
					dataList.add(reportData.getTripEndDate());
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
					dataList.add(reportData.getOutstandingAmount());
					if (companyObj.getCompanyRole().isSuperUser())
						dataList.add(reportData.getMarkup());

					dataList.add(reportData.getModeOfPayment());
					dataList.add(reportData.getTravelStatus());
					dataList.add(reportData.getPersonalBooking());
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
					dataList.add(reportData.getDepartment());
					dataList.add(reportData.getProjectCode());
					dataList.add(reportData.getReasonForTravel());
					flightReportInfoMap.put(i, dataList);	
				}
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

			}
			isGenerated=true; 
		}

		// Write the workbook in file system
		FileOutputStream out;
		try {
			 
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated=true; 
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
	 

	public static List<Object> getExcelHeadings(List<CommonDsrTravelReportData> list,List<Object> headerList,String pairType){
		if(list!=null && list.size()>0){
			CommonDsrTravelReportData commonDsrTravelReportData=list.get(0);
			if(commonDsrTravelReportData.getExtraRmConfigDetails()==null){
				Map<String,String> fieldsMap=commonDsrTravelReportData.getRmConfigMap();
				if(fieldsMap!=null){
					for(Map.Entry<String,String> entry:fieldsMap.entrySet()){ 
						if(pairType.equals("K"))
							headerList.add(entry.getKey());

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
						dataList.add(entry.getValue());

					}
				}
			}
		}

		return dataList;
	}

	 
}
