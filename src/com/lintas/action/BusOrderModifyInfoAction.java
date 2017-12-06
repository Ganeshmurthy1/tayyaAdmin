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
import com.lintas.action.CreditNote.Dao.BusCreditNoteDao;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.modal.BusCreditNote;
import com.lintas.action.order.modify.model.BusOrderModifyInfo;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.bus.model.BusOrderRow;
import com.lintas.admin.common.model.BusOrderRowCancellation;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class BusOrderModifyInfoAction extends ActionSupport implements ModelDriven<BusCreditNote>,SessionAware{

	/**@author info yogesh
	 * created date:19-04-2017
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightOrderModifyInfoAction.class);
	private static final long serialVersionUID = 1L;
	BusCreditNote creditNote=new BusCreditNote();
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
		BusOrderRow busOrderRow=new BusOrderRow();

		logger.info("------- Hotel order change page call--------");

		BusOrderModifyInfo orderModifiedInfo=new BusOrderModifyInfo();
		BusOrderRow busOrderRowOld=new BusCreditNoteDao().getBusOrderRowDataForCreditNote(creditNote.getBusOrderRowId());
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
				if(busOrderRowOld.getCancelMode()!=null && busOrderRowOld.getCancelMode().equalsIgnoreCase("Online")){
					//FlightOrderRowCancellation flightOrderRowCancellation=new CreditNoteDao().getFlightOrderRowCancellation(flightOrderRowOld.getOrderId());
					BusOrderRowCancellation busOrderRowCancellation=new CreditNoteDao().getBusOrderRowCancellationWithAPIStatus(busOrderRowOld.getOrderId());
					if(busOrderRowCancellation!=null ){
						
						int pageActionType =  Integer.parseInt(busOrderRowCancellation.getAPIStatusCode());
						logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);
						if(pageActionType == 1){
							
							
							message = busOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							BusCreditNote creditnote=orderModifyDao.getBusCreditNoteId(creditNote.getBusOrderRowId(),creditNote.getCompanyId());
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
								BusCreditNote updateObj=orderModifyDao.updateBusCreditNoteData(creditnote);
								if(creditnote.getId()==updateObj.getId())
								{
									addActionMessage("Your order is successfully updated");
									return SUCCESS;
								}
							}
							if(message!=null )
							{
								busOrderRow.setId(creditNote.getBusOrderRowId());
								busOrderRow.setStatusAction(creditNote.getStatusAction());
								busOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								
								
								if(sessCompanyObj.getCompanyRole().isSuperUser()){
									busOrderRow.setOrderUpdated(true);
									busOrderRow.setCancelMode(busOrderRow.getCancelMode());
								}
									
								busOrderRow.setOrderRequested(true);
								BusOrderRow updateBusOrderRow=orderModifyDao.updateBusOrderRowBookingStatusInfo(busOrderRow);
								if(updateBusOrderRow.getId()==creditNote.getBusOrderRowId())
								{
									long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
									if(uniqId>0)
									{
										int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
										if(id>0)
										{
											CreditNote	creditNote =orderModifyDao.updateCreditNoteInvoiceNumber(id);
											
											
											if(creditNote.getId()==id)
											{
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_BUS);
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
								(busOrderRowCancellation.getAPIStatusCode().equalsIgnoreCase("0") ||busOrderRowCancellation.getAPIStatusCode().equalsIgnoreCase("2") 
										||busOrderRowCancellation.getAPIStatusCode().equalsIgnoreCase("3")))
						{								
							message = busOrderRowCancellation.getStatusMessage();
							if(message!=null){
								addActionMessage(message); 
								
								return SUCCESS;  
							}

						}
					}
					addActionMessage("We are not able to cancel ur booking , Please contact support");
					return SUCCESS;
						
				}
				else if(sessCompanyObj!=null && (sessCompanyObj.getCompanyRole().isSuperUser() || sessCompanyObj.getCompanyRole().isCorporate() || sessCompanyObj.getCompanyRole().isDistributor()|| sessCompanyObj.getCompanyRole().isAgent()))
				{
					logger.info("--------getHotelOrderRowId-----------"+creditNote.getBusOrderRowId()); 
					logger.info("------company id-------------"+creditNote.getCompanyId()); 
					BusCreditNote creditnote=orderModifyDao.getBusCreditNoteId(creditNote.getBusOrderRowId(),creditNote.getCompanyId());
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
						
						BusCreditNote updateObj=orderModifyDao.updateBusCreditNoteData(creditnote);
						if(creditnote.getId()==updateObj.getId())
						{
							addActionMessage(getText("global.creditnotegetid"));
							return SUCCESS;
						}
					}
					else
					{
						busOrderRow.setId(creditNote.getBusOrderRowId());
						busOrderRow.setStatusAction(creditNote.getStatusAction());
						busOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
						busOrderRow.setCancelMode(creditNote.getCancelMode());
						if(busOrderRow.getCancelMode() == null && sessCompanyObj.getCompanyRole().isSuperUser()){
							busOrderRow.setCancelMode("Offline");
						}
						if(sessCompanyObj.getCompanyRole().isSuperUser())
							busOrderRow.setOrderUpdated(true);
						busOrderRow.setOrderRequested(true);
						BusOrderRow busOrderRowUpdated=orderModifyDao.updateBusOrderRowBookingStatusInfo(busOrderRow);
						if(busOrderRowUpdated.getId()==creditNote.getBusOrderRowId())
						{
							long uniqId=getSaveOrderModifiedInfo(creditNote,orderModifiedInfo,orderModifyDao); 
							if(uniqId>0)
							{
								int id= getSaveCreditNoteInfo(creditNote,orderModifyDao);
								if(id>0)
								{
									BusCreditNote	creditNote =orderModifyDao.updateBusCreditNoteInvoiceNumber(id);
									if(creditNote.getId()==id)
									{
										new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_BUS);
										addActionMessage(getText("global.insertHotelOrderModifiedInfosuccess"));
									}
									else 
										addActionMessage(getText("global.insertHotelOrderModifiedInfocreditNote.getGstAmountsuccesssuccess"));
									return SUCCESS;
								}
								else {
									addActionMessage(getText("global.insertHotelOrderModifiedInfocreditNote.getGstAmountsuccesssuccess"));
									return SUCCESS;
								}
							}
							else{
								addActionMessage(getText("global.insertHotelOrderModifiedInfocreditNote.getConvenienceFeessuccess"));
								return SUCCESS;
							} 
						}
						else{
							addActionMessage(getText("global.insertHotelOrderModifiedInfocreditNote.getCancellationFeessuccess"));
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

	private void buildOrderRowCancellation(BusOrderRow busOrderRow, BusOrderRowCancellation busOrderRowCancellation) throws HibernateException, Exception
	{
		if(busOrderRowCancellation == null)
			busOrderRowCancellation = new BusOrderRowCancellation();

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

		busOrderRowCancellation = new BusCreditNoteDao().insertOrUpdateBusOrderRowCancellation(busOrderRowCancellation);
	}

	private int getSaveCreditNoteInfo(BusCreditNote creditNote2, OrderModifyDao orderModifyDao) {
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
		creditNote2.setRowId(creditNote2.getBusOrderRowId().intValue());
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
		return orderModifyDao.insertBusCreditNoteInfo(creditNote2);

	}

	private long getSaveOrderModifiedInfo(BusCreditNote creditNote2, BusOrderModifyInfo orderModifiedInfo, OrderModifyDao orderModifyDao) {
		orderModifiedInfo.setUserComments(creditNote2.getEmployeeComments());
		orderModifiedInfo.setOrderedAt(new Timestamp(new Date().getTime()));
		orderModifiedInfo.setStatusAction(creditNote2.getStatusAction());
		orderModifiedInfo.setPaymentStatus(creditNote2.getPaymentStatus());
		orderModifiedInfo.setUserId(creditNote2.getUserId()); 
		orderModifiedInfo.setActionType(creditNote2.getActionType());
		orderModifiedInfo.setBusOrderRowId(creditNote2.getBusOrderRowId().intValue());
		return orderModifyDao.insertBusOrderModifiedInfo(orderModifiedInfo); 

	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public BusCreditNote getModel() {
		// TODO Auto-generated method stub
		return creditNote;
	}

	public BusCreditNote getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(BusCreditNote creditNote) {
		this.creditNote = creditNote;
	}
}
