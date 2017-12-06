package com.lintas.admin.flight.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lintas.admin.common.model.OrderCustomer;
import com.lintas.admin.common.model.Timestampable;

 
@Entity
@Table(name = "flight_order_customer_pricebreakup")
@JsonIgnoreProperties(ignoreUnknown = true)
@Proxy(lazy = false)
public class FlightOrderCustomerPriceBreakup extends Timestampable implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private FlightOrderRow flightOrderRow;


	@Column(name = "tax_description")
	private String tax_description;

	@Column(name = "description")
	private String description;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "baseFare", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal baseFare;

	@Column(name = "total", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal total ;

	@Column(name = "tax", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal tax;

	@Column(name = "markup")
	private String markup;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private OrderCustomer flightCustomer;
	private String roundupvalue;

	@Column(name = "supplier_discount", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal supplierDiscount;

	@Column(name = "system_discount", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal systemDiscount;

	@Column(name = "published_discount", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal publishedDiscount;

	@Column(name = "yq_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal YQTax;

	@Column(name = "yr_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal YRTax;

	@Column(name = "wo_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal WOTax;

	@Column(name = "psf_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal PSFTax;

	@Column(name = "udf_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal UDFTax;

	@Column(name = "jn_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal JNTax;

	@Column(name = "in_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal INTax;

	@Column(name = "transaction_fee", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal transactionFee;

	@Column(name = "g1_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal G1Tax;

	@Column(name = "f2_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal F2Tax;

	@Column(name = "f6_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal F6Tax;

	@Column(name = "zr_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal ZRTax;

	@Column(name = "yc_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal YCTax;

	@Column(name = "us_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal USTax;

	@Column(name = "xa_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal XATax;

	@Column(name = "xy_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal XYTax;

	@Column(name = "ay_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal AYTax;

	@Column(name = "xf_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal XFTax;

	@Column(name = "k3_tax", columnDefinition="decimal(20,4) default '0.0'")
	private BigDecimal K3Tax;


	public BigDecimal getK3Tax() {
		return K3Tax;
	}

	public void setK3Tax(BigDecimal k3Tax) {
		K3Tax = k3Tax;
	}
	public BigDecimal getG1Tax() {
		return G1Tax;
	}

	public BigDecimal getF2Tax() {
		return F2Tax;
	}

	public BigDecimal getF6Tax() {
		return F6Tax;
	}

	public BigDecimal getZRTax() {
		return ZRTax;
	}

	public BigDecimal getYCTax() {
		return YCTax;
	}

	public BigDecimal getUSTax() {
		return USTax;
	}

	public BigDecimal getXATax() {
		return XATax;
	}

	public BigDecimal getXYTax() {
		return XYTax;
	}

	public BigDecimal getAYTax() {
		return AYTax;
	}

	public BigDecimal getXFTax() {
		return XFTax;
	}

	public void setG1Tax(BigDecimal g1Tax) {
		G1Tax = g1Tax;
	}

	public void setF2Tax(BigDecimal f2Tax) {
		F2Tax = f2Tax;
	}

	public void setF6Tax(BigDecimal f6Tax) {
		F6Tax = f6Tax;
	}

	public void setZRTax(BigDecimal zRTax) {
		ZRTax = zRTax;
	}

	public void setYCTax(BigDecimal yCTax) {
		YCTax = yCTax;
	}

	public void setUSTax(BigDecimal uSTax) {
		USTax = uSTax;
	}

	public void setXATax(BigDecimal xATax) {
		XATax = xATax;
	}

	public void setXYTax(BigDecimal xYTax) {
		XYTax = xYTax;
	}

	public void setAYTax(BigDecimal aYTax) {
		AYTax = aYTax;
	}

	public void setXFTax(BigDecimal xFTax) {
		XFTax = xFTax;
	}

	public BigDecimal getYQTax() {
		return YQTax;
	}

	public void setYQTax(BigDecimal yQTax) {
		YQTax = yQTax;
	}

	public BigDecimal getYRTax() {
		return YRTax;
	}

	public void setYRTax(BigDecimal yRTax) {
		YRTax = yRTax;
	}

	public BigDecimal getWOTax() {
		return WOTax;
	}

	public void setWOTax(BigDecimal wOTax) {
		WOTax = wOTax;
	}

	public BigDecimal getPSFTax() {
		return PSFTax;
	}

	public void setPSFTax(BigDecimal pSFTax) {
		PSFTax = pSFTax;
	}

	public BigDecimal getUDFTax() {
		return UDFTax;
	}

	public void setUDFTax(BigDecimal uDFTax) {
		UDFTax = uDFTax;
	}

	public BigDecimal getJNTax() {
		return JNTax;
	}

	public void setJNTax(BigDecimal jNTax) {
		JNTax = jNTax;
	}

	public BigDecimal getINTax() {
		return INTax;
	}

	public void setINTax(BigDecimal iNTax) {
		INTax = iNTax;
	}

	public BigDecimal getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}

	public String getTax_description() {
		return tax_description;
	}

	public void setTax_description(String tax_description) {
		this.tax_description = tax_description;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getMarkup() {
		return markup;
	}

	public void setMarkup(String markup) {
		this.markup = markup;
	}

	public String getRoundupvalue() {
		return roundupvalue;
	}

	public void setRoundupvalue(String roundupvalue) {
		this.roundupvalue = roundupvalue;
	}

	public FlightOrderRow getOrderRow() {
		return flightOrderRow;
	}

	public void setOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}

	public OrderCustomer getCustomer() {
		return flightCustomer;
	}

	public void setCustomer(OrderCustomer flightCustomer) {
		this.flightCustomer = flightCustomer;
	}

	public FlightOrderRow getFlightOrderRow() {
		return flightOrderRow;
	}

	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderCustomer getFlightCustomer() {
		return flightCustomer;
	}

	public void setFlightCustomer(OrderCustomer flightCustomer) {
		this.flightCustomer = flightCustomer;
	}

	public BigDecimal getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(BigDecimal baseFare) {
		this.baseFare = baseFare;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getSupplierDiscount() {
		return supplierDiscount;
	}

	public void setSupplierDiscount(BigDecimal supplierDiscount) {
		this.supplierDiscount = supplierDiscount;
	}

	public BigDecimal getSystemDiscount() {
		return systemDiscount;
	}

	public void setSystemDiscount(BigDecimal systemDiscount) {
		this.systemDiscount = systemDiscount;
	}

	public BigDecimal getPublishedDiscount() {
		return publishedDiscount;
	}

	public void setPublishedDiscount(BigDecimal publishedDiscount) {
		this.publishedDiscount = publishedDiscount;
	}

}
