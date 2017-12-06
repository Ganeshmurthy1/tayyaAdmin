package com.lintas.action.CreditNote;
 

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.action.CreditNote.Dao.HotelCreditNoteDao;
import com.lintas.admin.common.model.CreditNoteData;
import com.lintas.admin.hotel.model.HotelOrderRow;
import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HotelCustomerCreditNoteAction extends ActionSupport implements ModelDriven<HotelOrderRow>, SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	HotelCreditNoteDao creditNoteDao=new HotelCreditNoteDao();
	private CreditNoteData creditNoteData= new CreditNoteData();
	private HotelOrderRow hotelOrderRow=new HotelOrderRow();
	private String fromDate;
	private String toDate;
	private String period;
	private String companyType;
	private String userId;
	private List<HotelOrderRow> agentCreditNoteList;

	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HotelCustomerCreditNoteAction.class);
	public String getHotelCustomerCreditNoteList(){
		Company companySessionObj=(Company)sessionMap.get("Company");
		List<HotelOrderRow> hotelAgentCreditNoteList=creditNoteDao.getHotelAgentCreditNoteList(companySessionObj);
		if(hotelAgentCreditNoteList!=null && hotelAgentCreditNoteList.size()>0){
			setAgentCreditNoteList(hotelAgentCreditNoteList);
		}
		return SUCCESS;
	}

	public String  generateHotelCustomerCreditNote(){
		/*logger.info("----------hotelOrderRow.getId()-------------"+hotelOrderRow.getId());
		HotelOrderRow  newhotelOrderRowObj=creditNoteDao.getHotelOrderRowDataForCreditNote(hotelOrderRow.getId());
		List<WalletAmountTranferHistory> walletHistoryList=creditNoteDao.getWalletHistoryDetailsByHotelOrderId(newhotelOrderRowObj.getOrderReference());
		logger.info("getHotelOrderRoomInfos..........."+newhotelOrderRowObj.getHotelOrderRoomInfos().size());
		logger.info("-----newhotelOrderRowObj.getOrderId()---------"+newhotelOrderRowObj.getOrderReference());
		List<HotelOrderRoomInfo> hotelOrderRoomInfoList=newhotelOrderRowObj.getHotelOrderRoomInfos();
		List<HotelOrderGuest> hotelGuestInfoList =creditNoteDao.getHotelGuestInfoByRoomId(newhotelOrderRowObj.getHotelOrderRoomInfos());
		List<User> agentAddressList= creditNoteDao.getAgentAddress(newhotelOrderRowObj.getUserId());
		if(newhotelOrderRowObj!=null && !newhotelOrderRowObj.isCreditNoteIssued() && newhotelOrderRowObj.getCreditNote()!=null){ 
			if(walletHistoryList!=null){
				logger.info("walletHistoryList-------"+walletHistoryList.size());
				for(WalletAmountTranferHistory walletAmountTranferHistory:walletHistoryList){
					if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Hotel Commission Shared") ){
						UserWallet userWallet=creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						BigDecimal userOpeningBalance=userWallet.getWalletbalance();
						BigDecimal addAmount=userWallet.getWalletbalance().add(walletAmountTranferHistory.getAmount());
						//logger.info(walletAmountTranferHistory.getWalletId()+"---added amount--"+addAmount);
						userWallet.setWalletbalance(addAmount);
						UserWallet newUserWalletObj=creditNoteDao.updateUserWallet(userWallet);
						logger.info(newUserWalletObj.getWalletId()+":-"+newUserWalletObj.getWalletbalance()+"Hotel Commission taken back");
						creditNoteDao.updateWalletHistory("Hotel Commission taken back",userOpeningBalance,addAmount,walletAmountTranferHistory);
					}
					if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Hotel Commission Added")){
						UserWallet userWallet =creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						BigDecimal userOpeningBalance=userWallet.getWalletbalance();
						BigDecimal addAmount=userWallet.getWalletbalance().subtract(walletAmountTranferHistory.getAmount());
						userWallet.setWalletbalance(addAmount);
						UserWallet newUserWalletObj=creditNoteDao.updateUserWallet(userWallet);
						logger.info(newUserWalletObj.getWalletId()+":-"+newUserWalletObj.getWalletbalance()+"Hotel Commission taken back");
						creditNoteDao.updateWalletHistory("Hotel Commission taken back",userOpeningBalance,addAmount,walletAmountTranferHistory);

					}  
					if(walletAmountTranferHistory.getAction().equalsIgnoreCase("Hotel Markup")){
						UserWallet userWallet =creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						BigDecimal userOpeningBalance=userWallet.getWalletbalance();
						BigDecimal addAmount=userWallet.getWalletbalance().subtract(walletAmountTranferHistory.getAmount());
						//logger.info(walletAmountTranferHistory.getWalletId()+"---minus amount--"+addAmount);
						userWallet.setWalletbalance(addAmount);

						UserWallet newUserWalletObj=creditNoteDao.updateUserWallet(userWallet);
						logger.info(newUserWalletObj.getWalletId()+":-"+newUserWalletObj.getWalletbalance()+"Hotel markup taken back");
						creditNoteDao.updateWalletHistory("Hotel markup taken back",userOpeningBalance,addAmount,walletAmountTranferHistory);

					}  
					 
					if(walletAmountTranferHistory.getAction().equalsIgnoreCase("HotelBookingInitiated")){

						BigDecimal refundedAmount=walletAmountTranferHistory.getAmount().subtract(newhotelOrderRowObj.getCreditNote().getCancellationFees()).subtract(newhotelOrderRowObj.getCreditNote().getConvenienceFees());
						logger.info("refundedAmount:::"+refundedAmount);
						newhotelOrderRowObj.getCreditNote().setRefundedAmount(refundedAmount);
						CreditNote updateCreditNote=creditNoteDao.updateRefundingAmount(newhotelOrderRowObj.getCreditNote());
						if(updateCreditNote!=null){
							creditNoteDao.updateCreditNoteIssuedInHotelOrderRow(newhotelOrderRowObj.getId(), true);
							UserWallet userWallet =creditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
							BigDecimal userOpeningBalance=userWallet.getWalletbalance();
							BigDecimal totUserwalletwithRefundedAmount=userWallet.getWalletbalance().add(updateCreditNote.getRefundedAmount());
							userWallet.setWalletbalance(totUserwalletwithRefundedAmount);
							UserWallet newUserWalletObj=creditNoteDao.updateUserWallet(userWallet);
							logger.info(newUserWalletObj.getWalletId()+":-"+newUserWalletObj.getWalletbalance()+"HotelBooking cancelled");
							creditNoteDao.updateWalletHistory("HotelBooking cancelled",userOpeningBalance,totUserwalletwithRefundedAmount,walletAmountTranferHistory);
						} 
					} 
				}
				creditNoteData.setCancellationFees(newhotelOrderRowObj.getCreditNote().getCancellationFees());
				creditNoteData.setConvenienceFees(newhotelOrderRowObj.getCreditNote().getConvenienceFees());
				//creditNoteData.setGstAmount(newFlightOrderRowObj.getCreditNote().getGstAmount());
				creditNoteData.setDate(DateConversion.convertDateToStringToDateWithTIME(newhotelOrderRowObj.getCheckInDate()));
				creditNoteData.setRefundedAmount(newhotelOrderRowObj.getCreditNote().getRefundedAmount());
				creditNoteData.setStaff(newhotelOrderRowObj.getUpdatedBy());
				creditNoteData.setTaxInvoiceNo(newhotelOrderRowObj.getInvoiceNo());
				creditNoteData.setTotalBookingAmount(newhotelOrderRowObj.getCreditNote().getTotalBookingAmount());
				creditNoteData.setTotwithGstAmount(newhotelOrderRowObj.getCreditNote().getRefundedAmount().add(newhotelOrderRowObj.getCreditNote().getGstAmount()));
				creditNoteData.setMessage("CREDIT NOTE ISSUED BY : "+creditNoteData.getStaff());
				creditNoteData.setCheckInDate(DateConversion.convertDateToStringToDateWithTIME(newhotelOrderRowObj.getCheckInDate()));
				creditNoteData.setCheckOutDate(DateConversion.convertDateToStringToDateWithTIME(newhotelOrderRowObj.getCheckOutDate()));
				 creditNoteData.setCustomerAdress(newhotelOrderRowObj.getOrderCustomer().getFirstName()+" "+newhotelOrderRowObj.getOrderCustomer().getLastName()+"-"
							+newhotelOrderRowObj.getOrderCustomer().getMobile()+"\n"+newhotelOrderRowObj.getOrderCustomer().getAddress());
				
				if(agentAddressList!=null){
					creditNoteData.setAgencyAddressList(agentAddressList);
				}
				if(hotelGuestInfoList!=null){
					creditNoteData.setHotelGuestInfoList(hotelGuestInfoList);
				}
				if(hotelOrderRoomInfoList!=null){
					creditNoteData.setHotelRoomInfoList(hotelOrderRoomInfoList);
				}

			}
		} 
		else{
			if(newhotelOrderRowObj!=null && newhotelOrderRowObj.getCreditNote()!=null){
				creditNoteData.setCancellationFees(newhotelOrderRowObj.getCreditNote().getCancellationFees());
				creditNoteData.setConvenienceFees(newhotelOrderRowObj.getCreditNote().getConvenienceFees());
				//creditNoteData.setGstAmount(newFlightOrderRowObj.getCreditNote().getGstAmount());
				creditNoteData.setDate(DateConversion.convertDateToStringToDateWithTIME(newhotelOrderRowObj.getCheckInDate()));
				creditNoteData.setRefundedAmount(newhotelOrderRowObj.getCreditNote().getRefundedAmount());
				creditNoteData.setStaff(newhotelOrderRowObj.getUpdatedBy());
				creditNoteData.setTaxInvoiceNo(newhotelOrderRowObj.getInvoiceNo());
				creditNoteData.setTotalBookingAmount(newhotelOrderRowObj.getCreditNote().getTotalBookingAmount());
				creditNoteData.setTotwithGstAmount(newhotelOrderRowObj.getCreditNote().getRefundedAmount().add(newhotelOrderRowObj.getCreditNote().getGstAmount()));
				creditNoteData.setMessage("CREDIT NOTE ISSUED BY : "+creditNoteData.getStaff());
				creditNoteData.setCheckInDate(DateConversion.convertDateToStringToDateWithTIME(newhotelOrderRowObj.getCheckInDate()));
				creditNoteData.setCheckOutDate(DateConversion.convertDateToStringToDateWithTIME(newhotelOrderRowObj.getCheckOutDate()));
				 creditNoteData.setCustomerAdress(newhotelOrderRowObj.getOrderCustomer().getFirstName()+" "+newhotelOrderRowObj.getOrderCustomer().getLastName()+"-"
							+newhotelOrderRowObj.getOrderCustomer().getMobile()+"\n"+newhotelOrderRowObj.getOrderCustomer().getAddress());
				
				
				if(agentAddressList!=null){
					creditNoteData.setAgencyAddressList(agentAddressList);
				}
				if(hotelGuestInfoList!=null){
					creditNoteData.setHotelGuestInfoList(hotelGuestInfoList);
				}
				if(hotelOrderRoomInfoList!=null){
					creditNoteData.setHotelRoomInfoList(hotelOrderRoomInfoList);
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
	public HotelOrderRow getModel() {
		// TODO Auto-generated method stub
		return hotelOrderRow;
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



	public List<HotelOrderRow> getAgentCreditNoteList() {
		return agentCreditNoteList;
	}



	public void setAgentCreditNoteList(List<HotelOrderRow> agentCreditNoteList) {
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