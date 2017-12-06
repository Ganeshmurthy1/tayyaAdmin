package com.tayyarah.employee.samplefiledownload.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class EmployeRegistrationSampleExcelFileDownloadAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(EmployeRegistrationSampleExcelFileDownloadAction.class);
	
	private InputStream fileInputStream;
	 private String fileName;
	 
	public String execute() throws Exception {
		String path=getText("global.upload_download_file_path");
		try {
			path = path + "EmployeeRegSampleExcel" + File.separator + fileName;
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
		File fileToDownload = new File(path);
        fileName = fileToDownload.getName();
        fileInputStream = new FileInputStream(fileToDownload);
	    return SUCCESS;
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
