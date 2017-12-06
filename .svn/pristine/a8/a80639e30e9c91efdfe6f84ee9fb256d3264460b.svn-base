package com.lintas.action.CreditNote;

 

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.action.FlightAgentCommissionInvoiceAction;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.admin.common.model.CreditNoteData;
import com.lintas.admin.flight.model.FlightCommissionReport;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FlightCustomerCreditNoteAction extends ActionSupport implements ModelDriven<FlightCommissionReport>, SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	CreditNoteDao creditNoteDao=new CreditNoteDao();
	private CreditNoteData creditNoteData= new CreditNoteData();
	private FlightCommissionReport flightCommissionReport=new FlightCommissionReport();
	private String fromDate;
	private String toDate;
	private String period;
	private String companyType;
	private String userId;
	private List<FlightCommissionReport> agentCreditNoteList;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(FlightAgentCommissionInvoiceAction.class);
	public String getFlightCustomerCreditNoteList(){
		Company companySessionObj=(Company)sessionMap.get("Company");
		List<FlightCommissionReport> agentCreditNoteList=creditNoteDao.getAgentCreditNoteList(companySessionObj);
		//logger.info("agentCreditNoteList----"+agentCreditNoteList.size());
		if(agentCreditNoteList!=null && agentCreditNoteList.size()>0){
			setAgentCreditNoteList(agentCreditNoteList);
		}
		//logger.info("agentCreditNoteList----"+getAgentCreditNoteList().size());
		return SUCCESS;
	}

	public String  generateFlightCustomerCreditNote(){
/*
		//logger.info("----------flightCommissionReport.getId()-------------"+flightCommissionReport.getId());
		FlightOrderRow  newFlightOrderRowObj=creditNoteDao.getFlightOrderRowDataForCreditNote(flightCommissionReport.getId());
		//logger.info("-----newFlightOrderRowObj.getOrderId()---------"+newFlightOrderRowObj.getOrderId());
		List<WalletAmountTranferHistory> walletHistoryList=creditNoteDao.getWalletHistoryDetailsByFlightOrderId(newFlightOrderRowObj.getOrderId());
		List<FlightOrderCustomer> flightOrderCustomerList= creditNoteDao.getFlightOrderCustomerDetails(newFlightOrderRowObj.getId());
		List<FlightOrderTripDetail> flightOrderTripDetailList= creditNoteDao.getFlightTripParticulars(newFlightOrderRowObj.getId());
		List<User> agentAddressList= creditNoteDao.getAgentAddress(newFlightOrderRowObj.getUserId());
		
		if(!newFlightOrderRowObj.isCreditNoteIssued()){ 
			if(walletHistoryList!=null){
				Collections.sort(walletHistoryList, new CompanyIDComparator());
				logger.info("walletHistoryList-------"+walletHistoryList.size());
				for(WalletAmountTranferHistory walletAmountTranferHistory:walletHistoryList){
					if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Flight Commission Shared") ){
						UserWallet userWallet=creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);

						BigDecimal userOpeningBalance=userWallet.getWalletbalance();
						BigDecimal addAmount=userWallet.getWalletbalance().add(walletAmountTranferHistory.getAmount());
						//logger.info(walletAmountTranferHistory.getWalletId()+"---added amount--"+addAmount);
						userWallet.setWalletbalance(addAmount);
						UserWallet newUserWalletObj=creditNoteDao.updateUserWallet(userWallet);
						logger.info(newUserWalletObj.getWalletId()+":-"+newUserWalletObj.getWalletbalance()+"Flight Commission taken back");
						creditNoteDao.updateWalletHistory("Flight Commission taken back",userOpeningBalance,addAmount,walletAmountTranferHistory);
					} 
					if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Flight Commission Added")){
						//logger.info("walletId---------------"+walletAmountTranferHistory.getWalletId());
						//logger.info("-------Action--------"+walletAmountTranferHistory.getAction());
						UserWallet userWallet =creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						//logger.info("userWallet getWalletbalance---------"+userWallet.getWalletbalance());
						BigDecimal userOpeningBalance=userWallet.getWalletbalance();
						BigDecimal addAmount=userWallet.getWalletbalance().subtract(walletAmountTranferHistory.getAmount());
						//logger.info(walletAmountTranferHistory.getWalletId()+"---minus amount--"+addAmount);
						userWallet.setWalletbalance(addAmount);
						UserWallet newUserWalletObj=creditNoteDao.updateUserWallet(userWallet);
						logger.info(newUserWalletObj.getWalletId()+":-"+newUserWalletObj.getWalletbalance()+"Flight Commission taken back");
						creditNoteDao.updateWalletHistory("Flight Commission taken back",userOpeningBalance,addAmount,walletAmountTranferHistory);

					}  
					 if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Flight Markup")){
						  creditNoteDao.getMarkupsFforAllCompaniesByFlightRowID(newFlightOrderRowObj.getId());
						 	UserWallet userWallet =creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						BigDecimal userOpeningBalance=userWallet.getWalletbalance();
						BigDecimal addAmount=userWallet.getWalletbalance().subtract(walletAmountTranferHistory.getAmount());
						//logger.info(walletAmountTranferHistory.getWalletId()+"---minus amount--"+addAmount);
						userWallet.setWalletbalance(addAmount);
						UserWallet newUserWalletObj=creditNoteDao.updateUserWallet(userWallet);
						logger.info(newUserWalletObj.getWalletId()+":-"+newUserWalletObj.getWalletbalance()+"Flight markup taken back");
						creditNoteDao.updateWalletHistory("Flight markup taken back",userOpeningBalance,addAmount,walletAmountTranferHistory);
 
					}  
 
					if(walletAmountTranferHistory.getAction().equalsIgnoreCase("FlightBooking Initiated")){

						logger.info("walletAmountTranferHistory.getAmount()" +walletAmountTranferHistory.getAmount());
						//logger.info("getCancellationFees" +newFlightOrderRowObj.getCreditNote().getCancellationFees());

						BigDecimal refundedAmount;
						if(newFlightOrderRowObj.getCreditNote() !=null)
						{
						 refundedAmount = walletAmountTranferHistory.getAmount().subtract(newFlightOrderRowObj.getCreditNote().getCancellationFees()).subtract(newFlightOrderRowObj.getCreditNote().getConvenienceFees());
								
						}
						else
						{
							refundedAmount = walletAmountTranferHistory.getAmount();
						}
						
						//logger.info("refundedAmount:::"+refundedAmount);
						newFlightOrderRowObj.getCreditNote().setRefundedAmount(refundedAmount);
						CreditNote updateCreditNote = creditNoteDao.updateRefundingAmount(newFlightOrderRowObj.getCreditNote());
						if(updateCreditNote!=null){
							creditNoteDao.updateCreditNoteIssuedInFlightOrderRow(newFlightOrderRowObj.getId(), true);
							UserWallet userWallet =creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
							BigDecimal userOpeningBalance=userWallet.getWalletbalance();
							BigDecimal totUserwalletwithcancellation=userWallet.getWalletbalance().add(walletAmountTranferHistory.getAmount());
							userWallet.setWalletbalance(totUserwalletwithcancellation);
							UserWallet newUserWalletObj=creditNoteDao.updateUserWallet(userWallet);
							//logger.info(newUserWalletObj.getWalletId()+":-"+newUserWalletObj.getWalletbalance()+"FlightBooking cancelled");
							creditNoteDao.updateWalletHistory("FlightBooking cancelled",userOpeningBalance,totUserwalletwithcancellation,walletAmountTranferHistory);
							if(newFlightOrderRowObj.getCreditNote().getCancellationFees().compareTo(new BigDecimal("0.00"))==1){
								//logger.info("---------------------------------FLIGHTBOOKING CANCELLED----CANCELLATION------------------------------------" );
								//logger.info("CANCELLATION UserID-----------: " + walletAmountTranferHistory.getUserId());
								UserWallet userWallet1 =creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
								BigDecimal userOpeningBalance1=userWallet1.getWalletbalance();
								BigDecimal totUserwalletwithcancellation1=userWallet1.getWalletbalance().subtract(newFlightOrderRowObj.getCreditNote().getCancellationFees());
								userWallet1.setWalletbalance(totUserwalletwithcancellation1);
								creditNoteDao.updateUserWallet(userWallet1);
								walletAmountTranferHistory.setAmount(newFlightOrderRowObj.getCreditNote().getCancellationFees());
								walletAmountTranferHistory.setOpeningBalance(userOpeningBalance1);
								walletAmountTranferHistory.setClosingBalance(totUserwalletwithcancellation1);
								creditNoteDao.updateWalletHistory("cancellation fees",userOpeningBalance1,totUserwalletwithcancellation1,walletAmountTranferHistory);
							}
							if(newFlightOrderRowObj.getCreditNote().getConvenienceFees().compareTo(new BigDecimal("0.00"))==1){
								//logger.info("---------------------------------FLIGHTBOOKING CANCELLED----CONVENIENCE------------------------------------" );
								//logger.info("CONVENIENCE UserID-----------: " + walletAmountTranferHistory.getUserId());
								UserWallet userWallet1 =creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
								BigDecimal userOpeningBalance1=userWallet1.getWalletbalance();
								BigDecimal totUserwalletwithcancellation1=userWallet1.getWalletbalance().subtract(newFlightOrderRowObj.getCreditNote().getConvenienceFees());
								userWallet1.setWalletbalance(totUserwalletwithcancellation1);
								creditNoteDao.updateUserWallet(userWallet1);

								walletAmountTranferHistory.setAmount(newFlightOrderRowObj.getCreditNote().getConvenienceFees());
								walletAmountTranferHistory.setOpeningBalance(userOpeningBalance1);
								walletAmountTranferHistory.setClosingBalance(totUserwalletwithcancellation1);
								creditNoteDao.updateWalletHistory("convinience fees",userOpeningBalance1,totUserwalletwithcancellation1,walletAmountTranferHistory);
							} 

						} 
					} 
				}
			}

			creditNoteData.setCancellationFees(newFlightOrderRowObj.getCreditNote().getCancellationFees());
			creditNoteData.setConvenienceFees(newFlightOrderRowObj.getCreditNote().getConvenienceFees());
			creditNoteData.setGstAmount(newFlightOrderRowObj.getCreditNote().getGstAmount());
			creditNoteData.setDate(DateConversion.getBluestarDate(newFlightOrderRowObj.getBookingDate()));
			creditNoteData.setRefundedAmount(newFlightOrderRowObj.getCreditNote().getRefundedAmount());
			creditNoteData.setStaff(newFlightOrderRowObj.getUpdatedBy());
			creditNoteData.setTaxInvoiceNo(newFlightOrderRowObj.getInvoiceNo());
			creditNoteData.setTotalBookingAmount(newFlightOrderRowObj.getCreditNote().getTotalBookingAmount());
			creditNoteData.setTotwithGstAmount(creditNoteData.getRefundedAmount().add(creditNoteData.getGstAmount()));
			creditNoteData.setMessage("CREDIT NOTE ISSUED BY : "+creditNoteData.getStaff());
			 creditNoteData.setCustomerAdress(newFlightOrderRowObj.getFlightCustomer().getFirstName()+" "+newFlightOrderRowObj.getFlightCustomer().getLastName()+"-"
			+newFlightOrderRowObj.getFlightCustomer().getMobile()+"\n"+newFlightOrderRowObj.getFlightCustomer().getAddress());
			if(agentAddressList!=null){
				creditNoteData.setAgencyAddressList(agentAddressList);
			}

			if(flightOrderCustomerList!=null){
				creditNoteData.setOrderCustomerParticulars(flightOrderCustomerList);
			}
			if(flightOrderTripDetailList!=null){
				creditNoteData.setTripParticulars(flightOrderTripDetailList);
			}

		}
		else{
			if(newFlightOrderRowObj!=null && newFlightOrderRowObj.getCreditNote()!=null){
				creditNoteData.setCancellationFees(newFlightOrderRowObj.getCreditNote().getCancellationFees());
				creditNoteData.setConvenienceFees(newFlightOrderRowObj.getCreditNote().getConvenienceFees());
				creditNoteData.setGstAmount(newFlightOrderRowObj.getCreditNote().getGstAmount());
				creditNoteData.setDate(DateConversion.getBluestarDate(newFlightOrderRowObj.getBookingDate()));
				creditNoteData.setRefundedAmount(newFlightOrderRowObj.getCreditNote().getRefundedAmount());
				creditNoteData.setStaff(newFlightOrderRowObj.getUpdatedBy());
				creditNoteData.setTaxInvoiceNo(newFlightOrderRowObj.getInvoiceNo());
				creditNoteData.setTotalBookingAmount(newFlightOrderRowObj.getCreditNote().getTotalBookingAmount());
				creditNoteData.setTotwithGstAmount(creditNoteData.getRefundedAmount().add(creditNoteData.getGstAmount()));
				creditNoteData.setMessage("CREDIT NOTE ISSUED BY : "+creditNoteData.getStaff());
				creditNoteData.setCustomerAdress(newFlightOrderRowObj.getFlightCustomer().getFirstName()+" "+newFlightOrderRowObj.getFlightCustomer().getLastName()+"-"
						+newFlightOrderRowObj.getFlightCustomer().getMobile()+"\n"+newFlightOrderRowObj.getFlightCustomer().getAddress());
				//logger.info("----customer address------"+creditNoteData.getCustomerAdress());
				if(agentAddressList!=null){
					creditNoteData.setAgencyAddressList(agentAddressList);
				}

				if(flightOrderCustomerList!=null){
					creditNoteData.setOrderCustomerParticulars(flightOrderCustomerList);
				}
				if(flightOrderTripDetailList!=null){
					creditNoteData.setTripParticulars(flightOrderTripDetailList);
				}
			}
		}*/

		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public FlightCommissionReport getModel() {
		// TODO Auto-generated method stub
		return flightCommissionReport;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}



	public List<FlightCommissionReport> getAgentCreditNoteList() {
		return agentCreditNoteList;
	}



	public void setAgentCreditNoteList(List<FlightCommissionReport> agentCreditNoteList) {
		this.agentCreditNoteList = agentCreditNoteList;
	}

	/**
	 * @return the creditNoteData
	 */
	public CreditNoteData getCreditNoteData() {
		return creditNoteData;
	}

	/**
	 * @param creditNoteData the creditNoteData to set
	 */
	public void setCreditNoteData(CreditNoteData creditNoteData) {
		this.creditNoteData = creditNoteData;
	}


}
