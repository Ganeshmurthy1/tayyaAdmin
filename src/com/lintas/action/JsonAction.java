package com.lintas.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class JsonAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	    private InputStream imageStream;

 //private String filename ="/home/json/";
	private String filename = "D:\\json\\";

	    private String customContentType = "application/json";
	    private String customDisposition = "attachment, filename: " + filename;
	   String jsonFile =  "directCompanyList.json";
	    
	    
	    
	    public InputStream getImageStream() {
	        return imageStream;
	    }

	    public String execute() {
	    	String filePath = filename + jsonFile ;
	  
	        File file = new File(filePath);
	        try {
	            imageStream = getInputStream(file);
	        }
	        catch (FileNotFoundException e) {

	        }

	        return SUCCESS;
	    }

	    private File getImageFile(String imageId) {

	    	 
	    	 //String filePath = "/home/json/";

	    	 String filePath = "D:\\json\\";
			 
			//File file = new File(filePath + "/Image/", imageId);
			File file = new File(filePath + imageId);
			//logger.info(file.toString());
			return file;
		}
	    
	    public String getFilename() {
	        return filename;
	    }

	    public String getCustomContentType() {
	        return customContentType;
	    }

	    public String getCustomContentDisposition() {
	        return  customDisposition;
	    }

	    public void setFilename(String filename) {
	        this.filename = filename;
	    }

	    protected InputStream getInputStream(File file) throws FileNotFoundException {
	        return new BufferedInputStream(new FileInputStream(file));
	    }

		public String getJsonFile() {
			return jsonFile;
		}

		public void setJsonFile(String jsonFile) {
			this.jsonFile = jsonFile;
		}


}
