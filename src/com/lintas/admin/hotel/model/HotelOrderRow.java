package com.lintas.admin.hotel.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.HotelOrderRowCommission;
import com.lintas.admin.model.HotelOrderRowMarkup;
import com.tayyarah.admin.orderrow.rm.structure.FlightOrderRowRmConfigStruct;
import com.tayyarah.admin.orderrow.rm.structure.HotelOrderRowRmConfigStruct;
@Entity
@Table(name = "hotel_order_row")
public class HotelOrderRow extends Timestampable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	 
	@Transient
	private String createdDate;
	@Transient
	private String checkIn;
	@Transient
	private String checkOut;
	@Transient
	private BigDecimal tempManagementFees;
	
	@Transient
	private BigDecimal managementFeesdummy;
	
	@Column(name = "company_Entity_Id")
	private Long companyEntityId;
	
	@Column(name="booking_date")
	private String bookingDate;
	
	
	private boolean isCreditNoteIssued;
	private boolean isOrderUpdated;
	@Column(name="cancel_mode")
	private String cancelMode;
	@Column(name = "is_order_requested",columnDefinition="BIT(1) default 0")
	private boolean isOrderRequested;
	@Column(name = "orderReference")
	private String orderReference;
	@Column(name = "updated_by")
	private String updatedBy;
	@Column(name = "reference_code")
	private String referenceCode; 
	@Column(name = "confirmation_no")
	private String confirmationNo;
	@Column(name = "api_provider")
	private String apiProvoder;
	@Column(name = "api_price",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiPrice;

	@Column(name = "final_price", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal finalPrice;

	@Column(name = "markup_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal markupAmount;

	/*totalPrice,FeeAmount,Markup,Discount are in this currencies*/
	//*this either can be referred as api currency which is given by api providers...*/
	//this can be calculated by base currency * api currency ratio
	@Column(name = "api_currency")
	private String apiCurrency;

	/*totalPrice,FeeAmount,Markup,Discount are in this currencies*/
	//*this either can be referred as booking currency...*/
	//this can be calculated by base currency * price currency ratio
	@Column(name = "booking_currency")
	private String bookingCurrency;	


	/*This is the currency for which the multiplier was calculated*/
	/*this either can be referred as agent system currency...*/
	//all amount n prices are stored in this currency...
	@Column(name = "base_currency")
	private String baseCurrency;
	@Column(name = "api_booiking_id")
	private String apiBookingId;
	@Column(name = "search_key")
	private Integer searchKey;	


	@Column(name = "api_confirmation_no")
	private String apiConfirmationNo;	

	/*Both discount and fee are in the same currency as the total price*/
	@Column(name = "fee_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal feeAmount;

	@Column(name = "discount_amount", columnDefinition = "decimal(20,10) default '0.0'")
	private BigDecimal discountAmount;
	@Column(name = "taxes", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal taxes;

	@Column(name = "payment_status")
	private String paymentStatus;	

	@Column(name = "status_action")
	private String statusAction;	

	@Column(name = "base_to_booking_exchange_rate", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal baseToBookingExchangeRate; 

	@Column(name = "api_to_base_exchange_rate", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiToBaseExchangeRate;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = OrderCustomer.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private OrderCustomer orderCustomer;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = HotelOrderHotelData.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private HotelOrderHotelData hotelOrderHotelData;
	@Column(name = "booking_status")
	private String bookingStatus;
	@Column(name = "ref_unique_type")
	private String refUniqueType;
	@Column(name = "invoice_no")
	private String invoiceNo;
	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "check_in_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkInDate;

	@Column(name = "check_out_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkOutDate;

	@Column(name = "total_guest")
	private int totalGuest;
	@Column(name = "agencyUserName")
	private String agencyUserName; 

	@Column(name = "api_comments")
	private String apiComments; 

	@Column(name = "company_id")
	private String companyId; 
	@Column(name = "confid_id")
	private String configId; 

	@Column(name = "user_id")
	private String userId; 
	@Column(name = "noofrooms")
	private int noOfRooms; 

	@Column(name = "user_comments")
	private String userComments; 	
	/*@Column(name = "is_international_destination",columnDefinition="BIT(1) default 0")
	private boolean internationalDestination;*/
	 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelOrderRow",targetEntity = HotelOrderRoomInfo.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<HotelOrderRoomInfo> hotelOrderRoomInfos;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelOrderRow",targetEntity = HotelOrderCancellationPolicy.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<HotelOrderCancellationPolicy> hotelOrderCancellationPolicies;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelOrderRow",targetEntity = HotelOrderRowMarkup.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<HotelOrderRowMarkup> hotelOrderRowMarkupList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelOrderRow",targetEntity = HotelOrderRowCommission.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<HotelOrderRowCommission> hotelOrderRowCommissionList;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = HotelOrderRowServiceTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "hotelOrderRow_serviceTax_id", referencedColumnName = "id")
	private HotelOrderRowServiceTax hotelOrderRowServiceTax;
	
	@Column(name = "booking_mode")
	private String bookingMode; 
	
	@Column(name = "service_tax",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal serviceTax; 	
	@Column(name = "total_gst_tax",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totGst; 	
	

	@Column(name="recieved_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal recievedAmount;
	
	@Column(name="tot_invoice_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totInvoiceAmount;
	
	@Column(name="is_knock_off",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean knockOff;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = HotelOrderRowGstTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "hotelOrderRow_gstTax_id", referencedColumnName = "id")
	private HotelOrderRowGstTax hotelOrderRowGstTax;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dynamic_rm_config_id", referencedColumnName = "id")
	private  HotelOrderRowRmConfigStruct hotelOrderRowRmConfigStruct;
	public HotelOrderRowRmConfigStruct getHotelOrderRowRmConfigStruct() {
		return hotelOrderRowRmConfigStruct;
	}
	public void setHotelOrderRowRmConfigStruct(HotelOrderRowRmConfigStruct hotelOrderRowRmConfigStruct) {
		this.hotelOrderRowRmConfigStruct = hotelOrderRowRmConfigStruct;
	}
	public BigDecimal getRecievedAmount() {
		return recievedAmount;
	}
	public BigDecimal getTotInvoiceAmount() {
		return totInvoiceAmount;
	}
	public boolean isKnockOff() {
		return knockOff;
	}
	public void setRecievedAmount(BigDecimal recievedAmount) {
		this.recievedAmount = recievedAmount;
	}
	public void setTotInvoiceAmount(BigDecimal totInvoiceAmount) {
		this.totInvoiceAmount = totInvoiceAmount;
	}
	public void setKnockOff(boolean knockOff) {
		this.knockOff = knockOff;
	}

	public String getBookingMode() {
		return bookingMode;
	}

	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}

	public String getApiBookingId() {
		return apiBookingId;
	}

	public void setApiBookingId(String apiBookingId) {
		this.apiBookingId = apiBookingId;
	}
	
	public String getApiConfirmationNo() {
		return apiConfirmationNo;
	}

	public void setApiConfirmationNo(String apiConfirmationNo) {
		this.apiConfirmationNo = apiConfirmationNo;
	}
	
	public BigDecimal getApiPrice() {
		return apiPrice;
	}

	public void setApiPrice(BigDecimal apiPrice) {
		this.apiPrice = apiPrice;
	}

	public HotelOrderRowServiceTax getHotelOrderRowServiceTax() {
		return hotelOrderRowServiceTax;
	}

	public void setHotelOrderRowServiceTax(HotelOrderRowServiceTax hotelOrderRowServiceTax) {
		this.hotelOrderRowServiceTax = hotelOrderRowServiceTax;
	}

	public List<HotelOrderRowMarkup> getHotelOrderRowMarkupList() {
		return hotelOrderRowMarkupList;
	}

	public void setHotelOrderRowMarkupList(
			List<HotelOrderRowMarkup> hotelOrderRowMarkupList) {
		this.hotelOrderRowMarkupList = hotelOrderRowMarkupList;
	}
	public String getBookingCurrency() {
		return bookingCurrency;
	}

	public void setBookingCurrency(String bookingCurrency) {
		this.bookingCurrency = bookingCurrency;
	}

	public List<HotelOrderRowCommission> getHotelOrderRowCommissionList() {
		return hotelOrderRowCommissionList;
	}

	public void setHotelOrderRowCommissionList(
			List<HotelOrderRowCommission> hotelOrderRowCommissionList) {
		this.hotelOrderRowCommissionList = hotelOrderRowCommissionList;
	}
	public String getPaymentStatus()
	{
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus)
	{
		this.paymentStatus = paymentStatus;
	}

	public String getBookingStatus()
	{
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus)
	{
		this.bookingStatus = bookingStatus;
	}

	public String getApiCurrency() {
		return apiCurrency;
	}

	public void setApiCurrency(String apiCurrency) {
		this.apiCurrency = apiCurrency;
	}
	public BigDecimal getBaseToBookingExchangeRate() {
		return baseToBookingExchangeRate;
	}

	public void setBaseToBookingExchangeRate(BigDecimal baseToBookingExchangeRate) {
		this.baseToBookingExchangeRate = baseToBookingExchangeRate;
	}

	public BigDecimal getApiToBaseExchangeRate() {
		return apiToBaseExchangeRate;
	}

	public void setApiToBaseExchangeRate(BigDecimal apiToBaseExchangeRate) {
		this.apiToBaseExchangeRate = apiToBaseExchangeRate;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getBaseCurrency()
	{
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency)
	{
		this.baseCurrency = baseCurrency;
	}
	public BigDecimal getMarkupAmount()
	{
		return markupAmount;
	}

	public void setMarkupAmount(BigDecimal markupAmount)
	{
		this.markupAmount = markupAmount;
	}

	public BigDecimal getFeeAmount()
	{
		return feeAmount;
	}

	public void setFeeAmount(BigDecimal feeAmount)
	{
		this.feeAmount = feeAmount;
	}

	public BigDecimal getDiscountAmount()
	{
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount)
	{
		this.discountAmount = discountAmount;
	}



	public List<HotelOrderRoomInfo> getHotelOrderRoomInfos() {
		return hotelOrderRoomInfos;
	}

	public void setHotelOrderRoomInfos(List<HotelOrderRoomInfo> hotelOrderRoomInfos) {
		this.hotelOrderRoomInfos = hotelOrderRoomInfos;
	}

	public List<HotelOrderCancellationPolicy> getHotelOrderCancellationPolicies() {
		return hotelOrderCancellationPolicies;
	}

	public void setHotelOrderCancellationPolicies(
			List<HotelOrderCancellationPolicy> hotelOrderCancellationPolicies) {
		this.hotelOrderCancellationPolicies = hotelOrderCancellationPolicies;
	}

	public OrderCustomer getOrderCustomer() {
		return orderCustomer;
	}

	public void setOrderCustomer(OrderCustomer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}

	public HotelOrderHotelData getHotelOrderHotelData() {
		return hotelOrderHotelData;
	}

	public void setHotelOrderHotelData(HotelOrderHotelData hotelOrderHotelData) {
		this.hotelOrderHotelData = hotelOrderHotelData;
	}



	public String getStatusAction() {
		return statusAction;
	}

	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getTotalGuest() {
		return totalGuest;
	}

	public void setTotalGuest(int totalGuest) {
		this.totalGuest = totalGuest;
	}

	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}


	/**
	 * @return the refUniqueType
	 */
	public String getRefUniqueType() {
		return refUniqueType;
	}

	/**
	 * @param refUniqueType the refUniqueType to set
	 */
	public void setRefUniqueType(String refUniqueType) {
		this.refUniqueType = refUniqueType;
	}

	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	/**
	 * @return the apiProvoder
	 */
	public String getApiProvoder() {
		return apiProvoder;
	}

	/**
	 * @param apiProvoder the apiProvoder to set
	 */

	public void setApiProvoder(String apiProvoder) {
		this.apiProvoder = apiProvoder;
	}

	/**
	 * @return the orderReference
	 */
	public String getOrderReference() {
		return orderReference;
	}

	/**
	 * @param orderReference the orderReference to set
	 */
	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	/**
	 * @return the referenceCode
	 */
	public String getReferenceCode() {
		return referenceCode;
	}

	/**
	 * @param referenceCode the referenceCode to set
	 */
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getAgencyUserName() {
		return agencyUserName;
	}

	public void setAgencyUserName(String agencyUserName) {
		this.agencyUserName = agencyUserName;
	}

	public String getApiComments() {
		return apiComments;
	}

	public void setApiComments(String apiComments) {
		this.apiComments = apiComments;
	}
	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isCreditNoteIssued() {
		return isCreditNoteIssued;
	}

	public void setCreditNoteIssued(boolean isCreditNoteIssued) {
		this.isCreditNoteIssued = isCreditNoteIssued;
	}

	public boolean isOrderUpdated() {
		return isOrderUpdated;
	}

	public void setOrderUpdated(boolean isOrderUpdated) {
		this.isOrderUpdated = isOrderUpdated;
	}

	public boolean isOrderRequested() {
		return isOrderRequested;
	}

	public void setOrderRequested(boolean isOrderRequested) {
		this.isOrderRequested = isOrderRequested;
	}

	public Integer getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(Integer searchKey) {
		this.searchKey = searchKey;
	}

	public String getConfirmationNo() {
		return confirmationNo;
	}

	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getCancelMode() {
		return cancelMode;
	}

	public void setCancelMode(String cancelMode) {
		this.cancelMode = cancelMode;
	}

	public BigDecimal getServiceTax() {
		return serviceTax;
	}

	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}

	public BigDecimal getTempManagementFees() {
		return tempManagementFees;
	}

	public void setTempManagementFees(BigDecimal tempManagementFees) {
		this.tempManagementFees = tempManagementFees;
	}
	public HotelOrderRowGstTax getHotelOrderRowGstTax() {
		return hotelOrderRowGstTax;
	}
	public void setHotelOrderRowGstTax(HotelOrderRowGstTax hotelOrderRowGstTax) {
		this.hotelOrderRowGstTax = hotelOrderRowGstTax;
	}
	public Long getCompanyEntityId() {
		return companyEntityId;
	}
	public void setCompanyEntityId(Long companyEntityId) {
		this.companyEntityId = companyEntityId;
	}
	public BigDecimal getTotGst() {
		return totGst;
	}
	public void setTotGst(BigDecimal totGst) {
		this.totGst = totGst;
	}
	public BigDecimal getManagementFeesdummy() {
		return managementFeesdummy;
	}
	public void setManagementFeesdummy(BigDecimal managementFeesdummy) {
		this.managementFeesdummy = managementFeesdummy;
	}

	/*public boolean isInternationalDestination() {
		return internationalDestination;
	}

	public void setInternationalDestination(boolean internationalDestination) {
		this.internationalDestination = internationalDestination;
	}*/
 
	
}
