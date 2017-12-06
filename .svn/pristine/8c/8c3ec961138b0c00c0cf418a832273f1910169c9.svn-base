package com.lintas.action;	

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.commonDsrReportConfg.CommonDsrReportConfg;
import com.admin.common.config.model.AdvertiseandOtherServiceTaxConfig;
import com.admin.common.config.model.BusServiceTaxConfig;
import com.admin.common.config.model.CarServiceTaxConfig;
import com.admin.common.config.model.FlightDomesticServiceTaxConfig;
import com.admin.common.config.model.FlightInternationalServiceTaxConfig;
import com.admin.common.config.model.HolidayServiceTaxConfig;
import com.admin.common.config.model.HotelServiceTaxConfig;
import com.admin.common.config.model.RailServiceTaxConfig;
import com.admin.common.config.model.VisaServiceTaxConfig;
import com.admin.miscellaneous.model.MiscellaneousGstTaxConfig;
import com.admin.whitelabel.dao.WhiteLabelDao;
import com.admin.whitelabel.model.ThemeInsertionRequestModel;
import com.isl.admin.commission.dao.AirlineCommissionBlockDaoImp;
import com.isl.admin.commission.model.AirlineCommissionCompanyBlock;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.advertisement.model.AdvertisementGstTaxConfig;
import com.lintas.admin.bus.model.BusGstTaxConfig;
import com.lintas.admin.car.model.CarGstTaxConfig;
import com.lintas.admin.flight.model.FlightDomesticGstTaxConfig;
import com.lintas.admin.flight.model.FlightInternationalGstTaxConfig;
import com.lintas.admin.holiday.model.HolidayGstTaxConfig;
import com.lintas.admin.hotel.model.HotelGstTaxConfig;
import com.lintas.admin.insurance.model.InsuranceGstTaxConfig;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CompanyConfigTax;
import com.lintas.admin.model.CompanyConfigType;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.FlightMarkup;
import com.lintas.admin.model.HotelMarkup;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainGstTaxConfig;
import com.lintas.admin.visa.model.VisaGstTaxConfig;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.config.encryptions;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;


public class CompanyConfigAction extends ActionSupport implements ModelDriven<CompanyConfig> ,SessionAware {
	/** @author info name:raham
	 * created date:28-07-2015
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyConfigAction.class);
	private static final long serialVersionUID = 1L; 
	CompanyConfig cc=new CompanyConfig();
	CompanyConfigTax companyConfigTax=new CompanyConfigTax();
	CompanyConfigDao ccDao=new CompanyConfigDao ();
	ThemeInsertionRequestModel insertionRequestModel=new ThemeInsertionRequestModel();
	WhiteLabelDao whiteLabelDao=new WhiteLabelDao();
	private long flightPaymentConfigId;
	private long bookingServiceConfigId;
	private long flightServiceTaxConfigId;
	private long flightBookingCutoffConfigId;
	private long hotelPaymentConfigId;
	private long hotelServiceTaxConfigId;
	private long hotelBookingCutoffConfigId;
	private SessionMap<String,Object> sessionMap;  
	CompanyDAO ComregDAO = new CompanyDAO();
	private InputStream inputStream;
	private encryptions enc=new encryptions();
	private String configStatus;
	private String configCompanyName;
	private String parentConfigIdSplit;
	private String filterBy; 
	private String  whiteLableType;
	private String dealSheetStatus;
	private String configType;
	private String flightPaymentType;
	private String flightCutOffDate;
	private String hotelPaymentType;
	private String flightBookingCutOffType;
	private String hotelBookingCutOffType;
	private String bookingServiceType;
	private String flightServiceTax;
	private String hotelServiceTax;
	private String flightBookingCutOff;
	private String hotelBookingCutOff;
	private List<AirlineCommissionCompanyBlock> companyBlockList= new ArrayList<>();
	private int  page = 1;
	private int  noOfPages;
	private int recordsPerPage =5;
	int statusCode;
	int actionId;
	int detailType;
	String actionMessage="";
	private  List<CompanyConfig>  configList = new ArrayList<CompanyConfig>();
	private  List<Company> companyUserIdsList = new ArrayList<Company>();
//raham created
	 
	private String producttype;
	
	
	
	private CompanyConfig companyConfig=new CompanyConfig();
	/* INSERTING COMPANY CONFIG DATA WITH ENCRIPTED APPKEY*/
	public String insertCompanyConfigDetails(){
		//FlightPaymentConfig flightPaymentConfig =new FlightPaymentConfig ();
		///HotelPaymentConfig hotelPaymentConfig =new HotelPaymentConfig ();
		///BookingServiceConfig bookingServiceConfig =new BookingServiceConfig ();
		//FlightBookingCutoffConfig flightBookingCutoffConfig =new FlightBookingCutoffConfig();
		//HotelBookingCutoffConfig hotelBookingCutoffConfig =new HotelBookingCutoffConfig();
		try {
			logger.info("---------CALLED------------");
			Company companySessionObj=(Company)sessionMap.get("Company");
			User sessionObj=(User)sessionMap.get("User");
			String[] parts =null;
			String companyId=null;
			String  comapnyName=null;
			String  comapnyUserId=null;
			if(getConfigCompanyName().contains(",")){
				parts =getConfigCompanyName().split(",");
				companyId = parts[0];  
				comapnyName = parts[1]; 
				comapnyUserId = parts[2]; 
				logger.info("---------comapnyUserId------------"+comapnyUserId);
			}

			cc.setCompanyUserId(comapnyUserId);
			cc.setCompany_id(Integer.parseInt(companyId));
			cc.setCompanyName(comapnyName);
			cc.setCreatedbyComapnyId(companySessionObj.getCompanyid());
			if(getConfigStatus().equalsIgnoreCase("yes")){
				cc.setActive(true);
			}
			else{
				cc.setActive(false);
			}

			/*if(flightPaymentType.equalsIgnoreCase("W")) 
				flightPaymentConfig.setWallet(true);
			if(flightPaymentType.equalsIgnoreCase("C")) 
				flightPaymentConfig.setCard(true);
*/
			//cc.setFlightPaymentConfig(flightPaymentConfig);
			//cc.setHotelPaymentConfig(hotelPaymentConfig);
			
			/*flightBookingCutoffConfig.setCutoffDate(DateConversion.StringToDate(cc.getFlightBookingCutoffConfig().getTranCutoffDate()));
			flightBookingCutoffConfig.setCutofftype(cc.getFlightBookingCutoffConfig().getCutofftype());
			flightBookingCutoffConfig.setCutoffweek(cc.getFlightBookingCutoffConfig().getCutoffweek());
			cc.setFlightBookingCutoffConfig(flightBookingCutoffConfig);


			
			hotelBookingCutoffConfig.setCutoffDate(DateConversion.StringToDate(cc.getHotelBookingCutoffConfig().getTranCutoffDate()));
			hotelBookingCutoffConfig.setCutofftype(cc.getHotelBookingCutoffConfig().getCutofftype());
			hotelBookingCutoffConfig.setCutoffweek(cc.getHotelBookingCutoffConfig().getCutoffweek());
			cc.setHotelBookingCutoffConfig(hotelBookingCutoffConfig);*/




			/*if(bookingServiceType.equalsIgnoreCase("F"))
				bookingServiceConfig.setFlight(true);		
			if(bookingServiceType.equalsIgnoreCase("H"))
				bookingServiceConfig.setHotel(true);*/

			//cc.setBookingServiceConfig(bookingServiceConfig);

			/*if(hotelPaymentType.equalsIgnoreCase("W")) 
				hotelPaymentConfig.setWallet(true);
			if(hotelPaymentType.equalsIgnoreCase("C")) 
				hotelPaymentConfig.setCard(true);*/


			//cc.setHotelPaymentConfig(hotelPaymentConfig);
			//cc.setBookingServiceConfig(bookingServiceConfig);

			/*cc.setFlightServiceTaxConfig(flightServiceTaxConfig);*/
			//flightServiceTaxConfig.setServiceCharge(cc.getFlightServiceTaxConfig().getServiceCharge());
			
			CarServiceTaxConfig carServiceTaxConfignew = new CarServiceTaxConfig();
			carServiceTaxConfignew.setId(cc.getBusServiceTaxConfig().getId());
			carServiceTaxConfignew.setApplicableFare(cc.getBusServiceTaxConfig().getApplicableFare());
			carServiceTaxConfignew.setBasicTax(cc.getBusServiceTaxConfig().getBasicTax());
			carServiceTaxConfignew.setConvenienceFee(cc.getBusServiceTaxConfig().getConvenienceFee());
			carServiceTaxConfignew.setKrishiKalyanCess(cc.getBusServiceTaxConfig().getKrishiKalyanCess());
			carServiceTaxConfignew.setManagementFee(cc.getBusServiceTaxConfig().getCarManagementFee());
			carServiceTaxConfignew.setSwatchBharathCess(cc.getBusServiceTaxConfig().getSwatchBharathCess());
			carServiceTaxConfignew.setTotalTax(cc.getBusServiceTaxConfig().getTotalTax());
			
			AdvertiseandOtherServiceTaxConfig advertiseandOtherServiceTaxConfignew = new AdvertiseandOtherServiceTaxConfig();
			advertiseandOtherServiceTaxConfignew.setId(cc.getRailServiceTaxConfig().getId());
			advertiseandOtherServiceTaxConfignew.setApplicableFare(cc.getRailServiceTaxConfig().getApplicableFare());
			advertiseandOtherServiceTaxConfignew.setBasicTax(cc.getRailServiceTaxConfig().getBasicTax());
			advertiseandOtherServiceTaxConfignew.setConvenienceFee(cc.getRailServiceTaxConfig().getConvenienceFee());
			advertiseandOtherServiceTaxConfignew.setKrishiKalyanCess(cc.getRailServiceTaxConfig().getKrishiKalyanCess());
			advertiseandOtherServiceTaxConfignew.setManagementFee(cc.getRailServiceTaxConfig().getAdvertisementAndOtherManagementFee());
			advertiseandOtherServiceTaxConfignew.setSwatchBharathCess(cc.getRailServiceTaxConfig().getSwatchBharathCess());
			advertiseandOtherServiceTaxConfignew.setTotalTax(cc.getRailServiceTaxConfig().getTotalTax());
			
			VisaServiceTaxConfig visaServiceTaxConfignew = new VisaServiceTaxConfig();
			visaServiceTaxConfignew.setId(cc.getRailServiceTaxConfig().getId());
			visaServiceTaxConfignew.setApplicableFare(cc.getRailServiceTaxConfig().getApplicableFare());
			visaServiceTaxConfignew.setBasicTax(cc.getRailServiceTaxConfig().getBasicTax());
			visaServiceTaxConfignew.setConvenienceFee(cc.getRailServiceTaxConfig().getConvenienceFee());
			visaServiceTaxConfignew.setKrishiKalyanCess(cc.getRailServiceTaxConfig().getKrishiKalyanCess());
			visaServiceTaxConfignew.setManagementFee(cc.getRailServiceTaxConfig().getVisaManagementFee());
			visaServiceTaxConfignew.setSwatchBharathCess(cc.getRailServiceTaxConfig().getSwatchBharathCess());
			visaServiceTaxConfignew.setTotalTax(cc.getRailServiceTaxConfig().getTotalTax());
			
			
			//added by basha for saving the gst data into their respective tables
			
			
			
			

			FlightDomesticServiceTaxConfig flightDomesticServiceTaxConfig = ccDao.insertFlightDomesticServiceTaxConfig(cc.getFlightDomesticServiceTaxConfig());
			FlightInternationalServiceTaxConfig flightInternationalServiceTaxConfig  = ccDao.insertFlightInternationalServiceTaxConfig(cc.getFlightInternationalServiceTaxConfig());
			HotelServiceTaxConfig hotelServiceTaxConfig = ccDao.insertHotelServiceTaxConfig(cc.getHotelServiceTaxConfig());
			HolidayServiceTaxConfig holidayServiceTaxConfig = ccDao.insertHolidayServiceTaxConfig(cc.getHolidayServiceTaxConfig());
			BusServiceTaxConfig busServiceTaxConfig = ccDao.insertBusServiceTaxConfig(cc.getBusServiceTaxConfig());
			CarServiceTaxConfig carServiceTaxConfig = ccDao.insertCarServiceTaxConfig(carServiceTaxConfignew);
			RailServiceTaxConfig railServiceTaxConfig = ccDao.insertRailServiceTaxConfig(cc.getRailServiceTaxConfig());
			AdvertiseandOtherServiceTaxConfig advertiseandOtherServiceTaxConfig = ccDao.insertAdvertiseandOtherServiceTaxConfig(advertiseandOtherServiceTaxConfignew);
			VisaServiceTaxConfig visaServiceTaxConfig = ccDao.insertVisaServiceTaxConfig(visaServiceTaxConfignew);
			FlightInternationalGstTaxConfig flightInternationalGstTaxConfig=ccDao.insertFlightInternationalGstTaxConfig(cc.getFlightInternationalGstTaxConfig());
			FlightDomesticGstTaxConfig flightDomesticGstTaxConfig=ccDao.insertFlightDomesticGstTaxConfig(cc.getFlightDomesticGstTaxConfig());
			HotelGstTaxConfig hotelGstTaxConfig=ccDao.insertHotelGstTaxConfig(cc.getHotelGstTaxConfig());
			CarGstTaxConfig carGstTaxConfig=ccDao.insertCarGstTaxConfig(cc.getCarGstTaxConfig());
			BusGstTaxConfig busGstTaxConfig=ccDao.insertBusGstTaxConfig(cc.getBusGstTaxConfig());
			TrainGstTaxConfig trainGstTaxConfig=ccDao.insertTrainGstTaxConfig(cc.getTrainGstTaxConfig());
			VisaGstTaxConfig visaGstTaxConfig=ccDao.insertVisaGstTaxConfig(cc.getVisaGstTaxConfig());
			InsuranceGstTaxConfig insuranceGstTaxConfig=ccDao.insertInsuranceGstTaxConfig(cc.getInsuranceGstTaxConfig());
			HolidayGstTaxConfig holidayGstTaxConfig=ccDao.insertHolidayGstTaxConfig(cc.getHolidayGstTaxConfig());
			AdvertisementGstTaxConfig advertisementGstTaxConfig=ccDao.insertAdvertisementGstTaxConfig(cc.getAdvertisementGstTaxConfig());
			MiscellaneousGstTaxConfig miscellaneousGstTaxConfig=ccDao.insertMiscellaneousGstTaxConfig(cc.getMiscellaneousGstTaxConfig());
			
			
			
			
			
			cc.setFlightDomesticServiceTaxConfig(flightDomesticServiceTaxConfig);
			cc.setFlightInternationalServiceTaxConfig(flightInternationalServiceTaxConfig);
			cc.setHotelServiceTaxConfig(hotelServiceTaxConfig);
			cc.setHolidayServiceTaxConfig(holidayServiceTaxConfig);
			cc.setBusServiceTaxConfig(busServiceTaxConfig);
			cc.setCarServiceTaxConfig(carServiceTaxConfig);
			cc.setRailServiceTaxConfig(railServiceTaxConfig);
			cc.setAdvertiseandOtherServiceTaxConfig(advertiseandOtherServiceTaxConfig);
			cc.setVisaServiceTaxConfig(visaServiceTaxConfig);
			cc.setFlightInternationalGstTaxConfig(flightInternationalGstTaxConfig);
			cc.setFlightDomesticGstTaxConfig(flightDomesticGstTaxConfig);
			cc.setHotelGstTaxConfig(hotelGstTaxConfig);
			cc.setCarGstTaxConfig(carGstTaxConfig);
			cc.setBusGstTaxConfig(busGstTaxConfig);
			cc.setTrainGstTaxConfig(trainGstTaxConfig);
			cc.setVisaGstTaxConfig(visaGstTaxConfig);
			cc.setInsuranceGstTaxConfig(insuranceGstTaxConfig);
			cc.setHolidayGstTaxConfig(holidayGstTaxConfig);
			cc.setAdvertisementGstTaxConfig(advertisementGstTaxConfig);
			cc.setMiscellaneousGstTaxConfig(miscellaneousGstTaxConfig);
			//cc.setCommonDsrReportConfg(commonDsrReportConfg);
			

			if(cc.getRateTypeFlight().equalsIgnoreCase("Net")){
				cc.setCommissionTypeFlight("Fixed");
				cc.setCommissionAmountFlight(new BigDecimal("0.00"));
			}
			if(cc.getRateTypeHotel().equalsIgnoreCase("Net")){
				cc.setCommissionTypeHotel("Fixed");
				cc.setCommissionAmountHotel(new BigDecimal("0.00"));
			}
			if(cc.getCommissionAmountFlight()==null){
				cc.setCommissionAmountFlight(new BigDecimal("0.00"));
			}
			if(cc.getCommissionAmountHotel()==null){
				cc.setCommissionAmountHotel(new BigDecimal("0.00"));
			}
			if(cc.getCommissionTypeFlight()==null || cc.getCommissionTypeFlight().equals("")){
				cc.setCommissionTypeFlight("Percentage");
			}
			CompanyConfigType companyConfigType=new CompanyConfigType();
			logger.info("getConfigType()" +cc.getConfigType());
			if(cc.getConfigType().equalsIgnoreCase("B2C")){
				companyConfigType.setB2C(true);
			}
			else if(cc.getConfigType().equalsIgnoreCase("B2B")){
				companyConfigType.setB2B(true);
			}
			else if(cc.getConfigType().equalsIgnoreCase("API")){
				companyConfigType.setAPI(true);
			}
			else if(cc.getConfigType().equalsIgnoreCase("B2E")){
				companyConfigType.setB2E(true);
			}

			else if(cc.getConfigType().equalsIgnoreCase("WL")){
				companyConfigType.setWhitelable(true);
			}
			cc.setCompanyConfigType(companyConfigType);
			cc.setCreatedbyUserId(sessionObj.getId());
			cc.setModifiedbyUserId(sessionObj.getId());
			cc.setCurrency_id(companySessionObj.getCurrencyCode());
			getCompanyUserIds();
			for(Company type: companyUserIdsList){
				if(type.getCompanyid()==cc.getCompany_id()){
					if(type.getCompanyRole().isAgent()){
						cc.setCompanyType("Agency");
					}
					else if(type.getCompanyRole().isDistributor()){
						cc.setCompanyType(getText("global.Wholesaler"));
					}
					else if(type.getCompanyRole().isCorporate()){
						cc.setCompanyType(getText("Corporate"));
					}
					else{
						cc.setCompanyType("Super Agency");
					} 
				}
			}
			String[]  configIdsSplit=null;
			if(getParentConfigIdSplit()!=null && getParentConfigIdSplit().contains(",") ){
				configIdsSplit =getParentConfigIdSplit().split(",");
				if(configIdsSplit!=null && configIdsSplit.length>0 && !comapnyUserId.equalsIgnoreCase(configIdsSplit[1])){
					cc.setParent_config_id(Integer.parseInt(configIdsSplit[0]));
				}
				else{
					cc.setParent_config_id(0);
				}
			} 
			else{
				cc.setParent_config_id(0);
			}
			logger.info("parent config id---------"+cc.getParent_config_id());
			logger.info("getWhiteLableType---------"+cc.isWhitelable());
			logger.info("getWhiteLableType---------"+cc.isMyConfig());
			CompanyConfig appKeyUpdate=null;
			ccDao.setIsMyConfigDeActive(cc.getConfigType(),Integer.parseInt(companyId));
			appKeyUpdate=ccDao.insertCompanyConfigData(cc);
			String appKey=enc.encrypt(String.valueOf(appKeyUpdate.getConfig_id())+"-"+String.valueOf(cc.getCompany_id()));
			appKeyUpdate.setAppKey(appKey);
			String configNumber=RandomConfigurationNumber.generateConfigNumber(String.valueOf(appKeyUpdate.getConfig_id()));
			if(configNumber!=null){
				appKeyUpdate.setConfig_number(configNumber);
			}
			else{
				appKeyUpdate.setConfig_number("0");	
			}
			if(ccDao.updateEncriptedAppkye(appKeyUpdate)){ 
				if(appKeyUpdate.isWhitelable()){
					ComregDAO.insertEmail(String.valueOf(appKeyUpdate.getCompany_id()), 0, Email.EMAIL_TYPE_WHITE_LABEL);
					addActionMessage(getText("global.insertCompanyConfigDetailssuccess"));
				}
				else{
					//ccDao.traverseandupdateParentconfigIdtoChild(newCompanyList,appKeyUpdate.getConfig_id(),getConfigType());
					addActionMessage(getText("global.insertCompanyConfigDetailssuccess"));
				}
				new NotificationAction().insertNotificationOneandAll(sessionObj,String.valueOf(cc.getConfig_id()),"Company Configuration Created","Company Configuration Notification",NotificationInventoryTypeEnum.COMPANY_CONFIG.getId(),true,false,false,true,false,false);

			}
			else{
				addActionError(getText("global.insertCompanyConfigDetailsserverdown"));
				return ERROR;
			}
			
			/*whitelabel*/
			if(insertionRequestModel.isWhitelabelActive()){
				
				/* Copy file to a safe location */
			    //String destPath = "/home/WhiteLable/";
				/*String destPath = "C:/Users/Ganesh murthy/Desktop/TestingDocs/";
			     String logofileName="logo-"+cc.getCompany_id()+"-"+appKeyUpdate.getConfig_id();*/
			     //String cssfileName="css "+cc.getCompany_id()+"-"+appKeyUpdate.getConfig_id();

			      try{
			    	 
			     	/* File destLogoFile  = new File(destPath, logofileName);
			    	 FileUtils.copyFile(insertionRequestModel.getLogoImageFile(), destLogoFile);*/
			    	// File destCssFile  = new File(destPath, cssfileName);
			    	 //FileUtils.copyFile(insertionRequestModel.getCssFile(), destCssFile);
			    	 
			    	 //insertionRequestModel.setLogoImagePath(destPath+logofileName);
			    	// insertionRequestModel.setCssPath(destPath+cssfileName);
			    	  insertionRequestModel.setLogoImagePath(uploadWhiteLabelLogo(appKeyUpdate.getConfig_id()));
			    	  
			      }catch (Exception e) {
			    	  System.out.println("Error");
				} 
				
				insertionRequestModel.setCompanyId(cc.getCompany_id());
				insertionRequestModel.setConfigId(appKeyUpdate.getConfig_id());
				whiteLabelDao.insertIntoCompanyTheme(insertionRequestModel);
			}
			
		} catch (Exception e) {
			logger.error("----------EXCEPTION--------"+e.getMessage());
			addActionError(getText("global.insertCompanyConfigDetailsserverdown"));
			return ERROR;
		}
		return SUCCESS;
	}


	/*  deleting CompanyConfig data using config id */
	public String deleteCompanyConfigDetails(){
		try {
			User sessionObj=(User)sessionMap.get("User");
			boolean isDelete=ccDao.deleteCompanyConfigData(cc);
			if(isDelete){
				showSuccessMessage("deleted");
				new NotificationAction().insertNotificationOneandAll(sessionObj,String.valueOf(cc.getConfig_id()),"Company Configuration Deleted","Company Configuration Deleted",NotificationInventoryTypeEnum.COMPANY_CONFIG.getId(),true,false,false,true,false,false);
				return SUCCESS;
			}
			else{
				showSuccessMessage("failed");
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("----------EXCEPTION--------"+e.getMessage());
			showSuccessMessage("failed");
			/* addActionError("Server down.Try Again!");*/
			return ERROR;
		}
	}


	/*fetching  CompanyConfig data by passing config id */
	public String getCompanyConfigProfile(){
		logger.info("----------congig Id--------"+cc.getConfig_id());
		Company companySessionObj=(Company) sessionMap.get("Company");
		CompanyConfig getProfile=ccDao.getCompanyConfigDetails(cc);
		logger.info("---------getProfile.isActive()--------"+getProfile.isActive());
		if(getProfile.isActive()){
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_EDIT.getId();
			detailType=BrowsingHistoryDetailTypeEnum.COMPANYCONFIG_EDIT.getId();

			setConfigStatus("yes");
		}
		else{
			setConfigStatus("no");	
		}
		if(getProfile.getCompanyConfigType().isB2C()){
			setConfigType("B2C");
		}
		if(getProfile.getCompanyConfigType().isB2B()){
			setConfigType("B2B");
		}
		if(getProfile.getCompanyConfigType().isAPI()){
			setConfigType("API");
		}
		if(getProfile.getCompanyConfigType().isB2E()){
			setConfigType("B2E");
		}
		if(getProfile.getCompanyConfigType().isWhitelable()){
			setConfigType("WL");
		}

		getProfile.setFlightDomesticServiceTaxConfig(getProfile.getFlightDomesticServiceTaxConfig());
		getProfile.setFlightInternationalServiceTaxConfig(getProfile.getFlightInternationalServiceTaxConfig());
		getProfile.setHotelServiceTaxConfig(getProfile.getHotelServiceTaxConfig());
		getProfile.setHolidayServiceTaxConfig(getProfile.getHolidayServiceTaxConfig());
		getProfile.setBusServiceTaxConfig(getProfile.getBusServiceTaxConfig());
		getProfile.setCarServiceTaxConfig(getProfile.getCarServiceTaxConfig());
		getProfile.setRailServiceTaxConfig(getProfile.getRailServiceTaxConfig());
		getProfile.setAdvertiseandOtherServiceTaxConfig(getProfile.getAdvertiseandOtherServiceTaxConfig());
		getProfile.setVisaServiceTaxConfig(getProfile.getVisaServiceTaxConfig());
		getProfile.setFlightInternationalGstTaxConfig(getProfile.getFlightInternationalGstTaxConfig());
		getProfile.setFlightDomesticGstTaxConfig(getProfile.getFlightDomesticGstTaxConfig());
		getProfile.setHotelGstTaxConfig(getProfile.getHotelGstTaxConfig());
		getProfile.setCarGstTaxConfig(getProfile.getCarGstTaxConfig());
		getProfile.setBusGstTaxConfig(getProfile.getBusGstTaxConfig());
		getProfile.setTrainGstTaxConfig(getProfile.getTrainGstTaxConfig());
		getProfile.setVisaGstTaxConfig(getProfile.getVisaGstTaxConfig());
		getProfile.setInsuranceGstTaxConfig(getProfile.getInsuranceGstTaxConfig());
		getProfile.setAdvertisementGstTaxConfig(getProfile.getAdvertisementGstTaxConfig());
		getProfile.setHolidayGstTaxConfig(getProfile.getHolidayGstTaxConfig());
		getProfile.setMiscellaneousGstTaxConfig(getProfile.getMiscellaneousGstTaxConfig());
		//getProfile.setCommonDsrReportConfg(getProfile.getCommonDsrReportConfg());
		AirlineCommissionBlockDaoImp blockDao =new AirlineCommissionBlockDaoImp();
		List<AirlineCommissionCompanyBlock> airlineCommissionCompanyBlockList = null;
		List<AirlineCommissionCompanyBlock> activeCompanyBlockList=new ArrayList<>();
		/*if(getProfile.isSheetMode()) 
			setDealSheetStatus("1");
		 else
			 setDealSheetStatus("0");*/
		boolean isMasterCompany= new CompanyDAO().checkCompanyRole(getProfile.getCompany_id());
		if(isMasterCompany)
			airlineCommissionCompanyBlockList = blockDao.getChildrenCompanyCommissionBlocks(true, companySessionObj.getCompanyid());
		else
			airlineCommissionCompanyBlockList  =blockDao.getChildrenCompanyCommissionBlocks(false, companySessionObj.getCompanyid());
		if(airlineCommissionCompanyBlockList != null && airlineCommissionCompanyBlockList.size()>0){
			for(AirlineCommissionCompanyBlock commissionCompanyBlock:airlineCommissionCompanyBlockList){
				if(commissionCompanyBlock.isActive()){
					activeCompanyBlockList.add(commissionCompanyBlock);
				}
				companyBlockList=activeCompanyBlockList;
			} 
		}
		sessionMap.put("CompanyconfigProfile",getProfile);
		User user = (User)sessionMap.get("User");
		HistoryInfo	historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.COMPANYCONFIG_EDIT.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);

		return SUCCESS;

	}

	/*fetching  CompanyConfig data by passing config id */
	public String updateCompanyConfigDetails() {
logger.info("----------------getProducttype--------------------" +getProducttype());
		try{
		
		User sessionUserObj=(User) sessionMap.get("User");
		CompanyConfig getProfile= (CompanyConfig)sessionMap.get("CompanyconfigProfile");
		cc.setConfig_id(getProfile.getConfig_id());
		if(cc.getCompanyType()==null)
		{
			cc.setCompanyType(getProfile.getCompanyType());
		}
		if(getConfigStatus().equalsIgnoreCase("yes")){
			cc.setActive(true);
		}
		else{
			cc.setActive(false);
		}
		if(getDealSheetStatus().equals("true"))
			cc.setSheetMode(true);
		else
			cc.setSheetMode(false);

	
		CarServiceTaxConfig carServiceTaxConfignew = new CarServiceTaxConfig();
		carServiceTaxConfignew.setId(cc.getBusServiceTaxConfig().getId());
		carServiceTaxConfignew.setApplicableFare(cc.getBusServiceTaxConfig().getApplicableFare());
		carServiceTaxConfignew.setBasicTax(cc.getBusServiceTaxConfig().getBasicTax());
		carServiceTaxConfignew.setConvenienceFee(cc.getBusServiceTaxConfig().getConvenienceFee());
		carServiceTaxConfignew.setKrishiKalyanCess(cc.getBusServiceTaxConfig().getKrishiKalyanCess());
		carServiceTaxConfignew.setManagementFee(cc.getBusServiceTaxConfig().getCarManagementFee());
		carServiceTaxConfignew.setSwatchBharathCess(cc.getBusServiceTaxConfig().getSwatchBharathCess());
		carServiceTaxConfignew.setTotalTax(cc.getBusServiceTaxConfig().getTotalTax());
		
		AdvertiseandOtherServiceTaxConfig advertiseandOtherServiceTaxConfignew = new AdvertiseandOtherServiceTaxConfig();
		advertiseandOtherServiceTaxConfignew.setId(cc.getRailServiceTaxConfig().getId());
		advertiseandOtherServiceTaxConfignew.setApplicableFare(cc.getRailServiceTaxConfig().getApplicableFare());
		advertiseandOtherServiceTaxConfignew.setBasicTax(cc.getRailServiceTaxConfig().getBasicTax());
		advertiseandOtherServiceTaxConfignew.setConvenienceFee(cc.getRailServiceTaxConfig().getConvenienceFee());
		advertiseandOtherServiceTaxConfignew.setKrishiKalyanCess(cc.getRailServiceTaxConfig().getKrishiKalyanCess());
		advertiseandOtherServiceTaxConfignew.setManagementFee(cc.getRailServiceTaxConfig().getAdvertisementAndOtherManagementFee());
		advertiseandOtherServiceTaxConfignew.setSwatchBharathCess(cc.getRailServiceTaxConfig().getSwatchBharathCess());
		advertiseandOtherServiceTaxConfignew.setTotalTax(cc.getRailServiceTaxConfig().getTotalTax());
		
		VisaServiceTaxConfig visaServiceTaxConfignew = new VisaServiceTaxConfig();
		visaServiceTaxConfignew.setId(cc.getRailServiceTaxConfig().getId());
		visaServiceTaxConfignew.setApplicableFare(cc.getRailServiceTaxConfig().getApplicableFare());
		visaServiceTaxConfignew.setBasicTax(cc.getRailServiceTaxConfig().getBasicTax());
		visaServiceTaxConfignew.setConvenienceFee(cc.getRailServiceTaxConfig().getConvenienceFee());
		visaServiceTaxConfignew.setKrishiKalyanCess(cc.getRailServiceTaxConfig().getKrishiKalyanCess());
		visaServiceTaxConfignew.setManagementFee(cc.getRailServiceTaxConfig().getVisaManagementFee());
		visaServiceTaxConfignew.setSwatchBharathCess(cc.getRailServiceTaxConfig().getSwatchBharathCess());
		visaServiceTaxConfignew.setTotalTax(cc.getRailServiceTaxConfig().getTotalTax());
		
		
			FlightInternationalServiceTaxConfig flightInternationalServiceTaxConfigNew = ccDao.updateFlightInternationalServiceTax(cc.getFlightInternationalServiceTaxConfig());
			cc.setFlightInternationalServiceTaxConfig(flightInternationalServiceTaxConfigNew); 
		
			FlightDomesticServiceTaxConfig flightDomesticServiceTaxConfignew = ccDao.updateFlightDomesticServiceTax(cc.getFlightDomesticServiceTaxConfig());
			cc.setFlightDomesticServiceTaxConfig(flightDomesticServiceTaxConfignew);
			
			HotelServiceTaxConfig hotelServiceTaxConfigNew = ccDao.updateHotelServiceTaxConfig(cc.getHotelServiceTaxConfig());
			cc.setHotelServiceTaxConfig(hotelServiceTaxConfigNew); 
		
			HolidayServiceTaxConfig holidayServiceTaxConfignew = ccDao.updateHolidayServiceTaxConfig(cc.getHolidayServiceTaxConfig());
			cc.setHolidayServiceTaxConfig(holidayServiceTaxConfignew);
			
			BusServiceTaxConfig busServiceTaxConfigNew = ccDao.updateBusServiceTaxConfig(cc.getBusServiceTaxConfig());
			cc.setBusServiceTaxConfig(busServiceTaxConfigNew); 
		
			CarServiceTaxConfig carServiceTaxConfig = ccDao.updateCarServiceTaxConfig(carServiceTaxConfignew);
			cc.setCarServiceTaxConfig(carServiceTaxConfig);
			
			RailServiceTaxConfig railServiceTaxConfigNew = ccDao.updateRailServiceTaxConfig(cc.getRailServiceTaxConfig());
			cc.setRailServiceTaxConfig(railServiceTaxConfigNew); 
		
			AdvertiseandOtherServiceTaxConfig advertiseandOtherServiceTaxConfig = ccDao.updateAdvertiseandOtherServiceTaxConfig(advertiseandOtherServiceTaxConfignew);
			cc.setAdvertiseandOtherServiceTaxConfig(advertiseandOtherServiceTaxConfig);
			
			VisaServiceTaxConfig visaServiceTaxConfig = ccDao.updateVisaServiceTaxConfig(visaServiceTaxConfignew);
			cc.setVisaServiceTaxConfig(visaServiceTaxConfig);
			
			//added by basha for gst components update in 04-07-2017
			
			
			//if have id updating the flight international gst tax config   else creating  new config row and updating that row .
			
			if(cc.getFlightInternationalGstTaxConfig().getId()==null){
				FlightInternationalGstTaxConfig flightInternationalGstTaxConfigNewInsert=ccDao.insertFlightInternationalGstTaxConfig(cc.getFlightInternationalGstTaxConfig());
				if(flightInternationalGstTaxConfigNewInsert!=null && flightInternationalGstTaxConfigNewInsert.getId()!=null){
					FlightInternationalGstTaxConfig flightInternationalGstTaxConfignew = ccDao.updateFlightInternationalGstTaxConfig(flightInternationalGstTaxConfigNewInsert);
					cc.setFlightInternationalGstTaxConfig(flightInternationalGstTaxConfignew); 
				
				}
				
			}else{
				FlightInternationalGstTaxConfig flightInternationalGstTaxConfignew = ccDao.updateFlightInternationalGstTaxConfig(cc.getFlightInternationalGstTaxConfig());
				cc.setFlightInternationalGstTaxConfig(flightInternationalGstTaxConfignew); 
			
			}
			
			//if have id updating the flight domestic gst tax config   else creating  new config row and updating that row .
			
			
			
			if(cc.getFlightDomesticGstTaxConfig().getId()==null){
				FlightDomesticGstTaxConfig flightDomesticGstTaxConfigNewInsert=ccDao.insertFlightDomesticGstTaxConfig(cc.getFlightDomesticGstTaxConfig());
				if(flightDomesticGstTaxConfigNewInsert!=null && flightDomesticGstTaxConfigNewInsert.getId()!=null){
					FlightDomesticGstTaxConfig flightDomesticGstTaxConfignew = ccDao.updateFlightDomesticGstTaxConfig(flightDomesticGstTaxConfigNewInsert);
					cc.setFlightDomesticGstTaxConfig(flightDomesticGstTaxConfignew);
				}
			}else{
				FlightDomesticGstTaxConfig flightDomesticGstTaxConfignew = ccDao.updateFlightDomesticGstTaxConfig(cc.getFlightDomesticGstTaxConfig());
				cc.setFlightDomesticGstTaxConfig(flightDomesticGstTaxConfignew);
			}
				
			
			//if have id updating the hotel gst tax config   else creating  new config row and updating that row .
			
			
			if(cc.getHotelGstTaxConfig().getId()==null){
				HotelGstTaxConfig hotelGstTaxConfigNewInsert=ccDao.insertHotelGstTaxConfig(cc.getHotelGstTaxConfig());
				if(hotelGstTaxConfigNewInsert!=null && hotelGstTaxConfigNewInsert.getId()!=null){
					HotelGstTaxConfig hotelGstTaxConfignew=ccDao.updateHotelGstTaxConfig(hotelGstTaxConfigNewInsert);
					cc.setHotelGstTaxConfig(hotelGstTaxConfignew);
				}
			}else{
				HotelGstTaxConfig hotelGstTaxConfignew=ccDao.updateHotelGstTaxConfig(cc.getHotelGstTaxConfig());
				cc.setHotelGstTaxConfig(hotelGstTaxConfignew);
			}
			
			//if have id updating the car gst tax config   else creating  new config row and updating that row .
			
			
			if(cc.getCarGstTaxConfig().getId()==null){
				CarGstTaxConfig carGstTaxConfigNewInsert=ccDao.insertCarGstTaxConfig(cc.getCarGstTaxConfig());
				if(carGstTaxConfigNewInsert!=null && carGstTaxConfigNewInsert.getId()!=null){
					CarGstTaxConfig carGstTaxConfignew=ccDao.updateCarGstTaxConfig(carGstTaxConfigNewInsert);
					cc.setCarGstTaxConfig(carGstTaxConfignew);
				}
				
			}else{
				CarGstTaxConfig carGstTaxConfignew=ccDao.updateCarGstTaxConfig(cc.getCarGstTaxConfig());
				cc.setCarGstTaxConfig(carGstTaxConfignew);
			}
			
			//if have id updating the bus gst tax config   else creating  new config row and updating that row .
			
			
			if(cc.getBusGstTaxConfig().getId()==null){
				BusGstTaxConfig busGstTaxConfigNewInsert=ccDao.insertBusGstTaxConfig(cc.getBusGstTaxConfig());
				if(busGstTaxConfigNewInsert!=null && busGstTaxConfigNewInsert.getId()!=null){
					BusGstTaxConfig busGstTaxConfignew=ccDao.updateBusGstTaxConfig(busGstTaxConfigNewInsert);
					cc.setBusGstTaxConfig(busGstTaxConfignew);
				}
				
			}else{
				BusGstTaxConfig busGstTaxConfignew=ccDao.updateBusGstTaxConfig(cc.getBusGstTaxConfig());
				cc.setBusGstTaxConfig(busGstTaxConfignew);
			}
			
			//if have id updating the train gst tax config   else creating  new config row and updating that row .
			
			if(cc.getTrainGstTaxConfig()==null){
				TrainGstTaxConfig  trainGstTaxConfigNewInsert = ccDao.insertTrainGstTaxConfig(cc.getTrainGstTaxConfig());
				if(trainGstTaxConfigNewInsert!=null && trainGstTaxConfigNewInsert.getId()!=null){
					TrainGstTaxConfig  trainGstTaxConfignew = ccDao.updateTrainGstTaxConfig(cc.getTrainGstTaxConfig());
					cc.setTrainGstTaxConfig(trainGstTaxConfignew);
				}
			}else{
				TrainGstTaxConfig  trainGstTaxConfignew = ccDao.updateTrainGstTaxConfig(cc.getTrainGstTaxConfig());
				cc.setTrainGstTaxConfig(trainGstTaxConfignew);
			}
			
			
			//if have id updating the visa gst tax config   else creating  new config row and updating that row .
			
			
			if(cc.getVisaGstTaxConfig().getId()==null){
				VisaGstTaxConfig  visaGstTaxConfigNewInsert=ccDao.insertVisaGstTaxConfig(cc.getVisaGstTaxConfig());
				if(visaGstTaxConfigNewInsert!=null && visaGstTaxConfigNewInsert.getId()!=null){
					VisaGstTaxConfig  visaGstTaxConfignew=ccDao.updateVisaGstTaxConfig(visaGstTaxConfigNewInsert);
					cc.setVisaGstTaxConfig(visaGstTaxConfignew);
				}
				
			}else{
				VisaGstTaxConfig  visaGstTaxConfignew=ccDao.updateVisaGstTaxConfig(cc.getVisaGstTaxConfig());
				cc.setVisaGstTaxConfig(visaGstTaxConfignew);
			}
			//if have id updating the insurance gst tax config   else creating  new config row and updating that row .
			
			
			if(cc.getInsuranceGstTaxConfig().getId()==null){
				InsuranceGstTaxConfig insuranceGstTaxConfigNewInsert=ccDao.insertInsuranceGstTaxConfig(cc.getInsuranceGstTaxConfig());
				if(insuranceGstTaxConfigNewInsert!=null && insuranceGstTaxConfigNewInsert.getId()!=null){
					InsuranceGstTaxConfig insuranceGstTaxConfignew=ccDao.updateInsuranceGstTaxConfig(insuranceGstTaxConfigNewInsert);
					cc.setInsuranceGstTaxConfig(insuranceGstTaxConfignew);
				}
			}else{
				InsuranceGstTaxConfig insuranceGstTaxConfignew=ccDao.updateInsuranceGstTaxConfig(cc.getInsuranceGstTaxConfig());
				cc.setInsuranceGstTaxConfig(insuranceGstTaxConfignew);
			}
			
			
			//if have id updating the holiday gst tax config   else creating  new config row and updating that row .
			
			
			
			if(cc.getHolidayGstTaxConfig().getId()==null){
				HolidayGstTaxConfig  holidayGstTaxConfigNewInsert=ccDao.insertHolidayGstTaxConfig(cc.getHolidayGstTaxConfig());
				if(holidayGstTaxConfigNewInsert!=null && holidayGstTaxConfigNewInsert.getId()!=null){
					HolidayGstTaxConfig  holidayGstTaxConfignew=ccDao.updateHolidayGstTaxConfig(holidayGstTaxConfigNewInsert);
					cc.setHolidayGstTaxConfig(holidayGstTaxConfignew);
				}
			}else{
				HolidayGstTaxConfig  holidayGstTaxConfignew=ccDao.updateHolidayGstTaxConfig(cc.getHolidayGstTaxConfig());
				cc.setHolidayGstTaxConfig(holidayGstTaxConfignew);
			}
			
			//if have id updating the advertisement gst tax config   else creating  new config row and updating that row .
			
			
			
			if(cc.getAdvertisementGstTaxConfig().getId()==null){
				AdvertisementGstTaxConfig  advertisementGstTaxConfigNewInsert=ccDao.insertAdvertisementGstTaxConfig(cc.getAdvertisementGstTaxConfig());
				if(advertisementGstTaxConfigNewInsert!=null && advertisementGstTaxConfigNewInsert.getId()!=null ){
					AdvertisementGstTaxConfig  advertisementGstTaxConfignew=ccDao.updateAdvertisementGstTaxConfig(advertisementGstTaxConfigNewInsert);
					cc.setAdvertisementGstTaxConfig(advertisementGstTaxConfignew);
				}
			}else{
				AdvertisementGstTaxConfig  advertisementGstTaxConfignew=ccDao.updateAdvertisementGstTaxConfig(cc.getAdvertisementGstTaxConfig());
				cc.setAdvertisementGstTaxConfig(advertisementGstTaxConfignew);
			}
			//if have id updating the miscellanious gst tax config   else creating  new config row and updating that row .
			
			
			if(cc.getMiscellaneousGstTaxConfig().getId()==null){
				MiscellaneousGstTaxConfig  miscellaneousGstTaxConfigInsert = ccDao.insertMiscellaneousGstTaxConfig(cc.getMiscellaneousGstTaxConfig());
				if(miscellaneousGstTaxConfigInsert!=null && miscellaneousGstTaxConfigInsert.getId()!=null){
				MiscellaneousGstTaxConfig  miscellaneousGstTaxConfignew = ccDao.updateMiscellaneousGstTaxConfig(miscellaneousGstTaxConfigInsert);
				cc.setMiscellaneousGstTaxConfig(miscellaneousGstTaxConfignew);
				}
			}else{
				MiscellaneousGstTaxConfig  miscellaneousGstTaxConfignew = ccDao.updateMiscellaneousGstTaxConfig(cc.getMiscellaneousGstTaxConfig());
				cc.setMiscellaneousGstTaxConfig(miscellaneousGstTaxConfignew);
			}
			
			
			//ended by basha for gst components update in 05-07-2017
			
		cc.setModifiedbyUserId(sessionUserObj.getId());
		cc.setCreatedbyComapnyId(getProfile.getCreatedbyComapnyId());
		cc.setCreatedbyUserId(getProfile.getCreatedbyUserId()); 
		cc.setAppKey(getProfile.getAppKey());
		cc.setCompanyName(getProfile.getCompanyName());
		cc.setCompany_id(getProfile.getCompany_id());
		cc.setConfig_number(getProfile.getConfig_number());
		cc.setCurrency_id(getProfile.getCurrency_id());
		cc.setCompanyUserId(getProfile.getCompanyUserId());
		if(cc.getConfigname()==null || cc.getConfigname().equals("")){
			cc.setConfigname(getProfile.getConfigname());
		}
		cc.setCompanyConfigType(getProfile.getCompanyConfigType());
		cc.setParent_config_id(getProfile.getParent_config_id());
		cc.setRateTypeFlight(getProfile.getRateTypeFlight());
		cc.setCommissionTypeFlight(getProfile.getCommissionTypeFlight());
		cc.setCommissionAmountFlight(getProfile.getCommissionAmountFlight());
		cc.setRateTypeHotel(getProfile.getRateTypeHotel());
		cc.setCommissionTypeHotel(getProfile.getCommissionTypeHotel());
		cc.setCommissionAmountHotel(getProfile.getCommissionAmountHotel());
		cc.setCompanyConfigType(getProfile.getCompanyConfigType());


	 	boolean updated = ccDao.updateCompanyconfig(cc);
		if(updated)
		{

			//ccDao.updateFlightPaymentConfig(flightPaymentConfig);
			List<FlightMarkup> flightMarkupIds=ccDao.getFlightMarkupIdsByConfigId(cc);
			List<HotelMarkup> hotelMarkupIds=ccDao.getHotelMarkupIdsByConfigId(cc);	
			if(flightMarkupIds!=null){
				ccDao.updateFlightmarkup(flightMarkupIds);
			}
			if(hotelMarkupIds!=null){
				ccDao.updateHotelmarkup(hotelMarkupIds);
			}
			addActionMessage(getText("global.updateCompanyConfigDetailssuccess"));
			new NotificationAction().insertNotificationOneandAll(sessionUserObj,String.valueOf(cc.getConfig_id()),"Company Configuration Updated","Company Configuration Updated",NotificationInventoryTypeEnum.COMPANY_CONFIG.getId(),true,false,false,true,false,false);
			return SUCCESS;
		}
		else
		{
			addActionError(getText("global.updatecompanyerror"));
			return ERROR;
		} 
		}catch(Exception e){
			addActionError(getText("global.updatecompanyerror"));
			return ERROR;
		}
		 
	} 
	/* fetching all company Config List*/
	public String getCompanyConfigList(){
		Company companyObj=(Company)sessionMap.get("Company");
		User userObj=(User)sessionMap.get("User");
		if(getPage()>0)
			page = getPage();
		List<CompanyConfig>	config_li= ccDao.getCompanyConfigList(userObj,companyObj,(page-1)*recordsPerPage,
				recordsPerPage);
		Long totalRecords = ccDao.count(null);
		noOfPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);
		if(config_li!=null && config_li.size()>0 ){
			configList=config_li;
		}
		return SUCCESS;
	}
	//method for  filter company configs  by company name

	public String filterConfigListByName(){
		List<CompanyConfig> filterConfigList=null;
		Company companyObj=(Company)sessionMap.get("Company");
		User userObj=(User)sessionMap.get("User");
		//paginationCommonPojo.setCompanyConfig(cc);
		if(getPage()>0)
			page = getPage();
		int offset=(page-1)*recordsPerPage;
		int maxResults=recordsPerPage;
		filterConfigList= ccDao.getCompanyConfigsByName(cc,0,
				0);
		Long totalRecords = ccDao.count(cc);
		companyConfig =cc;
		noOfPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);
		if(filterConfigList!=null && filterConfigList.size()>0){
			configList=filterConfigList;
		}
		else{
			setFilterBy("ALL");
			filterConfigList=ccDao.getCompanyConfigList(userObj,companyObj,offset,maxResults); 
			configList=filterConfigList;

		}
		return SUCCESS;
	}

	/* fetching all company Userid under parent company */
	public String getCompanyUserIds(){
		Company sessionObj=(Company)sessionMap.get("Company");
		User sessionUser=(User)sessionMap.get("User");
		List<Company> companyUserIdList=ccDao.getCompanyUserIds(sessionObj);
		configList =ccDao.getParentConfigIdsByCompanyId(sessionObj); 
		if(companyUserIdList!=null && companyUserIdList.size()>0){			
			for(Company company :companyUserIdList){
				if(company.isStatus()){					
					companyUserIdsList.add(company);
				}
			} 
		}
		if(sessionObj.getCompanyRole()!=null && !sessionObj.getCompanyRole().isAgent() && !sessionObj.getCompanyRole().isDistributor() && !sessionObj.getCompanyRole().isCorporate()){
			//CompanyConfig companyConfigNew=ccDao.getConfigByComnpanyId(sessionUser.getCompanyid());
		 companyConfigTax=ccDao.getConfigByComnpanyIdForB2ETaxes(sessionUser.getCompanyid());
		 
		} 
		HistoryInfo historyInfo = (HistoryInfo) ((sessionMap.get("history")!=null)?sessionMap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ADD_NEWCOMPANY_CONFIG, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  

		return SUCCESS;
	}

	public String setCompanyConfigLock(){
		logger.info("+---getConfig_id-------"+cc.getConfig_id());
		logger.info("+---getStatus-------"+cc.isActive());
		if(cc.isActive())  {
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_LOCK.getId();
			detailType=BrowsingHistoryDetailTypeEnum.COMPANY_LOCK.getId();		
			actionMessage="company Lock";
			logger.info("TRUE");
			cc.setActive(false);
		}
		else{
			statusCode = ActionStatusEnum.FAILED.getCode();
			statusCode = ActionStatusEnum.SUCCESS.getCode();
			actionId=BrowsingOptionActionEnum.ACTION_UNLOCK.getId();
			detailType=BrowsingHistoryDetailTypeEnum.COMPANY_UNLOCK.getId();
			actionMessage="company UnLock";
			logger.info("False");
			cc.setActive(true);
		}
		CompanyConfig companyConfig=ccDao.updateCompanyConfigLock(cc);
		logger.info(">STATUS---DB------"+companyConfig.isActive());
		if(companyConfig!=null){
			if(companyConfig.isActive()){
				addActionMessage("Company Configuration is now UnLocked."); 
			}
			else{
				addActionMessage("Company Configuration is now Locked."); 
			}
		}
		User user = (User)sessionMap.get("User");
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.ALL_COMPANY_CONFIG_ALL.getId(), actionId, statusCode, detailType, String.valueOf(user.getCompanyid()),actionMessage);
		return SUCCESS;
	}
	
	
	//method for upload White user  upload Logo file
	public String uploadWhiteLabelLogo(long id){
		String imageName=null;
		if(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)
		{
			MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();
			//String[] fileParameterNames = multiWrapper.getFileNames("Imagepath");
			Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
			if(fileParameterNames.hasMoreElements()){
				String inputValue = (String) fileParameterNames.nextElement(); 
				String[] localFileNames = multiWrapper.getFileNames(inputValue);
				UploadedFile[] files = multiWrapper.getFiles(inputValue); 
				String fileName = "";
				String fileType = "";
				//.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("png")|| fileType.equalsIgnoreCase("csv")|| fileType.equalsIgnoreCase("xlsx")
				for (UploadedFile cf : files) {
					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
					if(fileType!=null){
						//String file_path = "/home/WhiteLabel/";
						String file_path = "C:/Users/Ganesh murthy/Desktop/TestingDocs/";
						//File fileToCreate = new File(file_path, fileName+"_"+id+"."+fileType);
						File fileToCreate = new File(file_path, "wl-"+id+"."+fileType);
						try {
							if(cf!=null && cf.getContent()!=null)
							{
								File fi = (File) cf.getContent();

								FileUtils.copyFile(fi, fileToCreate);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							logger.error("----------IOEXCEPTION-----------"+e.getMessage());
							//e.printStackTrace();
						}
						imageName =  "wl-"+id+"."+fileType;

					}
				}
			}
		}
		return imageName;

	}

	
	
	
	@Override
	public CompanyConfig getModel() {
		// TODO Auto-generated method stub
		return cc;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);
	}
	public String getConfigStatus() {
		return configStatus;
	}
	public void setConfigStatus(String configStatus) {
		this.configStatus = configStatus;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public String getConfigCompanyName() {
		return configCompanyName;
	}
	public void setConfigCompanyName(String configCompanyName) {
		this.configCompanyName = configCompanyName;
	}
	public String getFilterBy() {
		return filterBy;
	}
	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}
	public String getParentConfigIdSplit() {
		return parentConfigIdSplit;
	}
	public void setParentConfigIdSplit(String parentConfigIdSplit) {
		this.parentConfigIdSplit = parentConfigIdSplit;
	}
	public String getWhiteLableType() {
		return whiteLableType;
	}
	public void setWhiteLableType(String whiteLableType) {
		this.whiteLableType = whiteLableType;
	}
	public List<CompanyConfig> getConfigList() {
		return configList;
	}
	public void setConfigList(List<CompanyConfig> configList) {
		this.configList = configList;
	}
	public List<Company> getCompanyUserIdsList() {
		return companyUserIdsList;
	}
	public void setCompanyUserIdsList(List<Company> companyUserIdsList) {
		this.companyUserIdsList = companyUserIdsList;
	}
	public String getDealSheetStatus() {
		return dealSheetStatus;
	}
	public void setDealSheetStatus(String dealSheetStatus) {
		this.dealSheetStatus = dealSheetStatus;
	}
	public List<AirlineCommissionCompanyBlock> getCompanyBlockList() {
		return companyBlockList;
	}
	public void setCompanyBlockList(List<AirlineCommissionCompanyBlock> companyBlockList) {
		this.companyBlockList = companyBlockList;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public int getRecordsPerPage() {
		return recordsPerPage;
	}
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}


	public CompanyConfig getCompanyConfig() {
		return companyConfig;
	}

	public void setCompanyConfig(CompanyConfig companyConfig) {
		this.companyConfig = companyConfig;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getFlightPaymentType() {
		return flightPaymentType;
	}

	public void setFlightPaymentType(String flightPaymentType) {
		this.flightPaymentType = flightPaymentType;
	}

	public String getHotelPaymentType() {
		return hotelPaymentType;
	}

	public void setHotelPaymentType(String hotelPaymentType) {
		this.hotelPaymentType = hotelPaymentType;
	}

	public String getFlightBookingCutOffType() {
		return flightBookingCutOffType;
	}

	public void setFlightBookingCutOffType(String flightBookingCutOffType) {
		this.flightBookingCutOffType = flightBookingCutOffType;
	}

	/*public String getHotelBookingCutOffType() {
		return hotelBookingCutOffType;
	}

	public void setHotelBookingCutOffType(String hotelBookingCutOffType) {
		this.hotelBookingCutOffType = hotelBookingCutOffType;
	}*/

	public String getBookingServiceType() {
		return bookingServiceType;
	}

	public void setBookingServiceType(String bookingServiceType) {
		this.bookingServiceType = bookingServiceType;
	}

	public String getFlightServiceTax() {
		return flightServiceTax;
	}

	public void setFlightServiceTax(String flightServiceTax) {
		this.flightServiceTax = flightServiceTax;
	}

	/*public String getHotelServiceTax() {
		return hotelServiceTax;
	}

	public void setHotelServiceTax(String hotelServiceTax) {
		this.hotelServiceTax = hotelServiceTax;
	}
	 */
	public String getFlightBookingCutOff() {
		return flightBookingCutOff;
	}

	public void setFlightBookingCutOff(String flightBookingCutOff) {
		this.flightBookingCutOff = flightBookingCutOff;
	}
	/*
	public String getHotelBookingCutOff() {
		return hotelBookingCutOff;
	}

	public void setHotelBookingCutOff(String hotelBookingCutOff) {
		this.hotelBookingCutOff = hotelBookingCutOff;
	}

	 */

	public String getFlightCutOffDate() {
		return flightCutOffDate;
	}

	public void setFlightCutOffDate(String flightCutOffDate) {
		this.flightCutOffDate = flightCutOffDate;
	}

	public String getHotelBookingCutOffType() {
		return hotelBookingCutOffType;
	}

	public void setHotelBookingCutOffType(String hotelBookingCutOffType) {
		this.hotelBookingCutOffType = hotelBookingCutOffType;
	}

	public String getHotelServiceTax() {
		return hotelServiceTax;
	}

	public void setHotelServiceTax(String hotelServiceTax) {
		this.hotelServiceTax = hotelServiceTax;
	}

	public String getHotelBookingCutOff() {
		return hotelBookingCutOff;
	}

	public void setHotelBookingCutOff(String hotelBookingCutOff) {
		this.hotelBookingCutOff = hotelBookingCutOff;
	}

	public long getFlightPaymentConfigId() {
		return flightPaymentConfigId;
	}

	public void setFlightPaymentConfigId(long flightPaymentConfigId) {
		this.flightPaymentConfigId = flightPaymentConfigId;
	}

	public long getBookingServiceConfigId() {
		return bookingServiceConfigId;
	}

	public void setBookingServiceConfigId(long bookingServiceConfigId) {
		this.bookingServiceConfigId = bookingServiceConfigId;
	}

	public long getFlightServiceTaxConfigId() {
		return flightServiceTaxConfigId;
	}

	public void setFlightServiceTaxConfigId(long flightServiceTaxConfigId) {
		this.flightServiceTaxConfigId = flightServiceTaxConfigId;
	}

	public long getFlightBookingCutoffConfigId() {
		return flightBookingCutoffConfigId;
	}

	public void setFlightBookingCutoffConfigId(long flightBookingCutoffConfigId) {
		this.flightBookingCutoffConfigId = flightBookingCutoffConfigId;
	}

	public long getHotelPaymentConfigId() {
		return hotelPaymentConfigId;
	}

	public void setHotelPaymentConfigId(long hotelPaymentConfigId) {
		this.hotelPaymentConfigId = hotelPaymentConfigId;
	}

	public long getHotelServiceTaxConfigId() {
		return hotelServiceTaxConfigId;
	}

	public void setHotelServiceTaxConfigId(long hotelServiceTaxConfigId) {
		this.hotelServiceTaxConfigId = hotelServiceTaxConfigId;
	}

	public long getHotelBookingCutoffConfigId() {
		return hotelBookingCutoffConfigId;
	}

	public void setHotelBookingCutoffConfigId(long hotelBookingCutoffConfigId) {
		this.hotelBookingCutoffConfigId = hotelBookingCutoffConfigId;
	}
  

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	  
	public ThemeInsertionRequestModel getInsertionRequestModel() {
		return insertionRequestModel;
	}

	public void setInsertionRequestModel(ThemeInsertionRequestModel insertionRequestModel) {
		this.insertionRequestModel = insertionRequestModel;
	}


	public CompanyConfigTax getCompanyConfigTax() {
		return companyConfigTax;
	}


	public void setCompanyConfigTax(CompanyConfigTax companyConfigTax) {
		this.companyConfigTax = companyConfigTax;
	}

}
