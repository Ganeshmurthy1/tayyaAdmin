package com.common.dsr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.ageing.report.dao.AgeingReportDaoImpl;
import com.admin.ageing.report.dto.AgeingReportVO;
import com.admin.common.commonDsrReportConfg.CommonDsrReportConfg;
import com.admin.common.commonDsrReportConfg.dao.CommonDsrReportConfgDao;
import com.common.salesreport.dsr.SalesReportCalSummary;
import com.common.salesreport.dsr.SalesReportDao;
import com.common.salesreport.dsr.SalesReportsExportToExcel;
import com.lintas.admin.model.Company;
import com.lintas.utility.AgeingReportsExportToExcel;
import com.lintas.utility.CommonDsrTravelReportsExportToExcel;
import com.lintas.utility.OutStandingTravelReportsExportToExcel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommonDsrReportExcelDownloadAction  extends ActionSupport implements ModelDriven<CommonDsrPage>,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InputStream fileInputStream;
	// Used to set file name dynamically
	private String fileName;
	private String taxType;;
	private CommonDsrPage commonDsrPage=new CommonDsrPage();
	CommonDsrDao commonDsrDao=new CommonDsrDao();
	OutStandingDao outStandingDao=new OutStandingDao();
	AgeingReportDaoImpl ageingReportDao=new  AgeingReportDaoImpl();
	SalesReportDao salesReportDao=new SalesReportDao();
	SessionMap<String, Object> sessionMap;
	private boolean isReportGenerated=false; 
	private long lengthOfMyFile;
	public String  execute(){
		Company sessionCompany=(Company) sessionMap.get("Company");
		File fileToDownload=null;
		String path=getText("global.upload_download_file_path");
		CommonDsrFilters	commonDsrFilters =commonDsrPage.getCommonDsrFilters();
		commonDsrFilters.setLoginCompany(sessionCompany);
		commonDsrFilters.setTravelType(commonDsrPage.getCommonDsrFilters().getTravelType());
		commonDsrFilters.setTravelReportType(commonDsrPage.getCommonDsrFilters().getTravelReportType());
		commonDsrPage.setCommonDsrFilters(commonDsrFilters);
		String file="CommonDSRReport.xls";
		List<CommonDsrTravelReportData> commonDsrTravelReports=null;
		  AgeingReportVO ageingReportVO=null;
		List<CommonDsrMissedFlightReport> commonDsrMissedFlightReports=null;
		SalesReportCalSummary salesReportCalSummary=null;
		String travelReportType=commonDsrPage.getCommonDsrFilters().getTravelReportType();
		String sheetName="";
		//CommonDsrReportConfg commonDsrReportConfg=new CompanyConfigDao().getCommonDsrReportConfg(sessionCompany.getCompanyid());
		CommonDsrReportConfg commonDsrReportConfg=null;
		switch (travelReportType) {
		case "All":
			commonDsrTravelReports =commonDsrDao.getCommonDsrTravelReportsToExportExcel(commonDsrPage);
			CommonDsrTravelReportsExportToExcel.generateCommonTravelReportExcel(commonDsrTravelReports, path,sessionCompany,commonDsrPage);
			break; 
		case "OutstandingReport":
			file="OutstandingReport.xlsx";
			commonDsrTravelReports =outStandingDao.getOutStandingDsrTravelReportsToExportExcel(commonDsrPage);
			OutStandingTravelReportsExportToExcel.generateOutstandingTravelReportExcel(commonDsrTravelReports, path,sessionCompany,commonDsrPage,file);
			break; 
		case "AgingReport":
			file="AgingReport.xlsx";
			ageingReportVO =ageingReportDao.getAgeingReportVO(null);
			AgeingReportsExportToExcel.generateAgeingReporExcel(ageingReportVO, path,sessionCompany,commonDsrPage,file);
			break; 
			 
		case "AirSalesReport":
			sheetName="Air Sales Report Info";
			file="AirSales.xls";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
			//ADDED BY BASHA FOR AIR ROUTE WISE REPORT
		case "AirRouteWiseSalesReport":                                                                                                                                               
			sheetName="Air Route Wise Sales Report Info";
			file="AirRouteWiseSales.xlsx";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
			//ADDED BY BASHA FOR Air Advanced Purchase Sales  REPORT
		case "AirAdvencedPurchaseSalesReport":
			sheetName="Air Advenced Purchase Sales Report Info";
			file="AirAdvencedPurchaseSalesReport.xlsx";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
			
		case "plannedAirTripReport":
			sheetName="Planned Air Trip Report Report Info";
			file="plannedAirTripReportSalesReport.xlsx";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
			
		case "HotelSalesReport":
			sheetName="Hotel Sales Report Info";
			file="HotelSales.xls";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
		case "HotelcitywiseSalesReport":
			sheetName="Hotel City Wise Sales Report Info";
			file="HotelCityWiseSales.xlsx";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
		case "Advancepurchasehotelreport":	
			sheetName="Advance Purchase Hotel Report Info";
			file="HotelAdvanceSales.xlsx";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
			
		case "CarSalesReport":
			sheetName="Car Sales Report Info";
			file="CarSales.xls";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
		case "BusSalesReport":
			sheetName="Bus Sales Report Info";
			file="BusSales.xls";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
		case "TrainSalesReport":
			sheetName="Train Sales Report Info";
			file="TrainSales.xls";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
		case "VisaSalesReport":
			sheetName="Visa Sales Report Info";
			file="VisaSales.xls";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
		case "InsuranceSalesReport":
			sheetName="Insurance Sales Report Info";
			file="InsuranceSales.xls";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
		case "MiscellaneousSalesReport":
			sheetName="Miscellaneous Sales Report Info";
			file="MiscellaneousSales.xls";
			salesReportCalSummary =salesReportDao.generateSalesReportsToExportExcel(commonDsrPage);
			SalesReportsExportToExcel.generateSalesReportExcel(salesReportCalSummary,path,file,commonDsrPage,sheetName);
			break;
		case "CustomDsrReport":
			sheetName="Custom Dsr Report Info";
			file="CustomDsrReport.xls";
			commonDsrReportConfg=new CommonDsrReportConfgDao().getCommonDsrReportConfgByCompanyId(sessionCompany.getCompanyid());
			 commonDsrTravelReports =commonDsrDao.getCommonDsrTravelReportsToExportExcel(commonDsrPage);
			 if(commonDsrReportConfg!=null){
				CommonDsrTravelReportsExportToExcel.generateCustomDsrTravelReportExcel(commonDsrTravelReports, path,sessionCompany,commonDsrPage,commonDsrReportConfg);
			 }else{
				 CommonDsrReportConfg commonDsrReportConfgnew=new CommonDsrReportConfg();
				CommonDsrTravelReportsExportToExcel.generateCustomDsrTravelReportExcel(commonDsrTravelReports, path,sessionCompany,commonDsrPage,commonDsrReportConfgnew);
				 
			 }
			 break;
		case "AirMissedSavingReport":
			sheetName="Air Missed Saving Report Info";
			file="AirMissedSavingReport.xlsx";
			commonDsrMissedFlightReports =new  MissedSavingReportDao().getCommonDsrMissedSavingReport(commonDsrPage);
			CommonDsrTravelReportsExportToExcel.generateMissedAirReportExcel(commonDsrMissedFlightReports, path,sessionCompany,commonDsrPage);
			break;
		default:
			commonDsrTravelReports =commonDsrDao.getCommonDsrTravelReportsToExportExcel(commonDsrPage);
			CommonDsrTravelReportsExportToExcel.generateCommonTravelReportExcel(commonDsrTravelReports, path,sessionCompany,commonDsrPage);
			break;
		} 

		fileToDownload = new File(path+"excel/"+file);
		try {
			fileName = fileToDownload.getName();
			fileInputStream = new FileInputStream(fileToDownload);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} 
		return SUCCESS; 

	}
	public InputStream getFileInputStream() 
	{
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public CommonDsrPage getModel() {
		// TODO Auto-generated method stub
		return commonDsrPage;
	}
	public CommonDsrPage getCommonDsrPage() {
		return commonDsrPage;
	}
	public void setCommonDsrPage(CommonDsrPage commonDsrPage) {
		this.commonDsrPage = commonDsrPage;
	}
	public boolean isReportGenerated() {
		return isReportGenerated;
	}
	public void setReportGenerated(boolean isReportGenerated) {
		this.isReportGenerated = isReportGenerated;
	}
	public long getLengthOfMyFile() {
		return lengthOfMyFile;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

}
