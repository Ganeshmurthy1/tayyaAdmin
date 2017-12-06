package com.common.dsr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommonDisReportViewPageAction extends ActionSupport implements ModelDriven<CommonDsrPage>,SessionAware{

	/**
	 * @Author     : Manish Samrat
	 * @CreatedAt  : 27/03/2017
	 */
	private static final long serialVersionUID = 1L;

	private InputStream fileInputStream;
	// Used to set file name dynamically
	private String fileName;
	private CommonDsrPage commonDsrPage=new CommonDsrPage();
	CommonDsrViewFilterDao commonDsrDao=new CommonDsrViewFilterDao();
	SessionMap<String, Object> sessionMap;
	List<FlightOrderRow> flightOrderRow=null; 
	List<CommonDsrTravelReportData> commonDsrTravelReports =null;

	public String  getCommonDsrTravelReportDataList(){
		Company sessionCompany=(Company) sessionMap.get("Company");
		CommonDsrFilters	commonDsrFilters =commonDsrPage.getCommonDsrFilters();
		commonDsrFilters.setLoginCompany(sessionCompany);
		commonDsrFilters.setTravelType(commonDsrPage.getCommonDsrFilters().getTravelType());
		commonDsrFilters.setTravelReportType(commonDsrPage.getCommonDsrFilters().getTravelReportType());
		commonDsrPage.setCommonDsrFilters(commonDsrFilters);
		setCommonDsrTravelReports(commonDsrDao.getCommonDsrTravelViewReports(commonDsrPage));
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
	public List<FlightOrderRow> getFlightOrderRow() {
		return flightOrderRow;
	}
	public void setFlightOrderRow(List<FlightOrderRow> flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}
	public List<CommonDsrTravelReportData> getCommonDsrTravelReports() {
		return commonDsrTravelReports;
	}
	public void setCommonDsrTravelReports(List<CommonDsrTravelReportData> commonDsrTravelReports) {
		this.commonDsrTravelReports = commonDsrTravelReports;
	}
	
}
