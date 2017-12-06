package com.admin.hotel.fin.sheet.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.admin.flight.fin.sheet.model.HotelandFlightDisReportProperty;
import com.lintas.admin.DAO.FlightOrderDao;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.utility.CommonDsrTravelReportsExportToExcel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HotelDisReportAction extends ActionSupport implements ModelDriven<HotelandFlightDisReportProperty>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HotelandFlightDisReportProperty hotelDisReportProperty=new HotelandFlightDisReportProperty();
	HotelOrderDao 	hotelOrderDao =new  HotelOrderDao();
	FlightOrderDao 	flightOrderDao =new  FlightOrderDao();
	private InputStream fileInputStream;
	// Used to set file name dynamically
	private String fileName;

	public InputStream getFileInputStream() 
	{
		return fileInputStream;
	}

	/*  public String goHotelDisReports(){
    	 return SUCCESS;
     }*/


	public String  execute(){
		List<HotelOrderRow> hotelOrderRowList=null;
		List<FlightOrderRow> flightOrderRowList=null;
		boolean isCreated=false;
		File fileToDownload=null;
		String path = getText("global.upload_download_file_path");
		if(hotelDisReportProperty.getTravelType().equalsIgnoreCase("H")){
			hotelOrderRowList= hotelOrderDao.getHotelReportsForGenerateExcel(hotelDisReportProperty);
			isCreated= CommonDsrTravelReportsExportToExcel.generateHotelReportExcel(hotelOrderRowList,hotelDisReportProperty,path);
			if(isCreated){
				fileToDownload = new File(path+"excel/hotel_dis_report.xls");
				try {
					fileName = fileToDownload.getName();
					fileInputStream = new FileInputStream(fileToDownload);
					//fileInputStream.close();

				} 
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				} 

			}
		}

		else if(hotelDisReportProperty.getTravelType().equalsIgnoreCase("F")){
			flightOrderRowList= flightOrderDao.getFlightReportsForGenerateExcel(hotelDisReportProperty);
			isCreated= CommonDsrTravelReportsExportToExcel.generateFlightReportExcel(flightOrderRowList,hotelDisReportProperty,path);
			if(isCreated){
				fileToDownload = new File(path+"excel/flight_dis_report.xls");
				try {
					fileName = fileToDownload.getName();
					fileInputStream = new FileInputStream(fileToDownload);
					//fileInputStream.close();

				} 
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				} 

			}
		}


		/*  if(isCreated){
			 fileToDownload = new File(path+"excel/hotel_dis_report.xls");
				try {
					fileName = fileToDownload.getName();
					fileInputStream = new FileInputStream(fileToDownload);
					//fileInputStream.close();

				} 
				 catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
		  }
		  else{
			throw  new RuntimeException("Data Inserting problem-----") ;
		  }*/
		return SUCCESS;
	}

	public HotelandFlightDisReportProperty getHotelDisReportProperty() {
		return hotelDisReportProperty;
	}

	public void setHotelDisReportProperty(HotelandFlightDisReportProperty hotelDisReportProperty) {
		this.hotelDisReportProperty = hotelDisReportProperty;
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
	public HotelandFlightDisReportProperty getModel() {
		// TODO Auto-generated method stub
		return hotelDisReportProperty;
	}


}
