package com.lintas.admin.vo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UploadDealImage extends ActionSupport implements SessionAware,ServletRequestAware{	
	
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(UploadDealImage.class);
	Map<String,String> jsonobj  =  new HashMap<String, String>();
	SessionMap<String, Object> sessionmap=null;
	private HttpServletRequest servletRequest;
	
	public String Upload() throws IOException
	{
		//User u = (User)sessionmap.get("User");

		try {
			String  currentTime = new SimpleDateFormat("yyyyMMddhhmm").format(new Date());
			if(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)
			{
				MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();

				Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
				if(fileParameterNames.hasMoreElements()){
					String inputValue = (String) fileParameterNames.nextElement(); 
					String[] localFileNames = multiWrapper.getFileNames(inputValue);

					UploadedFile[] files = multiWrapper.getFiles(inputValue); 
					String fileName = "";
					String fileType = "";

					for (UploadedFile cf : files) {
						logger.info("----------file length...-------"+cf.length());
						/*logger.info("file length..."+cf.length());*/
						fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
						fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
						if(fileType.equals("jpg")||fileType.equals("jpeg") || fileType.equals("gif") || fileType.equals("png")){
							if(cf.length()<102400){
								logger.info("----------Server path:...-------"+servletRequest);
								/*ServletContext servletContext = servletRequest.getSession().getServletContext();
								String relativeWebPath = "/admin_profile_pics";
								String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
								logger.info("----------absoluteDiskPath Server path here:...-------"+absoluteDiskPath);*/
								String file_path = servletRequest.getSession().getServletContext().getRealPath("/DealImages");
								//String file_path = "c://DealImages";
								logger.info("----------Server path:...-------"+ file_path);
								/*	logger.info("Server path:" + file_path);*/
								File fileToCreate = new File(file_path,  currentTime+"."+fileType);
								if(cf!=null && cf.getContent()!=null)
								{
									File fi = (File) cf.getContent();

									FileUtils.copyFile(fi, fileToCreate);
								}
								jsonobj.put("ImageUrl",currentTime+"."+fileType);
							}
							else{
								jsonobj.put("fileSizeError","Required size 100Kb only");  
							}
						}
						else{
							jsonobj.put("fileError","Unsupported file");  
						}
					}
				} 
			}
			else
			{
				StrutsRequestWrapper req = (StrutsRequestWrapper) ServletActionContext.getRequest();
				logger.info("----------ContentType:...-------"+req.getContentType());
				logger.info("----------LocalName:...-------"+req.getLocalName());
				logger.info("----------getContentLength:...-------"+req.getContentLength());
				jsonobj.put("Error", "Select file to upload");

			}

		} catch (Exception e) {

			logger.info("----------EXCEPTIoN-------"+e.getMessage());
		}
		return SUCCESS;
	}




	public Map<String, String> getJsonobj() {
		return jsonobj;
	}


	public void setJsonobj(Map<String, String> jsonobj) {
		this.jsonobj = jsonobj;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		servletRequest=request;
	}

}
