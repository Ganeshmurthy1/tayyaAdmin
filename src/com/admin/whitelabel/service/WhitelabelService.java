package com.admin.whitelabel.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;

import com.admin.whitelabel.model.CompanyTheme;
import com.admin.whitelabel.model.ThemeAuthRequestModel;
import com.admin.whitelabel.model.ThemeInsertionRequestModel;



/**
 * @author      : Manish Samrat
 * @createdAt   : 28/06/2017
 * @version
 */
public class WhitelabelService {

public CompanyTheme insertOrUpdateValuesIntoTable(ThemeInsertionRequestModel requestModel,CompanyTheme companyTheme){
		
		try{
			if(requestModel.getCompanyId()>0 && requestModel.getConfigId()>0){
				companyTheme.setCompanyId(requestModel.getCompanyId());
				companyTheme.setConfigId(requestModel.getConfigId());
			}
			companyTheme.setCssPath(requestModel.getCssPath());
			companyTheme.setDesignPattern(requestModel.getDesignPattern());
			companyTheme.setEnableB2B(requestModel.getEnableB2B());
			companyTheme.setEnableBus(requestModel.getEnableBus());
			companyTheme.setEnableCar(requestModel.getEnableCar());
			companyTheme.setEnableFlight(requestModel.getEnableFlight());
			companyTheme.setEnableFooter(requestModel.getEnableFooter());
			companyTheme.setEnableHeader(requestModel.getEnableHeader());
			companyTheme.setEnableHotel(requestModel.getEnableHotel());
			companyTheme.setEnableInsurance(requestModel.getEnableInsurance());
			companyTheme.setEnableLogoOnVoucher(requestModel.getEnableLogoOnVoucher());
			companyTheme.setEnableMiscellaneous(requestModel.getEnableMiscellaneous());
			companyTheme.setEnablePaymentGateway(requestModel.getEnablePaymentGateway());
			companyTheme.setEnableTrain(requestModel.getEnableTrain());
			companyTheme.setEnableVisa(requestModel.getEnableVisa());
			companyTheme.setLogoImagePath(requestModel.getLogoImagePath());
			companyTheme.setThemeName(requestModel.getThemeName());
			companyTheme.setUrl(requestModel.getUrl());
			companyTheme.setEnableB2E(requestModel.getEnableB2E());
			companyTheme.setEnableAboutUS(requestModel.getEnableAboutUS());
			companyTheme.setEnableContactUs(requestModel.getEnableContactUs());
			companyTheme.setEnableTermsCondition(requestModel.getEnableTermsCondition());
			companyTheme.setEnablePrivacyPolicy(requestModel.getEnablePrivacyPolicy());
			companyTheme.setEnableSocialMedia(requestModel.getEnableSocialMedia());
			companyTheme.setEnableDeals(requestModel.getEnableDeals());
			companyTheme.setEnableSlider(requestModel.getEnableSlider());
			
			return companyTheme;
		}
		catch (Exception e) {
		}
		
		return null;
	}
	

	public CompanyTheme fetchByAuthCriteria(CompanyTheme companyTheme,Criteria criteria,ThemeAuthRequestModel authRequestModel){
		try{
			Conjunction conjunction=Restrictions.conjunction();
			
			conjunction.add(Restrictions.eq("id", authRequestModel.getId()));
			conjunction.add(Restrictions.eq("companyId", authRequestModel.getCompanyId()));
			conjunction.add(Restrictions.eq("configId", authRequestModel.getConfigId()));
			conjunction.add(Restrictions.eq("themeName", authRequestModel.getThemeName()));
			
			criteria.add(conjunction);
			companyTheme=(CompanyTheme) criteria.uniqueResult();
		}catch (Exception e) {
			return null;
		}
		
		return companyTheme;
		
	}
	/*---------*/
	public CompanyTheme verifyingUserWhitelabelExistence(CompanyTheme companyTheme,Criteria criteria,ThemeAuthRequestModel authRequestModel){
		try{
			Conjunction conjunction=Restrictions.conjunction();
			
			conjunction.add(Restrictions.eq("companyId", authRequestModel.getCompanyId()));
			conjunction.add(Restrictions.eq("configId", authRequestModel.getConfigId()));
			
			criteria.add(conjunction);
			companyTheme=(CompanyTheme) criteria.uniqueResult();
		}catch (Exception e) {
			return null;
		}
		
		return companyTheme;
		
	}
}
