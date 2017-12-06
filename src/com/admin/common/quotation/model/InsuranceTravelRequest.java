

package com.admin.common.quotation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.lintas.admin.common.model.Timestampable;
@Entity
@Table(name="insurance_travel_request")
public class InsuranceTravelRequest extends Timestampable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch=FetchType.EAGER,targetEntity = InsuranceTravelRequestQuotation.class,   mappedBy = "insuranceTravelRequest" , cascade = CascadeType.ALL)
	private List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotations; 
 
	@Column(name = "company_id" ,columnDefinition="INT(11) default 0")
	private int companyId;
	
	@Column(name = "user_id",columnDefinition="INT(11) default 0")
	private int userId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="no_of_travelers")
	private String noOfTravelers;
	
	@Column(name = "status_id")
	 private int statusId;
	
	@Column(name="tr_no")
	private String TRNo;
	
	@Column(name="entity")
	private String entity;
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public List<InsuranceTravelRequestQuotation> getInsuranceTravelRequestQuotations() {
		return insuranceTravelRequestQuotations;
	}
	public void setInsuranceTravelRequestQuotations(
			List<InsuranceTravelRequestQuotation> insuranceTravelRequestQuotations) {
		this.insuranceTravelRequestQuotations = insuranceTravelRequestQuotations;
	}
	public String getTRNo() {
		return TRNo;
	}
	public void setTRNo(String tRNo) {
		TRNo = tRNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNoOfTravelers() {
		return noOfTravelers;
	}
	public void setNoOfTravelers(String noOfTravelers) {
		this.noOfTravelers = noOfTravelers;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
}