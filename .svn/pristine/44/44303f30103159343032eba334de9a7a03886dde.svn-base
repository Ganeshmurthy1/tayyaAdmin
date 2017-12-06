package com.lintas.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadExpenses extends ActionSupport {
	private static final long serialVersionUID = 1L;
	  private InputStream fileInputStream;
    // Used to set file name dynamically
    private String fileName;
    
    private String fileNameeee;
   
    public String getExpFilePath(){
		 return	getText("global.expense_upload_download_file_path");
		}
    
    public InputStream getFileInputStream() 
    {
            return fileInputStream;
    }
    public String execute() throws Exception 
    {
    	//logger.info("fileNameeee==============="+fileNameeee);
           // File fileToDownload = new File("D:\\saumyaFile\\"+fileNameeee);
            File fileToDownload = new File(getExpFilePath()+fileNameeee);
            fileName = fileToDownload.getName();
            //logger.info("-----getExpFilePath---"+getExpFilePath());
           // logger.info("fileName to download ======="+fileName);
            fileInputStream = new FileInputStream(fileToDownload);
            return SUCCESS;
    }

    public String getFileName() 
    {
            return fileName;
    }

    public void setFileName(String fileName) 
    {
            this.fileName = fileName;
    }
	public String getFileNameeee() {
		return fileNameeee;
	}
	public void setFileNameeee(String fileNameeee) {
		this.fileNameeee = fileNameeee;
	}
	
	
}
