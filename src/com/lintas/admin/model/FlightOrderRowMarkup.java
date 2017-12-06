package com.lintas.admin.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.lintas.admin.flight.model.FlightOrderRow;

@Entity
@Table(name = "flight_order_row_markup")
@Proxy(lazy = false)
public class FlightOrderRowMarkup  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private FlightOrderRow flightOrderRow;
	/*@Column(name="order_id")//orderid of flight
private String orderId;*/
	@Column(name="company_id")// compnyid
	private String CompanyId;
	@Column(name="mark_up", columnDefinition="decimal(20,10) default '0.0'")//own markup which he set
	private BigDecimal markUp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*public String getOrderId() {
return orderId;
}
public void setOrderId(String orderId) {
this.orderId = orderId;
}*/
	public String getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}
	public BigDecimal getMarkUp() {
		return markUp;
	}
	public void setMarkUp(BigDecimal markUp) {
		this.markUp = markUp;
	}
	public FlightOrderRow getFlightOrderRow() {
		return flightOrderRow;
	}

	public void setFlightOrderRow(FlightOrderRow flightOrderRow) {
		this.flightOrderRow = flightOrderRow;
	}



}
