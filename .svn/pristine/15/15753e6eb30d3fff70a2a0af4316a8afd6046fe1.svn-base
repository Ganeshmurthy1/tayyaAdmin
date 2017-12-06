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
import com.lintas.action.CreditNote.Dao.InsuranceCreditNoteDao;
import com.lintas.action.CreditNote.modal.InsuranceCreditNote;
import com.lintas.action.order.modify.model.InsuranceOrderModifyInfo;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.common.model.InsuranceOrderRowCancellation;
import com.lintas.admin.insurance.model.InsuranceOrderRow;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class InsuranceOrderModifyInfoAction extends ActionSupport implements ModelDriven<InsuranceCreditNote>,SessionAware{

	/**@author info yogesh
	 * created date:19-04-2017
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InsuranceOrderModifyInfoAction.class);
	private static final long serialVersionUID = 1L;
	InsuranceCreditNote creditNote=new InsuranceCreditNote();
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
		InsuranceOrderRow insuranceOrderRow=new InsuranceOrderRow();

		logger.info("------- Hotel order change page call--------");

		InsuranceOrderModifyInfo orderModifiedInfo=new InsuranceOrderModifyInfo();
		InsuranceOrderRow insuranceOrderRowOld=new InsuranceCreditNoteDao().getInsuranceOrderRowDataForCreditNote(creditNote.getInsuranceOrderRowId());
		Map<String, String> statusMap=null;
		String message=null;
		if(creditNote.getEmployeeComments()==null || creditNote.getEmployeeComments().equals("")){
			creditNote.setEmployeeComments("no remarks");
		}

		try 
		{
			if(insuranceOrderRowOld!=null)// &&(sessCompanyObj.getCompanyid()==Integer.parseInt(hotelOrderRowDetails.getCompanyId())))
			{
				logger.info("hotelOrderRowDetails.getCompanyId()---"+insuranceOrderRowOld.getCompanyId());
				int pageActionType = creditNote.getCancelType();
				logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);

				//check if the cancellation is happening first time /online
				if(creditNote.getCancelMode()!=null && creditNote.getCancelMode().equalsIgnoreCase("Online"))
				{
					InsuranceOrderRowCancellation insuranceOrderRowCancellation=new InsuranceCreditNoteDao().getInsuranceOrderRowCancellation(insuranceOrderRowOld.getOrderId());
					switch (pageActionType) 
					{					
					case ACTION_SUBMIT_ORDER_CHANGE:

						logger.info("------- API Cancel response statusMap--------"+statusMap);
						if(insuranceOrderRowCancellation!=null && insuranceOrderRowCancellation.getStatusCode() != null && insuranceOrderRowCancellation.getStatusCode().equalsIgnoreCase("3"))
						{
							message = insuranceOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							if(message!=null)
							{
								insuranceOrderRow.setId(creditNote.getInsuranceOrderRowId());
								insuranceOrderRow.setStatusAction(creditNote.getStatusAction());
								insuranceOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								insuranceOrderRow.setOrderRequested(true);
								insuranceOrderRow.setCancelMode(creditNote.getCancelMode());
								if(sessCompanyObj.getCompanyRole().isSuperUser())
									insuranceOrderRow.setOrderUpdated(true);
								InsuranceOrderRow updateInsuranceOrderRow=orderModifyDao.updateInsuranceOrderRowBookingStatusInfo(insuranceOrderRow);
								if(updateInsuranceOrderRow.getId()==creditNote.getInsuranceOrderRowId())
								{
									long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
									if(uniqId>0)
									{
										int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
										if(id>0)
										{
											InsuranceCreditNote	creditNote =orderModifyDao.updateInsuranceCreditNoteInvoiceNumber(id);
											if(creditNote.getId()==id)
											{
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_INSURANCE);
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
						else if(insuranceOrderRowCancellation!=null && insuranceOrderRowCancellation.getStatusCode() != null && 
								(insuranceOrderRowCancellation.getStatusCode().equalsIgnoreCase("0") ||insuranceOrderRowCancellation.getStatusCode().equalsIgnoreCase("1") 
										||insuranceOrderRowCancellation.getStatusCode().equalsIgnoreCase("2")))
						{								
							message = insuranceOrderRowCancellation.getStatusMessage();
							if(message!=null){
								addActionMessage(message); 
								return SUCCESS;  
							}

						}

						break;

					default://
						String ishotelcreditnotetestmode = getText("global.ishotelcreditnotetestmode");
						logger.info("######ishotelcreditnotetestmode########----------------"+ishotelcreditnotetestmode);
						if(insuranceOrderRowOld.getBookingMode().equalsIgnoreCase("Online"))
						{
							if((ishotelcreditnotetestmode != null && ishotelcreditnotetestmode.trim().equalsIgnoreCase("true")))
							{						
								InsuranceOrderRowCancellation insuranceOrderRowCancellation2 = new InsuranceCreditNoteDao().getUpdateInsuranceOrderRowCancellation(insuranceOrderRowOld.getOrderId());
								buildOrderRowCancellation(insuranceOrderRowOld,insuranceOrderRowCancellation2);

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
								if(insuranceOrderRowOld.getConfigId()!=null && !insuranceOrderRowOld.getConfigId().equalsIgnoreCase("") )
								{
									CompanyConfig congigObj=new CompanyConfigDao().getCompanyConfigDetails(Integer.parseInt(insuranceOrderRowOld.getConfigId()));
									hotelCancelRequest.setAppKey(congigObj.getAppKey());
								}

								hotelCancelRequest.setOrderId(insuranceOrderRowOld.getOrderId());						
								if(insuranceOrderRowCancellation != null)
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
					logger.info("--------getHotelOrderRowId-----------"+creditNote.getInsuranceOrderRowId()); 
					logger.info("------company id-------------"+creditNote.getCompanyId()); 
					InsuranceCreditNote creditnote=orderModifyDao.getInsuranceCreditNoteId(creditNote.getInsuranceOrderRowId(),creditNote.getCompanyId());
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
						InsuranceCreditNote updateObj=orderModifyDao.updateInsuranceCreditNoteData(creditnote);
						if(creditnote.getId()==updateObj.getId())
						{
							addActionMessage(getText("global.creditnotegetid"));
							return SUCCESS;
						}
					}
					else
					{
						insuranceOrderRow.setId(creditNote.getInsuranceOrderRowId());
						insuranceOrderRow.setStatusAction(creditNote.getStatusAction());
						insuranceOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
						insuranceOrderRow.setCancelMode(creditNote.getCancelMode());
						if(sessCompanyObj.getCompanyRole().isSuperUser())
							insuranceOrderRow.setOrderUpdated(true);
						insuranceOrderRow.setOrderRequested(true);
						InsuranceOrderRow insuranceOrderRowUpdated=orderModifyDao.updateInsuranceOrderRowBookingStatusInfo(insuranceOrderRow);
						if(insuranceOrderRowUpdated.getId()==creditNote.getInsuranceOrderRowId())
						{
							long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
							if(uniqId>0)
							{
								int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
								if(id>0)
								{
									InsuranceCreditNote	creditNote =orderModifyDao.updateInsuranceCreditNoteInvoiceNumber(id);
									if(creditNote.getId()==id)
									{
										new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_INSURANCE);
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

	private void buildOrderRowCancellation(InsuranceOrderRow insuranceOrderRow, InsuranceOrderRowCancellation insuranceOrderRowCancellation) throws HibernateException, Exception
	{
		if(insuranceOrderRowCancellation == null)
			insuranceOrderRowCancellation = new InsuranceOrderRowCancellation();

		insuranceOrderRowCancellation.setOrderId(insuranceOrderRow.getOrderId());
		insuranceOrderRowCancellation.setStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		insuranceOrderRowCancellation.setStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		insuranceOrderRowCancellation.setAPIStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		insuranceOrderRowCancellation.setAPIStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		insuranceOrderRowCancellation.setAPIConfirmationNumber(insuranceOrderRow.getOrderId());
		insuranceOrderRowCancellation.setConfirmationNumber(insuranceOrderRow.getOrderId());
		insuranceOrderRowCancellation.setChargeType("PERCENTAGE");
		insuranceOrderRowCancellation.setPaymentType("WALLET");
		insuranceOrderRowCancellation.setAPIChargeType("PERCENTAGE");
		insuranceOrderRowCancellation.setAPIChargeAmount(new BigDecimal(0));							
		insuranceOrderRowCancellation.setAPIRefundAmount(insuranceOrderRow.getSupplierPrice());							
		insuranceOrderRowCancellation.setAPICurrency(insuranceOrderRow.getBookingCurrency());				
		insuranceOrderRowCancellation.setAPIPaymentType("WALLET");

		insuranceOrderRowCancellation = new InsuranceCreditNoteDao().insertOrUpdateInsuranceOrderRowCancellation(insuranceOrderRowCancellation);
	}

	private int getSaveCreditNoteInfo(InsuranceCreditNote creditNote2, OrderModifyDao orderModifyDao) {
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
		creditNote2.setRowId(creditNote2.getInsuranceOrderRowId().intValue());
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
		return orderModifyDao.insertInsuranceCreditNoteInfo(creditNote2);

	}

	private long getSaveOrderModifiedInfo(InsuranceCreditNote creditNote2, InsuranceOrderModifyInfo orderModifiedInfo, OrderModifyDao orderModifyDao) {
		orderModifiedInfo.setUserComments(creditNote2.getEmployeeComments());
		orderModifiedInfo.setOrderedAt(new Timestamp(new Date().getTime()));
		orderModifiedInfo.setStatusAction(creditNote2.getStatusAction());
		orderModifiedInfo.setPaymentStatus(creditNote2.getPaymentStatus());
		orderModifiedInfo.setUserId(creditNote2.getUserId()); 
		orderModifiedInfo.setActionType(creditNote2.getActionType());
		orderModifiedInfo.setInsuranceOrderRowId(creditNote2.getInsuranceOrderRowId().intValue());
		return orderModifyDao.insertInsuranceOrderModifiedInfo(orderModifiedInfo); 

	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public InsuranceCreditNote getModel() {
		// TODO Auto-generated method stub
		return creditNote;
	}

	public InsuranceCreditNote getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(InsuranceCreditNote creditNote) {
		this.creditNote = creditNote;
	}
}
