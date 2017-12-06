

package com.admin.common.quotation.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.common.model.Timestampable;
@Entity
@Table(name="car_travel_request")
public class CarTravelRequest extends Timestampable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(targetEntity = CarTravelRequestQuotation.class,   mappedBy = "carTravelRequest" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CarTravelRequestQuotation> carTravelRequestQuotations; 
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
	public List<CarTravelRequestQuotation> getCarTravelRequestQuotations() {
		return carTravelRequestQuotations;
	}
	public void setCarTravelRequestQuotations(List<CarTravelRequestQuotation> carTravelRequestQuotations) {
		this.carTravelRequestQuotations = carTravelRequestQuotations;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getTRNo() {
		return TRNo;
	}
	public void setTRNo(String tRNo) {
		TRNo = tRNo;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
}