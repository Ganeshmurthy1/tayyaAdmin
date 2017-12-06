/**
 * 
 */
package com.admin.designationband;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.model.Company;
import com.opensymphony.xwork2.ActionSupport;

public class EmployeeDesignationAction extends ActionSupport implements SessionAware{

	/**
	 * @author Manish
	 */
	private static final long serialVersionUID = 1L;
	private EmployeeDesignationsModel employeeDesignations=null;
	private EmployeeBandModel employeeBandModel=null;
	private List<EmployeeDesignationsModel> employeeDesignationsList=null;
	private List<EmployeeBandModel> bandModelList=null;
	private List<EmployeeBandModel> bandModelListToViewPage=null;
	private EmployeeDesignationModel employeeDesignationModel=null;
	private List<EmployeeDesignationModel> employeeDesignationModelList=null;
	SessionMap<String, Object> sessionmap ;
	private String newBandName;
	private String newBandCode;
	private String newDesgName;
	private String newDesgCode;
	private int newDesgBandid;

	private String userName;
	private int bandId;
	private String designation;
	private String bandName;

	private String deleteRes="failed";
	private Map<String,String> insertionMsgMap=new LinkedHashMap<String,String>();
	private int id;
	DesignationDao designationDao=new DesignationDao();

	public String addEmployeeToBand() throws Exception{
		try{
			setEmployeeDesignationsList(designationDao.getAllDesgNameList());
			setBandModelList(designationDao.getAllBandNameList());
		}catch (Exception e) {
		}
		return SUCCESS;
	}

	public String showEmployeeBandList(){
		try{
			setEmployeeDesignationModelList(designationDao.getAllBandRecords());
		}catch (Exception e) { 
			addActionError("Database Records not Found");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String showAllBandListView(){
		try{
			setBandModelListToViewPage(designationDao.getAllBandNameList());
		}catch (Exception e) { 
			addActionError("Database Records not Found");
			return ERROR;
		}
		return SUCCESS;
	}
	public String showAllDesignationListView(){
		try{
			setEmployeeDesignationsList(designationDao.getAllDesgNameList());
		}catch (Exception e) { 
			addActionError("Database Records not Found");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String editEmployeeDesignation(){
		try{
			addEmployeeToBand();
			setEmployeeDesignationModel(designationDao.getEmployeeRecordsById(employeeDesignationModel.getId()));
		}catch (Exception e) {
			addActionError("Database Records not Found by Id");
			return ERROR;
		}
		return SUCCESS;
	}
	public String editDesignation(){
		try{
			addEmployeeToBand();
			setEmployeeDesignations(designationDao.getDesignationsInfoById(employeeDesignations.getDesgId()));
		}catch (Exception e) {
			addActionError("Database Records not Found by Id");
			return ERROR;
		}
		return SUCCESS;
	}
	public String editBand(){
		try{
			addEmployeeToBand();
			setEmployeeBandModel(designationDao.getOneBandRecordById(employeeBandModel.getBandId()));
		}catch (Exception e) {
			addActionError("Database Records not Found by Id");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String insertBandName(){
		Company currentCompany = (Company) sessionmap.get("Company");
		EmployeeBandModel ebm=new EmployeeBandModel();
		ebm.setBandName(newBandName);
		ebm.setBandCode(newBandCode);
		ebm.setCompanyid(currentCompany.getCompanyid());
		designationDao.insertingBandName(ebm);
		return SUCCESS;
	}
	public String insertDesgName(){
		try{
			Company currentCompany = (Company) sessionmap.get("Company");
			EmployeeBandModel employeeBandModel=designationDao.getOneBandRecordById(newDesgBandid);
			EmployeeDesignationsModel ebm=new EmployeeDesignationsModel();
			ebm.setDesgName(newDesgName);
			ebm.setDesgCode(newDesgCode);
			ebm.setEmployeeBandModel(employeeBandModel);
			ebm.setCompanyid(currentCompany.getCompanyid());
			designationDao.insertingDesgName(ebm);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	public String updateEmployeeInfo(){
		if(employeeDesignationModel != null){
			Set<String> listOfAssociatedBand=designationDao.chekingDesignationAssociatedToWhichBand(employeeDesignationModel);
			/*String fg=(String) listOfAssociatedBand.toArray()[0];*/
			if(listOfAssociatedBand.size()==1){
				addActionError("This Designation is already assigned to "+listOfAssociatedBand.iterator().next()+"");
				return ERROR;
			}
			else if(listOfAssociatedBand.size()>1){
				addActionError(" WARNING !  This Designation is Assigned to more than One Band,  Please Check it & remove duplicacy ");
				return ERROR;
			}
			else{
				designationDao.updateEmployeeInfoById(employeeDesignationModel);
				return SUCCESS;
			}
		}
		addActionError("Data is null OR Data Insufficient");
		return ERROR;
	}

	public String updateDesignationInfo(){
		if(employeeDesignations != null){
			if(designationDao.updateDesignationInfoById(employeeDesignations))
			  return SUCCESS;
			else
				return ERROR;
		}
		addActionError("Data is null OR Data Insufficient");
		return ERROR;
	}
	
	public String updateBandInfo(){
		if(employeeBandModel != null){
			if(designationDao.updateBandInfoById(employeeBandModel))
			  return SUCCESS;
			else
				return ERROR;
		}
		addActionError("Data is null OR Data Insufficient");
		return ERROR;
	}
	
	public String deleteEmployeeInfoById(){

		boolean isDeleted=designationDao.deleteEmployeeInfoById(id);
		if(isDeleted) 
			setDeleteRes("success");
		return SUCCESS;
	}

	public String insertIntoEmployeeDesignation(){
		Map<String,String> map=new LinkedHashMap<String,String>();
		EmployeeDesignationModel designationModel=new EmployeeDesignationModel();
		designationModel.setBandName(bandName);
		designationModel.setDesignation(designation);
		designationModel.setBandId(bandId);
		designationModel.setUserName(userName);

		if(designationModel != null){
			Set<String> listOfAssociatedBand=designationDao.chekingDesignationAssociatedToWhichBand(designationModel);
			/*String fg=(String) listOfAssociatedBand.toArray()[0];*/
			if(listOfAssociatedBand.size()==1){
				map.put("response", "already");
				map.put("msg", "This Designation is already assigned to "+listOfAssociatedBand.iterator().next()+"");
				setInsertionMsgMap(map);
				return SUCCESS;
				/*addActionError("This Designation is already assigned to "+listOfAssociatedBand.iterator().next()+"");
				return ERROR;*/
			}
			else if(listOfAssociatedBand.size()>1){
				map.put("response", "warning");
				map.put("msg", " WARNING !  This Designation is Assigned to more than One Band,  Please Check it & remove duplicacy ");
				setInsertionMsgMap(map);
				return SUCCESS;
				/*addActionError(" WARNING !  This Designation is Assigned to more than One Band,  Please Check it & remove duplicacy ");
				return ERROR;*/
			}
			else{
				EmployeeDesignationsModel designationsModeldata=designationDao.getDesignationInfoById(designationModel.getDesignation());
				designationModel.setEmployeeDesignationsModels(designationsModeldata);
				designationDao.insertIntoEmployeeDesignation(designationModel);
				map.put("response", "inserted");
				map.put("msg", "inserted");
				setInsertionMsgMap(map);

				return SUCCESS;
			}

		}
		map.put("response", "insufficient");
		map.put("msg", "Data is null OR Data Insufficient");

		setInsertionMsgMap(map);
		return SUCCESS;
		/*addActionError("Data is null OR Data Insufficient");
		return ERROR;*/
	}

	public String checkingDesignationAvailabilty(){
		Map<String,String> map=new LinkedHashMap<String,String>();
		EmployeeDesignationModel designationModel=new EmployeeDesignationModel();
		designationModel.setBandName(bandName);
		designationModel.setDesignation(designation);
		designationModel.setBandId(bandId);
		designationModel.setUserName(userName);

		if(designationModel != null){
			Set<String> listOfAssociatedBand=designationDao.chekingDesignationAssociatedToWhichBand(designationModel);
			
			if(listOfAssociatedBand.size()==0 ||(listOfAssociatedBand.size()==1 && listOfAssociatedBand.iterator().next().equalsIgnoreCase(bandName))){
				map.put("response", "inserted");
				map.put("msg", "These are available");
				setInsertionMsgMap(map);

				return SUCCESS;
			}
			
			/*if(listOfAssociatedBand.size()==1){
				map.put("response", "already");
				map.put("msg", "This Designation is already assigned to "+listOfAssociatedBand.iterator().next()+"");
				setInsertionMsgMap(map);
				return SUCCESS;
			}
			else if(listOfAssociatedBand.size()>1){
				map.put("response", "warning");
				map.put("msg", " WARNING !  This Designation is Assigned to more than One Band,  Please Check it & remove duplicacy ");
				setInsertionMsgMap(map);
				return SUCCESS;
			}*/
			else{
				map.put("response", "already");
				map.put("msg", "This Designation is already assigned to "+listOfAssociatedBand.iterator().next()+"");
				setInsertionMsgMap(map);
				return SUCCESS;
			}

		}
		map.put("response", "insufficient");
		map.put("msg", "Data is null OR Data Insufficient");

		setInsertionMsgMap(map);
		return SUCCESS;
		/*addActionError("Data is null OR Data Insufficient");
		return ERROR;*/
	}
	public List<EmployeeDesignationsModel> getEmployeeDesignationsList() {
		return employeeDesignationsList;
	}

	public void setEmployeeDesignationsList(List<EmployeeDesignationsModel> employeeDesignationsList) {
		this.employeeDesignationsList = employeeDesignationsList;
	}

	public List<EmployeeBandModel> getBandModelList() {
		return bandModelList;
	}

	public void setBandModelList(List<EmployeeBandModel> bandModelList) {
		this.bandModelList = bandModelList;
	}

	public String getNewBandName() {
		return newBandName;
	}

	public void setNewBandName(String newBandName) {
		this.newBandName = newBandName;
	}

	public EmployeeDesignationModel getEmployeeDesignationModel() {
		return employeeDesignationModel;
	}

	public void setEmployeeDesignationModel(EmployeeDesignationModel employeeDesignationModel) {
		this.employeeDesignationModel = employeeDesignationModel;
	}

	public List<EmployeeDesignationModel> getEmployeeDesignationModelList() {
		return employeeDesignationModelList;
	}

	public void setEmployeeDesignationModelList(List<EmployeeDesignationModel> employeeDesignationModelList) {
		this.employeeDesignationModelList = employeeDesignationModelList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeleteRes() {
		return deleteRes;
	}

	public void setDeleteRes(String deleteRes) {
		this.deleteRes = deleteRes;
	}

	public Map<String,String> getInsertionMsgMap() {
		return insertionMsgMap;
	}

	public void setInsertionMsgMap(Map<String,String> insertionMsgMap) {
		this.insertionMsgMap = insertionMsgMap;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getBandId() {
		return bandId;
	}

	public void setBandId(int bandId) {
		this.bandId = bandId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getNewBandCode() {
		return newBandCode;
	}

	public void setNewBandCode(String newBandCode) {
		this.newBandCode = newBandCode;
	}

	public String getNewDesgName() {
		return newDesgName;
	}

	public void setNewDesgName(String newDesgName) {
		this.newDesgName = newDesgName;
	}

	public String getNewDesgCode() {
		return newDesgCode;
	}

	public void setNewDesgCode(String newDesgCode) {
		this.newDesgCode = newDesgCode;
	}

	public int getNewDesgBandid() {
		return newDesgBandid;
	}

	public void setNewDesgBandid(int newDesgBandid) {
		this.newDesgBandid = newDesgBandid;
	}
	@Override
	public void setSession(Map<String, Object> sess) {
		sessionmap = (SessionMap<String, Object>) sess;

	}

	public List<EmployeeBandModel> getBandModelListToViewPage() {
		return bandModelListToViewPage;
	}

	public void setBandModelListToViewPage(List<EmployeeBandModel> bandModelListToViewPage) {
		this.bandModelListToViewPage = bandModelListToViewPage;
	}

	public EmployeeDesignationsModel getEmployeeDesignations() {
		return employeeDesignations;
	}

	public void setEmployeeDesignations(EmployeeDesignationsModel employeeDesignations) {
		this.employeeDesignations = employeeDesignations;
	}

	public EmployeeBandModel getEmployeeBandModel() {
		return employeeBandModel;
	}

	public void setEmployeeBandModel(EmployeeBandModel employeeBandModel) {
		this.employeeBandModel = employeeBandModel;
	}

}
