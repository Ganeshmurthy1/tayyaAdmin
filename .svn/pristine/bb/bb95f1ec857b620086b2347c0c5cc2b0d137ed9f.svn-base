package com.tayyarah.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lintas.admin.common.model.Timestampable;

@Entity
@Table(name = "hotel_room_details")	
public class HotelRoomDetails extends Timestampable implements Serializable {
	@Override
	public String toString() {
		return "HotelRoomDetails [basePrice=" + basePrice + ", taxPrice=" + taxPrice + ", availability=" + availability
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", extraBedPrice=" + extraBedPrice
				+ ", cancelBeforeDay=" + cancelBeforeDay + ", cancelAmount=" + cancelAmount + ", amountType="
				+ amountType + ", beds=" + beds + ", adults=" + adults + ", infants=" + infants + ", childs=" + childs
				+ ", cond=" + cond + ", hotelDetails=" + hotelDetails + "]";
	}

	/**
	 * @author HARSHA
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "base_price")
	private BigDecimal basePrice;

	@Column(name = "tax_price")
	private BigDecimal taxPrice;
	@Column(name = "name") 
	 private String name;
	 
	 @Column(name = "description") 
	 private String description;
 

	@Column(name = "availability")
	private Integer availability;

	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "extra_bed_price", columnDefinition="decimal default 0.00 NOT NULL")
	private BigDecimal extraBedPrice;
	@Column(name = "cancel_before_day", nullable = false)
	private Integer cancelBeforeDay;
	@Column(name = "cancel_amount")
	private BigDecimal cancelAmount;
	@Column(name = "amount_type") // percent ,fixed
	private String amountType;

	@Column(name = "beds")  
	private int beds;
	
	@Column(name = "adults")  
	private int adults;
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getInfants() {
		return infants;
	}
	public void setInfants(int infants) {
		this.infants = infants;
	}
	public int getChilds() {
		return childs;
	}
	public void setChilds(int childs) {
		this.childs = childs;
	}

	@Column(name = "infants")  
	private int infants;
	
	@Column(name = "childs")  
	private int childs;
	
	private String cond; 
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getExtraBedPrice() {
		return extraBedPrice;
	}
	public void setExtraBedPrice(BigDecimal extraBedPrice) {
		this.extraBedPrice = extraBedPrice;
	}
	public Integer getCancelBeforeDay() {
		return cancelBeforeDay;
	}
	public void setCancelBeforeDay(Integer cancelBeforeDay) {
		this.cancelBeforeDay = cancelBeforeDay;
	}
	public BigDecimal getCancelAmount() {
		return cancelAmount;
	}
	public void setCancelAmount(BigDecimal cancelAmount) {
		this.cancelAmount = cancelAmount;
	}

	public String getAmountType() {
		return amountType;
	}
	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	@ManyToOne (cascade = CascadeType.ALL) 
	@JoinColumn(name = "hotel_row_id", referencedColumnName = "id")
	private HotelDetails hotelDetails;// reference table 
	public HotelDetails getHotelDetails() {
		return hotelDetails;
	}
	public void setHotelDetails(HotelDetails hotelDetails) {
		this.hotelDetails = hotelDetails;
	}

	public String getCond() {
		return cond;
	}
	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
