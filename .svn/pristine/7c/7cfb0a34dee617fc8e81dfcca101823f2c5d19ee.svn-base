package com.lintas.admin.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.FlightWeekandMonthOrderDao;
import com.lintas.admin.DAO.HotelWeekandMonthOrderDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.opensymphony.xwork2.ActionSupport;

public class HotelOverViewAction extends ActionSupport implements SessionAware{

	/**
	 * @author info raham
	 * created at:30-09-2015
	 */
	private static final long serialVersionUID = 1L;

	FlightWeekandMonthOrderDao monthOrderDao =new  FlightWeekandMonthOrderDao();
	HotelWeekandMonthOrderDao hotelorders = new HotelWeekandMonthOrderDao();
	CompanyDAO companyDAO = new CompanyDAO();
	UserDAO userdao = new UserDAO();
	Map<String,Long> jsonobj  =  new HashMap<String, Long>();
	SessionMap<String , Object> sessionMap;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelOverViewAction.class);
	public String getHotelOrderJson()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		try{
			if(userSessionObj!=null && companySessionObj!=null)
			{
				StringBuffer userList = getUserIdListForAllCompany(userSessionObj,companySessionObj);
				Long HotelOrdercount =new Long(0);
				try{
					HotelOrdercount = hotelorders.getHotelTodayOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}

				Long Hotelconfirmordercount =new Long(new Long(0));
				try{
					Hotelconfirmordercount = hotelorders.getHotelTodayConfirmOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}

				Long Hotelpaymentordercount =new Long(0);
				try{
					Hotelpaymentordercount = hotelorders.getHotelTodayPaymentOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Hotelpaymentorderfailedcount =new Long(0);
				try{
					Hotelpaymentorderfailedcount = hotelorders.getHotelTodayPaymentFailedOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}

				jsonobj.put("HotelOrdercount",HotelOrdercount);
				jsonobj.put("Hotelconfirmordercount",Hotelconfirmordercount);
				jsonobj.put("Hotelpaymentordercount",Hotelpaymentordercount);
				jsonobj.put("Hotelpaymentfailedordercount",Hotelpaymentorderfailedcount);
			}
			else{
				jsonobj.put("error-not-login",new Long(0));
			}
		}catch(Exception e)
		{
			logger.error(e);
			jsonobj.put("error-exception",new Long(0));
		}
		return SUCCESS;

	}
	public String getFlightOrderJson()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		try{
			if(userSessionObj!=null && companySessionObj!=null)
			{
				Long FlightOrdercount =new Long(0);
				StringBuffer userList = getUserIdListForAllCompany(userSessionObj,companySessionObj);
				try{
					FlightOrdercount = monthOrderDao.getFlightTodayOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}

				Long Flightconfirmordercount =new Long(0);
				try{
					Flightconfirmordercount = monthOrderDao.getFlightTodayConfirmOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Flightpaymentordercount =new Long(0);
				try{
					Flightpaymentordercount= 	monthOrderDao.getFlightTodayPaymentOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Flightpaymentfailedordercount =new Long(0);
				try{
					Flightpaymentfailedordercount= 	monthOrderDao.getFlightTodayPaymentFailedOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				jsonobj.put("FlightOrdercount",FlightOrdercount);
				jsonobj.put("Flightconfirmordercount",Flightconfirmordercount);
				jsonobj.put("Flightpaymentordercount",Flightpaymentordercount);
				jsonobj.put("Flightpaymentfailedordercount",Flightpaymentfailedordercount);
			}
			else{
				jsonobj.put("error-not-login",new Long(0));
			}
		}catch(Exception e)
		{
			logger.error(e);
			jsonobj.put("error-exception",new Long(0));
		}
		return SUCCESS;

	}

	public String getCarOrderJson()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		try{
			if(userSessionObj!=null && companySessionObj!=null)
			{
				StringBuffer userList = getUserIdListForAllCompany(userSessionObj,companySessionObj);
				Long CarOrdercount =new Long(0);
				try{
					CarOrdercount = monthOrderDao.getCarTodayOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}


				Long Carconfirmordercount =new Long(0);
				try{
					Carconfirmordercount = monthOrderDao.getCarTodayConfirmOrdersCount(userSessionObj,companySessionObj , userList);

				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Carpaymentordercount =new Long(0);
				try{
					Carpaymentordercount= 	monthOrderDao.getCarTodayPaymentOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Carpaymentfailedordercount =new Long(0);
				try{
					Carpaymentfailedordercount= 	monthOrderDao.getCarTodayPaymentFailedOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}


				jsonobj.put("CarOrdercount",CarOrdercount);
				jsonobj.put("Carconfirmordercount",Carconfirmordercount);
				jsonobj.put("Carpaymentordercount",Carpaymentordercount);
				jsonobj.put("Carpaymentfailedordercount",Carpaymentfailedordercount);
			}
			else{
				jsonobj.put("error-not-login",new Long(0));
			}
		}catch(Exception e)
		{
			logger.error(e);
			jsonobj.put("error-exception",new Long(0));
		}
		return SUCCESS;

	}
	public String getInsuranceOrderJson()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		try{
			if(userSessionObj!=null && companySessionObj!=null)
			{
				StringBuffer userList = getUserIdListForAllCompany(userSessionObj,companySessionObj);
				Long InsuranceOrdercount =new Long(0);
				try{
					InsuranceOrdercount = monthOrderDao.getInsuranceTodayOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}


				Long Insuranceconfirmordercount =new Long(0);
				try{
					Insuranceconfirmordercount = monthOrderDao.getInsuranceTodayConfirmOrdersCount(userSessionObj,companySessionObj , userList);

				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Insurancepaymentordercount =new Long(0);
				try{
					Insurancepaymentordercount= 	monthOrderDao.getInsuranceTodayPaymentOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Insurancepaymentfailedordercount =new Long(0);
				try{
					Insurancepaymentfailedordercount= 	monthOrderDao.getInsuranceTodayPaymentFailedOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}



				//int totalagentlist = newUsersList.size();

				jsonobj.put("InsuranceOrdercount",InsuranceOrdercount);
				jsonobj.put("Insuranceconfirmordercount",Insuranceconfirmordercount);
				jsonobj.put("Insurancepaymentordercount",Insurancepaymentordercount);
				jsonobj.put("Insurancepaymentfailedordercount",Insurancepaymentfailedordercount);
			}
			else{
				jsonobj.put("error-not-login",new Long(0));
			}
		}catch(Exception e)
		{
			logger.error(e);
			jsonobj.put("error-exception",new Long(0));
		}
		return SUCCESS;

	}
	public String getVisaOrderJson()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		try{
			if(userSessionObj!=null && companySessionObj!=null)
			{
				StringBuffer userList = getUserIdListForAllCompany(userSessionObj,companySessionObj);


				Long VisaOrdercount =new Long(0);
				try{
					VisaOrdercount = monthOrderDao.getVisaTodayOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}


				Long Visaconfirmordercount =new Long(0);
				try{
					Visaconfirmordercount = monthOrderDao.getVisaTodayConfirmOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Visapaymentordercount =new Long(0);
				try{
					Visapaymentordercount= 	monthOrderDao.getVisaTodayPaymentOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Visapaymentfailedordercount =new Long(0);
				try{
					Visapaymentfailedordercount= 	monthOrderDao.getVisaTodayPaymentFailedOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}



				jsonobj.put("VisaOrdercount",VisaOrdercount);
				jsonobj.put("Visaconfirmordercount",Visaconfirmordercount);
				jsonobj.put("Visapaymentordercount",Visapaymentordercount);
				jsonobj.put("Visapaymentfailedordercount",Visapaymentfailedordercount);
			}
			else{
				jsonobj.put("error-not-login",new Long(0));
			}
		}catch(Exception e)
		{
			logger.error(e);
			jsonobj.put("error-exception",new Long(0));
		}
		return SUCCESS;

	}
	public String getBusOrderJson()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		try{
			if(userSessionObj!=null && companySessionObj!=null)
			{
				StringBuffer userList = getUserIdListForAllCompany(userSessionObj,companySessionObj);


				Long BusOrdercount =new Long(0);
				List<BusOrderRow> busOrderRows= null;
				try{
					BusOrdercount = monthOrderDao.getBusTodayOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}


				Long Busconfirmordercount =new Long(0);
				try{
					Busconfirmordercount = monthOrderDao.getBusTodayConfirmOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Buspaymentordercount =new Long(0);
				try{
					Buspaymentordercount= 	monthOrderDao.getBusTodayPaymentOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				Long Buspaymentfailedordercount =new Long(0);
				try{
					Buspaymentfailedordercount= 	monthOrderDao.getBusTodayPaymentFailedOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}


				jsonobj.put("BusOrdercount",BusOrdercount);
				jsonobj.put("Busconfirmordercount",Busconfirmordercount);
				jsonobj.put("Buspaymentordercount",Buspaymentordercount);
				jsonobj.put("Buspaymentfailedordercount",Buspaymentfailedordercount);
			}
			else{
				jsonobj.put("error-not-login",new Long(0));
			}
		}catch(Exception e)
		{
			logger.error(e);
			jsonobj.put("error-exception",new Long(0));
		}
		return SUCCESS;

	}
	public String getTrainOrderJson()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		try{
			if(userSessionObj!=null && companySessionObj!=null)
			{
				StringBuffer userList = getUserIdListForAllCompany(userSessionObj,companySessionObj);

				Long TrainOrdercount =new Long(0);
				List<TrainOrderRow> trainOrderRows= null;
				try{
					TrainOrdercount = monthOrderDao.getTrainTodayOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",TrainOrdercount);
				}


				Long Trainconfirmordercount =new Long(0);
				try{
					Trainconfirmordercount = monthOrderDao.getTrainTodayConfirmOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",Trainconfirmordercount);
				}
				Long Trainpaymentordercount =new Long(0);
				try{
					Trainpaymentordercount= 	monthOrderDao.getTrainTodayPaymentOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",Trainpaymentordercount);
				}
				Long Trainpaymentfailedordercount =new Long(0);
				try{
					Trainpaymentfailedordercount= 	monthOrderDao.getTrainTodayPaymentFailedOrdersCount(userSessionObj,companySessionObj , userList);
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",Trainpaymentfailedordercount);
				}


				jsonobj.put("TrainOrdercount",TrainOrdercount);
				jsonobj.put("Trainconfirmordercount",Trainconfirmordercount);
				jsonobj.put("Trainpaymentordercount",Trainpaymentordercount);
				jsonobj.put("Trainpaymentfailedordercount",Trainpaymentfailedordercount);
			}
			else{
				jsonobj.put("error-not-login",new Long(0));
			}
		}catch(Exception e)
		{
			logger.error(e);
			jsonobj.put("error-exception",new Long(0));
		}
		return SUCCESS;

	}
	public String getCompanyUserJson()
	{
		User userSessionObj = (User)sessionMap.get("User");
		Company companySessionObj = (Company)sessionMap.get("Company");
		try{
			if(userSessionObj!=null && companySessionObj!=null)
			{
				int totaldistributorlist = 0;
				try
				{
					if(userSessionObj.getUserrole_id().isSuperuser())
					{
						List<Company> companies = companyDAO.getAllDistributors(userSessionObj,companySessionObj) ;
						if(companies!=null && companies.size()>0)
							totaldistributorlist = companies.size();
					}
				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}

				List<User> allUsersList = userdao.getAllAgentsByCompanyId(userSessionObj,companySessionObj);
				List<User> newUsersList=new ArrayList<User>();
				int newUsersListSize = 0 ;
				if(allUsersList!=null  &&   allUsersList.size()>0){
					newUsersListSize = allUsersList.size();
					for(User userNew:allUsersList) {
						if(!userNew.getUserrole_id().isSuperuser() && !userNew.getUserrole_id().isUsermode()){
							if(!userNew.getUsername().equalsIgnoreCase("DirectUser")){
								newUsersList.add(userNew);
							}
						}
					}
				}
				List<Company>  allAgenciesList=userdao.getAllAgencyCompanyIds(companySessionObj);
				List<Company>  newAgenciesList= new ArrayList<>();
				if(allAgenciesList!=null  &&  allAgenciesList.size()>0){
					for(Company agency:allAgenciesList){
						if(agency.getCompanyRole().isAgent()){
							newAgenciesList.add(agency);
						}
					}
				}
				//logger.info("allAgenciesList------------"+newAgenciesList.size());
				int totalagentlist = 0;
				if(newAgenciesList!=null  &&  newAgenciesList.size()>0){
					totalagentlist = newAgenciesList.size();
				}
				int totalcorporatelist = 0;
				try
				{
					if(userSessionObj.getUserrole_id().isSuperuser())
					{
						List<Company> companies = companyDAO.getAllCorporates(userSessionObj,companySessionObj) ;
						if(companies!=null && companies.size()>0)
							totalcorporatelist = companies.size();
					}

				}
				catch(Exception e)
				{
					logger.error(e);
					jsonobj.put("error-exception",new Long(0));
				}
				jsonobj.put("totaldistributorlist",new Long(totaldistributorlist));
				jsonobj.put("totalcorporatelist",new Long(totalcorporatelist));
				jsonobj.put("totalagentlist",new Long(totalagentlist));
				jsonobj.put("totalemployeecount",new Long(newUsersListSize));
			}
			else{
				jsonobj.put("error-not-login",new Long(0));
			}
		}catch(Exception e)
		{
			logger.error(e);
			jsonobj.put("error-exception",new Long(0));
		}
		return SUCCESS;

	}

	private StringBuffer getUserIdListForAllCompany(User userSessionObj, Company companySessionObj) {
		StringBuffer userIdBuffer = new StringBuffer();
		if(userSessionObj!=null && companySessionObj!=null){
			if(userSessionObj.getUserrole_id().isSuperuser()){
				userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
			}
			else if(userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()){
				if(!companySessionObj.getCompanyRole().isCorporate())
				{
					userIdBuffer = companyDAO.GetAllUserListUnderCompany(userSessionObj,companySessionObj);
				}
			}
		}
		return userIdBuffer;
	}



	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap=(SessionMap<String, Object>) map;

	}
	public Map<String, Long> getJsonobj() {
		return jsonobj;
	}
	public void setJsonobj(Map<String, Long> jsonobj) {
		this.jsonobj = jsonobj;
	}



}
