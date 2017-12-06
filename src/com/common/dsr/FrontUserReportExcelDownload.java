/**
 * 
 */
package com.common.dsr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CrmDetailsDao;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.lintas.frontuser.FrontUserDao;
import com.lintas.frontuser.FrontUserDetail;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author MANISH
 *
 */
public class FrontUserReportExcelDownload extends ActionSupport implements ModelDriven<CompanyFilter>,SessionAware{
	
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	private InputStream fileInputStream;
	private String fileName;
	CrmDetailsDao crmdao = new CrmDetailsDao();
	CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	CompanyFilter companyFilter = companyFilterPage.getCompanyFilter(); 
	FrontUserDao frontUserDao = new FrontUserDao();
	public String  execute(){
		String path=getText("global.upload_download_file_path");
		File fileToDownload=null;
		String file="FrontUserReport.xlsx";
		
		Company companySessionObj=(Company)sessionMap.get("Company");
		User user = (User)sessionMap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilterPage.setCompanyFilter(companyFilter);
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setLoginUser(user); 
		companyFilter.setReportType(companyFilterPage.getCompanyFilter().getReportType());
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=frontUserDao.loadAllFrontUsers(companyFilterPage);
		if(newCompanyFilterPage!=null && newCompanyFilterPage.getFrontUserDetailList()!=null){
			generateFrontUserReportExcel(newCompanyFilterPage.getFrontUserDetailList(), path, companySessionObj);
		}
		
		fileToDownload = new File(path+"excel/"+file);
		try {
			fileName = fileToDownload.getName();
			fileInputStream = new FileInputStream(fileToDownload);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		return SUCCESS;
	}
	
	public static boolean generateFrontUserReportExcel(List<FrontUserDetail>  list, String path,
			Company companyObj) {

		boolean isGenerated = false;
		try {
			path = path + "excel" + File.separator + "FrontUserReport.xlsx";
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e) {
		}
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HHmmssSSS");
		// get current date time with Date()
		Date date = new Date();
		String pdfCreateDate = dateFormat.format(date);
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Front User Report");
		// Create row object
		XSSFRow row;
		// This data needs to be written (Object[])
		// Map<String, Object[]> flightReportInfoMap = new TreeMap<String,
		// Object[]>();
		SortedMap<Integer, List<Object>> flightReportInfoMap = new TreeMap<>();
		CompanyConfig newCompanyConfig=	new CompanyConfigDao().getConfigByComnpanyId(companyObj.getCompanyid());

		int i = 1;
		List<Object> headerList = new LinkedList<>();
		headerList.add(" S.No  ");
		headerList.add("First Name");
		headerList.add("Last Name");
		headerList.add("Country");
		headerList.add(" Mobile No. ");
		headerList.add("Email Address");
		
		
		flightReportInfoMap.put(i, headerList);

		if (list != null && list.size() > 0) {
			for (int j=0;j<list.size();j++) {
				FrontUserDetail reportData=list.get(j);
				i++;
				List<Object> dataList = new LinkedList<>();
				dataList.add(String.valueOf((j)+1));
				dataList.add(reportData.getFirstName());
				dataList.add(reportData.getLastName());
				dataList.add(reportData.getCountry());
				dataList.add(reportData.getMobile());
				dataList.add(reportData.getEmail());
				
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

			}
			isGenerated=true; 
		}

		FileOutputStream out;
		try {
			out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			isGenerated=true; 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			isGenerated=false; 

		} catch (IOException e) {
			e.printStackTrace();
			isGenerated=false; 
		}
		return isGenerated;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public CompanyFilter getModel() {
		return companyFilter;
	}
	public CompanyFilter getCompanyFilter() {
		return companyFilter;
	}
	public void setCompanyFilter(CompanyFilter companyFilter) {
		this.companyFilter = companyFilter;
	}
	public InputStream getFileInputStream() {
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
	
}