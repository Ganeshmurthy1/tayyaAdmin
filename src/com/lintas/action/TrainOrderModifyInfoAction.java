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
import com.lintas.action.CreditNote.Dao.TrainCreditNoteDao;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.action.order.modify.model.TrainOrderModifyInfo;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.common.model.TrainOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.admin.train.model.TrainOrderRow;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class TrainOrderModifyInfoAction extends ActionSupport implements ModelDriven<TrainCreditNote>,SessionAware{

	/**@author info basha
	 * created date:19-04-2017
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainOrderModifyInfoAction.class);
	private static final long serialVersionUID = 1L;
	TrainCreditNote creditNote=new TrainCreditNote();
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
		TrainOrderRow trainOrderRow=new TrainOrderRow();

		logger.info("------- Hotel order change page call--------");

		TrainOrderModifyInfo orderModifiedInfo=new TrainOrderModifyInfo();
		TrainOrderRow trainOrderRowOld=new TrainCreditNoteDao().getTrainOrderRowDataForCreditNote(creditNote.getTrainOrderRowId());
		Map<String, String> statusMap=null;
		String message=null;
		if(creditNote.getEmployeeComments()==null || creditNote.getEmployeeComments().equals("")){
			creditNote.setEmployeeComments("no remarks");
		}

		try 
		{
			if(trainOrderRowOld!=null)// &&(sessCompanyObj.getCompanyid()==Integer.parseInt(hotelOrderRowDetails.getCompanyId())))
			{
				//System.out.println("hotelOrderRowDetails.getCompanyId()---"+trainOrderRowOld.getCompanyId());
				int pageActionType = creditNote.getCancelType();
				logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);

				//check if the cancellation is happening first time /online
				if(creditNote.getCancelMode()!=null && creditNote.getCancelMode().equalsIgnoreCase("Online"))
				{
					TrainOrderRowCancellation trainOrderRowCancellation=new TrainCreditNoteDao().getTrainOrderRowCancellation(trainOrderRowOld.getOrderId());
					switch (pageActionType) 
					{					
					case ACTION_SUBMIT_ORDER_CHANGE:

						logger.info("------- API Cancel response statusMap--------"+statusMap);
						if(trainOrderRowCancellation!=null && trainOrderRowCancellation.getStatusCode() != null && trainOrderRowCancellation.getStatusCode().equalsIgnoreCase("3"))
						{
							message = trainOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							if(message!=null)
							{
								trainOrderRow.setId(creditNote.getTrainOrderRowId());
								trainOrderRow.setStatusAction(creditNote.getStatusAction());
								trainOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								trainOrderRow.setOrderRequested(true);
								trainOrderRow.setCancelMode(creditNote.getCancelMode());
								if(sessCompanyObj.getCompanyRole().isSuperUser())
									trainOrderRow.setOrderUpdated(true);
								TrainOrderRow updateTrainOrderRow=orderModifyDao.updateTrainOrderRowBookingStatusInfo(trainOrderRow);
								if(updateTrainOrderRow.getId()==creditNote.getTrainOrderRowId())
								{
									long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
									if(uniqId>0)
									{
										int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
										if(id>0)
										{
											TrainCreditNote	creditNote =orderModifyDao.updateTrainCreditNoteInvoiceNumber(id);
											if(creditNote.getId()==id)
											{
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_TRAIN);
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
						else if(trainOrderRowCancellation!=null && trainOrderRowCancellation.getStatusCode() != null && 
								(trainOrderRowCancellation.getStatusCode().equalsIgnoreCase("0") ||trainOrderRowCancellation.getStatusCode().equalsIgnoreCase("1") 
										||trainOrderRowCancellation.getStatusCode().equalsIgnoreCase("2")))
						{								
							message = trainOrderRowCancellation.getStatusMessage();
							if(message!=null){
								addActionMessage(message); 
								return SUCCESS;  
							}

						}

						break;

					default://
						String ishotelcreditnotetestmode = getText("global.ishotelcreditnotetestmode");
						logger.info("######ishotelcreditnotetestmode########----------------"+ishotelcreditnotetestmode);
						if(trainOrderRowOld.getBookingMode().equalsIgnoreCase("Online"))
						{
							if((ishotelcreditnotetestmode != null && ishotelcreditnotetestmode.trim().equalsIgnoreCase("true")))
							{						
								TrainOrderRowCancellation trainOrderRowCancellation2 = new TrainCreditNoteDao().getUpdateTrainOrderRowCancellation(trainOrderRowOld.getOrderId());
								buildOrderRowCancellation(trainOrderRowOld,trainOrderRowCancellation2);

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
								if(trainOrderRowOld.getConfigId()!=null && !trainOrderRowOld.getConfigId().equalsIgnoreCase("") )
								{
									CompanyConfig congigObj=new CompanyConfigDao().getCompanyConfigDetails(Integer.parseInt(trainOrderRowOld.getConfigId()));
									hotelCancelRequest.setAppKey(congigObj.getAppKey());
								}

								hotelCancelRequest.setOrderId(trainOrderRowOld.getOrderId());						
								if(trainOrderRowCancellation != null)
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
					//System.out.println("--------getHotelOrderRowId-----------"+creditNote.getTrainOrderRowId()); 
					//System.out.println("------company id-------------"+creditNote.getCompanyId()); 
					TrainCreditNote creditnote=orderModifyDao.getTrainCreditNoteId(creditNote.getTrainOrderRowId(),creditNote.getCompanyId());
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
						TrainCreditNote updateObj=orderModifyDao.updateTrainCreditNoteData(creditnote);
						if(creditnote.getId()==updateObj.getId())
						{
							addActionMessage(getText("global.creditnotegetid"));
							return SUCCESS;
						}
					}
					else
					{
						trainOrderRow.setId(creditNote.getTrainOrderRowId());
						trainOrderRow.setStatusAction(creditNote.getStatusAction());
						trainOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
						trainOrderRow.setCancelMode(creditNote.getCancelMode());
						if(sessCompanyObj.getCompanyRole().isSuperUser())
							trainOrderRow.setOrderUpdated(true);
						trainOrderRow.setOrderRequested(true);
						TrainOrderRow trainOrderRowUpdated=orderModifyDao.updateTrainOrderRowBookingStatusInfo(trainOrderRow);
						if(trainOrderRowUpdated.getId()==creditNote.getTrainOrderRowId())
						{
							long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
							if(uniqId>0)
							{
								int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
								if(id>0)
								{
									TrainCreditNote	creditNote =orderModifyDao.updateTrainCreditNoteInvoiceNumber(id);
									if(creditNote.getId()==id)
									{
										new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_TRAIN);
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

	private void buildOrderRowCancellation(TrainOrderRow trainOrderRow, TrainOrderRowCancellation trainOrderRowCancellation) throws HibernateException, Exception
	{
		if(trainOrderRowCancellation == null)
			trainOrderRowCancellation = new TrainOrderRowCancellation();

		trainOrderRowCancellation.setOrderId(trainOrderRow.getOrderId());
		trainOrderRowCancellation.setStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		trainOrderRowCancellation.setStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		trainOrderRowCancellation.setAPIStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		trainOrderRowCancellation.setAPIStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		trainOrderRowCancellation.setAPIConfirmationNumber(trainOrderRow.getOrderId());
		trainOrderRowCancellation.setConfirmationNumber(trainOrderRow.getOrderId());
		trainOrderRowCancellation.setChargeType("PERCENTAGE");
		trainOrderRowCancellation.setPaymentType("WALLET");
		trainOrderRowCancellation.setAPIChargeType("PERCENTAGE");
		trainOrderRowCancellation.setAPIChargeAmount(new BigDecimal(0));							
		trainOrderRowCancellation.setAPIRefundAmount(trainOrderRow.getSupplierPrice());							
		trainOrderRowCancellation.setAPICurrency(trainOrderRow.getBookingCurrency());				
		trainOrderRowCancellation.setAPIPaymentType("WALLET");

		trainOrderRowCancellation = new TrainCreditNoteDao().insertOrUpdateTrainOrderRowCancellation(trainOrderRowCancellation);
	}

	private int getSaveCreditNoteInfo(TrainCreditNote creditNote2, OrderModifyDao orderModifyDao) {
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
		creditNote2.setRowId(creditNote2.getTrainOrderRowId().intValue());
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
		return orderModifyDao.insertTrainCreditNoteInfo(creditNote2);

	}

	private long getSaveOrderModifiedInfo(TrainCreditNote creditNote2, TrainOrderModifyInfo orderModifiedInfo, OrderModifyDao orderModifyDao) {
		orderModifiedInfo.setUserComments(creditNote2.getEmployeeComments());
		orderModifiedInfo.setOrderedAt(new Timestamp(new Date().getTime()));
		orderModifiedInfo.setStatusAction(creditNote2.getStatusAction());
		orderModifiedInfo.setPaymentStatus(creditNote2.getPaymentStatus());
		orderModifiedInfo.setUserId(creditNote2.getUserId()); 
		orderModifiedInfo.setActionType(creditNote2.getActionType());
		orderModifiedInfo.setTrainOrderRowId(creditNote2.getTrainOrderRowId().intValue());
		return orderModifyDao.insertTrainOrderModifiedInfo(orderModifiedInfo); 

	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public TrainCreditNote getModel() {
		// TODO Auto-generated method stub
		return creditNote;
	}

	public TrainCreditNote getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(TrainCreditNote creditNote) {
		this.creditNote = creditNote;
	}
}
