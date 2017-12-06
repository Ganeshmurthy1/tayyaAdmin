package com.lintas.admin.model;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lintas.admin.hotel.model.HotelOrderRow;

@Entity(name="hotel_order_row_markup")
public class HotelOrderRowMarkup {
	@Id
	@GeneratedValue
	Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_row_id", referencedColumnName = "id")
	private HotelOrderRow hotelOrderRow;

	@Column(name="company_id")
	private String CompanyId;
	@Column(name="mark_up", columnDefinition="decimal(20,10) default '0.0'")
	private BigDecimal markUp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

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
	public HotelOrderRow getHotelOrderRow() {
		return hotelOrderRow;
	}
	public void setHotelOrderRow(HotelOrderRow hotelOrderRow) {
		this.hotelOrderRow = hotelOrderRow;
	}
	 
}
