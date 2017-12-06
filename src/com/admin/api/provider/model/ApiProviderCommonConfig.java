package com.admin.api.provider.model;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.admin.insurance.model.ApiProviderTrawellTagConfig;
import com.lintas.admin.DAO.CompanyConfigDao;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.CompanyConfig;

@Entity
@Table(name="api_provider_common_config")
public class ApiProviderCommonConfig extends Timestampable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	private long tboHotelId;
	@Transient
	private String  configType;

	@Transient
	private String  companyName;
	@Transient
	private long tboFlightId;
	@Transient
	private long desiyaHotelId;
	@Transient
	private long bluestarFlightId;
	@Transient
	private long etravelBusId;
	@Transient
	private long trawellTagInsuranceId;

	@Column(name = "configId")
	private int configId;


	private int companyId;
	@Column(name = "is_tbo_active")
	private boolean tboActive;
	@Column(name = "is_tbo_flight_active")
	private boolean tboFlightActive;
	@Column(name = "is_tbo_hotel_active")
	private boolean tboHotelActive;
	@Column(name = "is_desiya_active")
	private boolean desiyaActive;
	@Column(name = "is_desiya_hotel_active")
	private boolean desiyaHotelActive;
	@Column(name = "is_bluestar_active")
	private boolean bluestarActive;
	@Column(name = "is_bluestar_flight_active")
	private boolean bluestarFlightActive;

	@Column(name = "is_etravelsmart_active", columnDefinition = "boolean default false", nullable = false)
	private boolean etravelActive;
	@Column(name = "is_etravelsmart_bus_active", columnDefinition = "boolean default false", nullable = false)
	private boolean etravelBusActive;

	@Column(name = "is_trawelltag_active" , columnDefinition = "boolean default false", nullable = false)
	private boolean trawellTagActive;
	@Column(name = "is_trawelltag_insurance_active", columnDefinition = "boolean default false", nullable = false)
	private boolean trawellTagInsuranceActive;

	@Column(name = "is_tayyarah_active")
	private boolean tayyarahActive;
	@Column(name = "is_tayyarah_hotel_active")
	private boolean tayyarahHotelActive;

	@Column(name = "is_active")
	private boolean active;
	@Column(name = "title", columnDefinition = "text")
	private String title;

	@Column(name = "is_tbo_flight_mode")
	private boolean tboFlightEnvironment;

	@Column(name = "is_tbo_hotel_mode")
	private boolean tboHotelEnvironment;

	@Column(name = "is_desiya_hotel_mode")
	private boolean desiyaHotelEnvironment;

	@Column(name = "is_tayyarah_hotel_mode")
	private boolean tayyarahHotelEnvironment;

	@Column(name = "is_bluestar_flight_mode")
	private boolean bluestarFlightEnvironment;

	@Column(name = "is_etravel_bus_mode", columnDefinition = "boolean default false", nullable = false)
	private boolean etravelBusEnvironment;

	@Column(name = "is_trawelltag_insurance_mode", columnDefinition = "boolean default false", nullable = false)
	private boolean trawellTagInsuranceEnvironment;

	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "bluestar_flight_id")
	private  ApiProviderBluestarConfig ApiProviderBluestarConfigFlight;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "desiya_hotel_id")
	private  ApiProviderDesiyaConfig apiProviderDesiyaConfigHotel ; 

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbo_hotel_id")
	private ApiProviderTboConfig apiProviderTboConfigHotel;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tbo_flight_id")
	private ApiProviderTboConfig apiProviderTboConfigFlight;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tayyarah_hotel_id")
	private ApiProviderTayyarahConfig apiProviderTayyarahConfigFlight;

	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "etravel_bus_id")
	private  ApiProviderEtravelSmartConfig apiProviderEtravelBusConfig;

	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name = "trawelltag_insurance_id", referencedColumnName = "id")
	private  ApiProviderTrawellTagConfig apiProviderTrawellTagInsuranceConfig;

	public ApiProviderTrawellTagConfig getApiProviderTrawellTagInsuranceConfig() {
		return apiProviderTrawellTagInsuranceConfig;
	}
	public void setApiProviderTrawellTagInsuranceConfig(ApiProviderTrawellTagConfig apiProviderTrawellTagInsuranceConfig) {
		this.apiProviderTrawellTagInsuranceConfig = apiProviderTrawellTagInsuranceConfig;
	}
	public ApiProviderEtravelSmartConfig getApiProviderEtravelBusConfig() {
		return apiProviderEtravelBusConfig;
	}
	public void setApiProviderEtravelBusConfig(ApiProviderEtravelSmartConfig apiProviderEtravelBusConfig) {
		this.apiProviderEtravelBusConfig = apiProviderEtravelBusConfig;
	}
	public ApiProviderTayyarahConfig getApiProviderTayyarahConfigFlight() {
		return apiProviderTayyarahConfigFlight;
	}
	public void setApiProviderTayyarahConfigFlight(ApiProviderTayyarahConfig apiProviderTayyarahConfigFlight) {
		this.apiProviderTayyarahConfigFlight = apiProviderTayyarahConfigFlight;
	}
	public boolean isTboFlightEnvironment() {
		return tboFlightEnvironment;
	}
	public boolean isTayyarahHotelEnvironment() {
		return tayyarahHotelEnvironment;
	}
	public void setTayyarahHotelEnvironment(boolean tayyarahHotelEnvironment) {
		this.tayyarahHotelEnvironment = tayyarahHotelEnvironment;
	}
	public void setTboFlightEnvironment(boolean tboFlightEnvironment) {
		this.tboFlightEnvironment = tboFlightEnvironment;
	}
	public boolean isTboHotelEnvironment() {
		return tboHotelEnvironment;
	}
	public void setTboHotelEnvironment(boolean tboHotelEnvironment) {
		this.tboHotelEnvironment = tboHotelEnvironment;
	}
	public boolean isDesiyaHotelEnvironment() {
		return desiyaHotelEnvironment;
	}
	public void setDesiyaHotelEnvironment(boolean desiyaHotelEnvironment) {
		this.desiyaHotelEnvironment = desiyaHotelEnvironment;
	}
	public boolean isBluestarFlightEnvironment() {
		return bluestarFlightEnvironment;
	}
	public void setBluestarFlightEnvironment(boolean bluestarFlightEnvironment) {
		this.bluestarFlightEnvironment = bluestarFlightEnvironment;
	}
	public boolean isTboActive() {
		return tboActive;
	}
	public void setTboActive(boolean tboActive) {
		this.tboActive = tboActive;
	}
	public boolean isDesiyaActive() {
		return desiyaActive;
	}
	public void setDesiyaActive(boolean desiyaActive) {
		this.desiyaActive = desiyaActive;
	}
	public boolean isBluestarActive() {
		return bluestarActive;
	}
	public void setBluestarActive(boolean bluestarActive) {
		this.bluestarActive = bluestarActive;
	}

	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public boolean isTboFlightActive() {
		return tboFlightActive;
	}
	public void setTboFlightActive(boolean tboFlightActive) {
		this.tboFlightActive = tboFlightActive;
	}
	public boolean isTboHotelActive() {
		return tboHotelActive;
	}
	public void setTboHotelActive(boolean tboHotelActive) {
		this.tboHotelActive = tboHotelActive;
	}
	public boolean isDesiyaHotelActive() {
		return desiyaHotelActive;
	}
	public void setDesiyaHotelActive(boolean desiyaHotelActive) {
		this.desiyaHotelActive = desiyaHotelActive;
	}
	public boolean isBluestarFlightActive() {
		return bluestarFlightActive;
	}
	public void setBluestarFlightActive(boolean bluestarFlightActive) {
		this.bluestarFlightActive = bluestarFlightActive;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public ApiProviderBluestarConfig getApiProviderBluestarConfigFlight() {
		return ApiProviderBluestarConfigFlight;
	}
	public void setApiProviderBluestarConfigFlight(ApiProviderBluestarConfig apiProviderBluestarConfigFlight) {
		ApiProviderBluestarConfigFlight = apiProviderBluestarConfigFlight;
	}
	public ApiProviderDesiyaConfig getApiProviderDesiyaConfigHotel() {
		return apiProviderDesiyaConfigHotel;
	}
	public void setApiProviderDesiyaConfigHotel(ApiProviderDesiyaConfig apiProviderDesiyaConfigHotel) {
		this.apiProviderDesiyaConfigHotel = apiProviderDesiyaConfigHotel;
	}

	public ApiProviderTboConfig getApiProviderTboConfigHotel() {
		return apiProviderTboConfigHotel;
	}
	public void setApiProviderTboConfigHotel(ApiProviderTboConfig apiProviderTboConfigHotel) {
		this.apiProviderTboConfigHotel = apiProviderTboConfigHotel;
	}

	public ApiProviderTboConfig getApiProviderTboConfigFlight() {
		return apiProviderTboConfigFlight;
	}
	public void setApiProviderTboConfigFlight(ApiProviderTboConfig apiProviderTboConfigFlight) {
		this.apiProviderTboConfigFlight = apiProviderTboConfigFlight;
	}

	public boolean isTayyarahActive() {
		return tayyarahActive;
	}
	public void setTayyarahActive(boolean tayyarahActive) {
		this.tayyarahActive = tayyarahActive;
	}
	public boolean isTayyarahHotelActive() {
		return tayyarahHotelActive;
	}
	public void setTayyarahHotelActive(boolean tayyarahHotelActive) {
		this.tayyarahHotelActive = tayyarahHotelActive;
	}
	/*public ApiProviderTayyarahConfig getApiProviderTayyarahConfigFlight() {
		return apiProviderTayyarahConfigFlight;
	}
	public void setApiProviderTayyarahConfigFlight(ApiProviderTayyarahConfig apiProviderTayyarahConfigFlight) {
		this.apiProviderTayyarahConfigFlight = apiProviderTayyarahConfigFlight;
	}*/
	public long getTboHotelId() {
		return tboHotelId;
	}
	public void setTboHotelId(long tboHotelId) {
		this.tboHotelId = tboHotelId;
	}
	public long getTboFlightId() {
		return tboFlightId;
	}
	public void setTboFlightId(long tboFlightId) {
		this.tboFlightId = tboFlightId;
	}
	public long getDesiyaHotelId() {
		return desiyaHotelId;
	}
	public void setDesiyaHotelId(long desiyaHotelId) {
		this.desiyaHotelId = desiyaHotelId;
	}
	public long getBluestarFlightId() {
		return bluestarFlightId;
	}
	public void setBluestarFlightId(long bluestarFlightId) {
		this.bluestarFlightId = bluestarFlightId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getConfigId() {
		return configId;
	}
	public void setConfigId(int configId) {
		this.configId = configId;
	}
	public String getCompanyName() {
		CompanyConfig companyConfig=new CompanyConfigDao().getCompanyConfigDetails(getConfigId());
		if(companyConfig!=null){ 
			companyName=companyConfig.getCompanyName();
			if(companyConfig.getCompanyConfigType()!=null){
				if(companyConfig.getCompanyConfigType().isAPI())
					configType="API"; 
				if(companyConfig.getCompanyConfigType().isB2B())
					configType="B2B"; 
				if(companyConfig.getCompanyConfigType().isB2C())
					configType="B2C"; 
				if(companyConfig.getCompanyConfigType().isB2E())
					configType="B2E"; 
				if(companyConfig.getCompanyConfigType().isWhitelable())
					configType="Whitelable"; 
			}

		}
		else{
			companyName="NA";
			configType="NA";
		}
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public long getEtravelBusId() {
		return etravelBusId;
	}
	public void setEtravelBusId(long etravelBusId) {
		this.etravelBusId = etravelBusId;
	}
	public boolean isEtravelActive() {
		return etravelActive;
	}
	public void setEtravelActive(boolean etravelActive) {
		this.etravelActive = etravelActive;
	}
	public boolean isEtravelBusActive() {
		return etravelBusActive;
	}
	public void setEtravelBusActive(boolean etravelBusActive) {
		this.etravelBusActive = etravelBusActive;
	}
	public boolean isEtravelBusEnvironment() {
		return etravelBusEnvironment;
	}
	public void setEtravelBusEnvironment(boolean etravelBusEnvironment) {
		this.etravelBusEnvironment = etravelBusEnvironment;
	}
	public long getTrawellTagInsuranceId() {
		return trawellTagInsuranceId;
	}
	public boolean isTrawellTagActive() {
		return trawellTagActive;
	}
	public boolean isTrawellTagInsuranceActive() {
		return trawellTagInsuranceActive;
	}
	public boolean isTrawellTagInsuranceEnvironment() {
		return trawellTagInsuranceEnvironment;
	}
	public void setTrawellTagInsuranceId(long trawellTagInsuranceId) {
		this.trawellTagInsuranceId = trawellTagInsuranceId;
	}
	public void setTrawellTagActive(boolean trawellTagActive) {
		this.trawellTagActive = trawellTagActive;
	}
	public void setTrawellTagInsuranceActive(boolean trawellTagInsuranceActive) {
		this.trawellTagInsuranceActive = trawellTagInsuranceActive;
	}
	public void setTrawellTagInsuranceEnvironment(boolean trawellTagInsuranceEnvironment) {
		this.trawellTagInsuranceEnvironment = trawellTagInsuranceEnvironment;
	}

}