package com.lintas.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadFiles extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  private InputStream fileInputStream;
      // Used to set file name dynamically
      private String fileName;
      
      public InputStream getFileInputStream() 
      {
              return fileInputStream;
      }

      public String execute() throws Exception 
      {
              File fileToDownload = new File("C:/"+fileName+"");
              fileName = fileToDownload.getName();
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
}
