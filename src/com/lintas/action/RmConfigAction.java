/**
 * 
 */
package com.lintas.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.DAO.RmConfigDao;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.RmConfigModel;
import com.lintas.admin.model.RmConfigTripDetailsModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Manish Samrat
 * @createdAt 05/04/2017
 */
public class RmConfigAction extends ActionSupport implements ModelDriven<RmConfigModel>, SessionAware{

	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RmConfigAction.class);
	private static final long serialVersionUID = 1L;

	private SessionMap<String, Object> sessionMap;
	int companyUserId;
	RmConfigModel rmConfigModel = new RmConfigModel();
	//RmConfigModel sendRmConfigModel = new RmConfigModel();
	private List<RmConfigModel> rmConfigModelList = new ArrayList<>();
	CompanyDAO companyDAO = new CompanyDAO();
	RmConfigDao rmConfigDao = new RmConfigDao();
	List<Company> companyList = new ArrayList<Company>();
	List<String> fieldNames = new ArrayList<String>();
	RmConfigTripDetailsModel configTripDetailsModel=new RmConfigTripDetailsModel();
	// HashMap<String, Object> rmConfigModelMap = new HashMap();

	public String addRmConfigDetails() {
		String loadToDatabase = "";
		if (rmConfigModel != null && companyUserId > 0) {
			if (rmConfigModelList !=null && rmConfigModelList.size() > 0) {
				for (RmConfigModel rcm : rmConfigModelList) {
					if (rcm.isFieldCheckBox()) {
						loadToDatabase += rcm.getFieldName() + ":" + rcm.getFieldType() + ",";
					}
				}
			}
			if(loadToDatabase!=null && loadToDatabase.length()>1)
			{
				loadToDatabase = loadToDatabase.substring(0, loadToDatabase.length()-1);
			}
			if (rmConfigDao.getAndUpdateCompanyDetails(companyUserId)) {
				rmConfigModel.setDynamicFieldsData(loadToDatabase);
				rmConfigModel.setCorporateId(companyUserId);
				rmConfigDao.insertConfigDetails(rmConfigModel);
			}
		}
		return SUCCESS;


		/*if (rmConfigModelList !=null && rmConfigModelList.size() > 0) {
			for (RmConfigModel rcm : rmConfigModelList) {
				if (rcm.isFieldCheckBox()) {
					loadToDatabase += rcm.getFieldName() + ":" + rcm.getFieldType() + ",";
				}
			}
		}
		if(fieldNames!=null && fieldNames.size()>0){
			int i=0;
			for(String str:fieldNames){
//				rmConfigModel.get
			}
		}
		if (rmConfigDao.getAndUpdateCompanyDetails(companyUserId)) {
			rmConfigModel.setDynamicFieldsData(loadToDatabase);
			rmConfigModel.setCorporateId(companyUserId);
			rmConfigDao.insertConfigDetails(rmConfigModel);
		}*/

	}

	public String fetchRmConfig() {
		List<Long> corporateIdList = rmConfigDao.getAllCompanyIdCorporateOnly();
		List<Integer> listOfIntId = new ArrayList<Integer>();
		for (Long longId : corporateIdList) {
			listOfIntId.add((int) (long) longId);
		}
		setCompanyList(rmConfigDao.getCompanyNameById(listOfIntId, false));
		return SUCCESS;
	}

	public String fetchRmConfigToUpdate() {
		List<Long> corporateIdList = rmConfigDao.getAllCompanyIdCorporateOnly();
		List<Integer> listOfIntId = new ArrayList<Integer>();
		for (Long longId : corporateIdList) {
			listOfIntId.add((int) (long) longId);
		}
		setCompanyList(rmConfigDao.getCompanyNameById(listOfIntId, true));
		return SUCCESS;
	}

	public String getCompanyDetailById() {
		fetchRmConfigToUpdate();
		try {
			rmConfigModel = rmConfigDao.getConfigDetailsByCompanyId(companyUserId);

			String manualStringFields[] = rmConfigModel.getDynamicFieldsData() != null
					? rmConfigModel.getDynamicFieldsData().split(",") : null;
					for (String oneField : manualStringFields) {
						String fieldsName[] = oneField.split(":");
						fieldNames.add(fieldsName[0]);
					}
					logger.info("fieldNames" +fieldNames);
					//rmConfigModelMap.put("model", rmConfigModel);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public String updateRmConfigDetails() {
		String loadToDatabase = "";
		if (rmConfigModel != null && companyUserId > 0) {
			if(rmConfigModel.getDynamicFieldDataSend()!=null && !rmConfigModel.getDynamicFieldDataSend().trim().equalsIgnoreCase("") ){
				if(rmConfigModel.getDynamicFieldDropdownSend()!=null && !rmConfigModel.getDynamicFieldDropdownSend().trim().equalsIgnoreCase("") ){
					String[] arrFieldData = rmConfigModel.getDynamicFieldDataSend().split(",");
					String[] arrDropDownData = rmConfigModel.getDynamicFieldDropdownSend().split(",");
					if(arrFieldData.length==arrDropDownData.length)
						for ( int i=0;i<arrFieldData.length;i++) {
							if(!arrFieldData[i].trim().equalsIgnoreCase("") && !arrDropDownData[i].trim().equalsIgnoreCase(""))
								loadToDatabase += arrFieldData[i] + ":" + arrDropDownData[i] + ",";
					  }
					// System.out.println(loadToDatabase);
				}
			}
			rmConfigModel.setId(rmConfigDao.getConfigDetailsByCompanyId(companyUserId).getId());
			rmConfigModel.setDynamicFieldsData(loadToDatabase!=null && !loadToDatabase.trim().equalsIgnoreCase("")?loadToDatabase:"");
			rmConfigDao.updateCompanyRmConfigFlag(rmConfigModel);
			addActionMessage("RM Config Updated SuccessFully");
			return SUCCESS;

		}else{
			addActionError("RM Config Updated Failed. Try Again");

			return ERROR;
		}

	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}

	@Override
	public RmConfigModel getModel() {
		return rmConfigModel;
	}

	public int getCompanyUserId() {
		return companyUserId;
	}

	public void setCompanyUserId(int companyUserId) {
		this.companyUserId = companyUserId;
	}

	/*public RmConfigModel getRmConfigModel() {
		return rmConfigModel;
	}

	public void setRmConfigModel(RmConfigModel rmConfigModel) {
		this.rmConfigModel = rmConfigModel;
	}*/

	public List<RmConfigModel> getRmConfigModelList() {
		return rmConfigModelList;
	}

	public void setRmConfigModelList(List<RmConfigModel> rmConfigModelList) {
		this.rmConfigModelList = rmConfigModelList;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	/*public RmConfigModel getSendRmConfigModel() {
		return sendRmConfigModel;
	}

	public void setSendRmConfigModel(RmConfigModel sendRmConfigModel) {
		this.sendRmConfigModel = sendRmConfigModel;
	}*/


	public List<String> getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	public RmConfigTripDetailsModel getConfigTripDetailsModel() {
		return configTripDetailsModel;
	}

	public void setConfigTripDetailsModel(RmConfigTripDetailsModel configTripDetailsModel) {
		this.configTripDetailsModel = configTripDetailsModel;
	}

	/**
	 * @return the jsonResult
	 *//*
	public Map getJsonResult() {
		return rmConfigModelMap;
	}

	  *//**
	  * @param jsonResult the jsonResult to set
	  *//*
	public void setJsonResult(Object rmConfigModelMap) {
		this.rmConfigModelMap = (HashMap<String, Object>) rmConfigModelMap;
	}*/

}
