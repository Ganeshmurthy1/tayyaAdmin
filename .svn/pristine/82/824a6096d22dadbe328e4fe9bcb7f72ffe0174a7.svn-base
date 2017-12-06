/**
 * 
 */
package com.admin.entity.action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.admin.entity.dao.EntityDao;
import com.admin.entity.pojo.CommonGstTax;
import com.admin.enums.utility.IndianUnionTerritories;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.CompanyConfigTax;
import com.lintas.admin.model.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author MANISH
 *
 */
public class EntityAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	SessionMap<String, Object> sessionMap;
	Integer entityId;
	String travelType;
	String productType;
	CommonGstTax commonGstTaxes = new CommonGstTax();

	public String entityTaxInfoByEntityId() {
		Company sessionCompany = (Company) sessionMap.get("Company");
		CommonGstTax commonGstTax = new CommonGstTax();
		try {
			Company parentCompany = new CompanyDAO()
					.getParentCompanyByParentCompanyUserid(sessionCompany.getParent_company_userid());
			CompanyConfig newCompanyConfig = new CompanyConfigDao()
					.getConfigByComnpanyId(sessionCompany.getCompanyid());

			if (newCompanyConfig != null && newCompanyConfig.getConfig_id() > 0)
				commonGstTax = getTaxOrderType(travelType, newCompanyConfig);

			if (commonGstTax != null)
				setCommonGstTaxes(getEntityGSTTax(commonGstTax, sessionCompany, parentCompany));

		} catch (Exception e) {
		}

		return SUCCESS;
	}

	public CommonGstTax getEntityGSTTax(CommonGstTax commonGstTax, Company childCompany, Company parentCompany) {

		String entityState = null;
		if (entityId != null && entityId > 0)
			entityState = new EntityDao().getGstTaxInfoByEntityId(entityId);

		BigDecimal managementFee = commonGstTax.getManagementFee() != null ? commonGstTax.getManagementFee()
				: new BigDecimal("0.0");
		BigDecimal CGSTAmount = new BigDecimal("0.0");
		BigDecimal CGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTPer = new BigDecimal("0.0");
		BigDecimal CommonGSTAmount = new BigDecimal("0.0");

		BigDecimal totalGstAmount = new BigDecimal("0.0");
		BigDecimal totalGstPer = new BigDecimal("0.0");
		boolean isParentCompanyUT = IndianUnionTerritories.isUnionter(parentCompany.getBillingstate().trim());
		CGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getCGST());
		CGSTPer = commonGstTax.getCGST();
		if (entityState != null) {
			boolean isBillingCompanyUT = IndianUnionTerritories.isUnionter(entityState.trim());

			if (isParentCompanyUT && isBillingCompanyUT) {
				CommonGSTPer = commonGstTax.getUGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getUGST());
				commonGstTax.setIGST(new BigDecimal("0.0"));
				commonGstTax.setSGST(new BigDecimal("0.0"));

			}
			if (!isParentCompanyUT && !isBillingCompanyUT) {
				if (entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					CommonGSTPer = commonGstTax.getSGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getSGST());
					commonGstTax.setIGST(new BigDecimal("0.0"));
					commonGstTax.setUGST(new BigDecimal("0.0"));
				}
				if (!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					CommonGSTPer = commonGstTax.getIGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getIGST());
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					commonGstTax.setSGST(new BigDecimal("0.0"));
					commonGstTax.setUGST(new BigDecimal("0.0"));
					commonGstTax.setCGST(CGSTPer);
				}
			}

			if (isParentCompanyUT && !isBillingCompanyUT) {
				if (!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
						&& !IndianUnionTerritories.isUnionter(entityState.trim())) {
					CommonGSTPer = commonGstTax.getIGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getIGST());
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					commonGstTax.setSGST(new BigDecimal("0.0"));
					commonGstTax.setUGST(new BigDecimal("0.0"));
					commonGstTax.setCGST(CGSTPer);
				}
			}

			if (!entityState.trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
					&& IndianUnionTerritories.isUnionter(entityState.trim())) {
				CommonGSTPer = commonGstTax.getUGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getUGST());
				commonGstTax.setIGST(new BigDecimal("0.0"));
				commonGstTax.setSGST(new BigDecimal("0.0"));
			}

		} else {
			boolean isBillingCompanyUT = IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim());

			if (isParentCompanyUT && isBillingCompanyUT) {
				CommonGSTPer = commonGstTax.getUGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getUGST());
				commonGstTax.setIGST(new BigDecimal("0.0"));
				commonGstTax.setSGST(new BigDecimal("0.0"));

			}
			if (!isParentCompanyUT && !isBillingCompanyUT) {
				if (childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					CommonGSTPer = commonGstTax.getSGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getSGST());
					commonGstTax.setIGST(new BigDecimal("0.0"));
					commonGstTax.setUGST(new BigDecimal("0.0"));
				}
				if (!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())) {
					CommonGSTPer = commonGstTax.getIGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getIGST());
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					commonGstTax.setSGST(new BigDecimal("0.0"));
					commonGstTax.setUGST(new BigDecimal("0.0"));
					commonGstTax.setCGST(CGSTPer);
				}
			}

			if (isParentCompanyUT && !isBillingCompanyUT) {
				if (!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
						&& !IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())) {
					CommonGSTPer = commonGstTax.getIGST();
					CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getIGST());
					CGSTPer = new BigDecimal(0);
					CGSTAmount = new BigDecimal(0);
					commonGstTax.setSGST(new BigDecimal("0.0"));
					commonGstTax.setUGST(new BigDecimal("0.0"));
					commonGstTax.setCGST(CGSTPer);
				}
			}

			if (!childCompany.getBillingstate().trim().equalsIgnoreCase(parentCompany.getBillingstate().trim())
					&& IndianUnionTerritories.isUnionter(childCompany.getBillingstate().trim())) {
				CommonGSTPer = commonGstTax.getUGST();
				CommonGSTAmount = managementFee.divide(new BigDecimal("100.0")).multiply(commonGstTax.getUGST());
				commonGstTax.setIGST(new BigDecimal("0.0"));
				commonGstTax.setSGST(new BigDecimal("0.0"));
			}

		}

		totalGstPer = CGSTPer.add(CommonGSTPer);
		totalGstAmount = CGSTAmount.add(CommonGSTAmount);
		CommonGstTax commonGstTax2 = new CommonGstTax();
		commonGstTax2.setTotalTax(totalGstPer);
		commonGstTax2.setTotalGstAmount(totalGstAmount);
		commonGstTax2.setCGST(commonGstTax.getCGST());
		commonGstTax2.setSGST(commonGstTax.getSGST());
		commonGstTax2.setUGST(commonGstTax.getUGST());
		commonGstTax2.setIGST(commonGstTax.getIGST());
		commonGstTax2.setManagementFee(managementFee);
		return commonGstTax2;
	}

	public CommonGstTax getTaxOrderType(String type, CompanyConfig companyConfig) {
		CommonGstTax commonGstTax = new CommonGstTax();
		if (type.equalsIgnoreCase("flight")) {
//			BigDecimal managementFee=new BigDecimal("0.00");
			if(productType!=null){
//				managementFee=getManagementFeeByCompanyConfig(companyConfig, companyConfig.getCompany_id());
				if(productType.equalsIgnoreCase("Domestic")){
					commonGstTax.setCGST(companyConfig.getFlightDomesticGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
					commonGstTax.setIGST(companyConfig.getFlightDomesticGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
					commonGstTax.setSGST(companyConfig.getFlightDomesticGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
					commonGstTax.setUGST(companyConfig.getFlightDomesticGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
					commonGstTax.setConvenienceFee(companyConfig.getFlightDomesticGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
				}
				else if(productType.equalsIgnoreCase("International")){
					commonGstTax.setCGST(companyConfig.getFlightInternationalGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
					commonGstTax.setIGST(companyConfig.getFlightInternationalGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
					commonGstTax.setSGST(companyConfig.getFlightInternationalGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
					commonGstTax.setUGST(companyConfig.getFlightInternationalGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
					commonGstTax.setConvenienceFee(companyConfig.getFlightInternationalGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
				}
			}
			
//			commonGstTax.setManagementFee(managementFee.setScale(0, RoundingMode.HALF_UP));
		}

		else if (type.equalsIgnoreCase("hotel")) {
			commonGstTax.setCGST(companyConfig.getHotelGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setIGST(companyConfig.getHotelGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setSGST(companyConfig.getHotelGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setUGST(companyConfig.getHotelGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
			
			commonGstTax.setConvenienceFee(companyConfig.getHotelGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
		}

		else if (type.equalsIgnoreCase("car")) {
			commonGstTax.setCGST(companyConfig.getCarGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setIGST(companyConfig.getCarGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setSGST(companyConfig.getCarGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setUGST(companyConfig.getCarGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setApplicableFare(companyConfig.getCarGstTaxConfig().getApplicableFare());

			commonGstTax.setConvenienceFee(
					companyConfig.getCarGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
			commonGstTax.setManagementFee(
					companyConfig.getCarGstTaxConfig().getManagementFee().setScale(0, RoundingMode.HALF_UP));
		}

		else if (type.equalsIgnoreCase("bus")) {
			commonGstTax.setCGST(companyConfig.getBusGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setIGST(companyConfig.getBusGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setSGST(companyConfig.getBusGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setUGST(companyConfig.getBusGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setApplicableFare(companyConfig.getBusGstTaxConfig().getApplicableFare());

			commonGstTax.setConvenienceFee(
					companyConfig.getBusGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
			commonGstTax.setManagementFee(
					companyConfig.getBusGstTaxConfig().getManagementFee().setScale(0, RoundingMode.HALF_UP));
		}

		else if (type.equalsIgnoreCase("visa")) {
			commonGstTax.setCGST(companyConfig.getVisaGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setIGST(companyConfig.getVisaGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setSGST(companyConfig.getVisaGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setUGST(companyConfig.getVisaGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setApplicableFare(companyConfig.getVisaGstTaxConfig().getApplicableFare());

			commonGstTax.setConvenienceFee(
					companyConfig.getVisaGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
			commonGstTax.setManagementFee(
					companyConfig.getVisaGstTaxConfig().getManagementFee().setScale(0, RoundingMode.HALF_UP));
		}

		else if (type.equalsIgnoreCase("train")) {
			commonGstTax.setCGST(companyConfig.getTrainGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setIGST(companyConfig.getTrainGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setSGST(companyConfig.getTrainGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setUGST(companyConfig.getTrainGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setApplicableFare(companyConfig.getTrainGstTaxConfig().getApplicableFare());

			commonGstTax.setConvenienceFee(
					companyConfig.getTrainGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
			commonGstTax.setManagementFee(
					companyConfig.getTrainGstTaxConfig().getManagementFee().setScale(0, RoundingMode.HALF_UP));
		}

		else if (type.equalsIgnoreCase("insurance")) {
			commonGstTax.setCGST(companyConfig.getInsuranceGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setIGST(companyConfig.getInsuranceGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setSGST(companyConfig.getInsuranceGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setUGST(companyConfig.getInsuranceGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setApplicableFare(companyConfig.getInsuranceGstTaxConfig().getApplicableFare());

			commonGstTax.setConvenienceFee(
					companyConfig.getInsuranceGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
			commonGstTax.setManagementFee(
					companyConfig.getInsuranceGstTaxConfig().getManagementFee().setScale(0, RoundingMode.HALF_UP));
		}

		else if (type.equalsIgnoreCase("miscellaneous")) {
			commonGstTax
					.setCGST(companyConfig.getMiscellaneousGstTaxConfig().getCGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax
					.setIGST(companyConfig.getMiscellaneousGstTaxConfig().getIGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax
					.setSGST(companyConfig.getMiscellaneousGstTaxConfig().getSGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax
					.setUGST(companyConfig.getMiscellaneousGstTaxConfig().getUGST().setScale(2, RoundingMode.HALF_UP));
			commonGstTax.setApplicableFare(companyConfig.getMiscellaneousGstTaxConfig().getApplicableFare());

			commonGstTax.setConvenienceFee(
					companyConfig.getMiscellaneousGstTaxConfig().getConvenienceFee().setScale(0, RoundingMode.HALF_UP));
			commonGstTax.setManagementFee(
					companyConfig.getMiscellaneousGstTaxConfig().getManagementFee().setScale(0, RoundingMode.HALF_UP));
		}

		return commonGstTax;
	}

	/*public BigDecimal getManagementFeeByCompanyConfig(CompanyConfig companyConfig,Integer companyId){
		
		BigDecimal managementFee =new BigDecimal("0.00");
		BigDecimal managementFeeForBookingIntManagemtFee =new BigDecimal("0.00");
		BigDecimal managementFeeForBookingDomManagemtFee =new BigDecimal("0.00");
		if(companyConfig.getTaxtype()!=null){
			CompanyConfigTax companyConfigForManagementFee=new CompanyConfigDao().getConfigByComnpanyIdForB2ETaxes(companyId);
			if(companyConfigForManagementFee!=null){
				if(companyConfigForManagementFee.getGstTaxObj().getTaxtype().equalsIgnoreCase("GST")){
						if(travelType.equalsIgnoreCase("flight")){
							managementFeeForBookingDomManagemtFee=companyConfigForManagementFee.getGstTaxObj().getFlightDomesticGstTaxConfig().getManagementFee();
							managementFeeForBookingIntManagemtFee=companyConfigForManagementFee.getGstTaxObj().getFlightInternationalGstTaxConfig().getManagementFee();
						}
						else if(travelType.equalsIgnoreCase("hotel")){
							managementFeeForBookingDomManagemtFee=companyConfigForManagementFee.getGstTaxObj().getHotelGstTaxConfig().getDomesticManagementFee();
							managementFeeForBookingIntManagemtFee=companyConfigForManagementFee.getGstTaxObj().getHotelGstTaxConfig().getIntlManagementFee();
						}
					
				}else if(companyConfigForManagementFee.getServiceTaxObj().getTaxtype().equalsIgnoreCase("servicetax")){
						if(travelType.equalsIgnoreCase("flight")){
							managementFeeForBookingDomManagemtFee=companyConfigForManagementFee.getServiceTaxObj().getFlightDomesticGstTaxConfig().getManagementFee();
							managementFeeForBookingIntManagemtFee=companyConfigForManagementFee.getServiceTaxObj().getFlightInternationalGstTaxConfig().getManagementFee();
						}
						else if(travelType.equalsIgnoreCase("hotel")){
							managementFeeForBookingDomManagemtFee=companyConfigForManagementFee.getServiceTaxObj().getHotelGstTaxConfig().getDomesticManagementFee();
							managementFeeForBookingIntManagemtFee=companyConfigForManagementFee.getServiceTaxObj().getHotelGstTaxConfig().getIntlManagementFee();
						}
				}

		}else{
			if(travelType.equalsIgnoreCase("flight")){
				managementFeeForBookingDomManagemtFee=companyConfig.getFlightDomesticServiceTaxConfig().getManagementFee();
				managementFeeForBookingIntManagemtFee=companyConfig.getFlightInternationalServiceTaxConfig().getManagementFee();
			}
			else if(travelType.equalsIgnoreCase("hotel")){
				managementFeeForBookingDomManagemtFee=companyConfig.getHotelServiceTaxConfig().getDomesticManagementFee();
				managementFeeForBookingIntManagemtFee=companyConfig.getHotelServiceTaxConfig().getManagementFee();
			}

		}
	}
			if(productType.equalsIgnoreCase("Domestic"))
				managementFee=managementFeeForBookingDomManagemtFee;
			else if(productType.equalsIgnoreCase("International")){
				managementFee=managementFeeForBookingIntManagemtFee;
			}
			
		return managementFee;
	}*/

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}

	public Integer getEntityId() {
		return entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public CommonGstTax getCommonGstTaxes() {
		return commonGstTaxes;
	}

	public void setCommonGstTaxes(CommonGstTax commonGstTaxes) {
		this.commonGstTaxes = commonGstTaxes;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

}
