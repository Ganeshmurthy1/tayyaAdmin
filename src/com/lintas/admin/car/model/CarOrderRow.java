package com.lintas.admin.car.model;

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

import com.lintas.admin.DAO.CompanyDAO;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.Timestampable;
import com.lintas.admin.model.Company;
import com.lintas.utility.DateConversion;
import com.tayyarah.admin.orderrow.rm.structure.BusOrderRowRmConfigStruct;
import com.tayyarah.admin.orderrow.rm.structure.CarOrderRowRmConfigStruct;
import com.tayyarah.car.model.CarOrderCustomer;
import com.tayyarah.train.model.TrainOrderCustomer;

@Entity
@Table(name = "car_order_row")
public class CarOrderRow extends Timestampable {

	private static final long serialVersionUID = 1L;

	@Transient
	private Company company;

	@Transient
	private String carBookingDate;

	@Column(name = "company_Entity_Id")
	private Long companyEntityId; 

	@Column(name = "booking_date")
	@Temporal(TemporalType.DATE)
	private Date bookingDate;

	@Column(name = "remarks")
	private String remarks;
	@Column(name = "emp_name")
	private String empNmae;

	@Transient
	private String travelDateTemp;

	@Column(name = "confirmation_number")
	private String confirmationNumber;
	@Column(name = "car_company_name")
	private String carCompanyName;

	@Column(name = "location")
	private String location;

	@Column(name = "travel_date")
	@Temporal(TemporalType.DATE)
	private Date travelDate;

	@Column(name = "tollOr_parking_charges", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal tollOrParkingCharges;
	@Column(name = "driver_allowance_day", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal driverAllowanceDay;
	@Column(name = "driver_allowance_night", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal driverAllowanceNight;
	@Column(name = "extra_km")
	private String extraKM;
	@Column(name = "extra_hours")
	private String extraHours;
	@Column(name = "supplier_name")
	private String supplierName;
	@Column(name = "supplier_price", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal supplierPrice;
	@Column(name = "base_price", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal basePrice;
	@Column(name = "other_taxes", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal otherTaxes;
	@Column(name = "management_fee", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal managementFee;
	@Column(name = "convenience_fee", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal convenienceFee;
	@Column(name = "service_tax", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal serviceTax;
	@Column(name = "total_amount", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totalAmount;

	@Column(name = "invoice_no")
	private String invoiceNo;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "status_action")
	private String statusAction;

	@Column(name = "payment_status")
	private String paymentStatus;
	@Column(name = "orderId")
	private String orderId;
	@Column(name = "transaction_key")
	private String transactionKey;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "api_comments")
	private String apiComments; 

	@Column(name = "user_comments")
	private String userComments;

	@Column(name = "company_id")
	private String companyId; 
	@Column(name = "confid_id")
	private String configId; 
	@Column(name = "paid_by")
	private String paidBy; 
	@Column(name = "booking_currency")
	private String bookingCurrency;
	@Column(name = "processing_fees", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal processingFees;
	@Column(name = "mark_up", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal markUp;
	@Column(name = "taxes",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal taxes;

	@Column(name = "is_creditnote_issues",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean creditNoteIssued;
	@Column(name = "is_order_updated",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean orderUpdated;
	@Column(name = "is_order_requested",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean orderRequested;
	@Column(name = "base_to_booking_exchange_rate",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal baseToBookingExchangeRate;

	@Column(name = "api_to_base_exchange_rate" ,columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal apiToBaseExchangeRate;


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private OrderCustomer orderCustomer;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = CarOrderRowServiceTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "carOrderRow_serviceTax_id", referencedColumnName = "id")
	private CarOrderRowServiceTax carOrderRowServiceTax ;
	@Column(name = "booking_mode")
	private String bookingMode; 
	@Column(name="cancel_mode")
	private String cancelMode;

	@Column(name="recieved_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal recievedAmount;

	@Column(name="tot_invoice_amount",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal totInvoiceAmount;

	@Column(name="is_knock_off",columnDefinition = "BOOLEAN DEFAULT false")
	private boolean knockOff;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = CarOrderRowGstTax.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "carOrderRow_gstTax_id", referencedColumnName = "id")
	private CarOrderRowGstTax carOrderRowGstTax ;

	@Column(name = "total_gst_tax",columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal TotalGstTax;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carOrderRow",targetEntity = CarOrderCustomer.class)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CarOrderCustomer> carOrderCustomerList;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dynamic_rm_config_id", referencedColumnName = "id")
	private  CarOrderRowRmConfigStruct carOrderRowRmConfigStruct;
	public List<CarOrderCustomer> getCarOrderCustomerList() {
		return carOrderCustomerList;
	}
	public CarOrderRowRmConfigStruct getCarOrderRowRmConfigStruct() {
		return carOrderRowRmConfigStruct;
	}
	public void setCarOrderRowRmConfigStruct(CarOrderRowRmConfigStruct carOrderRowRmConfigStruct) {
		this.carOrderRowRmConfigStruct = carOrderRowRmConfigStruct;
	}
	public void setCarOrderCustomerList(List<CarOrderCustomer> carOrderCustomerList) {
		this.carOrderCustomerList = carOrderCustomerList;
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

	public String getTravelDateTemp() {
		return travelDateTemp;
	}


	public void setTravelDateTemp(String travelDateTemp) {
		this.travelDateTemp = travelDateTemp;
	}


	public String getConfirmationNumber() {
		return confirmationNumber;
	}


	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}


	public String getCarCompanyName() {
		return carCompanyName;
	}


	public void setCarCompanyName(String carCompanyName) {
		this.carCompanyName = carCompanyName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}





	public Date getTravelDate() {
		return travelDate;
	}


	public void setTravelDate(String travelDateTemp) {
		this.travelDate =DateConversion.StringToDate(travelDateTemp);
	}


	public BigDecimal getTollOrParkingCharges() {
		return tollOrParkingCharges;
	}


	public void setTollOrParkingCharges(BigDecimal tollOrParkingCharges) {
		this.tollOrParkingCharges = tollOrParkingCharges;
	}


	public BigDecimal getDriverAllowanceDay() {
		return driverAllowanceDay;
	}


	public void setDriverAllowanceDay(BigDecimal driverAllowanceDay) {
		this.driverAllowanceDay = driverAllowanceDay;
	}


	public BigDecimal getDriverAllowanceNight() {
		return driverAllowanceNight;
	}


	public void setDriverAllowanceNight(BigDecimal driverAllowanceNight) {
		this.driverAllowanceNight = driverAllowanceNight;
	}




	public String getExtraKM() {
		return extraKM;
	}


	public void setExtraKM(String extraKM) {
		this.extraKM = extraKM;
	}


	public String getExtraHours() {
		return extraHours;
	}


	public void setExtraHours(String extraHours) {
		this.extraHours = extraHours;
	}


	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}


	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}


	public BigDecimal getBasePrice() {
		return basePrice;
	}


	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}


	public BigDecimal getOtherTaxes() {
		return otherTaxes;
	}


	public void setOtherTaxes(BigDecimal otherTaxes) {
		this.otherTaxes = otherTaxes;
	}


	public BigDecimal getManagementFee() {
		return managementFee;
	}


	public void setManagementFee(BigDecimal managementFee) {
		this.managementFee = managementFee;
	}


	public BigDecimal getConvenienceFee() {
		return convenienceFee;
	}


	public void setConvenienceFee(BigDecimal convenienceFee) {
		this.convenienceFee = convenienceFee;
	}


	public BigDecimal getServiceTax() {
		return serviceTax;
	}


	public void setServiceTax(BigDecimal serviceTax) {
		this.serviceTax = serviceTax;
	}


	public BigDecimal getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public String getStatusAction() {
		return statusAction;
	}


	public void setStatusAction(String statusAction) {
		this.statusAction = statusAction;
	}


	public String getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getTransactionKey() {
		return transactionKey;
	}


	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
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


	public String getPaidBy() {
		return paidBy;
	}


	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}


	public String getBookingCurrency() {
		return bookingCurrency;
	}


	public void setBookingCurrency(String bookingCurrency) {
		this.bookingCurrency = bookingCurrency;
	}


	public BigDecimal getProcessingFees() {
		return processingFees;
	}


	public void setProcessingFees(BigDecimal processingFees) {
		this.processingFees = processingFees;
	}


	public BigDecimal getMarkUp() {
		return markUp;
	}


	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}


	public BigDecimal getTaxes() {
		return taxes;
	}


	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}


	public boolean isCreditNoteIssued() {
		return creditNoteIssued;
	}


	public void setCreditNoteIssued(boolean creditNoteIssued) {
		this.creditNoteIssued = creditNoteIssued;
	}


	public boolean isOrderUpdated() {
		return orderUpdated;
	}


	public void setOrderUpdated(boolean orderUpdated) {
		this.orderUpdated = orderUpdated;
	}


	public boolean isOrderRequested() {
		return orderRequested;
	}


	public void setOrderRequested(boolean orderRequested) {
		this.orderRequested = orderRequested;
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


	public OrderCustomer getOrderCustomer() {
		return orderCustomer;
	}


	public void setOrderCustomer(OrderCustomer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}


	public CarOrderRowServiceTax getCarOrderRowServiceTax() {
		return carOrderRowServiceTax;
	}


	public void setCarOrderRowServiceTax(CarOrderRowServiceTax carOrderRowServiceTax) {
		this.carOrderRowServiceTax = carOrderRowServiceTax;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Company getCompany() {
		if(getCompanyId()!=null) 
			company=new CompanyDAO().getCompanyProfile(Integer.parseInt(getCompanyId()));
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}



	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getEmpNmae() {
		return empNmae;
	}


	public void setEmpNmae(String empNmae) {
		this.empNmae = empNmae;
	}


	public String getBookingMode() {
		return bookingMode;
	}


	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getCarBookingDate() {
		return carBookingDate;
	}


	public void setCarBookingDate(String carBookingDate) {
		this.carBookingDate = carBookingDate;
	}


	public String getCancelMode() {
		return cancelMode;
	}


	public void setCancelMode(String cancelMode) {
		this.cancelMode = cancelMode;
	}


	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public CarOrderRowGstTax getCarOrderRowGstTax() {
		return carOrderRowGstTax;
	}
	public void setCarOrderRowGstTax(CarOrderRowGstTax carOrderRowGstTax) {
		this.carOrderRowGstTax = carOrderRowGstTax;
	}
	public Long getCompanyEntityId() {
		return companyEntityId;
	}
	public void setCompanyEntityId(Long companyEntityId) {
		this.companyEntityId = companyEntityId;
	}
	public BigDecimal getTotalGstTax() {
		return TotalGstTax;
	}
	public void setTotalGstTax(BigDecimal totalGstTax) {
		TotalGstTax = totalGstTax;
	}

}
