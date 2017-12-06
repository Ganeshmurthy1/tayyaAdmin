package com.lintas.action;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

import com.admin.common.util.CommonUtilSession;
import com.hotel.cancellation.APIHotelCancelRequest;
import com.hotel.cancellation.APIStatus;
import com.hotel.cancellation.HttpClient;
import com.lintas.action.CreditNote.Dao.HotelCreditNoteDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.HotelOrderDao;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.common.model.HotelCreditNote;
import com.lintas.admin.common.model.HotelOrderModifyInfo;
import com.lintas.admin.common.model.HotelOrderRowCancellation;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HotelOrderModifyInfoAction extends ActionSupport implements ModelDriven<HotelCreditNote>,SessionAware{

	/**@author info raham
	 * created date:07-10-2015
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelOrderModifyInfoAction.class);
	private static final long serialVersionUID = 1L;
	HotelCreditNote creditNote = new HotelCreditNote();
	SessionMap<String, Object> sessionMap;
	OrderModifyDao orderModifyDao=new OrderModifyDao();


	//Constants actions
	public final static int ACTION_DEFAULT = 0;
	public final static int ACTION_CANCEL = 1;
	public final static int ACTION_GET_STATUS = 2;
	public final static int ACTION_SUBMIT_ORDER_CHANGE = 3;



	public String insertHotelOrderModifiedInfo(){
		Company sessCompanyObj=(Company)sessionMap.get("Company");
		User sessionUser=(User)sessionMap.get("User");
		logger.info("------- Hotel order change page call--------");

		HotelOrderRow hotelOrderRow=new HotelOrderRow();
		HotelOrderModifyInfo orderModifiedInfo=new HotelOrderModifyInfo();
		HotelOrderRow hotelOrderRowDetails=new HotelCreditNoteDao().getHotelOrderRowDataForCreditNote(creditNote.getHotelOrderRowId());
		Map<String, String> statusMap=null;
		String message=null;
		if(creditNote.getEmployeeComments()==null || creditNote.getEmployeeComments().equals("")){
			creditNote.setEmployeeComments("no remarks");
		}

		try 
		{
			if(hotelOrderRowDetails!=null)// &&(sessCompanyObj.getCompanyid()==Integer.parseInt(hotelOrderRowDetails.getCompanyId())))
			{
				logger.info("hotelOrderRowDetails.getCompanyId()---"+hotelOrderRowDetails.getCompanyId());
				/*int pageActionType = creditNote.getCancelType();
				logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);*/
				
				
				
				
				
				
				//edited by basha
				if(hotelOrderRowDetails.getCancelMode()!=null && hotelOrderRowDetails.getCancelMode().equalsIgnoreCase("Online"))
				{
					HotelOrderRowCancellation HotelOrderRowCancellation=new HotelOrderDao().getHotelOrderRowCancellation(hotelOrderRowDetails.getOrderReference());
					//HotelOrderRowCancellation HotelOrderRowCancellation=new CreditNoteDao().getHotelOrderRowCancellationWithAPIStatus(hotelOrderRowDetails.getOrderReference());
					int pageActionType = Integer.parseInt(HotelOrderRowCancellation.getAPIStatusCode());
					logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);
					switch (pageActionType) 
					{					
					case ACTION_SUBMIT_ORDER_CHANGE:

						logger.info("------- API Cancel response statusMap--------"+statusMap);
						if(HotelOrderRowCancellation!=null && HotelOrderRowCancellation.getAPIStatusCode() != null && HotelOrderRowCancellation.getAPIStatusCode().equalsIgnoreCase("3"))
						{
							message = HotelOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							if(message!=null)
							{
								hotelOrderRow.setId(creditNote.getHotelOrderRowId());
								hotelOrderRow.setStatusAction(creditNote.getStatusAction());
								hotelOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								hotelOrderRow.setOrderRequested(true);
								hotelOrderRow.setCancelMode(hotelOrderRowDetails.getCancelMode());
								if(sessCompanyObj.getCompanyRole().isSuperUser())
									hotelOrderRow.setOrderUpdated(true);
								HotelOrderRow updatedHotelObj=orderModifyDao.updateHotelOrderRowBookingStatusInfo(hotelOrderRow);
								if(updatedHotelObj.getId()==creditNote.getHotelOrderRowId())
								{
									long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
									if(uniqId>0)
									{
										int id= getSaveHotelCreditNoteInfo(creditNote,orderModifyDao);
										if(id>0)
										{
											HotelCreditNote	hotelCreditNote =orderModifyDao.updateHotelCreditNoteInvoiceNumber(id);
											if(hotelCreditNote.getId()==id)
											{
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_HOTEL);
												addActionMessage(message); 
											}
											addActionMessage("Your order is successfully updated");
										}
										else 
											addActionMessage("Your order is successfully updated, but we could not send email");
										return SUCCESS;
									}
									else {
										addActionMessage("We could not update your order, Please contact support");
										return SUCCESS;
									}
								}
								else{
									addActionMessage("We could not update your order, Please contact support");
									return SUCCESS;
								} 
							}
						}
						else if(HotelOrderRowCancellation!=null && HotelOrderRowCancellation.getStatusCode() != null && 
								(HotelOrderRowCancellation.getStatusCode().equalsIgnoreCase("0") ||HotelOrderRowCancellation.getStatusCode().equalsIgnoreCase("1") 
										||HotelOrderRowCancellation.getStatusCode().equalsIgnoreCase("2")))
						{								
							message = HotelOrderRowCancellation.getStatusMessage();
							if(message!=null){
								addActionMessage(message); 
								return SUCCESS;  
							}

						}

						break;

					default://
						String ishotelcreditnotetestmode = getText("global.ishotelcreditnotetestmode");
						logger.info("######ishotelcreditnotetestmode########----------------"+ishotelcreditnotetestmode);
						if(hotelOrderRowDetails.getBookingMode().equalsIgnoreCase("Online"))
						{
							if((ishotelcreditnotetestmode != null && ishotelcreditnotetestmode.trim().equalsIgnoreCase("true")))
							{						
								HotelOrderRowCancellation hotelOrderRowCancellation = new HotelCreditNoteDao().getUpdateHotelOrderRowCancellation(hotelOrderRowDetails.getOrderReference());
								buildHotelOrderRowCancellation(hotelOrderRowDetails,hotelOrderRowCancellation);

								message="Cancellation Processed";
								if(message!=null){
									addActionMessage(message); 
									return SUCCESS;  
								}
							}
							else 
							{
								APIHotelCancelRequest hotelCancelRequest=new APIHotelCancelRequest();
								hotelCancelRequest.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId()));
								hotelCancelRequest.setPassword(sessionUser.getPassword());
								if(hotelOrderRowDetails.getConfigId()!=null && !hotelOrderRowDetails.getConfigId().equalsIgnoreCase("") )
								{
									CompanyConfig congigObj=new CompanyConfigDao().getCompanyConfigDetails(Integer.parseInt(hotelOrderRowDetails.getConfigId()));
									hotelCancelRequest.setAppKey(congigObj.getAppKey());
								}

								hotelCancelRequest.setOrderId(hotelOrderRowDetails.getOrderReference());						
								if(HotelOrderRowCancellation != null)
								{
									//just call api to get status...
									//if status is 3. then send credit note,, otherwise display alert n dont issue credit note
									hotelCancelRequest.setRemarks(creditNote.getEmployeeComments());
									hotelCancelRequest.setMethodType(APIHotelCancelRequest.METHOD_GET_STATUS);						
									String cancellatioUrl=getText("global.cancellationUrl");
									logger.info("######cancellatioUrl########----------------"+cancellatioUrl);
									statusMap= HttpClient.post(hotelCancelRequest,cancellatioUrl);
								}
								else
								{
									//just call api to initiate cancellation...
									//if status is 3. then send credit note,, otherwise display alert n dont issue credit note
									hotelCancelRequest.setRemarks(creditNote.getEmployeeComments());
									hotelCancelRequest.setMethodType(APIHotelCancelRequest.METHOD_INITIATE);						
									String cancellatioUrl=getText("global.cancellationUrl");
									logger.info("######cancellatioUrl########----------------"+cancellatioUrl);
									statusMap= HttpClient.post(hotelCancelRequest,cancellatioUrl);
								}
								//Remove this if or make false to consider api response
								if(statusMap.containsKey(APIStatus.STATUS_CODE_CANCEL_IN_PROCESS)){
									message=statusMap.get(APIStatus.STATUS_CODE_CANCEL_IN_PROCESS);
									if(message!=null){
										addActionMessage(message); 
										return SUCCESS;  
									}
								}
								else if(statusMap.containsKey(APIStatus.STATUS_CODE_CANCEL_PENDING)){
									message=statusMap.get(APIStatus.STATUS_CODE_CANCEL_PENDING);
									if(message!=null){
										addActionMessage(message); 
										return SUCCESS;  
									}
								}
								else if(statusMap.containsKey(APIStatus.STATUS_CODE_ERROR)){
									message=statusMap.get(APIStatus.STATUS_CODE_ERROR);
									if(message!=null){
										addActionMessage(message); 
										return SUCCESS;  
									}
								}
								break;
							}
						}
					}	
				}
				
				
				
				
				//check if the cancellation is happening first time /online
				/*if(creditNote.getCancelMode()!=null && creditNote.getCancelMode().equalsIgnoreCase("Online"))
				{
					HotelOrderRowCancellation HotelOrderRowCancellation=new HotelOrderDao().getHotelOrderRowCancellation(hotelOrderRowDetails.getOrderReference());
					switch (pageActionType) 
					{					
					case ACTION_SUBMIT_ORDER_CHANGE:

						logger.info("------- API Cancel response statusMap--------"+statusMap);
						if(HotelOrderRowCancellation!=null && HotelOrderRowCancellation.getStatusCode() != null && HotelOrderRowCancellation.getStatusCode().equalsIgnoreCase("3"))
						{
							message = HotelOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							if(message!=null)
							{
								hotelOrderRow.setId(creditNote.getHotelOrderRowId());
								hotelOrderRow.setStatusAction(creditNote.getStatusAction());
								hotelOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								hotelOrderRow.setOrderRequested(true);
								hotelOrderRow.setCancelMode(creditNote.getCancelMode());
								if(sessCompanyObj.getCompanyRole().isSuperUser())
									hotelOrderRow.setOrderUpdated(true);
								HotelOrderRow updatedHotelObj=orderModifyDao.updateHotelOrderRowBookingStatusInfo(hotelOrderRow);
								if(updatedHotelObj.getId()==creditNote.getHotelOrderRowId())
								{
									long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
									if(uniqId>0)
									{
										int id= getSaveHotelCreditNoteInfo(creditNote,orderModifyDao);
										if(id>0)
										{
											HotelCreditNote	hotelCreditNote =orderModifyDao.updateHotelCreditNoteInvoiceNumber(id);
											if(hotelCreditNote.getId()==id)
											{
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_HOTEL);
												addActionMessage(message); 
											}
											addActionMessage("Your order is successfully updated");
										}
										else 
											addActionMessage("Your order is successfully updated, but we could not send email");
										return SUCCESS;
									}
									else {
										addActionMessage("We could not update your order, Please contact support");
										return SUCCESS;
									}
								}
								else{
									addActionMessage("We could not update your order, Please contact support");
									return SUCCESS;
								} 
							}
						}
						else if(HotelOrderRowCancellation!=null && HotelOrderRowCancellation.getStatusCode() != null && 
								(HotelOrderRowCancellation.getStatusCode().equalsIgnoreCase("0") ||HotelOrderRowCancellation.getStatusCode().equalsIgnoreCase("1") 
										||HotelOrderRowCancellation.getStatusCode().equalsIgnoreCase("2")))
						{								
							message = HotelOrderRowCancellation.getStatusMessage();
							if(message!=null){
								addActionMessage(message); 
								return SUCCESS;  
							}

						}

						break;

					default://
						String ishotelcreditnotetestmode = getText("global.ishotelcreditnotetestmode");
						logger.info("######ishotelcreditnotetestmode########----------------"+ishotelcreditnotetestmode);
						if(hotelOrderRowDetails.getBookingMode().equalsIgnoreCase("Online"))
						{
							if((ishotelcreditnotetestmode != null && ishotelcreditnotetestmode.trim().equalsIgnoreCase("true")))
							{						
								HotelOrderRowCancellation hotelOrderRowCancellation = new HotelCreditNoteDao().getUpdateHotelOrderRowCancellation(hotelOrderRowDetails.getOrderReference());
								buildHotelOrderRowCancellation(hotelOrderRowDetails,hotelOrderRowCancellation);

								message="Cancellation Processed";
								if(message!=null){
									addActionMessage(message); 
									return SUCCESS;  
								}
							}
							else 
							{
								APIHotelCancelRequest hotelCancelRequest=new APIHotelCancelRequest();
								hotelCancelRequest.setUserId(String.valueOf(CommonUtilSession.checkEmulatedUser(sessionMap)?CommonUtilSession.getEmulatedUserIdInt(sessionMap):sessionUser.getId()));
								hotelCancelRequest.setPassword(sessionUser.getPassword());
								if(hotelOrderRowDetails.getConfigId()!=null && !hotelOrderRowDetails.getConfigId().equalsIgnoreCase("") )
								{
									CompanyConfig congigObj=new CompanyConfigDao().getCompanyConfigDetails(Integer.parseInt(hotelOrderRowDetails.getConfigId()));
									hotelCancelRequest.setAppKey(congigObj.getAppKey());
								}

								hotelCancelRequest.setOrderId(hotelOrderRowDetails.getOrderReference());						
								if(HotelOrderRowCancellation != null)
								{
									//just call api to get status...
									//if status is 3. then send credit note,, otherwise display alert n dont issue credit note
									hotelCancelRequest.setRemarks(creditNote.getEmployeeComments());
									hotelCancelRequest.setMethodType(APIHotelCancelRequest.METHOD_GET_STATUS);						
									String cancellatioUrl=getText("global.cancellationUrl");
									logger.info("######cancellatioUrl########----------------"+cancellatioUrl);
									statusMap= HttpClient.post(hotelCancelRequest,cancellatioUrl);
								}
								else
								{
									//just call api to initiate cancellation...
									//if status is 3. then send credit note,, otherwise display alert n dont issue credit note
									hotelCancelRequest.setRemarks(creditNote.getEmployeeComments());
									hotelCancelRequest.setMethodType(APIHotelCancelRequest.METHOD_INITIATE);						
									String cancellatioUrl=getText("global.cancellationUrl");
									logger.info("######cancellatioUrl########----------------"+cancellatioUrl);
									statusMap= HttpClient.post(hotelCancelRequest,cancellatioUrl);
								}
								//Remove this if or make false to consider api response
								if(statusMap.containsKey(APIStatus.STATUS_CODE_CANCEL_IN_PROCESS)){
									message=statusMap.get(APIStatus.STATUS_CODE_CANCEL_IN_PROCESS);
									if(message!=null){
										addActionMessage(message); 
										return SUCCESS;  
									}
								}
								else if(statusMap.containsKey(APIStatus.STATUS_CODE_CANCEL_PENDING)){
									message=statusMap.get(APIStatus.STATUS_CODE_CANCEL_PENDING);
									if(message!=null){
										addActionMessage(message); 
										return SUCCESS;  
									}
								}
								else if(statusMap.containsKey(APIStatus.STATUS_CODE_ERROR)){
									message=statusMap.get(APIStatus.STATUS_CODE_ERROR);
									if(message!=null){
										addActionMessage(message); 
										return SUCCESS;  
									}
								}
								break;
							}
						}
					}	
				}*/
				else if(sessCompanyObj!=null && (sessCompanyObj.getCompanyRole().isSuperUser() || sessCompanyObj.getCompanyRole().isCorporate() || sessCompanyObj.getCompanyRole().isDistributor()|| sessCompanyObj.getCompanyRole().isAgent()))
				{
					logger.info("--------getHotelOrderRowId-----------"+creditNote.getHotelOrderRowId()); 
					logger.info("------company id-------------"+creditNote.getCompanyId()); 
					HotelCreditNote creditnote=orderModifyDao.getHotelCreditNoteId(creditNote.getHotelOrderRowId(),creditNote.getCompanyId());
					if(creditnote!=null)
					{
						if(sessCompanyObj.getCompanyRole().isSuperUser())
						{
							if(creditNote.getCancellationFees()==null)
								creditnote.setCancellationFees(new BigDecimal(0));
							else
								creditnote.setCancellationFees(creditNote.getCancellationFees());
							
							if(creditNote.getConvenienceFees()==null)
								creditnote.setConvenienceFees(new BigDecimal(0));
							else
								creditnote.setConvenienceFees(creditNote.getConvenienceFees());
						}
						else{
							creditnote.setCancellationFees(new BigDecimal(0));
							creditnote.setConvenienceFees(new BigDecimal(0));
						}

						if(creditNote.getManagementFees()==null)
							creditnote.setManagementFees(new BigDecimal(0));
						else
							creditnote.setManagementFees(creditNote.getManagementFees());
						
						HotelCreditNote updateObj=orderModifyDao.updateHotelCreditNoteData(creditnote);
						if(creditnote.getId()==updateObj.getId())
						{
							addActionMessage(getText("global.creditnotegetid"));
							return SUCCESS;
						}
					}
					else
					{
						hotelOrderRow.setId(creditNote.getHotelOrderRowId());
						hotelOrderRow.setStatusAction(creditNote.getStatusAction());
						hotelOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
						
						if(sessCompanyObj.getCompanyRole().isSuperUser()){
							hotelOrderRow.setOrderUpdated(true);
							hotelOrderRow.setCancelMode("Offline");
						}
							
						hotelOrderRow.setOrderRequested(true);
						HotelOrderRow updatedHotelObj=orderModifyDao.updateHotelOrderRowBookingStatusInfo(hotelOrderRow);
						if(updatedHotelObj.getId()==creditNote.getHotelOrderRowId())
						{
							long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
							if(uniqId>0)
							{
								int id= getSaveHotelCreditNoteInfo(creditNote,orderModifyDao);
								if(id>0)
								{
									HotelCreditNote	hotelCreditNote =orderModifyDao.updateHotelCreditNoteInvoiceNumber(id);
									if(hotelCreditNote.getId()==id)
									{
										new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_HOTEL);
										addActionMessage(message); 
									}
									addActionMessage("Your order is successfully updated");
								}
								else 
									addActionMessage("Your order is successfully updated, but we could not send email");
								return SUCCESS;
							}
							else {
								addActionMessage("We could not update your order, Please contact support");
								return SUCCESS;
							}
						}
						else{
							addActionMessage("We could not update your order, Please contact support");
							return SUCCESS;
						}

					}
				}
				else{
					addActionMessage(getText("global.creditnotegetCompanyRole"));
					return SUCCESS;
				} 
			}
		} catch (Exception e) {
			addActionMessage(getText("global.creditnoteexceptionsuccess"));
			logger.error("(----------Exception-----------)"+e.getMessage());
			e.printStackTrace();
		} 
		return SUCCESS;

	}

	private void buildHotelOrderRowCancellation(HotelOrderRow hotelOrderRowDetails, HotelOrderRowCancellation hotelOrderRowCancellation) throws HibernateException, Exception
	{
		if(hotelOrderRowCancellation == null)
			hotelOrderRowCancellation = new HotelOrderRowCancellation();

		hotelOrderRowCancellation.setOrderId(hotelOrderRowDetails.getOrderReference());
		hotelOrderRowCancellation.setStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		hotelOrderRowCancellation.setStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		hotelOrderRowCancellation.setAPIStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		hotelOrderRowCancellation.setAPIStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		hotelOrderRowCancellation.setAPIConfirmationNumber(hotelOrderRowDetails.getOrderReference());
		hotelOrderRowCancellation.setConfirmationNumber(hotelOrderRowDetails.getOrderReference());
		hotelOrderRowCancellation.setChargeType("PERCENTAGE");
		hotelOrderRowCancellation.setPaymentType("WALLET");
		hotelOrderRowCancellation.setAPIChargeType("PERCENTAGE");
		hotelOrderRowCancellation.setAPIChargeAmount(new BigDecimal(0));							
		hotelOrderRowCancellation.setAPIRefundAmount(hotelOrderRowDetails.getApiPrice());							
		hotelOrderRowCancellation.setAPICurrency(hotelOrderRowDetails.getApiCurrency());				
		hotelOrderRowCancellation.setAPIPaymentType("WALLET");

		hotelOrderRowCancellation = new HotelCreditNoteDao().insertOrUpdateHotelOrderRowCancellation(hotelOrderRowCancellation);
	}

	private int getSaveHotelCreditNoteInfo(HotelCreditNote creditNote, OrderModifyDao orderModifyDao) {
		if(creditNote.getCancellationFees()==null)
		{
			creditNote.setCancellationFees(new BigDecimal("0.00"));
		}
		if(creditNote.getConvenienceFees()==null)
		{
			creditNote.setConvenienceFees(new BigDecimal("0.00"));
		} 
		if(creditNote.getManagementFees()==null)
		{
			creditNote.setManagementFees(new BigDecimal("0.00"));
		} 
		creditNote.setRowId(creditNote.getHotelOrderRowId().intValue());
		creditNote.setOrderUpdated(true);
		creditNote.setRefundedAmount(new BigDecimal(0.00));
		creditNote.setAfterStatus(creditNote.getStatusAction());
		creditNote.setAfterPayStatus(creditNote.getPaymentStatus());
		creditNote.setAlterBy(creditNote.getUpdatedBy());
		creditNote.setOrderedAt(new Timestamp(new Date().getTime()));

		if(creditNote.getGstAmount()==null)
		{
			creditNote.setGstAmount(new BigDecimal("0.00"));
		} 
		return orderModifyDao.insertHotelCreditNoteInfo(creditNote);

	}

	private long getSaveOrderModifiedInfo(HotelCreditNote creditNote, HotelOrderModifyInfo orderModifiedInfo, OrderModifyDao orderModifyDao) {
		orderModifiedInfo.setUserComments(creditNote.getEmployeeComments());
		orderModifiedInfo.setOrderedAt(new Timestamp(new Date().getTime()));
		orderModifiedInfo.setStatusAction(creditNote.getStatusAction());
		orderModifiedInfo.setPaymentStatus(creditNote.getPaymentStatus());
		orderModifiedInfo.setUserId(creditNote.getUserId()); 
		orderModifiedInfo.setActionType(creditNote.getActionType());
		orderModifiedInfo.setHotelOrderRowId(creditNote.getHotelOrderRowId().intValue());
		return orderModifyDao.insertHotelOrderModifiedInfo(orderModifiedInfo); 

	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public HotelCreditNote getModel() {
		// TODO Auto-generated method stub
		return creditNote;
	}


}
