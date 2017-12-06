package com.lintas.action.CreditNote;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.action.CreditNote.Dao.CarCreditNoteDao;
import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.modal.CarCreditNote;
import com.lintas.action.CreditNote.modal.CreditNoteUtility;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.car.model.CarCommissionReport;
import com.lintas.admin.car.model.CarOrderRow;
import com.lintas.admin.common.model.CreditNoteData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.utility.DateConversion;
import com.lintas.utility.GstPropertiesFile;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class CarAgentCreditNoteAction extends ActionSupport implements ModelDriven<CarCommissionReport>, SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	CarCreditNoteDao creditNoteDao=new CarCreditNoteDao();
	private CreditNoteData creditNoteData= new CreditNoteData();
	private CreditNoteDao flightCreditNoteDao=new CreditNoteDao();
	CarCommissionReport carCommissionReport=new CarCommissionReport();
	private String fromDate;
	private String toDate;
	private String period;
	private String companyType;
	private String userId;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CarAgentCreditNoteAction.class);
	
	public String  generateCreditNote() throws NumberFormatException, Exception{
		logger.info("----------generateCreditNote() rowid-------------"+carCommissionReport.getId());
		Company loginCompanyObj=(Company) sessionMap.get("Company");
		User loginUserObj=(User) sessionMap.get("User");
		CarOrderRow  newCarOrderRowObj=creditNoteDao.getCarOrderRowDataForCreditNote(carCommissionReport.getId());
		if(newCarOrderRowObj!=null){
			GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
			UserDAO userDAO = new UserDAO();
			User userNew=userDAO.getUserByUserId(Integer.valueOf(newCarOrderRowObj.getUserId()));
			Company bookingCompany=flightCreditNoteDao.getCompanyAddress(newCarOrderRowObj.getCompanyId());
			User walletUser = userNew;
			if(walletUser != null){
				if(bookingCompany.getCompanyRole()!=null &&(bookingCompany.getCompanyRole().isCorporate()||bookingCompany.getCompanyRole().isAgent()||bookingCompany.getCompanyRole().isDistributor()))
				{

					walletUser = userDAO.getUserPasswordForLock(bookingCompany.getEmail());
				}
			}
			List<CarCreditNote> newCreditNoteList= creditNoteDao.getCreditNoteListByOrderRowID(newCarOrderRowObj.getId());
			List<Integer> parentUsersList=new CreditNoteDao().getParentUserIdLevel2(Integer.valueOf(walletUser.getId()));
			if(newCarOrderRowObj!=null && !newCarOrderRowObj.isCreditNoteIssued() && newCreditNoteList!=null && newCreditNoteList.size()>0 && parentUsersList!=null && parentUsersList.size()>0)
			{
				try{
					issueCreditNote(newCarOrderRowObj,newCreditNoteList,parentUsersList,loginCompanyObj,loginUserObj,gstPropertiesFile);
				}
				catch(Exception e)
				{
					addActionError("Error: "+e.getMessage());
					return ERROR;
				}

			}
			newCarOrderRowObj=creditNoteDao.getCarOrderRowDataForCreditNote(carCommissionReport.getId());
			if(newCarOrderRowObj!=null && newCarOrderRowObj.isCreditNoteIssued() && parentUsersList.size()>0)
			{ 
				CarCreditNote creditNoteObj=new CarCreditNote();
				if(parentUsersList.size()==1){
					creditNoteObj=creditNoteDao.getCreditNoteDetailsByComapnyId(loginCompanyObj.getCompanyid(),newCarOrderRowObj.getId());		
				}
				else if(parentUsersList.size()==2){
					creditNoteObj=creditNoteDao.getCreditNoteDetailsByUserId(parentUsersList.get(1),newCarOrderRowObj.getId());		
				}
				else if(parentUsersList.size()==3){
					if(loginCompanyObj.getCompanyRole().isSuperUser()){
						creditNoteObj=creditNoteDao.getCreditNoteDetailsByUserId(parentUsersList.get(1),newCarOrderRowObj.getId());		
					}
					else{
						creditNoteObj=creditNoteDao.getCreditNoteDetailsByUserId(parentUsersList.get(2),newCarOrderRowObj.getId());		
					}
				}
				
//				CarCreditNote creditNoteObj=creditNoteDao.getCreditNoteDetailsByComapnyId(loginCompanyObj.getCompanyid(),newCarOrderRowObj.getId());		
				buildCreditNoteData(new BigDecimal(0) ,new BigDecimal(0),new BigDecimal(0),creditNoteObj,newCarOrderRowObj,gstPropertiesFile,creditNoteData); 
			}     
		}

		return SUCCESS;
	}




	private void issueCreditNote(CarOrderRow newCarOrderRowObj, List<CarCreditNote> newCreditNoteList2, List<Integer> parentUsersList, Company loginCompanyObj, User loginUserObj, GstPropertiesFile gstPropertiesFile) throws Exception {
		BigDecimal totManagementFee=new BigDecimal("0.00");
		BigDecimal cancellationFee=new BigDecimal("0.00");
		BigDecimal totalCancellationFee=new BigDecimal("0.00");
		BigDecimal refundedAmount=new BigDecimal("0.00");
		BigDecimal convFee=new BigDecimal("0.00");
		BigDecimal totManagementFeeForRefund=new BigDecimal("0.00");
		BigDecimal gstAmountFee=newCarOrderRowObj.getTotalGstTax();
		if(gstAmountFee==null){
			gstAmountFee=new BigDecimal("0.00");
		}
		
		
		SortedMap<Integer,BigDecimal> userIdwithManagementFeeMap=new TreeMap <>();
		SortedMap<Integer,BigDecimal> userIdwithConvenienceFeeMap=new TreeMap<>();
		CarOrderRow creditNoteIssuedRow=null;
		LinkedHashMap<Integer,WalletAmountTranferHistory> walletUserIdMap=new LinkedHashMap <>();
		BigDecimal actualAmount=new BigDecimal(0);
		List<WalletAmountTranferHistory> walletHistoryList=flightCreditNoteDao.getWalletHistoryDetailsByOrderId(newCarOrderRowObj.getOrderId());
		Map<Integer,CarCreditNote> userIdwithCreditNoteMap=new LinkedHashMap <>();
		if(walletHistoryList!=null && walletHistoryList.size()>0){
			for(WalletAmountTranferHistory walletAmountTranferHistory:walletHistoryList){
				walletUserIdMap.put(walletAmountTranferHistory.getUserId(), walletAmountTranferHistory);
			}
		}

		if(newCreditNoteList2!=null && newCreditNoteList2.size()>0){

			for(CarCreditNote creditNote:newCreditNoteList2)
			{
				userIdwithConvenienceFeeMap.put(Integer.parseInt(creditNote.getUserId()), creditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
				userIdwithCreditNoteMap.put(Integer.parseInt(creditNote.getUserId()), creditNote);
				if(!newCarOrderRowObj.getCompanyId().equals(creditNote.getCompanyId())) 
					totManagementFeeForRefund=totManagementFeeForRefund.add(creditNote.getManagementFees());
				BigDecimal cancellationFeeCheck=creditNote.getCancellationFees().setScale(2, BigDecimal.ROUND_UP);
				if(!cancellationFeeCheck.toString().equals("0.00")){
					totalCancellationFee=cancellationFeeCheck;
				}
			
			}	
		}
		int i=0;
		for(Integer userId:parentUsersList)
		{
			WalletAmountTranferHistory walletAmount=walletUserIdMap.get(userId);
			actualAmount=walletAmount.getAmount();
			CarCreditNote creditNote = userIdwithCreditNoteMap.get(userId);
			if(creditNote == null){
				throw new Exception("Credit Note not issued by parent");
			}

			if(creditNote!=null)
			{
				if(!creditNote.isCreditnoteIssued() && i==0)
				{
					logger.info("i-------------"+i);
					cancellationFee=creditNote.getCancellationFees();
					convFee=creditNote.getConvenienceFees();
					logger.info("compani is-------"+creditNote.getCompanyId()+"------"+creditNote.getRowId()+"cancellationFee----------"+cancellationFee);
					if(cancellationFee!=null){
						refundedAmount=actualAmount.subtract(cancellationFee).subtract(convFee).subtract(creditNote.getManagementFees()).subtract(gstAmountFee);
						logger.info("refundedAmount--deduct cancellation fee-----"+refundedAmount);
					}
					i++;
				}

				if(Integer.parseInt(creditNote.getCompanyId())==loginCompanyObj.getCompanyid()){ 
					CarCreditNote creditNoteUpdate=new CarCreditNote();
					creditNoteUpdate.setRefundedAmount(refundedAmount);
					creditNoteUpdate.setId(creditNote.getId());
					creditNoteUpdate.setCreditnoteIssued(true);
					creditNoteUpdate.setTotalBookingAmount(actualAmount);
					creditNoteUpdate=creditNoteDao.updateRefundingAmount(creditNoteUpdate);
					new CompanyDAO().insertEmail(String.valueOf(creditNoteUpdate.getId()), 0, Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_CAR);
					logger.info("refundedAmount--deduct ConvenienceFees fee-----"+refundedAmount);
					if(creditNoteUpdate.isOrderUpdated()){
						creditNoteIssuedRow= creditNoteDao.updateCreditNoteIssuedInCarOrderRow(newCarOrderRowObj.getId(), true);

						List<CarCreditNote> newcreditNoteList= creditNoteDao.getCreditNoteListByOrderRowID(creditNoteIssuedRow.getId());
						if(newcreditNoteList!=null && newcreditNoteList.size()>0){
							for(CarCreditNote creditNoteConvFee:newcreditNoteList)
							{
								logger.info("getCompanyId-----"+creditNoteConvFee.getCompanyId()+"getManagementFees---------"+creditNoteConvFee.getManagementFees());
								userIdwithManagementFeeMap.put(Integer.parseInt(creditNoteConvFee.getUserId()), creditNoteConvFee.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
								userIdwithConvenienceFeeMap.put(Integer.parseInt(creditNoteConvFee.getUserId()), creditNoteConvFee.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));

								if(!creditNoteIssuedRow.getCompanyId().equals(creditNoteConvFee.getCompanyId())) 
									totManagementFee=totManagementFee.add(creditNoteConvFee.getManagementFees().setScale(2, BigDecimal.ROUND_UP));

								BigDecimal cancellationFeeCheck=creditNoteConvFee.getCancellationFees().setScale(2, BigDecimal.ROUND_UP);
								if(!cancellationFeeCheck.toString().equals("0.00")){
									cancellationFee=cancellationFeeCheck;
								}
							}
						}
						logger.info("userIdwithManagementFeeMap--------"+userIdwithManagementFeeMap);
						logger.info("userIdwithConvenienceFeeMap--------"+userIdwithConvenienceFeeMap);
						new OrderModifyDao().updateCarOrderRowBookingStatusInfo(creditNoteIssuedRow);
						if(creditNoteIssuedRow.isCreditNoteIssued()){
							if(walletUserIdMap!=null && walletUserIdMap.size()>0){
								if(!walletAmount.getTransactionType().equalsIgnoreCase("Card")) 
								bookingAmountAddingBackToWallet(parentUsersList, walletUserIdMap);
								CreditNoteUtility.getInstance().deductAndCollectExtraCharges(parentUsersList, walletUserIdMap, userIdwithManagementFeeMap, userIdwithConvenienceFeeMap, flightCreditNoteDao, cancellationFee, totManagementFee,gstAmountFee);
							}
						}
					}

				}
				else{
					CarCreditNote creditNoteUpdate=new CarCreditNote();
					if(parentUsersList.size()==2){
						int superUserId=parentUsersList.get(0);
						convFee=userIdwithConvenienceFeeMap.get(superUserId);
						int agencyUserId=parentUsersList.get(1);
						if(agencyUserId==userId) 
							refundedAmount=actualAmount.subtract(gstAmountFee).subtract(totalCancellationFee).subtract(convFee).subtract(creditNote.getManagementFees()).subtract(totManagementFeeForRefund);
					}
					if(parentUsersList.size()==3){
						int superUserId=parentUsersList.get(0);
						convFee=userIdwithConvenienceFeeMap.get(superUserId);
						int disUserId=parentUsersList.get(1);
						int agencyUserId=parentUsersList.get(2);
						if(agencyUserId==userId) 
							refundedAmount=actualAmount.subtract(gstAmountFee).subtract(totalCancellationFee).subtract(convFee).subtract(creditNote.getManagementFees()).subtract(totManagementFeeForRefund);
						if(disUserId==userId) 
							refundedAmount=actualAmount.subtract(gstAmountFee).subtract(totalCancellationFee).subtract(convFee).subtract(totManagementFeeForRefund);
					
					}
					creditNoteUpdate.setRefundedAmount(refundedAmount);
					creditNoteUpdate.setId(creditNote.getId());
					creditNoteUpdate.setCreditnoteIssued(true);
					creditNoteUpdate.setTotalBookingAmount(actualAmount);
					creditNoteUpdate=creditNoteDao.updateRefundingAmount(creditNoteUpdate);

				}
			}
		}
	}

	private void buildCreditNoteData(BigDecimal totConvenienceFees ,BigDecimal totChildManageementFee, BigDecimal totCancellationFee ,CarCreditNote creditNoteObj, CarOrderRow newCarOrderRowObj,GstPropertiesFile gstPropertiesFile, CreditNoteData creditNoteData) {
		creditNoteData.setManagementFees(totChildManageementFee!=null ?totChildManageementFee.setScale(2, BigDecimal.ROUND_UP): new BigDecimal(0));
		creditNoteData.setConvenienceFees(totConvenienceFees!=null?totConvenienceFees.setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0));
		creditNoteData.setGstAmount(creditNoteObj.getGstAmount()!=null ?creditNoteObj.getGstAmount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0));
		creditNoteData.setCancellationFees(creditNoteObj.getCancellationFees()!=null?creditNoteObj.getCancellationFees().setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0));
		creditNoteData.setDate(DateConversion.convertDateToStringToDateWithTIME(creditNoteObj.getOrderedAt()));
		creditNoteData.setStaff(creditNoteObj.getAlterBy());
		creditNoteData.setTaxInvoiceNo(newCarOrderRowObj.getInvoiceNo());
		creditNoteData.setOrderId(newCarOrderRowObj.getOrderId());
		creditNoteData.setTotalBookingAmount(creditNoteObj.getTotalBookingAmount().setScale(2, BigDecimal.ROUND_UP));
		creditNoteData.setLintasGstOn(gstPropertiesFile.getGstSwitchonValue());
		creditNoteData.setBaseCurrency(newCarOrderRowObj.getBookingCurrency());
		creditNoteData.setBookedCurrency(newCarOrderRowObj.getBookingCurrency());
		creditNoteData.setCreditNoteId(creditNoteObj.getId());
		creditNoteData.setCustomerName(newCarOrderRowObj.getOrderCustomer().getFirstName()+""+newCarOrderRowObj.getOrderCustomer().getLastName());
		creditNoteData.setCNINumber(creditNoteObj.getCNINumber());
		creditNoteData.setIssuedDate(DateConversion.convertTimestampToStringwithoutseconds(creditNoteObj.getIssuedAt()));
		creditNoteData.setTaxInvoiceDate(DateConversion.convertTimestampToStringwithoutseconds(newCarOrderRowObj.getCreatedAt()));
		creditNoteData.setCompanyId(Integer.parseInt(creditNoteObj.getCompanyId()));
		creditNoteData.setCustomerAdress(newCarOrderRowObj.getOrderCustomer().getAddress());

		if(creditNoteObj.getRefundedAmount()!=null)
			creditNoteData.setRefundedAmount(creditNoteObj.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP));
		else
			creditNoteData.setRefundedAmount(new BigDecimal(0).setScale(2, BigDecimal.ROUND_UP));
		creditNoteData.setTotwithGstAmount(creditNoteData.getRefundedAmount().setScale(2, BigDecimal.ROUND_UP));

		if(totCancellationFee!=null)
		{
			creditNoteData.setRefundedAmount(creditNoteObj.getTotalBookingAmount().subtract(totCancellationFee));
			creditNoteData.setTotwithGstAmount(creditNoteObj.getTotalBookingAmount().subtract(totCancellationFee).setScale(2, BigDecimal.ROUND_UP));
		}

	}

	public String generateCustomerCreditNote(){
		return SUCCESS;
	}

	public void bookingAmountAddingBackToWallet(List<Integer> parentUserIds,LinkedHashMap<Integer,WalletAmountTranferHistory> walletMap) throws Exception{
		BigDecimal userOpeningBalance=new BigDecimal(0);
		BigDecimal closingBalance=new BigDecimal(0);
		if(parentUserIds.size()==1){
			int superUserId=parentUserIds.get(0);
			User superUserObj=flightCreditNoteDao.getParentAddress(superUserId);
			WalletAmountTranferHistory walletHistory=walletMap.get(superUserId)!=null?walletMap.get(superUserId):new WalletAmountTranferHistory();
			if(walletHistory!=null && walletHistory.getAction().equals("Car Booking payment")){
				if(superUserObj!=null && superUserObj.getAgentWallet()!=null){
					UserWallet fbInitWallet=superUserObj.getAgentWallet();
					if(walletHistory.getTransactionType().equals("Withdrawal")){
						userOpeningBalance=fbInitWallet.getDepositBalance();
						closingBalance=fbInitWallet.getDepositBalance().add(walletHistory.getAmount());
						fbInitWallet.setDepositBalance(closingBalance);
					}
					else{
						userOpeningBalance=fbInitWallet.getWalletbalance();
						closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
						fbInitWallet.setWalletbalance(closingBalance);
					}
					fbInitWallet.setTxType(walletHistory.getTransactionType());
					UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
					if(newUserWalletObj!=null){
						flightCreditNoteDao.updateWalletHistory("Car booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
			}
			else
				throw new Exception("Wallet Action is not matching");


		}

		if(parentUserIds.size()==2){
			int superUserId=parentUserIds.get(0);
			int disUserId=parentUserIds.get(1);
			User superUserObj=flightCreditNoteDao.getParentAddress(superUserId);
			User agencyUserObj=flightCreditNoteDao.getParentAddress(disUserId);
			if(superUserObj!=null && superUserObj.getAgentWallet()!=null){
				WalletAmountTranferHistory walletHistory=walletMap.get(superUserId)!=null?walletMap.get(superUserId):new WalletAmountTranferHistory();
				if(walletHistory!=null && walletHistory.getAction().equals("Car Booking payment")){
					UserWallet fbInitWallet=superUserObj.getAgentWallet();
					if(walletHistory.getTransactionType().equals("Withdrawal")){
						userOpeningBalance=fbInitWallet.getDepositBalance();
						closingBalance=fbInitWallet.getDepositBalance().add(walletHistory.getAmount());
						fbInitWallet.setDepositBalance(closingBalance);
					}
					else{
						userOpeningBalance=fbInitWallet.getWalletbalance();
						closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
						fbInitWallet.setWalletbalance(closingBalance);
					}
					fbInitWallet.setTxType(walletHistory.getTransactionType());
					UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
					if(newUserWalletObj!=null){
						flightCreditNoteDao.updateWalletHistory("Car booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
				else
					throw new Exception("Wallet Action is not matching");
			}
			if(agencyUserObj!=null && agencyUserObj.getAgentWallet()!=null){
				WalletAmountTranferHistory walletHistory=walletMap.get(disUserId)!=null?walletMap.get(disUserId):new WalletAmountTranferHistory();
				if(walletHistory!=null && walletHistory.getAction().equals("Car Booking payment")){
					UserWallet fbInitWallet=agencyUserObj.getAgentWallet();
					if(walletHistory.getTransactionType().equals("Withdrawal")){
						userOpeningBalance=fbInitWallet.getDepositBalance();
						closingBalance=fbInitWallet.getDepositBalance().add(walletHistory.getAmount());
						fbInitWallet.setDepositBalance(closingBalance);
					}
					else{
						userOpeningBalance=fbInitWallet.getWalletbalance();
						closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
						fbInitWallet.setWalletbalance(closingBalance);
					}
					fbInitWallet.setTxType(walletHistory.getTransactionType());
					UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
					if(newUserWalletObj!=null){
						flightCreditNoteDao.updateWalletHistory("Car booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
				else
					throw new Exception("Wallet Action is not matching");
			}

		}
		if(parentUserIds.size()==3){
			int superUserId=parentUserIds.get(0);
			int disUserId=parentUserIds.get(1);
			int agencyUserId=parentUserIds.get(2);
			User superUserObj=flightCreditNoteDao.getParentAddress(superUserId);
			User disUserObj=flightCreditNoteDao.getParentAddress(disUserId);
			User agencyUserObj=flightCreditNoteDao.getParentAddress(agencyUserId);
			if(superUserObj!=null && superUserObj.getAgentWallet()!=null){
				WalletAmountTranferHistory walletHistory=walletMap.get(superUserId)!=null?walletMap.get(superUserId):new WalletAmountTranferHistory();
				if(walletHistory!=null && walletHistory.getAction().equals("Car Booking payment")){
					UserWallet fbInitWallet=superUserObj.getAgentWallet();
					if(walletHistory.getTransactionType().equals("Withdrawal")){
						userOpeningBalance=fbInitWallet.getDepositBalance();
						closingBalance=fbInitWallet.getDepositBalance().add(walletHistory.getAmount());
						fbInitWallet.setDepositBalance(closingBalance);
					}
					else{
						userOpeningBalance=fbInitWallet.getWalletbalance();
						closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
						fbInitWallet.setWalletbalance(closingBalance);
					}
					fbInitWallet.setTxType(walletHistory.getTransactionType());
					UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
					if(newUserWalletObj!=null){
						flightCreditNoteDao.updateWalletHistory("Car booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
				else
					throw new Exception("Wallet Action is not matching");

			}
			if(disUserObj!=null && disUserObj.getAgentWallet()!=null){
				WalletAmountTranferHistory walletHistory=walletMap.get(disUserId)!=null?walletMap.get(disUserId):new WalletAmountTranferHistory();
				if(walletHistory!=null && walletHistory.getAction().equals("Car Booking payment")){
					UserWallet fbInitWallet=disUserObj.getAgentWallet();
					if(walletHistory.getTransactionType().equals("Withdrawal")){
						userOpeningBalance=fbInitWallet.getDepositBalance();
						closingBalance=fbInitWallet.getDepositBalance().add(walletHistory.getAmount());
						fbInitWallet.setDepositBalance(closingBalance);
					}
					else{
						userOpeningBalance=fbInitWallet.getWalletbalance();
						closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
						fbInitWallet.setWalletbalance(closingBalance);
					}
					fbInitWallet.setTxType(walletHistory.getTransactionType());
					UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
					if(newUserWalletObj!=null){
						flightCreditNoteDao.updateWalletHistory("Car booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
				else
					throw new Exception("Wallet Action is not matching");
			}
			if(agencyUserObj!=null && agencyUserObj.getAgentWallet()!=null){
				WalletAmountTranferHistory walletHistory=walletMap.get(agencyUserId)!=null?walletMap.get(agencyUserId):new WalletAmountTranferHistory();
				if(walletHistory!=null && walletHistory.getAction().equals("Car Booking payment")){
					UserWallet fbInitWallet=agencyUserObj.getAgentWallet();
					if(walletHistory.getTransactionType().equals("Withdrawal")){
						userOpeningBalance=fbInitWallet.getDepositBalance();
						closingBalance=fbInitWallet.getDepositBalance().add(walletHistory.getAmount());
						fbInitWallet.setDepositBalance(closingBalance);
					}
					else{
						userOpeningBalance=fbInitWallet.getWalletbalance();
						closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
						fbInitWallet.setWalletbalance(closingBalance);
					}
					fbInitWallet.setTxType(walletHistory.getTransactionType());
					UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
					if(newUserWalletObj!=null){
						flightCreditNoteDao.updateWalletHistory("Car booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
			}
			else
				throw new Exception("Wallet Action is not matching");

		}

	}



	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public CarCommissionReport getModel() {
		// TODO Auto-generated method stub
		return carCommissionReport;
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
