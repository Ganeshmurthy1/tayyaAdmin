

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
@Table(name="visa_travel_request")
public class VisaTravelRequest extends Timestampable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch=FetchType.EAGER,targetEntity = VisaTravelRequestQuotation.class,   mappedBy = "visaTravelRequest" , cascade = CascadeType.ALL)
	private List<VisaTravelRequestQuotation> visaTravelRequestQuotations; 
 
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
	
	@Column(name = "status_id")
	 private int statusId;
	
	@Column(name="tr_no")
	private String trNo;
	
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
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public List<VisaTravelRequestQuotation> getVisaTravelRequestQuotations() {
		return visaTravelRequestQuotations;
	}
	public void setVisaTravelRequestQuotations(List<VisaTravelRequestQuotation> visaTravelRequestQuotations) {
		this.visaTravelRequestQuotations = visaTravelRequestQuotations;
	}
	public String getTrNo() {
		return trNo;
	}
	public void setTrNo(String trNo) {
		this.trNo = trNo;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
}