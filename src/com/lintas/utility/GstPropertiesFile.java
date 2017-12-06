package com.lintas.utility;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class GstPropertiesFile {
	public String getGstorTdsValue(){
		Properties properties=getGstProperties();
		String perVal="0";
		if(getGstSwitchonValue().equalsIgnoreCase("lintas")){
			perVal=properties.getProperty("GSTPercentage");
			
		 }
		if(getGstSwitchonValue().equalsIgnoreCase("tayyarah")){
			perVal=properties.getProperty("TDSPercentage");
		 }
		 return perVal;
	 }
public String getTDSorGSTType(){
	 
	String type="";
	if(getGstSwitchonValue().equalsIgnoreCase("lintas")){
		type="GST";
	 }
	if(getGstSwitchonValue().equalsIgnoreCase("tayyarah")){
		type="TDS";
	 }
	 return type;
 }
  public String  getGstSwitchonValue(){
		String  companyType = null;
		Properties properties=getGstProperties();
		String lintasGst = properties.getProperty("isLintasGst");
		String isTayyarahTDS = properties.getProperty("isTayyarahTDS");
		//System.out.println("-----lintasGst---"+lintasGst);
		//System.out.println("-----isTayyarahTDS---"+isTayyarahTDS);
		if(Integer.parseInt(lintasGst)==1){
			companyType="lintas";
			// isLintasGst=true;
		}
		if(Integer.parseInt(isTayyarahTDS)==1){
			companyType="tayyarah";
			//isLintasGst=true;
		}
		return companyType;
	}

	public Properties getGstProperties(){
		InputStream inputStream=null;
		Properties properties=new Properties();
		try{
			String propFileName="GstEnable.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		}

		catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return properties;
	}

}
