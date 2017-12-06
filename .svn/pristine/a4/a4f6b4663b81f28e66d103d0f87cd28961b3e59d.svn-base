package com.lintas.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Galileoconfig;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class GalileoConfigAction extends ActionSupport implements ModelDriven<Galileoconfig>,SessionAware {
	/**
	 * @author info name:raham
	 * created date:27-07-2015
	 */
	private static final long serialVersionUID = 1L;
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(GalileoConfigAction.class);
	Galileoconfig gc = new Galileoconfig();
	CompanyDAO gcDao=new CompanyDAO();
	SessionMap<String, Object> sessionmap ;
	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}

/*	 inserting GalileoConfig data  company id
	public String addGalileoConfigDetails(){
		Company company	=(Company)sessionmap.get("Company");
		logger.info("addGalileoConfigDetails  inside  Companyid "+company.getCompanyid());
		try {
			if(!gcDao.isGalileoconfigUserExists(gc)){
				gc.setCompany_id(company.getCompanyid());

				mcad.setCompany_id(company.getCompanyid());
				gcDao.insertGalileoconfigData(gc);
				addActionMessage("Successfully Added.");
				return SUCCESS;
			}
			else{
				addActionMessage("Already existed.");
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("-------EXCEPTION........."+e.getMessage());
			addActionError("Server down.Try again.");
			return ERROR;
		}

	}


	 deleting  GalileoConfig data  passing Galileo id
	public String deleteGalileoConfigDetails(){
		try {
			boolean isDelete=gcDao.deleteGalileoConfigDetails(gc);
			if(isDelete){
				showSuccessMessage("deleted");
				//addActionMessage("Successfully Deleted Galileoconfig Details...!");
				return SUCCESS;
			}
			else{
				showSuccessMessage("failed");
				//addActionMessage("Galileoconfig Details Not Existed or Please Check Credentials!");
				return ERROR;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("-------EXCEPTION........."+e.getMessage());
			showSuccessMessage("failed");
			//addActionError("OOps....Try Again!");
			return ERROR;
		}


	}

	this method for passing Galileo_id  fetching GalileoConfig profile
	public String getGalileoConfigProfile(){
		logger.info("getGalileoConfigProfile....Galileo_id...."+gc.getGalileo_id());
		Galileoconfig getProfile=gcDao.getGalileoconfigDetails(gc);
		sessionmap.put("GalileoconfigProfile",getProfile);
		return SUCCESS;

	}

	this method for passing Galileo_id  updating GalileoConfig data
	public String updateGalileoConfig() {
		Galileoconfig getProfile= (Galileoconfig)sessionmap.get("GalileoconfigProfile");
		gc.setGalileo_id(getProfile.getGalileo_id());
		boolean updated = gcDao.updateGalileoconfig(gc);
		if(updated)
		{			
			//showSuccessMessage("updated");
			addActionMessage("Successfully Updated.");
			return SUCCESS;
		}
		else
		{
			//showSuccessMessage("failed");
			addActionError("Failed.Try again.");
			return ERROR;
		}
	}

	method for get Galileo Config List
	public String getGalileoConfigList(){
		List<Galileoconfig>  config_li= gcDao.getGalileoConfigList();
		sessionmap.put("GalileoConfigList", config_li);
		return SUCCESS;

	}*/
	@Override
	public Galileoconfig getModel() {
		// TODO Auto-generated method stub
		return gc;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionmap = (SessionMap<String, Object>) arg0;

	}

	@SuppressWarnings("deprecation")
	public void  showSuccessMessage(String mes){
		inputStream = new StringBufferInputStream(mes);

	}


}
