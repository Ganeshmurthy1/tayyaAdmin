package com.lintas.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;



public class UploadFiles {
	static final Logger logger = Logger.getLogger(UploadFiles.class);
	
	public String Upload(ServletActionContext req,int userid)
	{
		String imagepath = "";


		try {
			String  currentTime = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
			logger.info("----------file upload...-------" +userid);
			if(req.getRequest() instanceof MultiPartRequestWrapper)
			{
				MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();

				String[] fileParameterNames = multiWrapper.getFileNames("Imagepath");

				//logger.info("----------file fileParameterNames...------- "+fileParameterNames.hasMoreElements());
				/*if(fileParameterNames.hasMoreElements()){
					String inputValue = (String) fileParameterNames.nextElement(); */
				//String[] localFileNames = multiWrapper.getFileNames(inputValue);

				UploadedFile[] files = multiWrapper.getFiles(fileParameterNames[0]); 

				logger.info("----------file files...------- "+files);
				String fileName = "";
				String fileType = "";

				for (UploadedFile cf : files) {
					logger.info("----------file length...-------"+cf.length());
					/*logger.info("file length..."+cf.length());*/
					fileName = fileParameterNames[0].substring(0,fileParameterNames[0].indexOf("."));
					fileType= fileParameterNames[0].substring(fileParameterNames[0].indexOf(".")+1);
					if(fileType.equals("jpg")||fileType.equals("jpeg") || fileType.equals("gif") || fileType.equals("png")){

						//String file_path = servletRequest.getSession().getServletContext().getRealPath("/admin_profile_pics" );
						//	String file_path = "/home/profilepics/";
						String file_path = "D:\\vimalprofilepic\\";
						logger.info("----------Server path:...-------"+ file_path);
						/*	logger.info("Server path:" + file_path);*/
						File fileToCreate = new File(file_path, userid+"."+fileType);
						if(cf!=null && cf.getContent()!=null)
						{
							File fi = (File) cf.getContent();

							FileUtils.copyFile(fi, fileToCreate);
						}
						imagepath =  userid+"."+fileType;

					}


				}
				//}
			}
			else
			{
				//	StrutsRequestWrapper req = (StrutsRequestWrapper) ServletActionContext.getRequest();
				//	logger.info("----------file req...------- "+req);
			}

		} catch (Exception e) {


		}


		return imagepath;

	}

}
