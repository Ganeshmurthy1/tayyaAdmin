package com.commission.sheet.action;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.isl.admin.commission.service.CommissionService;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;

public class CompanyAirlineCommissionBlockSheetAction  extends ActionSupport  implements ModelDriven<AirlineCommissionCompanyBlock>,SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AirlineCommissionCompanyBlock airlineCommissionBlockSheet=new AirlineCommissionCompanyBlock();
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(CompanyAirlineCommissionBlockSheetAction.class);
	private SessionMap<String, Object> sessionmap;
	private AirlineCommissionBlockDaoImp commissionBlockDaoImp=null;
	private AirlineCommissionSheetDaoImp airlineCommissionSheetDaoImp=null;
	private List<AirlineCommissionCompanyBlock> blockList;
	private List<AirlineCommissionMasterSheet> sheetList;
	private String dealStatus;
	private int companyConfigId;
	private String type;
	private AirlineCommissionCompanyBlock commissionBlockSheet=null;
	private CommissionService commissionService = new CommissionService();
	private EmailNotificationDaoImp emailNotificationDaoImp = new EmailNotificationDaoImp();
	public String addChildCommissionBlock(){
		List<AirlineCommissionMasterSheet>  list=new AirlineCommissionSheetDaoImp().getSuperUserSheetList();
		List<AirlineCommissionMasterSheet> activeSheetList= new ArrayList<>();
		if(list!=null && list.size()>0){
			for(AirlineCommissionMasterSheet commissionMasterSheet:list){
				if(commissionMasterSheet.isActive()){
					activeSheetList.add(commissionMasterSheet);
				}
			}
		}
		sheetList=activeSheetList;		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.ADD_AFFILIATE_DEAL_SHEET, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);  

		return SUCCESS;
	}

	public String createNewCompanyCommissionBlock(){
		User sessionObj=(User) sessionmap.get("User");
		AirlineCommissionCompanyBlock commissionBlockSheet=null;
		commissionBlockDaoImp=new AirlineCommissionBlockDaoImp();
		CompanyConfigDao configDao=new CompanyConfigDao();
		Company currentcompany = (Company) sessionmap.get("Company");
		CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(currentcompany);
		if(companyConfig != null && companyConfig.isSheetMode())
		{
			logger.info("companyConfig-----------------------------------------"+companyConfig);
			logger.info("companyConfig.isSheetMode()-----------------------------------------"+companyConfig.isSheetMode());
			logger.info("getId-------------"+sessionObj.getId());
			if(getDealStatus()!=null && getDealStatus().equals("1")) 
				airlineCommissionBlockSheet.setActive(true);
			else
				airlineCommissionBlockSheet.setActive(false);

			airlineCommissionBlockSheet.setCreatedByUserID(sessionObj.getId());
			airlineCommissionBlockSheet.setCreatedByCompanyID(sessionObj.getCompanyid());
			//Long blockId = configDao.getCommissionBlockId(sessionObj.getCompanyid());

			logger.info("blockId----"+companyConfig.getAppliedBlockId());
			if(companyConfig.getAppliedBlockId()!=null){
				commissionBlockSheet = commissionBlockDaoImp.getAirlineCommissionCompanyBlock(companyConfig.getAppliedBlockId());
				if(commissionBlockSheet!=null){
					logger.info("commissionBlockSheet----"+commissionBlockSheet.getAppliedSheetId());
					airlineCommissionBlockSheet.setAppliedSheetId(commissionBlockSheet.getAppliedSheetId());
				}
			}
			try{
				AirlineCommissionCompanyBlock updayedcommissionBlockObj = commissionBlockDaoImp.createCompanyCommissionBlock(airlineCommissionBlockSheet);
				if(updayedcommissionBlockObj!=null && updayedcommissionBlockObj.getId()>0){
					emailNotificationDaoImp.insertEmailNotification(currentcompany.getCompanyid(), currentcompany.getCompanyid(), currentcompany.getCompanyid(), -1, EmailNotification.ACTION_PARENT_HIMSELF, EmailNotification.EMAIL_TYPE_COMMISSION_BLOCK_CREATION, String.valueOf(currentcompany.getCompanyid()));

					addActionMessage(getText("global.add")); 
					return SUCCESS;
				}
				else{
					addActionError(getText("global.error")); 
					return SUCCESS;
				}
			}
			catch(Exception e){
				logger.error(e);
				addActionError(getText("global.error")); 
				return SUCCESS;
			}
		}
		else{
			addActionError(getText("global.noConfigwithDealsheetError")); 
			return SUCCESS;
		}

	}
	public String getChildCommissionBlockList(){
		commissionBlockDaoImp=new AirlineCommissionBlockDaoImp();
		airlineCommissionSheetDaoImp = new AirlineCommissionSheetDaoImp();
		User sessionObj=(User) sessionmap.get("User");
		Company companySessionObj=(Company) sessionmap.get("Company");
		CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(companySessionObj);
		logger.info("companyConfig-----------------------------------------"+companyConfig);
		if(companyConfig != null && companyConfig.isSheetMode())
		{
			logger.info("companyConfig.isSheetMode()-----------------------------------------"+companyConfig.isSheetMode());
			companyConfigId=companyConfig.getConfig_id();
			blockList = commissionBlockDaoImp.getChildrenCompanyCommissionBlocks(false, sessionObj.getCompanyid());
			List<AirlineCommissionCompanyBlock> airlineCommissionCompanyBlockList = new ArrayList<AirlineCommissionCompanyBlock>();
			if(blockList!=null)
			{					
				for (AirlineCommissionCompanyBlock airlineCommissionCompanyBlock : blockList) {
					AirlineCommissionMasterSheet superUserAirlineCommissionSheet = airlineCommissionSheetDaoImp.getSuperUserAirlineCommissionSheet(airlineCommissionCompanyBlock.getAppliedSheetId());
					if(superUserAirlineCommissionSheet != null)
						airlineCommissionCompanyBlock.setSheetInfo(superUserAirlineCommissionSheet.getName());

					airlineCommissionCompanyBlockList.add(airlineCommissionCompanyBlock);
				}
				blockList = airlineCommissionCompanyBlockList;
			}
		}
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.AFFILIATE_DEAL_SHEET_LIST, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo); 
		return SUCCESS; 
	}

	public String getMyCommissionBlockList(){
		commissionBlockDaoImp=new AirlineCommissionBlockDaoImp();
		airlineCommissionSheetDaoImp = new AirlineCommissionSheetDaoImp();
		Company companySessionObj=(Company) sessionmap.get("Company");
		CompanyConfig companyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(companySessionObj);
		logger.info("companyConfig-----------------------------------------"+companyConfig);
		List<AirlineCommissionCompanyBlock> myBlockList=new ArrayList<AirlineCommissionCompanyBlock>();

		if(companyConfig != null && companyConfig.isSheetMode())
		{
			logger.info("companyConfig.isSheetMode()-----------------------------------------"+companyConfig.isSheetMode());
			companyConfigId=companyConfig.getConfig_id();
			AirlineCommissionCompanyBlock airlineCommissionCompanyBlock = commissionBlockDaoImp.getAirlineCommissionCompanyBlock(companyConfig.getAppliedBlockId());
			if(airlineCommissionCompanyBlock != null)
			{
				AirlineCommissionMasterSheet superUserAirlineCommissionSheet = airlineCommissionSheetDaoImp.getSuperUserAirlineCommissionSheet(airlineCommissionCompanyBlock.getAppliedSheetId());
				if(superUserAirlineCommissionSheet != null)
					airlineCommissionCompanyBlock.setSheetInfo(superUserAirlineCommissionSheet.getName());
				myBlockList.add(airlineCommissionCompanyBlock);
			}

			blockList=myBlockList;


		}
		
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());		
		historyInfo.changeNature(BrowsingOptionPageEnum.MY_DEAL_SHEET, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS);	
		new HistoryManager().inSertHistory(historyInfo);
		return SUCCESS;


	}
	public String getCompanyBlockProfile(){
		commissionBlockSheet=new AirlineCommissionBlockDaoImp().getAirlineCommissionCompanyBlock(airlineCommissionBlockSheet.getId());
		List<AirlineCommissionMasterSheet>  list=new AirlineCommissionSheetDaoImp().getSuperUserSheetList();
		List<AirlineCommissionMasterSheet> activeSheetList= new ArrayList<>();
		if(list!=null && list.size()>0){
			for(AirlineCommissionMasterSheet commissionMasterSheet:list){
				if(commissionMasterSheet.isActive()){
					activeSheetList.add(commissionMasterSheet);
				}
			}
		}
		sheetList=activeSheetList;
		return SUCCESS;
	}
	public String  updateCompanyBlockSheet(){
		if(getDealStatus().equals("1")) 
			airlineCommissionBlockSheet.setActive(true);
		else
			airlineCommissionBlockSheet.setActive(false);

		commissionBlockDaoImp = new AirlineCommissionBlockDaoImp();

		Company companySessionObj=(Company) sessionmap.get("Company");
		CompanyConfig superCompanyConfig = new CompanyConfigDao().getLastUpdatedCompanyConfigDetails(companySessionObj);
		///if it s sheet change by super user....
		AirlineCommissionCompanyBlock airlineCommissionCompanyBlockOld = commissionBlockDaoImp.getAirlineCommissionCompanyBlock(airlineCommissionBlockSheet.getId());
		AirlineCommissionCompanyBlock superUserSheetObj = commissionBlockDaoImp.updateAirlineCommissionCompanyBlock(airlineCommissionBlockSheet);
		if(airlineCommissionCompanyBlockOld.getAppliedSheetId() != airlineCommissionBlockSheet.getAppliedSheetId())
		{
			HashMap<Long, Boolean> resultMap = commissionService.traverseAndChangeSheet(superCompanyConfig, airlineCommissionBlockSheet.getId(), airlineCommissionBlockSheet.getAppliedSheetId(), null);
			logger.info("----Sheet change has been applied to all sub levels---result map...-"+resultMap);
			logger.info("----Sheet change has been applied to all sub levels---result map size...-"+resultMap.size());

		}
		emailNotificationDaoImp.insertEmailNotification(superCompanyConfig.getCompany_id(), superCompanyConfig.getCompany_id(), superCompanyConfig.getCompany_id(), -1, EmailNotification.ACTION_PARENT_TO_CHILD, EmailNotification.EMAIL_TYPE_COMMISSION_BLOCK_MODIFICATION, String.valueOf(airlineCommissionBlockSheet.getId()));
		logger.info("----superUserDealSheet.getId()----"+airlineCommissionBlockSheet.getId());
		if(superUserSheetObj.getId()==superUserSheetObj.getId()) 
			addActionMessage(getText("global.update")); 
		else
			addActionError(getText("global.error")); 

		return SUCCESS;

	}
	public String createDuplicateCompanyCommissionBlock(){
		User sessionObj=(User) sessionmap.get("User");
		commissionBlockDaoImp=new AirlineCommissionBlockDaoImp();
		logger.info("BlockId-----------------------------------------"+airlineCommissionBlockSheet.getId());
		logger.info("AppliedSheetId-----------------------------------------"+airlineCommissionBlockSheet.getAppliedSheetId());
		airlineCommissionBlockSheet.setCreatedByUserID(sessionObj.getId());
		airlineCommissionBlockSheet.setCreatedByCompanyID(sessionObj.getCompanyid());
		try{
			AirlineCommissionCompanyBlock updatedcommissionBlockObj = commissionBlockDaoImp.createDuplicateCompanyCommissionBlock(airlineCommissionBlockSheet);
			if(updatedcommissionBlockObj!=null &&(updatedcommissionBlockObj.getAppliedSheetId()==airlineCommissionBlockSheet.getAppliedSheetId())){
				addActionMessage(getText("global.blockDuplicate")); 
			}
			else{
				addActionMessage(getText("global.error")); 
			}
		}
		catch(Exception e){
			logger.error(e);
			addActionError(getText("global.error")); 
			return SUCCESS;
		}
		return SUCCESS;
	}

	/*----------------set deal status ---------------------- */
	public String setCommissionBlockStatus(){
		logger.info("block id----------"+airlineCommissionBlockSheet.getId());

		if(airlineCommissionBlockSheet.isActive()) {
			logger.info("block status- true---------"+airlineCommissionBlockSheet.isActive());
			airlineCommissionBlockSheet.setActive(false);
		}
		else{
			logger.info("block  status- false---------"+airlineCommissionBlockSheet.isActive());
			airlineCommissionBlockSheet.setActive(true);
		}
		AirlineCommissionCompanyBlock  status = new AirlineCommissionBlockDaoImp().updateBlockApproval(airlineCommissionBlockSheet);
		if(status!=null){
			logger.info("deal sheet status- true---------"+status.isActive());
			logger.info("---deal sheet ---ID ------"+status.getId());
			if(status.isActive()){
				addActionMessage(getText("global.sheetandblockStatus"));
			}
			else{
				addActionMessage(getText("global.sheetandblockStatusDeactive"));
			}
		}
		else{
			addActionError(getText("global.error"));
		}
		return SUCCESS; 
	}


	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;
	}

	public String getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}
	@Override
	public AirlineCommissionCompanyBlock getModel() {
		// TODO Auto-generated method stub
		return airlineCommissionBlockSheet;
	}

	public List<AirlineCommissionCompanyBlock> getBlockList() {
		return blockList;
	}

	public void setBlockList(List<AirlineCommissionCompanyBlock> blockList) {
		this.blockList = blockList;
	}

	public int getCompanyConfigId() {
		return companyConfigId;
	}

	public void setCompanyConfigId(int companyConfigId) {
		this.companyConfigId = companyConfigId;
	}

	public AirlineCommissionCompanyBlock getCommissionBlockSheet() {
		return commissionBlockSheet;
	}

	public void setCommissionBlockSheet(AirlineCommissionCompanyBlock commissionBlockSheet) {
		this.commissionBlockSheet = commissionBlockSheet;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AirlineCommissionMasterSheet> getSheetList() {
		return sheetList;
	}

	public void setSheetList(List<AirlineCommissionMasterSheet> sheetList) {
		this.sheetList = sheetList;
	}



}
