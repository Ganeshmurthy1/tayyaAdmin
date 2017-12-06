package com.lintas.action;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class UploadPassportScanCopyAction  extends ActionSupport {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(UploadPassportScanCopyAction.class);
	private InputStream imageStream;
		 private String filename ="/home/emp_passportscan_copies/";
 
		  //private String filename = "D:\\passportscancopy\\";
		    private String customContentType = "image/jpeg";
		    private String customDisposition = "attachment, filename: " + filename;
		    String passportScanCopyId =  "1.png";
		    
		   
		    
		    public String getPassportScanCopyId() {
				return passportScanCopyId;
			}

			public void setPassportScanCopyId(String passportScanCopyId) {
				this.passportScanCopyId = passportScanCopyId;
			}

			public InputStream getImageStream() {
		        return imageStream;
		    }

		    public String execute() {
		    	String filePath = filename + passportScanCopyId ;
		        File file = new File(filePath);
		        try {
		            imageStream = getInputStream(file);
		        }
		        catch (FileNotFoundException e) {

		        }

		        return SUCCESS;
		    }

		    private File getImageFile(String imageId) {
			 String filePath = "/home/emp_passportscan_copies/";
		    	//String filePath = "D:\\passportscancopy\\";
				//File file = new File(filePath + "/Image/", imageId);
				File file = new File(filePath + imageId);
				logger.info(file.toString());
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
}