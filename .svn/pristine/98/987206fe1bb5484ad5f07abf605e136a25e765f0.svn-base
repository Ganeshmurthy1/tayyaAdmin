package com.lintas.action;

import java.io.File;

import com.opensymphony.xwork2.ActionSupport;

public class ExcelUploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	    private File file;
	    private String contentType;
	    private String filename;
	    public static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExcelUploadAction.class);
	    public void setUpload(File file) {
	        this.file = file;
	    }

	    public void setUploadContentType(String contentType) {
	        this.contentType = contentType;
	    }

	    public void setUploadFileName(String filename) {
	        this.filename = filename;
	    }

	    public String doExcelUpload() {
	        log.info("UploadAction doExcelUpload start");
	        log.info("*** " + file + "\t" + file);
	        log.info("*** " + file + "\t" + file.length());
	        log.info("filenames:");
	        log.info("*** " + filename);
	        log.info("content types:");
	        log.info("*** " + contentType);
	        
	        String fileName = "file"+System.currentTimeMillis();
	       /* String xmlFileData = ExcelToXMLUtil.getXMLFileDataFromExcel(file);
	        File xmlFile = ExcelToXMLUtil.createXMLFile(xmlFileData, fileName);
	        try {
	        //Create a "parser factory" for creating SAX parsers
	        SAXParserFactory spfac = SAXParserFactory.newInstance();

	        //Now use the parser factory to create a SAXParser object
	        SAXParser sp = spfac.newSAXParser();
	        UserDesignationDataHandler DesignationDataHandler = new UserDesignationDataHandler();
	        InputStream xmlFileInputStream = new FileInputStream(xmlFile);
	         sp.parse(xmlFileInputStream, DesignationDataHandler);
	        List<UserDesignation>  DesignationDataVo = DesignationDataHandler.getUserDesignationData();
	        LOG.info("DesignationDataVo size::::" + DesignationDataVo.size());
	        for(int i = 0; i < DesignationDataVo.size();i++) {
	        	UserDesignation userdes = DesignationDataVo.get(i); 
	        	 LOG.info("SAX Designation::::" + userdes.getDesignation());
		         LOG.info("SAX Description::::"  + userdes.getDescription() );
	        }
	       
	       
	        } catch(SAXException ioex) {
	            ioex.printStackTrace();
	        } catch(ParserConfigurationException ioex) {
	            ioex.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/
	        log.info("UploadAction doExcelUpload end");
	        return SUCCESS;
	    }
	    

}
