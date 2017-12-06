package com.lintas.admin.hotel.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.RmConfigTripDetailsModel;

@Entity
@Access(AccessType.FIELD)
@Table(name = "hotel_order_guest")
public class HotelOrderGuest extends Timestampable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private String  convertDate;
	
	@Column(name = "session_user_id")
	private String sessionUserId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "title")
	private String title;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "age")
	private Integer age;

	@Column(name = "email")
	private String email;

	@Column(name = "pax_type")
	private String paxType;
	
	@Column(name = "leader" ,columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean leader;

/*	@ManyToOne()*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private HotelOrderRoomInfo roomInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "rm_config_trip_id", referencedColumnName = "id")
	 private RmConfigTripDetailsModel rmConfigTripDetailsModel;
	@Column(name = "pax_id")
	 private String paxId;
	public String getPaxId() {
		return paxId;
	}

	public void setPaxId(String paxId) {
		this.paxId = paxId;
	}

	public String getSessionUserId() {
		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public HotelOrderRoomInfo getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(HotelOrderRoomInfo roomInfo) {
		this.roomInfo = roomInfo;
	}

	/******
	 * 
	 * Getters and Setters
	 * 
	 */

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getLeader() {
		return leader;
	}

	public void setLeader(Boolean leader) {
		this.leader = leader;
	}

	public String getPaxType() {
		return paxType;
	}

	public void setPaxType(String paxType) {
		this.paxType = paxType;
	}

	public String getConvertDate() {
		return convertDate;
	}

	public void setConvertDate(String convertDate) {
		this.convertDate = convertDate;
	}

	public RmConfigTripDetailsModel getRmConfigTripDetailsModel() {
		return rmConfigTripDetailsModel;
	}

	public void setRmConfigTripDetailsModel(RmConfigTripDetailsModel rmConfigTripDetailsModel) {
		this.rmConfigTripDetailsModel = rmConfigTripDetailsModel;
	}

}
