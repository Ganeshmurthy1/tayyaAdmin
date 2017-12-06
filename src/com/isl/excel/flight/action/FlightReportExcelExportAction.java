package com.isl.excel.flight.action;

import java.io.InputStream;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.excel.flight.dao.FlightReportExcelDao;
import com.opensymphony.xwork2.ActionSupport;

public class FlightReportExcelExportAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String , Object> sessionMap;
	private String reportType;
	private InputStream fileInputStream;
    // Used to set file name dynamically
    private String fileName="rahamFlight.xlsx";
	FlightReportExcelDao flightReportExcelDao=new FlightReportExcelDao();
	public String execute(){
		/*logger.info("reportType-----------"+reportType);
		Company companySessionObj=(Company)sessionMap.get("Company");
		FlightReportPage flightReportPage=new FlightReportPage();
		flightReportPage.getFlightReportFilter().setReportType(Integer.parseInt(reportType));
		flightReportPage.getFlightReportFilter().setLoginCompany(companySessionObj);
		FlightReportPage newFlightReportPage=flightReportExcelDao.getCompanyFlightReports(flightReportPage);
		logger.info("newFlightReportPage-----size------"+newFlightReportPage.getItems().size());
		
		if(newFlightReportPage!=null){
			ExportReportsToExcel.generateFlightReportExcel(newFlightReportPage,null);
		}
		  File fileToDownload = new File("D://excel//"+fileName+"");
          fileName = fileToDownload.getName();
          try {
			fileInputStream = new FileInputStream(fileToDownload);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return SUCCESS;

	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	 
     
     public InputStream getFileInputStream() 
     {
             return fileInputStream;
     }

     

     public String getFileName() 
     {
             return fileName;
     }

     public void setFileName(String fileName) 
     {
             this.fileName = fileName;
     }       




}
