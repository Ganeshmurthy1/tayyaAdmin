

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
@Table(name="train_travel_request")
public class TrainTravelRequest extends Timestampable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany( fetch=FetchType.EAGER,targetEntity = TrainTravelRequestQuotation.class,   mappedBy = "trainTravelRequest" , cascade = CascadeType.ALL)
	private List<TrainTravelRequestQuotation> trainTravelRequestQuotations; 


	
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
	public List<TrainTravelRequestQuotation> getTrainTravelRequestQuotations() {
		return trainTravelRequestQuotations;
	}
	public void setTrainTravelRequestQuotations(List<TrainTravelRequestQuotation> trainTravelRequestQuotations) {
		this.trainTravelRequestQuotations = trainTravelRequestQuotations;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
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