package com.lintas.action.CreditNote;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.action.CreditNote.Dao.TrainCreditNoteDao;
import com.lintas.action.CreditNote.modal.CreditNoteUtility;
import com.lintas.action.CreditNote.modal.TrainCreditNote;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.OrderModifyDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.common.model.CreditNoteData;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.Email;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.admin.train.model.TrainCommissionReport;
import com.lintas.admin.train.model.TrainOrderRow;
import com.lintas.utility.DateConversion;
import com.lintas.utility.GstPropertiesFile;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class TrainAgentCreditNoteAction extends ActionSupport implements ModelDriven<TrainCommissionReport>, SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SessionMap<String, Object>  sessionMap;
	TrainCreditNoteDao creditNoteDao=new TrainCreditNoteDao();
	CreditNoteDao flightCreditNoteDao=new CreditNoteDao();
	private CreditNoteData creditNoteData= new CreditNoteData();
	TrainCommissionReport trainCommissionReport=new TrainCommissionReport();
	private String fromDate;
	private String toDate;
	private String period;
	private String companyType;
	private String userId;
	private List<TrainCommissionReport> agentCreditNoteList;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TrainAgentCreditNoteAction.class);
	public String getTrainAgentCreditNoteList(){
		Company companySessionObj=(Company)sessionMap.get("Company");
		List<TrainCommissionReport> agentCreditNoteList=creditNoteDao.getAgentCreditNoteList(companySessionObj);
		if(agentCreditNoteList!=null && agentCreditNoteList.size()>0){
			setAgentCreditNoteList(agentCreditNoteList);
		}


		return SUCCESS;
	}

	public String  generateCreditNote() throws NumberFormatException, Exception{
		logger.info("----------generateCreditNote() rowid-------------"+trainCommissionReport.getId());
		Company loginCompanyObj=(Company) sessionMap.get("Company");
		User loginUserObj=(User) sessionMap.get("User");
		TrainOrderRow  newTrainOrderRowObj=creditNoteDao.getTrainOrderRowDataForCreditNote(trainCommissionReport.getId());
		if(newTrainOrderRowObj!=null){
			GstPropertiesFile gstPropertiesFile =new GstPropertiesFile();
			UserDAO userDAO = new UserDAO();
			User userNew=userDAO.getUserByUserId(Integer.valueOf(newTrainOrderRowObj.getUserId()));
			Company bookingCompany=flightCreditNoteDao.getCompanyAddress(newTrainOrderRowObj.getCompanyId());
			User walletUser = userNew;
			if(walletUser != null){
				if(bookingCompany.getCompanyRole()!=null &&(bookingCompany.getCompanyRole().isCorporate()||bookingCompany.getCompanyRole().isAgent()||bookingCompany.getCompanyRole().isDistributor()))
				{

					walletUser = userDAO.getUserPasswordForLock(bookingCompany.getEmail());
				}
			}

			List<TrainCreditNote> newCreditNoteList= creditNoteDao.getCreditNoteListByOrderRowID(newTrainOrderRowObj.getId());
			List<Integer> parentUsersList=new CreditNoteDao().getParentUserIdLevel2(Integer.valueOf(walletUser.getId()));
			if(newTrainOrderRowObj!=null && !newTrainOrderRowObj.isCreditNoteIssued() && newCreditNoteList!=null && newCreditNoteList.size()>0 && parentUsersList!=null && parentUsersList.size()>0)
			{
				try{
					issueCreditNote(newTrainOrderRowObj,newCreditNoteList,parentUsersList,loginCompanyObj,loginUserObj,gstPropertiesFile);
				}
				catch(Exception e)
				{
					addActionError("Error: "+e.getMessage());
					return ERROR;
				}

			}
			newTrainOrderRowObj=creditNoteDao.getTrainOrderRowDataForCreditNote(trainCommissionReport.getId());
			if(newTrainOrderRowObj!=null && newTrainOrderRowObj.isCreditNoteIssued() && parentUsersList.size()>0)
			{
				TrainCreditNote creditNoteObj=new TrainCreditNote();
				if(parentUsersList.size()==1){
					creditNoteObj=creditNoteDao.getCreditNoteDetailsByComapnyId(loginCompanyObj.getCompanyid(),newTrainOrderRowObj.getId());		
				}
				else if(parentUsersList.size()==2){
					creditNoteObj=creditNoteDao.getCreditNoteDetailsByUserId(parentUsersList.get(1),newTrainOrderRowObj.getId());		
				}
				else if(parentUsersList.size()==3){
					if(loginCompanyObj.getCompanyRole().isSuperUser()){
						creditNoteObj=creditNoteDao.getCreditNoteDetailsByUserId(parentUsersList.get(1),newTrainOrderRowObj.getId());		
					}
					else{
						creditNoteObj=creditNoteDao.getCreditNoteDetailsByUserId(parentUsersList.get(2),newTrainOrderRowObj.getId());		
					}
				}
				
//				TrainCreditNote creditNoteObj=creditNoteDao.getCreditNoteDetailsByComapnyId(loginCompanyObj.getCompanyid(),newTrainOrderRowObj.getId());		
				buildCreditNoteData(new BigDecimal(0) ,new BigDecimal(0),new BigDecimal(0),creditNoteObj,newTrainOrderRowObj,gstPropertiesFile,creditNoteData);
			}
		}
		return SUCCESS;
	}




	private void issueCreditNote(TrainOrderRow newTrainOrderRowObj, List<TrainCreditNote> newCreditNoteList2, List<Integer> parentUsersList, Company loginCompanyObj, User loginUserObj, GstPropertiesFile gstPropertiesFile) throws Exception {
		BigDecimal totalCancellationFee=new BigDecimal("0.00");
		BigDecimal totManagementFee=new BigDecimal("0.00");
		BigDecimal cancellationFee=new BigDecimal("0.00");
		BigDecimal refundedAmount=new BigDecimal("0.00");
		BigDecimal actualAmount=new BigDecimal("0.00");
		BigDecimal gstAmountFee=newTrainOrderRowObj.getTotalGstTax();
		if(gstAmountFee==null){
			gstAmountFee=new BigDecimal("0.00");
		}
		BigDecimal convFee=new BigDecimal("0.00");
		BigDecimal totManagementFeeForRefund=new BigDecimal("0.00");
		SortedMap<Integer,BigDecimal> userIdwithManagementFeeMap=new TreeMap<>();
		SortedMap<Integer,BigDecimal> userIdwithConvenienceFeeMap=new TreeMap<>();
		TrainOrderRow creditNoteIssuedRow=null;
		LinkedHashMap<Integer,WalletAmountTranferHistory> walletUserIdMap=new LinkedHashMap <>();
		List<WalletAmountTranferHistory> walletHistoryList=flightCreditNoteDao.getWalletHistoryDetailsByOrderId(newTrainOrderRowObj.getOrderId());
		Map<Integer,TrainCreditNote> userIdwithCreditNoteMap=new LinkedHashMap <>();
		if(walletHistoryList!=null && walletHistoryList.size()>0){
			for(WalletAmountTranferHistory walletAmountTranferHistory:walletHistoryList){
				walletUserIdMap.put(walletAmountTranferHistory.getUserId(), walletAmountTranferHistory);
			}
		}
		if(newCreditNoteList2!=null && newCreditNoteList2.size()>0){

			for(TrainCreditNote creditNote:newCreditNoteList2)
			{
				userIdwithConvenienceFeeMap.put(Integer.parseInt(creditNote.getUserId()), creditNote.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
				userIdwithCreditNoteMap.put(Integer.parseInt(creditNote.getUserId()), creditNote);
				if(!newTrainOrderRowObj.getCompanyId().equals(creditNote.getCompanyId())) 
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
			TrainCreditNote creditNote = userIdwithCreditNoteMap.get(userId);
			if(creditNote == null){
				throw new Exception("Credit Note not issued by parent");
			}

			if(creditNote!=null)
			{
				if(!creditNote.isCreditnoteIssued() && i==0)
				{
					logger.info("i-------------"+i);
					cancellationFee=creditNote.getCancellationFees().setScale(2,BigDecimal.ROUND_UP);
					convFee=creditNote.getConvenienceFees().setScale(2,BigDecimal.ROUND_UP);
					logger.info("compani is-------"+creditNote.getCompanyId()+"------"+creditNote.getRowId()+"cancellationFee----------"+cancellationFee);
					if(cancellationFee!=null){
						refundedAmount=actualAmount.subtract(gstAmountFee).subtract(cancellationFee).subtract(convFee).subtract(creditNote.getManagementFees());
						logger.info("refundedAmount--deduct cancellation fee-----"+refundedAmount);
					}
					i++;
				}

				if(Integer.parseInt(creditNote.getCompanyId())==loginCompanyObj.getCompanyid()){ 

					TrainCreditNote creditNoteUpdate=new TrainCreditNote();
					creditNoteUpdate.setRefundedAmount(refundedAmount);
					creditNoteUpdate.setId(creditNote.getId());
					creditNoteUpdate.setCreditnoteIssued(true);
					creditNoteUpdate.setTotalBookingAmount(actualAmount);
					creditNoteUpdate=creditNoteDao.updateRefundingAmount(creditNoteUpdate);
					new CompanyDAO().insertEmail(String.valueOf(creditNoteUpdate.getId()), 0, Email.EMAIL_TYPE_CREDITNOTE_CONFIRM_TRAIN);
					logger.info("refundedAmount--deduct ConvenienceFees fee-----"+refundedAmount);
					if(creditNoteUpdate.isOrderUpdated()){
						creditNoteIssuedRow= creditNoteDao.updateCreditNoteIssuedInTrainOrderRow(newTrainOrderRowObj.getId(), true);
						List<TrainCreditNote> newcreditNoteList= creditNoteDao.getCreditNoteListByOrderRowID(creditNoteIssuedRow.getId());
						if(newcreditNoteList!=null && newcreditNoteList.size()>0){
							for(TrainCreditNote creditNoteConvFee:newcreditNoteList)
							{
								logger.info("getCompanyId-----"+creditNoteConvFee.getCompanyId()+"getManagementFees---------"+creditNoteConvFee.getManagementFees());
								userIdwithConvenienceFeeMap.put(Integer.parseInt(creditNoteConvFee.getUserId()), creditNoteConvFee.getConvenienceFees().setScale(2, BigDecimal.ROUND_UP));
								userIdwithManagementFeeMap.put(Integer.parseInt(creditNoteConvFee.getUserId()), creditNoteConvFee.getManagementFees().setScale(2, BigDecimal.ROUND_UP));
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
						new OrderModifyDao().updateTrainOrderRowBookingStatusInfo(creditNoteIssuedRow);
						logger.info("--------------------UPADATING TRAIN ORDER ROW with true--------------------isCreditNoteIssued----------------------"+creditNoteIssuedRow.isCreditNoteIssued());
						if(creditNoteIssuedRow.isCreditNoteIssued()){
							if(!walletAmount.getTransactionType().equalsIgnoreCase("Card")) 
							bookingAmountAddingBackToWallet(parentUsersList, walletUserIdMap);
							CreditNoteUtility.getInstance().deductAndCollectExtraCharges(parentUsersList, walletUserIdMap, userIdwithManagementFeeMap, userIdwithConvenienceFeeMap, flightCreditNoteDao, cancellationFee, totManagementFee,gstAmountFee);
						}
					}

				}
				else
				{
					TrainCreditNote creditNoteUpdate=new TrainCreditNote();
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
					creditNoteUpdate.setTotalBookingAmount(actualAmount);
					creditNoteUpdate.setId(creditNote.getId());
					creditNoteUpdate.setCreditnoteIssued(true);
					creditNoteUpdate=creditNoteDao.updateRefundingAmount(creditNoteUpdate);
				}
			}
		}
	}


	private void buildCreditNoteData(BigDecimal totConvenienceFees ,BigDecimal totChildManageementFee, BigDecimal totCancellationFee , TrainCreditNote creditNoteObj, TrainOrderRow newTrainOrderRowObj,GstPropertiesFile gstPropertiesFile, CreditNoteData creditNoteData) {
		creditNoteData.setManagementFees(totChildManageementFee!=null ?totChildManageementFee.setScale(2, BigDecimal.ROUND_UP): new BigDecimal(0));
		creditNoteData.setConvenienceFees(totConvenienceFees!=null?totConvenienceFees.setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0));
		creditNoteData.setGstAmount(creditNoteObj.getGstAmount()!=null ?creditNoteObj.getGstAmount().setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0));
		creditNoteData.setCancellationFees(creditNoteObj.getCancellationFees()!=null?creditNoteObj.getCancellationFees().setScale(2, BigDecimal.ROUND_UP):new BigDecimal(0));
		creditNoteData.setDate(DateConversion.convertDateToStringToDateWithTIME(creditNoteObj.getOrderedAt()));
		creditNoteData.setStaff(creditNoteObj.getAlterBy());
		creditNoteData.setTaxInvoiceNo(newTrainOrderRowObj.getInvoiceNo());
		creditNoteData.setOrderId(newTrainOrderRowObj.getOrderId());
		creditNoteData.setTotalBookingAmount(creditNoteObj.getTotalBookingAmount().setScale(2, BigDecimal.ROUND_UP));

		creditNoteData.setLintasGstOn(gstPropertiesFile.getGstSwitchonValue());
		creditNoteData.setBaseCurrency(newTrainOrderRowObj.getBookingCurrency());
		creditNoteData.setBookedCurrency(newTrainOrderRowObj.getBookingCurrency());
		creditNoteData.setCreditNoteId(creditNoteObj.getId());
		creditNoteData.setCustomerName(newTrainOrderRowObj.getOrderCustomer().getFirstName()+""+newTrainOrderRowObj.getOrderCustomer().getLastName());
		creditNoteData.setCNINumber(creditNoteObj.getCNINumber());
		creditNoteData.setIssuedDate(DateConversion.convertTimestampToStringwithoutseconds(creditNoteObj.getIssuedAt()));
		creditNoteData.setTaxInvoiceDate(DateConversion.convertTimestampToStringwithoutseconds(newTrainOrderRowObj.getCreatedAt()));
		creditNoteData.setCompanyId(Integer.parseInt(creditNoteObj.getCompanyId()));
		creditNoteData.setCustomerAdress(newTrainOrderRowObj.getOrderCustomer().getAddress());

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
			if(walletHistory!=null && walletHistory.getAction().equals("Train Booking payment")){
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
						flightCreditNoteDao.updateWalletHistory("Train booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
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
				if(walletHistory!=null && walletHistory.getAction().equals("Train Booking payment")){
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
						flightCreditNoteDao.updateWalletHistory("Train booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
				else
					throw new Exception("Wallet Action is not matching");
			}
			if(agencyUserObj!=null && agencyUserObj.getAgentWallet()!=null){
				WalletAmountTranferHistory walletHistory=walletMap.get(disUserId)!=null?walletMap.get(disUserId):new WalletAmountTranferHistory();
				if(walletHistory!=null && walletHistory.getAction().equals("Train Booking payment")){
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
						flightCreditNoteDao.updateWalletHistory("Train booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
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
				if(walletHistory!=null && walletHistory.getAction().equals("Train Booking payment")){
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
						flightCreditNoteDao.updateWalletHistory("Flight booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
				else
					throw new Exception("Wallet Action is not matching");

			}
			if(disUserObj!=null && disUserObj.getAgentWallet()!=null){
				WalletAmountTranferHistory walletHistory=walletMap.get(disUserId)!=null?walletMap.get(disUserId):new WalletAmountTranferHistory();
				if(walletHistory!=null && walletHistory.getAction().equals("Train Booking payment")){
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
						flightCreditNoteDao.updateWalletHistory("Train booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
					}
				}
				else
					throw new Exception("Wallet Action is not matching");
			}
			if(agencyUserObj!=null && agencyUserObj.getAgentWallet()!=null){
				WalletAmountTranferHistory walletHistory=walletMap.get(agencyUserId)!=null?walletMap.get(agencyUserId):new WalletAmountTranferHistory();
				if(walletHistory!=null && walletHistory.getAction().equals("Train Booking payment")){
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
						flightCreditNoteDao.updateWalletHistory("Train booking amount collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
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
	public TrainCommissionReport getModel() {
		// TODO Auto-generated method stub
		return trainCommissionReport;
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



	public List<TrainCommissionReport> getAgentCreditNoteList() {
		return agentCreditNoteList;
	}



	public void setAgentCreditNoteList(List<TrainCommissionReport> agentCreditNoteList) {
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
