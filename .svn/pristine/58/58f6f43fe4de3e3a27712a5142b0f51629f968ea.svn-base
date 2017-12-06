package com.lintas.deals.action;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.dispatcher.multipart.UploadedFile;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.CrudOperationDeals;
import com.lintas.admin.model.User;
import com.lintas.deals.DAO.CrudOperationDealsDao;
import com.lintas.deals.DAO.CrudOperationDealsDaoImp;
import com.lintas.utility.InvoiceFilter;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.tayyarah.browsingHistory.json.action.HistoryManager;
import com.tayyarah.browsingHistory.model.ActionStatusEnum;
import com.tayyarah.browsingHistory.model.BrowsingHistoryDetailTypeEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionActionEnum;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.HistoryInfo;



public class DealsAction extends ActionSupport implements ModelDriven<CrudOperationDeals>,SessionAware
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(DealsAction.class);
	private CrudOperationDeals deals = new CrudOperationDeals();
	private List<CrudOperationDeals> dealList = new ArrayList<CrudOperationDeals>();
	private CrudOperationDealsDao dealsDAO = new CrudOperationDealsDaoImp();
	SessionMap<String, Object> sessionmap ;
	private InputStream inputStream;
	private String filterDealType;
	InvoiceFilter filterObj=new InvoiceFilter();
	private List<CrudOperationDeals> ListOfDeals;
	private CrudOperationDeals CurrentDeals;
	CountryDao cDAO = new CountryDao();
	private List<Country> CountryList ;
	@Override
	public CrudOperationDeals getModel() {
		// TODO Auto-generated method stub
		return deals;
	}
	/**
	 * To save or update Deals.
	 * @return String
	 */
	public String saveOrUpdate()
	{	
		User user=(User)sessionmap.get("User");
	CrudOperationDeals SavedDeal = dealsDAO.saveOrUpdateDeals(deals);
	if(SavedDeal!=null){
		SavedDeal.setImageUrl(uploadImageFile(SavedDeal.getId()));
		CrudOperationDeals updateDeal = dealsDAO.updatDealImagepath(SavedDeal);
		if(updateDeal!=null)	
			addActionMessage(getText("global.saveOrUpdatesuccess"));
		     // addActionMessage("Successfully Added CMS.");
	}
	else{
		addActionMessage(getText("global.saveOrUpdateerror"));
		//addActionMessage("Failed try again.");
		HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_ADD_DEALS.getId(), BrowsingOptionActionEnum.CMS_ADD_DEALS.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.ADDCMS.getId(), String.valueOf(user.getCompanyid()),"add Deals add cms click ");
		
		return ERROR;
	}
	HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_ADD_DEALS.getId(), BrowsingOptionActionEnum.CMS_ADD_DEALS.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.ADDCMS.getId(), String.valueOf(user.getCompanyid()),"add Deals add cms click ");
		return SUCCESS;
	}

	
	public String getCountryandlanguagelist()
	{
		User user=(User)sessionmap.get("User");
		CountryList  = cDAO.getCountryList();
		HistoryInfo historyInfo = (HistoryInfo) ((sessionmap.get("history")!=null)?sessionmap.get("history"):new HistoryInfo());  
		  historyInfo.changeNature(BrowsingOptionPageEnum.SETTINGS_CMS_ADD_DEALS, BrowsingOptionActionEnum.ACTION_DEFAULT, ActionStatusEnum.SUCCESS); 
		  new HistoryManager().inSertHistory(historyInfo);
		   historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.ACTION_ADDNEW.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.ADD_NEW.getId(), String.valueOf(user.getCompanyid()),"show list of deals addnew  click ");
			return SUCCESS;
	}

	
	/**
	 * To list all users.
	 * @return String
	 */
	public String listOfDeal()
	{
		ListOfDeals = dealsDAO.listOfDeals();
		logger.info("ListOfDeals"+ ListOfDeals.size());
   return SUCCESS;
	}

	/**
	 * To list a single user by Id.
	 * @return String
	 */
	
	public String editDeals()
	{		
		User user=(User)sessionmap.get("User");
		CurrentDeals = dealsDAO.DealsById(deals);
		logger.info("deal city"+CurrentDeals.getCity());
		getCountryandlanguagelist();
		 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.EDIT.getId(), String.valueOf(user.getCompanyid()),"show list of deals edit click ");
		 return SUCCESS;
	}


	public String UpdateDeals()
	{	
		User user=(User)sessionmap.get("User");
		//CurrentDeals = (CrudOperationDeals) sessionmap.get("EditdDeals");
		CurrentDeals = dealsDAO.DealsById(deals);
		deals.setId(CurrentDeals.getId());

		//logger.info("------------deal my city---------"+deals.getId());
		CrudOperationDeals updateDeals=dealsDAO.UpdateDeals(deals);
		updateDeals.setImageUrl(uploadImageFile(updateDeals.getId()));
		CrudOperationDeals updatedDeal = dealsDAO.updatDealImagepath(updateDeals);
		//logger.info("------------updateDeals my city---------"+updateDeals.getId());

		if(updatedDeal!=null)
		{
			addActionMessage(getText("global.UpdateDealssuccess"));
		//addActionMessage("Successfully updated.");
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.UPDATECMS.getId(), String.valueOf(user.getCompanyid()),"deals update updatecms click ");
				
		return SUCCESS;
		}
		else
		{
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.ACTION_UPDATE.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.UPDATECMS.getId(), String.valueOf(user.getCompanyid()),"deals update updatecms click ");
				
		return ERROR;
		} 
	}


	public String deleteDealList(){
		User user=(User)sessionmap.get("User");
		logger.info("--------Delete deleteCmsList....------"+deals.getId());

		// cm.setMarkupId(cm.getMarkupId());
		boolean isdelete= dealsDAO.deleteDeal(deals.getId());
		if(isdelete){
			//addActionMessage("Successfully Deleted User Details.."); 
			showSuccessMessage("deleted");
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.ACTION_DELETE.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.DELETE.getId(), String.valueOf(user.getCompanyid()),"show list of deals delete click ");
			 return SUCCESS;
			
		}
		else{
			showSuccessMessage("failed");
			//addActionMessage("Oops! Try Again.");
			 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.ACTION_DELETE.getId(), ActionStatusEnum.FAILED.getCode(),BrowsingHistoryDetailTypeEnum.DELETE.getId(), String.valueOf(user.getCompanyid()),"show list of deals delete click ");
				
			return ERROR;
		}
	}



	public String showCmsDetailsById(){

		/*CrudOperationDeals cmsProfile=(CrudOperationDeals) sessionmap.get("EditdDeals");
		deals.setId(cmsProfile.getId());*/
		logger.info("---------Shoe deals ID--------------"+deals.getId());
		CrudOperationDeals detailsObj=dealsDAO.GetCmsDetails(deals.getId());	

		sessionmap.put("CmsDetailsObj", detailsObj);
		logger.info("---------Show Deals image url path --------------"+detailsObj.getImageUrl());
		return SUCCESS; 

	}

	/* this method for set super user user  lock  */

	public String setCmsStatus(){
		User user=(User)sessionmap.get("User");

		logger.info("------------getCompanyid........"+deals.getId());
		logger.info("--------------getStatus........"+deals.isStatus());
		if(deals.isStatus()){
			deals.setStatus(false);
		}
		else{
			deals.setStatus(true);
		}

		CrudOperationDeals status = dealsDAO.updateCmsActiveOrInactive(deals);
		if(status!=null){
			if(status.isStatus()){
				addActionMessage(getText("global.setCmsStatussuccesspublished"));
				//addActionMessage("Successfully Published Deal.");
				 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.CMS_PUBLISH.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.CMS_ACTIVE.getId(), String.valueOf(user.getCompanyid()),"show list of deals publish active click ");
					
			}
			else{
				addActionMessage(getText("global.setCmsStatussuccessunpublished"));
				//addActionMessage("Successfully unpublished Deal.");
				 HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionmap, BrowsingOptionPageEnum.SETTINGS_CMS_SHOW_ALL_DEALS.getId(), BrowsingOptionActionEnum.CMS_PUBLISH.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.CMS_INACTIVE.getId(), String.valueOf(user.getCompanyid()),"show list of deals publish inactive click ");
					
			}
		}
		// HistoryInfo historyInfo = new HistoryManager().inSertHistoryAndDetail(sessionMap, BrowsingOptionPageEnum.SETTINGS_HOTELS_HOTEL_LIST.getId(), BrowsingOptionActionEnum.ACTION_EDIT.getId(), ActionStatusEnum.SUCCESS.getCode(),BrowsingHistoryDetailTypeEnum.EDIT.getId(), String.valueOf(user.getCompanyid()),"Hotel room list edit click ");
			
		return SUCCESS;
	}


	public String filteringDealsByDealType()
	{
		logger.info("---------filter deal type--------"+getFilterDealType());
		filterObj.setFilterDealType(getFilterDealType());
		ListOfDeals = dealsDAO.filterDelasByDealType(filterObj);
		
		return SUCCESS;
	}

	public String uploadImageFile(long id){
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

				for (UploadedFile cf : files) {
					logger.info("----------file length...-------"+cf.length());
					logger.info("file length..."+cf.length());
					fileName = localFileNames[0].substring(0,localFileNames[0].indexOf("."));
					fileType= localFileNames[0].substring(localFileNames[0].indexOf(".")+1);
					if(fileType.equals("jpg")||fileType.equals("jpeg") || fileType.equals("gif") || fileType.equals("png")){

						//String file_path = servletRequest.getSession().getServletContext().getRealPath("/admin_profile_pics" );
						String file_path = "/home/dealimages/";
						//String file_path = "D:\\vimaldealimage\\";
						////logger.info("----------Server path:...-------"+ file_path);
						//logger.info("Server path:" + file_path);
						File fileToCreate = new File(file_path, id+"."+fileType);
						try {
							if(cf!=null && cf.getContent()!=null)
							{
								File fi = (File) cf.getContent();

								FileUtils.copyFile(fi, fileToCreate);
							}						} catch (IOException e) {
							// TODO Auto-generated catch block
							logger.error("----------IOEXCEPTION-----------"+e.getMessage());
							//e.printStackTrace();
						}
						imageName =  id+"."+fileType;

					}
				}
			}
		}
		return imageName;

	}

	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public CrudOperationDeals getDeals() {
		return deals;
	}
	public void setDeals(CrudOperationDeals deals) {
		this.deals = deals;
	}
	public List<CrudOperationDeals> getDealList() {
		return dealList;
	}
	public void setDealList(List<CrudOperationDeals> dealList) {
		this.dealList = dealList;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionmap=(SessionMap<String, Object>) map;

	}
	public String getFilterDealType() {
		return filterDealType;
	}
	public void setFilterDealType(String filterDealType) {
		this.filterDealType = filterDealType;
	}
	public List<CrudOperationDeals> getListOfDeals() {
		return ListOfDeals;
	}
	public void setListOfDeals(List<CrudOperationDeals> listOfDeals) {
		ListOfDeals = listOfDeals;
	}
	public CrudOperationDeals getCurrentDeals() {
		return CurrentDeals;
	}
	public void setCurrentDeals(CrudOperationDeals currentDeals) {
		CurrentDeals = currentDeals;
	}
	public List<Country> getCountryList() {
		return CountryList;
	}
	public void setCountryList(List<Country> countryList) {
		CountryList = countryList;
	}
	
	 
	   
}
