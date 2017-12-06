package com.lintas.admin.json;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.isl.admin.filter.dao.EmployeeFilterDao;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.CountryDao;
import com.lintas.admin.DAO.HotelMarkupDao;
import com.lintas.admin.DAO.MarkupDao;
import com.lintas.admin.DAO.UserDAO;
import com.lintas.admin.model.Airlinelist;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.Country;
import com.lintas.admin.model.FlightMarkup;
import com.lintas.admin.model.HotelMarkup;
import com.lintas.admin.model.User;
import com.lintas.admin.model.UserWallet;
import com.lintas.utility.DateConversion;
import com.opensymphony.xwork2.ActionSupport;
import com.tayyarah.browsingHistory.model.BrowsingOptionPageEnum;
import com.tayyarah.browsingHistory.model.EnumUtility;

public class AutocompleteSearchAction  extends ActionSupport implements SessionAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(AutocompleteSearchAction.class);
	CompanyDAO ComregDAO = new CompanyDAO();
	CountryDao countryDao = new CountryDao();
	UserDAO userDao = new UserDAO();
	CompanyConfigDao companyConfigDao = new CompanyConfigDao();
	EmployeeFilterDao employeeFilterDao=new EmployeeFilterDao();
	MarkupDao markupDao = new MarkupDao();
	HotelMarkupDao  hotelMarkupDao =new HotelMarkupDao ();
	SessionMap<String, Object> sessionMap;
	private List<Company> records=new ArrayList<Company>();
	private List<User> user_records=new ArrayList<User>();
	private List<User> agentList=new ArrayList<User>();
	private List<String> hotelnameslist=new ArrayList<>();
	private List<String>  hotelCitylist =new ArrayList<>();
	private List<Country> CountryList ;
	private List<Company> Allcompanylist;
	private List<Company> directCompanyList=new ArrayList<Company>();
	private List<Company> WalletUsersList;
	private List<Airlinelist> airlist=new ArrayList<Airlinelist>();
	private Map<String,String> currencyMap=new LinkedHashMap<String,String>();
	private  Map<String,String> airlinesmap=new LinkedHashMap<String,String>();
	//private List<Airlinelist> airlineMap=new ArrayList<>();
	private List<UserWallet> walletRecord=new LinkedList<UserWallet>();
	private String result;
	private String  parent_company_user_id;
	private String email;
	private String user_id;
	private String company_user_id;
	private String user_companyUserId;
	private String user_company_email;
	private String country;
	private String city;
	private List<User> usersList=null;
	private List<FlightMarkup> flightmarkupList;
	private List<HotelMarkup> AllHotelMatrkup;
	private boolean isCompanyExist;
	private String  myConfigCompanyUserId;
	private Map<String,Integer> childConfigCountMap=new HashMap<>();
	private String createdByCompanyId;
	private String configType;
	private List<CompanyConfig> companyConfigList;
	private int createdByUserId;
	//private List<User>  salesPersonRecords=new ArrayList<User>();
	private List<BrowsingOptionPageEnum> browsingOptionPageEnumList;
	private List<EnumUtility> pageNameList;
	public String userNames(){
		List<User> salesPersonList=ComregDAO.getPaymentUserList();
		if(salesPersonList!=null && salesPersonList.size()>0)
			setUsersList(salesPersonList);
		return SUCCESS;
	}
	public String  SuperUserCompanyListForJson(){
		List<Company> companyList=ComregDAO.getCompanyListByComapnyUserId(getParent_company_user_id(),getEmail());
		if(companyList!=null && companyList.size()>0)
		{
			for(Company comapany:companyList){
				if(comapany.getCompanyRole().isCorporate() || comapany.getCompanyRole().isSuperUser())
					records.add(comapany);
				
			}
			//records.addAll(companyList);
		} 
		return SUCCESS;
	}

	public String  SuperUserAgentListForJson(){
		List<User> usersList=null;
		Company companyObj=ComregDAO.getCompanyDetailsByUserid(getParent_company_user_id(),getEmail());
		User userObj=ComregDAO.getUserDetailsByUserid(getParent_company_user_id(), getEmail());
		if(companyObj!=null && userObj!=null){
			usersList=userDao.getAllAgentsByCompanyId(userObj,companyObj);
		}
		if(usersList!=null && usersList.size()>0)
		{
			for(User userNew:usersList){
				if(!userNew.getUserrole_id().isSuperuser() && !userNew.getUserrole_id().isUsermode()){
					agentList.add(userNew);
				}
			}
		} 

		return SUCCESS;
	}

	public String   fetchAllUserIdsUnderSuperUserForJson(){
		Company companyObj=ComregDAO.getCompanyDetailsByUserid(getParent_company_user_id(),getEmail());
		User userObj=ComregDAO.getUserDetailsByUserid(getParent_company_user_id(), getEmail());
		if(companyObj!=null && userObj!=null){
			usersList=userDao.getAllAgentsByCompanyId(userObj,companyObj);
		}
		return SUCCESS;
	}

	public String fetchCompanyConfigNamesForJson(){
		Company companyObj=ComregDAO.getCompanyDetailsByUserid(getParent_company_user_id(),getEmail());
		User userObj=ComregDAO.getUserDetailsByUserid(getParent_company_user_id(), getEmail());
		List<User> userIds=userDao.getAllAgentsByCompanyId(userObj,companyObj);
		List<Integer> userIdList=new ArrayList<>();
		if(userIds!=null && userIds.size()>0){
			for(User user:userIds){
				userIdList.add(user.getCompanyid());

			}
		}
		if(userIdList!=null && userIdList.size()>0){
			companyConfigList=userDao.getCompanyConfigNamesByCompanyId(userIdList);

		}
		return SUCCESS;
	}


	public String filterUserListByCompanyUserIdForJson(){

		String[] parts = getCompany_user_id().split(",");
		if(parts!=null && parts.length>1)
		{
			List<User> userList=ComregDAO.getUserListByCompanyId(parts[1]);

			if(userList!=null && userList.size()>0)
			{
				user_records.addAll(userList);

			} 
		}
		return SUCCESS;
	}

	public String filterCompanyListByCompanyUserId(){
		if(getUser_id().equals("")){
			setUser_id("all");
		}
		if(getCompany_user_id().equals("")){
			setCompany_user_id("all");
		}
		List<Company> companyList=null;
		String[] userParts=null;
		if(getUser_id().contains(",") && getCompany_user_id().contains(",")){
			userParts = getUser_id().split(",");
			companyList=ComregDAO.getCompanyistByUserId(userParts[1]);
		}

		else if(getUser_id().contains(",") && !getCompany_user_id().contains(",") ){
			userParts = getUser_id().split(",");
			companyList=ComregDAO.getCompanyistByUserId(userParts[1]);
		}
		else if(getCompany_user_id().contains(",") && !getUser_id().contains(",")){
			String[] companyParts = getCompany_user_id().split(",");
			companyList=ComregDAO.getCompanyListByCompanyUserId(companyParts[1]);

		}
		else{
			companyList=ComregDAO.getCompanyListByCompanyUserId(getUser_companyUserId());
		}

		if(companyList!=null && companyList.size()>0){
			Allcompanylist=companyList;
		}
		return SUCCESS;
	}


	public String filterAgentListByCompanyUserId(){

		if(getUser_id().equals("") || getUser_id()==null ){
			setUser_id("all");
		}
		if(getCompany_user_id().equals("") || getCompany_user_id()==null){
			setCompany_user_id("all");
		}
		String[] userParts=null;
		List<User> userList=null;
		if(getUser_id().contains(",") && getCompany_user_id().contains(",")){
			userParts = getUser_id().split(",");
			userList=ComregDAO.getAgentListByUserId(userParts[1]);
		}
		else if(getCompany_user_id().contains(",") && !getUser_id().contains(",")){
			String[] companyParts = getCompany_user_id().split(",");
			userList=ComregDAO.getAllAgentsByUserId(companyParts[1]);

		}
		else if(!getCompany_user_id().contains(",") && getUser_id().contains(",")){
			userParts = getUser_id().split(",");
			userList=ComregDAO.getAgentListByUserId(userParts[1]);
		}
		else{
			userList=ComregDAO.getAllAgentsByUserId(getUser_companyUserId());
		}

		if(userList!=null && userList.size()>0){
			setUsersList(userList);
		}

		return SUCCESS;
	}


	public String filterAgentWalletListByCompanyUserId(){


		if(getUser_id().equals("")){
			setUser_id("all");
		}
		if(getCompany_user_id().equals("")){
			setCompany_user_id("all");
		}
		List<User> walletList=null;
		if(getUser_id().contains(",") && getCompany_user_id().contains(",")){
			String[] userParts = getUser_id().split(",");
			walletList=ComregDAO.getWalletListByUserId(userParts[1]);
		}

		else if(!getCompany_user_id().contains(",") &&  getUser_id().contains(",")){
			String[] userParts = getUser_id().split(",");
			walletList=ComregDAO.getWalletListByUserId(userParts[1]);

		}
		else if(getCompany_user_id().contains(",") &&  !getUser_id().contains(",")){
			String[] companyParts = getCompany_user_id().split(",");
			walletList=ComregDAO.getWalletListByCompanyUserId(companyParts[1]);

		}
		else{
			walletList=ComregDAO.getWalletListByCompanyUserId(getUser_companyUserId());
		}

		if(walletList!=null && walletList.size()>0){

			if(sessionMap.get("WalletUsersList")!=null){	
				sessionMap.remove("WalletUsersList");
			} 
			sessionMap.put("WalletUsersList",walletList);
		}
		else{
			sessionMap.remove("WalletUsersList");
		}

		return SUCCESS;
	}

	public String filterCompanyConfigListtByCompanyUserId(){
		if(getUser_id().equals("")){
			setUser_id("all");
		}
		if(getCompany_user_id().equals("")){
			setCompany_user_id("all");
		}
		List<CompanyConfig> configList=null;
		String[] userParts=null;
		if(getUser_id().contains(",") && getCompany_user_id().contains(",")){
			userParts = getUser_id().split(",");
			configList=companyConfigDao.getCompanyConfigListByUserId(userParts[1]);
		}
		else if(!getCompany_user_id().contains(",") && getUser_id().contains(",")){
			userParts = getUser_id().split(",");
			configList=companyConfigDao.getCompanyConfigListByUserId(userParts[1]);
		}		
		else if(getCompany_user_id().contains(",") && !getUser_id().contains(",")){
			String[] companyParts = getCompany_user_id().split(",");
			configList=companyConfigDao.getCompanyCongigListByCompanyUserId(companyParts[1]);

		}
		else{
			configList=companyConfigDao.getCompanyCongigListByCompanyUserId(getUser_companyUserId());
		}

		if(configList!=null && configList.size()>0){
			if(sessionMap.get("CompanyConfigList")!=null){
				sessionMap.remove("CompanyConfigList");
			} 
			sessionMap.put("CompanyConfigList",configList);
		}
		else{
			sessionMap.remove("CompanyConfigList");
		}

		return SUCCESS;
	}

	public String filterFlightMarkupListByCompanyUserId(){
		if(getUser_id().equals("") || getUser_id()==null){
			setUser_id("all");
		}
		if(getCompany_user_id().equals("") || getCompany_user_id()==null){
			setCompany_user_id("all");
		}
		List<FlightMarkup> flightMarkupList=null;
		String[] userParts=null;
		if(getUser_id().contains(",") && getCompany_user_id().contains(",")){
			userParts = getUser_id().split(",");
			flightMarkupList=markupDao.getFlightMarkupListByUserId(userParts[1]);
		}
		else if(!getCompany_user_id().contains(",") && getUser_id().contains(",")){
			userParts = getUser_id().split(",");
			flightMarkupList=markupDao.getFlightMarkupListByUserId(userParts[1]);

		}		
		else if(getCompany_user_id().contains(",") && !getUser_id().contains(",")){
			String[] companyParts = getCompany_user_id().split(",");
			flightMarkupList=markupDao.getFlightMarkupListByCompanyUserId(companyParts[1]);

		}
		else{
			flightMarkupList=markupDao.getFlightMarkupListByCompanyUserId(getUser_companyUserId());
		}

		if(flightMarkupList!=null && flightMarkupList.size()>0){
			flightmarkupList=flightMarkupList;

		}

		return SUCCESS;
	}


	public String filterHotelMarkupListByCompanyUserId(){
		if(getUser_id().equals("") || getUser_id()==null ){
			setUser_id("all");
		}
		if(getCompany_user_id().equals("") || getCompany_user_id()==null){
			setCompany_user_id("all");
		}
		List<HotelMarkup> hotelMarkupList=null;
		List<HotelMarkup> newHotelMatrkupLi=new ArrayList<HotelMarkup>();
		String[] userParts=null;
		if(getUser_id().contains(",") && getCompany_user_id().contains(",")){
			userParts = getUser_id().split(",");
			hotelMarkupList=hotelMarkupDao.getHotelMarkupListByUserId(userParts[1]);

		}
		else if(!getCompany_user_id().contains(",") && getUser_id().contains(",")){
			userParts = getUser_id().split(",");
			hotelMarkupList=hotelMarkupDao.getHotelMarkupListByUserId(userParts[1]);

		}		
		else if(getCompany_user_id().contains(",") && !getUser_id().contains(",")){
			String[] companyParts = getCompany_user_id().split(",");
			hotelMarkupList=hotelMarkupDao.getHotelMarkupListByCompanyUserId(companyParts[1]);

		}
		else{
			logger.info("------calling----  ----company_User_id-----" +getUser_companyUserId());
			hotelMarkupList=hotelMarkupDao.getHotelMarkupListByCompanyUserId(getUser_companyUserId());
		}



		if(hotelMarkupList!=null && hotelMarkupList.size()>0){
			for(HotelMarkup hotelMarkup:hotelMarkupList){
				if(hotelMarkup.getHotelCheckinDate()==null  ){
					hotelMarkup.setCheckIn("ALL");

				}
				else{
					if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()).equalsIgnoreCase("31-12-0002")){
						hotelMarkup.setCheckIn("ALL");
					}
					else{
						hotelMarkup.setCheckIn(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckinDate()));	
					}
				}

				if(hotelMarkup.getHotelCheckoutDate()==null){
					hotelMarkup.setCheckOut("ALL");
				}
				else{
					if(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckoutDate()).equalsIgnoreCase("31-12-0002")){
						hotelMarkup.setCheckOut("ALL");
					}
					else{
						hotelMarkup.setCheckOut(DateConversion.convertDateToStringToDate(hotelMarkup.getHotelCheckoutDate()));
					} 
				}

				newHotelMatrkupLi.add(hotelMarkup);
			}
			AllHotelMatrkup=newHotelMatrkupLi;

		}
		return SUCCESS;
	}


	public String currencyJson(){

		List<Country> currecyList=	new CountryDao().getCountryList();	
		logger.info("============currecyList============"+currecyList);
		if(currecyList!=null && currecyList.size()>0){
			for(Country country:currecyList){
				currencyMap.put(country.getC_name(), country.getCur_code());
			}
		}
		return SUCCESS;
	}



	//added by basha for airline autocomplter 
	public  String airlinesJson(){
		List<Airlinelist> airlineList = new ArrayList<Airlinelist>(new  CountryDao().getAirlineList());
		if(airlineList!=null && airlineList.size()>0) 
			setAirlist(airlineList);

		return SUCCESS;
	}

	public String userWalletJson(){
		User sessionObj=(User) sessionMap.get("User");
		UserWallet userwallet=null;
		if(sessionObj!=null){
			try{
				userwallet=userDao.getUpdatedUserWallet(sessionObj);
				walletRecord.add(userwallet);
			}
			catch(Exception e)
			{
				logger.info("Exception======"+e.getMessage());
			}
		}
		return SUCCESS;
	}

	public String AllTechnicalSupportList(){

		Company companyObj=(Company) sessionMap.get("Company");
		try{
			List<User> techSupportList=employeeFilterDao.getAllTechnicalSupportList(companyObj);
			if(techSupportList!=null){
				agentList=techSupportList;
			}

		}
		catch(Exception e)
		{
			logger.info("Exception======"+e.getMessage());
		}

		return SUCCESS;

	}

	public String  isAnyCompanyExist(){
		boolean isCompanyExist= ComregDAO.ifAnyCompanyExist();
		setCompanyExist(isCompanyExist);
		return SUCCESS;
	}

	public  String directCompanyListUnderParent(){
		Company sessionObj=(Company) sessionMap.get("Company");
		List<Company> companyList = ComregDAO.getDirectCompanyListUnderParent(sessionObj);
		if(companyList!=null && companyList.size()>0){
			directCompanyList=companyList;
		}
		return SUCCESS;
	}
	public String childCompanyConfigCountUnderParent(){
		logger.info("myConfigCompanyUserId============"+myConfigCompanyUserId);
		logger.info("createdByCompanyId============"+createdByCompanyId);
		Map<String, Integer> resultMap=null; 
		CompanyConfigDao companyConfigDao=new CompanyConfigDao();
		List<Company> directCompanyList=companyConfigDao.getAllComapnyIdsByCompanyUserId(myConfigCompanyUserId);
		List<Integer> newCompanyList=new ArrayList<>();
		if(directCompanyList!=null && directCompanyList.size()>0){
			for(Company companyObj:directCompanyList){
				if(companyObj!=null && !companyObj.getCompany_userid().equals(myConfigCompanyUserId)){
					newCompanyList.add(companyObj.getCompanyid());
					resultMap=companyConfigDao.childCountforMyCompanyConfig(newCompanyList,Integer.parseInt(createdByCompanyId),configType);
				}
			}
		}
		if(resultMap!=null && resultMap.size()>0){
			childConfigCountMap=resultMap;
		}
		return SUCCESS;	
	}
	public String AllUserListByCompanyUserId(){
		Company company=(Company) sessionMap.get("Company");
		List<User> userList=new UserDAO().getallUserListByCompanyUserId(company);

		if(userList!=null && userList.size()>0){
			agentList=userList;
		}

		return SUCCESS; 
	}


	public String BrowsingPageNameList(){
		List<EnumUtility> pageNameList =BrowsingOptionPageEnum.getBrowsingPageNameList();
		if(pageNameList!=null & pageNameList.size()>0){
			setPageNameList(pageNameList);
		}
		return SUCCESS;
	}

	public List<Company> getRecords() {
		return records;
	}

	public void setRecords(List<Company> records) {
		this.records = records;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	@Override
	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap=(SessionMap<String, Object>) map;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getParent_company_user_id() {
		return parent_company_user_id;
	}

	public void setParent_company_user_id(String parent_company_user_id) {
		this.parent_company_user_id = parent_company_user_id;
	}

	public String getCompany_user_id() {
		return company_user_id;
	}

	public void setCompany_user_id(String company_user_id) {
		this.company_user_id = company_user_id;
	}
	public List<User> getUser_records() {
		return user_records;
	}

	public void setUser_records(List<User> user_records) {
		this.user_records = user_records;
	}

	public List<User> getAgentList() {
		return agentList;
	}

	public void setAgentList(List<User> agentList) {
		this.agentList = agentList;
	}

	public String getUser_companyUserId() {
		return user_companyUserId;
	}

	public void setUser_companyUserId(String user_companyUserId) {
		this.user_companyUserId = user_companyUserId;
	}

	public String getUser_company_email() {
		return user_company_email;
	}

	public void setUser_company_email(String user_company_email) {
		this.user_company_email = user_company_email;
	}

	public Map<String,String > getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(Map<String,String > currencyMap) {
		this.currencyMap = currencyMap;
	}


	public List<UserWallet> getWalletRecord() {
		return walletRecord;
	}

	public void setWalletRecord(List<UserWallet> walletRecord) {
		this.walletRecord = walletRecord;
	}

	public List<Company> getAllcompanylist() {
		return Allcompanylist;
	}

	public void setAllcompanylist(List<Company> allcompanylist) {
		Allcompanylist = allcompanylist;
	}

	public List<Company> getWalletUsersList() {
		return WalletUsersList;
	}

	public void setWalletUsersList(List<Company> walletUsersList) {
		WalletUsersList = walletUsersList;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	public List<FlightMarkup> getFlightmarkupList() {
		return flightmarkupList;
	}

	public void setFlightmarkupList(List<FlightMarkup> flightmarkupList) {
		this.flightmarkupList = flightmarkupList;
	}

	public List<HotelMarkup> getAllHotelMatrkup() {
		return AllHotelMatrkup;
	}

	public void setAllHotelMatrkup(List<HotelMarkup> allHotelMatrkup) {
		AllHotelMatrkup = allHotelMatrkup;
	}

	public boolean isCompanyExist() {
		return isCompanyExist;
	}

	public void setCompanyExist(boolean isCompanyExist) {
		this.isCompanyExist = isCompanyExist;
	}

	public String getMyConfigCompanyUserId() {
		return myConfigCompanyUserId;
	}

	public void setMyConfigCompanyUserId(String myConfigCompanyUserId) {
		this.myConfigCompanyUserId = myConfigCompanyUserId;
	}

	public Map<String,Integer> getChildConfigCountMap() {
		return childConfigCountMap;
	}

	public void setChildConfigCountMap(Map<String,Integer> childConfigCountMap) {
		this.childConfigCountMap = childConfigCountMap;
	}


	public String getCreatedByCompanyId() {
		return createdByCompanyId;
	}

	public void setCreatedByCompanyId(String createdByCompanyId) {
		this.createdByCompanyId = createdByCompanyId;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public List<CompanyConfig> getCompanyConfigList() {
		return companyConfigList;
	}

	public void setCompanyConfigList(List<CompanyConfig> companyConfigList) {
		this.companyConfigList = companyConfigList;
	}

	public List<Company> getDirectCompanyList() {
		return directCompanyList;
	}

	public void setDirectCompanyList(List<Company> directCompanyList) {
		this.directCompanyList = directCompanyList;
	}

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	/*public List<User> getSalesPersonRecords() {
		return salesPersonRecords;
	}

	public void setSalesPersonRecords(List<User> salesPersonRecords) {
		this.salesPersonRecords = salesPersonRecords;
	}
	 */
	public List<BrowsingOptionPageEnum> getBrowsingOptionPageEnumList() {
		return browsingOptionPageEnumList;
	}

	public void setBrowsingOptionPageEnumList(List<BrowsingOptionPageEnum> browsingOptionPageEnumList) {
		this.browsingOptionPageEnumList = browsingOptionPageEnumList;
	}
	public List<EnumUtility> getPageNameList() {
		return pageNameList;
	}

	public void setPageNameList(List<EnumUtility> pageNameList) {
		this.pageNameList = pageNameList;
	}

	public List<String> getHotelnameslist() {
		return hotelnameslist;
	}

	public void setHotelnameslist(List<String> hotelnameslist) {
		this.hotelnameslist = hotelnameslist;
	}

	public List<Country> getCountryList() {
		return CountryList;
	}

	public void setCountryList(List<Country> countryList) {
		CountryList = countryList;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getHotelCitylist() {
		return hotelCitylist;
	}

	public void setHotelCitylist(List<String> hotelCitylist) {
		this.hotelCitylist = hotelCitylist;
	}

	public Map<String, String> getAirlinesmap() {
		return airlinesmap;
	}

	public void setAirlinesmap(Map<String, String> airlinesmap) {
		this.airlinesmap = airlinesmap;
	}

	public List<Airlinelist> getAirlist() {
		return airlist;
	}

	public void setAirlist(List<Airlinelist> airlist) {
		this.airlist = airlist;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


}
