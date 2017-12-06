package com.lintas.admin.hotel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.common.model.Timestampable;
@Entity
@Access(AccessType.FIELD)
@Table(name = "hotel_order_room_info")
public class HotelOrderRoomInfo extends Timestampable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Transient
	private String convertDate;

	@Column(name = "name")
	private String name;

	@Column(name = "mealType")
	private String mealType;

	@Column(name = "reference_code")
	private String referenceCode;

	@Column(name = "status")
	private String status;

	@Column(name = "inclusions")
	private String inclusions;

	@Column(name = "room_type")
	private String roomType;

	/*	@ManyToOne()*/
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private HotelOrderRow hotelOrderRow;

	@OneToMany(mappedBy = "roomInfo")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<HotelOrderGuest> hotelOrderGuests = new ArrayList<>();

	@OneToMany(mappedBy = "roomInfo")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<HotelOrderCancellationPolicy> hotelOrderCancellationPolicies = new ArrayList<>();

	@Column(name = "noofguests")
	private int noofguests;

	public int getNoofguests() {
		return noofguests;
	}

	public void setNoofguests(int noofguests) {
		this.noofguests = noofguests;
	}

	/******
	 * 
	 * Getters and Setters
	 * 
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMealType()
	{
		return mealType;
	}

	public void setMealType(String mealType)
	{
		this.mealType = mealType;
	}
	public HotelOrderRow getHotelOrderRow() {
		return hotelOrderRow;
	}

	public void setHotelOrderRow(HotelOrderRow hotelOrderRow) {
		this.hotelOrderRow = hotelOrderRow;
	}

	public List<HotelOrderGuest> getHotelOrderGuests() {
		return hotelOrderGuests;
	}

	public void setHotelOrderGuests(List<HotelOrderGuest> hotelOrderGuests) {
		this.hotelOrderGuests = hotelOrderGuests;
	}

	public List<HotelOrderCancellationPolicy> getHotelOrderCancellationPolicies() {
		return hotelOrderCancellationPolicies;
	}

	public void setHotelOrderCancellationPolicies(
			List<HotelOrderCancellationPolicy> hotelOrderCancellationPolicies) {
		this.hotelOrderCancellationPolicies = hotelOrderCancellationPolicies;
	}

	public String getConvertDate() {
		return convertDate;
	}

	public void setConvertDate(String convertDate) {
		this.convertDate = convertDate;
	}

	public String getInclusions() {
		return inclusions;
	}

	public void setInclusions(String inclusions) {
		this.inclusions = inclusions;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
}
