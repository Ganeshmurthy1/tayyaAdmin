package com.admin.knockoff.common.filter.page;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.isl.admin.filter.Filter;
import com.lintas.admin.model.Company;
import com.lintas.admin.model.CompanyConfig;
import com.lintas.admin.model.User;
public class KnockOffFilter  extends Filter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> travelTypeList;
	private String travelType;
	private String knockOff;
	public KnockOffFilter() {
		super();
		this.travelType ="Flight";
		this.knockOff="ALL";
		this.travelTypeList=new LinkedList<>();
		this.travelTypeList.add("Flight");
		this.travelTypeList.add("Hotel");
		this.travelTypeList.add("Bus");
		this.travelTypeList.add("Car");
		this.travelTypeList.add("Train");
		this.travelTypeList.add("Visa");
		this.travelTypeList.add("Insurance");

	}
	public KnockOffFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser, int reportType) {
		super(loginCompanyConfig, loginCompany, loginUser, reportType);
	}
	public KnockOffFilter(CompanyConfig loginCompanyConfig, Company loginCompany, User loginUser) {
		super(loginCompanyConfig, loginCompany, loginUser);
	}
	public List<String> getTravelTypeList() {
		return travelTypeList;
	}
	@Override
	public String toString() {
		return "KnockOffFilter [travelTypeList=" + travelTypeList + ", travelType=" + travelType + "]";
	}
	public void setTravelTypeList(List<String> travelTypeList) {
		this.travelTypeList = travelTypeList;
	}

	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getKnockOff() {
		return knockOff;
	}
	public void setKnockOff(String knockOff) {
		this.knockOff = knockOff;
	}
}
