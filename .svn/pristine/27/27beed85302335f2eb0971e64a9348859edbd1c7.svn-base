package com.admin.hotel.fin.sheet.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.common.util.CommonUtilSession;
import com.admin.hotel.fin.sheet.Dao.HotelTravelRequestDao;
import com.admin.hotel.fin.sheet.Dao.TripRequestDao;
import com.admin.hotel.fin.sheet.model.HotelTravelRequest;
import com.admin.hotel.fin.sheet.model.HotelTravelRequestQuotation;
import com.admin.hotel.fin.sheet.model.TravelRequestStatusEnum;
import com.admin.hotel.fin.sheet.model.TripRequest;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.lintas.admin.model.User;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.notification.NotificationInventoryTypeEnum;
import com.tayyarah.notification.action.NotificationAction;

public class HotelTravelRequestAction extends ActionSupport implements ModelDriven<HotelTravelRequest>, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HotelTravelRequest hotelQuotationRow = new HotelTravelRequest();
	SessionMap<String, Object> sessionMap;
	HotelTravelRequestDao hotelOrderRowFineSheetDao = new HotelTravelRequestDao();
	private List<Country> countryList = null;
	private List<HotelTravelRequest> hotelQuotationRowlist = null;
	private List<HotelTravelRequestQuotation> hotelTravelRequestQuotationlist = null;
	private Long hotelQuotationRequestId;
	private Long tripId;
	List<String> fieldName = new ArrayList<String>();
	TripRequestDao tripRequestDao = new TripRequestDao();
	RmConfigModel rmConfigModel = new RmConfigModel();
	RmConfigDao rmConfigDao=new RmConfigDao();
	RmConfigTripDetailsModel configTripDetailsModel=new RmConfigTripDetailsModel();

	public String goHotelTravelRequest() {
		Company sessionCompany = (Company) sessionMap.get("Company");		
		RmConfigDao rmConfigDao = new RmConfigDao();
		try {
			setRmConfigModel(rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid()));
			String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("") ?rmConfigModel.getDynamicFieldsData().split(","):null;
			if(manualStringFields.length>0){
			for (String oneField : manualStringFields) {
						String fieldsName[] = oneField.split(":");
						fieldName.add(fieldsName[0]);
					}
			}
		} catch (Exception e) {
		}
		if(tripId != null){
			TripRequest tripRequest = tripRequestDao.getTripRequestById(tripId);
			configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
		}
		List<Country> list = new CountryDao().getCountryList();
		if (list != null && list.size() > 0) {
			countryList = new CountryDao().getCountryList();
		}
		setTripId(tripId);
		return SUCCESS;
	}

	public String createHotelTravelRequest() {
		User sessionUser = (User) sessionMap.get("User");
		Company sessionCompany = (Company) sessionMap.get("Company");
		hotelQuotationRow.setCheckInDate(DateConversion.StringToDate(hotelQuotationRow.getCheckIn()));
		hotelQuotationRow.setCheckOutDate(DateConversion.StringToDate(hotelQuotationRow.getCheckOut()));
		hotelQuotationRow.setCreatedAt(new Timestamp(new Date().getTime()));
		try {
			hotelQuotationRow.setCompanyId(sessionUser.getCompanyid());
			hotelQuotationRow.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
					? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
			hotelQuotationRow.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
			// logger.info("hotelQuotationRow-----"+hotelQuotationRow.getCheckIn());
			int noOfNights = CommonUtil.getNoofStayDays(hotelQuotationRow.getCheckIn(),
					hotelQuotationRow.getCheckOut());
			hotelQuotationRow.setNoOfNights(noOfNights);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HotelTravelRequest newHotelQuotationRow = hotelOrderRowFineSheetDao
				.insertHotelQuotationRowDetails(hotelQuotationRow);
		setHotelQuotationRequestId(newHotelQuotationRow.getId());
		if (newHotelQuotationRow != null) {
			configTripDetailsModel.setTrNumber(newHotelQuotationRow.getTRNo());
//			boolean isCompanyRmConfig=rmConfigDao.isCompanyHavingRmConfig(sessionCompany);
			if (tripId != null) {
				TripRequest tripRequest = new TripRequest();
				tripRequest.setId(tripId);
				tripRequest.setHotelTravelRequest(newHotelQuotationRow);
				tripRequest.setCompanyId(sessionUser.getCompanyid());
				tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
						? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
				tripRequest = tripRequestDao.updateTripRequestById(tripRequest);
				/*if(isCompanyRmConfig){
					RmConfigTripDetailsModel rmconfigTripDetailsModel = new GetNewRmConfigDetail().getRmConfigDetail(configTripDetailsModel,"Hotel");
					rmConfigDao.insertTripConfigDetails(rmconfigTripDetailsModel);
				}*/

				if (tripRequest != null && tripRequest.getId() > 0) {
					addActionMessage("Successfully created");
				}
			} else {
				TripRequest tripRequest = new TripRequest();
				tripRequest.setHotelTravelRequest(newHotelQuotationRow);
				tripRequest.setCreatedAt(new Timestamp(new Date().getTime()));
				// tripRequest.setTripId(RandomConfigurationNumber.generateTripId(newHotelQuotationRow.getId()));
				tripRequest.setCompanyId(sessionUser.getCompanyid());
				tripRequest.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
						? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
				tripRequest = tripRequestDao.insertTripRequest(tripRequest);
				if (tripRequest != null && tripRequest.getId() > 0) {
					if (tripRequest != null && tripRequest.getId() > 0) {
						tripRequest.setTripId(RandomConfigurationNumber.generateTripId(tripRequest.getId()));
						if (tripRequestDao.updateTripRequestNumber(tripRequest))
							addActionMessage("Successfully created");
						else
							addActionMessage("Something went wrong.Please wait.");
					}

				}
			}
			new NotificationAction().insertNotificationOneandAll(sessionUser,
					String.valueOf(newHotelQuotationRow.getId()), "Hotal book Request created",
					"Hotal book Request notification", NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(), false,
					false, false, true, false, false);
			return SUCCESS;
		}else{
			addActionError("We found some error.Please Try again.");
			return ERROR;
		}
	}

	public String getHotelTravelRequestList() {

		User sessionUser=(User)sessionMap.get("User");
		List<HotelTravelRequest> list = hotelOrderRowFineSheetDao.loadHotelQuotationRowList(sessionUser);
		if (list != null && list.size() > 0) {
			hotelQuotationRowlist = list;
		}
		return SUCCESS;
	}

	public HotelTravelRequest getHotelQuotationRow() {
		return hotelQuotationRow;
	}

	public void setHotelQuotationRow(HotelTravelRequest hotelQuotationRow) {
		this.hotelQuotationRow = hotelQuotationRow;
	}

	public String getHotelTravelRequestDetails() {
		Company sessionCompany = (Company) sessionMap.get("Company");
		hotelQuotationRow = hotelOrderRowFineSheetDao.getHotelTravelRequestDetails(hotelQuotationRow.getId());
		hotelQuotationRow.setCheckIn(DateConversion.convertDateToStringToDate(hotelQuotationRow.getCheckInDate()));
		hotelQuotationRow.setCheckOut(DateConversion.convertDateToStringToDate(hotelQuotationRow.getCheckOutDate()));
		List<Country> list = new CountryDao().getCountryList();
		if (list != null && list.size() > 0) {
			countryList = new CountryDao().getCountryList();
		}
		hotelTravelRequestQuotationlist = hotelOrderRowFineSheetDao.getHotelRequestTravelQuotationBookedList(hotelQuotationRow.getId());
		RmConfigDao rmConfigDao=new RmConfigDao();
		try{
			setRmConfigModel(rmConfigDao.getConfigDetailsByCompanyId(sessionCompany.getCompanyid()));
			String manualStringFields[] = rmConfigModel.getDynamicFieldsData()!=null && !rmConfigModel.getDynamicFieldsData().trim().equalsIgnoreCase("") ?rmConfigModel.getDynamicFieldsData().split(","):null;
			if(manualStringFields.length>0){
			for(String oneField:manualStringFields){
				String fieldsName[]=oneField.split(":");
				fieldName.add(fieldsName[0]);
			}
			}
		}catch (Exception e) {
		}
		TripRequest tripRequest = tripRequestDao.getTripRequestTripNumber(hotelQuotationRow.getId(),"H");
		configTripDetailsModel = rmConfigDao.getTripConfigDetails(tripRequest.getTripId());
		tripId = tripRequest.getTripId();
		return SUCCESS;
	}

	public String updateHotelTravelRequestDetails() {
		User sessionUser = (User) sessionMap.get("User");
		// HotetTravelRequest
		// hotelTravelRequest=hotelOrderRowFineSheetDao.getHotelTravelRequestDetails(hotelQuotationRow.getId());
		hotelQuotationRow.setCheckInDate(DateConversion.StringToDate(hotelQuotationRow.getCheckIn()));
		hotelQuotationRow.setCheckOutDate(DateConversion.StringToDate(hotelQuotationRow.getCheckOut()));
		hotelQuotationRow.setUserId(CommonUtilSession.checkEmulatedUser(sessionMap)
				? CommonUtilSession.getEmulatedUserIdInt(sessionMap) : sessionUser.getId());
		hotelQuotationRow.setStatusId(TravelRequestStatusEnum.CREATED.getValue());
		// hotelQuotationRow.setCreatedAt(hotelTravelRequest.getCreatedAt());
		try {
			int noOfNights = CommonUtil.getNoofStayDays(hotelQuotationRow.getCheckIn(),
					hotelQuotationRow.getCheckOut());
			hotelQuotationRow.setNoOfNights(noOfNights);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HotelTravelRequest hotetTravelRequestNew = hotelOrderRowFineSheetDao
				.updateHotelTravelRequestDetails(hotelQuotationRow);
		if (hotetTravelRequestNew != null) {
			addActionMessage("Successfully Updated.");
			new NotificationAction().insertNotificationOneandAll(sessionUser,
					String.valueOf(hotetTravelRequestNew.getId()), "Hotal book Request updated",
					"Hotal book Request notification", NotificationInventoryTypeEnum.HOTEL_BOOKREQUEST.getId(), false,
					false, false, true, false, false);

			return SUCCESS;
		} else {
			addActionError("We found some error.Please try again.");
			return ERROR;
		}
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap<String, Object>) map;
	}

	@Override
	public HotelTravelRequest getModel() {
		// TODO Auto-generated method stub
		return hotelQuotationRow;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<HotelTravelRequest> getHotelQuotationRowlist() {
		return hotelQuotationRowlist;
	}

	public void setHotelQuotationRowlist(List<HotelTravelRequest> hotelQuotationRowlist) {
		this.hotelQuotationRowlist = hotelQuotationRowlist;
	}

	public Long getHotelQuotationRequestId() {
		return hotelQuotationRequestId;
	}

	public void setHotelQuotationRequestId(Long hotelQuotationRequestId) {
		this.hotelQuotationRequestId = hotelQuotationRequestId;
	}

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public List<HotelTravelRequestQuotation> getHotelTravelRequestQuotationlist() {
		return hotelTravelRequestQuotationlist;
	}

	public void setHotelTravelRequestQuotationlist(List<HotelTravelRequestQuotation> hotelTravelRequestQuotationlist) {
		this.hotelTravelRequestQuotationlist = hotelTravelRequestQuotationlist;
	}

	public RmConfigModel getRmConfigModel() {
		return rmConfigModel;
	}

	public void setRmConfigModel(RmConfigModel rmConfigModel) {
		this.rmConfigModel = rmConfigModel;
	}

	public List<String> getFieldName() {
		return fieldName;
	}

	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}

	public RmConfigTripDetailsModel getConfigTripDetailsModel() {
		return configTripDetailsModel;
	}

	public void setConfigTripDetailsModel(RmConfigTripDetailsModel configTripDetailsModel) {
		this.configTripDetailsModel = configTripDetailsModel;
	}

}
