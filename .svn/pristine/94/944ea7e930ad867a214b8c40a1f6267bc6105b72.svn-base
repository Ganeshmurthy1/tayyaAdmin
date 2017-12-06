package com.commission.sheet.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.email.notification.EmailNotification;
import com.email.notification.EmailNotificationDaoImp;
import com.isl.admin.commission.dao.AirlineCommissionBlockDaoImp;
import com.isl.admin.commission.dao.AirlineCommissionSheetDaoImp;
import com.isl.admin.commission.model.AirlineCommissionCompanyBlock;
import com.isl.admin.commission.model.AirlineCommissionMasterSheet;
import com.isl.admin.commission.model.CommissionActionStatus;
import com.isl.admin.commission.service.CommissionService;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SuperUserDealSheetAction extends ActionSupport implements ModelDriven<AirlineCommissionMasterSheet>,SessionAware {

	/**
	 * 
	 */
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(SuperUserDealSheetAction.class);
	
	AirlineCommissionMasterSheet superUserDealSheet=new AirlineCommissionMasterSheet();
	AirlineCommissionSheetDaoImp commissionSheetDao=null;
	AirlineCommissionBlockDaoImp commissionBlockDao=null;
	SessionMap<String, Object> sessionmap;
	private List<AirlineCommissionMasterSheet> sheetList=null;
	private String dealStatus;
	private String type;
	private AirlineCommissionMasterSheet dealSheet = null;
	EmailNotificationDaoImp emailNotificationDaoImp = new EmailNotificationDaoImp();

	private static final long serialVersionUID = 1L;

	public String createSuperUserDealSheet(){
		commissionSheetDao = new AirlineCommissionSheetDaoImp();
		if(commissionSheetDao.getSuperUserSheetList()!=null && commissionSheetDao.getSuperUserSheetList().size()>0 )
			superUserDealSheet.setActive(false);
		else
			superUserDealSheet.setActive(true);

		User sessionObj = (User) sessionmap.get("User");
		Company sessionCompanyObj = (Company) sessionmap.get("Company");


		superUserDealSheet.setCreatedByUserID(sessionObj.getId());
		CommissionActionStatus commissionActionStatus = null;

		// insert initial deal sheet from agents excel or pdf with airline list  : check if Sheet 0 exit
		if(!commissionSheetDao.isAnyItemInAirlineCommissionSheetRow(new Long("0")))
		{
			commissionActionStatus = commissionSheetDao.insertMasterSheetItems(sessionObj);	
		}

		// insert  master sheet deal sheet from agents excel or pdf with airline list  : check if Sheet 0 exit
		AirlineCommissionMasterSheet superUserSheetObj = commissionSheetDao.saveSuperUserSheetDetails(superUserDealSheet);
		logger.info("----superUserDealSheet.getId()----"+superUserDealSheet.getId());
		Long sheetId=null;
		sheetId=new Long(0);
		if(superUserSheetObj!=null){
			try
			{
				// create deal sheet from 0 sheet id , create airlineCommissionCompanyBlock , create airlineCommissionSheetNew , create airlineCommissionBlock for super user
				commissionActionStatus = commissionSheetDao.duplicateMasterSheetItems(sheetId, sessionCompanyObj.getCompanyid(), superUserSheetObj);

				// created to update existing configs only ---- nothing to do with if you keep creating dealsheet 
				if(commissionActionStatus!=null && commissionActionStatus.getStatus() == CommissionActionStatus.CODE_SUCCESS){
					CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(sessionCompanyObj);

					if(superUserSheetObj.isActive())
					{
						if(companyConfig != null && commissionActionStatus.getCompanyBlock()!=null)
						{
							companyConfig.setAppliedBlockId(commissionActionStatus.getCompanyBlock().getId());
							new CompanyConfigDao().updateCompanyconfig(companyConfig);

							//update new deal sheet  for all configurations in the system 
							new CommissionService().traverseAndChangeSheet(companyConfig, new Long(1), superUserSheetObj.getId(), null);
							emailNotificationDaoImp.insertEmailNotification(sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), -1, EmailNotification.ACTION_PARENT_HIMSELF, EmailNotification.EMAIL_TYPE_COMMISSION_SHEET_CREATION, String.valueOf(superUserSheetObj.getId()));
						}
					}
					addActionMessage(getText("global.add")); 
					return SUCCESS;
				}
				else
				{
					addActionError(getText("global.error")); 
					return SUCCESS;
				}
			}
			catch(Exception e)
			{
				addActionError(getText("global.error")); 
				return SUCCESS;
			}			

		}
		else{
			addActionError(getText("global.error")); 
			return SUCCESS;
		} 

	}
	public String getSuperUserDealSheet(){
		commissionSheetDao=new AirlineCommissionSheetDaoImp();
		sheetList=commissionSheetDao.getSuperUserSheetList();
		return SUCCESS;
	}

	public String createDuplicateDealSheet(){
		logger.info("type-------------"+type+"id-----------"+superUserDealSheet.getId());
		dealSheet=new AirlineCommissionSheetDaoImp().getDealSheetDetails(superUserDealSheet.getId());
		return SUCCESS;
	}

	public String getDealSheetProfile(){
		dealSheet=new AirlineCommissionSheetDaoImp().getDealSheetDetails(superUserDealSheet.getId());
		return SUCCESS;
	}


	public String  activateOrDeactivateSheet(){
		Company sessionCompanyObj = (Company) sessionmap.get("Company");
		commissionSheetDao = new AirlineCommissionSheetDaoImp();
		//toggle selection
		superUserDealSheet.setActive(!superUserDealSheet.isActive());		
		AirlineCommissionMasterSheet superUserSheetObj = commissionSheetDao.toggleActiveSheet(superUserDealSheet);
		if(superUserSheetObj.isActive())//for activating
		{
			logger.info("----superUserDealSheet.getId()----"+superUserDealSheet.getId());
			if(superUserSheetObj.getId()==superUserDealSheet.getId()){
				

				CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(sessionCompanyObj);
				if(companyConfig != null )
				{			
					AirlineCommissionCompanyBlock airlineCommissionCompanyBlock = new AirlineCommissionBlockDaoImp().getAirlineCommissionCompanyBlock(companyConfig.getAppliedBlockId());
					airlineCommissionCompanyBlock.setAppliedSheetId(superUserSheetObj.getId());
					new AirlineCommissionBlockDaoImp().updateAirlineCommissionCompanyBlock(airlineCommissionCompanyBlock);				
					new CommissionService().traverseAndChangeSheet(companyConfig, airlineCommissionCompanyBlock.getId(), superUserSheetObj.getId(), null);
					emailNotificationDaoImp.insertEmailNotification(sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), -1, EmailNotification.ACTION_PARENT_HIMSELF, EmailNotification.EMAIL_TYPE_COMMISSION_SHEET_MODIFICATION, String.valueOf(superUserSheetObj.getId()));
				}
				 
				//emailNotificationDaoImp.insertEmailNotification(sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), -1, EmailNotification.ACTION_PARENT_HIMSELF, EmailNotification.EMAIL_TYPE_COMMISSION_SHEET_MODIFICATION, String.valueOf(superUserSheetObj.getId()));
				addActionMessage(getText("global.update")); 
			}			
			else
				addActionError(getText("global.error")); 

		}
		
		
		return SUCCESS;

	}



	public String  updateDealSheetDetails(){

		Company sessionCompanyObj = (Company) sessionmap.get("Company");

		if(getDealStatus().equals("1")) 
			superUserDealSheet.setActive(true);
		else
			superUserDealSheet.setActive(false);

		commissionSheetDao = new AirlineCommissionSheetDaoImp();
		AirlineCommissionMasterSheet superUserSheetObj = commissionSheetDao.updateDealSheet(superUserDealSheet);
		logger.info("----superUserDealSheet.getId()----"+superUserDealSheet.getId());
		if(superUserSheetObj.getId()==superUserDealSheet.getId()){

			CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(sessionCompanyObj);
			if(companyConfig != null )
			{			
				AirlineCommissionCompanyBlock airlineCommissionCompanyBlock = new AirlineCommissionBlockDaoImp().getAirlineCommissionCompanyBlock(companyConfig.getAppliedBlockId());
				airlineCommissionCompanyBlock.setAppliedSheetId(superUserSheetObj.getId());



				//change other sheets status to inactive...


				//efere


				new AirlineCommissionBlockDaoImp().updateAirlineCommissionCompanyBlock(airlineCommissionCompanyBlock);				
				new CommissionService().traverseAndChangeSheet(companyConfig, airlineCommissionCompanyBlock.getId(), superUserSheetObj.getId(), null);
				emailNotificationDaoImp.insertEmailNotification(sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), -1, EmailNotification.ACTION_PARENT_HIMSELF, EmailNotification.EMAIL_TYPE_COMMISSION_SHEET_MODIFICATION, String.valueOf(superUserSheetObj.getId()));
			}
			//emailNotificationDaoImp.insertEmailNotification(sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), sessionCompanyObj.getCompanyid(), -1, EmailNotification.ACTION_PARENT_HIMSELF, EmailNotification.EMAIL_TYPE_COMMISSION_SHEET_MODIFICATION, String.valueOf(superUserSheetObj.getId()));
			addActionMessage(getText("global.update")); 
		}			
		else
			addActionError(getText("global.error")); 

		return SUCCESS;

	}


	/*----------------set deal status ---------------------- */
	public String setDealSheetandBlockStatus(){
		logger.info("deal sheet id----------"+superUserDealSheet.getId());

		if(superUserDealSheet.isActive()) {
			logger.info("deal sheet status- true---------"+superUserDealSheet.isActive());
			superUserDealSheet.setActive(false);
		}
		else{
			logger.info("deal sheet status- false---------"+superUserDealSheet.isActive());
			superUserDealSheet.setActive(true);
		}

		AirlineCommissionMasterSheet  status = new AirlineCommissionSheetDaoImp().updateDealSheetApproval(superUserDealSheet);
		if(status!=null){
			logger.info("deal sheet status- true---------"+status.isActive());
			logger.info("---deal sheet ---ID ------"+status.getId());
			if(status.isActive()){
				addActionMessage(getText("global.sheetandblockStatus"));
			}
			else{
				addActionMessage(getText("global.error"));
			}
		}

		return SUCCESS; 
	}


	@Override
	public AirlineCommissionMasterSheet getModel() {
		// TODO Auto-generated method stub
		return superUserDealSheet;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}

	public List<AirlineCommissionMasterSheet> getSheetList() {
		return sheetList;
	}

	public void setSheetList(List<AirlineCommissionMasterSheet> sheetList) {
		this.sheetList = sheetList;
	}
	public String getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}
	public AirlineCommissionMasterSheet getDealSheet() {
		return dealSheet;
	}
	public void setDealSheet(AirlineCommissionMasterSheet dealSheet) {
		this.dealSheet = dealSheet;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}





}
