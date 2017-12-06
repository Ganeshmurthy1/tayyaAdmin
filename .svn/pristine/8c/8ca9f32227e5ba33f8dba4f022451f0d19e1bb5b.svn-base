package com.lintas.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class ImageAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InputStream imageStream;
	private String message;


	private String filename ="/home/profilepics/";
	/* private String filename = "D:\\vimalprofilepic\\";*/

	private String customContentType = "image/jpeg";
	private String customDisposition = "attachment, filename: " + filename;
	String imageId =  "1.png";

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public InputStream getImageStream() {
		return imageStream;
	}

	public String execute() {
		String filePath = filename + imageId ;

		File file = new File(filePath);
		try {
			imageStream = getInputStream(file);
		}
		catch (FileNotFoundException e) {
		}
		if(imageStream==null)
		{
			message="Image Not Found";
			return ERROR;
		}
		return SUCCESS;
	}

	private File getImageFile(String imageId) {

		/* String filePath = "D:\\vimalprofilepic\\";*/
		String filePath = "/home/profilepics/";

		// String filePath = "D:\\vimalprofilepic\\";
		//String filePath = "/home/profilepics/";

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
