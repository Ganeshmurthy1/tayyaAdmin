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
import com.lintas.action.CreditNote.Dao.VisaCreditNoteDao;
import com.lintas.action.CreditNote.modal.VisaCreditNote;
import com.lintas.action.order.modify.model.VisaOrderModifyInfo;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.common.model.VisaOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.admin.visa.model.VisaOrderRow;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class VisaOrderModifyInfoAction extends ActionSupport implements ModelDriven<VisaCreditNote>,SessionAware{

	/**@author info yogesh
	 * created date:19-04-2017
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(VisaOrderModifyInfoAction.class);
	private static final long serialVersionUID = 1L;
	VisaCreditNote creditNote=new VisaCreditNote();
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
		VisaOrderRow visaOrderRow=new VisaOrderRow();

		logger.info("------- Hotel order change page call--------");

		VisaOrderModifyInfo orderModifiedInfo=new VisaOrderModifyInfo();
		VisaOrderRow visaOrderRowOld=new VisaCreditNoteDao().getVisaOrderRowDataForCreditNote(creditNote.getVisaOrderRowId());
		Map<String, String> statusMap=null;
		String message=null;
		if(creditNote.getEmployeeComments()==null || creditNote.getEmployeeComments().equals("")){
			creditNote.setEmployeeComments("no remarks");
		}

		try 
		{
			if(visaOrderRowOld!=null)// &&(sessCompanyObj.getCompanyid()==Integer.parseInt(hotelOrderRowDetails.getCompanyId())))
			{
				logger.info("hotelOrderRowDetails.getCompanyId()---"+visaOrderRowOld.getCompanyId());
				int pageActionType = creditNote.getCancelType();
				logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);

				//check if the cancellation is happening first time /online
				if(creditNote.getCancelMode()!=null && creditNote.getCancelMode().equalsIgnoreCase("Online"))
				{
					VisaOrderRowCancellation visaOrderRowCancellation=new VisaCreditNoteDao().getVisaOrderRowCancellation(visaOrderRowOld.getOrderId());
					switch (pageActionType) 
					{					
					case ACTION_SUBMIT_ORDER_CHANGE:

						logger.info("------- API Cancel response statusMap--------"+statusMap);
						if(visaOrderRowCancellation!=null && visaOrderRowCancellation.getStatusCode() != null && visaOrderRowCancellation.getStatusCode().equalsIgnoreCase("3"))
						{
							message = visaOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							if(message!=null)
							{
								visaOrderRow.setId(creditNote.getVisaOrderRowId());
								visaOrderRow.setStatusAction(creditNote.getStatusAction());
								visaOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								visaOrderRow.setOrderRequested(true);
								visaOrderRow.setCancelMode(creditNote.getCancelMode());
								if(sessCompanyObj.getCompanyRole().isSuperUser())
									visaOrderRow.setOrderUpdated(true);
								VisaOrderRow updateVisaOrderRow=orderModifyDao.updateVisaOrderRowBookingStatusInfo(visaOrderRow);
								if(updateVisaOrderRow.getId()==creditNote.getVisaOrderRowId())
								{
									long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
									if(uniqId>0)
									{
										int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
										if(id>0)
										{
											VisaCreditNote	creditNote =orderModifyDao.updateVisaCreditNoteInvoiceNumber(id);
											if(creditNote.getId()==id)
											{
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_VISA);
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
						else if(visaOrderRowCancellation!=null && visaOrderRowCancellation.getStatusCode() != null && 
								(visaOrderRowCancellation.getStatusCode().equalsIgnoreCase("0") ||visaOrderRowCancellation.getStatusCode().equalsIgnoreCase("1") 
										||visaOrderRowCancellation.getStatusCode().equalsIgnoreCase("2")))
						{								
							message = visaOrderRowCancellation.getStatusMessage();
							if(message!=null){
								addActionMessage(message); 
								return SUCCESS;  
							}

						}

						break;

					default://
						String ishotelcreditnotetestmode = getText("global.ishotelcreditnotetestmode");
						logger.info("######ishotelcreditnotetestmode########----------------"+ishotelcreditnotetestmode);
						if(visaOrderRowOld.getBookingMode().equalsIgnoreCase("Online"))
						{
							if((ishotelcreditnotetestmode != null && ishotelcreditnotetestmode.trim().equalsIgnoreCase("true")))
							{						
								VisaOrderRowCancellation visaOrderRowCancellation2 = new VisaCreditNoteDao().getUpdateVisaOrderRowCancellation(visaOrderRowOld.getOrderId());
								buildOrderRowCancellation(visaOrderRowOld,visaOrderRowCancellation2);

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
								if(visaOrderRowOld.getConfigId()!=null && !visaOrderRowOld.getConfigId().equalsIgnoreCase("") )
								{
									CompanyConfig congigObj=new CompanyConfigDao().getCompanyConfigDetails(Integer.parseInt(visaOrderRowOld.getConfigId()));
									hotelCancelRequest.setAppKey(congigObj.getAppKey());
								}

								hotelCancelRequest.setOrderId(visaOrderRowOld.getOrderId());						
								if(visaOrderRowCancellation != null)
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
					logger.info("--------getHotelOrderRowId-----------"+creditNote.getVisaOrderRowId()); 
					logger.info("------company id-------------"+creditNote.getCompanyId()); 
					VisaCreditNote creditnote=orderModifyDao.getVisaCreditNoteId(creditNote.getVisaOrderRowId(),creditNote.getCompanyId());
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
						VisaCreditNote updateObj=orderModifyDao.updateVisaCreditNoteData(creditnote);
						if(creditnote.getId()==updateObj.getId())
						{
							addActionMessage(getText("global.creditnotegetid"));
							return SUCCESS;
						}
					}
					else
					{
						visaOrderRow.setId(creditNote.getVisaOrderRowId());
						visaOrderRow.setStatusAction(creditNote.getStatusAction());
						visaOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
						visaOrderRow.setCancelMode(creditNote.getCancelMode());
						if(sessCompanyObj.getCompanyRole().isSuperUser())
							visaOrderRow.setOrderUpdated(true);
						visaOrderRow.setOrderRequested(true);
						VisaOrderRow visaOrderRowUpdated=orderModifyDao.updateVisaOrderRowBookingStatusInfo(visaOrderRow);
						if(visaOrderRowUpdated.getId()==creditNote.getVisaOrderRowId())
						{
							long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
							if(uniqId>0)
							{
								int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
								if(id>0)
								{
									VisaCreditNote	creditNote =orderModifyDao.updateVisaCreditNoteInvoiceNumber(id);
									if(creditNote.getId()==id)
									{
										new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_VISA);
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

	private void buildOrderRowCancellation(VisaOrderRow visaOrderRow, VisaOrderRowCancellation visaOrderRowCancellation) throws HibernateException, Exception
	{
		if(visaOrderRowCancellation == null)
			visaOrderRowCancellation = new VisaOrderRowCancellation();

		visaOrderRowCancellation.setOrderId(visaOrderRow.getOrderId());
		visaOrderRowCancellation.setStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		visaOrderRowCancellation.setStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		visaOrderRowCancellation.setAPIStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		visaOrderRowCancellation.setAPIStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		visaOrderRowCancellation.setAPIConfirmationNumber(visaOrderRow.getOrderId());
		visaOrderRowCancellation.setConfirmationNumber(visaOrderRow.getOrderId());
		visaOrderRowCancellation.setChargeType("PERCENTAGE");
		visaOrderRowCancellation.setPaymentType("WALLET");
		visaOrderRowCancellation.setAPIChargeType("PERCENTAGE");
		visaOrderRowCancellation.setAPIChargeAmount(new BigDecimal(0));							
		visaOrderRowCancellation.setAPIRefundAmount(visaOrderRow.getSupplierPrice());							
		visaOrderRowCancellation.setAPICurrency(visaOrderRow.getBookingCurrency());				
		visaOrderRowCancellation.setAPIPaymentType("WALLET");

		visaOrderRowCancellation = new VisaCreditNoteDao().insertOrUpdateVisaOrderRowCancellation(visaOrderRowCancellation);
	}

	private int getSaveCreditNoteInfo(VisaCreditNote creditNote2, OrderModifyDao orderModifyDao) {
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
		creditNote2.setRowId(creditNote2.getVisaOrderRowId().intValue());
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
		return orderModifyDao.insertVisaCreditNoteInfo(creditNote2);

	}

	private long getSaveOrderModifiedInfo(VisaCreditNote creditNote2, VisaOrderModifyInfo orderModifiedInfo, OrderModifyDao orderModifyDao) {
		orderModifiedInfo.setUserComments(creditNote2.getEmployeeComments());
		orderModifiedInfo.setOrderedAt(new Timestamp(new Date().getTime()));
		orderModifiedInfo.setStatusAction(creditNote2.getStatusAction());
		orderModifiedInfo.setPaymentStatus(creditNote2.getPaymentStatus());
		orderModifiedInfo.setUserId(creditNote2.getUserId()); 
		orderModifiedInfo.setActionType(creditNote2.getActionType());
		orderModifiedInfo.setVisaOrderRowId(creditNote2.getVisaOrderRowId().intValue());
		return orderModifyDao.insertVisaOrderModifiedInfo(orderModifiedInfo); 

	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public VisaCreditNote getModel() {
		// TODO Auto-generated method stub
		return creditNote;
	}

	public VisaCreditNote getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(VisaCreditNote creditNote) {
		this.creditNote = creditNote;
	}
}
