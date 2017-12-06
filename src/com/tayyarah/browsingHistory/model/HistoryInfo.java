package com.tayyarah.browsingHistory.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.lintas.admin.model.User;

public class HistoryInfo implements Serializable {
	@Override
	public String toString() {

		StringBuffer detailsInfo = new StringBuffer();
		if(details == null)
			detailsInfo.append("no info");
		else
			for (HistoryDetailInfo historyDetailInfo : details) {
				detailsInfo.append("--"+historyDetailInfo.toString()+"\n");
			}
		return "HistoryInfo [page=" + page + ", action=" + action + ", status=" + status + ", details=" + detailsInfo
				+ ", email=" + userId + ", password=" + password + ", companyid=" + companyId + ", country=" + country
				+ ", ip_address=" + ip_address + ", postal=" + postal + ", longitude=" + longitude + ", latitude="
				+ latitude + ", cityName=" + cityName + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HistoryInfo(BrowsingOptionPageEnum page, BrowsingOptionActionEnum action, ActionStatusEnum status) {
		super();
		this.page = page;
		this.action = action;
		this.status = status;
	}

	public HistoryInfo() {
		// TODO Auto-generated constructor stub

	}

	public void changeNature(BrowsingOptionPageEnum page, BrowsingOptionActionEnum action, ActionStatusEnum status) {
		this.page = page;
		this.action = action;
		this.status = status;
	}
	public void changeNature(BrowsingOptionPageEnum page, BrowsingOptionActionEnum action, ActionStatusEnum status, HistoryDetailInfo detail) {
		this.page = page;
		this.action = action;
		this.status = status;
		this.details = new ArrayList<HistoryDetailInfo>();
		this.details.add(detail);
	}

	public void changeNature(BrowsingOptionPageEnum page, BrowsingOptionActionEnum action, ActionStatusEnum status, BrowsingHistoryDetailTypeEnum detailEnum, String inventoryId, String description) {
		this.page = page;
		this.action = action;
		this.status = status;
		this.details = new ArrayList<HistoryDetailInfo>();
		this.details.add(new HistoryDetailInfo(detailEnum, inventoryId, description));		
	}

	public void setDetails(BrowsingHistoryDetailTypeEnum detailEnum, String inventoryId, String description) {
		this.details = new ArrayList<HistoryDetailInfo>();
		this.details.add(new HistoryDetailInfo(detailEnum, inventoryId, description));	
	}

	/*public void changeNature(Integer pageId, BrowsingOptionActionEnum action, ActionStatusEnum status) {
		this.page = page;
		this.action = action;
		this.status = status;
	}*/

	public void setUser(User user) {
		this.userId = user.getId();
		this.companyId = user.getCompanyid();
		this.password = user.getPassword();
	}
	public void setGeoIP(JSONObject geoIp) {		
		try {
			JSONObject postals = geoIp.getJSONObject("postal");
			JSONObject location = geoIp.getJSONObject("location");
			JSONObject ipaddress = geoIp.getJSONObject("traits");
			JSONObject geoCity = geoIp.getJSONObject("city");
			JSONObject citys = geoCity.getJSONObject("names");
			JSONObject geoCountry = geoIp.getJSONObject("country");
			JSONObject countrys = geoCountry.getJSONObject("names");				
			this.longitude = location.getString("longitude");
			this.latitude = location.getString("latitude");
			if(postals.has("code")&& postals.getString("code")!=null) 
				this.postal = postals.getString("code");
			else 
				this.postal="0";

			this.ip_address = ipaddress.getString("ip_address");
			this.cityName = citys.getString("en");
			this.country = countrys.getString("en");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BrowsingOptionPageEnum getPage() {
		return page;
	}
	public void setPage(BrowsingOptionPageEnum page) {
		this.page = page;
	}
	public BrowsingOptionActionEnum getAction() {
		return action;
	}
	public void setAction(BrowsingOptionActionEnum action) {
		this.action = action;
	}
	public ActionStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ActionStatusEnum status) {
		this.status = status;
	}
	private BrowsingOptionPageEnum page;
	private BrowsingOptionActionEnum action;
	private ActionStatusEnum status;
	private ArrayList<HistoryDetailInfo> details;


	public ArrayList<HistoryDetailInfo> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<HistoryDetailInfo> details) {
		this.details = details;
	}
	//these parameters are session oriented..
	private Integer userId;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	private String password;
	private Integer companyId;
	public String country;	
	public String ip_address;	
	public String postal;
	private String longitude;	
	private String latitude;	
	private String cityName;	



}

