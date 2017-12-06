package com.lintas.action.CreditNote.modal;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;

import com.lintas.action.CreditNote.Dao.CreditNoteDao;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.admin.model.WalletAmountTranferHistory;

public class CreditNoteUtility {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CreditNoteUtility.class);
	private static CreditNoteUtility creditNoteUtility=null;
	private CreditNoteUtility(){
	}

	public static CreditNoteUtility getInstance(){
		if(creditNoteUtility==null){
			creditNoteUtility=new CreditNoteUtility();
		}
		return creditNoteUtility;

	}

	public void bookingAmountAddingBackToWallet(List<Integer> parentUserIds,LinkedHashMap<Integer,WalletAmountTranferHistory> walletMap, CreditNoteDao flightCreditNoteDao,String travelType) throws Exception{
		if(parentUserIds.size()==1) 
			superUserBlockToRefundBookingAmount(parentUserIds, walletMap,flightCreditNoteDao,travelType);
		if(parentUserIds.size()==2){ 
			superUserBlockToRefundBookingAmount(parentUserIds, walletMap,flightCreditNoteDao,travelType);
			agencyOrCorporateBlockToRefundBookingAmount(parentUserIds, walletMap,flightCreditNoteDao,travelType);
		}
		if(parentUserIds.size()==3){
			superUserBlockToRefundBookingAmount(parentUserIds, walletMap,flightCreditNoteDao,travelType);
			distributorBlockToRefundBookingAmount(parentUserIds, walletMap,flightCreditNoteDao,travelType);
			agencyOrCorporateBlockToRefundBookingAmount(parentUserIds, walletMap,flightCreditNoteDao,travelType);
		}
	}

	public void superUserBlockToRefundBookingAmount(List<Integer> parentUserIds,LinkedHashMap<Integer,WalletAmountTranferHistory> walletMap,CreditNoteDao flightCreditNoteDao,String travelType) throws Exception{
		int superUserId=parentUserIds.get(0);
		User superUserObj=flightCreditNoteDao.getParentAddress(superUserId);
		WalletAmountTranferHistory walletHistory=walletMap.get(superUserId)!=null?walletMap.get(superUserId):new WalletAmountTranferHistory();
		if(walletHistory!=null && walletHistory.getAction().equals(travelType)){
			if(superUserObj!=null && superUserObj.getAgentWallet()!=null){
				UserWallet fbInitWallet=superUserObj.getAgentWallet();
				BigDecimal userOpeningBalance=fbInitWallet.getWalletbalance();
				BigDecimal  closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
				fbInitWallet.setWalletbalance(closingBalance);
				UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
				if(newUserWalletObj!=null){
					flightCreditNoteDao.updateWalletHistory(travelType+" "+"collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
				}
			}
		}
		else
			throw new Exception("Walle taction is not matching for super user block");

	}
	public void distributorBlockToRefundBookingAmount(List<Integer> parentUserIds,LinkedHashMap<Integer,WalletAmountTranferHistory> walletMap,CreditNoteDao flightCreditNoteDao,String travelType) throws Exception{
		int disUserId=parentUserIds.get(1);
		User disUserObj=new CreditNoteDao().getParentAddress(disUserId);
		if(disUserObj!=null && disUserObj.getAgentWallet()!=null){
			WalletAmountTranferHistory walletHistory=walletMap.get(disUserId)!=null?walletMap.get(disUserId):new WalletAmountTranferHistory();
			if(walletHistory!=null && walletHistory.getAction().equals(travelType)){
				UserWallet fbInitWallet=disUserObj.getAgentWallet();
				BigDecimal userOpeningBalance=fbInitWallet.getWalletbalance();
				BigDecimal  closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
				fbInitWallet.setWalletbalance(closingBalance);
				UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
				if(newUserWalletObj!=null){
					flightCreditNoteDao.updateWalletHistory(travelType+" "+"collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
				}
			}
			else
				throw new Exception("Wallet Action is not matching for distributor block");
		}
	}

	public void agencyOrCorporateBlockToRefundBookingAmount(List<Integer> parentUserIds,LinkedHashMap<Integer,WalletAmountTranferHistory> walletMap,CreditNoteDao flightCreditNoteDao,String travelType) throws Exception{
		int agencyUserId=parentUserIds.get(2);
		User agencyUserObj=flightCreditNoteDao.getParentAddress(agencyUserId);
		if(agencyUserObj!=null && agencyUserObj.getAgentWallet()!=null){
			WalletAmountTranferHistory walletHistory=walletMap.get(agencyUserId)!=null?walletMap.get(agencyUserId):new WalletAmountTranferHistory();
			if(walletHistory!=null && walletHistory.getAction().equals(travelType)){
				UserWallet fbInitWallet=agencyUserObj.getAgentWallet();
				BigDecimal userOpeningBalance=fbInitWallet.getWalletbalance();
				BigDecimal  closingBalance =fbInitWallet.getWalletbalance().add(walletHistory.getAmount());
				fbInitWallet.setWalletbalance(closingBalance);
				UserWallet newUserWalletObj=flightCreditNoteDao.updateUserWallet(fbInitWallet);
				if(newUserWalletObj!=null){
					flightCreditNoteDao.updateWalletHistory(travelType+" "+"collected",userOpeningBalance,closingBalance,walletHistory.getAmount(),walletHistory);
				}
			}
			else
				throw new Exception("Wallet Action is not matching for agency or corporate block");
		}
	}

	public void deductAndCollectExtraCharges(List<Integer> parentUserIds,LinkedHashMap<Integer,WalletAmountTranferHistory> walletUserIdMap,SortedMap<Integer,BigDecimal> userIdwithManagementFeeMap,SortedMap<Integer,BigDecimal> userIdwithConvenienceFeeMap,CreditNoteDao flightCreditNoteDao,BigDecimal cancellationFee,BigDecimal totManagementFee,BigDecimal gstAmountFee){
		WalletAmountTranferHistory walletHistory= null;
		if(parentUserIds.size()==2){
			int superUserId=parentUserIds.get(0);
			int disUserId=parentUserIds.get(1);
			if(userIdwithManagementFeeMap.containsKey(disUserId)){
				logger.info("AGENCY BLOCK STARTED");
				/*if(walletUserIdMap.containsKey(disUserId)){*/
				WalletAmountTranferHistory walletAmountTranferHistory=walletUserIdMap.get(disUserId);
				if(walletAmountTranferHistory!=null){
					walletHistory=walletAmountTranferHistory;
					UserWallet userWallet =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
					BigDecimal totwithCancellationFee=null;
					BigDecimal userOpeningBalance=null;
					if(cancellationFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							userOpeningBalance=userWallet.getDepositBalance();
							totwithCancellationFee=userOpeningBalance.subtract(cancellationFee);
							userWallet.setDepositBalance(totwithCancellationFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							userOpeningBalance=new BigDecimal(0);
							totwithCancellationFee=userOpeningBalance;

						}
						else{
							userOpeningBalance=userWallet.getWalletbalance();
							totwithCancellationFee =userOpeningBalance.subtract(cancellationFee);
							userWallet.setWalletbalance(totwithCancellationFee);
						}
						userWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(userWallet);

						walletHistory.setUserId(disUserId);
						walletHistory.setWalletId(userWallet.getWalletId());
						if(newUserWalletObj!=null) 
							flightCreditNoteDao.updateWalletHistory("CancellationFee deducted",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);
						else
							flightCreditNoteDao.updateWalletHistory("CancellationFee deducted",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);


					}

					UserWallet  deductConvFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
					BigDecimal openingBalance=null; 
					BigDecimal totwithConvFee=null;
					if(totManagementFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							openingBalance=deductConvFeeUserWallet.getDepositBalance();
							totwithConvFee=openingBalance.subtract(totManagementFee);
							deductConvFeeUserWallet.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							openingBalance=new BigDecimal(0);
							totwithConvFee=openingBalance;
						}
						else{
							openingBalance=deductConvFeeUserWallet.getWalletbalance();
							totwithConvFee=openingBalance.subtract(totManagementFee);
							deductConvFeeUserWallet.setWalletbalance(totwithConvFee);
						}
						deductConvFeeUserWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(deductConvFeeUserWallet);

						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("Total ManagementFee deducted",openingBalance,totwithConvFee,totManagementFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("Total ManagementFee deducted",openingBalance,totwithConvFee,totManagementFee,walletHistory);

					}

					BigDecimal conOpeningBalance=null; 
					BigDecimal superUserConvenienceFee=userIdwithConvenienceFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
					UserWallet addConvFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
					if(superUserConvenienceFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							conOpeningBalance=addConvFeeUserWallet.getDepositBalance();
							totwithConvFee=conOpeningBalance.subtract(superUserConvenienceFee);
							addConvFeeUserWallet.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							conOpeningBalance=new BigDecimal(0);
							totwithConvFee=conOpeningBalance;
						}
						else{
							conOpeningBalance=addConvFeeUserWallet.getWalletbalance();
							totwithConvFee=conOpeningBalance.subtract(superUserConvenienceFee);
							addConvFeeUserWallet.setWalletbalance(totwithConvFee);
						}
						addConvFeeUserWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(addConvFeeUserWallet);

						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee deducted",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee deducted",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);

					}
 
					if(gstAmountFee!=null && gstAmountFee.compareTo(new BigDecimal(0))>0){
						UserWallet deductGstWallet =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								conOpeningBalance=deductGstWallet.getDepositBalance();
								totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
								deductGstWallet.setDepositBalance(totwithConvFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								conOpeningBalance=new BigDecimal(0);
								totwithConvFee=conOpeningBalance;
							}
							else{
								conOpeningBalance=deductGstWallet.getWalletbalance();
								totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
								deductGstWallet.setWalletbalance(totwithConvFee);
							}
							deductGstWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(deductGstWallet);

							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
							}
							else
								flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
						 
					}
					 

				}
				logger.info("AGENCY BLOCK ENDED");
			}
			if(userIdwithManagementFeeMap.containsKey(superUserId)){
				logger.info("SUPER AGENCY BLOCK STARTED");
				WalletAmountTranferHistory   walletAmountTranferHistory=walletUserIdMap.get(superUserId);
				if(walletAmountTranferHistory!=null){
					walletHistory=walletAmountTranferHistory;
					UserWallet userWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
					BigDecimal userOpeningBalance=null;
					BigDecimal totwithCancellationFee= null;
					if(cancellationFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							userOpeningBalance=userWallet.getDepositBalance();
							totwithCancellationFee=userOpeningBalance.add(cancellationFee);
							userWallet.setDepositBalance(totwithCancellationFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							userOpeningBalance=new BigDecimal(0);
							totwithCancellationFee=userOpeningBalance;
						}
						else{
							userOpeningBalance=userWallet.getWalletbalance();
							totwithCancellationFee=userOpeningBalance.add(cancellationFee);
							userWallet.setWalletbalance(totwithCancellationFee);
						}
						userWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(userWallet);

						walletHistory.setUserId(superUserId);
						walletHistory.setWalletId(userWallet.getWalletId());
						walletHistory.setParentUserId(0);

						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("CancellationFee collected",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("CancellationFee collected",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);

					}
					BigDecimal superUserManagementFee=userIdwithManagementFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
					UserWallet  addManagementFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
					BigDecimal conOpeningBalance=null;
					BigDecimal totwithConvFee=null;
					if(superUserManagementFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							conOpeningBalance=addManagementFeeUserWallet.getDepositBalance();
							totwithConvFee=conOpeningBalance.add(superUserManagementFee);
							addManagementFeeUserWallet.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							conOpeningBalance=new BigDecimal(0);
							totwithConvFee=conOpeningBalance;
						}
						else{
							conOpeningBalance=addManagementFeeUserWallet.getWalletbalance();
							totwithConvFee=conOpeningBalance.add(superUserManagementFee);
							addManagementFeeUserWallet.setWalletbalance(totwithConvFee);
						}
						addManagementFeeUserWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(addManagementFeeUserWallet);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("ManagementFee collected",conOpeningBalance,totwithConvFee,superUserManagementFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("ManagementFee collected",conOpeningBalance,totwithConvFee,superUserManagementFee,walletHistory);

					}
					BigDecimal superUserConvenienceFee=userIdwithConvenienceFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
					UserWallet addConvFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
					if(superUserConvenienceFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							conOpeningBalance=addConvFeeUserWallet.getDepositBalance();
							totwithConvFee=conOpeningBalance.add(superUserConvenienceFee);
							addConvFeeUserWallet.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							conOpeningBalance=new BigDecimal(0);
							totwithConvFee=conOpeningBalance;
						}
						else{
							conOpeningBalance=addConvFeeUserWallet.getWalletbalance();
							totwithConvFee=conOpeningBalance.add(superUserConvenienceFee);
							addConvFeeUserWallet.setWalletbalance(totwithConvFee);
						}
						addConvFeeUserWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(addConvFeeUserWallet);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee collected",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee collected",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);

					}
					
					if(gstAmountFee!=null && gstAmountFee.compareTo(new BigDecimal(0))>0){
						UserWallet deductGstWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								conOpeningBalance=deductGstWallet.getDepositBalance();
								totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
								deductGstWallet.setDepositBalance(totwithConvFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								conOpeningBalance=new BigDecimal(0);
								totwithConvFee=conOpeningBalance;
							}
							else{
								conOpeningBalance=deductGstWallet.getWalletbalance();
								totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
								deductGstWallet.setWalletbalance(totwithConvFee);
							}
							deductGstWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(deductGstWallet);

							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
							}
							else
								flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
						 
					}
					 
				}

			}
			logger.info("SUPER AGENCY BLOCK ENDED");
		}

		if(parentUserIds.size()==3){
			int superUserId=parentUserIds.get(0);
			int disUserId=parentUserIds.get(1);
			int agencyUserId=parentUserIds.get(2);
			if(userIdwithManagementFeeMap.containsKey(agencyUserId)){
				if(walletUserIdMap.containsKey(agencyUserId)){
					WalletAmountTranferHistory walletAmountTranferHistory=walletUserIdMap.get(agencyUserId);
					if(walletAmountTranferHistory!=null){
						walletHistory=walletAmountTranferHistory;
						UserWallet userWallet =flightCreditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						BigDecimal userOpeningBalance=null;
						BigDecimal totwithCancellationFee=null;
						if(cancellationFee!=null){
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								userOpeningBalance=userWallet.getDepositBalance();
								totwithCancellationFee=userOpeningBalance.subtract(cancellationFee);
								userWallet.setDepositBalance(totwithCancellationFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								userOpeningBalance=new BigDecimal(0);
								totwithCancellationFee=userOpeningBalance;
							}
							else{
								userOpeningBalance=userWallet.getWalletbalance();
								totwithCancellationFee =userOpeningBalance.subtract(cancellationFee);
								userWallet.setWalletbalance(totwithCancellationFee);
							}
							userWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(userWallet);

							walletHistory.setUserId(disUserId);
							walletHistory.setWalletId (newUserWalletObj.getWalletId());
							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("CancellationFee deducted",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);
							}
						}
						UserWallet  deductConvFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						BigDecimal openingBalance= null;
						BigDecimal totwithConvFee= null;
						if(totManagementFee!=null){
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								openingBalance=deductConvFeeUserWallet.getDepositBalance();
								totwithConvFee=openingBalance.subtract(totManagementFee);
								deductConvFeeUserWallet.setDepositBalance(totwithConvFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								openingBalance=new BigDecimal(0);
								totwithConvFee=openingBalance;
							}
							else{
								openingBalance=deductConvFeeUserWallet.getWalletbalance();
								totwithConvFee=openingBalance.subtract(totManagementFee);
								deductConvFeeUserWallet.setWalletbalance(totwithConvFee);
							}
							deductConvFeeUserWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(deductConvFeeUserWallet);
							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("Total ManagementFee deducted",openingBalance,totwithConvFee,totManagementFee,walletHistory);
							}
							else
								flightCreditNoteDao.updateWalletHistory("Total ManagementFee deducted",openingBalance,totwithConvFee,totManagementFee,walletHistory);

						}
						BigDecimal conOpeningBalance=null;//addManagementFeeUserWallet.getWalletbalance();
						BigDecimal superUserConvenienceFee=userIdwithConvenienceFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
						UserWallet  addConFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						if(superUserConvenienceFee!=null){
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								conOpeningBalance=addConFeeUserWallet.getDepositBalance();
								totwithConvFee=conOpeningBalance.subtract(superUserConvenienceFee);
								addConFeeUserWallet.setDepositBalance(totwithConvFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								conOpeningBalance=new BigDecimal(0);
								totwithConvFee=conOpeningBalance;
							}
							else{
								conOpeningBalance=addConFeeUserWallet.getWalletbalance();
								totwithConvFee=conOpeningBalance.subtract(superUserConvenienceFee);
								addConFeeUserWallet.setWalletbalance(totwithConvFee);
							}
							addConFeeUserWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(addConFeeUserWallet);
							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("ConvenienceFee deducted",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);
							}
							else
								flightCreditNoteDao.updateWalletHistory("ConvenienceFee deducted",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);

						}
						if(gstAmountFee!=null && gstAmountFee.compareTo(new BigDecimal(0))>0){
							UserWallet deductGstWallet =flightCreditNoteDao.getWalletAmountByWalletId(agencyUserId);
								if(walletHistory.getTransactionType().equals("Withdrawal")){
									conOpeningBalance=deductGstWallet.getDepositBalance();
									totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
									deductGstWallet.setDepositBalance(totwithConvFee);
								}
								else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
									conOpeningBalance=new BigDecimal(0);
									totwithConvFee=conOpeningBalance;
								}
								else{
									conOpeningBalance=deductGstWallet.getWalletbalance();
									totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
									deductGstWallet.setWalletbalance(totwithConvFee);
								}
								deductGstWallet.setTxType(walletHistory.getTransactionType());
								UserWallet newUserWalletObj=null;
								if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
									newUserWalletObj=flightCreditNoteDao.updateUserWallet(deductGstWallet);

								if(newUserWalletObj!=null){
									flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
								}
								else
									flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
							 
						}
						 
					}
				}
			}

			if(userIdwithManagementFeeMap.containsKey(disUserId)){
				/*if(walletUserIdMap.containsKey(disUserId)){*/
				WalletAmountTranferHistory  walletAmountTranferHistory=walletUserIdMap.get(disUserId);
				if(walletAmountTranferHistory!=null){
					walletHistory=walletAmountTranferHistory;
					UserWallet userWallet =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
					BigDecimal userOpeningBalance=null;
					BigDecimal totwithCancellationFee=null;
					if(cancellationFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							userOpeningBalance=userWallet.getDepositBalance();
							totwithCancellationFee=userOpeningBalance.add(cancellationFee);
							userWallet.setDepositBalance(totwithCancellationFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							userOpeningBalance=new BigDecimal(0);
							totwithCancellationFee=userOpeningBalance;
						}
						else{
							userOpeningBalance=userWallet.getWalletbalance();
							totwithCancellationFee=userOpeningBalance.add(cancellationFee);
							userWallet.setWalletbalance(totwithCancellationFee);
						}
						userWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(userWallet);

						walletHistory.setUserId(disUserId);
						walletHistory.setWalletId(newUserWalletObj.getWalletId());
						walletHistory.setParentUserId(0);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("CancellationFee collected",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);
						}

						UserWallet cancelltionDeductuserWallet =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
						BigDecimal userOpeningBalance1= null;
						BigDecimal totwithCancellationFee1=null;
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							userOpeningBalance1=cancelltionDeductuserWallet.getDepositBalance();
							totwithCancellationFee1=userOpeningBalance1.subtract(cancellationFee);
							cancelltionDeductuserWallet.setDepositBalance(totwithCancellationFee1);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							userOpeningBalance1=new BigDecimal(0);
							totwithCancellationFee1=userOpeningBalance1;
						}
						else{
							userOpeningBalance1=cancelltionDeductuserWallet.getWalletbalance();
							totwithCancellationFee1=userOpeningBalance1.subtract(cancellationFee);
							cancelltionDeductuserWallet.setWalletbalance(totwithCancellationFee1);
						}
						cancelltionDeductuserWallet.setTxType(walletHistory.getTransactionType());
						UserWallet updatedUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							updatedUserWalletObj=flightCreditNoteDao.updateUserWallet(cancelltionDeductuserWallet);

						if(updatedUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("CancellationFee deducted",userOpeningBalance1,totwithCancellationFee1,cancellationFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("CancellationFee deducted",userOpeningBalance1,totwithCancellationFee1,cancellationFee,walletHistory);

					}

					BigDecimal disManagementFee=userIdwithManagementFeeMap.get(disUserId).setScale(2, BigDecimal.ROUND_UP);
					logger.info("disManagementFee-------------"+disManagementFee);
					UserWallet  addConvFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
					BigDecimal conOpeningBalance=null;
					BigDecimal totwithConvFee=null;
					if(disManagementFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							conOpeningBalance=addConvFeeUserWallet.getDepositBalance();
							totwithConvFee=conOpeningBalance.add(disManagementFee);
							addConvFeeUserWallet.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							conOpeningBalance=new BigDecimal(0);
							totwithConvFee=conOpeningBalance;
						}
						else{
							conOpeningBalance=addConvFeeUserWallet.getWalletbalance();
							totwithConvFee=conOpeningBalance.add(disManagementFee);
							addConvFeeUserWallet.setWalletbalance(totwithConvFee);
						}
						addConvFeeUserWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(addConvFeeUserWallet);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("ManagementFee collected",conOpeningBalance,totwithConvFee,disManagementFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("ManagementFee collected",conOpeningBalance,totwithConvFee,disManagementFee,walletHistory);

					}

					BigDecimal agencyConFeeCollect=userIdwithConvenienceFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
					logger.info("disConFee-------------"+agencyConFeeCollect);
					UserWallet  addConFeeUserWalletCollect =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
					if(agencyConFeeCollect!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							conOpeningBalance=addConFeeUserWalletCollect.getDepositBalance();
							totwithConvFee=conOpeningBalance.add(agencyConFeeCollect);
							addConFeeUserWalletCollect.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							conOpeningBalance=new BigDecimal(0);
							totwithConvFee=conOpeningBalance;
						}
						else{
							conOpeningBalance=addConFeeUserWalletCollect.getWalletbalance();
							totwithConvFee=conOpeningBalance.add(agencyConFeeCollect);
							addConFeeUserWalletCollect.setWalletbalance(totwithConvFee);
						}
						addConFeeUserWalletCollect.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(addConFeeUserWalletCollect);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee collected",conOpeningBalance,totwithConvFee,agencyConFeeCollect,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee collected",conOpeningBalance,totwithConvFee,agencyConFeeCollect,walletHistory);


					}

					BigDecimal agencyConFeeDeduct=userIdwithConvenienceFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
					logger.info("disConFeeDeduct-------------"+agencyConFeeDeduct);
					UserWallet  addConFeeUserWalletDeduct =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
					if(agencyConFeeDeduct!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							conOpeningBalance=addConFeeUserWalletDeduct.getDepositBalance();
							totwithConvFee=conOpeningBalance.subtract(agencyConFeeDeduct);
							addConFeeUserWalletDeduct.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							conOpeningBalance=new BigDecimal(0);
							totwithConvFee=conOpeningBalance;
						}
						else{
							conOpeningBalance=addConFeeUserWalletDeduct.getWalletbalance();
							totwithConvFee=conOpeningBalance.subtract(agencyConFeeDeduct);
							addConFeeUserWalletDeduct.setWalletbalance(totwithConvFee);
						}
						addConFeeUserWalletDeduct.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(addConFeeUserWalletDeduct);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee deducted",conOpeningBalance,totwithConvFee,agencyConFeeDeduct,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee deducted",conOpeningBalance,totwithConvFee,agencyConFeeDeduct,walletHistory);

					}	
					
					if(gstAmountFee!=null && gstAmountFee.compareTo(new BigDecimal(0))>0){
						UserWallet deductGstWallet =flightCreditNoteDao.getWalletAmountByWalletId(disUserId);
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								conOpeningBalance=deductGstWallet.getDepositBalance();
								totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
								deductGstWallet.setDepositBalance(totwithConvFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								conOpeningBalance=new BigDecimal(0);
								totwithConvFee=conOpeningBalance;
							}
							else{
								conOpeningBalance=deductGstWallet.getWalletbalance();
								totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
								deductGstWallet.setWalletbalance(totwithConvFee);
							}
							deductGstWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(deductGstWallet);

							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
							}
							else
								flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
						 
					}
				}
				/*}*/
			}
			if(userIdwithManagementFeeMap.containsKey(superUserId)){
				WalletAmountTranferHistory   walletAmountTranferHistory=walletUserIdMap.get(superUserId);
				if(walletAmountTranferHistory!=null){
					walletHistory=walletAmountTranferHistory;
					UserWallet userWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
					BigDecimal userOpeningBalance=null;
					BigDecimal totwithCancellationFee= null;
					if(cancellationFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							userOpeningBalance=userWallet.getDepositBalance();
							totwithCancellationFee=userOpeningBalance.add(cancellationFee);
							userWallet.setDepositBalance(totwithCancellationFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							userOpeningBalance=new BigDecimal(0);
							totwithCancellationFee=userOpeningBalance;
						}
						else{
							userOpeningBalance=userWallet.getWalletbalance();
							totwithCancellationFee=userOpeningBalance.add(cancellationFee);
							userWallet.setWalletbalance(totwithCancellationFee);
						}
						userWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(userWallet);
						walletHistory.setUserId(superUserId);
						walletHistory.setWalletId(userWallet.getWalletId());
						walletHistory.setParentUserId(0);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("CancellationFee collected",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("CancellationFee collected",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);

					}
					BigDecimal superUserManagementFee=userIdwithManagementFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
					UserWallet  addManagementFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
					BigDecimal conOpeningBalance=null;
					BigDecimal totwithConvFee=null;
					if(superUserManagementFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							conOpeningBalance=addManagementFeeUserWallet.getDepositBalance();
							totwithConvFee=conOpeningBalance.add(superUserManagementFee);
							addManagementFeeUserWallet.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							conOpeningBalance=new BigDecimal(0);
							totwithConvFee=conOpeningBalance;
						}
						else{
							conOpeningBalance=addManagementFeeUserWallet.getWalletbalance();
							totwithConvFee=conOpeningBalance.add(superUserManagementFee);
							addManagementFeeUserWallet.setWalletbalance(totwithConvFee);
						}
						addManagementFeeUserWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(addManagementFeeUserWallet);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("ManagementFee collected",conOpeningBalance,totwithConvFee,superUserManagementFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("ManagementFee collected",conOpeningBalance,totwithConvFee,superUserManagementFee,walletHistory);


					}
					BigDecimal superUserConvenienceFee=userIdwithConvenienceFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
					UserWallet addConvFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
					if(superUserConvenienceFee!=null){
						if(walletHistory.getTransactionType().equals("Withdrawal")){
							conOpeningBalance=addConvFeeUserWallet.getDepositBalance();
							totwithConvFee=conOpeningBalance.add(superUserConvenienceFee);
							addConvFeeUserWallet.setDepositBalance(totwithConvFee);
						}
						else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
							conOpeningBalance=new BigDecimal(0);
							totwithConvFee=conOpeningBalance;
						}
						else{
							conOpeningBalance=addConvFeeUserWallet.getWalletbalance();
							totwithConvFee=conOpeningBalance.add(superUserConvenienceFee);
							addConvFeeUserWallet.setWalletbalance(totwithConvFee);
						}
						addConvFeeUserWallet.setTxType(walletHistory.getTransactionType());
						UserWallet newUserWalletObj=null;
						if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
							newUserWalletObj=flightCreditNoteDao.updateUserWallet(addConvFeeUserWallet);
						if(newUserWalletObj!=null){
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee collected",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);
						}
						else
							flightCreditNoteDao.updateWalletHistory("ConvenienceFee collected",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);

					}
					
					if(gstAmountFee!=null && gstAmountFee.compareTo(new BigDecimal(0))>0){
						UserWallet deductGstWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								conOpeningBalance=deductGstWallet.getDepositBalance();
								totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
								deductGstWallet.setDepositBalance(totwithConvFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								conOpeningBalance=new BigDecimal(0);
								totwithConvFee=conOpeningBalance;
							}
							else{
								conOpeningBalance=deductGstWallet.getWalletbalance();
								totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
								deductGstWallet.setWalletbalance(totwithConvFee);
							}
							deductGstWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(deductGstWallet);

							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
							}
							else
								flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
						 
					}
					 
				}

			}
		} 
		if(parentUserIds.size()==1){
			logger.info("CALLED SUPER USER DEDUCTIONS METHOD-------------");
			int superUserId=parentUserIds.get(0);
			if(userIdwithManagementFeeMap.containsKey(superUserId)){
				if(walletUserIdMap.containsKey(superUserId)){
					WalletAmountTranferHistory walletAmountTranferHistory=walletUserIdMap.get(superUserId);
					if(walletAmountTranferHistory!=null){
						walletHistory=walletAmountTranferHistory;
						UserWallet userWallet =flightCreditNoteDao.getWalletAmountByWalletId(walletAmountTranferHistory);
						BigDecimal userOpeningBalance=null;
						BigDecimal totwithCancellationFee=null;
						if(cancellationFee!=null){
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								userOpeningBalance=userWallet.getDepositBalance();
								totwithCancellationFee=userOpeningBalance.subtract(cancellationFee);
								userWallet.setDepositBalance(totwithCancellationFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								userOpeningBalance=new BigDecimal(0);
								totwithCancellationFee=userOpeningBalance;
							}
							else{
								userOpeningBalance=userWallet.getWalletbalance();
								totwithCancellationFee =userOpeningBalance.subtract(cancellationFee);
								userWallet.setWalletbalance(totwithCancellationFee);
							}
							userWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(userWallet);
							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("CancellationFee deducted",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);
							}
							else
								flightCreditNoteDao.updateWalletHistory("CancellationFee deducted",userOpeningBalance,totwithCancellationFee,cancellationFee,walletHistory);


						}
						BigDecimal conOpeningBalance= null;
						BigDecimal totwithConvFee=null;
						BigDecimal superUserConvenienceFee=userIdwithConvenienceFeeMap.get(superUserId).setScale(2, BigDecimal.ROUND_UP);
						UserWallet addConvFeeUserWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
						if(superUserConvenienceFee!=null){
							if(walletHistory.getTransactionType().equals("Withdrawal")){
								conOpeningBalance=addConvFeeUserWallet.getDepositBalance();
								totwithConvFee=conOpeningBalance.subtract(superUserConvenienceFee);
								addConvFeeUserWallet.setDepositBalance(totwithConvFee);
							}
							else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
								conOpeningBalance=new BigDecimal(0);
								totwithConvFee=conOpeningBalance;
							}
							else{
								conOpeningBalance=addConvFeeUserWallet.getWalletbalance();
								totwithConvFee=conOpeningBalance.subtract(superUserConvenienceFee);
								addConvFeeUserWallet.setWalletbalance(totwithConvFee);
							}
							addConvFeeUserWallet.setTxType(walletHistory.getTransactionType());
							UserWallet newUserWalletObj=null;
							if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
								newUserWalletObj=flightCreditNoteDao.updateUserWallet(addConvFeeUserWallet);
							if(newUserWalletObj!=null){
								flightCreditNoteDao.updateWalletHistory("ConvenienceFee deducted",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);
							}
							else
								flightCreditNoteDao.updateWalletHistory("ConvenienceFee deducted",conOpeningBalance,totwithConvFee,superUserConvenienceFee,walletHistory);

						}
						
						if(gstAmountFee!=null && gstAmountFee.compareTo(new BigDecimal(0))>0){
							UserWallet deductGstWallet =flightCreditNoteDao.getWalletAmountByWalletId(superUserId);
								if(walletHistory.getTransactionType().equals("Withdrawal")){
									conOpeningBalance=deductGstWallet.getDepositBalance();
									totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
									deductGstWallet.setDepositBalance(totwithConvFee);
								}
								else if(walletHistory.getTransactionType().equalsIgnoreCase("Card")){
									conOpeningBalance=new BigDecimal(0);
									totwithConvFee=conOpeningBalance;
								}
								else{
									conOpeningBalance=deductGstWallet.getWalletbalance();
									totwithConvFee=conOpeningBalance.subtract(gstAmountFee);
									deductGstWallet.setWalletbalance(totwithConvFee);
								}
								deductGstWallet.setTxType(walletHistory.getTransactionType());
								UserWallet newUserWalletObj=null;
								if(!walletHistory.getTransactionType().equalsIgnoreCase("Card")) 
									newUserWalletObj=flightCreditNoteDao.updateUserWallet(deductGstWallet);

								if(newUserWalletObj!=null){
									flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
								}
								else
									flightCreditNoteDao.updateWalletHistory("Gst deducted",conOpeningBalance,totwithConvFee,gstAmountFee,walletHistory);
							 
						}
						 
					}
				} 
			}
		} 
	}


}
