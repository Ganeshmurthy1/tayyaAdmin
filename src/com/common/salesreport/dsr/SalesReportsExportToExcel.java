package com.common.salesreport.dsr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
import com.lintas.utility.CommonDsrTravelReportsExportToExcel;

public class SalesReportsExportToExcel {
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SalesReportsExportToExcel.class);

	public static boolean generateSalesReportExcel(SalesReportCalSummary salesReportCalSummary, String path,
			String filename, CommonDsrPage commonDsrPage, String sheetName) {
		 
		boolean isGenerated = false;
		try {
			path = path + "excel" + File.separator + filename;
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
		String travelReportType = commonDsrPage.getCommonDsrFilters().getTravelReportType();
		List<CommonDsrTravelReportData> list = salesReportCalSummary.getCommonDsrTravelReports();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet(sheetName);
		XSSFSheet spreadsheet1=null;
		XSSFRow row1 = null;
		XSSFRow row = null;
		if(travelReportType.equals("AirRouteWiseSalesReport")){
			
		}
		else{
			 spreadsheet1 = workbook.createSheet("Summary");	
		}
		
		SortedMap<Integer, List<Object>> flightReportInfoMap = new TreeMap<>();
		SortedMap<Integer, List<Object>> flightReportInfoMap1 = new TreeMap<>();
		int i = 1;
		
		List<Object> headerList = new LinkedList<>();
		List<Object> headerList1 = new LinkedList<>();

		

		switch (travelReportType) {
		case "AirSalesReport":
			buildAirHeadings(headerList, list, commonDsrPage);
			break;
		// ADDED BY BASHA FOR Air Advanced Purchase REPORT
		case "AirAdvencedPurchaseSalesReport":
			buildAirAdvencedPurchaseSalesHeadings(headerList, list, commonDsrPage);
			break;
		case "AirRouteWiseSalesReport":
			buildAirRouteWiseHeadingsForSummary(headerList);
			break;
		case "plannedAirTripReport":
			buildplannedAirTripReportHeadings(headerList,list);
			break;
		case "HotelSalesReport":
			buildHotelHeadings(headerList, list,commonDsrPage);
			break;

		case "HotelcitywiseSalesReport":
			buildHotelCityWiseHeadings(headerList);
			break;

		case "Advancepurchasehotelreport":
			buildAdvancepurchasehotelHeadings(headerList);
			break;

		case "CarSalesReport":
			buildCarHeadings(headerList, list,commonDsrPage);
			break;
		case "BusSalesReport":
			buildTrainHeadings(headerList,list, commonDsrPage);
			break;
		case "TrainSalesReport":
			buildTrainHeadings(headerList,list, commonDsrPage);
			break;
		case "VisaSalesReport":
			buildVisaHeadings(headerList,list, commonDsrPage);
			break;
		case "InsuranceSalesReport":
			buildInsuranceHeadings(headerList,list, commonDsrPage);
			break;
		case "MiscellaneousSalesReport":
			buildMiscellaneousHeadings(headerList,list, commonDsrPage);
			break;
		default:
			logger.info("Selected Travel Report Type not found-------");
			break;
		}
		if (travelReportType.equalsIgnoreCase("AirSalesReport")) {
			headerList1 = buildAirHeadingsForSummary(headerList1);
		} else if (travelReportType.equalsIgnoreCase("AirAdvencedPurchaseSalesReport")) {
			headerList1 = buildAirAdvencedPurchaseSalesHeadingsForSummary(headerList1);
		} else if (travelReportType.equalsIgnoreCase("HotelSalesReport")) {
			headerList1 = buildHotelHeadingsForSummary(headerList1);
		} else if (travelReportType.equalsIgnoreCase("HotelcitywiseSalesReport")) {
			headerList1 = buildHotelCityWiseHeadingsForSummary(headerList1);
		} else if (travelReportType.equalsIgnoreCase("Advancepurchasehotelreport")) {
			headerList1 = buildAdvancepurchasehotelHeadingsForSummary(headerList1);
		}
		else if (travelReportType.equalsIgnoreCase("plannedAirTripReport")) {
			// do nothing beacuse no summary
		}
		else {
			headerList1 = buildHeadingsForSummary(headerList1);
		}
		flightReportInfoMap.put(i, headerList);
		flightReportInfoMap1.put(0, headerList1);
		List<Object> dataList1 = new LinkedList<>();
		if (list != null && list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				CommonDsrTravelReportData reportData = list.get(j);
				i++;
				List<Object> dataList = new LinkedList<>();

				if (travelReportType.equalsIgnoreCase("BusSalesReport") || travelReportType.equalsIgnoreCase("TrainSalesReport"))
					buildTrainData(dataList, reportData,commonDsrPage);
				if (travelReportType.equalsIgnoreCase("CarSalesReport"))
					buildCarData(dataList, reportData,commonDsrPage);
				if (travelReportType.equalsIgnoreCase("VisaSalesReport"))
					buildVisaData(dataList, reportData,commonDsrPage);
				if (travelReportType.equalsIgnoreCase("InsuranceSalesReport"))
					buildInsuranceData(dataList, reportData,commonDsrPage);
				if (travelReportType.equalsIgnoreCase("MiscellaneousSalesReport"))
					buildInsuranceData(dataList, reportData,commonDsrPage);
				if (travelReportType.equalsIgnoreCase("AirSalesReport"))
					buildAirData(dataList, reportData,commonDsrPage);
				if (travelReportType.equalsIgnoreCase("plannedAirTripReport"))
					buildPlannedAirTripReportData(dataList, reportData);
				if (travelReportType.equalsIgnoreCase("AirAdvencedPurchaseSalesReport"))
					buildAirAdvencedPurchaseSalesReportData(dataList, reportData);
				if (travelReportType.equalsIgnoreCase("HotelcitywiseSalesReport"))
					buildHotelCityWiseData(dataList, reportData);
				if (travelReportType.equalsIgnoreCase("HotelSalesReport"))
					buildHotelData(dataList, reportData,commonDsrPage);
				if (travelReportType.equalsIgnoreCase("Advancepurchasehotelreport"))
					buildAdvancepurchasehotelData(dataList, reportData);
				flightReportInfoMap.put(i, dataList);
			}
		}

		if (travelReportType.equalsIgnoreCase("AirSalesReport")) {
			buildAirDataForSummary(i, salesReportCalSummary, flightReportInfoMap1);
		}
		// ADDED BY BASHA FOR AIR ROUTE WISE REPORT
		else if (travelReportType.equalsIgnoreCase("AirAdvencedPurchaseSalesReport")) {
			buildAirAdvencedPurchaseSalesDataForSummary(i, salesReportCalSummary, flightReportInfoMap1);
		}
		// ADDED BY BASHA FOR AIR ROUTE WISE REPORT
		else if (travelReportType.equalsIgnoreCase("AirRouteWiseSalesReport")) {
			buildAirRouteWiseDataForSummary(i, salesReportCalSummary, flightReportInfoMap);
		} else if (travelReportType.equalsIgnoreCase("HotelSalesReport")) {
			buildHotelDataForSummary(i, salesReportCalSummary, flightReportInfoMap1);
		} else if (travelReportType.equalsIgnoreCase("HotelcitywiseSalesReport")) {
			buildcityWiseDataForSummary(i, salesReportCalSummary, flightReportInfoMap1);
		} else if (travelReportType.equalsIgnoreCase("Advancepurchasehotelreport")) {
			buildAdvancepurchasehotelDataForSummary(i, salesReportCalSummary, flightReportInfoMap1);
		} 
		else if (travelReportType.equalsIgnoreCase("plannedAirTripReport")) {
			// do nothing beacuse no summary
		}
		else {
			buildDataForSummary(dataList1, salesReportCalSummary);
			flightReportInfoMap1.put(1, dataList1);
		}
		buildBookingDataExcelSheet1(flightReportInfoMap, workbook, row, spreadsheet);
		if (travelReportType.equalsIgnoreCase("AirSalesReport")) {
			buildAirBookingSummaryExcelSheet2(flightReportInfoMap1, workbook, row1, spreadsheet1);
		} else if (travelReportType.equalsIgnoreCase("AirAdvencedPurchaseSalesReport")) {
			buildAirAdvencedPurchaseSalesBookingSummaryExcelSheet2(flightReportInfoMap1, workbook, row1, spreadsheet1);
		} else if (travelReportType.equalsIgnoreCase("HotelSalesReport")) {
			buildHotelBookingSummaryExcelSheet2(flightReportInfoMap1, workbook, row1, spreadsheet1);
		} else if (travelReportType.equalsIgnoreCase("HotelcitywiseSalesReport")) {
			buildHotelBookingSummaryExcelSheet2(flightReportInfoMap1, workbook, row1, spreadsheet1);
		} else if (travelReportType.equalsIgnoreCase("Advancepurchasehotelreport")) {
			buildHotelBookingSummaryExcelSheet2(flightReportInfoMap1, workbook, row1, spreadsheet1);
		} 
		else if (travelReportType.equalsIgnoreCase("plannedAirTripReport")) {
			// do nothing beacuse no summary
		}
		else {
			if(spreadsheet1!=null)
				buildBookingSummaryExcelSheet2(flightReportInfoMap1, workbook, row1, spreadsheet1);
				
			}

		FileOutputStream out;
		try {
			// out = new FileOutputStream(new
			// File("D://excel//"+pdfCreateDate+"_"+"flight_report"+".xlsx"));
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated = true;
			// System.out.println("Writesheet.xlsx written successfully" );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated = false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isGenerated = false;
		}
		return isGenerated;
	}

	private static SortedMap<Integer, List<Object>> buildAirAdvencedPurchaseSalesBookingSummaryExcelSheet2(
			SortedMap<Integer, List<Object>> flightReportInfoMap, XSSFWorkbook workbook, XSSFRow row,
			XSSFSheet spreadsheet) {
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				workbook.getSheetAt(0).autoSizeColumn(rowid);
				
				row = spreadsheet.createRow(rowid++);

				if (row.getRowNum() == 0) {
					row.setHeight((short) 300);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short) 400);
				}

				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(1).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(1).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.BLUE.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}

		}
		return flightReportInfoMap;

	}

	private static List<Object> buildAirAdvencedPurchaseSalesHeadingsForSummary(List<Object> headerList) {
		headerList.add("No of Days in Advance");
		headerList.add("No of Booked Segments");
		headerList.add("Basic Fare");
		headerList.add("Avg. Basic Fare");
		headerList.add("Total Taxes");
		headerList.add("Total Fare");
		headerList.add("Avg. Total Fare");
		return headerList;
	}

	private static SortedMap<Integer, List<Object>> buildAirAdvencedPurchaseSalesDataForSummary(int count,
			SalesReportCalSummary salesReportCalSummary, SortedMap<Integer, List<Object>> flightReportInfoMap1) {
		if (salesReportCalSummary != null && salesReportCalSummary.getAirOrHotelSalesReportList() != null
				&& salesReportCalSummary.getAirOrHotelSalesReportList().size() > 0) {
			for (int j = 0; j < salesReportCalSummary.getAirOrHotelSalesReportList().size(); j++) {
				SalesReportCalSummary salesReportCalSummaryNew = salesReportCalSummary.getAirOrHotelSalesReportList()
						.get(j);
				count++;
				List<Object> dataListNew = new LinkedList<>();
				dataListNew.add(String.valueOf(salesReportCalSummaryNew.getNoofDaysCount()));
				dataListNew.add(String.valueOf(salesReportCalSummaryNew.getNoofSegmentsCount()));
				dataListNew.add(salesReportCalSummaryNew.getTotalTktCost().toString());
				dataListNew.add(salesReportCalSummaryNew.getAvgTktCost().toString());
				dataListNew.add(salesReportCalSummaryNew.getTotalTax().toString());
				dataListNew.add(salesReportCalSummaryNew.getTotalFare().toString());
				dataListNew.add(salesReportCalSummaryNew.getAvgTotalFare().toString());
				flightReportInfoMap1.put(count, dataListNew);
			}

		}

		return flightReportInfoMap1;

	}

	private static List<Object> buildAirAdvencedPurchaseSalesHeadings(List<Object> headerList,
			List<CommonDsrTravelReportData> list, CommonDsrPage commonDsrPage) {
		/*
		 * String gstDateStart="2017-06-30"; String
		 * fromDateNew=commonDsrPage.getCommonDsrFilters().getFromDate(); String
		 * toDateNew=commonDsrPage.getCommonDsrFilters().getToDate();
		 * 
		 * SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		 * DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy"); Date
		 * gstStartDateObj=null; Date fromDate=null; Date toDate=null; try {
		 * gstStartDateObj = targetFormat.parse(gstDateStart); fromDate =
		 * originalFormat.parse(fromDateNew); String formattedFromDate =
		 * targetFormat.format(fromDate); fromDate =
		 * targetFormat.parse(formattedFromDate); toDate =
		 * originalFormat.parse(toDateNew); String formattedToDate =
		 * targetFormat.format(toDate); toDate =
		 * targetFormat.parse(formattedToDate);
		 * 
		 * } catch (ParseException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		headerList.add("Booking Reference");
		headerList.add("Booking Date");
		headerList.add("Passenger Name");
		headerList.add("PNR");
		headerList.add("Ticket Number");
		headerList.add("Travel Date");
		headerList.add("Sectors");
		headerList.add("Total amount");
		return headerList;

	}

	private static List<Object> buildAirAdvencedPurchaseSalesReportData(List<Object> dataList,
			CommonDsrTravelReportData reportData) {

		dataList.add(reportData.getBkgRef());
		dataList.add(reportData.getBookingDate());
		dataList.add(reportData.getTraveller());
		dataList.add(reportData.getAirlinePNRorProvBooking());
		dataList.add(reportData.getTicketNumorFinalBooking());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getDescription());
		dataList.add(reportData.getNetFare());

		return dataList;
	}

	public static SortedMap<Integer, List<Object>> buildBookingDataExcelSheet1(
			SortedMap<Integer, List<Object>> flightReportInfoMap, XSSFWorkbook workbook, XSSFRow row,
			XSSFSheet spreadsheet) {
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
					workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.BLUE.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}

		}
		return flightReportInfoMap;

	}

	public static SortedMap<Integer, List<Object>> buildBookingSummaryExcelSheet2(
			SortedMap<Integer, List<Object>> flightReportInfoMap, XSSFWorkbook workbook, XSSFRow row,
			XSSFSheet spreadsheet) {
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				workbook.getSheetAt(1).autoSizeColumn(rowid);
				row = spreadsheet.createRow(rowid++);

				if (row.getRowNum() == 0) {
					row.setHeight((short) 300);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short) 400);
				}

				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(1).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.BLUE.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}

		}
		return flightReportInfoMap;

	}



	public static SortedMap<Integer, List<Object>> buildAirBookingSummaryExcelSheet2(
			SortedMap<Integer, List<Object>> flightReportInfoMap, XSSFWorkbook workbook, XSSFRow row,
			XSSFSheet spreadsheet) {
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				workbook.getSheetAt(1).autoSizeColumn(rowid);
				row = spreadsheet.createRow(rowid++);

				if (row.getRowNum() == 0) {
					row.setHeight((short)300);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short) 400);
				}

				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(1).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					//workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.BLUE.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}

		}
		return flightReportInfoMap;
	}

	public static SortedMap<Integer, List<Object>> buildHotelBookingSummaryExcelSheet2(
			SortedMap<Integer, List<Object>> flightReportInfoMap, XSSFWorkbook workbook, XSSFRow row,
			XSSFSheet spreadsheet) {
		Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				workbook.getSheetAt(1).autoSizeColumn(rowid);
				row = spreadsheet.createRow(rowid++);

				if (row.getRowNum() == 0) {
					row.setHeight((short)300);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short) 400);
				}
				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(1).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					//workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.BLUE.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}

		}
		return flightReportInfoMap;
		/*Set<Integer> keyid = flightReportInfoMap.keySet();
		int rowid = 0;
		if (keyid != null && keyid.size() > 0) {
			for (Integer key : keyid) {
				workbook.getSheetAt(1).autoSizeColumn(rowid);
				row = spreadsheet.createRow(rowid++);

				if (row.getRowNum() == 0) {
					row.setHeight((short)300);
				}
				if (row.getRowNum() > 0) {
					row.setHeight((short) 400);
				}

				List<Object> objectArr = flightReportInfoMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					workbook.getSheetAt(1).autoSizeColumn(cellid);
					Cell cell = row.createCell(cellid++);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(1).autoSizeColumn(cellid);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.BLUE.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}

					cell.setCellValue((String) obj);
				}

			}

		}
		return flightReportInfoMap;*/

	}

 


	public static List<Object>  buildHotelHeadings(List<Object> headerList,List<CommonDsrTravelReportData> list,CommonDsrPage commonDsrPage) {
		headerList.add("Billing Entity");
		headerList.add("Order ID");
		headerList.add("Invoice ID");
		headerList.add("Booking Status");
		headerList.add("Booking Date");
		headerList.add("Booking Time");
		headerList.add("Booking Employee Name");
		headerList.add("Final Booking / confirmation");
		headerList.add("Guest Name");
		headerList.add("Employee Code");
		headerList.add("Cost Center");
		headerList.add("Department Code");
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){

			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("H") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList= CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");

		
		
		
		/*if (list != null && list.size() > 0) {
			CommonDsrTravelReportData commonDsrTravelReportData = list.get(0);
			if (commonDsrTravelReportData.getExtraRmConfigDetails() == null) {
				Map<String, String> fieldsMap = commonDsrTravelReportData.getRmConfigMap();
				if (fieldsMap != null) {
					for (Map.Entry<String, String> entry : fieldsMap.entrySet()) {
						headerList.add(entry.getKey());
					}
				}
			}

		}
*/
		headerList.add("Traveler Email");
		headerList.add("Traveler Phone");
		headerList.add("Hotel Type");
		headerList.add("Total Guest Count");
		headerList.add("Hotel Name");
		headerList.add("Total Nights");
		headerList.add("Rating");
		headerList.add("City");
		headerList.add("Address");
		headerList.add("Pincode");
		headerList.add("State");
		headerList.add("Country");
		headerList.add("Phone");
		headerList.add("CheckIn");
		headerList.add("CheckOut");
		headerList.add("Base Price");
		headerList.add("Extra Charges");
		headerList.add("Total Taxes");
		if (list != null && list.size() > 0) {
			CommonDsrTravelReportData commonDsrTravelReportData = list.get(0);
			if (commonDsrTravelReportData.getTaxType().equalsIgnoreCase("GST"))
				headerList.add("GST Tax");
			if (commonDsrTravelReportData.getTaxType().equalsIgnoreCase("Service"))
				headerList.add("Service Tax");
		} else
			headerList.add("GST Tax");
		headerList.add("Convenience Fees");
		headerList.add("Management Fees");
		headerList.add("Net Fare");
		return headerList;
	}

	public static List<Object> buildHotelData(List<Object> dataList, CommonDsrTravelReportData reportData,CommonDsrPage commonDsrPage) {
		dataList.add(reportData.getBillingEntity());
		dataList.add(reportData.getBkgRef());
		dataList.add(reportData.getSystemInvoiceId());
		dataList.add(reportData.getTravelStatus());
		dataList.add(reportData.getBookingDate());
		dataList.add(reportData.getBookingTime());
		dataList.add(reportData.getBookerName());
		dataList.add(reportData.getTicketNumorFinalBooking());
		dataList.add(reportData.getTraveller());
		dataList.add(reportData.getEmpCode());
		dataList.add(reportData.getCostCenter());
		dataList.add(reportData.getDepartment());
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("H")  && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				dataList.add(reportData.getExtraRmConfigDetails()!=null &&!reportData.getExtraRmConfigDetails().equals("")?reportData.getExtraRmConfigDetails():"-");
			else{
			 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
			}
		}
		else 
		 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);

		dataList.add(reportData.getTravelerEmail());
		dataList.add(reportData.getTravelerPhone());
		dataList.add(reportData.getFareType());
		dataList.add(String.valueOf(reportData.getGuestCount()));
		dataList.add(reportData.getProductName());
		dataList.add(reportData.getTotalNights());
		dataList.add(reportData.getRating());
		dataList.add(reportData.getCity());
		dataList.add(reportData.getAddress());
		dataList.add(reportData.getPincode());
		dataList.add(reportData.getState());
		dataList.add(reportData.getCountry());
		dataList.add(reportData.getPhone());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getTripEndDate());
		dataList.add(reportData.getBaseFare());
		dataList.add(reportData.getExtraCharge());
		dataList.add(reportData.getOtherTaxes());
		dataList.add(reportData.getServiceTax());
		dataList.add(reportData.getConvenienceCharge());
		dataList.add(reportData.getTayyarahServiceCharges());
		dataList.add(reportData.getNetFare());
		return dataList;

	}

	public static List<Object> buildAirHeadings(List<Object> headerList, List<CommonDsrTravelReportData> list,
			CommonDsrPage commonDsrPage) {
		String gstDateStart = "2017-06-30";
		String fromDateNew = commonDsrPage.getCommonDsrFilters().getFromDate();
		String toDateNew = commonDsrPage.getCommonDsrFilters().getToDate();

		SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date gstStartDateObj = null;
		Date fromDate = null;
		Date toDate = null;
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

		headerList.add("Billing Entity");
		headerList.add("Order ID");
		headerList.add("Invoice ID");
		headerList.add("Booking Status");
		headerList.add("Booking Date");
		headerList.add("Booking Time");
		headerList.add("Booking Employee Name");
		headerList.add("Passenger Name");
		headerList.add("Cost Center");
		headerList.add("Department");
		headerList.add("Emp Code");
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("F") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList= CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");

		
		/*if (list != null && list.size() > 0) {
			CommonDsrTravelReportData commonDsrTravelReportData = list.get(0);
			if(commonDsrTravelReportData.getExtraRmConfigDetails() == null) {
				Map<String, String> fieldsMap = commonDsrTravelReportData.getRmConfigMap();
				if (fieldsMap != null) {
					for (Map.Entry<String, String> entry : fieldsMap.entrySet()) {
						System.out.println("------entry.getKey()----"+entry.getKey());
						headerList.add(entry.getKey());
					}
				}
			}

		}*/
		headerList.add("Traveler Email");
		headerList.add("Traveler Phone");
		headerList.add("Type");
		headerList.add("Airline");
		headerList.add("Segment Number");
		headerList.add("Flight Number");
		headerList.add("Sector");
		headerList.add("Booking Class");
		headerList.add("Cabin class");
		headerList.add("Currency Type");
		headerList.add("Country Code");
		headerList.add("Departure");
		headerList.add("Dep.Time");
		headerList.add("Arrival");
		headerList.add("Arr.Time");
		headerList.add("Airlne Pnr");
		headerList.add("CRS PNR");
		headerList.add("Airline Code");
		headerList.add("Ticket Number");
		headerList.add("Base Fare");
		headerList.add("K3 Tax");
		headerList.add("YQ Tax");
		headerList.add("YR Tax");
		headerList.add("PSF Tax");
		headerList.add("UDF Tax");
		headerList.add("IN Tax");
		headerList.add("JN Tax");
		headerList.add("Other Taxes");
		headerList.add("Meal/ Seat Charges");
		headerList.add("Gross Fare");
		if (fromDate != null && toDate != null) {
			if ((fromDate.compareTo(gstStartDateObj) > 0) && (toDate.compareTo(gstStartDateObj) > 0)) {
				headerList.add("CGST");
				headerList.add("SGST/IGST/UGST");
				headerList.add("Gst Tax");
			}

			else {
				headerList.add("Swach Bharat Cess");
				headerList.add("Krishi Kalyan Cess");
				headerList.add("Service Tax");
			}

		} else {
			headerList.add("CGST");
			headerList.add("SGST/IGST/UGST");
			headerList.add("Gst Tax");
		}
		headerList.add("Convenience Fee");
		headerList.add("Management Fee");
		headerList.add("Net Discount");
		headerList.add("Net Fare");
		return headerList;
	}

	public static List<Object> buildAirData(List<Object> dataList, CommonDsrTravelReportData reportData,CommonDsrPage commonDsrPage) {
		dataList.add(reportData.getBillingEntity());
		dataList.add(reportData.getBkgRef());
		dataList.add(reportData.getSystemInvoiceId());
		dataList.add(reportData.getTravelStatus());
		dataList.add(reportData.getBookingDate());
		dataList.add(reportData.getBookingTime());
		dataList.add(reportData.getBookerName());
		dataList.add(reportData.getTraveller());
		dataList.add(reportData.getCostCenter());
		dataList.add(reportData.getDepartment());
		dataList.add(reportData.getEmpCode());
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("F")  && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				dataList.add(reportData.getExtraRmConfigDetails()!=null &&!reportData.getExtraRmConfigDetails().equals("")?reportData.getExtraRmConfigDetails():"-");
			else{
			 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
			}
		}
		else 
		 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
		dataList.add(reportData.getTravelerEmail());
		dataList.add(reportData.getTravelerPhone());
		dataList.add(reportData.getItineraryType());
		dataList.add(reportData.getProductName());
		dataList.add(reportData.getSegmentNumber());
		dataList.add(reportData.getFlightNumber());
		dataList.add(reportData.getDescription());
		dataList.add(reportData.getBookingClass());
		dataList.add(reportData.getCabinClass());
		dataList.add(reportData.getCorporateCurrency());
		dataList.add(reportData.getCountryId());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getDepTime());
		dataList.add(reportData.getTripEndDate());
		dataList.add(reportData.getArrTime());
		dataList.add(reportData.getGDSPNR());
		dataList.add(reportData.getGDSPNR());
		dataList.add(reportData.getProductCode());
		dataList.add(reportData.getTicketNumorFinalBooking());
		dataList.add(reportData.getBaseFare());
		dataList.add(reportData.getK3Tax());
		dataList.add(reportData.getYQTax());
		dataList.add(reportData.getYRTax());
		dataList.add(reportData.getPSFTax());
		dataList.add(reportData.getUDFTax());
		dataList.add(reportData.getINTax());
		dataList.add(reportData.getJNTax());
		dataList.add(reportData.getOtherTaxes());
		dataList.add(reportData.getExtraCharge());
		dataList.add(reportData.getGrossFare());
		if (reportData.getTaxType() != null && reportData.getTaxType().equalsIgnoreCase("GST")) {
			dataList.add(reportData.getCGST());
			dataList.add(reportData.getSGSTorUGSTorIGST());
			dataList.add(reportData.getTotGstTax());
		} else {
			dataList.add(reportData.getSwachBharatCess());
			dataList.add(reportData.getKrishiKalyanCess());
			dataList.add(reportData.getServiceTaxBase());
		}

		dataList.add(reportData.getConvenienceCharge());
		dataList.add(reportData.getTayyarahServiceCharges());
		dataList.add(reportData.getNetDiscount());
		dataList.add(reportData.getNetFare());
		return dataList;

	}

	public static List<Object> buildCarHeadings(List<Object> headerList,List<CommonDsrTravelReportData> list,CommonDsrPage commonDsrPage) {
		headerList.add("Order ID");
		headerList.add("Booking Status");
		headerList.add("Traveller");
		headerList.add("Approver Name");
		headerList.add("Bill NoBill");
		headerList.add("Business Unit");
		headerList.add("Cost Center");
		headerList.add("Emp Code");
		headerList.add("Travel Request Number");
		headerList.add("Location");
		headerList.add("Department");
		headerList.add("Project Code");
		headerList.add("Reason for Travel");
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("C") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList= CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");

		headerList.add("Confirmation Number");
		headerList.add("Pick up / Drop");
		headerList.add("Car Type");
		headerList.add("Description");
		headerList.add("Date of Travel");
		headerList.add("Tkt Cost");
		headerList.add("Total Invoice Value");
		return headerList;
	}

	public static List<Object> buildCarData(List<Object> dataList, CommonDsrTravelReportData reportData,CommonDsrPage commonDsrPage) {
		dataList.add(reportData.getSystemInvoiceId());
		dataList.add(reportData.getTravelStatus());
		dataList.add(reportData.getTraveller());
		dataList.add(reportData.getApproverName());
		dataList.add(reportData.getBillNonBill());
		dataList.add(reportData.getBusinessUnit());
		dataList.add(reportData.getCostCenter());
		dataList.add(reportData.getEmpCode());
		dataList.add(reportData.getTravelRequestNumber());
		dataList.add(reportData.getLocation());
		dataList.add(reportData.getDepartment());
		dataList.add(reportData.getProjectCode());
		dataList.add(reportData.getReasonForTravel());
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("C")  && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				dataList.add(reportData.getExtraRmConfigDetails()!=null && !reportData.getExtraRmConfigDetails().equals("")?reportData.getExtraRmConfigDetails():"-");
			else{
			 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
			}
		}
		else 
		 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
	 
		dataList.add(reportData.getGDSPNR());
		dataList.add(reportData.getDescription());
		dataList.add(reportData.getAmendmentType());
		dataList.add(reportData.getDescription2());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getGrossFare());
		dataList.add(reportData.getNetFare());
		return dataList;

	}

	public static List<Object> buildTrainHeadings(List<Object> headerList,List<CommonDsrTravelReportData> list,CommonDsrPage commonDsrPage) {
		headerList.add("Order ID");
		headerList.add("Booking Status");
		headerList.add("Pax Name");
		headerList.add("Approver Name");
		headerList.add("Bill NoBill");
		headerList.add("Business Unit");
		headerList.add("Cost Center");
		headerList.add("Emp Code");
		headerList.add("Travel Request Number");
		headerList.add("Location");
		headerList.add("Department");
		headerList.add("Project Code");
		headerList.add("Reason for Travel");
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if((commonDsrPage.getCommonDsrFilters().getTravelType().equals("T") || commonDsrPage.getCommonDsrFilters().getTravelType().equals("B")) && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList= CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");

		headerList.add("PNR/Tkt Number");
		headerList.add("Sector");
		headerList.add("Date of Travel");
		headerList.add("Tkt Cost");
		headerList.add("Total Invoice Value");
		return headerList;

	}

	public static List<Object> buildTrainData(List<Object> dataList, CommonDsrTravelReportData reportData,CommonDsrPage commonDsrPage) {
		dataList.add(reportData.getSystemInvoiceId());
		dataList.add(reportData.getTravelStatus());
		dataList.add(reportData.getTraveller());
		dataList.add(reportData.getApproverName());
		dataList.add(reportData.getBillNonBill());
		dataList.add(reportData.getBusinessUnit());
		dataList.add(reportData.getCostCenter());
		dataList.add(reportData.getEmpCode());
		dataList.add(reportData.getTravelRequestNumber());
		dataList.add(reportData.getLocation());
		dataList.add(reportData.getDepartment());
		dataList.add(reportData.getProjectCode());
		dataList.add(reportData.getReasonForTravel());
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if((commonDsrPage.getCommonDsrFilters().getTravelType().equals("T") || commonDsrPage.getCommonDsrFilters().getTravelType().equals("B"))  && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				dataList.add(reportData.getExtraRmConfigDetails()!=null && !reportData.getExtraRmConfigDetails().equals("")?reportData.getExtraRmConfigDetails():"-");
			else{
			 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
			}
		}
		else 
		 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
		dataList.add(reportData.getGDSPNR());
		dataList.add(reportData.getDescription());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getGrossFare());
		dataList.add(reportData.getNetFare());
		return dataList;

	}

	public static List<Object> buildVisaHeadings(List<Object> headerList,List<CommonDsrTravelReportData> list,CommonDsrPage commonDsrPage) {
		headerList.add("Order ID");
		headerList.add("Booking Status");
		headerList.add("Traveller");
		headerList.add("Approver Name");
		headerList.add("Bill NoBill");
		headerList.add("Business Unit");
		headerList.add("Cost Center");
		headerList.add("Emp Code");
		headerList.add("Travel Request Number");
		headerList.add("Location");
		headerList.add("Department");
		headerList.add("Project Code");
		headerList.add("Reason for Travel");
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("V")  && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList= CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
		 
		headerList.add("Confirmation Number");
		headerList.add("Visa Type / Description	");
		headerList.add("Date of Travel");
		headerList.add("Visa Cost");
		headerList.add("Total Invoice Value");
		return headerList;
	}

	public static List<Object> buildVisaData(List<Object> dataList, CommonDsrTravelReportData reportData,CommonDsrPage commonDsrPage) {
		dataList.add(reportData.getSystemInvoiceId());
		dataList.add(reportData.getTravelStatus());
		dataList.add(reportData.getTraveller());
		dataList.add(reportData.getApproverName());
		dataList.add(reportData.getBillNonBill());
		dataList.add(reportData.getBusinessUnit());
		dataList.add(reportData.getCostCenter());
		dataList.add(reportData.getEmpCode());
		dataList.add(reportData.getTravelRequestNumber());
		dataList.add(reportData.getLocation());
		dataList.add(reportData.getDepartment());
		dataList.add(reportData.getProjectCode());
		dataList.add(reportData.getReasonForTravel());
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("V")  && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				dataList.add(reportData.getExtraRmConfigDetails()!=null &&!reportData.getExtraRmConfigDetails().equals("")?reportData.getExtraRmConfigDetails():"-");
			else{
			 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
			}
		}
		else 
		 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
	 
		dataList.add(reportData.getGDSPNR());
		dataList.add(reportData.getDescription());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getGrossFare());
		dataList.add(reportData.getNetFare());
		return dataList;

	}

	public static List<Object> buildInsuranceHeadings(List<Object> headerList,List<CommonDsrTravelReportData> list,CommonDsrPage commonDsrPage) {
		headerList.add("Order ID");
		headerList.add("Booking Status");
		headerList.add("Traveller");
		headerList.add("Approver Name");
		headerList.add("Bill NoBill");
		headerList.add("Business Unit");
		headerList.add("Cost Center");
		headerList.add("Emp Code");
		headerList.add("Travel Request Number");
		headerList.add("Location");
		headerList.add("Department");
		headerList.add("Project Code");
		headerList.add("Reason for Travel");
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("I") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList= CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
		headerList.add("Confirmation Number");
		headerList.add("Insurance Type / Description");
		headerList.add("Date of Travel");
		headerList.add("Insurance Cost");
		headerList.add("Total Invoice Value");
		return headerList;
	}

	public static List<Object> buildInsuranceData(List<Object> dataList, CommonDsrTravelReportData reportData,CommonDsrPage commonDsrPage) {
		dataList.add(reportData.getSystemInvoiceId());
		dataList.add(reportData.getTravelStatus());
		dataList.add(reportData.getTraveller());
		dataList.add(reportData.getApproverName());
		dataList.add(reportData.getBillNonBill());
		dataList.add(reportData.getBusinessUnit());
		dataList.add(reportData.getCostCenter());
		dataList.add(reportData.getEmpCode());
		dataList.add(reportData.getTravelRequestNumber());
		dataList.add(reportData.getLocation());
		dataList.add(reportData.getDepartment());
		dataList.add(reportData.getProjectCode());
		dataList.add(reportData.getReasonForTravel());
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if((commonDsrPage.getCommonDsrFilters().getTravelType().equals("I")|| commonDsrPage.getCommonDsrFilters().getTravelType().equals("M")) && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				dataList.add(reportData.getExtraRmConfigDetails()!=null &&!reportData.getExtraRmConfigDetails().equals("")?reportData.getExtraRmConfigDetails():"-");
			else{
			 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
			}
		}
		else 
		 CommonDsrTravelReportsExportToExcel.getExcelData(reportData,dataList);
	 
		dataList.add(reportData.getGDSPNR());
		dataList.add(reportData.getDescription());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getGrossFare());
		dataList.add(reportData.getNetFare());
		return dataList;

	}
	public static List<Object>  buildMiscellaneousHeadings(List<Object> headerList,List<CommonDsrTravelReportData> list,CommonDsrPage commonDsrPage){
		headerList.add("Order ID");
		headerList.add("Booking Status");
		headerList.add("Traveller");
		headerList.add("Approver Name");
		headerList.add("Bill NoBill");
		headerList.add("Business Unit");
		headerList.add("Cost Center");
		headerList.add("Emp Code");
		headerList.add("Travel Request Number");
		headerList.add("Location");
		headerList.add("Department");
		headerList.add("Project Code");
		headerList.add("Reason for Travel");
		Company sessionCompany = commonDsrPage.getCommonDsrFilters().getLoginCompany();
		if(sessionCompany.getCompanyRole().isSuperUser()){
			if(commonDsrPage.getCommonDsrFilters().getTravelType().equals("M") && commonDsrPage.getCommonDsrFilters().getCompanyUserId().equals("")) 
				headerList.add("Extra RmConfig Details");

			else{
				headerList= CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
			}
		}
		else 
			headerList=CommonDsrTravelReportsExportToExcel.getExcelHeadings(list, headerList,"K");
		headerList.add("Confirmation Number");
		headerList.add("Miscellaneous Type / Description");
		headerList.add("Date of Travel");
		headerList.add("Miscellaneous Cost");
		headerList.add("Total Invoice Value");
		return headerList;
	}
 
	public static List<Object>  buildAirHeadingsForSummary(List<Object> headerList) {
		headerList.add("Airline");
		headerList.add("Total count");
		headerList.add("Cancelled Count");
		headerList.add("Total Invoice Amount");
		headerList.add("Total Refund Amount");
		headerList.add("Net Sale");
		headerList.add("Avg Net Fare");
		return headerList;

	}

	public static List<Object> buildHeadingsForSummary(List<Object> headerList) {
		headerList.add("Total Count");
		headerList.add("cancelled Count");
		headerList.add("Total Invoice Amount");
		headerList.add("Total Refund Amount");
		headerList.add("Net Sale");
		headerList.add("Avg.Net Fare");
		return headerList;

	}

	public static List<Object> buildAirRouteWiseHeadingsForSummary(List<Object> headerList) {
		headerList.add("Routing");
		headerList.add("Booked trips");
		headerList.add("Cancelled trips");
		headerList.add("Total Invoice Amount");
		headerList.add("Total Refund Amount");
		headerList.add("Net Sale");
		headerList.add("Avg Net Fare");
		/*headerList.add("Total Tkt Price");
		headerList.add("Total  Fare");
		headerList.add("Avg.Tkt Price");
		headerList.add("Avg. Total  Fare");*/
		return headerList;

	}

	public static List<Object> buildHotelHeadingsForSummary(List<Object> headerList) {
		headerList.add("City");
		headerList.add("Total Booked No.of room nights");
		headerList.add("Total Cancelled No.of room nights");
		headerList.add("Total Invoice Amount");
		headerList.add("Total Refund Amount");
		headerList.add("Net Sale");
		headerList.add("Avg Net Fare");
		return headerList;

	}

	public static List<Object> buildAdvancepurchasehotelHeadingsForSummary(List<Object> headerList) {
		headerList.add("No of Days in advance");
		headerList.add("No of rooms Booked");
		headerList.add("No of Nights Booked");
		headerList.add("Basic fare");
		headerList.add("Avg Basics");
		headerList.add("Taxes");
		headerList.add("Total Fare");
		headerList.add("Avg Total");
		return headerList;

	}

	public static SortedMap<Integer, List<Object>> buildAdvancepurchasehotelDataForSummary(int count,
			SalesReportCalSummary salesReportCalSummary, SortedMap<Integer, List<Object>> flightReportInfoMap1) {
		if (salesReportCalSummary != null) {
			List<Object> dataListNew = new LinkedList<>();
			dataListNew.add(String.valueOf(salesReportCalSummary.getNoofDaysInAdvance()));
			dataListNew.add(String.valueOf(salesReportCalSummary.getNoOfRooms()));
			dataListNew.add(String.valueOf(salesReportCalSummary.getNoofBookedNights()));
			dataListNew.add(salesReportCalSummary.getBaseFare().toString());
			dataListNew.add(salesReportCalSummary.getAvgBaseRoomNightCost().toString());
			dataListNew.add(salesReportCalSummary.getTaxes().toString());
			dataListNew.add(salesReportCalSummary.getTotalFare().toString());
			dataListNew.add(salesReportCalSummary.getAvgTotalRoomNightCost().toString());
			flightReportInfoMap1.put(count, dataListNew);
		}
		return flightReportInfoMap1;
	}

	public static List<Object> buildDataForSummary(List<Object> dataList1,
			SalesReportCalSummary salesReportCalSummary) {
		String totalCount = String.valueOf(salesReportCalSummary.getTotalCount());
		String cancelledCount = String.valueOf(salesReportCalSummary.getCancelledCount());
		dataList1.add(totalCount);
		dataList1.add(cancelledCount);
		dataList1.add(salesReportCalSummary.getTotalFare().toString());
		dataList1.add(salesReportCalSummary.getTotalTktCost().toString());
		dataList1.add(salesReportCalSummary.getAvgTktCost().toString());
		dataList1.add(salesReportCalSummary.getAvgTotalFare().toString());
		return dataList1;

	}

	private static SortedMap<Integer, List<Object>> buildAirRouteWiseDataForSummary(int count,
			SalesReportCalSummary salesReportCalSummary, SortedMap<Integer, List<Object>> flightReportInfoMap1) {
		if (salesReportCalSummary != null && salesReportCalSummary.getAirOrHotelSalesReportList() != null
				&& salesReportCalSummary.getAirOrHotelSalesReportList().size() > 0) {
			for (int j = 0; j < salesReportCalSummary.getAirOrHotelSalesReportList().size(); j++) {
				SalesReportCalSummary salesReportCalSummaryNew = salesReportCalSummary.getAirOrHotelSalesReportList()
						.get(j);
				count++;
				List<Object> dataListNew = new LinkedList<>();
				dataListNew.add(salesReportCalSummaryNew.getRouting());
				dataListNew.add(String.valueOf(salesReportCalSummaryNew.getTotalCount()));
				dataListNew.add(String.valueOf(salesReportCalSummaryNew.getCancelledCount()));
				dataListNew.add(salesReportCalSummaryNew.getTotalFare().toString());
				dataListNew.add(salesReportCalSummaryNew.getRefundAmount().toString());
				dataListNew.add(salesReportCalSummaryNew.getNetSale().toString());
				dataListNew.add(salesReportCalSummaryNew.getAvgTotalFare().toString());
				flightReportInfoMap1.put(count, dataListNew);
			}

		}
		return flightReportInfoMap1;

	}
	

	public static SortedMap<Integer, List<Object>> buildAirDataForSummary(int count,
			SalesReportCalSummary salesReportCalSummary, SortedMap<Integer, List<Object>> flightReportInfoMap1) {
		if (salesReportCalSummary != null && salesReportCalSummary.getAirOrHotelSalesReportList() != null
				&& salesReportCalSummary.getAirOrHotelSalesReportList().size() > 0) {
			for (int j = 0; j < salesReportCalSummary.getAirOrHotelSalesReportList().size(); j++) {
				SalesReportCalSummary salesReportCalSummaryNew = salesReportCalSummary.getAirOrHotelSalesReportList()
						.get(j);
				count++;
				List<Object> dataListNew = new LinkedList<>();
				dataListNew.add(salesReportCalSummaryNew.getAirlineOrHotel());
				dataListNew.add(String.valueOf(salesReportCalSummaryNew.getTotalCount()));
				dataListNew.add(String.valueOf(salesReportCalSummaryNew.getCancelledCount()));
				dataListNew.add(salesReportCalSummaryNew.getTotalFare().toString());
				dataListNew.add(salesReportCalSummaryNew.getCancelledTktAmount().toString());
				dataListNew.add(salesReportCalSummaryNew.getNetSale().toString());
				dataListNew.add(salesReportCalSummaryNew.getAvgTotalFare().toString());
				flightReportInfoMap1.put(count, dataListNew);
			}

		}
		return flightReportInfoMap1;
	}

	public static SortedMap<Integer, List<Object>> buildHotelDataForSummary(int count,
			SalesReportCalSummary salesReportCalSummary, SortedMap<Integer, List<Object>> flightReportInfoMap1) {
		if (salesReportCalSummary != null && salesReportCalSummary.getAirOrHotelSalesReportList() != null
				&& salesReportCalSummary.getAirOrHotelSalesReportList().size() > 0) {
			for (int j = 0; j < salesReportCalSummary.getAirOrHotelSalesReportList().size(); j++) {
				SalesReportCalSummary salesReportCalSummaryNew = salesReportCalSummary.getAirOrHotelSalesReportList()
						.get(j);
				count++;
				List<Object> dataListNew = new LinkedList<>();
				dataListNew.add(salesReportCalSummaryNew.getAirlineOrHotel());
				dataListNew.add(String.valueOf(salesReportCalSummaryNew.getTotalCount()));
				dataListNew.add(String.valueOf(salesReportCalSummaryNew.getCancelledCount()));
				dataListNew.add(salesReportCalSummaryNew.getTotalFare().toString());
				dataListNew.add(salesReportCalSummaryNew.getCancelledTktAmount().toString());
				dataListNew.add(salesReportCalSummaryNew.getNetSale().toString());
				dataListNew.add(salesReportCalSummaryNew.getAvgTotalFare().toString());
				flightReportInfoMap1.put(count, dataListNew);
			}

		}
		return flightReportInfoMap1;
	}

	public static List<Object> buildHotelCityWiseHeadings(List<Object> headerList) {
		headerList.add("Hotel Name");
		headerList.add("Guest Name");
		headerList.add("City");
		headerList.add("Check In");
		headerList.add("Check Out");
		headerList.add("No of RoomNights");
		headerList.add("Booking status");
		headerList.add("Total hotel Cost");
		headerList.add("Total invoice Price");
		return headerList;
	}

	public static List<Object> buildAdvancepurchasehotelHeadings(List<Object> headerList) {
		headerList.add("Booking Reference");
		headerList.add("Booking Date");
		headerList.add("Passenger Name");
		headerList.add("Hotel Name");
		headerList.add("city Name");
		headerList.add("Check in Date");
		headerList.add("Check Out date");
		headerList.add("Total invoice Amount");
		return headerList;
	}

	public static List<Object> buildAdvancepurchasehotelData(List<Object> dataList,
			CommonDsrTravelReportData reportData) {
		dataList.add(reportData.getSystemInvoiceId());
		dataList.add(reportData.getBookingDate());
		dataList.add(reportData.getBookerName());
		dataList.add(reportData.getHotelName());
		dataList.add(reportData.getCity());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getTripEndDate());
		dataList.add(reportData.getNetFare());
		return dataList;

	}

	public static List<Object> buildHotelCityWiseHeadingsForSummary(List<Object> headerList) {
		headerList.add("Total No. of Room Nights");
		headerList.add("Total Room Cancellations");
		headerList.add("Avg Base room night cost");
		headerList.add("Avg Total room night cost");
		return headerList;

	}

	public static SortedMap<Integer, List<Object>> buildHotelCityWiseBookingDataExcelSheet1(
			SortedMap<Integer, List<Object>> flightReportInfoMap, XSSFWorkbook workbook, XSSFRow row,
			XSSFSheet spreadsheet) {
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
					workbook.getSheetAt(0).setDefaultRowHeight((short) 5000);
					if (cell.getRowIndex() == 0) {
						workbook.getSheetAt(0).autoSizeColumn(0);
						XSSFCellStyle style = workbook.createCellStyle();
						style.setFillForegroundColor(HSSFColor.BLUE.index);
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
						XSSFFont font = workbook.createFont();
						font.setColor(HSSFColor.WHITE.index);
						style.setFont(font);
						cell.setCellStyle(style);
					}

					try {
						cell.setCellValue((String) obj);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

		}
		return flightReportInfoMap;

	}

	public static List<Object> buildHotelCityWiseData(List<Object> dataList, CommonDsrTravelReportData reportData) {
		dataList.add(reportData.getHotelName());
		dataList.add(reportData.getGuestName());
		dataList.add(reportData.getCity());
		dataList.add(reportData.getCheckInDate());
		dataList.add(reportData.getCheckOutDate());
		dataList.add(reportData.getNoOfNights());
		dataList.add(reportData.getStatus());
		dataList.add(reportData.getBasePrice());
		dataList.add(reportData.getTotalInvoiceAmount());
		return dataList;
	}

	public static SortedMap<Integer, List<Object>> buildcityWiseDataForSummary(int count,
			SalesReportCalSummary salesReportCalSummary, SortedMap<Integer, List<Object>> cityWiseReportInfoMap1) {
		if (salesReportCalSummary != null) {
			List<Object> dataListNew = new LinkedList<>();
			dataListNew.add(String.valueOf(salesReportCalSummary.getNoofBookedNights()));
			dataListNew.add(String.valueOf(salesReportCalSummary.getNoofancelledNights()));
			dataListNew
					.add(salesReportCalSummary.getAvgBaseRoomNightCost().setScale(2, RoundingMode.HALF_UP).toString());
			dataListNew
					.add(salesReportCalSummary.getAvgTotalRoomNightCost().setScale(2, RoundingMode.HALF_UP).toString());

			cityWiseReportInfoMap1.put(count, dataListNew);
		}
		return cityWiseReportInfoMap1;
	}
	public static List<Object> buildplannedAirTripReportHeadings(List<Object> headerList,List<CommonDsrTravelReportData> list) {
		headerList.add("Cart/orderId");
		headerList.add("Invoice Id");
		headerList.add("Ticket Status");
		headerList.add("Travel Date");
		headerList.add("Sector");
		headerList.add("Traveller");
		headerList.add("Employee Code");
		headerList.add("Band Code");
		headerList.add("Department Code");
		headerList.add("Location Code");
		headerList.add("Flight");
		headerList.add("Departure Time");
		headerList.add("Arrival Time");
		headerList.add("Airline PNR");
		headerList.add("GDS PNR");
		headerList.add("Ticket Number");
		headerList.add("Gross Amount");
		headerList.add("Email");
		headerList.add("Mobile");
		headerList.add("Phone");
		headerList.add("Employee ID");
		headerList.add("Business Unit");
		headerList.add("APPROVED BY");
		headerList.add("Department");
		
		return headerList;
	}
	
	public static List<Object> buildPlannedAirTripReportData(List<Object> dataList, CommonDsrTravelReportData reportData) {
		dataList.add(reportData.getOrderId());
		dataList.add(reportData.getSystemInvoiceId());
		dataList.add(reportData.getStatus());
		dataList.add(reportData.getTripStartsDate());
		dataList.add(reportData.getDescription());
		dataList.add(reportData.getTraveller());
		dataList.add(reportData.getEmpCode());
		dataList.add(reportData.getBandCode());
		dataList.add(reportData.getDepartment());
		dataList.add(reportData.getLocation());
		dataList.add(reportData.getFlightNumber());
		dataList.add(reportData.getDepTime());
		dataList.add(reportData.getArrTime());
		dataList.add(reportData.getAirlinePnr());
		dataList.add(reportData.getGDSPNR());
		dataList.add(reportData.getTicketNumorFinalBooking());
		dataList.add(reportData.getGrossFare());
		dataList.add(reportData.getTravelerEmail());
		dataList.add(reportData.getMobile());
		dataList.add(reportData.getTravelerPhone());
		dataList.add(reportData.getEmpCode());
		dataList.add(reportData.getBusinessUnit());
		dataList.add(reportData.getApproverName());
		dataList.add(reportData.getDepartment());
		return dataList;

	}
	
	
	 
}
