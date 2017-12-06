package com.lintas.action;
 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class UploadPassportSizeImageAction  extends ActionSupport {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(UploadPassportSizeImageAction.class);
	private InputStream imageStream;
		private String filename ="/home/emp_passportsize/";
		  //private String filename = "D:\\passport\\";
		    private String customContentType = "image/jpeg";
		    private String customDisposition = "attachment, filename: " + filename;
		    String passportSizeImageId =  "42.png";
		    
		   
		    public InputStream getImageStream() {
		        return imageStream;
		    }

		    public String execute() {
		    	String filePath = filename + passportSizeImageId ;
		        File file = new File(filePath);
		        try {
		            imageStream = getInputStream(file);
		        }
		        catch (FileNotFoundException e) {

		        }

		        return SUCCESS;
		    }

		    public String getPassportSizeImageId() {
				return passportSizeImageId;
			}

			public void setPassportSizeImageId(String passportSizeImageId) {
				this.passportSizeImageId = passportSizeImageId;
			}

			private File getImageFile(String imageId) {
				String filePath = "/home/emp_passportsize/";
		    	//String filePath = "D:\\passport\\";
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