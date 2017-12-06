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
import com.lintas.action.CreditNote.Dao.CarCreditNoteDao;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.action.order.modify.model.CarOrderModifyInfo;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.CarOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class CarOrderModifyInfoAction extends ActionSupport implements ModelDriven<CarCreditNote>,SessionAware{

	/**@author info yogesh
	 * created date:19-04-2017
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightOrderModifyInfoAction.class);
	private static final long serialVersionUID = 1L;
	CarCreditNote creditNote=new CarCreditNote();
	SessionMap<String, Object> sessionMap;
	OrderModifyDao orderModifyDao=new OrderModifyDao();
	//Constants actions
	public final static int ACTION_DEFAULT = 0;
	public final static int ACTION_CANCEL = 1;
	public final static int ACTION_GET_STATUS = 2;
	public final static int ACTION_SUBMIT_ORDER_CHANGE = 3;

	/*inserting order modified information*/
	public String insertOrderModifiedInfo(){
		Company sessCompanyObj=(Company)sessionMap.get("Company");
		User sessionUser=(User)sessionMap.get("User");
		CarOrderRow busOrderRow=new CarOrderRow();

		logger.info("------- Hotel order change page call--------");

		CarOrderModifyInfo orderModifiedInfo=new CarOrderModifyInfo();
		CarOrderRow busOrderRowOld=new CarCreditNoteDao().getCarOrderRowDataForCreditNote(creditNote.getCarOrderRowId());
		Map<String, String> statusMap=null;
		String message=null;
		if(creditNote.getEmployeeComments()==null || creditNote.getEmployeeComments().equals("")){
			creditNote.setEmployeeComments("no remarks");
		}

		try 
		{
			if(busOrderRowOld!=null)// &&(sessCompanyObj.getCompanyid()==Integer.parseInt(hotelOrderRowDetails.getCompanyId())))
			{
				logger.info("hotelOrderRowDetails.getCompanyId()---"+busOrderRowOld.getCompanyId());
				int pageActionType = creditNote.getCancelType();
				logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);

				//check if the cancellation is happening first time /online
				if(creditNote.getCancelMode()!=null && creditNote.getCancelMode().equalsIgnoreCase("Online"))
				{
					CarOrderRowCancellation busOrderRowCancellation=new CarCreditNoteDao().getCarOrderRowCancellation(busOrderRowOld.getOrderId());
					switch (pageActionType) 
					{					
					case ACTION_SUBMIT_ORDER_CHANGE:

						logger.info("------- API Cancel response statusMap--------"+statusMap);
						if(busOrderRowCancellation!=null && busOrderRowCancellation.getStatusCode() != null && busOrderRowCancellation.getStatusCode().equalsIgnoreCase("3"))
						{
							message = busOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							if(message!=null)
							{
								busOrderRow.setId(creditNote.getCarOrderRowId());
								busOrderRow.setStatusAction(creditNote.getStatusAction());
								busOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								busOrderRow.setOrderRequested(true);
								busOrderRow.setCancelMode(creditNote.getCancelMode());
								if(sessCompanyObj.getCompanyRole().isSuperUser())
									busOrderRow.setOrderUpdated(true);
								CarOrderRow updateCarOrderRow=orderModifyDao.updateCarOrderRowBookingStatusInfo(busOrderRow);
								if(updateCarOrderRow.getId()==creditNote.getCarOrderRowId())
								{
									long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
									if(uniqId>0)
									{
										int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
										if(id>0)
										{
											CarCreditNote	creditNote =orderModifyDao.updateCarCreditNoteInvoiceNumber(id);
											if(creditNote.getId()==id)
											{
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_CAR);
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
						else if(busOrderRowCancellation!=null && busOrderRowCancellation.getStatusCode() != null && 
								(busOrderRowCancellation.getStatusCode().equalsIgnoreCase("0") ||busOrderRowCancellation.getStatusCode().equalsIgnoreCase("1") 
										||busOrderRowCancellation.getStatusCode().equalsIgnoreCase("2")))
						{								
							message = busOrderRowCancellation.getStatusMessage();
							if(message!=null){
								addActionMessage(message); 
								return SUCCESS;  
							}

						}

						break;

					default://
						String ishotelcreditnotetestmode = getText("global.ishotelcreditnotetestmode");
						logger.info("######ishotelcreditnotetestmode########----------------"+ishotelcreditnotetestmode);
						if(busOrderRowOld.getBookingMode().equalsIgnoreCase("Online"))
						{
							if((ishotelcreditnotetestmode != null && ishotelcreditnotetestmode.trim().equalsIgnoreCase("true")))
							{						
								CarOrderRowCancellation busOrderRowCancellation2 = new CarCreditNoteDao().getUpdateCarOrderRowCancellation(busOrderRowOld.getOrderId());
								buildOrderRowCancellation(busOrderRowOld,busOrderRowCancellation2);

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
								if(busOrderRowOld.getConfigId()!=null && !busOrderRowOld.getConfigId().equalsIgnoreCase("") )
								{
									CompanyConfig congigObj=new CompanyConfigDao().getCompanyConfigDetails(Integer.parseInt(busOrderRowOld.getConfigId()));
									hotelCancelRequest.setAppKey(congigObj.getAppKey());
								}

								hotelCancelRequest.setOrderId(busOrderRowOld.getOrderId());						
								if(busOrderRowCancellation != null)
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
				else if(sessCompanyObj!=null && (sessCompanyObj.getCompanyRole().isSuperUser() || sessCompanyObj.getCompanyRole().isCorporate() || sessCompanyObj.getCompanyRole().isDistributor()|| sessCompanyObj.getCompanyRole().isAgent()))
				{
					logger.info("--------getHotelOrderRowId-----------"+creditNote.getCarOrderRowId()); 
					logger.info("------company id-------------"+creditNote.getCompanyId()); 
					CarCreditNote creditnote=orderModifyDao.getCarCreditNoteId(creditNote.getCarOrderRowId(),creditNote.getCompanyId());
					if(creditnote!=null)
					{
						if(sessCompanyObj.getCompanyRole().isSuperUser())
						{
							if(creditNote.getCancellationFees()==null)
								creditnote.setCancellationFees(new BigDecimal(0));
							else
								creditnote.setCancellationFees(creditNote.getCancellationFees());
						}
						else{
							creditnote.setCancellationFees(new BigDecimal(0));
						}

						if(creditNote.getConvenienceFees()==null)
							creditnote.setConvenienceFees(new BigDecimal(0));
						else
							creditnote.setConvenienceFees(creditNote.getConvenienceFees());
						if(creditNote.getManagementFees()==null)
							creditnote.setManagementFees(new BigDecimal(0));
						else
							creditnote.setManagementFees(creditNote.getManagementFees());
						
						CarCreditNote updateObj=orderModifyDao.updateCarCreditNoteData(creditnote);
						if(creditnote.getId()==updateObj.getId())
						{
							addActionMessage(getText("global.creditnotegetid"));
							return SUCCESS;
						}
					}
					else
					{
						busOrderRow.setId(creditNote.getCarOrderRowId());
						busOrderRow.setStatusAction(creditNote.getStatusAction());
						busOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
						busOrderRow.setCancelMode(creditNote.getCancelMode());
						if(sessCompanyObj.getCompanyRole().isSuperUser())
							busOrderRow.setOrderUpdated(true);
						busOrderRow.setOrderRequested(true);
						CarOrderRow busOrderRowUpdated=orderModifyDao.updateCarOrderRowBookingStatusInfo(busOrderRow);
						if(busOrderRowUpdated.getId()==creditNote.getCarOrderRowId())
						{
							long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
							if(uniqId>0)
							{
								int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
								if(id>0)
								{
									CarCreditNote	creditNote =orderModifyDao.updateCarCreditNoteInvoiceNumber(id);
									if(creditNote.getId()==id)
									{
										new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_CAR);
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

	private void buildOrderRowCancellation(CarOrderRow busOrderRow, CarOrderRowCancellation busOrderRowCancellation) throws HibernateException, Exception
	{
		if(busOrderRowCancellation == null)
			busOrderRowCancellation = new CarOrderRowCancellation();

		busOrderRowCancellation.setOrderId(busOrderRow.getOrderId());
		busOrderRowCancellation.setStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		busOrderRowCancellation.setStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		busOrderRowCancellation.setAPIStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		busOrderRowCancellation.setAPIStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		busOrderRowCancellation.setAPIConfirmationNumber(busOrderRow.getOrderId());
		busOrderRowCancellation.setConfirmationNumber(busOrderRow.getOrderId());
		busOrderRowCancellation.setChargeType("PERCENTAGE");
		busOrderRowCancellation.setPaymentType("WALLET");
		busOrderRowCancellation.setAPIChargeType("PERCENTAGE");
		busOrderRowCancellation.setAPIChargeAmount(new BigDecimal(0));							
		busOrderRowCancellation.setAPIRefundAmount(busOrderRow.getSupplierPrice());							
		busOrderRowCancellation.setAPICurrency(busOrderRow.getBookingCurrency());				
		busOrderRowCancellation.setAPIPaymentType("WALLET");

		busOrderRowCancellation = new CarCreditNoteDao().insertOrUpdateCarOrderRowCancellation(busOrderRowCancellation);
	}

	private int getSaveCreditNoteInfo(CarCreditNote creditNote2, OrderModifyDao orderModifyDao) {
		if(creditNote2.getCancellationFees()==null)
		{
			creditNote2.setCancellationFees(new BigDecimal("0.00"));
		}
		if(creditNote2.getConvenienceFees()==null)
		{
			creditNote2.setConvenienceFees(new BigDecimal("0.00"));
		} 
		if(creditNote2.getManagementFees()==null)
		{
			creditNote2.setManagementFees(new BigDecimal("0.00"));
		} 
		creditNote2.setRowId(creditNote2.getCarOrderRowId().intValue());
		creditNote2.setOrderUpdated(true);
		creditNote2.setRefundedAmount(new BigDecimal(0.00));
		creditNote2.setAfterStatus(creditNote2.getStatusAction());
		creditNote2.setAfterPayStatus(creditNote2.getPaymentStatus());
		creditNote2.setAlterBy(creditNote2.getUpdatedBy());
		creditNote2.setOrderedAt(new Timestamp(new Date().getTime()));

		if(creditNote2.getGstAmount()==null)
		{
			creditNote2.setGstAmount(new BigDecimal("0.00"));
		} 
		return orderModifyDao.insertCarCreditNoteInfo(creditNote2);

	}

	private long getSaveOrderModifiedInfo(CarCreditNote creditNote2, CarOrderModifyInfo orderModifiedInfo, OrderModifyDao orderModifyDao) {
		orderModifiedInfo.setUserComments(creditNote2.getEmployeeComments());
		orderModifiedInfo.setOrderedAt(new Timestamp(new Date().getTime()));
		orderModifiedInfo.setStatusAction(creditNote2.getStatusAction());
		orderModifiedInfo.setPaymentStatus(creditNote2.getPaymentStatus());
		orderModifiedInfo.setUserId(creditNote2.getUserId()); 
		orderModifiedInfo.setActionType(creditNote2.getActionType());
		orderModifiedInfo.setCarOrderRowId(creditNote2.getCarOrderRowId().intValue());
		return orderModifyDao.insertCarOrderModifiedInfo(orderModifiedInfo); 

	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public CarCreditNote getModel() {
		// TODO Auto-generated method stub
		return creditNote;
	}

	public CarCreditNote getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(CarCreditNote creditNote) {
		this.creditNote = creditNote;
	}
}
