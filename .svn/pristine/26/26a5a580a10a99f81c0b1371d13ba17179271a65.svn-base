/**
 * 
 */
package com.admin.ipfiledownload.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.lookbook.dao.LookBookDao;
import com.admin.lookbook.model.LookBookRequest;
import com.customerIp.filter.CustomerIpFilterPage;
import com.customerIp.filter.CustomerIpReportData;
import com.customerIp.filter.CustomerIpReportFilter;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author MANISH
 *
 */
public class IpRegistrationExcelFileDownload extends ActionSupport implements ModelDriven<LookBookRequest>,SessionAware {

	private static final long serialVersionUID = 1L;

	SessionMap<String, Object> sessionMap;
	LookBookRequest lookBookRequest = new LookBookRequest();
	private InputStream fileInputStream;
	private String fileName;
	//	 private String ip;
	//	 private String companyType;
	CustomerIpFilterPage customerIpFilterPage =new CustomerIpFilterPage();
	//	 private int configId;
	LookBookDao lookBookDao=new LookBookDao();
	public String  execute(){

		Company companySessionObj=(Company)sessionMap.get("Company");
		customerIpFilterPage = getCustomerIpFilterPage();
		CustomerIpReportFilter customerIpReportFilter = customerIpFilterPage.getCustomerIpReportFilter();
		customerIpReportFilter.setLoginCompany(companySessionObj);
		customerIpReportFilter.setReportType(customerIpFilterPage.getCustomerIpReportFilter().getReportType());
		customerIpFilterPage.setCustomerIpReportFilter(customerIpReportFilter);
		customerIpFilterPage =lookBookDao.fetchAllIpListFromIpStatus(customerIpFilterPage,companySessionObj);

		String path=null; 
		//		List<LookBookCustomerIPStatus> customerIPStatus=lookBookDao.fetchIpDetailsForEcellDownload(ip, customerIpFilterPage.getCustomerIpReportFilter().getCompanyType(), configId);
		if(customerIpFilterPage!=null && customerIpFilterPage.getItems()!=null && customerIpFilterPage.getItems().size()>0){
			List<CustomerIpReportData> list=customerIpFilterPage.getItems();
			path=generateIpReportExcel(list);
		}
		try{
			if(path!=null){
				path=path.replace("\\", "/");
				File fileToDownload = new File(path);
				fileName=fileToDownload.getName();
				fileInputStream=new FileInputStream(fileToDownload);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		//		String home = System.getProperty("user.home");
		//		File file = new File(path); 
		return SUCCESS; 
	}
	public  String generateIpReportExcel(List<CustomerIpReportData> list) {

		String path=getText("global.upload_download_file_path");
		try {

			path = path + "excel" + File.separator + "CustomerIpReport.xlsx";
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
		}

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("Ip Report");
		XSSFRow row;
		SortedMap<Integer, List<Object>> flightReportInfoMap = new TreeMap<Integer, List<Object>>();

		int i = 1;
		List<Object> headerList = new LinkedList<>();
		headerList.add("id");
		headerList.add("   Start Date   ");
		headerList.add("   Last Date   ");
		headerList.add(" Customer IP Addr ");
		headerList.add(" Block Status ");
		headerList.add(" B2C Flag ");
		headerList.add(" Total Search Count ");
		headerList.add(" Total Booked Count ");
		headerList.add(" Company Id ");
		headerList.add(" Config Id ");


		flightReportInfoMap.put(i,
				headerList );
		if(list!=null && list.size()>0)
		{
			for (CustomerIpReportData data : list) {
				i++;
				List<Object> dataList = new LinkedList<>();

				dataList.add(String.valueOf(data.getId()));
				dataList.add(String.valueOf(data.getStartDate()));
				dataList.add(String.valueOf(data.getLastDate()));
				dataList.add(String.valueOf(data.getIp()));
				dataList.add(String.valueOf(data.isBlockStatus()));
				dataList.add(String.valueOf(data.isB2cFlag()));
				dataList.add(String.valueOf(data.getTotalSearchCount()));
				dataList.add(String.valueOf(data.getTotalBookedCount()));
				dataList.add(String.valueOf(data.getCompanyId()));
				dataList.add(String.valueOf(data.getConfigId()));

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
				if(objectArr!=null && objectArr.size()>0)
				{
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

			}
		}
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
		}
		return path;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public LookBookRequest getModel() {
		// TODO Auto-generated method stub
		return lookBookRequest;
	}

	public LookBookRequest getLookBookRequest() {
		return lookBookRequest;
	}

	public void setLookBookRequest(LookBookRequest lookBookRequest) {
		this.lookBookRequest = lookBookRequest;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	//	public String getIp() {
	//		return ip;
	//	}
	//	public void setIp(String ip) {
	//		this.ip = ip;
	//	}
	//	public String getCompanyType() {
	//		return companyType;
	//	}
	//	public void setCompanyType(String companyType) {
	//		this.companyType = companyType;
	//	}
	//	public int getConfigId() {
	//		return configId;
	//	}
	//	public void setConfigId(int configId) {
	//		this.configId = configId;
	//	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public CustomerIpFilterPage getCustomerIpFilterPage() {
		return customerIpFilterPage;
	}
	public void setCustomerIpFilterPage(CustomerIpFilterPage customerIpFilterPage) {
		this.customerIpFilterPage = customerIpFilterPage;
	}

}
