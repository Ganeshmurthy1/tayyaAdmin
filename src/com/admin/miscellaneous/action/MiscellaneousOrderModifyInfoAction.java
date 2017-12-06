/**
 * 
 */
package com.admin.miscellaneous.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

import com.admin.common.util.CommonUtilSession;
import com.admin.miscellaneous.dao.MiscellaneousCreditNoteDao;
import com.admin.miscellaneous.model.MiscellaneousCreditNote;
import com.admin.miscellaneous.model.MiscellaneousOrderModifyInfo;
import com.admin.miscellaneous.model.MiscellaneousOrderRow;
import com.admin.miscellaneous.model.MiscellaneousOrderRowCancellation;
import com.hotel.cancellation.APIHotelCancelRequest;
import com.hotel.cancellation.APIStatus;
import com.hotel.cancellation.HttpClient;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manish Samrat
 *
 */
public class MiscellaneousOrderModifyInfoAction extends ActionSupport implements ModelDriven<MiscellaneousCreditNote>,SessionAware{

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(MiscellaneousOrderModifyInfoAction.class);
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	OrderModifyDao orderModifyDao=new OrderModifyDao();
	MiscellaneousCreditNote creditNote=new MiscellaneousCreditNote();
	//Constants actions
		public final static int ACTION_DEFAULT = 0;
		public final static int ACTION_CANCEL = 1;
		public final static int ACTION_GET_STATUS = 2;
		public final static int ACTION_SUBMIT_ORDER_CHANGE = 3;
			
	public String insertOrderModifiedInfo(){
		Company sessCompanyObj=(Company)sessionMap.get("Company");
		User sessionUser=(User)sessionMap.get("User");
		MiscellaneousOrderRow miscellaneousOrderRow=new MiscellaneousOrderRow();

		logger.info("------- Miscellaneous order change page call--------");

		MiscellaneousOrderModifyInfo orderModifiedInfo=new MiscellaneousOrderModifyInfo();
		MiscellaneousOrderRow miscellaneousOrderRowOld=new MiscellaneousCreditNoteDao().getMiscellaneousOrderRowDataForCreditNote(creditNote.getMiscellaneousOrderRowId());
		Map<String, String> statusMap=null;
		String message=null;
		if(creditNote.getEmployeeComments()==null || creditNote.getEmployeeComments().equals("")){
			creditNote.setEmployeeComments("no remarks");
		}

		try 
		{
			if(miscellaneousOrderRowOld!=null)// &&(sessCompanyObj.getCompanyid()==Integer.parseInt(hotelOrderRowDetails.getCompanyId())))
			{ 
				System.out.println("miscellaneousOrderRowOld.getCompanyId()---"+miscellaneousOrderRowOld.getCompanyId());
				int pageActionType = creditNote.getCancelType();
				logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);

				//check if the cancellation is happening first time /online
				if(creditNote.getCancelMode()!=null && creditNote.getCancelMode().equalsIgnoreCase("Online"))
				{
					MiscellaneousOrderRowCancellation miscellaneousOrderRowCancellation=new MiscellaneousCreditNoteDao().getMiscellaneousOrderRowCancellation(miscellaneousOrderRowOld.getOrderId());
					switch (pageActionType) 
					{					
					case ACTION_SUBMIT_ORDER_CHANGE:

						logger.info("------- API Cancel response statusMap--------"+statusMap);
						if(miscellaneousOrderRowCancellation!=null && miscellaneousOrderRowCancellation.getStatusCode() != null && miscellaneousOrderRowCancellation.getStatusCode().equalsIgnoreCase("3"))
						{
							message = miscellaneousOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							if(message!=null)
							{
								miscellaneousOrderRow.setId(creditNote.getMiscellaneousOrderRowId());
								miscellaneousOrderRow.setStatusAction(creditNote.getStatusAction());
								miscellaneousOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								miscellaneousOrderRow.setOrderRequested(true);
//								insuranceOrderRow.setCancelMode(creditNote.getCancelMode());
								if(sessCompanyObj.getCompanyRole().isSuperUser())
									miscellaneousOrderRow.setOrderUpdated(true);
								MiscellaneousOrderRow updateMiscellaneousOrderRow=orderModifyDao.updateMiscellaneousOrderRowBookingStatusInfo(miscellaneousOrderRow);
								if(updateMiscellaneousOrderRow.getId()==creditNote.getMiscellaneousOrderRowId())
								{
									long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
									if(uniqId>0)
									{
										int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
										if(id>0)
										{
											MiscellaneousCreditNote	creditNote =orderModifyDao.updateMiscellaneousCreditNoteInvoiceNumber(id);
											if(creditNote.getId()==id)
											{
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_MISCELLANEOUS);
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
						else if(miscellaneousOrderRowCancellation!=null && miscellaneousOrderRowCancellation.getStatusCode() != null && 
								(miscellaneousOrderRowCancellation.getStatusCode().equalsIgnoreCase("0") ||miscellaneousOrderRowCancellation.getStatusCode().equalsIgnoreCase("1") 
										||miscellaneousOrderRowCancellation.getStatusCode().equalsIgnoreCase("2")))
						{								
							message = miscellaneousOrderRowCancellation.getStatusMessage();
							if(message!=null){
								addActionMessage(message); 
								return SUCCESS;  
							}

						}

						break;

					default://
						String ismiscellaneouscreditnotetestmode = getText("global.ishotelcreditnotetestmode");
						logger.info("######ishotelcreditnotetestmode########----------------"+ismiscellaneouscreditnotetestmode);
						if(miscellaneousOrderRowOld.getBookingMode().equalsIgnoreCase("Online"))
						{
							if((ismiscellaneouscreditnotetestmode != null && ismiscellaneouscreditnotetestmode.trim().equalsIgnoreCase("true")))
							{						
								MiscellaneousOrderRowCancellation miscellaneousOrderRowCancellation2 = new MiscellaneousCreditNoteDao().getUpdateMiscellaneousOrderRowCancellation(miscellaneousOrderRowOld.getOrderId());
								buildOrderRowCancellation(miscellaneousOrderRowOld,miscellaneousOrderRowCancellation2);

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
								/*if(miscellaneousOrderRowOld.getConfigId()!=null && !miscellaneousOrderRowOld.getConfigId().equalsIgnoreCase("") )
								{
									CompanyConfig congigObj=new CompanyConfigDao().getCompanyConfigDetails(Integer.parseInt(miscellaneousOrderRowOld.getConfigId()));
									hotelCancelRequest.setAppKey(congigObj.getAppKey());
								}*/

								hotelCancelRequest.setOrderId(miscellaneousOrderRowOld.getOrderId());						
								if(miscellaneousOrderRowCancellation != null)
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
					System.out.println("--------getHotelOrderRowId-----------"+creditNote.getMiscellaneousOrderRowId()); 
					System.out.println("------company id-------------"+creditNote.getCompanyId()); 
					MiscellaneousCreditNote creditnote=orderModifyDao.getMiscellaneousCreditNoteId(creditNote.getMiscellaneousOrderRowId(),creditNote.getCompanyId());
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
						MiscellaneousCreditNote updateObj=orderModifyDao.updateMiscellaneousCreditNoteData(creditnote);
						if(creditnote.getId()==updateObj.getId())
						{
							addActionMessage(getText("global.creditnotegetid"));
							return SUCCESS;
						}
					}
					else
					{
						miscellaneousOrderRow.setId(creditNote.getMiscellaneousOrderRowId());
						miscellaneousOrderRow.setStatusAction(creditNote.getStatusAction());
						miscellaneousOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
//						miscellaneousOrderRow.setCancelMode(creditNote.getCancelMode());
						if(sessCompanyObj.getCompanyRole().isSuperUser())
							miscellaneousOrderRow.setOrderUpdated(true);
						miscellaneousOrderRow.setOrderRequested(true);
						MiscellaneousOrderRow miscellaneousOrderRowUpdated=orderModifyDao.updateMiscellaneousOrderRowBookingStatusInfo(miscellaneousOrderRow);
						if(miscellaneousOrderRowUpdated.getId()==creditNote.getMiscellaneousOrderRowId())
						{
							long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
							if(uniqId>0)
							{
								int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
								if(id>0)
								{
									MiscellaneousCreditNote	creditNote =orderModifyDao.updateMiscellaneousCreditNoteInvoiceNumber(id);
									if(creditNote.getId()==id)
									{
										new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_MISCELLANEOUS);
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
			
	private long getSaveOrderModifiedInfo(MiscellaneousCreditNote creditNote2, MiscellaneousOrderModifyInfo orderModifiedInfo, OrderModifyDao orderModifyDao) {
		orderModifiedInfo.setUserComments(creditNote2.getEmployeeComments());
		orderModifiedInfo.setOrderedAt(new Timestamp(new Date().getTime()));
		orderModifiedInfo.setStatusAction(creditNote2.getStatusAction());
		orderModifiedInfo.setPaymentStatus(creditNote2.getPaymentStatus());
		orderModifiedInfo.setUserId(creditNote2.getUserId()); 
		orderModifiedInfo.setActionType(creditNote2.getActionType());
		orderModifiedInfo.setInsuranceOrderRowId(creditNote2.getMiscellaneousOrderRowId().intValue());
		return orderModifyDao.insertMiscellaneousOrderModifiedInfo(orderModifiedInfo); 

	}
	
	private int getSaveCreditNoteInfo(MiscellaneousCreditNote creditNote2, OrderModifyDao orderModifyDao) {
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
		creditNote2.setRowId(creditNote2.getMiscellaneousOrderRowId().intValue());
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
		return orderModifyDao.insertMiscellaneousCreditNoteInfo(creditNote2);

	}
	private void buildOrderRowCancellation(MiscellaneousOrderRow miscellaneousOrderRow, MiscellaneousOrderRowCancellation miscellaneousOrderRowCancellation) throws HibernateException, Exception
	{
		if(miscellaneousOrderRowCancellation == null)
			miscellaneousOrderRowCancellation = new MiscellaneousOrderRowCancellation();

		miscellaneousOrderRowCancellation.setOrderId(miscellaneousOrderRow.getOrderId());
		miscellaneousOrderRowCancellation.setStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		miscellaneousOrderRowCancellation.setStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		miscellaneousOrderRowCancellation.setAPIStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		miscellaneousOrderRowCancellation.setAPIStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		miscellaneousOrderRowCancellation.setAPIConfirmationNumber(miscellaneousOrderRow.getOrderId());
		miscellaneousOrderRowCancellation.setConfirmationNumber(miscellaneousOrderRow.getOrderId());
		miscellaneousOrderRowCancellation.setChargeType("PERCENTAGE");
		miscellaneousOrderRowCancellation.setPaymentType("WALLET");
		miscellaneousOrderRowCancellation.setAPIChargeType("PERCENTAGE");
		miscellaneousOrderRowCancellation.setAPIChargeAmount(new BigDecimal(0));							
//		insuranceOrderRowCancellation.setAPIRefundAmount(insuranceOrderRow.getSupplierPrice());							
		miscellaneousOrderRowCancellation.setAPICurrency(miscellaneousOrderRow.getBookingCurrency());				
		miscellaneousOrderRowCancellation.setAPIPaymentType("WALLET");

		miscellaneousOrderRowCancellation = new MiscellaneousCreditNoteDao().insertOrUpdateMiscellaneousOrderRowCancellation(miscellaneousOrderRowCancellation);
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}
	@Override
	public MiscellaneousCreditNote getModel() {
		return creditNote;
	}

	public MiscellaneousCreditNote getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(MiscellaneousCreditNote creditNote) {
		this.creditNote = creditNote;
	}
}
