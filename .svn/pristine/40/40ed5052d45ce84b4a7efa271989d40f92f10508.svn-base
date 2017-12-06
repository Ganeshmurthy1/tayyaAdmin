package com.admin.payment.recievable.action;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.payment.recievable.PaymentRecievable;
import com.admin.payment.recievable.dao.PaymentRecievableDao;
import com.admin.payment.recievable.dao.PaymentRecievableDaoImpl;
import com.isl.admin.filter.CompanyFilter;
import com.isl.admin.page.CompanyFilterPage;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.User;
import com.lintas.admin.model.WalletAmountTranferHistory;
import com.lintas.config.RandomConfigurationNumber;
import com.lintas.utility.CommonUtil;
import com.lintas.utility.DateConversion;
import com.lintas.utility.ReadExcelData;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PaymentAction extends ActionSupport implements SessionAware,ModelDriven<PaymentRecievable>{
	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PaymentAction.class);
	private static final long serialVersionUID = 1L;
	PaymentRecievable paymentRecievable=new PaymentRecievable();
	SessionMap<String, Object> sessionMap;
	private List<PaymentRecievable> paymentRecievableList=null;
	PaymentRecievableDao paymentRecievableDao=new PaymentRecievableDaoImpl();
	private  CompanyFilterPage companyFilterPage=new CompanyFilterPage();
	public String goPayment(){
		return SUCCESS;
	}
	public String AddPayment(){
		String filePath=getText("global.Payment_KnockOff_file_path");
		paymentRecievable.setCreatedAt(new Timestamp(new Date().getTime()));
		Company company=new CompanyDAO().getCompanyProfile(paymentRecievable.getCompanyId());
		User user= null;
		PaymentRecievable paymentRecievableNew=null;
		if(company!=null)
			user=new UserDAO().getUserPasswordForLock(company.getEmail());
		if(user!=null){
			paymentRecievable.setUserId(user.getId());
			if(!paymentRecievable.isKnockOff()){
				paymentRecievable.setReceivedDate(DateConversion.StringToDate(paymentRecievable.getTransReceivedDate()));
				paymentRecievable.setFromDate(DateConversion.StringToDate(paymentRecievable.getTransFromDt()));
				paymentRecievable.setToDate(DateConversion.StringToDate(paymentRecievable.getTransToDt()));	
				paymentRecievableNew =paymentRecievableDao.save(paymentRecievable);
				if(paymentRecievableNew!=null && paymentRecievableNew.getId()>0){
					paymentRecievableNew.setReferenceNumber(RandomConfigurationNumber.generatePaymentRef(paymentRecievableNew.getId(), paymentRecievable));
					paymentRecievableNew=paymentRecievableDao.updatePaymentRefNo(paymentRecievableNew);
					if(paymentRecievableNew!=null){
						new UserDAO().insertWalletAmountTransferHistory(insertWalletHistory(paymentRecievableNew, user));
					}
				}
				return SUCCESS;
			}
			else{
				paymentRecievableNew =paymentRecievableDao.save(paymentRecievable);
				if(paymentRecievableNew!=null && paymentRecievableNew.getId()>0)
					paymentRecievableNew.setFilePath(uploadPaymentFile(paymentRecievableNew.getId(),filePath));
				paymentRecievableNew=paymentRecievableDao.updatePaymentFile(paymentRecievableNew);
				if(paymentRecievableNew!=null && paymentRecievableNew.getFilePath()!=null){
					List<Object> invoiceList=ReadExcelData.readPaymentFile(new File(filePath+paymentRecievableNew.getFilePath()));
					updateAllServicesKnockOff(invoiceList);
					return SUCCESS;
				}
				else{
					addActionError("File is not found. Please check!");
					return ERROR; 
				}
			}
		}

		else{
			addActionError("We found some error.Please try again.");
			return ERROR; 
		}

	}
	public String paymentReceivableList(){
		Company companySessionObj=(Company)sessionMap.get("Company");
		User userSessionObj=(User)sessionMap.get("User");
		CompanyFilter companyFilter = companyFilterPage.getCompanyFilter();
		companyFilter.setLoginCompany(companySessionObj);
		companyFilter.setLoginUser(userSessionObj);
		companyFilterPage.setCompanyFilter(companyFilter);
		CompanyFilterPage newCompanyFilterPage=paymentRecievableDao.paymentReceivableList(companyFilterPage);
		if(newCompanyFilterPage!=null &&  newCompanyFilterPage.getPaymentReceivableList()!=null &&  newCompanyFilterPage.getPaymentReceivableList().size()>0) 
			setCompanyFilterPage(newCompanyFilterPage);
		return SUCCESS; 
	}

	public String getPaymentRecievableDetails(){
		paymentRecievable=paymentRecievableDao.getDetails(paymentRecievable.getId());
		return SUCCESS;
	}

	public String updatePaymentRecievable(){
		if(paymentRecievableDao.update(paymentRecievable)){
			addActionMessage("Successfully updated."); 
			return SUCCESS;
		}
		else{
			addActionError("We found some error.Please try again.");
			return ERROR; 
		}

	}

	//method for upload user  upload Passport Scan Copy  image file
	public String uploadPaymentFile(long id,String file_path){
		String imageName=null;
		if(ServletActionContext.getRequest() instanceof MultiPartRequestWrapper)
		{
			MultiPartRequestWrapper multiWrapper =   (MultiPartRequestWrapper) ServletActionContext.getRequest();
			//String[] fileParameterNames = multiWrapper.getFileNames("Imagepath");
			Enumeration<String> fileParameterNames = multiWrapper.getFileParameterNames();
			if(fileParameterNames.hasMoreElements()){
				String inputValue = (String) fileParameterNames.nextElement(); 
				String[] localFileNames = multiWrapper.getFileNames(inputValue);
				UploadedFile[] files = multiWrapper.getFiles(inputValue); 
				String fileName = "";
				String fileType = "";
				//.equalsIgnoreCase("jpg")||fileType.equalsIgnoreCase("jpeg") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("png")|| fileType.equalsIgnoreCase("csv")|| fileType.equalsIgnoreCase("xlsx")
				for (UploadedFile cf : files) {
					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
					if(fileType!=null){
						File fileToCreate = new File(file_path, fileName+"_"+id+"."+fileType);
						try {
							if(cf!=null && cf.getContent()!=null)
							{
								File fi = (File) cf.getContent();

								FileUtils.copyFile(fi, fileToCreate);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							logger.error("----------IOEXCEPTION-----------"+e.getMessage());
							//e.printStackTrace();
						}
						imageName =  fileName+"_"+id+"."+fileType;

					}
				}
			}
		}
		return imageName;

	}

	public boolean  updateAllServicesKnockOff(List<Object> invoiceList){
		boolean isError=false;
		for (Object object : invoiceList) {
			String invoiceNo=(String) object;
			switch(CommonUtil.findStringFromString(invoiceNo)){
			case "TYA":
				paymentRecievableDao.updateAirKnockOff(CommonUtil.findDigitsFromString(invoiceNo),true);
				break;
			case "TYB":
				paymentRecievableDao.updateBusKnockOff(CommonUtil.findDigitsFromString(invoiceNo),true);
				break;
			case "TYC":
				paymentRecievableDao.updateCarKnockOff(CommonUtil.findDigitsFromString(invoiceNo),true);
				break;
			case "TYH":
				paymentRecievableDao.updateHotelKnockOff(CommonUtil.findDigitsFromString(invoiceNo),true);
				break;
			case "TYI":
				paymentRecievableDao.updateInsuranceKnockOff(CommonUtil.findDigitsFromString(invoiceNo),true);
				break;
			case "TYT":
				paymentRecievableDao.updateTrainKnockOff(CommonUtil.findDigitsFromString(invoiceNo),true);
				break;
			case "TYV":
				paymentRecievableDao.updateVisaKnockOff(CommonUtil.findDigitsFromString(invoiceNo),true);
				break;
			case "TYM":
				paymentRecievableDao.updateMiscellaneousKnockOff(CommonUtil.findDigitsFromString(invoiceNo),true);
				break;
			default:
				isError=true;
				break;
			}
		}
		return isError;

	}
	public WalletAmountTranferHistory  insertWalletHistory(PaymentRecievable paymentRecievable,User user){
		WalletAmountTranferHistory walletAmountTranferHistory=new WalletAmountTranferHistory();
		String action="";
		switch (paymentRecievable.getPaymentType()) {
		case "Debit":
			action="Payment received";
			break;
		case "Credit":
			action="Payment given";
			break;
		default:
			break;
		}
		walletAmountTranferHistory.setAction(action);
		walletAmountTranferHistory.setActionId(paymentRecievable.getReferenceNumber());
		walletAmountTranferHistory.setAmount(paymentRecievable.getAmount());
		walletAmountTranferHistory.setClosingBalance(new BigDecimal("0.00"));
		walletAmountTranferHistory.setOpeningBalance(new BigDecimal("0.00"));
		walletAmountTranferHistory.setParentOpeningBalance(new BigDecimal("0.00"));
		walletAmountTranferHistory.setParentClosingBalance(new BigDecimal("0.00"));
		walletAmountTranferHistory.setCompanyId(user.getCompanyid());
		walletAmountTranferHistory.setCreatedAt(new Timestamp(new Date().getTime()));
		walletAmountTranferHistory.setCurrency("INR");
		walletAmountTranferHistory.setInvoiceNo(paymentRecievable.getReferenceNumber());
		walletAmountTranferHistory.setParentUserId(0);
		walletAmountTranferHistory.setRemarks(action);
		walletAmountTranferHistory.setTransactionType(paymentRecievable.getPaymentType());
		walletAmountTranferHistory.setUserId(user.getId());
		walletAmountTranferHistory.setWalletId(user.getAgentWallet().getWalletId());
		return walletAmountTranferHistory;

	}


	public PaymentRecievable getPaymentRecievable() {
		return paymentRecievable;
	}
	public void setPaymentRecievable(PaymentRecievable paymentRecievable) {
		this.paymentRecievable = paymentRecievable;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}

	@Override
	public PaymentRecievable getModel() {
		// TODO Auto-generated method stub
		return paymentRecievable;
	}
	public List<PaymentRecievable> getPaymentRecievableList() {
		return paymentRecievableList;
	}
	public void setPaymentRecievableList(List<PaymentRecievable> paymentRecievableList) {
		this.paymentRecievableList = paymentRecievableList;
	}
	public CompanyFilterPage getCompanyFilterPage() {
		return companyFilterPage;
	}
	public void setCompanyFilterPage(CompanyFilterPage companyFilterPage) {
		this.companyFilterPage = companyFilterPage;
	}


}
