package com.lintas.action;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;

import com.hotel.cancellation.APIStatus;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.common.model.CreditNote;
import com.lintas.admin.common.model.OrderModifiedInfo;
import com.lintas.admin.flight.model.FlightOrderRow;
import com.lintas.admin.flight.model.FlightOrderRowCancellation;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
public class FlightOrderModifyInfoAction extends ActionSupport implements ModelDriven<CreditNote>,SessionAware{

	/**@author info raham
	 * created date:07-10-2015
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightOrderModifyInfoAction.class);
	private static final long serialVersionUID = 1L;
	CreditNote creditNote=new CreditNote();
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
		FlightOrderRow flightOrderRow=new FlightOrderRow();

		logger.info("------- Hotel order change page call--------");

		OrderModifiedInfo orderModifiedInfo=new OrderModifiedInfo();
		FlightOrderRow flightOrderRowOld=new CreditNoteDao().getFlightOrderRowDataForCreditNote(creditNote.getFlightOrderRowId());
		Map<String, String> statusMap=null;
		String message=null;
		if(creditNote.getEmployeeComments()==null || creditNote.getEmployeeComments().equals("")){
			creditNote.setEmployeeComments("no remarks");
		}

		try 
		{
			if(flightOrderRowOld!=null)// &&(sessCompanyObj.getCompanyid()==Integer.parseInt(hotelOrderRowDetails.getCompanyId())))
			{
				logger.info("hotelOrderRowDetails.getCompanyId()---"+flightOrderRowOld.getCompanyId());
				
				if(flightOrderRowOld.getCancelationMode()!=null && flightOrderRowOld.getCancelationMode().equalsIgnoreCase("Online")){
					//FlightOrderRowCancellation flightOrderRowCancellation=new CreditNoteDao().getFlightOrderRowCancellation(flightOrderRowOld.getOrderId());
					FlightOrderRowCancellation flightOrderRowCancellation=new CreditNoteDao().getFlightOrderRowCancellationWithAPIStatus(flightOrderRowOld.getOrderId());
					if(flightOrderRowCancellation!=null ){
						
						int pageActionType =  Integer.parseInt(flightOrderRowCancellation.getAPIStatusCode());
						logger.info("------- Hotel order change page call--pageActionType------"+pageActionType);
						if(pageActionType == 1){
							
							
							message = flightOrderRowCancellation.getStatusMessage();
							logger.info("API message------------"+message);
							CreditNote creditnote=orderModifyDao.getCreditNoteId(creditNote.getFlightOrderRowId(),creditNote.getCompanyId());
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
								CreditNote updateObj=orderModifyDao.updateCreditNoteData(creditnote);
								if(creditnote.getId()==updateObj.getId())
								{
									addActionMessage("Your order is successfully updated");
									return SUCCESS;
								}
							}
							if(message!=null )
							{
								flightOrderRow.setId(creditNote.getFlightOrderRowId());
								flightOrderRow.setStatusAction(creditNote.getStatusAction());
								flightOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
								
								
								if(sessCompanyObj.getCompanyRole().isSuperUser()){
									flightOrderRow.setOrderUpdated(true);
									flightOrderRow.setCancelationMode(flightOrderRowOld.getCancelationMode());
								}
									
								flightOrderRow.setOrderRequested(true);
								FlightOrderRow updateFlightOrderRow=orderModifyDao.updateFlightOrderRowBookingStatusInfo(flightOrderRow);
								if(updateFlightOrderRow.getId()==creditNote.getFlightOrderRowId())
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
												new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_FLIGHT);
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
						else if(flightOrderRowCancellation!=null && flightOrderRowCancellation.getStatusCode() != null && 
								(flightOrderRowCancellation.getAPIStatusCode().equalsIgnoreCase("0") ||flightOrderRowCancellation.getAPIStatusCode().equalsIgnoreCase("2") 
										||flightOrderRowCancellation.getAPIStatusCode().equalsIgnoreCase("3")))
						{								
							message = flightOrderRowCancellation.getStatusMessage();
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
					CreditNote creditnote=orderModifyDao.getCreditNoteId(creditNote.getFlightOrderRowId(),creditNote.getCompanyId());
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
						CreditNote updateObj=orderModifyDao.updateCreditNoteData(creditnote);
						if(creditnote.getId()==updateObj.getId())
						{
							addActionMessage("Your order is successfully updated");
							return SUCCESS;
						}
					}
					else
					{
						flightOrderRow.setId(creditNote.getFlightOrderRowId());
						flightOrderRow.setStatusAction(creditNote.getStatusAction());
						flightOrderRow.setPaymentStatus(creditNote.getPaymentStatus());
						flightOrderRow.setCancelationMode(creditNote.getCancelationMode());
						
						if(flightOrderRow.getCancelationMode() == null && sessCompanyObj.getCompanyRole().isSuperUser()){
							flightOrderRow.setCancelationMode("Offline");
						}
						if(sessCompanyObj.getCompanyRole().isSuperUser())
							flightOrderRow.setOrderUpdated(true);
						flightOrderRow.setOrderRequested(true);
						FlightOrderRow flightOrderRowUpdated=orderModifyDao.updateFlightOrderRowBookingStatusInfo(flightOrderRow);
						if(flightOrderRowUpdated.getId()==creditNote.getFlightOrderRowId())
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
										new CompanyDAO().insertEmail(String.valueOf(id), 0, Email.EMAIL_TYPE_CREDITNOTE_REQUEST_FLIGHT);
										addActionMessage("Your order is successfully updated");
									}
									else 
										addActionMessage("");
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
						else{
							addActionMessage("We could not update your order, Please contact support");
							return SUCCESS;
						} 
					}
				}
				else{
					addActionMessage("We could not update your order, Company not authorized");
					return SUCCESS;
				} 
			}
			
		} catch (Exception e) {
			addActionMessage("We could not update your order, Please contact support");
			logger.error("(----------Exception-----------)"+e.getMessage());
			e.printStackTrace();
		} 
		return SUCCESS;

	}

	private void buildOrderRowCancellation(FlightOrderRow flightOrderRow, FlightOrderRowCancellation flightOrderRowCancellation) throws HibernateException, Exception
	{
		if(flightOrderRowCancellation == null)
			flightOrderRowCancellation = new FlightOrderRowCancellation();

		flightOrderRowCancellation.setOrderId(flightOrderRow.getOrderId());
		flightOrderRowCancellation.setStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		flightOrderRowCancellation.setStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		flightOrderRowCancellation.setAPIStatusCode(APIStatus.STATUS_CODE_CANCEL_PROCESSED);
		flightOrderRowCancellation.setAPIStatusMessage(APIStatus.STATUS_MESSAGE_CANCEL_PROCESSED);
		flightOrderRowCancellation.setAPIConfirmationNumber(flightOrderRow.getOrderId());
		flightOrderRowCancellation.setConfirmationNumber(flightOrderRow.getOrderId());
		flightOrderRowCancellation.setChargeType("PERCENTAGE");
		flightOrderRowCancellation.setPaymentType("WALLET");
		flightOrderRowCancellation.setAPIChargeType("PERCENTAGE");
		flightOrderRowCancellation.setAPIChargeAmount(new BigDecimal(0));							
		flightOrderRowCancellation.setAPIRefundAmount(flightOrderRow.getPrice());							
		flightOrderRowCancellation.setAPICurrency(flightOrderRow.getApiCurrency());				
		flightOrderRowCancellation.setAPIPaymentType("WALLET");

		flightOrderRowCancellation = new CreditNoteDao().insertOrUpdateFlightOrderRowCancellation(flightOrderRowCancellation);
	}

	private int getSaveCreditNoteInfo(CreditNote creditNote2, OrderModifyDao orderModifyDao) {
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
		creditNote2.setRowId(creditNote2.getFlightOrderRowId().intValue());
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
		return orderModifyDao.insertFlightCreditNoteInfo(creditNote2);

	}

	private long getSaveOrderModifiedInfo(CreditNote creditNote2, OrderModifiedInfo orderModifiedInfo, OrderModifyDao orderModifyDao) {
		orderModifiedInfo.setUserComments(creditNote2.getEmployeeComments());
		orderModifiedInfo.setOrderedAt(new Timestamp(new Date().getTime()));
		orderModifiedInfo.setStatusAction(creditNote2.getStatusAction());
		orderModifiedInfo.setPaymentStatus(creditNote2.getPaymentStatus());

		orderModifiedInfo.setUserId(creditNote2.getUserId()); 
		orderModifiedInfo.setActionType(creditNote2.getActionType());
		orderModifiedInfo.setFlightOrderRowId(creditNote2.getFlightOrderRowId().intValue());
		return orderModifyDao.insertFlightOrderModifiedInfo(orderModifiedInfo); 

	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public CreditNote getModel() {
		// TODO Auto-generated method stub
		return creditNote;
	}
}
