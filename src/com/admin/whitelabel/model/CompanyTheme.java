/**
 * 
 */
package com.admin.whitelabel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * @author      : Manish Samrat
 * @createdAt   : 28/06/2017
 * @version
 */
@Entity
@Table(name="company_theme")
public class CompanyTheme implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private int companyId;
	private int configId;
	
	private String themeName;
	private String designPattern;
	private String url;
	private String logoImagePath;
	private String cssPath;
	
	private Boolean enableFlight;
	private Boolean enableHotel;
	private Boolean enableCar;
	private Boolean enableBus;
	private Boolean enableTrain;
	private Boolean enableInsurance;
	private Boolean enableVisa;
	private Boolean enableMiscellaneous;
	private Boolean enableLogoOnVoucher;
	private Boolean enableHeader;
	private Boolean enableFooter;
	private Boolean enablePaymentGateway;
	private Boolean enableB2B;
	private Boolean enableB2E; 
	private Boolean enableAboutUS;
	private Boolean enableContactUs;
	private Boolean enableTermsCondition;
	private Boolean enablePrivacyPolicy;
	private Boolean enableSocialMedia;
	private Boolean enableDeals;
	private Boolean enableSlider;
	public Boolean getEnableAboutUS() {
		return enableAboutUS;
	}
	public void setEnableAboutUS(Boolean enableAboutUS) {
		this.enableAboutUS = enableAboutUS;
	}
	public Boolean getEnableContactUs() {
		return enableContactUs;
	}
	public void setEnableContactUs(Boolean enableContactUs) {
		this.enableContactUs = enableContactUs;
	}
	public Boolean getEnableTermsCondition() {
		return enableTermsCondition;
	}
	public void setEnableTermsCondition(Boolean enableTermsCondition) {
		this.enableTermsCondition = enableTermsCondition;
	}
	public Boolean getEnablePrivacyPolicy() {
		return enablePrivacyPolicy;
	}
	public void setEnablePrivacyPolicy(Boolean enablePrivacyPolicy) {
		this.enablePrivacyPolicy = enablePrivacyPolicy;
	}
	public Boolean getEnableSocialMedia() {
		return enableSocialMedia;
	}
	public void setEnableSocialMedia(Boolean enableSocialMedia) {
		this.enableSocialMedia = enableSocialMedia;
	}
	public Boolean getEnableDeals() {
		return enableDeals;
	}
	public void setEnableDeals(Boolean enableDeals) {
		this.enableDeals = enableDeals;
	}
	public Boolean getEnableSlider() {
		return enableSlider;
	}
	public void setEnableSlider(Boolean enableSlider) {
		this.enableSlider = enableSlider;
	}
	
	
	public String getCssPath() {
		return cssPath;
	}
	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}
	public int getCompanyId() {
		return companyId;
	}
	public int getConfigId() {
		return configId;
	}
	public String getThemeName() {
		return themeName;
	}
	public String getDesignPattern() {
		return designPattern;
	}
	public String getLogoImagePath() {
		return logoImagePath;
	}
	
	public Boolean getEnableFlight() {
		return enableFlight;
	}
	public Boolean getEnableHotel() {
		return enableHotel;
	}
	public Boolean getEnableCar() {
		return enableCar;
	}
	public Boolean getEnableBus() {
		return enableBus;
	}
	public Boolean getEnableTrain() {
		return enableTrain;
	}
	public Boolean getEnableInsurance() {
		return enableInsurance;
	}
	public Boolean getEnableVisa() {
		return enableVisa;
	}
	public Boolean getEnableMiscellaneous() {
		return enableMiscellaneous;
	}
	public Boolean getEnableLogoOnVoucher() {
		return enableLogoOnVoucher;
	}
	public Boolean getEnableHeader() {
		return enableHeader;
	}
	public Boolean getEnableFooter() {
		return enableFooter;
	}
	public Boolean getEnablePaymentGateway() {
		return enablePaymentGateway;
	}
	public Boolean getEnableB2B() {
		return enableB2B;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public void setDesignPattern(String designPattern) {
		this.designPattern = designPattern;
	}
	public void setLogoImagePath(String logoImagePath) {
		this.logoImagePath = logoImagePath;
	}
	public void setEnableFlight(Boolean enableFlight) {
		this.enableFlight = enableFlight;
	}
	public void setEnableHotel(Boolean enableHotel) {
		this.enableHotel = enableHotel;
	}
	public void setEnableCar(Boolean enableCar) {
		this.enableCar = enableCar;
	}
	public void setEnableBus(Boolean enableBus) {
		this.enableBus = enableBus;
	}
	public void setEnableTrain(Boolean enableTrain) {
		this.enableTrain = enableTrain;
	}
	public void setEnableInsurance(Boolean enableInsurance) {
		this.enableInsurance = enableInsurance;
	}
	public void setEnableVisa(Boolean enableVisa) {
		this.enableVisa = enableVisa;
	}
	public void setEnableMiscellaneous(Boolean enableMiscellaneous) {
		this.enableMiscellaneous = enableMiscellaneous;
	}
	public void setEnableLogoOnVoucher(Boolean enableLogoOnVoucher) {
		this.enableLogoOnVoucher = enableLogoOnVoucher;
	}
	public void setEnableHeader(Boolean enableHeader) {
		this.enableHeader = enableHeader;
	}
	public void setEnableFooter(Boolean enableFooter) {
		this.enableFooter = enableFooter;
	}
	public void setEnablePaymentGateway(Boolean enablePaymentGateway) {
		this.enablePaymentGateway = enablePaymentGateway;
	}
	public void setEnableB2B(Boolean enableB2B) {
		this.enableB2B = enableB2B;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getEnableB2E() {
		return enableB2E;
	}
	public void setEnableB2E(Boolean enableB2E) {
		this.enableB2E = enableB2E;
	}
}
