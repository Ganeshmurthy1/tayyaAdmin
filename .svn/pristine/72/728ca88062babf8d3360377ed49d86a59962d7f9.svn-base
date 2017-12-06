package com.lintas.admin.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "bus_order_cancellation_customer_details")
public class BusOrderCancellationCustomerDetail extends Timestampable implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bus_order_cancellation_id", referencedColumnName = "id")
	private BusOrderRowCancellation busOrderRowCancellation;
	@Column(name = "seat_no")
	private String seatNo;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "title")
	private String title;
	@Column(name = "gender")
	private String gender;
	@Column(name = "age")
	private String age;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "email")
	private String email;
	@Column(name = "issleeper")
	private Boolean isSleeper;
	@Column(name = "eticketnumber")
	private String eticketnumber;
	@Column(name = "operator_pnr")
	private String operatorPnr;
	@Column(name = "seat_price",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal seatPrice;
	@Column(name = "api_seat_price",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiSeatPrice;
	@Column(name = "api_seat_cancellation_fee",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiSeatCancelltionFee;
	@Column(name = "api_seat_refunded_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiSeatRefundedAmount;
	public BusOrderRowCancellation getBusOrderRowCancellation() {
		return busOrderRowCancellation;
	}
	public void setBusOrderRowCancellation(BusOrderRowCancellation busOrderRowCancellation) {
		this.busOrderRowCancellation = busOrderRowCancellation;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsSleeper() {
		return isSleeper;
	}
	public void setIsSleeper(Boolean isSleeper) {
		this.isSleeper = isSleeper;
	}
	public String getEticketnumber() {
		return eticketnumber;
	}
	public void setEticketnumber(String eticketnumber) {
		this.eticketnumber = eticketnumber;
	}
	public String getOperatorPnr() {
		return operatorPnr;
	}
	public void setOperatorPnr(String operatorPnr) {
		this.operatorPnr = operatorPnr;
	}
	public BigDecimal getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(BigDecimal seatPrice) {
		this.seatPrice = seatPrice;
	}
	public BigDecimal getApiSeatPrice() {
		return apiSeatPrice;
	}
	public void setApiSeatPrice(BigDecimal apiSeatPrice) {
		this.apiSeatPrice = apiSeatPrice;
	}
	public BigDecimal getApiSeatCancelltionFee() {
		return apiSeatCancelltionFee;
	}
	public void setApiSeatCancelltionFee(BigDecimal apiSeatCancelltionFee) {
		this.apiSeatCancelltionFee = apiSeatCancelltionFee;
	}
	public BigDecimal getApiSeatRefundedAmount() {
		return apiSeatRefundedAmount;
	}
	public void setApiSeatRefundedAmount(BigDecimal apiSeatRefundedAmount) {
		this.apiSeatRefundedAmount = apiSeatRefundedAmount;
	}
	

}
