package com.admin.dashboard.analysis.json.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.dashboard.Vo.DashBoardJsonVo;
import com.admin.dashboard.analysis.json.dao.AllServicesCommonPieChartDao;
import com.admin.dashboard.analysis.json.daoImpl.AllServicesCommonPieChartDaoImpl;
import com.admin.dashboard.analysis.json.vo.ErrorMsg;
import com.admin.dashboard.analysis.json.vo.ServicesCountDataVO;
import com.admin.dashboard.analysis.json.vo.ServicesCountVO;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class AllServicesPieChartCountAction extends ActionSupport implements SessionAware{
	/**
	 * @author Basha
	 * 28 Aug 2017
	 */
	public static final Logger logger = Logger.getLogger(AllServicesPieChartCountAction.class);
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	CompanyDAO companyDAO = new CompanyDAO();
	String type="BK";
	AllServicesCommonPieChartDao allServicesCommonPieChartDao=new AllServicesCommonPieChartDaoImpl();
	ServicesCountVO servicesData=new ServicesCountVO();
	
	public String allServicesTodayBookingCount() {
		DashBoardJsonVo countForFlight = new DashBoardJsonVo();
		DashBoardJsonVo countForHotel = new DashBoardJsonVo();
		DashBoardJsonVo countForCar = new DashBoardJsonVo();
		DashBoardJsonVo countForBus = new DashBoardJsonVo();
		DashBoardJsonVo countForTrain = new DashBoardJsonVo();
		DashBoardJsonVo countForVisa = new DashBoardJsonVo();
		DashBoardJsonVo countForInsurance = new DashBoardJsonVo();
		DashBoardJsonVo countForMisc = new DashBoardJsonVo();
		Long totalServicesCount = new Long(0);
		String ServiceTypeFlight="Flight";
		String ServiceTypeHotel="Hotel";
		String ServiceTypeBus="Bus";
		String ServiceTypeCar="Car";
		String ServiceTypeTrain="Train";
		String ServiceTypeVisa="Visa";
		String ServiceTypeInsurance="Insurance";
		String ServiceTypeMisc="Misc";
		ErrorMsg error=null;
			User userSessionObj = (User) sessionMap.get("User");
			Company companySessionObj = (Company) sessionMap.get("Company");
			try {

				if (userSessionObj != null && companySessionObj != null) {
					List<User> userList = getUserIdListForAllCompanyList(userSessionObj, companySessionObj);
					List<String> userIdList= new ArrayList<>(); 
					if(userList!=null && userList.size()>0)
					{
						for(User user : userList)
						{
							if(companySessionObj.getCompanyRole().isCorporate() ){
							if(user.getUserrole_id().isUsermode())
								userIdList.add(String.valueOf(user.getId()));
							}
							else
								userIdList.add(String.valueOf(user.getId()));
								
						}
					}
					
					try {
						
						if(!type.equals("")){
							
						}
						
							countForFlight = allServicesCommonPieChartDao.getAllServicesOrdersCount(userSessionObj, companySessionObj, userIdList, type,ServiceTypeFlight);
							countForHotel = allServicesCommonPieChartDao.getAllServicesOrdersCount(userSessionObj, companySessionObj, userIdList, type,ServiceTypeHotel);	
							countForCar = allServicesCommonPieChartDao.getAllServicesOrdersCount(userSessionObj, companySessionObj, userIdList, type,ServiceTypeCar);	
							countForBus = allServicesCommonPieChartDao.getAllServicesOrdersCount(userSessionObj, companySessionObj, userIdList, type,ServiceTypeBus);	
							countForTrain = allServicesCommonPieChartDao.getAllServicesOrdersCount(userSessionObj, companySessionObj, userIdList, type,ServiceTypeTrain);	
							countForVisa = allServicesCommonPieChartDao.getAllServicesOrdersCount(userSessionObj, companySessionObj, userIdList, type,ServiceTypeVisa);	
							countForInsurance = allServicesCommonPieChartDao.getAllServicesOrdersCount(userSessionObj, companySessionObj, userIdList, type,ServiceTypeInsurance);	
							countForMisc = allServicesCommonPieChartDao.getAllServicesOrdersCount(userSessionObj, companySessionObj, userIdList, type,ServiceTypeMisc);	
							totalServicesCount=countForFlight.getCount()+countForHotel.getCount()+countForCar.getCount()+countForBus.getCount()+countForTrain.getCount()+countForVisa.getCount()+countForInsurance.getCount()+countForMisc.getCount();
							 
							if(totalServicesCount==0){
								servicesData.setTotalCount(totalServicesCount);
								error=new ErrorMsg();
								error.setMessage("No Bookings");
							}
							else{
							ServicesCountDataVO flight=new ServicesCountDataVO(ServiceTypeFlight, countForFlight.getCount(), countForFlight.getCancelledCount(), (countForFlight.getCount() * 100) / totalServicesCount, countForFlight.getTotalBookingAmount(), countForFlight.getTotalRefundedAmount(), countForFlight.getTotalLossAmount());
							ServicesCountDataVO hotel=new ServicesCountDataVO(ServiceTypeHotel, countForHotel.getCount(), countForHotel.getCancelledCount(), (countForHotel.getCount() * 100) / totalServicesCount, countForHotel.getTotalBookingAmount(), countForHotel.getTotalRefundedAmount(), countForHotel.getTotalLossAmount());
							ServicesCountDataVO car=new ServicesCountDataVO(ServiceTypeCar, countForCar.getCount(), countForCar.getCancelledCount(), (countForCar.getCount() * 100) / totalServicesCount, countForCar.getTotalBookingAmount(), countForCar.getTotalRefundedAmount(), countForCar.getTotalLossAmount());
							ServicesCountDataVO bus=new ServicesCountDataVO(ServiceTypeBus, countForBus.getCount(), countForBus.getCancelledCount(), (countForBus.getCount() * 100) / totalServicesCount, countForBus.getTotalBookingAmount(), countForBus.getTotalRefundedAmount(), countForBus.getTotalLossAmount());
							ServicesCountDataVO train=new ServicesCountDataVO(ServiceTypeTrain, countForTrain.getCount(), countForTrain.getCancelledCount(), (countForTrain.getCount() * 100) / totalServicesCount, countForTrain.getTotalBookingAmount(), countForTrain.getTotalRefundedAmount(), countForTrain.getTotalLossAmount());
							ServicesCountDataVO visa=new ServicesCountDataVO(ServiceTypeVisa, countForVisa.getCount(), countForVisa.getCancelledCount(), (countForVisa.getCount() * 100) / totalServicesCount, countForVisa.getTotalBookingAmount(), countForVisa.getTotalRefundedAmount(), countForVisa.getTotalLossAmount());
							ServicesCountDataVO insurance=new ServicesCountDataVO(ServiceTypeInsurance, countForInsurance.getCount(), countForInsurance.getCancelledCount(), (countForInsurance.getCount() * 100) / totalServicesCount, countForInsurance.getTotalBookingAmount(), countForInsurance.getTotalRefundedAmount(), countForInsurance.getTotalLossAmount());
							ServicesCountDataVO misc=new ServicesCountDataVO(ServiceTypeMisc, countForMisc.getCount(), countForMisc.getCancelledCount(), (countForMisc.getCount() * 100) / totalServicesCount, countForMisc.getTotalBookingAmount(), countForMisc.getTotalRefundedAmount(), countForMisc.getTotalLossAmount());
							servicesData=new ServicesCountVO(flight, hotel, car, bus, train, visa, insurance, misc);
							servicesData.setTotalCount(totalServicesCount);
							}
							
					} catch (Exception e) {
						logger.error(e);
						error=new ErrorMsg();
						error.setMessage("exception :"+e.getMessage());
					}
				}
				else {
					error=new ErrorMsg();
					error.setMessage("Session Expired.");
				}
			} catch (Exception e) {
				logger.error(e);
				error=new ErrorMsg();
				error.setMessage("exception :"+e.getMessage());
			}
			if(error!=null) 
				servicesData.setError(error);
			return SUCCESS;

		}


		private List<User> getUserIdListForAllCompanyList(User userSessionObj, Company companySessionObj) {
			List<User> userIdBuffer = new ArrayList<>();
			if (userSessionObj != null && companySessionObj != null) {
				if (userSessionObj.getUserrole_id().isSuperuser()) {
					userIdBuffer = companyDAO.GetAllUserListUnderCompanyAsList(userSessionObj, companySessionObj);
				} else if (userSessionObj.getUserrole_id().isUsermode() || userSessionObj.getUserrole_id().isAdmin()) {
					if (companySessionObj.getCompanyRole().isCorporate()) {
						userIdBuffer = companyDAO.GetAllUserListUnderCompanyAsList(userSessionObj, companySessionObj);
					}
				}
			}
			return userIdBuffer;
		}

		@Override
		public void setSession(Map<String, Object> map) {
			sessionMap = (SessionMap<String, Object>) map;

		}

	

		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public ServicesCountVO getServicesData() {
			return servicesData;
		}


		public void setServicesData(ServicesCountVO servicesData) {
			this.servicesData = servicesData;
		}


	
	}
